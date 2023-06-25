package orbag.samples.actions;

import orbag.action.ActionExecutionException;
import orbag.action.ActionRequest;
import orbag.action.ActionResult;
import orbag.action.ConfigurationItemActionBase;
import orbag.metadata.Description;
import orbag.metadata.DisplayLabel;
import orbag.metadata.Displayable;
import orbag.visibility.ManagedClasses;
import org.springframework.stereotype.Component;

@Component
@DisplayLabel("Search in google")
@Description("This action show what occurs in case the action return a link")
@ManagedClasses({Displayable.class})
public class SearchInGoogle extends ConfigurationItemActionBase {

    @Override
    public boolean isAvailableFor(ActionRequest request) {
        return request.getTargetCis().size()==1;
    }
    @Override
    public void execute(ActionRequest request, ActionResult result) throws ActionExecutionException {
        result.setLink("https://www.google.com/search?q="+ ((Displayable)request.getTargetCis().get(0)).getDisplayLabel());
    }
}
