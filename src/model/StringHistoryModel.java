package model;

import java.sql.Date;

public class StringHistoryModel {

	///
	/// declare local fields
	///
	
	private int id;				// holds id of history item
	private String siteNum;		// holds site number associated with this history item
	private String action;		// holds the action performed for this history item
	private String date;		// holds the date this history item was created
	
	///
	/// getters and setters
	///
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSiteNum() {
		return siteNum;
	}
	public void setSiteNum(String siteNum) {
		this.siteNum = siteNum;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
