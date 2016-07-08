//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.eclipse.core.databinding.DataBindingContext;
//import org.eclipse.jface.action.Action;
//import org.eclipse.jface.window.Window;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Listener;
//import org.eclipse.swt.widgets.TabFolder;
//import org.eclipse.swt.widgets.TabItem;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.forms.widgets.Form;
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.ScrolledForm;
//
//import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
//import de.tudresden.bau.cib.vl.core.simulation.energy.postprocessing.EnergyKeyPerformanceIndicatorCalculator;
//import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.EnergyKeyPerformanceIndicatorController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.model.EnergyKeyPerformanceIndicatorsWrapper;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.CombustibleEditor;
//
//public class GreenhouseGasEmissionTabItem extends TabItem {
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -1720198237766978614L;
//	
////	private Text textFinalEnergyHeatingAndCooling;
////	
////	private Map<String, Text> combustibleIdToFinalEnergyOfEnergyResource;
////	private Map<String, Text> combustibleIdToCO2OfEnergyResource;
////	private Map<String, Text> combustibleIdToSO2OfEnergyResource;
////	private Map<String, Text> combustibleIdToNOXOfEnergyResource;
//	
//	private FormToolkit toolkit;
//	
//	private EnergyKeyPerformanceIndicatorController controller;
//	private EnergyKeyPerformanceIndicatorsWrapper eKPIWrapper;
//	
//
//	public GreenhouseGasEmissionTabItem(TabFolder parent, int style) {
//		super(parent, style);
//		setText(Messages.get().TEXT_GREENHOUSE_GAS_EMISSION);
//		controller = EnergyKeyPerformanceIndicatorController.getInstance();
//	}
//	
//	public void createContent(TabFolder parent) {
//		ScrolledForm form = createContainer(parent);
//	    setControl(form);
//	    
//	    initGreenhouseGasEmissionDataBindings();
//	}
//	
//	private ScrolledForm createContainer(TabFolder parent) {
//		toolkit = new FormToolkit(parent.getDisplay());
//		ScrolledForm scrolledForm = toolkit.createScrolledForm(parent);
//		Form form = scrolledForm.getForm();
//		toolkit.decorateFormHeading(form);
//		form.setText(Messages.get().TEXT_GREENHOUSE_GAS_EMISSION);
//		
//		eKPIWrapper = controller.getEKPIViewModel();
//		
////		Composite header = new Composite(form.getHead(), SWT.NONE);
////		header.setLayout(new FillLayout(SWT.HORIZONTAL));
////		Label eKPIHeatingAndCoolingLabel = toolkit.createLabel(header, Messages.get().TEXT_FINAL_ENERGY+" [kWh]");
////		eKPIHeatingAndCoolingLabel.setFont(AppearanceFactory.DEFAULT_FONT_TITLE2_BOLD);
////		textFinalEnergyHeatingAndCooling = toolkit.createText(header, ""+(eKPIWrapper.geteKPI11a()+eKPIWrapper.geteKPI12()));	
////		form.setHeadClient(header);
//		
//		final Composite body = form.getBody();
//		
//		form.getToolBarManager().add(new Action(Messages.get().TEXT_CHOOSE_COMBUSTIBLES+"...", SWT.PUSH) { 
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -7782638412280769127L;
//
//			@Override
//			public void run() {  
//				List<Combustible> combustibles = createCombustibleDialog();
//				if(combustibles != null) {
//					for(Control child : body.getChildren()) child.dispose();
//					eKPIWrapper.setAssignedCombustibles(combustibles);
//					createEnergyResourceContainer(combustibles, body);
//				}
//				super.run();
//			}
//		});	
//		form.getToolBarManager().update(true);		
//		
//		List<Combustible> renderedCombustibles = new ArrayList<Combustible>();
//		List<Combustible> combustibles = controller.listCombustibles();
//		List<String> combustibleIds = controller.getEKPIViewModel().getCombustibleIds();
//		for(Combustible c : combustibles) if(combustibleIds.contains(c.getId())) renderedCombustibles.add(c);
//		
//		createEnergyResourceContainer(renderedCombustibles, body);
//
//		
////		Label separator = toolkit.createSeparator(body, SWT.SEPARATOR | SWT.HORIZONTAL);
////		GridData gridDataSeparator = new GridData(GridData.FILL_HORIZONTAL);
////		gridDataSeparator.horizontalSpan = 2;
////		separator.setLayoutData(gridDataSeparator);
////
////		Image imageFormula = AppearanceFactory.getInstance().getImageDescriptor(EnergyViewService.SYMBOLIC_NAME, 
////				EnergyViewService.ICONS_PATH+"eKPI_2.1.png").createImage();
////		Label labelImgFormula = toolkit.createLabel(body, "");
////		labelImgFormula.setImage(imageFormula);
////		GridData gridDataLabelImgFormula = new GridData(GridData.FILL_HORIZONTAL);
////		gridDataLabelImgFormula.horizontalSpan = 2;
////		labelImgFormula.setLayoutData(gridDataLabelImgFormula);
//		
//		return scrolledForm;
//	}
//	
//	private List<Combustible> createCombustibleDialog() {
//		CombustibleEditor dialog = new CombustibleEditor(Display.getCurrent().getActiveShell(), 
//				controller.getCombustibleFile());
//		List<String> combustibleIds = controller.getEKPIViewModel().getCombustibleIds();
//		dialog.setSelectedCombustibles(combustibleIds);
//		if (dialog.open() == Window.OK) {
//			return dialog.getSelectedCombustibles();
//		} 
//		return null;
//	}
//	
//	private void createEnergyResourceContainer(final List<Combustible> combustibles, Composite container) {
//		EnergyKeyPerformanceIndicatorsWrapper wrapper = controller.getEKPIViewModel();
//		double finalHeating = wrapper.geteKPI11a();
//		double finalCooling = wrapper.geteKPI12();
//		Map<String,Double> percentagePerHeating = wrapper.getPercentagePerHeatingCombustible();
//		Map<String,Double> percentagePerCooling = wrapper.getPercentagePerCoolingCombustible();
//		
//		GridLayout layout = new GridLayout(4, false);
//		layout.horizontalSpacing = 30;
//		layout.marginHeight = 10;
//		layout.marginBottom = 10;
//		layout.marginLeft = 10;
//		layout.marginRight = 10;
//		layout.verticalSpacing = 5;
//		container.setLayout(layout);
//		
////		combustibleIdToFinalEnergyOfEnergyResource 			= new HashMap<String, Text>();
////		combustibleIdToCO2OfEnergyResource 					= new HashMap<String, Text>();
////		combustibleIdToSO2OfEnergyResource 					= new HashMap<String, Text>();
////		combustibleIdToNOXOfEnergyResource 					= new HashMap<String, Text>();
//		
//		for(int i = 0; i < combustibles.size(); i++) {
//			Combustible combustible = combustibles.get(i);
//			final String cId = combustible.getId();
//			double percentageHeating = percentagePerHeating.containsKey(cId) ? percentagePerHeating.get(cId) : 0.0;
//			double percentageCooling = percentagePerCooling.containsKey(cId) ? percentagePerCooling.get(cId) : 0.0;
//			
//			Label numberLabel = toolkit.createLabel(container, i+") "+combustible.getName());
//			numberLabel.setFont(AppearanceFactory.DEFAULT_FONT_TITLE2_BOLD);
//			numberLabel.setLayoutData(new GridData(0,0,true,true,5,1));
//
////			1
//			toolkit.createLabel(container, Messages.get().TEXT_PERCENTAGE_HEATING);
////			2
//			final Text textHeatingPercentageFactor = toolkit.createText(container, ""+percentageHeating);
//			textHeatingPercentageFactor.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////			combustibleIdToHeatingPercentageOfEnergyResource.put(cId, textHeatingPercentageFactor);
//			textHeatingPercentageFactor.addListener(SWT.FocusOut, new Listener() {
//
//				/**
//				 * 
//				 */
//				private static final long serialVersionUID = -8300472578507475478L;
//
//				@Override
//				public void handleEvent(Event evt) {
//					double heatingPercentageValue = Double.valueOf(textHeatingPercentageFactor.getText());
//					Map<String, Double> percentageHeating = eKPIWrapper.getPercentagePerHeatingCombustible();
//					percentageHeating.put(cId, heatingPercentageValue);
//					eKPIWrapper.setPercentagePerHeatingCombustible(percentageHeating);
//					
////					double coolingPercentageValue = 0.0;
////					
////					Map<String,Double> percentageCooling = eKPIWrapper.getPercentagePerCoolingCombustible();
////					if(percentageCooling != null) {
////						coolingPercentageValue = percentageCooling.containsKey(cId) ? percentageCooling.get(cId) : 0.0;
////					}
////					
////					setTextFields(cId, heatingPercentageValue, coolingPercentageValue, combustibles);
//				}
//			});
////			3
//			toolkit.createLabel(container, "X");
////			4
//			Label resourceName = toolkit.createLabel(container, combustible.getName()+" [kWh]");
//			resourceName.setFont(AppearanceFactory.DEFAULT_FONT_TITLE2_BOLD);
//			
//			double finalEnergyWithConversionFactor = ((percentageHeating/100) * finalHeating) + ((percentageCooling/100) * finalCooling);
//			finalEnergyWithConversionFactor = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(finalEnergyWithConversionFactor);
////			5
////			Text textFinalAndConversionFactor = toolkit.createText(container, ""+finalEnergyWithConversionFactor, SWT.READ_ONLY);
////			textFinalAndConversionFactor.setFont(AppearanceFactory.DEFAULT_FONT_TITLE2_BOLD);
////			textFinalAndConversionFactor.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////			combustibleIdToFinalEnergyOfEnergyResource.put(cId, textFinalAndConversionFactor);
//			
////			1
//			toolkit.createLabel(container, Messages.get().TEXT_PERCENTAGE_COOLING);
////			2
//			final Text textCoolingPercentageFactor = toolkit.createText(container, ""+percentageCooling);
//			textCoolingPercentageFactor.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////			combustibleIdToCoolingPercentageOfEnergyResource.put(cId, textCoolingPercentageFactor);
//			textCoolingPercentageFactor.addListener(SWT.FocusOut, new Listener() {
//
//
//				/**
//				 * 
//				 */
//				private static final long serialVersionUID = -2878331401895762534L;
//
//				@Override
//				public void handleEvent(Event evt) {
//					double coolingPercentageValue = Double.valueOf(textCoolingPercentageFactor.getText());
//					Map<String, Double> percentageCooling = eKPIWrapper.getPercentagePerCoolingCombustible();
//					percentageCooling.put(cId, coolingPercentageValue);
//					eKPIWrapper.setPercentagePerCoolingCombustible(percentageCooling);
//					
////					double heatingPercentageValue = 0.0;
////					
////					Map<String,Double> percentageHeating = eKPIWrapper.getPercentagePerHeatingCombustible();
////					if(percentageHeating != null) {
////						heatingPercentageValue = percentageHeating.containsKey(cId) ? percentageHeating.get(cId) : 0.0;
////					}
////					
////					setTextFields(cId, heatingPercentageValue, coolingPercentageValue, combustibles);
//				}
//			});
////			3
//			toolkit.createLabel(container, "");
////			4
//			Label co2Label = toolkit.createLabel(container, "g CO2/kWh:  "+combustible.getEmissionCO2());
//			co2Label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////			5
////			double CO2 = combustible.getEmissionCO2()*finalEnergyWithConversionFactor;
////			CO2 = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(CO2);
////			Text textCO2 = toolkit.createText(container, ""+CO2, SWT.READ_ONLY);
////			textCO2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////			combustibleIdToCO2OfEnergyResource.put(cId, textCO2);
//			
////			1
//			toolkit.createLabel(container, "");
////			2
//			toolkit.createLabel(container, "");
////			3
//			toolkit.createLabel(container, "");
////			4
//			Label so2Label = toolkit.createLabel(container, "g SO2/kWh:  "+combustible.getEmissionSO2());
//			so2Label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////			5
////			double SO2 = combustible.getEmissionSO2()*finalEnergyWithConversionFactor;
////			SO2 = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(SO2);
////			Text textSO2 = toolkit.createText(container, ""+SO2, SWT.READ_ONLY);
////			textSO2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////			combustibleIdToSO2OfEnergyResource.put(cId, textSO2);
//			
////			1
//			toolkit.createLabel(container, "");
////			2
//			toolkit.createLabel(container, "");
////			3
//			toolkit.createLabel(container, "");
////			4
//			Label noxLabel = toolkit.createLabel(container, "g NOX/kWh:  "+combustible.getEmissionNOX());
//			noxLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////			5
////			double NOX = combustible.getEmissionNOX()*finalEnergyWithConversionFactor;
////			NOX = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(NOX);
////			Text textNOX = toolkit.createText(container, ""+NOX, SWT.READ_ONLY);
////			textNOX.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////			combustibleIdToNOXOfEnergyResource.put(cId, textNOX);
//		}
//		container.pack(true);
//		container.layout(true);
//	}
//
//	private DataBindingContext initGreenhouseGasEmissionDataBindings() {
//        DataBindingContext bindingContext = new DataBindingContext();
////        EnergyKeyPerformanceIndicatorsWrapper viewModel = controller.getEKPIViewModel();
////
////        IObservableValue eKPI11aTextObserveWidget = SWTObservables.observeText(textFinalEnergyHeatingAndCooling, SWT.FocusOut);
////        IObservableValue eKPI11aObserveValue = BeansObservables.observeValue(viewModel, "eKPI11a");
////        IObservableValue eKPI12ObserveValue = BeansObservables.observeValue(viewModel, "eKPI12");
////        final Double finalEnergyHeating = (Double) eKPI11aObserveValue.getValue();
////        final Double finalEnergyCooling = (Double) eKPI12ObserveValue.getValue();
////        IObservableValue newVal = new ComputedValue() {
////			
////			@Override
////			protected Object calculate() {
////				return finalEnergyHeating + finalEnergyCooling;
////			}
////		};
////        
////        bindingContext.bindValue(eKPI11aTextObserveWidget, newVal, null, null);
//
//        return bindingContext;
//	}
//	
////	private void setTextFields(String combustibleId, double percentageHeating, double percentageCooling, List<Combustible> combustibles) {
////    	double finalHeating = eKPIWrapper.geteKPI11a();
////    	double finalCooling = eKPIWrapper.geteKPI12();
////		double finalEnergyWithConversionFactor = ((percentageHeating/100) * finalHeating) + ((percentageCooling/100) * finalCooling);
////		combustibleIdToFinalEnergyOfEnergyResource.get(combustibleId).setText(""+finalEnergyWithConversionFactor);
////		
////		double conversionFactorCO2 = 0.0;
////		double conversionFactorSO2 = 0.0;
////		double conversionFactorNOX = 0.0;
////		
//////		CO2, SO2 & NOX
////		for(Iterator<Combustible> iter = combustibles.iterator(); iter.hasNext();) {
////			Combustible c = iter.next();
////			if(c.getId().equals(combustibleId)) {
////				conversionFactorCO2 = c.getEmissionCO2();
////				conversionFactorSO2 = c.getEmissionSO2();
////				conversionFactorNOX = c.getEmissionNOX();
////			}
////		}
////		
////		double CO2 = conversionFactorCO2 * finalEnergyWithConversionFactor;
////		double SO2 = conversionFactorSO2 * finalEnergyWithConversionFactor;
////		double NOX = conversionFactorNOX * finalEnergyWithConversionFactor;
////		
////		combustibleIdToCO2OfEnergyResource.get(combustibleId).setText(""+CO2);
////		combustibleIdToSO2OfEnergyResource.get(combustibleId).setText(""+SO2);
////		combustibleIdToNOXOfEnergyResource.get(combustibleId).setText(""+NOX);
////	}
//}
