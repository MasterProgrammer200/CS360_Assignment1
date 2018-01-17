

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
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import model.SiteModel;

/**
 * The purpose of the SiteView............
 * 
 * @author becknd01
 *
 */
public class SiteView extends JFrame{

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
	private JButton zoomInButton;                   //
	private JButton zoomOutButton;                  //
	private JPanel panPanel;						//
	private JPanel zoomPanel;						//
	private JPanel controlsPanel;					//
	
	// BuildMapPanel Fields
	private JPanel mapPanel;						//
	 
	// BuildToolBarPanel Fields
	private JPanel toolbarPanel;					//
	private JPanel historyPanel;					//
	private JPanel siteSelectorPanel;				//
	private JButton addButton;						//
	private JButton deleteButton;                   //
	private JButton editButton;                     //
	private JButton viewButton;                     //
	private JPanel buttonPanel;						//
	private JScrollPane siteSelectorScrollPane;		//
	private JScrollPane historyScrollPane;			//
	private JList<String> siteSelectorList;			//
	private JList<String> historyList;				//
	                                                
	// BuildMenuBarPanel Fields
	private JMenuBar menuBar;						//
	

	// BuildTitleBarPanel Fields
	private JPanel titleBarPanel;					//
	private JLabel titleBarJLabel;					//
	 
	 // General
	private JButtonListener jBL;					//								
	private ListSelectionListener jLL;				//
	private String[] history;						// Array for historyJList
	private String[] data; 							// Array for siteJList

	private Controller controller;					//



