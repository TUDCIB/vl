package de.tudresden.bau.cib.vl.gui.simulation.energy.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcspatialstructureelement;
import jsdai.lang.SdaiException;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sensitivity_input.NandradOutputSummary;
import sensitivity_input.ResultsOfZone;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.common.controller.AbstractViewController;
import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;
import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.SimulationResultView;

public class SimulationResultController extends AbstractViewController<SimulationResultView> { 
	
	
	protected SimulationProject model;	
	private Ifc2x3DataModelProxy ifcModel;

	public static final String ZONES_OF_INTEREST_SUMMARY_FILE = "ZonesOfInterestsummary.txt";
	
	
	public static final String VIEW_ID_EMPTY = "de.tudresden.bau.cib.vl.gui.simulation.energy.ui.view.main.SimulationResultEmptyView";
	public static final String VIEW_ID_NANDRAD_PASSIVE = "de.tudresden.bau.cib.vl.gui.simulation.energy.ui.view.main.SimulationResultNandradPassiveView";
	public static final String VIEW_ID_NANDRAD = "de.tudresden.bau.cib.vl.gui.simulation.energy.ui.view.main.SimulationResultNandradView";
	public static final String VIEW_ID_THERAKLES = "de.tudresden.bau.cib.vl.gui.simulation.energy.ui.view.main.SimulationResultTheraklesView";
	public static final String VIEW_ID_CFD = "de.tudresden.bau.cib.vl.gui.simulation.energy.ui.view.main.SimulationResultCFDView";	
	public static final Set<String> viewIDs = new HashSet<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = -7028013591792344627L;

	{
													add(VIEW_ID_EMPTY);
													add(VIEW_ID_NANDRAD_PASSIVE);
													add(VIEW_ID_NANDRAD);
													add(VIEW_ID_THERAKLES);
													add(VIEW_ID_CFD);
													
	
	
											  }};
	
	protected static Logger LOG = LoggerFactory.getLogger(SimulationResultController.class);	

	@Override
	protected Set<CommunicationEvents> defineReceivedEvents() {
		
		Set<CommunicationEvents> events = new HashSet<CommunicationEvents>();
		events.add(CommunicationEvents.SIMULATION_PROJECT_SELECTED);	
		events.add(CommunicationEvents.IFC_MODEL);	
		events.add(CommunicationEvents.PROJECT_LOADED);	
		
		return events;
	}

	@Override
	protected BundleContext getBundleContext() {
		return Activator.getDefault().getContext();
	}

	@Override
	protected void handleEvent(CommunicationEvents event,Map<String, Object> dataMap) {
		LOG.debug("Retrieving data: {} with topic: {}", 
					new Object[]{dataMap, event.getName()});
		Object data = dataMap.get(CommunicationService.PROPERTIES_KEY_DATA);
		
		switch(event) {
			case SIMULATION_PROJECT_SELECTED: 		{
				
				setModel((SimulationProject) data);
			}			
			break;	
			case IFC_MODEL: 
			{				
				setIfcModel((Ifc2x3DataModelProxy) data);	

				
			}			
			break;	
			case PROJECT_LOADED: 
			{
				
				if(data instanceof SimulationProject)
				{
					setModel((SimulationProject) data);
					creatGUI();	
				}	
			}			
			break;	
			
			
			default:
				break;
		}
		
	}

	public SimulationProject getModel() {
		return model;
	}
	
	public void setModel(SimulationProject model)
	{	
		this.model = model;
	}
	


	public void creatGUI()
	{			
		
		SimulationProject.TYPE type = model.getSimulationTypeId();
		SimulationProject.STATUS status = model.getSimulationStatus();			
		LOG.debug("Identifying type {} and status {} of simulation project", new Object[]{type, status});
		
		
		switch(status)
		{
			case NEW:
			case RUNNING: //break; //TODO
			case ERROR: //break;
			case COMPLETED: 			
				
				switch(type)
				{	
					case NANDRAD_PASSIV: 	view.createPartControlNandradPassive(model); break;	
					case NANDRAD: 			view.createPartControlNandrad(model); break;						
					case THERAKLES: 		view.createPartControlTherakles(model); break;								
					case CFD:				view.createPartControlCFD(this.model); break;
					default:				view.createPartControlHint("Please select a completed simualtion project"); break;
				}
			
			break;
			default: break;
		}
	}
	
	public EIfcroot getIfcElem(String ifcID)
	{
		EIfcroot ret = null;		
		if(ifcModel == null) return ret;
		
		try {
			ret = ifcModel.getIfcEntity(ifcID, null);
		} catch (IfcException e) {
			LOG.error(e.getMessage());
		}
		
		return ret;
	}
	
	public String getIfcName(String ifcID)
	{					
		EIfcroot elem = null;
		String ret = "Unknown";
		
		
		if(ifcModel == null)
			return ret;
		
		Set<String> set = new HashSet<String>();
		set.add(ifcID);
		try {
			Set<EIfcroot> res = ifcModel.getIfcEntitiesByGlobalIds(set);
			if(res.size()==1)
			{
				for(EIfcroot root : res)
				{
					elem = root;					
				}
			}
			
			if(elem != null)
			{
				if(elem instanceof EIfcspatialstructureelement)
					ret = ((EIfcspatialstructureelement)elem).getLongname((EIfcspatialstructureelement)elem);
				else
					ret = elem.getName(elem);
			}
			
			
			
		} catch (IfcException e) {
			LOG.error(e.getMessage(),e);
		} catch (SdaiException e) {
			LOG.error(e.getMessage(),e);
		} 
		
		return ret;

		
	}
	
	public void publishZonesOfInterests(Collection<EIfcroot> elems)
	{
		sendSync(CommunicationEvents.IFC_ZONESOFINTERESTS, elems);
	}
	
	public void publishIfcSelection(Set<String> guids)
	{
		sendSync(CommunicationEvents.IFC_SELECTION, guids);
	}
	
	private void showView(String id)
	{
		for(String viewID : viewIDs)
		{
			
			for (IWorkbenchPage page : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPages())
			{
				IViewPart help = page.findView(viewID);			
				
				if(viewID == id)
				{
					//Show
					try {
						 page.showView(id);
						//view.setController(this);
						
					} catch (PartInitException e) {
						LOG.error(e.getMessage());
					}
				}
				else
				{
					if(help != null)
						page.hideView(help);
				}
				
			}					
		}
			
	}
	
	

