//package de.tudresden.bau.cib.vl.gui.bim.view;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
//
//import cib.navigator.widget.nDNavigator;
//import cib.navigator.widget.nDNavigatorBrowserWidget;
//import de.tudresden.bau.cib.vl.gui.bim.controller.nDNavigatorViewController;
//import de.tudresden.bau.cib.vl.gui.common.Constants;
//import de.tudresden.bau.cib.vl.gui.common.view.AbstractView;
//
//
//
//public class nDNavigatorViewPart extends AbstractView<nDNavigatorViewController> {
//	
//
//	/**
//	 * The ID of the view as specified by the extension.
//	 */
//	public static final String ID = Constants.PLACEHOLDER_PREFIX+".bim."+Constants.PLACEHOLDER_WINDOW_MAIN+".nDNavigatorViewPart";
//	
//	private nDNavigator viewer;
//	
//	private String filePath;
//	//private ArrayList<String> selectedElements = new ArrayList<String>();
//
//	private Display display;
//	private nDNavigatorBrowserWidget widget;
//	
//	private List<String> selectedElements = new ArrayList<String>();
//
//
//	@Override
//	public void createPartControl(Composite parent) {
//		this.display = parent.getDisplay();	
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
//	}	
//	
//	public void setSelection(final List<String> selectedGUIDs)
//	{			
//		//widget.showElements( selectedGUIDs, selectedElements);
//		selectedElements =  selectedGUIDs;	
//	}	
//	
//	public void showElements( String [] guids, boolean show){		
//		widget.showOrHideElements(guids, show);		
//	}
//
//}
