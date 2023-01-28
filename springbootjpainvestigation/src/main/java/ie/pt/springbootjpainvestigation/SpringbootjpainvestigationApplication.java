package ie.pt.springbootjpainvestigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringbootjpainvestigationApplication implements CommandLineRunner  {

	@Autowired
	UserRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpainvestigationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Ready to run...");

		List<User> users = repo.findAll();

		for (User user : users){

			System.out.println(user);
		}



	}
}
