package de.tudresden.bau.cib.vl.core.model.eeBim.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.core.model.eeBim.exception.ResourceServiceException;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.OmniClass;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.ClassifiableAnnotable;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.Eetemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.ResourceDescriptor;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.json.RegisterGsonAdaptors;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.query.ResourceQueries;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.DataResourceService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;


public class DataResourceServiceImpl extends AbstractResourceServiceImpl implements DataResourceService {
	
	
	private Gson gson = null;
	private String BASE_URL = "http://130.208.198.50:8080"; 
	
	private static final Logger LOG = LoggerFactory.getLogger(DataResourceServiceImpl.class);
	
	private List<ConstructionTemplate> constructions = null;
	private List<MaterialTemplate> materials = null;
	private List<ClimateLocationTemplate> climates = null;

	
	public DataResourceServiceImpl() {
		gson = RegisterGsonAdaptors.getJson();
	}
	
	@Override
	public void setConfigurationService(ConfigurationService configurationService) {
		super.setConfigurationService(configurationService);
		retrieveResourceURL();
	}
	
	private void retrieveResourceURL() {
		String url = configurationService.getProperty("RESOURCE_WEBSERVICE");
		if(url != null) BASE_URL = url;
	}

	@Override
	public ResourceDescriptor getResource(String id) throws ResourceServiceException {
		String methodUrl = BASE_URL+"/drs/resource/"+id;
		Type listType = new TypeToken<ResourceDescriptor>(){}.getType();
		ResourceDescriptor rd = null;
		Reader reader = null;
		try {
			reader = getServiceInputStreamReader(methodUrl);
			rd = gson.fromJson(reader, listType);
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}
		return rd;
	}

	@Override
	public void deleteResource(String id) throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/dm/"+id;

