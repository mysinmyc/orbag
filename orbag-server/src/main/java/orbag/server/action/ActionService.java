package orbag.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import orbag.action.*;
import orbag.dao.ConfigurationItemNotFoundException;
import orbag.metadata.UnmanagedObjectException;
import orbag.util.UnsafeBiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.input.FieldGroupBuilder;
import orbag.input.FieldGroupConsumer;
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataRegistry;
import orbag.reference.ConfigurationItemReference;
import orbag.security.AccessType;
import orbag.security.OrbagSecurityException;
import orbag.security.SecurityAssertionService;
import orbag.visibility.FilterContext;
import orbag.visibility.VisibilityManager;

@Component
public class ActionService {

	@Autowired
	VisibilityManager visibilityManager;

	@Autowired
	ActionRegistry actionRegistry;

	@Autowired
	ConfigurationItemDao dao;

	@Autowired
	SecurityAssertionService securityAssertionService;

	@Autowired
	MetadataRegistry metadataRegistry;

	boolean hasUseAccessToSourceCi(ConfigurationItemReference sourceCi, Authentication user) throws UnmanagedObjectException {
		if (sourceCi == null) {
			return true;
		}

		ConfigurationItemDescriptor sourceCiDescriptor = metadataRegistry
				.getConfigurationItemDescriptorByName(sourceCi.getConfigurationItemType());

		return securityAssertionService.hasAuthorizationToConfigurationItemDescriptor(sourceCiDescriptor, user,
				AccessType.USE, AccessType.READ, AccessType.MODIFY);
	}

	boolean isActionVisibile(ConfigurationItemAction action, List<?> configurationItems, Authentication user) {
		for (Object ci : configurationItems) {
			if (!visibilityManager.isObjectVisibile(action, FilterContext.forTargetObject(ci).forUser(user))) {
				return false;
			}
		}
		return true;
	}

	String formatLabel(String label, List<ConfigurationItemReference> cisReferences) {
		if (label.indexOf("%") == -1) {
			return label;
		}
		if (cisReferences.size() > 1) {
			return label.replace("%", cisReferences.size() + " cis");
		}
		String ciLabel = cisReferences.get(0).getDisplayLabel();
		return label.replace("%", ciLabel);

	}

	public List<SerializableAction> getAvaiableActionsFor(ConfigurationItemReference sourceCiReference,
			List<ConfigurationItemReference> targetCisReferences, Authentication user) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
		if (targetCisReferences == null || targetCisReferences.isEmpty()
				|| !hasUseAccessToSourceCi(sourceCiReference, user)) {
			return new ArrayList<SerializableAction>();
		}
		List<?> targetCis = dao.getExistingCisOrThrow(targetCisReferences);
		ActionRequest request = new ActionRequest();
		if (sourceCiReference!=null) {
			request.setSourceCi(dao.getExistingCiOrThrow(sourceCiReference));
		}
		request.setTargetCis(targetCis);
		List<ConfigurationItemAction> availableActions = actionRegistry.getAllActions();
		return availableActions.stream().filter(a -> isActionVisibile(a, targetCis, user))
				.filter(a -> a.isAvailableFor(request)).map(a -> new SerializableAction(a.getIdentifier(),
						formatLabel(a.getDisplayLabel(), targetCisReferences), a.isQuick()))
				.toList();
	}

	ConfigurationItemAction getActionFromid(String id) {
		return actionRegistry.getAllActions().stream().filter(a -> a.getIdentifier().equals(id)).findAny().orElse(null);
	}

	boolean isFilterVisible(ConfigurationItemActionExecutionFilter filter, List<?> configurationItems,
			Authentication user) {
		for (Object ci : configurationItems) {
			if (!visibilityManager.isObjectVisibile(filter, FilterContext.forTargetObject(ci).forUser(user))) {
				return false;
			}
		}
		return true;
	}

	List<ConfigurationItemActionExecutionFilter> getFiltersFor(ConfigurationItemAction action, ActionRequest request) {
		return actionRegistry.getAllFilters().stream()
				.filter(f -> isFilterVisible(f, request.getTargetCis(), request.getSubmitter()))
				.filter(f -> f.isAvailableFor(action, request)).toList();
	}

	boolean filterAction(ConfigurationItemAction action, ActionRequest request, ActionResult result) {
		List<ConfigurationItemActionExecutionFilter> filters = getFiltersFor(action, request);
		for (ConfigurationItemActionExecutionFilter filter : filters) {
			if (!filter.filterAction(action,request, result)) {
				return false;
			}
		}
		return true;
	}

	void invokeAction(SerializableAction serializableAction, ConfigurationItemReference sourceCiReference,
					  List<ConfigurationItemReference> targetCisReferences, Authentication user,
					  UnsafeBiConsumer<ConfigurationItemAction, ActionRequest, ActionExecutionException> consumer) throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemNotFoundException, ActionExecutionException {
		if (serializableAction  ==null) {
			throw new UnmanagedObjectException("Missing action");
		}
		ConfigurationItemAction action = getActionFromid(serializableAction.getIdentifier());
		if (action == null) {
			throw new UnmanagedObjectException("Invalid action " + serializableAction.getIdentifier());
		}
		if (!hasUseAccessToSourceCi(sourceCiReference, user)) {
			throw new OrbagSecurityException(user, sourceCiReference, AccessType.USE);
		}
		Object sourceCi = sourceCiReference==null ? null: dao.getExistingCiOrThrow(sourceCiReference);
		List<?> targetCis = dao.getExistingCisOrThrow(targetCisReferences);
		ActionRequest request = new ActionRequest();
		request.setSourceCi(sourceCi);
		request.setTargetCis(targetCis);
		if (!(isActionVisibile(action, targetCis, user) && action.isAvailableFor(request))) {
			throw new OrbagSecurityException();
		}
		consumer.accept(action, request);
	}

	public void buildParameters(SerializableAction serializableAction, ConfigurationItemReference sourceCiReference,
			List<ConfigurationItemReference> targetCisReferences, Authentication user, FieldGroupBuilder builder)
			throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemNotFoundException, ActionExecutionException {
		invokeAction(serializableAction, sourceCiReference, targetCisReferences, user, (action, request) -> {
			for (ConfigurationItemActionExecutionFilter filter : getFiltersFor(action, request)) {
				filter.setActionParameters(action,builder, request);
			}
			action.buildParametersFor(request);
		});
	}

	public ActionResult submit(SerializableAction serializableAction, ConfigurationItemReference sourceCiReference,
			List<ConfigurationItemReference> targetCisReferences, FieldGroupConsumer parameters, Authentication user) throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemNotFoundException, ActionExecutionException {
		ActionResult result = new ActionResult();
		invokeAction(serializableAction, sourceCiReference, targetCisReferences, user, (action, request) -> {
				request.setParameters(parameters);
			if (filterAction(action, request, result) && result.isRequestValid()) {
				action.execute(request, result);
			}
		});
		return result;
	}

}
