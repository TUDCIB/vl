package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;

public class ClimateResourceAssignmentDialog extends TitleAreaDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8679846317633675311L;
	
	protected FormToolkit toolkit;
	protected final Resource resource;
	

	public ClimateResourceAssignmentDialog(Shell parentShell, 
			Resource resource) {
		super(parentShell);
		this.resource = resource;
	}
	
	@Override
	public void create() {
		super.create();
		setMessage("Assign climate resource: "+resource.getName()+" to BIM");
		setTitle("Climate resource assignment dialog");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		
		toolkit = new FormToolkit(area.getDisplay());
		
		Label resourceLabel = new Label(area, SWT.NONE);
		resourceLabel.setText("Resource: "+resource.getName()+"\n"+"URL: "+resource.getSourceFileUri());
		resourceLabel.setFont(AppearanceFactory.DEFAULT_FONT_LARGE_BOLD);
		
		return area;
	}
}
