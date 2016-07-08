package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment;

import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;

import org.eclipse.swt.widgets.Shell;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IfcContentProvider;

public class ConstructionResourceAssignmentDialog extends ResourceAssignmentDialog {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6268475376323346278L;
	

	public ConstructionResourceAssignmentDialog(Shell parent,
			IFCLabelProvider labelProvider, IfcContentProvider contentProvider,
			ConstructionTemplate resource, Set<EIfcroot> checkedEntities) {
		super(parent, labelProvider, contentProvider, resource, checkedEntities);
	}

}
