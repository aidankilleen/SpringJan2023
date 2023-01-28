package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UserDaoException {
        System.out.println( "Annotation Driven Configuration of Spring App" );

        //ApplicationContext ctx = new AnnotationConfigApplicationContext();

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.scan("ie.pt");
        ctx.refresh();

        //UserService userService = ctx.getBean(UserService.class);

        UserDao dao = ctx.getBean(UserDao.class);

        List<User> users = dao.getUsers();

        for (User user : users) {

            System.out.println(user);
        }

        User u3 = dao.getUser(3);

        System.out.println(u3);

        u3.setName("laura");

        dao.updateUser(u3);

        User u4 = new User("Fred", "fred@gmail.com", true);

        u4 = dao.addUser(u4);

        System.out.println(u4);
    }
}
