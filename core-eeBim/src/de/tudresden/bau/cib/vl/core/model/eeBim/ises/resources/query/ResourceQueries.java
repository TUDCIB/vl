package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;

public class ResourceQueries {

	
	private ResourceQueries() {
		throw new AssertionError();
	}
	
	/**
	 * @param xmlDoc
	 * @return A map with IDs as keys and names as values.
	 * @throws XPathExpressionException
	 */
	public static Map<String, String> getAllConstructionNames(Document xmlDoc) throws XPathExpressionException {
		Map<String, String> constructions = new TreeMap<String, String>();
		XPathExpression expr = null;
	    XPathFactory xFactory = XPathFactory.newInstance();
	    XPath xpath = xFactory.newXPath();

	    // search for Construction tags
	    expr = xpath.compile("//Construction");
	    Object result = expr.evaluate(xmlDoc, XPathConstants.NODESET);
	    NodeList constructionList = (NodeList) result;
	    for (int i = 0; i < constructionList.getLength(); i++){
	    	Node node = constructionList.item(i);
	    	Element element = (Element) node;
	    	String id = element.getAttribute("id");
	    	
	    	// search for Name tag
	    	expr = xpath.compile("Name");
	    	Object name = expr.evaluate(node, XPathConstants.STRING);
	    	if(StringUtils.isEmpty((CharSequence) name)) {
		    	// search for Shortname tag
		    	expr = xpath.compile("Shortname");
		    	name = expr.evaluate(node, XPathConstants.STRING);
	    	}
	    	
	    	constructions.put(id, (String) name);
	    }
	    return constructions;
	}
	
	public static List<ConstructionTemplate> getConstructionsWithMaterials(String baseUri, Document root, 
			List<MaterialTemplate> materials, String constructionCatalogueId) throws XPathExpressionException, InterruptedException, ExecutionException {
		List<ConstructionTemplate> constructions = new ArrayList<ConstructionTemplate>();
		materials = Collections.synchronizedList(materials);
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		XPathExpression expr = null;
	    XPathFactory xFactory = XPathFactory.newInstance();
	    XPath xpath = xFactory.newXPath();

	    // search for Construction tags
	    expr = xpath.compile("//Construction");
	    Object result = expr.evaluate(root, XPathConstants.NODESET);
	    NodeList constructionList = (NodeList) result;
	    List<Future<ConstructionTemplate>> futures = new ArrayList<Future<ConstructionTemplate>>();
	    for (int i = 0; i < constructionList.getLength(); i++){
	    	Node node = constructionList.item(i);
	    	Element element = (Element) node;
	    	String id = element.getAttribute("id");
	    	Callable<ConstructionTemplate> worker = new CreateConstructionResourceWorker(
	    			baseUri+id, element, materials, constructionCatalogueId);
	        Future<ConstructionTemplate> future = executor.submit(worker);	
	        futures.add(future);
	        
//	    	ConstructionTemplate construction = getConstructionWithMaterials(baseUri+id, element, materials);
//	    	constructions.add(construction);
	    }
	    
	    for(Future<ConstructionTemplate> future : futures) {
	    	ConstructionTemplate construction = future.get();
	    	constructions.add(construction);
	    }
	    // This will make the executor accept no new threads
	    // and finish all existing threads in the queue
	    executor.shutdown();
	    // Wait until all threads are finish
	    executor.awaitTermination(1, TimeUnit.HOURS);
	    	
		return constructions;
	}
	
