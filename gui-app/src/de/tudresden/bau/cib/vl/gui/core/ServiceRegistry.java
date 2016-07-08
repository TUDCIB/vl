package de.tudresden.bau.cib.vl.gui.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.sparql.function.library.wait;

import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.service.UserService;

/**
 * This class should be used if the Web application does not support Spring and 
 * some available core services are needed.
 * <br/>
 * <p>Note: This is a registry for available services and hence the Facade Design Pattern is used </p>
 * 
 * @author Ken
 *
 */
public class ServiceRegistry {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(ServiceRegistry.class);
	
	/**
	 * The shared instance.
	 */
	private static ServiceRegistry INSTANCE = null;

	/**
	 * Default constructor because of singleton pattern.
	 */
	ServiceRegistry() {
		INSTANCE = this;
	}

	/**
	 * Retrieves the single instance of ServiceRegistry.
	 * @return The instance of ServiceRegistry.
	 */
	public static ServiceRegistry getInstance() {
		waitForVirtualLaboratoryCore();
		return INSTANCE;
	}
	
	/**
	 * Waits for the backend because the frontend starts before the it which leads to errors.
	 */
	private static void waitForVirtualLaboratoryCore() {
//		Sind alle wichtigen Services verfügbar?
		while(userService == null 
				|| fileService == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static UserService userService;
	
	private static FileService fileService;
	

	public UserService getUserService() {
		return userService;
	}
	
	public FileService getFileService() {
		return fileService;
	}

	public void setUserService(UserService userService) {
		LOG.trace("Setting UserService: "+userService);
		this.userService = userService;
	}

	public void setFileService(FileService fileService) {
		LOG.trace("Setting FileService ... "+fileService);
		this.fileService = fileService;
	}
}
