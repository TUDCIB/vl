package de.tudresden.bau.cib.vl.gui.core.logging;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import ch.qos.logback.core.AppenderBase;

public class LoggingAppender<E> extends AppenderBase<E> {
	
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	private E event;
	
	
	@Override
	protected void append(E eventObject) {
		firePropertyChange("event", event, this.event = eventObject);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}
	
	private void firePropertyChange(String propertyName, 
			E oldValue,
			E newValue) {
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

}
