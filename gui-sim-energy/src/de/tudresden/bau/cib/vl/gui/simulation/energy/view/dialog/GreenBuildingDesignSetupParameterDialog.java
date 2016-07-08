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


public class GreenBuildingDesignSetupParameterDialog extends TitleAreaDialog {

	private FormToolkit toolkit;
	private Display display;
	
	private Spinner outerWallTransmittancesSpinner, outerWallCostsSpinner, innerWallTransmittancesSpinner, innerWallCostsSpinner, 
		slabTransmittancesSpinner, slabCostsSpinner, windowTransmittancesSpinner, windowCostsSpinner, doorTransmittancesSpinner,
		doorCostsSpinner;
	
	/** 3 digits - 0.00 */
	private float outerWallThermalTransmittances, innerWallThermalTransmittances, slabThermalTransmittances, windowThermalTransmittances, doorThermalTransmittances;
	/** 3 digits - 0.00 */
	private float outerWallCosts, innerWallCosts, slabCosts, windowCosts, doorCosts;
	
	private String roomDeclaration;
	

	public GreenBuildingDesignSetupParameterDialog(Shell parentShell, String roomName, String roomGuid) {
		super(parentShell);
		roomDeclaration = roomName + " (GUID: "+ roomGuid +")";
	}

	@Override
	public void create() {	
//		now creates the window and content (call createDialogArea())
		super.create();
		setTitle("Green building design parameter setup for room: "+roomDeclaration);
		setMessage("Define design requirements of room: "+roomDeclaration, IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {		
		display = parent.getDisplay();
		toolkit = new FormToolkit(display);
		Composite container = (Composite) super.createDialogArea(parent);
		createOuterWallComposite(container);
		createInnerWallComposite(container);
		createSlabComposite(container);
		createWindowComposite(container);
		createDoorComposite(container);
		return parent;
	}

	private void createOuterWallComposite(Composite parent) {
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setLayoutData(new GridData(GridData.FILL_BOTH));
		section.setText("Outer walls");
		Composite container = toolkit.createComposite(section, SWT.WRAP);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		container.setLayout(layout);
		
		toolkit.createLabel(container, "Thermal transmittance [W/m²K]:");
		outerWallTransmittancesSpinner = new Spinner(container, SWT.BORDER);
		outerWallTransmittancesSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
		outerWallTransmittancesSpinner.setDigits(3);
		outerWallTransmittancesSpinner.setMaximum(9999);
		outerWallTransmittancesSpinner.setMinimum(1);
		outerWallTransmittancesSpinner.setIncrement(10);
		outerWallTransmittancesSpinner.setSelection(50);
		
		toolkit.createLabel(container, "Costs [€/m²]:");
		outerWallCostsSpinner = new Spinner(container, SWT.BORDER);
		outerWallCostsSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
		outerWallCostsSpinner.setDigits(3);
		outerWallCostsSpinner.setMaximum(1000000);
		outerWallCostsSpinner.setMinimum(1);
		outerWallCostsSpinner.setIncrement(1000);
		outerWallCostsSpinner.setSelection(1000);
		
		section.setClient(container);
	}
	
	private void createInnerWallComposite(Composite parent) {
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText("Inner walls");
		Composite container = toolkit.createComposite(section, SWT.WRAP);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		container.setLayout(layout);
		
		toolkit.createLabel(container, "Thermal transmittance [W/m²K]:");
		innerWallTransmittancesSpinner = new Spinner(container, SWT.BORDER);
		innerWallTransmittancesSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
		innerWallTransmittancesSpinner.setDigits(3);
		innerWallTransmittancesSpinner.setMaximum(9999);
		innerWallTransmittancesSpinner.setMinimum(1);
		innerWallTransmittancesSpinner.setIncrement(10);
		innerWallTransmittancesSpinner.setSelection(50);
		
		toolkit.createLabel(container, "Costs [€/m²]:");
		innerWallCostsSpinner = new Spinner(container, SWT.BORDER);
		innerWallCostsSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
		innerWallCostsSpinner.setDigits(3);
		innerWallCostsSpinner.setMaximum(1000000);
		innerWallCostsSpinner.setMinimum(1);
		innerWallCostsSpinner.setIncrement(1000);
		innerWallCostsSpinner.setSelection(1000);
		
		section.setClient(container);
	}
	
	private void createSlabComposite(Composite parent) {
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText("Slabs");
		Composite container = toolkit.createComposite(section, SWT.WRAP);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		container.setLayout(layout);
		
		toolkit.createLabel(container, "Thermal transmittance [W/m²K]:");
		slabTransmittancesSpinner = new Spinner(container, SWT.BORDER);
		slabTransmittancesSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
		slabTransmittancesSpinner.setDigits(3);
		slabTransmittancesSpinner.setMaximum(9999);
		slabTransmittancesSpinner.setMinimum(1);
		slabTransmittancesSpinner.setIncrement(10);
		slabTransmittancesSpinner.setSelection(50);
		
		toolkit.createLabel(container, "Costs [€/m²]:");
		slabCostsSpinner = new Spinner(container, SWT.BORDER);
		slabCostsSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
		slabCostsSpinner.setDigits(3);
		slabCostsSpinner.setMaximum(1000000);
		slabCostsSpinner.setMinimum(1);
		slabCostsSpinner.setIncrement(1000);
		slabCostsSpinner.setSelection(1000);
		
		section.setClient(container);
	}
	
	private void createWindowComposite(Composite parent) {
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText("Windows");
		Composite container = toolkit.createComposite(section, SWT.WRAP);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		container.setLayout(layout);
		
		toolkit.createLabel(container, "Thermal transmittance [W/m²K]:");
		windowTransmittancesSpinner = new Spinner(container, SWT.BORDER);
		windowTransmittancesSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
		windowTransmittancesSpinner.setDigits(3);
		windowTransmittancesSpinner.setMaximum(9999);
		windowTransmittancesSpinner.setMinimum(1);
		windowTransmittancesSpinner.setIncrement(10);
		windowTransmittancesSpinner.setSelection(50);
		
		toolkit.createLabel(container, "Costs [€/m²]:");
		windowCostsSpinner = new Spinner(container, SWT.BORDER);
		windowCostsSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
		windowCostsSpinner.setDigits(3);
		windowCostsSpinner.setMaximum(1000000);
		windowCostsSpinner.setMinimum(1);
		windowCostsSpinner.setIncrement(1000);
		windowCostsSpinner.setSelection(1000);
		
		section.setClient(container);
	}
	
	private void createDoorComposite(Composite parent) {
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText("Doors");
		Composite container = toolkit.createComposite(section, SWT.WRAP);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		container.setLayout(layout);
		
		toolkit.createLabel(container, "Thermal transmittance [W/m²K]:");
		doorTransmittancesSpinner = new Spinner(container, SWT.BORDER);
		doorTransmittancesSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		doorTransmittancesSpinner.setDigits(3);
		doorTransmittancesSpinner.setMaximum(9999);
		doorTransmittancesSpinner.setMinimum(1);
		doorTransmittancesSpinner.setIncrement(10);
		doorTransmittancesSpinner.setSelection(50);
		
		toolkit.createLabel(container, "Costs [€/m²]:");
		doorCostsSpinner = new Spinner(container, SWT.BORDER);
		doorCostsSpinner.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		doorCostsSpinner.setDigits(3);
		doorCostsSpinner.setMaximum(1000000);
		doorCostsSpinner.setMinimum(1);
		doorCostsSpinner.setIncrement(1000);
		doorCostsSpinner.setSelection(1000);
		
		section.setClient(container);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void okPressed() {
		this.outerWallThermalTransmittances = new Float(this.outerWallTransmittancesSpinner.getSelection()) / 1000;
		this.innerWallThermalTransmittances = new Float(this.innerWallTransmittancesSpinner.getSelection()) / 1000;
		this.slabThermalTransmittances = new Float(this.slabTransmittancesSpinner.getSelection()) / 1000;
		this.windowThermalTransmittances = new Float(this.windowTransmittancesSpinner.getSelection()) / 1000;
		this.doorThermalTransmittances = new Float(this.doorTransmittancesSpinner.getSelection()) / 1000;
		
		this.outerWallCosts = new Float(this.outerWallCostsSpinner.getSelection()) / 1000;
		this.innerWallCosts = new Float(this.innerWallCostsSpinner.getSelection()) / 1000;
		this.slabCosts = new Float(this.slabCostsSpinner.getSelection()) / 1000;
		this.windowCosts = new Float(this.windowCostsSpinner.getSelection()) / 1000;
		this.doorCosts = new Float(this.doorCostsSpinner.getSelection()) / 1000;
		
		super.okPressed();
	}

	public float getOuterWallThermalTransmittances() {
		return outerWallThermalTransmittances;
	}

	public void setOuterWallThermalTransmittances(
			float outerWallThermalTransmittances) {
		this.outerWallThermalTransmittances = outerWallThermalTransmittances;
	}

	public float getInnerWallThermalTransmittances() {
		return innerWallThermalTransmittances;
	}

	public void setInnerWallThermalTransmittances(
			float innerWallThermalTransmittances) {
		this.innerWallThermalTransmittances = innerWallThermalTransmittances;
	}

	public float getSlabThermalTransmittances() {
		return slabThermalTransmittances;
	}

	public void setSlabThermalTransmittances(float slabThermalTransmittances) {
		this.slabThermalTransmittances = slabThermalTransmittances;
	}

	public float getWindowThermalTransmittances() {
		return windowThermalTransmittances;
	}

	public void setWindowThermalTransmittances(float windowThermalTransmittances) {
		this.windowThermalTransmittances = windowThermalTransmittances;
	}

	public float getDoorThermalTransmittances() {
		return doorThermalTransmittances;
	}

	public void setDoorThermalTransmittances(float doorThermalTransmittances) {
		this.doorThermalTransmittances = doorThermalTransmittances;
	}

	public float getOuterWallCosts() {
		return outerWallCosts;
	}

	public void setOuterWallCosts(float outerWallCosts) {
		this.outerWallCosts = outerWallCosts;
	}

	public float getInnerWallCosts() {
		return innerWallCosts;
	}

	public void setInnerWallCosts(float innerWallCosts) {
		this.innerWallCosts = innerWallCosts;
	}

	public float getSlabCosts() {
		return slabCosts;
	}

	public void setSlabCosts(float slabCosts) {
		this.slabCosts = slabCosts;
	}

	public float getWindowCosts() {
		return windowCosts;
	}

	public void setWindowCosts(float windowCosts) {
		this.windowCosts = windowCosts;
	}

	public float getDoorCosts() {
		return doorCosts;
	}

	public void setDoorCosts(float doorCosts) {
		this.doorCosts = doorCosts;
	}
} 
