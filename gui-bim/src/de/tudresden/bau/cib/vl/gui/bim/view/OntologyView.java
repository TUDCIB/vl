package de.tudresden.bau.cib.vl.gui.bim.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchActionConstants;

import com.hp.hpl.jena.rdf.model.Statement;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.RuleSet;
import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
import de.tudresden.bau.cib.vl.gui.bim.Activator;
import de.tudresden.bau.cib.vl.gui.bim.controller.OntologyController;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.OntologyEeBimParameterDialog;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.OntologyRuleSelectionDialog;
import de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer.OntologyContentProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer.OntologyLabelProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer.OntologyTreeNode;
import de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer.OntologyTreeNodeDragListener;
import de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer.OntologyTreeNodeDropListener;
import de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer.OntologyTreeViewer;
import de.tudresden.bau.cib.vl.gui.common.Constants;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;




public class OntologyView extends FileView<OntologyController> implements ISaveablePart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = Constants.PLACEHOLDER_PREFIX+".bim."+Constants.PLACEHOLDER_WINDOW_BOTTOM_LEFT+".OntologyTreeView";
	
	private Action removeIndividualAction;
	private Action saveOntologyToFileAction;
	private Action applyRulesAction;
	private Action eeBimParameterAction;
	private Action validateAction;
	
	
	private OntologyTreeViewer treeViewer1;
	private OntologyTreeViewer treeViewer2;
	private OntologyTreeViewer treeViewer3;	
	private OntologyTreeViewer treeViewer4;	
	private OntologyTreeViewer treeViewer5;	
	
	private TabFolder tabFolder;
	
	private OntologyService ontologyService;
	
	
	@Override
	protected OntologyController createController() {
		controller = new OntologyController();
		controller.setFileService(fileService);
		controller.setBimFitService(bimfitService);
		controller.setOntologyService(ontologyService);
		controller.setUserService(userService);
		return controller;
	}
	

	@Override
	public void createPartControl(Composite parent) {	
		super.createPartControl(parent);		

		tabFolder = new TabFolder(parent, SWT.BORDER);		
								
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NULL);
		tabItem1.setText("IFC");
		
		TabItem tabItem2 = new TabItem(tabFolder, SWT.NULL);
		tabItem2.setText("Construction");		
		
		TabItem tabItem3 = new TabItem(tabFolder, SWT.NULL);
		tabItem3.setText("Occupancy");
		
		TabItem tabItem4 = new TabItem(tabFolder, SWT.NULL);
		tabItem4.setText("Climate");
		
		TabItem tabItem5 = new TabItem(tabFolder, SWT.NULL);
		tabItem5.setText("Elements with no assignments");
		
		OntologyLabelProvider labelProvider = new OntologyLabelProvider();
		
		treeViewer1 = new OntologyTreeViewer(tabFolder, SWT.MULTI | SWT.NONE, getViewSite(),
												null,
												labelProvider,
												new OntologyContentProvider(ContentMode.IFC));
		
		treeViewer1.setAutoExpandLevel(5);
		
		treeViewer2 = new OntologyTreeViewer(tabFolder, SWT.MULTI | SWT.NONE, getViewSite(),
										null,
										labelProvider,
										new OntologyContentProvider(ContentMode.CONSTRUCTION));
		
		treeViewer2.setAutoExpandLevel(5);
		
		treeViewer3 = new OntologyTreeViewer(tabFolder, SWT.MULTI | SWT.NONE, getViewSite(),
										null,
										labelProvider,
										new OntologyContentProvider(ContentMode.OCCUPANCY));
		
		treeViewer3.setAutoExpandLevel(3);
		
		treeViewer4 = new OntologyTreeViewer(tabFolder, SWT.MULTI | SWT.NONE, getViewSite(),
										null,
										labelProvider,
										new OntologyContentProvider(ContentMode.CLIMATE));
		treeViewer4.setAutoExpandLevel(2);
		
		treeViewer5 = new OntologyTreeViewer(tabFolder, SWT.MULTI | SWT.NONE, getViewSite(),
				null,
				labelProvider,
				new OntologyContentProvider(ContentMode.NOT_ASSIGNED));
		treeViewer5.setAutoExpandLevel(2);
		
		
		ISelectionChangedListener changeListener = new ISelectionChangedListener()
		{

			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) 
				{
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
					
					ArrayList<OntologyTreeNode> helpList =new ArrayList<OntologyTreeNode>();
					for(Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext(); )
					{			
						
							OntologyTreeNode help = (OntologyTreeNode) iterator.next();							
							helpList.add(help);
							
				    }		
					
					
					controller.setSelectedNodes(helpList);
					
				

				}
				
			}
		};
		

		
		treeViewer1.addSelectionChangedListener(changeListener);
		treeViewer2.addSelectionChangedListener(changeListener);
		treeViewer3.addSelectionChangedListener(changeListener);		
		treeViewer4.addSelectionChangedListener(changeListener);
		treeViewer5.addSelectionChangedListener(changeListener);
		
		tabItem1.setControl(treeViewer1.getControl());
		tabItem2.setControl(treeViewer2.getControl());
		tabItem3.setControl(treeViewer3.getControl());		
		tabItem4.setControl(treeViewer4.getControl());	
		tabItem5.setControl(treeViewer5.getControl());
		
		//makeActions();
		hookContextMenu();
		
		initializeDND();
	}	
	
	private void initializeDND() {
//		DND.DROP_MOVE | DND.DROP_COPY
		int operations = DND.DROP_MOVE | DND.DROP_COPY;
		Transfer[] transferTypes = new Transfer[] { TextTransfer.getInstance() };
		treeViewer1.addDragSupport(operations, 
								    transferTypes,
								    new OntologyTreeNodeDragListener(treeViewer1));
		treeViewer1.addDropSupport(operations, 
					    			transferTypes,
					    			new OntologyTreeNodeDropListener(treeViewer1, controller));
		treeViewer2.addDragSupport(operations, 
									transferTypes,
									new OntologyTreeNodeDragListener(treeViewer2));
		treeViewer2.addDropSupport(operations, 
					    			transferTypes,
					    			new OntologyTreeNodeDropListener(treeViewer2, controller));
		treeViewer3.addDragSupport(operations, 
									transferTypes,
									new OntologyTreeNodeDragListener(treeViewer3));
		treeViewer3.addDropSupport(operations, 
					    			transferTypes,
					    			new OntologyTreeNodeDropListener(treeViewer3, controller));		
		treeViewer4.addDragSupport(operations, 
									transferTypes,
									new OntologyTreeNodeDragListener(treeViewer4));
		treeViewer4.addDropSupport(operations, 
				    			transferTypes,
				    			new OntologyTreeNodeDropListener(treeViewer4, controller));	
		
		treeViewer5.addDragSupport(operations, 
				transferTypes,
				new OntologyTreeNodeDragListener(treeViewer5));
		treeViewer5.addDropSupport(operations, 
			transferTypes,
			new OntologyTreeNodeDropListener(treeViewer5, controller));	
	}
	
	@Override
    public void update()
    {		
							
		treeViewer1.refresh();
		treeViewer2.refresh();
		treeViewer3.refresh();
		treeViewer4.refresh();
		treeViewer5.refresh();
    }
	
	public void setModel(final Model model)
	{
		if(!display.isDisposed()) {
			display.syncExec(
				new Runnable() {
					public void run(){							
						try {	
							if(!treeViewer1.getTree().isDisposed()) treeViewer1.setInput(model);
							if(!treeViewer2.getTree().isDisposed()) treeViewer2.setInput(model);
							if(!treeViewer3.getTree().isDisposed()) treeViewer3.setInput(model);
							if(!treeViewer4.getTree().isDisposed()) treeViewer4.setInput(model);
							if(!treeViewer5.getTree().isDisposed()) treeViewer5.setInput(model);
							
							applyRulesAction.setEnabled(true);
							removeIndividualAction.setEnabled(true);
							saveOntologyToFileAction.setEnabled(true);	
							eeBimParameterAction.setEnabled(true);
							validateAction.setEnabled(true);
						} catch (Exception e) {
							showMessage(e.getMessage());
							e.printStackTrace();
						}
						firePropertyChange(PROP_DIRTY);
					}
				});
		}		
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {

			public void menuAboutToShow(IMenuManager manager) {
				
				
				List<OntologyTreeNode> list = controller.getSelectedNodes();	
				
				
				 
				 if(!list.isEmpty())
				 {
					 if(list.size() == 1)
					 {
						 if(list.get(0).getTriple() != null)
						 {
								manager.add(removeIndividualAction);
						 }
					 }
					 else if (list.size() > 1)
					 {
						 boolean help = true;
						 for(OntologyTreeNode node : list)
						 {
							 if(node.getTriple() == null)
							 {
								 help = false;
								 break;
							 }
						 }
						 
						 if(help)
							 manager.add(removeIndividualAction);
					 }
				 }			
				
				
				manager.add(new Separator());			
				manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
				
				
				//OntologyView.this.fillContextMenu(manager);
			}
		});
		
		treeViewer1.getControl().setMenu(menuMgr.createContextMenu(treeViewer1.getControl()));
		getSite().registerContextMenu(menuMgr, treeViewer1);
		treeViewer2.getControl().setMenu(menuMgr.createContextMenu(treeViewer2.getControl()));
		getSite().registerContextMenu(menuMgr, treeViewer2);
		treeViewer3.getControl().setMenu(menuMgr.createContextMenu(treeViewer3.getControl()));
		getSite().registerContextMenu(menuMgr, treeViewer3);
		treeViewer4.getControl().setMenu(menuMgr.createContextMenu(treeViewer4.getControl()));
		getSite().registerContextMenu(menuMgr, treeViewer4);
		treeViewer5.getControl().setMenu(menuMgr.createContextMenu(treeViewer5.getControl()));
		getSite().registerContextMenu(menuMgr, treeViewer5);
	}

	

	private void fillContextMenu(IMenuManager manager) {		
		manager.add(removeIndividualAction);
		manager.add(new Separator());

		//drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}	
	
	@Override
	public void createActions() {
		
		super.createActions();
		
		removeIndividualAction = new Action() {

			public void run() {
				
				actionDeleteLink();
				
				
			}


		};
		removeIndividualAction.setText("Remove resource");
		removeIndividualAction.setToolTipText("Remove resource");
		ImageDescriptor imageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"cancel.png");
		removeIndividualAction.setImageDescriptor(imageDescriptor);
		
		final OntologyView view = this;
		eeBimParameterAction = new Action() {

			public void run() {		
				Collection<Statement> eeBimStatements = controller.listEeBimStatements();
				OntologyEeBimParameterDialog dialog = new OntologyEeBimParameterDialog(
						Display.getDefault().getActiveShell(), eeBimStatements, 
						(OntologyModel)controller.getModel(), controller);
				if(dialog.open() == Window.OK) {
					// it's ok => fire dirty state
					view.firePropertyChange(PROP_DIRTY);
				}
			}

		};
		eeBimParameterAction.setText("Set eeBim parameters");
		eeBimParameterAction.setToolTipText("Set eeBim parameters");
		ImageDescriptor imageDescriptorParameters = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"osa_contract.png");
		eeBimParameterAction.setImageDescriptor(imageDescriptorParameters);
		
		applyRulesAction = new Action() {

			public void run() {				
				Collection<RuleSet> rules = controller.listRuleSets();
					
				OntologyRuleSelectionDialog dialog = new OntologyRuleSelectionDialog(Display.getDefault().getActiveShell(), rules);
				if(dialog.open() == Window.OK) {
					Collection<RuleSet> rulesSetsToApply = dialog.getCheckedRuleSets();
					controller.applyRuleSets(rulesSetsToApply);
					// it's ok => fire dirty state
					view.firePropertyChange(PROP_DIRTY);
				}
			}

		};
		applyRulesAction.setText("Apply rules");
		applyRulesAction.setToolTipText("Apply rules");
		ImageDescriptor imageDescriptorRules = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"osa_arrow_green_left.png");
		applyRulesAction.setImageDescriptor(imageDescriptorRules);
		
		saveOntologyToFileAction = new Action() {

			public void run() {
				BusyIndicator.showWhile(Display.getCurrent(), new Runnable(){

					@Override
					public void run() {
						actionSaveOntology();
					}
					
				});				
			}			

		};
		saveOntologyToFileAction.setText("Save ontology");
		saveOntologyToFileAction.setToolTipText("Save ontology");
		ImageDescriptor imageDescriptorSave = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"filesave.png");
		saveOntologyToFileAction.setImageDescriptor(imageDescriptorSave);
		
		validateAction = new Action(){
			
			@Override
			public void run() {
				BusyIndicator.showWhile(Display.getCurrent(), new Runnable(){

					@Override
					public void run() {
						actionValidate();
					}
				});
			}
			
		};
		validateAction.setText("Validate model");
		validateAction.setToolTipText("Validate model");