//	protected void setModelNanadradPassive()
//	{
//			
//		try {
//			Set<FileInformation> files = model.getOutputFiles();
//			
//			Set<FileInformation> pictures = new HashSet<FileInformation>();
//			FileInformation zonesOfInterests = null;		
//			for(FileInformation file : files)
//			{
//				if(file.getFileType() == FileInformation.TYPE.PNG)
//				{
//					pictures.add(file);
//				}
//				
//				if(file.getName().compareToIgnoreCase(ZONES_OF_INTEREST_SUMMARY_FILE) == 0)
//				{
//					zonesOfInterests = file;
//				}			
//				
//			}		
//			
//			NandradOutputSummary nandrad = new NandradOutputSummary();
//			nandrad.ReadSummaryFile( zonesOfInterests.getUrl());	
//			
//			List<ResultsOfZone> resultsOfZone = nandrad.resultsOfZones;
//			
//			((SimulationResultNandradPassiveView) view).setResults(resultsOfZone, pictures);
//		} catch (IOException e) {
//			LOG.error("{}", e.getMessage(), e);
//		}		
//	}
//	
//	protected void setModelNandrad()
//	{
//		((SimulationResultNandradView)view).setModel(model);
//	}
//	
//	protected void setModelTherakles()
//	{
//		((SimulationResultTheraklesView)view).setModel(model);
//	}
//	
//	protected void setModelCFD()
//	{
//		((SimulationResultCFDView)view).setModel(model);
//	}

	public Ifc2x3DataModelProxy getIfcModel() {
		return ifcModel;
	}

	public void setIfcModel(Ifc2x3DataModelProxy ifcModel) {
		this.ifcModel = ifcModel;
	}
	
}
