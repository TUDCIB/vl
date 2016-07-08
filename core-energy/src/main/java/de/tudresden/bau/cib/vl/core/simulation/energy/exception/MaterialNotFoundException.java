package de.tudresden.bau.cib.vl.core.simulation.energy.exception;

public class MaterialNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3427670442488220627L;

	public MaterialNotFoundException(String message) {
		super(message);
	}
	
	public MaterialNotFoundException(Throwable t) {
		super(t);
	}
}
