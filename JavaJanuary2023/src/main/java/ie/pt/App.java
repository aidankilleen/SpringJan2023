package ie.pt;

import java.sql.*;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Database Test" );

        // connect to a database
        String connectionString = "jdbc:sqlite:C:/data/8westjava/users.db";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionString);

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
