package ie.pt.aopinvestigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopinvestigationApplication implements CommandLineRunner {


	@Autowired
	TestBean tb;

	@Autowired
	TestClass tc;

	public static void main(String[] args) {
		SpringApplication.run(AopinvestigationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// TestBean tb = new TestBean();

		tb.display();

		tc.display();


	}
}
