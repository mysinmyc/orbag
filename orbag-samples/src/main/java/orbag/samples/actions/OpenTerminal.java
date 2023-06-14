package orbag.samples.actions;

import orbag.action.*;
import orbag.metadata.DisplayLabel;
import orbag.metadata.Displayable;
import orbag.samples.cis.Server;
import orbag.visibility.ManagedClasses;
import org.springframework.stereotype.Component;

@Component
@DisplayLabel("Open terminal")
@ManagedClasses({Server.class})
public class OpenTerminal extends ConfigurationItemActionBase {

    @Override
    public boolean isAvailableFor(ActionRequest request) {
        return request.getTargetCis().size()==1;
    }

    @Override
    public boolean isQuick() {
        return true;
    }

    @Override
    public void execute(ActionRequest request, ActionResult result) throws ActionExecutionException {
        result.setLink("ssh://"+ ((Server)request.getTargetCis().get(0)).getAddress());
    }
}
