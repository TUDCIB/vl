package de.tudresden.bau.cib.vl.core.model.ontology.exception;

public class FactoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1175386346024511321L;

	public FactoryException(String msg) {
		super(msg);
	}
	
	public FactoryException(Throwable t) {
		super(t);
	}
	
	public FactoryException(String msg, Throwable t) {
		super(msg, t);
	}
}
