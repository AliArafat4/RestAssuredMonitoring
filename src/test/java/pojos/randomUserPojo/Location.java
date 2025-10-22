package pojos.randomUserPojo;

public class Location{
	private String country;
	private String city;
	private Street street;
	private Timezone timezone;
	private Integer postcode;
	private Coordinates coordinates;
	private String state;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setStreet(Street street){
		this.street = street;
	}

	public Street getStreet(){
		return street;
	}

	public void setTimezone(Timezone timezone){
		this.timezone = timezone;
	}

	public Timezone getTimezone(){
		return timezone;
	}

	public void setPostcode(Integer postcode){
		this.postcode = postcode;
	}

	public Integer getPostcode(){
		return postcode;
	}

	public void setCoordinates(Coordinates coordinates){
		this.coordinates = coordinates;
	}

	public Coordinates getCoordinates(){
		return coordinates;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	@Override
 	public String toString(){
		return 
			"Location{" + 
			"country = '" + country + '\'' + 
			",city = '" + city + '\'' + 
			",street = '" + street + '\'' + 
			",timezone = '" + timezone + '\'' + 
			",postcode = '" + postcode + '\'' + 
			",coordinates = '" + coordinates + '\'' + 
			",state = '" + state + '\'' + 
			"}";
		}
}