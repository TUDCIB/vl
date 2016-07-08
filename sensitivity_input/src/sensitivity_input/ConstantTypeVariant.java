/**
 * 
 */
package sensitivity_input;

import java.util.List;

/**
 * @author Frank Noack
 *
 */
public class ConstantTypeVariant extends Variant {
	private List< Quantity > properties;
	
	/**
	 * constructor
	 */
	public ConstantTypeVariant( String variantId, 
			                    String variantType, 
			                    List< Quantity > variantProperties ) {
		super( variantId, variantType );
		properties = variantProperties;
	}

	/**
	 * @return the properties
	 */
	public List< Quantity > getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(List< Quantity > properties) {
		this.properties = properties;
	}

}
