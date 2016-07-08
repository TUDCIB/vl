package de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.BIMOntoVocabulary;

public class MaterialResourceIndividualFactory extends AbstractResourceIndividualFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(MaterialResourceIndividualFactory.class);
	

	public MaterialResourceIndividualFactory(OntologyModel ontModel) {
		super(ontModel);
	}

	public Individual createMaterialIndividual(MaterialTemplate material) 
			throws FactoryException {
		return createIndividual(material, EeBIMOntoVocabulary.MATERIAL.getURI());
	}
	
	public void createMaterialProperties(MaterialTemplate materialTemplate) throws FactoryException {
//		OntClass thermalConductivityClass = ontClassMap.get("http://ontology.cib.bau.tu-dresden/eeBIMInterfaceOnto#ThermalConductivity");		
//		OntClass thermalAbsorptanceClass = ontClassMap.get("http://ontology.cib.bau.tu-dresden/eeBIMInterfaceOnto#ThermalAbsorptance");
//		OntClass resistanceClass = ontClassMap.get("http://ontology.cib.bau.tu-dresden/eeBIMInterfaceOnto#Resistance");
//		OntClass transmittanceClass = ontClassMap.get("http://ontology.cib.bau.tu-dresden/eeBIMInterfaceOnto#Transmittance");
//		OntClass absorptanceClass = ontClassMap.get("http://ontology.cib.bau.tu-dresden/eeBIMInterfaceOnto#Absorptance");		
//		OntClass emittanceClass = ontClassMap.get("http://ontology.cib.bau.tu-dresden/eeBIMInterfaceOnto#Emittance");
//		OntClass reflectanceClass = ontClassMap.get("http://ontology.cib.bau.tu-dresden/eeBIMInterfaceOnto#Reflectance");
//		OntClass rValueClass = ontClassMap.get("http://ontology.cib.bau.tu-dresden/eeBIMInterfaceOnto#R-Value");
//		OntClass solarAbsorptanceClass = ontClassMap.get("http://ontology.cib.bau.tu-dresden/eeBIMInterfaceOnto#SolarAbsorptance");
//		OntClass specificHeatCapacityClass = ontClassMap.get("http://ontology.cib.bau.tu-dresden/eeBIMInterfaceOnto#SpecificHeatCapacity");
//		OntClass visibleAbsorptanceClass = ontClassMap.get("http://ontology.cib.bau.tu-dresden/eeBIMInterfaceOnto#VisibleAbsorptance");
//
//		String tplName = materialTemplate.getName();
//
//		// create lambda
//		double lambda = materialTemplate.getLambda();
//		Individual owlLambda = createIndividual(thermalConductivityClass, materialTemplate.getSourceFileUri()+"_Lambda");
//		DatatypeProperty lambdaFloatValueProperty = datatypePropertiesMap.get(IFC2X3_TC1Vocabulary.FLOAT_VALUE.getURI());
//		if(lambdaFloatValueProperty != null) {
//			owlLambda.addLiteral(lambdaFloatValueProperty, lambda);
//		}
	}

}
