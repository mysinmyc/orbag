package orbag.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.httpBasic().and()
				.authorizeHttpRequests(c -> c.requestMatchers("/js/**", "/css/**", "/index.html", "/", "/favicon.ico")
						.permitAll().requestMatchers("/api/**").authenticated())
				.cors().and().csrf().disable().httpBasic();

		http.userDetailsService(userDetailsService);
		return http.build();
	}

}
