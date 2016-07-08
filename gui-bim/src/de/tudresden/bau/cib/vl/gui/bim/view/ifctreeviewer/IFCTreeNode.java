package de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer;


import java.util.ArrayList;

import jsdai.SIfc2x3.EIfcroot;
import jsdai.lang.SdaiException;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNode;

public class IFCTreeNode extends TreeNode {
	
	private EIfcroot ifcElement;
	
	public IFCTreeNode(String name) {
		super(name);
	}
	
	public IFCTreeNode(String name, String id) {
		super(name,id);
	}
	
	@Override
	public boolean equals(Object o)
	{
		
		if(o instanceof IFCTreeNode)
		{
			try {
				if(this.ifcElement != null && ((IFCTreeNode)o).getIfcElement() != null &&
						this.ifcElement.getGlobalid(this.ifcElement) == ((IFCTreeNode)o).getIfcElement().getGlobalid(((IFCTreeNode)o).getIfcElement()))
				{
					return true;
				}
			} catch (SdaiException e) {}
			
		}
		
		
		return super.equals(o);
	}
	
	@Override
	public int hashCode() {
		int prime = 12;
		int hash = super.hashCode();
		if(ifcElement != null) {			
			try {
				String guid = ifcElement.getGlobalid(ifcElement);
				hash = hash + (prime * guid.hashCode());
			} catch (SdaiException e) {}		
		}
		return hash;
	}
	
	public ArrayList<String> getIFCIds(boolean recursive) throws SdaiException  
	{
		ArrayList<String> ids = new ArrayList<String>();
		if(ifcElement != null)
			ids.add(ifcElement.getGlobalid(ifcElement));
		
		if(recursive && hasChildren())		
			ids.addAll( getChildIfcIDs(getChildrenList()));
		
		return ids;
		
	}
	
	public String getIFCId() throws SdaiException  
	{		
		if(ifcElement != null)
			return ifcElement.getGlobalid(ifcElement);
		else
			return "";
		

		
	}
	
	private ArrayList<String> getChildIfcIDs(ArrayList<TreeNode> children) throws SdaiException
	{
		
		ArrayList<String> retList = new ArrayList<String>();
		
		for(TreeNode node : children)
		{
			
			
			EIfcroot elem = ((IFCTreeNode)node).getIfcElement();
			if(elem != null)
				retList.add(elem.getGlobalid(elem));
			
			
			if(node.hasChildren())
			{					
				retList.addAll(getChildIfcIDs(node.getChildrenList()));					
			}
					
			
		}
		
		return retList;
		
	}


	public EIfcroot getIfcElement() {
		return ifcElement;
	}

	public void setIfcElement(EIfcroot ifcElement) {
		this.ifcElement = ifcElement;
	}	
	
	

}
