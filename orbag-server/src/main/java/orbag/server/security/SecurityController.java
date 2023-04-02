package orbag.server.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

	@GetMapping("/whoami")
	public WhoAmIResponse whoAmI(Authentication user) {
		WhoAmIResponse whoAmIResponse = new WhoAmIResponse();
		whoAmIResponse.setUsername(user.getName());
		whoAmIResponse.setAuthorities(
				user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
				);
		return whoAmIResponse;
	}
}
