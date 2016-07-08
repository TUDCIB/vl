package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;


/**
 * ClassifiedBy entity. @author MyEclipse Persistence Tools
 */

public class ClassifiedBy implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4823191857685251395L;
	private Long classifiedbyid;
	private ClassifiableAnnotable classifiableAnnotable;
	private String tableid;
	private String codeid;

	// Constructors

	/** default constructor */
	public ClassifiedBy() {
	}

	/** full constructor */
	public ClassifiedBy(ClassifiableAnnotable classifiableAnnotable,
			String tableid, String codeid) {
		this.classifiableAnnotable = classifiableAnnotable;
		this.tableid = tableid;
		this.codeid = codeid;
	}
	public ClassifiedBy(
				String tableid, String codeid) {
		this.tableid = tableid;
		this.codeid = codeid;
	}

	// Property accessors

	public Long getClassifiedbyid() {
		return this.classifiedbyid;
	}

	public void setClassifiedbyid(Long classifiedbyid) {
		this.classifiedbyid = classifiedbyid;
	}


	public ClassifiableAnnotable getClassifiableAnnotable() {
		return this.classifiableAnnotable;
	}

	public void setClassifiableAnnotable(
			ClassifiableAnnotable classifiableAnnotable) {
		this.classifiableAnnotable = classifiableAnnotable;
	}

	

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	public String getCodeid() {
		return codeid;
	}

	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}


}