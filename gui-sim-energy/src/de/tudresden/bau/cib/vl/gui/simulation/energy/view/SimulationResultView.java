package de.tudresden.bau.cib.vl.gui.simulation.energy.view;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcspatialstructureelement;
import jsdai.lang.SdaiException;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.AuthenticationEvent;
import org.eclipse.swt.browser.AuthenticationListener;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sensitivity_input.NandradOutputSummary;
import sensitivity_input.Quantity;
import sensitivity_input.ResultsOfZone;
import sensitivity_input.TheraklesOutputSummary;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.DecisionMakingService;
import de.tudresden.bau.cib.vl.gui.Activator;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
import de.tudresden.bau.cib.vl.gui.common.view.AbstractView;
import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.SimulationResultController;


public class SimulationResultView extends AbstractView<SimulationResultController> {

	private SimulationProject model;
	
	private static Logger LOG = LoggerFactory.getLogger(SimulationResultView.class);
	
	private boolean isShown = false;
	
	private Composite parent;
	private Display display;
	private FormToolkit toolkit;
	private TabFolder tabFolder;
	protected SimulationResultController controller;
	private DecisionMakingService decisionService;
	private ConfigurationService configurationService;
	private IToolBarManager toolBarManager;
	
	private Action actionHelp;
	
//	private final String toolTip =  "eKPI legend \n"+
//									"-----------------------------------------------------------\n"+
//									"1.1.1 - Net Energy Consumption of the Heating System \n"+
//									"1.1.2 - Final Energy Consumption of the Heating System \n"+
//									"1.1.2 - Primary Energy Consumption of the Heating System \n"+
//									"1.2.1 - Net Energy Consumption of the Cooling System \n"+
//									"1.2.2 - Final Energy Consumption of the Cooling System \n"+
//									"1.2.3 - Primary Energy Consumption of the Cooling System \n"+
//									"2.1 - Average air temperature \n"+
//									"2.2 - Average operative temperature \n"+ 
//									"2.3 - Number of comfortable hours (thermal) \n"+
//									"2.4 - Percentage of comfortable hours (hygric) \n"+
//									"3.1 - Operational costs \n";
	
	private final String toolTip =  "eKPI legend \n"+
									"-----------------------------------------------------------\n"+
									"1.0.1 - Net annual natural ventilation heat loss [kWh/m2a] \n"+
									"1.0.2 - Net annual internal equipment heat gains [kWh/m2a] \n"+
									"1.0.3 - Net annual transmission heat losses [kWh/m2a] \n"+
									"1.0.4 - Net annual solar heat gains [kWh/m2a] \n"+
									"1.1.1 - Net annual energy consumption of the heating system [kWh/m2a] \n"+
									"1.1.2 - Net annual energy consumption of the cooling system [kWh/m2a] \n"+
									"1.2.1 - Final annual energy consumption of the heating system [kWh/m2a] \n"+
									"1.2.2 - Final annual energy consumption of the cooling system [kWh/m2a] \n"+
									"1.3.1 - Primary energy consumption for heating and cooling [kWh/m2a] \n"+
									"1.4.1 - Annual average of operative temperature [degC] \n"+
									"1.4.2 - Number of annual discomfort hours via EN 15251 [h] \n";
	
	
	private static String SPLITTER = "summary"; //0Tj1QRvhT58RU2$awuR4rsummary.txt
	
	@Override
	protected SimulationResultController createController() {
		controller = new SimulationResultController();
		return controller;
	}
	
