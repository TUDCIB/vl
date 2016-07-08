package de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hp.hpl.jena.graph.Triple;

import de.tudresden.bau.cib.vl.gui.core.util.TreeNode;

public class OntologyTreeNode extends TreeNode {

	private String strURI = null;
	
	private Triple ontoTriple = null;
	
	private String strDisplayName = null;
	
	private Image image = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
	
	
	public String getStrURI() {
		return strURI;
	}

	public OntologyTreeNode(String name) {
		super(name);
		
		
	}
	
	@Override
	public TreeNode getChild(String childName)
	{
	
		
		for(TreeNode node : getChildren())
		{
			if(node instanceof OntologyTreeNode)
			{
			
				String uri = ((OntologyTreeNode) node).getStrURI();				
				
				if( uri != null && !uri.isEmpty())
				{
					if(uri.compareTo(childName) == 0) 
						return node;
				}				
			}				
		}
		
		return super.getChild(childName);	
		
		
	}
	
	public OntologyTreeNode(String name, String uri) {
		super(name);
		this.strURI = uri;
	}
	
	public OntologyTreeNode(String name, String uri, String displayName) {
		super(name);
		this.strURI = uri;
		this.strDisplayName = displayName;
	}


	public Triple getTriple() {
		return ontoTriple;
	}


	public void setTriple(Triple ontoTriple) {
		this.ontoTriple = ontoTriple;
	}
	
	@Override
	public String toString()
	{
		if(strDisplayName == null || strDisplayName.isEmpty())
		{
			return getName();
		}
		else
			return strDisplayName;
		
		
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}
	
	

	

	

}
