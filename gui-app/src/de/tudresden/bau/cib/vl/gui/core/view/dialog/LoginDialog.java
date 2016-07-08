package de.tudresden.bau.cib.vl.gui.core.view.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.tudresden.bau.cib.vl.gui.core.messages.Messages;



public class LoginDialog extends Dialog {

	private String username;
	private String password;

    private Label usernameLabel;
    private Text usernameText;
    private Label passwordLabel;
    private Text passwordText;

    /**
     * @param parentShell parentShell
     */
    public LoginDialog(Shell parentShell) {
            super(parentShell);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell newShell) {
            super.configureShell(newShell);
            newShell.setText(Messages.LOGIN);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
            Composite composite = (Composite) super.createDialogArea(parent);

            composite.setLayout(new GridLayout(2, true));

            usernameLabel = new Label(composite, SWT.FLAT);
            GridData ugdl = new GridData();
            ugdl.grabExcessHorizontalSpace = true;
            usernameLabel.setLayoutData(ugdl);
            usernameLabel.setText(Messages.USERNAME+":");

            usernameText = new Text(composite, SWT.FLAT | SWT.BORDER);
            GridData ugdt = new GridData(100, 30);
            ugdt.grabExcessHorizontalSpace = true;
            usernameText.setLayoutData(ugdt);

            passwordLabel = new Label(composite, SWT.FLAT);
            GridData pgdl = new GridData();
            pgdl.grabExcessHorizontalSpace = true;
            passwordLabel.setLayoutData(pgdl);
            passwordLabel.setText(Messages.PASSWORD+":");

            passwordText = new Text(composite, SWT.FLAT | SWT.BORDER | SWT.PASSWORD);
            GridData pgdt = new GridData(100, 30);
            pgdt.grabExcessHorizontalSpace = true;
            passwordText.setLayoutData(pgdt);
            
            usernameText.setText("");
            passwordText.setText("");
            
            composite.layout();
            
            usernameText.setFocus();

            return composite;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
    	this.username = usernameText.getText();
    	this.password = passwordText.getText();
    		
        super.okPressed();
    }

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
