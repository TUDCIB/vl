package de.tudresden.bau.cib.vl.core.simulation.energy.transformation;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import jsdai.lang.SdaiException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.tudresden.bau.cib.vl.core.simulation.energy.model.Material;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles.TheraklesClimateLocation;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles.TheraklesConstruction;

/**
 * @author ken
 * 
 * Creates the simulation model by using a IFC file converted by BSPro which includes ElementQuantities
 * like Volume and Area of a space.
 * 
 * NOTE: If we already have a IFC file with 2nd LEvel SpaceBoundaries and don't need BSPro we have to get the room volume
 * and room area on another way.
 *
 */
public class TheraklesSimulationModelGenerator {
	
	private static Logger LOGGER = LoggerFactory.getLogger(TheraklesSimulationModelGenerator.class);
	
	public static enum XML_TAGS {
		RoomModelProject, Room, Geometry, Height, Area, Constructions, RoomConstruction, orientation, inclination, R_ue, R_ui, ad, zoneT, AW, AF,
		constructionId, windowId, shadingTypeId, type, ClimateLocation, HeatingModel, MaxPower, ScheduleSetPoints, ConstantValue,
		CoolingModel, SupplyTemperature, MaxSupplyFlowRate, HeatRecoveryEfficiency, WeekDaySchedule, WeekEndSchedule, WeekEndDays,
		ScheduleVentilationRates, NaturalVentilationModel, ScheduleAirChangeRates, UserLoads, PersonLoad, ScheduleOccupancy, 
		ScheduleEquipmentLoad, ShadingControlModel, MaxRadiationIntensity, ScheduleShadingDegree, SimulationParameters, 
		TInitial, Albedo, GammaHC, GammaRad, DisableDifRad, AdditionalMassHeatCapacity, AdditionalMass
	};
	
	public static enum XML_TAGS_EMBEDDED_DATABASE {
		EmbeddedDataBase, Materials, Material, ConstructionTypes, ConstructionType, Layers, Layer, WindowTypes, WindowType, ShadingTypes
	};
	
	public static enum XML_TAG_ATTRIBUTES {
		Version, name, modelType, type, id, category, lambda, rho, cT, materialId, d, f, g, U
	};
	
	private static enum PSETS {
		Pset_Area_Volume, Pset_Azimuth_Tilt
	};
	
	private static String DESCRIPTION_SPACEBOUNDARY_AREA = "#spaceboundary_area=";
	
//	###############	CONSTANTS for this Prototype	#################
	private static final String THERAKLES_VERSION = "1.6";
	private static final String R_UE = "0.04";
	private static final String R_UI = "0.13";
	private static final String AD = "0.7";
	private static final String ZONET = "12";
	
//	#################################################################
	
	private double spaceArea = 0.0;
	private double spaceHeight = 0.0;
	
	private Document doc;
	private File simulationFile;
	private String exportPath;
	private TheraklesClimateLocation location;
	

	/**
	 * Therakles-SimulationModel
	 * 
	 * @param ifcModel
	 * @param ifcDataModel
	 * @param roomGuid
	 * @param exportPath
	 */
	public TheraklesSimulationModelGenerator(String exportPath, TheraklesClimateLocation location, double roomArea, double roomHeight) {
		this.exportPath = exportPath;
		this.spaceArea = roomArea;
		this.spaceHeight = roomHeight;	
		this.location = location;
	}
	
	public void buildSimulationModel(TheraklesConstruction[] constructions) {
		try {			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.newDocument();		

			doc.setXmlVersion("1.0");
			
			
			buildRoomModelProject(constructions);
		
			
// 			Write the DOM document to a file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "publicId");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "systemId");
		    Source source = new DOMSource(doc);
//		    if(!new File(exportPath).exists()) {
//		    	new File(exportPath).mkdir();
//		    }
//		    if(!exportPath.endsWith(File.separator)) {
//		    	exportPath += File.separator;
//		    }
//
//		    String spaceName = constructions[0].getIfcSpaceId();
//
//		    String outputPath = exportPath+"Room_"+spaceName+".rmxml";
		    
		    String outputPath = exportPath;
		    
		    simulationFile = new File(outputPath);
		    Result result = new StreamResult(simulationFile);
		    transformer.transform(source, result);
		    
		    LOGGER.debug("Output path is "+outputPath);
		    
////		    <!-- for testing purposes -->
//            //create string from xml tree
//            StringWriter sw = new StringWriter();
//            StreamResult stResult = new StreamResult(sw);
//            transformer.transform(source, stResult);
//            String xmlString = sw.toString();
//
//            //print xml
//            System.out.println("Here's the xml:\n\n" + xmlString);
			
		} catch (ParserConfigurationException e) {
			LOGGER.error(e.getMessage(),e);
		} catch (TransformerException e) {
			LOGGER.error(e.getMessage(),e);
		}
		
	}
	
