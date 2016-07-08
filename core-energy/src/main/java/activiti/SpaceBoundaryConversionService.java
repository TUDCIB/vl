package activiti;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jsdai.SIfc2x3.AIfccartesianpoint;
import jsdai.SIfc2x3.AIfcobject;
import jsdai.SIfc2x3.AIfcphysicalquantity;
import jsdai.SIfc2x3.EIfcapplication;
import jsdai.SIfc2x3.EIfcaxis2placement3d;
import jsdai.SIfc2x3.EIfcbuildingelement;
import jsdai.SIfc2x3.EIfccartesianpoint;
import jsdai.SIfc2x3.EIfcchangeactionenum;
import jsdai.SIfc2x3.EIfcconnectiongeometry;
import jsdai.SIfc2x3.EIfcconnectionsurfacegeometry;
import jsdai.SIfc2x3.EIfccurveboundedplane;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcelementquantity;
import jsdai.SIfc2x3.EIfcinternalorexternalenum;
import jsdai.SIfc2x3.EIfclocalplacement;
import jsdai.SIfc2x3.EIfcobjectplacement;
import jsdai.SIfc2x3.EIfcopeningelement;
import jsdai.SIfc2x3.EIfcorganization;
import jsdai.SIfc2x3.EIfcownerhistory;
import jsdai.SIfc2x3.EIfcperson;
import jsdai.SIfc2x3.EIfcpersonandorganization;
import jsdai.SIfc2x3.EIfcphysicalorvirtualenum;
import jsdai.SIfc2x3.EIfcphysicalquantity;
import jsdai.SIfc2x3.EIfcplane;
import jsdai.SIfc2x3.EIfcpolyline;
import jsdai.SIfc2x3.EIfcquantityarea;
import jsdai.SIfc2x3.EIfcquantitylength;
import jsdai.SIfc2x3.EIfcquantityvolume;
import jsdai.SIfc2x3.EIfcreldefinesbyproperties;
import jsdai.SIfc2x3.EIfcrelspaceboundary;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcsiprefix;
import jsdai.SIfc2x3.EIfcsiunit;
import jsdai.SIfc2x3.EIfcsiunitname;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcunitenum;
import jsdai.SIfc2x3.EIfcvirtualelement;
import jsdai.SIfc2x3.SIfc2x3;
import jsdai.lang.AEntity;
import jsdai.lang.A_double;
import jsdai.lang.A_string;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import jsdai.lang.SdaiModel;
import jsdai.lang.SdaiRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.tudresden.bau.cib.exceptions.parser.ParsingException;
import de.tudresden.bau.cib.filter.ifc.semantic.IfcDataModel;
import de.tudresden.bau.cib.filter.ifc.semantic.IfcDataModelImpl;
import de.tudresden.bau.cib.model.StepDataModel;
import de.tudresden.bau.cib.parser.StepParser;

public class SpaceBoundaryConversionService {
	
//	XML node constants
	private static final String XML_SPACE_BOUNDARY_EXPORT = "spaceboundaryexport";
	private static final String XML_NAME = "name";
	private static final String XML_DESCRIPTION = "description";
	private static final String XML_INSTANCE = "instance";
	private static final String XML_GUID = "guid";
	private static final String XML_LENGTH_UNIT = "length-unit";
	private static final String XML_PREFERENCES = "preferences";
	
	private static final String XML_LOCALPLACEMENT = "localplacement";
	private static final String XML_LOCATION = "location";
	private static final String XML_DIRECTION = "direction";
	private static final String XML_AXIS = "axis";
	private static final String XML_REFDIRECTION = "refdirection";
	
	private static final String XML_CARTESIANPOINT = "cartesianpoint";
	private static final String XML_X = "x";
	private static final String XML_Y = "y";
	private static final String XML_Z = "z";
	
	private static final String XML_POINTS = "points";
	private static final String XML_POINT = "point";
	
	private static final String XML_EXTRUSION = "extrusion";
	private static final String XML_CALCULATED_AREA = "calculated-area";
	private static final String XML_CALCULATED_VOLUME = "calculated-volume";
	private static final String XML_CALCULATED_SPACEBOUNDARY_VOLUME = "calculated-spaceboundary-volume";
	
	private static final String XML_SPACE = "space";
	private static final String XML_SPACEDATA = "spacedata";
	private static final String XML_SECOND_LEVEL_SPACEBOUNDARIES = "secondlevelspaceboundaries";
	private static final String XML_SIMPLIFIED_SPACEBOUNDARY = "simplifiedspaceboundary";
	private static final String XML_INTERNAL_OR_EXTERNAL_BOUNDARY = "internalorexternalboundary";
	
	private static final String XML_TYPE = "type";
	private static final String XML_AZIMUTH = "azimuth"; // orientation of a building element (in radian measure)
	private static final String XML_TILT = "tilt"; // tilt of a building element (in degree measure)
	private static final String XML_OUTWARD_NORMAL = "outward-normal"; 
	private static final String XML_ADJACENT_SPACE = "adjacent-space";
	private static final String XML_SPACEBOUNDARY_AREA = "spaceboundary-area";	 // areas of space boundary
	
	private static final String PSET_AREA_VOLUME = "Pset_Area_Volume";
	private static final String PSET_AZIMUTH_TILT = "Pset_Azimuth_Tilt";
	
	public static final String QUANTITY_GROSS_AREA = "GrossArea";
	public static final String QUANTITY_GROSS_VOLUME = "GrossVolume";
	public static final String QUANTITY_AZIMUTH = "azimuth";
	public static final String QUANTITY_TILT = "tilt";	
	
	public static final String BASE_QUANTITIES = "BaseQuantities";
	
	public static final String DESCRIPTION_SPACEBOUNDARY_AREA = "#spaceboundary_area=";	
	
	public static enum IfcSIUnitNameEnum {
		AMPERE,
		BECQUEREL,
		CANDELA,
		COULOMB,
		CUBIC_METRE,
		DEGREE_CELSIUS,
		FARAD,
		GRAM,
		GRAY,
		HENRY,
		HERTZ,
		JOULE,
		KELVIN,
		LUMEN,
		LUX,
		METRE,
		MOLE,
		NEWTON,
		OHM,
		PASCAL,
		RADIAN,
		SECOND,
		SIEMENS,
		SIEVERT,
		SQUARE_METRE,
		STERADIAN,
		TESLA,
		VOLT,
		WATT,
		WEBER
	}
	
