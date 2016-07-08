/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.eeBim.HeatingAndCoolingInterval;
import de.tudresden.bau.cib.vl.core.model.energy.Schedule;
import de.tudresden.bau.cib.vl.core.model.energy.Schedule.SCHEDULE_CYCLE;
import de.tudresden.bau.cib.vl.core.model.energy.Schedule.TYPES;
import de.tudresden.bau.cib.vl.core.model.time.DayHourTimePeriod;
import de.tudresden.bau.cib.vl.core.model.time.HolidayTimePeriod;


/**
 * The Class ScheduleTemplatesDeserializer.
 */
public final class ScheduleTemplatesDeserializer {

	
	/**
	 * The Enum XML_TAGS.
	 */
	public static enum XML_TAGS {
		
		/** The Schedules. */
		Schedules,
		
		/** The Default. */
		Default, 
 /** The Week days. */
 WeekDays, 
 /** The Week end days. */
 WeekEndDays, 
 /** The Holidays. */
 Holidays, 
		
		/** The Schedule group. */
		ScheduleGroup, 
 /** The Schedule. */
 Schedule, 
 /** The Daily cycle. */
 DailyCycle, 
 /** The Interval. */
 Interval,
		
		/** The Week end. */
		WeekEnd
	};
	
	/**
	 * The Enum XML_TAG_ATTRIBUTES.
	 */
	public static enum XML_TAG_ATTRIBUTES {
		
		/** The id. */
		id, 
 /** The name. */
 name, 
 /** The space type. */
 spaceType, 
 /** The type. */
 type, 
 /** The unit. */
 unit
	};
	
	/**
	 * The Enum PARAMETERS.
	 */
	public static enum PARAMETERS {
		
		/** The Heating set point temperature. */
		HeatingSetPointTemperature,
		
