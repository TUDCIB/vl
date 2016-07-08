package de.tudresden.bau.cib.vl.gui.core.view.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewProjectDialog extends TitleAreaDialog {	

	private Text textProjectName;
	private String strProjectName="";

	public String getStrProjectName() {
		return strProjectName;
	}

	public NewProjectDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Create new project");
		setMessage("Enter name of new project", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout(2, false);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		container.setLayout(layout);

		createProjectName(container);

		return area;
	}

	private void createProjectName(Composite container) {
		Label lbtFirstName = new Label(container, SWT.NONE);
		lbtFirstName.setText("Project name");

		GridData dataProjectName = new GridData();
		dataProjectName.grabExcessHorizontalSpace = true;
		dataProjectName.horizontalAlignment = GridData.FILL;

		textProjectName = new Text(container, SWT.BORDER);
		textProjectName.setLayoutData(dataProjectName);
		textProjectName.setFocus();
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	// save content of the Text fields because they get disposed
	// as soon as the Dialog closes
	protected void saveInput() {
		strProjectName = textProjectName.getText();  

	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}


}
