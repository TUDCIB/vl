package de.tudresden.bau.cib.vl.gui.core.util;

import org.eclipse.core.runtime.IAdaptable;

import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.util.VirtualFile;


public class TreeNodeProject extends TreeNode {

	private Project project;
	
	public TreeNodeProject(String name) {
		super(name);
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	
	
	

}