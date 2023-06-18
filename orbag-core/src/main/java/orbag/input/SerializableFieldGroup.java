package orbag.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Standard implementation of a fieldgroup that can be safely serialized
 *
 * <p>Each type of field has a separate array to avoid issues with some clients that doesn't handle correctly dynamic typing
 */
public class SerializableFieldGroup implements FieldGroupBuilder, FieldGroupConsumer {

	List<BooleanField> booleanFields = new ArrayList<>();
	List<ConfigurationItemReferenceField> configurationItemReferenceFields = new ArrayList<>();
	List<EnumField> enumFields = new ArrayList<>();
	List<NumericField> numericFields = new ArrayList<>();
	List<StringField> stringFields = new ArrayList<>();
	
	transient List<InputFieldBase<?>> fieldsCache;
	
	
	public List<BooleanField> getBooleanFields() {
		return booleanFields;
	}



	public void setBooleanFields(List<BooleanField> booleanFields) {
		this.booleanFields = booleanFields;
	}



	public List<ConfigurationItemReferenceField> getConfigurationItemReferenceFields() {
		return configurationItemReferenceFields;
	}



	public void setConfigurationItemReferenceFields(
			List<ConfigurationItemReferenceField> configurationItemReferenceFields) {
		this.configurationItemReferenceFields = configurationItemReferenceFields;
	}



	public List<EnumField> getEnumFields() {
		return enumFields;
	}



	public void setEnumFields(List<EnumField> enumFields) {
		this.enumFields = enumFields;
	}



	public List<NumericField> getNumericFields() {
		return numericFields;
	}



	public void setNumericFields(List<NumericField> numericFields) {
		this.numericFields = numericFields;
	}



	public List<StringField> getStringFields() {
		return stringFields;
	}



	public void setStringFields(List<StringField> stringFields) {
		this.stringFields = stringFields;
	}

	@Override
	@JsonIgnore
	public List<InputFieldBase<?>> getFields() {
		if (this.fieldsCache==null) {
			List<InputFieldBase<?>> fieldsCache = new ArrayList<InputFieldBase<?>>();
			Stream.of(booleanFields,configurationItemReferenceFields,enumFields,numericFields,stringFields).forEach(fieldsCache::addAll);
			this.fieldsCache = fieldsCache;
		}
		return this.fieldsCache;
	}



	public InputFieldBase<?> getField(String name) {
		for (InputFieldBase<?> current : getFields()) {
			if (current.getName().equals(name)) {
				return current;
			}
		}
		return null;
	}

	private <T extends InputFieldBase<?>> T buildInternal(String name, String displayLabel, Supplier<T> supplier, List<T> container)
			throws DuplicateFieldException {
		if (getField(name) != null) {
			throw new DuplicateFieldException("duplicate field " + name);
		}
		T newField = supplier.get();
		newField.setName(name);
		newField.setDisplayLabel(displayLabel);
		container.add(newField);
		fieldsCache.add(newField);
		return newField;
	}

	@Override
	public EnumField addEnumField(String name, String displayLabel,Class<?> enumType) throws DuplicateFieldException {
		EnumField enumField= buildInternal(name, displayLabel, EnumField::new,enumFields);
		enumField.setAllowedValues(Arrays.asList(enumType.getEnumConstants()).stream().map( e -> e.toString()).toList());
		return enumField;
	}
	
	@Override
	public StringField addStringField(String name, String displayLabel) throws DuplicateFieldException {
		return buildInternal(name, displayLabel, StringField::new,stringFields);
	}

	@Override
	public NumericField addNumericField(String name, String displayLabel) throws DuplicateFieldException {
		return buildInternal(name, displayLabel, NumericField::new,numericFields);
	}

	@Override
	public BooleanField addBooleanField(String name, String displayLabel) throws DuplicateFieldException {
		return buildInternal(name, displayLabel, BooleanField::new,booleanFields);
	}

	@Override
	public ConfigurationItemReferenceField addReferenceField(String name, String displayLabel, String configurationItemType ) throws DuplicateFieldException {
		ConfigurationItemReferenceField configurationItemReferenceField= buildInternal(name, displayLabel, ConfigurationItemReferenceField::new,configurationItemReferenceFields);
		configurationItemReferenceField.setConfigurationItemType(configurationItemType);
		return configurationItemReferenceField;
	}


}
