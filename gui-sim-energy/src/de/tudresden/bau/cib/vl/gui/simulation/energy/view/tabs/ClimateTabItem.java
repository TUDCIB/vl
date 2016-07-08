//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.rap.rwt.lifecycle.UICallBack;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.custom.CTabFolder;
//import org.eclipse.swt.custom.CTabItem;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.graphics.Rectangle;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Combo;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.FileDialog;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.TabFolder;
//import org.eclipse.swt.widgets.TabItem;
//import org.eclipse.swt.widgets.Widget;
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.Section;
//
//import de.tudresden.bau.cib.ui.widgets.google.AnnotatedTimeLine;
//import de.tudresden.bau.cib.ui.widgets.google.JSONGoogleDataTable;
//import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.TemplateViewController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//
//public class ClimateTabItem extends TabItem {
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -353531515592136162L;
//	
//	private AnnotatedTimeLine diagram;
//	private JSONGoogleDataTable table;
//	private CTabFolder folder;
//	private CTabItem temperatureTabItem, diffuseRadiationTabItem, directRadiationTabItem;
//	private TemplateViewController controller = TemplateViewController.getInstance();
//	
//
//	public ClimateTabItem(TabFolder parent, int style) {
//		super(parent, style);
//		setText(Messages.get().TEXT_CLIMATE_LOCATION_TEMPLATE);
//		controller.setClimateTabItem(this);
//	}
//	
//	public void createContent(TabFolder parent) {
//		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
//		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
//		section.setText(Messages.get().TEXT_CLIMATE_LOCATION);
//		
//		Composite client = new Composite(section, SWT.NONE);
//		GridLayout layout = new GridLayout();
//		layout.numColumns = 3;
//		layout.marginWidth = 2;
//		layout.marginHeight = 2;
//		client.setLayout(layout);
//		
//		toolkit.createLabel(client, Messages.get().TEXT_CLIMATE_LOCATION,SWT.NONE);
//		
//		final Combo combo = new Combo(client, SWT.NONE);
//		combo.setText(Messages.get().TEXT_SELECTED_NOTHING);
//		
//		renderTemplates(combo);	
//		
//		combo.addSelectionListener(new SelectionListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -4406920621876848329L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				int selectionIndex = combo.getSelectionIndex();
//				ClimateLocationTemplate template = controller.setSelectedClimateTemperature(selectionIndex);
//				
//				createEmptyTable(Messages.get().TEXT_TEMPERATURE, Messages.get().TEXT_TEMPERATURE);
//				createDiagram(folder, Messages.get().TEXT_TEMPERATURE);
//				if(template != null) {
//					MeasuredUnit unit;
////					show always the temperatures when selecting a new climate template
//					try {
//						unit = controller.getTemplateService().getTemperaturesOfReferenceYear(template);
//						showValues(unit);
//						folder.setSelection(temperatureTabItem);
//						temperatureTabItem.setControl(diagram);
//					} catch (IOException ex) {
//						MessageDialog.openError(
//								Display.getCurrent().getActiveShell(), 
//								Messages.get().MSG_ERR_PARSING_TEMPLATES, 
//								ex.getMessage());
//					}	
//				}
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//		
//		Button uploadButton = toolkit.createButton(client, Messages.get().TEXT_UPLOAD_FILE, SWT.NONE);
//		uploadButton.addSelectionListener(new SelectionListener() {
//			
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -1284644466220515196L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				UICallBack.runNonUIThreadWithFakeContext( Display.getCurrent(), new Runnable() {
//				public void run() {
//					try {
//						hideDiagram();
//						showUploadFileDialog();
//						showDiagram();
//					} catch (IOException e) {
//						MessageDialog.openWarning(
//								Display.getCurrent().getActiveShell(), 
//								Messages.get().MSG_ERR_UPLOAD_FAILED, 
//								Messages.get().MSG_ERR_UPLOAD_FAILED);
//					}
//				}	
//				} );
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//		
//		GridData horizontalGridData = new GridData(GridData.FILL_HORIZONTAL);
//		horizontalGridData.horizontalSpan = 3;
//		horizontalGridData.heightHint = 20;
//		
//		Label separator = toolkit.createSeparator(client, SWT.HORIZONTAL);
//		separator.setLayoutData(horizontalGridData);
//		
//		Label locationName = new Label(client, SWT.NONE);
//		locationName.setText(Messages.get().TEXT_CLIMATE_LOCATION);
//		locationName.setLayoutData(horizontalGridData);
//		
//		// SWT.BOTTOM to show at the bottom
//        folder = new CTabFolder(client, SWT.TOP);
//		GridData folderGridData = new GridData(GridData.FILL_BOTH);
//		folderGridData.horizontalSpan = 3;
//		folderGridData.heightHint = 20;
//        folder.setLayoutData(folderGridData);
//
//        temperatureTabItem = new CTabItem(folder, SWT.NONE);
//        temperatureTabItem.setText(Messages.get().TEXT_TEMPERATURE);
//        diffuseRadiationTabItem = new CTabItem(folder, SWT.NONE);
//        diffuseRadiationTabItem.setText(Messages.get().TEXT_DIFFUSE_RADIATION);
//        directRadiationTabItem = new CTabItem(folder, SWT.NONE);
//        directRadiationTabItem.setText(Messages.get().TEXT_DIRECT_RADIATION);
//        
////		create default diagram (show temperatures)
//		createEmptyTable(Messages.get().TEXT_TEMPERATURE, Messages.get().TEXT_TEMPERATURE);
//		
//        folder.addSelectionListener(new SelectionListener() {
//			
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 3636860710772424032L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				Widget widget = e.item;
//				if(widget instanceof CTabItem) {
//					ClimateLocationTemplate template = controller.getSelectedClimateTemplate();
//					if(template != null) {
//						CTabItem tab = (CTabItem) widget;	
//						try {
//							MeasuredUnit unit = null;
//							if(tab == temperatureTabItem) {
//								createEmptyTable(Messages.get().TEXT_TEMPERATURE, Messages.get().TEXT_TEMPERATURE);
//								createDiagram(folder, Messages.get().TEXT_TEMPERATURE);
//								unit = controller.getTemplateService().getTemperaturesOfReferenceYear(template);		
//							} else if(tab == diffuseRadiationTabItem) {
//								createEmptyTable(Messages.get().TEXT_DIFFUSE_RADIATION, Messages.get().TEXT_DIFFUSE_RADIATION);
//								createDiagram(folder, Messages.get().TEXT_DIFFUSE_RADIATION);
//								unit = controller.getTemplateService().getDiffuseRadiation(template);
//							} else if(tab == directRadiationTabItem) {
//								createEmptyTable(Messages.get().TEXT_DIRECT_RADIATION, Messages.get().TEXT_DIRECT_RADIATION);
//								createDiagram(folder, Messages.get().TEXT_DIRECT_RADIATION);
//								unit = controller.getTemplateService().getDirectRadiation(template);
//							}
//							if(unit != null) showValues(unit);
//						} catch (IOException ex) {
//							MessageDialog.openError(
//									Display.getCurrent().getActiveShell(), 
//									Messages.get().MSG_ERR_PARSING_TEMPLATES, 
//									ex.getMessage());
//						}
//						tab.setControl(diagram);
//					}
//				}
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//        folder.setSelection(temperatureTabItem);
//		    
//		section.setClient(client);
//	    setControl(section);
//	}
//	
//	private void renderTemplates(final Combo combo) {
//		UICallBack.runNonUIThreadWithFakeContext( Display.getCurrent(), new Runnable() {
//			public void run() {
//				combo.removeAll();
//				combo.setText(Messages.get().TEXT_SELECTED_NOTHING);
//				try {
//					List<ClimateLocationTemplate> templates = controller.listClimateTemplates();
//					Collections.sort(templates);
//					for(int i = 0; i < templates.size(); i++) {
//						ClimateLocationTemplate template = templates.get(i);
//						combo.add(template.getRegion(), i);
//					}
//				} catch (Exception e) {
//					MessageDialog.openError(
//							Display.getCurrent().getActiveShell(), 
//							Messages.get().MSG_ERR_PARSING_TEMPLATES, 
//							Messages.get().MSG_ERR_PARSING_TEMPLATES);
//				}
//			}
//		});
//	}
//	
//	private void createEmptyTable(String id, String label) {
//		table = new JSONGoogleDataTable();
////		| time | Value |
//		table.addColumn(Messages.get().TEXT_TIME, Messages.get().TEXT_TIME, "datetime", null);
//		table.addColumn(id, label, "number", null);
//	}
//	
//	private void createDiagram(Composite parent, String title) {
//		diagram = new AnnotatedTimeLine( parent, SWT.NONE );
//		GridData gd3Col = new GridData(GridData.FILL_BOTH);
//		gd3Col.horizontalSpan = 3;
//		diagram.setLayoutData(gd3Col);
//		boolean showZoomButtons = false;
//		diagram.setWidgetOptions("" +
//				"{" +
//				"title: '"+title+"', " +
//				"width: 500, " +
//				"height: 300, " +
//				"displayAnnotations: true, " +
//				"thickness: 0, " + 
//				"displayZoomButtons: "+showZoomButtons+
//				"}");
//		diagram.setWidgetData(table.toString());
//	}
//	
//	private void showValues(MeasuredUnit unit) throws IOException {		
//		if(unit != null) {
//			Map<Long, Double> valuePerTimeUnit = unit.getValues();
//			for(Map.Entry<Long, Double> entry : valuePerTimeUnit.entrySet()) {
//				long time = entry.getKey();
//				double value = entry.getValue();
//				Date date = new Date(time);
//					
//				table.addRow(new Object[]{
//		//				time
//						date,
//		//				Temperature
//						value
//				});
//			}	
//	//	    use JSON format
//			diagram.setWidgetData(table.toString());
//		}
//	}
//	
//	private void hideDiagram() {
//		diagram.setVisible(false);
//	}
//	
//	private void showDiagram() {
//		diagram.setVisible(true);
//	}
//	
//	private void showUploadFileDialog() throws IOException {
//		Display display = Display.getCurrent();
//		Shell shell = new Shell(display);
//		
//		Rectangle rectBounds = display.getBounds();
//		int width = 640;
//		int height = 480;
//		int x = 0;
//		int y = 0;
//
//		//note: for multi-monitors negative x value is returned
//		if(rectBounds.x > 0) { //single monitor
//			// This formulae calculate the shell's Left ant Top
//			x = (rectBounds.width - width) / 2;
//			y = (rectBounds.height - height) / 2;
//		} else { //assumption 2 monitors that's why rectBounds.width is divided by 2
//			x = (rectBounds.width / 2 - width) / 2;
//			y = (rectBounds.height - height) / 2;
//		}
//
//		// based on the above calculations, set the window location
//		shell.setBounds(x, y, width, height);
//		
////		shell.setLocation(0, 0);
////		shell.setSize(640, 480);
//		FileDialog fileDialog = new FileDialog(shell, SWT.TITLE | SWT.MULTI);
//		fileDialog.setText(Messages.get().TEXT_UPLOAD_FILE);
////		fileDialog.setAutoUpload(true);
//		fileDialog.open();
//		String[] uploadedFileNames = fileDialog.getFileNames();
////		fileDialog.getFilterPath();
//		TemplateViewController.getInstance().uploadClimateTemplates(uploadedFileNames);
//	}
//
//}
