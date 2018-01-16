

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
package STRWI_SiteView_Test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 * The purpose of the SiteView............
 * 
 * @author becknd01
 *
 */
public class SiteViewTest extends JFrame{

	// Constants
	//private final int WIDTH = 500;  // not used, remove after debugging.
	//private final int HEIGHT = 500; // not used, remove after debugging.
	
	
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
	private JScrollPane siteDescriptionScrollPane;	//
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
	private JPanel toolbarPanel;					//
	private JButton addButton;						//
	private JButton deleteButton;                   //
	private JButton editButton;                     //
	private JButton viewButton;                     //
	private JPanel buttonJPanel;					//
	private JPanel buttonJPanel0;                   //
	private JPanel buttonJPanel1;                   //
	private JPanel buttonJPanel2;                   //
	private JPanel buttonJPanel3;                   //
	private JScrollPane siteSelectorScrollPane;		//
	private JList siteSelectorList;				//
	                                                
	// BuildMenuBarPanel Fields
	private JMenuBar menuBar;						//

	
	// BuildTitleBarPanel Fields
	private JPanel titleBarPanel;					//
	private JLabel titleBarJLabel;					//
	 
	 // General
	private JButtonListener JBL;					//
	String[] data; 									// array for siteJList
    
	
	// Constructor
	/**
	 * The constructor for the HomeView Class Creates and configures the Site Manager Application's GUI.
	 */
	public SiteViewTest() {
		
		// Configure Window
		// setSize(WIDTH, HEIGHT);  				// not used, remove after debugging.
		
		// Retrieve array for siteJList	            
		//data = .getArray(); ........................................<<<<< To Do
		
		data = new String[5];
		
		setTitle("SJRWI Site Manager Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		
		// Instantiate Event Listener
		JBL = new JButtonListener();
		
		// Build All JPanels
		BuildDisplayPanel();		
		BuildMapControlPanel(JBL);
		BuildMapPanel(); 		
		BuildToolBarPanel(JBL); 	
		BuildMenuBar(JBL);			
		BuildTitleBarPanel();		
		
		// Add All JPanels
		add(displayPanel, BorderLayout.WEST);
		add(mapControlPanel, BorderLayout.EAST);
		add(mapPanel, BorderLayout.CENTER);
		add(toolbarPanel, BorderLayout.SOUTH);
		add(titleBarPanel, BorderLayout.NORTH);
		
		// Display Window
		pack();
		setVisible(true);
		
	}//end HomeView Constructor					
	

	 
	/**
	 * BuildDisplayPanel configures the JPanel titled" site information". 
	 */
	private void BuildDisplayPanel(){
		
		// Local Variables
		int tFSize = 15; 
		boolean editable = false;
		
		// Create and Configure JPanels.
		displayPanel = new JPanel(); 
		displayPanel.setBorder(BorderFactory.createTitledBorder("Site Information"));
		displayPanelTop = new JPanel();
		displayPanelBottom = new JPanel();
		JLabelPanel = new JPanel();
		JTextFieldPanel = new JPanel();
		
		// Configure Layout Managers
		displayPanel.setLayout(new GridLayout(2, 1));
		displayPanel.setPreferredSize(new Dimension(250, 250)); //(w, h)
		displayPanelTop.setLayout(new GridLayout(1, 2));
		displayPanelBottom.setLayout(new GridLayout(1, 1));
		JLabelPanel.setLayout(new GridLayout(6, 1));
		JTextFieldPanel.setLayout(new GridLayout(6, 1));
		
		
		// Create JLabels.
		siteIDNumberLabel = new JLabel("Site ID#: ");			
		siteLongitudeLabel = new JLabel("Longitude: ");			
		siteLattitudeLabel = new JLabel("Lattitude: ");			
		siteLocationLabel = new JLabel("Location: ");			
		siteNameLabel = new JLabel("Site Name: ");				
		siteDescriptionLabel = new JLabel("Site Description: ");	
		
		// Set Tool Tips for JLabels
		siteIDNumberLabel.setToolTipText("Coming Soon!");
		siteLongitudeLabel.setToolTipText("Coming Soon!");
		siteLattitudeLabel.setToolTipText("Coming Soon!");
		siteLocationLabel.setToolTipText("Coming Soon!");
		siteNameLabel.setToolTipText("Coming Soon!");
		siteDescriptionLabel.setToolTipText("Coming Soon!");
		
		// Create and Configure JTextFields.
		siteIDNumberTextField = new JTextField(tFSize);		
		siteIDNumberTextField.setEditable(editable);
		siteLongitudeTextField = new JTextField(tFSize);	
		siteLongitudeTextField.setEditable(editable);
		siteLattitudeTextField = new JTextField(tFSize);
		siteLattitudeTextField.setEditable(editable);
		siteLocationTextField	= new JTextField(tFSize);		
		siteLocationTextField.setEditable(editable);
		siteNameTextField = new JTextField(tFSize);		
		siteNameTextField.setEditable(editable);
		
		// Create and Configure JTextArea and JScrollPane
		siteDescriptionTextArea = new JTextArea();	
		siteDescriptionScrollPane = new JScrollPane(siteDescriptionTextArea);
		siteDescriptionTextArea.setLineWrap(true);
		siteDescriptionTextArea.setWrapStyleWord(true);
		siteDescriptionTextArea.setEditable(editable);
		siteDescriptionTextArea.setEditable(editable);
		
		// Add Components to JLabelPanel.
		JLabelPanel.add(siteIDNumberLabel);
		JLabelPanel.add(siteNameLabel);
		JLabelPanel.add(siteLocationLabel);
		JLabelPanel.add(siteLongitudeLabel);
		JLabelPanel.add(siteLattitudeLabel);
		JLabelPanel.add(siteDescriptionLabel);
		
		// Add Components to JTextFieldPanel
		JTextFieldPanel.add(siteIDNumberTextField);
		JTextFieldPanel.add(siteNameTextField);
		JTextFieldPanel.add(siteLocationTextField);
		JTextFieldPanel.add(siteLongitudeTextField);
		JTextFieldPanel.add(siteLattitudeTextField);
		
		// Add Panels to displayPanelTop
		displayPanelTop.add(JLabelPanel);
		displayPanelTop.add(JTextFieldPanel);
		
		// Add Component to displayBottomPanel;
		displayPanelBottom.add(siteDescriptionScrollPane);
		
		// Add Top and Bottom Panel to displayPanel
		displayPanel.add(displayPanelTop);
		displayPanel.add(displayPanelBottom);
		
	}//end BuildDisplayPanel						
	 
	
	
	private void BuildMapControlPanel(JButtonListener JBL) {
		
		// Create and Configure mapControlPanel
		mapControlPanel = new JPanel();
		mapControlPanel.setBorder(BorderFactory.createTitledBorder("Map Controls"));
		mapControlPanel.setPreferredSize(new Dimension(150,250));
		mapControlPanel.add(new JLabel("Coming Soon!"));

	}//end BuildMapControlPanel
	
	

	/**
	 * BuildMapPanel configures the JPanel that provides the GUIs map. 
	 * 
	 * @param JBL
	 * 		The ActionListener for this JPanel.
	 */
	private void BuildMapPanel(){
		
		// Create and Configure mapPanel
		mapPanel = new JPanel();
		mapPanel.setBorder(BorderFactory.createTitledBorder("Site Distribution Map"));
		mapPanel.setPreferredSize(new Dimension(250,250));
		mapPanel.add(new JLabel("Coming Soon!"));
		
	}//end BuildMapPanel
	
	
	
	/**
	 * BuildToolBarPanel configures the JPanel that provide the GUIs display selector and buttons. Bottom center panel.
	 * 
	 * @param jBL
	 * 		The ActionListener for this JPanel.
	 */
	private void BuildToolBarPanel(JButtonListener jBL){
		
		// Local Variable
		boolean editable = false;
		
		// Create and Configure toolBarPanel
		toolbarPanel = new JPanel();
		toolbarPanel.setLayout(new GridLayout(1,2));
		toolbarPanel.setBorder(BorderFactory.createTitledBorder("Dashboard Toolbar"));
		toolbarPanel.setPreferredSize(new Dimension(250,100)); // w, h
		
		// Create and Configure Button JPanels
		buttonJPanel = new JPanel();
		buttonJPanel0 = new JPanel();
		buttonJPanel1 = new JPanel();
		buttonJPanel2 = new JPanel();
		buttonJPanel3 = new JPanel();
		
		// Create JButtons
		JButton addButton = new JButton("Add");						
		JButton deleteButton = new JButton("Delete");                   
		JButton editButton = new JButton("Edit");                     
		JButton viewButton = new JButton("View"); 
		
		// Add ActionListener to JButtons
		addButton.addActionListener(jBL);
		deleteButton.addActionListener(jBL);
		editButton.addActionListener(jBL);
		viewButton.addActionListener(jBL); 
		
		// Create JButton Tool-tips
		addButton.setToolTipText("Coming Soon!");
		deleteButton.setToolTipText("Coming Soon!");
		editButton.setToolTipText("Coming Soon!");
		viewButton.setToolTipText("Coming Soon!");
		
		// Add JButtons to their Respective Sub-panels
		buttonJPanel0.add(addButton);
		buttonJPanel1.add(deleteButton);
		buttonJPanel2.add(editButton);
		buttonJPanel3.add(viewButton);
		
		// Add JButton Sub-panels to buttonJPanel
		buttonJPanel.add(buttonJPanel0);
		buttonJPanel.add(buttonJPanel1);
		buttonJPanel.add(buttonJPanel2);
		buttonJPanel.add(buttonJPanel3);
		
		// Create and Configure JList and JScrollPane
		siteSelectorList = new JList(data); 
		siteSelectorList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		siteSelectorList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		siteSelectorList.setVisibleRowCount(-1);
		JScrollPane siteSelectorScrollPane = new JScrollPane(siteSelectorList);
		siteSelectorScrollPane.setPreferredSize(new Dimension(80, 80));
		
		
		
		


		
		// Add actionListener
		
		
		// Add Panels to toolbarPanel
		toolbarPanel.add(siteSelectorScrollPane);
		toolbarPanel.add(buttonJPanel);
		
		
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
		
		titleBarPanel = new JPanel();
		titleBarPanel.setBorder(BorderFactory.createTitledBorder(""));
		titleBarPanel.setPreferredSize(new Dimension(250, 50));
		titleBarJLabel = new JLabel("St. Joseph River Watershed Initiative");
		titleBarJLabel.setFont(new Font("Serif",Font.BOLD, 20));
		titleBarJLabel.setForeground(Color.BLUE);
		titleBarPanel.add(titleBarJLabel);
		
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
	
	
	
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) {

	        if (siteSelectorList.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	           // fireButton.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	           // fireButton.setEnabled(true);
	        }
	    }
	}//end valueChanged
	
	/**
	 * The purpose of this method is to instantiate the SiteViewTest class.
	 * 
	 * @param args
	 * 		Not Used.
	 */
	public static void main(String[] args) {
		
		new SiteViewTest();
	}
}
