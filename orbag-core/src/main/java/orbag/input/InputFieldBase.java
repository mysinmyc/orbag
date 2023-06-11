package orbag.input;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.PROPERTY,property = "type")
@JsonSubTypes({@Type(value = StringField.class, name = "string"),
	@Type(value = NumericField.class, name = "numeric"),
	@Type(value = BooleanField.class, name = "boolean"),
	@Type(value = EnumField.class, name = "enum"),
	@Type(value = ConfigurationItemReferenceField.class, name = "reference")
})
*/
public abstract class InputFieldBase<T> {
	
	String name;

	String displayLabel;

	String category;

	T value;
	
	boolean readOnly;

	boolean changed;
	
	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	@JsonIgnore
	public boolean isEmpty() {
		return value == null;
	}
	
	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public abstract void parseValue(String value);

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
