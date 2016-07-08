/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * The Class Resource.
 */
public abstract class Resource implements Serializable, Comparable<Resource> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1748034040681495654L;

	/** The name. */
	protected String name;
	
	/** The id. */
	protected String id;
	
	/** The source file URI. */
	protected String sourceFileUri;
	

	/**
	 * Gets the source file path.
	 *
	 * @return Returns the file path which was used to fill/generate the element.
	 * In the case of a resource element it will return the XML file which was used.
	 */
	public String getSourceFileUri() {
		return sourceFileUri;
	}
	
	/**
	 * Sets the source file path.
	 *
	 * @param sourceFileUri the new source file path
	 */
	public void setSourceFileUri(String sourceFileUri) {
		this.sourceFileUri = sourceFileUri.replaceAll("\\\\", "/");
//		this.sourceFileUri = sourceFileUri;
		if(id == null) {
			// we take the file path as unique identifier for the template
			String uuid = UUID.nameUUIDFromBytes(this.sourceFileUri.getBytes()).toString();
			setId(uuid);
		}
	}
	
	/**
	 * Gets the id.
	 *
	 * @return The model identifier.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Resource compareResource) {
		String compareName = ((Resource) compareResource).getName(); 
		if(name == null || compareName == null) return 0;
		return this.name.compareTo(compareName); 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int prime = 48;
		int hash = super.hashCode();
		if(name != null) {			
			hash = hash + (prime * name.hashCode());			
		}
		if(id != null) {
			hash = hash + (prime * id.hashCode());
		}
		return hash;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Resource) {
			Resource other = (Resource) obj;
			return this.hashCode() == other.hashCode() && this.id.equals(other.id);
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "name: "+name+", file URI: "+sourceFileUri;
	}

}
