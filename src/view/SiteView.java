/** 
 * 
 * Name: 		Nicholas Becker
 * Class:		CS360-01 Software Engineering
 * Instructor: 	eProf Sedlmeyer
 * Project:		Assignment 1: Welcome to the Real World
 * Date:		01/13/18
 * Due:			01/17/18
 * 
 **/
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * The purpose of the HomeView
 * 
 * @author becknd01
 *
 */
public class SiteView {

	// Constants
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	
	// BuildDisplayPanel Fields
	private JPanel displayPanel;					//
	private JLabel siteIDNumberLabel;				//
	private JLabel siteLongitudeLabel;				//
	private JLabel siteLattitudeLabel;				//
	private JLabel siteLocationLabel;				//
	private JLabel siteNameLabel;					//
	private JLabel siteDescriptionLabel;			//
	private JTextField siteIDNumberTextBox;			//
	private JTextField siteLongitudeTextBox;		//
	private JTextField siteLattitudeTextBox;		//
	private JTextField siteLocationTextBox;			//
	private JTextField siteNameTextBox;				//
	private JTextPane siteDescriptionTextField;		//
	 
	// BuildMapPanel Fields
	private JPanel MapPanel;						//
	 
	 
	
	// BuildToolBarPanel Fields
	private JPanel ToolBarPanel;					//
	private JButton addButton;						//
	private JButton deleteButton;                   //
	private JButton editButton;                     //
	private JButton viewButton;                     //
	                                                
	 
	// BuildMenuBarPanel Fields
	private JMenuBar menuBar;						//
	
	 
	// BuildTitleBarPanel Fields
	private JPanel Panel;							//
	private JLabel titleLabel;						//
	 
	 // General
	private JButtonListener JBL;					//
		
	
	
	
	
	
	// Constructor
	/**
	 * The constructor for the HomeView Class.
	 */
	public SiteView(){
		
	}//end HomeView Constructor					
	
	
	
	// 
	/**
	 * BuildDisplayPanel configures the JPanel that presents site data. Upper right panel.
	 * 
	 * @param JBL
	 * 		The ActionListener for this Panel
	 */
	private void BuildDisplayPanel(JButtonListener JBL){
		
	}//end BuildDisplayPanel						
	 
	
	
	/**
	 * BuildMapPanel configures the JPanel that provides the GUIs map. Upper left panel.
	 * 
	 * @param JBL
	 * 		The ActionListener for this Panel
	 */
	private void BuildMapPanel(JButtonListener JBL){
		
	}//end BuildMapPanel
	
	
	/**
	 * BuildToolBarPanel configures the JPanel that provide the GUIs display selector and buttons. Bottom center panel.
	 * 
	 * @param JBL
	 * 		The ActionListener for this Panel
	 */
	private void BuildToolBarPanel(JButtonListener JBL){
		
	}//end BuildToolBarPanel
	
	
	
	/**
	 * BuildMeunBarPanel creates and configures the GUIs menuBar. 
	 * 
	 * @param JBL
	 * 		The ActionListener for this menuBar
	 */
	private void BuildMenuBarPanel(JButtonListener JBL){
		
	}//end BuildMenuBarPanel
	
	
	
	/**
	 * BuildTitleBarPanel configures the JPanel that provide the GUIs TitleBar. Top center panel. 
	 * 
	 * @param JBL
	 * 		The ActionListener for this menuBar
	 */
	private void BuildTitleBarPanel(JButtonListener JBL){
		
	}//end BuildTitleBarPanel
	
	
	
	/**
	 * The JComponentListener
	 * 
	 * @author becknd01
	 */
	private class JButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			
			
			
			
			
			
			
			
		}//end actionPerformed
		
	}//end ActionListener 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
