package orbag.security;

import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
@SuppressWarnings("serial")
public class OrbagSecurityException extends Exception{

	public OrbagSecurityException() {
		
	}
	
	Authentication user;
	
	Object target;
	
	AccessType[] accessTypes;
	
	public OrbagSecurityException(Authentication user, Object target, AccessType... accessTypes) {
		this.user = user;
		this.target = target;
		this.accessTypes = accessTypes;
	}
	
	@Override
	public String getMessage() {
		return "Acces "+ String.join(" or ",Stream.of(accessTypes).map(Object::toString).toList()) +" denied on "+target+" to "+user;
	}

}
