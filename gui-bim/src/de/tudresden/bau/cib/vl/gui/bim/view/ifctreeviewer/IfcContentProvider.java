package de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;

public abstract class IfcContentProvider implements IStructuredContentProvider, ITreeContentProvider {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2263545313469219777L;
	protected IFCTreeNode invisibleRoot;
	protected ContentMode mode;	
	
	
	public IfcContentProvider(ContentMode mode) {
		this.mode = mode;
		
	}
	
	
	@Override
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		
	}
	
	@Override
	public void dispose() {

	}	

	@Override
	public Object getParent(Object child) {
		
		if(child instanceof IFCTreeNode)
			return ((IFCTreeNode)child).getParent();			
	
		return null;
	}
	
	@Override
	public Object [] getChildren(Object parent) {

		if (parent instanceof IFCTreeNode) {
			return ((IFCTreeNode)parent).getChildren();
		}
		
		return new Object[0];
	}
	
	@Override
	public boolean hasChildren(Object parent) {

		if (parent instanceof IFCTreeNode)
			return ((IFCTreeNode)parent).hasChildren();			
		return false;
	}

	public ContentMode getMode() {
		return mode;
	}
}
