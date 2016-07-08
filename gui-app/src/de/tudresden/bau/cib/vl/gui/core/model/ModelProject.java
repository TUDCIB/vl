package de.tudresden.bau.cib.vl.gui.core.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.tudresden.bau.cib.vl.core.util.VirtualFile;

public class ModelProject{
	
	String name;
	Set<VirtualFile> files;
	public Set<VirtualFile> getFiles() {
		return files;
	}

	public void setFiles(Set<VirtualFile> files) {
		this.files = files;
	}

	public List<ModelSimulationProject> getSimProjects() {
		return simProjects;
	}

	public void setSimProjects(List<ModelSimulationProject> simProjects) {
		this.simProjects = simProjects;
	}

	List<ModelSimulationProject> simProjects;
	
	public ModelProject(String name)
	{
		this.name = name;
		files = new HashSet<VirtualFile>();
		simProjects = new ArrayList<ModelSimulationProject>();
	}	
	
}