	@Override
	public void createPartControl(Composite parent) {
		
		this.parent = parent;
		this.display = parent.getDisplay();
		
		
		toolBarManager = getViewSite().getActionBars().getToolBarManager();
		
		actionHelp = new Action() {			

			/**
			 * 
			 */
			private static final long serialVersionUID = -3938915755649898191L;

			public void run() {}
				
			
		};
		actionHelp.setText("eKPI legend");
		actionHelp.setToolTipText(toolTip);
		ImageDescriptor imageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"Info_16x16.png");			
		actionHelp.setImageDescriptor(imageDescriptor);
		actionHelp.setId("eKPI legend");
		
		

		
		
		
		
//		Label label1 = new Label( parent, SWT.WRAP );     
//	        
//		label1.setText("Please select a completed simualtion project");  
	
		
	}

	@Override
	public void setFocus() {

	}
	
	public void setModel(SimulationProject model)
	{
		this.model = model;
	}
	
	public void createPartControlCFD(SimulationProject model)
	{
		setModel(model);
		
		clearPartControl();		
		
		
		parent.setLayout(new FillLayout(SWT.VERTICAL));		
		
		toolkit = new FormToolkit(parent.getDisplay());
		
		ScrolledForm form = toolkit.createScrolledForm(parent);	
		toolkit.decorateFormHeading(form.getForm());
		form.setText("Simulation results CFD");	
		
		final Composite body = form.getBody();
		body.setLayout(new FillLayout(SWT.VERTICAL));
		
		display.asyncExec(new Runnable() {
			
			@Override
			public void run() {
		
			
				Browser browser = new Browser(body, SWT.NONE);				
			
				String html = createVideoHtml("/resources/video/", "streamlines_evolution");
				
		        if (html != null) {
		        	if(!browser.isDisposed()) {
		        		browser.setText(html);
		        	}
		        } 
			}
			
		});
		
		
		parent.layout(true);
		
	}

	private void clearPartControl() {
		if(parent.isDisposed()) return;
		for(Control child : parent.getChildren())
		{
			if(!child.isDisposed()) {
				child.dispose();
			}
		}
		
		
		toolBarManager.remove(actionHelp.getId());
		toolBarManager.update(true);
		
		
	}

	public void createPartControlNandradPassive(SimulationProject model)
	{
		
		setModel(model);
		
		clearPartControl();			
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//Read data
		
		Set<FileInformation> files = model.getOutputFiles();
		
		Set<FileInformation> pictures = new HashSet<FileInformation>();
		FileInformation zonesOfIntrests = null;		
		for(FileInformation file : files)
		{
			if(file.getFileType() == FileInformation.TYPE.PNG)
			{
				pictures.add(file);
			}
			
			if(file.getName().compareToIgnoreCase("ZonesOfInterestSummary.txt") == 0)
			{
				zonesOfIntrests = file;
			}			
			
		}		
			
		if(zonesOfIntrests == null) return;
		
		List<TableEntry> entries = new ArrayList<TableEntry>();	
		Set<EIfcroot> zones = new HashSet<EIfcroot>();
		try {
			NandradOutputSummary nandrad = new NandradOutputSummary();	
			nandrad.ReadSummaryFile( zonesOfIntrests.getUrl());
			
			
			TableEntry entry = null;
			for(ResultsOfZone result : nandrad.resultsOfZones  )
			{		
				String ifcId;
				EIfcroot elem;
				String ifcName ="";
								
				for( Quantity quant : result.getOutstandingQuantities())
				{
					ifcId = result.getIFCKey();
					elem = controller.getIfcElem(ifcId);					
					
					if(elem != null)
					{
						if(!zones.contains(elem))
							zones.add(elem);
						
						try {
							if(elem instanceof EIfcspatialstructureelement)								
								ifcName = ((EIfcspatialstructureelement)elem).getLongname((EIfcspatialstructureelement)elem);
							else
								ifcName = elem.getName(elem);
						
						} catch (SdaiException e) {

						}
					}				
					
							
					
					//ifcName = controller.getIfcName(ifcId);
					
					entry = new TableEntry();
					entry.index = result.getIndex().toString();
					entry.ifcId = ifcName.compareTo("Unknown")==0 ? ifcId : ifcName; 
					entry.comment = quant.name;
					entry.value = quant.value.toString() + " [" + quant.unit + "]";
					
					entries.add(entry);				
				}			
			}		
			
			controller.publishZonesOfInterests(zones);
			
			
		
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}		

		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//Create header
	
		parent.setLayout(new FillLayout(SWT.VERTICAL));		

		toolkit = new FormToolkit(parent.getDisplay());

		ScrolledForm form = toolkit.createScrolledForm(parent);	
		toolkit.decorateFormHeading(form.getForm());
		form.setText("Simulation results NANDRAD");	

		Composite body = form.getBody();
		body.setLayout(new GridLayout(1, false));
		
	
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//Create Table	
		
		Section sectionTable = toolkit.createSection(body, Section.TITLE_BAR );
		sectionTable.setText("Zones of interests");
		Composite compositeTable = toolkit.createComposite(sectionTable, SWT.WRAP);	
		compositeTable.setLayout(new GridLayout());
		sectionTable.setClient(compositeTable);

		Table t = toolkit.createTable(compositeTable, SWT.NO_SCROLL | SWT.MULTI |  SWT.FULL_SELECTION | SWT.BORDER);
		TableViewer viewer = new TableViewer(t);		

		GridData gd = new GridData(GridData.FILL_BOTH);
		//gd.heightHint = 100;
		gd.widthHint = 1000;
		t.setLayoutData(gd);
		t.setHeaderVisible(true);
		t.setLinesVisible(true);		

		t.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				String string = event.detail == SWT.CHECK ? "Checked"
						: "Selected";
				System.out.println(event.item + " " + string);
			}
		});		


		viewer.setContentProvider(ArrayContentProvider.getInstance());

		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		viewerColumn.getColumn().setWidth(50);
		viewerColumn.getColumn().setText("Zone");

		TableViewerColumn viewerColumn1 = new TableViewerColumn(viewer, SWT.NONE);
		viewerColumn1.getColumn().setWidth(200);
		viewerColumn1.getColumn().setText("IFC Element");

		TableViewerColumn viewerColumn2 = new TableViewerColumn(viewer, SWT.NONE);
		viewerColumn2.getColumn().setWidth(600);
		viewerColumn2.getColumn().setText("Comment");

		TableViewerColumn viewerColumn3 = new TableViewerColumn(viewer, SWT.NONE);
		viewerColumn3.getColumn().setWidth(150);
		viewerColumn3.getColumn().setText("Value");

		viewer.setLabelProvider(new TableLabelProvider());	
		viewer.setInput(entries);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		//Create images		
		
		for(FileInformation file : pictures)
		{

			String name = file.getName();
			name = name.replace(".png", "");
			
			// skip pictures with 50 dpi -> use only pics with 200 dpi
			if(StringUtils.contains(name, "50dpi")){
				continue;
			}
			
			name = name.replace("Zones", "");


			Section sectionPicture1 = toolkit.createSection(body, Section.TITLE_BAR );
			sectionPicture1.setText(name);
			Composite compositePicture1 = toolkit.createComposite(sectionPicture1, SWT.WRAP);		
			compositePicture1.setLayout(new GridLayout());	
			sectionPicture1.setClient(compositePicture1);

			ImageData imgData = new ImageData( file.getFilePath() );	        
			Image image = new Image( parent.getDisplay(), imgData.scaledTo(1200, 600));  

			Label label1 = new Label( compositePicture1, SWT.WRAP );     

			label1.setImage( image ); 
		}
		
