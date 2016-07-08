//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.jface.window.Window;
//import org.eclipse.rap.rwt.lifecycle.UICallBack;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.graphics.Rectangle;
//import org.eclipse.swt.layout.FillLayout;
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
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.ScrolledForm;
//import org.eclipse.ui.forms.widgets.Section;
//import org.joda.time.DateTime;
//
//import de.tudresden.bau.cib.ui.widgets.google.AnnotatedTimeLine;
//import de.tudresden.bau.cib.ui.widgets.google.JSONGoogleDataTable;
//import de.tudresden.bau.cib.vl.core.model.eeBim.HeatingAndCoolingInterval;
//import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;
//import de.tudresden.bau.cib.vl.core.model.energy.Schedule;
//import de.tudresden.bau.cib.vl.core.model.energy.Schedule.SCHEDULE_CYCLE;
//import de.tudresden.bau.cib.vl.core.model.energy.Schedule.TYPES;
//import de.tudresden.bau.cib.vl.core.model.time.DayHourTimePeriod;
//import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.TemplateViewController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.messages.Messages;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.ModifyOrCreateSpaceTypeTemplateDialog;
//
//public class SpaceTypeTabItem extends TabItem {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -1538589330096122344L;
//	private AnnotatedTimeLine diagram;
//	private JSONGoogleDataTable table;
//	private Combo combo;
//	private TemplateViewController controller = TemplateViewController.getInstance();
//	private Display display;
//	
//
//	public SpaceTypeTabItem(TabFolder parent, int style) {
//		super(parent, style);
//		setText(Messages.get().TEXT_SPACETYPE_TEMPLATE);
//		controller.setSpaceTypeTabItem(this);
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
//		form.setText(Messages.get().TEXT_SPACETYPE_TEMPLATE);
//		
//		FillLayout lt = new FillLayout(SWT.VERTICAL);
//		form.getBody().setLayout(lt);
//		
//		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR);
//		section.setText(Messages.get().TEXT_SPACETYPE);
//		
//		Composite client = new Composite(section, SWT.NONE);
//		GridLayout layout = new GridLayout();
//		layout.numColumns = 6;
//		layout.marginWidth = 2;
//		layout.marginHeight = 2;
//		client.setLayout(layout);
//		
////		COL 1
//		toolkit.createLabel(client, Messages.get().TEXT_SPACETYPE, SWT.NONE);
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
//			private static final long serialVersionUID = 2413640407968784004L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				int selectionIndex = combo.getSelectionIndex();
//				controller.setSelectedSpaceTypeTemplate(selectionIndex);
//				SpaceTypeTemplate template = controller.getSelectedSpaceTypeTemplate();
//				showValues(template);
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
//		editBtn.addSelectionListener(new SelectionListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -859337153937167383L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				UICallBack.runNonUIThreadWithFakeContext( display, new Runnable() {
//				
//					public void run() {
//						SpaceTypeTemplate template = controller.getSelectedSpaceTypeTemplate();
//						if(template != null) {
//							hideDiagram();
//							ModifyOrCreateSpaceTypeTemplateDialog dialog = new ModifyOrCreateSpaceTypeTemplateDialog(display.getActiveShell());
//							dialog.create();
//							if (dialog.open() == Window.OK) {
//								renderTemplates(combo);
//							} 
//							showDiagram();
//						}
//					}
//				});
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//		
////		COL 4
//		Button deleteBtn = new Button(client, SWT.PUSH);
//		Image delImg = AppearanceFactory.getInstance().getImage(Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"cancel.png");
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
//				UICallBack.runNonUIThreadWithFakeContext( display, new Runnable() {
//				
//					public void run() {
//						SpaceTypeTemplate template = controller.getSelectedSpaceTypeTemplate();
//						if(template != null) {
//							boolean isDeleted = controller.deleteSpaceTypeTemplate(template);
//							if(isDeleted) {
////								delete old selection
//								controller.setSelectedSpaceTypeTemplate(-1);
//								renderTemplates(combo);
//								MessageDialog.openInformation(display.getActiveShell(), 
//										"Deleted Successfully", "The template was deleted successfully.");
//							}
//						}
//					}
//				});
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
//			private static final long serialVersionUID = 8086854071988018007L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				UICallBack.runNonUIThreadWithFakeContext( Display.getCurrent(), new Runnable() {
//				
//					public void run() {
////						delete old selection
//						controller.setSelectedSpaceTypeTemplate(-1);
//						hideDiagram();
//						ModifyOrCreateSpaceTypeTemplateDialog dialog = new ModifyOrCreateSpaceTypeTemplateDialog(Display.getCurrent().getActiveShell());
//						dialog.create();
//						if (dialog.open() == Window.OK) {
//							renderTemplates(combo);
//						} 
//						showDiagram();
//					}
//				});
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
//			private static final long serialVersionUID = -8435104591051172796L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				UICallBack.runNonUIThreadWithFakeContext( Display.getCurrent(), new Runnable() {
//				public void run() {
//					try {
//						showUploadFileDialog();
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
//		Label separator = toolkit.createLabel(client, "");
//		GridData spaceData = new GridData();
//		spaceData.horizontalSpan = 5;
//		spaceData.heightHint = 350;
//		separator.setLayoutData(spaceData);
//		
//		createEmptyTable();
//		createDiagram(client);
//		    
//		section.setClient(client);
//		
//		return form;
//	}
//	
//	private void createEmptyTable() {
//		table = new JSONGoogleDataTable();
////		| time | Value |
//		table.addColumn(Messages.get().TEXT_TIME, Messages.get().TEXT_TIME, "datetime", null);
//		table.addColumn(Messages.get().TEXT_COOLING_SET_POINT, Messages.get().TEXT_COOLING_SET_POINT, "number", null);
//		table.addColumn(Messages.get().TEXT_HEATING_SET_POINT, Messages.get().TEXT_HEATING_SET_POINT, "number", null);
//	}
//	
//	private void createDiagram(Composite parent) {
//		diagram = new AnnotatedTimeLine( parent, SWT.NONE );
//		boolean showZoomButtons = false;
//		diagram.setWidgetOptions("" +
//				"{" +
//				"title: '"+Messages.get().TEXT_SCHEDULES+"', " +
//				"width: 500, " +
//				"height: 300, " +
////				"colors: ['blue', 'red'], " + // The colors to be used
////				"fill: 30, "+ // Fill the area below the lines with 30% opacity
//				"displayAnnotations: true, " +
//				"dateFormat : 'cccc:HH' ,"+
////				"dateFormat: 'dd:HH:mm'," +
//				"displayRangeSelector: false, " +
//				"thickness: 0, " + 
//				"displayZoomButtons: "+showZoomButtons+
//				"}");
//		diagram.setWidgetData(table.toString());
//		    
//		GridData gd3Col = new GridData(GridData.FILL_BOTH);
//		gd3Col.horizontalSpan = 5;
//		gd3Col.grabExcessHorizontalSpace = true;
//		gd3Col.horizontalAlignment = GridData.FILL;
//		diagram.setLayoutData(gd3Col);
//	}
//	
//	private void renderTemplates(final Combo combo) {
//		UICallBack.runNonUIThreadWithFakeContext( display, new Runnable() {
//			public void run() {
//				combo.removeAll();
//				combo.setText(Messages.get().TEXT_SELECTED_NOTHING);
//				try {
//					List<SpaceTypeTemplate> templates = controller.listSpaceTypeTemplates();
//					for(int i = 0; i < templates.size(); i++) {
//						SpaceTypeTemplate template = templates.get(i);
//						combo.add(template.getName(), i);
//					}
//				} catch(Exception e) {
//					MessageDialog.openError(
//							Display.getCurrent().getActiveShell(), 
//							Messages.get().MSG_ERR_PARSING_TEMPLATES, 
//							e.getMessage());
//				}
//			}
//		});
//	}
//	
//	/**
//	 * Fill Google table and show the values.
//	 * @param template
//	 */
//	@SuppressWarnings("unused")
//	public void showValues(SpaceTypeTemplate template) {
//		List<Schedule> schedules = template.getSchedules();
//		if(schedules != null) {
//			Schedule workdaySchedule = null;
//			Schedule weekendSchedule = null;
//			for(Schedule s : schedules) {
//				TYPES t = s.getType();
//				switch(t) {
//				case AllDays:
//					workdaySchedule = s;
//					break;						
//				case WeekEnd:
//					weekendSchedule = s;
//					break;
//				}
//			}
//			
//			List<DayHourTimePeriod> allIntervals = new ArrayList<DayHourTimePeriod>();
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("dd:HH:mm");
////			clear the table
//			createEmptyTable();
//			List<DayHourTimePeriod> intervals = workdaySchedule.getIntervals().get(SCHEDULE_CYCLE.DailyCycle);
//			allIntervals.addAll(intervals);
//			if(weekendSchedule != null) {
//				allIntervals.addAll(weekendSchedule.getIntervals().get(SCHEDULE_CYCLE.DailyCycle));
//			}
////			day starts on monday
//			DateTime day = new DateTime().withYear(2012).withMonthOfYear(1).withDayOfMonth(2).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
//			for(DayHourTimePeriod period : allIntervals) {
//				if(period instanceof HeatingAndCoolingInterval) {
//					HeatingAndCoolingInterval interval = (HeatingAndCoolingInterval) period;
//					int minimumHour = interval.getMinimumHour();
//					int maximumHour = interval.getMaximumHour();
//					int minmumDay = interval.getMinimumDay();
//					int maximumDay = interval.getMaximumDay();
//					Float coolingSetPoint = interval.getCoolingSetPoint();
//					Float heatingSetPoint = interval.getHeatingSetPoint();
//
////					daily addition
//					for(int j = minmumDay; j < (maximumDay+1); j++) { // minimumDay=1
//						day = day.withDayOfMonth(1+j);
//						
//						for(int k = minimumHour; k < (maximumHour+1); k++) {
//							day = day.withHourOfDay(0+k);
//							table.addRow(new Object[]{
//									day.toDate(),
//									coolingSetPoint,
//									heatingSetPoint
//							});
//						}
//					}
//				}
//			}
//			
//			if(weekendSchedule == null) { // no weekend => fill the weekend with '0' set points
////				weekend addition
//				for(int w = 6; w <= 7; w++) {
//					day = day.plusDays(1);
//					
//					for(int k = 0; k < (24); k++) {
//						day = day.withHourOfDay(k);
//						table.addRow(new Object[]{
//								day.toDate(),
//								0,
//								0
//						});
//					}
//				}
//			}
//
////		    use JSON format
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
//		} else { 
//			//assumption 2 monitors that's why rectBounds.width is divided by 2
//			x = (rectBounds.width / 2 - width) / 2;
//			y = (rectBounds.height - height) / 2;
//		}
//
//		// based on the above calculations, set the window location
//		shell.setBounds(x, y, width, height);
//		
//		FileDialog fileDialog = new FileDialog(shell, SWT.TITLE);
//		fileDialog.setText(Messages.get().TEXT_UPLOAD_FILE);
////		fileDialog.setAutoUpload(true);
//		fileDialog.open();
//		String[] uploadedFileNames = fileDialog.getFileNames();
////		fileDialog.getFilterPath();
//		controller.uploadSpaceTypeTemplates(uploadedFileNames);
//	}
//
//}
