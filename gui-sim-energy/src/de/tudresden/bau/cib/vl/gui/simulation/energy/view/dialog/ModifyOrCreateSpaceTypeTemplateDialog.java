//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.eclipse.jface.dialogs.IMessageProvider;
//import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.jface.dialogs.TitleAreaDialog;
//import org.eclipse.jface.resource.ImageDescriptor;
//import org.eclipse.jface.resource.JFaceResources;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.graphics.Point;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.DateTime;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.ScrolledForm;
//import org.eclipse.ui.forms.widgets.Section;
//
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
//
//public class ModifyOrCreateSpaceTypeTemplateDialog extends TitleAreaDialog {
//
//	
//	private final int PLUS = 9995;
//	private final int MINUS = 9996; 
//	
//	private Text tplNameText;
//	private Text tplShortDescText;
//	private Text tplLongDescText;
//	private Composite container, scheduleContainer;
//	private FormToolkit toolkit;
//	private ScrolledForm scrollForm;
//	
//	private List<DateTime> startTimeWidgets = new ArrayList<DateTime>();
//	private List<DateTime> endTimeWidgets 	= new ArrayList<DateTime>();
//	private List<Text> coolingSPTexts 		= new ArrayList<Text>();
//	private List<Text> heatingSPTexts 		= new ArrayList<Text>();
//	private List<Label> startTimeLabels 	= new ArrayList<Label>();
//	private List<Label> endTimeLabels 		= new ArrayList<Label>();
//	private List<Label> heatingSPLabels 	= new ArrayList<Label>();
//	private List<Label> coolingSPLabels 	= new ArrayList<Label>();
//	private List<Button> weekEndButtons 	= new ArrayList<Button>();
//	private List<Button> holidayButtons 	= new ArrayList<Button>();
//	private List<Label> separators 			= new ArrayList<Label>();
//	
//	private TemplateViewController controller = TemplateViewController.getInstance();
//	
//
//	public ModifyOrCreateSpaceTypeTemplateDialog(Shell parentShell) {
//		super(parentShell);
//	}
//
//	@Override
//	public void create() {
//		super.create();
//		setTitle(Messages.get().TEXT_EDIT_TEMPLATE);
//		setMessage(Messages.get().TEXT_DESCRIPTION_SPACETYPE_TEMPLATE, IMessageProvider.INFORMATION);
//
//	}
//	
//	@Override
//	protected Point getInitialSize() {
//		Point shellSize = super.getInitialSize();
//		return new Point(Math.max(
//				convertHorizontalDLUsToPixels(640), shellSize.x),
//				Math.max(convertVerticalDLUsToPixels(480),
//						shellSize.y));
//	}
//
//	@Override
//	protected Control createDialogArea(Composite parent) {
//        Composite area = (Composite) super.createDialogArea(parent);
//        toolkit = new FormToolkit(area.getDisplay());
//        
//		ScrolledForm  form = toolkit.createScrolledForm(area);
//		
//        Section metaSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR);
//        metaSection.setText(Messages.get().TEXT_INFORMATION);
//        metaSection.setLayoutData(new GridData(GridData.FILL_BOTH));
//        container = toolkit.createComposite(metaSection);
//        container.setLayoutData(new GridData(GridData.FILL_BOTH));
//        
//		GridLayout layout1 = new GridLayout();
//		layout1.numColumns = 2;
//		// layout.horizontalAlignment = GridData.FILL;
//		container.setLayout(layout1);
//
//		// The text fields will grow with the size of the dialog
//		GridData gridData1 = new GridData();
//		gridData1.grabExcessHorizontalSpace = true;
//		gridData1.horizontalSpan = 1;
//		gridData1.horizontalAlignment = GridData.FILL;
//		
//		SpaceTypeTemplate template = controller.getSelectedSpaceTypeTemplate();
//		
////		NAME
//		toolkit.createLabel(container, Messages.get().TEXT_NAME+"*");
//
//		if(template == null) {
//			tplNameText = toolkit.createText(container,Messages.get().TEXT_INPUT, SWT.BORDER);
//			tplNameText.setLayoutData(gridData1);
//		} else {
//			tplNameText = toolkit.createText(container,template.getName(), SWT.BORDER);
//			tplNameText.setLayoutData(gridData1);
//		}
////		<BR/>
//		
////		SHORT DESCRIPTION
//		toolkit.createLabel(container, Messages.get().TEXT_SHORT_DESCRIPTION);
//		if(template == null) {
//			tplShortDescText = toolkit.createText(container, Messages.get().TEXT_INPUT, SWT.BORDER);
//			tplShortDescText.setLayoutData(gridData1);
//		} else {
//			tplShortDescText = toolkit.createText(container, template.getShortDescription(), SWT.BORDER);
//			tplShortDescText.setLayoutData(gridData1);
//		}
////		<BR/>
//		
////		LONG DESCRIPTION
//		toolkit.createLabel(container, Messages.get().TEXT_LONG_DESCRIPTION);
//		if(template == null) {
//			tplLongDescText = toolkit.createText(container, Messages.get().TEXT_INPUT, SWT.BORDER);
//			tplLongDescText.setLayoutData(gridData1);
//		} else {
//			tplLongDescText = toolkit.createText(container, template.getLongDescription(), SWT.BORDER);
//			tplLongDescText.setLayoutData(gridData1);
//		}
////		<BR/>		
//		metaSection.setClient(container);
//		
//		Section scheduleSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR);
//		scheduleSection.setText(Messages.get().TEXT_SCHEDULES);
//		scheduleSection.setLayoutData(new GridData(GridData.FILL_BOTH));
//		
//		scrollForm = toolkit.createScrolledForm(scheduleSection);
//		scheduleContainer = scrollForm.getBody();
////		scheduleContainer = toolkit.createComposite(scheduleSection);
//		scheduleContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
//
//		GridLayout layout2 = new GridLayout();
//		layout2.numColumns = 6;
//		// layout2.horizontalAlignment = GridData.FILL;
//		scheduleContainer.setLayout(layout2);
//		
//		if(template != null) {
//			List<Schedule> schedules = template.getSchedules();
//			addScheduleIntervals(schedules);
//		} else {		
//			addInterval();
//		}
//	
//		scheduleSection.setClient(scrollForm);
//		return area;
//	}
//	
//	private void addScheduleIntervals(List<Schedule> schedules) {
//		int controlIndex = 0; // for retrieving the right controls of the ArrayList (Label, Text, DateTime)
//		for(int i = 0; i < schedules.size(); i++) {
//			Schedule schedule = schedules.get(i);
//			TYPES scheduleType = schedule.getType();
//			List<DayHourTimePeriod> intervals = schedule.getIntervals().get(SCHEDULE_CYCLE.DailyCycle);
//			for(int j = 0; j < intervals.size(); j++) {
//				boolean isLastInterval = (j+1) == intervals.size();
//				HeatingAndCoolingInterval hci = (HeatingAndCoolingInterval) intervals.get(j);
//				
////				add widgets
//				addInterval();
//				
//				DateTime startTimeWidget 	= startTimeWidgets.get(controlIndex);
//				DateTime endTimeWidget 		= endTimeWidgets.get(controlIndex);
//				
//				int start 					= hci.getMinimumHour();
//				int end 					= hci.getMaximumHour();		
//				
//				startTimeWidget.setHours(start);				
//				
//				if(isLastInterval) {
//					endTimeWidget.setHours(0);
//				} else {	
////					handle special cases
//					if(end == 23) { // check if there is an interval for 23:00-24:00
//						DayHourTimePeriod nextInterval = intervals.get(j+1);
//						if(nextInterval.getMinimumHour() == 23) { // there is an interval from 23:00-24:00
//							endTimeWidget.setHours(23);
//						} else {	// no interval
//							endTimeWidget.setHours(0);
//						}
//					} else {
//						endTimeWidget.setHours(end);
//					}
//				}
//				
//				Float cooling = hci.getCoolingSetPoint();
//				if(cooling != null) coolingSPTexts.get(controlIndex).setText(""+cooling);
//				Float heating = hci.getHeatingSetPoint();
//				if(heating != null) heatingSPTexts.get(controlIndex).setText(""+heating);	
//				
//				switch(scheduleType) {
//				case AllDays: break;
//				case WeekEnd: 
//					weekEndButtons.get(controlIndex).setSelection(true);
//					break;
//				case Holiday: 
//					holidayButtons.get(controlIndex).setSelection(true);
//					break;
//				}
//				
//				controlIndex++;
//			}
//		}
//	}
//
//	private void addInterval() {
//		// The text fields will grow with the size of the dialog
//		GridData gridData2 = new GridData();
//		gridData2.grabExcessHorizontalSpace = true;
//		gridData2.horizontalSpan = 1;
//		gridData2.widthHint = 20;
//		gridData2.horizontalAlignment = GridData.FILL;
//		
//		GridData timeGridData = new GridData();
//		timeGridData.horizontalSpan = 2;
//		timeGridData.grabExcessHorizontalSpace = false;
////		timeGridData.widthHint = 20;
//		timeGridData.horizontalAlignment = GridData.BEGINNING;
//		
//		GridData gridFillAll = new GridData();
//		gridFillAll.grabExcessHorizontalSpace = true;
//		gridFillAll.horizontalSpan = 6;
//		gridFillAll.horizontalAlignment = GridData.FILL;
//
////		START TIME
//		Label startTimeLabel = toolkit.createLabel(scheduleContainer, Messages.get().TEXT_STARTTIME+"*");
//		startTimeLabels.add(startTimeLabel);
//		DateTime startDateTimeWidget = new DateTime(scheduleContainer, SWT.TIME | SWT.SHORT);
//		startDateTimeWidget.setTime(0, 0, 0);
//		startDateTimeWidget.setLayoutData(timeGridData);
//		toolkit.adapt(startDateTimeWidget);
//		startTimeWidgets.add(startDateTimeWidget);		
//		
////		END TIME
//		Label endTimeLabel = toolkit.createLabel(scheduleContainer, Messages.get().TEXT_ENDTIME+"*");	
//		endTimeLabels.add(endTimeLabel);
//		DateTime endDateTimeWidget = new DateTime(scheduleContainer, SWT.TIME | SWT.SHORT);
//		endDateTimeWidget.setTime(0, 0, 0);
//		endDateTimeWidget.setLayoutData(timeGridData);
//		toolkit.adapt(endDateTimeWidget);
//		endTimeWidgets.add(endDateTimeWidget);	
////		<BR/>
//		
////		HEATING SET POINT
//		Label heatingSPLabel = toolkit.createLabel(scheduleContainer, Messages.get().TEXT_HEATING_SET_POINT);
//		heatingSPLabels.add(heatingSPLabel);
//		Text heatingSPText = toolkit.createText(scheduleContainer, "-", SWT.BORDER);
//		heatingSPText.setLayoutData(gridData2);
//		heatingSPTexts.add(heatingSPText);		
//		
////		COOLING SET POINT
//		Label coolingSPLabel = toolkit.createLabel(scheduleContainer, Messages.get().TEXT_COOLING_SET_POINT);
//		coolingSPLabels.add(coolingSPLabel);
//		Text coolingSPText = toolkit.createText(scheduleContainer, "-", SWT.BORDER);
//		coolingSPText.setLayoutData(gridData2);
//		coolingSPTexts.add(coolingSPText);		
//		
////		WEEKEND RADIO BUTTON
//		Button weekendButton = toolkit.createButton(scheduleContainer, Messages.get().TEXT_WEEKEND, SWT.CHECK);
//		weekendButton.setLayoutData(gridData2);
//		weekEndButtons.add(weekendButton);
//		
////		HOLIDAY RADIO BUTTON
//		Button holidayButton = toolkit.createButton(scheduleContainer, Messages.get().TEXT_HOLIDAYS, SWT.CHECK);
//		holidayButton.setLayoutData(gridData2);
//		holidayButtons.add(holidayButton);
////		<BR/>	
//		
////		SEPARATOR
//		Label separator = toolkit.createSeparator(scheduleContainer, SWT.SEPARATOR | SWT.HORIZONTAL);
//		separator.setLayoutData(gridFillAll);
//		separators.add(separator);
//		
//		scrollForm.reflow(true);
//	}
//	
//	private void disposeInterval() {
//		int index = startTimeWidgets.size()-1;
//		if(index <= 1) return; // at least one interval is needed
//		
//		DateTime startTime = startTimeWidgets.remove(index);
//		if(startTime != null) startTime.dispose();
//		
//		DateTime endTime = endTimeWidgets.remove(index);
//		if(endTime != null) endTime.dispose();
//		
//		Label startTimeLabel = startTimeLabels.remove(index);
//		if(startTimeLabel != null) startTimeLabel.dispose();
//		
//		Label endTimeLabel = endTimeLabels.remove(index);
//		if(endTimeLabel != null) endTimeLabel.dispose();
//		
//		Label coolingSPLabel = coolingSPLabels.remove(index);
//		if(coolingSPLabel != null) coolingSPLabel.dispose();
//		
//		Text coolingSPText = coolingSPTexts.remove(index);
//		if(coolingSPText != null) coolingSPText.dispose();
//		
//		Label heatingSPLabel = heatingSPLabels.remove(index);
//		if(heatingSPLabel != null) heatingSPLabel.dispose();
//		
//		Text heatingSPText = heatingSPTexts.remove(index);
//		if(heatingSPText != null) heatingSPText.dispose();
//		
//		Button weekendCheckButton = weekEndButtons.remove(index);
//		if(weekendCheckButton != null) weekendCheckButton.dispose();
//		
//		Button holidayCheckButton = holidayButtons.remove(index);
//		if(holidayCheckButton != null) holidayCheckButton.dispose();
//		
//		Label separator	= separators.remove(index);
//		if(separator != null) separator.dispose();
//		
//		scrollForm.reflow(true);
//	}
//
//	@Override
//	protected void createButtonsForButtonBar(Composite parent) {
//		GridData gridData = new GridData();
//		gridData.verticalAlignment = GridData.FILL;
//		gridData.horizontalSpan = 3;
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
//		plusButton.setToolTipText(Messages.get().TEXT_BTN_TT_ADD_MATERIAL);
//		minusButton.setToolTipText(Messages.get().TEXT_BTN_TT_REM_MATERIAL);
//		
//		plusButton.addSelectionListener(new SelectionListener() {
//			
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -1421123327709752599L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				addInterval();
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
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1943543671262135691L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				disposeInterval();
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//		
//		// Create Add button
//		createOkButton(parent, OK, Messages.get().ACTION_SAVE, true);
//		// Create Cancel button
//		Button cancelButton = createButton(parent, CANCEL, Messages.get().ACTION_CANCEL, false);
//		// Add a SelectionListener
//		cancelButton.addSelectionListener(new SelectionAdapter() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -410619903232336068L;
//
//			public void widgetSelected(SelectionEvent e) {
//				setReturnCode(CANCEL);
//				close();
//			}
//		});
//	}
//
//	protected Button createOkButton(Composite parent, int id, String label, boolean defaultButton) {
//		// increment the number of columns in the button bar
//		((GridLayout) parent.getLayout()).numColumns++;
//		Button button = new Button(parent, SWT.PUSH);
//		button.setText(label);
//		button.setFont(JFaceResources.getDialogFont());
//		button.setData(new Integer(id));
//		button.addSelectionListener(new SelectionAdapter() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -2539585911671954763L;
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
//	private boolean isValidInput() {
//		boolean valid = true;
//		if (tplNameText.getText().length() == 0 || tplNameText.getText().equalsIgnoreCase(Messages.get().TEXT_INPUT)) {
//			setErrorMessage(Messages.get().MSG_ERR_INPUT_NAME);
//			valid = false;
//		}
//		return valid;
//	}
//
//	@Override
//	protected boolean isResizable() {
//		return true;
//	}
//
//	private boolean saveInput() {
//		TemplateViewController controller = TemplateViewController.getInstance();
//		
//		SpaceTypeTemplate template = controller.getSelectedSpaceTypeTemplate();
//		if(template == null) template = new SpaceTypeTemplate();
//		
//		String name = tplNameText.getText();
//		String longDesc = tplLongDescText.getText();
//		String shortDesc = tplShortDescText.getText();
//		
//		template.setName(name);
//		template.setLongDescription(longDesc);
//		template.setShortDescription(shortDesc);
//		List<Schedule> schedules = new ArrayList<Schedule>();
//		Schedule allDaySchedule = null;
//		Schedule weekEndSchedule = null;
//		Schedule holidaySchedule = null;
//		for(int i = 0; i < startTimeWidgets.size(); i++) {
//			DateTime startTime = startTimeWidgets.get(i);
//			DateTime endTime = endTimeWidgets.get(i);
//			String cooling = coolingSPTexts.get(i).getText();
//			String heating = heatingSPTexts.get(i).getText();
//			
//			HeatingAndCoolingInterval hci = new HeatingAndCoolingInterval();
//			int startHours = startTime.getHours();
//			int endHours = endTime.getHours();
//			
//			if(endHours == 0) endHours = 23; // 00:00 not permitted for end time
//			
////			int startMinutes = startTime.getMinutes();
////			int endMinutes = endTime.getMinutes();
////			int startSeconds = startTime.getSeconds();
////			int endSeconds = endTime.getSeconds();
//			
//			hci.addAcceptedHours(startHours+"-"+endHours);
//			try {
//				hci.setCoolingSetPoint(Float.valueOf(cooling));	
//				template.setCoolingControlMode("OperativeTemperature");
//			} catch (NumberFormatException e) {
////				do nothing
//			}
//			try {
//				hci.setHeatingSetPoint(Float.valueOf(heating));		
//				template.setHeatingControlMode("OperativeTemperature");
//			} catch (NumberFormatException e) {
////				do nothing
//			}
//			
//			boolean holiday = holidayButtons.get(i).getSelection();
//			boolean weekend = weekEndButtons.get(i).getSelection();
//			
//			if(holiday) {
//				if(holidaySchedule == null) holidaySchedule = new Schedule(TYPES.Holiday);
//				holidaySchedule.addInterval(SCHEDULE_CYCLE.DailyCycle, hci);
//			} else if(weekend) {
//				if(weekEndSchedule == null) weekEndSchedule = new Schedule(TYPES.WeekEnd);
//				hci.setAcceptedDays("6-7");
//				weekEndSchedule.addInterval(SCHEDULE_CYCLE.DailyCycle, hci);
//			} else {
//				if(allDaySchedule == null) allDaySchedule = new Schedule(TYPES.AllDays);
//				hci.setAcceptedDays("1-5");
//				allDaySchedule.addInterval(SCHEDULE_CYCLE.DailyCycle, hci);
//			}
//		}
//		if(allDaySchedule != null) schedules.add(allDaySchedule);
//		if(weekEndSchedule != null) schedules.add(weekEndSchedule);
//		if(holidaySchedule != null) schedules.add(holidaySchedule);
//		
//		template.setSchedules(schedules);
//		boolean success = false;
//		try {
//			success = controller.saveSpaceTypeTemplate(template);			
//			if(success) {
//		    	MessageDialog.openInformation(Display.getCurrent().getActiveShell(), 
//		    			Messages.get().ACTION_SPACETYPE_SAVED, 
//		    			Messages.get().ACTION_SPACETYPE_SAVED);
//			}
//		} catch (Exception e) {
//	    	MessageDialog.openError(Display.getCurrent().getActiveShell(), 
//			Messages.get().ACTION_SAVE_FAILED, 
//			e.getMessage());
//		}
////		controller.setSelectedSpaceTypeTemplate(template);
//		return success;
//	}
//
//	@Override
//	protected void okPressed() {
//		boolean isOk = saveInput();
//		if(isOk) super.okPressed();
//	}
//} 
