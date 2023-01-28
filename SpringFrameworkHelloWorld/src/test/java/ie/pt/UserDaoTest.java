package ie.pt;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {

    private UserDao dao;

    public UserDaoTest() {
    }

    @Before
    public void init() throws UserDaoException {
        dao = new InMemoryUserDao();

        dao.addUser(new User(90000, "Test User 1", "test.user1@gmail.com", true));
        dao.addUser(new User(90001, "Test User 2", "test.user2@gmail.com", false));
        dao.addUser(new User(90002, "Test User 3", "test.user3@gmail.com", false));
        dao.addUser(new User(90003, "Test User 4", "test.user4@gmail.com", true));
    }

    @After
    public void cleanup() {
        dao.close();
    }

    /*
    @BeforeClass
    public static void setupTestUsers() throws UserDaoException {
        UserDao dao = new SqliteUserDao();

        dao.addUser(new User(90000, "Test User 1", "test.user1@gmail.com", true));
        dao.addUser(new User(90001, "Test User 2", "test.user2@gmail.com", false));
        dao.addUser(new User(90002, "Test User 3", "test.user3@gmail.com", false));
        dao.addUser(new User(90003, "Test User 4", "test.user4@gmail.com", true));

        dao.close();
    }

    @AfterClass
    public static void deleteTestUsers() throws UserDaoException {
        UserDao dao = new SqliteUserDao();

        try {
            dao.deleteUser(99999);

            dao.deleteUser(90000);
            dao.deleteUser(90000);
            dao.deleteUser(90001);
            dao.deleteUser(90002);
            dao.deleteUser(90003);

        } catch (UserDaoException ex) {
            // do nothing
        }

        dao.close();
    }
    */
    @Test
    public void testCreateDao() {
        List<User> users = dao.getUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void testGetUser() throws UserDaoException {

        User u = dao.getUser(90002);
        assertNotNull(u);
        assertEquals(u.getName(), "Test User 3");

        dao.close();
    }

    @Test
    public void testDeleteUser() throws UserDaoException {
        // delete the first user

        List<User> users = dao.getUsers();
        int count = users.size();
        User userToDelete = users.get(0);

        dao.deleteUser(userToDelete.getId());
        int newCount = dao.getUsers().size();
        assertEquals(count, newCount + 1);

        // restore the deleted user
        dao.addUser(userToDelete);

    }

    @Test(expected=UserDaoException.class)
    public void testDeleteNotFound() throws UserDaoException {
        dao.deleteUser(9999);
    }


    @Test
    public void testUpdateUser() throws UserDaoException {

        List<User> users = dao.getUsers();

        User userToModify = users.get(0);

        // make a copy not an assignment
        User originalUser = new User(userToModify);

        userToModify.setName("CHANGED");
        userToModify.setEmail("changed@gmail.com");
        userToModify.setActive(false);

        dao.updateUser(userToModify);

        User updatedUser = dao.getUser(userToModify.getId());
        assertEquals(updatedUser.getName(), "CHANGED");

        // put the user object back to its original state
        dao.updateUser(originalUser);
    }

    @Test
    public void testAddUser() throws UserDaoException {

        User newUser = new User("NEW USER", "new.user@gmail.com", true);

        User addedUser = dao.addUser(newUser);

        System.out.println(addedUser);
        assertNotEquals(addedUser.getId(), -1);

        //dao.deleteUser(addedUser.getId());
    }

    @Test
    public void testAddUserWithId() throws UserDaoException {

        User newUser = new User(99998, "User With Id", "userwithid@gmail.com", true);

        User addedUser = dao.addUser(newUser);
        System.out.println(addedUser);

        assertEquals(addedUser.getId(), 99998);
        dao.deleteUser(addedUser.getId());
    }

    @Test(expected = UserDaoException.class)
    public void testDuplicateId() throws UserDaoException {

        // delete user 99999 if it exists
        try {
            dao.deleteUser(99999);

        } catch(UserDaoException ex) {
            // do nothing
        }

        User newUser = new User(99999, "User With Id", "userwithid@gmail.com", true);

        dao.addUser(newUser);
        dao.addUser(newUser);

        // delete user 99999 to tidy up - can't work
    }

    @Test
    public void testIrishSurname() throws UserDaoException {


        User newUser = new User("John O'Sullivan", "john@gmail.com", true);

        User addedUser = dao.addUser(newUser);

        assertNotEquals(newUser.getId(), -1);
    }

    @Test
    public void testIrishSurnameInUpdate() throws UserDaoException {

        User userToUpdate = dao.getUser(90003);
        userToUpdate.setName("John O'Sullivan");
        dao.updateUser(userToUpdate);
        User updatedUser = dao.getUser(90003);
        assertEquals(userToUpdate.getName(), updatedUser.getName());
    }

    @Test
    public void testSQLInjection() throws UserDaoException {

        //User newUser = new User(
        //        "','',0);  --",
        //        "new.user@gmail.com",
        //        false
        //);
        //User newUser = new User(
        //"','',0); delete from users where id= 2; --",
        //        "new.user@gmail.com",
        //        false
        //);
        //User newUser = new User(
        //        "','',0); delete from users; --",
        //        "new.user@gmail.com",
        //        false
        //);
        User newUser = new User(
                "','',0); drop table users; --",
                "new.user@gmail.com",
                false
        );

        dao.addUser(newUser);

        assertNotEquals(newUser.getId(), -1);
    }

    @Test
    public void testSQLInjectionInUpdate() throws UserDaoException {

        User userToUpdate = new User(90001,
                "'; DELETE FROM users WHERE id = 90001 --",
                "new.user@gmail.com",
                false
        );

        dao.updateUser(userToUpdate);
        User updatedUser = dao.getUser(90001);
        assertNotNull(updatedUser);
        assertEquals(userToUpdate.getName(), updatedUser.getName());
    }


    @Test(expected = UserDaoException.class)
    public void testGetUserNotFound() throws UserDaoException {

        User user = dao.getUser(90010);

        System.out.println(user.getName());
    }

    @Test
    public void testUsingDifferentDatabase() throws UserDaoException {

        UserDao differentDao = new SqliteUserDao("C:/data/8westjava/differentdb.db");
        List<User> users = differentDao.getUsers();

        User u = users.get(0);
        assertEquals(u.getName(), "Zoe");
        differentDao.close();
    }

    @Test (expected = UserDaoException.class)
    public void testDatabaseNotFound() throws UserDaoException {

        UserDao notFoundDao= new SqliteUserDao("C:/data/doesntexist.db");
    }

    @Test
    public void testLongName() throws UserDaoException {

        char[] charArray = new char[300];
        Arrays.fill(charArray, '*');

        String longName = new String(charArray);

        User newUser = new User(longName, "long.name@gmail.com", false);

        User addedUser = dao.addUser(newUser);

        // reread the added user

        User actualAddedUser = dao.getUser(addedUser.getId());

        // Note:the actual added user should have the same name length
        // if not then the database truncated the name
        // seems to be fine for SQLite - not sure about others
        assertEquals(actualAddedUser.getName().length(), newUser.getName().length());

    }


}
