package de.tudresden.bau.cib.vl.core.model.ontology.linkmodel;

import java.io.File;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.parser.OwlParser;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activiti.cfg.xml")
public class LinkModelTest {

	@Autowired
	private ConfigurationService configurationService;
	private static String workingDirectoryPath = "target/data/repository";
	private static final String OWL_FILE = "";
	private OntologyModel ontModel;
	private static OwlParser owlParser;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		owlParser = OwlParser.createParser(workingDirectoryPath+File.separator+"ontology", "N3");
		ontModel = owlParser.parse(OWL_FILE);
		ontModel.setId(UUID.randomUUID().toString());
	}

	@After
	public void tearDown() throws Exception {	
		ontModel = null;
	}
}
