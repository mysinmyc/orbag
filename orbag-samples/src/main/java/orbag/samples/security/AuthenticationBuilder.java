package orbag.samples.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationBuilder {

    @Autowired
    public void buildAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication().withUser("it_user").password(passwordEncoder.encode("orbag")).authorities(new SimpleGrantedAuthority("it_user"))
                .and().withUser("business_user").password(passwordEncoder.encode("orbag")).authorities(new SimpleGrantedAuthority("business_user"));
    }
}
