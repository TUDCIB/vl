package de.tudresden.bau.cib.vl.gui.simulation.energy.view;

import java.util.ArrayList;
import java.util.Iterator;

import jsdai.SIfc2x3.EIfcroot;
import jsdai.lang.SdaiException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchActionConstants;

import de.tudresden.bau.cib.simmatrix.TConstructionType;
import de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant;
import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.gui.bim.Activator;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeNode;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
import de.tudresden.bau.cib.vl.gui.common.view.AbstractView;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNode;
import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.VariationController;
import de.tudresden.bau.cib.vl.gui.simulation.energy.utility.VariationTreeNode;


public class VariationView extends AbstractView<VariationController> {

	private TSimulationMatrix model;
	private TreeViewer viewer;
	private Display display;
	private VariationController controller;
	private TemplateService templateService;
	
	private Action removeAction;
	
	
	@Override
	protected VariationController createController() {
		controller = new VariationController();
		controller.setTemplateService(templateService);
		return controller;
	}
	

	
	@Override
	public void createPartControl(Composite parent) {
		
		this.display = parent.getDisplay();
		
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);		
		
		
		viewer.setContentProvider(new VariationContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		//viewer.setSorter(new NameSorter());		
		viewer.setInput(getViewSite());
		
		
		ISelectionChangedListener changeListener = new ISelectionChangedListener()
		{

			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) 
				{
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
					
					ArrayList<VariationTreeNode> helpList =new ArrayList<VariationTreeNode>();
					for(Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext(); )
					{			
						
							VariationTreeNode help = (VariationTreeNode) iterator.next();							
							helpList.add(help);
							
				    }		
					
					
					controller.setSelectedNodes(helpList);
					
				

				}
				
			}
		};
		

		
		viewer.addSelectionChangedListener(changeListener);
		
		
		removeAction = new Action() {				
			private static final long serialVersionUID = -3482196306373832693L;

			public void run() {				
				actionRemoveVariation();				
			}
		};
		
		removeAction.setText("Remove combination");
		removeAction.setToolTipText("Remove combination");
		ImageDescriptor imageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"cancel.png");
		removeAction.setImageDescriptor(imageDescriptor);
		
		
		hookContextMenu();
		
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
				VariationView.this.fillContextMenu(manager);
			}
			
			
			
			
			
		});
		
		viewer.getControl().setMenu(menuMgr.createContextMenu(viewer.getControl()));
		getSite().registerContextMenu(menuMgr, viewer);
	
	}

	

	private void fillContextMenu(IMenuManager manager) {		
		manager.add(removeAction);
		manager.add(new Separator());

		//drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}	
	
	

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	public void setNumberOfVariations(int number)
	{
		String seperator = " - ";
		String varCount = "Total number of variations: " + String.valueOf(number);		
		
		String title = getPartName();
		
		String[] splits = title.split(seperator);
		
		if(splits.length == 1)
		{
			title = title + seperator + varCount;
		}
		else if (splits.length == 2)
		{
			title = splits[0] + seperator + varCount;
		}
	
		
		// calling setPartName without this cause an exception
		final String finalTitle = title;		
		if(!viewer.getTree().isDisposed()) {
			display.asyncExec(new Runnable() {
	
				@Override
				public void run() {
					setPartName(finalTitle);
				}			
			});	
		}
		
		
	}
	
	public void setModel(final TreeNode root)
	{
		//this.model = model;
		
		if(!viewer.getTree().isDisposed()) {
			display.asyncExec(new Runnable() {
	
				@Override
				public void run() {
					viewer.setInput(root);
				}			
			});	
		}
		
	}
	
	private class VariationContentProvider implements IStructuredContentProvider, ITreeContentProvider
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 6040565400191822499L;
		
		
		private TreeNode invisibleRoot;

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub
			
		}

		public Object getParent(Object child)
		{
			
			if(child instanceof TreeNode)
				return ((TreeNode)child).getParent();			
		
			return null;
		}
		
		public Object [] getChildren(Object parent)
		{

			if (parent instanceof TreeNode) {
				return ((TreeNode)parent).getChildren();
			}
			
			return new Object[0];
		}
		
		public boolean hasChildren(Object parent)
		{

			if (parent instanceof TreeNode)
				return ((TreeNode)parent).hasChildren();			
			return false;
		}

		@Override
		public Object[] getElements(Object inputElement) {
			
			if(inputElement instanceof TreeNode)
			{
				return getChildren(inputElement);
			}
			else
			{
				TreeNode node = new TreeNode("No Simulation Matrix loaded");
				return new Object[]{node};
			}
			
			
			
		}

		
		private  EObject getVariantById(TSimulationMatrix matrix, String id)
		{
			
			for ( TConstructionType constType :  matrix.getVariables().getConstructionTypeVariables().getConstructionType())
			{
				for ( TConstructionTypeVariant typeVariant : constType.getConstructionTypeVariant())
				{
					if(typeVariant.getId().compareTo(id)==0)
					{
						return typeVariant;
					}
				}
			}			
			
			return null;
		}
		
	}

	private class VariationLabelProvider extends LabelProvider
	{
		
		@Override
		public String getText(Object obj) {
			
			if(obj instanceof IFCTreeNode)
			{
				
				EIfcroot root = ((IFCTreeNode)obj).getIfcElement();
				try {
					return root.getName(root);
				} catch (SdaiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			return obj.toString();
		}
	}
	
	private void actionRemoveVariation()
	{
		controller.publishRemoveCombination();
	}



	public void setNumberOfCombination(int number) {
		String seperator = " - ";
		String varCount = "Total number of combinations: " + String.valueOf(number);		
		
		String title = getPartName();
		
		String[] splits = title.split(seperator);
		
		if(splits.length == 1)
		{
			title = title + seperator + varCount;
		}
		else if (splits.length == 2)
		{
			title = splits[0] + seperator + varCount;
		}
	
		
		// calling setPartName without this cause an exception
		final String finalTitle = title;		
		if(!viewer.getTree().isDisposed()) {
			display.asyncExec(new Runnable() {
	
				@Override
				public void run() {
					setPartName(finalTitle);
				}			
			});	
		}
		
	}
	
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	

}
