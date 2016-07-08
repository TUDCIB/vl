package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeNode;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IfcContentProvider;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNode;


/**
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class IfcTreeSelectionDialog extends CheckedTreeSelectionDialog {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 118774570071718620L;
	protected FormToolkit toolkit;
	protected Set<EIfcroot> checkedEntities;
	
	
	public IfcTreeSelectionDialog(Shell parent, IFCLabelProvider labelProvider, 
			IfcContentProvider contentProvider, Collection<EIfcroot> checkedEntities) {
		super(parent, labelProvider, contentProvider);
		this.checkedEntities = new HashSet<EIfcroot>();
		if(checkedEntities != null) this.checkedEntities.addAll(checkedEntities);
	}
	

	@Override
	public void create() {
		super.create();
		setMessage("Select IFC entities");
		setTitle("Selection dialog");
	}
	
	@Override
	protected Composite createDialogArea(Composite parent) {		
		// create tree
		Composite area = (Composite) super.createDialogArea(parent);
		
		toolkit = new FormToolkit(area.getDisplay());
		
		ScrolledForm  form = toolkit.createScrolledForm(area);
		Composite container = form.getBody();
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		container.setLayout(new GridLayout(1, false));
		
		
		getTreeViewer().addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				// When user checks a checkbox in the tree, check all its children
				if(event.getChecked()) {
					getTreeViewer().setSubtreeChecked(event.getElement(), true);
				} else {
					getTreeViewer().setSubtreeChecked(event.getElement(), false);
				}
				// adds it to the selection set
				collectCheckedIfcEntities();
			}
		});

		checkTreeNodes();
		
		getTreeViewer().setAutoExpandLevel(1);
		return area;
	}
	
	protected void checkTreeNodes() {
		CheckboxTreeViewer viewer = getTreeViewer();
		viewer.expandAll();
		
		if(checkedEntities != null) {				
			TreeItem[] items = viewer.getTree().getItems();
			List<TreeNode> checkedNodes = new ArrayList<TreeNode>();
			for(int i = 0; i < items.length; i++) {
				TreeItem item = items[i];
				Object data = item.getData();
				if(data instanceof IFCTreeNode) {
					List<TreeNode> moreCheckedNodes = checkItems((IFCTreeNode)data);
					checkedNodes.addAll(moreCheckedNodes);
				}
			}
			this.setInitialSelections(checkedNodes.toArray());
		}		
	}
	
	private void collectCheckedIfcEntities() {
		checkedEntities.clear();
		Object[] checkedElements = getTreeViewer().getCheckedElements();
		for(Object checkedElement : checkedElements) {
			if(checkedElement instanceof IFCTreeNode) {
				IFCTreeNode ifcNode = (IFCTreeNode)checkedElement;
				EIfcroot ifcRoot = ifcNode.getIfcElement();
				if(ifcRoot != null) {
					checkedEntities.add(ifcRoot);
				}
			}
		}
	}
	
	private List<TreeNode> checkItems(IFCTreeNode treeNode) {
		List<TreeNode> checkedNodes = new ArrayList<TreeNode>();	
		EIfcroot ifcRoot = treeNode.getIfcElement();
		if(ifcRoot != null) {
			// 1: check if the current item is in the set
			if(checkedEntities.contains(ifcRoot)) {
				// tree node was found => return
				checkedNodes.add(treeNode);
			}
		}
		// tree node was not found => look deeper
		TreeNode[] children = treeNode.getChildren();
		if(children != null) {
			for(TreeNode child : children) {
				if(child instanceof IFCTreeNode) {
					List<TreeNode> moreCheckedNodes = checkItems((IFCTreeNode)child);
					checkedNodes.addAll(moreCheckedNodes);
				}
			}
		}

		return checkedNodes;
	}


	public Set<EIfcroot> getCheckedEntities() {
		return checkedEntities;
	}
	
	  // save content of the Text fields because they get disposed
	  // as soon as the Dialog closes
	  protected void saveInput() {}

	  @Override
	  protected final void okPressed() {
	    saveInput();
	    super.okPressed();
	  }
}
