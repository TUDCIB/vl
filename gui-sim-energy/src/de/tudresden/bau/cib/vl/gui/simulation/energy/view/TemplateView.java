//package de.tudresden.bau.cib.vl.gui.simulation.energy.view;
//
//import org.eclipse.jface.action.IToolBarManager;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.ui.IActionBars;
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.Section;
//import org.eclipse.ui.part.ViewPart;
//
//import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
//import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
//import de.tudresden.bau.cib.vl.core.service.UserService;
//import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergySimulationService;
//import de.tudresden.bau.cib.vl.gui.common.Constants;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.TemplateViewController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//
//public class TemplateView extends ViewPart {
//	
//	/**
//	 * The ID of the view as specified by the extension.
//	 */
//	public static final String ID = Constants.PLACEHOLDER_PREFIX+".simulation.energy."+Constants.PLACEHOLDER_WINDOW_BOTTOM+".TemplateView";
//	
//	
//	
//	@Override
//	public void createPartControl(Composite parent) {
//		parent.setLayout(new FillLayout(SWT.VERTICAL));
//	    FormToolkit toolkit = new FormToolkit(parent.getDisplay());
//	    Section section = toolkit.createSection(parent, Section.DESCRIPTION
//	        | Section.TITLE_BAR);
//	    section.setText(Messages.get().TEXT_TEMPLATE_MANAGEMENT); //$NON-NLS-1$
//	    // Composite for storing the data
//	    Composite client = toolkit.createComposite(section, SWT.WRAP);
//	    GridLayout layout = new GridLayout();
//	    layout.numColumns = 1;
//	    layout.marginWidth = 2;
//	    layout.marginHeight = 2;
//	    client.setLayout(layout);
//	    
//		TemplatesTabFolder tabFolder = new TemplatesTabFolder(client, SWT.NONE);
//	    GridData gd = new GridData(GridData.FILL_BOTH);
//	    tabFolder.setLayoutData(gd);
//	    
//		makeActions();
//		contributeToActionBars();
//		
//		section.setClient(client);
//	}
//
//	@Override
//	public void setFocus() {
//
//	}
//	
//	private void contributeToActionBars() {
//		IActionBars bars = getViewSite().getActionBars();
//		fillLocalToolBar(bars.getToolBarManager());
//	}
//
//	private void fillLocalToolBar(IToolBarManager manager) {
//
//	}
//
//	private void makeActions() {
//
//	}
//
//	public void setEnergySimulationService(
//			EnergySimulationService energySimulationService) {
//		TemplateViewController.getInstance().setEnergySimulationService(energySimulationService);
//	}
//	
//	public void setUserService(UserService userService) {
//		TemplateViewController.getInstance().setUserService(userService);
//	}
//	
//	public void setConfigurationService(ConfigurationService configurationService) {
//		TemplateViewController.getInstance().setConfigurationService(configurationService);
//	}
//	
//	public void setTemplateService(TemplateService templateService) {
//		TemplateViewController.getInstance().setTemplateService(templateService);
//	}
//
//}
