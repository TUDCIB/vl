package de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer;

import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;

import de.tudresden.bau.cib.vl.gui.bim.controller.IFCController;

public class IFCTreeNodeDropListener extends ViewerDropAdapter {

	private IFCController controller;	 
	private IFCTreeNode target;
	private IFCTreeViewer treeViewer;
	private int operation;

	public IFCTreeNodeDropListener(IFCTreeViewer treeViewer, IFCController controller) {
		super(treeViewer);
		this.controller = controller;
		this.treeViewer = treeViewer;
	}

	@Override
	public void drop(final DropTargetEvent event)  {
		event.display.asyncExec(new Runnable() {

			@Override
			public void run() {
				controller.onDropEvent(event.data, treeViewer.getContentProvider().getMode(), determineTarget(event));
			}
		});
	}

	@Override
	public boolean performDrop(Object data) {
		return true;
	}

	@Override
	public boolean validateDrop(Object target, int operation,
			TransferData transferType)
	{

		if (target instanceof IFCTreeNode)
		{
			this.target = (IFCTreeNode) target;
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