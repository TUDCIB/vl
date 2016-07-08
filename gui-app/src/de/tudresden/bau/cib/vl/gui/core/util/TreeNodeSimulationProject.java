package de.tudresden.bau.cib.vl.gui.core.util;

import org.eclipse.core.runtime.IAdaptable;

import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.util.VirtualFile;


public class TreeNodeSimulationProject extends TreeNode {

	private SimulationProject simulationProject;
	
	public TreeNodeSimulationProject(String name) {
		super(name);
	}

	public SimulationProject getSimulationProject() {
		return simulationProject;
	}

	public void setSimulationProject(SimulationProject simulationProject) {
		this.simulationProject = simulationProject;
	}
	
	
	

}