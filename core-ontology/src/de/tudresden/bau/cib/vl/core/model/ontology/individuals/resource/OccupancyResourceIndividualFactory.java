package de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource;

import com.hp.hpl.jena.ontology.Individual;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;

public class OccupancyResourceIndividualFactory extends AbstractResourceIndividualFactory {

	
	public OccupancyResourceIndividualFactory(OntologyModel ontModel) {
		super(ontModel);
	}
	
	public Individual createOccupancyIndividual(SpaceTypeTemplate spaceType) 
			throws FactoryException {
		return createIndividual(spaceType, EeBIMOntoVocabulary.SPACE_TYPE.getURI());		
	}

}
