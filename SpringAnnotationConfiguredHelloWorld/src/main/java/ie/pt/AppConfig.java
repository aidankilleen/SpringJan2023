package ie.pt;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public TestBean getTestBean() {
        return new TestBean("Instantiated by Spring - via AppConfig");
    }

    @Bean("greeting")
    public String getMessage() {
        return "This is a message";
    }

    @Bean("goodbye")
    public String getGoodbye() {
        return "Adios";
    }


    @Bean
    public DataSource getSqliteDataSource() {

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:C:/data/8westjava/users.db");

        return dataSource;
    }


    public DataSource getSqlServerDataSource() {

        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setURL("jdbc:sqlserver://***.database.windows.net:1433;database=***;user=***@***;password=*****;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

        return dataSource;
    }

    /*
    @Bean
    public UserDao getDataAccessObject() throws UserDaoException {

        return new SqliteUserDao("C:/data/8westjava/differentdb.db");
    }

     */
/*
    @Bean
    public UserService getUserService() {
        return new UserService();
    }

 */
}