	private void buildRoomModelProject(TheraklesConstruction[] constructions) {
		LOGGER.debug("Build Room Model Project...");
		Element xmlRoomModelProject = doc.createElement(XML_TAGS.RoomModelProject.toString());
		xmlRoomModelProject.setAttribute(XML_TAG_ATTRIBUTES.Version.toString(), THERAKLES_VERSION);
		doc.appendChild(xmlRoomModelProject);
		
		try {			
			buildRoom(xmlRoomModelProject, constructions);
			
//			EIfcspace[] spaces = ifcDataModel.getSpaces();
////			TODO parallelize it for many rooms - at this time take only one room
//		
//			if(spaces.length > 0) {
//				LOGGER.info("Build Room Model for Room "+spaces[0]);
//				buildRoom(xmlRoomModelProject, spaces[0]);			
//				
//				
//			} else {
//				LOGGER.info("No Spaces in the Building");
//			}
			
			appendEmbeddedDataBase(xmlRoomModelProject, constructions);
		
		} catch(SdaiException e) {
			LOGGER.error(e.getMessage(),e);
		}
		
		
		
	}
	
	/**
	 * TODO Die richtige Datenbank von IBK auslesen und die Selektionen dann an das Project hängen!
	 * 
	 * @param xmlRoomModelProject
	 * @param constructions
	 */
	private void appendEmbeddedDataBase(Element xmlRoomModelProject, TheraklesConstruction[] constructions) {
		LOGGER.debug("Appending Embedded Databases...");
//		<EmbeddedDatabase>
		Element xmlEmbeddedDatabase = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.EmbeddedDataBase.toString());
		xmlRoomModelProject.appendChild(xmlEmbeddedDatabase);
		
//		<!-- All available materials-->
//		<Materials>
		Element xmlMaterials = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Materials.toString());
		xmlEmbeddedDatabase.appendChild(xmlMaterials);
		
		Set<Material> allMaterials = new HashSet<Material>();
		for(TheraklesConstruction c : constructions) {
			allMaterials.addAll(c.getMaterials().keySet());
		}
//		<Material>
		for(Material m : allMaterials) {
			Element xmlMaterial = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Material.toString());
			xmlMaterial.setAttribute(XML_TAG_ATTRIBUTES.id.toString(), ""+m.getId());
			xmlMaterial.setAttribute(XML_TAG_ATTRIBUTES.category.toString(), ""+m.getCategory());
			xmlMaterial.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), m.getName());
			xmlMaterial.setAttribute(XML_TAG_ATTRIBUTES.lambda.toString(), ""+m.getLambda());
			xmlMaterial.setAttribute(XML_TAG_ATTRIBUTES.rho.toString(), ""+m.getRho());
			xmlMaterial.setAttribute(XML_TAG_ATTRIBUTES.cT.toString(), ""+m.getcT());
			xmlMaterials.appendChild(xmlMaterial);
		}
		
//		<ConstructionTypes>
		Element xmlConstructionTypes = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.ConstructionTypes.toString());
		xmlEmbeddedDatabase.appendChild(xmlConstructionTypes);
		
//		append construction types
		Set<Integer> alreadyDefinedConstructionTypes = new HashSet<Integer>();
//		<ConstructionType>
		for(TheraklesConstruction c : constructions) {
			int constructionTypeId = c.getConstructionTypeId();
			boolean canAdd = alreadyDefinedConstructionTypes.add(constructionTypeId);
			if(canAdd) {
				Element xmlConstructionType1 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.ConstructionType.toString());
				xmlConstructionType1.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), c.getName());
				xmlConstructionType1.setAttribute(XML_TAG_ATTRIBUTES.id.toString(), ""+c.getConstructionTypeId());
				xmlConstructionTypes.appendChild(xmlConstructionType1);
				
//		<Layers>
				Element xmlLayers1 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Layers.toString());
				xmlConstructionType1.appendChild(xmlLayers1);
				
//		<Layer>
				Map<Material, Double> materialsOfConstruction = c.getMaterials();
				
				for(Map.Entry<Material, Double> entry : materialsOfConstruction.entrySet()) {	
					Material m = entry.getKey();
					Double thickness = entry.getValue();
					Element xmlLayer1 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Layer.toString());
					xmlLayer1.setAttribute(XML_TAG_ATTRIBUTES.materialId.toString(), ""+m.getId());
					xmlLayer1.setAttribute(XML_TAG_ATTRIBUTES.d.toString(), ""+thickness);
					xmlLayers1.appendChild(xmlLayer1);
				}
			}			
		}
		
		
