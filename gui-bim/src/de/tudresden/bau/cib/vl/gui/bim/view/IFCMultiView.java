package de.tudresden.bau.cib.vl.gui.bim.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.lang.SdaiException;

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
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IWorkbenchActionConstants;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.gui.bim.Activator;
import de.tudresden.bau.cib.vl.gui.bim.controller.IFCController;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCModelContentProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeNode;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeNodeDragListener;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeNodeDropListener;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeViewer;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCViewerSorter;
import de.tudresden.bau.cib.vl.gui.common.Constants;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;




public class IFCMultiView extends FileView<IFCController> {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = Constants.PLACEHOLDER_PREFIX+".bim."+Constants.PLACEHOLDER_WINDOW_BOTTOM_LEFT+".IFCTreeView";
	
	private Action selectElementAction;
	private Action showElementInViewerAction;
	private Action hideElementInViewerAction;
	private Action makeTransparentInViewerAction;
	private Action makeSolidInViewerAction;
	private Action showAllInViewerAction;
	
	private IFCTreeViewer treeViewer1;
	private IFCTreeViewer treeViewer2;
	private IFCTreeViewer treeViewer3;	
	private IFCTreeViewer treeViewer4;	
	
	private TabFolder tabFolder;
	
	
	@Override
	protected IFCController createController() {
		controller = new IFCController();
		controller.setFileService(fileService);
		controller.setBimFitService(bimfitService);
		controller.setUserService(userService);
		return controller;
	}
	

	@Override
	public void createPartControl(Composite parent) {	
		super.createPartControl(parent);		

		tabFolder = new TabFolder(parent, SWT.BORDER);		
								
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NULL);
		tabItem1.setText("Spatial structure");
		
		TabItem tabItem2 = new TabItem(tabFolder, SWT.NULL);
		tabItem2.setText("Rooms");		
		
		TabItem tabItem3 = new TabItem(tabFolder, SWT.NULL);
		tabItem3.setText("Walls");
		
		TabItem tabItem4 = new TabItem(tabFolder, SWT.NULL);
		tabItem4.setText("Facade");
		
		IFCLabelProvider labelProvider = new IFCLabelProvider(){			
			
			

			/**
			 * 
			 */
			private static final long serialVersionUID = 1417099786599072043L;

			@Override
			public Color getBackground(Object element)
			{		
//				IFCTreeNode ifcNode = (IFCTreeNode)element;				
//				
//				try {
//					if(controller.getSelectedNodes().contains((TreeNode) element) 
//							|| controller.getSelectedIFCIds().contains( ifcNode.getIFCId() )		)	
//					{				
//						return display.getSystemColor(SWT.COLOR_GREEN); 
//					}
//				} catch (SdaiException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		
				return null;	
				

			}
			
			@Override
			public Color getForeground(Object element) {		
				IFCTreeNode ifcNode = (IFCTreeNode)element;		
				EIfcroot entity = ifcNode.getIfcElement();
				if(entity instanceof EIfcspace) {
					Collection<EIfcroot> zoi = controller.getZonesOfInterest();
					if(zoi.contains(entity)){ 
						return display.getSystemColor(SWT.COLOR_RED); 
					}
				}
				return null;	
				

			}
		};
		
		treeViewer1 = new IFCTreeViewer(tabFolder, SWT.MULTI | SWT.NONE, getViewSite(),
										null,
										labelProvider,
										new IFCModelContentProvider(ContentMode.SPATIALSTRUCTURE));
		
		treeViewer1.setAutoExpandLevel(4);
		
		treeViewer2 = new IFCTreeViewer(tabFolder, SWT.MULTI | SWT.NONE, getViewSite(),
										new IFCViewerSorter(1),
										labelProvider,
										new IFCModelContentProvider(ContentMode.ROOMS));
		
		treeViewer2.setAutoExpandLevel(3);
		
		treeViewer3 = new IFCTreeViewer(tabFolder, SWT.MULTI | SWT.NONE, getViewSite(),
										new IFCViewerSorter(1),
										labelProvider,
										new IFCModelContentProvider(ContentMode.INTERNAL_EXTERNAL_WALLS));
		
		treeViewer3.setAutoExpandLevel(3);
		
		treeViewer4 = new IFCTreeViewer(tabFolder, SWT.MULTI | SWT.NONE, getViewSite(),
										new IFCViewerSorter(1),
										labelProvider,
										new IFCModelContentProvider(ContentMode.FACADE));

