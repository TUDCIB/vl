package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


public class ResourceDescriptor implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8021860639010575230L;
	
	private long id;
	private String resourceName = null;
	private String resourceShortName = null;
	private String resourceDescription = null;
	private String resourceClassName = null;
	private String userName = null;
	private String userPassword = null;
	private String domain = null;
	private String authenticationType = null;
	private String handlerChainConfiguration = null;
	private Timestamp created;
	private Timestamp lastModified;

	private Map<String, String> properties;
	
	Properties resourceProperties = new Properties();
	
	public ResourceDescriptor() {
	
	}
	
    public Map<String, String> getProperties() {
        if (properties == null) {
            properties = new HashMap<String, String>();
        }
        return properties;
    }
    
    public String getPropertiesAsString() {
    	String propertiesString = null;
        if (properties != null) {
			if (properties.size() > 0) {
				StringBuffer propertiesSB = new StringBuffer();
				for (Iterator iter = properties.entrySet().iterator(); iter.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					String key = (String) entry.getKey();
					String value = (String) entry.getValue();
					propertiesSB.append(key).append('=').append(value);
					if (iter.hasNext()) {
						propertiesSB.append(',');
					}
				}
				propertiesString = propertiesSB.toString();
			}
 
        }
        return propertiesString;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;  
     }
    public void setPropertiesAsString(String propertiesString) {
    	
        if (propertiesString == null) throw new NullPointerException("propertiesString is null");

        String[] entries = propertiesString.split(",");
        Map<String, String>  properties = this.getProperties();
        properties.clear();
        
        for (int i = 0; i < entries.length; i++) {
            String entry = entries[i];
            if (entry.length() > 0) {
                int index = entry.indexOf('=');
                if (index > 0) {
                    String name = entry.substring(0, index);
                    String value = entry.substring(index + 1);
                    properties.put(name, value);
                } else {
                    // no value is empty string which is how java.util.Properties works
                    properties.put(entry, "");
                }
            }
        }
     }

	public String getProperty(String key){
		return (String)getProperties().get(key);
	}
	public void setProperty(String key, String value){
		getProperties().put(key, value);
	}

 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAuthenticationType() {
		return authenticationType;
	}
	public void setAuthenticationType(String authenticationType) {
		if(authenticationType != null){
			if(authenticationType.equalsIgnoreCase("Basic")||authenticationType.equalsIgnoreCase("NTLM")|| authenticationType.equalsIgnoreCase("Digest") ){
				this.authenticationType = authenticationType;
			} else {
				this.authenticationType = "Basic";// default to basic //should throw exception
			}
		}
	}
	public String getResourceClassName() {
		return resourceClassName;
	}

	public void setResourceClassName(String resourceClassName) {
		this.resourceClassName = resourceClassName;
	}
	
	public void setHandlerChainConfiguration(String handlerChainConfiguration) {
		this.handlerChainConfiguration = handlerChainConfiguration;
	}
	
    public String getHandlerChainConfiguration() {
		return handlerChainConfiguration;
	}
    
	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}
  
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public String getResourceShortName() {
		return resourceShortName;
	}
	public void setResourceShortName(String resourceShortName) {
		this.resourceShortName = resourceShortName;
	}
	
	public String getResourceDescription() {
		return resourceDescription;
	}
	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	

	public Properties getResourceProperties() {
		return resourceProperties;
	}

	public void setResourceProperties(Properties resourceProperties) {
		this.resourceProperties = resourceProperties;
	}


	@Override
	public String toString() {
		return "ResourceDescriptor [id=" + id
				+", resourceName=" + resourceName
				+ ", resourceShortName=" + resourceShortName 
				+ ", resourceClassName=" + resourceClassName + ", userName="
				+ userName + ", userPassword=" + userPassword
				+ ", resourceProperties=" + resourceProperties + "]";
	}

			
 }