////		<Material>
//		Element xmlMaterial1 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Material.toString());
//		xmlMaterial1.setAttribute(XML_TAG_ATTRIBUTES.id.toString(), "166");
//		xmlMaterial1.setAttribute(XML_TAG_ATTRIBUTES.category.toString(), "6");
//		xmlMaterial1.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "de:Mineralische Dämmplatte|en:Minerally insulation board");
//		xmlMaterial1.setAttribute(XML_TAG_ATTRIBUTES.lambda.toString(), "0.043");
//		xmlMaterial1.setAttribute(XML_TAG_ATTRIBUTES.rho.toString(), "110");
//		xmlMaterial1.setAttribute(XML_TAG_ATTRIBUTES.cT.toString(), "1000");
//		xmlMaterials.appendChild(xmlMaterial1);
////		<Material>
//		Element xmlMaterial2 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Material.toString());
//		xmlMaterial2.setAttribute(XML_TAG_ATTRIBUTES.id.toString(), "81");
//		xmlMaterial2.setAttribute(XML_TAG_ATTRIBUTES.category.toString(), "7");
//		xmlMaterial2.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "de:Gipskartonplatte|en:Gypsum Board");
//		xmlMaterial2.setAttribute(XML_TAG_ATTRIBUTES.lambda.toString(), "0.2");
//		xmlMaterial2.setAttribute(XML_TAG_ATTRIBUTES.rho.toString(), "850");
//		xmlMaterial2.setAttribute(XML_TAG_ATTRIBUTES.cT.toString(), "850");
//		xmlMaterials.appendChild(xmlMaterial2);
////		<Material>
//		Element xmlMaterial3 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Material.toString());
//		xmlMaterial3.setAttribute(XML_TAG_ATTRIBUTES.id.toString(), "58");
//		xmlMaterial3.setAttribute(XML_TAG_ATTRIBUTES.category.toString(), "5");
//		xmlMaterial3.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "de:Beton|en:Concrete");
//		xmlMaterial3.setAttribute(XML_TAG_ATTRIBUTES.lambda.toString(), "2.1");
//		xmlMaterial3.setAttribute(XML_TAG_ATTRIBUTES.rho.toString(), "2320");
//		xmlMaterial3.setAttribute(XML_TAG_ATTRIBUTES.cT.toString(), "850");
//		xmlMaterials.appendChild(xmlMaterial3);
//		
////		<ConstructionTypes>
//		Element xmlConstructionTypes = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.ConstructionTypes.toString());
//		xmlEmbeddedDatabase.appendChild(xmlConstructionTypes);
//		
////		<ConstructionType>
//		Element xmlConstructionType1 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.ConstructionType.toString());
//		xmlConstructionType1.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "de:Betonwand, 20 cm|en:Concrete Wall, 20 cm");
//		xmlConstructionType1.setAttribute(XML_TAG_ATTRIBUTES.id.toString(), "2");
//		xmlConstructionTypes.appendChild(xmlConstructionType1);
//		
////		<Layers>
//		Element xmlLayers1 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Layers.toString());
//		xmlConstructionType1.appendChild(xmlLayers1);
//		
////		<Layer>
//		Element xmlLayer1 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Layer.toString());
//		xmlLayer1.setAttribute(XML_TAG_ATTRIBUTES.materialId.toString(), "58");
//		xmlLayer1.setAttribute(XML_TAG_ATTRIBUTES.d.toString(), "0.1");
//		xmlLayers1.appendChild(xmlLayer1);
//		
////		<ConstructionType>
//		Element xmlConstructionType2 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.ConstructionType.toString());
//		xmlConstructionType2.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "de:Betonfußboden, 20 cm / 2|en:Concrete slab, 20 cm / 2");
//		xmlConstructionType2.setAttribute(XML_TAG_ATTRIBUTES.id.toString(), "3");
//		xmlConstructionTypes.appendChild(xmlConstructionType2);
//		
////		<Layers>
//		Element xmlLayers2 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Layers.toString());
//		xmlConstructionType2.appendChild(xmlLayers2);
//		
////		<Layer>
//		Element xmlLayer2 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Layer.toString());
//		xmlLayer2.setAttribute(XML_TAG_ATTRIBUTES.materialId.toString(), "58");
//		xmlLayer2.setAttribute(XML_TAG_ATTRIBUTES.d.toString(), "0.1");
//		xmlLayers2.appendChild(xmlLayer2);
//		
////		<ConstructionType>
//		Element xmlConstructionType3 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.ConstructionType.toString());
//		xmlConstructionType3.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "de:Leichte Trennwand,  10 cm / 2|en:Light weight internal wall, 10 cm / 2");
//		xmlConstructionType3.setAttribute(XML_TAG_ATTRIBUTES.id.toString(), "1");
//		xmlConstructionTypes.appendChild(xmlConstructionType3);
//		
////		<Layers>
//		Element xmlLayers3 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Layers.toString());
//		xmlConstructionType3.appendChild(xmlLayers3);
//		
////		<Layer>
//		Element xmlLayer3 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Layer.toString());
//		xmlLayer3.setAttribute(XML_TAG_ATTRIBUTES.materialId.toString(), "166");
//		xmlLayer3.setAttribute(XML_TAG_ATTRIBUTES.d.toString(), "0.03");
//		xmlLayers3.appendChild(xmlLayer3);
////		<Layer>
//		Element xmlLayer4 = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.Layer.toString());
//		xmlLayer4.setAttribute(XML_TAG_ATTRIBUTES.materialId.toString(), "81");
//		xmlLayer4.setAttribute(XML_TAG_ATTRIBUTES.d.toString(), "0.02");
//		xmlLayers3.appendChild(xmlLayer4);
		
		
//		<WindowTypes>
		Element xmlWindowTypes = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.WindowTypes.toString());
		xmlEmbeddedDatabase.appendChild(xmlWindowTypes);
		
