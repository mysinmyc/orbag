package orbag.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchExecutorRegistry {

	@Autowired(required = false)
	List<SearchExecutor<?>> searchExecutors;
	
	public List<SearchExecutor<?>> getAllSearchExecutors() {
		return searchExecutors;
	}
}
