package de.tudresden.bau.cib.vl.gui.bim.view.dnd;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;

import de.tudresden.bau.cib.vl.gui.bim.controller.Ifc3DViewController;

public class IFCViewerDropListener extends DropTargetAdapter {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8979708080442873218L;
	
	private Ifc3DViewController controller;
	
	
	public IFCViewerDropListener(Ifc3DViewController controller) {
		this.controller = controller;
	}

	@Override
	public void drop(DropTargetEvent event) {
		Object data = event.data;
		if(data instanceof String) {
			String resourceUri = (String) data;
			if(StringUtils.isNotEmpty(resourceUri)){
				controller.onDropEvent(data);
			}
		}
	}
}
