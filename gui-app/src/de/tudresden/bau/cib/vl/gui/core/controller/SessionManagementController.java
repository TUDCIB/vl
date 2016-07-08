package de.tudresden.bau.cib.vl.gui.core.controller;


import de.tudresden.bau.cib.vl.core.database.domain.User;



/**
 *
 * @author Ken Baumgaertel
 *	{@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public class SessionManagementController {
	
	
	private User user;	
	
	/**
	 * The shared instance.
	 */
	private static SessionManagementController INSTANCE = new SessionManagementController();

	/**
	 * Private constructor because of singleton pattern.
	 */
	private SessionManagementController() {

	}

	/**
	 * Retrieves the single instance of SessionManagementController.
	 * @return The instance of SessionManagementController.
	 */
	public static SessionManagementController getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SessionManagementController();
		return INSTANCE;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
