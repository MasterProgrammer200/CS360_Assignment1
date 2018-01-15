/*
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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * The purpose of the SiteView............
 * 
 * @author becknd01
 *
 */
public class SiteView extends JFrame{

	// Constants
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	
	// BuildDisplayPanel Fields                     
	private JPanel displayPanel;	                //
	private JPanel displayPanelTop;                 //
	private JPanel displayPanelBottom;				//
	private JPanel JLabelPanel;						//
	private JPanel JTextFieldPanel;					//
	private JLabel siteIDNumberLabel;				//
	private JLabel siteLongitudeLabel;				//
	private JLabel siteLattitudeLabel;				//
	private JLabel siteLocationLabel;				//
	private JLabel siteNameLabel;					//
	private JLabel siteDescriptionLabel;			//
	private JTextField siteIDNumberTextField;		//
	private JTextField siteLongitudeTextField;		//
	private JTextField siteLattitudeTextField;		//
	private JTextField siteLocationTextField;		//
	private JTextField siteNameTextField;			//
	private JTextArea siteDescriptionTextArea;		//
	 
	// BuildMapControlPanel Fields
	private JPanel mapControlPanel;					//
	private JButton panDownButton;					//
	private JButton panUpButton;                    //
	private JButton panLeftButton;                  //
	private JButton panRightButton;                 //
	private JButton ZoomInButton;                   //
	private JButton ZoomOutButton;                  //
	
	// BuildMapPanel Fields
	private JPanel mapPanel;						//
	 
	// BuildToolBarPanel Fields
	private JPanel toolBarPanel;					//
	private JButton addButton;						//
	private JButton deleteButton;                   //
	private JButton editButton;                     //
	private JButton viewButton;                     //
	                                                
	// BuildMenuBarPanel Fields
	private JMenuBar menuBar;						//
	
	// BuildTitleBarPanel Fields
	private JPanel titleBarPanel;					//
	private JLabel titleLabel;						//
	 
	 // General
	private JButtonListener JBL;					//
	

		
	
	
	// Constructor
	/**
	 * The constructor for the HomeView Class Creates and configures the Site Manager Application's GUI.
	 */
	public SiteView() {
		// Configure Window
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// Instantiate Event Listener
		JBL = new JButtonListener();
		
		// Build All JPanels
		BuildDisplayPanel();		
		BuildMapControlPanel(JBL);
		BuildMapPanel(JBL); 		
		BuildToolBarPanel(JBL); 	
		BuildMenuBar(JBL);			
		BuildTitleBarPanel();		
		
		// Add All JPanels
		add(displayPanel, BorderLayout.WEST);
		add(mapControlPanel, BorderLayout.EAST);
		add(mapPanel, BorderLayout.CENTER);
		add(toolBarPanel, BorderLayout.SOUTH);
		add(titleBarPanel, BorderLayout.NORTH);
		
		// Display Window
		setVisible(true);
		
	}//end HomeView Constructor					
	


	



	// 
	/**
	 * BuildDisplayPanel configures the JPanel that presents site data. Upper right panel.
	 * 
	 * @param JBL
	 * 		The ActionListener for this JPanel.
	 */
	private void BuildDisplayPanel(){
		
		// Create JPanel.
		displayPanel = new JPanel(); 
		displayPanelTop = new JPanel();
		displayPanelBottom = new JPanel();
		JLabelPanel = new JPanel();
		JTextFieldPanel = new JPanel();
		
		// Configure Layout Managers
		displayPanel.setLayout(new GridLayout(2, 1));
		displayPanelTop.setLayout(new GridLayout(1, 2));
		displayPanelBottom.setLayout(new GridLayout(1, 1));
		JLabelPanel.setLayout(new FlowLayout());
		JTextFieldPanel.setLayout(new FlowLayout());
		
		// Create JLabels.
		siteIDNumberLabel = new JLabel("Site ID#: ");			
		siteLongitudeLabel = new JLabel("Longitude: ");			
		siteLattitudeLabel = new JLabel("Lattitude: ");			
		siteLocationLabel = new JLabel("Location: ");			
		siteNameLabel = new JLabel("Site Name: ");				
		siteDescriptionLabel = new JLabel("Site Description: ");	
		
		// Create JTextFields.
		siteIDNumberTextField = new JTextField();		
		siteLongitudeTextField = new JTextField();		
		siteLattitudeTextField = new JTextField();		
		siteLocationTextField	= new JTextField();			
		siteNameTextField = new JTextField();			
		
		// Create and Configure JTextArea
		siteDescriptionTextArea = new JTextArea();	
		
		// Add Components to JLabelPanel.
		JLabelPanel.add(siteIDNumberLabel);
		JLabelPanel.add(siteNameLabel);
		JLabelPanel.add(siteLocationLabel);
		JLabelPanel.add(siteLongitudeLabel);
		JLabelPanel.add(siteLattitudeLabel);
		JLabelPanel.add(siteDescriptionLabel);
		
		// Add Components to JLabelPanel
		JTextFieldPanel.add(siteIDNumberTextField);
		JTextFieldPanel.add(siteNameTextField);
		JTextFieldPanel.add(siteLocationTextField);
		JTextFieldPanel.add(siteLongitudeTextField);
		JTextFieldPanel.add(siteLattitudeTextField);
		
		// Add Panels to displayPanelTop
		displayPanelTop.add(JLabelPanel);
		displayPanelTop.add(JTextFieldPanel);
		
		// Add Component to displayBottomPanel;
		displayPanelBottom.add(siteDescriptionTextArea);
		
	}//end BuildDisplayPanel						
	 
	
	
	private void BuildMapControlPanel(JButtonListener JBL) {
		
		
	}//end BuildMapControlPanel
	
	

	/**
	 * BuildMapPanel configures the JPanel that provides the GUIs map. Upper left panel.
	 * 
	 * @param JBL
	 * 		The ActionListener for this JPanel.
	 */
	private void BuildMapPanel(JButtonListener JBL){
		
	}//end BuildMapPanel
	
	
	
	/**
	 * BuildToolBarPanel configures the JPanel that provide the GUIs display selector and buttons. Bottom center panel.
	 * 
	 * @param JBL
	 * 		The ActionListener for this JPanel.
	 */
	private void BuildToolBarPanel(JButtonListener JBL){
		
	}//end BuildToolBarPanel
	
	
	
	/**
	 * BuildMeunBarPanel creates and configures the GUIs menuBar. 
	 * 
	 * @param JBL
	 * 		The ActionListener for this menuBar.
	 */
	private void BuildMenuBar(JButtonListener JBL){
		
	}//end BuildMenuBarPanel
	
	
	
	/**
	 * BuildTitleBarPanel configures the JPanel that provide the GUIs TitleBar. Top center panel. 
	 */
	private void BuildTitleBarPanel(){
		
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
