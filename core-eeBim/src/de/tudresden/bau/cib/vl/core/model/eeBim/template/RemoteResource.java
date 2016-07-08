package de.tudresden.bau.cib.vl.core.model.eeBim.template;

import de.tudresden.bau.cib.vl.core.model.Resource;

public class RemoteResource extends Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7233861371427080497L;
	
	protected String catalogueId;
	
	public String getCatalogueId() {
		return catalogueId;
	}

	public void setCatalogueId(String catalogueId) {
		this.catalogueId = catalogueId;
	}
}
