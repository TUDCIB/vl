/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

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
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.util.FileManager;


/**
 * The Class ClimateLocationDeserializer.
 */
public final class ClimateLocationDeserializer {
	
	/**
	 * The Enum XML_TAGS.
	 */
	private static enum XML_TAGS {
		
		/** The Climate data location. */
		ClimateDataLocation, 
		 /** The Position. */
		 Position, 
		 /** The Description. */
		 Description
	}
	
	/**
	 * The Enum XML_ATTRIBUTES.
	 */
	private static enum XML_ATTRIBUTES {
		
		/** The latitude. */
		latitude, 
		/** The longitude. */
		longitude, 
		/** The langid. */
		langid
	}
	
	/** The Constant DESCRIPTION_FILE. */
	private static final String DESCRIPTION_FILE = "description.xml";
	
	
	/** The Constant LOGGER. */
	private static final Logger LOG = LoggerFactory.getLogger(ClimateLocationDeserializer.class);
	

	/**
	 * Instantiates a new climate location deserializer.
	 */
	private ClimateLocationDeserializer() {
		throw new AssertionError();
	}
	
	/**
	 * Deserialize.
	 *
	 * @param climateDirectoryPath The directory where description.xml and .ccd are included.
	 * @return the climate location template
	 * @throws ParsingException the parsing exception
	 * @throws FileNotFoundException the file not found exception
	 */
	public static ClimateLocationTemplate deserialize(String climateDirectoryPath) throws ParsingException, FileNotFoundException {
		LOG.trace("Parsing directory: {}", climateDirectoryPath);
		File directory = new File(climateDirectoryPath);
		Set<File> filesInDirectory = FileManager.getFiles(directory, false);
		for(File f : filesInDirectory) {
			if(f.getName().equalsIgnoreCase(DESCRIPTION_FILE)) {
				FileInputStream fin = new FileInputStream(f);
				ClimateLocationTemplate model = deserialize(fin);
				model.setSourceFileUri(climateDirectoryPath);
				return model;
			}
		}
		return null;
	}

	/**
	 * Deserialize.
	 *
	 * @param inputstream the inputstream
	 * @return the climate location template
	 * @throws ParsingException the parsing exception
	 */
	private static ClimateLocationTemplate deserialize(InputStream inputstream) throws ParsingException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(inputstream);
			doc.normalizeDocument();
			ClimateLocationTemplate template = new ClimateLocationTemplate();
			
			NodeList climateDataLocationList = doc.getElementsByTagName(XML_TAGS.ClimateDataLocation.toString());
//	      <ClimateDataLocation>
			Element climateDataLocationNode = (Element) climateDataLocationList.item(0);
//	      <Position>
			Element positionNode = (Element) climateDataLocationNode.getElementsByTagName(XML_TAGS.Position.toString()).item(0);
			String latitude = positionNode.getAttribute(XML_ATTRIBUTES.latitude.toString());
			String longitude = positionNode.getAttribute(XML_ATTRIBUTES.longitude.toString());
			try {
				template.setLatitude(Float.valueOf(latitude));
				template.setLongitude(Float.valueOf(longitude));
			} catch(NumberFormatException nfe) {
				throw new ParsingException(nfe);
			}
			NodeList descriptionList = climateDataLocationNode.getElementsByTagName(XML_TAGS.Description.toString());
//	      <Description>
			for(int i = 0; i < descriptionList.getLength(); i++) {
				Element descriptionNode = (Element) descriptionList.item(i);
				String attributeValue = descriptionNode.getAttribute(XML_ATTRIBUTES.langid.toString());
				if(attributeValue.equalsIgnoreCase("en")) {
					String region = descriptionNode.getTextContent();
					template.setRegion(region);
					template.setName(region);
				}
			}
			return template;
		} catch (IOException e) {
			throw new ParsingException(e);
		} catch (ParserConfigurationException e) {
			throw new ParsingException(e);
		} catch (SAXException e) {
			throw new ParsingException(e);
		}
	}

}
