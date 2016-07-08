package de.tudresden.bau.cib.vl.gui.simulation.energy.utility;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceDropListener extends ViewerDropAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7212442239050886356L;
	private final Viewer viewer;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(ResourceDropListener.class);
	
	
	public ResourceDropListener(Viewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	@Override
	public boolean performDrop(Object data) {
		LOG.debug("Drag data: {}", data);
		return false;
	}

	@Override
	public boolean validateDrop(Object target, int operation,TransferData transferType) {
		LOG.debug("Validating drop target: {}, with operation: {}, and transfer type: {}",
				new Object[]{target, operation, transferType});
		if(target != null) {
			// drag is only supported to the BIM tree
			if(target instanceof Object) {
				return true;
			}
		}
		return false;
	}

}
