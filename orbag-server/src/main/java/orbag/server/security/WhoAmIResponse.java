package orbag.server.security;

import java.util.List;

public class WhoAmIResponse {

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	String userName;
	
	List<String> authorities;


	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
}
