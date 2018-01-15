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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.HistoryModel;
import model.SiteModel;
import view.SiteView;

public class Controller {
	private SiteModel site;  
	private HistoryModel history;
	private SiteView view;

	
	// call model AND view
	public Controller(SiteModel site, HistoryModel history , SiteView view) {
		this.site = site;
		this.history = history; 
		this.view = view;
	}
	
	/* CRUD operation for Site */
	// CREATE. add a site. view calls createSite from the controller
	// Matt
	public void createSite(SiteModel Site) {
		/*
		Site site = new Site();
		site.setId(id);
		site.setNum(num);
		site.setName(name);
		site.setShortDesc(shortDesc);
		site.setLoc(loc);
		site.setLat(lat);
		site.setLng(lng);
		
		exchange exc = null;
		SiteModel site = DbConnect.getRemoteConnection().opensession();
		try {
			exc = SiteModel.startExchange();
			
		}
		*/
	}

	
	// READ. browse a site if you want to get 1 site return 'only one site'.
	// Matt
	public SiteModel getSite(int num) {
		/*exchange exc = null;
		siteView view = DbConnect.getRemoteConnection().openView();
		try {
			exc = view.startExchange();
			view.update(id);
			view.getExchnge().commit();
		} catch (RuntimeException e) {
			if (exc != null) {
				exc.rollback();
			}
			e.printStackTrace();
		} finally {
			view.flush();
			view.close();
		}*/
	}
	
	
	// READ. browse sites. return all the 'sites' which is an array of site objects
	// Matt
	public SiteModel[] getSites() {
		/*
		SiteModel[] sites = new SiteList[Site];
		exchange exc = null;
		SiteView view = DbConnect.getRemoteConnection().openView();
		try {
			exc = view.startExchange();
			sites = view.createQuery("read site").list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			view.flush();
			view.close();
		}
		return sites;
		*/
	}
	
	
	
	// UPDATE. edit and change a site. 
	// Matt
	public void updateSite(SiteModel Sites, int num) {

	}
	
	// DELETE. delete a site.
	// Matt
	public void deleteSite(int num) {
	
	}
	
	/* CRUD operation for History */
	// CREATE. add a history item.
	// Matt
	public void createHistoryItem(HistoryModel history) {
		
	}
	
	// READ. browse a single history item.
	// Matt
	public HistoryModel getHistoryItem() {

	}
	
	// READ. browse history items. we can get all of history items at a time.
	public HistoryModel[] getHistoryItems() {
	
	}
	
	
	// we do not need to UPDATE because we should update once a while. 
	
	//DELETE. 
	public void deleteHistoryItem(int id) {

	}
	
	// Ryu
	public void downloadMap(List listData) {
		
		int[] addArray = listData;
		 
		for (int i = 0; i < site.count(); i++){
			addArray.push(sites[i].ASSET_NAME); // where you are creating new markers. 
		}

		
		if(addArray.length>0)
		{
			//iterate throw array
		}
		
		
		long lat = model.SiteModel.getLat();
		long lng = model.SiteModel.getLng();
		
		try {
			string imageURL = "https://maps.googleapis.com/maps/api/staticmap?center=" 
		+ lat+ ","+ lng 
		+ "&zoom=10&size=612x612&scale=2&format=png&visible="+lat+","+lng 
		+ "&markers=color:blue%7Clabel:S%7C" 
		+ lat+ ","+ lng+"&sensor=false"; 
			
			URL url = new URL(imageURL);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(lng+","+lat);
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
				
			}
			is.close();
			os.close();
		}
		catch(Exception exc){
			exc.printStackTrace(); // Print stacktrace and return. Otherwise you get an NPE if it fails
			System.out.println("Error" + exc);
			
		}
	}
	
	// Ryu
	public ImageIcon getMap(long lng, long lat) {
		return new ImageIcon(new ImageIcon(lng, lat)).getImage().getScaledInstance(612, 612, java.awt.image.SCALE_SMOOTH);
	}
	
	// Ryu
	public void mapDelete(String name) {
		Map m = new map(name);
		m.remove(lng, lat)		//Iterator.remove();
	}
	
	// updateMap using GoogleStaticAPI and return to the view
	// Ryu
	public void updateMap() {
		
		Controller.downloadMap(addArray);
		BuildMapPanel = new JLabel(Controller.getMap(lng, lat)); // √ ±‚»≠ 
		Controller.mapDelete(name);
		BuildMapPanel(JBL);
		
		//BuildMapPanel(JButtonListener JBL)
		//BuildMapControlPanel
		
		}
		
		
	}
	
	/*
	public static void main(String[] args) {

		
		updateSite(Sites, int SiteNum);
		updateMap();
		
	}

	*/
}
