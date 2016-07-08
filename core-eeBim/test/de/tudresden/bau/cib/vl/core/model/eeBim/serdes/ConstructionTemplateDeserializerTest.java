package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.DataResourceService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.FileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-service-context.xml")
public class ConstructionTemplateDeserializerTest {
	
	@Autowired
	DataResourceService service;
	@Autowired
	FileService fileService;
	private static final String OUTPUT_DIR = "target/";
	@Autowired
	ConfigurationService configuration;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeserializeTemplate() throws ParsingException, IOException {
		ConstructionTemplate template = null;
		template = ConstructionTemplateDeserializer.deserialize(
				ConstructionTemplateDeserializerTest.class.getClassLoader().getResource("./test_resources/constructiontype_10.d6p"));

		assertNotNull(template);
		System.out.println(template);
		System.out.println(template.getMaterialLayers());
		System.out.println("----- NEXT TEMPLATE -----");
		template = ConstructionTemplateDeserializer.deserialize(
				ConstructionTemplateDeserializerTest.class.getClassLoader().getResource("./test_resources/constructiontype_159.d6p"));

		assertNotNull(template);
		System.out.println(template);
		System.out.println(template.getMaterialLayers());
		System.out.println("----- NEXT TEMPLATE -----");
		template = ConstructionTemplateDeserializer.deserialize(
				ConstructionTemplateDeserializerTest.class.getClassLoader().getResource("./test_resources/constructiontype_165.d6p"));

		assertNotNull(template);
		System.out.println(template);
		System.out.println(template.getMaterialLayers());
	}

}
