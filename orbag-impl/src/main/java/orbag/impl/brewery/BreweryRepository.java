package orbag.impl.brewery;

import java.util.List;

import org.springframework.stereotype.Component;

import orbag.dao.OrbagRepository;

@Component
public class BreweryRepository implements OrbagRepository{

	
	BreweryClient breweryClient = new BreweryClient();
	
	@Override
	public boolean isManaged(Class<?> javaClass) {
		return  javaClass.equals(Brewery.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> javaClass) {
		return (List<T>) breweryClient.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Object identifier, Class<T> javaClass) {
		return (T) breweryClient.getById((String)identifier);
	}

}
