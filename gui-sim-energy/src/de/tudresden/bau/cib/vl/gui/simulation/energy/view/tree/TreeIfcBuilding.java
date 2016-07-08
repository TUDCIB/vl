//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tree;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//import jsdai.SIfc2x3.EIfcbuilding;
//import jsdai.SIfc2x3.EIfcbuildingstorey;
//import jsdai.lang.SdaiException;
//import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//
//public class TreeIfcBuilding extends TreeIfcBuildingStorey {
//
//	private ArrayList<TreeIfcBuildingStorey> children;
//	private EIfcbuilding building;
//	
//	
//	public TreeIfcBuilding(String name) {
//		super(name);
//		children = new ArrayList<TreeIfcBuildingStorey>();
//	}
//	
//	public void addChild(TreeIfcBuildingStorey child) {
//		children.add(child);
//		child.setParent(this);
//	}
//	
//	public void removeChild(TreeIfcBuildingStorey child) {
//		children.remove(child);
//		child.setParent(null);
//	}
//	
//	public TreeIfcBuildingStorey [] getChildren() {	
//		if(building == null) {
//			return (TreeIfcBuildingStorey [])children.toArray(new TreeIfcBuildingStorey[children.size()]);
//		}
//		Set<TreeIfcBuildingStorey> treeElementSet = new HashSet<TreeIfcBuildingStorey>();
//		try {					
//			EIfcbuildingstorey[] storeys = controller.getBuildingStoreys(building);
//			for(EIfcbuildingstorey storey : storeys) {
//				String name = Messages.getString("EnergySystemView.TEXT_UNKNOWN"); //$NON-NLS-1$
//				String guid = storey.testGlobalid(storey) ? storey.getGlobalid(storey) : null;
//				if(storey.testName(storey)) {
//					name = storey.getName(storey);
//				}
//				TreeIfcBuildingStorey treeElement = new TreeIfcBuildingStorey(name);
//				treeElement.setIfcElement(storey);
//				treeElementSet.add(treeElement);
//			}
//		} catch(IfcException e) {
//				
//		} catch (SdaiException e) {
//			
//		}
//		return treeElementSet.toArray(new TreeIfcBuildingStorey[treeElementSet.size()]);
//	}
//	
//	public boolean hasChildren() {
//		return children.size()>0;
//	}
//}
