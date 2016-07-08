package de.tudresden.bau.cib.vl.core.model.ontology.individuals;

import java.util.Set;
import java.util.UUID;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EBIMInterfaceOntoVocabulary;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.BIMOntoVocabulary;

public class EnergyEnhancedInterfacesIndividualFactory extends AbstractModelIndividualFactory<Ifc2x3DataModelProxy> {

	
	public EnergyEnhancedInterfacesIndividualFactory(OntologyModel ontModel, Ifc2x3DataModelProxy sourceModel) {
		super(ontModel, sourceModel);
	}

	public void addBuildingAge(Individual owlBuilding, int buildingAge) throws FactoryException, IfcException {
		OntClass ifcRelDefinesByPropertiesClass = BIMOntoVocabulary.IFC_REL_DEFINES_BY_PROPERTIES;
		OntClass ifcPropertySetClass = BIMOntoVocabulary.IFC_PROPERTY_SET;
		OntClass ifcIdentifierClass = BIMOntoVocabulary.IFC_IDENTIFIER;
		OntClass ifcTextClass = BIMOntoVocabulary.IFC_TEXT;
		ObjectProperty relatedObjects = BIMOntoVocabulary.RELATED_OBJECTS;
		ObjectProperty relatingPropertyDefinition = BIMOntoVocabulary.RELATING_PROPERTY_DEFINITION;
		ObjectProperty hasProperties = BIMOntoVocabulary.HAS_PROPERTIES;
		ObjectProperty Name = BIMOntoVocabulary.NAME;
		ObjectProperty Description = BIMOntoVocabulary.DESCRIPTION;
		DatatypeProperty stringValueProperty = BIMOntoVocabulary.STRING_VALUE;
		
		OntClass eeBimPropertyClass = EBIMInterfaceOntoVocabulary.EE_BIMPROPERTY;
		
		Individual owlRelDefinesByProperties = createIndividualByElementId(ifcRelDefinesByPropertiesClass, sourceModel.createGuid());
		ontologyModel.addRelation(owlRelDefinesByProperties, relatedObjects, owlBuilding);
		
		Individual owlPropertySet = createIndividualByElementId(ifcPropertySetClass, sourceModel.createGuid());
		String uri = UUID.randomUUID().toString();
		Individual owlEeBIMProperty = ontologyModel.createIndividual(eeBimPropertyClass, uri);
		
		Individual owlIdentifier = ontologyModel.createIndividual(ifcIdentifierClass, UUID.randomUUID().toString());
		owlIdentifier.addLiteral(stringValueProperty, "BuildingAge");
		
		Individual owlText = ontologyModel.createIndividual(ifcTextClass, UUID.randomUUID().toString());
		owlIdentifier.addLiteral(stringValueProperty, ""+buildingAge);
		
		ontologyModel.addRelation(owlEeBIMProperty, Name, owlIdentifier);	
		ontologyModel.addRelation(owlEeBIMProperty, Description, owlText);
		ontologyModel.addRelation(owlEeBIMProperty, hasProperties, owlEeBIMProperty);		
		ontologyModel.addRelation(owlRelDefinesByProperties, relatingPropertyDefinition, owlPropertySet);
	}
	
	public void addBuildingPersonLoad(Individual owlBuilding, int buildingPersonLoad, Ifc2x3DataModelProxy ifcModel) throws FactoryException, IfcException {
		OntClass ifcRelDefinesByPropertiesClass = BIMOntoVocabulary.IFC_REL_DEFINES_BY_PROPERTIES;
		OntClass ifcPropertySetClass = BIMOntoVocabulary.IFC_PROPERTY_SET;
		OntClass ifcIdentifierClass = BIMOntoVocabulary.IFC_IDENTIFIER;
		OntClass ifcTextClass = BIMOntoVocabulary.IFC_TEXT;
		ObjectProperty relatedObjects = BIMOntoVocabulary.RELATED_OBJECTS;
		ObjectProperty relatingPropertyDefinition = BIMOntoVocabulary.RELATING_PROPERTY_DEFINITION;
		ObjectProperty hasProperties = BIMOntoVocabulary.HAS_PROPERTIES;
		ObjectProperty Name = BIMOntoVocabulary.NAME;
		ObjectProperty Description = BIMOntoVocabulary.DESCRIPTION;
		DatatypeProperty stringValueProperty = BIMOntoVocabulary.STRING_VALUE;
		
		OntClass eeBimPropertyClass = EBIMInterfaceOntoVocabulary.EE_BIMPROPERTY;
		
		Individual owlRelDefinesByProperties = createIndividualByElementId(ifcRelDefinesByPropertiesClass, sourceModel.createGuid());
		ontologyModel.addRelation(owlRelDefinesByProperties, relatedObjects, owlBuilding);
		
		Individual owlPropertySet = createIndividualByElementId(ifcPropertySetClass, sourceModel.createGuid());
		String uri = UUID.randomUUID().toString();
		Individual owlEeBIMProperty = ontologyModel.createIndividual(eeBimPropertyClass, uri);
		
		Individual owlIdentifier = ontologyModel.createIndividual(ifcIdentifierClass, UUID.randomUUID().toString());
		owlIdentifier.addLiteral(stringValueProperty, "BuildingPersonLoad");
		
		Individual owlText = ontologyModel.createIndividual(ifcTextClass, UUID.randomUUID().toString());
		owlIdentifier.addLiteral(stringValueProperty, ""+buildingPersonLoad);
		
		ontologyModel.addRelation(owlEeBIMProperty, Name, owlIdentifier);	
		ontologyModel.addRelation(owlEeBIMProperty, Description, owlText);
		ontologyModel.addRelation(owlEeBIMProperty, hasProperties, owlEeBIMProperty);		
		ontologyModel.addRelation(owlRelDefinesByProperties, relatingPropertyDefinition, owlPropertySet);
	}

	@Override
	public Set<Individual> createAllIndividuals()
			throws FactoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Individual createIndividual(String elementId)
			throws FactoryException {
		// TODO Auto-generated method stub
		return null;
	}
}
