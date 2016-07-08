package de.tudresden.bau.cib.vl.core.simulation.energy.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingelement;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcinternalorexternalenum;
import jsdai.SIfc2x3.EIfcphysicalorvirtualenum;
import jsdai.SIfc2x3.EIfcrelspaceboundary;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcwindow;
import jsdai.lang.SdaiException;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject.STATUS;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.util.CombustibleResourceFactoryImpl;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.CombustibleContainerDeserializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.sparql.EeBimQueryExecutor;
import de.tudresden.bau.cib.vl.core.model.ontology.sparql.EeBimQueryExecutor.VARIABLES;
import de.tudresden.bau.cib.vl.core.model.processing.PostProcessingEngine;
import de.tudresden.bau.cib.vl.core.model.time.TimeMeasure;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.service.UserService;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.dao.EnergyKeyPerformanceIndicatorDao;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.dao.EnergyResultsDao;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicators;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyResults;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.EnergyKeyPerformanceIndicatorException;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.PostProcessingException;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.ResultCodes;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles.TheraklesEnergyResults;
import de.tudresden.bau.cib.vl.core.simulation.energy.postprocessing.EnergyKeyPerformanceIndicatorCalculator;
import de.tudresden.bau.cib.vl.core.simulation.energy.postprocessing.NandradPostProcessingInputStream;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergyResultService;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergySimulationService;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationExceptionCode;

public class EnergyResultServiceImpl implements EnergyResultService {

	private ConfigurationService configurationService;
	private UserService userService;
	private EnergySimulationService energySimulationService;
	private TemplateService templateService;
	private EnergyResultsDao energyResultsDao;
	
	private EnergyKeyPerformanceIndicatorDao energyKeyPerformanceIndicatorDao;
	
	final String AREA_PATTERN = "#spaceboundary_area=";	
	
	private static Map<Object, Object> loadOptions;
	private final SimpleDateFormat sdf = new SimpleDateFormat("mmm:ss:SSS");
	
	/**
	 * Needed for EMF
	 */
	static {
		loadOptions = new HashMap<Object, Object>();
		loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_LAX_FEATURE_PROCESSING, Boolean.TRUE);
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(EnergyResultServiceImpl.class);

	
	@Override
	public void deleteSimulationResults(Integer simulationId) throws SimulationException {
		SimulationProject simInformation = userService.getSimulationProjectById(simulationId);
		File outputDir = new File(simInformation.getPathOutputDirectory());
		File[] files = outputDir.listFiles();
		FileService.deleteFiles(files);
	}
	
