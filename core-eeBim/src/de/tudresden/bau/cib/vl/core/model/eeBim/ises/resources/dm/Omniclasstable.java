package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;

import java.util.Set;

/**
 * Omniclasstable entity. @author MyEclipse Persistence Tools
 */

public class Omniclasstable implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5428807960787302750L;
	private String tableId;
	private String tableName;
	private Set tableCodes;

	// Constructors

	/** default constructor */
	public Omniclasstable() {
	}

	/** full constructor */
	public Omniclasstable(String tableName) {
		this.tableName = tableName;
	}

    public Set getTableCodes() {
      return tableCodes;
    }
    public void setTableCodes( Set tableCodes ) {
      this.tableCodes = tableCodes;
    }

	// Property accessors

	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}