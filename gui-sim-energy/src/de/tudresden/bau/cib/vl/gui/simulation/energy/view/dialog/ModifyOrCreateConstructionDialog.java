//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang.math.NumberUtils;
//import org.eclipse.core.databinding.DataBindingContext;
//import org.eclipse.core.databinding.UpdateValueStrategy;
//import org.eclipse.core.databinding.beans.BeansObservables;
//import org.eclipse.core.databinding.conversion.IConverter;
//import org.eclipse.core.databinding.observable.value.IObservableValue;
//import org.eclipse.jface.databinding.swt.SWTObservables;
//import org.eclipse.jface.dialogs.IMessageProvider;
//import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.jface.dialogs.TitleAreaDialog;
//import org.eclipse.jface.resource.ImageDescriptor;
//import org.eclipse.jface.resource.JFaceResources;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.ModifyEvent;
//import org.eclipse.swt.events.ModifyListener;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Combo;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.ScrolledForm;
//
//import de.tudresden.bau.cib.vl.core.exception.ParsingException;
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
//import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.TemplateViewController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.model.ConstructionTemplateModel;
//
//public class ModifyOrCreateConstructionDialog extends TitleAreaDialog {
//
//	private Text tplNameText;
//	private Text tplCostsText;
//	private Text uValueText;
//	private List<Label> matComboLabels = new ArrayList<Label>();
//	private List<Label> thicknessLabels = new ArrayList<Label>();
//	private List<Combo> matCombos = new ArrayList<Combo>();
//	private List<Text> thicknessTexts = new ArrayList<Text>();
//	private Composite container;
//	private FormToolkit toolkit;
//	private Display display;
//	private List<MaterialTemplate> templates;
//	
//	private final int PLUS = 9995;
//	private final int MINUS = 9996; 
//	
//	private ConstructionTemplateModel template;
//	private TemplateViewController controller = TemplateViewController.getInstance();
//	
//
//	public ModifyOrCreateConstructionDialog(Shell parentShell, ConstructionTemplateModel ctm) {
//		super(parentShell);
//		this.template = ctm;
//	}
//
//	@Override
//	public void create() {	
////		retrieve material templates
//		try {
//			templates = controller.listMaterialTemplates();
//			if(templates == null) {MessageDialog.openError(
//					Display.getCurrent().getActiveShell(), 
//					Messages.MSG_ERR_PARSING_TEMPLATES, 
//					Messages.MSG_ERR_PARSING_TEMPLATES);
//			}
//		} catch (Exception e) {
//			MessageDialog.openError(display.getActiveShell(), "Error", "Problem by listing the materials");
//		} 
////		now creates the window and content (call createDialogArea())
//		super.create();
//		setTitle(Messages.TEXT_EDIT_TEMPLATE);
//		setMessage(Messages.TEXT_DESCRIPTION_CONSTRUCTION_TEMPLATE, IMessageProvider.INFORMATION);
//	}
//
//	@Override
//	protected Control createDialogArea(Composite parent) {	
//		display = parent.getDisplay();
//        Composite area = (Composite) super.createDialogArea(parent);
//		toolkit = new FormToolkit(area.getDisplay());
//		
//		ScrolledForm  form = toolkit.createScrolledForm(area);
//		container = form.getBody();
//		
////		container = toolkit.createComposite(area, SWT.NONE);
//        container.setLayoutData(new GridData(GridData.FILL_BOTH));
//        
//		GridLayout layout = new GridLayout();
//		layout.numColumns = 4;
//		// layout.horizontalAlignment = GridData.FILL;
//		container.setLayout(layout);
//
//		// The text fields will grow with the size of the dialog
//		GridData gridData = new GridData();
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.horizontalSpan = 3;
//		gridData.horizontalAlignment = GridData.FILL;
//		
////		NAME
//		toolkit.createLabel(container, Messages.TEXT_NAME+"*");
//		tplNameText = toolkit.createText(container, template == null ? Messages.TEXT_INPUT : template.getName());
//		tplNameText.setLayoutData(gridData);
////		<BR/>
//		
////		COSTS
//		Double costs = template != null ? template.getCostsPerSquareMeter() : null;
//		String costsText = costs != null ? ""+costs : "0.0";
////		costsText = costsText.replace(",", ".");
//		toolkit.createLabel(container, Messages.TEXT_COSTS_PER_SQUAREMETER);
//		tplCostsText = toolkit.createText(container, costsText);
//		tplCostsText.setLayoutData(gridData);
////		<BR/>
//		
////		u-VALUE
//		Double uValue = template != null ? template.getuValue() : null;
//		String uValueString = uValue != null ? ""+uValue : "-";
////		uValueString = uValueString.replace(",", ".");
//		toolkit.createLabel(container, "u-Value (W/m²K)");
//		uValueText = toolkit.createText(container, uValueString, SWT.READ_ONLY);
//		uValueText.setLayoutData(gridData);
////		<BR/>
//		
////		MATERIAL LAYER
//		if(template != null) {
//			Map<Integer, MaterialLayer> layers = template.getMaterialLayers();
//			if(layers != null) {
//				for(Map.Entry<Integer, MaterialLayer> entry : layers.entrySet()) {
//					addMaterialLayer(entry.getValue());
//				}
//			}
//		} else {
//			addMaterialLayer(null);
//		}
//
////		<BR/>
//		
////		init data bindings
//		initDataBindings();
//		
//		return area;
//	}
//	
//	private void renderTemplates(final Combo combo) {
//		display.syncExec(new Runnable() {
//			public void run() {				
//				combo.removeAll();
//				combo.setText(Messages.TEXT_SELECTED_NOTHING);
//				for(int i = 0; i < templates.size(); i++) {
//					MaterialTemplate template = templates.get(i);
//					combo.add(template.getName(), i);
//				}
//
//			}
//		});
//	}
//	
//	/**
//	 * Add a new material layer defined by the construction template.
//	 * @param layer
//	 */
//	private int addMaterialLayer(MaterialLayer layer) {
//		if(layer == null) {
//			layer = new MaterialLayer();
//		}
//		final MaterialLayer layerForListeners = layer;
//		final int NEW_LAYER_INDEX 	= matCombos.size();
//		
//		GridData gridData = new GridData();
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.horizontalAlignment = GridData.FILL;
//		Label label = toolkit.createLabel(container, Messages.TEXT_MATERIAL_LAYER);
//
//		final Combo matCombo = new Combo(container, SWT.NONE);
//		matCombo.setText(Messages.TEXT_SELECTED_NOTHING);
//		matCombo.setLayoutData(gridData);
//		
////		<BR/>	
//		gridData = new GridData();
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.horizontalAlignment = GridData.FILL;
//		Label thicknessLabel = toolkit.createLabel(container, Messages.TEXT_MATERIAL_THICKNESS +" in [m]");
//		final Text thicknessField = toolkit.createText(container, Messages.TEXT_INPUT, SWT.KeyUp);
//		thicknessField.setLayoutData(gridData);
//		
//		matCombo.addSelectionListener(new SelectionAdapter() {
//
//			@Override
//			public void widgetSelected(SelectionEvent evt) {
//				int selIndex 							= matCombo.getSelectionIndex();
//				if(selIndex > -1) {
//					String matName 						= matCombo.getItem(selIndex);
//					MaterialTemplate tpl 				= getMaterialTemplate(matName);
//					layerForListeners.setTemplate(tpl);
//					template.setMaterialLayer(NEW_LAYER_INDEX, layerForListeners);
//				}				
//			}
//		});
//				
//		renderTemplates(matCombo);
//
//
//		thicknessField.addModifyListener(new ModifyListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 5757453049907616745L;
//
//			@Override
//			public void modifyText(ModifyEvent e) {
//				String currentText 		= thicknessField.getText();
//				currentText = currentText.replace(",", ".");
//				if(NumberUtils.isNumber(currentText)){
//					float thickness 	= NumberUtils.toFloat(currentText);
//					layerForListeners.setThickness(thickness);
//					template.setMaterialLayer(NEW_LAYER_INDEX, layerForListeners);
//				}
//			}
//		});
//		
//		matCombos.add(matCombo);
//		matComboLabels.add(label);
//		thicknessTexts.add(thicknessField);
//		thicknessLabels.add(thicknessLabel);
//		container.layout();		
//		
//		MaterialTemplate mt = layer.getTemplate();
//		if(mt != null) {
//			String tplName = mt.getName();
//			float thickness = layer.getThickness();
//
//	//		select the material if defined in layer
//			int index = -1;
//
//			for(int i = 0; i < templates.size(); i++) {
//				if(templates.get(i).getName().equals(tplName)) {
//					index = i;
//					break;
//				}
//			}
//			String thicknessText = ""+thickness;
//			thicknessText = thicknessText.replace(',', '.');
//			thicknessTexts.get(thicknessTexts.size()-1).setText(thicknessText);
//			matCombos.get(matCombos.size()-1).select(index);
//		}
//		
////		template.addMaterialLayer(NEW_LAYER_INDEX, layer);
////		return the last index
//		return NEW_LAYER_INDEX;
//	}
//	
//	private MaterialTemplate getMaterialTemplate(String name) {
//		if(templates != null) {
//			for(MaterialTemplate tpl : templates) {
//				if(name.equalsIgnoreCase(tpl.getName())) return tpl;
//			}
//		}
//		return null;
//	}
//	
//	/**
//	 * Remove material layer.
//	 */
//	private void disposeMaterialLayer() {
//		int comboSize = matCombos.size();
//		if(comboSize <= 1) return;
//		Combo lastCombo = matCombos.remove(comboSize-1);
//		if(lastCombo != null) lastCombo.dispose();
//		Label label = matComboLabels.remove(comboSize-1);
//		if(label != null) label.dispose();
//		
//		Label label2 = thicknessLabels.remove(comboSize-1);
//		if(label2 != null) label2.dispose();
//		Text text = thicknessTexts.remove(comboSize-1);
//		if(text != null) text.dispose();
//		
//		container.layout();
//		template.removeMaterialLayer(comboSize-1); // remove last layer from model
//	}
//
//	@Override
//	protected void createButtonsForButtonBar(Composite parent) {
//		GridData gridData = new GridData();
//		gridData.verticalAlignment = GridData.FILL;
//		gridData.horizontalSpan = 5;
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.grabExcessVerticalSpace = true;
//		gridData.horizontalAlignment = SWT.CENTER;
//
//		parent.setLayoutData(gridData);
//		
//		Button plusButton = createButton(parent, PLUS, "", false);
//		ImageDescriptor plusImageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
//				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"viewmag+.png");
//		ImageDescriptor minusImageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
//				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"viewmag-.png");
//		plusButton.setImage(plusImageDescriptor.createImage());
//		Button minusButton = createButton(parent, MINUS, "", false);
//		minusButton.setImage(minusImageDescriptor.createImage());
//
//		plusButton.setToolTipText(Messages.TEXT_BTN_TT_ADD_MATERIAL);
//		minusButton.setToolTipText(Messages.TEXT_BTN_TT_REM_MATERIAL);
//		
//		plusButton.addSelectionListener(new SelectionListener() {
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				addMaterialLayer(null);
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//		
//		minusButton.addSelectionListener(new SelectionListener() {
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				disposeMaterialLayer();
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//		
//		// Create Add button
//		createOkButton(parent, OK, Messages.ACTION_SAVE, true);
//		// Create Cancel button
//		Button cancelButton = createButton(parent, CANCEL, Messages.ACTION_CANCEL, false);
//		// Add a SelectionListener
//		cancelButton.addSelectionListener(new SelectionAdapter() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 7810665238110626811L;
//
//			public void widgetSelected(SelectionEvent e) {
//				setReturnCode(CANCEL);
//				close();
//			}
//		});
//	}
//
//	private Button createOkButton(Composite parent, int id, String label, boolean defaultButton) {
//		// increment the number of columns in the button bar
//		((GridLayout) parent.getLayout()).numColumns++;
//		Button button = new Button(parent, SWT.PUSH);
//		button.setText(label);
//		button.setFont(JFaceResources.getDialogFont());
//		button.setData(new Integer(id));
//		button.addSelectionListener(new SelectionAdapter() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 2591684910885497786L;
//
//			public void widgetSelected(SelectionEvent event) {
//				if (isValidInput()) {
//					okPressed();
//				}
//			}
//		});
//		if (defaultButton) {
//			Shell shell = parent.getShell();
//			if (shell != null) {
//				shell.setDefaultButton(button);
//			}
//		}
//		setButtonLayoutData(button);
//		return button;
//	}
//
//	/**
//	 * Check of valid input.
//	 * @return
//	 */
//	private boolean isValidInput() {
//		boolean valid = true;
//		if (tplNameText.getText().length() == 0 || tplNameText.getText().equalsIgnoreCase(Messages.TEXT_INPUT)) {
//			setErrorMessage(Messages.MSG_ERR_INPUT_NAME);
//			valid = false;
//		}
//		for(Combo c : matCombos) {
//			if (c.getSelectionIndex() <= -1) {
//				setErrorMessage(Messages.MSG_ERR_NO_MATERIALS_SELECTED);
//				valid = false;
//			}
//		}
//		String costText = tplCostsText.getText();
//		try {
////			costText = costText.replace(',', '.');
//			Float.valueOf(costText);
//		} catch (NumberFormatException e) {
//			setErrorMessage(Messages.MSG_ERR_COSTS_NOT_VALID);
//			valid = false;
//		}
//		
//		Map<Integer, MaterialLayer> layers = template.getMaterialLayers();
//		if(layers == null) {
//			setErrorMessage("No material layers");
//			return false;
//		}
//		for(Map.Entry<Integer, MaterialLayer> entry : layers.entrySet()) {
//			int key = entry.getKey();
//			MaterialLayer l = entry.getValue();
//			if(l == null) {
//				setErrorMessage("Material layer: "+key+" invalid");
//				valid = false;
//			}
//		}
//		return valid;
//	}
//
//	@Override
//	protected boolean isResizable() {
//		return true;
//	}
//	
//	private boolean saveInput() throws ParsingException, IOException {
//		String constTplName 				= template.getName();
//		Map<Integer, MaterialLayer> layers	= template.getMaterialLayers();
//		Double costsPerSquareMeter			= template.getCostsPerSquareMeter();
//		
//	    ConstructionTemplate 	tpl 		= controller.getSelectedConstructionTemplate();
//	    if(tpl == null) 		tpl 		= new ConstructionTemplate();
//
//	    tpl.setMaterialLayers(layers);
//	    tpl.setName(constTplName);
//	    tpl.setCostsPerSquareMeter(costsPerSquareMeter);
//	    
//	    boolean success = false;
//		try {
//			success = controller.saveConstructionTemplate(tpl);
//		    if(success) {
//		    	MessageDialog.openInformation(Display.getCurrent().getActiveShell(), 
//		    			Messages.ACTION_MATERIALS_SAVED, 
//		    			Messages.ACTION_MATERIALS_SAVED);
//		    } else {
//		    	MessageDialog.openError(Display.getCurrent().getActiveShell(), 
//		    			Messages.ACTION_SAVE_FAILED, 
//		    			Messages.ACTION_SAVE_FAILED);
//		    }
//		} catch (Exception e) {
//	    	MessageDialog.openError(Display.getCurrent().getActiveShell(), 
//	    			Messages.ACTION_SAVE_FAILED, 
//	    			e.getMessage());
//		} 
//		return success;
//	}
//	
//	private DataBindingContext initDataBindings() {
//        DataBindingContext bindingContext = new DataBindingContext();
////      name
//        IObservableValue nameObserveWidget 	= SWTObservables.observeText(tplNameText, SWT.FocusOut);
//        IObservableValue nameObserveValue 	= BeansObservables.observeValue(template, "name");
//        bindingContext.bindValue(nameObserveWidget, nameObserveValue, null, null);
////      costs
//        IObservableValue costsObserveWidget = SWTObservables.observeText(tplCostsText, SWT.FocusOut);
//        IObservableValue costsObserveValue 	= BeansObservables.observeValue(template, "costsPerSquareMeter");
//        bindingContext.bindValue(costsObserveWidget, costsObserveValue, null, null);
//        
//        IConverter converter = new IConverter(){
//
//			@Override
//			public Object convert(Object obj) {
//				if(obj instanceof Double) {
//					Double value = (Double) obj;
//					String text = ""+value;
////					text = text.replace(",", ".");
//					return text;
//				}
//				return null;
//			}
//
//			@Override
//			public Object getFromType() {
//				return null;
//			}
//
//			@Override
//			public Object getToType() {
//				return null;
//			}
//        	
//        };
//
//        // Create UpdateValueStratgy and assign to the binding
//        UpdateValueStrategy strategy = new UpdateValueStrategy();
//        strategy.setConverter(converter);
// 
////      u-value      
//        IObservableValue uValueObserveWidget = SWTObservables.observeText(uValueText);
//        IObservableValue uValueObserveValue  = BeansObservables.observeValue(template, "uValue");
//        bindingContext.bindValue(uValueObserveWidget, uValueObserveValue, null, strategy);
//        return bindingContext;
//	}
//
//	@Override
//	protected void okPressed() {
//		boolean isOk;
//		try {
//			isOk = saveInput();
//			if(isOk) super.okPressed();
//		} catch (Exception e) {
//	    	MessageDialog.openError(Display.getCurrent().getActiveShell(), 
//	    			Messages.ACTION_SAVE_FAILED, 
//	    			"Invalid input");
//		} 
//	}
//
//	public void setuValueText(Text uValueText) {
//		this.uValueText = uValueText;
//	}
//} 
