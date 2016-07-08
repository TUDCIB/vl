package de.tudresden.bau.cib.vl.core.database.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")
public class Project implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -105662237899436532L;
	
	Integer id;
	//Integer userId;

	@Column(unique = true)
	String name;
	
	String pathDirectory;
	
	Set<FileInformation> files = new HashSet<FileInformation>();
	Set<SimulationProject> simProjects = new HashSet<SimulationProject>();    
	
	UserRepository userRepository;	



	public String getPathDirectory() {
		return pathDirectory;
	}

	public void setPathDirectory(String pathDirectory) {
		this.pathDirectory = pathDirectory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PROJ_ID")
    public Integer getId() {
    	return id;
    }
    
    public void setId(Integer id) {
		this.id = id;
	}
    
	@Override
	public int hashCode() {
		int prime = 43;
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
		Project p = (Project) obj;
		return p.id == this.id && p.name == this.name && p.pathDirectory == this.pathDirectory;
	}
	
	@Override
	public String toString() {
		return "ID: "+this.id + " Name: "+this.name;
	}
	
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name="PROJ_FI", 
    			joinColumns=@JoinColumn(name="FI_ID"),
    			inverseJoinColumns=@JoinColumn(name="PROJ_ID"))
	@OrderBy("id")	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "proj")	
//	@OrderBy("id DESC")
    public Set<FileInformation> getFiles() {
		return files;
	}

	public void setFiles(Set<FileInformation> files) {
		this.files = files;
	}
	
	
	public void addFile(FileInformation file){			
		files.add(file);		
	}
	
	public void removeFile(FileInformation file){			
		files.remove(file);		
	}
	

//	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//    @JoinTable(name="PROJ_SIMPROJ", 
//    			joinColumns=@JoinColumn(name="SIMPROJ_ID"),
//    			inverseJoinColumns=@JoinColumn(name="PROJ_ID"))
//	@OrderBy("id")
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")	
	public Set<SimulationProject> getSimProjects() {
		return simProjects;
	}

	public void setSimProjects(Set<SimulationProject> simProjects) {
		this.simProjects = simProjects;
	}
	
	public void addSimulationProject(SimulationProject simProject){
		simProjects.add(simProject);	
	}
	
	public void removeSimulationProject(SimulationProject simProject) {
		
		
		SimulationProject found = null;
		
		for(SimulationProject simP : simProjects)
		{
			if(simP.getId().intValue() == simProject.getId().intValue())
			{
				found = simP;
				break;
			}
		}
		
		if(found != null)
			simProjects.remove(found);
	
	
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UR_ID", nullable = false)
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRep) {
		this.userRepository = userRep;
	}
	
}
