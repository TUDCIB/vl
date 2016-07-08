package de.tudresden.bau.cib.vl.core.platform;

import de.tudresden.bau.cib.vl.core.service.ConfigurationService;



/**
 * Interface of the VL-Core plugin.
 * 
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public interface VirtualLaboratory {
	
	String BUNDLE_ID = "de.tudresden.bau.cib.vl.core";

	void start() throws Exception;
	
	void stop() throws Exception;
	
	ConfigurationService getConfiguration();
}
