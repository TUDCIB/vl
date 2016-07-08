package de.tudresden.bau.cib.vl.gui.core.handler;
//package de.tudresden.bau.cib.vl.gui.rap.handler;
//
//import org.eclipse.core.commands.AbstractHandler;
//import org.eclipse.core.commands.ExecutionEvent;
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.rwt.widgets.Upload;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.ui.IWorkbenchWindow;
//import org.eclipse.ui.handlers.HandlerUtil;
//
//
///**
// * Our sample handler extends AbstractHandler, an IHandler base class.
// * @see org.eclipse.core.commands.IHandler
// * @see org.eclipse.core.commands.AbstractHandler
// */
//public class OpenFileHandler extends AbstractHandler {
//	/**
//	 * The constructor.
//	 */
//	public OpenFileHandler() {
//		
//	}
//
//	/**
//	 * the command has been executed, so extract extract the needed information
//	 * from the application context.
//	 */
//	public Object execute(ExecutionEvent event) throws ExecutionException {
//		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
//		Shell shell = window.getShell();
//		Upload uploadWidget = new Upload(shell, SWT.NONE);
//		uploadWidget.setVisible(true);
////		boolean uploaded = uploadWidget.performUpload();
////		System.out.println("UPLOAD? = "+uploaded);
////		if(uploaded) {
////			UploadItem item = uploadWidget.getUploadItem();
////			DataManager.getInstance().setFile(new File(item.getFilePath()));
////		}
//		
//		// no file selected
////		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
////		FileDialog fileDialog = new FileDialog(window.getShell(),SWT.OPEN);
////		String selected = fileDialog.open();
//		
////		DataManager.getInstance().setFile(new File(selected));
//		return null;
//	}
//}
