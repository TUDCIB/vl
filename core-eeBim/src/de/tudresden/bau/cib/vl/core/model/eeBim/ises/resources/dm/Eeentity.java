package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;

/**
 * Eeentity entity. @author MyEclipse Persistence Tools
 */

public class Eeentity extends ClassifiableAnnotable implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5579175518162862753L;
	private String entityClass;
	private String entityUri;

	// Constructors

	/** default constructor */
	public Eeentity() {
	}

	/** full constructor */
	public Eeentity(String entityClass, String entityUri) {
		this.entityClass = entityClass;
		this.entityUri = entityUri;
	}

	// Property accessors


	public String getEntityClass() {
		return this.entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public String getEntityUri() {
		return this.entityUri;
	}

	public void setEntityUri(String entityUri) {
		this.entityUri = entityUri;
	}

}