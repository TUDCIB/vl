package de.tudresden.bau.cib.vl.core.model.ontology;

import java.util.HashMap;
import java.util.Map;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;

/**
 * TODO JavaDoc
 * @author  <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 */
public class EnergyEnhancedBimModel extends Model {
	
	public static final String NS_EEBIM = "http://openeebim.bau.tu-dresden.de/dev/ontology/eeBIMOnto#";
//	public static final String NS_EEBIM = "http://141.30.143.53/ontology/eeBim#";
	public static final String TYPE_EEBIM = "eeBim";
	
	private Map<String,ClimateLocationTemplate> climateModel;
	private Map<String, ConstructionTemplate> constructionsForIfcBuildingElementGuid;
	private Map<String, SpaceTypeTemplate> spaceTypesForIfcSpaceGuid;


	@Override
	public String getModelType() {
		return TYPE_EEBIM;
	}

	@Override
	public String getNameSpace() {
		return NS_EEBIM;
	}

	/**
	 * TODO javadoc
	 * @return
	 * @uml.property  name="climateModel"
	 */
	public final Map<String,ClimateLocationTemplate> getClimateModel() {
		return climateModel;
	}

	public final void addClimateModel(String ifcSiteGuid, ClimateLocationTemplate climateModel) {
		if(this.climateModel == null) this.climateModel = new HashMap<String, ClimateLocationTemplate>();
		this.climateModel.put(ifcSiteGuid, climateModel);
	}

	public Map<String, ConstructionTemplate> getConstructions() {
		return constructionsForIfcBuildingElementGuid;
	}

	public void addConstructionTemplate(String ifcBuildingElementGuid, ConstructionTemplate cm) {
		if(this.constructionsForIfcBuildingElementGuid == null) this.constructionsForIfcBuildingElementGuid = new HashMap<String, ConstructionTemplate>();
		constructionsForIfcBuildingElementGuid.put(ifcBuildingElementGuid, cm);
	}
	
	public Map<String, SpaceTypeTemplate> getSpaceTypeTemplates() {
		return spaceTypesForIfcSpaceGuid;
	}
	
	public void addSpaceTypeTemplate(String ifcSpaceGuid, SpaceTypeTemplate stt) {
		if(this.spaceTypesForIfcSpaceGuid == null) this.spaceTypesForIfcSpaceGuid = new HashMap<String, SpaceTypeTemplate>();
		spaceTypesForIfcSpaceGuid.put(ifcSpaceGuid, stt);
	}
}