//		<WindowType>
		Element xmlWindowType = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.WindowType.toString());
		xmlWindowType.setAttribute(XML_TAG_ATTRIBUTES.id.toString(), "5");
		xmlWindowType.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "de:Absorptionsglas, doppelt (VDI 2078)");
		xmlWindowType.setAttribute(XML_TAG_ATTRIBUTES.f.toString(), "0.6");
		xmlWindowType.setAttribute(XML_TAG_ATTRIBUTES.g.toString(), "0.5655");
		xmlWindowType.setAttribute(XML_TAG_ATTRIBUTES.U.toString(), "1.2");
		xmlWindowTypes.appendChild(xmlWindowType);

//		<ShadingTypes>
		Element xmlShadingTypes = doc.createElement(XML_TAGS_EMBEDDED_DATABASE.ShadingTypes.toString());
		xmlEmbeddedDatabase.appendChild(xmlShadingTypes);
	}

	private void buildRoom(Node parentNode, TheraklesConstruction[] constructions) throws SdaiException{	
		LOGGER.info("Build Room...");
//		<Room>
		Element xmlRoom = doc.createElement(XML_TAGS.Room.toString());
		parentNode.appendChild(xmlRoom);
		
//		<Geometry>
		Element xmlGeometry = doc.createElement(XML_TAGS.Geometry.toString());
		xmlRoom.appendChild(xmlGeometry);
//		<Height>
		Element xmlHeight = doc.createElement(XML_TAGS.Height.toString());

		xmlHeight.setTextContent(""+spaceHeight);
		xmlGeometry.appendChild(xmlHeight);
//		<Area>
		Element xmlArea = doc.createElement(XML_TAGS.Area.toString());
		xmlArea.setTextContent(""+spaceArea);
		xmlGeometry.appendChild(xmlArea);
		
		
//		<Constructions>
		Element xmlConstructions = doc.createElement(XML_TAGS.Constructions.toString());
		xmlGeometry.appendChild(xmlConstructions);
		
		for(TheraklesConstruction construction : constructions) {
//			createRoomConstruction(xmlConstructions, space, wall, windows);		
	
			createRoomConstruction(xmlConstructions, construction);	
		}
		
		createClimateLocation(xmlRoom);
		
		createHeatingModel(xmlRoom);
		
		createCoolingModel(xmlRoom);
		
		createNaturalVentilationModel(xmlRoom);
		
		createUserLoads(xmlRoom);
		
		createShadingControlModel(xmlRoom);
		
		createSimulationParameters(xmlRoom);
	}
	
	private void createSimulationParameters(Element xmlRoom) {
		LOGGER.debug("Create Simulation Parameters...");
//		<SimulationParameters>
		Element xmlSimulationParameters = doc.createElement(XML_TAGS.SimulationParameters.toString());
		xmlRoom.appendChild(xmlSimulationParameters);
		
//		<TInitial>
		Element xmlTInitial = doc.createElement(XML_TAGS.TInitial.toString());
		xmlTInitial.setTextContent("20");
		xmlSimulationParameters.appendChild(xmlTInitial);
//		<Albedo>
		Element xmlAlbedo = doc.createElement(XML_TAGS.Albedo.toString());
		xmlAlbedo.setTextContent("0.2");
		xmlSimulationParameters.appendChild(xmlAlbedo);
//		<GammaHC>
		Element xmlGammaHC = doc.createElement(XML_TAGS.GammaHC.toString());
		xmlGammaHC.setTextContent("0.5");
		xmlSimulationParameters.appendChild(xmlGammaHC);
//		<GammaRad>
		Element xmlGammaRad = doc.createElement(XML_TAGS.GammaRad.toString());
		xmlGammaRad.setTextContent("0.5");
		xmlSimulationParameters.appendChild(xmlGammaRad);
//		<DisableDifRad>
		Element xmlDisableDifRad = doc.createElement(XML_TAGS.DisableDifRad.toString());
		xmlDisableDifRad.setTextContent("0");
		xmlSimulationParameters.appendChild(xmlDisableDifRad);
//		<AdditionalMassHeatCapacity>
		Element xmlAdditionalMassHeatCapacity = doc.createElement(XML_TAGS.AdditionalMassHeatCapacity.toString());
		xmlAdditionalMassHeatCapacity.setTextContent("780");
		xmlSimulationParameters.appendChild(xmlAdditionalMassHeatCapacity);
//		<AdditionalMass>
		Element xmlAdditionalMass = doc.createElement(XML_TAGS.AdditionalMass.toString());
		xmlAdditionalMass.setTextContent("0");
		xmlSimulationParameters.appendChild(xmlAdditionalMass);
	}

	private void createShadingControlModel(Element xmlRoom) {
		LOGGER.info("Create Shading Control Model...");
//		<ShadingControlModel>
		Element xmlShadingControlModel = doc.createElement(XML_TAGS.ShadingControlModel.toString());
//		name
		xmlShadingControlModel.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "");
