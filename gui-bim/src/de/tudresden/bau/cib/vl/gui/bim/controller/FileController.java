package de.tudresden.bau.cib.vl.gui.bim.controller;

import java.io.File;
import java.util.Map;
import java.util.Set;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.service.UserService;
import de.tudresden.bau.cib.vl.gui.bim.view.FileView;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.core.controller.SessionManagementController;

public class FileController<T extends FileView<?>> extends Controller<T> {

	protected FileInformation fileInformation = null;
	


	

	protected File file = null;
	protected String filePath = "";
	
	protected FileService fileService;
	protected UserService userService;
	
	
	public boolean  processFile(String filePath)
	{
			//Datei laden
			loadFile(filePath);
			//Datei evtl. zu User-Repositiory laden
			uploadFile();
			//Datei parsen -> Model
			parseFile();
			//View updaten mit neuem Model	
			view.update();
			
			return true;
	}
	
	
	
	
	protected void loadFile(String filePath)
	{
				
		//Datei laden 		
		file = new File(filePath);				
		this.filePath = filePath; 				
	}
	
	public void loadFile()
	{
		loadFile(filePath);
	}
	
	protected void uploadFile()
	{

		Integer userId = SessionManagementController.getInstance().getUser().getId();
		File userDirectory = new File(userService.getUserRepositoryPath(userId));
		
		LOG.debug("Temp File path: "+file.getAbsolutePath());
		try {
			if(file != null)
				fileInformation = userService.uploadFileToUserRepository(userId, file);				
		} catch (Exception e) {
			LOG.error("Can't copy file '"+file.getAbsolutePath()+"' to "+userDirectory.getAbsolutePath()+"\n"+e.getMessage());
			
		}
		

			
			
		
	}

	public FileInformation getFileInformation() {
		return fileInformation;
	}
	
	public void setFileInformation(FileInformation fileInfo) {
		this.fileInformation = fileInfo;
	}


	protected void parseFile()
	{
		
	}




	@Override
	protected Set<CommunicationEvents> defineReceivedEvents() {
		return null;
	}

	@Override
	protected void handleEvent(CommunicationEvents event, Map<String, Object> dataMap) {
		
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}


}
