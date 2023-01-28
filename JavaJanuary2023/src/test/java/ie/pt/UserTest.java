package ie.pt;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testCreateUser() {
        User u = new User();
        assertNotNull(u);
    }

    @Test
    public void testUserName() {

        User u = new User(1, "Alice", "alice@gmail.com", false);
        assertEquals(u.getName(), "Alice");
    }

    @Test
    public void testCompare() {

        User u = new User (1, "Alice", "alice@gmail.com", true);
        User u2 = new User(1, "Alice", "alice@gmail.com", true);

        //System.out.println(u);
        //System.out.println(u2);


        assertTrue(u.equals(u2));
    }

    @Test
    public void testHashcode() {

        User u = new User (1, "Alice", "alice@gmail.com", true);
        User u2 = new User(1, "Alice", "alice@gmail.com", true);

        //System.out.println(u.hashCode());
        //System.out.println(u2.hashCode());

        assertEquals(u.hashCode(), u2.hashCode());

    }
}