	@Override
	public List<MeasuredUnit> getSimulationResultSet(
			Integer userId,
			String eeBimId,
			Set<Integer> resultTypes,
			List<String> locationIds,
			String startTime,
			String endTime,
			Integer unitTime,
			TimeMeasure timeMeasure,
			DetailLevel detailLevel,
			Integer simulationId,
			Ifc2x3DataModelProxy ifcModel,
			OntologyModel ontologyModel) throws PostProcessingException {
		
		try {
			Date startDate = ISO_DATE_FORMAT.parse(startTime);
			Date endDate = ISO_DATE_FORMAT.parse(endTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			int startYear = cal.get(Calendar.YEAR);
			
			Set<ResultCodes> codes = new HashSet<ResultCodes>();
			for(Integer rt : resultTypes) {
				codes.add(ResultCodes.valueOf(rt));
			}
						
			List<MeasuredUnit> units = new ArrayList<MeasuredUnit>();
			SimulationProject simulation = null;

			if(simulationId != null) { // if simulation identifier is available
				simulation = userService.getSimulationProjectById(simulationId);
			} else { // no simulation identifier available => search it
				throw new RuntimeException("Currently not provided");
			}
			
//			check if it is cost-related request => other post-processing needed
			if(codes.contains(ResultCodes.Energy_Related_Operational_Costs)
				|| codes.contains(ResultCodes.Non_Energy_Related_Operational_Costs)
				|| codes.contains(ResultCodes.Investment_Costs)
				|| codes.contains(ResultCodes.Lifecycle_Costs)) {
				
				List<ConstructionTemplate> constructionTemplates = templateService.listConstructionResources(userId);
				
				for(ResultCodes code : codes) {
					if(code == ResultCodes.Energy_Related_Operational_Costs
						|| code == ResultCodes.Non_Energy_Related_Operational_Costs
						|| code == ResultCodes.Investment_Costs
						|| code == ResultCodes.Lifecycle_Costs) {
						
						Set<ResultCodes> heatingAndCoolingCode = new HashSet<ResultCodes>();
						heatingAndCoolingCode.add(ResultCodes.Heating_And_Cooling_Final_Energy);
//						following saves the final heating&cooling energy to the eKPI
						getPostProcessedResults(heatingAndCoolingCode, locationIds, 
								startDate, endDate, unitTime, timeMeasure, detailLevel, ifcModel, simulation);
						MeasuredUnit costsUnit = doPostProcessingForCosts(
								locationIds, code, ifcModel, ontologyModel, simulation, constructionTemplates);
						units.add(costsUnit);
						codes.remove(code); // remove it from requested codes because there is no further post-processing needed
					}
				}
			}
			
//			now do the native post-processing (time, detail level etc.)
			List<MeasuredUnit> resultsFromSimulation = getPostProcessedResults(codes, locationIds, 
					startDate, endDate, unitTime, timeMeasure, detailLevel, ifcModel, simulation);
			units.addAll(resultsFromSimulation);
			
//			Important: append only meta data if no values are available
			if(units.size() == 0) {
				for(String locId : locationIds) {
					for(int rt : resultTypes) {
						ResultCodes code = ResultCodes.valueOf(rt);
						
						MeasuredUnit unit = new MeasuredUnit(locId);
						unit.setAggregated(false);
						unit.setStartYear(startYear);
						unit.setResultCode(code.getCode());
						unit.setValues(new TreeMap<Long, Double>());
						unit.setValueUnit(code.getValueUnit());
						unit.setTimeUnit(TimeMeasure.HOUR.name());
						unit.setLabel(code.getLabel());
						unit.setQuantity(code.getQuantity());
						
						units.add(unit);
					}
				}
			}
			
			return units;
			
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
			throw new PostProcessingException(e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new PostProcessingException(e);
		} catch (ParsingException e) {
			LOG.error(e.getMessage(), e);
			throw new PostProcessingException(e);
		} catch (SimulationException e) {
			LOG.error(e.getMessage(), e);
			throw new PostProcessingException(e);
		}
	}
	
	@Override
	public File getSimulationResultDirectory(Integer simulationId) {
		SimulationProject simInfo = userService.getSimulationProjectById(simulationId);
		File resultDir = new File(simInfo.getPathOutputDirectory());
		return resultDir;
	}
	
	@Override
	public Map<ResultCodes, List<MeasuredUnit>> getNandradMeasuredUnits(
			Set<ResultCodes> requestedResultTypes,
			List<File> energyResults, int startYear) throws SimulationException {
		LOG.debug("Retrieving Nandrad results by result codes: {}, start year: {} in files: {}", 
				new Object[]{requestedResultTypes, startYear, energyResults});
		
		ExecutorService executor = null;
		String numberOfThreadsString = configurationService.getProperty(ConfigurationService.PROPERTIES.NUMBER_OF_THREADS);
		if(numberOfThreadsString != null) {
			int numberOfThreads = Integer.valueOf(numberOfThreadsString);
			if(numberOfThreads > 0) executor = Executors.newFixedThreadPool(numberOfThreads);
		} 
		if(executor == null) executor = Executors.newCachedThreadPool();
		
//		nested map because a result code can be a combination of multiple result codes
		Map<ResultCodes, List<MeasuredUnit>> results = new HashMap<ResultCodes, List<MeasuredUnit>>();
		if(energyResults == null || energyResults.size() == 0) return results;
		
		Collections.sort(energyResults);		
		
		for(ResultCodes requestedResultType : requestedResultTypes) {		
			if(requestedResultType == null) { 
				LOG.warn("No result code: {} available", requestedResultType);
				continue; // continue loop
			}
			List<NandradPostProcessingInputStream> threads = new ArrayList<NandradPostProcessingInputStream>();
			
			Map<ResultCodes, String> codesWithAssignedFileNames = getNandradResultFileNamesAssignedToCode(requestedResultType);
//			now take the collected files
			if(codesWithAssignedFileNames.size() > 0) {
				for (Map.Entry<ResultCodes, String> entry : codesWithAssignedFileNames.entrySet()) {
//					is the code for NANDRAD result without any post-processing
					ResultCodes codeForResultFile = entry.getKey();
					String fileName = entry.getValue();
					File file = searchFile(energyResults, fileName);
					if(file == null) {
						LOG.warn("No file: {} found in: {}", new Object[]{fileName, energyResults});
						throw new SimulationException(SimulationExceptionCode.NO_SIMULATION_RESULT, "No file ('"+fileName+"') available");
					}
					String filePath = file.getAbsolutePath();
					LOG.debug("Nandrad result file path: {}", new Object[]{filePath});

					NandradPostProcessingInputStream is = new NandradPostProcessingInputStream(codeForResultFile.getCode(), filePath, startYear);
					threads.add(is);							
				}
			}
			
//			Start all threads
			LOG.debug("Collect NANDRAD results with {} threads", threads.size());
			
			Map<NandradPostProcessingInputStream, Future<List<MeasuredUnit>>> futures = new HashMap<NandradPostProcessingInputStream, Future<List<MeasuredUnit>>>();
			for(NandradPostProcessingInputStream entry : threads) {
				Future<List<MeasuredUnit>> result = executor.submit(entry);
				futures.put(entry, result);
			}
//			Wait for all threads
			LOG.debug("Wait for collecting NANDRAD results by {} threads", threads.size());
			try {
				for(Map.Entry<NandradPostProcessingInputStream, Future<List<MeasuredUnit>>> entry : futures.entrySet()) {
					NandradPostProcessingInputStream stream = entry.getKey();
					ResultCodes streamCode = ResultCodes.valueOf(stream.getCode());
					List<MeasuredUnit> measuredUnits = entry.getValue().get();
					
//					now do further post-processing					
					for(MeasuredUnit mu : measuredUnits) {
						mu.setResultCode(streamCode.getCode());
						mu.setQuantity(streamCode.getQuantity());
						mu.setLabel(streamCode.getLabel());
						mu.setStartYear(startYear);
						mu.setAggregated(false);
						
						if(mu.getId().equals(NO_ROOM_IDENTIFIER)) { // no room
							LOG.warn("Measured unit found that is no room: {}, result code: {}",
									new Object[]{mu, streamCode.getCode()});
						}
					}
					if(results.containsKey(requestedResultType)) results.get(requestedResultType).addAll(measuredUnits);
					else results.put(requestedResultType, measuredUnits);				
				}
				executor.shutdown();
			} catch(InterruptedException ie) {
				LOG.error(ie.getMessage(), ie);
				throw new SimulationException(SimulationExceptionCode.NO_SIMULATION_RESULT);
			} catch (ExecutionException e) {
				LOG.error(e.getMessage(), e);
				throw new SimulationException(SimulationExceptionCode.EXECUTION_PROBLEM);
			}
//			if there was no result we add it anyway
			if(!results.containsKey(requestedResultType)) results.put(requestedResultType, null);
		}
				
		return results;
	}
	
	/**
	 * Search the appropriate energy solver results dependent on the given parameter and makes the post-processing for it.
	 * 
	 * @param resultTypes
	 * @param locationIds
	 * @param startDate
	 * @param endDate
	 * @param unitTime
	 * @param timeMeasure
	 * @param detailLevel
	 * @param ifcModel
	 * @param simulation
	 * @return Post-processed results.
	 * @throws SimulationException 
	 * @throws PostProcessingException 
	 */
	private List<MeasuredUnit> getPostProcessedResults(
			Set<ResultCodes> codes,
			List<String> locationIds, 
			Date startDate,
			Date endDate,
			Integer unitTime,
			TimeMeasure timeMeasure,
			DetailLevel detailLevel, 
			Ifc2x3DataModelProxy ifcModel,
			SimulationProject simulation) throws SimulationException, PostProcessingException {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int startYear = cal.get(Calendar.YEAR);

		List<MeasuredUnit> finalResultList = null;
		
		List<File> energyResults = getResultFiles(simulation.getId());

		LOG.info("Requested energy results between: {} in simulation: {} are saved in: {}", 
				new Object[]{startDate+" - "+endDate, simulation.getId(), energyResults});	
			

//		############# THERAKLES ###########################################################################
//		units = getTheraklesMeasuredUnits(
//					locationIds, resultTypes, energyResults, simulation.getStartYear(), unitTime, timeMeasure);

//		############# NANDRAD #############################################################################
		Map<ResultCodes, List<MeasuredUnit>> units = getNandradMeasuredUnits(codes, energyResults, startYear);					
//		###################################################################################################

		finalResultList = doPostProcessing(simulation, units, locationIds, startDate, endDate, unitTime, timeMeasure, detailLevel, ifcModel);
		LOG.info("Simulation results after post-procssing: {}",
				new Object[]{finalResultList});
		return finalResultList;
	}
	
	/**
	 * Search the result file names by the given result code.
	 * It is kind of a result code -> Nandrad mapping.
	 * @param code
	 * @return The map with result codes and their assigned file names.
	 */
	private Map<ResultCodes, String> getNandradResultFileNamesAssignedToCode(ResultCodes code) {		
			Map<ResultCodes, String> innerSet = new HashMap<ResultCodes, String>();
			switch(code) {
//			Weather data
			case Outdoor_air_temperature:			
				innerSet.put(code, "Location_Temperature.d6o");
				break;
			case Outdoor_air_relative_humidity:
				innerSet.put(code, "Location_RelativeHumidity.d6o");
				break;
			case Long_wave_sky_radiation:
//				fileName = ".d6o";
				break;
			case Wind_direction:
//				fileName = ".d6o";
				break;
			case Wind_velocity:
//				fileName = ".d6o";
				break;
			case Outdoor_air_pressure:
//				fileName = ".d6o";
				break;
			case Wind_driven_rain:
//				fileName = ".d6o";
				break;
			case Short_wave_direct_radiation:
				innerSet.put(code, "Location_SWRadDirectHorizontal.d6o");
				break;
			case Short_wave_diffuse_radiation:
				innerSet.put(code, "Location_SWRadDiffuseHorizontal.d6o");
				break;
			case Azimuth:
				innerSet.put(code, "Location_Azimuth.d6o");
				break;
			case Elevation:
//				fileName = ".d6o";
				break;
			case Short_wave_global_radiation:
				innerSet.put(code, "Location_SWRadGlobalHorizontal.d6o");
				break;
			case Cloud_coverage:
//				fileName = ".d6o";
				break;
			case Dew_Point_Temperature:
				innerSet.put(code, "Location_DewPointTemperature.d6o");
				break;
			case Precipitation:
//				fileName = ".d6o";
				break;
			case Solar_declination:
				innerSet.put(code, "Location_Declination.d6o");
				break;
//			Other
			case Other_Ground_temperature:
//				fileName = ".d6o";
				break;
//			Set Points	
			case Set_Point_Room_Temperature_Heating:
				innerSet.put(code, "Zones_HeatingSetPointTemperature.d6o");
				break;
			case Set_Point_Room_Temperature_Cooling:
				innerSet.put(code, "Zones_CoolingSetPointTemperature.d6o");
				break;
			case Set_Point_Air_Flow_Rate:
//				fileName = ".d6o";
				break;
			case Set_Point_Air_Humidity:
//				fileName = ".d6o";
				break;
			case Set_Point_CO2_Concentration:
//				fileName = ".d6o";
				break;
//			External Heat gain/loss
			case Sum_of_heat_transmission_gainloss_through_constructions:
				innerSet.put(code, "Zones_ConstructionHeatConductionGains.d6o");
				break;
			case Sum_of_heat_transmission_gainloss_through_windows:
				innerSet.put(code, "Zones_WindowHeatTransmissionGains.d6o");
				break;
			case Sum_of_short_wave_heat_radiation_gainloss_through_windows:
				innerSet.put(code, "Zones_WindowSWRadGains.d6o");
//				innerSet.put(code, "Zones_WindowSWRadGains_objectList(Active_zones).d6o");
				break;
			case Heat_transmission_through_windows_doors_openings:
				innerSet.put(code, "EmbeddedObjects_HeatTransmissionFlux.d6o");
				break;
			case Short_wave_heat_radiation_through_windows_doors_openings:
				innerSet.put(code, "EmbeddedObjects_SWRadAdsorbedFlux.d6o");
				break;
			case Heat_conduction_flux_through_interfaces:
				innerSet.put(code, "Interfaces_HeatConductionFlux.d6o");
				break;
			case Long_wave_heat_radiation_flux_through_interfaces:
				innerSet.put(code, "Interfaces_LWRadBalanceFlux.d6o");
				break;
			case Short_wave_heat_radiation_throug_interfaces:
				innerSet.put(code, "Interfaces_SWRadAdsorbedFlux.d6o");
				break;
//			Useful Energy Demand
			case Heating:
				innerSet.put(code, "Zones_HeatingGains.d6o");
//				innerSet.put(code, "Zones_HeatingConvectiveGains_objectList(Active_zones).d6o");
				break;
			case Cooling:
				innerSet.put(code, "Zones_CoolingGains.d6o");
//				innerSet.put(code, "Zones_CoolingGains_objectList(Active_zones).d6o");
				break;
			case Heating_Convective_Gains:
				innerSet.put(code, "Zones_HeatingConvectiveGains.d6o");
//				innerSet.put(code, "Zones_HeatingConvectiveGains_objectList(Active_zones).d6o");
				break;
			case Heating_Radiant_Gains:
				innerSet.put(code, "Zones_HeatingRadiantGains.d6o");
//				innerSet.put(code, "Zones_HeatingRadiantGains_objectList(Active_zones).d6o");
				break;
//			Thermal Comfort
			case Operative_Room_Temperature:
				innerSet.put(code, "Zones_OperativeTemperature.d6o");
//				innerSet.put(code, "Zones_OperativeTemperature_objectList(Active_zones).d6o");
				break;
			case Room_Air_Temperature:
				innerSet.put(code, "Zones_AirTemperature.d6o");
//				innerSet.put(code, "Zones_AirTemperature_objectList(Active_zones).d6o");
				break;
			case Positive_Deviations_Set_Point_Room_Air_Temperature:
				innerSet.put(ResultCodes.Room_Air_Temperature, "Zones_AirTemperature.d6o");
				innerSet.put(ResultCodes.Set_Point_Room_Temperature_Heating, "Zones_HeatingSetPointTemperature.d6o");
				innerSet.put(ResultCodes.Set_Point_Room_Temperature_Cooling, "Zones_CoolingSetPointTemperature.d6o");
				innerSet.put(ResultCodes.Outdoor_air_temperature, "Location_Temperature.d6o");
				break;
			case Negative_Deviations_Set_Point_Room_Air_Temperature:
				innerSet.put(ResultCodes.Room_Air_Temperature, "Zones_AirTemperature.d6o");
				innerSet.put(ResultCodes.Set_Point_Room_Temperature_Heating, "Zones_HeatingSetPointTemperature.d6o");
				innerSet.put(ResultCodes.Set_Point_Room_Temperature_Cooling, "Zones_CoolingSetPointTemperature.d6o");
				innerSet.put(ResultCodes.Outdoor_air_temperature, "Location_Temperature.d6o");
				break;
			case Mean_radiative_temperature_of_the_space_envelope:
				innerSet.put(code, "Zones_RadiantTemperature.d6o");
				break;
			case Surface_temperature_of_interfaces:
				innerSet.put(code, "Interfaces_SurfaceTemperature.d6o");
				break;
//			eKPI1.1a
			case Heating_Net_Energy:
				innerSet.put(ResultCodes.Heating, "Zones_HeatingGains.d6o");
//				innerSet.put(code, "Zones_HeatingConvectiveGains_objectList(Active_zones).d6o");
				break;
			case Heating_Final_Energy:
				innerSet.put(ResultCodes.Heating, "Zones_HeatingGains.d6o");
//				innerSet.put(code, "Zones_HeatingConvectiveGains_objectList(Active_zones).d6o");
				break;
//			eKPI1.2
			case Cooling_Net_Energy:
				innerSet.put(ResultCodes.Cooling, "Zones_CoolingGains.d6o");
//				innerSet.put(code, "Zones_CoolingGains_objectList(Active_zones).d6o");
				break;
			case Cooling_Final_Energy:
				innerSet.put(ResultCodes.Cooling, "Zones_CoolingGains.d6o");
//				innerSet.put(code, "Zones_CoolingGains_objectList(Active_zones).d6o");
				break;
//			eKPI1.1a & 1.2
			case Heating_And_Cooling_Net_Energy:
				innerSet.put(ResultCodes.Heating, "Zones_HeatingGains.d6o");
//				innerSet.put(code, "Zones_HeatingConvectiveGains_objectList(Active_zones).d6o");
				innerSet.put(ResultCodes.Cooling, "Zones_CoolingGains.d6o");
//				innerSet.put(code, "Zones_CoolingGains_objectList(Active_zones).d6o");
				break;
			case Heating_And_Cooling_Final_Energy:
				innerSet.put(ResultCodes.Heating, "Zones_HeatingGains.d6o");
//				innerSet.put(code, "Zones_HeatingConvectiveGains_objectList(Active_zones).d6o");
				innerSet.put(ResultCodes.Cooling, "Zones_CoolingGains.d6o");
//				innerSet.put(code, "Zones_CoolingGains_objectList(Active_zones).d6o");
				break;
//			eKPI2.1
			case Greenhouse_Gas_Emissions:
				innerSet.put(ResultCodes.Heating, "Zones_HeatingGains.d6o");
//				innerSet.put(code, "Zones_HeatingConvectiveGains_objectList(Active_zones).d6o");
				innerSet.put(ResultCodes.Cooling, "Zones_CoolingGains.d6o");
//				innerSet.put(code, "Zones_CoolingGains_objectList(Active_zones).d6o");
				break;
//			eKPI3.1.a
			case Temperature_Over_Under_Runs:
				innerSet.put(ResultCodes.Room_Air_Temperature, "Zones_AirTemperature.d6o");
				innerSet.put(ResultCodes.Set_Point_Room_Temperature_Heating, "Zones_HeatingSetPointTemperature.d6o");
				innerSet.put(ResultCodes.Set_Point_Room_Temperature_Cooling, "Zones_CoolingSetPointTemperature.d6o");
				innerSet.put(ResultCodes.Outdoor_air_temperature, "Location_Temperature.d6o");
				break;
//			eKPI3.1.b
			case Thermal_Comfort:
				innerSet.put(ResultCodes.Room_Air_Temperature, "Zones_AirTemperature.d6o");
				innerSet.put(ResultCodes.Set_Point_Room_Temperature_Heating, "Zones_HeatingSetPointTemperature.d6o");
				innerSet.put(ResultCodes.Set_Point_Room_Temperature_Cooling, "Zones_CoolingSetPointTemperature.d6o");
				innerSet.put(ResultCodes.Outdoor_air_temperature, "Location_Temperature.d6o");
				break;
//			eKPI3.1.c
			case Thermal_Comfort_Global_Values:
				innerSet.put(ResultCodes.Room_Air_Temperature, "Zones_AirTemperature.d6o");
				innerSet.put(ResultCodes.Set_Point_Room_Temperature_Heating, "Zones_HeatingSetPointTemperature.d6o");
				innerSet.put(ResultCodes.Set_Point_Room_Temperature_Cooling, "Zones_CoolingSetPointTemperature.d6o");
				innerSet.put(ResultCodes.Outdoor_air_temperature, "Location_Temperature.d6o");
				break;
//			eKPI 6.1
			case Investment_Costs:
				break;
//			eKPI6.2
			case Energy_Related_Operational_Costs:
				innerSet.put(ResultCodes.Heating, "Zones_HeatingGains.d6o");
//				innerSet.put(code, "Zones_HeatingConvectiveGains_objectList(Active_zones).d6o");
				innerSet.put(ResultCodes.Cooling, "Zones_CoolingGains.d6o");
//				innerSet.put(code, "Zones_CoolingGains_objectList(Active_zones).d6o");
				break;
//			eKPI6.3
			case Non_Energy_Related_Operational_Costs:
				break;
//			eKPI6
			case Lifecycle_Costs:
				innerSet.put(ResultCodes.Heating, "Zones_HeatingGains.d6o");
//				innerSet.put(code, "Zones_HeatingConvectiveGains_objectList(Active_zones).d6o");
				innerSet.put(ResultCodes.Cooling, "Zones_CoolingGains.d6o");
//				innerSet.put(code, "Zones_CoolingGains_objectList(Active_zones).d6o");
				break;
			default: break;
			} // end of switch
		return innerSet;
	}
	
	private File searchFile(List<File> files, String pattern) {
		for(File file : files) {
			String fileName = file.getName();
			if(fileName.equals(pattern)) {
				return file;
			}
		}
		return null;
	}
	
	/**
	 * Searching the simulation result files for NANDRAD and Therakles equally.
	 * @param simulationInformation
	 * @return
	 * @throws SimulationException
	 */
	private List<File> getResultFiles(Integer simulationId) throws SimulationException {
		SimulationProject simulationInformation = userService.getSimulationProjectById(simulationId);
		if(energySimulationService.checkSimulationStatus(simulationInformation) != STATUS.COMPLETED) return null;		
		
		userService.updateSimulationProject(simulationInformation);
		
		List<File> files = new ArrayList<File>();
		String resultDirPath = simulationInformation.getPathOutputDirectory();
		File resultDir = new File(resultDirPath);
		LOG.info("Simulation results are saved in the paths: {}",
				new Object[]{resultDir});;
		for(File resultLocation : resultDir.listFiles()) {
			if(resultLocation.isDirectory()){
				files.addAll(Arrays.asList(resultLocation.listFiles()));
			} else {
				files.add(resultLocation);
			}
		}
		return files;
	}
	
	@Override
	public List<MeasuredUnit> getTheraklesMeasuredUnits(List<String> locationIds, Set<ResultCodes> requestedResultTypes, 
			List<File> energyResults, int startYear, Integer unitTime, String timeMeasure) {
		List<MeasuredUnit> units = new ArrayList<MeasuredUnit>();

		for(File resultFile : energyResults) {					
			LOG.debug("THERAKLES RESULT FILE path "+resultFile.getAbsolutePath());
			String fileName = resultFile.getName();

			int underscore = fileName.indexOf("_")+1;
			int dotXmlTxt = fileName.indexOf(".xml.txt");
			String roomId = fileName.substring(underscore, dotXmlTxt);
			if(locationIds.contains(roomId)) {
				locationIds.remove(roomId);
				for(ResultCodes code : requestedResultTypes) {
					TheraklesEnergyResults theraklesEnergyResults = new TheraklesEnergyResults(resultFile);
					List<Number> values = new ArrayList<Number>();
					LOG.info("Searching for values of code ('"+code.getCode()+"')");
					switch(code) {
					case Room_Air_Temperature:
						values = theraklesEnergyResults.getResults(TheraklesEnergyResults.ENERGYVALUES.AMBIENT_TEMPERATURE);
						break;
					case Short_wave_global_radiation:
						values = theraklesEnergyResults.getResults(TheraklesEnergyResults.ENERGYVALUES.GLOBAL_RADIATION);
						break;
					case Operative_Room_Temperature: // Operative Room Temperature
						values = theraklesEnergyResults.getResults(TheraklesEnergyResults.ENERGYVALUES.OPERATIVE_TEMPERATURE);
						break;
					case Operative_Room_Temperature_Min: // Operative Room Temperature - min
						values = theraklesEnergyResults.getResults(TheraklesEnergyResults.ENERGYVALUES.OPERATIVE_TEMPERATURE);
						break;
					case Operative_Room_Temperature_Max: // Operative Room Temperature - max
						values = theraklesEnergyResults.getResults(TheraklesEnergyResults.ENERGYVALUES.OPERATIVE_TEMPERATURE);
						break;
					case Azimuth:
						break;
					case Cooling:
						break;
					case Elevation:
						break;
					case External_heat_gainloss_solar_radiation:
						break;
					case Heating:
						break;
					case Outdoor_air_relative_humidity:
						break;
					case Outdoor_air_temperature:
						break;
					case Set_Point_Room_Temperature_Cooling:
						break;
					case Set_Point_Room_Temperature_Heating:
						break;
					case Short_wave_diffuse_radiation:
						break;
					case Short_wave_direct_radiation:
						break;
					default: break;
					}

					MeasuredUnit measuredUnit = new MeasuredUnit(roomId);
					measuredUnit.setQuantity(code.getQuantity());
					measuredUnit.setResultCode(code.getCode());
					measuredUnit.setStartYear(startYear);
					measuredUnit.setTimeUnit(TimeMeasure.HOUR.name());
					measuredUnit.setValueUnit(code.getValueUnit());
					measuredUnit.setLabel(code.getLabel());
					measuredUnit.setAggregated(false);
					for(int i = 0; i < values.size(); i++) {
//						only unit time steps
						if(i % unitTime == 0) {
							Double number = (Double) values.get(i);
							measuredUnit.addValue(""+i, ""+number);
						}
					}
					units.add(measuredUnit);
				}
			}
		}
		return units;
	}
	
	@Override
	public void saveEnergySimulationResults(Integer simulationId, Set<ResultCodes> codes) throws SimulationException {
		final int startYear = 2013;		
		
		List<File> energyResults = getResultFiles(simulationId);
		
		Map<ResultCodes,List<MeasuredUnit>> units = getNandradMeasuredUnits(codes, energyResults, startYear);
		if(units == null) throw new IllegalArgumentException("No results for simulation: "+simulationId);

		for(List<MeasuredUnit> resUnits : units.values()){
			for(MeasuredUnit unit : resUnits) {
				TreeMap<Long, Double> values = unit.getValues();
				EnergyResults result = new EnergyResults();
				result.setSimulationId(simulationId);
				result.setTimeToValue(values);
				energyResultsDao.insertEnergyResults(result);
			}
		}
	}	
	
	@Override
	public List<MeasuredUnit> doPostProcessing(
			SimulationProject simulation,
			Map<ResultCodes, List<MeasuredUnit>> rawUnits, 
			List<String> requestedLocationIds,
			Date startDate,
			Date endDate,
			Integer unitTime,
			TimeMeasure timeMeasure, 
			DetailLevel detailLevel,
			Ifc2x3DataModelProxy ifcModel) throws PostProcessingException {
		if(rawUnits == null) return null;
		List<MeasuredUnit> result = new ArrayList<MeasuredUnit>();
		if(rawUnits.size() == 0) return result;
		LOG.debug("Do post-processing for locations: {}, start time: {}, end time: {}, unit time: {}, time measure: {} and detail level: {}", 
				new Object[]{requestedLocationIds, startDate, endDate, unitTime, timeMeasure, detailLevel});
		for(Map.Entry<ResultCodes, List<MeasuredUnit>> entry : rawUnits.entrySet()) {
			ResultCodes code 			= entry.getKey();
			List<MeasuredUnit> values 	= entry.getValue();
			
//			(1) check if requested code is an eKPI or not because this affects the detail level and time measure
			if(isEKPI(code)) {
				detailLevel = DetailLevel.SEPARATED;
				switch(code) {
				case Greenhouse_Gas_Emissions:
					timeMeasure = TimeMeasure.HOUR;
					break;
				case Thermal_Comfort_Global_Values:
					timeMeasure = TimeMeasure.HOUR;
					break;
				case Lifecycle_Costs:
					timeMeasure = TimeMeasure.HOUR;
					break;
				default: break;
				}
			}
			
//			(2) post-processing dependent on time constraints
			Set<MeasuredUnit> unitsDependentOnTime = doPostProcessingForTime(values, startDate, endDate, unitTime, timeMeasure);				

//			(3) post-processing dependent on detail level => filter dependent to requested locations
			Map<String, Set<MeasuredUnit>> unitsDependentOnDetailLevel = doPostProcessingForDetailLevel(unitsDependentOnTime, detailLevel, requestedLocationIds, ifcModel);
			
//			(4) 
			Map<String, Set<MeasuredUnit>> filteredUnits = filterRequestsWithNoResultUnits(unitsDependentOnDetailLevel);
			
//			(5) post-processing for requested eKPI
			Map<String, List<MeasuredUnit>> eKPIUnits = doPostProcessingForEnergyKeyPerformanceIndicators(simulation, code, filteredUnits, ifcModel);
			for(List<MeasuredUnit> units : eKPIUnits.values()) {
				result.addAll(units);
			}
		}
		LOG.debug("Result of post-processing: {}", new Object[]{result});
		return result;
	}
	
	/**
	 * Calculates the building shell area by using the space boundary areas (added by BSPro). 
	 * 
	 * <p>
	 * <b>Note:</b> This method can only be used for facade elements
	 * because in inner elements the building element area can be used twice because one room is connected to a building 
	 * element by the space boundary while the neighbouring room is also linked to the same building element on the other
	 * side but the wall area between them can only be added once!
	 * </p>
	 * @deprecated Use after HESMOS the same method of Ifc2x3DataModelProxy
	 * @param buildingGuid The GlobalId of the building.
	 * @param ifcModel The IFC model.
	 * @return The overall building shell area value.
	 * @throws IfcException
	 */
	@Deprecated
	private Double calculateBuildingShellArea(String buildingGuid, Ifc2x3DataModelProxy ifcModel) throws IfcException {
		double buildingShellArea = 0.0;
		// Set with GlobalIds to prohibit that one GlobalId is used multiple times
		Set<String> cachedGuids = new HashSet<String>();
		try{
			EIfcspace[] spaces = ifcModel.getSpacesOfBuilding(buildingGuid);
			for(EIfcspace space : spaces) {						
				EIfcrelspaceboundary[] spaceBoundaries = ifcModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
				for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
					if(cachedGuids.add(spaceBoundary.getGlobalid(spaceBoundary))) {
						//	non-energy-related costs for external elements
						int internalOrExternal = spaceBoundary.getInternalorexternalboundary(spaceBoundary);
						if(internalOrExternal == EIfcinternalorexternalenum.EXTERNAL) { // facade element: window or other concrete element
							EIfcelement element = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
							if(element instanceof EIfcbuildingelement) {
								EIfcbuildingelement be = (EIfcbuildingelement) element;
	
								if(!(be instanceof EIfcwindow)) {									
									// TODO REMOVE AFTER HESMOS - FOLLOWING IS FROM THE E-MAIL OF Marie Geiﬂler on 14.01.2014
									if(be.testName(be)) {
										String templateName = be.getName(be);
										if(!(templateName.equalsIgnoreCase("OW_TICS")
												|| templateName.equalsIgnoreCase("OW_steel cover")
												|| templateName.equalsIgnoreCase("OW_sunprotection case")
												|| templateName.equalsIgnoreCase("OW_reinforced concrete"))) {
											continue;
										}
									}
									double area	= 0.0;
									if(spaceBoundary.testDescription(spaceBoundary)) {
										String description = spaceBoundary.getDescription(spaceBoundary);								
										if(description != null) {
											description = description.replace(description.substring(0, description.indexOf(AREA_PATTERN)),"");
											String areaText = description.replaceFirst(AREA_PATTERN, "");
											if(areaText != null) {
												area = Double.valueOf(areaText);
											}
										}
									}
									buildingShellArea += area;
								}
							}
						}
					}
				}
			}
//			round building shell area
			buildingShellArea = Math.round(buildingShellArea*100)/100;
		} catch(SdaiException se) {
			throw new IfcException(se);
		}
		return buildingShellArea;
	}
	
	/**
	 * Calculates the (outer) window area by using the space boundary areas (added by BSPro).
	 * <p>
	 * <b>Note:</b> This method cannot be used if it is requested to calculate the inner windows.
	 * </p>
	 * @deprecated Use after HESMOS the same method of Ifc2x3DataModelProxy
	 * @param buildingGuid The GlobalId of the building.
	 * @param ifcModel The IFC model.
	 * @return The overall window area value.
	 * @throws IfcException
	 */
	@Deprecated
	private Double calculateWindowArea(String buildingGuid, Ifc2x3DataModelProxy ifcModel) throws IfcException {		
		double windowArea = 0.0;
		// Set with GlobalIds to prohibit that one GlobalId is used multiple times
		Set<String> cachedGuids = new HashSet<String>();
		try {
			EIfcspace[] spaces = ifcModel.getSpacesOfBuilding(buildingGuid);
			for(EIfcspace space : spaces) {						
				EIfcrelspaceboundary[] spaceBoundaries = ifcModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
				for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
					if(cachedGuids.add(spaceBoundary.getGlobalid(spaceBoundary))) {
						//	non-energy-related costs for external elements
						int internalOrExternal = spaceBoundary.getInternalorexternalboundary(spaceBoundary);
						if(internalOrExternal == EIfcinternalorexternalenum.EXTERNAL) { // facade element: window or other concrete element
							EIfcelement element = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
							if(element instanceof EIfcwindow) {
								double area	= 0.0;
								if(spaceBoundary.testDescription(spaceBoundary)) {
									String description = spaceBoundary.getDescription(spaceBoundary);								
									if(description != null) {
										description = description.replace(description.substring(0, description.indexOf(AREA_PATTERN)),"");
										String areaText = description.replaceFirst(AREA_PATTERN, "");
										if(areaText != null) {
											area = Double.valueOf(areaText);
										}
									}
								}
								windowArea += area;
							}
						}
					}
				}
			}
			// round window area
			windowArea = Math.round(windowArea*100)/100;
		} catch (SdaiException se) {
			throw new IfcException(se);
		}
		return windowArea;
	}
	
