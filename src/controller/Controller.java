//Controller.java
//
package controller;

public class Controller {
	private Site model;  
	private History model;
	private SiteGUI view;
	
	// call model AND view
	public Controller(Site model, SiteGUI view) {
		this.model = model;
		this.view = view;
	}
	
	/* CRUD operation for Site */
	// CREATE. add a site. view calls createSite from the controller
	public void createSite(Sites) {
		
	}
	
	// READ. browse a site if you want to get 1 site return 'only one site'.
	public Site getSite(int SiteNum) {
		return model.getSiteNum();
	}
	// READ. browse sites. return all the 'sites' which is an array of site objects
	public Site[] getSites() {
		return model;
	}
	
	// UPDATE. edit and change a site. 
	public void updateSite(Sites, int SiteNum) {
		view.printDetails(model.getSites(), model.getSite(SiteNum));
	}
	
	// DELETE. delete a site.
	public void deleteSite(int SiteNum) {
	
	}
	
	/* CRUD operation for History */
	// CREATE. add a history item.
	public void createHistoryItem(History h) {
		
	}
	
	// READ. browse history items. we can get all of history items at a time.
	public History[] getHistoryItems() {
		History[] h = {a, b, c, ..., g};
		return h;
	}
	
	// READ. browse a single history item.
	public History getHistoryItem() {
		return HistoryItem;
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
