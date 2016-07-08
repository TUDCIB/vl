package de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;

public class IFCTreeNodeDragListener extends DragSourceAdapter {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1963971834545516198L;
	private IFCTreeViewer treeViewer;

	  public IFCTreeNodeDragListener(IFCTreeViewer treeViewer) {
	    this.treeViewer = treeViewer;
	  }

	  @Override
	  public void dragSetData(DragSourceEvent event) {
		  
	    ISelection selection = treeViewer.getSelection();

	    if (selection instanceof TreeSelection) {
	      TreeSelection treeSelection = (TreeSelection) selection;
	      event.data = ((IFCTreeNode) treeSelection.getFirstElement())
	                                  .toString();
	    } else {
	      event.doit = false;
	    }
	  }
}