	 @Override
	public EnergyKeyPerformanceIndicators createEnergyKeyPerformanceIndicator(
			OntologyModel eeBim, Ifc2x3DataModelProxy ifcModel,
			List<? extends ConstructionTemplate> constructionTemplates,
			Set<String> locationIds, Integer simInfoId) throws IfcException {
		double buildingShellArea = 0.0;
		double windowArea = 0.0;
		double investmentCosts = 0.0;
		
		List<String> buildingGuids = new ArrayList<String>();
		for(String guid : locationIds) {
			EIfcbuilding b = ifcModel.getIfcEntity(guid, EIfcbuilding.class);
			if(b != null) buildingGuids.add(guid);
		}
		
		EnergyKeyPerformanceIndicators eKPIs = new EnergyKeyPerformanceIndicators();
		eKPIs.setSimulationId(simInfoId);
		if(buildingGuids.size() > 0) {
			for(String buildingGuid : buildingGuids) {
				double bArea = calculateBuildingShellArea(buildingGuid, ifcModel);
				double wArea = calculateWindowArea(buildingGuid, ifcModel);
				
				LOG.debug("Building shell area: {} and window area: {} of building: {}",
						new Object[]{bArea, wArea, buildingGuid});
				
				buildingShellArea += bArea;
				windowArea += wArea;
				
				
				LOG.debug("Calculating investment costs...");
				try {
					double costs = calculateInvestmentCosts(buildingGuid, ifcModel, eeBim, constructionTemplates);
					LOG.debug("Calculated investment costs of building: {} are: {}",
							new Object[]{buildingGuid, costs});
					investmentCosts += costs;
				} catch(PostProcessingException ppe) {
					LOG.info("It was not possible to calculate the investment costs of building: {} with construction templates: {}",
							new Object[]{buildingGuid, constructionTemplates});
				}
			}
			eKPIs.setBuildingShellArea(buildingShellArea);
			eKPIs.setWindowArea(windowArea);
			eKPIs.setInvestmentCosts(investmentCosts);		
		}
		return eKPIs;
	}