//		form.layout(true, true);
		parent.layout(true);
	}
	
	
	public void createPartControlNandrad(SimulationProject model)
	{		
		
		setModel(model);
		
		clearPartControl();	
		
		toolBarManager.add(actionHelp);
		toolBarManager.update(true);
		
		Browser browser = new Browser(parent, SWT.NONE);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//Read Data	
		
		Set<URL> URLs = new HashSet<URL>();		
//		TODO
//		for(FileInformation file : model.getOutputFiles())
//		{
//			
//			URLs.add(file.getUrl());
//		}
			
		browser.setUrl("http://www.roomex.fi/WebSimVis/DataDisplay/Details/e51bc681-0344-4825-be2a-040ea24ccd2c");
		
				
//		//TheraklesOutputSummary tos = new TheraklesOutputSummary(file.getUrl());		
//		String csvPath = model.getPathOutputDirectory() + "//" + model.getName() + ".csv";
//		//tos.createInputFile(csvPath);		
//			
//		URL url = decisionService.uploadCase(new File(csvPath), model.getName(), model.getName());		
//		
//		browser.setUrl(url.toString());
		parent.layout(true);
	}	
	
	public void createPartControlTherakles(SimulationProject model) {
		
		setModel(model);
		
		clearPartControl();	
		
		toolBarManager.add(actionHelp);
		toolBarManager.update(true);
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//Create header
		
		tabFolder = new TabFolder(parent, SWT.BORDER);		
		 // Add an event listener to write the selected tab to stdout
	    tabFolder.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TabItem tabItem = tabFolder.getSelection()[0];
				showZoneOfInterest(tabItem);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//Read Data		

		final String HOST = configurationService.getProperty(PROPERTIES.ISES_GRANLUND_DECISIONMAKING_URL);
		final String USER = configurationService.getProperty(PROPERTIES.ISES_GRANLUND_DECISIONMAKING_USER);
		final String PASSWORD = configurationService.getProperty(PROPERTIES.ISES_GRANLUND_DECISIONMAKING_PASSWORD);
		
		TabItem tabItem = null;
		String name = null;
		String id = null;
		URL url = null;	
		
		Set<FileInformation> outputFiles = model.getOutputFiles();
		
		LOG.debug("Output files of Therakles project {}", new Object[]{outputFiles});
		
		for(FileInformation file : outputFiles) {	
			String fileName = file.getName();
			
			String[] splits = fileName.split(SPLITTER);
			
			if(splits.length >= 2) {
				LOG.debug("Therakles output file: {}", new Object[]{fileName});
				tabItem = new TabItem(tabFolder, SWT.NULL);				
				Browser browser = new Browser(tabFolder, SWT.NONE);
				
				browser.addAuthenticationListener(new AuthenticationListener(){
					
					@Override
					public void authenticate(AuthenticationEvent event) {
						try {
							URL url = new URL(event.location);
							if (url.getHost().equals(HOST)) {
								event.user = USER;
								event.password = PASSWORD;
							} else {
								/* do nothing, let default prompter run */
								LOG.error("Wrong host: {}", new Object[]{url.getHost()});
							}
						} catch (MalformedURLException e) {
							/* should not happen, let default prompter run */
							LOG.error(e.getMessage());
						}
					}
				});
				
				tabItem.setControl(browser);
				id = splits[0];
				name = controller.getIfcName(id);
				// set GUID as data for TabItem
				tabItem.setData(id);
				
				if(name.compareTo("Unknown") == 0) {
					name = id;
				}	
				tabItem.setText(name);	
				
				LOG.debug("Local file URL: {}", new Object[]{file.getUrl()});
				
				TheraklesOutputSummary tos = new TheraklesOutputSummary(file.getUrl());
				//String strGranundInput = tos.createInputString();
				
				String csvPath = model.getPathOutputDirectory() + "/" + name + ".csv";
				tos.createSensitivityInputFile(csvPath);	
				
				LOG.debug("CSV file saved: {}", new Object[]{csvPath});
					
				url = decisionService.uploadCase(new File(csvPath), model.getName(), model.getName());		
				LOG.debug("URL of case: {}", new Object[]{url});
				
				browser.setUrl(url.toString());
				if(tabFolder.getItems().length > 0) showZoneOfInterest(tabFolder.getItems()[0]);
				parent.layout(true);
			} else {
//				name = fileName;
				// ignore file
			}			
		}	

	}
	
	
	public void createPartControlHint(String hint)
	{
		 Label label1 = new Label( parent, SWT.WRAP );     
	        
		  label1.setText(hint);
		
	}
	
	private String createVideoHtml(String videoDir, String fileName) {
//		final HostInfo hostInfo = HostInfo.getInstance();
//		hostInfo.init();
//
//		VideoHelper videoHelper = new VideoHelper(hostInfo, videoDir);
//		VideoMetadata assignedVideo = new VideoMetadata("CFD Video: "+fileName, fileName, 640, 480, null);
//    	String htmlForVideo = videoHelper.createHtmlForVideo(assignedVideo);
//    	return htmlForVideo;
		return null;
	}
	
	
	private class TableEntry
	{
		public String index;
		public String ifcId;
		public String comment;
		public String value;

	}

	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5863506449239393170L;

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {

			if(element instanceof TableEntry)
			{

				TableEntry ent = (TableEntry) element;

				String result = "";
				switch(columnIndex){
				case 0:
					result = ent.index;
					break;
				case 1:
					result = ent.ifcId;
					break;
				case 2:
					result = ent.comment;
					break;	
				case 3:
					result = ent.value;
					break;					
				default:
					//should not reach here
					result = "";
				}
				return result;
			}	
			else
				return "";



		}
	}
	
	public void setDecisionService(DecisionMakingService decisionService) {
		this.decisionService = decisionService;
	}

	private void showZoneOfInterest(TabItem tabItem) {
		Set<String> guids = new HashSet<String>();
		// the data of the TabItem should be the GlobalId
		String guid = (String)tabItem.getData();
		if(StringUtils.isNotEmpty(guid)) {
			guids.add(guid);
			//	controller.publishIfcSelection(guids);
			EIfcroot elem = controller.getIfcElem(guid);
			if(elem != null) {
				controller.publishZonesOfInterests(Arrays.asList(elem));
			}
		}
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	

}
