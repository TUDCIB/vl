package de.tudresden.bau.cib.vl.core.simulation.energy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.tudresden.bau.cib.vl.core.simulation.energy.model.Material;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles.TheraklesConstruction;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles.TheraklesWindow;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles.TheraklesConstruction.TYPE_ENUM;
import de.tudresden.bau.cib.vl.core.simulation.energy.transformation.TheraklesSimulationModelGenerator.XML_TAGS;
import de.tudresden.bau.cib.vl.core.simulation.energy.transformation.TheraklesSimulationModelGenerator.XML_TAGS_EMBEDDED_DATABASE;
import de.tudresden.bau.cib.vl.core.simulation.energy.transformation.TheraklesSimulationModelGenerator.XML_TAG_ATTRIBUTES;


public class RmXmlImporter {
	
	private Document doc;
	

	public RmXmlImporter() {
		
	}
	
	public TheraklesConstruction[] parseRoomModel(String rmxmlPath) throws ParserConfigurationException, SAXException, IOException {
		File file = new File(rmxmlPath);
		if(!file.exists()) {
			throw new FileNotFoundException("File not found");
		}
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		doc = db.parse(file);
		doc.normalizeDocument();
		
//		(1) parse the constructions
		List<TheraklesConstruction> constructions = new ArrayList<TheraklesConstruction>();
		NodeList constructionsList = doc.getElementsByTagName(XML_TAGS.RoomConstruction.toString());
		for(int i = 0; i < constructionsList.getLength(); i++) {
			TheraklesConstruction construction = new TheraklesConstruction(null);
			
			Element constructionNode = (Element)constructionsList.item(i);
//          <orientation>
			String orientation = getTagValue(XML_TAGS.orientation.toString(), constructionNode);
//          <inclination>
			String inclination = getTagValue(XML_TAGS.inclination.toString(), constructionNode);
//          <R_ue>
			String R_ue = getTagValue(XML_TAGS.R_ue.toString(), constructionNode);
//          <R_ui>
			String R_ui = getTagValue(XML_TAGS.R_ui.toString(), constructionNode);
//          <ad>
			String ad = getTagValue(XML_TAGS.ad.toString(), constructionNode);
//          <zoneT>
			String zoneT = getTagValue(XML_TAGS.zoneT.toString(), constructionNode);
//          <AW>
			String AW = getTagValue(XML_TAGS.AW.toString(), constructionNode);
//          <AF>
			String AF = getTagValue(XML_TAGS.AF.toString(), constructionNode);
//          <constructionId>
			String constructionId = getTagValue(XML_TAGS.constructionId.toString(), constructionNode);
//          <windowId>
			String windowId = getTagValue(XML_TAGS.windowId.toString(), constructionNode);
//          <shadingTypeId>
			String shadingTypeId = getTagValue(XML_TAGS.shadingTypeId.toString(), constructionNode);
//          <type>
			String type = getTagValue(XML_TAGS.type.toString(), constructionNode);
			
//			FIXME
			TheraklesWindow window = new TheraklesWindow(Integer.valueOf(windowId), "Fake");
			
			construction.setOrientation(Double.valueOf(orientation));
			construction.setInclination(Double.valueOf(inclination));
			construction.setR_ue(Double.valueOf(R_ue));
			construction.setR_ui(Double.valueOf(R_ui));
			construction.setAd(Double.valueOf(ad));
			construction.setZoneT(Integer.valueOf(zoneT));
			construction.setAW(Double.valueOf(AW));
			construction.setAF(Double.valueOf(AF));
			construction.setConstructionTypeId(Integer.valueOf(constructionId));
			construction.setWindow(window);
			construction.setShadingTypeId(Integer.valueOf(shadingTypeId));
			construction.setType(TYPE_ENUM.valueOf(type));
			
			constructions.add(construction);			
		}
		
//		(2) parse the embedded database
		Map<Integer, Material> materials = parseMaterials(doc);
		
//		(3) enhance the constructions)
		parseConstructionTypes(doc, constructions, materials);
		
		
		return constructions.toArray(new TheraklesConstruction[constructions.size()]);
	}
	
	private Map<Integer,Material> parseMaterials(Document doc) {
		Map<Integer,Material> materials = new HashMap<Integer,Material>();
		
		NodeList materialList = doc.getElementsByTagName(XML_TAGS_EMBEDDED_DATABASE.Material.toString());
		for(int i = 0; i < materialList.getLength(); i++) {
			Element materialNode = (Element)materialList.item(i);
			
			int id = Integer.valueOf(materialNode.getAttribute(XML_TAG_ATTRIBUTES.id.toString()));
			String name = materialNode.getAttribute(XML_TAG_ATTRIBUTES.name.toString());
			String category = materialNode.getAttribute(XML_TAG_ATTRIBUTES.category.toString());
			String lambda = materialNode.getAttribute(XML_TAG_ATTRIBUTES.lambda.toString());
			String rho = materialNode.getAttribute(XML_TAG_ATTRIBUTES.rho.toString());
			String cT = materialNode.getAttribute(XML_TAG_ATTRIBUTES.cT.toString());
			
			Material material = new Material(name);
			material.setId(id);
			material.setCategory(Integer.valueOf(category));
			material.setLambda(Float.valueOf(lambda));
			material.setRho(Float.valueOf(rho));
			material.setcT(Float.valueOf(cT));
			
			materials.put(id, material);
		}
		return materials;
	}
	
	private void parseConstructionTypes(Document doc, List<TheraklesConstruction> constructions, Map<Integer,Material> materials) {	
		NodeList constructionTypes = doc.getElementsByTagName(XML_TAGS_EMBEDDED_DATABASE.ConstructionType.toString());
		for(TheraklesConstruction c : constructions) {
			int constructionTypeId = c.getConstructionTypeId();
//			search it in the construction types
			for(int i = 0; i < constructionTypes.getLength(); i++) {
				Element current = (Element)constructionTypes.item(i);
				int currentId = Integer.valueOf(current.getAttribute(XML_TAG_ATTRIBUTES.id.toString()));
				if(currentId == constructionTypeId) {
					String name = current.getAttribute(XML_TAG_ATTRIBUTES.name.toString());
					c.setName(name);
//					<Layers>
					NodeList layerList = current.getElementsByTagName(XML_TAGS_EMBEDDED_DATABASE.Layer.toString());
//					associate materials
					Map<Material, Double> associatedMaterials = new HashMap<Material, Double>();
					for(int j = 0; j < layerList.getLength(); j++) {
//						<layer>
						Element layerNode = (Element)layerList.item(j);
						Integer materialId = Integer.valueOf(layerNode.getAttribute(XML_TAG_ATTRIBUTES.materialId.toString()));
						Double d = Double.valueOf(layerNode.getAttribute(XML_TAG_ATTRIBUTES.d.toString()));
						
						Material associatedMaterial = materials.get(materialId);
						associatedMaterials.put(associatedMaterial, d);
					}
					c.setMaterials(associatedMaterials);
				}
			}
		}		
	}
	
	public String parseClimateLocation() {
		NodeList rooms = doc.getElementsByTagName(XML_TAGS.Room.toString());
		Element roomNode = (Element)rooms.item(0);
//		<ClimateLocation>
		return getTagValue(XML_TAGS.ClimateLocation.toString(), roomNode);
	}
	
	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes(); 
		Node nValue = (Node) nlList.item(0);		 
		return nValue.getNodeValue();
	}
	
}
