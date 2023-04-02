package orbag.server.security;

import java.util.List;

public class WhoAmIResponse {

	String username;
	
	List<String> authorities;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
}
