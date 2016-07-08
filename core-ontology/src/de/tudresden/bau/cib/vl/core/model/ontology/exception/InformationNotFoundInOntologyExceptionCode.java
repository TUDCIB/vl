package de.tudresden.bau.cib.vl.core.model.ontology.exception;

/**
 * TODO JavaDoc
 * @author  <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 */
public enum InformationNotFoundInOntologyExceptionCode {

	/**
	 * @uml.property  name="iNDIVIDUAL_NOT_FOUND"
	 * @uml.associationEnd  
	 */
	INDIVIDUAL_NOT_FOUND("The individual was not found in the ontology"),
	
	PROPERTY_NOT_FOUND("The property was not found in the ontology"),
	
	/**
	 * @uml.property  name="sPARQL_NO_RESULT"
	 * @uml.associationEnd  
	 */
	SPARQL_NO_RESULT("There was no result for the query"),
	/**
	 * @uml.property  name="sPARQL_MULTIPLE_VALUES_FOUND"
	 * @uml.associationEnd  
	 */
	SPARQL_MULTIPLE_VALUES_FOUND("There are more than one value which should not be possible");

	private final String message;
	
	InformationNotFoundInOntologyExceptionCode(String message) {
		this.message = message;
	}
	
	/**
	 * TODO javadoc
	 * @return
	 * @uml.property  name="message"
	 */
	public String getMessage() {
		return message;
	}
}
