package de.tudresden.bau.cib.vl.gui.gis.exception;

public class NoLocationFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1148981832126173725L;

	public NoLocationFoundException(String msg) {
		super(msg);
	}
	
	public NoLocationFoundException(Throwable t) {
		super(t);
	}
}
