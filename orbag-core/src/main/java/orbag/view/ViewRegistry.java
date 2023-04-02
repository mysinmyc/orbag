package orbag.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewRegistry {

	@Autowired(required =false)
	List<ConfigurationItemView>  views;
	
	
	public List<ConfigurationItemView> getAllViews() {
		return views;
	}
}
