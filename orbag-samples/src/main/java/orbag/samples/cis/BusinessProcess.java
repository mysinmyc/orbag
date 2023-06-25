package orbag.samples.cis;

import jakarta.persistence.*;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;
import orbag.samples.impacts.Business2InfrastructureAware;
import orbag.samples.impacts.Infrastructure2BusinessAware;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@ConfigurationItem(category = "Business")
@Entity
public class BusinessProcess extends RootConfigurationItem implements Business2InfrastructureAware, Infrastructure2BusinessAware {

    @ManyToOne
    Customer customer;

    @Lazy
    @ManyToMany
    @JoinTable(
            name = "business_process2application_instance",
            joinColumns = @JoinColumn(name = "business_process_id"),
            inverseJoinColumns = @JoinColumn(name = "application_instance_id"))
    List<ApplicationInstance> applications;

    public Customer getCustomer() {
        return customer;
    }

    @ConfigurationItemProperty(highlighted = true)
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ConfigurationItemProperty(itemsClass = ApplicationInstance.class)
    public List<ApplicationInstance> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationInstance> applications) {
        this.applications = applications;
    }

    @Override
    public List<?> getInfrastructuralDependencies() {
        return getApplications();
    }

    @Override
    public List<?> getBusinessImpacts() {
        return List.of(customer);
    }
}