	@Override
	public Map<String, Set<MeasuredUnit>> filterRequestsWithNoResultUnits(
			Map<String, Set<MeasuredUnit>> unitsDependentOnDetailLevel) {
		Map<String, Set<MeasuredUnit>> result = new HashMap<String, Set<MeasuredUnit>>();
		for(Map.Entry<String, Set<MeasuredUnit>> entry : unitsDependentOnDetailLevel.entrySet()) {
			String key 				= entry.getKey();
			Set<MeasuredUnit> value = entry.getValue();
			if(!(value == null || value.size() == 0)) {
				result.put(key, value);
			} else {
				LOG.debug("Filter results that have no values, key: {} and values: {}",
						new Object[]{key, value});
			}
		}
		return result;
	}


//	@Override
//	public Map<String, Set<MeasuredUnit>> doPostProcessingForEnergyKeyPerformanceIndicators(
//			SimulationInformation simulation,
//			ResultCodes code, Map<String, Set<MeasuredUnit>> rawUnits, 
//			Ifc2x3DataModelProxy ifcModel) throws PostProcessingException {	
//		LOG.debug("Do eKPI dependent post-processing for code: {} of raw units: {}",new Object[]{code, rawUnits});
//		Map<String, Set<MeasuredUnit>> result = new HashMap<String, Set<MeasuredUnit>>();
//		
//		EnergyKeyPerformanceIndicators eKPIs = getEKPIBySimulation(simulation);
//		if(eKPIs == null) {
//			eKPIs = new EnergyKeyPerformanceIndicators();
//			eKPIs.setSimulationId(simulation.getId());
//		}
//		
//		for(Map.Entry<String, Set<MeasuredUnit>> entry : rawUnits.entrySet()) {
//			String requestedLocationId = entry.getKey();
//			Set<MeasuredUnit> unitsForRequestedLocationId = entry.getValue();
//			
//			Set<MeasuredUnit> calculatedUnits = new HashSet<MeasuredUnit>();
//			try {
//				EIfcbuilding building = null;
//				Set<String> zoneIds = null;
//				switch(code) {
////				eKPI1.1a
//				case Heating_Net_Energy:
//					MeasuredUnit heatingNetEnergy = PostProcessingEngine.aggregateUnits(new ArrayList<MeasuredUnit>(unitsForRequestedLocationId));
//					heatingNetEnergy.setId(requestedLocationId);
//					heatingNetEnergy.setAggregated(true);
//					heatingNetEnergy.setLabel(code.getLabel());
//					heatingNetEnergy.setQuantity(code.getQuantity());
//					heatingNetEnergy.setResultCode(code.getCode());
//					heatingNetEnergy.setValueUnit(code.getValueUnit());
//					calculatedUnits.add(heatingNetEnergy);
//					break;
//				case Heating_Final_Energy:
////					check if the requested ID is a Building GlobalID
//					building = ifcModel.getIfcEntity(requestedLocationId, EIfcbuilding.class);
//					if(building == null) throw new PostProcessingException("The requested ID: "+requestedLocationId+" is no building");
//					zoneIds = ifcModel.getSpacesOfSpatialStructure(requestedLocationId);
//					List<MeasuredUnit> heatingFinalEnergyUnits = new ArrayList<MeasuredUnit>();
//					for(MeasuredUnit mu : unitsForRequestedLocationId) {
//						if(zoneIds.contains(mu.getId())) {
//							heatingFinalEnergyUnits.add(mu);
//						}
//					}
//					MeasuredUnit heatingFinalEnergy = PostProcessingEngine.aggregateUnits(heatingFinalEnergyUnits);
//					double finalEnergyHeating = EnergyKeyPerformanceIndicatorCalculator.calculateHeatingFinalEnergy(heatingFinalEnergy, eKPIs);
//					Map<String,List<String>> finalHeatingEKPIMap = new HashMap<String, List<String>>();
//					finalHeatingEKPIMap.put(ResultCodes.Heating_Final_Energy.getLabel(), Arrays.asList(""+finalEnergyHeating));
//					heatingFinalEnergy.setId(requestedLocationId);
//					heatingFinalEnergy.setKeyPerformanceIndicator(finalHeatingEKPIMap);
//					heatingFinalEnergy.setAggregated(true);
//					heatingFinalEnergy.setLabel(code.getLabel());
//					heatingFinalEnergy.setQuantity(code.getQuantity());
//					heatingFinalEnergy.setResultCode(code.getCode());
//					heatingFinalEnergy.setValueUnit(code.getValueUnit());
//					calculatedUnits.add(heatingFinalEnergy);
//					
//					eKPIs.setFinalEnergyHeating(finalEnergyHeating);
//					break;
////				eKPI1.2
//				case Cooling_Net_Energy:
//					MeasuredUnit coolingNetEnergy = PostProcessingEngine.aggregateUnits(new ArrayList<MeasuredUnit>(unitsForRequestedLocationId));
//					coolingNetEnergy.setId(requestedLocationId);
//					coolingNetEnergy.setAggregated(true);
//					coolingNetEnergy.setLabel(code.getLabel());
//					coolingNetEnergy.setQuantity(code.getQuantity());
//					coolingNetEnergy.setResultCode(code.getCode());
//					coolingNetEnergy.setValueUnit(code.getValueUnit());
//					calculatedUnits.add(coolingNetEnergy);
//					break;
//				case Cooling_Final_Energy:
////					check if the requested ID is a Building GlobalID
//					building = ifcModel.getIfcEntity(requestedLocationId, EIfcbuilding.class);
//					if(building == null) throw new PostProcessingException("The requested ID: "+requestedLocationId+" is no building");
//				
//					zoneIds = ifcModel.getSpacesOfSpatialStructure(requestedLocationId);
//					List<MeasuredUnit> coolingFinalEnergyUnits = new ArrayList<MeasuredUnit>();
//					for(MeasuredUnit mu : unitsForRequestedLocationId) {
//						if(zoneIds.contains(mu.getId())) {
//							coolingFinalEnergyUnits.add(mu);
//						}
//					}
//					MeasuredUnit coolingFinalEnergy = PostProcessingEngine.aggregateUnits(coolingFinalEnergyUnits);
//					double finalEnergyCooling = EnergyKeyPerformanceIndicatorCalculator.calculateCoolingFinalEnergy(coolingFinalEnergy, eKPIs);
//					Map<String,List<String>> finalCoolingEKPIMap = new HashMap<String, List<String>>();
//					finalCoolingEKPIMap.put(ResultCodes.Cooling_Final_Energy.getLabel(), Arrays.asList(""+finalEnergyCooling));
//					coolingFinalEnergy.setKeyPerformanceIndicator(finalCoolingEKPIMap);
//					coolingFinalEnergy.setId(requestedLocationId);
//					coolingFinalEnergy.setAggregated(true);
//					coolingFinalEnergy.setLabel(code.getLabel());
//					coolingFinalEnergy.setQuantity(code.getQuantity());
//					coolingFinalEnergy.setResultCode(code.getCode());
//					coolingFinalEnergy.setValueUnit(code.getValueUnit());
//					calculatedUnits.add(coolingFinalEnergy);
//					
//					eKPIs.setFinalEnergyCooling(finalEnergyCooling);
//					break;
//				case Heating_And_Cooling_Net_Energy:
//					List<MeasuredUnit> coolingNEUnits = new ArrayList<MeasuredUnit>();
//					List<MeasuredUnit> heatingNEUnits = new ArrayList<MeasuredUnit>();
//					
//					for(MeasuredUnit mu : unitsForRequestedLocationId) {
//						if(mu.getResultCode()==ResultCodes.Heating.getCode()) {
//							coolingNEUnits.add(mu);
//						} else if(mu.getResultCode()==ResultCodes.Cooling.getCode()) {
//							heatingNEUnits.add(mu);
//						}
//					}
//					MeasuredUnit coolingNE = PostProcessingEngine.aggregateUnits(coolingNEUnits);
//					coolingNE.setId(requestedLocationId);
//					coolingNE.setAggregated(true);
//					coolingNE.setLabel(ResultCodes.Cooling_Net_Energy.getLabel());
//					coolingNE.setQuantity(ResultCodes.Cooling_Net_Energy.getQuantity());
//					coolingNE.setResultCode(ResultCodes.Cooling_Net_Energy.getCode());
//					coolingNE.setValueUnit(ResultCodes.Cooling_Net_Energy.getValueUnit());
//					calculatedUnits.add(coolingNE);
//					MeasuredUnit heatingNE = PostProcessingEngine.aggregateUnits(heatingNEUnits);
//					heatingNE.setId(requestedLocationId);
//					heatingNE.setAggregated(true);
//					heatingNE.setLabel(ResultCodes.Heating_Net_Energy.getLabel());
//					heatingNE.setQuantity(ResultCodes.Heating_Net_Energy.getQuantity());
//					heatingNE.setResultCode(ResultCodes.Heating_Net_Energy.getCode());
//					heatingNE.setValueUnit(ResultCodes.Heating_Net_Energy.getValueUnit());
//					calculatedUnits.add(heatingNE);
//					break;
//				case Heating_And_Cooling_Final_Energy:
////					check if the requested ID is a Building GlobalID
//					building = ifcModel.getIfcEntity(requestedLocationId, EIfcbuilding.class);
//					if(building == null) throw new PostProcessingException("The requested ID: "+requestedLocationId+" is no building");
//				
//					zoneIds = ifcModel.getSpacesOfSpatialStructure(requestedLocationId);
//					List<MeasuredUnit> coolingFEUnits = new ArrayList<MeasuredUnit>();
//					List<MeasuredUnit> heatingFEUnits = new ArrayList<MeasuredUnit>();
//					
//					for(MeasuredUnit mu : unitsForRequestedLocationId) {
//						if(zoneIds.contains(mu.getId())) {
//							if(mu.getResultCode()==ResultCodes.Heating.getCode()) {
//								heatingFEUnits.add(mu);
//							} else if(mu.getResultCode()==ResultCodes.Cooling.getCode()) {
//								coolingFEUnits.add(mu);
//							}
//						}
//					}
//					MeasuredUnit coolingFE = PostProcessingEngine.aggregateUnits(coolingFEUnits);
//					coolingFE.setId(requestedLocationId);
//					coolingFE.setAggregated(true);
//					coolingFE.setLabel(ResultCodes.Cooling_Final_Energy.getLabel());
//					coolingFE.setQuantity(ResultCodes.Cooling_Final_Energy.getQuantity());
//					coolingFE.setResultCode(ResultCodes.Cooling_Final_Energy.getCode());
//					coolingFE.setValueUnit(ResultCodes.Cooling_Final_Energy.getValueUnit());
//					
//					MeasuredUnit heatingFE = PostProcessingEngine.aggregateUnits(heatingFEUnits);
//					heatingFE.setId(requestedLocationId);
//					heatingFE.setAggregated(true);
//					heatingFE.setLabel(ResultCodes.Heating_Final_Energy.getLabel());
//					heatingFE.setQuantity(ResultCodes.Heating_Final_Energy.getQuantity());
//					heatingFE.setResultCode(ResultCodes.Heating_Final_Energy.getCode());
//					heatingFE.setValueUnit(ResultCodes.Heating_Final_Energy.getValueUnit());
//					
//					finalEnergyHeating = EnergyKeyPerformanceIndicatorCalculator.calculateHeatingFinalEnergy(heatingFE, eKPIs);
//					Map<String,List<String>> finalEnergyEKPIMap = new HashMap<String, List<String>>();
//					finalEnergyEKPIMap.put(ResultCodes.Heating_Final_Energy.getLabel(), Arrays.asList(""+finalEnergyHeating));
//					finalEnergyCooling = EnergyKeyPerformanceIndicatorCalculator.calculateCoolingFinalEnergy(coolingFE, eKPIs);
//					finalEnergyEKPIMap.put(ResultCodes.Cooling_Final_Energy.getLabel(), Arrays.asList(""+finalEnergyCooling));
//					
//					heatingFE.setKeyPerformanceIndicator(finalEnergyEKPIMap);
//					coolingFE.setKeyPerformanceIndicator(finalEnergyEKPIMap);
//					
//					calculatedUnits.add(heatingFE);
//					calculatedUnits.add(coolingFE);
//					
//					eKPIs.setFinalEnergyHeating(finalEnergyHeating);
//					eKPIs.setFinalEnergyCooling(finalEnergyCooling);
//					break;
////				eKPI2.1
//				case Greenhouse_Gas_Emissions:				
//					List<Combustible> combustibles = listCombustibles(simulation.getUserId());
//					Map<EnergyResourceType, Double> greenhouseGasEmissions = EnergyKeyPerformanceIndicatorCalculator.calculateEcologicGreenhouseGasEmissionValue(eKPIs, combustibles);
//					
////					Table: 
////						CO2 | SO2 | NOX
////						234 | 945 | 0.4
//					Map<String, List<String>> eKPI = new HashMap<String, List<String>>();
//					eKPI.put(EnergyResourceType.CO2.name(), Arrays.asList(""+greenhouseGasEmissions.get(EnergyResourceType.CO2)));
//					eKPI.put(EnergyResourceType.SO2.name(), Arrays.asList(""+greenhouseGasEmissions.get(EnergyResourceType.SO2)));
//					eKPI.put(EnergyResourceType.NOX.name(), Arrays.asList(""+greenhouseGasEmissions.get(EnergyResourceType.NOX)));
//					
//					MeasuredUnit greenhouseGasEmissionsUnit = new MeasuredUnit(requestedLocationId);
//					greenhouseGasEmissionsUnit.setAggregated(true);
//					greenhouseGasEmissionsUnit.setKeyPerformanceIndicator(eKPI);
//					greenhouseGasEmissionsUnit.setLabel(ResultCodes.Greenhouse_Gas_Emissions.getLabel());
//					greenhouseGasEmissionsUnit.setQuantity(ResultCodes.Greenhouse_Gas_Emissions.getQuantity());
//					greenhouseGasEmissionsUnit.setResultCode(ResultCodes.Greenhouse_Gas_Emissions.getCode());
//					greenhouseGasEmissionsUnit.setValueUnit(ResultCodes.Greenhouse_Gas_Emissions.getValueUnit());
//					calculatedUnits.add(greenhouseGasEmissionsUnit);
//					
//					eKPIs.setGreenhouseGasEmissions(greenhouseGasEmissions);
//					break;
////				eKPI3.1.a
//				case Temperature_Over_Under_Runs:
////					only rooms can be requested
//					EIfcspace space = ifcModel.getIfcEntity(requestedLocationId, EIfcspace.class);
//					if(space == null) throw new PostProcessingException("Global: "+requestedLocationId+" is no room");
//					Map<String, MeasuredUnit> temperatureUnits = new TreeMap<String, MeasuredUnit>();
//					Map<String, MeasuredUnit> heatingSetpointUnits = new TreeMap<String, MeasuredUnit>();
//					Map<String, MeasuredUnit> coolingSetpointUnits = new TreeMap<String, MeasuredUnit>();
//					List<MeasuredUnit> outdoorTemperatureUnits = new ArrayList<MeasuredUnit>();
//					
//					for(MeasuredUnit mu : unitsForRequestedLocationId) {
//						String muId = mu.getId();
//						if(mu.getResultCode()==ResultCodes.Room_Air_Temperature.getCode()) {
//							temperatureUnits.put(muId,mu);
//						} else if(mu.getResultCode()==ResultCodes.Set_Point_Room_Temperature_Heating.getCode()) {
//							heatingSetpointUnits.put(muId,mu);
//						} else if(mu.getResultCode()==ResultCodes.Set_Point_Room_Temperature_Cooling.getCode()) {
//							coolingSetpointUnits.put(muId,mu);
//						} else if(mu.getResultCode()==ResultCodes.Outdoor_air_temperature.getCode()) {
//							outdoorTemperatureUnits.add(mu);
//						}
//					}
////					try {				
//						for (Map.Entry<String, MeasuredUnit> opEntry : temperatureUnits.entrySet()) {
//							String key = opEntry.getKey();
//
//							MeasuredUnit temperature = opEntry.getValue();
//							MeasuredUnit heatingsetPoint = heatingSetpointUnits.get(key);
//							MeasuredUnit coolingsetPoint = coolingSetpointUnits.get(key);
//							MeasuredUnit outdoorTemperature = outdoorTemperatureUnits.get(0);
////							temperatures have to be available
//							if(temperature == null) throw new AssertionError();
//							
////							MeasuredUnit overrun = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureOverrun(operativeTemperature, coolingsetPoint);
////							MeasuredUnit underrun = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureUnderrun(operativeTemperature, heatingsetPoint);
////							calculatedUnits.add(overrun);
////							calculatedUnits.add(underrun);
//							calculatedUnits.add(heatingsetPoint);
//							calculatedUnits.add(coolingsetPoint);
//							calculatedUnits.add(temperature);
//							calculatedUnits.add(outdoorTemperature);
//							
////							NOTE: because only one room can be requested with eKPI3.1 we return immediately the result
////							this map iteration and the one for all eKPIs will be stopped
//							result.put(requestedLocationId, calculatedUnits);
//							return result;
//						}					
////					} catch(EnergyKeyPerformanceIndicatorException eKPIEx) {
////						throw new PostProcessingException(eKPIEx);
////					}
//					
//					break;
////				eKPI3.1.b
//				case Thermal_Comfort:
////					only rooms can be requested
//					space = ifcModel.getIfcEntity(requestedLocationId, EIfcspace.class);
//					if(space == null) throw new PostProcessingException("Global: "+requestedLocationId+" is no room");
//					temperatureUnits = new TreeMap<String, MeasuredUnit>();
//					heatingSetpointUnits = new TreeMap<String, MeasuredUnit>();
//					coolingSetpointUnits = new TreeMap<String, MeasuredUnit>();
//					outdoorTemperatureUnits = new ArrayList<MeasuredUnit>();
//					
//					temperatureUnits = new TreeMap<String, MeasuredUnit>();
//					heatingSetpointUnits = new TreeMap<String, MeasuredUnit>();
//					coolingSetpointUnits = new TreeMap<String, MeasuredUnit>();
//					
//					for(MeasuredUnit mu : unitsForRequestedLocationId) {
//						String muId = mu.getId();
//						int rc = mu.getResultCode();
//						if(rc==ResultCodes.Room_Air_Temperature.getCode()) {
//							temperatureUnits.put(muId,mu);
//						} else if(rc==ResultCodes.Set_Point_Room_Temperature_Heating.getCode()) {
//							heatingSetpointUnits.put(muId,mu);
//						} else if(rc==ResultCodes.Set_Point_Room_Temperature_Cooling.getCode()) {
//							coolingSetpointUnits.put(muId,mu);
//						}
//					}
//					try {				
//						for (Map.Entry<String, MeasuredUnit> opEntry : temperatureUnits.entrySet()) {
//							String key = opEntry.getKey();
//
//							MeasuredUnit temperature = opEntry.getValue();
//							MeasuredUnit heatingsetPoint = heatingSetpointUnits.get(key);
//							MeasuredUnit coolingsetPoint = coolingSetpointUnits.get(key);
////							temperatures have to be available
//							if(temperature == null) throw new AssertionError();
//							
//							MeasuredUnit overrun = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureOverrun(temperature, coolingsetPoint);
//							MeasuredUnit underrun = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureUnderrun(temperature, heatingsetPoint);
//							calculatedUnits.add(overrun);
//							calculatedUnits.add(underrun);
////							calculatedUnits.add(operativeTemperature);
//							
////							NOTE: because only one room can be requested with eKPI3.1 we return immediately the result
////							this map iteration and the one for all eKPIs will be stopped
//							result.put(requestedLocationId, calculatedUnits);
//							return result;
//						}					
//					} catch(EnergyKeyPerformanceIndicatorException eKPIEx) {
//						throw new PostProcessingException(eKPIEx);
//					}
//
//					break;
////				eKPI3.1.c
//				case Thermal_Comfort_Global_Values:
////					only rooms can be requested
//					space = ifcModel.getIfcEntity(requestedLocationId, EIfcspace.class);
//					if(space == null) throw new PostProcessingException("Global: "+requestedLocationId+" is no room");
//					temperatureUnits = new TreeMap<String, MeasuredUnit>();
//					heatingSetpointUnits = new TreeMap<String, MeasuredUnit>();
//					coolingSetpointUnits = new TreeMap<String, MeasuredUnit>();
//					outdoorTemperatureUnits = new ArrayList<MeasuredUnit>();
//					
//					temperatureUnits = new TreeMap<String, MeasuredUnit>();
//					heatingSetpointUnits = new TreeMap<String, MeasuredUnit>();
//					coolingSetpointUnits = new TreeMap<String, MeasuredUnit>();
//					
//					for(MeasuredUnit mu : unitsForRequestedLocationId) {
//						String muId = mu.getId();
//						int rc = mu.getResultCode();
//						if(rc==ResultCodes.Room_Air_Temperature.getCode()) {
//							temperatureUnits.put(muId,mu);
//						} else if(rc==ResultCodes.Set_Point_Room_Temperature_Heating.getCode()) {
//							heatingSetpointUnits.put(muId,mu);
//						} else if(rc==ResultCodes.Set_Point_Room_Temperature_Cooling.getCode()) {
//							coolingSetpointUnits.put(muId,mu);
//						}
//					}
//					try {				
//						for (Map.Entry<String, MeasuredUnit> opEntry : temperatureUnits.entrySet()) {
//							String key = opEntry.getKey();
//
//							MeasuredUnit temperature = opEntry.getValue();
//							MeasuredUnit heatingsetPoint = heatingSetpointUnits.get(key);
//							MeasuredUnit coolingsetPoint = coolingSetpointUnits.get(key);
////							temperatures have to be available
//							if(temperature == null) throw new AssertionError();
//							
//							TreeMap<Integer,Integer> overrunStagged = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureOverrunCountStaggered(temperature, coolingsetPoint);
//							TreeMap<Integer,Integer> underrunStagged = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureUnderrunCountStaggered(temperature, heatingsetPoint);
//							
//							List<String> distances = new ArrayList<String>();
//							List<String> overrunText = new ArrayList<String>();
//							for(Map.Entry<Integer, Integer> entryOver : overrunStagged.entrySet()) {
//								String distance = String.valueOf(entryOver.getKey());
//								int overCount = entryOver.getValue();
//								overrunText.add(String.valueOf(overCount));
//								if(!distances.contains(distance)) {
//									distances.add(distance);
//								}
//							}
//							List<String> underrunText = new ArrayList<String>();
//							for(Map.Entry<Integer, Integer> entryUnder : underrunStagged.entrySet()) {
//								String distance = String.valueOf(entryUnder.getKey());
//								int underCount = entryUnder.getValue();
//								underrunText.add(String.valueOf(underCount));
//								if(!distances.contains(distance)) {
//									distances.add(distance);
//								}
//							}
//							
//							Map<String, List<String>> keyPerformanceIndicator = new LinkedHashMap<String, List<String>>();
//							keyPerformanceIndicator.put("Distances", distances);
//							keyPerformanceIndicator.put("OverrunCount", overrunText);
//							keyPerformanceIndicator.put("UnderrunCount", underrunText);
//							
//							MeasuredUnit thermalComfortGlobalUnit = new MeasuredUnit(requestedLocationId);
//							thermalComfortGlobalUnit.setAggregated(false);
//							thermalComfortGlobalUnit.setLabel(ResultCodes.Thermal_Comfort_Global_Values.getLabel());
//							thermalComfortGlobalUnit.setQuantity(ResultCodes.Thermal_Comfort_Global_Values.getQuantity());
//							thermalComfortGlobalUnit.setResultCode(ResultCodes.Thermal_Comfort_Global_Values.getCode());
//							thermalComfortGlobalUnit.setValueUnit(ResultCodes.Thermal_Comfort_Global_Values.getValueUnit());
//							thermalComfortGlobalUnit.setKeyPerformanceIndicator(keyPerformanceIndicator);
//							
//							calculatedUnits.add(thermalComfortGlobalUnit);
//							
////							NOTE: because only one room can be requested with eKPI3.1 we return immediately the result
////							this map iteration and the one for all eKPIs will be stopped
//							result.put(requestedLocationId, calculatedUnits);
//							return result;
//						}					
//					} catch(EnergyKeyPerformanceIndicatorException eKPIEx) {
//						throw new PostProcessingException(eKPIEx);
//					}
//					break;
////				case Investment_Costs:
//////					check if the requested ID is a Building GlobalID
////					building = ifcModel.getIfcEntity(requestedLocationId, EIfcbuilding.class);
////					if(building == null) throw new PostProcessingException("The requested ID: "+requestedLocationId+" is no building");
////					
////					// retrieve all building elements
////					
////					break;
////				case Energy_Related_Operational_Costs:
//////					check if the requested ID is a Building GlobalID
////					building = ifcModel.getIfcEntity(requestedLocationId, EIfcbuilding.class);
////					if(building == null) throw new PostProcessingException("The requested ID: "+requestedLocationId+" is no building");
////					
////					eKPIs = energyKeyPerformanceIndicatorDao.getEKPIBySimulationId(simulation.getId());				
////					combustibles = listCombustibles(simulation.getUserId());
////					double energyRelatedOperationalCosts = EnergyKeyPerformanceIndicatorCalculator.calculateEnergyRelatedOperationalCosts(combustibles, eKPIs);
////					Map<String, List<String>> kpiMap = new HashMap<String, List<String>>();
////					kpiMap.put(ResultCodes.Energy_Related_Operational_Costs.getLabel(), Arrays.asList(""+energyRelatedOperationalCosts));
////					MeasuredUnit costsUnit = new MeasuredUnit(requestedLocationId);
////					costsUnit.setAggregated(true);
////					costsUnit.setLabel(ResultCodes.Energy_Related_Operational_Costs.getLabel());
////					costsUnit.setQuantity(ResultCodes.Energy_Related_Operational_Costs.getQuantity());
////					costsUnit.setResultCode(ResultCodes.Energy_Related_Operational_Costs.getCode());
////					costsUnit.setValueUnit(ResultCodes.Energy_Related_Operational_Costs.getValueUnit());
////					costsUnit.setKeyPerformanceIndicator(kpiMap);
////					
////					calculatedUnits.add(costsUnit);
////					break;
////				case Lifecycle_Costs:
//////					check if the requested ID is a Building GlobalID
////					building = ifcModel.getIfcEntity(requestedLocationId, EIfcbuilding.class);
////					if(building == null) throw new PostProcessingException("The requested ID: "+requestedLocationId+" is no building");
////					break;
//				default: // no eKPI
//					return rawUnits;
//				}
//				result.put(requestedLocationId, calculatedUnits);
//			} catch(IfcException ie) {
//				throw new PostProcessingException(ie);
//			}
//		}
////		save it to the database
//		saveEnergyKeyPerformanceIndicators(eKPIs);
//		
//		return result;
//	}
	
	@Override
	public Map<String, List<MeasuredUnit>> doPostProcessingForEnergyKeyPerformanceIndicators(
			SimulationProject simulation,
			ResultCodes code, Map<String, Set<MeasuredUnit>> rawUnits, 
			Ifc2x3DataModelProxy ifcModel) throws PostProcessingException {	
		Map<String, List<MeasuredUnit>> result = new LinkedHashMap<String, List<MeasuredUnit>>();		
		
		EnergyKeyPerformanceIndicators eKPIs = getEKPIBySimulationId(simulation.getId());
		if(eKPIs == null) {
			eKPIs = new EnergyKeyPerformanceIndicators();
			eKPIs.setSimulationId(simulation.getId());
		}
		
		LOG.debug("Do eKPI dependent post-processing for code: {} with eKPI: {} of raw units: {}",
				new Object[]{code, eKPIs, rawUnits});
		
		List<MeasuredUnit> calculatedUnits = new ArrayList<MeasuredUnit>();
		List<MeasuredUnit> finalEnergy = null;
		switch(code) {
//			eKPI1.1a
			case Heating_Net_Energy:
				MeasuredUnit heatingNetEnergy = handleHeatingNetEnergy(rawUnits);
				if(heatingNetEnergy != null) calculatedUnits.add(heatingNetEnergy);
				
				result.put("-", calculatedUnits);
				break;
			case Heating_Final_Energy:
				Map.Entry<String, Set<MeasuredUnit>> entry = rawUnits.entrySet().iterator().next();
				MeasuredUnit heatingFinalEnergy = handleHeatingFinalEnergy(entry, eKPIs, ifcModel);
				if(heatingFinalEnergy != null) calculatedUnits.add(heatingFinalEnergy);
				
				result.put(entry.getKey(), calculatedUnits);
				break;
//			eKPI1.2
			case Cooling_Net_Energy:
				MeasuredUnit coolingNetEnergy = handleCoolingNetEnergy(rawUnits);
				if(coolingNetEnergy != null) calculatedUnits.add(coolingNetEnergy);
				
				result.put("-", calculatedUnits);
				break;
			case Cooling_Final_Energy:
				entry = rawUnits.entrySet().iterator().next();
				MeasuredUnit coolingFinalEnergy = handleCoolingFinalEnergy(entry, eKPIs, ifcModel);
				if(coolingFinalEnergy != null) calculatedUnits.add(coolingFinalEnergy);
				
				result.put(entry.getKey(), calculatedUnits);
				break;
			case Heating_And_Cooling_Net_Energy:
				finalEnergy = handleHeatingAndCooling(rawUnits);
				if(finalEnergy != null) calculatedUnits.addAll(finalEnergy);
				
				result.put("-", calculatedUnits);
				break;
			case Heating_And_Cooling_Final_Energy:
				finalEnergy = handleFinalHeatingAndCooling(rawUnits, eKPIs, ifcModel);
				if(finalEnergy != null) calculatedUnits.addAll(finalEnergy);
				String buildingGuid = rawUnits.entrySet().iterator().next().getKey();
				
				result.put(buildingGuid, calculatedUnits);
				break;
//			eKPI2.1
			case Greenhouse_Gas_Emissions:	
				entry = rawUnits.entrySet().iterator().next();

				MeasuredUnit greenhouseGasEmissionsUnit = handleGreenhouseGasEmissions(entry.getKey(), eKPIs, simulation);
				if(greenhouseGasEmissionsUnit != null) calculatedUnits.add(greenhouseGasEmissionsUnit);

				result.put(entry.getKey(), calculatedUnits);
				break;
//			eKPI3.1.a
			case Temperature_Over_Under_Runs:
				Set<MeasuredUnit> temperatureOverUnderrunsSet = handleTemperatureOverUnderruns(rawUnits, ifcModel);
				if(temperatureOverUnderrunsSet != null) calculatedUnits.addAll(temperatureOverUnderrunsSet);
				entry = rawUnits.entrySet().iterator().next();
				
				result.put(entry.getKey(), calculatedUnits);						
				break;			
//			eKPI3.1.b
			case Thermal_Comfort:
				Set<MeasuredUnit> thermalComfortSet = handleThermalComfort(rawUnits, ifcModel);		
				if(thermalComfortSet != null) calculatedUnits.addAll(thermalComfortSet);
				entry = rawUnits.entrySet().iterator().next();

				result.put(entry.getKey(), calculatedUnits);
				break;
//			eKPI3.1.c
			case Thermal_Comfort_Global_Values:
				Set<MeasuredUnit> thermalComfortGlobalValuesSet = handleThermalComfortGlobalValues(rawUnits, ifcModel);
				if(thermalComfortGlobalValuesSet != null) calculatedUnits.addAll(thermalComfortGlobalValuesSet);
				entry = rawUnits.entrySet().iterator().next();

				result.put(entry.getKey(), calculatedUnits);
				break;
			default: // no eKPI
				for(Map.Entry<String, Set<MeasuredUnit>> rawEntry : rawUnits.entrySet()) {
					result.put(rawEntry.getKey(), new ArrayList<MeasuredUnit>(rawEntry.getValue()));
				}
				return result;
		}
//		save it
		saveEnergyKeyPerformanceIndicators(eKPIs);
		
		return result;
	}
	
