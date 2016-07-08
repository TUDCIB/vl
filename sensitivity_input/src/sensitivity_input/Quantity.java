/**
 * 
 */
package sensitivity_input;

/**
 * The class holds one particular quantity with name, unit and value.
 * 
 * @author Frank Noack
 *
 */
public class Quantity {
	
	public String name;
	public String unit;
	public Double value;
	
	public Quantity( String name, String unit, Double value ) {
		this.name = name;
		this.unit = unit;
		this.value = value;
	}

}
