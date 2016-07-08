package de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog;
//package de.tudresden.bau.cib.vl.gui.simulation.energy.ui.dialog;
//
//import java.lang.reflect.InvocationTargetException;
//
//import org.apache.log4j.Logger;
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.jface.dialogs.ProgressMonitorDialog;
//import org.eclipse.jface.operation.IRunnableWithProgress;
//import org.eclipse.swt.widgets.Shell;
//
//
//
//public class StatusDialog extends ProgressMonitorDialog {
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -3043349269043872926L;
//	private de.tudresden.bau.cib.vl.core.simulation.energy.process.Process energyProcess;
//	
//	private static final Logger LOGGER = Logger.getLogger(StatusDialog.class);
//
//
//	public StatusDialog(Shell parent, de.tudresden.bau.cib.vl.core.simulation.energy.process.Process energyProcess) {
//		super(parent);
//		this.energyProcess = energyProcess;
//	}
//
//	public void show() throws ExecutionException {
//		try {
//			run(true, true, new IRunnableWithProgress() {
//				@Override
//				public void run(IProgressMonitor monitor) {
//					int totalWork = de.tudresden.bau.cib.vl.core.simulation.energy.process.Process.PROCESS_STATUS.values().length;
//					monitor.beginTask(energyProcess.getStatusMessage(), totalWork);
//					
//					int oldStatus = 0;
//
//					while(!energyProcess.isProcessCompleted()) {
//						if (monitor.isCanceled()) {
//							return;
//						}
//						
//						int status = energyProcess.getProcessStatus();
//				
//						if(oldStatus != status) {
//							String msg = energyProcess.getStatusMessage();
//							monitor.subTask(msg);
//							// because of two different asynchronous threads we increase it eventually by more than 1
//							monitor.worked((status-oldStatus));
//							
//							oldStatus = status;
//						} else {
//							try {
////								wait 250ms
//								Thread.sleep(250);
//							} catch (InterruptedException e) {
//								LOGGER.error("[StatusDialog]  "+e.getMessage());
//							}
//						}
//					}
////					show that the work comes to the end Note: this is only a good usability issue
//					monitor.worked((totalWork - oldStatus));
//					try {
////						wait 250ms
//						Thread.sleep(250);
//					} catch (InterruptedException e) {
//						LOGGER.error("[StatusDialog]  "+e.getMessage());
//					}
////					done
//					monitor.done();
//				}
//			});
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
