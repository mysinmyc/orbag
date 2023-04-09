package orbag.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ConfigService {

	@Value("${server.address:UNDEFINED}")
	String serverAddress;
	
	public String getServerAddress(HttpServletRequest httpServletRequest) {

		if ("UNDEFINED".equals(serverAddress)) {
			return httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getLocalPort();
		} else {
			return serverAddress;
		}
	}
}
