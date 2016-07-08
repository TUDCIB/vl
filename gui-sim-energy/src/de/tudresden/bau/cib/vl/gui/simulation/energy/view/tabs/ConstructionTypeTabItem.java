//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.jface.window.Window;
//import org.eclipse.rap.rwt.service.ServerPushSession;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.graphics.Rectangle;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Combo;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.FileDialog;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.TabFolder;
//import org.eclipse.swt.widgets.TabItem;
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.ScrolledForm;
//import org.eclipse.ui.forms.widgets.Section;
//
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
//import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.TemplateViewController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.model.ConstructionTemplateModel;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.ModifyOrCreateConstructionDialog;
//
//public class ConstructionTypeTabItem extends TabItem {	
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -6624368872076953434L;
//	private Combo combo;
//	private TemplateViewController controller = TemplateViewController.getInstance();
//	private Display display;
//	
//
//	public ConstructionTypeTabItem(TabFolder parent, int style) {
//		super(parent, style);
//		setText(Messages.get().TEXT_CONSTRUCTION_TEMPLATE);
//		controller.setConstructionTypeTabItem(this);
//	}
//	
//	public void createContent(TabFolder parent) {
//		this.display = parent.getDisplay();
//		ScrolledForm form = createContainer(parent);
//	    setControl(form);
//	}
//	
//	private ScrolledForm createContainer(TabFolder parent) {
//		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
//		ScrolledForm  form = toolkit.createScrolledForm(parent);
//		form.setText(Messages.get().TEXT_CONSTRUCTION_TEMPLATE);
//		
//		FillLayout lt = new FillLayout(SWT.VERTICAL);
//		form.getBody().setLayout(lt);
//		
//		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR);
//		section.setText(Messages.get().TEXT_CONSTRUCTION);
//		
//		Composite client = new Composite(section, SWT.NONE);
//		GridLayout layout = new GridLayout();
//		layout.numColumns = 6;
//		layout.marginWidth = 2;
//		layout.marginHeight = 2;
//		client.setLayout(layout);
//		
////		COL 1
//		toolkit.createLabel(client, Messages.get().TEXT_CONSTRUCTION, SWT.NONE);
////		COL 2
//		combo = new Combo(client, SWT.NONE);
//		combo.setText(Messages.get().TEXT_SELECTED_NOTHING);
//		renderTemplates(combo);
//		
//		combo.addSelectionListener(new SelectionListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -4709777609899245180L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				int selectionIndex = combo.getSelectionIndex();
//				controller.setSelectedConstructionTemplate(selectionIndex);
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//		
////		COL 3
//		Button editBtn = new Button(client, SWT.PUSH);
//		Image img = AppearanceFactory.getInstance().getImage(Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"pencil.png");
//		editBtn.setImage(img);
//		editBtn.setToolTipText("Edit template");
//		editBtn.addSelectionListener(new SelectionListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -3483004170698292472L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				final ServerPushSession pushSession = new ServerPushSession();
//				Runnable runnable = new Runnable() {
//					public void run() {
//						final ConstructionTemplate template = controller.getSelectedConstructionTemplate();
//
//						// schedule the UI update
//						display.asyncExec(new Runnable() {
//
//							@Override
//							public void run() {
//								ConstructionTemplateModel ctm = new ConstructionTemplateModel(template,controller);
//								if(template != null) {
//									ModifyOrCreateConstructionDialog dialog = new ModifyOrCreateConstructionDialog(
//											display.getActiveShell(), ctm);
//									dialog.create();
//									if (dialog.open() == Window.OK) {
//										renderTemplates(combo);
//									} 
//								}
//							}
//						});
//						pushSession.stop();
//					}};
//
//				pushSession.start();
//				new Thread(runnable).start();
//			}
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//
//		//	COL 4
//		Button deleteBtn = new Button(client, SWT.PUSH);
//		Image delImg = AppearanceFactory.getInstance().getImage(Activator.PLUGIN_ID, 
//				Activator.ICONS_16x16_PATH+"cancel.png");
//		deleteBtn.setImage(delImg);
//		deleteBtn.setToolTipText("Delete template");
//		deleteBtn.addSelectionListener(new SelectionListener() {
//
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1056969814770836728L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				final ServerPushSession pushSession = new ServerPushSession();
//				Runnable runnable = new Runnable() {
//					public void run() {
//						final ConstructionTemplate template = controller.getSelectedConstructionTemplate();
//						if(template != null) {
//							final boolean isDeleted = controller.deleteConstructionTemplate(template);
//
//							// schedule the UI update
//							display.asyncExec(new Runnable() {	
//								@Override
//								public void run() {	
//									if(isDeleted) {
//										renderTemplates(combo);
//	//									delete old selection
//										controller.setSelectedConstructionTemplate(-1);
//										renderTemplates(combo);
//										MessageDialog.openInformation(display.getActiveShell(), 
//												"Deleted Successfully", "The template was deleted successfully.");
//									}
//								}
//							});
//							pushSession.stop();
//					}
//					}};
//
//				pushSession.start();
//				new Thread(runnable).start();
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//		
////		COL 5
//		Button createBtn = new Button(client, SWT.PUSH);
//		createBtn.setText(Messages.get().TEXT_CREATE_TEMPLATE);
//		createBtn.addSelectionListener(new SelectionListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 4781404670090732116L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				final ServerPushSession pushSession = new ServerPushSession();
//
//				Runnable runnable = new Runnable() {
//					@Override
//					public void run() {
//						//	delete old selection
//						controller.setSelectedConstructionTemplate(-1);
//						
//						// schedule the UI update
//						display.asyncExec(new Runnable() {	
//							@Override
//							public void run() {	
//								ConstructionTemplateModel ctm = new ConstructionTemplateModel(null, controller);
//								ModifyOrCreateConstructionDialog dialog = new ModifyOrCreateConstructionDialog(display.getActiveShell(), ctm);
//								dialog.create();
//								if (dialog.open() == Window.OK) {
//									renderTemplates(combo);
//								}
//							}
//						});
//						pushSession.stop();
//					}
//				};
//				pushSession.start();
//				new Thread(runnable).start();
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//		
////		COL 6
//		Button uploadButton = toolkit.createButton(client, Messages.get().TEXT_UPLOAD_FILE, SWT.NONE);
//		uploadButton.addSelectionListener(new SelectionListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1056969814770836728L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				final ServerPushSession pushSession = new ServerPushSession();
//
//				Runnable runnable = new Runnable() {
//					@Override
//					public void run() {						
//						// schedule the UI update
//						display.asyncExec(new Runnable() {	
//							@Override
//							public void run() {	
//								try {
//									showUploadFileDialog();
//								} catch (IOException e) {
//									MessageDialog.openWarning(
//											Display.getCurrent().getActiveShell(), 
//											Messages.get().MSG_ERR_UPLOAD_FAILED, 
//											Messages.get().MSG_ERR_UPLOAD_FAILED);
//								}
//							}
//						});
//						pushSession.stop();
//					}
//				};
//				pushSession.start();
//				new Thread(runnable).start();
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//		    
//		section.setClient(client);
//		
//		return form;
//	}
//	
//	private void renderTemplates(final Combo combo) { 
//		final ServerPushSession pushSession = new ServerPushSession();
//
//		Runnable runnable = new Runnable() {
//			@Override
//			public void run() {						
//				// schedule the UI update
//				display.asyncExec(new Runnable() {	
//					@Override
//					public void run() {	
//						combo.removeAll();
//						combo.setText(Messages.get().TEXT_SELECTED_NOTHING);
//						try {
//							List<ConstructionTemplate> templates = controller.listConstructionTemplates();
//							for(int i = 0; i < templates.size(); i++) {
//								ConstructionTemplate template = templates.get(i);
//								combo.add(template.getName(), i);
//							}
//						} catch(Exception e) {
//							MessageDialog.openError(
//									display.getActiveShell(), 
//									Messages.get().MSG_ERR_PARSING_TEMPLATES, 
//									Messages.get().MSG_ERR_PARSING_TEMPLATES);
//						} 
//					}
//				});
//				pushSession.stop();
//			}
//		};
//		pushSession.start();
//		new Thread(runnable).start();
//	}
//	
//	private void showUploadFileDialog() throws IOException {
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
//		} else { 
//			//assumption 2 monitors that's why rectBounds.width is divided by 2
//			x = (rectBounds.width / 2 - width) / 2;
//			y = (rectBounds.height - height) / 2;
//		}
//
//		// based on the above calculations, set the window location
//		shell.setBounds(x, y, width, height);
//		
//		FileDialog fileDialog = new FileDialog(shell, SWT.TITLE | SWT.MULTI);
//		fileDialog.setText(Messages.get().TEXT_UPLOAD_FILE);
////		fileDialog.setAutoUpload(true);
//		fileDialog.open();
//		String[] uploadedFileNames = fileDialog.getFileNames();
////		fileDialog.getFilterPath();
//		controller.uploadConstructionTemplates(uploadedFileNames);
//	}
//
//}
