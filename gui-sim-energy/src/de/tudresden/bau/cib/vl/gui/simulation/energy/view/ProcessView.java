package de.tudresden.bau.cib.vl.gui.simulation.energy.view;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcspatialstructureelement;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.service.BimFitService;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.service.UserService;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergySimulationService;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.SimulationMatrixService;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.SpaceBoundaryService;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCModelContentProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
import de.tudresden.bau.cib.vl.gui.common.view.AbstractView;
import de.tudresden.bau.cib.vl.gui.core.controller.SessionManagementController;
import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.ProcessController;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.IfcTreeSelectionDialog;

public class ProcessView extends AbstractView<ProcessController> {
	
	private Display display = null;
	
	private Button passiveBtn, sensitivityBtn, buildingSimulationBtn, cfdSimulationBtn;
	private Button optimizeGreenBuildingDesignBtn;
	private Label oneToTwo, twoToThree, threeToFour;
	
	private ProcessController controller;
	private ConfigurationService configurationService;
	private SpaceBoundaryService spaceBoundaryService;
	private OntologyService ontologyService;
	private TemplateService resourceService;
	private SimulationMatrixService simulationMatrixService;
	private EnergySimulationService energySimulationService;
	private BimFitService modelService;
	private FileService fileService;
	private UserService userService;
	
	@Override
	protected ProcessController createController() {
		controller = ProcessController.getInstance();
		controller.setConfigurationService(configurationService);
		controller.setEnergySimulationService(energySimulationService);
		controller.setFileService(fileService);
		controller.setIfcModelService(modelService);
		controller.setOntologyService(ontologyService);
		controller.setResourceService(resourceService);
		controller.setSimulationMatrixService(simulationMatrixService);
		controller.setSpaceBoundaryService(spaceBoundaryService);
		controller.setUserService(userService);
		Integer userId = SessionManagementController.getInstance().getUser().getId();
		controller.setUserId(userId);
		return controller;
	}
	
	
	@Override
	public void createPartControl(Composite parent) {
		this.display = parent.getDisplay();
		controller.setDisplay(display);
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		passiveBtn = new Button(parent, SWT.NONE);
		passiveBtn.setText("Passive Simulation");
		passiveBtn.setEnabled(false);
		passiveBtn.setImage(AppearanceFactory.getInstance().createImage(
				Activator.getDefault().getBundle().getSymbolicName(), 
				Activator.ICONS_64x64_PATH+"passive1_red.png"));
		
		passiveBtn.addSelectionListener(new SelectionAdapter() {			
			@Override
			public void widgetSelected(SelectionEvent e) {		
				controller.startPassiveSimulation();
			}
		});
		
		oneToTwo = new Label(parent, SWT.CENTER);
		oneToTwo.setImage(AppearanceFactory.getInstance().createImage(
				Activator.getDefault().getBundle().getSymbolicName(), 
				Activator.ICONS_64x64_PATH+"1_to_2.png"));
		oneToTwo.setEnabled(false);
		
		sensitivityBtn = new Button(parent, SWT.NONE);
		sensitivityBtn.setText("Sensitivity Analysis");
		sensitivityBtn.setEnabled(false);
		sensitivityBtn.setImage(AppearanceFactory.getInstance().createImage(
				Activator.getDefault().getBundle().getSymbolicName(), 
				Activator.ICONS_64x64_PATH+"sensitivity2_orange.png"));
		
		sensitivityBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Set<EIfcspatialstructureelement> requestedLocationIds = new HashSet<EIfcspatialstructureelement>();
					
					Collection<EIfcroot> zonesOfInterest = controller.getZonesOfInterest();
					if(zonesOfInterest == null || zonesOfInterest.isEmpty())
						zonesOfInterest = new HashSet<EIfcroot>();
					
					IfcTreeSelectionDialog dialog = new IfcTreeSelectionDialog(display.getActiveShell(), 
							new IFCLabelProvider(), new IFCModelContentProvider(ContentMode.ROOMS), zonesOfInterest );
					Ifc2x3DataModelProxy ifcModel = controller.getIfcModel();
					dialog.setInput(ifcModel);
					if(dialog.open() == Window.OK) {
						Set<EIfcroot> entities = dialog.getCheckedEntities();
						for(EIfcroot ifcRoot : entities) {
							requestedLocationIds.add((EIfcspatialstructureelement) ifcRoot);
						}
						controller.startSensitivityAnalysis(requestedLocationIds);
					}	
				} catch (IOException ex) {
					MessageDialog.openError(display.getActiveShell(), 
							"IFC exception",
							ex.getMessage());
				}
			}
		});
		
