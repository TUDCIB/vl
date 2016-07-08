//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.tree;
//
//import org.eclipse.jface.viewers.LabelProvider;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.ui.ISharedImages;
//import org.eclipse.ui.PlatformUI;
//
//public class EnergyTreeViewLabelProvider extends LabelProvider {
//
//	
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 8621137013789138095L;
//
//	public String getText(Object obj) {
//		return obj.toString();
//	}
//	
//	public Image getImage(Object obj) {
//		String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
//		if (obj instanceof TreeIfcBuildingStorey)
//			imageKey = ISharedImages.IMG_OBJ_FOLDER;
//		return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
//	}
//}