//		modelType
		xmlShadingControlModel.setAttribute(XML_TAG_ATTRIBUTES.modelType.toString(), "CONSTANT");
		xmlRoom.appendChild(xmlShadingControlModel);
		
//		<MaxRadiationIntensity>
		Element xmlMaxRadiationIntensity = doc.createElement(XML_TAGS.MaxRadiationIntensity.toString());
		xmlMaxRadiationIntensity.setTextContent("250");
		xmlShadingControlModel.appendChild(xmlMaxRadiationIntensity);
		
//		<ScheduleShadingDegree>
		Element xmlScheduleShadingDegree = doc.createElement(XML_TAGS.ScheduleShadingDegree.toString());
//		type
		xmlScheduleShadingDegree.setAttribute(XML_TAG_ATTRIBUTES.type.toString(), "CONSTANT");
		
		xmlShadingControlModel.appendChild(xmlScheduleShadingDegree);
//		<ConstantValue>
		Element xmlConstantValue = doc.createElement(XML_TAGS.ConstantValue.toString());
		xmlConstantValue.setTextContent("1");
		xmlScheduleShadingDegree.appendChild(xmlConstantValue);	
	}

	private void createUserLoads(Element xmlRoom) {
		LOGGER.debug("Create User Loads...");
//		<UserLoads>
		Element xmlUserLoads = doc.createElement(XML_TAGS.UserLoads.toString());
//		name
		xmlUserLoads.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "OFFICE");
		xmlRoom.appendChild(xmlUserLoads);
		
//		<PersonLoad>
		Element xmlPersonLoad = doc.createElement(XML_TAGS.PersonLoad.toString());
		xmlPersonLoad.setTextContent("80");
		xmlUserLoads.appendChild(xmlPersonLoad);
		
//		<ScheduleOccupancy>
		Element xmlScheduleOccupancy = doc.createElement(XML_TAGS.ScheduleOccupancy.toString());
//		type
		xmlScheduleOccupancy.setAttribute(XML_TAG_ATTRIBUTES.type.toString(), "CONSTANT");
		
		xmlUserLoads.appendChild(xmlScheduleOccupancy);
//		<ConstantValue>
		Element xmlConstantValue = doc.createElement(XML_TAGS.ConstantValue.toString());
		xmlConstantValue.setTextContent("4");
		xmlScheduleOccupancy.appendChild(xmlConstantValue);	
		
//		<ScheduleEquipmentLoad>
		Element xmlScheduleEquipmentLoad = doc.createElement(XML_TAGS.ScheduleEquipmentLoad.toString());
//		type
		xmlScheduleEquipmentLoad.setAttribute(XML_TAG_ATTRIBUTES.type.toString(), "CONSTANT");
		
		xmlUserLoads.appendChild(xmlScheduleEquipmentLoad);
//		<ConstantValue>
		Element xmlConstantValue2 = doc.createElement(XML_TAGS.ConstantValue.toString());
		xmlConstantValue2.setTextContent("0");
		xmlScheduleEquipmentLoad.appendChild(xmlConstantValue2);	
	}

	private void createNaturalVentilationModel(Element xmlRoom) {
		LOGGER.debug("Create Natural Ventilation Model...");
//		<NaturalVentilationModel>
		Element xmlNaturalVentilationModel = doc.createElement(XML_TAGS.NaturalVentilationModel.toString());
//		name
		xmlNaturalVentilationModel.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "");
		xmlRoom.appendChild(xmlNaturalVentilationModel);
		
//		<ScheduleAirChangeRates>
		Element xmlScheduleAirChangeRates = doc.createElement(XML_TAGS.ScheduleAirChangeRates.toString());
//		type
		xmlScheduleAirChangeRates.setAttribute(XML_TAG_ATTRIBUTES.type.toString(), "CONSTANT");
		
		xmlNaturalVentilationModel.appendChild(xmlScheduleAirChangeRates);
