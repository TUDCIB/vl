package de.tudresden.bau.cib.vl.gui.common.view;

import org.eclipse.ui.part.ViewPart;

import de.tudresden.bau.cib.vl.gui.common.controller.AbstractViewController;
import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;

/**
 * Abstract view class when applying the MVC concept.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 * @param <C> The (concrete) controller which the view needs.
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractView<C extends AbstractViewController> extends ViewPart {

	/**
	 * Sub classes have to instantiate their controller itself and have to set needed 
	 * services if the controllers require them. This means that every view hold their own
	 * controller.
	 * @return
	 */
	protected abstract C createController();
	
	@SuppressWarnings("unchecked")
	protected void initController() {
		C controller = createController();
		CommunicationService communicationService = CommunicationService.getInstance();
		controller.setCommunicationService(communicationService);
		controller.setView(this);
	}
}



