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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * Representing the persistent information of the user repository files
 * like referenced files of a user.
 * 
 * @author Ken
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")
public class UserRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8433814629867510112L;
	Integer userRepositoryId;
	Integer userId;
	/**
	 * Path to the user directory on the server.
	 */
	String userRepositoryPath;
	/**
	 * Files and their identifiers of the user.
	 */
	Set<FileInformation> files = new HashSet<FileInformation>();
	
	Set<Project> projects = new HashSet<Project>();
	
 
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UR_ID")
    public Integer getUserRepositoryId() {
    	return userRepositoryId;
    }
    
    public void setUserRepositoryId(Integer userRepositoryId) {
		this.userRepositoryId = userRepositoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
	/**
	 * @return A list with file identifiers.
	 */

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name="UR_FI", 
    			joinColumns=@JoinColumn(name="FI_ID"),
    			inverseJoinColumns=@JoinColumn(name="UR_ID"))
	@OrderBy("id")
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "userRep")
    public Set<FileInformation> getFiles() {
		return files;
	}
	
	public void addFile(FileInformation file) {
		this.files.add(file);
	}
	
	public void removeFile(FileInformation file) {
		this.files.remove(file);
	}

	/**
	 * @param models A list of model or file paths.
	 */
	public void setFiles(Set<FileInformation> files) {
		
			this.files = files;
		
	}
	
	public String getUserRepositoryPath() {
		return userRepositoryPath;
	}
	
	public void setUserRepositoryPath(String userRepositoryPath) {
		this.userRepositoryPath = userRepositoryPath;
	}

	@Override
    public int hashCode() {
    	return userRepositoryId;
    }
	
	@Override
    public boolean equals(Object obj) {
		if(obj instanceof UserRepository)
		{
			UserRepository ur = (UserRepository) obj;
			return ur.hashCode() == this.hashCode() && this.userId == ur.userId;
		}
		else
			return false;
    };
    
    @Override
    public String toString() {
    	return userId+":"+userRepositoryPath;
    }
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userRepository")
    public Set<Project> getProjects() {
 		return projects;
 	}
    
    
    public void setProjects(Set<Project> projects) {
 		//this.projects = new HashSet<Project>(projects);
 		this.projects = projects;
 	}
    
    public void addProject(Project proj)
    {   	
    	
    	projects.add(proj);
    }
    
    public void removeProject(Project proj)
    {
    	projects.remove(proj);
    }
}
