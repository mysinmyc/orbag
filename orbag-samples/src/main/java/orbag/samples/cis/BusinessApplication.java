package orbag.samples.cis;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;
import orbag.samples.impacts.Business2InfrastructureAware;
import orbag.samples.impacts.Infrastructure2BusinessDiscovered;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@ConfigurationItem(category = "Business")
@Entity
public class BusinessApplication extends RootConfigurationItem implements Business2InfrastructureAware, Infrastructure2BusinessDiscovered {

    @Lazy
    @OneToMany(mappedBy = "businessApplication")
    List<ApplicationInstance> instances;

    public List<ApplicationInstance> getInstances() {
        return instances;
    }

    @ConfigurationItemProperty(readOnly = true,itemsClass = ApplicationInstance.class)
    public void setInstances(List<ApplicationInstance> instances) {
        this.instances = instances;
    }

    @Override
    public List<?> getInfrastructuralDependencies() {
        return instances;
    }
}
