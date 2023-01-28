package ie.pt.springboothelloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class SpringboothelloworldApplication implements CommandLineRunner {


	Logger logger = LoggerFactory.getLogger(SpringboothelloworldApplication.class);

	@Autowired
	TestBean tb;

	@Autowired
	UserDao dao;


	@Value("${userdata.message}")
	String message;

	@Value("${userdata.password}")
	private String password;


	public static void main(String[] args) {
		SpringApplication.run(SpringboothelloworldApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("Spring boot hello world started");

		logger.info("This is the userdata.message:" + message);

		logger.info("The password is " + password);

		logger.info(tb.getMessage());

		logger.info("This is an info message");

		logger.warn("this is a warning");

		logger.error("this is an error");

		logger.debug("this is a debug message");

		logger.trace("this is a trace message");


		List<User> users = dao.getUsers();

		for (User u : users) {
			System.out.println(u);
		}

	}
}
