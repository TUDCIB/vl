package de.tudresden.bau.cib.vl.core.model.ontology.individuals.linking;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.EnergyEnhancedBimModel;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.InformationNotFoundInOntologyException;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.InformationNotFoundInOntologyExceptionCode;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.LinkException;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.EnergyEnhancedBimIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;

public class Resource2ResourceLinker {
	
	private static final Logger LOG = LoggerFactory.getLogger(Resource2ResourceLinker.class);

	/**
	 *  private default constructor ==> can't be instantiated
	 *  side effect: class is final because it can't be subclassed:
	 *  super() can't be called from subclasses
	 *  Taken from the book "Effective Java"
	 */
	private Resource2ResourceLinker() {
		throw new AssertionError();
	}

	private static boolean hasTemplate(Individual individual, Individual toIndividual, ObjectProperty relationType, OntologyModel metamodel) {
//		set element references
		if(individual != null && toIndividual != null && relationType != null){	
			individual.addProperty(relationType, toIndividual);	
			LOG.trace("Individual: {} connected through: {} with individual: {} successfully: [{}] => [{}]",
					new Object[]{individual, relationType, toIndividual, individual, toIndividual});
			return true;
		} else {
			throw new InformationNotFoundInOntologyException(
					InformationNotFoundInOntologyExceptionCode.INDIVIDUAL_NOT_FOUND, "IndividualA = "+individual+" individualB = "+toIndividual+" relationType = "+relationType);
		}
	}
	
	private static boolean hasTemplate(EnergyEnhancedBimModel fromModel, String fromElementId, Individual toIndividual, 
			ObjectProperty relationType, OntologyModel metamodel) throws LinkException, InformationNotFoundInOntologyException {
		try {			
			EnergyEnhancedBimIndividualFactory fromIndFactory = new EnergyEnhancedBimIndividualFactory(metamodel, fromModel);
			Individual individualA = fromIndFactory.createIndividual(fromElementId);	
			return hasTemplate(individualA, toIndividual, relationType, metamodel);		
		} catch (FactoryException e) {
			throw new LinkException(e.getMessage(), e);
		}
	}
	
	public static void hasMaterial(EnergyEnhancedBimModel fromModel, EnergyEnhancedBimModel toModel, 
			String fromElementId, MaterialTemplate toMaterialTemplate, OntologyModel metamodel) throws LinkException, InformationNotFoundInOntologyException {
		EnergyEnhancedBimIndividualFactory toIndFactory = new EnergyEnhancedBimIndividualFactory(metamodel, toModel);
		try {
			Individual toIndividual = toIndFactory.createIndividual(toMaterialTemplate.getId());
			ObjectProperty hasMaterialProperty = metamodel.getOntologyProperties().get(EeBIMOntoVocabulary.HAS_MATERIAL.getURI());
			hasTemplate(fromModel, fromElementId, toIndividual, hasMaterialProperty, metamodel);
		} catch (FactoryException e) {
			throw new LinkException(e.getMessage(), e);
		}
	}
	
	public static void createAllLinksWithinModel(EnergyEnhancedBimModel eeBimModel, OntologyModel metamodel) throws LinkException, InformationNotFoundInOntologyException {		
		Map<String, ConstructionTemplate> constructions = eeBimModel.getConstructions();
		if(constructions != null) {
			for(Map.Entry<String, ConstructionTemplate> cEntry : constructions.entrySet()) {
				ConstructionTemplate ct = cEntry.getValue();
				for(Map.Entry<Integer, MaterialLayer> matEntry : ct.getMaterialLayers().entrySet()) {
					MaterialTemplate mat = matEntry.getValue().getTemplate();
					if(mat != null) {
//						create hasMaterial Links from Construction to Material
						hasMaterial(eeBimModel, eeBimModel, ct.getId(), mat, metamodel);
					}
				}
			}
		}
	}
}
