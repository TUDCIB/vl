package de.tudresden.bau.cib.vl.gui.common.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.tudresden.bau.cib.vl.gui.common.view.AbstractView;

/**
 * Abstract controller class when applying the MVC concept.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 * @param <V> The (concrete) view which the controller is addressing.
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractMultiViewController extends AbstractController {

	protected Set<AbstractView> views = new HashSet<AbstractView>();
	
	public void setView(AbstractView view) {
		addView(view);		
	}
	
	
	public void addView(AbstractView view) {
		views.add(view);		
	}
	
	public void removeView(AbstractView view)
	{
		views.remove(view);
	}
	
	

}
