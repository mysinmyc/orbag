package orbag.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration  {

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;


	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AuthenticationService authenticationService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(c -> c.requestMatchers("/js/**", "/css/**", "/index.html", "/", "/favicon.ico", "/ui/**", "/public/**", "/error", "/v3/**","/swagger-ui.html", "/swagger-ui/**", "/api/authentication/**")
						.permitAll().requestMatchers("/api/**").authenticated())
				.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and().csrf().disable();
		http.addFilterBefore( jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.userDetailsService(userDetailsService);
		SecurityFilterChain result= http.build();
		authenticationService.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		return result;
	}





}
