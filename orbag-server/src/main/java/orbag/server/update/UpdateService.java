package orbag.server.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.input.InputFieldBase;
import orbag.input.SerializableFieldGroup;
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.ConfigurationItemPropertyDescriptor;
import orbag.metadata.MetadataRegistry;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;

@Component
public class UpdateService {

	@Autowired
	MetadataRegistry metadataRegistry;
	
	@Autowired
	ConfigurationItemDao dao;
	
	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;
	public UpdateRequest getUpdateRequestTemplateFor(String configurationItemType, String configurationItemId,
			Authentication user) {
	
		ConfigurationItemDescriptor descriptor = metadataRegistry.getConfigurationItemDescriptorByName(configurationItemType);
	
		
		Object ci = dao.getCi(new ConfigurationItemReference(configurationItemId, configurationItemType));
		UpdateRequest updateRequest = new UpdateRequest();
	
		SerializableFieldGroup fieldGroup = new SerializableFieldGroup();
		descriptor.getProperties().forEach(p -> {	
			addProperty(ci,p,fieldGroup,p.getGetterMethod().getReturnType());
		});
		updateRequest.setProperties(fieldGroup);
		updateRequest.setConfigurationItem(configurationItemReferenceService.getReference(ci));
		return updateRequest;
	}

	@SuppressWarnings("unchecked")
	private <T> void addProperty(Object ci, ConfigurationItemPropertyDescriptor p, SerializableFieldGroup fieldGroup, Class<T> type) {
		InputFieldBase<T> field = (InputFieldBase<T>) fieldGroup.addFieldOfType(p.getName(), p.getDisplayLabel(),type );
		if (field!=null) {
			field.setReadOnly(p.isReadOnly());
			try {
				field.setValue((T) p.getGetterMethod().invoke(ci));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public ConfigurationItemReference update(UpdateRequest request, Authentication user) {
		Object ci = dao.getCi(request.getConfigurationItem());
		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry.getConfigurationItemDescriptorByClass(ci.getClass());
		for (InputFieldBase<?> parameter : request.getProperties().getFields()) {
			if (! parameter.isChanged()) {
				continue;
			}
			ConfigurationItemPropertyDescriptor property = configurationItemDescriptor.getProperty(parameter.getName());
			if (property==null) {
				throw new RuntimeException("Invalid property "+parameter.getName());
			}
			if (property.isReadOnly()) {
				throw new RuntimeException("ReadOnly property "+parameter.getName());
			}
			try {
				configurationItemDescriptor.getProperty(parameter.getName()).getSetterMethod().invoke(ci, parameter.getValue());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		dao.update(ci);
		return configurationItemReferenceService.getReference(ci);
	}

}
