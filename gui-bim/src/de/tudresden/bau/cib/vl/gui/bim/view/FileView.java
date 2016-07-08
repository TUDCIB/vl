package de.tudresden.bau.cib.vl.gui.bim.view;




import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.service.UserService;
import de.tudresden.bau.cib.vl.gui.bim.Activator;
import de.tudresden.bau.cib.vl.gui.bim.controller.FileController;
import de.tudresden.bau.cib.vl.gui.common.Constants;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
//import org.eclipse.ui.part.ViewPart;


public abstract class FileView<T extends FileController<?>> extends View<T> {
	
	
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = Constants.PLACEHOLDER_PREFIX+".bim."+Constants.PLACEHOLDER_WINDOW_BOTTOM_LEFT+".FileView";
	
	private Action loadFileAction;
	private Action refreshAction;
	
	protected Display display;
	protected IToolBarManager toolBarManager;
	protected T controller;
	protected FileService fileService;
	protected UserService userService;
	

	@Override
	public void setFocus() {

	}
	
	@Override
	public void showBusy(boolean busy) {
		super.showBusy(busy);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		this.display = parent.getDisplay(); 		
		toolBarManager = getViewSite().getActionBars().getToolBarManager();
		
		createActions();
		createToolBar();		
	}
	
	public void createActions()
	{
		loadFileAction = new Action() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -124853728260834826L;

			public void run() {
				Shell shell = new Shell(Display.getCurrent());
				shell.setLocation(0, 0);
				shell.setSize(640, 480);				
				FileDialog fileDialog = new FileDialog(shell, SWT.TITLE );
				fileDialog.setText( "Load File" );
//				fileDialog.setAutoUpload( true );
				fileDialog.open();

				String filePath = fileDialog.getFileName();
				controller.processFile(filePath);
				
			}
		};
		loadFileAction.setText("Load file");
		loadFileAction.setToolTipText("Load file");
		ImageDescriptor imageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"filenew.png");
		loadFileAction.setImageDescriptor(imageDescriptor);
		
		
		refreshAction = new Action(){

			
			/**
			 * 
			 */
			private static final long serialVersionUID = 2546867518761245042L;

			public void run() {				
				controller.loadFile();
			}
			
		};
		
		refreshAction.setText("Refresh file");
		refreshAction.setToolTipText("Refresh file");
		ImageDescriptor imageDescriptor1 = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"reload.png");
		refreshAction.setImageDescriptor(imageDescriptor1);
		
	}
	
	public void createToolBar()
	{
				
		toolBarManager.add(loadFileAction);
		toolBarManager.add(new Separator());
		toolBarManager.add(refreshAction);
		
	}

	public void update()
	{
		
	}	
	
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
