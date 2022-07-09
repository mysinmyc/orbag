package orbag.server.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/list")
public class ListController {

	@Autowired
	ListService listService;
	
	@GetMapping("/{configurationItemName}")
	public  ListConfigurationItemResponse listConfigurationItems(@PathVariable("configurationItemName") String configurationItemName) {
		ListConfigurationItemResponse response = new ListConfigurationItemResponse();
		response.setCis(listService.list(configurationItemName));
		return response;
	}
}
