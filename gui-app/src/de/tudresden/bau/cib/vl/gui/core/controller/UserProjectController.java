package de.tudresden.bau.cib.vl.gui.core.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.User;
import de.tudresden.bau.cib.vl.core.database.domain.UserRepository;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.bim.service.BimFitService;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.service.UserService;
import de.tudresden.bau.cib.vl.core.util.VirtualFile;
import de.tudresden.bau.cib.vl.gui.Activator;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.common.controller.AbstractViewController;
import de.tudresden.bau.cib.vl.gui.core.messages.Messages;
import de.tudresden.bau.cib.vl.gui.core.model.ModelProject;
import de.tudresden.bau.cib.vl.gui.core.model.UserProjectModel;
import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNode;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNodeDirectory;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNodeFile;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNodeProject;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNodeSimulationProject;
import de.tudresden.bau.cib.vl.gui.core.view.UserProjectView;


public class UserProjectController extends AbstractViewController<UserProjectView> {
	
	public static final String ICONS_PATH = "resources"+File.separator+"icons"+File.separator;
	public static final String ICONS_22x22_PATH = ICONS_PATH+"22x22"+File.separator;
	public static final String ICONS_32x32_PATH = ICONS_PATH+"32x32"+File.separator;
	
	private BimFitService modelService;
	private FileService fileService;
	private OntologyService ontologyService;
	private UserService userService;
	private ConfigurationService configurationService;
	
	
	private OntologyModel ontologyModel;


	private Project selectedProject;
	private FileInformation selectedFile;
	private SimulationProject selectedSimulationProj;
	private TreeNode selectedTreeNode;

	private UserRepository model;
	private User currentUser;
	


	public UserRepository getModel() {
		return model;
	}

	public void setModel(UserRepository model) {
		this.model = model;
	}


	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}


	private static final Logger LOG = LoggerFactory.getLogger(UserProjectController.class);
	
	
	public Set<VirtualFile> getFilesOfCurrentUser(String fileFormat, boolean recursively) throws MalformedURLException {
		Integer userId = currentUser.getId();
//		HttpSession session = RWT.getSessionStore().getHttpSession();
//		Integer userId = (Integer) session.getAttribute(Configuration.USER_ID);
		Set<VirtualFile> files = userService.getFilesOfUser(userId, fileFormat, recursively);
		return files;
	}
	
	public Set<VirtualFile> getFilesOfCurrentUser(String[] fileFormats, boolean recursively) throws MalformedURLException {
		Integer userId = currentUser.getId();
//		HttpSession session = RWT.getSessionStore().getHttpSession();
//		Integer userId = (Integer) session.getAttribute(Configuration.USER_ID);
		
		Set<VirtualFile> files = new HashSet<VirtualFile>();
		
		for(String fileFormat : fileFormats)
		{
			files.addAll(userService.getFilesOfUser(userId, fileFormat, recursively));
		}
		
		return files;
	}
	
	public void uploadFile(String filePath) {
		if(StringUtils.isEmpty(filePath)) return;
		FileInformation info = null;


		Integer userId = currentUser.getId();
		//File userDirectory = new File(userService.getUserRepositoryPath(userId));
		//File userDirectory = new File(model.getUserRepositoryPath());
		LOG.debug("Temp File path: "+filePath);
		try {
			File tempFile = new File(filePath);
			if(selectedProject != null)
				info = userService.uploadFileToProject(userId, tempFile, selectedProject);		
			else
				info = userService.uploadFileToUserRepository(userId, tempFile);	


			switch(info.getFileType())
			{
			case IFC:  publishUploadedIfcFile(info); break;
			case X3D:  publishUploadedX3dFile(info); break;
			default: break;
			}					


		} catch (Exception e) {
			LOG.error("Can't copy file '"+filePath+"' to "+model.getUserRepositoryPath()+"\n"+e.getMessage());
		}				

		view.update();
	}
	
	public void publishUploadedX3dFile(FileInformation file) {
		sendSync(CommunicationEvents.X3D_FILE, file);
		
	}

	public UserProjectView getFileView() {
		return view;
	}
	
	public void setSelectedFile(FileInformation file)
	{
		selectedFile = file;
	}
	
	public void publishUploadedIfcFile(FileInformation file)
	{
		sendSync(CommunicationEvents.IFC_FILE, file);
	}
	
	public void publishSelectedProject() {
		sendSync(CommunicationEvents.PROJECT_SELECTED, selectedProject);
	}

	public void publishSelectedFile() throws ParsingException, MalformedURLException, IOException {
		FileInformation vFile = selectedFile;
		URL fileUrl = vFile.getUrl();
		LOG.trace("Set new file "+fileUrl +" now publishing...");		
		
		if(vFile.getFileType() == FileInformation.TYPE.IFC)
		{	
			handleIfcFile(vFile);		
		}
		else if( vFile.getFileType() == FileInformation.TYPE.X3D)
		{
			
			handleX3dFile(vFile);
			
			
			
		}
		else if( vFile.getFileType() == FileInformation.TYPE.RDF)
		{
			
			handleRdfFile(vFile);
			
	
			
		}
	}
	
	public void publishCheckOfSimulations() {
		sendSync(CommunicationEvents.CHECK_SIMULATION_STATUS, currentUser);
	}
	
	private void handleX3dFile(final FileInformation vFile) {
		Job job = new Job("X3D parsing job") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				try {
					//TODO remove
//					sendSync(CommunicationEvents.SERVER_NEW_FILE_X3D, vFile);
					sendSync(CommunicationEvents.X3D_FILE, vFile);
					
					return Status.OK_STATUS;
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
				return Status.CANCEL_STATUS; 
			}
		};

		// Start the Job
		job.schedule(); 
		
	}

	private void handleRdfFile(final FileInformation vFile) {
		Job job = new Job("RDF parsing job") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				try {
					
					//TODO remove, not necessary anymore
					ontologyModel = ontologyService.loadOntologyModel(vFile.getFilePath());
					
					sendSync(CommunicationEvents.ONTOLOGY_MODEL, ontologyModel);
					
					return Status.OK_STATUS;
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
				return Status.CANCEL_STATUS; 
			}
		};

		// Start the Job
		job.schedule(); 
		
	}

	private void handleIfcFile(final FileInformation vFile) {
		Job job = new Job("IFC parsing job") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Model model;
				try {
					
					//TODO remove, not necessary any more
					model = modelService.parseIfc2x3File(vFile.getUrl());
					model.setSourceFilePath(vFile.getFilePath());
					
					sendSync(CommunicationEvents.IFC_FILE, vFile);	
					
					
					return Status.OK_STATUS;
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
				return Status.CANCEL_STATUS; 
			}
		};

		// Start the Job
		job.schedule(); 

	}

	public void setSelection(String guid) {
//		String sessionId = RWT.getSessionStore().getId();
//		sendSync(CommunicationEvents.CLIENT_NEW_SELECTION, new String []{guid});
	}
	
	public Set<Project> getProjectsOfCurrentUser()
	{
		Integer userId = currentUser.getId();
		Set<Project> projects = userService.getProjectsOfUser(userId);
		return projects;
		
	}
	
	public Set<VirtualFile> getFilesOfProject(Project project)
	{
		
		Set<VirtualFile> ret = null;
		
		try {
			 ret = fileService.getVirtualFiles(new File(project.getPathDirectory()),false);
		} catch (MalformedURLException e) {
			LOG.error("{}", e.getMessage(), e);
		}
		
		return ret;
	}
	
	
	public Set<SimulationProject> getSimulationProjectsOfProject(Project project)
	{
		Set<SimulationProject> ret = null;
		
		return ret;
	}
	
	public void createProject(String projectName)
	{	
		userService.createProject(projectName, model);
	}
	
	public void createSimulationProject( String simProjectName)
	{
		if(selectedProject == null)
			return;
		
		userService.createSimulationProject(selectedProject, simProjectName);		
	}
	
	
