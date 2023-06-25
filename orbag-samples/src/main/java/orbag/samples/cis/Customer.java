package orbag.samples.cis;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;
import orbag.samples.impacts.Business2InfrastructureAware;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@ConfigurationItem(category = "Party")
@Entity
public class Customer extends RootConfigurationItem implements Business2InfrastructureAware {

    @Lazy
    @OneToMany(mappedBy = "customer")
    List<BusinessProcess> businessProcesses;

    public List<BusinessProcess> getBusinessProcesses() {
        return businessProcesses;
    }

    @ConfigurationItemProperty(itemsClass = BusinessProcess.class)
    public void setBusinessProcesses(List<BusinessProcess> businessProcesses) {
        this.businessProcesses = businessProcesses;
    }

    @Override
    public List<?> getInfrastructuralDependencies() {
        return getBusinessProcesses();
    }
}
