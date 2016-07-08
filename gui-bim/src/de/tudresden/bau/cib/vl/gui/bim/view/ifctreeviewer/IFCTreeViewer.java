package de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;

public class IFCTreeViewer extends TreeViewer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -994387264836656936L;
	
	private IFCModelContentProvider contentProvider;

	public IFCTreeViewer(Composite parent, 
						int style,
						IViewSite site,
						ViewerSorter sorter,
						LabelProvider labelProvider,
						IFCModelContentProvider contentProvider)
	{
		super(parent, style);
		this.contentProvider = contentProvider;	
		this.setContentProvider(contentProvider);
		this.setLabelProvider(labelProvider);
		this.setSorter(sorter);
		this.setInput(site);				
	}
	
	@Override
	public IFCModelContentProvider getContentProvider() {
		return contentProvider;
	}

	

}
