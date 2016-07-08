package de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles;

import java.util.HashMap;
import java.util.Map;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.Material;

public class TheraklesConstruction extends ConstructionTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8163697026676784481L;
	/**
	 * Map for assigned materials and their thickness.
	 */
	private Map<Material, Double> materials;
	
	public enum TYPE_ENUM {
		Inside, Outside, FixedTemperature
	}
	/**
	 * TODO eventually a reference to a construction type class
	 */
	private int constructionTypeId = 1;
	private TYPE_ENUM type;
	private String ifcBuildingElementId;
	private String ifcSpaceId;
	private String ifcBuildingStoreyId;
	private String ifcBuildingId;
	private String buildingId;
	private double orientation;
	private double inclination;
	private double R_ue;
	private double R_ui;
	private double ad;
	private int zoneT;
	private double AW;
	private double AF;
	private TheraklesWindow window;
	private int shadingTypeId;
	
	
	public TheraklesConstruction(String ifcBuildingElementId) {
		this.ifcBuildingElementId = ifcBuildingElementId;
		materials = new HashMap<Material, Double>();
	}
	
	public Map<Material, Double> getMaterials() {
		return materials;
	}
	
	public void setMaterials(Map<Material, Double> newMaterials) {
		this.materials = new HashMap<Material, Double>(newMaterials);
	}
	
	public void addMaterial(Material m, Double thickness) {
		if(m != null) {
			this.materials.put(m, thickness);
		}
	}
	
	public void removeMaterial(Material m) {
		if(m != null) {
			materials.remove(m);
		}
	}
	
	public TYPE_ENUM getType() {
		return type;
	}
	public void setType(TYPE_ENUM type) {
		this.type = type;
	}

	public double getOrientation() {
		return orientation;
	}

	public void setOrientation(double orientation) {
		this.orientation = convertRadiansToDegrees(orientation);
	}

	public double getInclination() {
		return inclination;
	}

	public void setInclination(double inclination) {
		this.inclination = inclination;
	}

	public double getR_ue() {
		return R_ue;
	}

	public void setR_ue(double r_ue) {
		R_ue = r_ue;
	}

	public double getAd() {
		return ad;
	}

	public void setAd(double ad) {
		this.ad = ad;
	}

	public int getZoneT() {
		return zoneT;
	}

	public void setZoneT(int zoneT) {
		this.zoneT = zoneT;
	}

	public double getAW() {
		return AW;
	}

	public void setAW(double aW) {
		AW = aW;
	}

	public double getAF() {
		return AF;
	}

	public void setAF(double aF) {
		AF = aF;
	}

	public int getShadingTypeId() {
		return shadingTypeId;
	}

	public void setShadingTypeId(int shadingTypeId) {
		this.shadingTypeId = shadingTypeId;
	}

	public double getR_ui() {
		return R_ui;
	}

	public void setR_ui(double r_ui) {
		R_ui = r_ui;
	}

	public int getConstructionTypeId() {
		return constructionTypeId;
	}
	
	public void setConstructionTypeId(int constructionTypeId) {
		this.constructionTypeId = constructionTypeId;
	}

	@Override
	public String toString() {
		return "ConstructionTypeId: "+constructionTypeId+" Name: "+name+" Orientation: "+orientation+" IfcElement: "+ifcBuildingElementId;
	}

	public TheraklesWindow getWindow() {
		return window;
	}

	public void setWindow(TheraklesWindow window) {
		this.window = window;
	}
	
    private double convertRadiansToDegrees(double radians) {
    	double degrees = (180 / Math.PI) * radians;
    	return Math.round(degrees); // its better to round it
    }
    
    @Override
    public int hashCode() {
    	return (constructionTypeId+name.hashCode());
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof TheraklesConstruction) {
    		TheraklesConstruction other = (TheraklesConstruction)obj;
    		return other.hashCode() == this.hashCode();
    	}
    	return super.equals(obj);
    }
    
	public String getIfcBuildingElementId() {
		return ifcBuildingElementId;
	}

	public void setIfcBuildingElementId(String ifcBuildingElementId) {
		this.ifcBuildingElementId = ifcBuildingElementId;
	}

	public String getIfcSpaceId() {
		return ifcSpaceId;
	}

	public void setIfcSpaceId(String ifcSpaceId) {
		this.ifcSpaceId = ifcSpaceId;
	}

	public String getIfcBuildingStoreyId() {
		return ifcBuildingStoreyId;
	}

	public void setIfcBuildingStoreyId(String ifcBuildingStoreyId) {
		this.ifcBuildingStoreyId = ifcBuildingStoreyId;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getIfcBuildingId() {
		return ifcBuildingId;
	}

	public void setIfcBuildingId(String ifcBuildingId) {
		this.ifcBuildingId = ifcBuildingId;
	}

}