//		<ConstantValue>
		Element xmlConstantValue = doc.createElement(XML_TAGS.ConstantValue.toString());
		xmlConstantValue.setTextContent("0.5");
		xmlScheduleAirChangeRates.appendChild(xmlConstantValue);	
	}

	private void createCoolingModel(Element xmlRoom) {
		LOGGER.debug("Create Cooling Model...");
//		<CoolingModel>
		Element xmlCoolingModel = doc.createElement(XML_TAGS.CoolingModel.toString());
//		name
		xmlCoolingModel.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "");
//		modelType
		xmlCoolingModel.setAttribute(XML_TAG_ATTRIBUTES.modelType.toString(), "NONE");
		xmlRoom.appendChild(xmlCoolingModel);
		
//		<MaxPower>
		Element xmlMaxPower = doc.createElement(XML_TAGS.MaxPower.toString());
		xmlMaxPower.setTextContent("1200");
		xmlCoolingModel.appendChild(xmlMaxPower);
		
//		<SupplyTemperature>
		Element xmlSupplyTemperature = doc.createElement(XML_TAGS.SupplyTemperature.toString());
		xmlSupplyTemperature.setTextContent("16");
		xmlCoolingModel.appendChild(xmlSupplyTemperature);
		
//		<MaxSupplyFlowRate>
		Element xmlMaxSupplyFlowRate = doc.createElement(XML_TAGS.MaxSupplyFlowRate.toString());
		xmlMaxSupplyFlowRate.setTextContent("40");
		xmlCoolingModel.appendChild(xmlMaxSupplyFlowRate);
		
//		<HeatRecoveryEfficiency>
		Element xmlHeatRecoveryEfficiency = doc.createElement(XML_TAGS.HeatRecoveryEfficiency.toString());
		xmlHeatRecoveryEfficiency.setTextContent("0");
		xmlCoolingModel.appendChild(xmlHeatRecoveryEfficiency);
		
//		<ScheduleSetPoints>
		Element xmlScheduleSetPoints = doc.createElement(XML_TAGS.ScheduleSetPoints.toString());
//		type
		xmlScheduleSetPoints.setAttribute(XML_TAG_ATTRIBUTES.type.toString(), "DAILY_WEEK");
		
		xmlCoolingModel.appendChild(xmlScheduleSetPoints);
//		<WeekDaySchedule>
		Element xmlWeekDaySchedule = doc.createElement(XML_TAGS.WeekDaySchedule.toString());
		xmlWeekDaySchedule.setTextContent("40 40 40 40 40 40 40 40 26 26 26 26 26 26 26 26 26 40 40 40 40 40 40 40 ");
		xmlScheduleSetPoints.appendChild(xmlWeekDaySchedule);
//		<WeekEndSchedule>
		Element xmlWeekEndSchedule = doc.createElement(XML_TAGS.WeekEndSchedule.toString());
		xmlWeekEndSchedule.setTextContent("40 40 40 40 40 40 40 40 40 40 40 40 40 40 40 40 40 40 40 40 40 40 40 40 ");
		xmlScheduleSetPoints.appendChild(xmlWeekEndSchedule);
//		<WeekEndDays>
		Element xmlWeekEndDays = doc.createElement(XML_TAGS.WeekEndDays.toString());
		xmlWeekEndDays.setTextContent("Sat Sun ");
		xmlScheduleSetPoints.appendChild(xmlWeekEndDays);		
//      <ScheduleVentilationRates>
		Element xmlScheduleVentilationRates = doc.createElement(XML_TAGS.ScheduleVentilationRates.toString());
//		<type>
		xmlScheduleVentilationRates.setAttribute(XML_TAG_ATTRIBUTES.type.toString(), "CONSTANT");
		xmlCoolingModel.appendChild(xmlScheduleVentilationRates);	
