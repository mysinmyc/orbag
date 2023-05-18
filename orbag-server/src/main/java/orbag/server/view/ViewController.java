package orbag.server.view;

import orbag.dao.ConfigurationItemNotFoundException;
import orbag.metadata.UnmanagedObjectException;
import orbag.security.OrbagSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbag.data.SerializableTableBuilder;
import orbag.reference.ConfigurationItemReferenceService;

@RestController
@RequestMapping("/api/view")
public class ViewController {

	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;

	@Autowired
	ViewService viewService;

	@PostMapping("/getAvailable")
	public GetAvailableViewsResponse getAvailableViews(@RequestBody GetAvailableViewsRequest request,
			Authentication user) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
		GetAvailableViewsResponse response = new GetAvailableViewsResponse();
		response.setAvailableViews(viewService.getAvailableViews(request.getTargetCi(), user));
		return response;
	}

	@PostMapping("/bind")
	public BindViewResponse bind(@RequestBody BindViewRequest request, Authentication user) throws UnmanagedObjectException, OrbagSecurityException, ConfigurationItemNotFoundException {
		SerializableTableBuilder<Object> serializableTableBuilder = new SerializableTableBuilder<>(
				configurationItemReferenceService);
		viewService.bindInto(request.getTargetCi(), request.getView().getIdentifier(), user, serializableTableBuilder);

		BindViewResponse response = new BindViewResponse();
		response.setResultTable(serializableTableBuilder.build());
		return response;
	}

}
