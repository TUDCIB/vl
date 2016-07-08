package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.vl.core.model.eeBim.HeatingAndCoolingInterval;
import de.tudresden.bau.cib.vl.core.model.energy.Schedule;
import de.tudresden.bau.cib.vl.core.model.energy.Schedule.SCHEDULE_CYCLE;
import de.tudresden.bau.cib.vl.core.model.energy.Schedule.TYPES;

public class ScheduleTemplatesSerializerTest {

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
	public void testSerialize() throws ParserConfigurationException, TransformerException {
		String destPath = "D:\\schedules.xml";
		Map<String, List<Schedule>> scheduleMap = new HashMap<String, List<Schedule>>();
		
		String spaceType1 = "Gehege";
		List<Schedule> list1 = new ArrayList<Schedule>();
		Schedule allDays1 = new Schedule(TYPES.AllDays);
		HeatingAndCoolingInterval hci11 = new HeatingAndCoolingInterval();
		hci11.addAcceptedDays("1-7");
		hci11.addAcceptedHours("0-8");
		hci11.setHeatingSetPoint(18.0f);
		allDays1.addInterval(SCHEDULE_CYCLE.DailyCycle, hci11);
		HeatingAndCoolingInterval hci12 = new HeatingAndCoolingInterval();
		hci12.addAcceptedDays("1-7");
		hci12.addAcceptedHours("8-22");
		hci12.setHeatingSetPoint(22.0f);
		allDays1.addInterval(SCHEDULE_CYCLE.DailyCycle, hci12);
		HeatingAndCoolingInterval hci13 = new HeatingAndCoolingInterval();
		hci13.addAcceptedDays("1-7");
		hci13.addAcceptedHours("22-24");
		hci13.setHeatingSetPoint(18.0f);
		allDays1.addInterval(SCHEDULE_CYCLE.DailyCycle, hci13);
		
		list1.add(allDays1);
		
		String spaceType2 = "Testbox";
		List<Schedule> list2 = new ArrayList<Schedule>();
		Schedule allDays2 = new Schedule(TYPES.AllDays);
		HeatingAndCoolingInterval hci21 = new HeatingAndCoolingInterval();
		hci21.addAcceptedDays("1-5");
		hci21.addAcceptedHours("0-6");
		hci21.setCoolingSetPoint(28.0f);
		hci21.setHeatingSetPoint(18.0f);
		allDays2.addInterval(SCHEDULE_CYCLE.DailyCycle, hci21);
		HeatingAndCoolingInterval hci22 = new HeatingAndCoolingInterval();
		hci22.addAcceptedDays("1-5");
		hci22.addAcceptedHours("6-12");
		hci22.setCoolingSetPoint(26.0f);
		hci22.setHeatingSetPoint(22.0f);
		allDays2.addInterval(SCHEDULE_CYCLE.DailyCycle, hci22);
		HeatingAndCoolingInterval hci23 = new HeatingAndCoolingInterval();
		hci23.addAcceptedDays("1-5");
		hci23.addAcceptedHours("12-18");
		hci23.setCoolingSetPoint(27.0f);
		hci23.setHeatingSetPoint(20.0f);
		allDays2.addInterval(SCHEDULE_CYCLE.DailyCycle, hci23);
		HeatingAndCoolingInterval hci24 = new HeatingAndCoolingInterval();
		hci24.addAcceptedDays("1-5");
		hci24.addAcceptedHours("18-24");
		hci24.setCoolingSetPoint(30.0f);
		hci24.setHeatingSetPoint(16.0f);
		allDays2.addInterval(SCHEDULE_CYCLE.DailyCycle, hci24);
		Schedule weekEnd2 = new Schedule(TYPES.WeekEnd);
		HeatingAndCoolingInterval hci25 = new HeatingAndCoolingInterval();
		hci25.addAcceptedDays("6-7");
		hci25.addAcceptedHours("0-12");
		hci25.setCoolingSetPoint(28.0f);
		hci25.setHeatingSetPoint(18.0f);
		weekEnd2.addInterval(SCHEDULE_CYCLE.DailyCycle, hci25);
		HeatingAndCoolingInterval hci26 = new HeatingAndCoolingInterval();
		hci26.addAcceptedDays("6-7");
		hci26.addAcceptedHours("12-24");
		hci26.setCoolingSetPoint(26.0f);
		hci26.setHeatingSetPoint(22.0f);
		weekEnd2.addInterval(SCHEDULE_CYCLE.DailyCycle, hci26);
		list2.add(allDays2);
		list2.add(weekEnd2);
		
		scheduleMap.put(spaceType1, list1);
		scheduleMap.put(spaceType2, list2);
		
		ScheduleTemplatesSerializer.serialize(scheduleMap, destPath);
		assertTrue(new File(destPath).exists());
	}

}