	public enum IfcSIPrefix {
		EXA (1000000000000000000.0),
		PETA (1000000000000000.0),
		TERA (1000000000000.0),
		GIGA (1000000000.0),
		MEGA (1000000.0),
		KILO (1000.0),
		HECTO (100.0),
		DECA (10.0),
		DECI (0.1),
		CENTI (0.01),
		MILLI (0.001),
		MICRO (0.000001),
		NANO (0.000000001),
		PICO (0.000000000001),
		FEMTO (0.000000000000001),
		ATTO (0.000000000000000001);
		
		double conversionValue;
		
		IfcSIPrefix(double conversionValue) {
			this.conversionValue = conversionValue;
		}
		
		public double getConversionValue() {
			return conversionValue;
		}
	}
	
	private static final String SECOND_LEVEL_SB_NAME = "2ndLevel";

	private File temporaryDirectory;
	private static final String OUTPUT_FILE_NAME = "_output.xml";
	public static final String FILE_EXTENSION = "_2ndLSB.ifc";
	
	private IfcDataModel ifcModel;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpaceBoundaryConversionService.class);
//	according to 2nd Level Space Boundary Implementation Agreement
	private static final String HEADER_2ND_LEVEL_DESCRIPTION = "ViewDefinition [CoordinationView, SpaceBoundary2ndLevelAddOnView]";
	
//	source file
	private File fileToConvert;
