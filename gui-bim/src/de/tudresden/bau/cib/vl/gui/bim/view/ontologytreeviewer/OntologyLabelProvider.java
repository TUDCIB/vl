package de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;


public class OntologyLabelProvider extends LabelProvider implements IColorProvider {


	@Override
	public Color getForeground(Object element) {
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		return null;
	}
	
	public Image getImage(Object obj) {
	
		if(obj instanceof OntologyTreeNode)
		{
			return ((OntologyTreeNode) obj).getImage();
		}

		return null;
	}

}
