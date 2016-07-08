package de.tudresden.bau.cib.vl.gui.common.exception;

public class ConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1127490314897933053L;

	public ConfigurationException(String msg) {
		super(msg);
	}
	
	public ConfigurationException(Throwable t) {
		super(t);
	}
}
