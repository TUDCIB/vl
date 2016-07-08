//package de.tudresden.bau.cib.vl.gui.common.widgets;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.Composite;
//
//import de.tudresden.bau.cib.ui.widgets.google.AnnotatedTimeLine;
//import de.tudresden.bau.cib.ui.widgets.google.JSONGoogleDataTable;
//import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
//
//public class TimeLineWidget extends Composite {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 6389032998031940326L;
//	private AnnotatedTimeLine timeLine;
//	private JSONGoogleDataTable table;
//	private int width;
//	private int height;
//	
//
//	public TimeLineWidget(Composite parent, int style) {
//		super(parent, style);		
//
//	    buildTable(null);
//	    buildDiagram();
//	}
//
//	public TimeLineWidget(Composite parent, int style, int minHeight, int minWidth) {
//		this(parent, style);
//		this.width = minWidth;
//		this.height = minHeight;
//	}
//	
//	private void buildDiagram() {	
//		int boundsWidth = this.getBounds().width;
//		int boundsHeight = this.getBounds().height;
//		if(boundsHeight > this.height) this.height = boundsHeight;
//		if(boundsWidth > this.width) this.width = boundsWidth;
//		
//		timeLine = new AnnotatedTimeLine(this, SWT.NONE);
//		timeLine.setBounds(0, 0, this.width, this.height);
//		boolean showZoomButtons = false;
//		timeLine.setWidgetOptions("{" +
//				"title: 'Results', " +
//				"width: "+this.width+", " +
//				"height: "+this.height+", " +
//				"displayAnnotations: true, " +
//				"thickness: 0, " + 
//				"displayZoomButtons: "+showZoomButtons+"}");	
//	
////	    use JSON format
//		timeLine.setWidgetData(table.toString());
//	}
//	
//	private void buildTable(List<MeasuredUnit> units) {
//		table = new JSONGoogleDataTable();
//		table.addColumn("time", "Time", "datetime", null);
//		if(units == null) return;
//		for(int i = 0; i< units.size(); i++) {
//			MeasuredUnit unit = units.get(i);
//			table.addColumn(unit.getId(), unit.getLabel(), "number", null);
//		}
//	}
//	
//	public void setData(List<MeasuredUnit> units) {
////		clear the tables
//		buildTable(units);
//		
//		final int hoursPerDay = (int) TimeUnit.DAYS.toHours(1);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		
//		String hasEqualTimeUnits = null;
//		
////		TODO currently shows only one unit
//		
//		for(int index = 0; index < units.size(); index++) {
//			MeasuredUnit unit = units.get(index);		
//			// check for equal time units TODO if not equal convert them!
//			if(hasEqualTimeUnits == null) {
//				hasEqualTimeUnits = unit.getTimeUnit();
//			} else if(!hasEqualTimeUnits.equalsIgnoreCase(unit.getTimeUnit())) {
//				throw new IllegalArgumentException("Time units are different ("+hasEqualTimeUnits+" != "+unit.getTimeUnit()+")");
//			}
//			
//			cal.set(Calendar.YEAR, Integer.valueOf(unit.getStartYear()));
//			
//			int i = 0;
//			int currentDay = 0;
//			Map<Long, Double> valuePerTimeUnit = unit.getValues();
//			for(Map.Entry<Long, Double> entry : valuePerTimeUnit.entrySet()) {
////				double time = entry.getKey();
//				double value = entry.getValue();
////				TODO unit time beachten
//				if((i % hoursPerDay) == 0) {
//					currentDay++;
//				}
//				cal.set(Calendar.HOUR_OF_DAY, (i % hoursPerDay));
//				cal.set(Calendar.DAY_OF_YEAR, currentDay);
//				Date date = cal.getTime();
//				
//				table.addRow(new Object[]{
//					//	time
//					date,
//					// value
//					value
//				});
//				
//				i++;
//			}
//			break;
//		}			
//		buildDiagram();
//	}
//
//}