//	target file with 2nd Level Space Boundaries
	private File convertedIfcFile;
	private Integer resultValue;
	
	private StepParser parser;
	
	private InputStream in = null;
	private Document doc = null;
	private SdaiModel sdaiModel = null;
	
	private Node root = null;
	private EIfcownerhistory ownerHistory = null;
	
	private Set<String> availableGuids;
	private List<EIfcspace> spacesToAnalyze;
	private EIfcspace currentSpace;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SS");
	
	
	public SpaceBoundaryConversionService() {
	}
	
	public void parseFile(String repoPath, String filePath) throws ParsingException, SdaiException {
		parser = new StepParser(repoPath);
		StepDataModel stepModel = parser.parse(filePath);
		sdaiModel = stepModel.getModel();
		ifcModel = new IfcDataModelImpl(stepModel);
		this.fileToConvert = new File(filePath);
		
//		get all available GlobalIds of the model
		catchAvailableGuids();
	}
	
	public boolean analyzeGeometry(String temporaryDirectory, String spaceBoundaryReadPath) throws IOException {
		this.temporaryDirectory = new File(temporaryDirectory);
		
		String outputXmlPath = null;
		long startTime = System.currentTimeMillis();

		try {		
			LOGGER.info("Using Model "+fileToConvert.getName()+"...");
//			String command = "cmd /c start /WAIT "+Configuration.getProperty("PATH_SPACEBOUNDARYREAD")+" "+fileToConvert.getAbsolutePath();
//			String command = EnergySimulationController.getInstance().getConfigurationService().getProperty("PATH_SPACEBOUNDARYREAD")+" "+fileToConvert.getAbsolutePath();
			
//			String spaceBoundaryReadPath = new File("resources/SpaceBoundaryReadXML.exe").getAbsolutePath();
			String command = spaceBoundaryReadPath+" "+fileToConvert.getAbsolutePath();
//			less-than-ideal solution
//			String command = "D:\\Nutzer\\ken\\Workspaces\\Prototypes\\SpaceBoundaryConversion\\resources\\SpaceBoundaryReadXML.exe "+fileToConvert.getAbsolutePath();
			LOGGER.info("...the used command is '"+command+"'");
			Process process = Runtime.getRuntime().exec(command);
			
			outputXmlPath = this.temporaryDirectory.getAbsolutePath()+File.separator+fileToConvert.getName()+OUTPUT_FILE_NAME;
//			SpaceBoundaryRead writes all information in an output stream
	        StreamGobbler bsproThread = new StreamGobbler(process.getInputStream(), 
	        		outputXmlPath);
	        
//	        EnergyPlugin.getExecutorService().execute(bsproThread);
	        
	        bsproThread.start();
	        
			LOGGER.info("...BSPro started...");
			
//			bsproThread.join();
			
			long endOfBSProTime = System.currentTimeMillis();
			
			this.resultValue = process.waitFor();
			Date calculated = new Date(endOfBSProTime-startTime);
			LOGGER.info("...BSPro Process finished with duration: '"+sdf.format(calculated)+"'.");
			
			if(this.resultValue == 1) {
				LOGGER.warn("No SpaceBoundary Conversion possible!");
				return false;
			}
			
			bsproThread.join();
			
//			FileOutputStream fos = new FileOutputStream(new File(temporaryDirectory.getAbsolutePath()+File.separator+OUTPUT_FILE_NAME));
//			
//			IOUtils.copy(process.getInputStream(), fos);
//			fos.close();
			
			
			while(!new File(outputXmlPath).canWrite()){
				Thread.sleep(1000);
				LOGGER.info("Waiting to write on file '"+outputXmlPath+"'");
			}

		} catch (InterruptedException e) {
			LOGGER.error("Thread interruption "+e.getMessage());
			return false;
		}
		
		readFile(outputXmlPath);
		
		return true;
	}
	
	public void exportFile(String saveDirectory) throws SdaiException {
//      write Header according to IFC implementation agreement for Space Boundaries
        writeHeader();
        List<EEntity> ents = new ArrayList<EEntity>();
        
		LOGGER.info("... SpaceBoundary Conversion completed...");
		AEntity entities = ifcModel.getBasicModel().getModel().getInstances();
		SdaiIterator iterator = entities.createIterator();
		while(iterator.next()) {
			EEntity entity = entities.getCurrentMemberEntity(iterator);
			ents.add(entity);
		}
		String fileName = fileToConvert.getName();
		
//		cut the extension
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		
		long startTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SS");
		
		String exportPath = saveDirectory+File.separator+fileName+FILE_EXTENSION;
		LOGGER.info("...Exporting to... "+exportPath+"...");
//		export the IfcModel
//		ifcModel.exportIfcModel(ents.toArray(new EEntity[ents.size()]), not working correctly -> AWU
//				exportPath, SIfc2x3.class);
		ifcModel.getBasicModel().exportModel(ents.toArray(new EEntity[ents.size()]), 
				exportPath, SIfc2x3.class);
		
		convertedIfcFile = new File(exportPath);
		
		LOGGER.info("...Export done!");
		long endTime = System.currentTimeMillis();
		long measuredTime = endTime-startTime;
		Date calculatedEndTime = new Date(measuredTime);
		LOGGER.info("Overall Time of Conversion: '"+sdf.format(calculatedEndTime)+"'");
		
		parser.stop();
	}
	
	/**
	 * Catches all provided GlobalIds of the available entities in the model.
	 * 
	 * @throws SdaiException
	 */
	private void catchAvailableGuids() throws SdaiException {
		availableGuids = new HashSet<String>();
		EEntity[] ifcRootEntities = ifcModel.getBasicModel().getEntitiesOf(EIfcroot.class);
		for(EEntity entity : ifcRootEntities) {
			EIfcroot ifcRootEntity = (EIfcroot) entity;
			availableGuids.add(ifcModel.getGlobalId(ifcRootEntity));
		}
	}
	
	/** 
	 * Creates a unique Global Identifier for the IFC element.
	 * @return The created GlobalId for the IFC entity.
	 */
	private String createGuid() {
		String guid = UUID.randomUUID().toString().replaceAll("-", "").substring(0,22);
//		HINT: recursion!
		if(availableGuids.contains(guid)) {
			guid = createGuid();
		}
		availableGuids.add(guid);
		return guid;
	}
	
	private EIfcownerhistory getOwnerHistory() throws SdaiException{
		if(ownerHistory != null) { return ownerHistory;}
		else {
			ownerHistory = (EIfcownerhistory) ifcModel.getBasicModel().getEntitiesOf(EIfcownerhistory.class)[0];
			if(ownerHistory == null) {
				EIfcperson person = (EIfcperson) sdaiModel.createEntityInstance(EIfcperson.class);
				person.setFamilyname(person, "TU Dresden");
				person.setGivenname(person, "Unknown");
				person.setId(person, "42542");
				EIfcorganization organization = (EIfcorganization) sdaiModel.createEntityInstance(EIfcorganization.class);
				organization.setName(organization, "Technische Universitaet Dresden, Faculty of Construction Informatics");
				organization.setId(organization, "TU Dresden, CIB");
				EIfcpersonandorganization personAndOrganization = (EIfcpersonandorganization) sdaiModel.createEntityInstance(EIfcpersonandorganization.class);
				personAndOrganization.setTheorganization(personAndOrganization, organization);
				EIfcownerhistory ownerHistory = (EIfcownerhistory) sdaiModel.createEntityInstance(EIfcownerhistory.class);
//				ownerHistory.setState(ownerHistory, EIfcstateenum.READWRITE);
				ownerHistory.setChangeaction(ownerHistory, EIfcchangeactionenum.ADDED);
				ownerHistory.setCreationdate(ownerHistory, Math.round(System.currentTimeMillis()/1000));
//				ownerHistory.setLastmodifieddate(ownerHistory, null);
				ownerHistory.setLastmodifyinguser(ownerHistory, personAndOrganization);
				ownerHistory.setOwningapplication(ownerHistory, (EIfcapplication) ifcModel.getBasicModel().getEntitiesOf(EIfcapplication.class)[0]);
				ownerHistory.setOwninguser(ownerHistory, personAndOrganization);
				this.ownerHistory = ownerHistory;
			}
		}		
		return ownerHistory;
	}
	
	public List<EIfcspace> getSpaces() throws SdaiException{
		List<EIfcspace> bsproSpaces = new ArrayList<EIfcspace>();
		EIfcspace[] spaces = ifcModel.getSpaces();
				
		NodeList xmlSpaces = ((Element)root).getElementsByTagName(XML_SPACE);
		
		for(int i = 0; i < xmlSpaces.getLength(); i++) {
			Node xmlSpace = xmlSpaces.item(i);
			Element element = (Element)xmlSpace;
			
			NodeList xmlSpaceDataList = element.getElementsByTagName(XML_SPACEDATA);
			for(int sdl = 0; sdl < xmlSpaceDataList.getLength(); sdl++) {
				Node xmlSpaceDataNode = xmlSpaceDataList.item(sdl);
				NodeList guids = ((Element)xmlSpaceDataNode).getElementsByTagName(XML_GUID);
				assert guids.getLength() == 1; // because we are in <space><spacedata> we cannot have more than 1 <guid> tag
				for(int j = 0; j < guids.getLength(); j++) {
					Node guidNode = guids.item(j);
					String guid = guidNode.getTextContent().trim();
					
					EIfcspace space = findSpace(spaces, guid);
					bsproSpaces.add(space);
				}
			}				
		}		
		return bsproSpaces;	
	}
	
	/**
	 * Add property set to space with base quantities like gross area and gross volume. 
	 *
	 * @param space
	 * @throws SdaiException
	 */
	private void addPropertySetToIfcSpace() throws SdaiException{
		if(currentSpace.testGlobalid(currentSpace)){		
			double conversionValue = getConversionFactor();
			String guid = currentSpace.getGlobalid(currentSpace);
			Node xmlSpaceNode = findXmlSpaceNode(guid);
//			calculated area of space - TODO is is net or gross area?
			Node xmlCalculatedArea = searchNode(xmlSpaceNode, XML_CALCULATED_AREA);
			String calculatedArea = xmlCalculatedArea.getTextContent().trim();
			double calculatedAreaValue = 0.0;
			try {
				calculatedAreaValue = Double.valueOf(calculatedArea) * conversionValue;
			} catch(NumberFormatException e) {
				LOGGER.warn(e.getMessage());
			}
			
//			calculated volume of space - TODO is is net or gross volume?
			Node xmlCalculatedVolume = searchNode(xmlSpaceNode, XML_CALCULATED_VOLUME);
			String calculatedVolume = xmlCalculatedVolume.getTextContent().trim();
			double calculatedVolumeValue = 0.0;
			try {
				calculatedVolumeValue = Double.valueOf(calculatedVolume) * conversionValue;
			} catch(NumberFormatException e) {
				LOGGER.warn(e.getMessage());
			}							
			
//			search existing Quantity Sets
//			EIfcphysicalquantity[] physicalQuantities1 = ifcDataModel.getElementQuantity(space, QUANTITY_GROSS_AREA);
//			EIfcphysicalquantity[] physicalQuantities2 = ifcDataModel.getElementQuantity(space, QUANTITY_GROSS_VOLUME);
			Set<EIfcquantityarea> physicalQuantities1 = ifcModel.getElementQuantity(currentSpace, EIfcquantityarea.class);
			Set<EIfcquantityvolume> physicalQuantities2 = ifcModel.getElementQuantity(currentSpace, EIfcquantityvolume.class);
			if(physicalQuantities1.size() > 0 && physicalQuantities2.size() > 0) {
				LOGGER.info("...Element quantity sets "+QUANTITY_GROSS_AREA+" and "+QUANTITY_GROSS_VOLUME+" already exists...");
			} else {
				EIfcquantityarea quantityArea = (EIfcquantityarea) sdaiModel.createEntityInstance(EIfcquantityarea.class);
				quantityArea.setName(quantityArea, QUANTITY_GROSS_AREA);
				quantityArea.setAreavalue(quantityArea, calculatedAreaValue);
				EIfcquantityvolume quantityVolume = (EIfcquantityvolume) sdaiModel.createEntityInstance(EIfcquantityvolume.class);
				quantityVolume.setName(quantityVolume, QUANTITY_GROSS_VOLUME);
				quantityVolume.setVolumevalue(quantityVolume, calculatedVolumeValue);
				
				EIfcreldefinesbyproperties relDefinesByProperties = (EIfcreldefinesbyproperties) sdaiModel.createEntityInstance(EIfcreldefinesbyproperties.class);
				relDefinesByProperties.setGlobalid(relDefinesByProperties, createGuid());
				relDefinesByProperties.setOwnerhistory(relDefinesByProperties, getOwnerHistory());
				relDefinesByProperties.setDescription(relDefinesByProperties, PSET_AREA_VOLUME);
				
				EIfcelementquantity elementQuantity = (EIfcelementquantity) sdaiModel.createEntityInstance(EIfcelementquantity.class);
				elementQuantity.setGlobalid(elementQuantity, createGuid());
				elementQuantity.setOwnerhistory(elementQuantity, getOwnerHistory());
				elementQuantity.setName(elementQuantity, BASE_QUANTITIES);
				
				AIfcphysicalquantity physicalQuantity = (AIfcphysicalquantity) elementQuantity.createQuantities(elementQuantity);
				physicalQuantity.addUnordered(quantityArea);
				physicalQuantity.addUnordered(quantityVolume);
//				doesn't work!
//				physicalQuantity.addByIndex(1, quantityArea);
//				physicalQuantity.addByIndex(2, quantityVolume);
				
				relDefinesByProperties.setRelatingpropertydefinition(relDefinesByProperties, elementQuantity);	
				
				AIfcobject ifcObjectAggregate = relDefinesByProperties.createRelatedobjects(relDefinesByProperties);
				ifcObjectAggregate.addUnordered(currentSpace);
//				doesn't work!
//				ifcObjectAggregate.addByIndex(1, space);
			}
		}
	}
	
	/**
	 * Add property set to IfcElement with azimuth and tilt values.
	 * 
	 * @param element
	 * @param simplifiedSpaceBoundary
	 * @throws SdaiException
	 */
	private void addPropertySetToIfcElement(EIfcelement element, Node simplifiedSpaceBoundary) throws SdaiException{		
//		azimuth of space boundary 
		Node xmlAzimuth = searchNode(simplifiedSpaceBoundary, XML_AZIMUTH);
		String azimuth = xmlAzimuth.getTextContent().trim();
//		tilt of space boundary		
		Node xmlOutwardNormal = searchNode(simplifiedSpaceBoundary, XML_OUTWARD_NORMAL);
		Node xmlTilt = searchNode(xmlOutwardNormal, XML_TILT);
		String tilt = xmlTilt.getTextContent().trim();
		
//		search existing Quantity Sets
		List<EIfcphysicalquantity> physicalQuantities1 = ifcModel.getElementQuantity(element, QUANTITY_AZIMUTH);
		List<EIfcphysicalquantity> physicalQuantities2 = ifcModel.getElementQuantity(element, QUANTITY_TILT);

		if(physicalQuantities1.size() > 0 && physicalQuantities2.size() > 0) {
			LOGGER.debug("...Element quantity sets "+QUANTITY_AZIMUTH+" and "+QUANTITY_TILT+" already exists...");
		} else {	

			EIfcelementquantity elementQuantity = (EIfcelementquantity) sdaiModel.createEntityInstance(EIfcelementquantity.class);
			elementQuantity.setGlobalid(elementQuantity, createGuid());
			elementQuantity.setOwnerhistory(elementQuantity, getOwnerHistory());
			elementQuantity.setName(elementQuantity, BASE_QUANTITIES);	
			AIfcphysicalquantity physicalQuantityAggregate = elementQuantity.createQuantities(elementQuantity);
			double azimuthValue = 0.0;
			try {
				azimuthValue = Double.valueOf(azimuth);
				EIfcquantitylength quantityAzimuth = (EIfcquantitylength) sdaiModel.createEntityInstance(EIfcquantitylength.class);
				quantityAzimuth.setName(quantityAzimuth, QUANTITY_AZIMUTH);
				quantityAzimuth.setLengthvalue(quantityAzimuth, azimuthValue);
				physicalQuantityAggregate.addUnordered(quantityAzimuth);
			} catch (NumberFormatException e) {
				LOGGER.debug("Number format is not permitted: "+e.getMessage());
			}
			
			try {
				double tiltValue = Double.valueOf(tilt);
				EIfcquantitylength quantityTilt = (EIfcquantitylength) sdaiModel.createEntityInstance(EIfcquantitylength.class);
				quantityTilt.setName(quantityTilt, QUANTITY_TILT);
				quantityTilt.setLengthvalue(quantityTilt, tiltValue);
				physicalQuantityAggregate.addUnordered(quantityTilt);
			} catch (NumberFormatException e) {
				LOGGER.debug("Number format is not permitted: "+e.getMessage());
			}
			
			EIfcreldefinesbyproperties relDefinesByProperties = (EIfcreldefinesbyproperties) sdaiModel.createEntityInstance(EIfcreldefinesbyproperties.class);
			relDefinesByProperties.setGlobalid(relDefinesByProperties, createGuid());
			relDefinesByProperties.setOwnerhistory(relDefinesByProperties, getOwnerHistory());
			relDefinesByProperties.setDescription(relDefinesByProperties, PSET_AZIMUTH_TILT);
			relDefinesByProperties.setRelatingpropertydefinition(relDefinesByProperties, elementQuantity);
	
			AIfcobject ifcObjectAggregate = relDefinesByProperties.createRelatedobjects(relDefinesByProperties);
			ifcObjectAggregate.addUnordered(element);
		}
		
	}
	
	/**
	 * 
	 * @param spaceBoundary
	 * @throws SdaiException 
	 */
	private void attachBasisSurface(EIfcspace space, EIfcrelspaceboundary secondLevelSpaceBoundary, 
			EIfcelement element, EIfccurveboundedplane curveBoundedPlane) throws SdaiException {
		EIfcplane plane = (EIfcplane) sdaiModel.createEntityInstance(EIfcplane.class);
		
		EIfcobjectplacement objectPlacement = space.getObjectplacement(space);
		if(objectPlacement instanceof EIfclocalplacement) {
			EIfclocalplacement localPlacement = (EIfclocalplacement) objectPlacement;
			EEntity ifcAxis2Placement = localPlacement.getRelativeplacement(localPlacement);
			if(ifcAxis2Placement instanceof EIfcaxis2placement3d){
				EIfcaxis2placement3d ifcaxis2placement3d = (EIfcaxis2placement3d)ifcAxis2Placement;
				LOGGER.debug("Attach BasisSurface with the LocalPlacement of the space");
				plane.setPosition(plane, ifcaxis2placement3d);				
				curveBoundedPlane.setBasissurface(curveBoundedPlane, plane);
			}
		}
	}
	
	private EIfcspace findSpace(EIfcspace[] spaces, String guid) throws SdaiException{
		for(EIfcspace space : spaces) {
			if(space.testGlobalid(space)) {
				if(space.getGlobalid(space).equals(guid)) {
					return space;
				}
			}
		}
		return null;
	}
	
	private Node findXmlSpaceNode(String guid) {
//		guid = guid.trim();
		NodeList xmlSpaces = ((Element)root).getElementsByTagName(XML_SPACE);
		for(int i= 0; i < xmlSpaces.getLength(); i++) {
			Node xmlSpace = xmlSpaces.item(i);
			NodeList xmlSpaceDataList = ((Element)xmlSpace).getElementsByTagName(XML_SPACEDATA);
			for(int k = 0; k < xmlSpaceDataList.getLength();k++) {
				Node xmlSpaceData = xmlSpaceDataList.item(k);
				NodeList attributes = xmlSpaceData.getChildNodes();
				for(int j = 0; j < attributes.getLength(); j++) {
					Node attribute = attributes.item(j);
					if(attribute.getNodeName().equals(XML_GUID)) {
						if(guid.equals(attribute.getTextContent().trim())){
							return xmlSpace;
						}
					}
				}
			}
		}
		return null;
	}
	
	private Node searchNode(Node parentNode, String nodeName) {
		NodeList children = parentNode.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if(child.getNodeName().equals(nodeName)){
				return child;
			}
		}
		return null;
	}
	
	private Node[] searchNodes(Node parentNode, String nodeName) {
		List<Node> nodes = new ArrayList<Node>();
		NodeList children = parentNode.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if(child.getNodeName().equals(nodeName)){
				nodes.add(child);
			}
		}
		return nodes.toArray(new Node[nodes.size()]);
	}
	
	private int getInternalOrExternalInteger(String inOrEx) {	
		if(inOrEx.equals(EIfcinternalorexternalenum.toString(EIfcinternalorexternalenum.EXTERNAL))) {
			return EIfcinternalorexternalenum.EXTERNAL;
		} else if(inOrEx.equals(EIfcinternalorexternalenum.toString(EIfcinternalorexternalenum.INTERNAL))){
			return EIfcinternalorexternalenum.INTERNAL;
		} else {
			return EIfcinternalorexternalenum.NOTDEFINED;
		}
	}
	
	/**
	 * Gets the created IfcVirtualElement by BSPro and add it to the model.
	 * 
	 * @param simplifiedSpaceBoundaryNode
	 * @param guidOfVirtualElement
	 * @return
	 */
	private EIfcvirtualelement getVirtualElement(Node simplifiedSpaceBoundaryNode, String guidOfVirtualElement) throws SdaiException{
		EIfcvirtualelement virtualElement = (EIfcvirtualelement) sdaiModel.createEntityInstance(EIfcvirtualelement.class);
		virtualElement.setGlobalid(virtualElement, guidOfVirtualElement);
		virtualElement.setOwnerhistory(virtualElement, getOwnerHistory());
		
		LOGGER.debug("... Virtual Element " + virtualElement +" created successfully...");
		return virtualElement;
	}
	
	/**
	 * Evaluates the conversion factor from BSProLib to IFC model.
	 * 
	 * HINT: Its only the conversion factor per metre. If millimetre is specified in IFC as IfcLengthUnit than it will return 1000.
	 * @param siUnit
	 * @return
	 * @throws SdaiException
	 */
	private double getConversionFactor() throws SdaiException {		
		Node xmlSpaceBoundaryExportNode = searchNode(doc, XML_SPACE_BOUNDARY_EXPORT);
		Node xmlPreferencesNode = searchNode(xmlSpaceBoundaryExportNode, XML_PREFERENCES);
		Node xmlLengthUnitNode = searchNode(xmlPreferencesNode, XML_LENGTH_UNIT);
		String lengthUnit = xmlLengthUnitNode.getTextContent().trim();
		
//		Value Conversion - BSPro uses millimetres
		double bsproConversionValuePerMetre = 1.0; // 1 metre
		
		if(lengthUnit.equalsIgnoreCase("mm")) { // in BSPro it is 'mm'
			bsproConversionValuePerMetre = 1.0/1000.0;
		}
		Set<EIfcsiunit> siUnits = ifcModel.getIfcSIUnits(ifcModel.getIfcProject());
		double ifcLengthUnitConversionFactorPerMetre = 1.0;
		for(EIfcsiunit siUnit: siUnits) {
			if(siUnit.getUnittype(siUnit) == EIfcunitenum.LENGTHUNIT) {				
				double approximativeValue = 1.0; // approximative value is 1 metre
				double conversionValue = approximativeValue; 
				int siUnitNameEnum = siUnit.getName(siUnit);
//				should be METRE
				IfcSIUnitNameEnum unitName = IfcSIUnitNameEnum.valueOf(EIfcsiunitname.toString(siUnitNameEnum));
				if(unitName != null) {		
					if(siUnit.testPrefix(siUnit)){
						int siPrefix = siUnit.getPrefix(siUnit);
						IfcSIPrefix prefix = IfcSIPrefix.valueOf(EIfcsiprefix.toString(siPrefix));
						conversionValue = approximativeValue/prefix.getConversionValue(); // conversion factor per approximative value
					}
				}
				// no prefix given -> take approximative value which is in IFC 1 metre
				ifcLengthUnitConversionFactorPerMetre = conversionValue;
			}
		}
		
		double value = ifcLengthUnitConversionFactorPerMetre * bsproConversionValuePerMetre;		
		return value;
	}
	
	public void createSecondLevelSpaceBoundaries() throws SdaiException {		
		spacesToAnalyze = getSpaces();
		LOGGER.info("... avalailable Spaces: "+spacesToAnalyze.size() + " ...");
		if(spacesToAnalyze.size() > 0) {
			currentSpace = spacesToAnalyze.get(0);
		}
		
		EIfcelement[] elements = ifcModel.getIfcElements();
		
		double conversionValue = getConversionFactor();
		
		Set<EIfcrelspaceboundary> spaceBoundaries = new HashSet<EIfcrelspaceboundary>();
		EIfcspace space = currentSpace;
		LOGGER.debug("...get 2nd Level Space Boundaries of Space "+space+" and using conversion factor '"+conversionValue+"'");	
		if(space != null) {
			String spaceGuid = space.getGlobalid(space);
			Node xmlSpaceNode = findXmlSpaceNode(spaceGuid);
			
//			add space information like area, volume...
			addPropertySetToIfcSpace();
			
			/* STEP 1 in Impl. Guide
			 * 
			 * Define the relationship between the space and a bounding element. Note that each space boundary 
			 * requires a unique IfcRelSpaceBoundary object.
			 * - Create an instance of IfcRelSpaceBoundary
			 * - Set the relationship to the space IfcRelSpaceBoundary.RelatingSpace
			 * - Set the relationship to the element IfcRelSpaceBoundary.RelatedBuildingElement
			 * - Define general attributes
			 * 		+ GloballyUniqueId
			 * 		+ OwnerHistory
			 * 
			 * Provide the Name attribute for the space boundary
			 * - implementation agreement (#CV-2x3-151):
			 * 		+ For 2nd level space boundaries -- set the Name attribute to ‘2ndLevel’
			 * 		+ Note: If the exporting application is able to distinguish between 2nd level space boundary 
			 * 		  types (a), (b), and (c), the specific type will be identified through the Description attribute -- 
			 * 		  set to 2a', '2b', and '2c' respectively.
			 * 
			 * Determine if it is an internal or external boundary (external = adjacent to open space/outside)
			 * 	implementation agreement:
			 * 		+ For 2nd level space boundaries -- set the InternalOrExternalBoundary attribute to ‘INTERNAL’ or 'EXTERNAL' 
			 * 		as appropriate. Note: although the value .NotDefined. is allowed by the IFC Schema, it is not allowed in this 
			 * 		context by implementers’ agreement.
			 * 
			 * Set the PhysicalOrVirtualBoundary attribute, based on the type of the boundary element
			 * 	- Set the value to ‘VIRTUAL’, if the bounding element is:
			 * 		+ An IfcVirtualElement
			 * 		+ An IfcOpeningElement with empty set of HasFillings (i.e. an opening, that is not filled by a window or door)
			 * 	- Set the value to ‘PHYSICAL’ if the bounding element is any other subtype of IfcElement
			 * 	- A value of ‘NOTDEFINED’ may be used in ambiguous cases, but is generally discouraged.
			 * Define the connection geometry (see Step 2, 3.3.2) only if
			 * 	- it is not a logical boundary and
			 * 	- space boundaries can be established (e.g. in case of having a b-rep wall geometry the connection geometry 
			 * 	might not be calculable from the CAD system)
			 */
			
			Node[] xmlSecondLevelSpaceBoundariesList = searchNodes(xmlSpaceNode, XML_SECOND_LEVEL_SPACEBOUNDARIES);
			for(int i = 0; i < xmlSecondLevelSpaceBoundariesList.length; i++) {
				Node xmlSecondLevelSpaceBoundaries = xmlSecondLevelSpaceBoundariesList[i];
				NodeList simplifiedSpaceBoundaries = ((Element)xmlSecondLevelSpaceBoundaries).getElementsByTagName(XML_SIMPLIFIED_SPACEBOUNDARY);
				for(int j = 0; j < simplifiedSpaceBoundaries.getLength(); j++) {				
					Node simplifiedSpaceBoundary = simplifiedSpaceBoundaries.item(j);														
					Node internalOrExternal = searchNode(simplifiedSpaceBoundary, XML_INTERNAL_OR_EXTERNAL_BOUNDARY);										
					String inOrEx = internalOrExternal.getTextContent().trim();					
					Node xmlGuidOfRelatedElement = searchNode(simplifiedSpaceBoundary, XML_GUID);									
					String guidOfRelatedElement = xmlGuidOfRelatedElement.getTextContent().trim();					
					EIfcelement element = null;
					for(EIfcelement e : elements) {
						if(e.testGlobalid(e)) {
							if(e.getGlobalid(e).equals(guidOfRelatedElement)) {
								element = e;
							}
						}
					}							
					if(element == null) { // if it is 0 it is added by BSPro
						element = getVirtualElement(simplifiedSpaceBoundary, guidOfRelatedElement);
					}				
					
					Node xmlPoints = searchNode(simplifiedSpaceBoundary, XML_POINTS);
					
					NodeList xmlPointsList = xmlPoints.getChildNodes();
//					create IfcPolyline
					EIfcpolyline polyLine = createIfcPolyline(conversionValue, xmlPointsList);	
					
					EIfcrelspaceboundary secondLevelSpaceBoundary = (EIfcrelspaceboundary)sdaiModel.createEntityInstance(EIfcrelspaceboundary.class);
					
					EIfccurveboundedplane curveBoundedPlane = (EIfccurveboundedplane) sdaiModel.createEntityInstance(EIfccurveboundedplane.class);
					curveBoundedPlane.setOuterboundary(curveBoundedPlane, polyLine);
//					create empty inner boundaries
					curveBoundedPlane.createInnerboundaries(curveBoundedPlane);
					/* STEP 2
					 * Connection Geometry have to be defined relative to the space, i.e. using the local coordinate system
					 * of the Space. This is defined by the attribute IfcConnectionSurfaceGeometry.SurfaceOnRelatingElement
					 */
					attachBasisSurface(space, secondLevelSpaceBoundary, element, curveBoundedPlane);
					
					/* STEP 3
					 * The connection geometry as defined in step 2 has to be linked with an instance of IfcRelSpaceBoundary. 
					 * This is done by creating an IfcConnectionSurfaceGeometry object, which defines a link to the connection 
					 * geometry via the first attribute (IfcConnectionSurfaceGeometry.SurfaceOnRelatingElement).
					 * Please do not use the second attribute (IfcConnectionSurfaceGeometry.SurfaceOnRelatedElement) together with 
					 * the connection geometry that is defined relative to the space. This would lead to a wrong placement and orientation 
					 * of the connection geometry. Instead, this attribute can be empty as described in step 2.
					 */
					EIfcconnectionsurfacegeometry connectionGeometry = (EIfcconnectionsurfacegeometry) 
						sdaiModel.createEntityInstance(EIfcconnectionsurfacegeometry.class);
					connectionGeometry.setSurfaceonrelatingelement(connectionGeometry, curveBoundedPlane);
					
					Node xmlSpaceBoundaryArea = searchNode(simplifiedSpaceBoundary, XML_SPACEBOUNDARY_AREA);
//					get space boundary area
					String spaceBoundaryArea = xmlSpaceBoundaryArea.getTextContent().trim();
//					convert value if necessary
					Double spaceBoundaryAreaValue = Double.valueOf(spaceBoundaryArea) * conversionValue;
					
//					2a for adjacent Space, 2b for adjacent BuildingElement - default is BuildingElement 
					String spaceBoundaryLevel = "2b";
					
					Node xmlAdjacentSpace = searchNode(simplifiedSpaceBoundary, XML_ADJACENT_SPACE);
					if(xmlAdjacentSpace != null) {
						String adjacentSpace = xmlAdjacentSpace.getTextContent().trim();
						if(adjacentSpace.length() > 0) {
							spaceBoundaryLevel = "2a";
						}
					}
					
					String spaceBoundaryGuid = createGuid();
					secondLevelSpaceBoundary.setGlobalid(secondLevelSpaceBoundary, spaceBoundaryGuid);
					secondLevelSpaceBoundary.setName(secondLevelSpaceBoundary, SECOND_LEVEL_SB_NAME);
//					HINT: space boundary in description because we can't attach a relation to a relation.
					String description = spaceBoundaryArea.length() > 0 ? spaceBoundaryLevel+" , "+DESCRIPTION_SPACEBOUNDARY_AREA+
							spaceBoundaryAreaValue : spaceBoundaryLevel;
					secondLevelSpaceBoundary.setDescription(secondLevelSpaceBoundary, description);
					secondLevelSpaceBoundary.setRelatingspace(secondLevelSpaceBoundary, space);
					secondLevelSpaceBoundary.setRelatedbuildingelement(secondLevelSpaceBoundary, element);
					secondLevelSpaceBoundary.setConnectiongeometry(secondLevelSpaceBoundary, connectionGeometry);
					secondLevelSpaceBoundary.setInternalorexternalboundary(secondLevelSpaceBoundary, getInternalOrExternalInteger(inOrEx));
					secondLevelSpaceBoundary.setOwnerhistory(secondLevelSpaceBoundary, getOwnerHistory());

					int physVirt = EIfcphysicalorvirtualenum.NOTDEFINED;
					if(element instanceof EIfcvirtualelement || element instanceof EIfcopeningelement) {
						physVirt = EIfcphysicalorvirtualenum.VIRTUAL;
					} else if(element instanceof EIfcbuildingelement) {
						physVirt = EIfcphysicalorvirtualenum.PHYSICAL;
					}
					secondLevelSpaceBoundary.setPhysicalorvirtualboundary(secondLevelSpaceBoundary, physVirt);
					
					Node xmlElement = searchNode(simplifiedSpaceBoundary, XML_GUID);
					String guidElement = xmlElement.getTextContent().trim();
					EIfcelement ifcElement = (EIfcelement) ifcModel.getIfcEntity(guidElement, EIfcelement.class);

//					add additional information of the Space Boundary like azimuth, tilt...
					addPropertySetToIfcElement(ifcElement, simplifiedSpaceBoundary);
					
					LOGGER.info("... 2nd Level Space Boundary "+secondLevelSpaceBoundary+" created successfully...");
					
					spaceBoundaries.add(secondLevelSpaceBoundary);
					LOGGER.info("... added to Space "+space);
				}
			}
		}	
		LOGGER.debug("Creating space boundaries of current space '"+space+"' successfully");	
		LOGGER.debug(""+spacesToAnalyze);
		synchronized(SpaceBoundaryConversionService.class) {
			spacesToAnalyze.remove(space);
			currentSpace = (spacesToAnalyze.size() > 0) ? spacesToAnalyze.get(0) : null;
		}
		LOGGER.debug(""+currentSpace);
	}

	/**
	 * 	@see IMPLEMENTATION GUIDE: SPACE BOUNDARIES FOR ENERGY ANALYSIS page 11
	 * IFC-Documentation of IfcCurveBoundedPlane: The BasisSurface is an IfcPlane that establishes 
	 * the position coordinate system by SELF\IfcElementarySurface.Position. 
	 * The OuterBoundary and the InnerBoundaries (if provided) shall lie on the surface of IfcPlane. 
	 * Therefore the IfcCurve's establishing the outer and inner boundaries shall be:
	 *	- either a 2D curve within the XY plane of the position coordinate sytem of IfcPlane
	 *	- or a 3D curve with all coordinates having a z value = 0.
	 * 
	 * @param conversionValue
	 * @param xmlPointsList
	 * @return
	 * @throws SdaiException
	 */
	private EIfcpolyline createIfcPolyline(double conversionValue, NodeList xmlPointsList) throws SdaiException {
		EIfcpolyline polyLine = (EIfcpolyline) sdaiModel.createEntityInstance(EIfcpolyline.class);
							
		AIfccartesianpoint points = polyLine.createPoints(polyLine);
		LOGGER.debug("...creating IfcPolyline with Coordinates...");				
		for(int pointNumber = 0; pointNumber < xmlPointsList.getLength(); pointNumber++) {
			Node xmlPoint = xmlPointsList.item(pointNumber);
			if(xmlPoint.getTextContent() == null) continue;
			if(xmlPoint.getTextContent().trim().length() == 0) continue;
		
			EIfccartesianpoint point1 = (EIfccartesianpoint) sdaiModel.createEntityInstance(EIfccartesianpoint.class);
			A_double coordinates = point1.createCoordinates(point1);
			Node xNode = searchNode(xmlPoint, XML_X);
			Node yNode = searchNode(xmlPoint, XML_Y);
			Node zNode = searchNode(xmlPoint, XML_Z);
			double x = new Double(xNode.getTextContent().trim()) * conversionValue;
			double y = new Double(yNode.getTextContent().trim()) * conversionValue;
			double z = new Double(zNode.getTextContent().trim()) * conversionValue;
			coordinates.addByIndex(1, x);
			coordinates.addByIndex(2, y);
//			coordinates.addByIndex(3, 0.0); // z-value must be '0'	
			coordinates.addByIndex(3, z);				
			points.addUnordered(point1);
			LOGGER.debug("\t '"+coordinates+"'");
		}
		return polyLine;
	}
	
	/**
	 * Deletes 1st Level Space Boundaries of a IfcSpace.
	 * 
	 * Currently we assume that all needed information for the creation of the 2nd Level will be provided by BSProLib - 
	 * so we can delete all 1st level elements.
	 * 
	 * @param space The space with 1st Level Space Boundaries.
	 * @throws SdaiException
	 */
	private void deleteAllFirstLevelSpaceBoundariesOfSpace(EIfcspace space) throws SdaiException{
		LOGGER.info("... deleting old 1st Level Space Boundaries...");
		EIfcrelspaceboundary[] physicalSpaceBoundaries = ifcModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
		for(EIfcrelspaceboundary spaceBoundary : physicalSpaceBoundaries) {
////			delete the connection geometry if not used anymore FIXME
//			deleteConnectionGeometry(spaceBoundary);
//			delete the space boundary
			spaceBoundary.deleteApplicationInstance();
		}
		EIfcrelspaceboundary[] virtualSpaceBoundaries = ifcModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.VIRTUAL);
		for(EIfcrelspaceboundary spaceBoundary : virtualSpaceBoundaries) {
////			delete the connection geometry if not used anymore FIXME
//			deleteConnectionGeometry(spaceBoundary);
//			delete the space boundary
			spaceBoundary.deleteApplicationInstance();
		}
	}
	
	/**
	 * Deletes 1st Level Space Boundaries in the model.
	 * 
	 * Currently we assume that all needed information for the creation of the 2nd Level will be provided by BSProLib - 
	 * so we can delete all 1st level elements.
	 * 
	 * @throws SdaiException
	 */
	public void deleteAllFirstLevelSpaceBoundaries() throws SdaiException{
		LOGGER.info("... deleting old 1st Level Space Boundaries...");
		EIfcrelspaceboundary[] spaceBoundaries = ifcModel.getBasicModel().getEntitiesOf(EIfcrelspaceboundary.class);
		for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
			if(spaceBoundary.testName(spaceBoundary)){
				if(spaceBoundary.getName(spaceBoundary).equals(SECOND_LEVEL_SB_NAME)) {
//					do nothing
				}
				else {
////				delete the connection geometry if not used anymore FIXME
//					deleteConnectionGeometry(spaceBoundary);
//					delete the space boundary
					spaceBoundary.deleteApplicationInstance();
				}
			}
			else {
	////			delete the connection geometry if not used anymore FIXME
	//			deleteConnectionGeometry(spaceBoundary);
	//			delete the space boundary
				spaceBoundary.deleteApplicationInstance();
			}

		}
	}
	
	@SuppressWarnings("unused")
	private void deleteConnectionGeometry(EIfcrelspaceboundary spaceBoundary) throws SdaiException {
		if(spaceBoundary.testConnectiongeometry(spaceBoundary)){
			EIfcconnectiongeometry connectionGeometry = spaceBoundary.getConnectiongeometry(spaceBoundary);
//			iterate over referenced entities, if they are not referenced by other entities we can delete them
			EEntity[] referencedEntities = ifcModel.getBasicModel().getAllReferences(connectionGeometry);
			for(EEntity entity : referencedEntities) {
				
			}
		}

	}
	
	public Document readFile(String filePath) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			LOGGER.info("Parsing XML file: "+filePath+" ...");
			doc = db.parse(new File(filePath));
			LOGGER.info("...parsing completed...");
			doc.getDocumentElement().normalize();
			LOGGER.info("...normalized...");
			
			root = doc.getFirstChild();
			
			return doc;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	private Document readStream() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			LOGGER.info("Reading input stream...");
			doc = db.parse(in);
			doc.getDocumentElement().normalize();
			
			root = doc.getFirstChild();
			return doc;
		} catch (ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
		} catch (SAXException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
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
				LOGGER.info("..."+descriptionEntry + " removed...");
				description.addByIndex(i, HEADER_2ND_LEVEL_DESCRIPTION);
				LOGGER.info("...ViewDefinition [CoordinationView, SpaceBoundary2ndLevelAddOnView] added successfully");
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
			LOGGER.error(e1.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
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
    	  LOGGER.error(e.getMessage());
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
    			LOGGER.error(ioe.getMessage());
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

	public List<EIfcspace> getSpacesToAnalyze() {
		return spacesToAnalyze;
	}

}
