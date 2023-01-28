package ie.pt.springwebsecurityinvestigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.sqlite.hibernate.dialect.SQLiteDialect;

import javax.servlet.ServletException;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SQLiteDialect getSqliteDialect() {
        return new SQLiteDialect();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests(authorization ->
                        {
                            try {
                                authorization
                                        .antMatchers("/").permitAll()
                                        .anyRequest().authenticated()
                                .and()
                                    .formLogin()
                                .and()
                                    .httpBasic();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );

        return httpSecurity.build();
    }



    @Bean
    public UserDetailsService getUserDetailsService(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }


/*
    @Bean
    public AuthenticationManager getAuthenticationManager(HttpSecurity http) throws Exception {

                AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userDetailsManager);

        return authenticationManagerBuilder.build();
    }
*/


    public AuthenticationManager getAuthenticationManagerOriginal(HttpSecurity http) throws Exception {

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        UserDetails user = User
                            .withUsername("alice")
                            .password("$2a$12$Gfth.g6qzNyP1PtGGNe5Guv695.m7hTrrCC0XpA7k/kcHyVE0UEq.")
                            .roles("USER")
                            .build();
        UserDetails admin = User
                            .withUsername("admin")
                            .password("$2a$12$Gfth.g6qzNyP1PtGGNe5Guv695.m7hTrrCC0XpA7k/kcHyVE0UEq.")
                            .roles("ADMIN")
                            .build();

        userDetailsManager.createUser(user);
        userDetailsManager.createUser(admin);

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userDetailsManager);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }


}
