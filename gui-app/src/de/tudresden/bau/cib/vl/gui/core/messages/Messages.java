package de.tudresden.bau.cib.vl.gui.core.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "de.tudresden.bau.cib.vl.gui.core.messages.messages"; //$NON-NLS-1$
	

	// File
	public static String FILES;
	public static String FILE_IFC;
	public static String FILE_eeBIM;

	public static String VIEW_DATAMANAGEMENT;
	public static String ACTION_SELECT_FILE;
	public static String ACTION_UPLOAD_FILE;
	public static String MSG_FILE_SELECTED;
	public static String MSG_FILE_UPLOADED;
	
	// Dialog
	public static String TXT_USER_SETTINGS_DIALOG_TITLE;
	public static String TXT_USER_SETTINGS_MSG;
	public static String TXT_USER_SETTINGS_DIALOG_OK;
	public static String TXT_USER_SETTINGS_DIALOG_CANCEL;
	public static String TXT_USER_SETTINGS_DIALOG_FIRSTNAME;
	public static String TXT_USER_SETTINGS_DIALOG_LASTNAME;
	public static String MSG_USER_SETTINGS_DIALOG_FIRSTNAME_ERROR;
	public static String MSG_USER_SETTINGS_DIALOG_LASTNAME_ERROR;
	
	public static String APPLICATION_NAME;
	public static String BROWSE;
	public static String OK;

	public static String MSG_LOGIN_SUCCESSFULLY;
	public static String MSG_LOGIN_FAILED;
	public static String LOGIN;
	public static String USERNAME;
	public static String PASSWORD;
	
	//
	public static String PROJECTS;
	public static String NEW_PROJECT;
	public static String DELETE_PROJECT;
	public static String DELETE_SIMULATION_PROJECT;
	public static String DELETE_FILE;
	public static String DOWNLOAD;
	public static String UPDATE_SIMULATION_STATUS;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

  
	private Messages() {
		// prevent instantiation
	}

}
