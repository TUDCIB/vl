package de.tudresden.bau.cib.vl.core.simulation.energy.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.exception.ToolException;
import de.tudresden.bau.cib.vl.core.exception.code.ToolExceptionCode;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.simulation.energy.process.SpaceBoundaryConversionToolProcess;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.SpaceBoundaryService;
import de.tudresden.bau.cib.vl.core.tool.Tool;

public class SpaceBoundaryServiceImpl implements SpaceBoundaryService {
		
	private static final Logger LOG = LoggerFactory.getLogger(SpaceBoundaryServiceImpl.class);
	
	private ConfigurationService configurationService;
	private FileService fileService;
	private static final String BUNDLE_PATH_TO_SPACEBOUNDARY_DIR = "spaceboundary/";
	private static final String EXE_SPACEBOUNDARYREAD_TOOL = "SpaceBoundaryReadXML.exe";
	
	/**
	 * Starts the space boundary conversion of the given IFC file which includes first level space boundaries.
	 * @param ifcModel
	 * @param fileToConvert
	 * @return The converted IFC file with 2nd level space boundaries.
	 * @throws ToolException
	 */
	@Override
	public File convertSpaceBoundaries(Ifc2x3DataModelProxy ifcModel, File fileToConvert) throws ToolException {
		try {
			if(ifcModel.has2ndLevelSpaceBoundaries()) {
				LOG.info("IFC model already provides second level space boundaries!");
				return fileToConvert;
			}
		} catch (IfcException e) {
			throw new ToolException("IFC problem occurred", e);
		}
		String saveDirectoryPath = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
		File saveDirectory = new File(saveDirectoryPath);
		if(!saveDirectory.exists()) {
			saveDirectory.mkdirs();
		}

		String temporaryDirectoryPath = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
		File temporaryDirectory = new File(temporaryDirectoryPath);
		if(!temporaryDirectory.exists()) {
			temporaryDirectory.mkdirs();
		}
		
		// copy BSPro tool to temp dir if it is not still existent
		try {
			String pathToSBConversionTool = copySpaceBoundaryConversionToolToLocalDisk(temporaryDirectoryPath);
			File bSProFile = new File(pathToSBConversionTool);
			if(!bSProFile.exists()) {
				throw new ToolException(ToolExceptionCode.TOOL_NOT_FOUND + " -> expected path: "+bSProFile);
			} else {
				bSProFile.deleteOnExit(); // delete BSPro on local disk if program ends
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new ToolException(ToolExceptionCode.TOOL_NOT_FOUND);
		}
		
		SpaceBoundaryConversionToolProcess sbToolProcess = new SpaceBoundaryConversionToolProcess(
				temporaryDirectoryPath, EXE_SPACEBOUNDARYREAD_TOOL,
				ifcModel, saveDirectory, fileToConvert, temporaryDirectory);
		
		Tool<SpaceBoundaryConversionToolProcess, File> sbTool = new Tool<SpaceBoundaryConversionToolProcess, File>(sbToolProcess, 10, (60*24));
		sbTool.startExecution();
		File resultFile = sbTool.getOutput();
		return resultFile;

	}

	private String copySpaceBoundaryConversionToolToLocalDisk(String temporaryDirectoryPath) throws IOException {
		final String newBSProPath = temporaryDirectoryPath+File.separator+EXE_SPACEBOUNDARYREAD_TOOL;
		if(new File(newBSProPath).exists()) return newBSProPath; // already in temp dir
		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		if(bundle != null) { // production mode
			URL bsproExe = bundle.getResource(BUNDLE_PATH_TO_SPACEBOUNDARY_DIR+EXE_SPACEBOUNDARYREAD_TOOL);
			LOG.debug("Copying BSPro from bundle {} to {}", new Object[]{bsproExe, temporaryDirectoryPath});
			FileService.copyFileFromBundle(bsproExe, temporaryDirectoryPath, bundle);
		} else { // test mode
			String diskPath = BUNDLE_PATH_TO_SPACEBOUNDARY_DIR+EXE_SPACEBOUNDARYREAD_TOOL;
			LOG.debug("Copying BSPro from disk {} to {}", new Object[]{diskPath, temporaryDirectoryPath});
			FileService.copyFileToDirectory(new File(diskPath), new File(temporaryDirectoryPath));
		}
		return newBSProPath;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

}
