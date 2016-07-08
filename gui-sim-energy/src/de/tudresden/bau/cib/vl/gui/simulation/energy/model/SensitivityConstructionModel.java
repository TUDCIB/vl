package de.tudresden.bau.cib.vl.gui.simulation.energy.model;

import java.util.Map;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;

public class SensitivityConstructionModel extends SensitivityModel {

	private Map<Integer, MaterialLayer> layers;
	private int selectedMaterialIndex;

	public Map<Integer, MaterialLayer> getLayers() {
		return layers;
	}

	public void setLayers(Map<Integer, MaterialLayer> layers) {
		this.layers = layers;
	}

	public int getSelectedMaterialIndex() {
		return selectedMaterialIndex;
	}

	public void setSelectedMaterialIndex(int selectedMaterialIndex) {
		this.selectedMaterialIndex = selectedMaterialIndex;
	}

}
