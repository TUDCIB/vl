package de.tudresden.bau.cib.vl.gui.core;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.tudresden.bau.cib.vl.gui.common.Constants;




/**
 * Configures the perspective layout. This class is contributed through the
 * plugin.xml.
 */
public class Perspective implements IPerspectiveFactory {
	
	String APPLICATION_ID = "Demonstrator";

	String LAYOUT_FOLDER_TOP = APPLICATION_ID+"_folder_top";
	String LAYOUT_FOLDER_MAIN = APPLICATION_ID+"_folder_main";
	String LAYOUT_FOLDER_LEFT = APPLICATION_ID+"_folder_left";
	String LAYOUT_FOLDER_RIGHT = APPLICATION_ID+"_folder_right";
	String LAYOUT_FOLDER_RIGHT_TOP = APPLICATION_ID+"_folder_right_top";
	String LAYOUT_FOLDER_RIGHT_CENTER = APPLICATION_ID+"_folder_right_center";
	String LAYOUT_FOLDER_RIGHT_BOTTOM = APPLICATION_ID+"_folder_right_bottom";
	String LAYOUT_FOLDER_BOTTOM = APPLICATION_ID+"_folder_bottom";
	String LAYOUT_FOLDER_BOTTOM_LEFT = APPLICATION_ID+"_folder_bottom_left";

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(false);
		
		// create folders (create the main folder first as a reference)
		IFolderLayout topFolder = layout.createFolder(LAYOUT_FOLDER_TOP, IPageLayout.LEFT, 0.95f, layout.getEditorArea());
		
		IFolderLayout applicationFolder = layout.createFolder(LAYOUT_FOLDER_MAIN, IPageLayout.BOTTOM, 0.10f, LAYOUT_FOLDER_TOP);
		IFolderLayout dataManagementFolder = layout.createFolder(LAYOUT_FOLDER_LEFT, IPageLayout.LEFT, 0.15f, LAYOUT_FOLDER_MAIN);
		
		IFolderLayout rightTopFolder = layout.createFolder(LAYOUT_FOLDER_RIGHT_TOP, IPageLayout.RIGHT, 0.8f, LAYOUT_FOLDER_MAIN);
		IFolderLayout rightBottomFolder = layout.createFolder(LAYOUT_FOLDER_RIGHT_BOTTOM, IPageLayout.BOTTOM, 0.7f, LAYOUT_FOLDER_RIGHT_TOP);
		
		IFolderLayout propertiesFolder = layout.createFolder(LAYOUT_FOLDER_BOTTOM, IPageLayout.BOTTOM, 0.7f, LAYOUT_FOLDER_MAIN);
		IFolderLayout dataRepresentationFolder = layout.createFolder(LAYOUT_FOLDER_BOTTOM_LEFT, IPageLayout.BOTTOM, 0.7f, LAYOUT_FOLDER_LEFT);

//		dataManagementFolder.addView(UserProjectView.ID);
		
		// top
		topFolder.addPlaceholder(Constants.PLACEHOLDER_TOP_FULL);
		
//		main
		applicationFolder.addPlaceholder(Constants.PLACEHOLDER_WINDOW_MAIN_FULL);
//		left
		dataManagementFolder.addPlaceholder(Constants.PLACEHOLDER_WINDOW_LEFT_FULL);
//		bottom
		propertiesFolder.addPlaceholder(Constants.PLACEHOLDER_WINDOW_BOTTOM_FULL);
//		bottom left
		dataRepresentationFolder.addPlaceholder(Constants.PLACEHOLDER_WINDOW_BOTTOM_LEFT_FULL);
//		right top
		rightTopFolder.addPlaceholder(Constants.PLACEHOLDER_WINDOW_TOP_FULL);
//		right bottom
		rightBottomFolder.addPlaceholder(Constants.PLACEHOLDER_WINDOW_RIGHT_BOTTOM_FULL);
//		rightBottomFolder.addView(LogView.ID);
		
		// top
//		topFolder.addView(ProcessView.ID);
		
	}
}
