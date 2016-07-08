package de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer;

import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.lang.SdaiException;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class IFCViewerSorter extends ViewerSorter {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5900226682373848359L;
		
		private int mode;
		
		public IFCViewerSorter(int mode)
		{
			this.mode = mode;
		}

	
		public int compareStoreyElevationAndSpaceName(Viewer viewer, Object e1, Object e2) 
		{
			
				IFCTreeNode n1 = (IFCTreeNode)e1;
				IFCTreeNode n2 = (IFCTreeNode)e2;			
			
				EIfcroot ifcE1 = n1.getIfcElement();
				EIfcroot ifcE2 = n2.getIfcElement();
			
				
				if( ifcE1 instanceof EIfcbuildingstorey && ifcE2 instanceof EIfcbuildingstorey){
					try {
				
						EIfcbuildingstorey storey1 = (EIfcbuildingstorey) ifcE1;
						EIfcbuildingstorey storey2 = (EIfcbuildingstorey) ifcE2;
						
						if(storey1.testElevation(storey1) && storey2.testElevation(storey2)) {
							double elevation1 = storey1.getElevation(storey1);
							double elevation2 = storey2.getElevation(storey2);					
							if(elevation1 < elevation2) {
								return -1;
							} else {
								return 1;
							}
						}
					} catch(SdaiException e) {
						
					}
				}	
				else if(ifcE1 instanceof EIfcspace && ifcE2 instanceof EIfcspace) {
					try {
						
						if((n1.getName() != null) && n2.getName() != null) {
							String name1 = n1.getName().split(".")[1];
							String name2 = n2.getName().split(".")[1];
							return name1.compareToIgnoreCase(name2);
						}
					} catch(Exception e) {
						
					}
				}
				return 0;
		}
		
		@Override
		public int compare(Viewer viewer, Object e1, Object e2) 
		{
			switch(mode)
			{
				case 1: return compareStoreyElevationAndSpaceName(viewer,  e1,  e2); 
				default: return 0;
			}
		}
	}