//		<ConstantValue>
		Element xmlConstantValue = doc.createElement(XML_TAGS.ConstantValue.toString());
		xmlConstantValue.setTextContent("0");
		xmlScheduleVentilationRates.appendChild(xmlConstantValue);	
	}

	private void createHeatingModel(Element xmlRoom) {
		LOGGER.debug("Create Heating Model...");
//		<HeatingModel>
		Element xmlHeatingModel = doc.createElement(XML_TAGS.HeatingModel.toString());
//		name
		xmlHeatingModel.setAttribute(XML_TAG_ATTRIBUTES.name.toString(), "");
//		modelType
		xmlHeatingModel.setAttribute(XML_TAG_ATTRIBUTES.modelType.toString(), "SIMPLE");
		xmlRoom.appendChild(xmlHeatingModel);
		
//		<MaxPower>
		Element xmlMaxPower = doc.createElement(XML_TAGS.MaxPower.toString());
		xmlMaxPower.setTextContent("2000");
		xmlHeatingModel.appendChild(xmlMaxPower);
		
//		<ScheduleSetPoints>
		Element xmlScheduleSetPoints = doc.createElement(XML_TAGS.ScheduleSetPoints.toString());
//		type
		xmlScheduleSetPoints.setAttribute(XML_TAG_ATTRIBUTES.type.toString(), "CONSTANT");
		
		xmlHeatingModel.appendChild(xmlScheduleSetPoints);
//		<ConstantValue>
		Element xmlConstantValue = doc.createElement(XML_TAGS.ConstantValue.toString());
		xmlConstantValue.setTextContent("20");
		xmlScheduleSetPoints.appendChild(xmlConstantValue);	
	}

	private void createClimateLocation(Element xmlRoom) throws SdaiException{
		LOGGER.debug("Create Climate Location...");
//		<ClimateLocation>
		Element xmlClimateLocation = doc.createElement(XML_TAGS.ClimateLocation.toString());
//		site location
		xmlClimateLocation.setTextContent(location.getTown());
				
		xmlRoom.appendChild(xmlClimateLocation);
	}
	
	private void createRoomConstruction(Node xmlRoomConstructions, TheraklesConstruction construction) throws SdaiException{
//		<Construction>
		Element xmlRoomConstruction = doc.createElement(XML_TAGS.RoomConstruction.toString());
		xmlRoomConstructions.appendChild(xmlRoomConstruction);
		
//		<orientation>
		double orientation = construction.getOrientation();
		Element xmlOrientation = doc.createElement(XML_TAGS.orientation.toString());
		xmlOrientation.setTextContent(""+orientation);				
		xmlRoomConstruction.appendChild(xmlOrientation);
		
//		<inclination>
		double inclination = construction.getInclination();
		Element xmlInclination = doc.createElement(XML_TAGS.inclination.toString());
		xmlInclination.setTextContent(""+inclination);				
		xmlRoomConstruction.appendChild(xmlInclination);
		
//		<R_ue> - Wärmeübergangswiderstand an der Außenoberfläche
		Element xmlRue = doc.createElement(XML_TAGS.R_ue.toString());
		xmlRue.setTextContent(""+construction.getR_ue());
		xmlRoomConstruction.appendChild(xmlRue);
		
//		<R_ui> - Wärmeübergangswiderstand an der Innenoberfläche
		Element xmlRui = doc.createElement(XML_TAGS.R_ui.toString());
		xmlRui.setTextContent(""+construction.getR_ui());
		xmlRoomConstruction.appendChild(xmlRui);
//		<ad>
		Element xmlAd = doc.createElement(XML_TAGS.ad.toString());
		xmlAd.setTextContent(""+construction.getAd());
		xmlRoomConstruction.appendChild(xmlAd);
//		<zoneT>
		Element xmlZoneT = doc.createElement(XML_TAGS.zoneT.toString());
		xmlZoneT.setTextContent(""+construction.getZoneT());
		xmlRoomConstruction.appendChild(xmlZoneT);
		
//		<AW> - Netto-Wandfläche
		Element xmlAW = doc.createElement(XML_TAGS.AW.toString());
		xmlAW.setTextContent(""+construction.getAW());
		xmlRoomConstruction.appendChild(xmlAW);
//		<AF> - Gesamtfensterfläche
		Element xmlAF = doc.createElement(XML_TAGS.AF.toString());
		xmlAF.setTextContent(""+construction.getAF());
		xmlRoomConstruction.appendChild(xmlAF);
		
//		<constructionId>
		Element xmlConstructionId = doc.createElement(XML_TAGS.constructionId.toString());
		xmlConstructionId.setTextContent(""+construction.getConstructionTypeId());
		xmlRoomConstruction.appendChild(xmlConstructionId);
//		<windowId>
		Element xmlWindowId = doc.createElement(XML_TAGS.windowId.toString());
		xmlWindowId.setTextContent((construction.getWindow() != null) ? ""+construction.getWindow().getId() : "0");
		xmlRoomConstruction.appendChild(xmlWindowId);
//		<shadingTypeId>
		Element xmlShadingTypeId = doc.createElement(XML_TAGS.shadingTypeId.toString());
		xmlShadingTypeId.setTextContent(""+construction.getShadingTypeId());
		xmlRoomConstruction.appendChild(xmlShadingTypeId);
//		<type>
		Element xmlType = doc.createElement(XML_TAGS.type.toString());
		xmlType.setTextContent(construction.getType().toString());
		xmlRoomConstruction.appendChild(xmlType);
	}

