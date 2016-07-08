package de.tudresden.bau.cib.vl.core.database.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="OSP") 
public class OptimizationSimulationProject extends SimulationProject {


	/**
	 * 
	 */
	private static final long serialVersionUID = 157787317837689575L;
	
//	Set<SimulationProject> simProjects;  
//	TODO foreign key fehlt in simproject
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
//	public Set<SimulationProject> getSimProjects() {
//		return simProjects;
//	}
//	
//	public void addSimulationProject(SimulationProject simProject){
//		if(simProjects == null) simProjects = new HashSet<SimulationProject>();
//		simProjects.add(simProject);	
//	}
//
//	public void setSimProjects(Set<SimulationProject> simProjects) {
//		this.simProjects = simProjects;
//	}
	
	@Override
	public int hashCode() {
		int prime = 17;
		int hash = super.hashCode();
		if(name != null) {			
			hash = hash + (prime * name.hashCode());			
		}
		if(id != null) {
			hash = hash + (prime * id);
		}
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		OptimizationSimulationProject sp = (OptimizationSimulationProject) obj;
		return sp.id == this.id && sp.name == this.name && sp.hashCode() == this.hashCode();
	}
}
