package orbag.dao.jpa;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestEntity {

	@GeneratedValue
	@Id
	Long id;
		
	String stringField;
	
	Integer integerField;
	
	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public Integer getIntegerField() {
		return integerField;
	}

	public void setIntegerField(Integer integerField) {
		this.integerField = integerField;
	}

	public Long getLongField() {
		return longField;
	}

	public void setLongField(Long longField) {
		this.longField = longField;
	}

	Long longField;
	
	
		
	
}
