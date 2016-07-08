package de.tudresden.bau.cib.vl.core.simulation.energy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AllInOneSimTest {

	private static final String PATH_TO_IFC_FILE = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\2ndLevel\\PforzheimerSchule_2ndLSB.ifc";
	private static final String PATH_TO_NANDRAD = "D:\\Nutzer\\ken\\data\\VirtualLaboratory\\tools\\NANDRAD\\NandradSolver.exe";
	private static final String PATH_TO_DB_CONSTRUCTIONS = "D:/Nutzer/ken/data/VirtualLaboratory/resources/DB_Constructions/";
	private static final String PATH_TO_SIM_MODEL = "D:\\Nutzer\\ken\\data\\Nandrad_Test\\SIM_EXPORT.nandrad";
	private static final String PATH_TO_TEMPLATES_AND_MAPPINGS = "D:/Nutzer/ken/data/VirtualLaboratory/resources/DB_Templates/";
	private static final String PATH_TO_BIM2SIM = "D:\\Nutzer\\ken\\data\\VirtualLaboratory\\tools\\BIM2SIM\\Ifc2Nandrad.jar";
	
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
	public void testStartExecution() throws Exception {
//		START BIM2SIM tool
		
		String command1 = "cmd /c start /WAIT java -jar "+PATH_TO_BIM2SIM+" "+PATH_TO_IFC_FILE +" "+PATH_TO_SIM_MODEL+ " "+PATH_TO_DB_CONSTRUCTIONS +" "+PATH_TO_TEMPLATES_AND_MAPPINGS;
		System.out.println("The used Bim2Sim command is '"+command1+"'");

			Process process1 = Runtime.getRuntime().exec(command1);
			
			int val1 = process1.waitFor();
			System.out.println("...Bim2Sim process finished (result code: '"+val1+"').");
			if(val1 == 0) {
				File resultFile = new File(PATH_TO_SIM_MODEL);
				assertNotNull(resultFile);
				assertTrue(resultFile.canWrite());
				
//				START NANDRAD
				String command2 = "cmd /c start /WAIT "+PATH_TO_NANDRAD+" "+PATH_TO_SIM_MODEL+" --les-solver=GMRES(1000) --precond=BAND(1)";

					
					System.out.println("The used Nandrad command is '"+command2+"'");
					Process process2 = Runtime.getRuntime().exec(command2);
					
					int val2 = process2.waitFor();
					System.out.println("...Nandrad finished (result code: '"+val2+"').");
					
					
			}

		


	}

}
