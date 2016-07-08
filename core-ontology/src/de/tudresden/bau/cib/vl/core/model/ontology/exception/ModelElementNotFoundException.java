package de.tudresden.bau.cib.vl.core.model.ontology.exception;

public class ModelElementNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 69252497118653454L;

	public ModelElementNotFoundException(String msg) {
		super(msg);
	}
	
	public ModelElementNotFoundException(Throwable t) {
		super(t);
	}
	
	public ModelElementNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}
