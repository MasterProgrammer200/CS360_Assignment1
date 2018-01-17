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

import java.io.File;
//Controller.java
//
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
	private final String COLUMN_DATE_CREATED = "date_created";

	private final String TABLE_HISTORY = "history";
	private final String COLUMN_HISTORY_ID = "id";
	private final String COLUMN_HISTORY_SITE_NUM = "site_num";
	private final String COLUMN_HISTORY_ACTION = "action";
	private final String COLUMN_HISTORY_DATE = "date";
	
	private int zoomNum;
	
//	// where the magic happens
//	public static void main(String[] args) {
//		Controller c = new Controller();
//		ArrayList<SiteModel> s = c.getSites();
//		System.out.println(s.toString());
//		//UpdateMap updatemap = new UpdateMap(); 
//	}
	
	// call model AND view
	public Controller() {
		db = new DbConnect();
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
					+ COLUMN_LONGITUDE  + ", " 
					+ COLUMN_DATE_CREATED + ") "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?);";

			// initialize the prepare statement, execute it, and
			// store the result
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, s.getNum());
			stmt.setString(2, s.getName());
			stmt.setString(3, s.getShortDesc());
			stmt.setString(4, s.getLoc());
			stmt.setBigDecimal(5, s.getLat());
			stmt.setBigDecimal(6, s.getLng());
			stmt.setDate(7, s.getDateCreated());
			
			result = stmt.execute();
			
			// check if insurt was successful 
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
			result.setDateCreated(rs.getDate(COLUMN_DATE_CREATED));

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
				s.setDateCreated(rs.getDate(COLUMN_DATE_CREATED));
				
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

	public void ZoomIn() {
		zoomNum++;
		if (zoomNum > 20) {
			zoomNum = 20;
		}
	}
	
	public void ZoomOut() {
		zoomNum--;
		if (zoomNum < 1) {
			zoomNum = 1;
		}
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
			+ COLUMN_DATE_CREATED + " = ? "
			+ "WHERE " + COLUMN_SITE_NUM + " = ?;";

			// initialize the prepare statement, execute it, and
			// store the result
			stmt = conn.prepareStatement(query);
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getShortDesc());
			stmt.setString(3, s.getLoc());
			stmt.setBigDecimal(4, s.getLat());
			stmt.setBigDecimal(5, s.getLng());
			stmt.setDate(6, s.getDateCreated());
			stmt.setInt(7, s.getId());
			rs = stmt.executeQuery();
			rs.next();

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
			query = "DELETE FROM " + TABLE_SITE +  " WHERE " + COLUMN_SITE_NUM + " = ?;";

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

	// READ. browse history items. we can get all of history items at a time.
	public ArrayList<HistoryModel> getHistoryItems(int siteNum) {
	
		///
		/// declare local variables
		///

		ArrayList<HistoryModel> result; // holds the site retrieved from the database
		Connection conn; // holds the connection to the database
		String query; // holds query string
		PreparedStatement stmt; // holds Prepared Statement to execute on the database
		ResultSet rs; // holds the result from the database

		// initialize variables
		result = new ArrayList<HistoryModel>();
		conn = null;
		query = null;
		stmt = null;
		rs = null;

		try {

			// connect to the database
			conn = db.getRemoteConnection();

			// concatenate select query
			query = "SELECT * FROM " + TABLE_HISTORY + " WHERE " + COLUMN_HISTORY_SITE_NUM + " = " + " ?;";

			// initialize the prepare statement, execute it, and
			// store the result
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, siteNum);
			rs = stmt.executeQuery();
			
			// loop through each object returned from the database
			while (rs.next()) {
				
				HistoryModel h = new HistoryModel();

				// store the result from the database in the site object
				h.setId(rs.getInt(COLUMN_HISTORY_ID));
				h.setSiteNum(rs.getInt(COLUMN_HISTORY_SITE_NUM));
				h.setAction(rs.getString(COLUMN_HISTORY_ACTION).charAt(0));
				h.setDate(rs.getDate(COLUMN_HISTORY_DATE));
				
				// add the site to the list
				result.add(h);

			}

		} catch (SQLException ex) {
			db.printSQLError(ex);
		} finally {
			db.closeConnection(conn);
		}

		// return the site retrieved from the database
		return result;
	}

	// we do not need to UPDATE because we should update once a while.

	// DELETE.
	public boolean deleteHistoryItem(int siteNum) {
		
		///
		/// declare local variables
		///

		boolean result; // holds whether the histories were successfully deleted
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
			query = "DELETE FROM " + TABLE_HISTORY +  " WHERE " + COLUMN_HISTORY_SITE_NUM + " = ?;";

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
	
	/**
	 * 
	 * checkHistory() takes a history item and checks if its date
	 * field is older than three months.
	 * 
	 * @param history : the history item whose date we should compare
	 * 
	 * @return whether the history item is older than three months
	 * 
	 */
	public boolean checkHistory(HistoryModel history) {
		
		///
		/// declare local variables
		///
		boolean isOlderThan3Months;	// holds whether the history item is older than three months
		LocalDate historyDate;		// holds the history date
		
		// initialize variables
		isOlderThan3Months = false;
		historyDate = LocalDate.parse(history.getDate().toString());
		
		// check if the history date is older than three months
		if (historyDate.isBefore(LocalDate.now().minusMonths(3))) {
			// set flag to tree
			isOlderThan3Months = true;
		}
		
		// return whether the history item is older than three months
		return isOlderThan3Months;
		

	}
	
	/**
	 * 
	 * siteArrayListToArray() takes a Site array list and converts it into
	 * an array.
	 * 
	 * @param strArrList : the array list to transform into an array
	 * 
	 * @return the resulting String Site array
	 * 
	 */
	public String[] siteArrayListToArray(ArrayList<SiteModel> strArrList) {
		
		///
		/// declare local variables
		///
		String[] strSiteArray;
		
		// Initialize local variables
		strSiteArray = new String[strArrList.size()];	// set array size to that of the array list
		
		// loop through each item in the array
		for (int i = 0; i < strSiteArray.length; i++) {
			
			// get the site at the current index
			SiteModel s = strArrList.get(i);
			
			// set the current array item with the values at the current site
			strSiteArray[i] = s.getNum() + "";
			
		}
		
		// return the newly created array of string sites
		return strSiteArray;
		
	}
	
	/**
	 * 
	 * historyArrayListToArray() takes history array list and converts it into
	 * an history array.
	 * 
	 * @param historyArrList : the array list to transform into an array
	 * 
	 * @return the resulting String History array
	 * 
	 */
	public String[] historyArrayListToArray(ArrayList<HistoryModel> historyArrList) {
		
		///
		/// declare local variables
		///
		String[] strHistoryArray;
		
		// Initialize local variables
		strHistoryArray = new String[historyArrList.size()];	// set array size to that of the array list
		
		// loop through each item in the array
		for (int i = 0; i < strHistoryArray.length; i++) {
			
			// get the site at the current index
			HistoryModel h = historyArrList.get(i);
			
			// set the current array item with the values at the current site
			strHistoryArray[i] = "" + h.getDate();
			
		}
		
		// return the newly created array of string histories
		return strHistoryArray;
		
	}
	
	
	public String changeLocation(ArrayList<SiteModel> strArrList) {
		ArrayList<SiteModel> Sites = getSites();
		String locat = new String(); 

		for(int i = 0; i < Sites.size(); i++) {
			locat += "&markers=size:tiny%7Ccolor:red%7C";
			locat += Sites.get(i).getLat()+ "," + Sites.get(i).getLng(); // latitude , longitude
			//System.out.print(locat);
		} 
		return locat;
	}
		
		
/*		String[] loca = null;
		//int keynum = site.getNum();
		for (int i = 0; i < strArrList.length; i++) {
			// set the current array item with the values at the current site
			loca[i] = site.getLat()+ "," + site.getLng();
			while (true) {
				loca[i] += i;
				i++;
				System.out.print("&markers=size:tiny%7Ccolor:red%7C" 
								+ loca);
				if(loca[i]==null)break;
			  }
	}*/


	public void downloadMap(String location) {

		ArrayList<SiteModel> Sites = getSites();
		String locat = changeLocation(Sites);
		zoomNum = 8; //default of the zoom size
		
		try {
			String imageURL = "https://maps.googleapis.com/maps/api/staticmap?center=BUTLER,IN" 
		//+ location 
		+ "&zoom="+zoomNum+"&size=250x250&scale=2&format=png&sensor=false&visible="+ location //if you want to use UTF-8: URLEncoder.encode(location, "UTF-8")
		+ "&markers=size:tiny%7Ccolor:red%7C" 
		+ locat;
					
			URL url = new URL(imageURL);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(location);
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
				
			}
			is.close();
			os.close();
		}
		catch(Exception exc){
			exc.printStackTrace(); // Print stack trace and return.
			System.out.println("Error" + exc);
			
		}
	}
	
	//get Image of the static Map
	public ImageIcon getMap(String location) { //bring the file which was download
		return new ImageIcon((new ImageIcon(location)).getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH)); //니가 다운로드 해 받은 그 이미지 파일을 가져올 수 있도록 합니다. ctrl shift o, scale smooth라고 힌트를 넣어줌
	}
	
	public void MapFileDelete(String fileName) {
		File f = new File(fileName);
		f.delete();
	}
	
	// updateMap using GoogleStaticAPI and return to the view
	public JLabel updateMap() {
		ArrayList<SiteModel> Sites = getSites();
		String location = new String(); 
		JLabel googleMap = new JLabel(); // Declaration by variables 	
		
		for(int i = 0; i < Sites.size(); i++) {
			location = Sites.get(i).getLat()+ "," + Sites.get(i).getLng(); // latitude , longitude
		} 
		
		downloadMap(location);//Search for the actual address
		googleMap = new JLabel(getMap(location)); //Reset to the map you download
		MapFileDelete(location);//Delete the corresponding image file from the program. 
		//JLabelPanel.add(siteLocationLabel);// Google Maps are launched in JFrame
		
		
		
		return googleMap;
		
	
	}
}

