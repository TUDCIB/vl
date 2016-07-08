package de.tudresden.bau.cib.vl.core.simulation.energy.exception;

import de.tudresden.bau.cib.vl.core.exception.ToolException;


public class NandradException extends ToolException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5961406819053253883L;

	public NandradException(String text) {
		super(text);
	}
	
	public NandradException(Throwable t) {
		super(t);
	}
	
	NandradExceptionCode nandradExceptionCode;
	private Exception hiddenException;
	private String message;

	public NandradException(String error, Exception exception) {
		super(error, exception);
		this.hiddenException = exception;
		this.message = error;
	}

	public NandradException(
			NandradExceptionCode nandradExceptionCode,
			Exception exception) {
		super(nandradExceptionCode.getMessage(), exception);
		this.nandradExceptionCode = nandradExceptionCode;
		this.hiddenException = exception;
	}

	public String getMessage() {
		return (message == null) ? (nandradExceptionCode.getMessage()) : message;
	}

	public Exception getHiddenException() {
		return (hiddenException);
	}
}
