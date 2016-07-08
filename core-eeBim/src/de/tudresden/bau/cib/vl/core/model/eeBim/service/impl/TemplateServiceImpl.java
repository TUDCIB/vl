package de.tudresden.bau.cib.vl.core.model.eeBim.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.UserRepository;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;
import de.tudresden.bau.cib.vl.core.model.eeBim.exception.ResourceServiceException;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.ClimateLocationDeserializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.ClimateTemplateTemperatureDeserializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.CombustibleContainerSerializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.ConstructionTemplateDeserializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.ConstructionTemplateSerializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.MaterialTemplateDeserializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.ScheduleTemplatesDeserializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.ScheduleTemplatesSerializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.SpaceTypeTemplatesDeserializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.SpaceTypeTemplatesSerializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;
import de.tudresden.bau.cib.vl.core.model.energy.Schedule;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.service.FileService;

/**
 * Service for searching, uploading and deleting templates like construction, space type, climate or material templates of users.
 * TODO use MongoDB as document database: {@link http://www.mkyong.com/spring-batch/spring-batch-example-xml-file-to-database/) if Google Cache istoo slow
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class TemplateServiceImpl extends AbstractResourceServiceImpl {
	
	private static final Logger LOG = LoggerFactory.getLogger(TemplateServiceImpl.class);
	
	private LoadingCache<Integer,List<ConstructionTemplate>> constructionTplCache;
	private LoadingCache<Integer,List<MaterialTemplate>> materialTplCache;
  
    private long constructionTickerTime;
    private long materialTickerTime;
    
    private static final String PATH_TO_DB_CLIMATE_DIR = "resources/IBK/resources/DB_Climate/";
    private static final String PATH_TO_DB_CONSTRUCTIONS_DIR = "resources/IBK/resources/DB_Constructions/";
    private static final String PATH_TO_DB_MATERIALS_DIR = "resources/IBK/resources/DB_Materials/";
    private static final String PATH_TO_DB_TEMPLATES_DIR = "resources/IBK/resources/DB_Templates/";
    
    private static final String USER_CONSTRUCTIONS = "constructionTemplates";
    private static final String USER_CLIMATE_TEMPLATES = "climateTemplates";
    private static final String USER_SPACETYPE_TEMPLATES = "spaceTypeTemplates";
    private static final String USER_MATERIAL_TEMPLATES = "materialTemplates";
    
	private void setupCaches() {
		LOG.debug("Initializing template caches");
		final ConcurrentHashMap<Integer,List<ConstructionTemplate>> constructionTpls = new ConcurrentHashMap<Integer, List<ConstructionTemplate>>();
		final ConcurrentHashMap<Integer,List<MaterialTemplate>> materialTpls = new ConcurrentHashMap<Integer, List<MaterialTemplate>>();
		
		constructionTickerTime = System.nanoTime();  
		Ticker constructionTicker = new Ticker(){  
			@Override  
			public long read() {  
				return constructionTickerTime;  
			}              
		};   
		
		materialTickerTime = System.nanoTime();  
		Ticker materialTicker = new Ticker(){  
			@Override  
			public long read() {  
				return materialTickerTime;  
			}              
		}; 

		constructionTplCache = CacheBuilder.newBuilder()  
				.expireAfterAccess(
						Long.valueOf(configurationService.getProperty(PROPERTIES.SESSION_TIME_MINUTES)), 
						TimeUnit.MINUTES)   
				.concurrencyLevel(6)  
				.maximumSize(12)
				.ticker(constructionTicker)  
				.build(new CacheLoader<Integer, List<ConstructionTemplate>>(){  
					@Override  
					public List<ConstructionTemplate> load(Integer key) throws Exception {    
						return constructionTpls.get(key);  
					}  
				});
		
		materialTplCache = CacheBuilder.newBuilder()  
				.expireAfterAccess(
						Long.valueOf(configurationService.getProperty(PROPERTIES.SESSION_TIME_MINUTES)), 
						TimeUnit.MINUTES)  
				.concurrencyLevel(6)  
				.maximumSize(12)
				.ticker(materialTicker)  
				.build(new CacheLoader<Integer, List<MaterialTemplate>>(){  
					@Override  
					public List<MaterialTemplate> load(Integer key) throws Exception {    
						return materialTpls.get(key);  
					}  
				});
	}
	
	private void putConstructionTemplatesToCache(Integer userId, List<ConstructionTemplate> tpls) {
		this.constructionTplCache.put(userId, tpls);
	}

	private List<ConstructionTemplate> getConstructionTemplatesFromCache(Integer userId) {
		List<ConstructionTemplate> tpl = constructionTplCache.getIfPresent(userId);
		constructionTickerTime = System.nanoTime(); 
		return tpl;
	}
	
	private void putMaterialTemplatesToCache(Integer userId, List<MaterialTemplate> tpls) {
		this.materialTplCache.put(userId, tpls);
	}

	private List<MaterialTemplate> getMaterialTemplatesFromCache(Integer userId) {
		List<MaterialTemplate> tpls = materialTplCache.getIfPresent(userId);
		materialTickerTime = System.nanoTime(); 
		return tpls;
	}

	@Override
	public List<ClimateLocationTemplate> listClimateResources() {
		File climateTemplatesDirectory = new File(PATH_TO_DB_CLIMATE_DIR);
		File[] climateDirectoriesInDirectory = climateTemplatesDirectory.listFiles();
//		we are using the description.xml
//		Set<File> templates = FileManager.getFiles(new File(climateTemplatesDirectory), "xml", true);
		List<ClimateLocationTemplate> climateTemplates = null;
		for(File f : climateDirectoriesInDirectory) {
			ClimateLocationTemplate template;
			try {
				template = ClimateLocationDeserializer.deserialize(f.getAbsolutePath());
				if(template != null) {
					if(climateTemplates == null) climateTemplates = new ArrayList<ClimateLocationTemplate>();
					climateTemplates.add(template);
				}				
			} catch (ParsingException e) {
				LOG.error(e.getMessage());
			} catch (FileNotFoundException e) {
				LOG.error(e.getMessage());
			}			
		}
//		LOG.trace("Found: {} climate templates in directory: {}", new Object[]{climateTemplates, climateTemplatesDirectory});
		return climateTemplates;
	}
	
	@Override
	public List<ClimateLocationTemplate> listClimateResources(Integer userId) throws FileNotFoundException, ParsingException {
		List<ClimateLocationTemplate> templates = listClimateResources();
		if(userId > -1) {
			//	add user specific templates
			String userRepositoryPath = userService.getUserRepositoryPath(userId);
			String pathToTemplates = userRepositoryPath+File.separator+USER_CLIMATE_TEMPLATES;
			File climateTemplatesDirectory = new File(pathToTemplates);
			if(climateTemplatesDirectory.exists()) {
				File[] climateDirectoriesInDirectory = climateTemplatesDirectory.listFiles();
				List<ClimateLocationTemplate> userClimateTemplates = null;
				for(File f : climateDirectoriesInDirectory) {
					ClimateLocationTemplate template = ClimateLocationDeserializer.deserialize(f.getAbsolutePath());
					if(template != null) {
						if(userClimateTemplates == null) userClimateTemplates = new ArrayList<ClimateLocationTemplate>();
						userClimateTemplates.add(template);
					}				
				}
				return createUniqueList(templates, userClimateTemplates);
			}
		}
		return templates;
	}
	
	@Override
	public List<ConstructionTemplate> listConstructionResources() {
		Set<File> files = FileService.getFiles(new File(PATH_TO_DB_CONSTRUCTIONS_DIR), "d6p", false);
		List<ConstructionTemplate> templates = null;
		for(File f : files) {
			ConstructionTemplate template = null;
			try {
				template = ConstructionTemplateDeserializer.deserialize(f.getAbsolutePath());
			} catch (ParsingException e) {
				LOG.error(e.getMessage() +" "+f.getAbsolutePath());
			} catch (IOException e) {
				LOG.error(e.getMessage() +" "+f.getAbsolutePath());
			}
			if(template != null) {
				if(templates == null) templates = new ArrayList<ConstructionTemplate>();
				templates.add(template);
			}	
		}
//		LOG.trace("Found: {} construction templates in directory: {}", new Object[]{templates, constructionTemplatesDirectory});
		return templates;
	}
	
	@Override
	public List<ConstructionTemplate> listConstructionResources(Integer userId) throws IOException, ParsingException {
		List<ConstructionTemplate> templates = getConstructionTemplatesFromCache(userId);
		if(templates == null) {
			templates = listConstructionResources();
			if(userId > -1) {
				// add user specific templates
				String userRepositoryPath = userService.getUserRepositoryPath(userId);
				String pathToTemplates = userRepositoryPath+File.separator+USER_CONSTRUCTIONS;
				LOG.debug("List paths to user specific construction templates: {} of user: {}",
						new Object[]{pathToTemplates, userId});
				if(new File(pathToTemplates).exists()) {
					Set<File> files = FileService.getFiles(new File(pathToTemplates), "d6p", false);
					List<ConstructionTemplate> userTemplates = new ArrayList<ConstructionTemplate>();
					for(File f : files) {
						ConstructionTemplate template = ConstructionTemplateDeserializer.deserialize(f.getAbsolutePath());
						userTemplates.add(template);
					}
					templates = createUniqueList(templates, userTemplates);
				}
			}
			putConstructionTemplatesToCache(userId, templates);
		}
		return templates;
	}
	
	@Override
	public List<MaterialTemplate> listMaterialsOfConstruction(ConstructionTemplate construction) {
		List<MaterialTemplate> materials = new ArrayList<MaterialTemplate>();
		Map<Integer,MaterialLayer> layers = construction.getMaterialLayers();
		for (Map.Entry<Integer, MaterialLayer> entry : layers.entrySet()) {
			MaterialLayer layer = entry.getValue();
			materials.add(layer.getTemplate());
			
		}
		return materials;
	}
	
	@Override
	public List<MaterialTemplate> listMaterialTemplates() {
		Set<File> files = FileService.getFiles(new File(PATH_TO_DB_MATERIALS_DIR), "m6", false);
		List<MaterialTemplate> templates = null;

		for(File f : files) {
			MaterialTemplate template = null;
			try {
				template = MaterialTemplateDeserializer.deserialize(f.getAbsolutePath());
			} catch (ParsingException e) {
				LOG.error(e.getMessage());
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
			if(template != null) {
				if(templates == null) templates = new ArrayList<MaterialTemplate>();
				templates.add(template);
			}	
		}
//		LOG.trace("Found: {} material templates in directory: {}", new Object[]{templates, materialTemplatesDirectory});
		return templates;
	}
	
	@Override
	public List<MaterialTemplate> listMaterialTemplates(Integer userId) throws ParsingException, IOException {
		List<MaterialTemplate> templates = getMaterialTemplatesFromCache(userId);
		
		if(templates == null) {
			templates = listMaterialTemplates();
			if(userId > -1) {
				// add user specific templates
				String userRepositoryPath = userService.getUserRepositoryPath(userId);
				String pathToTemplates = userRepositoryPath+File.separator+USER_MATERIAL_TEMPLATES;
				if(new File(pathToTemplates).exists()) {
					Set<File> files = FileService.getFiles(new File(pathToTemplates), "m6", false);
					List<MaterialTemplate> userTemplates = new ArrayList<MaterialTemplate>();
					for(File f : files) {
						MaterialTemplate template = MaterialTemplateDeserializer.deserialize(f.getAbsolutePath());
						userTemplates.add(template);
					}
					templates = createUniqueList(templates, userTemplates);
					putMaterialTemplatesToCache(userId, templates);
				}
			}
		}
		return templates;
	}
	
	@Override
	public MeasuredUnit getDirectRadiation(
			ClimateLocationTemplate climateTemplate) throws IOException {
		String directoryPath = climateTemplate.getSourceFileUri();
		Set<File> xmlFiles = FileService.getFiles(new File(directoryPath), "ccd", false);
		for(File xmlFile : xmlFiles) {
			if(xmlFile.getName().equalsIgnoreCase("DirectRadiation.ccd")) {
				return ClimateTemplateTemperatureDeserializer.deserializeTemperatures(xmlFile.getAbsolutePath());
			}
		}
		return null;
	}
	
	@Override
	public MeasuredUnit getDiffuseRadiation(
			ClimateLocationTemplate climateTemplate) throws IOException {
		String directoryPath = climateTemplate.getSourceFileUri();
		Set<File> xmlFiles = FileService.getFiles(new File(directoryPath), "ccd", false);
		for(File xmlFile : xmlFiles) {
			if(xmlFile.getName().equalsIgnoreCase("DiffuseRadiation.ccd")) {
				return ClimateTemplateTemperatureDeserializer.deserializeTemperatures(xmlFile.getAbsolutePath());
			}
		}
		return null;
	}
	
	@Override
	public MeasuredUnit getTemperaturesOfReferenceYear(
			ClimateLocationTemplate climateTemplate) throws IOException {
		String directoryPath = climateTemplate.getSourceFileUri();
		Set<File> xmlFiles = FileService.getFiles(new File(directoryPath), "ccd", false);
		for(File xmlFile : xmlFiles) {
			if(xmlFile.getName().equalsIgnoreCase("Temperature.ccd")) {
				return ClimateTemplateTemperatureDeserializer.deserializeTemperatures(xmlFile.getAbsolutePath());
			}
		}
		return null;
	}
	
	@Override
	public List<SpaceTypeTemplate> listOccupancyResources() {
//		we are using the HESMOS_SpaceTypes.xml
		String filePath = PATH_TO_DB_TEMPLATES_DIR+"HESMOS_SpaceTypes.xml";
		List<SpaceTypeTemplate> templates = null;
		try {
			templates = SpaceTypeTemplatesDeserializer.deserialize(filePath);
			LOG.debug("Found {} space type templates in file: {}", new Object[]{templates.size(), filePath});
			String schedulesPath = PATH_TO_DB_TEMPLATES_DIR+"HESMOS_Schedules.xml";
			Map<String, List<Schedule>> scheduleMap = ScheduleTemplatesDeserializer.deserialize(schedulesPath);

			for(SpaceTypeTemplate stt : templates) {
				String spaceTypeName = stt.getName();
				List<Schedule> schedules = scheduleMap.get(spaceTypeName);
				if(schedules != null) stt.setSchedules(schedules);
			}
			return templates;				
		} catch (ParsingException e) {
			LOG.error(e.getMessage());
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage());
		}		
		return null;
	}
	
	@Override
	public List<SpaceTypeTemplate> listOccupancyResources(Integer userId) throws FileNotFoundException, ParsingException {
		List<SpaceTypeTemplate> templates = listOccupancyResources();
		if(userId > -1) {
			//	add user specific templates
			String userRepositoryPath = userService.getUserRepositoryPath(userId);
			String pathToTemplates = userRepositoryPath+File.separator+USER_SPACETYPE_TEMPLATES;
			//	we are using the userId_SpaceTypes.xml
			String filePath = pathToTemplates+File.separator+userId+"_SpaceTypes.xml";
			if(new File(filePath).exists()) {
				List<SpaceTypeTemplate> userTemplates = SpaceTypeTemplatesDeserializer.deserialize(filePath);
				LOG.debug("Found {} space type templates the user defined  in file: {}", new Object[]{templates.size(), filePath});
				
				String schedulesPath = pathToTemplates+File.separator+userId+"_Schedules.xml";
				Map<String, List<Schedule>> userScheduleMap = ScheduleTemplatesDeserializer.deserialize(schedulesPath);
				//	LOG.debug("Found the user defined schedules: {} in file: {}", new Object[]{userScheduleMap, schedulesPath});
				//	add schedules
				for(SpaceTypeTemplate stt : userTemplates) {
					String spaceTypeName = stt.getName();
					List<Schedule> schedules = userScheduleMap.get(spaceTypeName);
					if(schedules != null) stt.setSchedules(schedules);
				}
				return createUniqueList(templates, userTemplates);
			}
		}
		return templates;
	}
	
	/**
	 * Creates a list of templates where every template occurs only once. It filters templates by names. 
	 * When templates are available which have the same names it will take only the first one.
	 * @param originalTemplates
	 * @param compareTemplates
	 * @return
	 */
	private <T extends Resource> List<T> createUniqueList(List<T> originalTemplates, List<T> compareTemplates) {
		if(compareTemplates == null) return originalTemplates;
		List<T> output = new ArrayList<T>();
//		(1) the compare templates
		for(T tpl : compareTemplates) {
			String tplName = tpl.getName();
			boolean existent = false;
			for(T outputTpl : output) {
				if(tplName.equals(outputTpl.getName())) {
					existent = true;
					break;
				}
			}
			if(!existent) output.add(tpl);
		}
//		(2) the original templates
		for(T tpl : originalTemplates) {
			String tplName = tpl.getName();
			boolean existent = false;
			for(T outputTpl : output) {
				if(tplName.equals(outputTpl.getName())) {
					existent = true;
					break;
				}
			}
			if(!existent) output.add(tpl);
		}
		return output;
	}
	
	@Override
	public void uploadClimateTemplates(Integer userId, String[] fileNames) throws IOException {
		if(fileNames != null) {
			UserRepository ur = userService.getUserRepositoryByUserId(userId);
			String urPath = ur.getUserRepositoryPath();
			LOG.debug("UserRepository path: {}",urPath);
			File climateDir = new File(urPath+File.separator+USER_CLIMATE_TEMPLATES);
			climateDir.mkdir();
			String climateProjectDirName = UUID.randomUUID().toString();
	//		climateProjectDir like '15' for Munich or 'TRY' for Test Reference Year but here it is created randomly
			File climateProjectDir = new File(climateDir.getAbsolutePath()+File.separator+climateProjectDirName);
			climateProjectDir.mkdir();
	//		upload to user repository
			for(String fileName : fileNames) {
				userService.uploadFileToDirectoryInUserRepository(
						userId, new File(fileName), "./"+USER_CLIMATE_TEMPLATES+"/"+climateProjectDirName);
			}
		}
	}
	
	@Override
	public String uploadConstructionTemplates(Integer userId, String filePath) throws IOException {
		if(filePath != null) {
			String urPath = userService.getUserRepositoryPath(userId);
			File constructionDir = new File(urPath+File.separator+USER_CONSTRUCTIONS);
			constructionDir.mkdir();
	//		upload to user repository
			boolean success = true;
			FileInformation fi = userService.uploadFileToDirectoryInUserRepository(userId, new File(filePath), "./"+USER_CONSTRUCTIONS);
			if(fi == null) {
				success = false;
			}
			LOG.debug("Upload of construction template: {} to UserRepository path: {} was: "+(success ? "successfully" : "not successfully"),
					new Object[]{filePath, fi.getFilePath()});
			return fi.getFilePath();
		}
		return null;
	}
	
	@Override
	public boolean uploadSpaceTypeTemplates(Integer userId, String[] fileNames) throws IOException {
		if(fileNames != null) {
			String urPath = userService.getUserRepositoryPath(userId);
			File spaceTypeDir = new File(urPath+File.separator+USER_SPACETYPE_TEMPLATES);
			spaceTypeDir.mkdir();
	//		upload to user repository
			boolean success = true;
			for(String fileName : fileNames) {
				FileInformation fi = userService.uploadFileToDirectoryInUserRepository(userId, new File(fileName), "./"+USER_SPACETYPE_TEMPLATES);
				if(fi == null) {
					success = false;
				}
				LOG.debug("Upload of space type templates: {} to UserRepository path: {} was: "+(success ? "successfully" : "not successfully"),
						new Object[]{fileName, urPath});
			}
			return success;
		}
		return false;
	}
	
	public String saveCombustibleContainer(Integer userId, CombustibleContainer container) throws IOException {
		if(container != null) {
			String urPath = userService.getUserRepositoryPath(userId);
			LOG.debug("Save combustible container: {} of user: {} to UserRepository path: {}",
					new Object[]{container, userId, urPath});
					
//			save it in the TEMP directory => uploadToUserRepository copies it in the user repo
			String destPath = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY)+File.separator+COMBUSTIBLE_FILE_NAME+".d6p";;
			CombustibleContainerSerializer.serialize(container, destPath);
			return uploadConstructionTemplates(userId, destPath);
		}
		return null;
	}
	
	@Override
	public String saveConstructionTemplate(Integer userId, ConstructionTemplate template) throws IOException, ParsingException {
		if(template != null) {
			String urPath = userService.getUserRepositoryPath(userId);
			LOG.debug("Save construction template: {} of user: {} to UserRepository path: {}",
					new Object[]{template, userId,urPath});
			
			String destPath = template.getSourceFileUri();
			if(destPath == null) {	// its a new template			
//				destPath = ur.getUserRepositoryPath()+File.separator+PATH_CONSTRUCTION_TEMPLATES+File.separator+template.getName()+".d6p";
//				save it in the TEMP directory => uploadToUserRepository copies it in the user repo
				destPath = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY)+File.separator+template.getName()+".d6p";;
				template.setSourceFileUri(destPath);
			}
			try {
				ConstructionTemplateSerializer.serialize(template, destPath);
				return uploadConstructionTemplates(userId, destPath);
			} catch (ParserConfigurationException pce) {
				throw new ParsingException(pce);
			} catch (TransformerException e) {
				throw new ParsingException(e);
			}
		}
		return null;
	}
	
	@Override
	public boolean saveSpaceTypeTemplate(Integer userId, SpaceTypeTemplate template) throws ParsingException, IOException {
		if(template != null) {
			List<SpaceTypeTemplate> spaceTypeTemplates = listOccupancyResources(userId);
			String urPath = userService.getUserRepositoryPath(userId);
			LOG.debug("UserRepository path: {}",urPath);
			
			String destPath = template.getSourceFileUri();
			if(destPath == null) {	// its a new template			
//				save it in the TEMP directory => uploadToUserRepository copies it in the user repo
				destPath = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY)+File.separator+userId+"_SpaceTypes.xml";
				template.setSourceFileUri(destPath);
				spaceTypeTemplates.add(template);
			}
			LOG.debug("Save the space type template: {} to: {}", new Object[]{template, destPath});
			try {
				SpaceTypeTemplatesSerializer.serialize(spaceTypeTemplates, destPath);
				
	//			save schedules
				String schedulesPath = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY)+File.separator+userId+"_Schedules.xml";
				List<Schedule> schedules = template.getSchedules();
				LOG.debug("Save the schedules: {} to: {}", new Object[]{schedules, schedulesPath});
				Map<String, List<Schedule>> scheduleMap = new HashMap<String, List<Schedule>>();
				scheduleMap.put(template.getName(), schedules);
				ScheduleTemplatesSerializer.serialize(scheduleMap, schedulesPath);
				
				String[] paths = new String[2];
				paths[0] = destPath;
				paths[1] = schedulesPath;
				return uploadSpaceTypeTemplates(userId, paths);
			} catch (ParserConfigurationException pce) {
				throw new ParsingException(pce);
			} catch (TransformerException e) {
				throw new ParsingException(e);
			}
		}
		return false;
	}
	
	@Override
	public InputStream stochastifyConstruction(ConstructionTemplate resource,
			double meanValue, double standardDeviation, int numberOfSamples)
			throws ResourceServiceException {
		throw new UnsupportedOperationException("Currently not supported in IBK resources");
	}
	
	@Override
	public File stochastifyMaterial(InputStream resource, double meanValue, double standardDeviation, int numberOfSamples, String destPath) 
			throws ResourceServiceException {
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		DocumentBuilder db = dbf.newDocumentBuilder();
//		Document doc = db.parse(resource);
//		doc.normalizeDocument();
//		
//		File outputFile = new File(destPath);
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		DOMSource source = new DOMSource(doc);
//		StreamResult result = new StreamResult(outputFile);
// 
//		transformer.transform(source, result);
//		return outputFile;
		throw new UnsupportedOperationException("Currently not supported in IBK resources");
	}


	@Override
	public boolean deleteTemplate(Integer userId, Resource template) {
		if(template != null) {
			String urPath = userService.getUserRepositoryPath(userId);
			
			String destPath = template.getSourceFileUri();
			File file = new File(destPath);		
			LOG.info("Delete template: {} of user: {} from user repository: {}",
					new Object[]{destPath, userId, urPath});
			return userService.deleteFile(userId, file);
		}
		return false;
	}

	@Override
	public Double calculateUValue(Map<Integer, MaterialLayer> materialLayers, Collection<MaterialTemplate> materialTemplates) {
		final double outerHeatTransferCoefficient = 1/25.0; // 25 W/m2K
		final double innerHeatTransferCoefficient = 1/8.0;	// 8 W/m2K
		Double u = null;
		double Rges = 0.0;	// m2K/W
		Collection<MaterialLayer> layers = materialLayers.values();
		if(layers != null && materialTemplates != null) {
			for(MaterialLayer ml : layers) {
				float thickness = ml.getThickness(); // unit: meter
				MaterialTemplate template = ml.getTemplate();
				if(template != null) {
					String tplName = template.getName();
					if(StringUtils.isNotEmpty(tplName)) {
						for(MaterialTemplate mt : materialTemplates) {
							String compareName = mt.getName();
							if(StringUtils.isNotEmpty(compareName)) {
								if(compareName.equalsIgnoreCase(tplName)) {
									double lambda = mt.getLambda();	// unit: W/mK
									if(lambda == 0.0 || thickness == 0.0f) continue;
									
									double R = thickness/lambda; // unit: m2K/W
									Rges += R;
								}
							}
						}
					}
				}
			}
		}
		if(Rges == 0.0) {
			return null;
		} else {
			Rges = Rges + innerHeatTransferCoefficient + outerHeatTransferCoefficient;
			u = 1/Rges;	// W/m2K
//			2 positions after the decimal point
			u = (new Double(Math.round(u *100)) / 100);
			return u;
		}
	}
	
	@Override
	public void setConfigurationService(ConfigurationService configurationService) {
		super.setConfigurationService(configurationService);
		setupCaches();
	}

}
