package de.tudresden.bau.cib.vl.core.exception;

public class ParsingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 497185017575121263L;

	public ParsingException(Exception e) {
		super(e);
	}
	
	public ParsingException(Throwable e) {
		super(e);
	}
	
	public ParsingException(String msg) {
		super(msg);
	}
	
	public ParsingException(String msg, Throwable t) {
		super(msg, t);
	}
	
	
}
