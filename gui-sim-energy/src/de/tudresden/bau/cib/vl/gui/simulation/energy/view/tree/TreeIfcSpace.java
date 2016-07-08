//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tree;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//import jsdai.SIfc2x3.EIfcbuildingelement;
//import jsdai.SIfc2x3.EIfcspace;
//import jsdai.lang.SdaiException;
//import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//
//
//public class TreeIfcSpace extends TreeIfcBuildingElement {
//
//	private ArrayList<TreeIfcBuildingElement> children;
//	private EIfcspace space;
//	
//	
//	public TreeIfcSpace(String name) {
//		super(name);
//		children = new ArrayList<TreeIfcBuildingElement>();
//	}
//	
//	public void addChild(TreeIfcBuildingElement child) {
//		children.add(child);
//		child.setParent(this);
//	}
//	
//	public void removeChild(TreeIfcBuildingElement child) {
//		children.remove(child);
//		child.setParent(null);
//	}
//	
//	public TreeIfcBuildingElement [] getChildren() {	
//		if(space == null) {
//			return (TreeIfcBuildingElement [])children.toArray(new TreeIfcBuildingElement[children.size()]);
//		}
//		Set<TreeIfcBuildingElement> treeElementSet = new HashSet<TreeIfcBuildingElement>();
//		try {				
//			Set<EIfcbuildingelement> elements = controller.getBuildingElementsOfSpace(space);
//			for(EIfcbuildingelement element : elements) {
//				String name = Messages.getString("EnergySystemView.TEXT_UNKNOWN"); //$NON-NLS-1$
//				String guid = element.testGlobalid(space) ? element.getGlobalid(element) : null;
//				if(element.testName(element)) {
//					name = element.getName(element);
//				}
//				TreeIfcBuildingElement treeElement = new TreeIfcBuildingElement(name);
//				treeElement.setIfcElement(element);
//				treeElementSet.add(treeElement);
//			}
//		} catch (SdaiException e) {
//			
//		} catch (IfcException e) {
//
//		}
//		return treeElementSet.toArray(new TreeIfcBuildingElement[treeElementSet.size()]);
//	}
//	
//	public boolean hasChildren() {
//		return children.size()>0;
//	}
//
//}
