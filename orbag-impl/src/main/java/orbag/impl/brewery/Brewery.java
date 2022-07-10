package orbag.impl.brewery;

import java.time.LocalDateTime;

import orbag.metadata.ConfigurationItem;
import orbag.metadata.Manageable;

@ConfigurationItem
public class Brewery implements Manageable<String> {

	String id;

	String name;

	String brewery_type;

	String street;

	String address_2;

	String address_3;

	String city;

	String state;

	String cuntry_province;

	String postal_code;

	String country;

	String longitude;

	String latitude;

	String phome;

	String website_url;

	LocalDateTime updated_at;

	LocalDateTime created_at;

	@Override
	public String getIdentifier() {
		return id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrewery_type() {
		return brewery_type;
	}

	public void setBrewery_type(String brewery_type) {
		this.brewery_type = brewery_type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress_2() {
		return address_2;
	}

	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}

	public String getAddress_3() {
		return address_3;
	}

	public void setAddress_3(String address_3) {
		this.address_3 = address_3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCuntry_province() {
		return cuntry_province;
	}

	public void setCuntry_province(String cuntry_province) {
		this.cuntry_province = cuntry_province;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPhome() {
		return phome;
	}

	public void setPhome(String phome) {
		this.phome = phome;
	}

	public String getWebsite_url() {
		return website_url;
	}

	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	@Override
	public String getDisplayLabel() {
		return name + " (" + city + " - " + state + ")";
	}
}
