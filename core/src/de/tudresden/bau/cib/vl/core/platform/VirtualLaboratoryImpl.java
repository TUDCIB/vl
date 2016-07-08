package de.tudresden.bau.cib.vl.core.platform;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.service.ConfigurationService;



public class VirtualLaboratoryImpl implements VirtualLaboratory {
	
	
	private ConfigurationService configuration;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(VirtualLaboratoryImpl.class);
	


	@Override
	public void start() throws Exception {
		LOG.info("Starting the Virtual Laboratory...");
		System.err.println("Starting the Virtual Laboratory...");
	}

	@Override
	public void stop() throws Exception {
		LOG.info("...Virtual Laboratory stopped.\nHave a nice day.");
		System.err.println("...Virtual Laboratory stopped.\nHave a nice day.");
	}
	
	public void setConfiguration(ConfigurationService configurationService) {
		LOG.trace("Setting Configuration ..."+configurationService);
		this.configuration = configurationService;
		LOG.debug("\n" +
				"###################################################################\n" +
				"###########   Configuration for the VirtualLaboratory   ###########\n" +
				"###################################################################");
		Map<String, Object> props = configurationService.getAllProperties();
		for(Map.Entry<String, Object> entry : props.entrySet()) {
			LOG.debug(entry.getKey()+"\t=\t"+entry.getValue());
		}
		LOG.debug("\n" +
				"###################################################################\n" +
				"###########           End of Configuration              ###########\n" +
				"###################################################################");
		
		Runtime rt = Runtime.getRuntime();
		LOG.debug("Maximum memory (virtual machine) available for the Virtual Laboratory: " + rt.maxMemory() +" bytes" );
	}
	
	@Override
	public ConfigurationService getConfiguration() {
		return configuration;
	}

}
