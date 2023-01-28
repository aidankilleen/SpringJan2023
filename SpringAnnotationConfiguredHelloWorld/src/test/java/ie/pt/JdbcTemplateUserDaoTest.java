package ie.pt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class JdbcTemplateUserDaoTest {

    AnnotationConfigApplicationContext ctx;
    UserDao dao;

    @Before
    public void setup() {
        ctx = new AnnotationConfigApplicationContext();

        ctx.scan("ie.pt");
        ctx.refresh();

        dao = ctx.getBean(UserDao.class);
    }

    @Test
    public void testGetUsers() {

        List<User> users = dao.getUsers();

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUser() throws UserDaoException {
        User u3 = dao.getUser(3);
        System.out.println(u3);
        Assert.assertNotNull(u3);
    }

    @Test (expected=UserDaoException.class)
    public void testGetUserNotFound() throws UserDaoException {
        User uNotFound = dao.getUser(999);
    }


    @Test
    public void testDeleteUser() throws UserDaoException {
        dao.deleteUser(3);
    }

    @Test
    public void testAddUser() throws UserDaoException {

        User newUser = new User("NEW", "new.user@gmail.com", true);

        User addedUser = dao.addUser(newUser);

        System.out.println(addedUser);


    }

    @Test
    public void testUpdateUser() throws UserDaoException {

        User user = dao.getUser(1);

        user.setName("CHANGED");

        dao.updateUser(user);


    }



}
