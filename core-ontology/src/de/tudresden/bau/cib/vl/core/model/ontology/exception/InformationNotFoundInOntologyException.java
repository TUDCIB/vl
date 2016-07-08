package de.tudresden.bau.cib.vl.core.model.ontology.exception;


/**
 * TODO JavaDoc
 * @author  <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 */
public class InformationNotFoundInOntologyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3642237461891686668L;

	/**
	 * @uml.property  name="informationNotFoundInOntologyExceptionCode"
	 * @uml.associationEnd  
	 */
	InformationNotFoundInOntologyExceptionCode informationNotFoundInOntologyExceptionCode;
	private Exception hiddenException;
	private String message;
	
	
	public InformationNotFoundInOntologyException(InformationNotFoundInOntologyExceptionCode informationNotFoundInOntologyExceptionCode) {
		super(informationNotFoundInOntologyExceptionCode.getMessage());
		this.message = informationNotFoundInOntologyExceptionCode.getMessage();
	}
	
	public InformationNotFoundInOntologyException(InformationNotFoundInOntologyExceptionCode informationNotFoundInOntologyExceptionCode, String details) {
		super(informationNotFoundInOntologyExceptionCode.getMessage());
		this.message = informationNotFoundInOntologyExceptionCode.getMessage()+" "+details;
	}

	public InformationNotFoundInOntologyException(String error, Exception exception) {
		super(error);
		this.hiddenException = exception;
		this.message = error;
	}

	public InformationNotFoundInOntologyException(
			InformationNotFoundInOntologyExceptionCode informationNotFoundInOntologyExceptionCode,
			Exception exception) {
		this(informationNotFoundInOntologyExceptionCode);
		this.informationNotFoundInOntologyExceptionCode = informationNotFoundInOntologyExceptionCode;
		this.hiddenException = exception;
	}

	/**
	 * TODO javadoc
	 * @return
	 * @uml.property  name="message"
	 */
	public String getMessage() {
		return (message == null) ? (informationNotFoundInOntologyExceptionCode.getMessage()) : message;
	}

	/**
	 * TODO javadoc
	 * @return
	 * @uml.property  name="hiddenException"
	 */
	public Exception getHiddenException() {
		return (hiddenException);
	}
}
