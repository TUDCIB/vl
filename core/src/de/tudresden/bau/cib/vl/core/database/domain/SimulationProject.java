package de.tudresden.bau.cib.vl.core.database.domain;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "discriminator") // uncomment if there are problems in previous release
@DiscriminatorColumn(name="Discriminator", discriminatorType=DiscriminatorType.STRING)  // remove if there are problems in previous release
@DiscriminatorValue(value="SP") // remove if there are problems in previous release
public class SimulationProject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4082946846128021313L;

	/**
	 * Current simulation type. For example, if status == NANDRAD_PASSIV then it means that 
	 * it is currently a simulation project for a passive simulation.
	 * BEGIN is before a passive simulation, NANDRAD before CFD etc.
	 */
	public enum TYPE {
		BEGIN (0),
		NANDRAD_PASSIV (1),
		THERAKLES (2),
		NANDRAD (3),
		CFD (4),
		OPTIMIZATION (5)
		;
		private int order = 0;
		
		private TYPE(int order) {
			this.order = order;
		}
		
		public int getOrder() {
			return order;
		}
		
	}	
	
	public enum STATUS {
		NEW,
		RUNNING,
		COMPLETED,
		ERROR
	}	
	
	public static final String DEFAULT_RELATIVE_INPUT_DIR = "INPUT";
	public static final String DEFAULT_RELATIVE_OUTPUT_DIR = "OUTPUT";
	
	String name;
	Integer id;
	
	TYPE simulationTypeId = TYPE.BEGIN;
	
	STATUS simulationStatus = STATUS.NEW;
	
	String pathDirectory;
	String pathInputDirectory;
	String pathOutputDirectory;
	
	String remoteId;

	Set<FileInformation> inputFiles = new HashSet<FileInformation>();    
	Set<FileInformation> outputFiles = new HashSet<FileInformation>(); 

	Project project;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SIMPROJ_ID")
    public Integer getId() {
    	return id;
    }
    
    public void setId(Integer id) {
		this.id = id;
	}

	public TYPE getSimulationTypeId() {
		return simulationTypeId;
	}

	public void setSimulationTypeId(TYPE simulationTypeId) {
		this.simulationTypeId = simulationTypeId;
	}



	public String getPathInputDirectory() {
		return pathInputDirectory;
	}

	public String getPathOutputDirectory() {
		return pathOutputDirectory;
	}
	
	void setPathInputDirectory(String pathInputDirectory) {
		this.pathInputDirectory = pathInputDirectory;
	}

	void setPathOutputDirectory(String pathOutputDirectory) {
		this.pathOutputDirectory = pathOutputDirectory;
	}
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//    @JoinTable(name="SIMPROJ_FI", 
//    			joinColumns=@JoinColumn(name="FI_ID_IN", nullable=true),
//    			inverseJoinColumns=@JoinColumn(name="SIMPROJ_ID"))
//	@OrderBy("id")
////	@OneToMany(fetch = FetchType.EAGER, mappedBy = "proj")	
////	@OrderBy("id DESC")	
	@JoinColumn(name="fi_id_in", nullable=true)
	public Set<FileInformation> getInputFiles() {
		return inputFiles;
	}

	public void setInputFiles(Set<FileInformation> inputFiles) {
		this.inputFiles = inputFiles;
	}
	
	public void addInputFiles(FileInformation file)
	{
		inputFiles.add(file);
	}
	
	public void removeInputFile(FileInformation file) {
		inputFiles.remove(file);
	}
	
	public void removeOutputFile(FileInformation file) {
		outputFiles.remove(file);
	}
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//    @JoinTable(name="SIMPROJ_FI", 
//    			joinColumns=@JoinColumn(name="FI_ID_OUT", nullable=true),
//    			inverseJoinColumns=@JoinColumn(name="SIMPROJ_ID"))
//	@OrderBy("id")
////	@OneToMany(fetch = FetchType.EAGER, mappedBy = "proj")	
	@JoinColumn(name="fi_id_out", nullable=true)
	public Set<FileInformation> getOutputFiles() {
		return outputFiles;
	}

	public void setOutputFiles(Set<FileInformation> outputFiles) {
		this.outputFiles = outputFiles;
	}
	
	public void addOutputFiles(FileInformation file)
	{
		outputFiles.add(file);
	}

	public STATUS getSimulationStatus() {
		return simulationStatus;
	}

	public void setSimulationStatus(STATUS simulationStatus) {
		this.simulationStatus = simulationStatus;
	}

	/**
	 * The remote identifier is necessary to have a relation to a cloud API. But we also use it to specify the directory name in the
	 * temp directory so that the simulation results can be retrieved on a local machine.
	 * @return The identifier where simulation results are present.
	 */
	public String getRemoteId() {
		return remoteId;
	}

	/**
	 * The remote identifier is necessary to have a relation to a cloud API. But we also use it to specify the directory name in the
	 * temp directory so that the simulation results can be retrieved on a local machine.
	 * @param remoteId
	 */
	public void setRemoteId(String remoteId) {
		this.remoteId = remoteId;
	}
	
	@Override
	public int hashCode() {
		int prime = 11;
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
		SimulationProject sp = (SimulationProject) obj;
		return sp.id == this.id && sp.name == this.name && sp.pathInputDirectory == this.pathInputDirectory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID", nullable = false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project proj) {
		this.project = proj;
	}

	public String getPathDirectory() {
		return pathDirectory;
	}

	public void setPathDirectory(String pathDirectory) {
		this.pathDirectory = pathDirectory;
		
		this.pathInputDirectory = pathDirectory + File.separator + SimulationProject.DEFAULT_RELATIVE_INPUT_DIR;
		this.pathOutputDirectory = pathDirectory + File.separator + SimulationProject.DEFAULT_RELATIVE_OUTPUT_DIR;	
	}

	@Override
	public String toString() {
		return "SimulationProject: "+id+" with name: "+name+" and remoteId: "+remoteId+" of parent project: "+project;
	}
	
}
