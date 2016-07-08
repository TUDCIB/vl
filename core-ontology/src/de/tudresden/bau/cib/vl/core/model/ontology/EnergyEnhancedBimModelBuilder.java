package de.tudresden.bau.cib.vl.core.model.ontology;

import java.util.List;
import java.util.Map;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.sparql.EeBimQueryExecutor;
import de.tudresden.bau.cib.vl.core.model.ontology.sparql.EeBimQueryExecutor.VARIABLES;



/**
 * This class constructs the eeBim model based on ontology individuals. 16.12.2013: Currently it is possible to construct the model with climate templates, construction templates and space type templates. Implementation notes: The builder design pattern is applied.
 * @author  <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 */
public class EnergyEnhancedBimModelBuilder {	

	/**
	 * @uml.property  name="eeBim"
	 * @uml.associationEnd  
	 */
	private EnergyEnhancedBimModel eeBim;
	/**
	 * @uml.property  name="ontModel"
	 * @uml.associationEnd  
	 */
	private OntologyModel ontModel;
	
	
	public EnergyEnhancedBimModelBuilder(OntologyModel model) {
		this.ontModel = model;
	}
	
	public EnergyEnhancedBimModelBuilder build() {
		eeBim = new EnergyEnhancedBimModel();	
		eeBim.setId(ontModel.getId());
		return this;
	}
	
	/**
	 * Appends climate information to the model.
	 * @return Returns the eeBim with the climate information.
	 */
	public EnergyEnhancedBimModelBuilder withClimateInformation() {
		List<Map<String, String>> results = EeBimQueryExecutor.findClimateLocation(null, ontModel);
		if(results != null) {
			for(Map<String,String> result : results) {
				ClimateLocationTemplate template = new ClimateLocationTemplate();
//				String uri = result.get(VARIABLES.x);
//				String id = LinkModel.getElementIdentifierFromUri(uri);
//				template.setId(id);
				String ifcSiteGuid = result.get(VARIABLES.guid);
				template.setLatitude(Float.valueOf(result.get(VARIABLES.latitude)));
				template.setLongitude(Float.valueOf(result.get(VARIABLES.longitude)));
				template.setRegion(result.get(VARIABLES.region));
				template.setSourceFileUri(result.get(VARIABLES.tplPath));
				eeBim.addClimateModel(ifcSiteGuid, template);
			}
		}
		return this;
	}
	
	/**
	 * Appends construction information to the model.
	 * @return Returns the eeBim with the construction information.
	 */
	public EnergyEnhancedBimModelBuilder withConstructionTemplatesInformation() {
		List<Map<String, String>> results = EeBimQueryExecutor.findConstructions(ontModel);
		if(results != null) {
			for(Map<String,String> result : results) {
				ConstructionTemplate template = new ConstructionTemplate();
				template.setName(result.get(VARIABLES.name));
				template.setSourceFileUri(result.get(VARIABLES.tplPath));
				String beGuid = result.get(VARIABLES.guid);
//				String uri = result.get(VARIABLES.x);
//				template.setId(LinkModel.getElementIdentifierFromUri(uri));
			
//				List<Map<VARIABLES, String>> matResults = QueryExecutor.findMaterialTemplatesRelatedToBuildingElement(beGuid, ontModel);
//				for(Map<VARIABLES,String> mResult : matResults) {
//					MaterialTemplate mtpl = new MaterialTemplate();
//					mtpl.setName(mResult.get(VARIABLES.name));
//					mtpl.setSourceFilePath(mResult.get(VARIABLES.tplPath));
//				}
				eeBim.addConstructionTemplate(beGuid, template);
			}
		}
		return this;
	}
	
//	public EnergyEnhancedBimModelBuilder withConstructionTemplatesInformation(String buildingElementGuid) {
//		List<Map<VARIABLES, String>> results = QueryExecutor.findConstructionTemplate(buildingElementGuid, ontModel);
//		if(results != null) {
//			for(Map<VARIABLES,String> result : results) {
//				ConstructionTemplate template = new ConstructionTemplate();
//				template.setName(result.get(VARIABLES.name));
//				template.setSourceFilePath(result.get(VARIABLES.tplPath));
//				String beGuid = result.get(VARIABLES.guid);
//				String buildingElementUri = result.get(VARIABLES.x);
//				System.err.println(template);
//				System.err.println(buildingElementUri+" =? "+buildingElementGuid);
////				template.setId(LinkModel.getElementIdentifierFromUri(uri));
//			
////				List<Map<VARIABLES, String>> matResults = QueryExecutor.findMaterialTemplatesRelatedToBuildingElement(beGuid, ontModel);
////				for(Map<VARIABLES,String> mResult : matResults) {
////					MaterialTemplate mtpl = new MaterialTemplate();
////					mtpl.setName(mResult.get(VARIABLES.name));
////					mtpl.setSourceFilePath(mResult.get(VARIABLES.tplPath));
////				}
//				eeBim.addConstructionTemplate(beGuid, template);
//			}
//		}
//		return this;
//	}
	
	/**
	 * Appends occupancy information to the model.
	 * @return Returns the eeBim with the space type information.
	 */
	public EnergyEnhancedBimModelBuilder withSpaceTypeTemplatesInformation() {
		List<Map<String, String>> roomResults = EeBimQueryExecutor.findRooms(ontModel);
		if(roomResults != null) {
			for(Map<String,String> result : roomResults) {
				String roomGuid = result.get(VARIABLES.guid);
				List<Map<String, String>> results = EeBimQueryExecutor.findSpaceTypeTemplate(roomGuid, ontModel);
				for(Map<String,String> res : results) {
					SpaceTypeTemplate template = new SpaceTypeTemplate();
					template.setSourceFileUri(res.get(VARIABLES.tplPath));
					template.setName(res.get(VARIABLES.name));
					eeBim.addSpaceTypeTemplate(roomGuid, template);
//					String uri = result.get(VARIABLES.x);
//					template.setId(LinkModel.getElementIdentifierFromUri(uri));
				}
			}
		}
		return this;
	}

	public EnergyEnhancedBimModel get() {
		return eeBim;
	}
}
