package orbag.server;

import orbag.EnableOrbagCore;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class TestSecurityConfiguration {
	

	@SuppressWarnings("deprecation")
	@Bean
	@Primary
	public UserDetailsService userDetailsServiceTest() {
		return new InMemoryUserDetailsManager(
				User.withDefaultPasswordEncoder().username("test1").password("test1")
				.authorities(new SimpleGrantedAuthority("test1")).build(),
				User.withDefaultPasswordEncoder().username("test2").password("test2")
				.authorities(new SimpleGrantedAuthority("test2")).build(),
				User.withDefaultPasswordEncoder().username("test3").password("test3")
				.authorities(new SimpleGrantedAuthority("test3")).build()
				
				);
	}

	

}

