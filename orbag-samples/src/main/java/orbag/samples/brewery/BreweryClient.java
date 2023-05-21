package orbag.samples.brewery;

import java.net.URLEncoder;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class BreweryClient {

	RestTemplate restTemplate;
	
	public BreweryClient() {
		restTemplate = new RestTemplate();
	}
	
	public List<Brewery> list() {
		ResponseEntity<GetBreweriesResponse> response = restTemplate
				.getForEntity("https://api.openbrewerydb.org/breweries?per_page=100", GetBreweriesResponse.class);

		return response.getBody();
	}
	
	public Brewery getById(String id) {
		ResponseEntity<Brewery> response = restTemplate
				.getForEntity("https://api.openbrewerydb.org/breweries/"+id,Brewery.class);

		return response.getBody();
	}
	
	
	@SuppressWarnings("deprecation")
	public List<Brewery> searchByName(String name) {
		ResponseEntity<GetBreweriesResponse> response = restTemplate
				.getForEntity("https://api.openbrewerydb.org/breweries?per_page=100&by_name="+URLEncoder.encode(name), GetBreweriesResponse.class);

		return response.getBody();
	}
}
