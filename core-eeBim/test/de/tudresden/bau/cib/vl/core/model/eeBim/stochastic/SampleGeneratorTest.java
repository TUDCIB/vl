package de.tudresden.bau.cib.vl.core.model.eeBim.stochastic;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.tudresden.bau.cib.vl.core.model.eeBim.service.DataResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-service-context.xml")  
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class SampleGeneratorTest {
	
	
	@Autowired
	DataResourceService service;
	

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
	public void testGenerateSamples() {
		int numberOfSamples = 100;
		double meanValue = 1.2;
		double standardDeviation = 0.3;
		double[] samples = service.generateSamples(meanValue, standardDeviation, numberOfSamples);
		assertTrue(samples.length > 0);
		for(double sample : samples) System.out.println(sample);
		System.out.println("----------------------------------");
	}

}
