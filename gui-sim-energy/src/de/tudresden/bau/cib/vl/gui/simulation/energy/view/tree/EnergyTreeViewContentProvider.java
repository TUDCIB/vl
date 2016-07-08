//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tree;
//
//import java.util.Map;
//import java.util.Set;
//
//import jsdai.SIfc2x3.EIfcbuilding;
//import jsdai.SIfc2x3.EIfcbuildingelement;
//import jsdai.SIfc2x3.EIfcbuildingstorey;
//import jsdai.SIfc2x3.EIfcspace;
//import jsdai.lang.SdaiException;
//
//import org.eclipse.jface.viewers.IStructuredContentProvider;
//import org.eclipse.jface.viewers.ITreeContentProvider;
//import org.eclipse.jface.viewers.Viewer;
//
//import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.EnergySystemController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//
//public class EnergyTreeViewContentProvider implements
//		IStructuredContentProvider, ITreeContentProvider {
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 8879056262510117907L;
//
//	private EnergySystemController controller = EnergySystemController.getInstance();
//
//	private TreeIfcBuilding invisibleRoot;
//
//	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
//		
//	}
//	
//	public void dispose() {
//
//	}
//	
//	public Object[] getElements(Object parent) {
//		if (parent == null) {
//			if (invisibleRoot == null) {
//				initialize();
//			}
//			return getChildren(invisibleRoot);
//			
//		} else if(parent instanceof Map) {
//			Map<String, EIfcbuilding> buildingMap = (Map<String, EIfcbuilding>) parent;
//			invisibleRoot = new TreeIfcBuilding("");					 //$NON-NLS-1$
//			try {
//				for(Map.Entry<String, EIfcbuilding> buildingEntry : buildingMap.entrySet()) {
//					EIfcbuilding building = buildingEntry.getValue();
//					String bName = Messages.getString("EnergySystemView.TEXT_UNKNOWN"); //$NON-NLS-1$
//					if(building.testName(building)) {
//						bName = building.getName(building);
//					}
//						
//					TreeIfcBuilding treeBuilding = new TreeIfcBuilding(bName);
//					treeBuilding.setIfcElement(building);
//					invisibleRoot.addChild(treeBuilding);
//					for(EIfcbuildingstorey storey : controller.getBuildingStoreys(buildingEntry.getValue())) {
//						String stName = Messages.getString("EnergySystemView.TEXT_UNKNOWN"); //$NON-NLS-1$
//						if(storey.testName(storey)) {
//							stName = storey.getName(storey);
//						}
//							
//						TreeIfcBuildingStorey treeStoreyElement = new TreeIfcBuildingStorey(stName);
//						treeStoreyElement.setIfcElement(storey);
//						treeBuilding.addChild(treeStoreyElement);
//							
//						EIfcspace[] spacesInStorey = controller.getSpacesInStorey(storey);
//						for(EIfcspace space : spacesInStorey) {
//							String spaceName = Messages.getString("EnergySystemView.TEXT_UNKNOWN"); //$NON-NLS-1$
//							if(space.testLongname(space)) {
//								spaceName = space.getLongname(space);
//							} else {
//								spaceName = space.getName(space);
//							}
//							TreeIfcSpace treeSpaceElement = new TreeIfcSpace(spaceName);
//							treeSpaceElement.setIfcElement(space);
//							treeStoreyElement.addChild(treeSpaceElement);
//							
//							Set<EIfcbuildingelement> elementsInZone = controller.getBuildingElementsOfSpace(space);
//							for(EIfcbuildingelement element : elementsInZone) {
//								String elementName = Messages.getString("EnergySystemView.TEXT_UNKNOWN"); //$NON-NLS-1$
//								if(element.testName(element)) {
//									elementName = element.getName(element);
//								}
//								TreeIfcBuildingElement treeElement = new TreeIfcBuildingElement(elementName);
//								treeElement.setIfcElement(element);
//								treeSpaceElement.addChild(treeElement);
//							}
//						}
//					}
//				}
//			} catch(SdaiException e) {
//				e.printStackTrace();
//			} catch (IfcException e) {
//				e.printStackTrace();
//			}
//		}
//		return getChildren(invisibleRoot);
//	}
//	
//	public Object getParent(Object child) {
//		if (child instanceof TreeIfcBuildingElement) {
//			return ((TreeIfcBuildingElement)child).getParent();
//		}
//		return null;
//	}
//	
//	public Object [] getChildren(Object parent) {
//		if (parent instanceof TreeIfcSpace) {
//			return ((TreeIfcSpace)parent).getChildren();
//		}
//		return new Object[0];
//	}
//	
//	public boolean hasChildren(Object parent) {
//		if (parent instanceof TreeIfcSpace)
//			return ((TreeIfcSpace)parent).hasChildren();
//		return false;
//	}
//
//	private void initialize() {
//		TreeIfcBuilding root = new TreeIfcBuilding(
//				Messages.getString("EnergySystemView.TEXT_ELEMENTS")); //$NON-NLS-1$
//		invisibleRoot = new TreeIfcBuilding(""); //$NON-NLS-1$
//		invisibleRoot.addChild(root);
//
//	}
//
//}
