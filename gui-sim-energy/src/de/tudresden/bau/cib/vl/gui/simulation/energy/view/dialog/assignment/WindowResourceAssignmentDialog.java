package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment;

import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;

import org.eclipse.swt.widgets.Shell;

import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCModelContentProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;

public class WindowResourceAssignmentDialog extends ResourceAssignmentDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 689972250516553720L;

	public WindowResourceAssignmentDialog(Shell parent,
			IFCLabelProvider labelProvider, IFCModelContentProvider contentProvider,
			Resource resource, Set<EIfcroot> checkedEntities) {
		super(parent, labelProvider, contentProvider, resource, checkedEntities);
	}
}
