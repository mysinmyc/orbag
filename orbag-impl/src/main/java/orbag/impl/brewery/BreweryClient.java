package orbag.impl.brewery;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


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
}