//		ImageDescriptor imageDescriptorValidate = AppearanceFactory.getInstance().getImageDescriptor(
//				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"filesave.png");
//		validateAction.setImageDescriptor(imageDescriptorValidate);
		
		// all actions are disabled until an ontology model is selected
		eeBimParameterAction.setEnabled(false);
		applyRulesAction.setEnabled(false);
		removeIndividualAction.setEnabled(false);
		saveOntologyToFileAction.setEnabled(false);	
		validateAction.setEnabled(false);
	}
	
	private void showMessage(String message) {
		MessageDialog.openInformation(
				display.getFocusControl().getShell(),
			"Semantic BIM View",
			message);
	}
	
	private void actionDeleteLink()
	{						
			controller.publishRemovingOfLink();			
			firePropertyChange(PROP_DIRTY);
	}
	
	private void actionSaveOntology() {
		
		controller.saveToFile();
		firePropertyChange(PROP_DIRTY);
		
	}
	
	private void actionValidate() {
		controller.validateInferenceModel();
	}
	
	@Override
	public void createToolBar()
	{
		super.createToolBar();				
		
		toolBarManager.add(new Separator());
		toolBarManager.add(eeBimParameterAction);
		toolBarManager.add(applyRulesAction);
		toolBarManager.add(validateAction);
		toolBarManager.add(saveOntologyToFileAction);		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		controller.saveToFile();
		
		firePropertyChange(PROP_DIRTY);
		
	}

	@Override
	public void doSaveAs() {
		
	}

	@Override
	public boolean isDirty() {
		
		
		return controller.isDirty();
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		
		return controller.isDirty();
	}


	public void setOntologyService(OntologyService ontologyService) {
		this.ontologyService = ontologyService;
	}	
		
}
