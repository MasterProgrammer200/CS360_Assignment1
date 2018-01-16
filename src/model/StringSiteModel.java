package model;


public class StringSiteModel {

	///
	/// declare local fields
	///
	
	private int id;				// holds id of site
	private String num;			// holds site number
	private String name;		// holds the name of the site
	private String shortDesc;	// holds a short description of the site
	private String loc;			// holds the location of the site
	private String lat;			// holds the latitude of the site
	private String lng;			// holds the longitude of the site
	
	// getters and setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}	
}
