package orbag.graph;

import orbag.security.AccessType;
import orbag.security.SecurityAssertionService;
import org.springframework.security.core.Authentication;

public class SecuredGraphBuilderDecorator implements GraphBuilder{


    GraphBuilder nestedBuilder;

    SecurityAssertionService securityAssertionService;

    Authentication user;

    public SecuredGraphBuilderDecorator(GraphBuilder nestedBuilder, SecurityAssertionService securityAssertionService, Authentication user) {
        this.nestedBuilder = nestedBuilder;
        this.securityAssertionService = securityAssertionService;
        this.user = user;
    }

    @Override
    public boolean addRelation(Object fromCi, Object toCi, String relationName, String relationLabel) {
        if (! (securityAssertionService.getAccessRightsToObjectFor(fromCi,user).hasAnyAccess(AccessType.READ,AccessType.USE)
                || securityAssertionService.getAccessRightsToObjectFor(toCi,user).hasAnyAccess(AccessType.READ,AccessType.USE))){
            return false;
        }
        return nestedBuilder.addRelation(fromCi,toCi,relationName,relationLabel);
    }

    @Override
    public boolean isStopBuild() {
        return nestedBuilder.isStopBuild();
    }

    @Override
    public void setStopBuild(boolean stopBuild) {
        nestedBuilder.setStopBuild(stopBuild);
    }
}
