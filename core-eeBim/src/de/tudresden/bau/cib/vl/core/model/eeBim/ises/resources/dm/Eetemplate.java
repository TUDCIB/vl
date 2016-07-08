package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;

/**
 * Eetemplate entity. @author MyEclipse Persistence Tools
 */

public class Eetemplate extends ClassifiableAnnotable implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3127751587954898365L;
	private String templateClass;
	private String templateUri;

	// Constructors

	/** default constructor */
	public Eetemplate() {
	}

	/** full constructor */
	public Eetemplate(String templateClass, String templateUri) {
		this.templateClass = templateClass;
		this.templateUri = templateUri;
	}

	// Property accessors

	public String getTemplateClass() {
		return this.templateClass;
	}

	public void setTemplateClass(String templateClass) {
		this.templateClass = templateClass;
	}

	public String getTemplateUri() {
		return this.templateUri;
	}

	public void setTemplateUri(String templateUri) {
		this.templateUri = templateUri;
	}


}