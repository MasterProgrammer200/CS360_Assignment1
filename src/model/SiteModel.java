/** 
 * 
 * Name: 		Matthew Hunt
 * Class:		CS360-01 Software Engineering
 * Instructor: 	eProf Sedlmeyer
 * Project:		Assignment 1: Welcome to the Real World
 * Date:		01/14/18
 * Due:			01/17/18
 * 
 **/
package model;

public class SiteModel {
	
	///
	/// declare local fields
	///
	
	private int id;				// holds id of site
	private int num;			// holds site number
	private String name;		// holds the name of the site
	private String shortDesc;	// holds a short description of the site
	private String loc;			// holds the location of the site
	private long lat;			// holds the latitude of the site
	private long lng;			// holds the longitude of the site
	
	///
	/// getters and setters
	///
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
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
	public long getLat() {
		return lat;
	}
	public void setLat(long lat) {
		this.lat = lat;
	}
	public long getLng() {
		return lng;
	}
	public void setLng(long lng) {
		this.lng = lng;
	}
	
}
