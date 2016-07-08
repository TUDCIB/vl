//package de.tudresden.bau.cib.vl.gui.core.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import org.eclipse.rap.rwt.SingletonUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.tudresden.bau.cib.vl.core.exception.ParsingException;
//import de.tudresden.bau.cib.vl.core.model.Model;
//import de.tudresden.bau.cib.vl.core.platform.exception.ManagementException;
//import de.tudresden.bau.cib.vl.core.service.ModelService;
//import de.tudresden.bau.cib.vl.core.service.UserService;
//import de.tudresden.bau.cib.vl.core.util.VirtualFile;
//import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
//import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;
//import de.tudresden.bau.cib.vl.gui.core.view.FileView;
//
//
//public class FileController {
//
//	private FileView fileView;
//	private UserService userService;
//	private ModelService modelService;
//	private CommunicationService communicationService;
//	
//	
//	private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
//	
//	
//	private FileController() {
//
//	}
//	
//	public static FileController getInstance() {
//		return SingletonUtil.getSessionInstance(FileController.class);
//	}
//	
//	public Set<VirtualFile> getFilesOfCurrentUser(String fileFormat, boolean recursively) throws MalformedURLException {
//		Integer userId = SessionManagementController.getInstance().getUser().getId();
////		HttpSession session = RWT.getSessionStore().getHttpSession();
////		Integer userId = (Integer) session.getAttribute(Configuration.USER_ID);
//		Set<VirtualFile> files = userService.getFilesOfUser(userId, fileFormat, recursively);
//		return files;
//	}
//	
//	public Set<VirtualFile> getFilesOfCurrentUser(String[] fileFormats, boolean recursively) throws MalformedURLException {
//		Integer userId = SessionManagementController.getInstance().getUser().getId();
////		HttpSession session = RWT.getSessionStore().getHttpSession();
////		Integer userId = (Integer) session.getAttribute(Configuration.USER_ID);
//		
//		Set<VirtualFile> files = new HashSet<VirtualFile>();
//		
//		for(String fileFormat : fileFormats)
//		{
//			files.addAll(userService.getFilesOfUser(userId, fileFormat, recursively));
//		}
//		
//		return files;
//	}
//	
//	public boolean uploadFiles(String[] temporaryFilePaths) {
////		HttpSession session = RWT.getSessionStore().getHttpSession();
////		Integer userId = (Integer) session.getAttribute(Configuration.USER_ID);
//		Integer userId = SessionManagementController.getInstance().getUser().getId();
//		File userDirectory = new File(userService.getUserRepositoryPath(userId));
//		for(String tempFilePath : temporaryFilePaths) {
//			LOG.debug("Temp File path: "+tempFilePath);
//			try {
//				File tempFile = new File(tempFilePath);
//				userService.uploadFileToUserRepository(userId, tempFile);				
//			} catch (Exception e) {
//				LOG.error("Can't copy file '"+tempFilePath+"' to "+userDirectory.getAbsolutePath()+"\n"+e.getMessage());
//				continue;
//			}
//			updateView();
//			return true;
//		}
//		
//		return false;
//	}
//	
//	public void updateView() {
//		fileView.updateTreeViewer();
//	}
//
//	public FileView getFileView() {
//		return fileView;
//	}
//
//	public void setFileView(FileView fileView) {
//		this.fileView = fileView;
//	}
//
//	public void setSelectedFile(VirtualFile vFile) throws ParsingException, MalformedURLException, IOException, ManagementException {
//		String fileUrl = vFile.getUrl();
//		LOG.trace("Set new file "+fileUrl +" now publishing...");
////		String sessionId = RWT.getSessionStore().getId();
////		HttpSession session = RWT.getSessionStore().getHttpSession();
////		Object userId = session.getAttribute(Configuration.USER_ID);
//		
//		
//		
//		//TODO
//		//VFile um einen Typ erweitern
//		String [] help = vFile.getAbsolutePath().split("\\.");
//		String ext="";
//		if(help.length == 2)
//			ext = help[1];
//		
//		if(ext.isEmpty() || ext.compareTo("ifc")==0 )
//		{	
//			Model model = modelService.parseFileURL(new URL(vFile.getUrl()));
//			
//			model.setSourceFilePath(vFile.getAbsolutePath());
//			
//			Map<String, Model> data = new HashMap<String, Model>();
//			data.put(CommunicationService.PROPERTIES_KEY_DATA, model);
//			communicationService.sendAsync(CommunicationEvents.SERVER_NEW_IFCMODEL, data);
//			Map<String, String> data2 = new HashMap<String, String>();
//			data2.put(CommunicationService.PROPERTIES_KEY_DATA, fileUrl);
//			communicationService.sendAsync(CommunicationEvents.CLIENT_NEW_FILE, data2);
//			Map<String, String> data3 = new HashMap<String, String>();
//			data3.put(CommunicationService.PROPERTIES_KEY_DATA, vFile.getAbsolutePath());
//			communicationService.sendAsync(CommunicationEvents.SERVER_NEW_FILE, data3);
//		}
//		else if( ext.compareTo("x3d")==0)
//		{
//			
//			Map<String, VirtualFile> data2 = new HashMap<String, VirtualFile>();
//			data2.put(CommunicationService.PROPERTIES_KEY_DATA, vFile);
//			communicationService.sendAsync(CommunicationEvents.SERVER_NEW_FILE_X3D, data2);
//			
//		}
//	}
//
//	public void setSelection(String guid) {
////		String sessionId = RWT.getSessionStore().getId();
//		Map<String, String[]> data = new HashMap<String, String[]>();
//		data.put(CommunicationService.PROPERTIES_KEY_DATA, new String []{guid});
//		communicationService.sendAsync(CommunicationEvents.CLIENT_NEW_SELECTION, data);
//	}
//
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}
//
//	public void setModelService(ModelService modelService) {
//		this.modelService = modelService;
//	}
//
//	public void setCommunicationService(CommunicationService communicationService) {
//		this.communicationService = communicationService;
//	}
//	
//	public UserService getUserService() {
//		return userService;
//	}
//
//	public ModelService getModelService() {
//		return modelService;
//	}
//
//	public CommunicationService getCommunicationService() {
//		return communicationService;
//	}
//	
//}
