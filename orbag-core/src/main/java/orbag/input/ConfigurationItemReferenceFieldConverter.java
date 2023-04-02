package orbag.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.metadata.MetadataRegistry;
import orbag.reference.ConfigurationItemReferenceService;

@Component
public class ConfigurationItemReferenceFieldConverter implements FieldValueGetter<ConfigurationItemReferenceField>,FieldValueSetter<ConfigurationItemReferenceField>{

	@Autowired
	MetadataRegistry metadataRegistry;
	
	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;
	
	@Autowired
	ConfigurationItemDao dao;
	
	@Override
	public boolean canSetValue(Object value, InputFieldBase<?> field) {
		return field instanceof ConfigurationItemReferenceField;
	}

	@Override
	public void setFieldValue(Object value, ConfigurationItemReferenceField field) {
			field.setValue(configurationItemReferenceService.getReference(value));
	}

	@Override
	public boolean canGetFieldValue(InputFieldBase<?> field, Class<?> modelType) {
		return field instanceof ConfigurationItemReferenceField;
	}

	@Override
	public Object fieldToValue(ConfigurationItemReferenceField field, Class<?> modelType) {
		if (field.getValue()==null) {
			return null;
		}
		return dao.getCi(field.getValue());
	}

}
