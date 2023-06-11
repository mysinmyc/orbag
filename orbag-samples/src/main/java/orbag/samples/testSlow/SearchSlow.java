package orbag.samples.testSlow;

import orbag.data.TableBuilder;
import orbag.input.FieldGroupConsumer;
import orbag.samples.cis.SlowConfigurationItem;
import orbag.search.DefaultSearchExecutor;
import orbag.search.SearchContext;
import orbag.util.LimitExceededException;
import orbag.visibility.ManagedClasses;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@ManagedClasses(SlowConfigurationItem.class)
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SearchSlow extends DefaultSearchExecutor {

    @Override
    public void executeQuery(FieldGroupConsumer parameters, TableBuilder<Object> resultBuilder, SearchContext context) throws LimitExceededException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        super.executeQuery(parameters, resultBuilder, context);
    }
}
