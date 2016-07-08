/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;

/**
 * The Class ConstructionTemplateDeserializer.
 */
public final class ConstructionTemplateDeserializer {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ConstructionTemplateDeserializer.class);
	
	private static final String RELATIVE_MATERIAL_DATABASE_DIRECTORY_PATH = "../DB_Materials";
	
	
	/**
	 * The Enum XML_TAGS.
	 */
	public static enum XML_TAGS {
		
		/** The Delphin project. */
		DelphinProject, 
		 /** The Project info. */
		 ProjectInfo, 
		 /** The Display name. */
		 DisplayName, 
		 /** The Directory placeholders. */
		 DirectoryPlaceholders, 
		 /** The Placeholder. */
		 Placeholder, 
		 /** The Materials. */
		 Materials, 
		 /** The Material reference. */
		 MaterialReference, 
		
		/** The Discretization. */
		Discretization, 
		 /** The X steps. */
		 XSteps,	
		/** The Assignments. */
		Assignments, 
		 /** The Assignment. */
		 Assignment, 
		 /** The Reference. */
		 Reference, 
		 /** The IB k_ range. */
		 IBK_Range, 
		 /** The Costs. */
		 Costs
	};
	
	/**
	 * The Enum XML_TAG_ATTRIBUTES.
	 */
	public static enum XML_TAG_ATTRIBUTES {
		
		/** The id. */
		id, 
		 /** The name. */
		 name, 
		 /** The display name. */
		 displayName, 
		 /** The geometry type. */
		 geometryType, 
		 /** The unit. */
		 unit, 
		 /** The type. */
		 type, 
		 /** The location. */
		 location
	};
	
	
	/**
	 * Instantiates a new construction template deserializer.
	 */
	private ConstructionTemplateDeserializer() {
		throw new AssertionError();
	}
	
	/**
	 * Deserialize.
	 *
	 * @param fileUrl the file url
	 * @return the construction template
	 * @throws ParsingException the parsing exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ConstructionTemplate deserialize(URL fileUrl) throws ParsingException, IOException {
		ConstructionTemplate template = deserialize(fileUrl.openStream());
		template.setSourceFileUri(fileUrl.toString());
		return template;
	}
	
	/**
	 * Deserialize.
	 *
	 * @param in the input stream. Finally this stream will be closed.
	 * @return the construction template
	 * @throws ParsingException the parsing exception
	 * @throws IOException 
	 */
	private static ConstructionTemplate deserialize(InputStream in) throws ParsingException, IOException {
		ConstructionTemplate template = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(in);
			doc.normalizeDocument();

			//	<DelphinProject>
			Node root = doc.getFirstChild();

			XPathExpression expr = null;
			XPathFactory xFactory = XPathFactory.newInstance();
			XPath xpath = xFactory.newXPath();

			expr = xpath.compile("//"+XML_TAGS.ProjectInfo+"/"+XML_TAGS.DisplayName+"/text()");
			String name = (String) expr.evaluate(root, XPathConstants.STRING);
			template = new ConstructionTemplate();
			template.setName(name);

			//	get all directory place holders
			Map<String, String> placeholderMap = new HashMap<String, String>();
			expr = xpath.compile("//"+XML_TAGS.DirectoryPlaceholders);
			NodeList directoryPlaceholders = (NodeList) expr.evaluate(root, XPathConstants.NODESET);
			for (int i = 0; i < directoryPlaceholders.getLength(); i++){
				//	<PlaceHolder>
				Node node = directoryPlaceholders.item(i);
				expr = xpath.compile("//"+XML_TAGS.DirectoryPlaceholders+"/"+XML_TAGS.Placeholder+"/@"+XML_TAG_ATTRIBUTES.name);
				String directoryPlaceHolderName = (String) expr.evaluate(node, XPathConstants.STRING);	
				expr = xpath.compile("//"+XML_TAGS.DirectoryPlaceholders+"/"+XML_TAGS.Placeholder+"/text()");
				String directoryPlaceHolder = (String) expr.evaluate(node, XPathConstants.STRING);	 
				placeholderMap.put(directoryPlaceHolderName, directoryPlaceHolder);
			}

			//<XSteps>
			expr = xpath.compile("//"+XML_TAGS.Discretization+"/"+XML_TAGS.XSteps+"/text()");
			String xsteps = ((String) expr.evaluate(root, XPathConstants.STRING)).trim();	    	
			String[] thicknessArray = xsteps.split(" ");

			expr = xpath.compile("//"+XML_TAGS.Discretization+"/"+XML_TAGS.XSteps+"/@"+XML_TAG_ATTRIBUTES.unit);
			String unit = ((String) expr.evaluate(root, XPathConstants.STRING)).trim();

			//<Assignments>
			expr = xpath.compile("//"+XML_TAGS.Assignments+"/"+XML_TAGS.Assignment);
			Object assignments = expr.evaluate(root, XPathConstants.NODESET);
			NodeList nodes = (NodeList) assignments;
			for (int i = 0; i < nodes.getLength(); i++){
				//	<Assignment>
				Node node = nodes.item(i);
				MaterialTemplate matTpl = new MaterialTemplate();
				//	<Reference displayName="">
				expr = xpath.compile(XML_TAGS.Reference+"/@"+XML_TAG_ATTRIBUTES.displayName);
				String matTplName = (String) expr.evaluate(node, XPathConstants.STRING);
				matTplName = matTplName.trim();
				matTpl.setName(matTplName);

				//search corresponding material reference
				expr = xpath.compile("//"+XML_TAGS.Materials+"/"+XML_TAGS.MaterialReference+"[@"+XML_TAG_ATTRIBUTES.displayName+"='"+matTplName+"']/text()");
				String matTplPath = (String) expr.evaluate(root, XPathConstants.STRING);

//				convert the path back to a relative path because NANDRAD defined a placeholder for the construction template directory in the construction template
//				matTplPath = convertIBKPathWithPlaceholderToPath(matTplPath);
//				search curly brackets
				Pattern placeHolderPattern = Pattern.compile("\\{(.*?)}");
				Matcher matcher = placeHolderPattern.matcher(matTplPath);
				if(matcher.find()) {
					String matDirPlaceholder = matcher.group(1);
					String path = placeholderMap.get(matDirPlaceholder);
					if(path != null) {
						//						build a path with relative path and directory path
						matTplPath = path + matTplPath.substring(matcher.end());
					}				
				}
				matTpl.setSourceFileUri(matTplPath);

				String thicknessString = thicknessArray[i];
				//				sometimes the IBK templates contains as number 0,15 instead of 0.15 => replace the comma with a point for parsing the float number
				if(thicknessString.contains(",")) thicknessString = thicknessString.replaceAll("," , ".");
				float thickness = Float.valueOf(thicknessString);
				MaterialLayer layer = new MaterialLayer();
				layer.setTemplate(matTpl);
				layer.setThickness(thickness);
				layer.setUnit(unit);

				template.addMaterialLayer(i, layer);
			}

			//<Costs>
			expr = xpath.compile("//"+XML_TAGS.Costs);
			String costs = ((String) expr.evaluate(root, XPathConstants.STRING)).trim();	
			if(StringUtils.isNotEmpty(costs)) {
				try {
					Double costsPerSquareMeter = NumberUtils.createDouble(costs);
					if(costsPerSquareMeter != null)	template.setCostsPerSquareMeter(costsPerSquareMeter);
				} catch (NumberFormatException e) {
					template.setCostsPerSquareMeter(0.0);
					LOG.debug("No valid costs: {} for template: {}", 
							new Object[]{costs, template.getName()});
				}
			}
		} catch (ParserConfigurationException e) {
			throw new ParsingException(e);
		} catch (SAXException e) {
			throw new ParsingException(e);
		} catch (IOException e) {
			throw new ParsingException(e);
		} catch (XPathExpressionException e) {
			throw new ParsingException(e);
		} finally {
			in.close();
		}
//		LOG.trace("Following construction template: {} is built", new Object[]{template});
		return template;
	}
	
	/**
	 * Deserialize the file to a template.
	 *
	 * @param filePath the file path
	 * @return the construction template
	 * @throws ParsingException the parsing exception
	 * @throws IOException 
	 */
	public static ConstructionTemplate deserialize(String filePath) throws ParsingException, IOException {
		LOG.trace("Reading construction template: {}", filePath);
		File file = new File(filePath);
		if(!file.exists()) {
			throw new FileNotFoundException("File not found");
		}
		
		InputStream in = new FileInputStream(file);	
		ConstructionTemplate template = deserialize(in);
		template.setSourceFileUri(filePath);
		return template;
	}
	
	private static String convertIBKPathWithPlaceholderToPath(String ibkPathWithPlaceholder) {
		int lastSlash = ibkPathWithPlaceholder.lastIndexOf(File.separator);
		String fileName = ibkPathWithPlaceholder.substring(lastSlash+1);
		
		return RELATIVE_MATERIAL_DATABASE_DIRECTORY_PATH+File.separator+fileName;
	}
}
