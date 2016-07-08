package de.tudresden.bau.cib.vl.core.model.ontology.individuals.linking;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.EnergyEnhancedBimModel;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.InformationNotFoundInOntologyException;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.InformationNotFoundInOntologyExceptionCode;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.LinkException;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.EnergyEnhancedBimIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.IfcIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.sparql.EeBimQueryExecutor;
import de.tudresden.bau.cib.vl.core.model.ontology.sparql.EeBimQueryExecutor.VARIABLES;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;

/**
 * TODO JavaDoc
 * TODO implement deletion of no connected individuals
 *
 * @author Ken Baumgaertel
 *	{@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public final class Ifc2ResourceLinker {
	
	private static final Logger LOG = LoggerFactory.getLogger(Ifc2ResourceLinker.class);
	
    /**
     *  private default constructor ==> can't be instantiated
     *  side effect: class is final because it can't be subclassed:
     *  super() can't be called from subclasses
     *  Taken from the book "Effective Java"
     */
    private Ifc2ResourceLinker() {
        throw new AssertionError();
    }
	
	private static boolean hasResource(Ifc2x3DataModelProxy fromModel, String fromElementId, Individual toIndividual, 
			ObjectProperty relationType, OntologyModel metamodel) throws LinkException, InformationNotFoundInOntologyException {
		try {			
			IfcIndividualFactory fromIndFactory = new IfcIndividualFactory(metamodel, fromModel, true);
			Individual individualA = fromIndFactory.createIndividual(fromElementId);	
			return hasResource(individualA, toIndividual, relationType, metamodel);
		} catch (FactoryException e) {
			throw new LinkException(e.getMessage(), e);
		}
	}
	
	private static boolean hasResource(Individual individual, Individual toIndividual, ObjectProperty relationType, OntologyModel metamodel) {
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
	
	private static void removeHasResource(Model fromModel,
			String fromElementId, String toUri,
			ObjectProperty relationType, OntologyModel metamodel,
			boolean deleteFromIndividual, boolean deleteToIndividual) throws InformationNotFoundInOntologyException {
		String fromUri = OntologyModel.getUniformResourceIdentifierOfElementInModel(fromModel, fromElementId);
		Individual individualA = metamodel.getIndividualByURI(fromUri);
		Individual individualB = metamodel.getIndividualByURI(toUri);
//		remove element references
		if(individualA != null && individualB != null && relationType != null){	
			individualA.removeProperty(relationType, individualB);	
			LOG.trace("Element: {} from model: {} connected through: {} with: {} was removed successfully: [{}] => [{}]",
					new Object[]{fromElementId, fromModel.getId(), relationType, toUri, individualA, individualB});
//			TODO implement deletion if their are no other references to the individual
		} else {
			throw new InformationNotFoundInOntologyException(
					InformationNotFoundInOntologyExceptionCode.INDIVIDUAL_NOT_FOUND, "IndividualA = "+individualA+" individualB = "+individualB+" relationType = "+relationType);	
		}
	}
	
	private static void removeHasTemplate(Model fromModel, Model toModel, String fromElementId, Resource toTemplate, 
			ObjectProperty relationType, OntologyModel metamodel, boolean deleteFromIndividual, boolean deleteToIndividual) throws InformationNotFoundInOntologyException {
		String toElementId = toTemplate.getId();
		String toUri = OntologyModel.getUniformResourceIdentifierOfElementInModel(toModel, toElementId);
		
		removeHasResource(fromModel, fromElementId, toUri, relationType, metamodel, deleteFromIndividual, deleteToIndividual);
	}
	
	public static void hasClimateLocation(Ifc2x3DataModelProxy fromModel, EnergyEnhancedBimModel toModel, String fromElementId, 
			ClimateLocationTemplate toClimateTemplate, OntologyModel metamodel) throws LinkException, InformationNotFoundInOntologyException {
		EnergyEnhancedBimIndividualFactory toIndFactory = new EnergyEnhancedBimIndividualFactory(metamodel, toModel);
		try {
			Individual toIndividual = toIndFactory.createIndividual(toClimateTemplate.getId());
			ObjectProperty hasClimateProperty = metamodel.getOntologyProperties().get(EeBIMOntoVocabulary.HAS_CLIMATE_LOCATION.getURI());
			hasResource(fromModel, fromElementId, toIndividual, hasClimateProperty, metamodel);
		} catch (FactoryException e) {
			throw new LinkException(e.getMessage(), e);
		}
	}
	
	/**
	 * Removes the link from one element to the climate template.
	 * @param fromModel				The model where the from element is stored.
	 * @param toModel				The eeBim
	 * @param fromElementId			From element
	 * @param toClimateTemplate		Climate Template
	 * @param metamodel				Ontology Model
	 * @param deleteFromIndividual	True: Delete if there are no other links to this individual.
	 * @param deleteToIndividual	True: Delete if there are no other links to this individual. 
	 * @throws LinkException
	 */
	public static void removeClimateLocation(Model fromModel, EnergyEnhancedBimModel toModel, 
			String fromElementId, ClimateLocationTemplate toClimateTemplate, OntologyModel metamodel, 
			boolean deleteFromIndividual, boolean deleteToIndividual) throws InformationNotFoundInOntologyException {
		ObjectProperty hasClimateProperty = metamodel.getOntologyProperties().get(EeBIMOntoVocabulary.HAS_CLIMATE_LOCATION.getURI());
		removeHasTemplate(fromModel, toModel, fromElementId, toClimateTemplate, hasClimateProperty, metamodel, deleteFromIndividual, deleteToIndividual);
	}
	
	public static void removeConstructionTemplate(Model fromModel, EnergyEnhancedBimModel toModel, 
			String fromElementId, ConstructionTemplate toConstructionTemplate, OntologyModel metamodel, 
			boolean deleteFromIndividual, boolean deleteToIndividual) throws InformationNotFoundInOntologyException {
		ObjectProperty hasConstructionProperty = metamodel.getOntologyProperties().get(EeBIMOntoVocabulary.HAS_CONSTRUCTION.getURI());
		removeHasTemplate(fromModel, toModel, fromElementId, toConstructionTemplate, hasConstructionProperty, metamodel, deleteFromIndividual, deleteToIndividual);
	}
	
	public static void removeSpaceTypeTemplate(Model fromModel, EnergyEnhancedBimModel toModel, 
			String fromElementId, OntologyModel metamodel, 
			boolean deleteFromIndividual, boolean deleteToIndividual) throws InformationNotFoundInOntologyException {
		ObjectProperty hasSpaceTypeProperty = metamodel.getOntologyProperties().get(EeBIMOntoVocabulary.HAS_SPACE_TYPE.getURI());
		List<Map<String, String>> resultList = EeBimQueryExecutor.findSpaceTypeTemplate(fromElementId, metamodel);
		if(resultList != null) {
			for(Map<String, String> result : resultList) { // actually there should only be one element
				String toUri = result.get(VARIABLES.spaceType);		
				removeHasResource(fromModel, fromElementId, toUri, hasSpaceTypeProperty, metamodel, deleteFromIndividual, deleteToIndividual);
			}
		}		
	}

	public static void removeSpaceTypeTemplate(Ifc2x3DataModelProxy fromModel, EnergyEnhancedBimModel toModel, 
			String fromElementId, SpaceTypeTemplate toSpaceTypeTemplate, OntologyModel metamodel, 
			boolean deleteFromIndividual, boolean deleteToIndividual) throws InformationNotFoundInOntologyException {
		ObjectProperty hasSpaceTypeProperty = metamodel.getOntologyProperties().get(EeBIMOntoVocabulary.HAS_SPACE_TYPE.getURI());
		removeHasTemplate(fromModel, toModel, fromElementId, toSpaceTypeTemplate, hasSpaceTypeProperty, metamodel, deleteFromIndividual, deleteToIndividual);
	}
	
	public static boolean hasConstruction(Ifc2x3DataModelProxy fromModel, EnergyEnhancedBimModel toModel, 
			String fromElementId, ConstructionTemplate toConstructionTemplate, OntologyModel metamodel) throws LinkException, InformationNotFoundInOntologyException {
		EnergyEnhancedBimIndividualFactory toIndFactory = new EnergyEnhancedBimIndividualFactory(metamodel, toModel);
		try {
			Individual toIndividual = toIndFactory.createIndividual(toConstructionTemplate.getId());
			ObjectProperty hasConstructionProperty = metamodel.getOntologyProperties().get(EeBIMOntoVocabulary.HAS_CONSTRUCTION.getURI());
			return hasResource(fromModel, fromElementId, toIndividual, hasConstructionProperty, metamodel);
		} catch (FactoryException e) {
			throw new LinkException(e.getMessage(), e);
		}
	}
	

	
	public static void hasSpaceType(Ifc2x3DataModelProxy fromModel, EnergyEnhancedBimModel toModel, 
			String fromElementId, SpaceTypeTemplate toSpaceTypeTemplate, OntologyModel metamodel) throws LinkException, InformationNotFoundInOntologyException {
		EnergyEnhancedBimIndividualFactory toIndFactory = new EnergyEnhancedBimIndividualFactory(metamodel, toModel);
		try {
			Individual toIndividual = toIndFactory.createIndividual(toSpaceTypeTemplate.getId());
			ObjectProperty hasSpaceTypeProperty = metamodel.getOntologyProperties().get(EeBIMOntoVocabulary.HAS_SPACE_TYPE.getURI());
			hasResource(fromModel, fromElementId, toIndividual, hasSpaceTypeProperty, metamodel);
		} catch (FactoryException e) {
			throw new LinkException(e.getMessage(), e);
		}
	}
}
