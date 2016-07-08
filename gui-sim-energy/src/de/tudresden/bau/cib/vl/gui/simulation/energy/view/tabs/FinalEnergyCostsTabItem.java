//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs;
//
//import java.util.List;
//
//import org.eclipse.core.databinding.DataBindingContext;
//import org.eclipse.core.databinding.UpdateValueStrategy;
//import org.eclipse.core.databinding.beans.BeansObservables;
//import org.eclipse.core.databinding.conversion.IConverter;
//import org.eclipse.core.databinding.observable.value.IObservableValue;
//import org.eclipse.jface.action.Action;
//import org.eclipse.jface.databinding.swt.SWTObservables;
//import org.eclipse.jface.window.Window;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.TabFolder;
//import org.eclipse.swt.widgets.TabItem;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.ScrolledForm;
//import org.eclipse.ui.forms.widgets.Section;
//
//import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.EnergyKeyPerformanceIndicatorController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.model.EnergyKeyPerformanceIndicatorsWrapper;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.CombustibleEditor;
//
///**
// * TODO rename it to LifeCycleCostTabItem
// *
// * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
// * @author <a href="mailto:Mario.Guertler@tu-dresden.de">Mario Guertler</a>
// */
//public class FinalEnergyCostsTabItem extends TabItem {
//
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -8451449654865610338L;
//	
//	private EnergyKeyPerformanceIndicatorController controller;
//	private EnergyKeyPerformanceIndicatorsWrapper eKPIWrapper;
//	
//	Text textPriceIncrease, textDiscountRate, textYears;
//	Text textNonEnergyPriceIncrease, textNonEnergyDiscountRate, textNonEnergyYears;
//	Text textAreaWindows, textWindowTimesPerYear, textWindowPerformanceValue, textWindowHourlyRate;
//	Text textInvestmentCosts, textWindowCosts, textBuildingShellCosts; 
////	Text textEnergyRelatedCosts;
//	Text textBuildingShellArea, textBuildingShellTimesPerYear, textBuildingShellPerformanceValue, textBuildingShellHourlyRate;
//	Text textMaintenancePercenatge, textMaintenanceResult;
////	Text textFinalEnergyCosts;
//	
//
//	public FinalEnergyCostsTabItem(TabFolder parent, int style) {
//		super(parent, style);
//		setText(Messages.getString("FinalEnergyCostsTabItem_0")); //$NON-NLS-1$
//		controller = EnergyKeyPerformanceIndicatorController.getInstance();
//	}
//	
//	public void createContent(TabFolder parent) {
//		ScrolledForm form = createContainer(parent);
//	    setControl(form);
//	    initDataBindings();
//	}
//	
//	private ScrolledForm createContainer(TabFolder parent) {
//		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
//		ScrolledForm  form = toolkit.createScrolledForm(parent);
//		form.setText(Messages.getString("FinalEnergyCostsTabItem_0")); //$NON-NLS-1$
//		Composite body = form.getBody();
//		body.setLayout(new GridLayout(1, false));
//		
//		eKPIWrapper = controller.getEKPIViewModel();
//		
//		
//		form.getToolBarManager().add(new Action(Messages.get().TEXT_CHOOSE_COMBUSTIBLES+"...", SWT.PUSH) {  //$NON-NLS-1$
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -7782638412280769127L;
//
//			@Override
//			public void run() {		   
//				List<Combustible> combustibles = createCombustibleDialog();
//				if(combustibles != null) {
//					eKPIWrapper.setAssignedCombustibles(combustibles);
//				}
//				super.run();
//			}
//		});	
//		form.getToolBarManager().update(true);		
//
//
//		Section investmentCostsSection 			= 	toolkit.createSection(body, Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);
//		investmentCostsSection.setText(Messages.getString("FinalEnergyCostsTabItem_3")); //$NON-NLS-1$
//		Composite investmentCostsComposite		=	toolkit.createComposite(investmentCostsSection, SWT.WRAP);
//		investmentCostsComposite.setLayout(new GridLayout(2, true));
//		toolkit.createLabel(investmentCostsComposite, Messages.getString("FinalEnergyCostsTabItem_4")); //$NON-NLS-1$
//		textInvestmentCosts 				= 	toolkit.createText(investmentCostsComposite, "", SWT.READ_ONLY); //$NON-NLS-1$
//		textInvestmentCosts.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		investmentCostsSection.setClient(investmentCostsComposite);
//		investmentCostsSection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//
//		Section energyRelatedCostsSection 		= 	toolkit.createSection(body, Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);
//		energyRelatedCostsSection.setText(Messages.getString("FinalEnergyCostsTabItem_6")); //$NON-NLS-1$
//		Composite energyRelatedCostsComposite	=	toolkit.createComposite(energyRelatedCostsSection, SWT.WRAP);
//		energyRelatedCostsComposite.setLayout(new GridLayout(2, true));
//		toolkit.createLabel(energyRelatedCostsComposite, Messages.getString("FinalEnergyCostsTabItem_7")); //$NON-NLS-1$
//		textPriceIncrease = toolkit.createText(energyRelatedCostsComposite, ""); //$NON-NLS-1$
//		textPriceIncrease.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		toolkit.createLabel(energyRelatedCostsComposite, Messages.getString("FinalEnergyCostsTabItem_9")); //$NON-NLS-1$
//		textDiscountRate = toolkit.createText(energyRelatedCostsComposite, ""); //$NON-NLS-1$
//		textDiscountRate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		toolkit.createLabel(energyRelatedCostsComposite, Messages.getString("FinalEnergyCostsTabItem_11")); //$NON-NLS-1$
//		textYears = toolkit.createText(energyRelatedCostsComposite, ""); //$NON-NLS-1$
//		textYears.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////		toolkit.createLabel(energyRelatedCostsComposite, "Energy-related costs");
////		textEnergyRelatedCosts = toolkit.createText(energyRelatedCostsComposite, "", SWT.READ_ONLY);
////		textEnergyRelatedCosts.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		energyRelatedCostsSection.setClient(energyRelatedCostsComposite);
//		energyRelatedCostsSection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		Section nonEnergyRelatedCostsSection 		= 	toolkit.createSection(body, Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);
//		nonEnergyRelatedCostsSection.setText(Messages.getString("FinalEnergyCostsTabItem_8")); //$NON-NLS-1$
//		Composite nonEnergyRelatedCostsComposite	=	toolkit.createComposite(nonEnergyRelatedCostsSection, SWT.WRAP);
//		nonEnergyRelatedCostsComposite.setLayout(new GridLayout(2, true));
//		toolkit.createLabel(nonEnergyRelatedCostsComposite, Messages.getString("FinalEnergyCostsTabItem_7")); //$NON-NLS-1$
//		textNonEnergyPriceIncrease = toolkit.createText(nonEnergyRelatedCostsComposite, ""); //$NON-NLS-1$
//		textNonEnergyPriceIncrease.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		toolkit.createLabel(nonEnergyRelatedCostsComposite, Messages.getString("FinalEnergyCostsTabItem_9")); //$NON-NLS-1$
//		textNonEnergyDiscountRate = toolkit.createText(nonEnergyRelatedCostsComposite, ""); //$NON-NLS-1$
//		textNonEnergyDiscountRate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		toolkit.createLabel(nonEnergyRelatedCostsComposite, Messages.getString("FinalEnergyCostsTabItem_11")); //$NON-NLS-1$
//		textNonEnergyYears = toolkit.createText(nonEnergyRelatedCostsComposite, ""); //$NON-NLS-1$
//		textNonEnergyYears.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		nonEnergyRelatedCostsSection.setClient(nonEnergyRelatedCostsComposite);
//		nonEnergyRelatedCostsSection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//	
//		Section nonEnergyRelatedCostsSectionWindows 	= 	toolkit.createSection(body, Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);
//		nonEnergyRelatedCostsSectionWindows.setText(Messages.getString("FinalEnergyCostsTabItem_13")); //$NON-NLS-1$
//		Composite energyCostsCompositeWindows			=	toolkit.createComposite(nonEnergyRelatedCostsSectionWindows, SWT.WRAP);
//		energyCostsCompositeWindows.setLayout(new GridLayout(2, true));
//		
//		toolkit.createLabel(energyCostsCompositeWindows, Messages.getString("FinalEnergyCostsTabItem_14")); //$NON-NLS-1$
//		textAreaWindows = toolkit.createText(energyCostsCompositeWindows, ""); //$NON-NLS-1$
//		textAreaWindows.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//
//		toolkit.createLabel(energyCostsCompositeWindows, Messages.getString("FinalEnergyCostsTabItem_16")); //$NON-NLS-1$
//		textWindowTimesPerYear = toolkit.createText(energyCostsCompositeWindows, ""); //$NON-NLS-1$
//		textWindowTimesPerYear.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//
//		toolkit.createLabel(energyCostsCompositeWindows, Messages.getString("FinalEnergyCostsTabItem_18")); //$NON-NLS-1$
//		textWindowPerformanceValue = toolkit.createText(energyCostsCompositeWindows, ""); //$NON-NLS-1$
//		textWindowPerformanceValue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//
//		toolkit.createLabel(energyCostsCompositeWindows, Messages.getString("FinalEnergyCostsTabItem_20")); //$NON-NLS-1$
//		textWindowHourlyRate = toolkit.createText(energyCostsCompositeWindows, ""); //$NON-NLS-1$
//		textWindowHourlyRate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		toolkit.createLabel(energyCostsCompositeWindows, Messages.getString("FinalEnergyCostsTabItem_22")); //$NON-NLS-1$
//		textWindowCosts = toolkit.createText(energyCostsCompositeWindows, "", SWT.READ_ONLY); //$NON-NLS-1$
//		textWindowCosts.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		nonEnergyRelatedCostsSectionWindows.setClient(energyCostsCompositeWindows);
//		nonEnergyRelatedCostsSectionWindows.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		Section nonEnergyRelatedCostsSectionBuildingShell 	= 	toolkit.createSection(body, Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);
//		nonEnergyRelatedCostsSectionBuildingShell.setText(Messages.getString("FinalEnergyCostsTabItem_24")); //$NON-NLS-1$
//		Composite energyCostsCompositeBuildingShell			=	toolkit.createComposite(nonEnergyRelatedCostsSectionBuildingShell, SWT.WRAP);
//		energyCostsCompositeBuildingShell.setLayout(new GridLayout(2, true));
//		
//		toolkit.createLabel(energyCostsCompositeBuildingShell, Messages.getString("FinalEnergyCostsTabItem_25")); //$NON-NLS-1$
//		textBuildingShellArea = toolkit.createText(energyCostsCompositeBuildingShell, ""); //$NON-NLS-1$
//		textBuildingShellArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//
//		toolkit.createLabel(energyCostsCompositeBuildingShell, Messages.getString("FinalEnergyCostsTabItem_27")); //$NON-NLS-1$
//		textBuildingShellTimesPerYear = toolkit.createText(energyCostsCompositeBuildingShell, ""); //$NON-NLS-1$
//		textBuildingShellTimesPerYear.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//
//		toolkit.createLabel(energyCostsCompositeBuildingShell, Messages.getString("FinalEnergyCostsTabItem_29")); //$NON-NLS-1$
//		textBuildingShellPerformanceValue = toolkit.createText(energyCostsCompositeBuildingShell, ""); //$NON-NLS-1$
//		textBuildingShellPerformanceValue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//
//		toolkit.createLabel(energyCostsCompositeBuildingShell, Messages.getString("FinalEnergyCostsTabItem_31")); //$NON-NLS-1$
//		textBuildingShellHourlyRate = toolkit.createText(energyCostsCompositeBuildingShell, ""); //$NON-NLS-1$
//		textBuildingShellHourlyRate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		toolkit.createLabel(energyCostsCompositeBuildingShell, Messages.getString("FinalEnergyCostsTabItem_33")); //$NON-NLS-1$
//		textBuildingShellCosts = toolkit.createText(energyCostsCompositeBuildingShell, "", SWT.READ_ONLY); //$NON-NLS-1$
//		textBuildingShellCosts.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		nonEnergyRelatedCostsSectionBuildingShell.setClient(energyCostsCompositeBuildingShell);
//		nonEnergyRelatedCostsSectionBuildingShell.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
//		
//		
//		Section nonEnergyRelatedCostsSectionMaintenance 	= 	toolkit.createSection(body, Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);
//		nonEnergyRelatedCostsSectionMaintenance.setText(Messages.getString("FinalEnergyCostsTabItem_34")); //$NON-NLS-1$
//		Composite energyCostsCompositeMaintenance			=	toolkit.createComposite(nonEnergyRelatedCostsSectionMaintenance, SWT.WRAP);
//		energyCostsCompositeMaintenance.setLayout(new GridLayout(2, true));
//		
//		toolkit.createLabel(energyCostsCompositeMaintenance, Messages.getString("FinalEnergyCostsTabItem_35")); //$NON-NLS-1$
//		textMaintenancePercenatge = toolkit.createText(energyCostsCompositeMaintenance, ""); //$NON-NLS-1$
//		textMaintenancePercenatge.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//
//		toolkit.createLabel(energyCostsCompositeMaintenance, Messages.getString("FinalEnergyCostsTabItem_36")); //$NON-NLS-1$
//		textMaintenanceResult = toolkit.createText(energyCostsCompositeMaintenance, "", SWT.READ_ONLY); //$NON-NLS-1$
//		textMaintenanceResult.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		nonEnergyRelatedCostsSectionMaintenance.setClient(energyCostsCompositeMaintenance);
//		nonEnergyRelatedCostsSectionMaintenance.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
//	
//		
////		Section nonEnergyRelatedCostsSectionBuildingShell 	= 	toolkit.createSection(body, Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);
////		nonEnergyRelatedCostsSectionBuildingShell.setText("Non-energy related costs - building shell");
////		Composite energyCostsCompositeBuildingShell			=	toolkit.createComposite(nonEnergyRelatedCostsSectionBuildingShell, SWT.WRAP);
////		energyCostsCompositeBuildingShell.setLayout(new GridLayout(2, true));
////		
////		toolkit.createLabel(energyCostsCompositeBuildingShell, "Area of building shell");
////		textBuildingShellArea = toolkit.createText(energyCostsCompositeBuildingShell, "");
////		textBuildingShellArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////
////		toolkit.createLabel(energyCostsCompositeBuildingShell, "Times per year");
////		textBuildingShellTimesPerYear = toolkit.createText(energyCostsCompositeBuildingShell, "");
////		textBuildingShellTimesPerYear.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////
////		toolkit.createLabel(energyCostsCompositeBuildingShell, "Performance value");
////		textBuildingShellPerformanceValue = toolkit.createText(energyCostsCompositeBuildingShell, "");
////		textBuildingShellPerformanceValue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////
////		toolkit.createLabel(energyCostsCompositeBuildingShell, "Hourly rate");
////		textBuildingShellHourlyRate = toolkit.createText(energyCostsCompositeBuildingShell, "");
////		textBuildingShellHourlyRate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////		
////		toolkit.createLabel(energyCostsCompositeBuildingShell, "Sum for building shell");
////		textBuildingShellCosts = toolkit.createText(energyCostsCompositeBuildingShell, "");
////		textBuildingShellCosts.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////		
////		nonEnergyRelatedCostsSectionBuildingShell.setClient(energyCostsCompositeBuildingShell);
////		nonEnergyRelatedCostsSectionBuildingShell.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		
//		Label separator = toolkit.createSeparator(body, SWT.SEPARATOR | SWT.HORIZONTAL);
//		GridData gridDataSeparator = new GridData(GridData.FILL_HORIZONTAL);
//		gridDataSeparator.horizontalSpan = 2;
//		separator.setLayoutData(gridDataSeparator);
//		
////		Section finalEnergyCostsSection 				= 	toolkit.createSection(body, Section.TITLE_BAR | Section.EXPANDED);
////		finalEnergyCostsSection.setText("Final energy costs");
////		Composite finalEnergyCostsComposite				=	toolkit.createComposite(finalEnergyCostsSection, SWT.WRAP);
////		finalEnergyCostsComposite.setLayout(new GridLayout(2, true));
////		
////		Label finalEnergyCostsLabel = toolkit.createLabel(finalEnergyCostsComposite, "Sum of final energy costs");
////		finalEnergyCostsLabel.setFont(AppearanceFactory.DEFAULT_FONT_TITLE1_BOLD);
////		textFinalEnergyCosts = toolkit.createText(finalEnergyCostsComposite, "");
////		textFinalEnergyCosts.setFont(AppearanceFactory.DEFAULT_FONT_TITLE1_BOLD);
////		textFinalEnergyCosts.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
////
////		finalEnergyCostsSection.setClient(finalEnergyCostsComposite);
////		finalEnergyCostsSection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		return form;
//	}
//	
//	private DataBindingContext initDataBindings() {
//        DataBindingContext bindingContext = new DataBindingContext();
//        EnergyKeyPerformanceIndicatorsWrapper viewModel = controller.getEKPIViewModel();
////      price increase
//        IObservableValue textPriceIncreaseObserveWidget 	= SWTObservables.observeText(textPriceIncrease, SWT.FocusOut);
//        IObservableValue priceIncreaseObserveValue 			= BeansObservables.observeValue(viewModel, "priceIncrease"); //$NON-NLS-1$
//        bindingContext.bindValue(textPriceIncreaseObserveWidget, priceIncreaseObserveValue, null, null);
////      discount rate
//        IObservableValue textDiscountRateObserveWidget 		= SWTObservables.observeText(textDiscountRate, SWT.FocusOut);
//        IObservableValue discountRateObserveValue 			= BeansObservables.observeValue(viewModel, "discountRate"); //$NON-NLS-1$
//        bindingContext.bindValue(textDiscountRateObserveWidget, discountRateObserveValue, null, null);
////      years
//        IObservableValue textYearsRateObserveWidget 		= SWTObservables.observeText(textYears, SWT.FocusOut);
//        IObservableValue yearsObserveValue 					= BeansObservables.observeValue(viewModel, "years"); //$NON-NLS-1$
//        bindingContext.bindValue(textYearsRateObserveWidget, yearsObserveValue, null, null);
//        
////      non-energy price increase
//        IObservableValue textNonEnergyPriceIncreaseObserveWidget 	= SWTObservables.observeText(textNonEnergyPriceIncrease, SWT.FocusOut);
//        IObservableValue nonEnergyPriceIncreaseObserveValue 			= BeansObservables.observeValue(viewModel, "nonEnergyPriceIncrease"); //$NON-NLS-1$
//        bindingContext.bindValue(textNonEnergyPriceIncreaseObserveWidget, nonEnergyPriceIncreaseObserveValue, null, null);
////      non-energy discount rate
//        IObservableValue textNonEnergyDiscountRateObserveWidget 		= SWTObservables.observeText(textNonEnergyDiscountRate, SWT.FocusOut);
//        IObservableValue nonEnergyDiscountRateObserveValue 			= BeansObservables.observeValue(viewModel, "nonEnergyDiscountRate"); //$NON-NLS-1$
//        bindingContext.bindValue(textNonEnergyDiscountRateObserveWidget, nonEnergyDiscountRateObserveValue, null, null);
////      non-energy years
//        IObservableValue textNonEnergyYearsRateObserveWidget 		= SWTObservables.observeText(textNonEnergyYears, SWT.FocusOut);
//        IObservableValue nonEnergyYearsObserveValue 					= BeansObservables.observeValue(viewModel, "nonEnergyYears"); //$NON-NLS-1$
//        bindingContext.bindValue(textNonEnergyYearsRateObserveWidget, nonEnergyYearsObserveValue , null, null);        
//        
////      window area
//        IObservableValue textAreaWindowsObserveWidget 		= SWTObservables.observeText(textAreaWindows, SWT.FocusOut);
//        IObservableValue areaWindowsObserveValue 			= BeansObservables.observeValue(viewModel, "windowArea"); //$NON-NLS-1$
//        bindingContext.bindValue(textAreaWindowsObserveWidget, areaWindowsObserveValue, null, null);
////    	window times per year
//        IObservableValue textWindowTimesPerYearObserveWidget= SWTObservables.observeText(textWindowTimesPerYear, SWT.FocusOut);
//        IObservableValue windowtimesObserveValue 			= BeansObservables.observeValue(viewModel, "windowCleaningTimesPerYear"); //$NON-NLS-1$
//        bindingContext.bindValue(textWindowTimesPerYearObserveWidget, windowtimesObserveValue, null, null);
////    	window performance value
//        IObservableValue textWindowPerformanceValueObserveWidget= SWTObservables.observeText(textWindowPerformanceValue, SWT.FocusOut);
//        IObservableValue windowPerformanceObserveValue 		= BeansObservables.observeValue(viewModel, "windowPerformanceValue"); //$NON-NLS-1$
//        bindingContext.bindValue(textWindowPerformanceValueObserveWidget, windowPerformanceObserveValue, null, null);
////    	window hourly rate
//        IObservableValue textWindowHourlyRateObserveWidget= SWTObservables.observeText(textWindowHourlyRate, SWT.FocusOut);
//        IObservableValue windowHourlyRateObserveValue 		= BeansObservables.observeValue(viewModel, "windowHourlyRate"); //$NON-NLS-1$
//        bindingContext.bindValue(textWindowHourlyRateObserveWidget, windowHourlyRateObserveValue, null, null);
//        
////      building shell area
//        IObservableValue textBuildingShellAreaObserveWidget = SWTObservables.observeText(textBuildingShellArea, SWT.FocusOut);
//        IObservableValue buildingShellAreaObserveValue 		= BeansObservables.observeValue(viewModel, "buildingShellArea"); //$NON-NLS-1$
//        bindingContext.bindValue(textBuildingShellAreaObserveWidget, buildingShellAreaObserveValue, null, null);
////    	building shell times per year
//        IObservableValue textBuildingShellTimesPerYearObserveWidget= SWTObservables.observeText(textBuildingShellTimesPerYear, SWT.FocusOut);
//        IObservableValue buildingShellTimesObserveValue 	= BeansObservables.observeValue(viewModel, "buildingShellCleaningTimesPerYear"); //$NON-NLS-1$
//        bindingContext.bindValue(textBuildingShellTimesPerYearObserveWidget, buildingShellTimesObserveValue, null, null);
////    	building shell performance value
//        IObservableValue textBuildingShellPerformanceValueObserveWidget= SWTObservables.observeText(textBuildingShellPerformanceValue, SWT.FocusOut);
//        IObservableValue buildingShellPerformanceObserveValue= BeansObservables.observeValue(viewModel, "buildingShellPerformanceValue"); //$NON-NLS-1$
//        bindingContext.bindValue(textBuildingShellPerformanceValueObserveWidget, buildingShellPerformanceObserveValue, null, null);
////    	building shell hourly rate
//        IObservableValue textBuildingShellHourlyRateObserveWidget= SWTObservables.observeText(textBuildingShellHourlyRate, SWT.FocusOut);
//        IObservableValue buildingShellHourlyRateObserveValue = BeansObservables.observeValue(viewModel, "buildingShellHourlyRate"); //$NON-NLS-1$
//        bindingContext.bindValue(textBuildingShellHourlyRateObserveWidget, buildingShellHourlyRateObserveValue, null, null);
////		maintenance percentage
//        IObservableValue textMaintenancePercentageObserveWidget= SWTObservables.observeText(textMaintenancePercenatge, SWT.FocusOut);
//        IObservableValue maintenancePercentageObserveValue = BeansObservables.observeValue(viewModel, "maintenancePercentage"); //$NON-NLS-1$
//        bindingContext.bindValue(textMaintenancePercentageObserveWidget, maintenancePercentageObserveValue, null, null);
//        
//////    	energy-related costs
////        IObservableValue textEnergyRelatedCostsObserveWidget= SWTObservables.observeText(textEnergyRelatedCosts);
////        IObservableValue energyRelatedCostsObserveValue = BeansObservables.observeValue(viewModel, "eKPI62");
////        bindingContext.bindValue(textEnergyRelatedCostsObserveWidget, energyRelatedCostsObserveValue, null, null);  
////    	investment costs
//        IObservableValue textInvestmentCostsObserveWidget= SWTObservables.observeText(textInvestmentCosts);
//        IObservableValue investmentCostsObserveValue = BeansObservables.observeValue(viewModel, "eKPI61"); //$NON-NLS-1$
//        bindingContext.bindValue(textInvestmentCostsObserveWidget, investmentCostsObserveValue, null, createCurrencyStrategy());        
////    	non-energy-related costs for windows
//        IObservableValue textWindowCostsObserveWidget= SWTObservables.observeText(textWindowCosts);
//        IObservableValue windowCostsObserveValue = BeansObservables.observeValue(viewModel, "nonEnergyRelatedCostsForWindows"); //$NON-NLS-1$
//        bindingContext.bindValue(textWindowCostsObserveWidget, windowCostsObserveValue, null, createCurrencyStrategy());  
////    	non-energy-related costs for building shell      
//        IObservableValue textBuildingShellCostsObserveWidget= SWTObservables.observeText(textBuildingShellCosts);
//        IObservableValue buildingShellCostsObserveValue = BeansObservables.observeValue(viewModel, "nonEnergyRelatedCostsForBuildingShell"); //$NON-NLS-1$
//        bindingContext.bindValue(textBuildingShellCostsObserveWidget, buildingShellCostsObserveValue, null, createCurrencyStrategy()); 
////    	non-energy-related costs for maintenance       
//        IObservableValue textMaintenanceCostsObserveWidget= SWTObservables.observeText(textMaintenanceResult);
//        IObservableValue maintenanceCostsObserveValue = BeansObservables.observeValue(viewModel, "nonEnergyRelatedCostsForMaintenance"); //$NON-NLS-1$
//        bindingContext.bindValue(textMaintenanceCostsObserveWidget, maintenanceCostsObserveValue, null,  createCurrencyStrategy()); 
//        
//        
//        
//        
////    	final energy costs      
////        IObservableValue textFinalEnergyCostsObserveWidget= SWTObservables.observeText(textFinalEnergyCosts);
////        IObservableValue finalEnergyCosts = BeansObservables.observeValue(viewModel, "eKPI6All");
////        bindingContext.bindValue(textFinalEnergyCostsObserveWidget, finalEnergyCosts, null, null); 
//
//        return bindingContext;
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
//	private UpdateValueStrategy createMaintenanceBindingUpdateValaueStrategy()
//	{
//		
//		 IConverter converter = new IConverter(){
//
//				@Override
//				public Object convert(Object obj) {
//					if(obj instanceof Double) {
//						Double value = (Double) obj;
//						return value.toString();
//					}
//					return null;
//				}
//
//				@Override
//				public Object getFromType() {
//					return null;
//				}
//
//				@Override
//				public Object getToType() {
//					return null;
//				}
//	        	
//	        };
//
//	        // Create UpdateValueStratgy and assign to the binding
//	        UpdateValueStrategy strategy = new UpdateValueStrategy();
//	        strategy.setConverter(converter);
//	        
//	        return strategy;
//	}
//	
//	private UpdateValueStrategy createCurrencyStrategy()
//	{
//		
//		 IConverter converter = new IConverter(){
//
//				@Override
//				public Object convert(Object obj) {
//					if(obj instanceof Double || obj instanceof Integer) {
//						Double value = (Double) obj;						
//						value= Math.round(value*100.0)/100.0;							
//						
//						return String.format("%.2f", value);
//					}
//					return null;
//				}
//
//				@Override
//				public Object getFromType() {
//					return null;
//				}
//
//				@Override
//				public Object getToType() {
//					return null;
//				}
//	        	
//	        };
//
//	        // Create UpdateValueStratgy and assign to the binding
//	        UpdateValueStrategy strategy = new UpdateValueStrategy();
//	        strategy.setConverter(converter);
//	        
//	        return strategy;
//	}	
//}