//		twoToThree = new Label(parent, SWT.CENTER);
//		twoToThree.setImage(AppearanceFactory.getInstance().getImage(
//				Activator.getDefault().getBundle().getSymbolicName(), 
//				Activator.ICONS_64x64_PATH+"2_to_3.png"));
//		twoToThree.setEnabled(false);
//		
//		buildingSimulationBtn = new Button(parent, SWT.NONE);
//		buildingSimulationBtn.setText("Building simulation");
//		buildingSimulationBtn.setEnabled(false);
//		buildingSimulationBtn.setImage(AppearanceFactory.getInstance().getImage(
//				Activator.getDefault().getBundle().getSymbolicName(), 
//				Activator.ICONS_64x64_PATH+"building3_green.png"));
//		
//		buildingSimulationBtn.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				controller.startBuildingSimulation();
//			}
//		});
//		
//		threeToFour = new Label(parent, SWT.CENTER);
//		threeToFour.setImage(AppearanceFactory.getInstance().getImage(
//				Activator.getDefault().getBundle().getSymbolicName(), 
//				Activator.ICONS_64x64_PATH+"3_to_4.png"));
//		threeToFour.setEnabled(false);
//		
//		cfdSimulationBtn = new Button(parent, SWT.NONE);
//		cfdSimulationBtn.setText("CFD simulation");
//		cfdSimulationBtn.setEnabled(false);
//		cfdSimulationBtn.setImage(AppearanceFactory.getInstance().getImage(
//				Activator.getDefault().getBundle().getSymbolicName(), 
//				Activator.ICONS_64x64_PATH+"cfd4_blue.png"));
		
		optimizeGreenBuildingDesignBtn = new Button(parent, SWT.NONE);
		optimizeGreenBuildingDesignBtn.setText("Automatic green building design optimization (Constructions)");
		optimizeGreenBuildingDesignBtn.setEnabled(true);
		optimizeGreenBuildingDesignBtn.setImage(
				AppearanceFactory.getInstance().createImage(Activator.getDefault().getBundle().getSymbolicName(),
						Activator.ICONS_PATH+"circle-icon-grey.png", 32, 32));
		optimizeGreenBuildingDesignBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controller.startGreenBuildingDesignOptimization();
			}
		});
	}

	@Override
	public void setFocus() {

	}
	
	public void enablePassiveSimButton(boolean isEnabled) {
		passiveBtn.setEnabled(isEnabled);		
	}

	public void enableSensitivityButton(boolean isEnabled) {
		sensitivityBtn.setEnabled(isEnabled);
		oneToTwo.setEnabled(isEnabled);
	}

	public void enableCFDButton(boolean isEnabled) {
		if(cfdSimulationBtn != null) {
			cfdSimulationBtn.setEnabled(isEnabled);
			threeToFour.setEnabled(isEnabled);
		}
	}

	public void enableBuildingSimButton(boolean isEnabled) {
		if(buildingSimulationBtn != null) {
			buildingSimulationBtn.setEnabled(isEnabled);
			twoToThree.setEnabled(isEnabled);
		}
	}
	
	public void enableGreenBuildingOptimizationButton(boolean isEnabled) {
		if(optimizeGreenBuildingDesignBtn != null) {
			optimizeGreenBuildingDesignBtn.setEnabled(isEnabled);
		}
	}


	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}


	public void setSpaceBoundaryService(SpaceBoundaryService spaceBoundaryService) {
		this.spaceBoundaryService = spaceBoundaryService;
	}


	public void setOntologyService(OntologyService ontologyService) {
		this.ontologyService = ontologyService;
	}


	public void setResourceService(TemplateService resourceService) {
		this.resourceService = resourceService;
	}


	public void setSimulationMatrixService(
			SimulationMatrixService simulationMatrixService) {
		this.simulationMatrixService = simulationMatrixService;
	}


	public void setEnergySimulationService(
			EnergySimulationService energySimulationService) {
		this.energySimulationService = energySimulationService;
	}


	public void setBimFitService(BimFitService modelService) {
		this.modelService = modelService;
	}


	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
