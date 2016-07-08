package de.tudresden.bau.cib.vl.core.simulation.energy.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;


public final class LocationIdentifierWriter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LocationIdentifierWriter.class);
	private static final String EXPORT_DATAFILTER = "export-datafilter.txt";
	
	
	private LocationIdentifierWriter() {
		throw new AssertionError();
	}
	
	/**
	 * Writes the text file with the IFC GUIDs of the entities which will be simulated.
	 * Scheme of that file is on {@link http://141.30.41.151:8050/nandrad/wiki/IFCImport}
	 * @param exportDirectoryPath
	 * @param guids
	 * @param ifcModel
	 * @return The exported text file. 
	 * @throws IOException
	 * @throws IfcException 
	 */
	public static File writeLocationIdentifiers(String exportDirectoryPath, 
			Collection<String> guids) throws IOException, IfcException {	
		File exportFile = new File(exportDirectoryPath+EXPORT_DATAFILTER);
		FileWriter writer = new FileWriter(exportFile);
		LOGGER.debug("Writing GUIDs: ({}) to directory: ({})", new Object[]{guids, exportDirectoryPath});	
		for(String guid : guids) {
			writer.write(guid+"\n");
		}
		writer.close();
		return exportFile;
	}

}
