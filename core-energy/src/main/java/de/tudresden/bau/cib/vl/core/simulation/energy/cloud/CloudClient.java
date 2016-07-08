package de.tudresden.bau.cib.vl.core.simulation.energy.cloud;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

public class CloudClient {

	private final URI URI;
	private final String TOKEN;
	private WebResource service;
	
	public enum Status {
		IDLE,
		RUNNING,
		NOTSTARTED,
		ERROR,
		DONE,
		OK
		;
		public static Status findByAbbreviation(String name) {
			if(StringUtils.isEmpty(name)) return null;
			Status[] values = values();
			name = name.toLowerCase();
			for(Status status : values) {
				if(StringUtils.contains(name, status.name().toLowerCase())) {
					return status;
				}
			}
			return null;
		}
	}
	
	public enum Type {
		PREPARE_DATA ("prepare_simulation_data"),
		PASSIVE_SIMULATION ("passive_simulation"),
		SENSITIVITY_ANALYSIS ("sensitivity_analysis")
		;
		
		private String name;
		private Type(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	
	
	public CloudClient(URI uri, String token) {
		this.URI = uri;
		this.TOKEN = token;
		init();
	}
	
	private void init() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		service = client.resource(URI);
	}
	
	/**
	 * TODO javadoc
	 * @param projectName
	 * @return Project identifier received from cloud service
	 */
	public String createNewProject(String projectName) {	
    	FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
    	formDataMultiPart.field("action", "start_new_project");
    	formDataMultiPart.field("token", TOKEN);  	
		formDataMultiPart.field("project_name", projectName);

    	
		String json = service.type(MediaType.MULTIPART_FORM_DATA).post(String.class, formDataMultiPart);
		JSONObject jsonObject = JSONObject.fromObject(json);
//		String action = jsonObject.getString("action");
		String status = jsonObject.getString("status");
		if(!status.equalsIgnoreCase("ok")) throw new IllegalArgumentException("Problem occured by creating new project -> "+status);
		String projectId = jsonObject.getString("project_id");
		return projectId;
	}
	
	public boolean uploadPassiveSimulationData(String projectId, File zippedSimArchive) {
    	FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
    	formDataMultiPart.field("action", "upload_building_data");
    	formDataMultiPart.field("token", TOKEN);  	
		formDataMultiPart.field("project_id", projectId);
		FileDataBodyPart fileDataPart = new FileDataBodyPart("upload_building_data", zippedSimArchive, MediaType.APPLICATION_OCTET_STREAM_TYPE);
		FormDataContentDisposition contentDisposition = FormDataContentDisposition
				.name("building_data")
				.fileName(zippedSimArchive.getName()).build();
		fileDataPart.contentDisposition(contentDisposition);
		formDataMultiPart.bodyPart(fileDataPart);
    	
		String json = service.type(MediaType.MULTIPART_FORM_DATA).post(String.class, formDataMultiPart);
		JSONObject jsonObject = JSONObject.fromObject(json);
//		String action = jsonObject.getString("action");
		String status = jsonObject.getString("status");
		if(!status.equalsIgnoreCase("ok")) throw new IllegalArgumentException(status);
//		String retProjectId = jsonObject.getString("project_id");
		return true;		
	}
	
	public boolean uploadSimulationMatrix(String projectId, File zippedSimArchive) {
    	FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
    	formDataMultiPart.field("action", "upload_simulation_matrix");
    	formDataMultiPart.field("token", TOKEN);  	
		formDataMultiPart.field("project_id", projectId);
		FileDataBodyPart fileDataPart = new FileDataBodyPart("upload_simulation_matrix", 
				zippedSimArchive, MediaType.APPLICATION_OCTET_STREAM_TYPE);
		FormDataContentDisposition contentDisposition = FormDataContentDisposition
				.name("simulation_matrix")
				.fileName(zippedSimArchive.getName()).build();
		fileDataPart.contentDisposition(contentDisposition);
		formDataMultiPart.bodyPart(fileDataPart);
    	
		String json = service.type(MediaType.MULTIPART_FORM_DATA).post(String.class, formDataMultiPart);
		JSONObject jsonObject = JSONObject.fromObject(json);
		String status = jsonObject.getString("status");
		if(!status.equalsIgnoreCase("ok")) throw new IllegalArgumentException(status);
		return true;
	}
	
	public Status startPassiveSimulation(String projectId) {
    	FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
    	formDataMultiPart.field("action", "start_passive_simulation");
    	formDataMultiPart.field("token", TOKEN);  	
		formDataMultiPart.field("project_id", projectId);

    	
		String json = service.type(MediaType.MULTIPART_FORM_DATA).post(String.class, formDataMultiPart);
		JSONObject jsonObject = JSONObject.fromObject(json);
//		String action = jsonObject.getString("action");
		String status = jsonObject.getString("status");
//		String retProjectId = jsonObject.getString("project_id");
		return Status.findByAbbreviation(status);		
	}
	
	public Status startSensitivityAnalysis(String projectId) {
    	FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
    	formDataMultiPart.field("action", "start_sensitivity_analysis");
    	formDataMultiPart.field("token", TOKEN);  	
		formDataMultiPart.field("project_id", projectId);    	
		String json = service.type(MediaType.MULTIPART_FORM_DATA).post(String.class, formDataMultiPart);
		JSONObject jsonObject = JSONObject.fromObject(json);
		String status = jsonObject.getString("status");
		return Status.findByAbbreviation(status);		
	}
	
	public Status checkCloudNode(String projectId, Type cloudType) {
    	FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
    	formDataMultiPart.field("action", "get_status");
    	formDataMultiPart.field("token", TOKEN);  	
		formDataMultiPart.field("project_id", projectId);
    	
		String json = service.type(MediaType.MULTIPART_FORM_DATA).post(String.class, formDataMultiPart);
		JSONObject jsonObject = JSONObject.fromObject(json);
//		String status = jsonObject.getString("status");
		String status = null;
		JSONObject descObject = jsonObject.getJSONObject("description");
		status = descObject.getString(cloudType.getName());
		return Status.findByAbbreviation(status);	
	}
	
	public Status prepareSimulationdata(String projectId) {
    	FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
    	formDataMultiPart.field("action", "prepare_simulation_data");
    	formDataMultiPart.field("token", TOKEN);  	
		formDataMultiPart.field("project_id", projectId);
    	
		String json = service.type(MediaType.MULTIPART_FORM_DATA).post(String.class, formDataMultiPart);
		JSONObject jsonObject = JSONObject.fromObject(json);
		String status = jsonObject.getString("status");
		return Status.findByAbbreviation(status);	
	}
	
	public InputStream getPassiveSimulationResults(String projectId) throws IOException {
    	FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
    	formDataMultiPart.field("action", "get_passive_simulation_results");
    	formDataMultiPart.field("token", TOKEN);  	
		formDataMultiPart.field("project_id", projectId);
    	
		InputStream result = service.type(MediaType.MULTIPART_FORM_DATA).post(InputStream.class, formDataMultiPart);
		// if the result is currently unavailable the service returns a JSON message -> we have to check this
//		StringWriter writer = new StringWriter();
//		IOUtils.copy(result, writer);
//		String json = writer.toString();
//		if(json.contains("get_status")) {
//			JSONObject jsonObject = JSONObject.fromObject(json);
//			String status = jsonObject.getString("status");
//			throw new RuntimeException("Simulation currently running: "+status);
//		}
		return result;	
	}
}
