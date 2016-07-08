package de.tudresden.bau.cib.vl.core.model.ontology;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test to remove language attribute from IFC-OWL file created through the mapper of Jakob Beetz.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class RemoveLanguageAttributeFromIfcTest {
	
	private FileReader reader;
	private File ifcOwlFile;
	private static final String IFC_OWL_PATH = "D:\\ises\\IFC2X3_TC1.owl";
	private static final String SEARCH_STRING = "<rdfs:label xml:lang=\"ifc\">"; 
	private static final String REPLACE_STRING = "<rdfs:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">";
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		ifcOwlFile = new File(IFC_OWL_PATH);
		assertTrue(ifcOwlFile.exists());
		reader = new FileReader(ifcOwlFile);
		assertNotNull(reader);
	}

	@After
	public void tearDown() throws Exception {
		reader.close();
	}

	@Test
	public void testRemoveLanguageAttribute() throws Exception {
		int status = 0;
		BufferedReader br = new BufferedReader(reader);
		String content = null;
		String line = "";
		while((line = br.readLine()) != null) {
			line = line.replace(SEARCH_STRING, REPLACE_STRING);
			content += line.trim();
			status ++;
			if(status % 10 == 0) System.out.println("Worked "+status+" lines");
		}
		
		FileWriter fileWriter = null;

		fileWriter = new FileWriter(ifcOwlFile, false);
		fileWriter.write(content);
		fileWriter.close();
		System.out.println("Changed file written to: "+ifcOwlFile);
	}
}
