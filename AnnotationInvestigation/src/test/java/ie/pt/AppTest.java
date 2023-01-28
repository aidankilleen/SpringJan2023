package ie.pt;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */


public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testUserSetters() {

        User u = new User();

        u.setId(1);
        u.setName("Aidan");
        u.setEmail("aidan@gmail.com");
        u.setActive(true);

        System.out.println(u);
    }

    @Test
    public void testUserConstructor() throws IllegalAccessException {

        User u = new User(1, "Alice", "alice@gmail.com", false);
        System.out.println(u);

        System.out.println(JSON.stringify(u));
    }

    @Test
    public void testJsonStringify() throws IllegalAccessException {

        Manager m = new Manager(1, "Jane Jones", "Microsoft", 15, true, "Ireland", "P@$$word");
        System.out.println(JSON.stringify(m));
    }


}
