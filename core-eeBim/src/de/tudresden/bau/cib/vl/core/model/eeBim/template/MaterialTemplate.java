package de.tudresden.bau.cib.vl.core.model.eeBim.template;



public class MaterialTemplate extends RemoteResource {
	

	public static final String TYPE_MATERIAL = "Material";

	/**
	 * 
	 */
	private static final long serialVersionUID = 2752660409661109984L;
	
	/**
	 * Thermal conductivity
	 */
	private double lambda;
	/**
	 * overall heat transfer resistance  (water uptake coefficient)
	 */
	private double AW;


	@Override
	public String toString() {
		return TYPE_MATERIAL +"("+super.toString()+")";
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public double getAW() {
		return AW;
	}

	public void setAW(double aW) {
		AW = aW;
	}

}
