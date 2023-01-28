package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UserDaoException {
        System.out.println( "Spring Framework Hello World!" );


        //TestBean tb = new TestBean("this is a bean");
        //System.out.println(tb);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-beans.xml");

        UserDao dao = ctx.getBean(UserDao.class);

        List<User> users = dao.getUsers();

        for (User u: users) {
            System.out.println(u);
        }

        dao.close();



        TestBean tb = ctx.getBean(TestBean.class);

        System.out.println(tb);

        String message = ctx.getBean("welcome", String.class);
        System.out.println(message);

        String goodbye = ctx.getBean("goodbye", String.class);
        System.out.println(goodbye);



    }
}
