package de.tudresden.bau.cib.vl.gui.core.model;

import java.util.ArrayList;
import java.util.List;

import de.tudresden.bau.cib.vl.core.util.VirtualFile;

public class UserProjectModel {
	
	private List<ModelProject> projects;
	
	
	public UserProjectModel()
	{
		projects = new ArrayList<ModelProject>();
	}
	
	public void addProject(ModelProject proj)
	{
		projects.add(proj);
	}
	
	public void addProject(String projectName)
	{
		projects.add(new ModelProject(projectName));
	}	

}
