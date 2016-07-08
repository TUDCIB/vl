package de.tudresden.bau.cib.vl.core.model.ontology.individuals;

import java.util.Set;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;

public abstract class AbstractModelIndividualFactory<T extends Model> extends AbstractIndividualFactory {

	protected T sourceModel;
	
	
	public AbstractModelIndividualFactory(OntologyModel ontModel, T sourceModel) {
		super(ontModel);
		this.sourceModel = sourceModel;
	}

	/**
	 * Creates all individuals in a given model and their inter-linking.
	 * @return
	 * @throws FactoryException
	 */
	public abstract Set<Individual> createAllIndividuals() throws FactoryException;
	
	/**
	 * Creates an individual in the model with the given identifier or returns the already existing individual.
	 * 
	 * @param elementId
	 * @return The new or already existing individual.
	 * @throws FactoryException
	 */
	public abstract Individual createIndividual(String elementId) throws FactoryException;
	
	/**
	 * Creates a new individual without checking if it already exists.
	 * 
	 * @param clazz
	 * @param elementId
	 * @return
	 * @throws FactoryException
	 */
	protected final Individual createIndividualByElementId(OntClass clazz, String elementId) throws FactoryException {
		String uri = OntologyModel.getUniformResourceIdentifierOfElementInModel(sourceModel, elementId);
		return super.createIndividual(clazz, uri);
	}
}
