package orbag.impl.brewery;

import java.util.List;

import orbag.dao.OrbagListableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.dao.OrbagRepository;
import orbag.data.PaginationInfo;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;

@Component
public class BreweryRepository implements OrbagRepository, OrbagListableRepository {

	@Autowired
	BreweryClient breweryClient;
	
	@Override
	public boolean isManaged(Class<?> javaClass) {
		return  javaClass.equals(Brewery.class);
	}

	@Override
	public Object getIdentifier(Object ci) {
		if ( ci instanceof Brewery) {
			return ((Brewery)ci).getIdentifier();
		}
		throw new UnsupportedOperationException();
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Object identifier, Class<T> javaClass) {
		if ( javaClass.equals(Brewery.class)) {
			return (T) breweryClient.getById((String)identifier);
		}
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void listInto(Class<T> javaClass, UnsafeConsumer<T,LimitExceededException> consumer,PaginationInfo paginationInfo) throws LimitExceededException {
		consumer.acceptAll( (List<T>) breweryClient.list());
	}

}
