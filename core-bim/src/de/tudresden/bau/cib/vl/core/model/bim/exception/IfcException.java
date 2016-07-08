package de.tudresden.bau.cib.vl.core.model.bim.exception;


public class IfcException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5808683019657010132L;

	IfcExceptionCode exceptionCode;
	
	private Exception hiddenException;

	public IfcException(Exception exception) {
		super(exception);
		hiddenException = exception;
	}
	
	public IfcException(String error, Exception exception) {
		super(error);
		hiddenException = exception;
	}

	public IfcException(IfcExceptionCode exceptionCode, Exception exception) {
		super(exceptionCode.getMessage());
		this.exceptionCode = exceptionCode;
		this.hiddenException = exception;
	}

	public Exception getHiddenException() {
		return (hiddenException);
	}

	public String getMessage() {
		return exceptionCode != null ? (exceptionCode.getMessage()) : super.getMessage()+" "+hiddenException;
	}
	
	
}
