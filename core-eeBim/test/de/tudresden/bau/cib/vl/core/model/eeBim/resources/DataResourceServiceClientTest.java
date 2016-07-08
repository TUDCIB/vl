package de.tudresden.bau.cib.vl.core.model.eeBim.resources;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Document;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.tudresden.bau.cib.vl.core.model.eeBim.exception.ResourceServiceException;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.OmniClass;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.ClassifiableAnnotable;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.Eetemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.ResourceDescriptor;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.StdServiceDescriptor;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.query.ResourceQueries;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.ConstructionTemplateDeserializerTest;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.DataResourceService;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.DataResourceService.CLASS_TYPE;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.FileService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-service-context.xml")
public class DataResourceServiceClientTest {
	
	@Autowired
	DataResourceService service;
	@Autowired
	FileService fileService;
	private static final String OUTPUT_DIR = "target/ises/";
	@Autowired
	ConfigurationService configuration;
	

	@Before
	public void setUp() throws Exception {
		assertNotNull(service);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@BeforeClass
	public static void setupBeforeClass() {
		File file = new File(OUTPUT_DIR);
		if(!file.exists()) file.mkdirs();
	}
	
	@Test
	public void testListResourceCatalogues() throws ResourceServiceException {
		List<ClassifiableAnnotable> rds = service.listResourceCatalogues();
		assertNotNull(rds);
		assertTrue("No resource descriptors",rds.size() > 0);
		System.out.println(rds);
		for(ClassifiableAnnotable rd : rds) {
			System.out.println("---------------");
			System.out.println(rd);
		}
	}
	
	@Test
	public void testGetEntities() throws ResourceServiceException {
		List<ClassifiableAnnotable> rds = service.getAllEntities();
		assertNotNull("No entities",rds);
		assertTrue(rds.size() > 0);
		for(ClassifiableAnnotable rd : rds) {
			System.out.println("---------------");
			System.out.println(rd);
		}
	}
	
	@Test
	public void testGetResource47() throws ResourceServiceException {
		ResourceDescriptor rd = service.getResource("47");
		assertNotNull("no resource",rd);
		long id = rd.getId();
		System.out.println("ID: "+id);
		String name = rd.getResourceName();
		System.out.println(name);
	}

	@Test
	public void testGetTemplate48() throws ResourceServiceException {
		Eetemplate tpl = service.getTemplate("48");
		assertNotNull("no template",tpl);
		long id = tpl.getClassifiableAnnotableid();
		assertTrue(id==48);
		String shortname = tpl.getShortname();
		System.out.println(shortname);
		String desc = tpl.getShortdescription();
		System.out.println(desc);
	}
	
	@Test
	public void testGetTemplatesWhichContainsWall() throws ResourceServiceException {
		List<Eetemplate> tpls = service.getTemplatesWhichContains("wall");
		assertNotNull("no templates",tpls);
		assertTrue(tpls.size() > 0);
		for(Eetemplate tpl : tpls) {
			long id = tpl.getClassifiableAnnotableid();
			System.out.println("ID: "+id);
			String shortname = tpl.getShortname();
			System.out.println(shortname);
			String desc = tpl.getShortdescription();
			System.out.println(desc);
		}
	}
	
	@Test
	public void testGetTemplatesOfTypeWall() throws ResourceServiceException {
		List<Eetemplate> tpls = service.getTemplatesOfType(CLASS_TYPE.WALL);
		assertNotNull("no templates",tpls);
		assertTrue("no templates available", tpls.size() > 0);
		for(Eetemplate tpl : tpls) {
			long id = tpl.getClassifiableAnnotableid();
			System.out.println("ID: "+id);
			String shortname = tpl.getShortname();
			System.out.println(shortname);
			String desc = tpl.getShortdescription();
			System.out.println(desc);
		}
	}
	
	@Test
	public void testGetTemplatesByOmniClass() throws ResourceServiceException {
		List<Eetemplate> tpls = service.getTemplatesByOmniClass(OmniClass.Cementitious_Concretes);
		assertNotNull("no templates",tpls);
		assertTrue(tpls.size() > 0);
		for(Eetemplate tpl : tpls) {
			long id = tpl.getClassifiableAnnotableid();
			System.out.println("ID: "+id);
			String shortname = tpl.getShortname();
			System.out.println(shortname);
			String desc = tpl.getShortdescription();
			System.out.println(desc);
		}
	}
	
	@Test
	public void testGetContentOfResource() throws ResourceServiceException {
		String content = service.getContentOfResource("48");
		assertNotNull(content);
		System.out.println(content);
	}
	
	@Test
	public void testGetAllTemplates() throws Exception {
		List<Eetemplate> templates = service.getAllTemplates();
		assertNotNull("no templates",templates);
		assertTrue(templates.size()>0);
		for(Eetemplate tpl : templates) {
			System.out.println("-----------------------------");
			long id = tpl.getClassifiableAnnotableid();
			System.out.println("ID: "+id);
			String shortname = tpl.getShortname();
			System.out.println(shortname);
			String desc = tpl.getShortdescription();
			System.out.println(desc);
		}
	}
	
	@Test
	public void testGetConstructions() throws Exception {
		final String ibkConstructionCatalogueId = "47"; // without materials
//		final String ibkConstructionCatalogueId = "74"; // with materials
		final String granlundConstructionCatalogue = "43";

		String xml = service.getAllResourcesFromCatalogue(ibkConstructionCatalogueId);
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setNamespaceAware(true);
	    DocumentBuilder builder;
	    Document doc = null;
	    builder = factory.newDocumentBuilder();
	    
	    File tempFile = new File("target/temp_resources.xml");
	    
	    FileService.copyToFile(xml, tempFile, false);
	    doc = builder.parse(tempFile);
//	    doc = builder.parse(this.getClass().getClassLoader().getResource("constructions.xml").openStream());
	    Map<String, String> constructions = ResourceQueries.getAllConstructionNames(doc);
	    assertTrue(constructions.size()>0);
	    System.out.println(constructions);
	}
	
	@Test
	public void testListIBKConstructions() throws Exception {
		final String ibkConstructionCatalogueId = "47"; // without materials
//		final String ibkConstructionCatalogueId = "74"; // with materials
		
//		ClassifiableAnnotable ca = dataResourceServiceClient.getResourceDescriptor(ibkConstructionCatalogueId);
//		assertNotNull("no resource",ca);
//		long id = ca.getClassifiableAnnotableid();
//		System.out.println("ID: "+id);
//		System.out.println(ca);
//		ResourceDescriptor rd = ca.getResourceDescriptor();
//		assertTrue(rd instanceof StdServiceDescriptor);
//		StdServiceDescriptor std = (StdServiceDescriptor) rd;
//		long stdId = std.getId();
//		System.out.println("StdId: "+stdId);
		
		String xml = service.getAllResourcesFromCatalogue(ibkConstructionCatalogueId);
		File outputFile = new File(OUTPUT_DIR+"constructions.xml");
		FileService.copyToFile(xml, outputFile, false);
		assertTrue(outputFile.exists());
	}
	
	@Test
	public void testDownloadClimates() throws Exception {
		final String climateCatalogueId = "69";
		String xml = service.getAllResourcesFromCatalogue(climateCatalogueId);
		File outputFile = new File(OUTPUT_DIR+"climates.xml");
		FileService.copyToFile(xml, outputFile, false);
		assertTrue(outputFile.exists());
		System.out.println("Climates download to: "+outputFile.getAbsolutePath());
	}
	
	@Test
	public void testListClimates() throws Exception {
		List<ClimateLocationTemplate> climates = service.listClimateResources();
		assertNotNull(climates);
		assertTrue(climates.size()>0);
		System.out.println(climates);
	}
	
	@Test
	public void testListMaterials() throws Exception {
		final String ibkMaterialCatalogueId = "45";
		String xml = service.getAllResourcesFromCatalogue(ibkMaterialCatalogueId);
		File outputFile = new File(OUTPUT_DIR+"materials.xml");
		FileService.copyToFile(xml, outputFile, false);
		assertTrue(outputFile.exists());
		System.out.println("Downloaded materials to: "+outputFile);
	}
	
	@Test
	public void testListMaterialsFromService() throws Exception {
		List<MaterialTemplate> materials = service.listMaterialTemplates();
		assertNotNull(materials);
		assertTrue(materials.size()>0);
		for(MaterialTemplate material : materials) {
			System.out.println(material);
		}
	}
	
	@Test
	public void testGetMaterial() throws Exception {
		final String ibkMaterialCatalogueId = "45";
		final String resourceId = "4002"; //"2033";
		String xml = service.getResourceFromCatalogue(resourceId, ibkMaterialCatalogueId);
		File outputFile = new File(OUTPUT_DIR+"material_"+resourceId+".xml");
		FileService.copyToFile(xml, outputFile, false);
		assertTrue(outputFile.exists());
		System.out.println("Downloaded material to: "+outputFile);
	}
	
	@Test
	public void testListSchedules() throws Exception {
		final String granlundSchedulesCatalogueId = "44";
		String xml = service.getAllResourcesFromCatalogue(granlundSchedulesCatalogueId);
		File outputFile = new File(OUTPUT_DIR+"schedules.xml");
		FileService.copyToFile(xml, outputFile, false);
		assertTrue(outputFile.exists());
		System.out.println("Downloaded schedules to: "+outputFile);
	}
	
	@Test
	public void testSearchOfficeProfile() throws Exception {
		String omniClass = "13-55 11 00";
		fail("not implemented");
	}
	
	@Test
	public void testStochastifyMaterial() throws Exception {
		InputStream in = ConstructionTemplateDeserializerTest.class.getClassLoader().getResourceAsStream(
				"./test_resources/material_4002.xml");
		assertNotNull(in);
		
		double meanValue = 1.0;
		double standardDeviation = 0.2;
		int numberOfSamples = 100;
		
		File dirs = new File(OUTPUT_DIR+"stochastic/");
		dirs.mkdirs();
		
		File outputFile = service.stochastifyMaterial(in, meanValue, standardDeviation, numberOfSamples, OUTPUT_DIR+"stochastic/stochasticMaterial_4002.xml");
		assertNotNull(outputFile);
		System.out.println(outputFile);
	}
	
	@Test
	public void testStochastifyConstruction() throws Exception {
		List<ConstructionTemplate> constructions = service.listConstructionResources();
		
		int numberOfResources = constructions.size();
		int random = (int) (Math.random()*numberOfResources);
		
		ConstructionTemplate resource = constructions.get(random);
		System.out.println(resource);
		
		File dirs = new File(OUTPUT_DIR+"stochastic/");
		dirs.mkdirs();
		InputStream stream = service.stochastifyConstruction(resource, -1, -1, -1);
		assertNotNull(stream);
		File outputFile = new File(OUTPUT_DIR+"stochastic/StochasticConstructionResource_"+resource.getId()+".xml");
		FileService.copyToFile(stream, outputFile, false);
		assertTrue(outputFile.exists());
		System.out.println("Stochastic resource exported to "+outputFile);
	}
	
	@Test
	public void testListOccupancies() throws Exception {
		final String occupancyCatalogueId = "73";
		String xml = service.getAllResourcesFromCatalogue(occupancyCatalogueId);
		File outputFile = new File(OUTPUT_DIR+"occupancy.xml");
		FileService.copyToFile(xml, outputFile, false);
		assertTrue(outputFile.exists());
		System.out.println("Downloaded occupancy to: "+outputFile);
	}
	
	@Test
	public void testListTrimoElements() throws Exception {
		final String catalogueId = "68"; 
		String xml = service.getAllResourcesFromCatalogue(catalogueId);
		File outputFile = new File(OUTPUT_DIR+"trimo.xml");
		FileService.copyToFile(xml, outputFile, false);
		assertTrue(outputFile.exists());
		System.out.println("Downloaded TRIMO elements to: "+outputFile);
	}
	
	@Test
	public void testListConstructions() throws Exception {
		List<ConstructionTemplate> constructions = service.listConstructionResources();
		assertNotNull(constructions);
		assertTrue(constructions.size() > 0);
		for(ConstructionTemplate construction : constructions) {
			System.out.println(construction);
		}
	}
	
	@Test
	public void testListMaterialsOfLocalConstruction() throws Exception {
		String testPath = "test/test_resources/construction_4278.xml";
		File testFile = new File(testPath);
		String uri = URI.create(testPath).toString();
		assertTrue(testFile.exists());
		FileInputStream stream = new FileInputStream(testFile);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		builder = factory.newDocumentBuilder();

		doc = builder.parse(stream);
		List<MaterialTemplate> materials = ResourceQueries.getAllMaterials(doc, uri, "45");
		assertTrue(materials.size() > 0);
		System.out.println(materials);
	}
	
	@Test
	public void testListMaterialsOfRemoteConstruction() throws Exception {
		ConstructionTemplate construction = new ConstructionTemplate();
		construction.setId("4344");
		construction.setSourceFileUri("http://130.208.198.50:8080/dfservice/dm/open/74/GETID/4344");
		List<MaterialTemplate> materials = service.listMaterialsOfConstruction(construction);
		assertTrue(materials.size() > 0);
		System.out.println(materials);
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		factory.setNamespaceAware(true);
//		DocumentBuilder builder;
//		Document doc = null;
//		builder = factory.newDocumentBuilder();
//		
//		String xml = dataResourceServiceClient.getResourceFromCatalogue("4278", "74");
//	    InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8")); 
//	    doc = builder.parse(stream);
//
//		List<MaterialTemplate> materials = ResourceQueries.getAllMaterials(
//				doc, "http://130.208.198.50:8080/dfservice/dm/open/45/GETID/");
//		assertTrue(materials.size() > 0);
//		System.out.println(materials);
	}
	
	@Test
	public void createResourceDescriptor() {
		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setAuthenticationType("auth");
		rd.setCreated(new Timestamp(453453453l));
		rd.setDomain("myDomain");
		rd.setId(1l);
		rd.setLastModified(new Timestamp(5343434343l));
		rd.setProperties(new HashMap<String, String>());
		rd.setResourceClassName("myResourceClass");
		rd.setResourceDescription("myResourceDesc");
		rd.setResourceName("myResourceName");
		rd.setResourceShortName("myResShortName");
		rd.setUserName("myUser");
		rd.setUserPassword("myPwd");
		Gson gson = new Gson();
		String json = gson.toJson(rd);
		System.out.println(json);
		Type rdtype = new TypeToken<ResourceDescriptor>(){}.getType();
		Object o = gson.fromJson(json, rdtype);
		assertNotNull(o);
		assertTrue(o instanceof ResourceDescriptor);
		ResourceDescriptor convertedRd = (ResourceDescriptor) o;
		System.out.println(convertedRd);
		assertTrue(rd.getResourceName().equals(convertedRd.getResourceName()));
	}
	
	@Test
	public void createResourceDescriptorList() {
		List<ResourceDescriptor> rds = new ArrayList<ResourceDescriptor>();
		for(int i = 1; i <= 5; i++) {
			ResourceDescriptor rd = new ResourceDescriptor();
			rd.setAuthenticationType("auth"+i);
			rd.setCreated(new Timestamp(453453453l*i));
			rd.setDomain("myDomain"+i);
			rd.setId(i);
			rd.setLastModified(new Timestamp(5343434343l*i));
			rd.setProperties(new HashMap<String, String>());
			rd.setResourceClassName("myResourceClass"+i);
			rd.setResourceDescription("myResourceDesc"+i);
			rd.setResourceName("myResourceName"+i);
			rd.setResourceShortName("myResShortName"+i);
			rd.setUserName("myUser"+i);
			rd.setUserPassword("myPwd"+i);
			rds.add(rd);
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(rds);
		System.out.println(json);
		
		Type rdtype = new TypeToken<List<ResourceDescriptor>>(){}.getType();
		Object o = gson.fromJson(json, rdtype);
		assertNotNull(o);
		assertTrue(o instanceof List);
		List convertedRds = (List) o;
		System.out.println(convertedRds);
		for(Object oo : convertedRds) {
			assertTrue(oo instanceof ResourceDescriptor);
			System.out.println((ResourceDescriptor)oo);
		}	
	}
	
	@Test
	public void createStdServiceDescriptor() {
		StdServiceDescriptor std = new StdServiceDescriptor();
		std.setAuthenticationType("auth");
		std.setCreated(new Timestamp(453453453l));
		std.setDomain("myDomain");
		std.setId(1l);
		std.setLastModified(new Timestamp(5343434343l));
		std.setProperties(new HashMap<String, String>());
		std.setResourceClassName("myResourceClass");
		std.setResourceDescription("myResourceDesc");
		std.setResourceName("myResourceName");
		std.setResourceShortName("myResShortName");
		std.setUserName("myUser");
		std.setUserPassword("myPwd");
		std.setConnectionDetails("http://wsdl");
		std.setOperationName("getAll");
		std.setOperationParmeters("all");
		std.setPortName("8080");
		std.setServiceEndpointInterface("http://ises");
		std.setServiceName("ISES");
		std.setServiceType("soap");
		std.setTargetNamespace("http://cib.bau.tu-dresden.de");
		std.setWsdlLocation("http://127.0.0.1/wsdl");
		Gson gson = new Gson();
		String json = gson.toJson(std);
		System.out.println(json);
		
		
		Type rdtype = new TypeToken<ResourceDescriptor>(){}.getType();
		Object o = gson.fromJson(json, rdtype);
		assertNotNull(o);
		assertTrue(o instanceof ResourceDescriptor);
		ResourceDescriptor convertedRd = (ResourceDescriptor) o;
		System.out.println(convertedRd);
		assertTrue(std.getResourceName().equals(convertedRd.getResourceName()));
	}
	
	@Test
	public void createStdServiceDescriptorList() {
		List<StdServiceDescriptor> stds = new ArrayList<StdServiceDescriptor>();
		for(int i = 1; i <= 5; i++) {
			StdServiceDescriptor std = new StdServiceDescriptor();
			std.setAuthenticationType("auth"+i);
			std.setCreated(new Timestamp(453453453l*i));
			std.setDomain("myDomain"+i);
			std.setId(i);
			std.setLastModified(new Timestamp(5343434343l*i));
			std.setProperties(new HashMap<String, String>());
			std.setResourceClassName("myResourceClass"+i);
			std.setResourceDescription("myResourceDesc"+i);
			std.setResourceName("myResourceName"+i);
			std.setResourceShortName("myResShortName"+i);
			std.setUserName("myUser"+i);
			std.setUserPassword("myPwd"+i);
			std.setConnectionDetails("http://wsdl"+i);
			std.setOperationName("getAll"+i);
			std.setOperationParmeters("all"+i);
			std.setPortName("808"+i);
			std.setServiceEndpointInterface("http://ises/"+i);
			std.setServiceName("ISES"+i);
			std.setServiceType("soap"+i);
			std.setTargetNamespace("http://cib.bau.tu-dresden.de/"+i);
			std.setWsdlLocation("http://127.0.0.1/wsdl"+i);
			stds.add(std);
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(stds);
		System.out.println(json);
		
		Type rdtype = new TypeToken<List<StdServiceDescriptor>>(){}.getType();
		Object o = gson.fromJson(json, rdtype);
		assertNotNull(o);
		assertTrue(o instanceof List);
		List convertedRds = (List) o;
		System.out.println(convertedRds);
		for(Object oo : convertedRds) {
			assertTrue(oo instanceof StdServiceDescriptor);
			System.out.println((StdServiceDescriptor)oo);
		}	
	}

}