	public static List<ConstructionTemplate> getConstructionsWithoutMaterials(String baseUri, Document doc, String catalogueId) 
			throws XPathExpressionException {
		List<ConstructionTemplate> constructionsList = new ArrayList<ConstructionTemplate>();
		
		XPathExpression expr = null;
	    XPathFactory xFactory = XPathFactory.newInstance();
	    XPath xpath = xFactory.newXPath();

	    // search for Construction tags
	    expr = xpath.compile("//Construction");
	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList constructionList = (NodeList) result;
	    for (int i = 0; i < constructionList.getLength(); i++){
	    	Node node = constructionList.item(i);
	    	Element element = (Element) node;
	    	String id = element.getAttribute("id");
	    	
	    	// search for Name tag
	    	expr = xpath.compile("Name");
	    	Object name = expr.evaluate(node, XPathConstants.STRING);
	    	if(StringUtils.isEmpty((CharSequence) name)) {
		    	// search for Shortname tag
		    	expr = xpath.compile("Shortname");
		    	name = expr.evaluate(node, XPathConstants.STRING);
	    	}
	    	
	    	ConstructionTemplate resource = new ConstructionTemplate();
	    	resource.setName((String) name);
			resource.setSourceFileUri(baseUri+id);
			resource.setId(id);
			resource.setCatalogueId(catalogueId);
			
			// search u-value
			expr = xpath.compile("Uvalue");
			Object uValue = expr.evaluate(node, XPathConstants.NUMBER);
			if(uValue != null) {
				resource.setUValue((Double)uValue);
				
				expr = xpath.compile("Uvalue[@unit]");
				Object uValueUnit = expr.evaluate(node, XPathConstants.STRING);
				resource.setUValueUnit((String)uValueUnit);
			}
			
			constructionsList.add(resource);
	    }
	    return constructionsList;
	}
	
	public static List<MaterialTemplate> addMaterialsToConstruction(Document root, ConstructionTemplate construction, 
			String baseUrl, String materialCatalogueId) throws XPathExpressionException, InterruptedException, ExecutionException {
		XPathExpression expr = null;
		XPathFactory xFactory = XPathFactory.newInstance();
		XPath xpath = xFactory.newXPath();
		List<MaterialTemplate> materials = getAllMaterials(root, baseUrl, materialCatalogueId);;
	    // search for Construction tags
	    expr = xpath.compile("//Construction");
	    Object result = expr.evaluate(root, XPathConstants.NODESET);
	    NodeList constructionList = (NodeList) result;
	    for (int j = 0; j < constructionList.getLength(); j++){
	    	Node node = constructionList.item(j);
	    	Element element = (Element) node;

			Map<Integer,MaterialLayer> layerMap = new HashMap<Integer,MaterialLayer>();
			// search for Layers tag
			expr = xpath.compile(".//Layer");
			Object layers = expr.evaluate(element, XPathConstants.NODESET);
			NodeList layersList = (NodeList) layers;
			for (int i = 0; i < layersList.getLength(); i++){
				Element layerNode = (Element) layersList.item(i); 		

				// search for Material tag
				expr = xpath.compile(".//Material");
				Element materialNode = (Element) expr.evaluate(layerNode, XPathConstants.NODE);
				String materialIdRef = materialNode.getAttribute("materialIdRef");

				// search for Thickness tag
				expr = xpath.compile(".//Thickness");
				Object thicknessValue = expr.evaluate(layerNode, XPathConstants.NUMBER);
				Float thickness = new Float((Double) thicknessValue);

				MaterialTemplate material = findMaterial(materialIdRef, materials);
				if(material != null) {
					MaterialLayer matLayer = new MaterialLayer();
					matLayer.setTemplate(material);
					matLayer.setThickness(thickness);
					matLayer.setUnit("m");
					layerMap.put(i, matLayer);
				}
			}
			construction.setMaterialLayers(layerMap);
			if(constructionList.getLength() > 1) {
				throw new IllegalArgumentException("Only one construction expected in resource of URI: "+construction.getSourceFileUri());
			}
	    }
		
		return materials;
	}
	