		/** The Cooling set point temperature. */
		CoolingSetPointTemperature
	}
	
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleTemplatesDeserializer.class);

	
	/**
	 * Instantiates a new schedule templates deserializer.
	 */
	private ScheduleTemplatesDeserializer() {
		throw new AssertionError();
	}
	
	/**
	 * Deserialize.
	 *
	 * @param filePath the file path
	 * @return the map
	 * @throws ParsingException the parsing exception
	 * @throws FileNotFoundException the file not found exception
	 */
	public static Map<String, List<Schedule>> deserialize(String filePath) throws ParsingException, FileNotFoundException {
		LOG.trace("Reading schedule templates: {}", filePath);
		File file = new File(filePath);
		if(!file.exists()) {
			throw new FileNotFoundException("File not found");
		}
		
		Map<String, List<Schedule>> scheduleMap = new HashMap<String, List<Schedule>>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.normalizeDocument();
			
			HolidayTimePeriod holidays = null;
			DayHourTimePeriod weekdays = null;
			DayHourTimePeriod weekEndDays = null;
			
	//		(1) parse the Schedule Templates
	
			NodeList xmlSchedulesList = doc.getElementsByTagName(XML_TAGS.Schedules.toString());
			for(int i = 0; i < xmlSchedulesList.getLength(); i++) {		
	//          <Schedules>			
				Element schedulesNode = (Element)xmlSchedulesList.item(i);
				NodeList xmlDefaultList = schedulesNode.getElementsByTagName(XML_TAGS.Default.toString());
				for(int j = 0; j < xmlDefaultList.getLength(); j++) {
	//		    	<Default>			
					Element defaultNode = (Element)xmlDefaultList.item(j);
					NodeList xmlWeekDaysList = defaultNode.getElementsByTagName(XML_TAGS.WeekDays.toString());
					for(int k = 0; k < xmlWeekDaysList.getLength(); k++) {	
						Element xmlWeekDays = (Element) xmlWeekDaysList.item(k);
						String content = xmlWeekDays.getTextContent();
						String[] weekdaysArr = content.split(",");
						weekdays = new DayHourTimePeriod();
						for(String weekday : weekdaysArr) {
							if(weekday.equalsIgnoreCase("Mon")) weekdays.addAcceptedDay(1);
							if(weekday.equalsIgnoreCase("Tue")) weekdays.addAcceptedDay(2);
							if(weekday.equalsIgnoreCase("Wed")) weekdays.addAcceptedDay(3);
							if(weekday.equalsIgnoreCase("Thr")) weekdays.addAcceptedDay(4);
							if(weekday.equalsIgnoreCase("Fri")) weekdays.addAcceptedDay(5);
							if(weekday.equalsIgnoreCase("Sat")) weekdays.addAcceptedDay(6);
							if(weekday.equalsIgnoreCase("Sun")) weekdays.addAcceptedDay(7);
						}
					}
					NodeList xmlWeekEndDaysList = defaultNode.getElementsByTagName(XML_TAGS.WeekEndDays.toString());
					for(int k = 0; k < xmlWeekEndDaysList.getLength(); k++) {	
						Element xmlWeekEndDays = (Element) xmlWeekEndDaysList.item(k);
						String content = xmlWeekEndDays.getTextContent();
						String[] weekEndDaysArr = content.split(",");
						weekEndDays = new DayHourTimePeriod();
						for(String weekEndDay : weekEndDaysArr) {
							if(weekEndDay.equalsIgnoreCase("Mon")) weekEndDays.addAcceptedDay(1);
							if(weekEndDay.equalsIgnoreCase("Tue")) weekEndDays.addAcceptedDay(2);
							if(weekEndDay.equalsIgnoreCase("Wed")) weekEndDays.addAcceptedDay(3);
							if(weekEndDay.equalsIgnoreCase("Thr")) weekEndDays.addAcceptedDay(4);
							if(weekEndDay.equalsIgnoreCase("Fri")) weekEndDays.addAcceptedDay(5);
							if(weekEndDay.equalsIgnoreCase("Sat")) weekEndDays.addAcceptedDay(6);
							if(weekEndDay.equalsIgnoreCase("Sun")) weekEndDays.addAcceptedDay(7);
						}
					}
					NodeList xmlHolidaysList = defaultNode.getElementsByTagName(XML_TAGS.Holidays.toString());
			        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.")
			                .withLocale(Locale.GERMANY);
					for(int k = 0; k < xmlHolidaysList.getLength(); k++) {	
						Element xmlHolidays = (Element) xmlHolidaysList.item(k);
						String content = xmlHolidays.getTextContent();
						String[] holidaysArr = content.split(",");
						holidays = new HolidayTimePeriod();
						for(String holiday : holidaysArr) {
							holiday = holiday.trim();
					        DateTime dt = formatter.parseDateTime(holiday);
							holidays.addHoliday(dt);
						}
					}
					}
				
				NodeList xmlScheduleGroupList = schedulesNode.getElementsByTagName(XML_TAGS.ScheduleGroup.toString());
				for(int j = 0; j < xmlScheduleGroupList.getLength(); j++) {
	//	          <ScheduleGroup>			
					Element scheduleGroupNode = (Element)xmlScheduleGroupList.item(j);
					String spaceType = scheduleGroupNode.getAttribute(XML_TAG_ATTRIBUTES.spaceType.toString());
					handleScheduleGroup(spaceType, scheduleGroupNode, scheduleMap, holidays, weekdays, weekEndDays);
				}	
			}
//			LOG.trace("Following schedule templates are available: {}", scheduleMap);
			return scheduleMap;
		} catch (Exception e) {
			throw new ParsingException(e);
		}
	}
	
	/**
	 * Handle schedule group.
	 *
	 * @param spaceType the space type
	 * @param xmlScheduleGroup the xml schedule group
	 * @param scheduleMap the schedule map
	 * @param holidays the holidays
	 * @param weekdays the weekdays
	 * @param weekEndDays the week end days
	 */
	private static void handleScheduleGroup(String spaceType, Element xmlScheduleGroup, Map<String, List<Schedule>> scheduleMap, HolidayTimePeriod holidays, DayHourTimePeriod weekdays, DayHourTimePeriod weekEndDays) {
		List<Schedule> schedules = new ArrayList<Schedule>();
//		<ScheduleGroup>
		if(xmlScheduleGroup != null) {
			NodeList xmlScheduleList = xmlScheduleGroup.getElementsByTagName(XML_TAGS.Schedule.toString());
			for(int i = 0; i < xmlScheduleList.getLength(); i++) {	
//				<Schedule>
				Element xmlSchedule = (Element) xmlScheduleList.item(i);
				String type = xmlSchedule.getAttribute(XML_TAG_ATTRIBUTES.type.toString());
				TYPES types = TYPES.valueOf(type);
				
				switch(types) {
				case AllDays:
					NodeList xmlDailyCycleList = xmlSchedule.getElementsByTagName(XML_TAGS.DailyCycle.toString());
					if(xmlDailyCycleList != null) {
						Schedule schedule = new Schedule(types);	
						for(int j = 0; j < xmlDailyCycleList.getLength(); j++) {
//							<DailyCycle>
							Element xmlDailyCycle = (Element) xmlDailyCycleList.item(j);
							List<DayHourTimePeriod> intervals = handleDailyCycleIntervals(xmlDailyCycle, holidays, weekdays, weekEndDays);
							schedule.addIntervals(SCHEDULE_CYCLE.DailyCycle, intervals);
//							LOG.trace("Added intervals: {} to schedule: {}", 
//									new Object[]{intervals, schedule});
						}
						schedules.add(schedule);
					}
					break;
				case WeekEnd:
					NodeList xmlWeekEndCycleList = xmlSchedule.getElementsByTagName(XML_TAGS.DailyCycle.toString());
					if(xmlWeekEndCycleList != null) {
						Schedule weekEndSchedule = new Schedule(types);				
						for(int j = 0; j < xmlWeekEndCycleList.getLength(); j++) {
//							<DailyCycle>
							Element xmlWeekEndCycle = (Element) xmlWeekEndCycleList.item(j);
							List<DayHourTimePeriod> intervals = handleDailyCycleIntervals(xmlWeekEndCycle, holidays, weekdays, weekEndDays);
							for(DayHourTimePeriod dtp : intervals) dtp.setAcceptedDays("6-7");
							weekEndSchedule.addIntervals(SCHEDULE_CYCLE.DailyCycle, intervals);
//							LOG.trace("Added intervals: {} to schedule: {}", 
//									new Object[]{intervals, weekEndSchedule});
						}
						schedules.add(weekEndSchedule);
					}
					break;
				case Holiday:
					NodeList xmlHolidayCycleList = xmlSchedule.getElementsByTagName(XML_TAGS.DailyCycle.toString());
					if(xmlHolidayCycleList != null) {
						Schedule holidaySchedule = new Schedule(types);				
						for(int j = 0; j < xmlHolidayCycleList.getLength(); j++) {
//							<DailyCycle>
							Element xmlHolidayCycle = (Element) xmlHolidayCycleList.item(j);
							List<DayHourTimePeriod> intervals = handleDailyCycleIntervals(xmlHolidayCycle, holidays, weekdays, weekEndDays);
							holidaySchedule.addIntervals(SCHEDULE_CYCLE.DailyCycle, intervals);
//							LOG.trace("Added intervals: {} to schedule: {}", 
//									new Object[]{intervals, holidaySchedule});
						}
						schedules.add(holidaySchedule);
					}
					break;
				default: throw new RuntimeException("Schedule type unknown ('"+types+"')");
				}
			}
		}
		
		scheduleMap.put(spaceType, schedules);
	}
	
	/**
	 * Handle daily cycle intervals.
	 *
	 * @param xmlNode the xml node
	 * @param holidays the holidays
	 * @param weekdays the weekdays
	 * @param weekEndDays the week end days
	 * @return the list
	 */
	private static List<DayHourTimePeriod> handleDailyCycleIntervals(
			Element xmlNode, HolidayTimePeriod holidays, DayHourTimePeriod weekdays, DayHourTimePeriod weekEndDays) {		
		List<DayHourTimePeriod> intervals = new ArrayList<DayHourTimePeriod>();
//		<DailyCycle>
		NodeList xmlIntervalList = xmlNode.getElementsByTagName(XML_TAGS.Interval.toString());
//		default interval from 0-24
		String start = "0";
		String end = "23";
//		String timeUnit = "h";
//		checks if there is the IBK:Parameter 'End' which means that the interval have a given end time, 
//		if it has no end parameter we take 24h as end time
		for(int k = 0; k < xmlIntervalList.getLength(); k++) {
			boolean containsEndParameter = false;
			HeatingAndCoolingInterval interval = new HeatingAndCoolingInterval();
//			<Interval>
			Element xmlInterval = (Element) xmlIntervalList.item(k);
			NodeList xmlIbkParameterList = xmlInterval.getElementsByTagName("IBK:Parameter");
			
			for(int l = 0; l < xmlIbkParameterList.getLength(); l++) {
//				<IBK:Parameter>
				Element xmlIbkParameter = (Element) xmlIbkParameterList.item(l);
				String name = xmlIbkParameter.getAttribute(XML_TAG_ATTRIBUTES.name.toString()).trim();
				String unit = xmlIbkParameter.getAttribute(XML_TAG_ATTRIBUTES.unit.toString()).trim();
				String value = xmlIbkParameter.getTextContent().trim();
//				<IBK:Parameter name="End"  >
				if(name.equalsIgnoreCase("End")) {
//					timeUnit = unit;
					end = value;	
					interval.addAcceptedHours(start+"-"+end);
					start = end;
//					this interval have an end
					containsEndParameter = true;
				} 
//				<IBK:Parameter name="HeatingSetPointTemperature" >
				else if(name.equalsIgnoreCase(PARAMETERS.HeatingSetPointTemperature.toString())) {
					interval.setHeatingSetPoint(Float.valueOf(value));
					interval.setHeatingUnit(unit);
				} 
//				<IBK:Parameter name="CoolingSetPointTemperature" >
				else if(name.equalsIgnoreCase(PARAMETERS.CoolingSetPointTemperature.toString())) {
					interval.setCoolingSetPoint(Float.valueOf(value));
					interval.setCoolingUnit(unit);
				}
			}
//			no end parameter set -> the interval goes until 24h
			if(!containsEndParameter) { // for example on week end
				if(!start.equals("0")){
					interval.addAcceptedHours(start+"-23");
				} else {
					interval.addAcceptedHours("0-23");
				}
			}
			interval.addAcceptedDays(weekdays.getDays());
			intervals.add(interval);
		}
		
		return intervals;
	}
}
