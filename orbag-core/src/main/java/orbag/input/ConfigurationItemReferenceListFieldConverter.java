package orbag.input;

import orbag.dao.ConfigurationItemDao;
import orbag.dao.ConfigurationItemNotFoundException;
import orbag.metadata.MetadataRegistry;
import orbag.metadata.UnmanagedObjectException;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ConfigurationItemReferenceListFieldConverter implements FieldValueGetter<ConfigurationItemReferenceListField>,FieldValueSetter<ConfigurationItemReferenceListField>{

	@Autowired
	MetadataRegistry metadataRegistry;
	
	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;
	
	@Autowired
	ConfigurationItemDao dao;
	
	@Override
	public boolean canSetValue(Object value, InputFieldBase<?> field) {
		return field instanceof ConfigurationItemReferenceListField;
	}

	@Override
	public void setFieldValue(Object value, ConfigurationItemReferenceListField field) {
		try {
			List<ConfigurationItemReference> referenceList =  new ArrayList<>();
 			if (value instanceof Collection) {
				for (Object object :  (Collection)value) {
					referenceList.add(configurationItemReferenceService.getReference(object));
				}
			}
			field.setValue( referenceList);
		} catch (UnmanagedObjectException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean canGetFieldValue(InputFieldBase<?> field, Class<?> modelType) {
		return field instanceof ConfigurationItemReferenceListField;
	}

	@Override
	public Object fieldToValue(ConfigurationItemReferenceListField field, Class<?> modelType) {
		if (field.getValue()==null) {
			return null;
		}
		try {
			return dao.getExistingCisOrThrow(field.getValue());
		}catch (UnmanagedObjectException| ConfigurationItemNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
