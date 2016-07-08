package de.tudresden.bau.cib.vl.gui.bim.controller;


import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.bim.service.BimFitService;
import de.tudresden.bau.cib.vl.gui.bim.Activator;
import de.tudresden.bau.cib.vl.gui.bim.view.View;
import de.tudresden.bau.cib.vl.gui.common.controller.AbstractViewController;


public abstract class Controller<T extends View<?>> extends AbstractViewController<T> {

	protected Model model = null;
	
	protected static Logger LOG = LoggerFactory.getLogger(Controller.class);	


	protected BimFitService bimfitService;
	
	protected void showMessage(final String title, final String message, final boolean isError) {	
		Display.getCurrent().asyncExec(new Runnable() {
			@Override
			public void run() {
				if(isError) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), 
							title,
							message);
				} else {
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(), 
							title,
							message);
				}
			}
		});
	}

	public void setBimFitService(BimFitService bimfitService) {
		this.bimfitService = bimfitService;
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
		view.setModel(model);
	}	
	
	public T getView() {
		return view;
	}
	
	public void updateView()
	{
		view.update();
	}
	
	@Override
	protected BundleContext getBundleContext() {
		return Activator.getDefault().getContext();
	}
}
