package orbag.impl.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class CustomSecurityConfiguration  {
	
	@SuppressWarnings("deprecation")
	@Bean
	public UserDetailsService userDetailsServiceImpl() {
		
		UserDetails it_user = User.withDefaultPasswordEncoder().username("it_user").password("orbag")
				.authorities(new SimpleGrantedAuthority("it_user")).build();
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("orbag")
				.authorities(new SimpleGrantedAuthority("user")).build();
		return new InMemoryUserDetailsManager(it_user,user);
	}
}

