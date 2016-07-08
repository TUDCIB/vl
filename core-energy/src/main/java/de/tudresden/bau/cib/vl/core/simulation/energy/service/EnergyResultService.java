package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.time.TimeMeasure;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicators;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.PostProcessingException;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.ResultCodes;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;

public interface EnergyResultService {
	
	public static final SimpleDateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	static final String NO_ROOM_IDENTIFIER = "-1";
	
	enum EnergyResourceType {
		CO2,
		SO2,
		NOX
	}

	static final String COMBUSTIBLE_FILE_NAME = "combustibles.xml";
	
	
	enum DetailLevel {
		AGGREGATED, 
		BOTH, 
		SEPARATED;
		
		public static DetailLevel findByAbbreviation(String abbreviation) throws IllegalArgumentException {
			if(abbreviation != null) {
			    for(DetailLevel tm : values()){
			        if(tm.name().equalsIgnoreCase(abbreviation)){
			            return tm;
			        }
			    }
			}
		    throw new IllegalArgumentException("Can't find detail level with name: "+abbreviation);
		}
	}

	void deleteSimulationResults(Integer simulationId) throws SimulationException;

	/**
	 * @param simulationId
	 * @return The simulation result project directory.
	 */
	File getSimulationResultDirectory(Integer simId);
	
	List<MeasuredUnit> getSimulationResultSet(Integer userId, String eeBimId, Set<Integer> resultCodes,
			List<String> locationIds, String startTime, String endTime,
			Integer unitTime, TimeMeasure timeMeasure, DetailLevel detailLevel,
			Integer simulationId, Ifc2x3DataModelProxy ifcModel, OntologyModel ontologyModel) throws PostProcessingException;
	
	Map<ResultCodes, List<MeasuredUnit>> getNandradMeasuredUnits(
			Set<ResultCodes> resultTypes,
			List<File> energyResults, int startYear) throws SimulationException;

	List<MeasuredUnit> getTheraklesMeasuredUnits(List<String> locationIds,
			Set<ResultCodes> requestedResultTypes, List<File> energyResults, int startYear,
			Integer unitTime, String timeMeasure);
	
	void saveEnergySimulationResults(Integer simulationId, Set<ResultCodes> codes) throws SimulationException;
	
	/**
	 * Initial method for starting the post-processing. It first starts the post-processing dependent on the time constraints 
	 * (start date, end date, unit time, time measure) and after that on detail level with regard to requested location identifiers.
	 * 
	 * @param units
	 * @param locationIds
	 * @param startDate
	 * @param endDate
	 * @param unitTime
	 * @param timeMeasure
	 * @param detailLevel
	 * @param ifcModel
	 * @return
	 */
	public List<MeasuredUnit> doPostProcessing(
			SimulationProject simulation,
			Map<ResultCodes, List<MeasuredUnit>> rawUnits, 
			List<String> requestedLocationIds,
			Date startDate,
			Date endDate,
			Integer unitTime,
			TimeMeasure timeMeasure, 
			DetailLevel detailLevel,
			Ifc2x3DataModelProxy ifcModel) throws PostProcessingException;
	
	/**
	 * Post-processing dependent on requested detail level.
	 * @param rawUnits
	 * @param detailLevel
	 * @param locationIds
	 * @param ifcModel
	 * @return
	 * @throws PostProcessingException
	 */
	Map<String, Set<MeasuredUnit>> doPostProcessingForDetailLevel(
			Set<MeasuredUnit> rawUnits, DetailLevel detailLevel,
			List<String> requestedLocationIds, Ifc2x3DataModelProxy ifcModel)
			throws PostProcessingException;
	
	/**
	 * Post-processing for time constraints.
	 * @param rawUnits
	 * @param startDate
	 * @param endDate
	 * @param unitTime
	 * @param timeMeasure
	 * @return
	 */
	Set<MeasuredUnit> doPostProcessingForTime(List<MeasuredUnit> rawUnits, Date startDate,
			Date endDate, Integer unitTime, TimeMeasure timeMeasure);
	
