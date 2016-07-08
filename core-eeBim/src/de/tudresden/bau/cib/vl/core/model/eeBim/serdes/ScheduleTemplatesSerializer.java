/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.tudresden.bau.cib.vl.core.model.eeBim.HeatingAndCoolingInterval;
import de.tudresden.bau.cib.vl.core.model.energy.Schedule;
import de.tudresden.bau.cib.vl.core.model.energy.Schedule.SCHEDULE_CYCLE;
import de.tudresden.bau.cib.vl.core.model.energy.Schedule.TYPES;
import de.tudresden.bau.cib.vl.core.model.time.DayHourTimePeriod;

/**
 * The Class ScheduleTemplatesSerializer.
 */
public final class ScheduleTemplatesSerializer {

	
	/**
	 * Instantiates a new schedule templates serializer.
	 */
	private ScheduleTemplatesSerializer() {
		throw new AssertionError();
	}
	
	/**
	 * Serialize.
	 *
	 * @param scheduleMap the schedule map
	 * @param destPath the dest path
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws TransformerException the transformer exception
	 */
	public static void serialize(Map<String, List<Schedule>> scheduleMap, String destPath) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		
		
		// <Schedules>
		Element rootElement = doc.createElement(ScheduleTemplatesDeserializer.XML_TAGS.Schedules.toString());
		rootElement.setAttribute(ScheduleTemplatesDeserializer.XML_TAG_ATTRIBUTES.id.toString(), "1");
		rootElement.setAttribute(ScheduleTemplatesDeserializer.XML_TAG_ATTRIBUTES.name.toString(), "BAM-AKS");
		doc.appendChild(rootElement);
		
		// <Default>
		Element defaultElement = doc.createElement(ScheduleTemplatesDeserializer.XML_TAGS.Default.toString());
		rootElement.appendChild(defaultElement);
		
			// <WeekDays>
			Element weekDaysElement = doc.createElement(ScheduleTemplatesDeserializer.XML_TAGS.WeekDays.toString());
//			FIXME hard coded
			weekDaysElement.setTextContent("Mon,Tue,Wed,Thr,Fri");
			defaultElement.appendChild(weekDaysElement);
			
			// <WeekEndDays>
			Element weekEndDaysElement = doc.createElement(ScheduleTemplatesDeserializer.XML_TAGS.WeekEndDays.toString());
//			FIXME hard coded
			weekEndDaysElement.setTextContent("Sat,Sun");
			defaultElement.appendChild(weekEndDaysElement);
			
			// <Holidays>
			Element holidaysElement = doc.createElement(ScheduleTemplatesDeserializer.XML_TAGS.Holidays.toString());
//			FIXME hard coded
			holidaysElement.setTextContent("1.1., 1.5., 3.10., 25.12., 26.12.");
			defaultElement.appendChild(holidaysElement);
			
			
		for(Map.Entry<String, List<Schedule>> entry : scheduleMap.entrySet()) {
			String spaceType = entry.getKey();
			List<Schedule> schedules = entry.getValue();

			// <ScheduleGroup>
			Element scheduleGroupElement = doc.createElement(ScheduleTemplatesDeserializer.XML_TAGS.ScheduleGroup.toString());
			scheduleGroupElement.setAttribute(ScheduleTemplatesDeserializer.XML_TAG_ATTRIBUTES.spaceType.toString(), spaceType);
			rootElement.appendChild(scheduleGroupElement);
			
			for(Schedule schedule : schedules) {
				TYPES type = schedule.getType();
				
				// <Schedule>
				Element scheduleElement = doc.createElement(ScheduleTemplatesDeserializer.XML_TAGS.Schedule.toString());
				scheduleElement.setAttribute(ScheduleTemplatesDeserializer.XML_TAG_ATTRIBUTES.type.toString(), type.name());
				scheduleGroupElement.appendChild(scheduleElement);
				
				Map<SCHEDULE_CYCLE, List<DayHourTimePeriod>> intervals = schedule.getIntervals();
				for(Map.Entry<SCHEDULE_CYCLE, List<DayHourTimePeriod>> intValEntry : intervals.entrySet()) {					
//					SCHEDULE_CYCLE cycle = intValEntry.getKey();
					List<DayHourTimePeriod> periods = intValEntry.getValue();
					// <DailyCycle>
					Element dailyCycleElement = doc.createElement(ScheduleTemplatesDeserializer.XML_TAGS.DailyCycle.toString());
					scheduleElement.appendChild(dailyCycleElement);
					
					for(int i = 0; i < periods.size(); i++) {
						HeatingAndCoolingInterval hci = (HeatingAndCoolingInterval) periods.get(i);
						int endhour = hci.getMaximumHour();
						// <Interval>
						Element intervalElement = doc.createElement(ScheduleTemplatesDeserializer.XML_TAGS.Interval.toString());
						dailyCycleElement.appendChild(intervalElement);
						
						if(endhour < 24 && ((i+1) < periods.size())) {
							// <IBK:Parameter -End >
							Element endElement = doc.createElement("IBK:Parameter");
							endElement.setAttribute(ScheduleTemplatesDeserializer.XML_TAG_ATTRIBUTES.name.toString(), "End");
							endElement.setAttribute(ScheduleTemplatesDeserializer.XML_TAG_ATTRIBUTES.unit.toString(), "h");
							endElement.setTextContent(""+endhour);
							intervalElement.appendChild(endElement);
						}
						
						if(hci.getHeatingSetPoint() != null) {						
							// <IBK:Parameter -HeatingSetPointemperature >
							Element heatingSetPointElement = doc.createElement("IBK:Parameter");
							heatingSetPointElement.setAttribute(ScheduleTemplatesDeserializer.XML_TAG_ATTRIBUTES.name.toString(), "HeatingSetPointTemperature");
							heatingSetPointElement.setAttribute(ScheduleTemplatesDeserializer.XML_TAG_ATTRIBUTES.unit.toString(), hci.getHeatingUnit());
							heatingSetPointElement.setTextContent(""+hci.getHeatingSetPoint());
							intervalElement.appendChild(heatingSetPointElement);
						}
						
						if(hci.getCoolingSetPoint() != null) {
							// <IBK:Parameter -CoolingSetPointTemperature >
							Element coolingSetPointElement = doc.createElement("IBK:Parameter");
							coolingSetPointElement.setAttribute(ScheduleTemplatesDeserializer.XML_TAG_ATTRIBUTES.name.toString(), "CoolingSetPointTemperature");
							coolingSetPointElement.setAttribute(ScheduleTemplatesDeserializer.XML_TAG_ATTRIBUTES.unit.toString(), hci.getCoolingUnit());
							coolingSetPointElement.setTextContent(""+hci.getCoolingSetPoint());
							intervalElement.appendChild(coolingSetPointElement);
						}
							
					}
				}

			}
		}
			
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(destPath));
 
		transformer.transform(source, result);
	}
}
