package de.tudresden.bau.cib.vl.core.database.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import de.tudresden.bau.cib.vl.core.database.dao.ProjectDao;
import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;

public class ProjectDaoImpl implements ProjectDao {

	
	private SessionFactory sessionFactory;
	private static final String TABLE = Project.class.getSimpleName();
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	
	@Override
	public Integer insertProject(Project project) {
		return (Integer) sessionFactory.getCurrentSession().save(project);		

	}
	
	@Override
	public Integer insertSimProject(SimulationProject simProject) {
		return (Integer) sessionFactory.getCurrentSession().save(simProject);		

	}

	@Override
	public void updateProject(Project project) {
		sessionFactory.getCurrentSession().update(project);
	}

	@Override
	public void deleteProject(Project project) {
		sessionFactory.getCurrentSession().delete(project);

	}

	@Override
	public Project getProjectByName(String name) {
		return (Project) sessionFactory.getCurrentSession().get(Project.class,name);
	}

	@Override
	public Project getProjectById(Integer id) {
		return (Project) sessionFactory.getCurrentSession().get(Project.class,id);

	}

	@Override
	public void updateSimulationProject(SimulationProject simProject) {		
		//updateProject(simProject.getProject());
		//Project project = getProjectBySimulationProject(simProject);
		//updateProject(project);
		
		sessionFactory.getCurrentSession().update(simProject);
//		
	}

	@Override
	public Project getProjectBySimulationProject(SimulationProject project) {
		List<Project> projects = listProjects();
		for(Project prj : projects) {
			Set<SimulationProject> simProjects = prj.getSimProjects();
			for(SimulationProject simPrj : simProjects) {
				if(simPrj.getId().intValue() == project.getId().intValue())
				//if(simPrj.getId() == project.getId())
					return prj;
			}
		}
		return null;
	}

	@Override
	public List<Project> listProjects() {
		String hql = "from "+TABLE;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Project> list = query.list();
		return list;
	}

	@Override
	public SimulationProject getSimulationProjectById(Integer id) {
		List<Project> projects = listProjects();
		for(Project prj : projects) {
			Set<SimulationProject> simProjects = prj.getSimProjects();
			for(SimulationProject simPrj : simProjects) {
				if(simPrj.getId() == id) return simPrj;
			}
		}
		return null;
	}

	@Override
	public void deleteSimulationProject(SimulationProject simProject) {
		Object mergedObject = sessionFactory.getCurrentSession().merge(simProject);
		if(mergedObject instanceof SimulationProject) {
			SimulationProject mobj = (SimulationProject) mergedObject;
			sessionFactory.getCurrentSession().delete(mobj);
		}		
		
	}

}