	@Override
	public Map<String, Set<MeasuredUnit>> doPostProcessingForDetailLevel(Set<MeasuredUnit> rawUnits, 
			DetailLevel detailLevel, List<String> locationIds, Ifc2x3DataModelProxy ifcModel) throws PostProcessingException {
		LOG.debug("Do detail level dependent post-processing with detail level: {} for location IDs: {}",new Object[]{detailLevel, locationIds});
//		KEY: requested location ID, VALUE: the related zones
		Map<String, Set<String>> requestedLocationIdToZones = new HashMap<String, Set<String>>();
		try {
			for(String guid : locationIds) {
				Set<String> zones = ifcModel.getSpacesOfSpatialStructure(guid);
				requestedLocationIdToZones.put(guid, zones);
			}
		} catch (IfcException ie) {
			throw new PostProcessingException(ie);
		}
		
		Map<String, Set<MeasuredUnit>> result = new HashMap<String, Set<MeasuredUnit>>();
		switch(detailLevel) {
		case SEPARATED: // take each zone like in Nandrad result files defined 
			for (Map.Entry<String, Set<String>> entry : requestedLocationIdToZones.entrySet()) {
				String requestedLocationId = entry.getKey();
				Set<String> zoneIds = entry.getValue();
				Set<MeasuredUnit> unitsForRequestedLocation = new HashSet<MeasuredUnit>();
				for(MeasuredUnit unit : rawUnits) {
					if(zoneIds.contains(unit.getId()) || unit.getId().equals(EnergyResultService.NO_ROOM_IDENTIFIER)){
						unitsForRequestedLocation.add(unit);
					}
				}	
				result.put(requestedLocationId, unitsForRequestedLocation);
			}
			break;
		case BOTH:			
//			separated
			for (Map.Entry<String, Set<String>> entry : requestedLocationIdToZones.entrySet()) {
				String requestedLocationId = entry.getKey();
				Set<String> zoneIds = entry.getValue();
				Set<MeasuredUnit> unitsForRequestedLocation = new HashSet<MeasuredUnit>();
				for(MeasuredUnit unit : rawUnits) {
					if(zoneIds.contains(unit.getId()) || unit.getId().equals(EnergyResultService.NO_ROOM_IDENTIFIER)){
						unitsForRequestedLocation.add(unit);
					}
				}	
				result.put(requestedLocationId, unitsForRequestedLocation);
			}
			
//			no break! => jump to aggregated
		case AGGREGATED: // aggregate all zones				
			for (Map.Entry<String, Set<String>> entry : requestedLocationIdToZones.entrySet()) {
				Set<MeasuredUnit> relatedZoneUnits = new HashSet<MeasuredUnit>();
				String requestedLocationId = entry.getKey();
				Set<String> zoneIds = entry.getValue();		
				List<MeasuredUnit> buildingUnits = new ArrayList<MeasuredUnit>();
				for(MeasuredUnit unit : rawUnits) {
//					decide if zone or building
					if(zoneIds.contains(unit.getId())){
						relatedZoneUnits.add(unit);
					} else if(unit.getId().equals(EnergyResultService.NO_ROOM_IDENTIFIER)) {
						buildingUnits.add(unit);
					}
				}	
//				aggregate zone
				Set<MeasuredUnit> aggregatedUnits = PostProcessingEngine.aggregateMixedUnits(relatedZoneUnits);
				for(MeasuredUnit aggUnit : aggregatedUnits) {
					aggUnit.setId(requestedLocationId);
				}
				if(result.containsKey(requestedLocationId)) {
					result.get(requestedLocationId).addAll(aggregatedUnits);
				} else {
					result.put(requestedLocationId, aggregatedUnits);
				}
//				building already aggregated
				result.get(requestedLocationId).addAll(buildingUnits);
			}
			break;
		default: break;
		}
		return result;
	}
	
	@Override
	public Set<MeasuredUnit> doPostProcessingForTime(List<MeasuredUnit> rawUnits, 
			Date startDate,
			Date endDate,
			Integer unitTime,
			TimeMeasure timeMeasure) {
		LOG.debug("Do time-dependent post-processing with time measure: {}, start date: {}, end date: {} and unit time: {}",
				new Object[]{timeMeasure, startDate, endDate, unitTime});
		Set<MeasuredUnit> finalResultList = new HashSet<MeasuredUnit>();
		
		for (MeasuredUnit unit : rawUnits) {
			TreeMap<Long, Double> values = unit.getValues();
//			(1) --> startTime and endTime
			values = PostProcessingEngine.searchValuesBetween(startDate, endDate, unit);					
			unit.setValues(values);
				
//			(2) --> timeMeasure
			switch(timeMeasure) {
			case YEAR:
				Double sumOfYear = PostProcessingEngine.createSumOfValues(unit);
				TreeMap<Long, Double> newVals = new TreeMap<Long, Double>();
				newVals.put(startDate.getTime(), sumOfYear);
				unit.setValues(newVals);
				Map<String, List<String>> eKPI = new HashMap<String, List<String>>();
				eKPI.put("Sum of year", Arrays.asList(""+sumOfYear));
				unit.setKeyPerformanceIndicator(eKPI);
				unit.setTimeUnit(TimeMeasure.YEAR.name());
				break;
			case MONTH:
				PostProcessingEngine.addUpValuesPerMonth(unit);
				unit.setTimeUnit(TimeMeasure.MONTH.name());
				break;
			case WEEK:
				PostProcessingEngine.addUpValuesPerWeek(unit);
				unit.setTimeUnit(TimeMeasure.WEEK.name());
				break;
			case DAY:
				PostProcessingEngine.addUpValuesPerDay(unit);
				unit.setTimeUnit(TimeMeasure.DAY.name());
				break;
			case HOUR:
//				do nothing because the default is hourly
				unit.setTimeUnit(TimeMeasure.HOUR.name());
				break;
			default: 
				throw new AssertionError();
			}
				
//			(3) --> unitTime
			int index = 0;
			Iterator<Long> keyIterator = unit.getValues().keySet().iterator();
			while(keyIterator.hasNext()) {
				keyIterator.next();
				if((index % unitTime) != 0) {
					keyIterator.remove();
				}	
				index++;
			}			
			finalResultList.add(unit);
		}		
		return finalResultList;
	}
	
	@Override
	public MeasuredUnit doPostProcessingForCosts(List<String> locationIds,
			ResultCodes code, Ifc2x3DataModelProxy ifcModel, OntologyModel ontologyModel,
			SimulationProject simulation, List<ConstructionTemplate> constructionTemplates) throws PostProcessingException {		
		MeasuredUnit costsUnit = null;
		
		EnergyKeyPerformanceIndicators eKPIs = getEKPIBySimulationId(simulation.getId());
		if(eKPIs == null) {
			eKPIs = new EnergyKeyPerformanceIndicators();
			eKPIs.setSimulationId(simulation.getId());
		}
		
		LOG.debug("Do costs dependent post-processing for code: {} of location IDs: {} with simulation: {} and eKPIs: {}",
				new Object[]{code, locationIds, simulation, eKPIs});
		
		for(String requestedLocationId : locationIds) {
			switch(code) {
			case Investment_Costs:				
				costsUnit = handleInvestmentCosts(requestedLocationId, ifcModel, eKPIs, ontologyModel, constructionTemplates);
				break;
			case Energy_Related_Operational_Costs:
				costsUnit = handleEnergyRelatedCosts(requestedLocationId, eKPIs, ifcModel);
				break;
			case Lifecycle_Costs:								
				costsUnit = handleLifecycleCosts(requestedLocationId, eKPIs, constructionTemplates, ifcModel, ontologyModel);
				break;
			default: // no eKPI
				return null;
			}
//			save it to the database
			saveEnergyKeyPerformanceIndicators(eKPIs);
		}
		/* TODO check this piece of code carefully when using {@link handleInvestmentCosts(Collection<String), Ifc2x3DataModel...}
		switch(code) {
		case Investment_Costs:				
			costsUnit = handleInvestmentCosts(locationIds, ifcModel, eKPIs, ontologyModel, constructionTemplates);
			break;
		case Energy_Related_Operational_Costs:
			for(String requestedLocationId : locationIds) {
				costsUnit = handleEnergyRelatedCosts(requestedLocationId, eKPIs, simulation.getUserId(), ifcModel);
			}
			break;
		case Lifecycle_Costs:		
			for(String requestedLocationId : locationIds) {
				costsUnit = handleLifecycleCosts(requestedLocationId, eKPIs, simulation.getUserId(), constructionTemplates, ifcModel, ontologyModel);
			}
			break;
		default: // no eKPI
			return null;
		}
//		save it to the database
		saveEnergyKeyPerformanceIndicators(eKPIs);
		*/
		
		return costsUnit;
	}
	
	private MeasuredUnit handleCoolingNetEnergy(Map<String, Set<MeasuredUnit>> rawUnits) {
		List<MeasuredUnit> coolingNetEnergyUnits = new ArrayList<MeasuredUnit>();
		for(Map.Entry<String, Set<MeasuredUnit>> entry : rawUnits.entrySet()) {
			Set<MeasuredUnit> unitsForRequestedLocationId = entry.getValue();			
			coolingNetEnergyUnits.addAll(unitsForRequestedLocationId);
		}
		
		MeasuredUnit coolingNetEnergy = PostProcessingEngine.aggregateUnits(new ArrayList<MeasuredUnit>(coolingNetEnergyUnits));
//		Watt to KiloWatt
		PostProcessingEngine.convertValuesOfUnit(coolingNetEnergy, 1000);
		
		coolingNetEnergy.setId("-");
		coolingNetEnergy.setAggregated(true);
		coolingNetEnergy.setLabel(ResultCodes.Cooling_Net_Energy.getLabel());
		coolingNetEnergy.setQuantity(ResultCodes.Cooling_Net_Energy.getQuantity());
		coolingNetEnergy.setResultCode(ResultCodes.Cooling_Net_Energy.getCode());
		coolingNetEnergy.setValueUnit(ResultCodes.Cooling_Net_Energy.getValueUnit());
		return coolingNetEnergy;
	}
	
	private MeasuredUnit handleHeatingNetEnergy(Map<String, Set<MeasuredUnit>> rawUnits) {
		List<MeasuredUnit> heatingNetEnergyUnits = new ArrayList<MeasuredUnit>();
		for(Map.Entry<String, Set<MeasuredUnit>> entry : rawUnits.entrySet()) {
			Set<MeasuredUnit> unitsForRequestedLocationId = entry.getValue();			
			heatingNetEnergyUnits.addAll(unitsForRequestedLocationId);
		}
			
		MeasuredUnit heatingNetEnergy = PostProcessingEngine.aggregateUnits(heatingNetEnergyUnits);
//		Watt to KiloWatt
		PostProcessingEngine.convertValuesOfUnit(heatingNetEnergy, 1000);
		
		heatingNetEnergy.setId("-");
		heatingNetEnergy.setAggregated(true);
		heatingNetEnergy.setLabel(ResultCodes.Heating_Net_Energy.getLabel());
		heatingNetEnergy.setQuantity(ResultCodes.Heating_Net_Energy.getQuantity());
		heatingNetEnergy.setResultCode(ResultCodes.Heating_Net_Energy.getCode());
		heatingNetEnergy.setValueUnit(ResultCodes.Heating_Net_Energy.getValueUnit());
		
		return heatingNetEnergy;
	}
	
	private MeasuredUnit handleHeatingFinalEnergy(Map.Entry<String, Set<MeasuredUnit>> entry, 
			EnergyKeyPerformanceIndicators eKPIs, Ifc2x3DataModelProxy ifcModel) throws PostProcessingException {
		String requestedLocationId = entry.getKey();
		Set<MeasuredUnit> unitsForRequestedLocationId = entry.getValue();
			
		try{
//			check if the requested ID is a Building GlobalID
			EIfcbuilding building = ifcModel.getIfcEntity(requestedLocationId, EIfcbuilding.class);
			if(building == null) throw new PostProcessingException("The requested ID: "+requestedLocationId+" is no building");
			Set<String> zoneIds = ifcModel.getSpacesOfSpatialStructure(requestedLocationId);
			List<MeasuredUnit> heatingFinalEnergyUnits = new ArrayList<MeasuredUnit>();
			for(MeasuredUnit mu : unitsForRequestedLocationId) {
				if(zoneIds.contains(mu.getId())) {
					heatingFinalEnergyUnits.add(mu);
				}
			}
			MeasuredUnit heatingFinalEnergy = PostProcessingEngine.aggregateUnits(heatingFinalEnergyUnits);
//			Watt to KiloWatt
			PostProcessingEngine.convertValuesOfUnit(heatingFinalEnergy, 1000);
			
			double finalEnergyHeating = EnergyKeyPerformanceIndicatorCalculator.calculateHeatingFinalEnergy(heatingFinalEnergy, eKPIs);
			finalEnergyHeating = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(finalEnergyHeating);
			LOG.trace("Calculated final energy heating: {}", new Object[]{finalEnergyHeating});
			Map<String,List<String>> finalHeatingEKPIMap = new HashMap<String, List<String>>();
			finalHeatingEKPIMap.put(ResultCodes.Heating_Final_Energy.getLabel(), Arrays.asList(""+finalEnergyHeating));
			
			heatingFinalEnergy.setId(requestedLocationId);
			heatingFinalEnergy.setKeyPerformanceIndicator(finalHeatingEKPIMap);
			heatingFinalEnergy.setAggregated(true);
			heatingFinalEnergy.setLabel(ResultCodes.Heating_Final_Energy.getLabel());
			heatingFinalEnergy.setQuantity(ResultCodes.Heating_Final_Energy.getQuantity());
			heatingFinalEnergy.setResultCode(ResultCodes.Heating_Final_Energy.getCode());
			heatingFinalEnergy.setValueUnit(ResultCodes.Heating_Final_Energy.getValueUnit());
			
			eKPIs.setFinalEnergyHeating(finalEnergyHeating);
			
			return heatingFinalEnergy;
		} catch (IfcException ie) {
			throw new PostProcessingException(ie);
		}
	}
	
	/**
	 * @param entry An entry with the building GUID as key and the measurement units as values.
	 * @param eKPIs
	 * @param ifcModel
	 * @return
	 * @throws PostProcessingException
	 */
	private MeasuredUnit handleCoolingFinalEnergy(Map.Entry<String, Set<MeasuredUnit>> entry, EnergyKeyPerformanceIndicators eKPIs, Ifc2x3DataModelProxy ifcModel) throws PostProcessingException {
		String requestedLocationId = entry.getKey(); // normally the requested location identifier will be the building.
		Set<MeasuredUnit> unitsForRequestedLocationId = entry.getValue();
		try{
//			check if the requested ID is a Building GlobalID
			EIfcbuilding building = ifcModel.getIfcEntity(requestedLocationId, EIfcbuilding.class);
			if(building == null) throw new PostProcessingException("The requested ID: "+requestedLocationId+" is no building");
		
			Set<String> zoneIds = ifcModel.getSpacesOfSpatialStructure(requestedLocationId);
			List<MeasuredUnit> coolingFinalEnergyUnits = new ArrayList<MeasuredUnit>();
			for(MeasuredUnit mu : unitsForRequestedLocationId) {
				if(zoneIds.contains(mu.getId())) {
					coolingFinalEnergyUnits.add(mu);
				}
			}
			MeasuredUnit coolingFinalEnergy = PostProcessingEngine.aggregateUnits(coolingFinalEnergyUnits);
//			Watt to KiloWatt
			PostProcessingEngine.convertValuesOfUnit(coolingFinalEnergy, 1000);
			
			double finalEnergyCooling = EnergyKeyPerformanceIndicatorCalculator.calculateCoolingFinalEnergy(coolingFinalEnergy, eKPIs);
			finalEnergyCooling = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(finalEnergyCooling);
			LOG.trace("Calculated final energy cooling: {}", new Object[]{finalEnergyCooling});
			Map<String,List<String>> finalCoolingEKPIMap = new HashMap<String, List<String>>();
			finalCoolingEKPIMap.put(ResultCodes.Cooling_Final_Energy.getLabel(), Arrays.asList(""+finalEnergyCooling));
			coolingFinalEnergy.setKeyPerformanceIndicator(finalCoolingEKPIMap);
			coolingFinalEnergy.setId(requestedLocationId);
			coolingFinalEnergy.setAggregated(true);
			coolingFinalEnergy.setLabel(ResultCodes.Cooling_Final_Energy.getLabel());
			coolingFinalEnergy.setQuantity(ResultCodes.Cooling_Final_Energy.getQuantity());
			coolingFinalEnergy.setResultCode(ResultCodes.Cooling_Final_Energy.getCode());
			coolingFinalEnergy.setValueUnit(ResultCodes.Cooling_Final_Energy.getValueUnit());
			
			eKPIs.setFinalEnergyCooling(finalEnergyCooling);
			
			return coolingFinalEnergy;
		} catch (IfcException ie) {
			throw new PostProcessingException(ie);
		}
	}
	
