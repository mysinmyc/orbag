package orbag.server.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.data.TableBuilder;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.server.OrbagServerException;
import orbag.view.ConfigurationItemView;
import orbag.view.ViewDataBindRequest;
import orbag.view.ViewRegistry;
import orbag.visibility.FilterContext;
import orbag.visibility.VisibilityManager;

@Component
public class ViewService {

	@Autowired
	ConfigurationItemDao dao;
	
	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;
	
	@Autowired
	ViewRegistry viewRegistry;
	
	@Autowired
	VisibilityManager visibilityManager;
		
	public List<SerializableView> getAvailableViews(ConfigurationItemReference configurationItemReference,
			Authentication user) {
		ViewDataBindRequest request = new ViewDataBindRequest();
		request.setCi(dao.getCi(configurationItemReference));
		List<ConfigurationItemView> availableViews = new ArrayList<>();
		for (ConfigurationItemView view : visibilityManager.filterObjects(viewRegistry.getAllViews(), FilterContext.forTargetObject(request.getCi()).forUser(user))) {
			if (view.isAvailableFor(request)) {
				availableViews.add(view);
			}
		}
		return availableViews.stream().map(a -> new SerializableView(a.getIdentifier(), a.getDisplayLabel()))
				.toList();		
	}

	ConfigurationItemView getViewFromid(String id) {
		return viewRegistry.getAllViews().stream().filter(a -> a.getIdentifier().equals(id)).findAny().orElse(null);
	}

	public void bindInto(ConfigurationItemReference configurationItemReference, String viewId, Authentication user,
			TableBuilder<Object> tableBuilder) {
		ViewDataBindRequest request = new ViewDataBindRequest();
		request.setCi(dao.getCi(configurationItemReference));
		ConfigurationItemView view = getViewFromid(viewId);
		
		if ( view ==null || !visibilityManager.isObjectVisibile(view, FilterContext.forTargetObject(request.getCi()).forUser(user))) {
			throw new OrbagServerException("View not visibile");
		}
		view.bindInto(request, tableBuilder);
		
	}




}
