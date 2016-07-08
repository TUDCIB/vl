package de.tudresden.bau.cib.vl.gui.core.model;

import java.util.ArrayList;
import java.util.List;

import de.tudresden.bau.cib.vl.core.util.VirtualFile;

public class ModelSimulationProject{
	
	enum SimualtionStatus {
		
		NEW,
		READY,
		RUNNING,
		FINISHED_OK,
		FINISHED_NOK			
		
	};
	
	String name;
	SimualtionStatus status;

	
	List<VirtualFile> inputFiles;
	List<VirtualFile> outputFiles;
	
	
	public ModelSimulationProject()
	{
		status = SimualtionStatus.NEW;
		inputFiles = new ArrayList<VirtualFile>();
		outputFiles = new ArrayList<VirtualFile>();
	}
	
};