	private MeasuredUnit handleGreenhouseGasEmissions(String buildingGuid, EnergyKeyPerformanceIndicators eKPIs, SimulationProject simulation) {
		List<Combustible> combustibles = listCombustibles();
		Map<EnergyResourceType, Double> greenhouseGasEmissions = EnergyKeyPerformanceIndicatorCalculator.calculateEcologicGreenhouseGasEmissionValue(eKPIs, combustibles);
			
//		Table: 
//			CO2 | SO2 | NOX
//			234 | 945 | 0.4
		Map<String, List<String>> eKPI = new HashMap<String, List<String>>();
		double co2Value = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(greenhouseGasEmissions.get(EnergyResourceType.CO2));
		double so2Value = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(greenhouseGasEmissions.get(EnergyResourceType.SO2));
		double noxValue = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(greenhouseGasEmissions.get(EnergyResourceType.NOX));
		eKPI.put(EnergyResourceType.CO2.name(), Arrays.asList(""+co2Value));
		eKPI.put(EnergyResourceType.SO2.name(), Arrays.asList(""+so2Value));
		eKPI.put(EnergyResourceType.NOX.name(), Arrays.asList(""+noxValue));
			
		MeasuredUnit greenhouseGasEmissionsUnit = new MeasuredUnit(buildingGuid);
		greenhouseGasEmissionsUnit.setAggregated(true);
		greenhouseGasEmissionsUnit.setKeyPerformanceIndicator(eKPI);
		greenhouseGasEmissionsUnit.setLabel(ResultCodes.Greenhouse_Gas_Emissions.getLabel());
		greenhouseGasEmissionsUnit.setQuantity(ResultCodes.Greenhouse_Gas_Emissions.getQuantity());
		greenhouseGasEmissionsUnit.setResultCode(ResultCodes.Greenhouse_Gas_Emissions.getCode());
		greenhouseGasEmissionsUnit.setValueUnit(ResultCodes.Greenhouse_Gas_Emissions.getValueUnit());
		greenhouseGasEmissionsUnit.setTimeUnit(TimeMeasure.YEAR.name());
			
		eKPIs.setGreenhouseGasEmissions(greenhouseGasEmissions);
		
		return greenhouseGasEmissionsUnit;
	}
	
	private List<MeasuredUnit> handleHeatingAndCooling(Map<String, Set<MeasuredUnit>> rawUnits) {
		List<MeasuredUnit> calculatedUnits = new ArrayList<MeasuredUnit>();
		
		List<MeasuredUnit> heatingNEUnits = new ArrayList<MeasuredUnit>();
		List<MeasuredUnit> coolingNEUnits = new ArrayList<MeasuredUnit>();	
		
		for(Map.Entry<String, Set<MeasuredUnit>> en : rawUnits.entrySet()) {						
			for(MeasuredUnit mu : en.getValue()) {
				if(mu.getResultCode()==ResultCodes.Heating.getCode()) {
					heatingNEUnits.add(mu);
				} else if(mu.getResultCode()==ResultCodes.Cooling.getCode()) {
					coolingNEUnits.add(mu);
				}
			}
		}
		
		MeasuredUnit heatingNE = PostProcessingEngine.aggregateUnits(heatingNEUnits);
//		Watt to KiloWatt
		PostProcessingEngine.convertValuesOfUnit(heatingNE, 1000);
		
		heatingNE.setId("-");
		heatingNE.setAggregated(true);
		heatingNE.setLabel(ResultCodes.Heating_Net_Energy.getLabel());
		heatingNE.setQuantity(ResultCodes.Heating_Net_Energy.getQuantity());
		heatingNE.setResultCode(ResultCodes.Heating_Net_Energy.getCode());
		heatingNE.setValueUnit(ResultCodes.Heating_Net_Energy.getValueUnit());
		
		
		MeasuredUnit coolingNE = PostProcessingEngine.aggregateUnits(coolingNEUnits);
//		Watt to KiloWatt
		PostProcessingEngine.convertValuesOfUnit(coolingNE, 1000);
		
		coolingNE.setId("-");
		coolingNE.setAggregated(true);
		coolingNE.setLabel(ResultCodes.Cooling_Net_Energy.getLabel());
		coolingNE.setQuantity(ResultCodes.Cooling_Net_Energy.getQuantity());
		coolingNE.setResultCode(ResultCodes.Cooling_Net_Energy.getCode());
		coolingNE.setValueUnit(ResultCodes.Cooling_Net_Energy.getValueUnit());
		
		calculatedUnits.add(heatingNE);
		calculatedUnits.add(coolingNE);
		
		return calculatedUnits;
	}
	
	/**
	 * @param rawUnits A set with the building GUID as key and the measurement units as values.
	 * @param eKPIs
	 * @param ifcModel
	 * @return An ordered list with the [0] heating and [1] cooling units.
	 * @throws PostProcessingException
	 */
	private List<MeasuredUnit> handleFinalHeatingAndCooling(Map<String, Set<MeasuredUnit>> rawUnits, 
			EnergyKeyPerformanceIndicators eKPIs, Ifc2x3DataModelProxy ifcModel) throws PostProcessingException {
		List<MeasuredUnit> calculatedUnits = new ArrayList<MeasuredUnit>();
		
		Set<MeasuredUnit> heatingNEUnitSet = new HashSet<MeasuredUnit>();
		Set<MeasuredUnit> coolingNEUnitSet = new HashSet<MeasuredUnit>();		
		
		String buildingGuid = rawUnits.entrySet().iterator().next().getKey();
		
		for(Map.Entry<String, Set<MeasuredUnit>> en : rawUnits.entrySet()) {						
			for(MeasuredUnit mu : en.getValue()) {
				if(mu.getResultCode()==ResultCodes.Heating.getCode()) {
					heatingNEUnitSet.add(mu);
				} else if(mu.getResultCode()==ResultCodes.Cooling.getCode()) {
					coolingNEUnitSet.add(mu);
				}
			}
		}
		
		Map.Entry<String, Set<MeasuredUnit>> heatingEntry = new AbstractMap.SimpleEntry<String, Set<MeasuredUnit>>(buildingGuid, heatingNEUnitSet);
		Map.Entry<String, Set<MeasuredUnit>> coolingEntry = new AbstractMap.SimpleEntry<String, Set<MeasuredUnit>>(buildingGuid, coolingNEUnitSet);		
			
		MeasuredUnit heatingFE = handleHeatingFinalEnergy(heatingEntry, eKPIs, ifcModel);
		
		heatingFE.setId(heatingEntry.getKey());
		heatingFE.setAggregated(true);
		heatingFE.setLabel(ResultCodes.Heating_Final_Energy.getLabel());
		heatingFE.setQuantity(ResultCodes.Heating_Final_Energy.getQuantity());
		heatingFE.setResultCode(ResultCodes.Heating_Final_Energy.getCode());
		heatingFE.setValueUnit(ResultCodes.Heating_Final_Energy.getValueUnit());
		
		MeasuredUnit coolingFE = handleCoolingFinalEnergy(coolingEntry, eKPIs, ifcModel);
		
		coolingFE.setId(coolingEntry.getKey());
		coolingFE.setAggregated(true);
		coolingFE.setLabel(ResultCodes.Cooling_Final_Energy.getLabel());
		coolingFE.setQuantity(ResultCodes.Cooling_Final_Energy.getQuantity());
		coolingFE.setResultCode(ResultCodes.Cooling_Final_Energy.getCode());
		coolingFE.setValueUnit(ResultCodes.Cooling_Final_Energy.getValueUnit());
			
		double finalEnergyHeating = EnergyKeyPerformanceIndicatorCalculator.calculateHeatingFinalEnergy(heatingFE, eKPIs);		
		double finalEnergyCooling = EnergyKeyPerformanceIndicatorCalculator.calculateCoolingFinalEnergy(coolingFE, eKPIs);
		
		finalEnergyHeating = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(finalEnergyHeating);
		finalEnergyCooling = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(finalEnergyCooling);
		
//		we need the right order of insertion
		Map<String,List<String>> finalEnergyEKPIMap = new LinkedHashMap<String, List<String>>();
		finalEnergyEKPIMap.put(ResultCodes.Heating_Final_Energy.getLabel(), Arrays.asList(""+finalEnergyHeating));
		finalEnergyEKPIMap.put(ResultCodes.Cooling_Final_Energy.getLabel(), Arrays.asList(""+finalEnergyCooling));
			
		heatingFE.setKeyPerformanceIndicator(finalEnergyEKPIMap);
		coolingFE.setKeyPerformanceIndicator(finalEnergyEKPIMap);
			
		calculatedUnits.add(heatingFE);
		calculatedUnits.add(coolingFE);
			
		eKPIs.setFinalEnergyHeating(finalEnergyHeating);
		eKPIs.setFinalEnergyCooling(finalEnergyCooling);
		return calculatedUnits;
	}
	
	private MeasuredUnit handleInvestmentCosts(String buildingGuid, Ifc2x3DataModelProxy ifcModel,
			EnergyKeyPerformanceIndicators eKPIs, OntologyModel ontologyModel, 
			List<ConstructionTemplate> constructionTemplates) throws PostProcessingException {
		try {
	//		check if the requested ID is a Building GlobalId
			EIfcbuilding building = ifcModel.getIfcEntity(buildingGuid, EIfcbuilding.class);
			if(building == null) throw new PostProcessingException("The requested ID: "+buildingGuid+" is no building");		
			double investmentCosts = 0.0;
			if(eKPIs.getInvestmentCosts() <= 0.0) {
				LOG.debug("Calculate investment costs for location: {}", 
						new Object[]{buildingGuid});
				investmentCosts = calculateInvestmentCosts(buildingGuid, ifcModel, ontologyModel, constructionTemplates);
				eKPIs.setInvestmentCosts(investmentCosts);
			} else {
				investmentCosts = eKPIs.getInvestmentCosts();
			}
			Map<String,List<String>> investmentKpiMap = new HashMap<String, List<String>>();
			investmentKpiMap.put(ResultCodes.Investment_Costs.getLabel(), Arrays.asList(""+investmentCosts));
			
			MeasuredUnit investmentCostsUnit = new MeasuredUnit(buildingGuid);
			investmentCostsUnit.setAggregated(true);
			investmentCostsUnit.setKeyPerformanceIndicator(investmentKpiMap);
			investmentCostsUnit.setLabel(ResultCodes.Investment_Costs.getLabel());
			investmentCostsUnit.setQuantity(ResultCodes.Investment_Costs.getQuantity());
			investmentCostsUnit.setResultCode(ResultCodes.Investment_Costs.getCode());
			investmentCostsUnit.setValueUnit(ResultCodes.Investment_Costs.getValueUnit());
			investmentCostsUnit.setTimeUnit(TimeMeasure.YEAR.name());
			
			return investmentCostsUnit;
		} catch (IfcException ie) {
			throw new PostProcessingException(ie);
		} 
	}
	
	private MeasuredUnit handleInvestmentCosts(Collection<String> spatialStructureGlobalIds, Ifc2x3DataModelProxy ifcModel,
			EnergyKeyPerformanceIndicators eKPIs, OntologyModel ontologyModel, 
			List<ConstructionTemplate> constructionTemplates) throws PostProcessingException {

		//	check if the requested ID is a Building GlobalId
		//	EIfcbuilding building = ifcModel.getIfcEntity(buildingGuid, EIfcbuilding.class);
		//	if(building == null) throw new PostProcessingException("The requested ID: "+buildingGuid+" is no building");		
		double investmentCosts = 0.0;
		if(eKPIs.getInvestmentCosts() <= 0.0) {
			LOG.debug("Calculate investment costs for location: {}", 
					new Object[]{spatialStructureGlobalIds});
			investmentCosts = calculateInvestmentCosts(spatialStructureGlobalIds, ifcModel, ontologyModel, constructionTemplates);
			eKPIs.setInvestmentCosts(investmentCosts);
		} else {
			investmentCosts = eKPIs.getInvestmentCosts();
		}
		Map<String,List<String>> investmentKpiMap = new HashMap<String, List<String>>();
		investmentKpiMap.put(ResultCodes.Investment_Costs.getLabel(), Arrays.asList(""+investmentCosts));

		MeasuredUnit investmentCostsUnit = new MeasuredUnit(spatialStructureGlobalIds.iterator().next());
		investmentCostsUnit.setAggregated(true);
		investmentCostsUnit.setKeyPerformanceIndicator(investmentKpiMap);
		investmentCostsUnit.setLabel(ResultCodes.Investment_Costs.getLabel());
		investmentCostsUnit.setQuantity(ResultCodes.Investment_Costs.getQuantity());
		investmentCostsUnit.setResultCode(ResultCodes.Investment_Costs.getCode());
		investmentCostsUnit.setValueUnit(ResultCodes.Investment_Costs.getValueUnit());
		investmentCostsUnit.setTimeUnit(TimeMeasure.YEAR.name());

		return investmentCostsUnit;
	}
	
	@Override
	public Double calculateInvestmentCosts(String buildingGuid, Ifc2x3DataModelProxy ifcModel, OntologyModel ontologyModel, 
			List<? extends ConstructionTemplate> constructionTemplates) throws PostProcessingException {
		if(constructionTemplates == null) return 0.0;
		
		double investmentCosts = 0.0;
//		TODO change buildingGuid to spatialStructureGuid to calculate investment costs for every spatial structure like rooms, storeys and buildings equally
		try {				
//			caching map which contains the template path as key and the costs as value 
			ConcurrentMap<String, Double> costsForConstructionFilePath = new ConcurrentHashMap<String, Double>();
			for(ConstructionTemplate cTpl : constructionTemplates) {
				String filePath = cTpl.getSourceFileUri();
				Double costsPerSquareMeter = cTpl.getCostsPerSquareMeter();
				costsForConstructionFilePath.put(filePath, costsPerSquareMeter);
			}
			
			// retrieve all rooms because we need the space boundaries which provides the information about the building element area
			EIfcspace[] spacesForInvestmentCosts = ifcModel.getSpaces(buildingGuid);
			//	Parallelize: It is very time-consuming to search each building element and get the investment costs for it
			int NR_POST_PROCESSING_THREAD = Integer.valueOf(configurationService.getProperty(PROPERTIES.NR_POST_PROCESSING_THREAD));
			ExecutorService executor = Executors.newFixedThreadPool(NR_POST_PROCESSING_THREAD);
			List<Future<Double>> taskList = new ArrayList<Future<Double>>();
			for(EIfcspace space : spacesForInvestmentCosts) {
				BuildingElementCostPerSpaceCallable costsRunnable = new BuildingElementCostPerSpaceCallable(
						ifcModel, space, ontologyModel, costsForConstructionFilePath);
				Future<Double> future = executor.submit(costsRunnable);
				taskList.add(future);
			}
			
			// now retrieve the result
			int taskNumber = taskList.size();
			LOG.info("Started {} tasks for calculating the investment costs", taskNumber);
			for(int i = 0; i < taskNumber; i++) {
				Future<Double> future = taskList.get(i);
				try {
					investmentCosts += future.get();
					if(i % 50 == 0) LOG.info("{}/{} tasks completed", new Object[]{i, taskNumber});
				} catch (InterruptedException e) {
					LOG.error("Task: {} interrupted", new Object[]{future.toString()});
				} catch (ExecutionException e) {
					LOG.error("Task: {} cannot be executed", new Object[]{future.toString()});
				}
			}
			LOG.info("Calculation of investment costs completed");
			
//			// old approach
//			List<EIfcelement> notAssignedElements = new ArrayList<EIfcelement>();
//			for(EIfcspace space : spacesForInvestmentCosts) {	
////				retrieve space boundaries because the area of the elements is only given through the space boundary calculation by BSPro
////				=> otherwise we cannot calculate the investment costs currently
//				EIfcrelspaceboundary[] spaceBoundaries = ifcModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
//				for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
//					EIfcelement element = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
//					String elementGuid = ifcModel.getGlobalId(element);
//					if(spaceBoundary.testDescription(spaceBoundary)) {
//						String description = spaceBoundary.getDescription(spaceBoundary);
//						double area	= 0.0;
//						if(description != null) {
//							description = description.replace(description.substring(0, description.indexOf(AREA_PATTERN)),"");
//							String areaText = description.replaceFirst(AREA_PATTERN, "");
//							if(areaText != null) {
//								area = Double.valueOf(areaText);
//							}
//							long timeBeforeQuery = System.currentTimeMillis();
//							List<Map<VARIABLES, String>> results = QueryExecutor.findConstructionTemplate(elementGuid, ontologyModel);
//							long timeAfterQuery = System.currentTimeMillis();
//							LOG.debug("Time consumed (hh:mm:ss:SSS): {} for finding the construction template for element: {}",
//									new Object[]{sdf.format(new Date(timeAfterQuery-timeBeforeQuery)), elementGuid});
//							if(results != null) {
//								String tplPath = results.get(0).get(VARIABLES.tplPath);
//								if(StringUtils.isEmpty(tplPath)) continue;								
//								Double costsPerSquareMeter = costsForConstructionFilePath.get(tplPath);
//								LOG.debug("Assigned construction template: {} of element: {} has costs per square meter: {}",
//									new Object[]{tplPath, elementGuid, costsPerSquareMeter});
//								if(costsPerSquareMeter == null) continue;
//								
//								double investmentCostsOfBuildingElement = EnergyKeyPerformanceIndicatorCalculator.calculateInvestmentCostsOfBuildingElement(costsPerSquareMeter, area);
//								System.err.println(investmentCostsOfBuildingElement+"Ä: TPL: "+tplPath+" - costs: "+costsPerSquareMeter +" area: "+area);
//								investmentCosts += investmentCostsOfBuildingElement;
//							} else {
//								notAssignedElements.add(element);
//							}
//						}
//					}
//				}	
//			}
//			System.err.println("Not assigned elements: "+notAssignedElements);
//			LOG.trace("Not assigned elements: {}",notAssignedElements);
		} catch (IfcException ie) {
			throw new PostProcessingException(ie);
		} 
//		catch (SdaiException e) {
//			throw new PostProcessingException(e);
//		}
		return investmentCosts;
	}
	
