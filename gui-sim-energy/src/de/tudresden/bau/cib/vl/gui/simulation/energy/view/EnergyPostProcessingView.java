//package de.tudresden.bau.cib.vl.gui.simulation.energy.view;
//
//import java.util.List;
//import java.util.Map;
//
//import org.eclipse.jface.action.Action;
//import org.eclipse.jface.action.IToolBarManager;
//import org.eclipse.jface.action.ToolBarManager;
//import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.rap.rwt.RWT;
//import org.eclipse.rap.rwt.lifecycle.UICallBack;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Combo;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.ToolBar;
//import org.eclipse.ui.IActionBars;
//import org.eclipse.ui.IViewSite;
//import org.eclipse.ui.PartInitException;
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.Section;
//import org.eclipse.ui.part.ViewPart;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.tudresden.bau.cib.vl.core.database.domain.SimulationInformation;
//import de.tudresden.bau.cib.vl.core.database.domain.SimulationInformation.DETAILS;
//import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
//import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergySimulationService;
//import de.tudresden.bau.cib.vl.core.simulation.energy.service.PostProcessingService;
//import de.tudresden.bau.cib.vl.gui.common.Constants;
//import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.EnergyKeyPerformanceIndicatorController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//
//public class EnergyPostProcessingView extends ViewPart {
//	
//	/**
//	 * The ID of the view as specified by the extension.
//	 */
//	public static final String ID = Constants.PLACEHOLDER_PREFIX+".simulation.energy."+Constants.PLACEHOLDER_WINDOW_BOTTOM+".EnergyPostProcessingView";
//	
//	private Combo	simulationCombo;
//	private Action 	saveAction;
//	private Composite client;
//	private EnergyPostProcessingTabFolder tabFolder;
//	private EnergyKeyPerformanceIndicatorController controller = EnergyKeyPerformanceIndicatorController.getInstance();
//	
//	
//	private static final Logger LOG = LoggerFactory.getLogger(EnergyPostProcessingView.class);
//	
//	
//	@Override
//	public void createPartControl(Composite parent) {
//		parent.setLayout(new FillLayout(SWT.VERTICAL));
//	    FormToolkit toolkit = new FormToolkit(parent.getDisplay());
//	    Section section = toolkit.createSection(parent, Section.TITLE_BAR);
//	    section.setText(Messages.get().TEXT_ENERGY_POST_PROCESSING); //$NON-NLS-1$
//	    // Composite for storing the data
//	    client = toolkit.createComposite(section, SWT.WRAP);
//	    GridLayout layout = new GridLayout();
//	    layout.numColumns = 1;
//	    layout.marginWidth = 2;
//	    layout.marginHeight = 2;
//	    client.setLayout(layout);
//	    
//		Label label1 = toolkit.createLabel(client, Messages.get().TEXT_ENERGY_SIMULATION);
//		label1.setFont(AppearanceFactory.DEFAULT_FONT_TITLE2_BOLD);
//		simulationCombo = new Combo(client, SWT.NONE);
//		simulationCombo.setText(Messages.get().TEXT_NO_SIMULATIONS);
//		simulationCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		simulationCombo.addSelectionListener(new SelectionListener() {
//			
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -4528878492526975983L;
//
//			@Override
//			public void widgetSelected(SelectionEvent se) {
//				String simName = simulationCombo.getItem(simulationCombo.getSelectionIndex());
//				selectSimulation(simName);
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent se) {
//				widgetSelected(se);
//			}
//		});
//	    
//		makeActions();
//		contributeToActionBars();
//		
//		retrieveDataForComboBox();
//		
////		EnergyPostProcessingTabFolder tabFolder = new EnergyPostProcessingTabFolder(client, SWT.NONE);
////	    GridData gd = new GridData(GridData.FILL_BOTH);
////	    tabFolder.setLayoutData(gd);
//		
//		ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT);
//		ToolBar toolbar = toolBarManager.createControl(section);
//
//		saveAction = new Action(Messages.get().ACTION_SAVE, 
//				AppearanceFactory.getInstance().getImageDescriptor(
//						Activator.PLUGIN_ID, Activator.ICONS_22x22_PATH+"filesave.png")) {
//				
//				/**
//				 * 
//				 */
//				private static final long serialVersionUID = 5391182789486678676L;
//				
//				@Override
//				public void run() {					
//					UICallBack.runNonUIThreadWithFakeContext( Display.getCurrent(), new Runnable() {
//						public void run() {
//							controller.saveEnergyKeyPerformanceIndicators();
//							
//							MessageDialog.openInformation(
//									Display.getCurrent().getActiveShell(), Messages.get().MSG_SAVE_SUCCESSFULLY, Messages.get().MSG_SAVE_SUCCESSFULLY);
//						}
//					});
//					super.run();
//				}
//			};
//		toolBarManager.add(saveAction);
//		toolBarManager.update(true);
//
//		section.setTextClient(toolbar);
//		section.setClient(client);
//	}
//	
//	@Override
//	public void init(IViewSite site) throws PartInitException {
//		super.init(site);
//	}
//
//	@Override
//	public void setFocus() {
//
//	}
//	
//	private void contributeToActionBars() {
//		IActionBars bars = getViewSite().getActionBars();
//		fillLocalToolBar(bars.getToolBarManager());
//	}
//
//	private void fillLocalToolBar(IToolBarManager manager) {
////		manager.add(saveAction);
//	}
//
//	private void makeActions() {
////		saveAction = new Action() {
////
////			/**
////			 * 
////			 */
////			private static final long serialVersionUID = 5391182789486678676L;
////			
////			@Override
////			public void run() {
////				EnergyKeyPerformanceIndicatorController.getInstance().saveEnergyKeyPerformanceIndicators();
////				super.run();
////			}
////		};
//	}
//	
//	private void retrieveDataForComboBox() {
//		simulationCombo.removeAll();	
//		
//		List<SimulationInformation> simulations = controller.listSimulations();
//		
////		http://127.0.0.1:9090/ivel/start?view=template&simName=SimVariant1
//		String simName = RWT.getRequest().getParameter("simName");				
//		
//		if(simulations.size() > 0) {
//			int i = 0;
//			boolean foundSim = false;
//			for(SimulationInformation sim : simulations) {
//				Map<DETAILS, String> detailMap = sim.getDetails();
//				String cSimName = detailMap.get(DETAILS.NAME);
//				if(cSimName != null) {
//					simulationCombo.add(cSimName);					
//					LOG.debug("Compare simName: {} with simulation name: {}", new Object[]{simName, cSimName});
//					if(simName != null && cSimName.equalsIgnoreCase(simName)) {
//						simulationCombo.select(i);
//						selectSimulation(simName);
//						foundSim = true;
//					}					
//				}
//				i++;
//			}
//			if(!foundSim && simulationCombo.getItems() != null) {
//				simulationCombo.select(0);				
//				String cSimName = simulationCombo.getItem(simulationCombo.getSelectionIndex());
//				selectSimulation(cSimName);
//			}
//		} else {
//			simulationCombo.setText(Messages.get().TEXT_NO_SIMULATIONS);
//		}
//	}
//	
//	private void selectSimulation(String simName) {
//		controller.loadEnergyKeyPerformanceIndicator(simName);
//		if(tabFolder == null) {
////			initialize Tab folder because it depends on a selected eKPI
//			tabFolder = new EnergyPostProcessingTabFolder(client, SWT.NONE);
//			GridData gd = new GridData(GridData.FILL_BOTH);
//			tabFolder.setLayoutData(gd);
//		}
//		tabFolder.setSelection(0);
//		tabFolder.layout(true);
//	}
//	
//	public void setEnergySimulationService(
//			EnergySimulationService energySimulationService) {
//		EnergyKeyPerformanceIndicatorController.getInstance().setEnergySimulationService(energySimulationService);
//	}
//	
//	public void setConfigurationService(ConfigurationService configurationService) {
//		EnergyKeyPerformanceIndicatorController.getInstance().setConfigurationService(configurationService);
//	}
//	
//	public void setPostProcessingService(PostProcessingService postProcessingService) {
//		EnergyKeyPerformanceIndicatorController.getInstance().setPostProcessingService(postProcessingService);
//	}
//
//}
