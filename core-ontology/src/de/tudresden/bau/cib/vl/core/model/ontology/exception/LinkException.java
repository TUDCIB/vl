package de.tudresden.bau.cib.vl.core.model.ontology.exception;

public class LinkException extends FactoryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1175386346024511321L;

	public LinkException(String msg) {
		super(msg);
	}
	
	public LinkException(Throwable t) {
		super(t);
	}
	
	public LinkException(String msg, Throwable t) {
		super(msg, t);
	}
}
