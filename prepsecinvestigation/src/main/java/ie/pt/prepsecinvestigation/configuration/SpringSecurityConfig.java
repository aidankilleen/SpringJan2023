package ie.pt.prepsecinvestigation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChainBean(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .cors().disable()
                .formLogin(withDefaults())
                .authorizeRequests()
                .antMatchers("/h2-console/**/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/login")
                .and().headers().frameOptions().sameOrigin();

        return http.build();
    }
}
