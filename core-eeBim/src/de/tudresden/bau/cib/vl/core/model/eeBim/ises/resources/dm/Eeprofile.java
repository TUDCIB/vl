package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;

/**
 * Eeprofile entity. @author MyEclipse Persistence Tools
 */

public class Eeprofile extends ClassifiableAnnotable implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7022240635505392153L;
	// Fields

	private String profileClass;
	private String profileUri;

	// Constructors

	/** default constructor */
	public Eeprofile() {
	}

	/** full constructor */
	public Eeprofile(String profileClass, String profileUri) {
		this.profileClass = profileClass;
		this.profileUri = profileUri;
	}

	// Property accessors


	public String getProfileClass() {
		return this.profileClass;
	}

	public void setProfileClass(String profileClass) {
		this.profileClass = profileClass;
	}

	public String getProfileUri() {
		return this.profileUri;
	}

	public void setProfileUri(String profileUri) {
		this.profileUri = profileUri;
	}

}