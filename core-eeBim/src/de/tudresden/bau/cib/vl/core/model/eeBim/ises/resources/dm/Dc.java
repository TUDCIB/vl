package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;


import java.sql.Timestamp;

/**
 * Dc entity. @author MyEclipse Persistence Tools
 */

public class Dc implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4859375157098575355L;
	private Long dcid;
	private String dcContributer;
	private String dcCoverage;
	private String dcCreator;
	private Timestamp dcDate;
	private String dcDescription;
	private String dcFormat;
	private String dcIdentifier;
	private String dcLanguage;
	private String dcPublisher;
	private String dcRelation;
	private String dcRights;
	private String dcSource;
	private String dcSubject;
	private String dcTitle;
	private String dcType;

	// Constructors

	/** default constructor */
	public Dc() {
	}

	/** full constructor */
	public Dc(
			String dcContributer, String dcCoverage, String dcCreator,
			Timestamp dcDate, String dcDescription, String dcFormat,
			String dcIdentifier, String dcLanguage, String dcPublisher,
			String dcRelation, String dcRights, String dcSource,
			String dcSubject, String dcTitle, String dcType) {
		this.dcContributer = dcContributer;
		this.dcCoverage = dcCoverage;
		this.dcCreator = dcCreator;
		this.dcDate = dcDate;
		this.dcDescription = dcDescription;
		this.dcFormat = dcFormat;
		this.dcIdentifier = dcIdentifier;
		this.dcLanguage = dcLanguage;
		this.dcPublisher = dcPublisher;
		this.dcRelation = dcRelation;
		this.dcRights = dcRights;
		this.dcSource = dcSource;
		this.dcSubject = dcSubject;
		this.dcTitle = dcTitle;
		this.dcType = dcType;
	}

	// Property accessors

	public Long getDcid() {
		return this.dcid;
	}

	public void setDcid(Long dcid) {
		this.dcid = dcid;
	}
	public String getDcContributer() {
		return this.dcContributer;
	}

	public void setDcContributer(String dcContributer) {
		this.dcContributer = dcContributer;
	}

	public String getDcCoverage() {
		return this.dcCoverage;
	}

	public void setDcCoverage(String dcCoverage) {
		this.dcCoverage = dcCoverage;
	}

	public String getDcCreator() {
		return this.dcCreator;
	}

	public void setDcCreator(String dcCreator) {
		this.dcCreator = dcCreator;
	}

	public Timestamp getDcDate() {
		return this.dcDate;
	}

	public void setDcDate(Timestamp dcDate) {
		this.dcDate = dcDate;
	}

	public String getDcDescription() {
		return this.dcDescription;
	}

	public void setDcDescription(String dcDescription) {
		this.dcDescription = dcDescription;
	}

	public String getDcFormat() {
		return this.dcFormat;
	}

	public void setDcFormat(String dcFormat) {
		this.dcFormat = dcFormat;
	}

	public String getDcIdentifier() {
		return this.dcIdentifier;
	}

	public void setDcIdentifier(String dcIdentifier) {
		this.dcIdentifier = dcIdentifier;
	}

	public String getDcLanguage() {
		return this.dcLanguage;
	}

	public void setDcLanguage(String dcLanguage) {
		this.dcLanguage = dcLanguage;
	}

	public String getDcPublisher() {
		return this.dcPublisher;
	}

	public void setDcPublisher(String dcPublisher) {
		this.dcPublisher = dcPublisher;
	}

	public String getDcRelation() {
		return this.dcRelation;
	}

	public void setDcRelation(String dcRelation) {
		this.dcRelation = dcRelation;
	}

	public String getDcRights() {
		return this.dcRights;
	}

	public void setDcRights(String dcRights) {
		this.dcRights = dcRights;
	}

	public String getDcSource() {
		return this.dcSource;
	}

	public void setDcSource(String dcSource) {
		this.dcSource = dcSource;
	}

	public String getDcSubject() {
		return this.dcSubject;
	}

	public void setDcSubject(String dcSubject) {
		this.dcSubject = dcSubject;
	}

	public String getDcTitle() {
		return this.dcTitle;
	}

	public void setDcTitle(String dcTitle) {
		this.dcTitle = dcTitle;
	}

	public String getDcType() {
		return this.dcType;
	}

	public void setDcType(String dcType) {
		this.dcType = dcType;
	}

}