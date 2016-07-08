/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import java.io.File;
import java.util.Map;

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

import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;

/**
 * The Class ConstructionTemplateSerializer.
 */
public final class ConstructionTemplateSerializer {

	/** The Constant MATERIAL_REFERENCE_ID_PATTERN. */
	public static final String MATERIAL_REFERENCE_ID_PATTERN = "_";
	private static final String IBK_MATERIAL_DATABASE_PLACEHOLDER = "${Material Database}";
	
	
	/**
	 * Instantiates a new construction template serializer.
	 */
	private ConstructionTemplateSerializer() {
		throw new AssertionError();
	}
	
	/**
	 * Serialize.
	 *
	 * @param template the template
	 * @param destPath the dest path
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws TransformerException the transformer exception
	 */
	public static void serialize(ConstructionTemplate template, String destPath) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		
		// root elements
		Element rootElement = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.DelphinProject.toString());
		rootElement.setAttribute("xmlns:IBK", "http://www.bauklimatik-dresden.de/IBK");
		rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		rootElement.setAttribute("fileVersion", "5.6.7");
		rootElement.setAttribute("xsi:schemaLocation", "http://www.bauklimatik-dresden.de DelphinProject.xsd");
		rootElement.setAttribute("xmlns", "http://www.bauklimatik-dresden.de");
		doc.appendChild(rootElement);
 
		// <ProjectInfo>
		Element projectInfo = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.ProjectInfo.toString());
		rootElement.appendChild(projectInfo);
		
			// <DisplayName>
			Element displayName = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.DisplayName.toString());
			displayName.setTextContent(template.getName());
			projectInfo.appendChild(displayName);
			
////		// <DirectoryPlaceholders>
//		Element directoryPlaceholders = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.DirectoryPlaceholders.toString());
////			<Placeholder>
//			Element placeholder = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.Placeholder.toString());		
//			placeholder.setAttribute(
//					ConstructionTemplateDeserializer.XML_TAG_ATTRIBUTES.name.toString(), 
//					ConstructionTemplateDeserializer.MATERIAL_DATABASE);
//			placeholder.setTextContent("");
//			directoryPlaceholders.appendChild(placeholder);	
//		rootElement.appendChild(directoryPlaceholders);	
		
		// <Materials>
		Element materials = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.Materials.toString());
		rootElement.appendChild(materials);
		
			Map<Integer, MaterialLayer> layers = template.getMaterialLayers();
			for(int i = 0; i < layers.size(); i++) {
				MaterialLayer layer = layers.get(i);
				// <Material>
				Element materialReference = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.MaterialReference.toString());
				materialReference.setAttribute(
						ConstructionTemplateDeserializer.XML_TAG_ATTRIBUTES.displayName.toString(), 
						layer.getTemplate().getName());
				String matTplPath = layer.getTemplate().getSourceFileUri();
//				convert the path because NANDRAD needs the placeholder in the construction template
				matTplPath = convertPathToIBKPathWithPlaceholder(matTplPath);
				
				materialReference.setTextContent(matTplPath);
				materials.appendChild(materialReference);
			}
		
		// <Discretization>
		Element discretization = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.Discretization.toString());
		discretization.setAttribute(ConstructionTemplateDeserializer.XML_TAG_ATTRIBUTES.geometryType.toString(), "2D");
		rootElement.appendChild(discretization);
		
			// <XSteps>
			Element xsteps = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.XSteps.toString());
			xsteps.setAttribute(ConstructionTemplateDeserializer.XML_TAG_ATTRIBUTES.unit.toString(), "m");
			
			String thickness = "";

			for(int i = 0; i < layers.size(); i++) {
				MaterialLayer ml = layers.get(i);
				thickness += ml.getThickness();
				thickness += " ";
			}
//			thickness = thickness.trim();
			xsteps.setTextContent(thickness);
			discretization.appendChild(xsteps);
		
		// <Assignments>
		Element assignments = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.Assignments.toString());
		rootElement.appendChild(assignments);	
		
			// <Assignment>
			for(int i = 0; i < layers.size(); i++) {
				MaterialLayer ml = layers.get(i);
				MaterialTemplate matTpl = ml.getTemplate();
				Element assignment = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.Assignment.toString());
				assignment.setAttribute(ConstructionTemplateDeserializer.XML_TAG_ATTRIBUTES.type.toString(), "Material");
				assignment.setAttribute(ConstructionTemplateDeserializer.XML_TAG_ATTRIBUTES.location.toString(), "Element");
				assignments.appendChild(assignment);
				
				// <Reference>
				Element reference = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.Reference.toString());
//					displayName
				reference.setAttribute(
						ConstructionTemplateDeserializer.XML_TAG_ATTRIBUTES.displayName.toString(), 
						ml.getTemplate().getName());
				
//				search reference identifier, ID ist zahl nach "_" also material_1 => 1
				String templatePath = matTpl.getSourceFileUri();
				
				String templateFileName = new File(templatePath).getName();
				int beginOfReference = templateFileName.lastIndexOf(MATERIAL_REFERENCE_ID_PATTERN);
				int dot = templateFileName.lastIndexOf(".");
//				reference ID
				String referenceId = templateFileName.substring((beginOfReference+1), dot);
				
				reference.setTextContent(referenceId);
				assignment.appendChild(reference);	
				
				// <IBK:Range>
				Element range = doc.createElement("IBK:Range");
				range.setTextContent(i+" 0 "+i+" 0"); // because the order of material references implicit the assignment
				assignment.appendChild(range);	
			}
		
		
		// <Costs>
		Element costs = doc.createElement(ConstructionTemplateDeserializer.XML_TAGS.Costs.toString());
		costs.setTextContent(""+template.getCostsPerSquareMeter());
		rootElement.appendChild(costs);	
			
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(destPath));
 
		transformer.transform(source, result);
	}
	
	private static String convertPathToIBKPathWithPlaceholder(String path) {
		int lastSlash = path.lastIndexOf(File.separator);
		String fileName = path.substring(lastSlash+1);
		
		return IBK_MATERIAL_DATABASE_PLACEHOLDER+"/"+fileName;
	}
}
