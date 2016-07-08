/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;

/**
 * The Class ClimateTemplateTemperatureDeserializer.
 */
public final class ClimateTemplateTemperatureDeserializer {


	/** The formatter. */
	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");
		
	
	/**
	 * Instantiates a new climate template temperature deserializer.
	 */
	private ClimateTemplateTemperatureDeserializer() {
		throw new AssertionError();
	}
	
	/**
	 * Deserialize temperatures.
	 *
	 * @param filePath the file path
	 * @return the measured unit
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static MeasuredUnit deserializeTemperatures(String filePath) throws IOException {
		MeasuredUnit unit = new MeasuredUnit("ClimateTemperatures");
		unit.setTimeUnit("h");
		unit.setStartYear(2012);
		
		int hourOfYear = 0;
		int dayOfYear = 0;
		
		FileReader fr = new FileReader(filePath);

		BufferedReader br = new BufferedReader(fr);
		String line = "";
		int i = 0;
//		it begins with line 7
		while ((line = br.readLine()) != null) {
			if(i < 6) {
				i++;
				continue; 
			}
			line = line.trim();
			
			List<String> contentOfLine = new ArrayList<String>();
			String tmpString = "";
			int lineLength = line.length();
			for(int j = 0; j < lineLength; j++) {
				char c = line.charAt(j);
				if (c != ' ' && c != '\t') {
					tmpString +=c;
				} else {
					if(!tmpString.equals("")) contentOfLine.add(tmpString);
					tmpString = "";				
				}
//				adds the temperature to the list because they are the last chars
				if(j == lineLength-1) contentOfLine.add(tmpString);
			}
			int hourOfDay = formatter.parseDateTime(contentOfLine.get(1)).getHourOfDay();
			String temperature = contentOfLine.get(2);
			
			dayOfYear = Integer.valueOf(contentOfLine.get(0));
			hourOfYear = (dayOfYear*24) + hourOfDay;
			
			unit.addValue(""+hourOfYear, temperature);
			
		}
		br.close();
		fr.close();
		return unit;
	}
}
