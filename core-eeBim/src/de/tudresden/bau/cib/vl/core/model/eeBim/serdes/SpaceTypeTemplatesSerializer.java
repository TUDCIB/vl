/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import java.io.File;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;

/**
 * The Class SpaceTypeTemplatesSerializer.
 */
public final class SpaceTypeTemplatesSerializer {

	
	/**
	 * Instantiates a new space type templates serializer.
	 */
	private SpaceTypeTemplatesSerializer() {
		throw new AssertionError();
	}
	
	/**
	 * Serialize.
	 *
	 * @param templates the templates
	 * @param destPath the dest path
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws TransformerException the transformer exception
	 */
	public static void serialize(Collection<SpaceTypeTemplate> templates, String destPath) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		
		
		// <SpaceTypeProperties>
		Element rootElement = doc.createElement(SpaceTypeTemplatesDeserializer.XML_TAGS.SpaceTypeProperties.toString());
		doc.appendChild(rootElement);
		
		for(SpaceTypeTemplate template : templates) {
 
			// <SpaceType>
			Element spaceType = doc.createElement(SpaceTypeTemplatesDeserializer.XML_TAGS.SpaceType.toString());
//			spaceType.setAttribute(SpaceTypeTemplatesDeserializer.XML_TAG_ATTRIBUTES.id.toString(), template.getId());
			spaceType.setAttribute(SpaceTypeTemplatesDeserializer.XML_TAG_ATTRIBUTES.id.toString(), template.getName());
			rootElement.appendChild(spaceType);
			
				// <ShortDescription>
				Element shortDescription = doc.createElement(SpaceTypeTemplatesDeserializer.XML_TAGS.ShortDescription.toString());
				shortDescription.setAttribute(SpaceTypeTemplatesDeserializer.XML_TAG_ATTRIBUTES.lang.toString(), "en");
				shortDescription.setTextContent(template.getShortDescription());
				spaceType.appendChild(shortDescription);
				// <LongDescription>
				Element longDescription = doc.createElement(SpaceTypeTemplatesDeserializer.XML_TAGS.LongDescription.toString());
				longDescription.setAttribute(SpaceTypeTemplatesDeserializer.XML_TAG_ATTRIBUTES.lang.toString(), "en");
				longDescription.setTextContent(template.getLongDescription());
				spaceType.appendChild(longDescription);
				// <xs:string>
				Element heatingControlMode = doc.createElement("xs:string");
				heatingControlMode.setAttribute(
						SpaceTypeTemplatesDeserializer.XML_TAG_ATTRIBUTES.name.toString(), 
						SpaceTypeTemplatesDeserializer.SPACE_TYPE_DESCRIPTIONS.HeatingControlMode.toString());
				heatingControlMode.setTextContent(template.getHeatingControlMode() != null ? template.getHeatingControlMode() : "None");
				spaceType.appendChild(heatingControlMode);
				// <xs:string>
				Element coolingcontrolModel = doc.createElement("xs:string");
				coolingcontrolModel.setAttribute(
						SpaceTypeTemplatesDeserializer.XML_TAG_ATTRIBUTES.name.toString(), 
						SpaceTypeTemplatesDeserializer.SPACE_TYPE_DESCRIPTIONS.CoolingControlMode.toString());
				coolingcontrolModel.setTextContent(template.getCoolingControlMode() != null ? template.getCoolingControlMode() : "None");
				spaceType.appendChild(coolingcontrolModel);
			}
			
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(destPath));
 
		transformer.transform(source, result);
	}
}
