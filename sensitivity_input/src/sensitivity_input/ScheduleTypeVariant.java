/**
 * 
 */
package sensitivity_input;

import java.util.List;

/**
 * @author Frank Noack
 *
 */
public class ScheduleTypeVariant extends Variant {
	private List< ScheduleQuantity > quantities;
	
	/**
	 * constructor
	 */
	public ScheduleTypeVariant( String iD, 
			                    String type, 
			                    List< ScheduleQuantity > q ){
		super ( iD, type );
		quantities = q;
	}

	/**
	 * @return the quantities
	 */
	public List< ScheduleQuantity > getQuantities() {
		return quantities;
	}

	/**
	 * @param quantities the quantities to set
	 */
	public void setQuantities(List< ScheduleQuantity > quantities) {
		this.quantities = quantities;
	}

}
