/** 
 * 
 * Name: 		Haemin Ryu and Matt Hunt
 * Class:		CS360-01 Software Engineering
 * Instructor: 	eProf Sedlmeyer
 * Project:		Assignment 1: Welcome to the Real World
 * Date:		01/13/18
 * Due:			01/17/18
 * 
 **/
package controller; 

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DbConnect;
import model.HistoryModel;
import model.SiteModel;
import view.SiteView;

public class Controller {

	private SiteModel site;
	private HistoryModel history;
	private SiteView view;
	private DbConnect db;

	private final String TABLE_SITE = "site";
	private final String COLUMN_ID = "id";
	private final String COLUMN_SITE_NUM = "num";
	private final String COLUMN_NAME = "name";
	private final String COLUMN_SHORT_DESC = "short_desc";
	private final String COLUMN_LOCATION = "loc";
	private final String COLUMN_LATITUDE = "lat";
	private final String COLUMN_LONGITUDE = "lng";

	private final String TABLE_HISTORY = "history";
	private final String COLUMN_HISTORY_ID = "id";
	private final String COLUMN_HISTORY_SITE_NUM = "num";
	private final String COLUMN_HISTORY_ACTION = "action";
	private final String COLUMN_HISTORY_DATE = "date";
	
	// where the magic happens
	public static void main(String[] args) {
		Controller c = new Controller(null, null, null);
		ArrayList<SiteModel> s = c.getSites();
		System.out.println(s.toString());
	}
	
	// call model AND view
	public Controller(SiteModel site, HistoryModel history , SiteView view) {
		this.site = site;
		this.history = history; 
		this.view = view;
	}

	/* CRUD operation for Site */
	// CREATE. add a site. view calls createSite from the controller
	public boolean createSite(SiteModel s) {

		///
		/// declare local variables
		///

		boolean result; // holds whether the site was successfully created
		Connection conn; // holds the connection to the database
		String query; // holds query string
		PreparedStatement stmt; // holds Prepared Statement to execute on the database
		ResultSet rs; // holds the result from the database
		
		// initialize variables
		result = false;
		conn = null;
		query = null;
		stmt = null;
		rs = null;

		try {

			// connect to the database
			conn = db.getRemoteConnection();

			// concatenate select query
			query = "INSERT INTO " + TABLE_SITE + " (" 
					+ COLUMN_SITE_NUM + ", " 
					+ COLUMN_NAME + ", " 
					+ COLUMN_SHORT_DESC + ", "  
					+ COLUMN_LOCATION + ", " 
					+ COLUMN_LATITUDE + ", " 
					+ COLUMN_LONGITUDE + ") "
					+ "VALUES(?, ?, ?, ?, ?, ?);";

			// initialize the prepare statement, execute it, and
			// store the result
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, s.getNum());
			stmt.setString(2, s.getName());
			stmt.setString(3, s.getShortDesc());
			stmt.setString(4, s.getLoc());
			stmt.setBigDecimal(5, s.getLat());
			stmt.setBigDecimal(6, s.getLng());
			int count = stmt.executeUpdate();
			
			// check if insurt was successful 
			if (count > 0) {
				result = true;
			}

		} catch (SQLException ex) {
			db.printSQLError(ex);
		} finally {
			db.closeConnection(conn);
		}
		
