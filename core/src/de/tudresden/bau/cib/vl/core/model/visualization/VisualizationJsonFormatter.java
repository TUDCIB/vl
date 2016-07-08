package de.tudresden.bau.cib.vl.core.model.visualization;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;

public final class VisualizationJsonFormatter {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(VisualizationJsonFormatter.class);
	
	public static final String ID = "id";
	public static final String AGGREGATED = "aggregated";
	public static final String TIME = "TIME";
	public static final String TIME_LABEL = "Time";
	public static final String VALUE = "VALUE";
	public static final String START_YEAR = "startYear";
	public static final String LOCATION_ID = "locId";
	public static final String LABEL = "label";
	public static final String TIME_UNIT = "timeUnit";
	public static final String QUANTITY = "quantity";
	public static final String VALUE_UNIT = "valueUnit";
	public static final String RESULT_CODE = "resultCode";
	public static final String EKPI = "eKPI";
	public static final String COLS = "cols";
	public static final String ROWS = "rows";
	public static final String X = "x";
	public static final String Y = "y";
	
	static final SimpleDateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	
	public static enum TYPES {
		datetime, string, number
	}
	
	
	private VisualizationJsonFormatter() {
		throw new AssertionError();
	}
	
	
	/**
	 * Converts the measured units into a JSON representation.
	 * @param measuredUnits
	 * @return
	 */
	public static String toJson(List<MeasuredUnit> measuredUnits) {
		JSONArray jsonArray = new JSONArray();
//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		
		for(MeasuredUnit unit : measuredUnits) {
			JSONObject location = new JSONObject();
			String id = unit.getId();
			JSONArray idArray = new JSONArray();
			idArray.add(id);
			
			String startDateString = ISO_DATE_FORMAT.format(new Date());
			
//			rows
			Map<Long, Double> valueMap = unit.getValues();
			JSONArray rows = new JSONArray();
			int i = 0;
			for(Map.Entry<Long, Double> entry : valueMap.entrySet()) {
				JSONObject row = new JSONObject();
				Long milliSeconds = entry.getKey(); // key is in milliseconds
				if(i == 0) startDateString = ISO_DATE_FORMAT.format(new Date(milliSeconds));
//				Date date = new Date(milliSeconds);
//				
//				calendar.setTime(date);
//
//				int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
//				int previousDays = dayOfYear-1;
//				int previousHours = previousDays*24;
//				int hour24 = calendar.get(Calendar.HOUR_OF_DAY);
//				row.element(X, (previousHours+hour24));
				
				row.element(X, i);
				row.element(Y, entry.getValue());		
				rows.add(row);
				
				i++;
			}
			
			location.element(LOCATION_ID, idArray);
//			location.element(START_YEAR, unit.getStartYear()+"-01-01T00:00:00");
			location.element(START_YEAR, startDateString);
//			eKPI
			location.element(EKPI, createEKPISection(unit.getKeyPerformanceIndicator()));
			location.element(AGGREGATED, unit.isAggregated());
//			cols
			JSONArray cols = new JSONArray();
//			X
			JSONObject colsX = new JSONObject();
			colsX.element(ID, TIME);
//			colsX.element(LABEL, TIME_LABEL);
			colsX.element(TIME_UNIT, unit.getTimeUnit());
			colsX.element("type", TYPES.datetime);
//			Y
			JSONObject colsY = new JSONObject();
			colsY.element(ID, VALUE);
			colsY.element(QUANTITY, unit.getQuantity());
			colsY.element(LABEL, unit.getLabel());
			colsY.element(VALUE_UNIT, unit.getValueUnit());
			colsY.element(RESULT_CODE, unit.getResultCode());
			colsY.element("type", TYPES.number);
			cols.add(colsX);
			cols.add(colsY);

			location.element(COLS, cols);
			location.element(ROWS, rows);
			jsonArray.add(location);
		}
		return jsonArray.toString();
	}
	
