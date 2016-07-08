package de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer;

import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;

import de.tudresden.bau.cib.vl.gui.bim.controller.OntologyController;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;

public class OntologyTreeNodeDropListener extends ViewerDropAdapter {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1119919436049554271L;
	private OntologyController controller;	
	private OntologyTreeViewer treeViewer;
	private OntologyTreeNode target;
	private int operation;
	
	
	public OntologyTreeNodeDropListener(OntologyTreeViewer viewer, OntologyController controller) {
		super(viewer);
		this.treeViewer = viewer;
		this.controller = controller;
	}

	@Override
	public boolean performDrop(Object data) {
		return true;
	}
	
	@Override
	public void drop(final DropTargetEvent event)  {
		event.display.asyncExec(new Runnable() {

			@Override
			public void run() {
				controller.onDropEvent(event.data, ContentMode.NOT_ASSIGNED, determineTarget(event));
			}

		});
	}

	@Override
	public boolean validateDrop(Object target, int operation, TransferData transferType) {

		if (target instanceof OntologyTreeNode)
		{
			this.target = (OntologyTreeNode) target;
			this.operation = operation;
			return true;
		} else
		{
			this.target = null;
			this.operation = 0;
			return false;
		}
	}

}