		treeViewer4.setAutoExpandLevel(2);
		
		
		ISelectionChangedListener changeListener = new ISelectionChangedListener()
		{

			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) 
				{
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
					
					ArrayList<IFCTreeNode> helpList =new ArrayList<IFCTreeNode>();
					for(Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext(); )
					{			
						
							IFCTreeNode help = (IFCTreeNode) iterator.next();							
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

		tabItem1.setControl(treeViewer1.getControl());
		tabItem2.setControl(treeViewer2.getControl());
		tabItem3.setControl(treeViewer3.getControl());		
		tabItem4.setControl(treeViewer4.getControl());	
		
		makeActions();
		hookContextMenu();
		
		initializeDND();
	}	
	
	private void initializeDND() {
//		DND.DROP_MOVE | DND.DROP_COPY
		int operations = DND.DROP_MOVE | DND.DROP_COPY;
		Transfer[] transferTypes = new Transfer[] { TextTransfer.getInstance() };
		treeViewer1.addDragSupport(operations, 
								    transferTypes,
								    new IFCTreeNodeDragListener(treeViewer1));
		treeViewer1.addDropSupport(operations, 
					    			transferTypes,
					    			new IFCTreeNodeDropListener(treeViewer1, controller));
		treeViewer2.addDragSupport(operations, 
									transferTypes,
									new IFCTreeNodeDragListener(treeViewer2));
		treeViewer2.addDropSupport(operations, 
					    			transferTypes,
					    			new IFCTreeNodeDropListener(treeViewer2, controller));
		treeViewer3.addDragSupport(operations, 
									transferTypes,
									new IFCTreeNodeDragListener(treeViewer3));
		treeViewer3.addDropSupport(operations, 
					    			transferTypes,
					    			new IFCTreeNodeDropListener(treeViewer3, controller));		
		treeViewer4.addDragSupport(operations, 
									transferTypes,
									new IFCTreeNodeDragListener(treeViewer4));
		treeViewer4.addDropSupport(operations, 
				    			transferTypes,
				    			new IFCTreeNodeDropListener(treeViewer4, controller));	
	}
	
	@Override
    public void update()
    {		
							
		treeViewer1.refresh();
		treeViewer2.refresh();
		treeViewer3.refresh();
		treeViewer4.refresh();
	

    }
	
	@Override
	public void setModel(final Model model)
	{
		if(!display.isDisposed()) {
			display.asyncExec(
				new Runnable() {
					public void run(){							
						try {	
							if(!treeViewer1.getTree().isDisposed()) treeViewer1.setInput(model);
							if(!treeViewer2.getTree().isDisposed()) treeViewer2.setInput(model);
							if(!treeViewer3.getTree().isDisposed()) treeViewer3.setInput(model);
							if(!treeViewer4.getTree().isDisposed()) treeViewer4.setInput(model);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		}
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9041507985844388528L;

			public void menuAboutToShow(IMenuManager manager) {
				IFCMultiView.this.fillContextMenu(manager);
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
	}

	

	private void fillContextMenu(IMenuManager manager) {		
		manager.add(selectElementAction);
		manager.add(new Separator());
		manager.add(showElementInViewerAction);
		manager.add(hideElementInViewerAction);
		manager.add(makeTransparentInViewerAction);
		manager.add(makeSolidInViewerAction);
		manager.add(showAllInViewerAction);
		//drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}	
	
	private void makeActions() {
		selectElementAction = new Action() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1386026895416944091L;

			public void run() {
				if(controller.getSelectedNodes() != null && 
						!(controller).getSelectedNodes().isEmpty() ) {
					try{						
						actionElementsSelected(controller.getSelectedNodes());
						
						update();
					} catch (SdaiException e) {
						//showMessage("Selection problem");
					}

				}
			}
		};
		selectElementAction.setText("Select element");
		selectElementAction.setToolTipText("Select element");
		ImageDescriptor imageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"viewmagfit.png");
		selectElementAction.setImageDescriptor(imageDescriptor);
		
		showElementInViewerAction = new Action() {
		
			private static final long serialVersionUID = 6694700307836740626L;	
		

			public void run() {
				if(controller.getSelectedNodes() != null && 
						!controller.getSelectedNodes().isEmpty()) {
					try{						
						actionElementShow(controller.getSelectedNodes());
					} catch (SdaiException e) {
						//showMessage("Show problem");
					}

				}
			}
		};
		showElementInViewerAction.setText("Show element");
		showElementInViewerAction.setToolTipText("Show element");
		ImageDescriptor imageDescriptor1 = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"eye-icon.png");
		showElementInViewerAction.setImageDescriptor(imageDescriptor1);
		
		hideElementInViewerAction = new Action() {


			private static final long serialVersionUID = 8557816478670536944L;

			public void run() {
				if(controller.getSelectedNodes() != null && 
						!controller.getSelectedNodes().isEmpty()) {
					try{						
						actionElementHide(controller.getSelectedNodes());
					} catch (SdaiException e) {
						showMessage("Hide problem");
					}

				}
			}
		};
		hideElementInViewerAction.setText("Hide element");
		hideElementInViewerAction.setToolTipText("Hide element");
		ImageDescriptor imageDescriptor2 = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"eye-canceled-icon.png");
		hideElementInViewerAction.setImageDescriptor(imageDescriptor2);
		
		makeTransparentInViewerAction = new Action() {


			/**
			 * 
			 */
			private static final long serialVersionUID = -1974501948256493647L;

			public void run() {
				if(controller.getSelectedNodes() != null && 
						!controller.getSelectedNodes().isEmpty()) {
					try{						
						actionMakeTransparent(controller.getSelectedNodes());
					} catch (SdaiException e) {
						showMessage("Transparent problem");
					}

				}
			}
		};
		makeTransparentInViewerAction.setText("Make this transparent");
		makeTransparentInViewerAction.setToolTipText("Make this transparent");
//		ImageDescriptor imageDescriptor4 = AppearanceFactory.getInstance().getImageDescriptor(
//				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"eye-canceled-icon.png");
//		makeTransparentInViewerAction.setImageDescriptor(imageDescriptor4);
		
		makeSolidInViewerAction = new Action() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1749829674852747547L;

			public void run() {
				if(controller.getSelectedNodes() != null && 
						!controller.getSelectedNodes().isEmpty()) {
					try{						
						actionMakeSolid(controller.getSelectedNodes());
					} catch (SdaiException e) {
						showMessage("Transparent problem");
					}

				}
			}
		};
		makeSolidInViewerAction.setText("Make this solid");
		makeSolidInViewerAction.setToolTipText("Make this solid");
//		ImageDescriptor imageDescriptor5 = AppearanceFactory.getInstance().getImageDescriptor(
//				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"eye-canceled-icon.png");
//		makeSolidInViewerAction.setImageDescriptor(imageDescriptor5);
		
		showAllInViewerAction = new Action() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 3457263028755172177L;

			public void run() {					
				actionShowAll();
			}
		};
		showAllInViewerAction.setText("Show all");
		showAllInViewerAction.setToolTipText("Show all");
//		ImageDescriptor imageDescriptor6 = AppearanceFactory.getInstance().getImageDescriptor(
//				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"eye-canceled-icon.png");
//		showAllInViewerAction.setImageDescriptor(imageDescriptor6);
		
		
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(
				display.getFocusControl().getShell(),
			"Semantic BIM View",
			message);
	}
	
	private void actionElementShow(List<IFCTreeNode> nodes) throws SdaiException {
		List<String> selection = new ArrayList<String>();
		
		for(IFCTreeNode node : nodes)
		{
			selection.addAll(node.getIFCIds(true));
		}
		
		
		
		controller.publishShow(selection);
	}
	
	private void actionElementHide(List<IFCTreeNode> nodes) throws SdaiException {
		Set<String> selection = new HashSet<String>();
		
		for(IFCTreeNode node : nodes)
		{
			selection.addAll(node.getIFCIds(true));
		}
		
		controller.publishHide(selection);
	}
	
	private void actionElementsSelected(List<IFCTreeNode> nodes) throws SdaiException {
		Set<String> selection = new HashSet<String>();
		
		for(IFCTreeNode node : nodes)
		{
			selection.addAll(node.getIFCIds(true));
			
			treeViewer1.refresh(node);
			treeViewer2.refresh(node);
			treeViewer3.refresh(node);
			treeViewer4.refresh(node);
			
		}		
		
		controller.setSelectionIds(selection);		
	}
	
	private void actionShowAll() {
		controller.publishShowAll();
	}
	
	private void actionMakeSolid(List<IFCTreeNode> selectedNodes) throws SdaiException {
		Set<String> selection = new HashSet<String>();
		
		for(IFCTreeNode node : selectedNodes)
		{
			selection.addAll(node.getIFCIds(true));
		}
		
		controller.publishMakeSolid(selection);
	}

	private void actionMakeTransparent(List<IFCTreeNode> selectedNodes) throws SdaiException {
		Set<String> selection = new HashSet<String>();
		
		for(IFCTreeNode node : selectedNodes)
		{
			selection.addAll(node.getIFCIds(true));
		}
		
		controller.publishMakeTransparent(selection);
	}
		
}
