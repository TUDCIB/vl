package de.tudresden.bau.cib.vl.core.model.ontology.individuals;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.Individual;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.EnergyEnhancedBimModel;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource.ClimateResourceIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource.ConstructionResourceIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource.MaterialResourceIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource.OccupancyResourceIndividualFactory;

public class EnergyEnhancedBimIndividualFactory extends AbstractModelIndividualFactory<EnergyEnhancedBimModel> {

	
	private static final Logger LOG = LoggerFactory.getLogger(EnergyEnhancedBimIndividualFactory.class);
	private ConstructionResourceIndividualFactory constructionFactory;
	private MaterialResourceIndividualFactory materialFactory;
	private OccupancyResourceIndividualFactory occupancyFactory;
	private ClimateResourceIndividualFactory climateFactory;
	
	
	public EnergyEnhancedBimIndividualFactory(OntologyModel ontModel, EnergyEnhancedBimModel eeBimModel) {
		super(ontModel, eeBimModel);
		this.climateFactory = new ClimateResourceIndividualFactory(ontModel);
		this.constructionFactory = new ConstructionResourceIndividualFactory(ontModel);
		this.materialFactory = new MaterialResourceIndividualFactory(ontModel);
		this.occupancyFactory = new OccupancyResourceIndividualFactory(ontModel);
	}

	@Override
	public Set<Individual> createAllIndividuals() throws FactoryException {
		LOG.debug("Read eeBim Model and create all individuals");
		Set<Individual> individuals = new HashSet<Individual>();
		Map<String,ClimateLocationTemplate> clm = sourceModel.getClimateModel();
		if(clm != null) {
			ClimateLocationTemplate template = clm.values().iterator().next();
			individuals.add(createIndividual(template.getId()));
		}
		Map<String, ConstructionTemplate> constructionModels = sourceModel.getConstructions();
		if(constructionModels != null) {
			for(Map.Entry<String, ConstructionTemplate> entry : constructionModels.entrySet()) {
				ConstructionTemplate constructionModel = entry.getValue();
				individuals.add(createIndividual(constructionModel.getId()));
			}
		}
		Map<String, SpaceTypeTemplate> spaceTypes = sourceModel.getSpaceTypeTemplates();
		if(spaceTypes != null) {
			for(Map.Entry<String, SpaceTypeTemplate> entry : spaceTypes.entrySet()) {
				SpaceTypeTemplate spaceType = entry.getValue();
				individuals.add(createIndividual(spaceType.getId()));
			}
		}
		return individuals;
	}

	@Override
	public Individual createIndividual(String elementId)
			throws FactoryException {		
		Map<String,ClimateLocationTemplate> climateModel = sourceModel.getClimateModel();	
		if(climateModel != null) {
			ClimateLocationTemplate template = climateModel.values().iterator().next();
			//	ClimateLocation?
			if(template.getId().equals(elementId)) {
				Individual ind = climateFactory.createClimateIndividual(template);
				return ind;
			} 
		}
		//	Constructions? (With Materials)
		Map<String, ConstructionTemplate> constructionForIfcBuildingElementGuid = sourceModel.getConstructions();
		if(constructionForIfcBuildingElementGuid != null) {
			for(Map.Entry<String, ConstructionTemplate> entry : constructionForIfcBuildingElementGuid.entrySet()) { 
				ConstructionTemplate cm = entry.getValue();
				if(cm.getId().equals(elementId)) {
					Individual ind = constructionFactory.createConstructionIndividual(cm);	
					return ind;
				} else {
					for(Map.Entry<Integer, MaterialLayer> matEntry : cm.getMaterialLayers().entrySet()) {
						MaterialTemplate mat = matEntry.getValue().getTemplate();
						if(mat.getId().equals(elementId)) {
							Individual ind = materialFactory.createMaterialIndividual(mat);
							return ind;
						}
					}
				}
			}
		}
		//	SpaceTypes?
		Map<String, SpaceTypeTemplate> spaceTypeForIfcSpaceGuid = sourceModel.getSpaceTypeTemplates();
		if(spaceTypeForIfcSpaceGuid != null) {
			for(Map.Entry<String, SpaceTypeTemplate> entry : spaceTypeForIfcSpaceGuid.entrySet()) { 
				SpaceTypeTemplate stt = entry.getValue();
				if(stt.getId().equals(elementId)) {
					Individual ind = occupancyFactory.createOccupancyIndividual(stt);	
					return ind;
				}
			}
		}
		return null;
	}

}
