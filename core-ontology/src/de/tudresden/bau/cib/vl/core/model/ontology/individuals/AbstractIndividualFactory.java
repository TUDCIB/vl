package de.tudresden.bau.cib.vl.core.model.ontology.individuals;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;

/**
 * TODO JavaDoc
 * @author  <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 */
public abstract class AbstractIndividualFactory implements IndividualFactory {

	/**
	 * @uml.property  name="metamodel"
	 * @uml.associationEnd  
	 */
	protected OntologyModel ontologyModel;
	/**
	 * @uml.property  name="ontClass"
	 * @uml.associationEnd  
	 */
//	protected OntClassMap ontClassMap;
	/**
	 * @uml.property  name="objectProperties"
	 * @uml.associationEnd  
	 */
//	protected ObjectPropertyMap objectPropertiesMap;
	/**
	 * @uml.property  name="datatypeProperties"
	 * @uml.associationEnd  
	 */
//	protected DatatypePropertyMap datatypePropertiesMap;
	
	
	public AbstractIndividualFactory(OntologyModel ontModel) {
		this.ontologyModel = ontModel;
//		this.ontClassMap = ontModel.getOntologyClasses();
//		this.objectPropertiesMap = ontModel.getOntologyProperties();
//		this.datatypePropertiesMap = ontModel.getDatatypeProperties();
	}
	
	/**
	 * @param m
	 * @param ontClass
	 * @return The URI of the ontology class in lower case.
	 */
	public static String getUriOfOntologyClassInModelInLowerCase(Model m, String ontClass) {
		String uri = m.getNameSpace().toLowerCase()+ontClass.toLowerCase();
		return uri;
	}
	
	/**
	 * Creates a new individual without checking if it already exists.
	 * 
	 * @param clazz
	 * @param uri
	 * @return
	 * @throws FactoryException
	 */
	protected final Individual createIndividual(OntClass clazz, String uri) throws FactoryException {
//		Individual individual = ontologyModel.createIndividual(clazz, uri);
//		if(individual == null) {
//			throw new FactoryException("Individual with URI: "+uri+" was not created");
//		} 
//		return individual;
		return createIndividual(clazz, uri, ontologyModel);
	}
	
	public static Individual createIndividual(OntClass clazz, String uri, OntologyModel ontologyModel) throws FactoryException {
		Individual individual = ontologyModel.createIndividual(clazz, uri);
		if(individual == null) {
			throw new FactoryException("Individual with URI: "+uri+" was not created");
		} 
		return individual;
	}
	
}