	private static ConstructionTemplate getConstructionWithMaterials(String uri,
			Node constructionNode, Collection<MaterialTemplate> materials, String catalogueId) throws XPathExpressionException {
		XPathExpression expr = null;
		XPathFactory xFactory = XPathFactory.newInstance();
		XPath xpath = xFactory.newXPath();

		Element element = (Element) constructionNode;
		String id = element.getAttribute("id");

		// search for Name tag
		expr = xpath.compile("Name");
		Object name = expr.evaluate(constructionNode, XPathConstants.STRING);

		ConstructionTemplate construction = new ConstructionTemplate();
		construction.setId(id);
		construction.setName((String) name);
		construction.setSourceFileUri(uri);
		construction.setCatalogueId(catalogueId);
		
		// search u-value
		expr = xpath.compile("Uvalue");
		Object uValue = expr.evaluate(constructionNode, XPathConstants.STRING);
		if(uValue != null) {
			construction.setUValue((Double)uValue);
			
			expr = xpath.compile("Uvalue[@unit]");
			Object uValueUnit = expr.evaluate(constructionNode, XPathConstants.STRING);
			construction.setUValueUnit((String)uValueUnit);
		}

		Map<Integer,MaterialLayer> layerMap = new HashMap<Integer,MaterialLayer>();
		// search for Layers tag
		expr = xpath.compile(".//Layer");
		Object layers = expr.evaluate(constructionNode, XPathConstants.NODESET);
		NodeList layersList = (NodeList) layers;
		for (int i = 0; i < layersList.getLength(); i++){
			Element layerNode = (Element) layersList.item(i); 
//			String layerOrder = layerNode.getAttribute("order");
//			String layerId = layerNode.getAttribute("id");

			// search for Material tag
			expr = xpath.compile(".//Material");
			Element materialNode = (Element) expr.evaluate(layerNode, XPathConstants.NODE);
			String materialIdRef = materialNode.getAttribute("materialIdRef");

			// search for Thickness tag
			expr = xpath.compile(".//Thickness");
			Object thicknessValue = expr.evaluate(layerNode, XPathConstants.NUMBER);
			Float thickness = new Float((Double) thicknessValue);

			MaterialTemplate material = findMaterial(materialIdRef, materials);
			if(material != null) {
				MaterialLayer matLayer = new MaterialLayer();
				matLayer.setTemplate(material);
				matLayer.setThickness(thickness);
				matLayer.setUnit("m");
				layerMap.put(i, matLayer);
			}
		}
		construction.setMaterialLayers(layerMap);
		return construction;
	}
	
	private static MaterialTemplate findMaterial(String materialIdRef, Collection<MaterialTemplate> materials) {
		for(MaterialTemplate material : materials) {
			String id = material.getId();
			if(id.equalsIgnoreCase(materialIdRef)) return material;
		}
		return null;
	}

	public static List<MaterialTemplate> getAllMaterials(Document doc, String baseUrl, String catalogueId) throws XPathExpressionException, InterruptedException, ExecutionException {
		List<MaterialTemplate> materials = new ArrayList<MaterialTemplate>();
		XPathExpression expr = null;
	    XPathFactory xFactory = XPathFactory.newInstance();
	    XPath xpath = xFactory.newXPath();
	    ExecutorService executor = Executors.newFixedThreadPool(10);

	    // search for Material tags
	    expr = xpath.compile("//Material");
	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList materialList = (NodeList) result;
	    List<Future<MaterialTemplate>> futures = new ArrayList<Future<MaterialTemplate>>();
	    for (int i = 0; i < materialList.getLength(); i++){
	    	Node node = materialList.item(i);
	    	Callable<MaterialTemplate> worker = new CreateMaterialResourceWorker(baseUrl, (Element)node, catalogueId);
	        Future<MaterialTemplate> future = executor.submit(worker);	
	        futures.add(future);    	
	    }
	    for(Future<MaterialTemplate> future : futures) {
	    	MaterialTemplate material = future.get();
	    	if(material != null) materials.add(material);
	    }
	    // This will make the executor accept no new threads
	    // and finish all existing threads in the queue
	    executor.shutdown();
	    // Wait until all threads are finish
	    executor.awaitTermination(1, TimeUnit.HOURS);
	    return materials;
	}
	
