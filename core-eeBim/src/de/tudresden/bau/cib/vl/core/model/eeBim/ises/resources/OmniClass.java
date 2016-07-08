package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources;

public enum OmniClass {
	Cementitious_Concretes (23,"13 15 11 11"),
	Windows (23, "17 13 00")
	;
	
	Integer tableId;
	String code;
	
	OmniClass(Integer tableId, String code) {
		this.tableId = tableId;
		this.code = code;
	}
	
	public String getOmniClassNumber() {
		return tableId+"/"+code;
	}
}