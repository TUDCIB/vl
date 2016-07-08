package de.tudresden.bau.cib.vl.gui.simulation.energy.controller.assignment;

import java.util.Collection;

import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcspace;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeNode;
import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.ProcessController;

public class IfcZonesOfInterestColoredLabelProvider extends IFCLabelProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = -49827377961215523L;

	@Override
	public Color getForeground(Object element) {
		IFCTreeNode ifcNode = (IFCTreeNode)element;		
		EIfcroot entity = ifcNode.getIfcElement();
		if(entity instanceof EIfcspace) {
			Collection<EIfcroot> zoi = ProcessController.getInstance().getZonesOfInterest();
			if(zoi.contains(entity)){ 
				return Display.getDefault().getSystemColor(SWT.COLOR_RED); 
			}
		}
		return null;	
	}

}