	MeasuredUnit doPostProcessingForCosts(List<String> locationIds,
			ResultCodes code, Ifc2x3DataModelProxy ifcModel, OntologyModel ontologyModel,
			SimulationProject simulation, List<ConstructionTemplate> constructionTemplates) throws PostProcessingException;
	
	/**
	 * Post-processing if eKPI is requested 
	 * @param eKPIs
	 * @param code
	 * @param rawUnits
	 * @param ifcModel
	 * @return
	 * @throws PostProcessingException
	 */
	Map<String, List<MeasuredUnit>> doPostProcessingForEnergyKeyPerformanceIndicators(
			SimulationProject simulation,
			ResultCodes code, Map<String, Set<MeasuredUnit>> rawUnits,
			Ifc2x3DataModelProxy ifcModel) throws PostProcessingException;
	
	EnergyKeyPerformanceIndicators getEKPIBySimulationId(Integer simId);
	
	List<Combustible> listCombustibles();

	Map<String, Set<MeasuredUnit>> filterRequestsWithNoResultUnits(
			Map<String, Set<MeasuredUnit>> unitsDependentOnDetailLevel);
	
	CombustibleContainer loadCombustibles(File file) throws IOException;
	void saveCombustibles(File file, CombustibleContainer combustibleContainer) throws IOException;
	
	void saveEnergyKeyPerformanceIndicators(EnergyKeyPerformanceIndicators eKPIs);

	/**
	 * Calculate the investment costs by retrieving the space boundaries and the related building elements because the area of the elements 
	 * is only given through the space boundary calculation by BSPro, otherwise we cannot calculate the investment costs currently.
	 * @param buildingGuid Only the GUID of a IfcBuilding is permitted.
	 * @param ifcModel Null value is not accepted
	 * @param ontologyModel Null value is not accepted
	 * @param constructionTemplates Null value is not accepted
	 * @return The investment costs number.
	 * @throws PostProcessingException
	 */
	Double calculateInvestmentCosts(String buildingGuid,
			Ifc2x3DataModelProxy ifcModel, OntologyModel ontologyModel,
			List<? extends ConstructionTemplate> constructionTemplates)
			throws PostProcessingException;
	
	/**
	 * Calculate the investment costs by retrieving the space boundaries and the related building elements because the area of the elements 
	 * is only given through the space boundary calculation by BSPro, otherwise we cannot calculate the investment costs currently.
	 * @param spatialStructureGlobalIds GlobalId of IfcBuilding, IfcBuildingStorey or IfcSpace are permitted.
	 * @param ifcModel Null value is not accepted
	 * @param ontologyModel Null value is not accepted
	 * @param constructionTemplates Null value is not accepted
	 * @return The investment costs number.
	 * @throws PostProcessingException
	 */
	Double calculateInvestmentCosts(Collection<String> spatialStructureGlobalIds,
			Ifc2x3DataModelProxy ifcModel, OntologyModel ontologyModel,
			List<ConstructionTemplate> constructionTemplates)
			throws PostProcessingException;

	/**
	 * Creates the energy key performance indicator for the requested simulation. It calculates the building shell area, 
	 * window area and investment costs based on the assigned construction templates.
	 * 
	 * @param eeBim
	 * @param ifcModel
	 * @param constructionTemplates
	 * @param locationIds
	 * @param simInfoId
	 * @return The new eKPI instance.
	 * @throws IfcException
	 */
	EnergyKeyPerformanceIndicators createEnergyKeyPerformanceIndicator(
			OntologyModel eeBim, Ifc2x3DataModelProxy ifcModel,
			List<? extends ConstructionTemplate> constructionTemplates,
			Set<String> locationIds, Integer simInfoId) throws IfcException;
}
