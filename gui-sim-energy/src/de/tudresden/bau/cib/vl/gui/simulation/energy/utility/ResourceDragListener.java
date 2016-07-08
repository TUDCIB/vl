package de.tudresden.bau.cib.vl.gui.simulation.energy.utility;



import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.widgets.Control;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.model.Resource;


public class ResourceDragListener implements DragSourceListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1218370869458938788L;
	private Viewer viewer;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(ResourceDragListener.class);
	
	
	public ResourceDragListener(Viewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		Object source = event.getSource();
		if(source != null) {
			DragSource ds = (DragSource) source;
			Control sourceControl = ds.getControl();
		}		
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();	
		Object element = selection.getFirstElement();
		if(element instanceof Resource) {
			event.data = ((Resource)element).getSourceFileUri();
			LOG.debug("Drag data: {}", event.data);
		} else {
			throw new IllegalArgumentException("No resource data in drag listener");
		}
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		Object source = event.getSource();
		if(source != null) {
			DragSource ds = (DragSource) source;
			Control sourceControl = ds.getControl();
		}
	}

}
