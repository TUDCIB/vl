package de.tudresden.bau.cib.vl.core.service;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.vl.core.service.impl.FileServiceImpl;

public class FileServiceTest {
	
	FileServiceImpl service;
	String testDir = "test/resources/";
	String testFile = testDir+"ISES_2ndLSB.ifc";
	String outputDir = "target/output/";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		service = new FileServiceImpl();
		File dir = new File(outputDir);
		assertTrue(dir.mkdirs());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testZip() {
		fail("Not yet implemented");
	}

	@Test
	public void testZipDir() throws Exception {
		FileService.zipDir(new File(testDir), outputDir+"bla.zip");
	}

}
