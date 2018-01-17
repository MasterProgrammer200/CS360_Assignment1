

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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
	
	
	/**
	 * not Used
	 */
	private static final long serialVersionUID = 1L;
	
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
	private JLabel mapJLabel;
	
	
	 // General
	private JButtonListener jBL;					//								
	private ListSelectionListener jLL;				//
	private String[] history;						// Array for historyJList
	private String[] data; 							// Array for siteJList
	private Controller controller;					//
	private DecimalFormat df;


	// Constructor
	/**
	 * The constructor for the HomeView Class Creates and configures the Site Manager Application's GUI.
	 */
	public SiteView() {
		
		 df = new DecimalFormat("");
		
		// Retrieve array for siteJList	            
		controller = new Controller();
		
		// Retrieve array for siteJList	            
		data = controller.siteArrayListToArray(controller.getSites()); 
		history = controller.historyArrayListToArray(controller.getHistoryItems(100)); //------------------>>>>>>>>>>>>>>THIS IS TEMPORARY
		
		// Configure Window
		
		setTitle("SJRWI Site Manager Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		
		// Instantiate Event Listener
		jBL = new JButtonListener();
		jLL = new JListListener();
		
		// Build All JPanels
		buildDisplayPanel();		
		buildMapControlPanel(jBL);
		buildMapPanel(); 		
		buildToolBarPanel(jBL, jLL); 	
		buildMenuBar(jBL);			
		buildTitleBarPanel();		
		
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
	 * BuildDisplayPanel creates and configures the JPanel titled "site information". 
	 */
	private void buildDisplayPanel(){
		
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
	 
	
	
	/**
	 * The buildMapControlPanel method creates and configures the JPanel titled "Map Controls".
	 * 
	 * @param JBL
	 */
	private void buildMapControlPanel(JButtonListener JBL) {
		
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
	private void buildMapPanel(){
		
		// Create and Configure mapPanel
		mapPanel = new JPanel();
		
		mapPanel.setBorder(BorderFactory.createTitledBorder("Site Distribution Map"));
		mapPanel.setPreferredSize(new Dimension(250,250));
		
		mapJLabel = controller.updateMap();
		mapPanel.add(mapJLabel);
		
	}//end BuildMapPanel
	
	
	
	/**
	 * BuildToolBarPanel configures the JPanel that provide the GUIs display selector and buttons. Bottom center panel.
	 * @param jLL 
	 * @param jBL 
	 */
	private void buildToolBarPanel(JButtonListener jBL, ListSelectionListener jLL){
		
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
	
	
	
//	/**
//	 * Refreshes the siteSelectorPanel to update the component for any changes made by the user.
//	 */
//	public void refreshSiteSelectorPanel() {
//		
//		siteSelectorList = new JList<String>(data); 
//		siteSelectorList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//		siteSelectorList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//		siteSelectorList.setVisibleRowCount(10);
//		siteSelectorScrollPane = new JScrollPane(siteSelectorList);
//		siteSelectorScrollPane.setPreferredSize(new Dimension(200,100));
//		siteSelectorList.addListSelectionListener(jLL);
//		siteSelectorList.setToolTipText("Coming Soon!");
//		
//		siteSelectorPanel.add(siteSelectorScrollPane);
//		
//		
//	}
	
	
	
	/**
	 * BuildMeunBarPanel creates and configures the GUIs menuBar. 
	 * 
	 * @param JBL
	 * 		The ActionListener for this menuBar.
	 */
	private void buildMenuBar(JButtonListener JBL){
		
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
	private void buildTitleBarPanel(){
		
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

	        	// 1. VALIDATE TEXTBOXES
	        	
	        	if (!validateInput()) {
	        		return;
	        	}
	        	
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
	        	
	        	// 3. Buttons become add and exit again respectively
	        	siteSelectorList.setEnabled(true);
	        	addButton.setText("Add");
	        	editButton.setText("Edit");
	        	
	        	// 4. Refresh list box/refresh map/clear text fields/disable text fields

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
	        	
	        	refreshMap();
	        	
	        }
			else if (command.equals("Exit")) {
				System.exit(0);
			}
			else if (e.getActionCommand().equals("Delete")) {
				controller.deleteSite(Integer.parseInt(data[siteSelectorList.getSelectedIndex()]));
				controller.deleteHistoryItem(Integer.parseInt(data[siteSelectorList.getSelectedIndex()]));
				refreshMap();
				
				//controller.siteArrayListToArray(controller.getSites());
				data = controller.siteArrayListToArray(controller.getSites());
				siteSelectorList.setListData(data);
			}
			else if (command.equals("View")) {
				
				// Get Selected SiteModel object
				SiteModel selectedSite;
				int siteSelected = Integer.parseInt(data[siteSelectorList.getSelectedIndex()]);
				selectedSite = controller.getSite(Integer.parseInt(data[siteSelectorList.getSelectedIndex()]));
				
				
				// Update Site Information Panel
				siteIDNumberTextField.setText(String.valueOf(selectedSite.getNum()));
	        	siteNameTextField.setText(selectedSite.getName());
	        	siteLocationTextField.setText(selectedSite.getLoc());
	        	siteLongitudeTextField.setText(df.format(selectedSite.getLng()));
	        	siteLattitudeTextField.setText(df.format(selectedSite.getLat()));
	        	siteDescriptionTextArea.setText(selectedSite.getShortDesc());
	        	
	        	// Update History List

	        	history = controller.historyArrayListToArray(controller.getHistoryItems(siteSelected));
				historyList.setListData(history);
				
			}
			else if (command.equals("Edit")) {
				
				addButton.setEnabled(false);
				deleteButton.setEnabled(false);
				editButton.setText("Save");
				viewButton.setEnabled(false);
				siteSelectorList.setEnabled(false);
				
				SiteModel selectedSite;
				int siteSelected = Integer.parseInt(data[siteSelectorList.getSelectedIndex()]);
				selectedSite = controller.getSite(Integer.parseInt(data[siteSelectorList.getSelectedIndex()]));
				
				
				// Update Site Information Panel
				siteIDNumberTextField.setText(String.valueOf(selectedSite.getNum()));
	        	siteNameTextField.setText(selectedSite.getName());
	        	siteLocationTextField.setText(selectedSite.getLoc());
	        	siteLongitudeTextField.setText(df.format(selectedSite.getLng()));
	        	siteLattitudeTextField.setText(df.format(selectedSite.getLat()));
	        	siteDescriptionTextArea.setText(selectedSite.getShortDesc());
	        	
	        	// Update History List
	        	history = null;
	        	history = controller.historyArrayListToArray(controller.getHistoryItems(siteSelected));
				historyList.setListData(history);
				
				siteIDNumberTextField.setEditable(false);
	        	siteNameTextField.setEditable(true);
	        	siteLocationTextField.setEditable(true);
	        	siteLongitudeTextField.setEditable(true);
	        	siteLattitudeTextField.setEditable(true);
	        	siteDescriptionTextArea.setEditable(true);
			}
			else if (command.equals("Up")) {
				controller.panUp();
				refreshMap();
			}
			else if (command.equals("Left")) {
				controller.panLeft();
				refreshMap();
			}
			else if (command.equals("Right")) {
				controller.panRight();
				refreshMap();
			}
			else if (command.equals("Down")) {
				controller.panDown();
				refreshMap();
			}
			else if (command.equals("+")) {
				controller.ZoomIn();
				refreshMap();
			}
			else if (command.equals("-")) {
				controller.ZoomOut();
				refreshMap();
			}
			else if (command.equals("Upload")) {
				
				refreshMap();
			}
			else {
				System.out.println("Nope");
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
			
			// enable Buttons when Selected
			
				addButton.setEnabled(true);
				deleteButton.setEnabled(true);
				editButton.setEnabled(true);
				viewButton.setEnabled(true);
				
		}//end ListSelectionModel
		
	}//end JListListener
	
	
	
		/**
		 *  Helper method that removes out of date map and adds new map.
		 */
	private void refreshMap() {
		Container parent = mapJLabel.getParent();
		parent.remove(mapJLabel);
		mapJLabel = controller.updateMap();
		parent.add(mapJLabel );
		parent.validate();
		parent.repaint();
	}
	
	
	
	/**
	 * The purpose of this method is to instantiate the SiteViewTest class.
	 * 
	 * @param args
	 * 		Not Used
	 */
	public static void main(String[] args) {
		
		
		// super ultra uncrackable security  
		String username = JOptionPane.showInputDialog(null, "Please enter your Username");
		String password = JOptionPane.showInputDialog(null, "Please enter your Password");
		
		if (username.equals("1") && password.equals("1")) {
			new SiteView();
		}
		else {
			System.exit(0);
		}
		
	}//end main


	private boolean validateInput() {
		
		boolean isValid = true;
		String errMsg = "";
		
		try{
			int num = Integer.parseInt(siteIDNumberTextField.getText());
		} catch (NumberFormatException e) {
			errMsg += "Site Number must be an integer\n";
		}
		
		if (siteNameTextField.getText().isEmpty()) {
			errMsg += "Site Name must contain a value\n";
		}
		
		if (siteLocationTextField.getText().isEmpty()) {
			errMsg += "Site Location must contain a value\n";
		}
		
		try{
			BigDecimal num = new BigDecimal(siteLongitudeTextField.getText());
		} catch (NumberFormatException e) {
			errMsg += "Site Longitude must be a decimal\n";
		}
		
		try{
			BigDecimal num = new BigDecimal(siteLongitudeTextField.getText());
		} catch (NumberFormatException e) {
			errMsg += "Site Lattitude must be a decimal\n";
		}
		
		if (errMsg != "") {
			isValid = false;
			JOptionPane optionPane = new JOptionPane(errMsg, JOptionPane.WARNING_MESSAGE);
			JDialog dialog = optionPane.createDialog("An Error hath occured.");
			dialog.setAlwaysOnTop(true); // to show top of all other application
			dialog.setVisible(true); // to visible the dialog
		}
		
		return isValid;
		
	}
	
}//end class SiteView
