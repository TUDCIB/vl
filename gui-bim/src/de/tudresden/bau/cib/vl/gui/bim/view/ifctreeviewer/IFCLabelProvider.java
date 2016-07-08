package de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer;

import java.io.File;

import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfccolumn;
import jsdai.SIfc2x3.EIfcdoor;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcsite;
import jsdai.SIfc2x3.EIfcslab;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcwallstandardcase;
import jsdai.SIfc2x3.EIfcwindow;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.tudresden.bau.cib.vl.gui.Activator;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;


public class IFCLabelProvider extends LabelProvider implements IColorProvider {


	private final Image IMG_SITE =  AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"site.png", 16, 16);
	
	private final Image IMG_BLDG =  AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"building.png", 16, 16);
	
	private final Image IMG_STOREY =  AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"storey1.png", 16, 16);
	
	private final Image IMG_SPACE = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"space.png", 16, 16);
	
	private final Image IMG_WALL = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"wall1.png", 16, 16);
	
	private final Image IMG_SLAB = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"slab.png", 16, 16);
	
	private final Image IMG_WDOW = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"window.png", 16, 16);
			
	private final Image IMG_DOOR = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"door.png", 16, 16);
			
	private final Image IMG_COL = AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
			Activator.ICONS_32x32_PATH+"BuildingElements"+File.separator+"column4.png", 16, 16);
	

	public String getText(Object obj) {
		return obj.toString();
	}
	
	public Image getImage(Object obj) {
		
		if (obj instanceof IFCTreeNode)
		{
			IFCTreeNode node = (IFCTreeNode) obj;
			EIfcroot ifc = node.getIfcElement();		
			
			if(ifc == null)
			{
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
				
			}
			else
			{

				
				if(ifc instanceof EIfcsite)
				{
					return IMG_SITE;
				}
				if(ifc instanceof EIfcbuilding)
				{
					return IMG_BLDG;
				}
				if(ifc instanceof EIfcbuildingstorey)
				{
					return IMG_STOREY;
				}
				if(ifc instanceof EIfcspace)
				{
					return IMG_SPACE;
				}
				if(ifc instanceof EIfcwallstandardcase)
				{
					return IMG_WALL;
				}	
				if(ifc instanceof EIfcslab)
				{
					return IMG_SLAB;
				}	
				if(ifc instanceof EIfcwindow)
				{
					return IMG_WDOW;
				}
				if(ifc instanceof  EIfcdoor)
				{
					return IMG_DOOR;
				}
				if(ifc instanceof  EIfccolumn)
				{
					return IMG_COL;
				}
				
			}
			
		}
		
		return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		

	}

	@Override
	public Color getForeground(Object element) {
		return null;
	}

	@Override
	public Color getBackground(Object element)
	{		
		return null;	
		

	}
	
	
	
}
