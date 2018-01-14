/** 
 * 
 * Name: 		Haemin Ryu
 * Class:		CS360-01 Software Engineering
 * Instructor: 	eProf Sedlmeyer
 * Project:		Assignment 1: Welcome to the Real World
 * Date:		01/13/18
 * Due:			01/17/18
 * 
 **/
//Controller.java
//
package controller;

import model.HistoryModel;
import model.SiteModel;
import view.SiteView;

public class Controller {
	
	private SiteModel site;  
	private HistoryModel history;
	private SiteView view;
	
	// where the magic happens
	public static void main() {
		
	}
	
	// call model AND view
	// constructor
	public Controller(SiteModel site, SiteView view) {
		this.site = site;
		this.view = view;
	}
	
	/* CRUD operation for Site */
	// CREATE. add a site. view calls createSite from the controller
	public void createSite(SiteModel s) {
		
	}
	
	// READ. browse a site if you want to get 1 site return 'only one site'.
	public SiteModel getSite(int siteNum) {
		return null;
	}
	// READ. browse sites. return all the 'sites' which is an array of site objects
	public SiteModel[] getSites() {
		return null;
	}
	
	// UPDATE. edit and change a site. 
	public void updateSite(SiteModel s, int SiteNum) {
		//view.printDetails(model.getSites(), model.getSite(SiteNum));
	}
	
	// DELETE. delete a site.
	public void deleteSite(int SiteNum) {
	
	}
	
	/* CRUD operation for History */
	// CREATE. add a history item.
	public void createHistoryItem(HistoryModel h) {
		
	}
	
	// READ. browse history items. we can get all of history items at a time.
	public HistoryModel[] getHistoryItems() {
		//History[] h = {a, b, c, ..., g};
		return null;
	}
	
	// READ. browse a single history item.
	public HistoryModel getHistoryItem() {
		return null;
	}
	
	// we do not need to UPDATE because we should update once a while. 
	
	//DELETE. 
	public void deleteHistoryItem(int historyID) {
		
	}
	
	// updateMap using GoogleStaticAPI and return to the view
	public void updateMap() {
	
	}
	
	
	
	
	/*//without DB 
	public static void main(String[] args) {
		
		//
		Site model = SiteInfoFromDB();
		//
		History model = HistoryInfoFromDB();
		//
		Site view = new Site();
		
		updateSite(Sites, int SiteNum);
		updateMap();
		
		
	}
	
	 private static Site SiteInfoFromDB() {
		
		int siteNum;
		Site site = new Site();
		site
		site.getSite(siteNum);
		return site;
		
	}
	*/
}
