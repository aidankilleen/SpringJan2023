package ie.pt.springboothelloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SqliteUserDao
 *
 * Data Access Object for User records from the users table in the database.
 * The database connection is opened when the object is instantiated.
 * Please call the close() method to close the connection.
 */

@Repository
public class SqliteUserDao implements UserDao {

    private Connection conn;

    @Value("${userdata.databasefile:C:/data/8westjava/users.db}")
    private String databaseFile = "";



    /**
     * Instantiate a new UserDao
     * @param databaseFile full path to database file C:/data/8westjava/users.db for example
     */
    public SqliteUserDao(String databaseFile) throws UserDaoException {

        System.out.println("constructor called");
        System.out.println(this.databaseFile);



        this.databaseFile = databaseFile;

    }

    /**
     * Instantiate a new UserDao
     * Using default database path of C:/data/8westjava/users.db
     */
    public SqliteUserDao() throws UserDaoException {

        this("C:/data/8westjava/users.db");
    }

    @PostConstruct
    public void init() throws UserDaoException {
        System.out.println("init() called");
        System.out.println(this.databaseFile);

        String connectionString = "jdbc:sqlite:" + databaseFile;
        try {
            conn = DriverManager.getConnection(connectionString);

            // TODO - is there a more elegant solution to verify the connection?
            Statement stmt = conn.createStatement();
            stmt.executeQuery("SELECT * FROM users");
            stmt.close();

        } catch (SQLException throwables) {
            throw new UserDaoException("Database not found");
        }

    }

    /**
     * close()
     *
     * Close the database connection
     * NB: database must be closed when you are finished with it.
     */
    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * getUsers()
     * @return list of users in database
     */
    @Override
    public List<User> getUsers() {

        List<User> users = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                boolean active = rs.getBoolean("active");

                User u = new User(id, name, email, active);
                users.add(u);
            }
            rs.close();
            stmt.close();
        }
        catch(SQLException ex) {
            //ex.printStackTrace();
            // do nothing - puts a lot of responsibility on me:-(
        }

        return users;
    }

    /**
     * get a single user from the database
     * @param id the id of the user to retrieve
     * @return the User object
     * @throws UserDaoException if user is not found
     */
    @Override
    public User getUser(int id) throws UserDaoException {
        User u = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE id = " + id);

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                boolean active = rs.getBoolean("active");

                u = new User (id, name, email, active);
            } else {
                throw new UserDaoException("User not found");
            }

        } catch (SQLException  ex) {
            ex.printStackTrace();
        } finally {
            try{
                rs.close();
                stmt.close();
            } catch(Exception ex){

            }
        }
        return u;
    }

    /**
     * Delete the specified user object from the database.
     * @param id the id of the user to delete
     * @throws UserDaoException if no user with this id exists
     */
    @Override
    public void deleteUser(int id) throws UserDaoException {

        try {
            Statement stmt = conn.createStatement();
            if (stmt.executeUpdate("DELETE FROM users WHERE id = " + id) == 0) {

                // no user was deleted
                throw new UserDaoException("User not found. No records deleted");
            }
        }
        catch(Exception ex) {
            //ex.printStackTrace();
        }
    }

    /**
     * Update a user object using the id of the object specified
     * @param userToUpdate the updated user object
     * @return the user object after the updates
     * @throws UserDaoException in case of errors.
     */
    @Override
    public User updateUser(User userToUpdate) throws UserDaoException {

        String sql = "UPDATE users SET name = ?, email = ?, active = ? WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, userToUpdate.getName());
            stmt.setString(2, userToUpdate.getEmail());
            stmt.setInt(3, userToUpdate.isActive() ? 1 : 0);
            stmt.setInt(4, userToUpdate.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new UserDaoException("Update failed");
        }
        return userToUpdate;
    }

    /**
     * insecure version of updateUser function which is susceptible to SQL injection
     * attacks and can't have a name with an apostrophe
     * @param userToUpdate
     * @return
     */
    @Deprecated
    public User updateUserInsecure(User userToUpdate) {

        try {
            Statement stmt = conn.createStatement();

            String sql = "UPDATE users SET name = '" + userToUpdate.getName() + "', " +
                         "email = '" + userToUpdate.getEmail() + "', " +
                         "active = " + (userToUpdate.isActive() ? 1 : 0) + " " +
                         "WHERE id = " + userToUpdate.getId();

            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return userToUpdate;
    }

    /**
     * Add a new user object to the database. If the new User has an id of -1 then
     * an id will be assigned by the database. If the id is not -1 then the specified
     * id will be used.
     * @param newUser the new user object
     * @return the new user object including the id
     * @throws UserDaoException if a user with this id already exists
     */
    @Override
    public User addUser(User newUser) throws UserDaoException {

        String sql;

        if (newUser.getId() != -1) {
            sql = "INSERT INTO users (name, email, active, id) VALUES(?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO users (name, email, active) VALUES(?, ?, ?)";
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            // fill in the parameters
            stmt.setString(1, newUser.getName());
            stmt.setString(2, newUser.getEmail());
            stmt.setInt(3, newUser.isActive() ? 1 : 0);

            if (newUser.getId() != -1) {
                stmt.setInt(4, newUser.getId());
            }
            stmt.executeUpdate();
            stmt.close();

            // read the id of the new user
            sql = "SELECT last_insert_rowid()";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                newUser.setId(id);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new UserDaoException("Insert failed, duplicate id exists");
        }
        return newUser;
    }

    /**
     * insecure version of addUser function which is susceptible to SQL injection
     * attacks and can't have a name with an apostrophe
     * @param newUser
     * @return
     */
    @Deprecated
    public User addUserInsecure(User newUser) throws UserDaoException {

        try {
            Statement stmt = conn.createStatement();

            String sql;

            if (newUser.getId() == -1) {
                sql = "INSERT INTO users (name, email, active) " +
                        "VALUES ('" + newUser.getName() + "'," +
                        "'" + newUser.getEmail() + "', " +
                        (newUser.isActive() ? 1 : 0) + ")";
            } else {
                sql = "INSERT INTO users (id, name, email, active) " +
                        "VALUES (" + newUser.getId() +
                        ",'" + newUser.getName() + "'," +
                        "'" + newUser.getEmail() + "', " +
                        (newUser.isActive() ? 1 : 0) + ")";
            }
            stmt.executeUpdate(sql);

            // get the id of the newly added user
            sql = "SELECT last_insert_rowid()";

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt(1);
                newUser.setId(id);
            }
        } catch(SQLException ex) {
            // TODO is this the only way to fail?
            throw new UserDaoException("Failed to insert user - duplicate id??");
        }
        return newUser;
    }
}
