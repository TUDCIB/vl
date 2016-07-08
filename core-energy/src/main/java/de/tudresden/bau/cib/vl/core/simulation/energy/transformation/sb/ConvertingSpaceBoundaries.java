/**
 * TU Dresden, Institute of Construction Informatics
 *
 */
package de.tudresden.bau.cib.vl.core.simulation.energy.transformation.sb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.AIfcobjectdefinition;
import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcobjectdefinition;
import jsdai.SIfc2x3.EIfcrelaggregates;
import jsdai.SIfc2x3.EIfcrelspaceboundary;
import jsdai.SIfc2x3.EIfcsite;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.SIfc2x3;
import jsdai.lang.AEntity;
import jsdai.lang.A_string;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.exceptions.parser.ParsingException;
import de.tudresden.bau.cib.filter.ifc.semantic.IfcDataModel;
import de.tudresden.bau.cib.model.utility.IterableAggregate;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.SpaceBoundaryConversionException;


/**
 * Space Boundary Converter with SpaceBoundaryRead from Olof Granlund.
 * Converts 1st Level Space Boundaries to 2nd Level Space Boundaries and export it to IFC2x3. 
 *
 * @author Ken Baumgaertel
 *	{@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public class ConvertingSpaceBoundaries {
	
	private File temporaryDirectory;
	private static final String OUTPUT_FILE_NAME = "_output.xml";
	public static final String FILE_EXTENSION = "_2ndLSB.ifc";
	
	private IfcDataModel ifcModel;
	
	private Logger LOG = LoggerFactory.getLogger(ConvertingSpaceBoundaries.class);
//	according to 2nd Level Space Boundary Implementation Agreement
	private static final String HEADER_2ND_LEVEL_DESCRIPTION = "ViewDefinition [CoordinationView, SpaceBoundary2ndLevelAddOnView]";
	
//	source file
	private File fileToConvert;
//	target file with 2nd Level Space Boundaries
	private File convertedIfcFile;
	private File saveDirectory;
	private Integer resultValue;
	
	
	public ConvertingSpaceBoundaries(IfcDataModel ifcModel, File saveDirectory, File fileToConvert, File temporaryDirectory) {
		this.fileToConvert = fileToConvert;
		this.ifcModel = ifcModel;
		this.temporaryDirectory = temporaryDirectory;
		this.saveDirectory = saveDirectory;
	}
	
	/**
	 * Starts the conversion and waits for it.
	 * 
	 * @return True if the conversion ended successfully.
	 * @throws SdaiException
	 * @throws ParsingException
	 * @throws IOException
	 * @throws SpaceBoundaryConversionException 
	 */
	public boolean startConversion(String spaceBoundaryReadPath) throws SdaiException, ParsingException, IOException, SpaceBoundaryConversionException {  
		String outputXmlPath = null;
		long startTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SS");
		
//		because currently it is possible to have multiple IfcBuilding instances in one IFC file which is not supported with the old BSPro
		prepareIfc(fileToConvert.getAbsolutePath());

		try {		
			LOG.info("Using Model "+fileToConvert.getName()+"...");
//			String command = "cmd /c start /WAIT "+Configuration.getProperty("PATH_SPACEBOUNDARYREAD")+" "+fileToConvert.getAbsolutePath();
//			String command = EnergySimulationController.getInstance().getConfigurationService().getProperty("PATH_SPACEBOUNDARYREAD")+" "+fileToConvert.getAbsolutePath();
			
//			String spaceBoundaryReadPath = new File("resources/SpaceBoundaryReadXML.exe").getAbsolutePath();
			String command = spaceBoundaryReadPath+" "+fileToConvert.getAbsolutePath();
//			less-than-ideal solution
//			String command = "D:\\Nutzer\\ken\\Workspaces\\Prototypes\\SpaceBoundaryConversion\\resources\\SpaceBoundaryReadXML.exe "+fileToConvert.getAbsolutePath();
			LOG.info("...the used command is '"+command+"'");
			Process process = Runtime.getRuntime().exec(command);
			
			outputXmlPath = temporaryDirectory.getAbsolutePath()+File.separator+fileToConvert.getName()+OUTPUT_FILE_NAME;
//			SpaceBoundaryRead writes all information in an output stream
	        StreamGobbler bsproThread = new StreamGobbler(process.getInputStream(), 
	        		outputXmlPath);
	        
//	        EnergyPlugin.getExecutorService().execute(bsproThread);
	        
	        bsproThread.start();
	        
			LOG.info("...BSPro started...");
			
//			bsproThread.join();
			
			long endOfBSProTime = System.currentTimeMillis();
			
			this.resultValue = process.waitFor();
			Date calculated = new Date(endOfBSProTime-startTime);
			LOG.info("...BSPro Process finished with duration: '"+sdf.format(calculated)+"'.");
			
			if(this.resultValue == 1) {
				LOG.warn("No SpaceBoundary Conversion possible!");
				return false;
			}
			
			bsproThread.join();
			
//			FileOutputStream fos = new FileOutputStream(new File(temporaryDirectory.getAbsolutePath()+File.separator+OUTPUT_FILE_NAME));
//			
//			IOUtils.copy(process.getInputStream(), fos);
//			fos.close();
			
			
			while(!new File(outputXmlPath).canWrite()){
				Thread.sleep(1000);
				LOG.info("Waiting to write on file '"+outputXmlPath+"'");
			}

		} catch (InterruptedException e) {
			LOG.error("Thread interruption "+e.getMessage());
			return false;
		}
		
		SecondLevelSpaceBoundaryBuilder spaceBoundaryBuilder = new SecondLevelSpaceBoundaryBuilder(
				outputXmlPath, ifcModel);
		
		LOG.info("...Building Second Space Boundaries...");

//		XMLParser xmlParser = new XMLParser(stdout, ifcDataModel);
		List<EEntity> ents = new ArrayList<EEntity>();
		Set<EIfcspace> spaces = spaceBoundaryBuilder.getSpaces();
		LOG.info("... avalailable Spaces: "+spaces.size() + " ...");
		for(EIfcspace space : spaces) {
			Set<EIfcrelspaceboundary> spaceBoundaries = spaceBoundaryBuilder.createSecondLevelSpaceBoundaries(space);
			LOG.info("...Number of created 2nd Level Space Boundaries for Space: "+spaceBoundaries.size()+"...");
		}
		
		spaceBoundaryBuilder.deleteAllFirstLevelSpaceBoundaries();
		
//      write Header according to IFC implementation agreement for Space Boundaries
        writeHeader();
        
		LOG.info("... SpaceBoundary Conversion completed...");
		AEntity entities = ifcModel.getBasicModel().getModel().getInstances();
		for(EEntity entity : new IterableAggregate<EEntity>(entities)) {
			ents.add(entity);
		}
		String fileName = fileToConvert.getName();
		
//		cut the extension
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		
		String exportPath = saveDirectory.getAbsolutePath()+File.separator+fileName+FILE_EXTENSION;
		LOG.info("...Exporting to... "+exportPath+"...");
//		export the IfcModel
//		ifcModel.exportIfcModel(ents.toArray(new EEntity[ents.size()]), not working correctly -> AWU
//				exportPath, SIfc2x3.class);
		ifcModel.getBasicModel().exportModel(ents.toArray(new EEntity[ents.size()]), 
				exportPath, SIfc2x3.class);
		
		convertedIfcFile = new File(exportPath);
		
		LOG.info("...Export done!");
		long endTime = System.currentTimeMillis();
		long measuredTime = endTime-startTime;
		Date calculatedEndTime = new Date(measuredTime);
		LOG.info("Overall Time of Conversion: '"+sdf.format(calculatedEndTime)+"'");
		
		return true;
	}
	
	/**
	 * Prepares the IFC model for further processing.
	 * Currently, it deletes the IfcBuilding instances which are not related to the IfcBuildingStorey and set the relations of all other 
	 * IfcBuilding instances to the one which will remain
	 * @throws SdaiException 
	 * @throws IfcException 
	 */
	private void prepareIfc(String path) throws SpaceBoundaryConversionException, SdaiException {
		Map<String, EIfcbuilding> buildings = ifcModel.getBuildings();
		if(buildings == null) throw new SpaceBoundaryConversionException("No IfcBuilding instance available");
		if(buildings.size() == 0) throw new SpaceBoundaryConversionException("No IfcBuilding instance available");
		if(buildings.size() == 1) return; // nothing to do -> thats ok
		
		List<EEntity> allEntities = new ArrayList<EEntity>();
		allEntities.addAll(Arrays.asList(ifcModel.getBasicModel().getEntities()));
		
		List<EEntity> entitiesToDeleteList = new ArrayList<EEntity>();
		boolean changed = false;
		
		EIfcbuilding remainingIfcBuildingInstance = null;
		EIfcrelaggregates[] relAggregates = ifcModel.getBasicModel().getEntitiesOf(EIfcrelaggregates.class);
		
		for (Map.Entry<String, EIfcbuilding> entry : buildings.entrySet()) {
			String buildingGuid = entry.getKey();
			EIfcbuilding value = entry.getValue();
//			search the IfcRelAggregates which connects storeys to the building, because this is the  IfcBuilding which will remain
			relAggLoop : for(EIfcrelaggregates relAggregate : relAggregates) {
				EIfcobjectdefinition relatingObject = relAggregate.getRelatingobject(relAggregate);
				AIfcobjectdefinition relatedObjects = relAggregate.getRelatedobjects(relAggregate);
				String relatingObjectGuid = ifcModel.getGlobalId(relatingObject);
				if(relatingObjectGuid.equals(buildingGuid)) {
					for(EIfcobjectdefinition relatedObject : new IterableAggregate<EIfcobjectdefinition>(relatedObjects)) {
						String relatedObjectGuid = ifcModel.getGlobalId(relatedObject);
						if(buildings.containsKey(relatedObjectGuid)) { // it is another IfcBuilding => remove this relationship
							entitiesToDeleteList.add(relAggregate); // delete it
							continue relAggLoop;
						} else { // have a relationship to the storeys TODO MAYBE THIS IS NOT APPLICABLE FOR ALL CASES
							remainingIfcBuildingInstance = value;
						}
					}	
				}
			}
		}
		if(remainingIfcBuildingInstance == null) throw new SpaceBoundaryConversionException("No IfcBuilding instance found which will be remained");
//		get the IfcSite and assign it to the building
		EIfcsite ifcSite = null;
		EIfcsite[] ifcSites = ifcModel.getBasicModel().getEntitiesOf(EIfcsite.class);
		if(ifcSites != null) {
			if(ifcSites.length > 0) {
				ifcSite = ifcSites[0]; // take only the first one	
				
//				search the IfcRelAggregates which relates to the IfcSite
				for(EIfcrelaggregates relAggregate : relAggregates) {
					EIfcobjectdefinition relatingObject = relAggregate.getRelatingobject(relAggregate);					
					if(relatingObject instanceof EIfcsite) {
						AIfcobjectdefinition newRelatedObjects = relAggregate.createRelatedobjects(relAggregate); // create new related objects with the IfcBuilding
						newRelatedObjects.addUnordered(remainingIfcBuildingInstance);
					}
				}
			}
		}
		
		String remainingIfcBuildingGuid = ifcModel.getGlobalId(remainingIfcBuildingInstance);
		
//		delete all other IfcBuilding instances
		for (Map.Entry<String, EIfcbuilding> entry : buildings.entrySet()) {
			String key = entry.getKey();
			EIfcbuilding value = entry.getValue();
			
			if(remainingIfcBuildingGuid.equals(key)) {			
				if(ifcSite != null) {
					
				}
				continue; // we do not want to delete this instance
			}
			else {
				entitiesToDeleteList.add(value);
			}
		}
		
		if(entitiesToDeleteList.size() > 0) changed = true; // there are entities which will be deleted so the model changes
//		delete the entities which are not needed anymore
		for(EEntity entity : entitiesToDeleteList) {
			allEntities.remove(entity);
			entity.deleteApplicationInstance();
		}
		
//		export it
		if(changed)	{ // if entities are deleted we export a new IFC file
			LOG.debug("IFC model was changed due to multiple IfcBuilding instances => export it to path: {}",
					new Object[]{path});
			ifcModel.exportIfcModel(allEntities.toArray(new EEntity[allEntities.size()]), path, SIfc2x3.class);
		}
	}

	/**
	 * As defined in "Implementation Agreement for Space Boundaries" the header must involve the type of the Space Boundaries.
	 * 
	 * 
	 * @throws SdaiException
	 */
	private void writeHeader() throws SdaiException {
//		SdaiModel sdaiModel = stepModel.getModel();
//		ASchemaInstance schemaInstanceAggregate = sdaiModel.getAssociatedWith();
//		A_string description = schemaInstanceAggregate.getByIndex(1).getDescription();
//		for(int i = 1; i < description.getMemberCount();i++){
//			String descriptionEntry = description.getByIndex(i);
//			if(descriptionEntry.trim().toLowerCase().matches(".*coordinationview.*")) {
//				description.removeByIndex(i);
//				LOGGER.info("..."+descriptionEntry + " removed");
//				description.addByIndex(i, HEADER_2ND_LEVEL_DESCRIPTION);
//				LOGGER.info("...ViewDefinition [CoordinationView, SpaceBoundary2ndLevelAddOnView] added successfully");
//			}
//		}
		SdaiRepository sdaiRepository = ifcModel.getBasicModel().getModel().getRepository();
		A_string description = sdaiRepository.getDescription();
		for(int i = 1; i < description.getMemberCount();i++){
			String descriptionEntry = description.getByIndex(i);
			if(descriptionEntry.trim().toLowerCase().matches(".*coordinationview.*")) {
				description.removeByIndex(i);
				LOG.debug("..."+descriptionEntry + " removed...");
				description.addByIndex(i, HEADER_2ND_LEVEL_DESCRIPTION);
				LOG.debug("...ViewDefinition [CoordinationView, SpaceBoundary2ndLevelAddOnView] added successfully");
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void writeXML(InputStream is, String outputPath) {
		try {
//			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//			FileWriter fileWriter = new FileWriter(new File(outputPath));
//			
//			String line;
//	        while (( line = reader.readLine()) != null){
//	            fileWriter.write(line);
//	        }
//	        
//	        reader.close();
//	        fileWriter.flush();
//	        fileWriter.close();
			
			FileOutputStream fos = new FileOutputStream(new File(outputPath));
			byte buf[] = new byte[1024];
			int len;
			while((len = is.read(buf))>0) {
				fos.write(buf, 0, len);
			}
			fos.close();
			is.close();
		} catch (FileNotFoundException e1) {
			LOG.error(e1.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}

	}
	
	@SuppressWarnings("unused")
	private void writeToConsole(InputStream stdout) {
      BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
      String line;
      try {
          while ((line = br.readLine()) != null) {
              System.out.println(line);
          }
      } catch (IOException e) {
    	  LOG.error(e.getMessage());
      }
	}
    
    class StreamGobbler extends Thread {
    	InputStream is;
    	String outputPath;

    	StreamGobbler(InputStream is, String outputPath) {
    		this.is = is;
    		this.outputPath = outputPath;
    	}

    	public void run() {
    		try {
  			
    			FileOutputStream fos = new FileOutputStream(new File(outputPath));
    			byte buf[] = new byte[1024];
    			int len;
    			while((len = is.read(buf))>0) {
    				fos.write(buf, 0, len);
    			}
    			fos.close();
    			is.close();
    		} catch (IOException ioe) {
    			LOG.error(ioe.getMessage());
    		}
    		
//			InputStream is = process.getInputStream();
//		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
//
//		    Transformer transformer;
//			try {
//				transformer = transformerFactory.newTransformer();
//			    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//			    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//			    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//				transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "publicId");
//				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "systemId");
//			    transformer.transform(new StreamSource (is),  
//			    		new StreamResult( new FileOutputStream(temporaryDirectory.getAbsolutePath()+File.separator+OUTPUT_FILE_NAME))
//			     );
//			} catch (TransformerConfigurationException e) {
//				e.printStackTrace();
//			} catch (TransformerException e) {
//				e.printStackTrace();
//			}
    	}
    }

	public File getConvertedIfcFile() {
		return convertedIfcFile;
	}

	public Integer getResultValue() {
		return resultValue;
	}

}
