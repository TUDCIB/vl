package de.tudresden.bau.cib.vl.core.model.eeBim.template;

public class MaterialLayer {
	private float thickness;
	private String unit;
	private MaterialTemplate template;

	public float getThickness() {
		return thickness;
	}

	public String getUnit() {
		return unit;
	}

	public void setThickness(float thickness) {
		this.thickness = thickness;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public MaterialTemplate getTemplate() {
		return template;
	}

	public void setTemplate(MaterialTemplate template) {
		this.template = template;
	}
	
	@Override
	public String toString() {
		return "thickness: "+thickness+", unit: "+unit+", "+template.toString();
	}
}
