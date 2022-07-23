package orbag.server.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import orbag.data.PaginationInfo;

@RestController
@RequestMapping("/api/list")
public class ListController {

	@Autowired
	ListService listService;

	@GetMapping("/{configurationItemName}")
	public ListConfigurationItemResponse listConfigurationItems(
			@PathVariable("configurationItemName") String configurationItemName,
			@RequestParam(defaultValue = "50", name = "limit") Integer limit,
			@RequestParam(defaultValue = "0", name = "offset") Integer offset) {
		ListConfigurationItemResponse response = new ListConfigurationItemResponse();
		response.setCis(listService.list(configurationItemName, new PaginationInfo(limit,offset)));
		return response;
	}
}
