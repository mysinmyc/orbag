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
@DisplayLabel("Error action")
@Description("This action is used to test what happens in the frontend in case of error")
@ManagedClasses({Displayable.class})
public class ErrorAction extends ConfigurationItemActionBase {


    @Override
    public void execute(ActionRequest request, ActionResult result) throws ActionExecutionException {
        throw new ActionExecutionException("Action failed");
    }
}
