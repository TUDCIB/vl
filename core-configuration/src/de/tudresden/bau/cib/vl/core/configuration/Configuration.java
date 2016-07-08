package de.tudresden.bau.cib.vl.core.configuration;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.service.ConfigurationService;

public class Configuration implements ConfigurationService {	
	
	private PropertiesConfiguration config; 
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);
	
	private static final String PLACEHOLDER_START_DELIMITER = "${";
	private static final String PLACEHOLDER_END_DELIMITER = "}";
	
	
	/**
	 * Search for all .properties files and loads their content.
	 * @param filePath
	 * @throws ConfigurationException 
	 * @throws URISyntaxException 
	 */
	public PropertiesConfiguration loadProperties(String directoryPath) throws ConfigurationException {
		config = new PropertiesConfiguration();	
		File directory = new File(directoryPath);
		File[] propertyFiles = directory.listFiles();
		for(File propertyFile : propertyFiles){
			if(propertyFile.getName().equalsIgnoreCase(".svn")) continue; // if somebody is confused and checked it in the SVN
			PropertiesConfiguration c = new PropertiesConfiguration(propertyFile);
			config.append(c);
		}
		
		substitutePropertiesPlaceholder();
		
		return config;
	}

	
	/**
	 * Search for all .properties files and loads their content.
	 * @param filePath
	 * @throws ConfigurationException 
	 * @throws URISyntaxException 
	 */
	public PropertiesConfiguration loadPropertiesFromClassPath(String directoryPath) throws ConfigurationException, URISyntaxException {
		config = new PropertiesConfiguration();	
		URL url = Configuration.class.getClassLoader().getResource(directoryPath);
		File directory = new File(url.toURI());
		File[] propertyFiles = directory.listFiles();
		for(File propertyFile : propertyFiles){
			PropertiesConfiguration c = new PropertiesConfiguration(propertyFile);
			config.append(c);
		}
		
		substitutePropertiesPlaceholder();
		
		return config;
	}
	
	@Override
	public String getProperty(String propertyName) {
		return (String) config.getProperty(propertyName);
	}
	
	@Override
	public String[] getPropertyArray(PROPERTIES property) {
		return config.getStringArray(property.name());
	}
	
	@Override
	public String getBaseURL() {
		return getProperty(PROPERTIES.URL_VIRTUAL_LABORATORY.toString());
	}
	
	@Override
	public String getProperty(PROPERTIES property) {
		return (String) config.getProperty(property.name());
	}
	
	@Override
	public Map<String, Object> getAllProperties() {
		Map<String,Object> properties = new HashMap<String,Object>();
		Iterator<String> iterator = config.getKeys();
		while(iterator.hasNext()) {
			String key = iterator.next();
			Object prop = config.getProperty(key);
			properties.put(key, prop);
		}
		return properties;
	}

	/**
	 * This will call automatically {@link Configuration#loadProperties(String)}.
	 * 
	 * @param configDirectoryPath
	 * @throws ConfigurationException
	 */
	public void setConfigDirectoryPath(String configDirectoryPath) throws ConfigurationException {
		LOGGER.trace("...setting Configuration path: "+configDirectoryPath);
		loadProperties(configDirectoryPath);
	}
	
	/**
	 * Substitutes all placeholders ( ${placeholder} ) in property values with value of placeholder
	 * Is called in {@link Configuration#loadProperties(String)} and {@link Configuration#loadPropertiesFromClassPath(String)}
	 * 
	 * @throws ConfigurationException 
	 */
	private void substitutePropertiesPlaceholder() throws ConfigurationException
	{
		String key;
		Object obj;
		String value;
		String newValue;
		
		for(Iterator<String> it = config.getKeys(); it.hasNext(); )
		{
			
			key = it.next();
			obj = config.getProperty(key);			
			
			// only string representations
			if(obj instanceof String )
			{	
				value = (String) obj;
				
				if(hasPlaceHolder(value))
				{
					newValue = substitutePropertyPlaceholder(value);					
					config.setProperty(key, newValue);	
				}
			}
		}		
	}

	/**
	 * Substitutes placeholder in single property value by property value of placeholder.
	 * If property value contains no placeholder then given property value is returned.
	 * If property value of placeholder contains a placeholder again, then this method is called recursive.
	 * 
	 * @param value	value of a property
	 * @return new property value with substituted placeholder
	 * @throws ConfigurationException
	 */
	private String substitutePropertyPlaceholder(String value) throws ConfigurationException
	{		
		String placeHolder;
		String placeHolderContent;
		String newValue="";
		int startIdx;
		int endIdx;				
	
		if(	(startIdx = value.indexOf(PLACEHOLDER_START_DELIMITER)) >=0         
			&& 	(endIdx = value.indexOf(PLACEHOLDER_END_DELIMITER)) > 0 )
		{
			
			placeHolder = 	value.substring(startIdx, endIdx+1);
			placeHolderContent = placeHolder.substring(PLACEHOLDER_START_DELIMITER.length(), 
													   placeHolder.length()-PLACEHOLDER_END_DELIMITER.length());
			
			newValue = (String) config.getProperty(placeHolderContent);
			
			if(newValue == null)
			{
				String msg = "Used placeholder variable '"+ placeHolderContent + "' doesn't exist";
				throw new ConfigurationException(msg);
			}
			
			//check if newValue contains a placeholder as well
			if(hasPlaceHolder(newValue))
			{
				newValue = substitutePropertyPlaceholder(newValue);
				
			}					
			
			return value.replace(placeHolder, newValue);
			
		}
		else
		{
			return value;
			
		}
		
		
		
	}

	/**
	 * Checks if given property value contains placeholder
	 * 
	 * @param value
	 * @return True, if placeholder was detected. False, if not	
	 *  		   
	 */
	private boolean hasPlaceHolder(String value)
	{
		return (value.indexOf(PLACEHOLDER_START_DELIMITER) >=0         
				&&  value.indexOf(PLACEHOLDER_END_DELIMITER) > 0);
		
	}

}
