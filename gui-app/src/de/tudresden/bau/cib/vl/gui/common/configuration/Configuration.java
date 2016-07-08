package de.tudresden.bau.cib.vl.gui.common.configuration;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.configuration.PropertiesConfiguration;

import de.tudresden.bau.cib.vl.gui.common.exception.ConfigurationException;



public class Configuration {
	
	/**
	  * Constants
	  */
	public static final String USER_SESSION = "userSession";
	public static final String USER_ID = "userId";
	
	

	private static PropertiesConfiguration config; 
	
	/**
	 * The shared instance.
	 */
	private static Configuration INSTANCE = null;

	/**
	 * Private constructor because of singleton pattern.
	 */
	private Configuration() {
		try {
			loadPropertiesFromClassPath("config");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the single instance of Configuration.
	 * @return The instance of Configuration.
	 */
	public static Configuration getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Configuration();
		return INSTANCE;
	}

	/**
	 * Search for all .properties files and loads their content.
	 * @param filePath
	 * @throws ConfigurationException 
	 * @throws URISyntaxException 
	 */
	private PropertiesConfiguration loadProperties(String directoryPath) throws ConfigurationException {
		config = new PropertiesConfiguration();	
		try {
			File directory = new File(directoryPath);
			File[] propertyFiles = directory.listFiles();
			for(File propertyFile : propertyFiles){
				if(propertyFile.getName().equalsIgnoreCase(".svn")) continue; // if somebody is confused and checked it in the SVN
				PropertiesConfiguration c = new PropertiesConfiguration(propertyFile);
				config.append(c);
			}
			return config;
		} catch (org.apache.commons.configuration.ConfigurationException e) {
			throw new ConfigurationException(e);
		}
	}
	
	/**
	 * Search for all .properties files and loads their content.
	 * @param filePath
	 * @throws ConfigurationException 
	 * @throws URISyntaxException 
	 */
	private PropertiesConfiguration loadPropertiesFromClassPath(String directoryPath) throws ConfigurationException {
		config = new PropertiesConfiguration();	
		try {
			URL url = Configuration.class.getClassLoader().getResource(directoryPath);
			File directory = new File(url.toURI());
			File[] propertyFiles = directory.listFiles();
			for(File propertyFile : propertyFiles){
				PropertiesConfiguration c = new PropertiesConfiguration(propertyFile);
				config.append(c);
			}
			return config;
		} catch (URISyntaxException uri) {
			throw new ConfigurationException(uri);
		} catch (org.apache.commons.configuration.ConfigurationException e) {
			throw new ConfigurationException(e);
		}
	}
	
	public String getProperty(String propertyName) {
		return (String) config.getProperty(propertyName);
	}
}
