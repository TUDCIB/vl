/**
 * 
 */
package sensitivity_input;

import java.net.URL;
import java.util.ArrayList;

/**
 * This class holds a summary of a particular quantity over several zones.
 * 
 * It reflects a NANDRAD output file with a table of the particular quantity
 * in the regarded zones. 
 * 
 * @author Frank Noack
 *
 */
public class OutputQuantityOfZones {
	
	private String name;
	private String unit;
	private URL statDataSeriesSummary;
	
	public ArrayList< Integer > zoneIndices;
	public ArrayList< String > IFCKeys;
	public ArrayList< Double > sumOfAnnualLosses;
	public ArrayList< Double > sumOfAnnualGains;
	public ArrayList< Double > Median;
	public ArrayList< Double > Min;
	public ArrayList< Double > Max;
	public ArrayList< Double > FirstQuartile;
	public ArrayList< Double > ThirdQuartile;
	public ArrayList< Double > WhiskerMin;
	public ArrayList< Double > WhiskerMax;
	
	public OutputQuantityOfZones( String name, URL statData ) {
		this.name = name;
		this.statDataSeriesSummary = statData;
		this.zoneIndices = new ArrayList< Integer >();
		this.IFCKeys = new ArrayList< String >();
		this.sumOfAnnualGains = new ArrayList< Double >();
		this.sumOfAnnualLosses = new ArrayList< Double >();
		this.Median = new ArrayList< Double >();
		this.Min = new ArrayList< Double >();
		this.Max = new ArrayList< Double >();
		this.FirstQuartile = new ArrayList< Double >();
		this.ThirdQuartile = new ArrayList< Double >();
		this.WhiskerMin = new ArrayList< Double >();
		this.WhiskerMax = new ArrayList< Double >();

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the statDataSeriesSummary
	 */
	public URL getStatDataSeriesSummary() {
		return statDataSeriesSummary;
	}

	/**
	 * @param statDataSeriesSummary the statDataSeriesSummary to set
	 */
	public void setStatDataSeriesSummary(URL statDataSeriesSummary) {
		this.statDataSeriesSummary = statDataSeriesSummary;
	}

}
