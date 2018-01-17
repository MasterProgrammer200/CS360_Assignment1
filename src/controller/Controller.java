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
import java.math.BigDecimal;
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
	private BigDecimal latNum;
	private BigDecimal lngNum;

//	// where the magic happens
//	public static void main(String[] args) {
//		Controller c = new Controller();
//		ArrayList<SiteModel> s = c.getSites();
//		System.out.println(s.toString());
//	}
	
	// call model AND view
	public Controller() {
		db = new DbConnect();
		zoomNum = 8; //default of the zoom size
		latNum = new BigDecimal(41.427043);
		lngNum = new BigDecimal(-84.871626);
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
			
			// check if insert was successful 
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
			
			//change the center address
			latNum = result.getLat();
			lngNum = result.getLng();
			zoomNum = 13;
			
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
			query = "SELECT * FROM " + TABLE_HISTORY + " WHERE " + COLUMN_HISTORY_SITE_NUM + " = " + "?;";

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
	
	//when click '+' button in the zoom
	public void ZoomIn() {
		zoomNum++;
		if (zoomNum > 20) { //set a limit size, maximum
			zoomNum = 20;
		}
	}
	
	//when click '-' button in the zoom
	public void ZoomOut() {
		zoomNum--;
		if (zoomNum < 1) { //set a limit size, minimum
			zoomNum = 1;
		}
	}
	
	//when click 'Up' button of the pan
	public void panUp() {

		BigDecimal plus, empty;
		plus = new BigDecimal(0.1);  //++0.1, To increase the latitude number
		empty = new BigDecimal(0); //new center latitude number
		
		empty = latNum.add(plus); //latNum = default center latitude number
		latNum = empty;
	}
	
	//when click 'Down' button of the pan
	public void panDown() {

		BigDecimal minus, empty;
		minus = new BigDecimal(0.1);  //--0.1 , To decrease the latitude number
		empty = new BigDecimal(0); //new center latitude number
		
		empty = latNum.subtract(minus); //latNum = default center latitude number
		latNum = empty;
	}
	
	//when click 'Right' button of the pan
	public void panRight() {

		BigDecimal plus = new BigDecimal(0.1);  //++0.1 , To increase the longitude number
		BigDecimal empty = new BigDecimal(0.0); //new center longitude number
		
		empty = lngNum.add(plus); //lngNum=default center longitude number
		lngNum = empty;
		  
	}
	
	//when click 'Left' button of the pan
	public void panLeft() {

		BigDecimal minus = new BigDecimal(0.1);  //--0.1, To decrease the longitude number
		BigDecimal empty = new BigDecimal(0.0); //new center latitude number
		
			empty = lngNum.subtract(minus); //lngNum=default center longitude number
			lngNum = empty;
		  
	}
	
	// locate all Markers
	public String changeLocation(ArrayList<SiteModel> strArrList) {
		ArrayList<SiteModel> Sites = getSites();
		String locat = new String(); // save the address and markers' location

		for(int i = 0; i < Sites.size(); i++) { // connect the address of markers
			locat += "&markers=size:tiny%7Ccolor:red%7C";
			locat += Sites.get(i).getLat()+ "," + Sites.get(i).getLng(); // output latitude , longitude			
		} 
		return locat;
	}

	//bring the Map image using google Static Map API
	public void downloadMap(String location) {

		ArrayList<SiteModel> Sites = getSites();
		String locat = changeLocation(Sites);

		try {
			String imageURL = "https://maps.googleapis.com/maps/api/staticmap?center="+ latNum +","+ lngNum //change the center location for zooming system 
		+ "&zoom="+zoomNum+"&size=250x250&scale=2&format=png&sensor=false&visible="+ location //if you want to use UTF-8, insert the code 'URLEncoder.encode(location, "UTF-8")'
		+ "&markers=size:tiny%7Ccolor:red%7C" //set the markers' size and color
		+ locat; //connect the all markers
					
			URL url = new URL(imageURL); //create URL
			InputStream is = url.openStream();//access the url address
			OutputStream os = new FileOutputStream(location);// be able to download
			byte[] b = new byte[2048];//allocate array 'byte' 
			int end;
			while ((end = is.read(b)) != -1) { //repeat to read the file until there's no file.
				os.write(b, 0, end);//download the image file like a map of address what the user wants
			}
			is.close();
			os.close();
		}
		catch(Exception exc){
			exc.printStackTrace(); // Print stack trace and return.
			System.out.print("Error!" + exc);
		}
	}
	
	//get Image of the Google Static Map
	public ImageIcon getMap(String location) { //bring the file which was download
		return new ImageIcon((new ImageIcon(location)).getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH)); //bring the map image file. scale smooth is a hint.
	}
	//delete Image File of the Google static Map
	public void MapFileDelete(String fileName) {
		File f = new File(fileName); //An abstract representation of file and directory pathnames.
		f.delete();
	}
	
	// updateMap using GoogleStaticMapAPI and return to the view
	public JLabel updateMap() {
		
		ArrayList<SiteModel> Sites = getSites();//return Sites contents
		String location = new String(); // location has a string "latitude , longitude"
		JLabel googleStaticMap = new JLabel(); // Declaration by JLabel variables
		
		for(int i = 0; i < Sites.size(); i++) {
			location = Sites.get(i).getLat()+ "," + Sites.get(i).getLng(); // latitude , longitude
		} 
		
		downloadMap(location);//Search for the actual address
		googleStaticMap = new JLabel(getMap(location)); //Reset to the map you download
		MapFileDelete(location);//Delete the corresponding image file from the program. 	
		
		return googleStaticMap;

	}
}

