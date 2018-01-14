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

	// private final String PARAMETER_FIRST = "@" + COLUMN_FIRST;
	// private final String PARAMETER_LAST = "@" + COLUMN_LAST;
	// private final String PARAMETER_PAY_RATE = "@" + COLUMN_PAY_RATE;
	private final String PARAMETER_ID = "@" + COLUMN_ID;
	// private final String connectionString = "Data Source = |DataDirectory|\\" +
	// DB_NAME + "; Version = 3";

	// where the magic happens
	public static void main(String[] args) {
		Controller c = new Controller(null, null);
		ArrayList<SiteModel> s = c.getSites();
		System.out.println(s.toString());
	}

	// call model AND view
	// constructor
	public Controller(SiteModel site, SiteView view) {
		db = new DbConnect();

		this.site = site;
		this.view = view;
	}

	/* CRUD operation for Site */
	// CREATE. add a site. view calls createSite from the controller
	public void createSite(SiteModel s) {

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

			// display any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		} finally {

			// try to close the connection to the database
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ignore) {

				}
			}
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

			// display any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		} finally {

			// try to close the connection to the database
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ignore) {

				}
			}
		}

		// return the site retrieved from the database
		return result;
	}

	// UPDATE. edit and change a site.
	public void updateSite(SiteModel s, int SiteNum) {
		// view.printDetails(model.getSites(), model.getSite(SiteNum));
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

	// updateMap using GoogleStaticAPI and return to the view
	public void updateMap() {

	}

	/*
	 * //without DB public static void main(String[] args) {
	 * 
	 * // Site model = SiteInfoFromDB(); // History model = HistoryInfoFromDB(); //
	 * Site view = new Site();
	 * 
	 * updateSite(Sites, int SiteNum); updateMap();
	 * 
	 * 
	 * }
	 * 
	 * private static Site SiteInfoFromDB() {
	 * 
	 * int siteNum; Site site = new Site(); site site.getSite(siteNum); return site;
	 * 
	 * }
	 */
}
