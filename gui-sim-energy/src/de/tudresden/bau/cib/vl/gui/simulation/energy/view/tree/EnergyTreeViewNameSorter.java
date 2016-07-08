//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tree;
//
//import jsdai.SIfc2x3.EIfcbuildingstorey;
//import jsdai.SIfc2x3.EIfcspace;
//import jsdai.lang.SdaiException;
//
//import org.eclipse.jface.viewers.Viewer;
//import org.eclipse.jface.viewers.ViewerSorter;
//
//
//public class EnergyTreeViewNameSorter extends ViewerSorter {
//
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 7885840791365109549L;
//
//	@Override
//	public int compare(Viewer viewer, Object e1, Object e2) {
//		if(e1 instanceof TreeIfcBuildingStorey && e2 instanceof TreeIfcBuildingStorey) {
//			try {
//				TreeIfcBuildingStorey t1 = (TreeIfcBuildingStorey) e1;
//				TreeIfcBuildingStorey t2 = (TreeIfcBuildingStorey) e2;
//				EIfcbuildingstorey storey1 = (EIfcbuildingstorey) t1.getIfcElement();
//				EIfcbuildingstorey storey2 = (EIfcbuildingstorey) t2.getIfcElement();
//				
//				if(storey1.testElevation(storey1) && storey2.testElevation(storey2)) {
//					double elevation1 = storey1.getElevation(storey1);
//					double elevation2 = storey2.getElevation(storey2);					
//					if(elevation1 < elevation2) {
//						return -1;
//					} else {
//						return 1;
//					}
//				}
//			} catch(SdaiException e) {
//				
//			}
//		} else 	if(e1 instanceof TreeIfcSpace && e2 instanceof TreeIfcSpace) {
//			try {
//				TreeIfcSpace t1 = (TreeIfcSpace) e1;
//				TreeIfcSpace t2 = (TreeIfcSpace) e2;
//				EIfcspace space1 = (EIfcspace) t1.getIfcElement();
//				EIfcspace space2 = (EIfcspace) t2.getIfcElement();
//				
//				if((space1.getName(space1) != null) && space2.getName(space2) != null) {
//					String name1 = space1.getName(space1);
//					String name2 = space2.getName(space2);
//					return name1.compareToIgnoreCase(name2);
//				}
//			} catch(SdaiException e) {
//				
//			}
//		}
//		return 0;
//	}
//}
