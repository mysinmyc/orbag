package orbag.samples.cis;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;
import orbag.metadata.DisplayLabelUtils;
import orbag.samples.impacts.Business2InfrastructureAware;
import orbag.samples.impacts.Infrastructure2BusinessAware;

import java.util.List;

@ConfigurationItem
@Entity
public class ApplicationInstance  extends RootConfigurationItem implements Infrastructure2BusinessAware, Business2InfrastructureAware {



    String installationPath;

    @ManyToOne
    BusinessApplication businessApplication;

    @ManyToOne
    Server server;

    @ConfigurationItemProperty(mandatoryForCreation = true, highlighted = true)
    public String getInstallationPath() {
        return installationPath;
    }

    public void setInstallationPath(String installationPath) {
        this.installationPath = installationPath;
    }

    @ConfigurationItemProperty(mandatoryForCreation = true, highlighted = true)
    public BusinessApplication getBusinessApplication() {
        return businessApplication;
    }

    public void setBusinessApplication(BusinessApplication businessApplication) {
        this.businessApplication = businessApplication;
    }


    public List<BusinessProcess> getDependentProcesses() {
        return dependentProcesses;
    }

    public void setDependentProcesses(List<BusinessProcess> dependentProcesses) {
        this.dependentProcesses = dependentProcesses;
    }

    public Server getServer() {
        return server;
    }

    @ConfigurationItemProperty(mandatoryForCreation = true, highlighted = true)
    public void setServer(Server server) {
        this.server = server;
    }


    @ManyToMany(mappedBy = "applications")
    List<BusinessProcess> dependentProcesses;

    @Override
    public List<?> getInfrastructuralDependencies() {
        return List.of(server);
    }

    @Override
    public List<?> getBusinessImpacts() {
        return dependentProcesses;
    }

    @Override
    public String getDisplayLabel() {
        return ""+installationPath+"@"+ DisplayLabelUtils.getDisplayLabel(server);
    }
}
