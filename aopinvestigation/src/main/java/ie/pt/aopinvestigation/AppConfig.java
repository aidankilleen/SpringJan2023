package ie.pt.aopinvestigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public TestBean getTestBean () {

        return new TestBean("TestBean message here");

    }

    @Bean
    public TestClass getTestClass() {

        return new TestClass("TestClass message here");
    }
}
