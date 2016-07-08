package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;


/**
 * Attributable entity. @author MyEclipse Persistence Tools
 */

public class Attributable implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6472047466060400443L;
	private Long attributableid;
	private String name;
	private String value;

	// Constructors

	/** default constructor */
	public Attributable() {
	}

	/** full constructor */
	public Attributable(String name, String value) {
		this.name = name;
		this.value = value;
	}

	// Property accessors

	public Long getAttributableid() {
		return this.attributableid;
	}

	public void setAttributableid(Long attributableid) {
		this.attributableid = attributableid;
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}