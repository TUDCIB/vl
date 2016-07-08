package de.tudresden.bau.cib.vl.core.simulation.energy.process;

import java.io.File;
import java.io.IOException;

import jsdai.lang.SdaiException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.exceptions.parser.ParsingException;
import de.tudresden.bau.cib.vl.core.exception.ToolException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.SpaceBoundaryConversionException;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.SpaceBoundaryConversionExceptionCode;
import de.tudresden.bau.cib.vl.core.simulation.energy.transformation.sb.ConvertingSpaceBoundaries;
import de.tudresden.bau.cib.vl.core.tool.ToolProcess;

/**
 * Starts the 1st level space boundary to 2nd level space boundary conversion and returns the converted file.
 * The original file is not modified.
 * 
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public class SpaceBoundaryConversionToolProcess extends ToolProcess<File> {
	
	private Ifc2x3DataModelProxy ifcModel;
	private File saveDirectory;
	private File fileToConvert;
	private File temporaryDirectory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpaceBoundaryConversionToolProcess.class);
	
	public SpaceBoundaryConversionToolProcess(		
			String pathToSpaceBoundaryTool,
			String batchFileExe,
			Ifc2x3DataModelProxy ifcModel, 
			File saveDirectory, 
			File fileToConvert, 
			File temporaryDirectory) {
		super(pathToSpaceBoundaryTool, batchFileExe);
		this.ifcModel = ifcModel;
		this.saveDirectory = saveDirectory;
		this.fileToConvert = fileToConvert;
		this.temporaryDirectory = temporaryDirectory;
	}

	@Override
	public File call() throws ToolException {
		ConvertingSpaceBoundaries conversion = new ConvertingSpaceBoundaries(ifcModel.getOrigin(), saveDirectory, fileToConvert, temporaryDirectory);
		LOGGER.info("Starting process with following parameters: IfcModel = "+ifcModel+"\nSave directory = "+saveDirectory+"\nFile to convert = "+fileToConvert+"\ntemporary directory = "+temporaryDirectory);
		try {
			boolean isEnded = conversion.startConversion(pathToBatchFile+batchFileName);
			
			if(isEnded) {
				File resultfile = conversion.getConvertedIfcFile();
				if(resultfile != null) {
					return resultfile;
				} else {
					LOGGER.error(SpaceBoundaryConversionExceptionCode.NO_RESULT_FILE_AVAILABLE.getMessage());
					throw new SpaceBoundaryConversionException(SpaceBoundaryConversionExceptionCode.NO_RESULT_FILE_AVAILABLE.getMessage());
				}
			}
			
		} catch (SdaiException e) {
			LOGGER.error(e.getMessage());
			throw new SpaceBoundaryConversionException(SpaceBoundaryConversionExceptionCode.MODEL_ERROR, e);
		} catch (ParsingException e) {
			LOGGER.error(e.getMessage());
			throw new SpaceBoundaryConversionException(e.getMessage(),e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new SpaceBoundaryConversionException(SpaceBoundaryConversionExceptionCode.EXECUTION_PROBLEM, e);
		} 
//		catch (IfcException e) {
//			LOGGER.error(e);
//			throw new SpaceBoundaryConversionException(SpaceBoundaryConversionExceptionCode.MODEL_ERROR, e);
//		}
		return null;
	}

}
