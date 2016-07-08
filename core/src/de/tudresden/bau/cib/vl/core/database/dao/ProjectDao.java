package de.tudresden.bau.cib.vl.core.database.dao;




import java.util.List;

import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;


public interface ProjectDao {
	
	
	public Integer insertProject(Project project);
	public void updateProject(Project project);
	public void deleteProject(Project project);
	
	public Project getProjectByName(String name);
	public Project getProjectById(Integer id);	
	public List<Project> listProjects();
	void deleteSimulationProject(SimulationProject simProject);
	public void updateSimulationProject(SimulationProject simProject);
	public SimulationProject getSimulationProjectById(Integer id);
	
	public Project getProjectBySimulationProject(SimulationProject project);
	Integer insertSimProject(SimulationProject simProject);


}