		DeleteMethod delete = null;
		try {
			delete = new DeleteMethod(methodUrl);
			delete.setRequestHeader("Content-type", "application/json; charset=UTF-8");
			//delete.setDoAuthentication(true);

			HttpClient httpclient = new HttpClient();

			int result = httpclient.executeMethod(delete);
			LOG.debug("Response status code: {}", result);
			if (result != HttpStatus.SC_OK){
				//throw new Exception(HttpStatus.getStatusText(result));
				LOG.debug("Response status: {}", HttpStatus.getStatusText(result));
			}

		} catch (UnsupportedEncodingException e) {
			throw new ResourceServiceException(e);
		} catch (HttpException e) {
			throw new ResourceServiceException(e);
		} catch (IOException e) {
			throw new ResourceServiceException(e);
		} finally {
			// Release current connection to the connection pool once you are
			// done
			delete.releaseConnection();
		}
	}

	@Override
	public void postResource(ResourceDescriptor rd) throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/dm/";
		Type listType = new TypeToken<ResourceDescriptor>(){}.getType();
		String jsonRequest = null;

		try {
			jsonRequest = gson.toJson(rd, listType); 
			if(jsonRequest != null && !jsonRequest.isEmpty()) {
				PostMethod post = null;
				try {
					post = new PostMethod(methodUrl);
					RequestEntity entity;
					InputStream is = new ByteArrayInputStream(jsonRequest.getBytes());
					entity = new InputStreamRequestEntity(is);
					post.setRequestEntity(entity);
					post.setRequestHeader("Content-type", "application/json; charset=UTF-8");
					//put.setDoAuthentication(true);

					HttpClient httpclient = new HttpClient();

					int result = httpclient.executeMethod(post);
					LOG.debug("Response status code: {}", result);
					if (result != HttpStatus.SC_OK){
						LOG.debug("Response status: {}", HttpStatus.getStatusText(result));
					}

				} catch (UnsupportedEncodingException e) {
					throw new ResourceServiceException(e);
				} catch (HttpException e) {
					throw new ResourceServiceException(e);
				} catch (IOException e) {
					throw new ResourceServiceException(e);
				} finally {
					// Release current connection to the connection pool once you are
					// done
					post.releaseConnection();
				}
			}
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} 	
	}

	@Override
	public void putResource(ResourceDescriptor rd) throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/dm/";
		Type listType = new TypeToken<ResourceDescriptor>(){}.getType();
		String jsonRequest = null;

		try {
			jsonRequest = gson.toJson(rd, listType); 
			if(jsonRequest != null && !jsonRequest.isEmpty()) {
				PutMethod put = null;
				try {
					put = new PutMethod(methodUrl);
					RequestEntity entity;
					InputStream is = new ByteArrayInputStream(jsonRequest.getBytes());
					entity = new InputStreamRequestEntity(is);
					put.setRequestEntity(entity);
					put.setRequestHeader("Content-type", "application/json; charset=UTF-8");
					//put.setDoAuthentication(true);

					HttpClient httpclient = new HttpClient();

					int result = httpclient.executeMethod(put);
					LOG.debug("Response status code: {}", result);
					if (result != HttpStatus.SC_OK){
						//throw new Exception(HttpStatus.getStatusText(result));
						LOG.debug("Response status: {}", HttpStatus.getStatusText(result));
					}

				} catch (UnsupportedEncodingException e) {
					throw new ResourceServiceException(e);
				} catch (HttpException e) {
					throw new ResourceServiceException(e);
				} catch (IOException e) {
					throw new ResourceServiceException(e);
				} finally {
					// Release current connection to the connection pool once you are
					// done
					put.releaseConnection();
				}
			}
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} 	
	}
	
	@Override
	public List<ClassifiableAnnotable> listResourceCatalogues() throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/dms";
		Type listType = new TypeToken<List<ClassifiableAnnotable>>(){}.getType();
		List<ClassifiableAnnotable> templist = null;
		Reader reader = null;
		try {
			reader = getServiceInputStreamReader(methodUrl);
			templist = gson.fromJson(reader, listType);
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}
		return templist;
	}
	
	@Override
	public List<ResourceDescriptor> getAllResources() throws ResourceServiceException {
    	String methodUrl = BASE_URL+"/drs/resourcesearch/all";
		Type listType = new TypeToken<List<ResourceDescriptor>>(){}.getType();
		List<ResourceDescriptor> templist = null;
		Reader reader = null;
		try {
			reader = getServiceInputStreamReader(methodUrl);
			templist = gson.fromJson(reader, listType);
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}
		return templist;
	}
	
	@Override
	public List<Eetemplate> getAllTemplates() throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/templates";
		Type listType = new TypeToken<List<Eetemplate>>(){}.getType();
		List<Eetemplate> templist = null;
		Reader reader = null;
		try {
			reader = getServiceInputStreamReader(methodUrl);
			templist = gson.fromJson(reader, listType);
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}
		return templist;
	}
	
	@Override
	public Eetemplate getTemplate(String id) throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/template/"+id;
		Eetemplate tpl = null;
		Reader reader = null;
		try {
			reader = getServiceInputStreamReader(methodUrl);
			tpl = gson.fromJson(reader, Eetemplate.class);
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}
		return tpl;
	}

	@Override
	public List<ClassifiableAnnotable> getAllEntities() throws ResourceServiceException {
		String methodUrl = BASE_URL+"/drs/search/all";
		Type listType = new TypeToken<List<ClassifiableAnnotable>>(){}.getType();
		List<ClassifiableAnnotable> templist = null;
		Reader reader = null;
		try {
			reader = getServiceInputStreamReader(methodUrl);
			templist = gson.fromJson(reader, listType);
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}
		return templist;
	}

	private Reader getServiceInputStreamReader(String methodUrl) throws HttpException, IOException {
		GetMethod get = new GetMethod(methodUrl);
		get.addRequestHeader("Accept" , "application/json");
		HttpClient httpclient = new HttpClient();

		InputStream is = null;
		BufferedReader isreader = null;
		int result = httpclient.executeMethod(get);
		LOG.debug("Response status code: {}", result);

		is = get.getResponseBodyAsStream();
		isreader = new BufferedReader(new InputStreamReader(is));
		
//		String line = null;
//		while((line = isreader.readLine()) != null) {
//			System.out.println(line);
//		}
		return isreader;
	}
	
	private InputStream getServiceInputStream(String methodUrl, String mimeMediaType) throws HttpException, IOException {
		GetMethod get = new GetMethod(methodUrl);
		get.addRequestHeader("Accept" , mimeMediaType);
		HttpClient httpclient = new HttpClient();

		InputStream is = null;
		int result = httpclient.executeMethod(get);
		LOG.debug("Response status code: {}", result);

		is = get.getResponseBodyAsStream();
		return is;
	}

	@Override
	public List<Eetemplate> getTemplatesWhichContains(String pattern)
			throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/templatesearch/properties/name="+pattern;
		
		Type listType = new TypeToken<List<Eetemplate>>(){}.getType();
		List<Eetemplate> templist = null;
		Reader reader = null;
		try {
			reader = getServiceInputStreamReader(methodUrl);
			templist = gson.fromJson(reader, listType);
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}
		return templist;
	}

	@Override
	public List<Eetemplate> getTemplatesOfType(CLASS_TYPE type) throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/templatesearch/"+type.getClassification();
		
		Type listType = new TypeToken<List<Eetemplate>>(){}.getType();
		List<Eetemplate> templist = null;
		Reader reader = null;
		try {
			reader = getServiceInputStreamReader(methodUrl);
			templist = gson.fromJson(reader, listType);
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}
		return templist;
	}

	@Override
	public String getContentOfResource(String id) throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/template/openfile/"+id;
		
		try {
			GetMethod get = new GetMethod(methodUrl);
			get.addRequestHeader("Accept" , "application/xml");
			HttpClient httpclient = new HttpClient();
	
			InputStream is = null;
			BufferedReader isreader = null;
			int result = httpclient.executeMethod(get);
			LOG.debug("Response status code: {}", result);
			
			return get.getResponseBodyAsString();
	
//			is = get.getResponseBodyAsStream();
//			isreader = new BufferedReader(new InputStreamReader(is));
//			return isreader;
		} catch (IOException e) {
			throw new ResourceServiceException(e);
		}
	}

	@Override
	public List<Eetemplate> getTemplatesByOmniClass(OmniClass oc)
			throws ResourceServiceException {
		String number = oc.getOmniClassNumber();
		number = number.replaceAll(" ", "%20");
		String methodUrl = BASE_URL+"/dfservice/templatesearch/"+number;
		
		Type listType = new TypeToken<List<Eetemplate>>(){}.getType();
		List<Eetemplate> templist = null;
		Reader reader = null;
		try {
			reader = getServiceInputStreamReader(methodUrl);
			templist = gson.fromJson(reader, listType);
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}
		return templist;
	}

	@Override
	public ClassifiableAnnotable getResourceDescriptor(String id) throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/dm/"+id;
		Type listType = new TypeToken<ClassifiableAnnotable>(){}.getType();
		ClassifiableAnnotable rd = null;
		Reader reader = null;
		try {
			reader = getServiceInputStreamReader(methodUrl);
			rd = gson.fromJson(reader, listType);
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}
		return rd;
	}

	@Override
	public String getAllResourcesFromCatalogue(String catalogueId) throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/dm/open/"+catalogueId+"/GETLIST/*";
		BufferedReader isreader = null;
		String content = "";
		try {
			InputStream is = getServiceInputStream(methodUrl, "application/xml");
			isreader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			// there is a problem while beginning and closing tags are not in the right syntax
			while((line = isreader.readLine()) != null) {
				line = line.replaceAll("&lt;", "<");
				line = line.replaceAll("&gt;", ">");
//				line = StringUtils.stripAccents(line);
				content += line;
			}
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		} finally {
			try {
				isreader.close();
			} catch (IOException e) {}
		}
		return content;
	}
	
	@Override
	public String getResourceFromCatalogue(String resourceId, String catalogueId) throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/dm/open/"+catalogueId+"/GETID/"+resourceId;
		BufferedReader isreader = null;
		String content = "";
		try {
			InputStream is = getServiceInputStream(methodUrl, "application/xml");
			isreader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			// there is a problem while beginning and closing tags are not in the right syntax
			while((line = isreader.readLine()) != null) {
				line = line.replaceAll("&lt;", "<");
				line = line.replaceAll("&gt;", ">");
//				line = StringUtils.stripAccents(line);
				content += line;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new ResourceServiceException(e);
		} finally {
			try {
				if(isreader != null) isreader.close();
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return content;
	}

	@Override
	public Double calculateUValue(Map<Integer, MaterialLayer> materialLayers, Collection<MaterialTemplate> materialTemplates) {
		throw new UnsupportedOperationException("Currently not supported");
	}

	@Override
	public MeasuredUnit getDirectRadiation(ClimateLocationTemplate climateTemplate) throws IOException {
		throw new UnsupportedOperationException("Currently not supported");
	}

	@Override
	public MeasuredUnit getDiffuseRadiation(ClimateLocationTemplate climateTemplate) throws IOException {
		throw new UnsupportedOperationException("Currently not supported");
	}

	@Override
	public MeasuredUnit getTemperaturesOfReferenceYear(ClimateLocationTemplate climateTemplate) throws IOException {
		throw new UnsupportedOperationException("Currently not supported");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClimateLocationTemplate> listClimateResources() {
		if(climates != null) return climates;
		final String climateCatalogueId = "69";
		LOG.debug("Searching climate resources from catalogue: {}", new Object[]{climateCatalogueId});
		
		if(climates == null) {
			String mode = configurationService.getProperty(PROPERTIES.LOAD_RESOURCES_MODE);
			if(mode.equalsIgnoreCase("local")) {
				LOG.debug("Load local climate resources");
				climates = (List<ClimateLocationTemplate>) loadLocalResources(
						climateCatalogueId, PROPERTIES.PATH_TO_NMI_CLIMATES);
			} else if(mode.equalsIgnoreCase("remote")) {
				LOG.debug("Load remote climate resources");
				climates = new ArrayList<ClimateLocationTemplate>();
				try {
				    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				    factory.setNamespaceAware(true);
				    DocumentBuilder builder;
				    Document doc = null;
				    
				    builder = factory.newDocumentBuilder();
				    
					// load it from NMI web service
				    String xml = getAllResourcesFromCatalogue(climateCatalogueId);
				    InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8")); 
				    doc = builder.parse(stream);
				    climates.addAll(ResourceQueries.getAllClimates(BASE_URL+"/dfservice/dm/open/"+climateCatalogueId+"/GETID/", 
				    		doc, climateCatalogueId));
				} catch (Exception e) {
					LOG.error("Error when retrieving climate resources from resource service: {}", e.getMessage(), e);
				}
			} else {
				throw new IllegalArgumentException("Wrong mode in configuration!");
			}
		} 
		return climates;
	}

	@Override
	public List<ClimateLocationTemplate> listClimateResources(Integer userId)
			throws FileNotFoundException, ParsingException {
		// currently there are no user specific templates
		return listClimateResources();
	}
	
	@Override
	public List<MaterialTemplate> listMaterialsOfConstruction(
			ConstructionTemplate construction) {
		LOG.debug("Searching material resources of construction: {}", construction);
		final String ibkMaterialCatalogueId = "45";
//		final String ibkConstructionCatalogueId = "74"; // with materials
		List<MaterialTemplate> materials = new ArrayList<MaterialTemplate>();
		String uri = construction.getSourceFileUri();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder;
			Document doc = null;
			builder = factory.newDocumentBuilder();
			
			String xml = getResourceFromCatalogue(construction.getId(), construction.getCatalogueId());
		    InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8")); 
		    doc = builder.parse(stream);
	
			materials = ResourceQueries.addMaterialsToConstruction(doc, construction, 
					BASE_URL+"/dfservice/dm/open/"+ibkMaterialCatalogueId+"/GETID/", ibkMaterialCatalogueId);
			return materials;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return materials;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConstructionTemplate> listConstructionResources() {
		if(constructions != null) return constructions;
		LOG.debug("Searching for all construction resources");
		
		final String ibkConstructionCatalogueIdWithoutMaterials = "47"; // without materials
		final String ibkConstructionCatalogueIdWithMaterials = "74"; // with materials		
//		final String trimoCatalogue = "68"; // TRIMO catalogue without materials
//		final String trimoCatalogue = "94"; // TRIMO catalogue with materials
		
		if(constructions == null) {
			String mode = configurationService.getProperty(PROPERTIES.LOAD_RESOURCES_MODE);
			if(mode.equalsIgnoreCase("local")) {
				LOG.debug("Load local construction resources");
				constructions = (List<ConstructionTemplate>) loadLocalResources(
						ibkConstructionCatalogueIdWithMaterials, PROPERTIES.PATH_TO_NMI_CONSTRUCTIONS);
//				List<ConstructionTemplate> trimoConstructions = (List<ConstructionTemplate>) loadLocalResources(
//						trimoCatalogue, PROPERTIES.PATH_TO_NMI_CONSTRUCTIONS_TRIMO);
//				if(trimoConstructions != null) constructions.addAll(trimoConstructions);
			} else if(mode.equalsIgnoreCase("remote")) {
				LOG.debug("Load remote construction resources");
				constructions = new ArrayList<ConstructionTemplate>();
				try {
				    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				    factory.setNamespaceAware(true);
				    DocumentBuilder builder;
				    Document doc = null;
				    
				    builder = factory.newDocumentBuilder();
				    
					// load it from NMI web service
				    String xml = getAllResourcesFromCatalogue(ibkConstructionCatalogueIdWithoutMaterials);
				    InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8")); 
				    doc = builder.parse(stream);
				    // without materials but use the URI with materials so that afterwards materials can be retrieved
				    constructions.addAll(ResourceQueries.getConstructionsWithoutMaterials(BASE_URL+"/dfservice/dm/open/"+ibkConstructionCatalogueIdWithMaterials+"/GETID/", 
				    		doc, ibkConstructionCatalogueIdWithMaterials));
				    
//				    // load Trimo elements FIXME Catalogue is not complete
//				    xml = getAllResourcesFromCatalogue(trimoCatalogue);
//				    stream = new ByteArrayInputStream(xml.getBytes("UTF-8")); 
//				    doc = builder.parse(stream);
//				    constructions.addAll(ResourceQueries.getConstructionsWithoutMaterials(BASE_URL+"/dfservice/dm/open/"+trimoCatalogue+"/GETID/", doc, trimoCatalogue));
				    
				    // with materials it takes too much time
//					if(materials == null) materials = listMaterialTemplates();
//				    constructions = ResourceQueries.getConstructionsWithMaterials(BASE_URL+"/dfservice/dm/open/"+ibkConstructionCatalogueId+"/GETID/", doc, materials);
				} catch (Exception e) {
					LOG.error("Error when retrieving construction resources from resource service: {}", e.getMessage(), e);
				}
			} else {
				throw new IllegalArgumentException("Wrong mode in configuration!");
			}
		} 

		return constructions;
	}

	@Override
	public List<ConstructionTemplate> listConstructionResources(Integer userId)
			throws IOException, ParsingException {
		// currently there are no user specific templates
		return listConstructionResources();
	}

	@Override
	public List<SpaceTypeTemplate> listOccupancyResources() {
		LOG.error("Occupancy resources are currently not supported");
		return new ArrayList<SpaceTypeTemplate>();
	}

	@Override
	public List<SpaceTypeTemplate> listOccupancyResources(Integer userId)
			throws FileNotFoundException, ParsingException {
		return listOccupancyResources();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialTemplate> listMaterialTemplates() {
		if(materials != null) return materials;
		LOG.debug("Searching for all material resources");
		final String ibkMaterialCatalogueId = "45";
		if(materials == null) {
			String mode = configurationService.getProperty(PROPERTIES.LOAD_RESOURCES_MODE);
			if(mode.equalsIgnoreCase("local")) {
				LOG.debug("Load local material resources");
				materials = (List<MaterialTemplate>) loadLocalResources(ibkMaterialCatalogueId, PROPERTIES.PATH_TO_NMI_MATERIALS);
			} else if(mode.equalsIgnoreCase("remote")){
				LOG.debug("Load remote material resources");
				materials = new ArrayList<MaterialTemplate>();
				try {
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					factory.setNamespaceAware(true);
					DocumentBuilder builder;
					Document doc = null;
					builder = factory.newDocumentBuilder();
					
					// load it from NMI web service
					String xml = getAllResourcesFromCatalogue(ibkMaterialCatalogueId);
					InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
					doc = builder.parse(stream);
					materials =  ResourceQueries.getAllMaterials(doc, 
							BASE_URL+"/dfservice/dm/open/"+ibkMaterialCatalogueId+"/GETID/", ibkMaterialCatalogueId);
				} catch (Exception e) {
					LOG.error("Error when retrieving materials resources from resource service: {}", e.getMessage(), e);
				}
			}
		}

		return materials;
	}

	private List<? extends Resource> loadLocalResources(String catalogueId, PROPERTIES pathToResources) {
		String filePath = configurationService.getProperty(pathToResources);
		List<? extends Resource> resources = null;
		try {
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    factory.setNamespaceAware(true);
		    DocumentBuilder builder;
		    Document doc = null;
		    
		    builder = factory.newDocumentBuilder();
		    File file = new File(filePath);
		    InputSource source = new InputSource(new FileReader(file));
		    source.setEncoding("UTF-8");
		    doc = builder.parse(source);
		    
			switch(pathToResources) {
			case PATH_TO_NMI_CONSTRUCTIONS:
				// with materials
//			    resources = ResourceQueries.getConstructionsWithMaterials(BASE_URL+"/dfservice/dm/open/"+catalogueId+"/GETID/", doc, materials);
			    // without materials
				resources = ResourceQueries.getConstructionsWithoutMaterials(BASE_URL+"/dfservice/dm/open/"+catalogueId+"/GETID/", doc, catalogueId);
			    break;
			case PATH_TO_NMI_CONSTRUCTIONS_TRIMO:
				// with materials
//			    resources = ResourceQueries.getConstructionsWithMaterials(BASE_URL+"/dfservice/dm/open/"+catalogueId+"/GETID/", doc, materials);
			    // without materials
				resources = ResourceQueries.getConstructionsWithoutMaterials(BASE_URL+"/dfservice/dm/open/"+catalogueId+"/GETID/", doc, catalogueId);
			    break;
			case PATH_TO_NMI_MATERIALS:
			    resources =  ResourceQueries.getAllMaterials(doc, BASE_URL+"/dfservice/dm/open/"+catalogueId+"/GETID/", catalogueId);
				break;
			case PATH_TO_NMI_CLIMATES:
				resources = ResourceQueries.getAllClimates(BASE_URL+"/dfservice/dm/open/"+catalogueId+"/GETID/", doc, catalogueId);
				break;
			default: break;
			}
			
			return resources;
		} catch (Exception e) {
			LOG.error("Error when retrieving construction resources from file: {}", 
					new Object[]{filePath}, e);
		}
		return new ArrayList<Resource>();
	}

	@Override
	public List<MaterialTemplate> listMaterialTemplates(Integer userId)
			throws ParsingException, IOException {
		// currently there are no user specific templates
		return listMaterialTemplates();
	}

	@Override
	public boolean deleteTemplate(Integer userId, Resource template) {
		throw new UnsupportedOperationException("Currently not supported");
	}

	@Override
	public String uploadConstructionTemplates(Integer userId, String filePath)
			throws IOException {
		throw new UnsupportedOperationException("Currently not supported");
	}

	@Override
	public void uploadClimateTemplates(Integer userId, String[] fileNames)
			throws IOException {
		throw new UnsupportedOperationException("Currently not supported");
	}

	@Override
	public boolean uploadSpaceTypeTemplates(Integer userId, String[] fileNames)
			throws IOException {
		throw new UnsupportedOperationException("Currently not supported");
	}

	@Override
	public String saveConstructionTemplate(Integer userId,
			ConstructionTemplate template) throws IOException, ParsingException {
		throw new UnsupportedOperationException("Currently not supported");
	}

	@Override
	public boolean saveSpaceTypeTemplate(Integer userId,
			SpaceTypeTemplate template) throws ParsingException, IOException {
		throw new UnsupportedOperationException("Currently not supported");
	}
	
	@Override
	public InputStream stochastifyConstruction(ConstructionTemplate resource, double meanValue, double standardDeviation, int numberOfSamples) throws ResourceServiceException {
		// http://ibriexweb.rabygg.is:8080/stpservice/stp/process/56/constructionid=10,samplingenabled=1,uvaluecalc=1
		try {
			String id = resource.getId();
			
			URL url = new URL(BASE_URL+"/stpservice/stp/process/56/constructionid="+id+",samplingenabled=1,uvaluecalc=1");
			return url.openStream();
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		}
	}
	
	@Override
	public File stochastifyMaterial(InputStream resource, double meanValue, double standardDeviation, int numberOfSamples, String destPath) 
			throws ResourceServiceException {
		try {
			// read input XML		
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(resource);
			doc.normalizeDocument();
			
			// call stochastic sample generator
			double[] samples = generateSamples(meanValue, standardDeviation, numberOfSamples);
			
			XPathExpression expr = null;
		    XPathFactory xFactory = XPathFactory.newInstance();
		    XPath xpath = xFactory.newXPath();
	
		    // search for Material tags
		    expr = xpath.compile("//Material/Resistance");
		    Object searchResult = expr.evaluate(doc, XPathConstants.NODESET);
		    NodeList list = (NodeList) searchResult;
		    if(list.getLength() > 1) throw new IllegalArgumentException("Input resource contains multiple tags! Please call this method again with exact 1 material");
		    for (int i = 0; i < list.getLength(); i++){
		    	Node node = list.item(i);
		    	node.setTextContent("");
		    	
		    	String sampleText = "";
		    	Element samplesNode = doc.createElement("samples");
		    	
		    	for(int s = 0; s < samples.length; s++) {
		    		sampleText += " "+samples[s];
		    	}
		    	sampleText = sampleText.trim();
		    	samplesNode.setTextContent(sampleText);
		    	node.appendChild(samplesNode); 	
		    }
		    
		    // write it to the destination path
			File outputFile = new File(destPath);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(outputFile);
	 
			transformer.transform(source, result);
			return outputFile;
		} catch (Exception e) {
			throw new ResourceServiceException(e);
		}
	}

	@Override
	public ClassifiableAnnotable searchProfile(String tableId,
			String omniClassId) throws ResourceServiceException {
		String methodUrl = BASE_URL+"/dfservice/templatesearch/"+tableId+"/"+omniClassId;
		throw new UnsupportedOperationException("Currently not supported");
	}

	
}
