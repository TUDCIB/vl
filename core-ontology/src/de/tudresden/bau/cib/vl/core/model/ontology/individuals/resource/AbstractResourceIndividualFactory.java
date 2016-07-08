package de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;

import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.AbstractIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;

public class AbstractResourceIndividualFactory extends AbstractIndividualFactory {

	
	public AbstractResourceIndividualFactory(OntologyModel ontModel) {
		super(ontModel);
	}

	protected Individual createIndividual(Resource template, String ontClassUri) 
			throws FactoryException {
		String uri = template.getSourceFileUri();
		Individual ind = ontologyModel.getIndividualByURI(uri);
		if(ind == null) {
			OntClass tOntClass = EeBIMOntoVocabulary.findOntClass(ontClassUri);
			ind = createIndividual(tOntClass, uri);
			
			String name = template.getName();
			if(name != null) {
				DatatypeProperty nameProperty = EeBIMOntoVocabulary.NAME;
				if(nameProperty != null) {
					ind.addProperty(nameProperty, name);
				}
			}
			
			String templatePath = template.getSourceFileUri();
	
			if(templatePath != null) {				
				DatatypeProperty pathToTemplateProperty = EeBIMOntoVocabulary.PATH_TO_TEMPLATE;
				if(pathToTemplateProperty != null) {
					ind.addProperty(pathToTemplateProperty, templatePath);
				}
			}
		}
		return ind;
	}
}
