package orbag.data;

public interface ConfigurationItemTableBuilder<T> extends TableBuilder<T> {

	
	PartialColumn addConfigurationItemReferenceColumn(String name);
	
	PartialColumn addConfigurationItemReferenceListColumn(String name);
		
}
