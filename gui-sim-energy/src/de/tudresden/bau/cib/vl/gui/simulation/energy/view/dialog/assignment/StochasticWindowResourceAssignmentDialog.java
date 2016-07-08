package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment;

import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.tudresden.bau.cib.simmatrix.MaterialUnit;
import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCModelContentProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.simulation.energy.model.SensitivityWindowModel;
import de.tudresden.bau.cib.vl.gui.simulation.energy.model.SensitivityWindowModel.WindowProperties;

public class StochasticWindowResourceAssignmentDialog extends StochasticResourceAssignmentDialog<SensitivityWindowModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4597583540146477487L;
	
	private Text glassFractionInput, frameFractionInput, thermalTransmittanceInput, solarHeatGainCoefficientInput, shadingFactorInput;
	private Combo glassFractionCombo, frameFractionCombo, thermalTransmittanceCombo, solarHeatGainCoefficientCombo, shadingFactorCombo;

	

	public StochasticWindowResourceAssignmentDialog(Shell parent,
			IFCLabelProvider labelProvider, IFCModelContentProvider contentProvider,
			Resource resource, Set<EIfcroot> checkedEntities) {
		super(parent, labelProvider, contentProvider, resource, checkedEntities);
	}
	
	@Override
	protected void createVariantControls(Composite parent) {		
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		// layout.horizontalAlignment = GridData.FILL;
		parent.setLayout(layout);
		
		// Glass fraction
		toolkit.createLabel(parent, "Glass fraction");
		glassFractionInput = toolkit.createText(parent, "");
		glassFractionCombo = new Combo(parent, SWT.READ_ONLY);
		glassFractionCombo.add(MaterialUnit.FRAC.getLiteral());
				
		// Frame fraction
		toolkit.createLabel(parent, "Frame fraction");
		frameFractionInput = toolkit.createText(parent, "");
		frameFractionCombo = new Combo(parent, SWT.READ_ONLY);
		frameFractionCombo.add(MaterialUnit.FRAC.getLiteral());
				
		// Thermal transmittance
		toolkit.createLabel(parent, "Thermal transmittance");
		thermalTransmittanceInput = toolkit.createText(parent, "");
		thermalTransmittanceCombo = new Combo(parent, SWT.READ_ONLY);
		thermalTransmittanceCombo.add(MaterialUnit.WM2K.getLiteral());		
		
		// Solar heat gain coefficient
		toolkit.createLabel(parent, "Solar heat gain coefficient");
		solarHeatGainCoefficientInput = toolkit.createText(parent, "");
		solarHeatGainCoefficientCombo = new Combo(parent, SWT.READ_ONLY);
		solarHeatGainCoefficientCombo.add(MaterialUnit._1.getLiteral());		
		
		// Shading factor
		toolkit.createLabel(parent, "Shading factor");
		shadingFactorInput = toolkit.createText(parent, "");
		shadingFactorCombo = new Combo(parent, SWT.READ_ONLY);
		shadingFactorCombo.add(MaterialUnit.FRAC.getLiteral());
	}
	
	@Override
	protected DataBindingContext initDataBindings() {
        DataBindingContext bindingContext = super.initDataBindings();
//      glassFractionValue
        IObservableValue glassFractionObserveWidget = SWTObservables.observeText(glassFractionInput, SWT.FocusOut);
        IObservableValue glassFractionObserveValue 	= BeansObservables.observeValue(model, WindowProperties.glassFractionValue.name());
        bindingContext.bindValue(glassFractionObserveWidget, glassFractionObserveValue);
//      glassFractionUnit
        IObservableValue glassFractionUnitObserveWidget = SWTObservables.observeSelection(glassFractionCombo);
        IObservableValue glassFractionUnitObserveValue 	= BeansObservables.observeValue(model, "glassFractionUnit");
        bindingContext.bindValue(glassFractionUnitObserveWidget, glassFractionUnitObserveValue);
//      frameFractionValue
        IObservableValue frameFractionObserveWidget = SWTObservables.observeText(frameFractionInput, SWT.FocusOut);
        IObservableValue frameFractionObserveValue 	= BeansObservables.observeValue(model, WindowProperties.frameFractionValue.name());
        bindingContext.bindValue(frameFractionObserveWidget, frameFractionObserveValue);
//      frameFractionUnit
        IObservableValue frameFractionUnitObserveWidget = SWTObservables.observeSelection(frameFractionCombo);
        IObservableValue frameFractionUnitObserveValue 	= BeansObservables.observeValue(model, "frameFractionUnit");
        bindingContext.bindValue(frameFractionUnitObserveWidget, frameFractionUnitObserveValue);
//      thermalTransmittanceValue
        IObservableValue thermalTransmittanceValueObserveWidget = SWTObservables.observeText(thermalTransmittanceInput, SWT.FocusOut);
        IObservableValue thermalTransmittanceValueObserveValue 	= BeansObservables.observeValue(model, WindowProperties.thermalTransmittanceValue.name());
        bindingContext.bindValue(thermalTransmittanceValueObserveWidget, thermalTransmittanceValueObserveValue);
//      thermalTransmittanceUnit
        IObservableValue thermalTransmittanceUnitObserveWidget = SWTObservables.observeSelection(thermalTransmittanceCombo);
        IObservableValue thermalTransmittanceUnitObserveValue 	= BeansObservables.observeValue(model, "thermalTransmittanceUnit");
        bindingContext.bindValue(thermalTransmittanceUnitObserveWidget, thermalTransmittanceUnitObserveValue);
//      solarHeatGainValue
        IObservableValue solarHeatGainValueObserveWidget = SWTObservables.observeText(solarHeatGainCoefficientInput, SWT.FocusOut);
        IObservableValue solarHeatGainValueObserveValue 	= BeansObservables.observeValue(model, WindowProperties.solarHeatGainValue.name());
        bindingContext.bindValue(solarHeatGainValueObserveWidget, solarHeatGainValueObserveValue);
//      solarHeatGainUnit
        IObservableValue solarHeatGainUnitObserveWidget = SWTObservables.observeSelection(solarHeatGainCoefficientCombo);
        IObservableValue solarHeatGainUnitObserveValue 	= BeansObservables.observeValue(model, "solarHeatGainUnit");
        bindingContext.bindValue(solarHeatGainUnitObserveWidget, solarHeatGainUnitObserveValue);
//      shadingFactorValue
        IObservableValue shadingFactorValueObserveWidget = SWTObservables.observeText(shadingFactorInput, SWT.FocusOut);
        IObservableValue shadingFactorValueObserveValue 	= BeansObservables.observeValue(model, WindowProperties.shadingFactorValue.name());
        bindingContext.bindValue(shadingFactorValueObserveWidget, shadingFactorValueObserveValue);
//      shadingFactorUnit
        IObservableValue shadingFactorUnitObserveWidget = SWTObservables.observeSelection(shadingFactorCombo);
        IObservableValue shadingFactorUnitObserveValue 	= BeansObservables.observeValue(model, "shadingFactorUnit");
        bindingContext.bindValue(shadingFactorUnitObserveWidget, shadingFactorUnitObserveValue);
        
		glassFractionCombo.select(0);
		frameFractionCombo.select(0);
		thermalTransmittanceCombo.select(0);
		solarHeatGainCoefficientCombo.select(0);
		shadingFactorCombo.select(0);
		return bindingContext; 	
    };

}
