package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;

import org.apache.commons.lang3.math.NumberUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCModelContentProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.simulation.energy.model.SensitivityConstructionModel;

public class StochasticConstructionResourceAssignmentDialog extends StochasticResourceAssignmentDialog<SensitivityConstructionModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3569645217268941156L;
	
	private Text uValueText;
	private List<Label> matComboLabels = new ArrayList<Label>();
	private List<Label> thicknessLabels = new ArrayList<Label>();
	private List<Combo> matCombos = new ArrayList<Combo>();
	private List<Text> thicknessTexts = new ArrayList<Text>();
	private List<MaterialTemplate> templates;
	private Button[] radios = null;
	private Map<Integer, MaterialLayer> layers;
	

	public StochasticConstructionResourceAssignmentDialog(Shell parent,
			IFCLabelProvider labelProvider, IFCModelContentProvider contentProvider,
			ConstructionTemplate resource, Set<EIfcroot> checkedEntities, List<MaterialTemplate> materialTemplates) {
		super(parent, labelProvider, contentProvider, resource, checkedEntities);
		this.templates = materialTemplates;
	}
	
	@Override
	protected void createVariantControls(Composite parent) {		
		GridLayout layout = new GridLayout();
		layout.numColumns = 7;
		// layout.horizontalAlignment = GridData.FILL;
		parent.setLayout(layout);
		
//		// The text fields will grow with the size of the dialog
//		GridData gridData = new GridData();
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.horizontalSpan = 3;
//		gridData.horizontalAlignment = GridData.FILL;
//		
//		u-VALUE
//		Double uValue = template != null ? template.getuValue() : null;
//		String uValueString = uValue != null ? ""+uValue : "-";
////		uValueString = uValueString.replace(",", ".");
//		toolkit.createLabel(parent, "u-Value (W/m²K)");
//		uValueText = toolkit.createText(parent, uValueString, SWT.READ_ONLY);
//		uValueText.setLayoutData(gridData);
//		<BR/>
		
//		MATERIAL LAYERS
		if(resource != null) {
			layers = new HashMap<Integer, MaterialLayer>(((ConstructionTemplate)resource).getMaterialLayers());
			
			radios = new Button[layers.size()];
			
			if(layers != null) {
				for(Map.Entry<Integer, MaterialLayer> entry : layers.entrySet()) {
					// add radio button
					Button radio = toolkit.createButton(parent, null, SWT.RADIO);
					radios[entry.getKey()] = radio;
					// add material with thickness
					addMaterialLayer(entry.getValue(), parent);
				}
			}
		} else {
			// add radio button
			Button radio = toolkit.createButton(parent, null, SWT.RADIO);
			radios[0] = radio;
			addMaterialLayer(null, parent);
		}

//		<BR/>
	}
	
	/**
	 * Add a new material layer defined by the construction template.
	 * @param layer
	 */
	private int addMaterialLayer(MaterialLayer layer, Composite parent) {
		if(layer == null) {
			layer = new MaterialLayer();
		}
		final int NEW_LAYER_INDEX 	= matCombos.size();
		
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 3;
		
		Label label = toolkit.createLabel(parent, "Material layer");

		final Combo matCombo = new Combo(parent, SWT.NONE);
		matCombo.setText("Nothing selected");
		matCombo.setLayoutData(gridData);
		// we currently don't want to let the user change the assigned material
		matCombo.setEnabled(false);
		
//		<BR/>	
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 1;
		
		Label thicknessLabel = toolkit.createLabel(parent, "Material thickness" +" in [m]");
		final Text thicknessField = toolkit.createText(parent, "", SWT.KeyUp);
		thicknessField.setLayoutData(gridData);
		thicknessField.setEnabled(false);
		
//		matCombo.addSelectionListener(new SelectionAdapter() {
//			
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -1421123327709752599L;
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
				
		renderTemplates(matCombo);


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
		
		matCombos.add(matCombo);
		matComboLabels.add(label);
		thicknessTexts.add(thicknessField);
		thicknessLabels.add(thicknessLabel);
		parent.layout();		
		
		// select the materials in the checkboxes
		MaterialTemplate mt = layer.getTemplate();
		if(mt != null) {
			String tplName = mt.getName();
			float thickness = layer.getThickness();

	//		select the material if defined in layer
			int index = -1;

			for(int i = 0; i < templates.size(); i++) {
				if(templates.get(i).getName().equals(tplName)) {
					index = i;
					break;
				}
			}
			String thicknessText = ""+thickness;
//			thicknessText = thicknessText.replace(',', '.');
			thicknessTexts.get(thicknessTexts.size()-1).setText(thicknessText);
			matCombos.get(matCombos.size()-1).select(index);
		}
		
//		template.addMaterialLayer(NEW_LAYER_INDEX, layer);
//		return the last index
		return NEW_LAYER_INDEX;
	}
	
	private void renderTemplates(final Combo combo) {		
		combo.removeAll();
		combo.setText("Nothing selected");
		for(int i = 0; i < templates.size(); i++) {
			MaterialTemplate template = templates.get(i);
			combo.add(template.getName(), i);
		}
	}

	public List<Combo> getMatCombos() {
		return matCombos;
	}

	public List<Text> getThicknessTexts() {
		return thicknessTexts;
	}
	
	@Override
	protected void saveInput() {
		super.saveInput();
		// set thicknesses
		for (Map.Entry<Integer, MaterialLayer> entry : layers.entrySet()) {
			Integer index = entry.getKey();
			MaterialLayer layer = entry.getValue();
			
			// thickness
			Text thicknessText = thicknessTexts.get(index);
			float thickness = NumberUtils.createFloat(thicknessText.getText());
			layer.setThickness(thickness);
			
			// TODO Material?
			
			// evaluate stochastic layer
			Button radio = radios[index];
			if(radio.getSelection()) {
				model.setMeanValue(thickness);
				model.setSelectedMaterialIndex(index);
			}
		}
		model.setLayers(layers);
	}
	
//	private MaterialTemplate getMaterialTemplate(String name) {
//		if(templates != null) {
//			for(MaterialTemplate tpl : templates) {
//				if(name.equalsIgnoreCase(tpl.getName())) return tpl;
//			}
//		}
//		return null;
//	}

}
