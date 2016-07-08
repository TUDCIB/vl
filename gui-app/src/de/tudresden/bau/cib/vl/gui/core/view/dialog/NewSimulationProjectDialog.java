package de.tudresden.bau.cib.vl.gui.core.view.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Shell;

public class NewSimulationProjectDialog extends NewProjectDialog {

	public NewSimulationProjectDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Create new simulation project");
		setMessage("Enter name of new simulation project", IMessageProvider.INFORMATION);
	}

}
