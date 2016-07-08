package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;

/**
 * Onmiclasscode entity. @author MyEclipse Persistence Tools
 */

public class Onmiclasscode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7680640460294237551L;
	private String codeId;
	private String tableId;
	private Integer codeLevel;
	private String codeName;
	private String codeDescription;
	private String keywords;
	private Omniclasstable table;
	// Constructors

	/** default constructor */
	public Onmiclasscode() {
	}

	/** minimal constructor */
	public Onmiclasscode(String tableId) {
		this.tableId = tableId;
	}

	/** full constructor */
	public Onmiclasscode(String tableId, Integer codeLevel, String codeName,
			String codeDescription, String keywords) {
		this.tableId = tableId;
		this.codeLevel = codeLevel;
		this.codeName = codeName;
		this.codeDescription = codeDescription;
		this.keywords = keywords;
	}

	// Property accessors
	

	public String getCodeId() {
		return this.codeId;
	}

	public Omniclasstable getOmniclasstable() {
		return table;
	}

	public void setOmniclasstable(Omniclasstable table) {
		this.table = table;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public Integer getCodeLevel() {
		return this.codeLevel;
	}

	public void setCodeLevel(Integer codeLevel) {
		this.codeLevel = codeLevel;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeDescription() {
		return this.codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

}