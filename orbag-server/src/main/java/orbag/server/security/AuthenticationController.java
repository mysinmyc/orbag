package orbag.server.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import orbag.security.OrbagSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {


	@Autowired
	AuthenticationService authenticationService;

	@GetMapping("/whoami")
	public WhoAmIResponse whoAmI(Authentication user) {
		WhoAmIResponse whoAmIResponse = new WhoAmIResponse();
		whoAmIResponse.setUsername(user.getName());
		whoAmIResponse.setAuthorities(
				user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
		);
		return whoAmIResponse;
	}


	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request, HttpServletResponse response) throws OrbagSecurityException {
		LoginResponse loginResponse = new LoginResponse();
		Authentication authentication = authenticationService.authenticate(request.getUserName(),request.getPassword());
		String token = authenticationService.generateToken(authentication);
		loginResponse.setToken(token);
		if (request.isPersistent()) {
			Cookie cookie =new Cookie(JwtAuthenticationFilter.COOKIE, token);
			response.addCookie(cookie);
		}
		return loginResponse;
	}

}