//	public TreeDirectory createModel()
//	{
//		TreeDirectory root = new TreeDirectory(Messages.get().PROJECTS);
//		TreeDirectory invisibleRoot = new TreeDirectory("");
//		invisibleRoot.addChild(root);	
//		
//		
//		//add projects of current user
//		List<Project> projects = getProjectsOfCurrentUser();
//		for(Project project : projects)
//		{
//			TreeDirectory treeProject = new TreeDirectory(project.getName());
//			root.addChild(treeProject);
//			
//			
//			//add files of the current project
//			Set<VirtualFile> allFiles =  getFilesOfCurrentProject(project);							
//			if(allFiles != null) {
//				for(VirtualFile file : allFiles) {
//					String name = file.getName();
//					TreeFile tf = new TreeFile(name);
//					tf.setTreeFile(file);
//					treeProject.addChild(tf);	
//				}
//
//			}					
//			
//		}
//		
//		return invisibleRoot;
//		
//		
//	}
	
	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
//		publishSelectedProject(selectedProject);
	}

	public SimulationProject getSelectedSimulationProj() {
		return selectedSimulationProj;
	}

	public void setSelectedSimulationProj(SimulationProject selectedSimualtionProj) {
		this.selectedSimulationProj = selectedSimualtionProj;
	}

	public FileInformation getSelectedFile() {
		return selectedFile;
	}

	public UserProjectModel createModel()
	{
		UserProjectModel model = new UserProjectModel();
		
		
		TreeNodeDirectory root = new TreeNodeDirectory(Messages.PROJECTS);
		TreeNodeDirectory invisibleRoot = new TreeNodeDirectory("");
		invisibleRoot.addChild(root);	
		
		
		//add projects of current user
		Set<Project> projects = getProjectsOfCurrentUser();
		for(Project project : projects)
		{
			ModelProject modelProj = new ModelProject(project.getName());
			
			Set<VirtualFile> allFiles =  getFilesOfProject(project);	
			if(allFiles != null)
				modelProj.setFiles(allFiles);
			
			
			
						
			
			model.addProject(modelProj);
			
		}
		
		return model;
		
		
	}
	
	public void clearSelectedElements()
	{
		selectedProject = null;
		selectedFile = null;
		selectedSimulationProj = null;
	}

	public void deleteSelectedProject() {
		if(selectedProject == null) {
			return;		
		}
		// defensive copy because of ConcurrentModificationException problem
		Set<SimulationProject> simProjects = new HashSet<SimulationProject>(selectedProject.getSimProjects());
		
		for(SimulationProject simProj : simProjects){
			userService.deleteSimulationProject(simProj);
		}			
		
		userService.deleteProject(selectedProject);		
	}
	
	public void deleteSelectedSimulationProject(){
		if(selectedSimulationProj == null)
			return;		
		//Integer userId = currentUser.getId();
		userService.deleteSimulationProject( selectedSimulationProj);	
		Project parentProject = selectedSimulationProj.getProject();
		if(parentProject.getId() != selectedProject.getId()) {
			throw new IllegalArgumentException("The selected project and the parent project of simulation project: "+selectedSimulationProj+" are not equal!");
		} else {
			// update the project
			selectedProject = parentProject;
		}
	}

	public void deleteSelectedFile()
	{
		if(selectedProject == null || selectedFile == null)
			return;

		selectedProject.removeFile(selectedFile);		
		
		
		userService.updateProject(selectedProject);		
		userService.deleteFile(selectedFile);
		
	}

	@Override
	protected Set<CommunicationEvents> defineReceivedEvents() {
		Set<CommunicationEvents> events = new HashSet<CommunicationEvents>();
		events.add(CommunicationEvents.USER_LOGIN);
		
		events.add(CommunicationEvents.USERREPOSITORY_UPDATED);
		events.add(CommunicationEvents.SIMULATION_STATUS);
		
		return events;
	}

	@Override
	protected BundleContext getBundleContext() {
		return Activator.getDefault().getContext();
	}

	@Override
	protected void handleEvent(CommunicationEvents event, Map<String,Object> dataMap) {
		LOG.debug("Receiving event: {} with data: {}", new Object[]{event.getName(), dataMap});
		Object data = dataMap.get(CommunicationService.PROPERTIES_KEY_DATA);
		switch(event){
		case USER_LOGIN:
			currentUser = (User)data;
			if(currentUser != null) {
				model = userService.getUserRepositoryByUserId(currentUser.getId());
				if(view != null) view.setInput(model);
			}
			break;
		case USERREPOSITORY_UPDATED:
			Integer userId = currentUser.getId();	
			model = userService.getUserRepositoryByUserId(userId);
			if(view != null) view.setInput(model);
			break;	
		case SIMULATION_STATUS:
			model = userService.getUserRepositoryByUserId(currentUser.getId());
			if(view != null) view.setInput(model);
			break;
			
		default: break;
		}
	}

	public void setOntologyService(OntologyService ontologyService) {
		this.ontologyService = ontologyService;
	}

	public void publishSelectedSimulationProject() {
		
		sendSync(CommunicationEvents.SIMULATION_PROJECT_SELECTED, selectedSimulationProj);
		
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public void setBimFitService(BimFitService modelService) {
		this.modelService = modelService;
	}

	public void download()
	{
		
		
	}

	public void setSelectedNode(TreeNode treeNode) {
		selectedTreeNode = treeNode;
		
	}
	
	public String getDownloadURL()
	{
		String ret = "";
		
		if(selectedTreeNode == null)
			return ret;
		
		if(selectedTreeNode instanceof TreeNodeFile)
		{
			ret = ((TreeNodeFile) selectedTreeNode).getFile().getUrl().toString();
		}
		else if(selectedTreeNode instanceof TreeNodeProject)
		{
			try 
			{			
				Project proj = ((TreeNodeProject) selectedTreeNode).getProject();
				
				String outPutPath = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY)+proj.getName()+".zip";
				
				FileService.zipDir(new File (proj.getPathDirectory()), outPutPath);			
				
				
				ret = fileService.getVirtualFile(new File(outPutPath)).getUrl().toString();
				
				
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
			
			
			
		}
		else if(selectedTreeNode instanceof TreeNodeSimulationProject)
		{
			try 
			{			
				SimulationProject proj = ((TreeNodeSimulationProject) selectedTreeNode).getSimulationProject();
				
				String outPutPath = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY)+proj.getName()+".zip";
				
				FileService.zipDir(new File (proj.getPathDirectory()), outPutPath);		
				
				ret = fileService.getVirtualFile(new File(outPutPath)).getUrl().toString();
				
				
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}
		
		return ret;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
}
