/**
 * 
 * 
 * 
 */
package model;

import java.util.Date;

public class HistoryModel {
	
	///
	/// declare local fields
	///
	
	private int id;				// holds id of history item
	private int siteNum;		// holds site number associated with this history item
	private char action;		// holds the action performed for this history item
	private Date date;			// holds the date this history item was created
	
	///
	/// getters and setters
	///
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSiteNum() {
		return siteNum;
	}
	public void setSiteNum(int siteNum) {
		this.siteNum = siteNum;
	}
	public char getAction() {
		return action;
	}
	public void setAction(char action) {
		this.action = action;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
