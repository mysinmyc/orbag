package orbag.input;

import jakarta.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FieldgroupConverter implements AttributeConverter<SerializableFieldGroup, String> {

	static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(SerializableFieldGroup attribute) {
		try {
			return objectMapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public SerializableFieldGroup convertToEntityAttribute(String dbData) {
		try {
			return objectMapper.readValue(dbData, SerializableFieldGroup.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
