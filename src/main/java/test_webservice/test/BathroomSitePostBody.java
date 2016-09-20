package test_webservice.test;

public class BathroomSitePostBody {
	int id;
	String gender;
	int numberStalls;
	int numberUrinals;
	double longitude;
	double latitude;
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

