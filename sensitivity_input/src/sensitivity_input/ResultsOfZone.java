/**
 * 
 */
package sensitivity_input;

import java.util.ArrayList;

/**
 * Contains the results of one zone as delivered by NANDRAD.
 * 
 * @author Frank Noack
 *
 */
public class ResultsOfZone implements Comparable< ResultsOfZone >{
	
	/**
	 * the index of the zone in the energy solver
	 */
	private Integer index;
	private String IFCKey;
	
	public ArrayList< Quantity > quantities;
	/** 
	 * the quantity that is responsible for the selection (if that zone is
	 * selected)
	 */
	public ArrayList < Quantity > outstandingQuantities;
	public boolean isZoneOfInterest;

	/**
	 * constructor
	 * 
	 * @param	index	index number oft this zone in the  energy solver
	 * @param	IFCKey	IFC key of the regarded zone
	 */
	public ResultsOfZone( int index, String IFCKey ) {
		this.index = index;
		this.IFCKey = IFCKey;
		this.isZoneOfInterest = false;
		this.setQuantities( new ArrayList< Quantity >() );
		this.setOutstandingQuantities(new ArrayList< Quantity >() );
	}
	
	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	/**
	 * @return the iFCKey
	 */
	public String getIFCKey() {
		return IFCKey;
	}
	/**
	 * @param iFCKey the iFCKey to set
	 */
	public void setIFCKey(String iFCKey) {
		IFCKey = iFCKey;
	}

	/**
	 * @return the outstandingQuantities
	 */
	public ArrayList < Quantity > getOutstandingQuantities() {
		return outstandingQuantities;
	}

	/**
	 * @param outstandingQuantities the outstandingQuantities to set
	 */
	public void setOutstandingQuantities(ArrayList < Quantity > outstandingQuantities) {
		this.outstandingQuantities = outstandingQuantities;
	}

	@Override
	public int compareTo(ResultsOfZone result ) {
		// TODO Auto-generated method stub
		return this.index.compareTo( result.getIndex() );
	}

	/**
	 * @return the quantities
	 */
	public ArrayList< Quantity > getQuantities() {
		return quantities;
	}

	/**
	 * @param quantities the quantities to set
	 */
	public void setQuantities(ArrayList< Quantity > quantities) {
		this.quantities = quantities;
	}

}
