package orbag.server.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import orbag.security.OrbagSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    AuthenticationManager authenticationManager;

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtEncodingService jwtEncodingService;

    public Authentication authenticate(String userName, String password) throws OrbagSecurityException {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (AuthenticationException e) {
            throw new OrbagSecurityException("login failed for user "+userName,e);
        }
    }

    public String generateToken(Authentication authentication) {
        return jwtEncodingService.generateToken(authentication);
    }

    public Authentication loginByToken(String token) throws OrbagSecurityException {
        try {
            String user = jwtEncodingService.decodeToken(token).getToken();
            UserDetails userDetails = userDetailsService.loadUserByUsername(user);
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        } catch (JWTVerificationException e) {
            throw new OrbagSecurityException("Invalid token",e);
        }
    }

}