	private static MaterialTemplate createMaterial(Node node, String baseUrl, String catalogueId) throws XPathExpressionException {
		XPathExpression expr = null;
	    XPathFactory xFactory = XPathFactory.newInstance();
	    XPath xpath = xFactory.newXPath();
    	Element element = (Element) node;
    	String id = element.getAttribute("id");
    	
    	// search for Name tag
    	expr = xpath.compile("Name");
    	String name = (String)expr.evaluate(node, XPathConstants.STRING);
    	if(StringUtils.isNotEmpty(id)) {	    	
//    		// search for Density tag
//    		expr = xpath.compile("Density");
//    		Object density = expr.evaluate(node, XPathConstants.NUMBER);
//    	
//    		// search for Resistance tag
//    		expr = xpath.compile("Resistance");
//    		Double resistance = (Double) expr.evaluate(node, XPathConstants.NUMBER);
    	
	    	// search for Conductivity tag
	    	expr = xpath.compile("Conductivity");
	    	Double conductivity = (Double) expr.evaluate(node, XPathConstants.NUMBER);
	    	
	    	MaterialTemplate material = new MaterialTemplate();
	    	material.setName(name);
	    	material.setSourceFileUri(baseUrl+id);
	    	material.setLambda(conductivity);
	    	material.setId(id);
	    	material.setCatalogueId(catalogueId);
	    	
	    	return material;
    	}
    	return null;
	}
	
	public static List<ClimateLocationTemplate> getAllClimates(String baseUri, Document doc, String catalogueId) throws XPathExpressionException {
		List<ClimateLocationTemplate> climates = new ArrayList<ClimateLocationTemplate>();
		XPathExpression expr = null;
	    XPathFactory xFactory = XPathFactory.newInstance();
	    XPath xpath = xFactory.newXPath();

	    // search for WeatherDataZone tags
	    expr = xpath.compile("//WeatherDataZone");
	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList climateList = (NodeList) result;
	    for (int i = 0; i < climateList.getLength(); i++){
	    	Node node = climateList.item(i);
	    	Element element = (Element) node;
	    	String id = element.getAttribute("id");
	    	
	    	// search for Latitude tag
	    	expr = xpath.compile("Latitude");
	    	Object latitude = expr.evaluate(node, XPathConstants.STRING);
	    	
	    	// search for Longitude tag
	    	expr = xpath.compile("Longitude");
	    	Object longitude = expr.evaluate(node, XPathConstants.STRING);
	    	
	    	// search for City tag
	    	expr = xpath.compile("City");
	    	Object city = expr.evaluate(node, XPathConstants.STRING);
	    	
	    	// search for Country tag
	    	expr = xpath.compile("Country");
	    	Object country = expr.evaluate(node, XPathConstants.STRING);
	    	
	    	// search for Elevation tag
	    	expr = xpath.compile("Elevation");
	    	Object elevation = expr.evaluate(node, XPathConstants.STRING);
	    	
	    	ClimateLocationTemplate climate = new ClimateLocationTemplate();
	    	climate.setId(id);
	    	climate.setSourceFileUri(baseUri+id);
	    	climate.setName(country+", "+city);
	    	
	    	climate.setCountry((String)country);	    	
	    	climate.setLatitude(Float.valueOf((String)latitude));
	    	climate.setLongitude(Float.valueOf((String)longitude));
	    	climate.setTown((String)city);
	    	climate.setAltitude(Float.valueOf((String)elevation));    	
	    	climate.setRegion(country+", "+city);
	    	climate.setCatalogueId(catalogueId);
	    	
	    	climates.add(climate);
	    }
	    return climates;
	}
	
	private static class CreateConstructionResourceWorker implements Callable<ConstructionTemplate> {
		
		private String uri;
		private Element element;
		private List<MaterialTemplate> materials;
		private String catalogueId;
		
		public CreateConstructionResourceWorker(String uri, Element element, List<MaterialTemplate> materials, String catalogueId) {
			this.uri = uri;
			this.element = element;
			this.materials = materials;
			this.catalogueId = catalogueId;
		}

		@Override
		public ConstructionTemplate call() throws Exception {
			ConstructionTemplate construction = getConstructionWithMaterials(uri, element, materials, catalogueId);
			return construction;
		}
		
	}
	
	private static class CreateMaterialResourceWorker implements Callable<MaterialTemplate> {
		private String uri;
		private Node node;
		private String catalogueId;
		
		public CreateMaterialResourceWorker(String uri, Node node, String catalogueId) {
			this.uri = uri;
			this.node = node;
			this.catalogueId = catalogueId;
		}

		@Override
		public MaterialTemplate call() throws Exception {
			MaterialTemplate material = createMaterial(node, uri, catalogueId);
			return material;
		}
	}
}
