package orbag.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ConfigService {

	@Value("${custom.server.address:UNDEFINED}")
	String serverAddress;


	@Value("${orbag.config.application_name:ORBAG}")
	String applicationName;

	@Value("${orbag.config.login_message:}")
	String loginMessage;

	public String getServerAddress(HttpServletRequest httpServletRequest) {
		if ("UNDEFINED".equals(serverAddress)) {
			//return httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getLocalPort();
			return httpServletRequest.getRequestURL().toString().replaceAll("(.*://[^/]+)/.*$","$1");
		} else {
			return serverAddress;
		}
	}

	public String getApplicationName() {
		return applicationName;
	}

	public String getLoginMessage() {
		return loginMessage;
	}
}
