package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.OptimizationSimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.service.SimulationService;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicatorsToBe;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.SimulationMatrixException;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;

public interface EnergySimulationService extends SimulationService {
	
	/**
	 * Simulation with Therakles
	 * 
	 * @param simulationProject
	 * @param ontologyModel
	 * @param locationIds, GlobalIds of the IfcSpaces
	 * @throws SimulationException 
	 */
	SimulationProject startEnergySimulationForZones(SimulationProject simulationProject, OntologyModel ontologyModel, Set<String> locationIds, 
			Ifc2x3DataModelProxy ifcModel) throws SimulationException;

	/**
	 * This method simulates the thermal behaviour of the whole building by using the NANDRAD solver of TUD-IBK.
	 * <p>
	 * It is divided into six steps:
	 * (0) it retrieves the construction templates of the user who is requesting the simulation.
	 * (1) Write Location identifiers to text file	- includes always the GUID of the building
	 * (2) Create Ifc2Nandrad and Nandrad project distribution directory structure
	 * (3) Start BIM2SIM conversion
	 * (4) Start NANDRAD simulation 
	 * </p>
	 * @param simulationProject
	 * @param constructions
	 * @param ontologyModel
	 * @param requestedLocationIds
	 * @param ifcModel
	 * @return
	 * @throws SimulationException
	 */
	SimulationProject startEnergySimulationForBuilding(SimulationProject simulationProject,
			List<ConstructionTemplate> constructions,
			OntologyModel ontologyModel,
			Ifc2x3DataModelProxy ifcModel)
			throws SimulationException;
	
	void startPassiveSimulation(Integer userId, SimulationProject simulationProject) throws SimulationException, IOException;
	
	void startPassiveSimulation(SimulationProject simulationProject, String nandradModelFilePath) throws SimulationException;

	/**
	 * Starts the sensitivity analysis.
	 * @param simProject
	 * @throws SimulationException
	 * @throws IOException
	 */
	void startSensitivityAnalysis(SimulationProject simProject) throws SimulationException, IOException;

	/**
	 * Copy all needed resources to a target directory in the right structure.
	 * 		COPY DIRECTORIES WITH FOLLOWING STRUCTURE:
	 *		directory
	 *		|-	bin 
	 *		|	|	-	Bim2Sim
	 *		|	|			|	-	Bim2Sim.jar
	 *		|	|	-	Nandrad
	 *		|	|			|	-	NandradSolver.exe
	 *		|	|	-	ResCon
	 *		|	|			|	-	ResCon.jar
	 *		|	|	-	Therakles
	 *		|	|			|	-	TheraklesSolver.exe
	 *		|	|			|	-	...
	 *		|-	project
	 *		|	|	-	DB_Climate
	 *		|	|			|	-	...
	 *		|	|	-	DB_Templates
	 *		|	|				|	-	HESMOS_Outputs.xml
	 *		|	|				|	-	HESMOS_Schedules.xml
	 *		|	|				|	-	HESMOS_SpaceTypes.xml
	 *		|	|	-	SimMa
	 *		|	|			|	-	EMPTY -> !!! THE SIMULATION MATRIX MUST BE COPIED AFTER THIS METHOD !!!
	 *		|	|	-	ISES_project.ifc
	 *		|	|	-	ISES_project.rdf
	 *		|
	 *		|-	resources
	 *		|	|	-	...
	 *		|-	scripts
	 *		|	|	-	...
	 *
	 * @param passiveSimProject
	 * @param sensitivityProject
	 * @throws IOException
	 */
	void copyContentFromPassiveProjectToSensitiveProject(SimulationProject passiveSimProject,
			SimulationProject sensitivityProject) throws IOException;
	
	void copyContentFromSensitiveProjectToSensitiveProject(
			SimulationProject oldSimProject,
			SimulationProject newSimProject) throws IOException;
		
	/**
	 * TODO javadoc
	 * Creates multiple ontology models by assigning various constructions to building elements.
	 * @param ifcModel
	 * @param ontologyModel
	 * @param simProject
	 * @param roomGuidToeKPIToBe
	 * @return
	 */
	boolean optimizeGreenBuildingDesign(Integer userId, Ifc2x3DataModelProxy ifcModel, OntologyModel ontologyModel, 
			OptimizationSimulationProject simProject, Map<String, EnergyKeyPerformanceIndicatorsToBe> roomGuidToeKPIToBe);

	FileInformation saveSimulationMatrixToSimulationProject(
			TSimulationMatrix matrix, Integer userId,
			SimulationProject simProject) throws URISyntaxException,
			SimulationMatrixException, IOException;



}
