package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;


public class GreenBuildingDesignSetupGeneralParameterDialog extends TitleAreaDialog {

	private FormToolkit toolkit;
	private Display display;
	
	private Spinner roofThermalTransmittanceSpinner, roofCostsSpinner;
	
	/** 3 digits - 0.00 */
	private float roofThermalTransmittance;
	/** 3 digits - 0.00 */
	private float roofCosts;
	
	private String buildingDeclaration;
	

	public GreenBuildingDesignSetupGeneralParameterDialog(Shell parentShell, String buildingName, String buildingGuid) {
		super(parentShell);
		buildingDeclaration = buildingName + " (GUID: "+ buildingGuid +")";
	}

	@Override
	public void create() {	
//		now creates the window and content (call createDialogArea())
		super.create();
		setTitle("Green building design parameter setup for building: "+buildingDeclaration);
		setMessage("Define green building design requirements of building: "+buildingDeclaration, IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {		
		display = parent.getDisplay();
		toolkit = new FormToolkit(display);
		Composite container = (Composite) super.createDialogArea(parent);
		createRoofComposite(container);
		return parent;
	}
	
	private void createRoofComposite(Composite parent) {
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText("Roof");
		Composite container = toolkit.createComposite(section, SWT.WRAP);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		container.setLayout(layout);
		
		toolkit.createLabel(container, "Thermal transmittance [W/m²K]:");
		roofThermalTransmittanceSpinner = new Spinner(container, SWT.BORDER);
		roofThermalTransmittanceSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		roofThermalTransmittanceSpinner.setDigits(3);
		roofThermalTransmittanceSpinner.setMaximum(9999);
		roofThermalTransmittanceSpinner.setMinimum(1);
		roofThermalTransmittanceSpinner.setIncrement(10);
		roofThermalTransmittanceSpinner.setSelection(50);
		
		toolkit.createLabel(container, "Costs [€/m²]:");
		roofCostsSpinner = new Spinner(container, SWT.BORDER);
		roofCostsSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		roofCostsSpinner.setDigits(3);
		roofCostsSpinner.setMaximum(1000000);
		roofCostsSpinner.setMinimum(1);
		roofCostsSpinner.setIncrement(1000);
		roofCostsSpinner.setSelection(1000);
		
		section.setClient(container);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void okPressed() {
		this.roofThermalTransmittance = new Float(this.roofThermalTransmittanceSpinner.getSelection()) / 1000;
		
		this.roofCosts = new Float(this.roofCostsSpinner.getSelection()) / 1000;
		
		super.okPressed();
	}

	public float getRoofThermalTransmittance() {
		return roofThermalTransmittance;
	}

	public void setRoofThermalTransmittance(float roofThermalTransmittance) {
		this.roofThermalTransmittance = roofThermalTransmittance;
	}

	public float getRoofCosts() {
		return roofCosts;
	}

	public void setRoofCosts(float roofCosts) {
		this.roofCosts = roofCosts;
	}
} 
