package ie.pt.springbootjpainvestigation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringbootjpainvestigationApplicationTests {

	@Autowired
	UserRepository repo;

	@Autowired
	UserDao dao;


	@Test
	void testGetAllUsers() {

		List<User> users = repo.findAll();
		for (User user: users) {
			System.out.println(user);
		}
		assertNotEquals(users.size(), 0);
	}

	@Test
	void testUpdateUser() {

		Optional<User> user = repo.findById(6);

		assertTrue(user.isPresent());
		User userToChange = user.get();

		String originalName = userToChange.getName();

		userToChange.setName("CHANGED");
		repo.save(userToChange);

		Optional<User> changedUser = repo.findById(6);
		assertEquals(changedUser.get().getName(), "CHANGED");

		userToChange.setName(originalName);

		repo.save(userToChange);
	}

	@Test
	void testDeleteUser() {

		User userToDelete = new User("xxx", "xxx.yyy@gmail.com",false);

		repo.save(userToDelete);

		System.out.println(userToDelete);

		int id = userToDelete.getId();

		repo.deleteById(id);

		Optional<User> user = repo.findById(id);

		assertFalse(user.isPresent());
	}

	@Test
	void testInsertUser() {

		User userToAdd = new User("NEW USER", "new.user@gmail.com", false);
		User addedUser = repo.save(userToAdd);
		System.out.println(userToAdd);
		assertNotNull(userToAdd.getId());

		repo.deleteById(addedUser.getId());

	}

	@Test
	void testJpaUserDaoGetAll() {

		List<User> users = dao.getUsers();
		for (User user: users) {
			System.out.println(user);
		}
		assertNotEquals(users.size(), 0);
	}

	@Test
	void testJpaUserDaoGet() throws UserDaoException {

		User newUser = new User("New User", "user@gmail.com", false);
		User addedUser = dao.addUser(newUser);
		User checkUser = dao.getUser(addedUser.getId());
		assertEquals(checkUser.getName(), newUser.getName());
		dao.deleteUser(addedUser.getId());
	}

	@Test
	void testJpaUserDaoGetNotFound() throws UserDaoException {

		UserDaoException thrown = assertThrows(UserDaoException.class, () -> {
			dao.getUser(9999);
		});
		//assertEquals(thrown.getMessage(), "User not found: 9999");
	}

	@Test
	void testJpaUserDaoDelete() throws UserDaoException {

		User newUser = new User("New User", "user@gmail.com", false);
		User addedUser = dao.addUser(newUser);
		User checkUser = dao.getUser(addedUser.getId());
		assertEquals(checkUser.getName(), newUser.getName());
		dao.deleteUser(addedUser.getId());

		assertThrows(UserDaoException.class, () -> {
			dao.getUser(addedUser.getId());
		});


	}

	@Test
	void testJpaUserDaoDeleteNotFound() throws UserDaoException {

		assertThrows(UserDaoException.class, () -> {
			dao.deleteUser(9999);
		});
	}

	@Test
	void testJpaUserDaoAddUser() throws UserDaoException {

		User userToAdd = new User("New User", "new.user@gmail.com", true);
		User addedUser = dao.addUser(userToAdd);
		assertNotNull(addedUser.getId());

		dao.deleteUser(addedUser.getId());
	}



	void testJpaUserAddUserWithId() throws UserDaoException {

		User userToAdd = new User(1000, "User With Id", "user.with.id@gmail.com", false);
		User addedUser = dao.addUser(userToAdd);
		System.out.println(addedUser);

		// check that supplied id was ignored
		assertNotEquals(addedUser.getId(), 1000);

		dao.deleteUser(addedUser.getId());
	}

	@Test
	void testJpaUserUpdate() throws UserDaoException {

		User userToAdd = new User("aaa", "bbb@ccc.ddd", false);

		User userToChange = dao.addUser(userToAdd);

		userToChange.setName("CHANGED");
		userToChange.setEmail("CHANGED@GMAIL.COM");
		userToChange.setActive(userToChange.isActive());

		dao.updateUser(userToChange);

		User changedUser = dao.getUser(userToChange.getId());

		assertEquals(changedUser.getName(), userToChange.getName());

		// tidy up after test
		dao.deleteUser(changedUser.getId());



	}





}
