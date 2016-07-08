package de.tudresden.bau.cib.vl.core.simulation.energy.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.visualization.VisualizationJsonFormatter;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergyResultService;

public class JSONTest {
	
	private String json = "";
	private static final String JSON_FILE = "D:\\result_1349968996754.json";
	private FileReader reader;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		File file = new File(JSON_FILE);
		assertNotNull(file);
		
		reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String s;
		while((s = br.readLine()) != null) json += s;
		
		reader.close();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testJSONValues() throws ParseException {
		assertTrue(json.length() > 0);

		JSONArray jsonArray = JSONArray.fromObject(json);
		int size = jsonArray.size();
		for(int i = 0; i < size; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			JSONArray idArray = jsonObject.getJSONArray(VisualizationJsonFormatter.LOCATION_ID);			
			String startYear = jsonObject.getString(VisualizationJsonFormatter.START_YEAR);
			String eKPI = jsonObject.getString(VisualizationJsonFormatter.EKPI);
			String aggregated = jsonObject.getString(VisualizationJsonFormatter.AGGREGATED);
			JSONArray cols = jsonObject.getJSONArray(VisualizationJsonFormatter.COLS);
			JSONObject colsX = cols.getJSONObject(0);
			String id = colsX.getString("id");
			String timeUnit = colsX.getString(VisualizationJsonFormatter.TIME_UNIT);
			String type = colsX.getString("type");
			JSONObject colsY = cols.getJSONObject(1);
			String quantity = colsY.getString(VisualizationJsonFormatter.QUANTITY);
			String valueUnit = colsY.getString(VisualizationJsonFormatter.VALUE_UNIT);
			String resultCode = colsY.getString(VisualizationJsonFormatter.RESULT_CODE);
			String label = colsY.getString(VisualizationJsonFormatter.LABEL);
			
			JSONArray rows = jsonObject.getJSONArray(VisualizationJsonFormatter.ROWS);
			

			for(int j = 0; j < idArray.size(); j++) {
				String locId = idArray.getString(j);
				MeasuredUnit unit = new MeasuredUnit(locId);
				assertTrue("Anzahl ROWS "+rows.size(), rows.size() == 8761);
				for(int k = 0; k < rows.size(); k++) {
					JSONObject row = rows.getJSONObject(k);
					String x = row.getString(VisualizationJsonFormatter.X);
					String y = row.getString(VisualizationJsonFormatter.Y);
					unit.addValue(x, y);
					int val = Integer.parseInt(x);
					if((k % 10) == 0)System.out.println("Objekt "+i+"; Wert: "+val);
					assertEquals(k, val);
				}		
				Calendar cal = Calendar.getInstance();
				Date startDate = EnergyResultService.ISO_DATE_FORMAT.parse(startYear);
				cal.setTime(startDate);
				unit.setStartYear(cal.get(Calendar.YEAR));
				unit.setTimeUnit(timeUnit);
				unit.setLabel(label);
				unit.setQuantity(quantity);
				unit.setResultCode(Integer.valueOf(resultCode));
				unit.setValueUnit(valueUnit);
				unit.setAggregated(Boolean.valueOf(aggregated));
			}					

		}
	}

}
