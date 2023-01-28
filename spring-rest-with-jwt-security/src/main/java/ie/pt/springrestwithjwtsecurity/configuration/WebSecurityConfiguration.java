package ie.pt.springrestwithjwtsecurity.configuration;


import ie.pt.springrestwithjwtsecurity.security.JwtTokenFilterConfigurer;
import ie.pt.springrestwithjwtsecurity.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    SecurityFilterChain securityFilterChainBean(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/h2-console/**/**").permitAll()
                .anyRequest().authenticated()
                .and().headers().frameOptions().sameOrigin()
                .and().apply(new JwtTokenFilterConfigurer(jwtTokenProvider)); // Add our custom jwt filter to the chain

                // used to allow access to rest while testing the filter
                ///.antMatchers("/sales/**").permitAll()

        return http.build();

    }


}
