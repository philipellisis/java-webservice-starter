package test_webservice.test;

public class BathroomSitePostBody {
	@Order(order=1)
	String gender;
	@Order(order=2)
	Integer numberStalls;
	@Order(order=3)
	Integer numberUrinals;
	@Order(order=4)
	Double longitude;
	@Order(order=5)
	Double latitude;

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getNumberStalls() {
		return numberStalls;
	}
	public void setNumberStalls(int numberStalls) {
		this.numberStalls = numberStalls;
	}
	public int getNumberUrinals() {
		return numberUrinals;
	}
	public void setNumberUrinals(int numberUrinals) {
		this.numberUrinals = numberUrinals;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
