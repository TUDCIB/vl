//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.TabFolder;
//import org.eclipse.swt.widgets.TabItem;
//import org.eclipse.ui.forms.widgets.Form;
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.ScrolledForm;
//
//import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//
//public class SocioCultureValueTabItem extends TabItem {
//	
//
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -5002960008553701414L;
//	
//
//	public SocioCultureValueTabItem(TabFolder parent, int style) {
//		super(parent, style);
//		setText(Messages.get().TEXT_SOCIO_CULTURAL_VALUE);
//	}
//	
//	public void createContent(TabFolder parent) {
//		ScrolledForm form = createContainer(parent);
//	    setControl(form);
//	}
//	
//	private ScrolledForm createContainer(TabFolder parent) {
//		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
//		ScrolledForm scrolledForm = toolkit.createScrolledForm(parent);
//		Form form = scrolledForm.getForm();
//		toolkit.decorateFormHeading(form);
//		form.setText(Messages.get().TEXT_SOCIO_CULTURAL_VALUE);
//		Composite body = form.getBody();
//		body.setLayout(new GridLayout(2, true));
//	
//		FillLayout buttonContainerLayout = new FillLayout(SWT.HORIZONTAL);
//		buttonContainerLayout.marginHeight = 20;
//		buttonContainerLayout.spacing = 20;
//		toolkit.createLabel(body, "");
//		Composite buttonContainer = toolkit.createComposite(body);
//		buttonContainer.setLayout(buttonContainerLayout);
//		Button calcButton = toolkit.createButton(buttonContainer, Messages.get().ACTION_CALCULATE, SWT.PUSH);
////		Button cancelButton= toolkit.createButton(buttonContainer, Messages.get().ACTION_CANCEL, SWT.PUSH);
//		
//		Label separator = toolkit.createSeparator(body, SWT.SEPARATOR | SWT.HORIZONTAL);
//		GridData gridDataSeparator = new GridData(GridData.FILL_HORIZONTAL);
//		gridDataSeparator.horizontalSpan = 2;
//		separator.setLayoutData(gridDataSeparator);
//
//		Image imageFormula = AppearanceFactory.getInstance().getImageDescriptor(Activator.PLUGIN_ID, 
//				Activator.ICONS_PATH+"eKPI_3.1-4.3.png").createImage();
//		Label labelImgFormula = toolkit.createLabel(body, "");
//		labelImgFormula.setImage(imageFormula);
//		GridData gridDataLabelImgFormula = new GridData(GridData.FILL_HORIZONTAL);
//		gridDataLabelImgFormula.horizontalSpan = 2;
//		labelImgFormula.setLayoutData(gridDataLabelImgFormula);
//		
//		return scrolledForm;
//	}
//}
