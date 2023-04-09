package orbag.server.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ConfigController {

	@Autowired
	ConfigService configService;
	
	@GetMapping("/public/config.json")
	public ConfigResponse getConfig(HttpServletRequest httpServletRequest) {
		
		ConfigResponse configResponse = new ConfigResponse();
		configResponse.setAddress(configService.getServerAddress(httpServletRequest));
		return configResponse;
	}
}
