package ie.pt.springboothelloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringboothelloworldApplicationTests {

	@Autowired
	UserDao dao;

	@Autowired
	TestBean tb;

	@Test
	void contextLoads() {

		assertEquals("Test Bean", tb.getMessage());
	}

	@Test
	void testUserDao() {

		List<User> users = dao.getUsers();
		assertNotNull(users);

		for (User user : users) {
			System.out.println(user);
		}
	}

}
