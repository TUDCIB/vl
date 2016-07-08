package de.tudresden.bau.cib.vl.core.simulation.energy.service.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.DecisionMakingService;

public class DecisionMakingServiceImpl implements DecisionMakingService {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(DecisionMakingServiceImpl.class);
	
	private static ClientConfig config;
	private static Client client;
	private static WebResource service;
	
	private ConfigurationService configurationService;


	@Override
	public URL uploadCase(File csvFile, String simulationName, String projectName) {
		LOG.debug("{} {} {}", new Object[]{csvFile, simulationName, projectName});
		if(!csvFile.exists()) throw new IllegalArgumentException("file: "+csvFile+" not exists");
		// create case
		try {
//			TODO remove old API
//			String content = FileUtils.readFileToString(csvFile);
//			ClientResponse createResponse = service
//					.path("api/DataDisplayApi")
//					.header("X-SimulationTitle", simulationName)
//					.header("X-CollectionID", projectName)
////					.header("Authorization", "Basic a2VuOll0cHdvcmQxNCE=")
//					.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
//					.post(ClientResponse.class, content);
			
			// API from 01.07.2014 - without pictures					
			FileDataBodyPart filePart1 = new FileDataBodyPart("data", csvFile, MediaType.APPLICATION_OCTET_STREAM_TYPE);
//			 FormDataBodyPart fdp = new FormDataBodyPart("content",
//					    new ByteArrayInputStream(FileUtils.readFileToByteArray(csvFile)),
//					    MediaType.APPLICATION_OCTET_STREAM_TYPE);
					  
			FormDataContentDisposition contentDisposition = FormDataContentDisposition
					.name("data")
					.fileName("file_"+System.currentTimeMillis()+".csv").build();
			filePart1.contentDisposition(contentDisposition);
			LOG.debug("Disposition {}", new Object[]{contentDisposition});
			
			FormDataMultiPart multiPart = new FormDataMultiPart();				
			multiPart.bodyPart(filePart1);
//			multiPart.bodyPart(fdp);
			LOG.debug("Multipart {}", new Object[]{multiPart});
			
			ClientResponse createResponse = service
					.path("api/DataDisplayApi")
					.header("X-SimulationTitle", simulationName)
					.header("X-CollectionID", projectName)
//					.header("Authorization", "Basic a2VuOll0cHdvcmQxNCE=")
					.type(MediaType.MULTIPART_FORM_DATA_TYPE)
					.post(ClientResponse.class, multiPart);
			LOG.debug("Response {}", new Object[]{createResponse});
			
//			// API from 01.07.2014 - with pictures
//			FormDataMultiPart multiPart = new FormDataMultiPart();
//			
//			FileDataBodyPart filePart1 = new FileDataBodyPart("csv-file", csvFile);
//			File image					=	new File("D:\\Nutzer\\ken\\Workspaces\\VirtualLaboratory\\master\\core-energy\\test\\resources\\decisionmaking\\Case1_0.jpg");
//			FileDataBodyPart filePartGraphic1 = new FileDataBodyPart("jpeg-1", image);
//			FormDataContentDisposition contentDisposition = FormDataContentDisposition
//					.name("data")
//					.fileName("Case1_0.jpg")
//					.build();
//			multiPart.contentDisposition(contentDisposition);
//			multiPart.bodyPart(filePart1);
//			multiPart.bodyPart(filePartGraphic1);
//			
//			ClientResponse createResponse = service
//					.path("api/DataDisplayApi")
//					.header("X-SimulationTitle", simulationName)
//					.header("X-CollectionID", projectName)
//					.header("Content-Type", "image/jpeg")
////					.header("Authorization", "Basic a2VuOll0cHdvcmQxNCE=")
//					.type(MediaType.MULTIPART_FORM_DATA_TYPE)
//					.post(ClientResponse.class, multiPart);

			int status = createResponse.getStatus();
			LOG.debug("Response status: {} from upload of file: {}", new Object[]{status, csvFile});

			if(status < 400) {		
				MultivaluedMap<String, String> createResponseHeaders = createResponse.getHeaders();
				String visualizationUrl = createResponseHeaders.getFirst("Location");
				String createResponseContent = createResponse.getEntity(String.class);
				
				// request token
				String token = createToken();
				
				// create URL by combining case URL and token
//				URL url = new URL(visualizationUrl+"?t="+token);
				URL url = new URL(visualizationUrl);
				LOG.debug("URL of uploaded case: {}", new Object[]{url});
				return url;	
			}
		} catch (MalformedURLException e) {
			LOG.error("Problem by creating URL for case of file: {} with message: {}",
					new Object[]{csvFile, e.getMessage()},e);
		} 
		return null;
	}
	
	

	@Override
	public Set<URL> listAllCases(String projectName) {
		Set<URL> urls = new HashSet<URL>();
		// request token
		String token = createToken();
		
		String json = service
				.path("api/DataDisplayApi")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
	
		JSONArray jsonArray = JSONArray.fromObject(json);
		for(int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			String collectionId = object.getString("CollectionID");
			
			if(projectName == null || projectName.equalsIgnoreCase(collectionId)) {
				String urlObject = object.getString("DisplayUrl");
				try {
					// create URL by combining case URL and token
					URL url = new URL(urlObject.toString()+"?t="+token);
					urls.add(url);
				} catch (MalformedURLException e) {
					LOG.error("Problem receiving all cases: {}", 
							new Object[]{e.getMessage()}, e);
				}
			}
		}
		return urls;
	}
	
	@Override
	public String createToken() {
		String token = service.path("api/TokenApi").get(String.class);
		return token;
	}
	
	private void createClient() {
//		final String 	BASE_URL	=	"http://www.roomex.fi/WebSimVis/";
//		final String	USER		=	"tk-osasto";
//		final String 	PASSWORD	=	"granlund";
		final String 	BASE_URL	=	configurationService.getProperty(PROPERTIES.ISES_GRANLUND_DECISIONMAKING_URL);
		final String	USER		=	configurationService.getProperty(PROPERTIES.ISES_GRANLUND_DECISIONMAKING_USER);
		final String 	PASSWORD	=	configurationService.getProperty(PROPERTIES.ISES_GRANLUND_DECISIONMAKING_PASSWORD);
		
		config = new DefaultClientConfig();
		client = Client.create(config);		
		// add login
		client.addFilter(new HTTPBasicAuthFilter(USER, PASSWORD));
		service = client.resource(UriBuilder.fromUri(BASE_URL).build());
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
		createClient();
	}

}
