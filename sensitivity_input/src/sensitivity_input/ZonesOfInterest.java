/**
 * 
 */
package sensitivity_input;

import java.util.ArrayList;

/**
 * Holds the zones of interest which are selected on the basis of passive 
 * NADRAD simulation.
 * It reflects the table of the summary output file as a whole.
 *  
 * @author Frank Noack
 *
 */
public class ZonesOfInterest {
	
	public ArrayList< Integer > zoneIndices;
	public ArrayList< String > IFCKeys;
	/**
	 * the quantitiy which is responsible for selection 
	 */
	public ArrayList< String > outstandigQuantity;
	public ArrayList< Double > value;
	public ArrayList< String > unit;
	
	public ZonesOfInterest() {
		zoneIndices = new ArrayList< Integer >();
		IFCKeys = new ArrayList< String >();
		outstandigQuantity = new ArrayList< String >();
		value = new ArrayList< Double >();
		unit = new ArrayList< String >();
	}
}
