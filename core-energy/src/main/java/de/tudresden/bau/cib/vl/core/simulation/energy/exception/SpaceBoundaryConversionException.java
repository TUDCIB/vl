package de.tudresden.bau.cib.vl.core.simulation.energy.exception;

import de.tudresden.bau.cib.vl.core.exception.ToolException;


public class SpaceBoundaryConversionException extends ToolException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5961406819053253883L;

	public SpaceBoundaryConversionException(String text) {
		super(text);
	}
	
	public SpaceBoundaryConversionException(Throwable t) {
		super(t);
	}
	
	SpaceBoundaryConversionExceptionCode spaceBoundaryConversionExceptionCode;
	private Exception hiddenException;
	private String message;

	public SpaceBoundaryConversionException(String error, Exception exception) {
		super(error, exception);
		this.hiddenException = exception;
		this.message = error;
	}

	public SpaceBoundaryConversionException(
			SpaceBoundaryConversionExceptionCode spaceBoundaryConversionExceptionCode,
			Exception exception) {
		super(spaceBoundaryConversionExceptionCode.getMessage(), exception);
		this.spaceBoundaryConversionExceptionCode = spaceBoundaryConversionExceptionCode;
		this.hiddenException = exception;
	}

	public String getMessage() {
		return (message == null) ? (spaceBoundaryConversionExceptionCode.getMessage()) : message;
	}

	public Exception getHiddenException() {
		return (hiddenException);
	}
}
