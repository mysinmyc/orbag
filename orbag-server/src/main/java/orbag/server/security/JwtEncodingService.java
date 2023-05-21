package orbag.server.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtEncodingService  implements InitializingBean {

    @Value("${orbag.security.jwt.secret:}")
    String secret;

    @Value("${orbag.security.jwt.expiration_ms:3600000}")
    long expiration_ms;

    public DecodedJWT decodeToken(String token) throws JWTVerificationException {
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token);
    }

    public String generateToken(Authentication authentication) {
        String token =JWT.create().withSubject(authentication.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration_ms))
                .withClaim("random", UUID.randomUUID().toString())
                .withArrayClaim("authorities", authentication.getAuthorities().stream().map( a -> a.getAuthority()).toList().toArray(new String[]{}))
                .sign(Algorithm.HMAC512(secret));
        return token;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (secret==null || secret.isEmpty()) {
            secret = UUID.randomUUID().toString();
        }
    }
}
