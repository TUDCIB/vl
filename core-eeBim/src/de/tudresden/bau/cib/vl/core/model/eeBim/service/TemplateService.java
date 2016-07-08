package de.tudresden.bau.cib.vl.core.model.eeBim.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.core.model.eeBim.exception.ResourceServiceException;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;

public interface TemplateService {
	
	static final String COMBUSTIBLE_FILE_NAME = "combustibles.xml";
	
	/**
	 * Calculates the u-value of the assigned material layers.
	 * [see {@link http://de.wikipedia.org/wiki/W%C3%A4rmedurchgangskoeffizient}]
	 * @param materialLayers
	 * @param materialTemplates Because new added layers didn't include the lambda and overall heat transfer resistance all 
	 * 		available material templates are needed to look into the assigned templates.
	 * @return The u-value with 2 positions after the decimal point
	 */
	Double calculateUValue(Map<Integer, MaterialLayer> materialLayers, Collection<MaterialTemplate> materialTemplates);
	
	MaterialTemplate findMaterialTemplate(String tplName,
			List<MaterialTemplate> materialTemplates) throws ParsingException,
			IOException;

	ClimateLocationTemplate findClimateTemplate(double latitude, double longitude, List<ClimateLocationTemplate> templates);
	
	ClimateLocationTemplate findClimate(String uri, List<ClimateLocationTemplate> climateResources);
	
	/**
	 * Search in a collection of templates the template with the given URI.
	 * @param uri The source file URI
	 * @param resources A collection with all templates.
	 * @return The template if available.
	 */
	<T extends Resource> T findTemplateByUri(String uri, Collection<T> resources);
	
	ConstructionTemplate findConstructionTemplateByName(
			String tplName, 
			Collection<ConstructionTemplate> constructionTemplates);
	
	ConstructionTemplate findConstructionTemplateByUri(String uri, Collection<ConstructionTemplate> constructionTemplates);
	
	MeasuredUnit getDirectRadiation(ClimateLocationTemplate climateTemplate) throws IOException;
	
	MeasuredUnit getDiffuseRadiation(ClimateLocationTemplate climateTemplate) throws IOException;
	
	MeasuredUnit getTemperaturesOfReferenceYear(ClimateLocationTemplate climateTemplate) throws IOException;

	/**
	 * @return Default climate templates.
	 */
	List<ClimateLocationTemplate> listClimateResources();
	
	/**
	 * @param userId
	 * @return Default climate templates and user defined climate templates.
	 * @throws FileNotFoundException
	 * @throws ParsingException
	 */
	List<ClimateLocationTemplate> listClimateResources(Integer userId) throws FileNotFoundException, ParsingException;
	
	/**
	 * @return Default construction templates.
	 */
	List<ConstructionTemplate> listConstructionResources();
	
	/**
	 * @param userId
	 * @return Default and user defined construction templates.
	 * @throws IOException
	 * @throws ParsingException
	 */
	List<ConstructionTemplate> listConstructionResources(Integer userId) throws IOException, ParsingException;
	
	/**
	 * @return Default space type templates.
	 */
	List<SpaceTypeTemplate> listOccupancyResources();
	
	/**
	 * Pay Attention: Because there is a two step deserialization of Space Type Templates and Schedule Templates it can be very time-consuming.
	 * @param userId
	 * @return Default and user defined space type templates.
	 * @throws FileNotFoundException
	 * @throws ParsingException
	 */
	List<SpaceTypeTemplate> listOccupancyResources(Integer userId) throws FileNotFoundException, ParsingException;
	
	/**
	 * @return Default material templates.
	 */
	List<MaterialTemplate> listMaterialTemplates();
	
	/**
	 * @param userId
	 * @return Default and user defined material templates.
	 * @throws ParsingException
	 * @throws IOException
	 */
	List<MaterialTemplate> listMaterialTemplates(Integer userId) throws ParsingException, IOException;
	
	List<MaterialTemplate> listMaterialsOfConstruction(ConstructionTemplate construction);
	
	File stochastifyMaterial(InputStream resource, double meanValue, double standardDeviation, int numberOfSamples, String destPath) 
			throws ResourceServiceException;
	
	/**
	 * Deletes the template if it is in the user repository. 
	 * If it is not in the user repository it is a general template which cannot be deleted.
	 * @param userId
	 * @param template
	 * @return
	 */
	boolean deleteTemplate(Integer userId, Resource template);
	
	/**
	 * @param userId
	 * @param filePath
	 * @return The new file path of the construction template saved in the user repository.
	 * @throws IOException
	 */
	String uploadConstructionTemplates(Integer userId, String filePath) throws IOException;
	
	void uploadClimateTemplates(Integer userId, String[] fileNames) throws IOException;
	
	boolean uploadSpaceTypeTemplates(Integer userId, String[] fileNames) throws IOException;
	
	/**
	 * @param userId
	 * @param template
	 * @return The new file path of the construction template saved in the user repository.
	 * @throws IOException
	 * @throws ParsingException
	 */
	String saveConstructionTemplate(Integer userId, ConstructionTemplate template) throws IOException, ParsingException;
	
	boolean saveSpaceTypeTemplate(Integer userId, SpaceTypeTemplate template) throws ParsingException, IOException;

	InputStream stochastifyConstruction(ConstructionTemplate resource,
			double meanValue, double standardDeviation, int numberOfSamples)
			throws ResourceServiceException;
	
	/**
	 * TODO javadoc
	 * @param meanValue
	 * @param standardDeviation
	 * @param numberOfSamples
	 * @author <a href="mailto:Frank.Noack@tu-dresden.de">Frank Noack</a>
	 * @return
	 */
	double[] generateSamples(double meanValue, double standardDeviation, int numberOfSamples);
}
