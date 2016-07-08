package de.tudresden.bau.cib.vl.gui.common.extensionpoints;



public interface ILogin {

	boolean login();
	
	boolean login(String sessionId);
}
