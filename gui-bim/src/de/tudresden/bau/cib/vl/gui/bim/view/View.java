package de.tudresden.bau.cib.vl.gui.bim.view;

import org.eclipse.swt.widgets.Composite;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.bim.service.BimFitService;
import de.tudresden.bau.cib.vl.gui.bim.controller.Controller;
import de.tudresden.bau.cib.vl.gui.common.view.AbstractView;

@SuppressWarnings("rawtypes")
public abstract class View<T extends Controller> extends AbstractView<T> {

	
	
	
	public static final String ID = "de.tudresden.bau.cib.vl.gui.bim.view.View";
	
	protected BimFitService bimfitService;

	
	@Override
	public void createPartControl(Composite parent) {
		
	}

	@Override
	public void setFocus() {
		
	}
	
	public void update()
	{
		
	}

	public void setModel(Model model)
	{
		
		
	}
	
	public void setBimFitService(BimFitService bimfitService) {
		this.bimfitService = bimfitService;
	}

}



