package ie.pt.springbootrestinvestigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.sqlite.SQLiteDataSource;
import sun.invoke.empty.Empty;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;



public class JdbcTemplateUserDao implements UserDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserDao() {

    }

    @PostConstruct
    public void setup() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void close() {

    }

    @Override
    public List<User> getUsers() {
        List<User> users = jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
        return users;
    }

    @Override
    public User getUser(int id) throws UserDaoException {

        User user;

        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = " + id, new UserRowMapper());
        }
        catch(EmptyResultDataAccessException ex) {
            throw new UserDaoException("User not found");
        }
        return user;
    }

    @Override
    public void deleteUser(int id) throws UserDaoException {

        if (jdbcTemplate.update("DELETE FROM users WHERE id = "+ id) == 0) {
            throw new UserDaoException("User not found");
        };
    }

    @Override
    public User updateUser(User userToUpdate) throws UserDaoException {
        jdbcTemplate.update(new PreparedStatementCreator(){
                                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                                    PreparedStatement ps=connection.prepareStatement(
                                            "UPDATE users SET name = ?, email = ?, active = ? WHERE id = ?",new String[]{"id"});
                                    int index=1;
                                    ps.setString(index++,userToUpdate.getName());
                                    ps.setString(index++,userToUpdate.getEmail());
                                    ps.setInt(index++,userToUpdate.isActive() ? 1 : 0);
                                    ps.setInt(index++, userToUpdate.getId());
                                    return ps;
                                }
                            }
                );
        return userToUpdate;
    }

    @Override
    public User addUser(User newUser) throws UserDaoException {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator(){
                                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                                    PreparedStatement ps=connection.prepareStatement(
                                            "INSERT INTO users (name, email, active) values (?, ?, ?)",new String[]{"id"});
                                    int index=1;
                                    ps.setString(index++,newUser.getName());
                                    ps.setString(index++,newUser.getEmail());
                                    ps.setInt(index++,newUser.isActive() ? 1 : 0);
                                    return ps;
                                }
                            }
                ,keyHolder);
        newUser.setId(keyHolder.getKey().intValue());
        return newUser;
    }
}
