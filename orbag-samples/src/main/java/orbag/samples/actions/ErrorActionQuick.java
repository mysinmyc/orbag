package orbag.samples.actions;

import orbag.action.ActionExecutionException;
import orbag.action.ActionRequest;
import orbag.action.ActionResult;
import orbag.action.ConfigurationItemActionBase;
import orbag.metadata.DisplayLabel;
import orbag.metadata.Displayable;
import orbag.visibility.ManagedClasses;
import org.springframework.stereotype.Component;

@Component
@DisplayLabel("Error action (QUICK) ")
@ManagedClasses({Displayable.class})
public class ErrorActionQuick extends ConfigurationItemActionBase {

    @Override
    public boolean isQuick() {
        return true;
    }

    @Override
    public void execute(ActionRequest request, ActionResult result) throws ActionExecutionException {
        throw new ActionExecutionException("Action failed");
    }
}