	// Constructor
	/**
	 * The constructor for the HomeView Class Creates and configures the Site Manager Application's GUI.
	 */
	public SiteView() {
		
		// Configure Window
		// setSize(WIDTH, HEIGHT);  // -----------------------><><><>< Not used, remove after debugging ><><><><><
		

		// Retrieve array for siteJList	            
		controller = new Controller();


		
		// Retrieve array for siteJList	            
		data = controller.siteArrayListToArray(controller.getSites()); //------------------>>>>>>>>>>>>>>method call here.
		history = controller.historyArrayListToArray(controller.getHistoryItems(100)); //------------------>>>>>>>>>>>>>>THIS IS TEMPORARY
		
		setTitle("SJRWI Site Manager Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		
		// Instantiate Event Listener
		jBL = new JButtonListener();
		
		// Build All JPanels
		BuildDisplayPanel();		
		BuildMapControlPanel(jBL);
		BuildMapPanel(); 		
		BuildToolBarPanel(jBL, jLL); 	
		BuildMenuBar(jBL);			
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
		
		// Create JPanels
		panPanel = new JPanel();
		zoomPanel = new JPanel();
		
		// Configure JPanel Layout Managers
		panPanel.setLayout(new BorderLayout());
		zoomPanel.setLayout(new GridLayout(2, 1));
		
		// Configure Borders
		panPanel.setBorder(BorderFactory.createTitledBorder("Pan"));
		zoomPanel.setBorder(BorderFactory.createTitledBorder("Zoom"));
		
		// Set Size
		zoomPanel.setPreferredSize(new Dimension(130, 100));
		
		// Create and Configure JButtons
		panUpButton = new JButton("Up");
		panDownButton = new JButton("Down");
		panLeftButton = new JButton("Left");
		panRightButton = new JButton("Right");
		zoomInButton = new JButton("+");
		zoomOutButton = new JButton("-");
		
		// Add ActionListener
		panUpButton.addActionListener(jBL);
		panDownButton.addActionListener(jBL);
		panLeftButton.addActionListener(jBL);
		panRightButton.addActionListener(jBL);
		zoomInButton.addActionListener(jBL);
		zoomOutButton.addActionListener(jBL);
			
		// Add Tool-tips to JButtons
		zoomInButton.setToolTipText("Zoom In");
		zoomOutButton.setToolTipText("Zoom Out");
		panUpButton.setToolTipText("Pan Up");
		panDownButton.setToolTipText("Pan Down");
		panLeftButton.setToolTipText("Pan Left");
		panRightButton.setToolTipText("Pan Right");
		
		// Add Pan Buttons to panPanel
		panPanel.add(panUpButton, BorderLayout.NORTH);
		panPanel.add(panDownButton, BorderLayout.SOUTH);
		panPanel.add(panLeftButton, BorderLayout.WEST);
		panPanel.add(panRightButton, BorderLayout.EAST);
		
		// Add Zoom Buttons to zoomButtonPanel
		zoomPanel.add(zoomInButton);
		zoomPanel.add(zoomOutButton);
		
		// Add zoomPanel and panPanel to mapControlPanel
		mapControlPanel.add(panPanel);
		mapControlPanel.add(zoomPanel);
		
	}//end BuildMapControlPanel
	
	

	/**
	 * BuildMapPanel configures the JPanel that provides the GUIs map. 
	 * 
	 * @param jBL
	 * 		The ActionListener for this JPanel.
	 */
	private void BuildMapPanel(){
		
		// Create and Configure mapPanel
		mapPanel = new JPanel();
		
		mapPanel.setBorder(BorderFactory.createTitledBorder("Site Distribution Map"));
		mapPanel.setPreferredSize(new Dimension(250,250));
		mapPanel.add(controller.updateMap());
		
	}//end BuildMapPanel
	
	
	
	/**
	 * BuildToolBarPanel configures the JPanel that provide the GUIs display selector and buttons. Bottom center panel.
	 * 
	 * @param jBL
	 * 		The ActionListener for this JPanel.
	 * @param jLL 
	 * 		The ListListener for this JPanel.
	 */
	private void BuildToolBarPanel(JButtonListener jBL, ListSelectionListener jLL){
		
		// Local Variable
		boolean enabled = false;
		
		// Create and Configure toolBarPanel
		toolbarPanel = new JPanel();
		toolbarPanel.setLayout(new GridLayout(1,3));
		toolbarPanel.setBorder(BorderFactory.createTitledBorder("Dashboard Toolbar"));
		toolbarPanel.setPreferredSize(new Dimension(250,150)); // w, h
		
		//Create and Configure HistoryPanel;
		historyPanel = new JPanel();
		historyPanel.setBorder(BorderFactory.createTitledBorder("Sample Collection Dates"));
		
		//Create and Configure siteSelectorPanel;
		siteSelectorPanel = new JPanel();
		siteSelectorPanel.setBorder(BorderFactory.createTitledBorder("Choose a Site to view"));
		
		//Create and Configure controlPanel
		controlsPanel = new JPanel();
		controlsPanel.setBorder(BorderFactory.createTitledBorder("Select an Operation"));
		buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createTitledBorder(""));
		buttonPanel.setPreferredSize(new Dimension(200, 100));
		buttonPanel.setLayout(new GridLayout(2, 2));
	
		// Create and Configure JButtons 
		addButton = new JButton("Add");						
		deleteButton = new JButton("Delete");                   
		editButton = new JButton("Edit");                     
		viewButton = new JButton("View"); 
		
		// set Enabled status of all JButtons except addButton
		deleteButton.setEnabled(enabled);
		editButton.setEnabled(enabled);
		viewButton.setEnabled(enabled);
		
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

		// Add JButton Sub-panels to buttonPanel
		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(editButton);
		buttonPanel.add(viewButton);
		
		// Add buttonPanel to controlsPanel
		controlsPanel.add(buttonPanel);
		
		// Create and Configure JList for siteSelectorPanel
		siteSelectorList = new JList<String>(data); 
		siteSelectorList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		siteSelectorList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		siteSelectorList.setVisibleRowCount(10);
		siteSelectorScrollPane = new JScrollPane(siteSelectorList);
		siteSelectorScrollPane.setPreferredSize(new Dimension(200,100));
		siteSelectorList.addListSelectionListener(jLL);
		siteSelectorList.setToolTipText("Coming Soon!");
		
		// Create and Configure JList for historyPanel
		historyList = new JList<String>(history);
		historyList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		historyList.setVisibleRowCount(10);
		historyScrollPane = new JScrollPane(historyList);
		historyScrollPane.setPreferredSize(new Dimension(200, 100));
		historyList.setEnabled(enabled);
		historyList.setToolTipText("Coming Soon!");
		
		// Add scrollPanes/JLists to their respective panels
		siteSelectorPanel.add(siteSelectorScrollPane);
		historyPanel.add(historyScrollPane);
		
		// Add Panels to toolbarPanel
		toolbarPanel.add(siteSelectorPanel);
		toolbarPanel.add(historyPanel);
		toolbarPanel.add(controlsPanel);
		
	}//end BuildToolBarPanel
	
	
	
