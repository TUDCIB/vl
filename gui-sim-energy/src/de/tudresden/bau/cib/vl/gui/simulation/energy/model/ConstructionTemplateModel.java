//package de.tudresden.bau.cib.vl.gui.simulation.energy.model;
//
//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
//import java.util.HashMap;
//import java.util.Map;
//
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.TemplateViewController;
//
///**
// * Construction template for databinding.
// *
// * @author Ken Baumgaertel
// *	{@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
// *
// */
//public class ConstructionTemplateModel {
//	
//	private String name									=	"";
//	private Double uValue								=	null;
//	private Double costsPerSquareMeter					= 	null;
//	private Map<Integer, MaterialLayer> materialLayers	=	null;
//	private TemplateViewController controller;
//	
//	
//	public ConstructionTemplateModel(ConstructionTemplate ct, TemplateViewController controller) {
//		this.controller = controller;
//		if(ct != null) {
//			setName(ct.getName());
//			this.materialLayers = ct.getMaterialLayers();
//			Double uValue = controller.calculateUValue(this);
//			setuValue(uValue);
//			Double costs = ct.getCostsPerSquareMeter();
//			setCostsPerSquareMeter(costs);
//		}
//	}
//	
//	public void addMaterialLayer(int index, MaterialLayer layer) {
//		if(this.materialLayers == null) this.materialLayers = new HashMap<Integer, MaterialLayer>();
//		this.materialLayers.put(index, layer);
//		if(layer.getThickness() != 0.0f && layer.getTemplate() != null) {
//			Double uValue = controller.calculateUValue(this);
//			if(uValue != null)	setuValue(uValue);
//		}
//	}
//	
//	public void removeMaterialLayer(int index) {
//		if(materialLayers != null) {
//			MaterialLayer layer = materialLayers.remove(index);
//			if(layer != null && layer.getThickness() != 0.0f && layer.getTemplate() != null) {
//				Double uValue = controller.calculateUValue(this);
//				if(uValue != null)	setuValue(uValue);
//			}
//		}
//	}
//	
//	public MaterialLayer getMaterialLayer(int index) {
//		if(materialLayers != null) {
//			return materialLayers.get(index);
//		}
//		return null;
//	}
//	
//	public Map<Integer, MaterialLayer> getMaterialLayers() {
//		return materialLayers;
//	}
//	
//	public void setMaterialLayer(Integer index, MaterialLayer layer) {
//		if(this.materialLayers == null) this.materialLayers = new HashMap<Integer, MaterialLayer>();
//		this.materialLayers.put(index, layer);
//		if(layer.getThickness() != 0.0f && layer.getTemplate() != null) {
//			Double uValue = controller.calculateUValue(this);
//			if(uValue != null)	setuValue(uValue);
//		}
//	}
//
//	public void setMaterialLayers(Map<Integer, MaterialLayer> materialLayers) {
//		firePropertyChange("materialLayers", this.materialLayers, this.materialLayers = materialLayers);
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		firePropertyChange("name", this.name, this.name = name);
//	}
//
//	public Double getCostsPerSquareMeter() {
//		return costsPerSquareMeter;
//	}
//
//	public void setCostsPerSquareMeter(Double costsPerSquareMeter) {
//		if(costsPerSquareMeter != null) {
//			firePropertyChange("costsPerSquareMeter", this.costsPerSquareMeter, this.costsPerSquareMeter = costsPerSquareMeter);
//		}
//	}
//
//	public Double getuValue() {
//		return uValue;
//	}
//
//	public void setuValue(Double uValue) {
//		if(uValue != null) {
//			firePropertyChange("uValue", this.uValue, this.uValue = uValue);
//		}
//	}
//
//	/**
//	 * Important for Databinding
//	 */
//	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
//
//	/**
//	 * Important for Databinding
//	 */
//	public void addPropertyChangeListener(PropertyChangeListener 
//			listener) {
//		changeSupport.addPropertyChangeListener(listener);
//	}
//
//	/**
//	 * Important for Databinding
//	 */
//	public void removePropertyChangeListener(PropertyChangeListener 
//			listener) {
//		changeSupport.removePropertyChangeListener(listener);
//	}
//
//	/**
//	 * Important for Databinding
//	 */
//	public void addPropertyChangeListener(String propertyName,
//			PropertyChangeListener listener) {
//		changeSupport.addPropertyChangeListener(propertyName, listener);
//	}
//
//	/**
//	 * Important for Databinding
//	 */
//	public void removePropertyChangeListener(String propertyName,
//			PropertyChangeListener listener) {
//		changeSupport.removePropertyChangeListener(propertyName, listener);
//	}
//
//	/**
//	 * Important for Databinding
//	 */
//	protected void firePropertyChange(String propertyName, 
//			Object oldValue,
//			Object newValue) {
//		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
//	}
//}
