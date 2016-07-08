//package de.tudresden.bau.cib.vl.gui.simulation.energy.controller;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.TransformerException;
//
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.core.runtime.IStatus;
//import org.eclipse.core.runtime.Status;
//import org.eclipse.core.runtime.jobs.Job;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
//import de.tudresden.bau.cib.vl.core.database.domain.UserRepository;
//import de.tudresden.bau.cib.vl.core.exception.ParsingException;
//import de.tudresden.bau.cib.vl.core.model.Resource;
//import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;
//import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
//import de.tudresden.bau.cib.vl.core.service.UserService;
//import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergySimulationService;
//import de.tudresden.bau.cib.vl.gui.core.controller.SessionManagementController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.model.ConstructionTemplateModel;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs.ClimateTabItem;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs.ConstructionTypeTabItem;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs.SpaceTypeTabItem;
//
///**
// * 
// * NOTE: CURRENTLY WE DON'T ALLOW MULTIPLE USERS. THIS AFFECTS THE HESMOS VIEWS.
// * 
// * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
// *
// */
//public class TemplateViewController {
//	
//	private List<ConstructionTemplate> constructionTemplates;
//	private List<ClimateLocationTemplate> climateTemplates;
//	private List<SpaceTypeTemplate> spaceTypeTemplates;
//	private List<MaterialTemplate> materialTemplates;
//
//	private EnergySimulationService energySimulationService;
//	private ConfigurationService configurationService;
//	private ClimateTabItem climateTabItem;
//	private SpaceTypeTabItem spaceTypeTabItem;
//	private ConstructionTypeTabItem constructionTypeTabItem;
//	private UserService userService;
//	private TemplateService templateService;
//	
//	private ConstructionTemplate selectedConstructionTemplate;
//	private SpaceTypeTemplate selectedSpaceTypeTemplate;
//	private ClimateLocationTemplate selectedClimateTemplate;
//	
//	public static final String PATH_CLIMATE_TEMPLATES = "climateTemplates";
//	public static final String PATH_CONSTRUCTION_TEMPLATES = "constructionTemplates";
//	public static final String PATH_SPACETYPE_TEMPLATES = "spaceTypeTemplates";
//	public static final String PATH_MATERIAL_TEMPLATES = "materialTemplates";
//	
//	private static final Logger LOG = LoggerFactory.getLogger(TemplateViewController.class);
//
//	
//	/**
//	 * The shared instance.
//	 */
//	private static TemplateViewController INSTANCE = new TemplateViewController();
//
//	/**
//	 * Private constructor because of singleton pattern.
//	 */
//	private TemplateViewController() {
//
//	}
//
//	/**
//	 * Retrieves the single instance of TemplateViewController.
//	 * @return The instance of TemplateViewController.
//	 */
//	public static TemplateViewController getInstance() {
//		if (INSTANCE == null)
//			INSTANCE = new TemplateViewController();
//		return INSTANCE;
//	}
//	
//	public List<ClimateLocationTemplate> listClimateTemplates() throws FileNotFoundException, ParsingException {
//		if(climateTemplates == null) {
//			Job job = new Job("Load climate data Job") {
//				  @Override
//				  protected IStatus run(IProgressMonitor monitor) {
//						try {
//							List<ClimateLocationTemplate> templates = templateService.listClimateResources(SessionManagementController.getInstance().getUser().getId());
//							climateTemplates = new ArrayList<ClimateLocationTemplate>();
//							climateTemplates.addAll(templates);
//							Collections.sort(climateTemplates);
//							return Status.OK_STATUS;
//						} catch (Exception e) {
//							LOG.error("{}", e.getMessage(), e);
//						} 
//						return Status.CANCEL_STATUS;
//				  }
//			};
//			// Start the Job
//			job.setPriority(Job.LONG);
//			job.schedule(); 
//		}
//		return climateTemplates;
//	}
//
//
//	public List<ConstructionTemplate> listConstructionTemplates() {
//		if(constructionTemplates == null) {
//			Job job = new Job("Load construction data Job") {
//				  @Override
//				  protected IStatus run(IProgressMonitor monitor) {
//						try {
//							List<ConstructionTemplate> templates = templateService.listConstructionResources(SessionManagementController.getInstance().getUser().getId());
//							constructionTemplates = new ArrayList<ConstructionTemplate>();
//							constructionTemplates.addAll(templates);
//							Collections.sort(constructionTemplates);
//							return Status.OK_STATUS;
//						} catch (Exception e) {
//							LOG.error("{}", e.getMessage(), e);
//						} 
//						return Status.CANCEL_STATUS;
//				  }
//			};
//			// Start the Job
//			job.setPriority(Job.LONG);
//			job.schedule(); 
//		}
//		return constructionTemplates;
//	}
//
//	public List<SpaceTypeTemplate> listSpaceTypeTemplates() {
//		if(spaceTypeTemplates == null) {
//			Job job = new Job("Load occupancy data Job") {
//				  @Override
//				  protected IStatus run(IProgressMonitor monitor) {
//						try {
//							List<SpaceTypeTemplate> templates = templateService.listOccupancyResources(SessionManagementController.getInstance().getUser().getId());
//							spaceTypeTemplates = new ArrayList<SpaceTypeTemplate>();
//							spaceTypeTemplates.addAll(templates);
//							Collections.sort(spaceTypeTemplates);
//							return Status.OK_STATUS;
//						} catch (Exception e) {
//							LOG.error("{}", e.getMessage(), e);
//						} 
//						return Status.CANCEL_STATUS;
//				  }
//			};
//			// Start the Job
//			job.setPriority(Job.LONG);
//			job.schedule(); 
//		}
//		return spaceTypeTemplates;
//	}
//
//	public List<MaterialTemplate> listMaterialTemplates() throws ParsingException, IOException {
//		if(materialTemplates == null) {
//			Job job = new Job("Load material data Job") {
//				  @Override
//				  protected IStatus run(IProgressMonitor monitor) {
//						try {
//							List<MaterialTemplate> templates = templateService.listMaterialTemplates(SessionManagementController.getInstance().getUser().getId());
//							materialTemplates = new ArrayList<MaterialTemplate>();
//							materialTemplates.addAll(templates);
//							Collections.sort(materialTemplates);
//							return Status.OK_STATUS;
//						} catch (Exception e) {
//							LOG.error("{}", e.getMessage(), e);
//						} 
//						return Status.CANCEL_STATUS;
//				  }
//			};
//			// Start the Job
//			job.setPriority(Job.LONG);
//			job.schedule(); 
//		}
//		return materialTemplates;
//	}
//	
//	public ClimateLocationTemplate getSelectedClimateTemplate() {
//		return selectedClimateTemplate;
//	}
//	
//	public ClimateLocationTemplate setSelectedClimateTemperature(int selectionIndex) {
//		ClimateLocationTemplate template = climateTemplates.get(selectionIndex);
//		this.selectedClimateTemplate = template;
//		return template;
//	}
//	
//	public SpaceTypeTemplate getSelectedSpaceTypeTemplate() {
//		return selectedSpaceTypeTemplate;
//	}
//	
//	public void setSelectedSpaceTypeTemplate(int selectionIndex) {
//		if(selectionIndex < 0) {
//			this.selectedSpaceTypeTemplate = null;
//		} else {
//			SpaceTypeTemplate template = spaceTypeTemplates.get(selectionIndex);	
//			this.selectedSpaceTypeTemplate = template;
//		}
//	}
//	
//	public ConstructionTemplate getSelectedConstructionTemplate() {
//		return selectedConstructionTemplate;
//	}
//	
//	public ConstructionTemplate setSelectedConstructionTemplate(int selectionIndex) {
//		if(selectionIndex < 0) {
//			this.selectedConstructionTemplate = null;
//			return null;
//		}
//		ConstructionTemplate template = constructionTemplates.get(selectionIndex);
//		this.selectedConstructionTemplate = template;
//		return template;
//	}
//	
//	public Double calculateUValue(ConstructionTemplateModel ctm) {	
//		try {
//			Collection<MaterialTemplate> materialTemplates 	= listMaterialTemplates();
//			Map<Integer, MaterialLayer> materialLayers 		= ctm.getMaterialLayers();
//			if(materialTemplates != null && materialLayers != null) {
//				Double uValue 								= templateService.calculateUValue(materialLayers, materialTemplates);
//				if(uValue != null) {
//					ctm.setuValue(uValue);
//				}
//			}
//		} catch (ParsingException e) {
//			LOG.warn(e.getMessage());
//		} catch (IOException e) {
//			LOG.warn(e.getMessage());
//		}
//		return null;
//	}
//	
//	public void uploadClimateTemplates(String[] fileNames) throws IOException {
//		Integer userId = SessionManagementController.getInstance().getUser().getId();
//		templateService.uploadClimateTemplates(userId, fileNames);
//	}
//	
//	public boolean uploadConstructionTemplates(String[] fileNames) throws IOException {
//		if(fileNames != null) {
//			Integer userId = SessionManagementController.getInstance().getUser().getId();
//			UserRepository ur = userService.getUserRepositoryByUserId(userId);
//			String urPath = ur.getUserRepositoryPath();
//			File constructionDir = new File(urPath+File.separator+PATH_CONSTRUCTION_TEMPLATES);
//			constructionDir.mkdir();
//	//		upload to user repository
//			boolean success = true;
//			for(String fileName : fileNames) {
//				FileInformation fi = userService.uploadFileToDirectoryInUserRepository(userId, new File(fileName), "./"+PATH_CONSTRUCTION_TEMPLATES);
//				if(fi == null) {
//					success = false;
//				}
//				LOG.debug("Upload of construction template: {} to UserRepository path: {} was: "+(success ? "successfully" : "not successfully"),
//						new Object[]{fileName, urPath});
//			}
//			return success;
//		}
//		return false;
//	}
//	
//	public boolean uploadSpaceTypeTemplates(String[] fileNames) throws IOException {
//		if(fileNames != null) {
//			Integer userId = SessionManagementController.getInstance().getUser().getId();
//			UserRepository ur = userService.getUserRepositoryByUserId(userId);
//			String urPath = ur.getUserRepositoryPath();
//			File spaceTypeDir = new File(urPath+File.separator+PATH_SPACETYPE_TEMPLATES);
//			spaceTypeDir.mkdir();
//	//		upload to user repository
//			boolean success = true;
//			for(String fileName : fileNames) {
//				FileInformation fi = userService.uploadFileToDirectoryInUserRepository(userId, new File(fileName), "./"+PATH_SPACETYPE_TEMPLATES);
//				if(fi == null) {
//					success = false;
//				}
//				LOG.debug("Upload of space type templates: {} to UserRepository path: {} was: "+(success ? "successfully" : "not successfully"),
//						new Object[]{fileName, urPath});
//			}
//			return success;
//		}
//		return false;
//	}
//	
//	public boolean deleteSpaceTypeTemplate(Resource template) {
//		if(template != null) {
//			Integer userId = SessionManagementController.getInstance().getUser().getId();
//			boolean isDeleted = templateService.deleteTemplate(userId, template);
//			if(isDeleted) {
//				this.spaceTypeTemplates.remove(template);
//				Collections.sort(spaceTypeTemplates);
//				return isDeleted;
//			}
//		}
//		return false;
//	}
//	
//	public boolean deleteConstructionTemplate(Resource template) {
//		if(template != null) {
//			Integer userId = SessionManagementController.getInstance().getUser().getId();
//			boolean isDeleted = templateService.deleteTemplate(userId, template);
//			if(isDeleted) {
//				this.constructionTemplates.remove(template);
//				Collections.sort(constructionTemplates);
//				return isDeleted;
//			}
//		}
//		return false;
//	}
//	
//	/**
//	 * Saves the construction template.
//	 * @param template
//	 * @return
//	 * @throws IOException
//	 * @throws ParserConfigurationException
//	 * @throws TransformerException
//	 * @throws ParsingException
//	 */
//	public boolean saveConstructionTemplate(ConstructionTemplate template) throws ParsingException, IOException {
////		check if template is already available
////		if(energySimulationService.checkIfAvailable(template, constructionTemplates)) return false;
//		
//		Integer userId = SessionManagementController.getInstance().getUser().getId();
//		String newFilePath = templateService.saveConstructionTemplate(userId, template);
//		if(newFilePath != null) {
//			this.selectedConstructionTemplate = template;
//			
//			constructionTemplates.add(template);
//			Collections.sort(constructionTemplates);
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	public boolean saveSpaceTypeTemplate(SpaceTypeTemplate template) throws IOException, ParsingException {
////		check if template is already available
////		if(energySimulationService.checkIfAvailable(template, spaceTypeTemplates)) return false;
//		
//		Integer userId = SessionManagementController.getInstance().getUser().getId();
//		boolean saved = templateService.saveSpaceTypeTemplate(userId, template);
//		if(saved) {
//			this.selectedSpaceTypeTemplate = template;
//			
//			spaceTypeTemplates.add(template);
//			Collections.sort(spaceTypeTemplates);
//		}
//		return saved;
//	}
//	
//	public void setClimateTabItem(ClimateTabItem item) {
//		this.climateTabItem = item;
//	}
//
//	public void setEnergySimulationService(
//			EnergySimulationService energySimulationService) {
//		this.energySimulationService = energySimulationService;
//	}
//
//	public EnergySimulationService getEnergySimulationService() {
//		return energySimulationService;
//	}
//
//	public void setSpaceTypeTabItem(SpaceTypeTabItem spaceTypeTabItem) {
//		this.spaceTypeTabItem = spaceTypeTabItem;
//	}
//
//	public void setConstructionTypeTabItem(
//			ConstructionTypeTabItem constructionTypeTabItem) {
//		this.constructionTypeTabItem = constructionTypeTabItem;
//	}
//
//	public void setConfigurationService(ConfigurationService configurationService) {
//		this.configurationService = configurationService;
//	}
//
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}
//	
//	public void setTemplateService(TemplateService templateService) {
//		this.templateService = templateService;
//	}
//
//	public TemplateService getTemplateService() {
//		return templateService;
//	}
//
//}
