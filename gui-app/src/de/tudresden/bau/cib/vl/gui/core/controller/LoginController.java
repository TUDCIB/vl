package de.tudresden.bau.cib.vl.gui.core.controller;


import org.eclipse.swt.widgets.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.database.domain.User;
import de.tudresden.bau.cib.vl.core.service.UserService;
import de.tudresden.bau.cib.vl.gui.common.extensionpoints.ILogin;
import de.tudresden.bau.cib.vl.gui.core.ServiceRegistry;



/**
 * Controls the login of users.
 *
 * @author Ken Baumgaertel
 *	{@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public class LoginController implements ILogin {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	private static final String TEST_DEFAULT_USER = "default";
	private static final String TEST_DEFAULT_PASSWORD = "default";
	
	

	public LoginController() {

	}
	
	@Override
    public boolean login() {  	
		// create default user is none is existent
		UserService userManagement = ServiceRegistry.getInstance().getUserService();
		User user = new User();
		user.setUsername(TEST_DEFAULT_USER);
		user.setPassword(TEST_DEFAULT_PASSWORD);
		userManagement.createUser(user);
		
		return login(TEST_DEFAULT_USER, TEST_DEFAULT_PASSWORD);

    	// 25.11.2014: no login needed currently
//		boolean success = false;
//        LoginDialog loginDialog = new LoginDialog(Display.getCurrent().getActiveShell());
//        while (!success) {
//        		if (loginDialog.open() == Window.OK) {
//        			String username = loginDialog.getUsername();
//        			String password = loginDialog.getPassword();
//        			
//        	    	boolean loggedIn = login(username, password);
//        	    	if(!loggedIn) {
//        	            MessageDialog.openError(Display.getCurrent().getActiveShell(), Messages.MSG_LOGIN_FAILED, 
//        	            		Messages.MSG_LOGIN_FAILED);
//        	    	} else {
//        	    		success = true;
//        	            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.MSG_LOGIN_SUCCESSFULLY,
//        	            		Messages.MSG_LOGIN_SUCCESSFULLY+", " + username + "!");
//        	            return true;
//        	    	}
//                } else {
//                	MessageDialog.openError(Display.getCurrent().getActiveShell(), Messages.MSG_LOGIN_FAILED, 
//                			Messages.MSG_LOGIN_FAILED);
//                }
//        }
//        return false;
    }
	
	public boolean login(String username, String password) {   		
		UserService userManagement = ServiceRegistry.getInstance().getUserService();
		if(username.length() > 0 && password.length() > 0) {		
			User user = userManagement.getUser(username, password);
	    	if(user == null) { // NO SUCCESS
	    		LOG.info("User not found with username: {} and password: {}", new Object[]{username, password});
            	return false;
	    	} else {
	    		if(user.getPassword().equals(password)) { // SUCCESS
    	    		LOG.info("User: {} logged in successfully",
    	    				new Object[]{user.getUsername()});
//    	            SET THE USER
		    		setUser(user);
		    		return true;
	    		}
	    	}
		}
		return false;
	}
	
	public void setUser(final User user) {
		final Display display = Display.getCurrent();
		display.syncExec(new Runnable() {
			public void run() {
				SessionManagementController.getInstance().setUser(user);
			}
		});	
	}
	
    public String getEncryptedPassword(String password) {
//    	Certificate publicKey = null;
//      try {
//      	KeyStore keyStore = KeyStore.getInstance("JKS");
//          keyStore.load(new FileInputStream(Configuration.getWorkspaceDirectory() + "unicaseClient.keystore"),
//          		"jsFTga3rGTR833329GFQEfas".toCharArray());
//          publicKey = keyStore.getCertificate("unicase.org test test(!!!) certificate");
//      } catch (Exception e) {
//          
//      }
//      PublicKey key = publicKey.getPublicKey();
//      byte[] inpBytes;
//      inpBytes = getPassword().getBytes();
//      Cipher cipher = Cipher.getInstance("RSA");
//      cipher.init(Cipher.ENCRYPT_MODE, key);
//      byte[] encryptededByteAr = cipher.doFinal(inpBytes);
//      byte[] base64EncodedByteAr = Base64.encodeBase64(encryptededByteAr);
//      return new String(base64EncodedByteAr);
  	return password;
    }

	@Override
	public boolean login(String sessionId) {
		UserService userManagement = ServiceRegistry.getInstance().getUserService();
		User user = userManagement.getUserBySessionId(sessionId);
		if(user != null) {
			LOG.debug("Found user: {} by session identifier: {}",
					new Object[]{user.getUsername(), sessionId});
			boolean loggedIn = login(user.getUsername(), user.getPassword());
			setUser(user);
			return loggedIn;
		}
		LOG.debug("Found no user by session identifier: {}",
				new Object[]{sessionId});
		return false;
	}

}