	/**
	 * BuildMeunBarPanel creates and configures the GUIs menuBar. 
	 * 
	 * @param JBL
	 * 		The ActionListener for this menuBar.
	 */
	private void BuildMenuBar(JButtonListener JBL){
		
		menuBar = new JMenuBar();
		
		// Create and Configure JMenu and JMenuItems
		JMenu fileMenu = new JMenu("File");
		JMenuItem uploadItem = new JMenuItem("Upload");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		// Add Mnemonic to JMenu Components
		fileMenu.setMnemonic(KeyEvent.VK_F);
		uploadItem.setMnemonic(KeyEvent.VK_U);
		exitItem.setMnemonic(KeyEvent.VK_E);
		
		// Add Tool-tips
		uploadItem.setToolTipText("Import From file");
		exitItem.setToolTipText("Exit Program");
		
		// Add ActionListener to JMenuItems
		uploadItem.addActionListener(jBL);
		exitItem.addActionListener(jBL);
		
		// Add JMenuItems to JMenu
		fileMenu.add(uploadItem);
		fileMenu.add(exitItem);
		
		// Add JMenu to JMenuBar
		menuBar.add(fileMenu);
		
		
		// Set to JFrame
		setJMenuBar(menuBar);
		
		
		
		
		
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
	 * The JButtonListener
	 * 
	 * @author becknd01
	 */
	private class JButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
	        System.out.println("Selected: " + command);
	        
	        if (command.equals("Add")) {
	        	
	        	// 1. Clear and enable textboxes
	        	siteIDNumberTextField.setEditable(true);
	        	siteNameTextField.setEditable(true);
	        	siteLocationTextField.setEditable(true);
	        	siteLongitudeTextField.setEditable(true);
	        	siteLattitudeTextField.setEditable(true);
	        	siteDescriptionTextArea.setEditable(true);
	        	
	        	siteIDNumberTextField.setText("");
	        	siteNameTextField.setText("");
	        	siteLocationTextField.setText("");
	        	siteLongitudeTextField.setText("");
	        	siteLattitudeTextField.setText("");
	        	siteDescriptionTextArea.setText("");
	        	
	        	// 2. button name is switched to delete
	        	addButton.setText("Save");

	        } else if (command.equals("Save")) {

	        	// 1. VALIDATE TEXTBOXES - TODO <---- Nick im leaving this for you
	        	
	        	// 2. create the new site
	        	SiteModel site = new SiteModel();
	        	site.setNum(Integer.parseInt(siteIDNumberTextField.getText()));
	        	site.setName(siteNameTextField.getText());
	        	site.setLoc(siteLocationTextField.getText());
	        	site.setLng(new BigDecimal(siteLongitudeTextField.getText()));
	        	site.setLat(new BigDecimal(siteLattitudeTextField.getText()));
	        	site.setShortDesc(siteDescriptionTextArea.getText());
	        	site.setDateCreated(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
	        	
	        	controller.createSite(site);
	        	
	        	// 3. Button becomes add again
	        	addButton.setText("Add");
	        	
	        	// 4. Refresh list box/clear text fields/disable text fields

	        	data = controller.siteArrayListToArray(controller.getSites()); 
	        	siteSelectorList.setListData(data);
	        	historyList.removeAll();
	        	
	        	siteIDNumberTextField.setText("");
	        	siteNameTextField.setText("");
	        	siteLocationTextField.setText("");
	        	siteLongitudeTextField.setText("");
	        	siteLattitudeTextField.setText("");
	        	siteDescriptionTextArea.setText("");
	        	
	        	siteIDNumberTextField.setEditable(false);
	        	siteNameTextField.setEditable(false);
	        	siteLocationTextField.setEditable(false);
	        	siteLongitudeTextField.setEditable(false);
	        	siteLattitudeTextField.setEditable(false);
	        	siteDescriptionTextArea.setEditable(false);
	        	
	        }
	        
		}//end actionPerformed
		
	}//end ActionListener 
	
	
	/**
	 * The JListListener is the Event handler for the site selector JList.
	 * 
	 * @author becknd01
	 *
	 */
	private class JListListener implements ListSelectionListener {
		
		@Override
	    public void valueChanged(ListSelectionEvent e) {
	        
		}//end ListSelectionModel
		
	}//end JListListener
	
	
	
	/**
	 * The purpose of this method is to instantiate the SiteViewTest class.
	 * 
	 * @param args
	 * 		Not Used.
	 */
	public static void main(String[] args) {
		
		new SiteView();
	}//end main
}//end class SiteView
