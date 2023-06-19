package sk.posam.hoursalpha.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoderProvider passwordEncoderProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    public void authenticate(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // check if passwords match
        if (!passwordEncoderProvider.getEncoder().matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        System.out.println(passwordEncoderProvider.getEncoder().encode(password));
        System.out.println(userDetails.getPassword());

        // create authentication token
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());

        // authenticate the user
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

}
