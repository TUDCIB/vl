package de.tudresden.bau.cib.vl.gui.core.view.dialog;
///**
// * 
// */
//package de.tudresden.bau.cib.application.view.dialog;
//
//import org.eclipse.rwt.widgets.Upload;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.custom.ScrolledComposite;
//import org.eclipse.swt.events.ModifyEvent;
//import org.eclipse.swt.events.ModifyListener;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Dialog;
//import org.eclipse.swt.widgets.Shell;
//
//import de.tudresden.bau.cib.application.management.LocalizationFactory;
//
///**
// * @author Ken
// * 
// * TODO Dialog bauen damit Nutzer sein Home Directory angeben kann wo die Daten gespeichert werden sollen. 
// * (Configuration.setHomeDirectory())
// *
// */
//public class ConfigurationSettingsDialog extends Dialog {
//	
//	private Composite composite;
//	private String homeDirectoryPath;
//
//	public ConfigurationSettingsDialog(Shell parent, int style) {
//		super(parent, style);
//// 		Create the ScrolledComposite to scroll horizontally and vertically
//	    ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
//// 		Create the composite to hold the controls
//	    composite = new Composite(sc, style);
//	    composite.setLayout(new FillLayout());
//	    
//	    createContents();
//	    
//	    sc.setContent(composite);
//
//	    // Set the minimum size
//	    sc.setMinSize(400, 400);
//
//	    // Expand both horizontally and vertically
//	    sc.setExpandHorizontal(true);
//	    sc.setExpandVertical(true);
//	}
//	
//	private void createContents() {
//		
//		createChooseHomeDirectory();
//	}
//	
//	private void createChooseHomeDirectory() {
//		final Upload fileChooser = new Upload(composite, SWT.NONE);
//		fileChooser.setBrowseButtonText(LocalizationFactory.getInstance().getText("BROWSE"));
//		fileChooser.setUploadButtonText(LocalizationFactory.getInstance().getText("OK"));
//		
//		fileChooser.addModifyListener(new ModifyListener() {
//
//            public void modifyText(ModifyEvent event){
//            	homeDirectoryPath = fileChooser.getPath();
//            }
//            
//        });
//
//	}
//
//}
