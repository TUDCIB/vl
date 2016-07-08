package de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource;

import java.util.Map;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;

public class ConstructionResourceIndividualFactory extends AbstractResourceIndividualFactory {
	
	private MaterialResourceIndividualFactory materialFactory;

	public ConstructionResourceIndividualFactory(OntologyModel ontModel) {
		super(ontModel);
		this.materialFactory = new MaterialResourceIndividualFactory(ontModel);
	}

	public Individual createConstructionIndividual(ConstructionTemplate construction) 
			throws FactoryException {
		Individual ind = createIndividual(construction, EeBIMOntoVocabulary.CONSTRUCTION.getURI());
		
		// add u-Value
		if(construction.getUValue() != null) {
			DatatypeProperty uValueProperty = EeBIMOntoVocabulary.U_VALUE;
			if(uValueProperty != null) {
				ind.addLiteral(uValueProperty, construction.getUValue());
			}
		}
//		add attributes
		Map<Integer, MaterialLayer> materialLayers = construction.getMaterialLayers();
		if(materialLayers != null) {
			ObjectProperty hasMaterialProperty = EeBIMOntoVocabulary.HAS_MATERIAL;
			if(hasMaterialProperty != null) {
				for(Map.Entry<Integer, MaterialLayer> matEntry : materialLayers.entrySet()) {
					MaterialTemplate mat = matEntry.getValue().getTemplate();
					if(mat != null) {
						Individual matInd = materialFactory.createMaterialIndividual(mat);
						ind.addProperty(hasMaterialProperty, matInd);
					}
				}
			}
		}
		return ind;
	}
}
