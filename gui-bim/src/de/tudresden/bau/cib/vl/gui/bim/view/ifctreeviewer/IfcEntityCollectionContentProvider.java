package de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer;

import java.util.Collection;

import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.lang.SdaiException;

public class IfcEntityCollectionContentProvider extends IfcContentProvider {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2047433711154831002L;

	private static final String UNKNOWN = "unknown";
	
	
	public IfcEntityCollectionContentProvider(ContentMode mode) {
		super(mode);
	}	

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object input) {
		invisibleRoot = new IFCTreeNode("");
		if(input instanceof Collection) {
			try {
				Collection<EIfcroot> ifcEntities = (Collection<EIfcroot>) input;
				for(EIfcroot elem : ifcEntities) {
					String elemName = UNKNOWN;
					if(elem.testName(elem)) {
						elemName = elem.getName(elem);									
					}														
						
					String [] className = elem.getClass().toString().split("\\.");
					String [] tmp = className[className.length -1].split("Ifc");
					String n = tmp[tmp.length-1];
					
					IFCTreeNode tl = (IFCTreeNode) invisibleRoot.getChild(n);
					if(tl != null) {												
						IFCTreeNode treeElement = new IFCTreeNode(elemName);								
						treeElement.setIfcElement(elem);
						tl.addChild(treeElement);
						
					} else {																
						IFCTreeNode node = new IFCTreeNode(n);
						IFCTreeNode treeElement = new IFCTreeNode(elemName);
						node.addChild(treeElement);
						invisibleRoot.addChild(node);
						treeElement.setIfcElement(elem);
					}
				}
			} catch (SdaiException e) {
				
			}
		} else {					
			IFCTreeNode root = new IFCTreeNode("No elements loaded");
			invisibleRoot.addChild(root);
		}	
		return getChildren(invisibleRoot);
	}

}
