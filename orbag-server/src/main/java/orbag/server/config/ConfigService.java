package orbag.server.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriBuilder;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ConfigService {

	@Value("${custom.server.address:UNDEFINED}")
	String serverAddress;
	
	public String getServerAddress(HttpServletRequest httpServletRequest) {

		if ("UNDEFINED".equals(serverAddress)) {
			//return httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getLocalPort();
			
			return httpServletRequest.getRequestURL().toString().replaceAll("(.*://[^/]+)/.*$","$1");
			
			
		} else {
			return serverAddress;
		}
	}
}
