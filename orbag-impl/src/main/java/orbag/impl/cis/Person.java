package orbag.impl.cis;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import orbag.metadata.ConfigurationItem;

@ConfigurationItem(category = "Party")
@Entity
public class Person extends RootConfigurationItem{

	
	Date birthDate;

	@Enumerated(EnumType.STRING)
	Gender gender;
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
}
