//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs;
//
//import org.eclipse.core.databinding.DataBindingContext;
//import org.eclipse.core.databinding.beans.BeansObservables;
//import org.eclipse.core.databinding.observable.value.IObservableValue;
//import org.eclipse.jface.databinding.swt.SWTObservables;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.TabFolder;
//import org.eclipse.swt.widgets.TabItem;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.forms.widgets.Form;
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.ScrolledForm;
//import org.eclipse.ui.forms.widgets.Section;
//
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.EnergyKeyPerformanceIndicatorController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.model.EnergyKeyPerformanceIndicatorsWrapper;
//
//public class HeatingTabItem extends TabItem {
//	
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 7235263601550259911L;
//	
//	private Text	textFinalEnergyHeating;
//	private Text	textPlantExpeditureFigureHeating;
//	private Text	textNetEnergyHeating;
//	private FormToolkit toolkit;
//	private EnergyKeyPerformanceIndicatorController controller;
//	private EnergyKeyPerformanceIndicatorsWrapper eKPIWrapper;
//	
//
//	public HeatingTabItem(TabFolder parent, int style) {
//		super(parent, style);
//		setText(Messages.get().TEXT_HEATING); //$NON-NLS-1$
//		controller = EnergyKeyPerformanceIndicatorController.getInstance();
//		eKPIWrapper = controller.getEKPIViewModel();
//	}
//	
//	public void createContent(TabFolder parent) {
//		ScrolledForm form = createContainer(parent);
//	    setControl(form);
//	    initDataBindings();
//	}
//	
//	private ScrolledForm createContainer(TabFolder parent) {
//		toolkit = new FormToolkit(parent.getDisplay());
//		ScrolledForm scrolledForm = toolkit.createScrolledForm(parent);
//		Form form = scrolledForm.getForm();
//		toolkit.decorateFormHeading(form);
//		form.setText(Messages.get().TEXT_HEATING);
//		Composite body = form.getBody();
//		body.setLayout(new GridLayout(2, true));
//		
////		eKPI1.1a
//		createSection(body);
//		
////		Label separator = toolkit.createSeparator(body, SWT.SEPARATOR | SWT.HORIZONTAL);
////		GridData gridDataSeparator = new GridData(GridData.FILL_HORIZONTAL);
////		gridDataSeparator.horizontalSpan = 2;
////		separator.setLayoutData(gridDataSeparator);
//
////		Image imageFormula = AppearanceFactory.getInstance().getImageDescriptor(EnergyViewService.SYMBOLIC_NAME, 
////				EnergyViewService.ICONS_PATH+"eKPI_1.1a-1.3.png").createImage();
////		Label labelImgFormula = toolkit.createLabel(body, "");
////		labelImgFormula.setImage(imageFormula);
////		GridData gridDataLabelImgFormula = new GridData(GridData.FILL_HORIZONTAL);
////		gridDataLabelImgFormula.horizontalSpan = 2;
////		labelImgFormula.setLayoutData(gridDataLabelImgFormula);
//		
//		return scrolledForm;
//	}
//
//	private DataBindingContext initDataBindings() {
//        DataBindingContext bindingContext = new DataBindingContext();
//        EnergyKeyPerformanceIndicatorsWrapper viewModel = controller.getEKPIViewModel();
//        
//        IObservableValue textPlantExpeditureFigureHeatingObserveWidget = SWTObservables.observeText(textPlantExpeditureFigureHeating, SWT.FocusOut);
//        IObservableValue plantExpeditureFigureHeatingObserveValue = BeansObservables.observeValue(viewModel, "plantExpeditureFigureHeating"); //$NON-NLS-1$
//        bindingContext.bindValue(textPlantExpeditureFigureHeatingObserveWidget, plantExpeditureFigureHeatingObserveValue, null, null);
//
////        IObservableValue textNetEnergyHeatingObserveWidget = SWTObservables.observeText(textNetEnergyHeating, SWT.FocusOut);
////        IObservableValue netEnergyHeatingObserveValue = BeansObservables.observeValue(viewModel, "netEnergyHeating"); //$NON-NLS-1$
////        bindingContext.bindValue(textNetEnergyHeatingObserveWidget, netEnergyHeatingObserveValue, null, null);
////        
////        IObservableValue eKPI11aTextObserveWidget = SWTObservables.observeText(textFinalEnergyHeating, SWT.FocusOut);
////        IObservableValue eKPI11aObserveValue = BeansObservables.observeValue(viewModel, "eKPI11a"); //$NON-NLS-1$
////        bindingContext.bindValue(eKPI11aTextObserveWidget, eKPI11aObserveValue, null, null);
//
//        return bindingContext;
//	}
//	
//	private void createSection(Composite body) {
////		eKPI1.1a
//		Section eKPI11aSection = toolkit.createSection(body, Section.TITLE_BAR);
//		eKPI11aSection.setText(Messages.get().TEXT_EKPI11a);
//		Composite eKPI11aComposite	=	toolkit.createComposite(eKPI11aSection, SWT.WRAP);
//		eKPI11aComposite.setLayout(new GridLayout(2, true));
//
//		toolkit.createLabel(eKPI11aComposite, Messages.get().TEXT_PLANT_EXPEDITURE_FIGURE);
//		textPlantExpeditureFigureHeating = toolkit.createText(eKPI11aComposite, ""); //$NON-NLS-1$
//		textPlantExpeditureFigureHeating.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
////		toolkit.createLabel(eKPI11aComposite, Messages.get().TEXT_NET_ENERGY_HEATING+" [kWh]");
////		textNetEnergyHeating = toolkit.createText(eKPI11aComposite, "", SWT.READ_ONLY); //$NON-NLS-1$
////		textNetEnergyHeating.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////		
////		Label sumLabel = toolkit.createLabel(eKPI11aComposite, Messages.get().TEXT_FINAL_ENERGY_HEATING+" [kWh]"); //$NON-NLS-1$
////		sumLabel.setFont(AppearanceFactory.DEFAULT_FONT_TITLE2_BOLD);
////		textFinalEnergyHeating = toolkit.createText(eKPI11aComposite, "", SWT.READ_ONLY); //$NON-NLS-1$
////		textFinalEnergyHeating.setFont(AppearanceFactory.DEFAULT_FONT_TITLE2_BOLD);
////		textFinalEnergyHeating.setEditable(false);
////		textFinalEnergyHeating.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		eKPI11aSection.setClient(eKPI11aComposite);
//		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
//		gridData.horizontalSpan = 2;
//		eKPI11aSection.setLayoutData(gridData);
//	}
//}
