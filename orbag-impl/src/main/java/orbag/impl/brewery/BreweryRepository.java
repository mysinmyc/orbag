package orbag.impl.brewery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.dao.OrbagRepository;
import orbag.data.PaginationInfo;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;

@Component
public class BreweryRepository implements OrbagRepository{

	@Autowired
	BreweryClient breweryClient;
	
	@Override
	public boolean isManaged(Class<?> javaClass) {
		return  javaClass.equals(Brewery.class);
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Object identifier, Class<T> javaClass) {
		return (T) breweryClient.getById((String)identifier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void listInto(Class<T> javaClass, UnsafeConsumer<T,LimitExceededException> consumer,PaginationInfo paginationInfo) throws LimitExceededException {
		consumer.acceptAll( (List<T>) breweryClient.list());
	}

}
