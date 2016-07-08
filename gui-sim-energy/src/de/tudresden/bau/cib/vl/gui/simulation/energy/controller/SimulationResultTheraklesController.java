//package de.tudresden.bau.cib.vl.gui.simulation.energy.controller;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.eclipse.ui.IViewPart;
//import org.eclipse.ui.PartInitException;
//import org.eclipse.ui.PlatformUI;
//
//import sensitivity_input.NandradOutputSummary;
//import sensitivity_input.ResultsOfZone;
//import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.SimulationResultNandradPassiveView;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.SimulationResultNandradView;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.SimulationResultTheraklesView;
//
//
//
//public class SimulationResultTheraklesController extends SimulationResultController
//{
//
//	@Override
//	public void setModelTherakles()
//	{
//		if(view == null || view.getClass() == SimulationResultTheraklesView.class)
//		 {
//			try {
//				PlatformUI.getWorkbench().getActiveWorkbenchWindow().
//				getActivePage().showView("de.tudresden.bau.cib.vl.gui.simulation.energy.ui.view.main.SimulationResultTheraklesView");
//			} catch (PartInitException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			view.setModel(model);	
//		 }									
//
//		
//		 if(view != null && view.getClass() != SimulationResultTheraklesView.class)
//		 {
//				PlatformUI.getWorkbench().getActiveWorkbenchWindow().
//				getActivePage().hideView(view);
//		 }		
//	}
//
//}
