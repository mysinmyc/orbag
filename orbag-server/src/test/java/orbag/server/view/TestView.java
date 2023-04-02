package orbag.server.view;

import org.springframework.stereotype.Component;

import orbag.data.TableBuilder;
import orbag.view.ConfigurationItemViewBase;
import orbag.view.ViewDataBindRequest;
import orbag.visibility.ManagedClasses;

@Component
@ManagedClasses(TestViewCi.class)
public class TestView extends ConfigurationItemViewBase{

	@Override
	public void bindInto(ViewDataBindRequest request, TableBuilder<Object> tableBuilder) {
		// TODO Auto-generated method stub
		
	}


}
