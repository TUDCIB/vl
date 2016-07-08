/**
 * 
 */
package sensitivity_input;

import java.util.List;

/**
 * @author Frank Noack
 *
 */
public class ConstructionTypeVariant extends Variant{
	private List< Layer > layers;
	
	/**
	 * constructor
	 */
	public ConstructionTypeVariant( String variantId, 
			                        String variantType, 
			                        List< Layer > layers ) {
		super( variantId, variantType );
		this.setLayers(layers);
	}

	/**
	 * @return the layers
	 */
	public List< Layer > getLayers() {
		return layers;
	}

	/**
	 * @param layers the layers to set
	 */
	public void setLayers(List< Layer > layers) {
		this.layers = layers;
	}

}
