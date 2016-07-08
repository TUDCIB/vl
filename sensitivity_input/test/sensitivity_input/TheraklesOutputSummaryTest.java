package sensitivity_input;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TheraklesOutputSummaryTest {
	
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
	public void testReadTheraklesOutputSummary() throws Exception {
		File file = new File("test/resources/0O8xNO1vn6iBNxcFc0MpGysummary.txt");
		TheraklesOutputSummary tos = new TheraklesOutputSummary(file.toURI().toURL());
		System.out.println(tos.createSensitivityInputString());
	}
}
