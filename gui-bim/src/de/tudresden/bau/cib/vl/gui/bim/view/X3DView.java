//package de.tudresden.bau.cib.vl.gui.bim.view;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.Composite;
//
//import cib.navigator.widget.nDNavigator;
//import cib.navigator.widget.nDNavigatorBrowserWidget;
//import de.tudresden.bau.cib.vl.gui.bim.controller.X3DController;
//import de.tudresden.bau.cib.vl.gui.common.Constants;
//
//
//
//public class X3DView extends FileView<X3DController> {
//	
//
//	/**
//	 * The ID of the view as specified by the extension.
//	 */
//	public static final String ID = Constants.PLACEHOLDER_PREFIX+".bim."+Constants.PLACEHOLDER_WINDOW_MAIN+".X3DView";
//	
//	private nDNavigator viewer;
//	
//	
//	private nDNavigatorBrowserWidget widget;	
//	
//	private List<String> selectedElements = new ArrayList<String>();
//
//
//	@Override
//	public void createPartControl(Composite parent) {
//		super.createPartControl(parent);
//		
//		widget = new nDNavigatorBrowserWidget(parent, SWT.NONE);
//	}
//
//	@Override
//	public void setFocus() {		
////		viewer.setFocus();
//	}
//	
//	public void loadProjectIn3DViewer(String fileName) {
//		
//		String projectId="";
//		
//		//TODO workaround	
//		String [] s1 = fileName.split("\\.");
//		if(s1.length == 2)
//		{
//			String [] s2 = s1[0].split("_");
//			projectId = s2[s2.length-1];
//			
//		}
//		
//		widget.loadProject(projectId);
//		
//
//	}
//	
//	public void loadAndParseX3DFile()
//	{
//		
//		widget.loadAndParseX3DFile(controller.getFileInformation().getUrl());
//	}
//	
//	
//	public void setSelection(final List<String> selectedGUIDs)
//	{			
//		
//		selectedElements =  selectedGUIDs;	
//	}
//	
//	
//	public void showElements( String [] guids, boolean show){
//		
//		widget.showOrHideElements(guids, show);
//		
//	}
//
//
//}
