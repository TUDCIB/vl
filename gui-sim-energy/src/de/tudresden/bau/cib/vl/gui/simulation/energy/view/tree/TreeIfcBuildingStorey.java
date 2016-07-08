//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tree;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//import jsdai.SIfc2x3.EIfcbuildingstorey;
//import jsdai.SIfc2x3.EIfcspace;
//import jsdai.lang.SdaiException;
//import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//
//public class TreeIfcBuildingStorey extends TreeIfcSpace {
//
//	private ArrayList<TreeIfcSpace> children;
//	private EIfcbuildingstorey storey;
//	
//	
//	public TreeIfcBuildingStorey(String name) {
//		super(name);
//		children = new ArrayList<TreeIfcSpace>();
//	}
//	
//	public void addChild(TreeIfcSpace child) {
//		children.add(child);
//		child.setParent(this);
//	}
//	
//	public void removeChild(TreeIfcSpace child) {
//		children.remove(child);
//		child.setParent(null);
//	}
//	
//	public TreeIfcSpace [] getChildren() {	
//		if(storey == null) {
//			return (TreeIfcSpace [])children.toArray(new TreeIfcSpace[children.size()]);
//		}
//		Set<TreeIfcSpace> treeElementSpaceSet = new HashSet<TreeIfcSpace>();
//		try {				
//			EIfcspace[] spacesInStorey = controller.getSpacesInStorey(storey);
//			for(EIfcspace space : spacesInStorey) {
//				String spaceName = Messages.getString("EnergySystemView.TEXT_UNKNOWN"); //$NON-NLS-1$
//				String spaceGuid = storey.testGlobalid(space) ? storey.getGlobalid(space) : null;
//				if(space.testName(space)) {
//					spaceName = space.getName(space);
//				}
//				TreeIfcSpace treeSpaceElement = new TreeIfcSpace(spaceName);
//				treeSpaceElement.setIfcElement(space);
//				treeElementSpaceSet.add(treeSpaceElement);
//			}
//		} catch (SdaiException e) {
//			
//		} catch (IfcException e) {
//
//		}
//		return treeElementSpaceSet.toArray(new TreeIfcSpace[treeElementSpaceSet.size()]);
//	}
//	
//	public boolean hasChildren() {
//		return children.size()>0;
//	}
//}
