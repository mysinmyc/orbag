package orbag.view;

import orbag.data.TableBuilder;
import orbag.metadata.Displayable;

public interface ConfigurationItemView extends Displayable {

	String getIdentifier();
	
	default boolean isAvailableFor(ViewDataBindRequest request) {
		return true;
	}
	
	void bindInto(ViewDataBindRequest request, TableBuilder<Object> tableBuilder);

}
