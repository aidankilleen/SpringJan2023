package ie.pt.springbootjdbcinvestigation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringbootjdbcinvestigationApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(SpringbootjdbcinvestigationApplication.class);

	@Autowired
	UserDao dao;


	public static void main(String[] args) {
		SpringApplication.run(SpringbootjdbcinvestigationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("All good to go");

		List<User> users = dao.getUsers();

		for(User user: users) {
			System.out.println(user);
		}
	}
}
