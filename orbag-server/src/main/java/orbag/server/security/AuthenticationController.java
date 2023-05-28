package orbag.server.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import orbag.security.OrbagSecurityException;
import orbag.server.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {


	@Autowired
	AuthenticationService authenticationService;

	@Operation(description = "Return authenticated user")
	@SecurityRequirements({@SecurityRequirement(name = ApiInfo.JWT)})
	@GetMapping("/whoami")
	public WhoAmIResponse whoAmI(Authentication user) {
		WhoAmIResponse whoAmIResponse = new WhoAmIResponse();
		whoAmIResponse.setUserName(user.getName());
		whoAmIResponse.setAuthorities(
				user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
		);
		return whoAmIResponse;
	}

	@Operation(description = "Try to login the user")
	@PostMapping("/login")	
	public LoginResponse login(@RequestBody LoginRequest request, HttpServletResponse response) throws OrbagSecurityException {
		LoginResponse loginResponse = new LoginResponse();
		Authentication authentication = authenticationService.authenticate(request.getUserName(),request.getPassword());
		String token = authenticationService.generateToken(authentication);
		loginResponse.setToken(token);
		loginResponse.setUsername(authentication.getName());
		loginResponse.setAuthorities(
				authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
		);
		if (request.isPersistent()) {
			Cookie cookie =new Cookie(JwtAuthenticationFilter.COOKIE, token);
			cookie.setPath("/api");
			response.addCookie(cookie);
		}
		return loginResponse;
	}

}
