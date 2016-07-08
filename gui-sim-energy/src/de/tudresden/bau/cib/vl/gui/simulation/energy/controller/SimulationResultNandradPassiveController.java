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
//
//
//
//public class SimulationResultNandradPassiveController extends SimulationResultController
//{
//
//	@Override
//	public void setModelNanadradPassive()
//	{
//		if(view == null || view.getClass() == SimulationResultNandradPassiveView.class)
//		 {		
//
//			try {
//				PlatformUI.getWorkbench().getActiveWorkbenchWindow().
//				getActivePage().showView("de.tudresden.bau.cib.vl.gui.simulation.energy.ui.view.main.SimulationResultNandradPassiveView");																		  
//			} catch (PartInitException e) {
//				LOG.error("{}", e.getMessage(), e);
//			}		
//			
//			try {
//				Set<FileInformation> files = model.getOutputFiles();
//				
//				Set<FileInformation> pictures = new HashSet<FileInformation>();
//				FileInformation zonesOfInterests = null;		
//				for(FileInformation file : files)
//				{
//					if(file.getFileType() == FileInformation.TYPE.PNG)
//					{
//						pictures.add(file);
//					}
//					
//					if(file.getName().compareToIgnoreCase(ZONES_OF_INTEREST_SUMMARY_FILE) == 0)
//					{
//						zonesOfInterests = file;
//					}			
//					
//				}		
//				
//				NandradOutputSummary nandrad = new NandradOutputSummary();
//				nandrad.ReadSummaryFile( zonesOfInterests.getUrl());	
//				
//				List<ResultsOfZone> resultsOfZone = nandrad.resultsOfZones;
//				
//				((SimulationResultNandradPassiveView) view).setResults(resultsOfZone, pictures);
//			} catch (IOException e) {
//				LOG.error("{}", e.getMessage(), e);
//			}	
//		 }		 								
//
//		
//		 if(view != null && view.getClass() != SimulationResultNandradPassiveView.class)
//		 {
//				PlatformUI.getWorkbench().getActiveWorkbenchWindow().
//				getActivePage().hideView(view);
//		 }		
//	}
//
//}
