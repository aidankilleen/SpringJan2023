package ie.pt.springrestwithjwtsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Autowired
    JwtUserDetails jwtUserDetails;

    public String createToken() {
        return "thismightbeatokenlater";
    }


    public Authentication getAuthentication(String token) {

        // username will come from the token via decryption
        // for now use hardcoded

        String username = "user";

        UserDetails userDetails =jwtUserDetails.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(
                        userDetails, "",
                        userDetails.getAuthorities());

    }
}