		return result;
	}

	// READ. browse a site if you want to get 1 site return 'only one site'.
	public SiteModel getSite(int siteNum) {

		///
		/// declare local variables
		///

		SiteModel result; // holds the site retrieved from the database
		Connection conn; // holds the connection to the database
		String query; // holds query string
		PreparedStatement stmt; // holds Prepared Statement to execute on the database
		ResultSet rs; // holds the result from the database

		// initialize variables
		result = new SiteModel();
		conn = null;
		query = null;
		stmt = null;
		rs = null;

		try {

			// connect to the database
			conn = db.getRemoteConnection();

			// concatenate select query
			query = "SELECT * FROM " + TABLE_SITE + " WHERE " + COLUMN_SITE_NUM + " = " + " ?;";

			// initialize the prepare statement, execute it, and
			// store the result
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, siteNum);
			rs = stmt.executeQuery();
			rs.next();

			// store the result from the database in the site object
			result.setId(rs.getInt(COLUMN_ID));
			result.setNum(rs.getInt(COLUMN_SITE_NUM));
			result.setName(rs.getString(COLUMN_NAME));
			result.setShortDesc(rs.getString(COLUMN_SHORT_DESC));
			result.setLoc(rs.getString(COLUMN_LOCATION));
			result.setLat(rs.getBigDecimal(COLUMN_LATITUDE));
			result.setLng(rs.getBigDecimal(COLUMN_LONGITUDE));

		} catch (SQLException ex) {
			db.printSQLError(ex);
		} finally {
			db.closeConnection(conn);
		}

		// return the site retrieved from the database
		return result;
	}

	// READ. browse sites. return all the 'sites' which is an array of site objects
	public ArrayList<SiteModel> getSites() {
		
		///
		/// declare local variables
		///
		ArrayList<SiteModel> result; // holds the site retrieved from the database
		Connection conn; // holds the connection to the database
		String query; // holds query string
		PreparedStatement stmt; // holds Prepared Statement to execute on the database
		ResultSet rs; // holds the result from the database

		// initialize variables
		result = new ArrayList<SiteModel>();
		conn = null;
		query = null;
		stmt = null;
		rs = null;

		try {

			// connect to the database
			conn = db.getRemoteConnection();

			// concatenate select query
			query = "SELECT * FROM " + TABLE_SITE;

			// initialize the prepare statement, execute it, and
			// store the result
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			// loop through each object returned from the database
			while (rs.next()) {

				SiteModel s = new SiteModel();

				// store the result from the database in the site object
				s.setId(rs.getInt(COLUMN_ID));
				s.setNum(rs.getInt(COLUMN_SITE_NUM));
				s.setName(rs.getString(COLUMN_NAME));
				s.setShortDesc(rs.getString(COLUMN_SHORT_DESC));
				s.setLoc(rs.getString(COLUMN_LOCATION));
				s.setLat(rs.getBigDecimal(COLUMN_LATITUDE));
				s.setLng(rs.getBigDecimal(COLUMN_LONGITUDE));
				
				// add the site to the list
				result.add(s);

			}

		} catch (SQLException ex) {
			db.printSQLError(ex);
		} finally {
			db.closeConnection(conn);
		}

		// return the site retrieved from the database
		return result;
	}

	// UPDATE. edit and change a site.
	public void updateSite(SiteModel s, int SiteNum) {

		///
		/// declare local variables
		///

		SiteModel result; // holds the site retrieved from the database
		Connection conn; // holds the connection to the database
		String query; // holds query string
		PreparedStatement stmt; // holds Prepared Statement to execute on the database
		ResultSet rs; // holds the result from the database

		// initialize variables
		result = new SiteModel();
		conn = null;
		query = null;
		stmt = null;
		rs = null;

		try {

			// connect to the database
			conn = db.getRemoteConnection();

			// concatenate select query
			query = "UPDATE " + TABLE_SITE + " SET " 
			+ COLUMN_NAME + " = ?, " 
			+ COLUMN_SHORT_DESC + " = ?, "  
			+ COLUMN_LOCATION + " = ?, " 
			+ COLUMN_LATITUDE + " = ?, "
			+ COLUMN_LONGITUDE + " = ?, "
			+ "WHERE " + COLUMN_SITE_NUM + " = ?;";

			// initialize the prepare statement, execute it, and
			// store the result
			stmt = conn.prepareStatement(query);
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getShortDesc());
			stmt.setString(3, s.getLoc());
			stmt.setBigDecimal(4, s.getLat());
			rs = stmt.executeQuery();
			rs.next();

			// store the result from the database in the site object
			result.setId(rs.getInt(COLUMN_ID));
			result.setNum(rs.getInt(COLUMN_SITE_NUM));
			result.setName(rs.getString(COLUMN_NAME));
			result.setShortDesc(rs.getString(COLUMN_SHORT_DESC));
			result.setLoc(rs.getString(COLUMN_LOCATION));
			result.setLat(rs.getBigDecimal(COLUMN_LATITUDE));
			result.setLng(rs.getBigDecimal(COLUMN_LONGITUDE));

		} catch (SQLException ex) {
			db.printSQLError(ex);
		} finally {
			db.closeConnection(conn);
		}
	}

	public boolean deleteSite(int siteNum) {
		///
		/// declare local variables
		///

		boolean result; // holds whether the site was successfully created
		Connection conn; // holds the connection to the database
		String query; // holds query string
		PreparedStatement stmt; // holds Prepared Statement to execute on the database
		ResultSet rs; // holds the result from the database

		// initialize variables
		result = false;
		conn = null;
		query = null;
		stmt = null;
		rs = null;

		try {

			// connect to the database
			conn = db.getRemoteConnection();

			// concatenate select query
			query = "DELETE FROM " + TABLE_SITE +  "WHERE " + COLUMN_SITE_NUM + " = ?;";

			// initialize the prepare statement, execute it, and
			// store the result
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, siteNum);
			int count = stmt.executeUpdate();

			// check if insert was successful
			if (count > 0) {
				result = true;
			}

		} catch (SQLException ex) {
			db.printSQLError(ex);
		} finally {
			db.closeConnection(conn);
		}

		return result;
	}

	/* CRUD operation for History */
	// CREATE. add a history item.
	public boolean createHistoryItem(HistoryModel h) {
		
		///
		/// declare local variables
		///

		boolean result; // holds whether the history item was successfully created
		Connection conn; // holds the connection to the database
		String query; // holds query string
		PreparedStatement stmt; // holds Prepared Statement to execute on the database
		ResultSet rs; // holds the result from the database
		
		// initialize variables
		result = false;
		conn = null;
		query = null;
		stmt = null;
		rs = null;

		try {

			// connect to the database
			conn = db.getRemoteConnection();

			// concatenate select query
			query = "INSERT INTO " + TABLE_HISTORY + " (" 
					+ COLUMN_HISTORY_SITE_NUM + ", " 
					+ COLUMN_HISTORY_ACTION + ", " 
					+ COLUMN_HISTORY_DATE + ")"  
					+ "VALUES(?, ?, ?);";

			// initialize the prepare statement, execute it, and
			// store the result

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, h.getSiteNum());
			stmt.setString(2, ""+h.getAction());
			stmt.setDate(3, h.getDate());

			int count = stmt.executeUpdate();
			
			// check if insurt was successful 
			if (count > 0) {
				result = true;
			}

		} catch (SQLException ex) {
			db.printSQLError(ex);
		} finally {
			db.closeConnection(conn);
		}
		
		return result;
		
	}

	// READ. browse history items. we can get all of history items at a time.
	public HistoryModel[] getHistoryItems() {
		// History[] h = {a, b, c, ..., g};
		return null;
	}

	// READ. browse a single history item.
	public HistoryModel getHistoryItem() {
		return null;
	}

	// we do not need to UPDATE because we should update once a while.

	// DELETE.
	public void deleteHistoryItem(int historyID) {
		
	}

	
	// Ryu
	public void downloadMap(List listData) {
		
		int[] addArray;
		 
		for (int i = 0; i < listData.length; i++){
			listData.push(addArray[i].ASSET_NAME);// where you are creating new markers. 
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