	@Override
	public Double calculateInvestmentCosts(Collection<String> spatialStructureGlobalIds, Ifc2x3DataModelProxy ifcModel, OntologyModel ontologyModel, 
			List<ConstructionTemplate> constructionTemplates) throws PostProcessingException {
		LOG.info("Calculating investment costs for spatial IDs: {}", new Object[]{spatialStructureGlobalIds});
		Double investmentCosts = 0.0;

		//	Parallelize: It is very time-consuming to search each building element and get the investment costs for it
		int NR_POST_PROCESSING_THREAD = Integer.valueOf(configurationService.getProperty(PROPERTIES.NR_POST_PROCESSING_THREAD));
		ExecutorService executor = Executors.newFixedThreadPool(NR_POST_PROCESSING_THREAD);
		List<Future<Double>> taskList = new ArrayList<Future<Double>>();

		//	caching map which contains the template path as key and the costs as value 
		ConcurrentMap<String, Double> costsForConstructionFilePath = new ConcurrentHashMap<String, Double>();
		for(ConstructionTemplate cTpl : constructionTemplates) {
			String filePath = cTpl.getSourceFileUri();
			Double costsPerSquareMeter = cTpl.getCostsPerSquareMeter();
			costsForConstructionFilePath.put(filePath, costsPerSquareMeter);
		}

		for(String spatialStructureGlobalId : spatialStructureGlobalIds) {
			try {
				EIfcspace[] spacesForInvestmentCosts = null;
				EIfcroot ifcRoot = ifcModel.getIfcEntity(spatialStructureGlobalId, null);
				String guid = ifcModel.getGlobalId(ifcRoot);
				if(ifcRoot instanceof EIfcbuilding) {
					spacesForInvestmentCosts = ifcModel.getSpaces(guid);
				} else if(ifcRoot instanceof EIfcbuildingstorey) {
					spacesForInvestmentCosts = ifcModel.getSpacesInStorey((EIfcbuildingstorey)ifcRoot);
				} else if(ifcRoot instanceof EIfcspace) {
					spacesForInvestmentCosts = new EIfcspace[1];
					spacesForInvestmentCosts[0] = (EIfcspace)ifcRoot;
				} else {
					throw new IllegalArgumentException("Given ID is no building, storey or room");
				}
				
				for(EIfcspace space : spacesForInvestmentCosts) {
					BuildingElementCostPerSpaceCallable costsRunnable = new BuildingElementCostPerSpaceCallable(
							ifcModel, space, ontologyModel, costsForConstructionFilePath);
					Future<Double> future = executor.submit(costsRunnable);
					taskList.add(future);
				}
			} catch (IfcException ie) {
				throw new PostProcessingException(ie);
			} 
		}

		// now retrieve the result
		int taskNumber = taskList.size();
		LOG.info("Started {} tasks for calculating the investment costs", taskNumber);
		for(int i = 0; i < taskNumber; i++) {
			Future<Double> future = taskList.get(i);
			try {
				investmentCosts += future.get();
				if(i % 50 == 0) LOG.info("{}/{} tasks completed", new Object[]{i, taskNumber});
			} catch (InterruptedException e) {
				LOG.error("Task: {} interrupted", new Object[]{future.toString()});
			} catch (ExecutionException e) {
				LOG.error("Task: {} cannot be executed", new Object[]{future.toString()});
			}
		}
		LOG.info("Calculation of investment costs completed");
			// old approach
//			for(EIfcspace space : spacesForInvestmentCosts) {	
////				retrieve space boundaries because the area of the elements is only given through the space boundary calculation by BSPro
////				=> otherwise we cannot calculate the investment costs currently
//				EIfcrelspaceboundary[] spaceBoundaries = ifcModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
//				for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
//					EIfcelement element = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
//					String elementGuid = ifcModel.getGlobalId(element);
//					if(spaceBoundary.testDescription(spaceBoundary)) {
//						String description = spaceBoundary.getDescription(spaceBoundary);
//						double area	= 0.0;
//						if(description != null) {
//							description = description.replace(description.substring(0, description.indexOf(AREA_PATTERN)),"");
//							String areaText = description.replaceFirst(AREA_PATTERN, "");
//							if(areaText != null) {
//								area = Double.valueOf(areaText);
//							}
//							long timeBeforeQuery = System.currentTimeMillis();
//							List<Map<VARIABLES, String>> results = QueryExecutor.findConstructionTemplate(elementGuid, ontologyModel);
//							long timeAfterQuery = System.currentTimeMillis();
//							LOG.debug("Time consumed (hh:mm:ss:SSS): {} for finding the construction template for element: {}",
//									new Object[]{sdf.format(new Date(timeAfterQuery-timeBeforeQuery)), elementGuid});
//							if(results != null) {
//								String tplPath = results.get(0).get(VARIABLES.tplPath);
//								if(StringUtils.isEmpty(tplPath)) continue;								
//								Double costsPerSquareMeter = costsForConstructionFilePath.get(tplPath);
//								LOG.debug("Assigned construction template: {} of element: {} has costs per square meter: {}",
//									new Object[]{tplPath, elementGuid, costsPerSquareMeter});
//								if(costsPerSquareMeter == null) continue;
//								
//								double investmentCostsOfBuildingElement = EnergyKeyPerformanceIndicatorCalculator.calculateInvestmentCostsOfBuildingElement(costsPerSquareMeter, area);
//								investmentCosts += investmentCostsOfBuildingElement;
//							}
//						}
//					}
//				}	
//			}
//		catch (SdaiException e) {
//			throw new PostProcessingException(e);
//		}
		return investmentCosts;
	}
	
	private MeasuredUnit handleLifecycleCosts(String requestedLocationId, EnergyKeyPerformanceIndicators eKPIs,
			 List<ConstructionTemplate> constructionTemplates, Ifc2x3DataModelProxy ifcModel,
			 OntologyModel ontologyModel) throws PostProcessingException {
		LOG.debug("Calculate lifecycle costs for location: {} with eKPIs: {}", 
				new Object[]{requestedLocationId, eKPIs});
		try {
	//		check if the requested ID is a Building GlobalId
			EIfcbuilding building = ifcModel.getIfcEntity(requestedLocationId, EIfcbuilding.class);
			if(building == null) throw new PostProcessingException("The requested ID: "+requestedLocationId+" is no building");
			
			double windowArea					= 0.0;
			double buildingShellArea			= 0.0;
			double investmentCosts 				= 0.0;
			if(eKPIs.getInvestmentCosts() >= 0.0 && eKPIs.getWindowArea() >= 0.0 && eKPIs.getBuildingShellArea() >= 0.0) { // use the saved investment costs
				investmentCosts = eKPIs.getInvestmentCosts();
				windowArea = eKPIs.getWindowArea();
				buildingShellArea = eKPIs.getBuildingShellArea();
			} else {
				windowArea = calculateWindowArea(requestedLocationId, ifcModel);
				buildingShellArea = calculateBuildingShellArea(requestedLocationId, ifcModel);
				investmentCosts = calculateInvestmentCosts(requestedLocationId, ifcModel, ontologyModel, constructionTemplates);
				
				// retrieve all building elements
//				EIfcspace[] spacesForLifecycleCosts = ifcModel.getSpaces(requestedLocationId);
//				for(EIfcspace space : spacesForLifecycleCosts) {						
//					EIfcrelspaceboundary[] spaceBoundaries = ifcModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
//					for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
//						EIfcelement element = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
//						String elementGuid = ifcModel.getGlobalId(element);
//						double area	= 0.0;
//	//					calculate only the investment costs if none are given
//						if(investmentCosts <= 0.0) {
//							if(spaceBoundary.testDescription(spaceBoundary)) {
//								String description = spaceBoundary.getDescription(spaceBoundary);								
//								if(description != null) {
//									description = description.replace(description.substring(0, description.indexOf(AREA_PATTERN)),"");
//									String areaText = description.replaceFirst(AREA_PATTERN, "");
//									if(areaText != null) {
//										area = Double.valueOf(areaText);
//									}
//									List<Map<VARIABLES, String>> results = QueryExecutor.findConstructionTemplate(elementGuid, ontologyModel);
//									if(results != null) {
//										String tplPath = results.get(0).get(VARIABLES.tplPath);
//										for(ConstructionTemplate cTpl : constructionTemplates) {
//											if(cTpl.getSourceFilePath().equalsIgnoreCase(tplPath)) {
//												Double costsPerSquareMeter = cTpl.getCostsPerSquareMeter();
//												if(costsPerSquareMeter != null) {
//													double investmentCostsOfBuildingElement = EnergyKeyPerformanceIndicatorCalculator.calculateInvestmentCostsOfBuildingElement(costsPerSquareMeter, area);
//													investmentCosts += investmentCostsOfBuildingElement;
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//		//				non-energy-related costs for external elements
//						int internalOrExternal = spaceBoundary.getInternalorexternalboundary(spaceBoundary);
//						if(internalOrExternal == EIfcinternalorexternalenum.EXTERNAL) { // facade element: window or other concrete element
//							if(element instanceof EIfcwindow) {
//								windowArea += area;
//							} else { // wall, slab, column etc.
//								buildingShellArea += area;
//							}
//						}
//		
//					}
//				}
				eKPIs.setWindowArea(windowArea);
				eKPIs.setBuildingShellArea(buildingShellArea);
				eKPIs.setInvestmentCosts(investmentCosts);
			}
	//		energy-related costs			
			List<Combustible> combustiblesForLifecyclecosts = listCombustibles();
			
			double energyRelatedOperationalCostsForLifecyclecosts = EnergyKeyPerformanceIndicatorCalculator.calculateEnergyRelatedOperationalCosts(combustiblesForLifecyclecosts, eKPIs);					
			double nonEnergyRelatedOperationalCosts = EnergyKeyPerformanceIndicatorCalculator.calculateNonEnergyRelatedOperationalCosts(eKPIs);				
			double lifecycleCostsSum = investmentCosts + energyRelatedOperationalCostsForLifecyclecosts + nonEnergyRelatedOperationalCosts;
			// round the values
			investmentCosts = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(investmentCosts);
			energyRelatedOperationalCostsForLifecyclecosts = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(energyRelatedOperationalCostsForLifecyclecosts);
			nonEnergyRelatedOperationalCosts = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(nonEnergyRelatedOperationalCosts);
			lifecycleCostsSum = EnergyKeyPerformanceIndicatorCalculator.roundToReadableValue(lifecycleCostsSum);
			
			LOG.debug("Calculated energy-related operational costs are: {}, non-energy-related operational costs are: {} and investment costs are: {} (window area is: {} and building shell area is: {}, assigned combustibles are: {})",
					new Object[]{energyRelatedOperationalCostsForLifecyclecosts, nonEnergyRelatedOperationalCosts, investmentCosts,
					windowArea, buildingShellArea, eKPIs.getCombustibleIds()});
			Map<String, List<String>> energyKpiMapForLifecycleCosts = new HashMap<String, List<String>>();
			energyKpiMapForLifecycleCosts.put(ResultCodes.Energy_Related_Operational_Costs.getLabel(), Arrays.asList(""+energyRelatedOperationalCostsForLifecyclecosts));
			energyKpiMapForLifecycleCosts.put(ResultCodes.Investment_Costs.getLabel(), Arrays.asList(""+investmentCosts));
			energyKpiMapForLifecycleCosts.put(ResultCodes.Non_Energy_Related_Operational_Costs.getLabel(), Arrays.asList(""+nonEnergyRelatedOperationalCosts));
//			energyKpiMapForLifecycleCosts.put(ResultCodes.Lifecycle_Costs.getLabel(), Arrays.asList(""+lifecycleCostsSum));				
	
			
			MeasuredUnit costsUnit = new MeasuredUnit(requestedLocationId);
			costsUnit.setAggregated(true);
			costsUnit.setLabel(ResultCodes.Lifecycle_Costs.getLabel());
			costsUnit.setQuantity(ResultCodes.Lifecycle_Costs.getQuantity());
			costsUnit.setResultCode(ResultCodes.Lifecycle_Costs.getCode());
			costsUnit.setValueUnit(ResultCodes.Lifecycle_Costs.getValueUnit());
			costsUnit.setKeyPerformanceIndicator(energyKpiMapForLifecycleCosts);
			costsUnit.setTimeUnit(TimeMeasure.YEAR.name());
			
			eKPIs.setLifeCycleCosts(lifecycleCostsSum);
			
			return costsUnit;
		} catch(IfcException ie) {
			throw new PostProcessingException(ie);
		} 
//		catch (SdaiException e) {
//			throw new PostProcessingException(e);
//		}
	}
	
	private MeasuredUnit handleEnergyRelatedCosts(String requestedLocationId, EnergyKeyPerformanceIndicators eKPIs,
			Ifc2x3DataModelProxy ifcModel) throws PostProcessingException {
		LOG.debug("Calculate energy-related costs for location: {}", 
				new Object[]{requestedLocationId});
		try {
	//		check if the requested ID is a Building GlobalId
			EIfcbuilding building = ifcModel.getIfcEntity(requestedLocationId, EIfcbuilding.class);
			if(building == null) throw new PostProcessingException("The requested ID: "+requestedLocationId+" is no building");				
			
			List<Combustible> combustiblesForEnergyrelatedCosts = listCombustibles();
			double energyRelatedOperationalCosts = EnergyKeyPerformanceIndicatorCalculator.calculateEnergyRelatedOperationalCosts(combustiblesForEnergyrelatedCosts, eKPIs);
			
			Map<String, List<String>> energyKpiMapForEnergyRelatedCosts = new HashMap<String, List<String>>();
			energyKpiMapForEnergyRelatedCosts.put(ResultCodes.Energy_Related_Operational_Costs.getLabel(), Arrays.asList(""+energyRelatedOperationalCosts));
			
			MeasuredUnit energyRelatedCostsUnit = new MeasuredUnit(requestedLocationId);
			energyRelatedCostsUnit.setAggregated(true);
			energyRelatedCostsUnit.setLabel(ResultCodes.Energy_Related_Operational_Costs.getLabel());
			energyRelatedCostsUnit.setQuantity(ResultCodes.Energy_Related_Operational_Costs.getQuantity());
			energyRelatedCostsUnit.setResultCode(ResultCodes.Energy_Related_Operational_Costs.getCode());
			energyRelatedCostsUnit.setValueUnit(ResultCodes.Energy_Related_Operational_Costs.getValueUnit());
			energyRelatedCostsUnit.setKeyPerformanceIndicator(energyKpiMapForEnergyRelatedCosts);
			energyRelatedCostsUnit.setTimeUnit(TimeMeasure.YEAR.name());
			
			eKPIs.setEnergyRelatedCosts(energyRelatedOperationalCosts);
			
			return energyRelatedCostsUnit;
		} catch(IfcException ie) {
			throw new PostProcessingException(ie);
		}
	}
	
