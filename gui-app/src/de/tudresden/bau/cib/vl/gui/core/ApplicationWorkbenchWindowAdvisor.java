package de.tudresden.bau.cib.vl.gui.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import de.tudresden.bau.cib.vl.gui.common.extensionpoints.ILogin;
import de.tudresden.bau.cib.vl.gui.core.messages.Messages;


/**
 * Configures the initial size and appearance of a workbench window.
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
	
	
	private static final String EXTENSIONPOINT_ID = "de.tudresden.bau.cib.vl.gui.extensionpoints.definitions.login";
	

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {  
    	runLoginExtension();
    	
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
//        configurer.setInitialSize(new Point(1024, 768));
//        configurer.setInitialSize(new Point(1920, 1080));
        
        configurer.setShowCoolBar(false);
        configurer.setShowStatusLine(false);
        configurer.setTitle(Messages.APPLICATION_NAME);  
//      allow multiple perspectives
        configurer.setShowPerspectiveBar(false);
//        configurer.setShowFastViewBars(true);
        configurer.setShowMenuBar(true);
        
//      full size
        configurer.setShellStyle(SWT.NO_TRIM);
        configurer.setShowProgressIndicator(true);
    }

	@Override
    public void postWindowOpen() {
		Shell shell = getWindowConfigurer().getWindow().getShell();
		shell.setMaximized(true);
    }
    
	private void runLoginExtension() {
		// search for the login extension point, if non is available use the default user
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSIONPOINT_ID);
		try {
			for (IConfigurationElement e : config) {
				final Object o = e.createExecutableExtension("class");
				if (o instanceof ILogin) {
					ISafeRunnable runnable = new ISafeRunnable() {
						@Override
						public void handleException(Throwable exception) {
							exception.printStackTrace();
						}

						@Override
						public void run() throws Exception {
							((ILogin) o).login();
						}
					};
					SafeRunner.run(runnable);
				}
			}
		} catch (CoreException ex) {
			ex.printStackTrace();
		}
	}
}
