//package de.tudresden.bau.cib.vl.gui.simulation.energy.view.handler;
//
//import org.eclipse.core.commands.AbstractHandler;
//import org.eclipse.core.commands.ExecutionEvent;
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.jface.window.Window;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.ui.handlers.HandlerUtil;
//
//import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.SimulationController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.StartSimulationDialog;
//
//
//public class StartEnergySimulationHandler extends AbstractHandler {
//
//	
//	@Override
//	public Object execute(ExecutionEvent event) throws ExecutionException {
//		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
//
//		SimulationController controller = SimulationController.getInstance();
//		StartSimulationDialog dialog = new StartSimulationDialog(controller.getIfcModel(), controller.getSelectedGuids(), shell);
//		dialog.create();
//		
//		if (dialog.open() == Window.OK) {
////			return dialog.getSelectedCombustibles();
//		} 
//		return null;
//	}
//
//}
