package de.tudresden.bau.cib.vl.core.model.eeBim.template;

import java.util.HashMap;
import java.util.Map;

public class ConstructionTemplate extends RemoteResource {
	
	public static final String TYPE_CONSTRUCTION = "Construction";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -154346920075092483L;

	private Map<Integer, MaterialLayer> layers = new HashMap<Integer, MaterialLayer>();
	private Double costsPerSquareMeter	= 0.0;
	private Double uValue;
	private String uValueUnit;


	public Map<Integer, MaterialLayer> getMaterialLayers() {
		return layers;
	}

	public void setMaterialLayers(Map<Integer, MaterialLayer> layers) {
		this.layers = layers;
	}
	
	public void addMaterialLayer(int index, MaterialLayer mm) {
		layers.put(index, mm);
	}
	
	public void removeMaterialLayer(int index) {
		layers.remove(index);
	}
	
	@Override
	public String toString() {
		return TYPE_CONSTRUCTION +"("+super.toString()+" material layers: "+layers+")";
	}

	public Double getCostsPerSquareMeter() {
		return costsPerSquareMeter;
	}

	/**
	 * @param costsPerSquareMeter In EURO
	 */
	public void setCostsPerSquareMeter(Double costsPerSquareMeter) {
		this.costsPerSquareMeter = costsPerSquareMeter;
	}

	public void setUValue(Double uValue) {
		this.uValue = uValue;
	}

	public Double getUValue() {
		return uValue;
	}

	public String getUuValueUnit() {
		return uValueUnit;
	}

	public void setUValueUnit(String uValueUnit) {
		this.uValueUnit = uValueUnit;
	}

}
