//package de.tudresden.bau.cib.vl.gui.simulation.energy.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.eclipse.rap.rwt.SingletonUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.tudresden.bau.cib.vl.core.database.domain.SimulationInformation;
//import de.tudresden.bau.cib.vl.core.database.domain.SimulationInformation.DETAILS;
//import de.tudresden.bau.cib.vl.core.database.domain.User;
//import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
//import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;
//import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
//import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicators;
//import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergySimulationService;
//import de.tudresden.bau.cib.vl.core.simulation.energy.service.PostProcessingService;
//import de.tudresden.bau.cib.vl.gui.core.controller.SessionManagementController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.model.EnergyKeyPerformanceIndicatorsWrapper;
//
///**
// * 
// * NOTE: CURRENTLY WE DON'T ALLOW MULTIPLE USERS. THIS AFFECTS THE HESMOS VIEWS.
// *
// * @author Ken Baumgaertel
// *	{@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
// *
// */
//public class EnergyKeyPerformanceIndicatorController {
//	
//	private EnergySimulationService 		energySimulationService;
//	private ConfigurationService 			configurationService;
//	private PostProcessingService 			postProcessingService;
//	
//	private EnergyKeyPerformanceIndicatorsWrapper 	eKPIViewModel;
//	private static EnergyKeyPerformanceIndicatorController INSTANCE;
//	
//	private static final Logger LOG = LoggerFactory.getLogger(EnergyKeyPerformanceIndicatorController.class);
//	
//	/**
//	 * Private constructor because of singleton pattern.
//	 */
//	private EnergyKeyPerformanceIndicatorController() {		
////		eKPIViewModel = new EnergyKeyPerformanceIndicatorsWrapper();
//	}
//
//	/**
//	 * Retrieves the single instance of EnergyKeyPerformanceIndicatorController.
//	 * @return The instance of EnergyKeyPerformanceIndicatorController.
//	 */
//	public static EnergyKeyPerformanceIndicatorController getInstance() {
//		return SingletonUtil.getSessionInstance(EnergyKeyPerformanceIndicatorController.class);
//	}
//	
//	public void loadEnergyKeyPerformanceIndicator(String simName) {
//		List<SimulationInformation> simulations = listSimulations();
//		for(SimulationInformation sim : simulations) {
//			String name = sim.getDetails().get(DETAILS.NAME);
//			if(name != null && name.equalsIgnoreCase(simName)) {
//				EnergyKeyPerformanceIndicators eKPIs = postProcessingService.getEKPIBySimulationId(sim.getId());
//				if(eKPIs != null) {
//					if(this.eKPIViewModel == null) {
//						this.eKPIViewModel = new EnergyKeyPerformanceIndicatorsWrapper(eKPIs);
//					} else {
//						this.eKPIViewModel.seteKPIs(eKPIs);
//					}
//					
//					List<Combustible> assignedCombustibles = new ArrayList<Combustible>();
//					List<String> assignedCombustibleIds = eKPIs.getCombustibleIds();
//					List<Combustible> combustibles = listCombustibles();
//					if(combustibles != null) {
//						for(Combustible c : combustibles) {
//							if(assignedCombustibleIds.contains(c.getId())) assignedCombustibles.add(c);
//						}
//					}
//					eKPIViewModel.setAssignedCombustibles(assignedCombustibles);
//					
//					eKPIViewModel.prepareData();
//				}
//			}
//		}
//	}
//	
//	/**
//	 * List the simulation of the current user.
//	 * @return
//	 */
//	public List<SimulationInformation> listSimulations() {
//		User currentUser = SessionManagementController.getInstance().getUser();
//		if(currentUser != null) {
//			Integer userId = currentUser.getId();
//			List<SimulationInformation> simulations = energySimulationService.listSimulations(userId);
//			return simulations;
//		}
//		return new ArrayList<SimulationInformation>();
//	}
//	
//	/**
//	 * List the combustibles of the current user.
//	 * @return
//	 */
//	public List<Combustible> listCombustibles() {
//		User currentUser = SessionManagementController.getInstance().getUser();
//		if(currentUser != null) {
//			Integer userId = currentUser.getId();
//			return postProcessingService.listCombustibles(userId);
//		}
//		return null;
//	}
//	
//	public CombustibleContainer loadCombustibles(File file) throws IOException {
//		return postProcessingService.loadCombustibles(file);
//	}
//	
//	public void saveCombustibles(File file, CombustibleContainer combustibleContainer) throws IOException {
//		postProcessingService.saveCombustibles(file, combustibleContainer);
//	}
//	
//	public File getCombustibleFile() {
//		String pathToResources = configurationService.getProperty(ConfigurationService.PROPERTIES.PATH_RESOURCES);
//		if(!StringUtils.endsWith(pathToResources, "/")) {
//			pathToResources += File.separator;
//		}
//		String pathToCombustibles = pathToResources+PostProcessingService.COMBUSTIBLE_FILE_NAME;
//		File combustibleFile = new File(pathToCombustibles);
//		return combustibleFile;
//	}
//	
//	public EnergyKeyPerformanceIndicatorsWrapper getEKPIViewModel() {
//		return eKPIViewModel;
//	}
//	
//	public void saveEnergyKeyPerformanceIndicators() {
//		postProcessingService.saveEnergyKeyPerformanceIndicators(eKPIViewModel.geteKPIs());
//	}
//
//	public void setEnergySimulationService(
//			EnergySimulationService energySimulationService) {
//		this.energySimulationService = energySimulationService;
//	}
//
//	public void setConfigurationService(ConfigurationService configurationService) {
//		this.configurationService = configurationService;
//	}
//
//	public void setPostProcessingService(PostProcessingService postProcessingService) {
//		this.postProcessingService = postProcessingService;
//	}
//}
