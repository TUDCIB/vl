package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment;

import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IfcContentProvider;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.IfcTreeSelectionDialog;

/**
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class ResourceAssignmentDialog extends IfcTreeSelectionDialog {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2824772263506263971L;
	
	protected final Resource resource;
	
	
	public ResourceAssignmentDialog(Shell parent, 
			IFCLabelProvider labelProvider, IfcContentProvider contentProvider, 
			Resource resource, Set<EIfcroot> checkedEntities) {
		super(parent, labelProvider, contentProvider, checkedEntities);
		this.resource = resource;
	}
	

	@Override
	public void create() {
		super.create();
		setMessage("Assign resource: "+resource.getName()+" to IFC entities");
		setTitle("Assignment dialog");
	}
	
	@Override
	protected Composite createDialogArea(Composite parent) {	
		Label resourceLabel = new Label(parent, SWT.NONE);
		resourceLabel.setText("Resource: "+resource.getName()+"\n"+"URL: "+resource.getSourceFileUri());
		resourceLabel.setFont(AppearanceFactory.DEFAULT_FONT_LARGE_BOLD);
		
		// create tree
		Composite area = (Composite) super.createDialogArea(parent);
		return area;
	}
}
