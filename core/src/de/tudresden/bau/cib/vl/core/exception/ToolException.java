package de.tudresden.bau.cib.vl.core.exception;

import de.tudresden.bau.cib.vl.core.exception.code.ToolExceptionCode;


public class ToolException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5961406819053253883L;

	public ToolException(String text) {
		super(text);
	}
	
	public ToolException(Throwable t) {
		super(t);
	}
	
	ToolExceptionCode toolExceptionCode;
	private Exception hiddenException;
	
	public ToolException(
			ToolExceptionCode toolExceptionCode) {
		super(toolExceptionCode.getMessage());
		this.toolExceptionCode = toolExceptionCode;
	}

	public ToolException(String error, Exception exception) {
		super(error);
		this.hiddenException = exception;
	}

	public ToolException(
			ToolExceptionCode toolExceptionCode,
			Exception exception) {
		super(toolExceptionCode.getMessage()+" "+exception.getMessage());
		this.toolExceptionCode = toolExceptionCode;
		this.hiddenException = exception;
	}

	public Exception getHiddenException() {
		return (hiddenException);
	}
}
