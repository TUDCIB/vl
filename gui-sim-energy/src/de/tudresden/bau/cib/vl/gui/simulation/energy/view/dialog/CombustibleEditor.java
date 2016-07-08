///*******************************************************************************
// * Copyright (c) 2009 - 2013  TU Dresden, CIB
// * Author: Sebastian Fuchs
// * All rights reserved.
// *******************************************************************************/
//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.eclipse.core.databinding.DataBindingContext;
//import org.eclipse.core.databinding.UpdateValueStrategy;
//import org.eclipse.core.databinding.conversion.NumberToStringConverter;
//import org.eclipse.core.databinding.conversion.StringToNumberConverter;
//import org.eclipse.core.databinding.observable.Observables;
//import org.eclipse.core.databinding.observable.list.IObservableList;
//import org.eclipse.core.databinding.observable.map.IObservableMap;
//import org.eclipse.core.databinding.observable.set.IObservableSet;
//import org.eclipse.core.databinding.observable.value.IObservableValue;
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.core.runtime.IStatus;
//import org.eclipse.core.runtime.Status;
//import org.eclipse.core.runtime.jobs.Job;
//import org.eclipse.emf.common.command.BasicCommandStack;
//import org.eclipse.emf.common.notify.AdapterFactory;
//import org.eclipse.emf.databinding.edit.EMFEditProperties;
//import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
//import org.eclipse.emf.ecore.EStructuralFeature;
//import org.eclipse.emf.ecore.util.EcoreUtil;
//import org.eclipse.emf.edit.command.ChangeCommand;
//import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
//import org.eclipse.emf.edit.domain.EditingDomain;
//import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
//import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
//import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
//import org.eclipse.jface.action.Action;
//import org.eclipse.jface.action.IToolBarManager;
//import org.eclipse.jface.databinding.swt.SWTObservables;
//import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
//import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
//import org.eclipse.jface.databinding.viewers.ObservableMapCellLabelProvider;
//import org.eclipse.jface.databinding.viewers.ViewersObservables;
//import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.jface.dialogs.TitleAreaDialog;
//import org.eclipse.jface.viewers.ISelectionChangedListener;
//import org.eclipse.jface.viewers.IStructuredSelection;
//import org.eclipse.jface.viewers.SelectionChangedEvent;
//import org.eclipse.jface.viewers.StructuredSelection;
//import org.eclipse.jface.viewers.TableViewer;
//import org.eclipse.jface.viewers.TableViewerColumn;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.custom.SashForm;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.TableItem;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.forms.widgets.Form;
//import org.eclipse.ui.forms.widgets.FormToolkit;
//import org.eclipse.ui.forms.widgets.ScrolledForm;
//
//import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
//import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;
//import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleFactory;
//import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage;
//import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.provider.CombustibleItemProviderAdapterFactory;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.EnergyKeyPerformanceIndicatorController;
//
//
///**
// * Editor for EnEV Combustible Model
// *
// * @author Sebastian Fuchs
// * @author Ken Baumgaertel
// * @since 22.07.2013
// */
//public class CombustibleEditor extends TitleAreaDialog {
//
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 8836069807998651245L;
//	private Form form;
//	private TableViewer masterTableViewer;
//	private File file;
//	private CombustibleContainer combustibleContainer;
//	private EditingDomain editingDomain;
//
//	private FormToolkit toolkit;
//
//	private DataBindingContext dbc;
//	private List<Combustible> selectedCombustibles;
//	
//	
//	public CombustibleEditor(Shell parentShell, File file) {
//		super(parentShell);
//		this.file = file;
//		selectedCombustibles = new ArrayList<Combustible>();
//	}
//	
//	@Override
//	public Control createContents(Composite parent) {
////		setTitle(Messages.get().TEXT_EDIT_TEMPLATE);
////		setMessage(Messages.get().TEXT_DESCRIPTION_CONSTRUCTION_TEMPLATE, IMessageProvider.INFORMATION);
//		
//		try {
//			combustibleContainer = EnergyKeyPerformanceIndicatorController.getInstance().loadCombustibles(file);
//		} catch (IOException e) {
//			MessageDialog.openError(getParentShell(), "Load Combustibles error", "Cannot load combustibles");
//		}
//		List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
//		factories.add(new ResourceItemProviderAdapterFactory());
//		factories.add(new CombustibleItemProviderAdapterFactory());
//		factories.add(new ReflectiveItemProviderAdapterFactory());
//		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(factories);
//		BasicCommandStack commands = new BasicCommandStack();
//		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commands);
//		
//		return super.createContents(parent);
//	}
//
//	@Override
//	protected Control createDialogArea(Composite parent) {
//		Composite area = (Composite) super.createDialogArea(parent);
//		toolkit = new FormToolkit(area.getDisplay());
//		ScrolledForm sf = toolkit.createScrolledForm(area);
//		form = sf.getForm();
////		form = toolkit.createForm(parent);
//		toolkit.decorateFormHeading(form);
//		form.setText("");
//
////		IToolBarManager toolBarManager = form.getToolBarManager();
////		toolBarManager.add(new Action("Save combustible catalogue"){
////			/**
////			 * 
////			 */
////			private static final long serialVersionUID = 58530419525049821L;
////
////			@Override
////			public void run() {
////				save();
////			}
////		});
////		toolBarManager.update(true);
//
//		Composite body = form.getBody();
//		body.setLayout(new FillLayout());
//		SashForm sashForm = new SashForm(body, SWT.HORIZONTAL | SWT.SMOOTH);
//		sashForm.setSashWidth(8);
//
//		Table masterTable = toolkit.createTable(sashForm, SWT.MULTI | SWT.FULL_SELECTION);
//		masterTable.setHeaderVisible(true);
//		masterTable.setLinesVisible(true);
//		makeTableViewer(masterTable);
//
//		ScrolledForm detailScrolledForm = toolkit.createScrolledForm(sashForm);
//		Form detailForm = detailScrolledForm.getForm();
//		toolkit.decorateFormHeading(detailForm);
//		detailForm.setText("Combustible");	
//		
//		IToolBarManager combustibleDetailedToolbar = detailForm.getToolBarManager();
//		combustibleDetailedToolbar.add(new Action("New"){
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 4292627871042335353L;
//
//			@Override
//			public void run() {
//				ChangeCommand changeCommand = new ChangeCommand(combustibleContainer) {
//
//					@Override
//					protected void doExecute() {
//						Combustible newCombustible = CombustibleFactory.eINSTANCE.createCombustible();
//						newCombustible.setId(EcoreUtil.generateUUID());
//						newCombustible.setName("New combustible");
//						combustibleContainer.getCombustibles().add(newCombustible);
//						masterTableViewer.setSelection(new StructuredSelection(newCombustible));
//					}
//				};
//				editingDomain.getCommandStack().execute(changeCommand);
//			}
//		});
//		combustibleDetailedToolbar.add(new Action("Delete"){
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 6590747201787072180L;
//
//			@Override
//			public void run() {
//				ChangeCommand changeCommand = new ChangeCommand(combustibleContainer) {
//					@Override
//					protected void doExecute() {
//						IStructuredSelection selection = (IStructuredSelection) masterTableViewer.getSelection();
//						if (selection == null || selection.isEmpty()){
//							return;
//						}
//						Combustible masterCombustible = (Combustible) selection.getFirstElement();
//						combustibleContainer.getCombustibles().remove(masterCombustible);
//					}
//				};
//				editingDomain.getCommandStack().execute(changeCommand);
//			}
//		});
//		combustibleDetailedToolbar.add(new Action("Save combustible catalogue"){
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 58530419525049821L;
//
//			@Override
//			public void run() {
//				save();
//			}
//		});
//		combustibleDetailedToolbar.update(true);
//		
//		
//		makeDetailBody(detailForm.getBody());
//
//		sashForm.setWeights(new int[]{50, 50});
//		return area;
//	}
//	
//	private void makeDetailBody(Composite parent) {
//		parent.setLayout(new GridLayout(3, false));
//		dbc = new DataBindingContext();
//
//		Text textID = createText(parent, "ID", null, CombustiblePackage.Literals.COMBUSTIBLE__ID);
//		textID.setEditable(false);
//		textID.setEnabled(false);
//		createText(parent, "Name", null, CombustiblePackage.Literals.COMBUSTIBLE__NAME);
//		createText(parent, "Unit", null, CombustiblePackage.Literals.COMBUSTIBLE__UNIT);
//		createTextDouble(parent, "Heating value H_i", "kWh/Unit", CombustiblePackage.Literals.COMBUSTIBLE__HEATING_VALUE_HI);
//		createTextDouble(parent, "Useful heat H_s", "kWh/Unit", CombustiblePackage.Literals.COMBUSTIBLE__USEFUL_HEAT_HS);
//		createTextDouble(parent, "Ration H_i/H_s", null, CombustiblePackage.Literals.COMBUSTIBLE__RATIO_HS_TO_HI);
//		createTextDouble(parent, "Price per Anno", "EUR/Year", CombustiblePackage.Literals.COMBUSTIBLE__BASE_PRICE_PER_ANNO);
//		createTextDouble(parent, "Price per Unit", "Cent/Unit", CombustiblePackage.Literals.COMBUSTIBLE__PRICE_PER_UNIT);
//		createTextDouble(parent, "Price per kWh", "Cent/kWh", CombustiblePackage.Literals.COMBUSTIBLE__PRICE_PER_KWH);
//		createTextDouble(parent, "Storage yield", "%", CombustiblePackage.Literals.COMBUSTIBLE__STORAGE_YIELD);
//		createTextDouble(parent, "Primary energy factor", null, CombustiblePackage.Literals.COMBUSTIBLE__PRIMARY_ENERGY_FACTOR);
//		createTextDouble(parent, "CO2-Emission factor", "g/kWh", CombustiblePackage.Literals.COMBUSTIBLE__EMISSION_CO2);
//		createTextDouble(parent, "SO2-Emission factor", "g/kWh", CombustiblePackage.Literals.COMBUSTIBLE__EMISSION_SO2);
//		createTextDouble(parent, "NOX-Emission factor", "g/kWh", CombustiblePackage.Literals.COMBUSTIBLE__EMISSION_NOX);
//	}
//	
//	private Text createText(Composite parent, String label, String unit, EStructuralFeature feature){
//		return internalMakeText(parent, label, unit, feature, null, null);
//	}
//	
//	private Text createTextDouble(Composite parent, String label, String unit, EStructuralFeature feature){
//		UpdateValueStrategy targetToModel = new UpdateValueStrategy().setConverter(StringToNumberConverter.toDouble(true));
//		UpdateValueStrategy modelToTarget = new UpdateValueStrategy().setConverter(NumberToStringConverter.fromDouble(true));
//		return internalMakeText(parent, label, unit, feature, targetToModel, modelToTarget);
//	}
//	
//	private Text internalMakeText(Composite parent, String label, String unit, EStructuralFeature feature, UpdateValueStrategy targetToModel, UpdateValueStrategy modelToTarget){
//		toolkit.createLabel(parent, label);
//		Text text = toolkit.createText(parent, "");
//		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		toolkit.createLabel(parent, unit);
//
//		IViewerObservableValue masterSelection = ViewersObservables.observeSingleSelection(masterTableViewer);
//		IObservableValue modelObservableDetail = EMFEditProperties.value(editingDomain, feature).observeDetail(masterSelection);
//
//		IObservableValue delayedTextValue = Observables.observeDelayedValue(300, SWTObservables.observeText(text, SWT.Modify));
//
//		dbc.bindValue(delayedTextValue, modelObservableDetail, targetToModel, modelToTarget);
//
//		return text;
//	}
//
//	private void makeTableViewer(Table masterTable) {
//		masterTableViewer = new TableViewer(masterTable);
//		ObservableListContentProvider contentProvider = new ObservableListContentProvider();
//		IObservableSet knownElements = contentProvider.getKnownElements();
//		masterTableViewer.setContentProvider(contentProvider);
//		
//		masterTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
//
//			@Override
//			public void selectionChanged(SelectionChangedEvent sce) {
//				selectedCombustibles.clear();
//				TableItem[] selection = masterTableViewer.getTable().getSelection();
//				for(TableItem selected : selection) {
//					Combustible combustible = (Combustible) selected.getData();
//					if(combustible != null) selectedCombustibles.add(combustible);
//				}
//			}
//			
//		});
//
//		IObservableMap attributeMapName = EMFEditProperties.value(editingDomain,
//				CombustiblePackage.Literals.COMBUSTIBLE__NAME
//				).observeDetail(knownElements);
//		TableViewerColumn columnName = new TableViewerColumn(masterTableViewer, SWT.LEFT);
//		columnName.getColumn().setText("Combustible");
//		columnName.getColumn().setWidth(150);
//		columnName.getColumn().setMoveable(false);
//		columnName.getColumn().setResizable(true);
//		columnName.setLabelProvider(new ObservableMapCellLabelProvider(attributeMapName));
//
//		IObservableMap attributeMapHeatingValue = EMFEditProperties.value(editingDomain,
//				CombustiblePackage.Literals.COMBUSTIBLE__HEATING_VALUE_HI
//				).observeDetail(knownElements);
//		TableViewerColumn columnHeatingValue = new TableViewerColumn(masterTableViewer, SWT.RIGHT);
//		columnHeatingValue.getColumn().setText("Heating value [kWh/Einheit]");
//		columnHeatingValue.getColumn().setWidth(100);
//		columnHeatingValue.getColumn().setMoveable(false);
//		columnHeatingValue.getColumn().setResizable(true);
//		columnHeatingValue.setLabelProvider(new ObservableMapCellLabelProvider(attributeMapHeatingValue));
//
//		IObservableMap attributeMapUnit = EMFEditProperties.value(editingDomain,
//				CombustiblePackage.Literals.COMBUSTIBLE__UNIT
//				).observeDetail(knownElements);
//		TableViewerColumn columnUnit = new TableViewerColumn(masterTableViewer, SWT.LEFT);
//		columnUnit.getColumn().setText("Unit");
//		columnUnit.getColumn().setWidth(50);
//		columnUnit.getColumn().setMoveable(false);
//		columnUnit.getColumn().setResizable(true);
//		columnUnit.setLabelProvider(new ObservableMapCellLabelProvider(attributeMapUnit));
//
//		IObservableMap attributeBasePrice = EMFEditProperties.value(editingDomain,
//				CombustiblePackage.Literals.COMBUSTIBLE__BASE_PRICE_PER_ANNO
//				).observeDetail(knownElements);
//		TableViewerColumn columnBasePrice = new TableViewerColumn(masterTableViewer, SWT.RIGHT);
//		columnBasePrice.getColumn().setText("Base price [EUR/a]");
//		columnBasePrice.getColumn().setWidth(100);
//		columnBasePrice.getColumn().setMoveable(false);
//		columnBasePrice.getColumn().setResizable(true);
//		columnBasePrice.setLabelProvider(new ObservableMapCellLabelProvider(attributeBasePrice));
//
//		IObservableMap attributePricePerKWH = EMFEditProperties.value(editingDomain,
//				CombustiblePackage.Literals.COMBUSTIBLE__PRICE_PER_KWH
//				).observeDetail(knownElements);
//		TableViewerColumn columnPricePerKWH = new TableViewerColumn(masterTableViewer, SWT.RIGHT);
//		columnPricePerKWH.getColumn().setText("Price per kWh [Cent/kWh]");
//		columnPricePerKWH.getColumn().setWidth(100);
//		columnPricePerKWH.getColumn().setMoveable(false);
//		columnPricePerKWH.getColumn().setResizable(true);
//		columnPricePerKWH.setLabelProvider(new ObservableMapCellLabelProvider(attributePricePerKWH));
//
//		IEMFEditListProperty combustiblesProperty = EMFEditProperties.list(editingDomain, CombustiblePackage.Literals.COMBUSTIBLE_CONTAINER__COMBUSTIBLES);
//		IObservableList observableCombustibles = combustiblesProperty.observe(combustibleContainer);
//		masterTableViewer.setInput(observableCombustibles);
//		
//		int[] indices = select();
//		masterTableViewer.getTable().select(indices);
//	}
//	
//	public List<Combustible> getSelectedCombustibles() {
//		return selectedCombustibles;
//	}
//	
//	private void save() {
//		Job job = new Job("Save Ressource"){
//			@Override
//			protected IStatus run(IProgressMonitor monitor) {
//				try {
//					EnergyKeyPerformanceIndicatorController.getInstance().saveCombustibles(file, combustibleContainer);
//					return Status.OK_STATUS;
//				} catch (IOException e1) {
//					return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Save error", e1);
//				}
//			}
//		};
//		job.setSystem(true);
//		job.schedule();
//	}
//	
//	public void setSelectedCombustibles(List<String> combustibleIds) {
//		List<Combustible> combustibles = EnergyKeyPerformanceIndicatorController.getInstance().listCombustibles();
//		for(Combustible c : combustibles) {
//			if(combustibleIds.contains(c.getId())) {
//				selectedCombustibles.add(c);
//			}
//		}
//	}
//	
//	private int[] select() {
//		List<Integer> tableItemsIndex = new ArrayList<Integer>();
//		Table table = masterTableViewer.getTable();
//		TableItem[] items = table.getItems();
//		for(int i = 0; i < items.length; i++) {
//			TableItem item = items[i];
//			Combustible c = (Combustible) item.getData();
//			String cId = c.getId();
//			for(Combustible selC : selectedCombustibles) {
//				if(selC.getId().equals(cId)) tableItemsIndex.add(i);
//			}
//		}
//		int[] indices = new int[tableItemsIndex.size()];
//		for(int j = 0; j < tableItemsIndex.size(); j++) {
//			indices[j] = tableItemsIndex.get(j);
//		}
//		return indices;
//	}
//
//}
