/**
 * 
 */
package sensitivity_input;

/**
 * @author Frank Noack
 *
 */
public class Layer {
	private String unit;
	private double thickness;
	private String specification;
	
	/**
	 * constructor
	 */
	public Layer( String unit, double thickness, String source ) {
		this.setUnit(unit);
		this.setThickness(thickness);
		this.setSpecification(source);
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
	 * @return the thickness
	 */
	public double getThickness() {
		return thickness;
	}

	/**
	 * @param thickness the thickness to set
	 */
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

	/**
	 * @return the source
	 */
	public String getSpecification() {
		return specification;
	}

	/**
	 * @param source the source to set
	 */
	public void setSpecification(String source) {
		this.specification = source;
	}

}
