package de.tudresden.bau.cib.vl.gui.core.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;




public class TreeNode implements IAdaptable {		
	
	

	
	
	
	private String name = "Undefined";
	private String id = "Undefined";

	private TreeNode parent;
	
	private ArrayList<TreeNode> children = new ArrayList<TreeNode>();
	
	@Override
	public Object getAdapter(Class arg0) {
		return null;
	}	
	
	/**
	 * @return Children and their grand children and so on...
	 */
	public List<TreeNode> getAllAncestors() {
		List<TreeNode> ancestors = new ArrayList<TreeNode>();
		for(TreeNode child : children) {
			ancestors.add(child);
			if(child.hasChildren()) {
				ancestors.addAll(child.getAllAncestors());
			}
		}
		return ancestors;
	}
	
	
	public TreeNode(String name) {			
		this.name = name;
	}
	
	public TreeNode(String name, String id) {			
		this.name = name;
		this.id = id;
	}
	


	
	public String getName() {
		return name;
	}
	
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	
	public TreeNode getParent() {
		return parent;
	}
	
	public String toString() {
		return getName();
	}
		
	
	public void addChild(TreeNode child) {
		children.add(child);
		child.setParent(this);
	}
	
	public void removeChild(TreeNode child) {
		children.remove(child);
		child.setParent(null);
	}
	
	public TreeNode [] getChildren() {	
		
		return children.toArray(new TreeNode[children.size()]);
	}
	
	public ArrayList<TreeNode> getChildrenList() {	
		
		return children;
	}
	
	public boolean hasChildren() {
		return children.size()>0;
	}		
	
	public int getChildCount()
	{
		return children.size();
		
	}
	
	public TreeNode getChild(String name)
	{
		for(TreeNode node : children)
		{
			if(node.getName().compareTo(name) == 0)
				return node;
		}
		
		return null;
	}
	
	
	public TreeNode getChildByName(String name)
	{
		return getChild(name);
	}
	
	public TreeNode getChildById(String id)
	{
		for(TreeNode node : children)
		{
			if(node.getId().compareTo(id) == 0)
				return node;
		}
		
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TreeNode) {
			TreeNode other = (TreeNode) obj;
			return 
					this.children.size()==other.children.size() 
					&& this.name.equals(other.name)
					&& ((this.parent != null && other.parent != null) ? this.parent.equals(other.parent) : true);
		}
		return false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
	
}