	private Set<MeasuredUnit> handleTemperatureOverUnderruns(Map<String, Set<MeasuredUnit>> rawUnits, Ifc2x3DataModelProxy ifcModel) throws PostProcessingException {
		Set<MeasuredUnit> calculatedUnits = new HashSet<MeasuredUnit>();
		
		for(Map.Entry<String, Set<MeasuredUnit>> entry : rawUnits.entrySet()) {
			String requestedLocationId = entry.getKey();
			Set<MeasuredUnit> unitsForRequestedLocationId = entry.getValue();
			
			try {
	//			only rooms can be requested
				EIfcspace space = ifcModel.getIfcEntity(requestedLocationId, EIfcspace.class);
				if(space == null) throw new PostProcessingException("Global: "+requestedLocationId+" is no room");
				Map<String, MeasuredUnit> temperatureUnits = new TreeMap<String, MeasuredUnit>();
				Map<String, MeasuredUnit> heatingSetpointUnits = new TreeMap<String, MeasuredUnit>();
				Map<String, MeasuredUnit> coolingSetpointUnits = new TreeMap<String, MeasuredUnit>();
				List<MeasuredUnit> outdoorTemperatureUnits = new ArrayList<MeasuredUnit>();
				
				for(MeasuredUnit mu : unitsForRequestedLocationId) {
					String muId = mu.getId();
					if(mu.getResultCode()==ResultCodes.Room_Air_Temperature.getCode()) {
						temperatureUnits.put(muId,mu);
					} else if(mu.getResultCode()==ResultCodes.Set_Point_Room_Temperature_Heating.getCode()) {
						heatingSetpointUnits.put(muId,mu);
					} else if(mu.getResultCode()==ResultCodes.Set_Point_Room_Temperature_Cooling.getCode()) {
						coolingSetpointUnits.put(muId,mu);
					} else if(mu.getResultCode()==ResultCodes.Outdoor_air_temperature.getCode()) {
						outdoorTemperatureUnits.add(mu);
					}
				}			
				for (Map.Entry<String, MeasuredUnit> opEntry : temperatureUnits.entrySet()) {
					String key = opEntry.getKey();
	
					MeasuredUnit temperature = opEntry.getValue();
					MeasuredUnit heatingsetPoint = heatingSetpointUnits.get(key);
					MeasuredUnit coolingsetPoint = coolingSetpointUnits.get(key);
					MeasuredUnit outdoorTemperature = outdoorTemperatureUnits.get(0);
	//				temperatures have to be available
					if(temperature == null) throw new AssertionError();
						
	//				MeasuredUnit overrun = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureOverrun(operativeTemperature, coolingsetPoint);
	//				MeasuredUnit underrun = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureUnderrun(operativeTemperature, heatingsetPoint);
	//				calculatedUnits.add(overrun);
	//				calculatedUnits.add(underrun);
					calculatedUnits.add(heatingsetPoint);
					calculatedUnits.add(coolingsetPoint);
					calculatedUnits.add(temperature);
					calculatedUnits.add(outdoorTemperature);
						
	//				NOTE: because only one room can be requested with eKPI3.1 we return immediately the result
	//				this map iteration and the one for all eKPIs will be stopped
					return calculatedUnits;
				}
			} catch (IfcException ie) {
				throw new PostProcessingException(ie);
			}
		}
		return null;
	}
	
	private Set<MeasuredUnit> handleThermalComfort(Map<String, Set<MeasuredUnit>> rawUnits, Ifc2x3DataModelProxy ifcModel) throws PostProcessingException {
		Set<MeasuredUnit> calculatedUnits = new HashSet<MeasuredUnit>();
		for(Map.Entry<String, Set<MeasuredUnit>> entry : rawUnits.entrySet()) {
			String requestedLocationId = entry.getKey();
			Set<MeasuredUnit> unitsForRequestedLocationId = entry.getValue();
		
			try{
//				only rooms can be requested
				EIfcspace space = ifcModel.getIfcEntity(requestedLocationId, EIfcspace.class);
				if(space == null) throw new PostProcessingException("Global: "+requestedLocationId+" is no room");
				Map<String, MeasuredUnit> temperatureUnits = new TreeMap<String, MeasuredUnit>();
				Map<String, MeasuredUnit> heatingSetpointUnits = new TreeMap<String, MeasuredUnit>();
				Map<String, MeasuredUnit> coolingSetpointUnits = new TreeMap<String, MeasuredUnit>();
				
				temperatureUnits = new TreeMap<String, MeasuredUnit>();
				heatingSetpointUnits = new TreeMap<String, MeasuredUnit>();
				coolingSetpointUnits = new TreeMap<String, MeasuredUnit>();
				
				for(MeasuredUnit mu : unitsForRequestedLocationId) {
					String muId = mu.getId();
					int rc = mu.getResultCode();
					if(rc==ResultCodes.Room_Air_Temperature.getCode()) {
						temperatureUnits.put(muId,mu);
					} else if(rc==ResultCodes.Set_Point_Room_Temperature_Heating.getCode()) {
						heatingSetpointUnits.put(muId,mu);
					} else if(rc==ResultCodes.Set_Point_Room_Temperature_Cooling.getCode()) {
						coolingSetpointUnits.put(muId,mu);
					}
				}				
				for (Map.Entry<String, MeasuredUnit> opEntry : temperatureUnits.entrySet()) {
					String key = opEntry.getKey();

					MeasuredUnit temperature = opEntry.getValue();
					MeasuredUnit heatingsetPoint = heatingSetpointUnits.get(key);
					MeasuredUnit coolingsetPoint = coolingSetpointUnits.get(key);
//					temperatures have to be available
					if(temperature == null) throw new AssertionError();
						
					MeasuredUnit overrun = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureOverrun(temperature, coolingsetPoint);
					MeasuredUnit underrun = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureUnderrun(temperature, heatingsetPoint);
					calculatedUnits.add(overrun);
					calculatedUnits.add(underrun);
						
//					NOTE: because only one room can be requested with eKPI3.1 we return immediately the result
//					this map iteration and the one for all eKPIs will be stopped
					return calculatedUnits;	
				}				
			} catch (IfcException ie) {
				throw new PostProcessingException(ie);
			} catch(EnergyKeyPerformanceIndicatorException eKPIEx) {
				throw new PostProcessingException(eKPIEx);
			}
		}
		return null;
	}
	
	private Set<MeasuredUnit> handleThermalComfortGlobalValues(Map<String, Set<MeasuredUnit>> rawUnits, Ifc2x3DataModelProxy ifcModel) throws PostProcessingException {
		Set<MeasuredUnit> calculatedUnits = new HashSet<MeasuredUnit>();
		
		for(Map.Entry<String, Set<MeasuredUnit>> entry : rawUnits.entrySet()) {
			String requestedLocationId = entry.getKey();
			Set<MeasuredUnit> unitsForRequestedLocationId = entry.getValue();
			
			try {
//				only rooms can be requested
				EIfcspace space = ifcModel.getIfcEntity(requestedLocationId, EIfcspace.class);
				if(space == null) throw new PostProcessingException("Global: "+requestedLocationId+" is no room");
				Map<String, MeasuredUnit> temperatureUnits = new TreeMap<String, MeasuredUnit>();
				Map<String, MeasuredUnit> heatingSetpointUnits = new TreeMap<String, MeasuredUnit>();
				Map<String, MeasuredUnit> coolingSetpointUnits = new TreeMap<String, MeasuredUnit>();
				
				temperatureUnits = new TreeMap<String, MeasuredUnit>();
				heatingSetpointUnits = new TreeMap<String, MeasuredUnit>();
				coolingSetpointUnits = new TreeMap<String, MeasuredUnit>();
				
				for(MeasuredUnit mu : unitsForRequestedLocationId) {
					String muId = mu.getId();
					int rc = mu.getResultCode();
					if(rc==ResultCodes.Room_Air_Temperature.getCode()) {
						temperatureUnits.put(muId,mu);
					} else if(rc==ResultCodes.Set_Point_Room_Temperature_Heating.getCode()) {
						heatingSetpointUnits.put(muId,mu);
					} else if(rc==ResultCodes.Set_Point_Room_Temperature_Cooling.getCode()) {
						coolingSetpointUnits.put(muId,mu);
					}
				}
							
				for (Map.Entry<String, MeasuredUnit> opEntry : temperatureUnits.entrySet()) {
					String key = opEntry.getKey();

					MeasuredUnit temperature = temperatureUnits.get(key);
					MeasuredUnit heatingsetPoint = heatingSetpointUnits.get(key);
					MeasuredUnit coolingsetPoint = coolingSetpointUnits.get(key);
//					temperatures have to be available
					if(temperature == null) throw new AssertionError();

					TreeMap<Integer,Integer> overrunStagged = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureOverrunCountStaggered(temperature, coolingsetPoint);
					TreeMap<Integer,Integer> underrunStagged = EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureUnderrunCountStaggered(temperature, heatingsetPoint);
					
					List<String> distances = new ArrayList<String>();
					List<String> overrunText = new ArrayList<String>();
					for(Map.Entry<Integer, Integer> entryOver : overrunStagged.entrySet()) {
						String distance = String.valueOf(entryOver.getKey());
						int overCount = entryOver.getValue();
						overrunText.add(String.valueOf(overCount));
						if(!distances.contains(distance)) {
							distances.add(distance);
						}
					}
					List<String> underrunText = new ArrayList<String>();
					for(Map.Entry<Integer, Integer> entryUnder : underrunStagged.entrySet()) {
						String distance = String.valueOf(entryUnder.getKey());
						int underCount = entryUnder.getValue();
						underrunText.add(String.valueOf(underCount));
						if(!distances.contains(distance)) {
							distances.add(distance);
						}
					}
					
					Map<String, List<String>> keyPerformanceIndicator = new LinkedHashMap<String, List<String>>();
					keyPerformanceIndicator.put("Distances", distances);
					keyPerformanceIndicator.put("OverrunCount", overrunText);
					keyPerformanceIndicator.put("UnderrunCount", underrunText);
					
					MeasuredUnit thermalComfortGlobalUnit = new MeasuredUnit(requestedLocationId);
					thermalComfortGlobalUnit.setAggregated(true);
					thermalComfortGlobalUnit.setLabel(ResultCodes.Thermal_Comfort_Global_Values.getLabel());
					thermalComfortGlobalUnit.setQuantity(ResultCodes.Thermal_Comfort_Global_Values.getQuantity());
					thermalComfortGlobalUnit.setResultCode(ResultCodes.Thermal_Comfort_Global_Values.getCode());
					thermalComfortGlobalUnit.setValueUnit(ResultCodes.Thermal_Comfort_Global_Values.getValueUnit());
					thermalComfortGlobalUnit.setKeyPerformanceIndicator(keyPerformanceIndicator);
					thermalComfortGlobalUnit.setTimeUnit(TimeMeasure.YEAR.name());
					
					calculatedUnits.add(thermalComfortGlobalUnit);
					
//					NOTE: because only one room can be requested with eKPI3.1 we return immediately the result
//					this map iteration and the one for all eKPIs will be stopped
					return calculatedUnits;
				}					
			} catch(EnergyKeyPerformanceIndicatorException eKPIEx) {
				throw new PostProcessingException(eKPIEx);
			} catch (IfcException e) {
				throw new PostProcessingException(e);
			}
		}
		return null;
	}
	
	/**
	 * Check if requested code is an eKPI 
	 * @param code
	 * @return
	 */
	private boolean isEKPI(ResultCodes code) {
		switch(code) {
//		eKPI1.1a
		case Heating_Net_Energy:
			return true;
		case Heating_Final_Energy:
			return true;
//		eKPI1.2
		case Cooling_Net_Energy:
			return true;
		case Cooling_Final_Energy:
			return true;
//		eKPI1.1a & 1.2
		case Heating_And_Cooling_Net_Energy:
			return true;
		case Heating_And_Cooling_Final_Energy:
			return true;
//		eKPI2.1
		case Greenhouse_Gas_Emissions:
			return true;
//		eKPI3.1.a
		case Temperature_Over_Under_Runs:
			return true;
//		eKPI3.1.b
		case Thermal_Comfort:
			return true;
//		eKPI3.1.c
		case Thermal_Comfort_Global_Values:
			return true;
//		eKPI 6.1
		case Investment_Costs:
			return true;
//		eKPI6.2
		case Energy_Related_Operational_Costs:
			return true;
//		eKPI6.3
		case Non_Energy_Related_Operational_Costs:
			return true;
//		eKPI6
		case Lifecycle_Costs:
			return true;
		default: return false;
		}
	}
	
	@Override
	public List<Combustible> listCombustibles() {
		String pathToResources = configurationService.getProperty(ConfigurationService.PROPERTIES.PATH_RESOURCES);
		if(!StringUtils.endsWith(pathToResources, "/")) {
			pathToResources += File.separator;
		}
		String pathToCombustibles = pathToResources+COMBUSTIBLE_FILE_NAME;
		File combustibleFile = new File(pathToCombustibles);
		if(combustibleFile.exists()) {
			try {
				EList<Combustible> combustibles = CombustibleContainerDeserializer.deserialize(pathToCombustibles);
				List<Combustible> list = new ArrayList<Combustible>();
				for(Combustible c : combustibles) {
					list.add(c);
				}
				return list;
			} catch (IOException io) {
				LOG.error(io.getMessage());
			}
		}
		return null;
	}

	public void setEnergyKeyPerformanceIndicatorDao(
			EnergyKeyPerformanceIndicatorDao energyKeyPerformanceIndicatorDao) {
		this.energyKeyPerformanceIndicatorDao = energyKeyPerformanceIndicatorDao;
	}

	@Override
	public EnergyKeyPerformanceIndicators getEKPIBySimulationId(Integer simId) {
		EnergyKeyPerformanceIndicators eKPI = energyKeyPerformanceIndicatorDao.getEKPIBySimulationId(simId);
		return eKPI;
	}


	@Override
	public void saveEnergyKeyPerformanceIndicators(
			EnergyKeyPerformanceIndicators eKPIs) {
		if(eKPIs.getId() != null) {
			LOG.debug("Updating energy key performance indicator: {}", new Object[]{eKPIs});
			energyKeyPerformanceIndicatorDao.updateEnergyKeyPerformanceIndicators(eKPIs);
		}//	not saved currently
		else {
			LOG.debug("Saving energy key performance indicator: {}", new Object[]{eKPIs});
			energyKeyPerformanceIndicatorDao.insertEnergyKeyPerformanceIndicators(eKPIs);
		}
	}

	@Override
	public CombustibleContainer loadCombustibles(File file) throws IOException{
		ResourceSet resourceSet = new ResourceSetImpl();
//		programmatically registration of Ecore like in plugin.xml
		resourceSet.getPackageRegistry().put(CombustiblePackage.eINSTANCE.getNsURI(), CombustiblePackage.eINSTANCE);
		Map<String,Object> factoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		factoryMap.put("xmi", new CombustibleResourceFactoryImpl());
		
		URI uri = URI.createFileURI(file.getAbsolutePath());	
		
		Resource resource = resourceSet.getResource(uri, true);
		
		resource.load(loadOptions);
		
		EList<EObject> contents = resource.getContents();
		CombustibleContainer combustibleContainer = (CombustibleContainer) contents.get(0); //alle Brennwerte = Container | Combustible ein Brennelement
		return combustibleContainer;
	}

	@Override
	public void saveCombustibles(File file, CombustibleContainer combustibleContainer) throws IOException{
		URI uri = URI.createFileURI(file.getAbsolutePath());
		
		Resource resource = new CombustibleResourceFactoryImpl().createResource(uri);
		
//		Resource resource = resourceSet.createResource(uri);
		EList<EObject> contents = resource.getContents();
		contents.add(combustibleContainer);
		resource.save(loadOptions);
	}
	
	private class BuildingElementCostPerSpaceCallable implements Callable<Double> {

		private final Ifc2x3DataModelProxy ifcModel;
		private final OntologyModel ontologyModel;
		private final ConcurrentMap<String, Double> costsForConstructionFilePath;
		private final EIfcspace space;
		
		public BuildingElementCostPerSpaceCallable(Ifc2x3DataModelProxy ifcModel, EIfcspace space, OntologyModel ontologyModel, ConcurrentMap<String, Double> costsForConstructionFilePath) {
			this.ifcModel = ifcModel;
			this.space = space;
			this.ontologyModel = ontologyModel;
			this.costsForConstructionFilePath = costsForConstructionFilePath;
		}
		
		@Override
		public Double call() throws Exception {
			double investmentCostsOfBuildingElement = 0.0;
			//	retrieve space boundaries because the area of the elements is only given through the space boundary calculation by BSPro
			//	=> otherwise we cannot calculate the investment costs currently
			EIfcrelspaceboundary[] spaceBoundaries = ifcModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
			LOG.trace("Analyzing room: {} which have {} building elements", new Object[]{space, spaceBoundaries.length});
			for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
				EIfcelement element = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
				String elementGuid = ifcModel.getGlobalId(element);
				if(spaceBoundary.testDescription(spaceBoundary)) {
					String description = spaceBoundary.getDescription(spaceBoundary);
					double area	= 0.0;
					if(description != null) {
						description = description.replace(description.substring(0, description.indexOf(AREA_PATTERN)),"");
						String areaText = description.replaceFirst(AREA_PATTERN, "");
						if(areaText != null) {
							area = Double.valueOf(areaText);
						}
						long timeBeforeQuery = System.currentTimeMillis();
						List<Map<String, String>> results = EeBimQueryExecutor.findConstructionTemplate(elementGuid, ontologyModel);
						long timeAfterQuery = System.currentTimeMillis();
						LOG.trace("Time consumed (hh:mm:ss:SSS): {} for finding the construction template for element: {}",
								new Object[]{sdf.format(new Date(timeAfterQuery-timeBeforeQuery)), elementGuid});
						if(results != null) {
							String tplPath = results.get(0).get(VARIABLES.tplPath);
							if(StringUtils.isEmpty(tplPath)) continue;								
							Double costsPerSquareMeter = costsForConstructionFilePath.get(tplPath);
							LOG.trace("Construction template: {} of element: {} has costs per square meter: {}",
									new Object[]{tplPath, elementGuid, costsPerSquareMeter});
							if(costsPerSquareMeter == null) continue;

							investmentCostsOfBuildingElement += EnergyKeyPerformanceIndicatorCalculator.calculateInvestmentCostsOfBuildingElement(costsPerSquareMeter, area);
						}
					}
				}
			}
			return investmentCostsOfBuildingElement;	
		}

	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public void setEnergySimulationService(
			EnergySimulationService energySimulationService) {
		this.energySimulationService = energySimulationService;
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	public void setEnergyResultsDao(EnergyResultsDao energyResultsDao) {
		this.energyResultsDao = energyResultsDao;
	}

}
