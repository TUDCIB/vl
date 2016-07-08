//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tree;
//
//import jsdai.SIfc2x3.EIfcroot;
//
//import org.eclipse.core.runtime.IAdaptable;
//
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.EnergySystemController;
//
//public class TreeIfcBuildingElement implements IAdaptable {
//
//	private String name;
//	private TreeIfcSpace parent;
//	private EIfcroot ifcElement;
//	protected EnergySystemController controller = EnergySystemController.getInstance();
//	
//	
//	public TreeIfcBuildingElement(String name) {
//		this.name = name;
//	}
//	
//	public String getName() {
//		return name;
//	}
//	
//	public void setParent(TreeIfcSpace parent) {
//		this.parent = parent;
//	}
//	
//	public TreeIfcSpace getParent() {
//		return parent;
//	}
//	
//	public String toString() {
//		return getName();
//	}
//	
//	public Object getAdapter(Class key) {
//		return null;
//	}
//
//	public EIfcroot getIfcElement() {
//		return ifcElement;
//	}
//
//	public void setIfcElement(EIfcroot ifcElement) {
//		this.ifcElement = ifcElement;
//	}
//}
