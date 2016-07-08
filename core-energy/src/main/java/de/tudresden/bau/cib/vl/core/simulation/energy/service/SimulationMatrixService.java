package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.EIfcspatialstructureelement;
import de.tudresden.bau.cib.simmatrix.FileFormat;
import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.simmatrix.TWindowType;
import de.tudresden.bau.cib.simmatrix.TWindowTypeVariant;
import de.tudresden.bau.cib.simmatrix.TimePeriod;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.SimulationMatrixException;
import de.tudresden.bau.cib.vl.core.util.Pair;

public interface SimulationMatrixService {
	
	enum Type {
		SensitivityAnalysis,
		DesignAlternatives,
		StochasticSimulation
		;
		
		public String getName() {
			return name();
		}
	}
	
	enum AssignmentGroup {
		SpaceGroup,
		ElementGroup
	}
	
	static final String EXTENSION = "simmatrix";

	/**
	 * Creates an empty matrix with {@link Type#DesignAlternatives} as initial matrix type.
	 * @param id The identifier for the matrix.
	 * @return The simulation matrix object.
	 */
	TSimulationMatrix createMatrix(String id);
	
	String addOccupancyTypeVariant(String occupancyUri, TimePeriod period,
			FileFormat type, TSimulationMatrix matrix);
	
	/**
	 * Adds targets to the matrix.
	 * @param ifcEntities
	 * @param matrix
	 * @throws SimulationMatrixException
	 */
	void addTargets(Collection<? extends EIfcspatialstructureelement> ifcEntities, TSimulationMatrix matrix) throws SimulationMatrixException;
	
	/**
	 * Adds a construction type variant to the matrix.
	 * @param constructionResource
	 * @param matrix
	 * @throws SimulationMatrixException
	 * @return The ID of the construction type variant.
	 */
	String addConstructionTypeVariant(ConstructionTemplate constructionResource, TSimulationMatrix matrix) throws SimulationMatrixException;
	
	/**
	 * Adds a construction type variant to the matrix by specifying the material layers with their material and thickness.
	 * @param constructionUri
	 * @param layers
	 * @param matrix
	 * @return The ID of the construction type variant.
	 * @throws SimulationMatrixException
	 */
	String addConstructionTypeVariant(String constructionUri, Map<Integer, MaterialLayer> layers, TSimulationMatrix matrix);
	
	/**
	 * Adds a window type to the matrix if it doesn't exists.
	 * @param windowType
	 * @param matrix
	 */
	void addWindowType(TWindowType windowType, TSimulationMatrix matrix);
	
	String addWindowTypeVariant(String windowTypeName, TWindowTypeVariant variant, TSimulationMatrix matrix);
	
	String addWindowTypeVariant(String windowTypeName, 
			String glassFraction, String glassFractionUnit, String frameFraction, String frameFractionUnit, 
			String thermalTransmittance, String thermalTransmittanceUnit, String solarHeatGain, String solarHeatGainUnit, 
			String shadingFactor, String shadingFactorUnit, TSimulationMatrix matrix);
	
	String createAssignmentGroup(Set<String> elementIds, AssignmentGroup group, TSimulationMatrix matrix) throws SimulationMatrixException;
	
	String addWeatherDataSetVariant(String uri, TSimulationMatrix matrix);
	
	/**
	 * Adds a combination to the matrix. 
	 * @param varRefId Reference to the variant (<b>Mandatory</b>)
	 * @param keyRefId Reference to an assignment group (<b>Optional</b>)
	 * @param matrix
	 * @throws SimulationMatrixException
	 */
	void addCombinations(String varRefId, String keyRefId, TSimulationMatrix matrix) throws SimulationMatrixException;
	
	/**
	 * Saves a matrix to the given URI.
	 * @param uri
	 * @param matrix
	 * @throws SimulationMatrixException
	 */
	void saveMatrix(URI uri, TSimulationMatrix matrix) throws SimulationMatrixException;

	/**
	 * Loads a matrix from a URL.
	 * @param url The file URL
	 * @return The simulation matrix object.
	 * @throws URISyntaxException
	 */
	TSimulationMatrix loadMatrix(URL url) throws URISyntaxException;

	/**
	 * Loads a matrix from file path.
	 * @param filePath The local file path.
	 * @return The simulation matrix object.
	 */
	TSimulationMatrix loadMatrix(String filePath);
	
	/**
	 * Removes a combination in the matrix.
	 * @param combinationId
	 * 	 * @param matrix
	 */
	void removeCombinationId(String combinationId, TSimulationMatrix matrix);


	/**
	 * Removes multiple combinations in the matrix.
	 * @param combinationIds
	 * 	 * @param matrix
	 */
	void removeCombinationIds(Collection<String> combinationIds, TSimulationMatrix matrix);
	
	public void removeVariantFromCombination(List<Pair<String,String>> ids, TSimulationMatrix matrix);
	
	public void removeVariantFromCombination(String variationId, String ifcElementId, TSimulationMatrix matrix);
}
