/**
 * 
 */
package sensitivity_input;

import java.util.ArrayList;

/**
 * This class holds a summary of several Therakles simulations, it reflects
 * the table of the summary output file.
 * 
 * @author Frank Noack
 *
 */
public class ResultsOfTheraklesSim {
	
	private String ifcId;
	private String combinationId;
	private ArrayList< Quantity > kPIs;
	
	/**
	 * constructor
	 * 
	 * @param key the IFC key of the zone
	 * @param id the combination ID
	 * @param kPIs the results of the calculation for the zone
	 */
	public ResultsOfTheraklesSim( String key, String id, 
			ArrayList< Quantity > kPIs ) {
		this.ifcId = key;
		this.combinationId = id;
		
		this.kPIs = kPIs;
	}
	
//	/**
//	 * constructor
//	 * 
//	 * @param key the IFC key of the zone
//	 * @param id the combination ID
//	 * @param kPIs the results of the calculation for the zone
//	 */
//	public ResultsOfTheraklesSim( String key, String combId, simId,
//			ArrayList< Quantity > kPIs ) {
//		this.ifcId = key;
//		this.combinationId = combId;
//		this.simId = simId;
//		
//		this.kPIs = kPIs;
//	}

	
	/**
	 * default constructor
	 */
	public ResultsOfTheraklesSim() {
		this.ifcId = new String();
		this.combinationId = new String();
		this.kPIs = new ArrayList< Quantity >();
	}


	/**
	 * @return the ifcKey
	 */
	public String getIfcKey() {
		return ifcId;
	}


	/**
	 * @param ifcKey the ifcKey to set
	 */
	public void setIfcId(String ifcKey) {
		this.ifcId = ifcKey;
	}


	/**
	 * @return the combination ID
	 */
	public String getCombinationId() {
		return combinationId;
	}
	
	
	/**
	 * @param Id the combination ID to set
	 */
	public void setId(String id) {
		this.combinationId = id;
	}
	
	
	/**
	 * @return the the kPIs as quantities
	 */
	public ArrayList< Quantity > getkPIs() {
		return kPIs;
	}
	
	
	/**
	 * @param kpis the kPIs to set
	 */
	public void setkPIs(ArrayList< Quantity > kpis) {
		this.kPIs = kpis;
	}

}
