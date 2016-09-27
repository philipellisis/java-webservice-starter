package test_webservice.test;

public class BathroomSite {
	int id;
	String gender;
	Integer numberStalls;
	Integer numberUrinals;
	Double longitude;
	Double latitude;
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getNumberStalls() {
		return numberStalls;
	}
	public void setNumberStalls(Integer numberStalls) {
		this.numberStalls = numberStalls;
	}
	public Integer getNumberUrinals() {
		return numberUrinals;
	}
	public void setNumberUrinals(Integer numberUrinals) {
		this.numberUrinals = numberUrinals;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
}