	/**
	 * FIXME Time conversion is currently wrong! and Key Performance Indicator too
	 * @param json
	 * @return
	 * @throws ParseException
	 */
	public static List<MeasuredUnit> separatedJsontoRawData(String json) throws ParseException {
		LOGGER.debug("Creating measurement units from JSON");	
		
		List<MeasuredUnit> units = new ArrayList<MeasuredUnit>();
		JSONArray jsonArray = JSONArray.fromObject(json);
		int size = jsonArray.size();
		for(int i = 0; i < size; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			JSONArray idArray = jsonObject.getJSONArray(LOCATION_ID);			
			String startYear = jsonObject.getString(START_YEAR);
//			String eKPI = jsonObject.getString(EKPI);
			String aggregated = jsonObject.getString(AGGREGATED);
			JSONArray cols = jsonObject.getJSONArray(COLS);
			JSONObject colsX = cols.getJSONObject(0);
//			String id = colsX.getString("id");
			String timeUnit = colsX.getString(TIME_UNIT);
//			String type = colsX.getString("type");
			JSONObject colsY = cols.getJSONObject(1);
			String quantity = colsY.getString(QUANTITY);
			String valueUnit = colsY.getString(VALUE_UNIT);
			String resultCode = colsY.getString(RESULT_CODE);
			String label = colsY.getString(LABEL);
			
			JSONArray rows = jsonObject.getJSONArray(ROWS);
			
			String mid = null;
			for(int j = 0; j < idArray.size(); j++) {
				mid = idArray.getString(j);
			}
			MeasuredUnit unit = new MeasuredUnit(mid);
			
			for(int j = 0; j < rows.size(); j++) {
				JSONObject row = rows.getJSONObject(j);
				String x = row.getString(X);
				String y = row.getString(Y);
				unit.addValue(x, y);
			}		
			Calendar cal = Calendar.getInstance();
			Date startDate = MeasuredUnit.ISO_DATE_FORMAT.parse(startYear);
			cal.setTime(startDate);
			unit.setStartYear(cal.get(Calendar.YEAR));
			unit.setTimeUnit(timeUnit);
			unit.setLabel(label);
			unit.setQuantity(quantity);
			unit.setResultCode(Integer.valueOf(resultCode));
			unit.setValueUnit(valueUnit);
			unit.setKeyPerformanceIndicator(null);
			unit.setAggregated(Boolean.valueOf(aggregated));
			units.add(unit);
			LOGGER.debug("Measurement unit created successfully ["+unit+"]");
		}
		return units;
	}
	
	/**
	 * eKPI: {
	 * 	cols: {["Location", "Heating", "Cooling"]},
	 * 	rows: [["Building", "96536", "-9000"], ["1st Storey", "9565", "-903"]]
	 * }
	 * @param eKPIs
	 * @return
	 */
	private static JSONObject createEKPISection(Map<String, List<String>> eKPIs) {
		JSONObject eKPIObject = new JSONObject();	
		if(eKPIs != null) {			
			int colArrSize = eKPIs.size();
			int highestRowArrSize = -1;
			String[] colArray = eKPIs.keySet().toArray(new String[colArrSize]);
			String[][] rowArray = new String[colArrSize][];		
		
			int index = 0;
			for (Map.Entry<String, List<String>> entry : eKPIs.entrySet()) {
//				String key = entry.getKey();
				List<String> valueList = entry.getValue();
				if(valueList.size() > highestRowArrSize) highestRowArrSize = valueList.size();
//				eKPIColsArray.add(key);
//				eKPIRowsArray.add(value);	
				rowArray[index] = valueList.toArray(new String[valueList.size()]);
				index++;
			}
			JSONArray eKPIColsArray = JSONArray.fromObject(colArray);
			JSONArray eKPIRowsArray = new JSONArray();	
			for(int j = 0; j < highestRowArrSize; j++) {
				List<String> row = new ArrayList<String>();
				for(int i = 0; i < colArrSize; i++) {
					String value = (rowArray[i][j] != null) ? rowArray[i][j] : "-";
					row.add(value);
				}
				eKPIRowsArray.add(row);
			}
			
			eKPIObject.element(COLS, eKPIColsArray);
			eKPIObject.element(ROWS, eKPIRowsArray);
		}
		return eKPIObject;
	}
}
