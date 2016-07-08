package de.tudresden.bau.cib.ui.widgets;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

public abstract class Widget extends Composite {

	/**
	 * Important for Databinding
	 */
	protected PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);


	public Widget(final Composite parent, final int style) {
		super(parent, style);
	}

	/**
	 * Important for Databinding
	 */
	public void addPropertyChangeListener(PropertyChangeListener 
			listener) {
		changeSupport.addPropertyChangeListener(listener);
	}
	
	/**
	 * Important for Databinding
	 */
	public void removePropertyChangeListener(PropertyChangeListener 
			listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * Important for Databinding
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Important for Databinding
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	/**
	 * Important for Databinding
	 */
	protected void firePropertyChange(String propertyName, 
			Object oldValue,
			Object newValue) {
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	@Override
	public void setLayout(final Layout layout) {
		throw new UnsupportedOperationException("Cannot change internal layout");
	}
}