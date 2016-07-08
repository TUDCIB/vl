package de.tudresden.bau.cib.vl.core.model.bim.service.impl;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcExceptionCode;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.parser.Ifc2x3Parser;
import de.tudresden.bau.cib.vl.core.model.bim.service.BimFitService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;


public class BimFitServiceImpl implements BimFitService {
	
	private ConfigurationService configurationService;
	private Logger LOG = LoggerFactory.getLogger(BimFitServiceImpl.class);
	
	Set<String> supportedTypes = new HashSet<String>();
	
	@Override
	public Ifc2x3Parser createIfc2x3Parser() {
		String workingDirectoryPath = configurationService.getProperty(
				ConfigurationService.PROPERTIES.PATH_PARSER_REPOSITORY)+"jsdaiRepo/";
		LOG.debug("Creating IFC parser with repository '"+workingDirectoryPath+"'");
		return Ifc2x3Parser.createParser(workingDirectoryPath);
	}
	
	@Override
	public Ifc2x3DataModelProxy parseIfc2x3File(URL url) throws IfcException {
		String filePath = url.toString();
		LOG.debug("Parsing file: {}", filePath);
		Ifc2x3Parser parser = createIfc2x3Parser();
		try {
			Ifc2x3DataModelProxy model = parser.parse(url.openStream());
			model.setSourceFilePath(filePath);
//			we take the file path as unique identifier for the model
			String uuid = UUID.nameUUIDFromBytes(filePath.getBytes()).toString();
			model.setId(uuid);
			return model;
		} catch (ParsingException e) {
			LOG.error("File parsing of URL: {} failed: {}", new Object[]{url, e.getMessage()});
			throw new IfcException(IfcExceptionCode.PARSE_FILE, e);
		} catch (IOException e) {
			LOG.error("File parsing of URL: {} failed: {}", new Object[]{url, e.getMessage()});
			throw new IfcException(IfcExceptionCode.PARSE_FILE, e);
		}
	}
	
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

}
