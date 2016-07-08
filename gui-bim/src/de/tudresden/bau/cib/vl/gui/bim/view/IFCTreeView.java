package de.tudresden.bau.cib.vl.gui.bim.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.gui.bim.Activator;
import de.tudresden.bau.cib.vl.gui.bim.controller.IFCController;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCModelContentProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeNode;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeNodeDragListener;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeNodeDropListener;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeViewer;
import de.tudresden.bau.cib.vl.gui.common.Constants;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNode;



public class IFCTreeView extends FileView<IFCController> {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = Constants.PLACEHOLDER_PREFIX+".bim."+Constants.PLACEHOLDER_WINDOW_BOTTOM_LEFT+".IFCTreeView";
	
	private IFCTreeViewer treeViewer;	
	
	private Action selectElementAction;
	private Action showElementInViewerAction;
	private Action hideElementInViewerAction;	
	
	
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

//		show tree viewer
		createTreeViewerForSpaces(parent);	
		
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
				IFCTreeView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, treeViewer);
	}



	

	private void fillContextMenu(IMenuManager manager) {		
		manager.add(selectElementAction);
		manager.add(new Separator());
		manager.add(showElementInViewerAction);
		manager.add(hideElementInViewerAction);
		//drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	@Override
    public void update()
    {
		treeViewer.refresh();
    }
	
 
	
	@Override
	public void setModel(Model model)
	{
		setTreeViewerInput((Ifc2x3DataModelProxy) model);
	}

	private void makeActions() {
		selectElementAction = new Action() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1386026895416944091L;

			public void run() {
				if((controller.getSelectedNodes() != null) && 
						!(controller.getSelectedNodes().isEmpty())) {
					try{						
						actionElementsSelected(controller.getSelectedNodes());					
						
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
						!(controller.getSelectedNodes().isEmpty())) {
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
				if((controller.getSelectedNodes() != null) && 
						!(controller.getSelectedNodes().isEmpty())) {
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
		
		
	}
	
	private void showMessage(String message) {
		MessageDialog.openInformation(
				treeViewer.getControl().getShell(),
			"Semantic BIM View",
			message);
	}
	
	private void createTreeViewerForSpaces(Composite c) {	

	    
		IFCLabelProvider labelProvider = new IFCLabelProvider()
		{			
			

			/**
			 * 
			 */
			private static final long serialVersionUID = -8649069842210603773L;

			@Override
			public Color getBackground(Object element)
			{		
				
				if(controller.getSelectedNodes().contains((TreeNode) element))			
				{				
					return display.getSystemColor(SWT.COLOR_GREEN); 
				}
				
				return null;	
				

			}
			
		};
		
		
	    
		treeViewer = new IFCTreeViewer(c, SWT.MULTI | SWT.NONE, getViewSite(),
										null,
										labelProvider,
										new IFCModelContentProvider(ContentMode.SPATIALSTRUCTURE));
		
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
		
		treeViewer.addSelectionChangedListener(changeListener);
		
		Transfer[] transferTypes = new Transfer[] { TextTransfer.getInstance() };
		treeViewer.addDragSupport(DND.DROP_MOVE | DND.DROP_COPY, 
								    transferTypes,
								    new IFCTreeNodeDragListener(treeViewer));
		treeViewer.addDropSupport(DND.DROP_MOVE | DND.DROP_COPY, 
					    			transferTypes,
					    			new IFCTreeNodeDropListener(treeViewer, controller));
	    
		makeActions();
		hookContextMenu();
		
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
			
			treeViewer.refresh(node);
		}		
		
		controller.setSelectionIds(selection);		
	}	
	

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}
	
	public void setTreeViewerInput(final Ifc2x3DataModelProxy ifcModel) {
		if(ifcModel != null) {
			display.asyncExec(
					new Runnable() {
						public void run(){							
							try {								
								treeViewer.setInput(ifcModel);
							} catch (Exception e) {
								
							}
						}
					});
		}
	}


	
}
