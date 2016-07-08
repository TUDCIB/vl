package de.tudresden.bau.cib.vl.core.model.mapping;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public final class Mapper {

	private PropertiesConfiguration config;
	
	/**
	 * Loads a mapping file which is a text file with key-value pairs.
	 * @param filePath The path to the file.
	 * @return The corresponding Apache configuration if needed.
	 * @throws ConfigurationException Underlying Apache exception.
	 * @throws FileNotFoundException If no mapping file is on the file path.
	 */
	public PropertiesConfiguration loadMapping(String filePath) throws ConfigurationException, FileNotFoundException {
		config = new PropertiesConfiguration();	
		File propertyFile = new File(filePath);
		if(!propertyFile.exists()) throw new FileNotFoundException("There is no file: "+filePath);
		PropertiesConfiguration c = new PropertiesConfiguration(propertyFile);
		config.append(c);		
		return config;
	}
	
	public String getProperty(String propertyName) {
		if(config == null) throw new NullPointerException("Please load the mapping file");
		return (String) config.getProperty(propertyName);
	}
}
