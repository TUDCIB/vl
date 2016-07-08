package de.tudresden.bau.cib.vl.gui.core.view.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.tudresden.bau.cib.vl.gui.core.messages.Messages;



/**
 * Dialog for the input of user details.
 * 
 * @author Ken
 *
 */
public class UserSettingsDialog extends TitleAreaDialog {

	
	private Text firstNameText;
	private Text lastNameText;
	private String firstName;
	private String lastName;
	

	public UserSettingsDialog(Shell parent) {
		super(parent);
	}

	@Override
	public void create() {
		super.create();
		// Set the title
		setTitle(Messages.TXT_USER_SETTINGS_DIALOG_TITLE);
		// Set the message
		setMessage(Messages.TXT_USER_SETTINGS_MSG, IMessageProvider.INFORMATION);

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		// layout.horizontalAlignment = GridData.FILL;
		parent.setLayout(layout);

		// The text fields will grow with the size of the dialog
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;

		Label label1 = new Label(parent, SWT.NONE);
		label1.setText(Messages.TXT_USER_SETTINGS_DIALOG_FIRSTNAME);

		firstNameText = new Text(parent, SWT.BORDER);
		firstNameText.setLayoutData(gridData);
		
		Label label2 = new Label(parent, SWT.NONE);
		label2.setText(Messages.TXT_USER_SETTINGS_DIALOG_LASTNAME);

		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		lastNameText = new Text(parent, SWT.BORDER);
		lastNameText.setLayoutData(gridData);
		return parent;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 3;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.CENTER;

		parent.setLayoutData(gridData);
		// Create Add button

		createOkButton(parent, OK, Messages.TXT_USER_SETTINGS_DIALOG_OK, true);
		// Add a SelectionListener

		// Create Cancel button
		Button cancelButton = createButton(parent, CANCEL, Messages.TXT_USER_SETTINGS_DIALOG_CANCEL, false);
		// Add a SelectionListener
		cancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setReturnCode(CANCEL);
				close();
			}
		});
	}

	protected Button createOkButton(Composite parent, int id, String label,
			boolean defaultButton) {
		// increment the number of columns in the button bar
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText(label);
		button.setFont(JFaceResources.getDialogFont());
		button.setData(new Integer(id));
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (isValidInput()) {
					okPressed();
				}
			}
		});
		if (defaultButton) {
			Shell shell = parent.getShell();
			if (shell != null) {
				shell.setDefaultButton(button);
			}
		}
		setButtonLayoutData(button);
		return button;
	}

	private boolean isValidInput() {
		boolean valid = true;
		if (firstNameText.getText().length() == 0) {
			setErrorMessage(Messages.MSG_USER_SETTINGS_DIALOG_FIRSTNAME_ERROR);
			valid = false;
		}
		if (lastNameText.getText().length() == 0) {
			setErrorMessage(Messages.MSG_USER_SETTINGS_DIALOG_LASTNAME_ERROR);
			valid = false;
		}
		return valid;
	}
	
	@Override
	protected boolean isResizable() {
		return true;
	}

	// We need to have the textFields into Strings because the UI gets disposed
	// and the Text Fields are not accessible any more.
	private void saveInput() {
		firstName = firstNameText.getText();
		lastName = lastNameText.getText();
//		TODO save it
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	

}
