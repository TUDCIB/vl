/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;

/**
 * The Class SpaceTypeTemplatesDeserializer.
 */
public final class SpaceTypeTemplatesDeserializer {
	
	
	/**
	 * The Enum XML_TAGS.
	 */
	public static enum XML_TAGS {
		
		/** The Space type properties. */
		SpaceTypeProperties, 
 /** The Space type. */
 SpaceType, 
 /** The Short description. */
 ShortDescription, 
 /** The Long description. */
 LongDescription
	};
	
	/**
	 * The Enum XML_TAG_ATTRIBUTES.
	 */
	public static enum XML_TAG_ATTRIBUTES {
		
		/** The id. */
		id, 
 /** The name. */
 name, 
 /** The lang. */
 lang
	};
	
	/**
	 * The Enum SPACE_TYPE_DESCRIPTIONS.
	 */
	public static enum SPACE_TYPE_DESCRIPTIONS {
		
		/** The Heating control mode. */
		HeatingControlMode,
		
		/** The Cooling control mode. */
		CoolingControlMode,
		
		/** The Short description. */
		ShortDescription, 
		
		/** The Long description. */
		LongDescription
	}	
	
	/** The Constant LOGGER. */
	private static final Logger LOG = LoggerFactory.getLogger(SpaceTypeTemplatesDeserializer.class);

	
	/**
	 * Instantiates a new space type templates deserializer.
	 */
	private SpaceTypeTemplatesDeserializer() {
		throw new AssertionError();
	}
	
	/**
	 * Deserialize.
	 *
	 * @param filePath the file path
	 * @return the list
	 * @throws FileNotFoundException the file not found exception
	 * @throws ParsingException the parsing exception
	 */
	public static List<SpaceTypeTemplate> deserialize(String filePath) throws FileNotFoundException, ParsingException {
		LOG.trace("Reading space type template: {}", filePath);
		File file = new File(filePath);
		if(!file.exists()) {
			throw new FileNotFoundException("File not found");
		}
		List<SpaceTypeTemplate> templates = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.normalizeDocument();
			
			templates = new ArrayList<SpaceTypeTemplate>();
	//		(1) parse the SpaceTypeProperties
			NodeList spaceTypePropertiesList = doc.getElementsByTagName(XML_TAGS.SpaceType.toString());
			for(int i = 0; i < spaceTypePropertiesList.getLength(); i++) {
	//          <SpaceType>			
				Element spaceTypeNode = (Element)spaceTypePropertiesList.item(i);
				String spaceTypeId = spaceTypeNode.getAttribute(XML_TAG_ATTRIBUTES.id.toString());
				String id = spaceTypeId.trim();	
				
				SpaceTypeTemplate template = new SpaceTypeTemplate();
				template.setName(id);
				template.setSourceFileUri(filePath);
				
				NodeList shortDescriptionList = spaceTypeNode.getElementsByTagName(SPACE_TYPE_DESCRIPTIONS.ShortDescription.toString());
				for(int j = 0; j < shortDescriptionList.getLength(); j++) {
	//	        	<ShortDescription>			
					Element shortDescriptionNode = (Element)shortDescriptionList.item(j);
					template.setShortDescription(shortDescriptionNode.getTextContent());
				}
				NodeList longDescriptionList = spaceTypeNode.getElementsByTagName(SPACE_TYPE_DESCRIPTIONS.LongDescription.toString());
				for(int j = 0; j < longDescriptionList.getLength(); j++) {
	//	        	<LongDescription>			
					Element longDescriptionNode = (Element)longDescriptionList.item(j);
					template.setLongDescription(longDescriptionNode.getTextContent());
				}
				NodeList modeList = spaceTypeNode.getElementsByTagName("xs:string");
				for(int j = 0; j < modeList.getLength(); j++) {
	//	        	<xs:string>			
					Element modeNode = (Element)modeList.item(j);
					if(modeNode.getAttribute(XML_TAG_ATTRIBUTES.name.toString()).equalsIgnoreCase(SPACE_TYPE_DESCRIPTIONS.CoolingControlMode.toString())) {
						template.setCoolingControlMode(modeNode.getTextContent());
					} else if(modeNode.getAttribute(XML_TAG_ATTRIBUTES.name.toString()).equalsIgnoreCase(SPACE_TYPE_DESCRIPTIONS.HeatingControlMode.toString())) {
						template.setHeatingControlMode(modeNode.getTextContent());
					} else {
						throw new RuntimeException("Unhandled Node");
					}
				}
				
				templates.add(template);
			}
		} catch (ParserConfigurationException e) {
			throw new ParsingException(e);
		} catch (SAXException e) {
			throw new ParsingException(e);
		} catch (IOException e) {
			throw new ParsingException(e);
		}
		return templates.size() > 0 ? templates : null;
	}
}
