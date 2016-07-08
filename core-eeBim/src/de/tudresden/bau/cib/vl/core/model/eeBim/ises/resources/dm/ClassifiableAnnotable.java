package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * ClassifiableAnnotable entity. @author MyEclipse Persistence Tools
 */


public abstract class ClassifiableAnnotable implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1670389842933022049L;
	// Fields

	private Long classifiableAnnotableid = -1L;
	private String name;
	private String shortname;
	private String shortdescription;
	private String contextIdentifier;
	private String contextDescription;
	private String version;
	private String rdf;
	private Timestamp created;
	private Timestamp lastModified;
	private Dc dc;
	private ResourceDescriptor resourceDescriptor;

	private Set<ClassifiedBy> classifiedBys = new HashSet<ClassifiedBy>(0);
	private Set<Attributable> attributes = new HashSet<Attributable>(0);

	// Constructors

	/** default constructor */
	public ClassifiableAnnotable() {
	}

	public ClassifiableAnnotable(String name, String shortname, String shortdescription, String contextIdentifier,
			String contextDescription, String version, String rdf,
			ResourceDescriptor resourceDescriptor, Timestamp created,
			Timestamp lastModified, Set classifiedBys, Dc dc,
			 Set attributes) {
		this.name = name;
		this.shortname = shortname;
		this.shortdescription = shortdescription;
		this.contextIdentifier = contextIdentifier;
		this.contextDescription = contextDescription;
		this.version = version;
		this.rdf = rdf;
		this.created = created;
		this.lastModified = lastModified;
		this.resourceDescriptor = resourceDescriptor;
		this.dc = dc;
		this.classifiedBys = classifiedBys;
		this.attributes = attributes;
	}

	// Property accessors

	public Long getClassifiableAnnotableid() {
		return this.classifiableAnnotableid;
	}

	public void setClassifiableAnnotableid(Long classifiableAnnotableid) {
		this.classifiableAnnotableid = classifiableAnnotableid;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	
	public String getShortdescription() {
		return shortdescription;
	}

	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}

	public String getContextIdentifier() {
		return this.contextIdentifier;
	}

	public void setContextIdentifier(String contextIdentifier) {
		this.contextIdentifier = contextIdentifier;
	}

	public String getContextDescription() {
		return this.contextDescription;
	}

	public void setContextDescription(String contextDescription) {
		this.contextDescription = contextDescription;
	}
	
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRdf() {
		return this.rdf;
	}

	public void setRdf(String rdf) {
		this.rdf = rdf;
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

	public Set<ClassifiedBy> getClassifiedBys() {
		return this.classifiedBys;
	}

	public void setClassifiedBys(Set<ClassifiedBy> classifiedBys) {
		this.classifiedBys = classifiedBys;
	}
	public ResourceDescriptor getResourceDescriptor() {
		return this.resourceDescriptor;
	}

	public void setResourceDescriptor(ResourceDescriptor resourceDescriptor) {
		this.resourceDescriptor = resourceDescriptor;
	}
	public Dc getDc() {
		return this.dc;
	}

	public void setDc(Dc dc) {
		this.dc = dc;
	}
	
	
	public Set<Attributable> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(Set<Attributable> attributes) {
		this.attributes = attributes;
	}


	@Override
	public String toString() {
		return "ClassifiableAnnotable [classifiableAnnotableid="+classifiableAnnotableid
				+ ", Name=" + name
				+ ", resourceShortName=" + shortname 
				+ ", shortdescription=" + shortdescription 
				+ ", contextIdentifier="	+ contextIdentifier 
				+ ", version=" + version
				+ ", descriptor=["+resourceDescriptor+"]"
				//+ ", created=" + created != null ? created.toString():""
				//+ ", lastModified=" + lastModified != null ? lastModified.toString():"" 
				+ "]";
	}

}