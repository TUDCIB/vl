package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment;

import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCModelContentProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.simulation.energy.model.SensitivityModel;

/**
 * Abstract dialog class for sensitivity analysis assignments.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public abstract class StochasticResourceAssignmentDialog<T extends SensitivityModel> extends ResourceAssignmentDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7659012127398494912L;
	private Composite variantContainer;
	private Composite settingsContainer;
	protected Button sensitivityButton;
	protected Text standardDeviationInput;
	protected Spinner numberSamplesWidget;
	protected T model;
	

	public StochasticResourceAssignmentDialog(Shell parent,
			IFCLabelProvider labelProvider, IFCModelContentProvider contentProvider,
			Resource resource, Set<EIfcroot> checkedEntities) {
		super(parent, labelProvider, contentProvider, resource, checkedEntities);
	}
	
	@Override
	protected Composite createDialogArea(Composite parent) {
		Composite container = super.createDialogArea(parent);
		
		// create sensitivity controls
		createVariantContainer(container);
		// initialize data bindings
		initDataBindings();
		return container;
	}
	
	private void createVariantContainer(Composite parent) {
		sensitivityButton = toolkit.createButton(parent, "Stochastic parameters (Note: only one at the same time is permitted, use comma for significant figures)", SWT.CHECK);
		sensitivityButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Label separator = toolkit.createSeparator(parent, SWT.HORIZONTAL);
		separator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		settingsContainer = toolkit.createComposite(parent);
		settingsContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
		settingsContainer.setLayout(new GridLayout(4, false));
		
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 1;
		
		toolkit.createLabel(settingsContainer, "Standard deviation:");
		standardDeviationInput = toolkit.createText(settingsContainer, "0");
		standardDeviationInput.setLayoutData(gridData);
		
		toolkit.createLabel(settingsContainer, "Samples: ");
		numberSamplesWidget = new Spinner(settingsContainer, SWT.HORIZONTAL);
		numberSamplesWidget.setMinimum(1);
		numberSamplesWidget.setMaximum(100);
		numberSamplesWidget.setIncrement(1); 
		numberSamplesWidget.setLayoutData(gridData);
		
		variantContainer = toolkit.createComposite(parent);
		variantContainer.setLayoutData(new GridData(GridData.FILL_BOTH));

		// by default the containers are disabled
		setSensitivityMode(false);
		sensitivityButton.addSelectionListener(new SelectionListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -7931989518810359589L;

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(sensitivityButton.getSelection()) {
					variantContainer.setEnabled(true);
					settingsContainer.setEnabled(true);
				} else {
					variantContainer.setEnabled(false);
					settingsContainer.setEnabled(false);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		// create controls dependent on resource
        createVariantControls(variantContainer); 
	}

	public void setSensitivityMode(boolean isSensitivity) {
		settingsContainer.setEnabled(isSensitivity);
		variantContainer.setEnabled(isSensitivity);
	}
	
	/**
	 * <b>Note: Intended to be overridden by sub classes.</b>
	 * @param parent
	 */
	protected abstract void createVariantControls(Composite parent);
	
	/**
	 * Optional hide sensitivity controls if it is not recommended to use them.
	 * @param isVisible <b>true</b>: show controls, <b>false</b>: hide controls
	 */
	public void hideSensitivityControls(boolean isVisible) {
		sensitivityButton.setVisible(isVisible);
		settingsContainer.setEnabled(isVisible);
		variantContainer.setVisible(isVisible);
	}
	
	protected DataBindingContext initDataBindings() {
        DataBindingContext bindingContext = new DataBindingContext();
//      standardDeviation
        IObservableValue standardDeviationObserveWidget = SWTObservables.observeText(standardDeviationInput, SWT.FocusOut);
        IObservableValue standardDeviationObserveValue 	= BeansObservables.observeValue(model, "standardDeviation");
        bindingContext.bindValue(standardDeviationObserveWidget, standardDeviationObserveValue);
//      sampleNumber
        IObservableValue sampleNumberObserveWidget = SWTObservables.observeSelection(numberSamplesWidget);
        IObservableValue sampleNumberObserveValue 	= BeansObservables.observeValue(model, "sampleNumber");
        bindingContext.bindValue(sampleNumberObserveWidget, sampleNumberObserveValue);
		return bindingContext; 	
	}
	
	public final void setModel(T model) {
		this.model = model;
	}

}
