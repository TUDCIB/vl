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
//import org.eclipse.swt.widgets.Label;
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
//public class CoolingTabItem extends TabItem {
//	
//
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -3638758311374902370L;
//	private Text	textFinalEnergyCooling;
//	private Text	textNetEnergyCooling;
//	private Text	textPlantExpeditureFigureCooling;
//	private FormToolkit toolkit;
//	private EnergyKeyPerformanceIndicatorController controller;
//	private EnergyKeyPerformanceIndicatorsWrapper eKPIWrapper;
//	
//
//	public CoolingTabItem(TabFolder parent, int style) {
//		super(parent, style);
//		setText(Messages.get().TEXT_COOLING);
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
//		form.setText(Messages.get().TEXT_COOLING);
//		Composite body = form.getBody();
//		body.setLayout(new GridLayout(2, true));
//
////		eKPI1.2
//		createEKPI12Section(body);
//		
//		Label separator = toolkit.createSeparator(body, SWT.SEPARATOR | SWT.HORIZONTAL);
//		GridData gridDataSeparator = new GridData(GridData.FILL_HORIZONTAL);
//		gridDataSeparator.horizontalSpan = 2;
//		separator.setLayoutData(gridDataSeparator);
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
//        IObservableValue textPlantExpeditureFigureCoolingObserveWidget = SWTObservables.observeText(textPlantExpeditureFigureCooling, SWT.FocusOut);
//        IObservableValue plantExpeditureFigureCoolingObserveValue = BeansObservables.observeValue(viewModel, "plantExpeditureFigureCooling"); //$NON-NLS-1$
//        bindingContext.bindValue(textPlantExpeditureFigureCoolingObserveWidget, plantExpeditureFigureCoolingObserveValue, null, null);
//        
////        IObservableValue eKPI12TextObserveWidget = SWTObservables.observeText(textFinalEnergyCooling, SWT.FocusOut);
////        IObservableValue eKPI12ObserveValue = BeansObservables.observeValue(viewModel, "eKPI12");
////        bindingContext.bindValue(eKPI12TextObserveWidget, eKPI12ObserveValue, null, null);
//
//        return bindingContext;
//	}
//	
//	private void createEKPI12Section(Composite body) {
////		eKPI1.2
//		Section eKPI11aSection = toolkit.createSection(body, Section.TITLE_BAR);
//		eKPI11aSection.setText(Messages.get().TEXT_EKPI12);
//		Composite eKPI12Composite	=	toolkit.createComposite(eKPI11aSection, SWT.WRAP);
//		eKPI12Composite.setLayout(new GridLayout(2, true));
//		
//		toolkit.createLabel(eKPI12Composite, Messages.get().TEXT_PLANT_EXPEDITURE_FIGURE);
//		textPlantExpeditureFigureCooling = toolkit.createText(eKPI12Composite, ""); //$NON-NLS-1$
//		textPlantExpeditureFigureCooling.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
////		toolkit.createLabel(eKPI12Composite, Messages.get().TEXT_NET_ENERGY_COOLING+" [kWh]");
////		textNetEnergyCooling = toolkit.createText(eKPI12Composite, ""+eKPIWrapper.getNetEnergyCooling(), SWT.READ_ONLY); //$NON-NLS-1$
////		textNetEnergyCooling.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////		
////		Label sumLabel = toolkit.createLabel(eKPI12Composite, Messages.get().TEXT_FINAL_ENERGY_COOLING+" [kWh]");
////		sumLabel.setFont(AppearanceFactory.DEFAULT_FONT_TITLE2_BOLD);
////		textFinalEnergyCooling = toolkit.createText(eKPI12Composite, "", SWT.READ_ONLY);
////		textFinalEnergyCooling.setFont(AppearanceFactory.DEFAULT_FONT_TITLE2_BOLD);
////		textFinalEnergyCooling.setEditable(false);
////		textFinalEnergyCooling.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		eKPI11aSection.setClient(eKPI12Composite);
//		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
//		gridData.horizontalSpan = 2;
//		eKPI11aSection.setLayoutData(gridData);
//	}
//}
