package de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;

public class OntologyTreeViewer extends TreeViewer {

	public OntologyTreeViewer(Composite parent, 
						int style,
						IViewSite site,
						ViewerSorter sorter,
						LabelProvider labelProvider,
						OntologyContentProvider contenProvider)
	{
		super(parent, style);
		
		this.setContentProvider(contenProvider);
		this.setLabelProvider(labelProvider);
		this.setSorter(sorter);
		this.setInput(site);		
	
	
	}

}
