package de.tudresden.bau.cib.vl.gui.common.controller;

import de.tudresden.bau.cib.vl.gui.common.view.AbstractView;

/**
 * Abstract controller class when applying the MVC concept.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 */
public abstract class AbstractViewController<V extends AbstractView<?>> extends AbstractController {

	protected V view;
	
	public void setView(V view) {
		this.view = view;
	}

}