//	private void createRoomConstruction(Node xmlRoomConstructions, EIfcspace space, EIfcelement wall, Set<EIfcelement> windows) throws SdaiException{
////		<Construction>
//		Element xmlRoomConstruction = doc.createElement(XML_TAGS.RoomConstruction.toString());
//		xmlRoomConstructions.appendChild(xmlRoomConstruction);
//		
////		<orientation>
//		double orientation = 0.0;
//		Element xmlOrientation = doc.createElement(XML_TAGS.orientation.toString());
//		EIfcphysicalquantity[] physicalQuantities1 = controller.getElementQuantity(wall, ELEMENT_QUANTITIES.azimuth.toString());
//		if(physicalQuantities1.length > 0) {
//			EIfcquantitylength quantityOrientation = (EIfcquantitylength) physicalQuantities1[0];
//			orientation = quantityOrientation.getLengthvalue(quantityOrientation);
//		}
//		xmlOrientation.setTextContent(""+convertRadiansToDegrees(orientation));				
//		xmlRoomConstruction.appendChild(xmlOrientation);
//		
////		<inclination>
//		double inclination = 0.0;
//		Element xmlInclination = doc.createElement(XML_TAGS.inclination.toString());
//		EIfcphysicalquantity[] physicalQuantities2 = controller.getElementQuantity(wall, ELEMENT_QUANTITIES.tilt.toString());
//		if(physicalQuantities2.length > 0) {
//			EIfcquantitylength quantityInclination = (EIfcquantitylength) physicalQuantities2[0];
//			inclination = quantityInclination.getLengthvalue(quantityInclination);
//		}
//		xmlInclination.setTextContent(""+inclination);				
//		xmlRoomConstruction.appendChild(xmlInclination);
//		
////		<R_ue> - Wärmeübergangswiderstand an der Außenoberfläche
//		Element xmlRue = doc.createElement(XML_TAGS.R_ue.toString());
//		xmlRue.setTextContent(R_UE);
//		xmlRoomConstruction.appendChild(xmlRue);
//		
////		<R_ui> - Wärmeübergangswiderstand an der Innenoberfläche
//		Element xmlRui = doc.createElement(XML_TAGS.R_ui.toString());
//		xmlRui.setTextContent(R_UI);
//		xmlRoomConstruction.appendChild(xmlRui);
////		<ad>
//		Element xmlAd = doc.createElement(XML_TAGS.ad.toString());
//		xmlAd.setTextContent(AD);
//		xmlRoomConstruction.appendChild(xmlAd);
////		<zoneT>
//		Element xmlZoneT = doc.createElement(XML_TAGS.zoneT.toString());
//		xmlZoneT.setTextContent(ZONET);
//		xmlRoomConstruction.appendChild(xmlZoneT);
//
////		calculate gross wall area from Space Boundaries
//		double grossWallArea = calculateGrossWallArea(space, wall);
////		calculate gross window area from Space Boundaries		
//		double grossWindowArea = calculateGrossWindowArea(space, windows);
////		calculate net wall area from Space Boundaries without window
//		double wallNetArea = calculateNetWallArea(grossWallArea, grossWindowArea);
//		
////		<AW> - Netto-Wandfläche
//		Element xmlAW = doc.createElement(XML_TAGS.AW.toString());
//		xmlAW.setTextContent(""+wallNetArea);
//		xmlRoomConstruction.appendChild(xmlAW);
////		<AF> - Gesamtfensterfläche
//		Element xmlAF = doc.createElement(XML_TAGS.AF.toString());
//		xmlAF.setTextContent(""+grossWindowArea);
//		xmlRoomConstruction.appendChild(xmlAF);
//		
////		take following tags from the embedded database
////		<constructionId>
//		Element xmlConstructionId = doc.createElement(XML_TAGS.constructionId.toString());
//		xmlConstructionId.setTextContent("2");
//		xmlRoomConstruction.appendChild(xmlConstructionId);
////		<windowId>
//		Element xmlWindowId = doc.createElement(XML_TAGS.windowId.toString());
//		xmlWindowId.setTextContent(windows.size() > 0 ? "5" : "0");
//		xmlRoomConstruction.appendChild(xmlWindowId);
////		<shadingTypeId>
//		Element xmlShadingTypeId = doc.createElement(XML_TAGS.shadingTypeId.toString());
//		xmlShadingTypeId.setTextContent("0");
//		xmlRoomConstruction.appendChild(xmlShadingTypeId);
////		<type>
//		Element xmlType = doc.createElement(XML_TAGS.type.toString());
////		TODO rausfinden ob es Innenwand oder Aussenwand ist - SpaceBoundary Definition ist eigentlich anders
//		EIfcrelspaceboundary[] spaceBoundaries = controller.getSpaceBoundariesBetweenSpaceAndElement(space, wall);
//		boolean internal = false;
//		for(EIfcrelspaceboundary sb : spaceBoundaries) {
////			0: internal	1: external
//			internal = sb.getInternalorexternalboundary(sb) == 0 ? true : false;
//		}
//		xmlType.setTextContent(internal ? "Inside" : "Outside");
//		xmlRoomConstruction.appendChild(xmlType);
//	}

	public File getSimulationModel() {
		return simulationFile;
	}
}
