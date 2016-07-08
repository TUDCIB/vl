package de.tudresden.bau.cib.vl.core.simulation.energy.process;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.exception.ToolException;
import de.tudresden.bau.cib.vl.core.tool.ToolProcess;

/**
 * Defines the batch command for starting the IFC to Nandrad converter.
 *
 * @author Ken Baumgaertel
 *	{@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public class Bim2SimToolProcess extends ToolProcess<File> {

	private static final Logger LOG = LoggerFactory.getLogger(Bim2SimToolProcess.class);
	private final String pathToIfcFile;
	private final String[] options;
	private Process process;
	
	
	public Bim2SimToolProcess(String pathToBatchFile, String batchFileName, String pathToIfcFile, String[] options) {
		super(pathToBatchFile, batchFileName);
		this.pathToIfcFile = pathToIfcFile;
		this.options = options;
	}

	@Override
	public File call() throws ToolException {
		Runtime rt = Runtime.getRuntime();
		
		File directory = new File(pathToBatchFile);
		/*
		 * {@link http://141.30.41.151:8050/nandrad/wiki/IFCImport}
		 * java -jar Ifc2Nandrad.jar <ifc-project-file> [-m|-p|-d]
		 */
		String command = "cmd /c start /WAIT java -jar "+batchFileName+" "+pathToIfcFile;
		if(options != null) {
			command += " ";
			for(String option : options) {
				command += option;
			}
		}
		LOG.info("Starting Bim2Sim process with Ifc-File: {} and command: {} batch file is in directory: {}", 
				new Object[]{pathToIfcFile, command, pathToBatchFile});
		try {
			process = rt.exec(command, null, directory);
			
			int val = process.waitFor();
			LOG.info("Bim2Sim process finished (result code: {})", val);
			if(val > -1) {
				String nandradModelPath = getNandradFilename(pathToIfcFile);
				File resultFile = new File(nandradModelPath);
				if(resultFile.canWrite()) {
					return resultFile;
				} else {
					throw new ToolException("Premature end of file or file doesn't exist");
				}
			} else { // problem occurred
				throw new ToolException("Bim2Sim process finished with result code: "+val);
			}
		} catch (IOException io) {
			throw new ToolException(io);
		} catch (InterruptedException e) {
			throw new ToolException(e);
		}
	}
	
    /**
     * Get the full qualified file name of the NANDRAD project file
     * @param ifcFilename	IFC file name
     * @return	Full qualified file name of the NANDRAD project file 
     */
    private static String getNandradFilename(String ifcFilePath) {
        File file = new File (ifcFilePath);
        String path = normalizePath(file.getParentFile().getPath());
        String filenamePure = file.getName();
        
        int posPoint = filenamePure.indexOf(".");
        if (posPoint > 0)
        	filenamePure = filenamePure.substring(0, posPoint);
        
        return path+filenamePure+".nandrad";
    }
    
    /**
     * Get a directory ending with separator
     * @param filePath	Directory to normalize
     * @return	Directory ending with separator
     */
    private static String normalizePath(String filePath) {
        if (!filePath.endsWith( File.separator ))
        	return filePath+=File.separator;
        else
        	return filePath;
    }

}
