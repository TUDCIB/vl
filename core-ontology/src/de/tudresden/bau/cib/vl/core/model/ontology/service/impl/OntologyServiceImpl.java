package de.tudresden.bau.cib.vl.core.model.ontology.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jsdai.SIfc2x3.AIfcproperty;
import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingelement;
import jsdai.SIfc2x3.EIfccolumn;
import jsdai.SIfc2x3.EIfccomplexproperty;
import jsdai.SIfc2x3.EIfcdescriptivemeasure;
import jsdai.SIfc2x3.EIfcdoor;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcmaterial;
import jsdai.SIfc2x3.EIfcopeningelement;
import jsdai.SIfc2x3.EIfcproperty;
import jsdai.SIfc2x3.EIfcpropertysinglevalue;
import jsdai.SIfc2x3.EIfcrelassociatesmaterial;
import jsdai.SIfc2x3.EIfcrelfillselement;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcslab;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcwindow;
import jsdai.lang.SdaiException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import de.tudresden.bau.cib.model.utility.IterableAggregate;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.EnergyEnhancedBimModel;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.InformationNotFoundInOntologyException;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.InformationNotFoundInOntologyExceptionCode;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.LinkException;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.ReasoningException;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.IfcIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.linking.Ifc2ResourceLinker;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.linking.Resource2ResourceLinker;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource.ClimateResourceIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource.ConstructionResourceIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.resource.MaterialResourceIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.parser.OwlParser;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.Reasoner;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.RuleSet;
import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.service.FileService;

public class OntologyServiceImpl implements OntologyService {
	
	private static final Logger LOG = LoggerFactory.getLogger(OntologyServiceImpl.class);
	private ConfigurationService configurationService;
	private TemplateService templateService;
	private FileService fileService;
	
	public static String 	ONTOLOGY_LANGUAGE 	= null; //"RDF/XML"; // RDF/XML || RDF/XML-ABBREV || N3 || TTL

	
	@Override
	public File saveRDF(OntologyModel model, String filePath) throws FileNotFoundException {
		String saveWithSchema = configurationService.getProperty(PROPERTIES.FLAG_ONTOLOGY_SAVE_WITHOUT_SCHEMES);
		boolean saveWithSchemes = StringUtils.isNotEmpty(saveWithSchema) ? Boolean.parseBoolean(saveWithSchema) : true;
		
		File file = saveRDFToDisk(model, filePath, saveWithSchemes);
		LOG.debug("Saved ontology model: {} to path: {}", new Object[]{model.getId(), filePath});
		return file;
	}
	
	private File saveRDFToDisk(OntologyModel ontologyModel, String filePath, boolean saveWithSchemes) throws FileNotFoundException {
		return saveRDFToDisk(ontologyModel, filePath, ONTOLOGY_LANGUAGE, saveWithSchemes);		
	}
	
	@Override
	public File saveRDFToDisk(OntologyModel ontologyModel, String filePath, 
			String format, boolean saveWithSchemes) throws FileNotFoundException {
		if(!saveWithSchemes) {
			// we don't want to save the schemes in the file => remove them
			Collection<Model> schemes = ontologyModel.getSchemes();
			for(Model schema : schemes) {
				ontologyModel.getUnderlyingModel().remove(schema);
			}
		}
		
		LOG.info("Saving RDF to: "+filePath);
		File file = new File(filePath);
		FileOutputStream fos = new FileOutputStream(file);
			
		ontologyModel.getUnderlyingModel().write(fos, format);	
		return file;
	}
	
	@Override
	public OntologyModel loadOntologyModel(String filePath) throws ParsingException, FileNotFoundException {
		LOG.debug("Try to load link model from: {}", new Object[]{filePath});
		OntologyModel model = null;
		String workingDirectoryPath = configurationService.getProperty(ConfigurationService.PROPERTIES.PATH_PARSER_REPOSITORY.toString());
		String type = FileService.getFileExtension(filePath);
		OwlParser parser = createParser(type, workingDirectoryPath);
		model = (OntologyModel) parser.parse(filePath);	
		String uuid = UUID.nameUUIDFromBytes(filePath.getBytes()).toString();
		model.setId(uuid);
		LOG.info("Ontology model: {} loaded {}", new Object[]{filePath, model!=null ? "successfully" : "not successfully"});
		Set<String> pathToSchemes = getSchemePathsOfLinkedModels();
		LOG.info("Loading following scheme paths: {} of linked model: {}", pathToSchemes, model.getId());
		// we always load the schemes
		loadSchemesOfLinkedModels(model, pathToSchemes.toArray(pathToSchemes.toArray(new String[pathToSchemes.size()])));
		return model;
	}
	
	@Override
	public void createArchitecturalDomain(Ifc2x3DataModelProxy ifcModel, OntologyModel model) throws FactoryException {
		LOG.info("Creating architectural domain ontology individuals");
		boolean filterClasses = Boolean.valueOf(configurationService.getProperty(PROPERTIES.FLAG_FILTER_IFC_CLASSES_FOR_ONTOLOGY));
		IfcIndividualFactory factory = new IfcIndividualFactory(model, ifcModel, filterClasses);
		if(!(factory instanceof IfcIndividualFactory)) throw new IllegalArgumentException("Factory is no IFC factory");
		IfcIndividualFactory ifcFactory = (IfcIndividualFactory) factory;
		
		createBuildingInformationModel(ifcModel, ifcFactory);
	}
	
	@Override
	public void createArchitecturalDomainView(Collection<? extends EIfcroot> ifcElements, 
			Ifc2x3DataModelProxy ifcModel, OntologyModel ontModel)
			throws FactoryException {
		if(ifcElements == null) throw new IllegalArgumentException("No entities are given");
		LOG.debug("Creating individuals in the architectural domain by iterating over IFC entities");
		
		boolean filterClasses = Boolean.valueOf(configurationService.getProperty(PROPERTIES.FLAG_FILTER_IFC_CLASSES_FOR_ONTOLOGY));
		IfcIndividualFactory factory = new IfcIndividualFactory(ontModel, ifcModel, filterClasses);
		if(!(factory instanceof IfcIndividualFactory)) throw new IllegalArgumentException("Factory is no IFC factory");
		
		try {
			for(EIfcroot ifcElement : ifcElements) {
				String guid = ifcModel.getGlobalId(ifcElement);
				factory.createIfcBuilding(guid);
			}
		} catch(IfcException e) {
			throw new FactoryException(e);
		}
	}
	
	@Override
	public void createEnergyRelatedDomain(EnergyEnhancedBimModel eeBim, Ifc2x3DataModelProxy ifcModel, OntologyModel model) throws FactoryException {
		try {
			boolean filterClasses = Boolean.valueOf(configurationService.getProperty(PROPERTIES.FLAG_FILTER_IFC_CLASSES_FOR_ONTOLOGY));
			IfcIndividualFactory factory = new IfcIndividualFactory(model, ifcModel, filterClasses);
			if(!(factory instanceof IfcIndividualFactory)) throw new IllegalArgumentException("Factory is no IFC factory");
			
			EIfcrelassociatesmaterial[] relMaterials = ifcModel.getIfcEntitiesOf(EIfcrelassociatesmaterial.class);
			if(relMaterials == null || relMaterials.length == 0) {
				LOG.debug("Found no material relationships");
			} else {
				for(EIfcrelassociatesmaterial relMaterial : relMaterials) {
					String relMatGuid = ifcModel.getGlobalId(relMaterial);
					factory.createIndividual(relMatGuid);
				}
			}
			EIfcbuildingelement[] buildingElements = ifcModel.getIfcEntitiesOf(EIfcbuildingelement.class);
			MaterialResourceIndividualFactory materialFactory = new MaterialResourceIndividualFactory(model);
			Map<String, ConstructionTemplate> constructions = eeBim.getConstructions();
			for(EIfcbuildingelement be : buildingElements) {
				String beGuid = ifcModel.getGlobalId(be);
//				Set<EIfcmaterial> materials = ifcModel.getAssociatedMaterial(be);
				ConstructionTemplate constructionTemplate = constructions.get(beGuid);
				Map<Integer, MaterialLayer> layers = constructionTemplate.getMaterialLayers();
				for(Map.Entry<Integer, MaterialLayer> layerEntry : layers.entrySet()){
					MaterialLayer layer = layerEntry.getValue();
					MaterialTemplate materialTemplate = layer.getTemplate();
					
//					for(EIfcmaterial material : materials) {
						materialFactory.createMaterialProperties(materialTemplate);
//					}
				}
			}
		} catch (IfcException e) {
			throw new FactoryException(e);
		}
	}
	
	/**
	 * Split code to separate create Methods 
	 * @param ifcModel
	 * @param factory
	 * @throws FactoryException
	 */
	private void createBuildingInformationModel(Ifc2x3DataModelProxy ifcModel, IfcIndividualFactory factory) throws FactoryException {
		try {
			List<String> buildingGuids = ifcModel.getBuildingsIDs();
			
			// create site
			factory.createIfcSite();
			
			for(String buildingGuid : buildingGuids) {
				EIfcbuilding building = ifcModel.getIfcEntity(buildingGuid, EIfcbuilding.class);
				if(building == null) throw new IllegalArgumentException("No IfcBuilding in the model");
				// create building
				Individual owlBuilding = factory.createIndividual(buildingGuid);
				// create building storey
				factory.createIfcBuildingStoreys(building);
				
				EIfcspace[] spacesOfBuilding = ifcModel.getSpacesOfBuilding(buildingGuid);
				List<EIfcspace> spaceList = Arrays.asList(spacesOfBuilding);
				// create room model
				factory.createIfcSpaces(spaceList);
				Set<EIfcelement> elements = ifcModel.getElementsInBuilding(buildingGuid);
				Set<String> elementGuids = ifcModel.getGlobalIds(elements);
				factory.createIfcElements(elementGuids);	
				
				// TODO add building height, volume, area etc.
				
				double buildingShellArea = ifcModel.calculateBuildingShellArea(spaceList);
				Individual owlBShellAreaQuantity = factory.createAreaQuantity("BuildingShellArea", null, buildingShellArea);
				if(owlBShellAreaQuantity != null) {
					factory.createIfcRelDefinesByProperties(Arrays.asList(owlBuilding), owlBShellAreaQuantity);
				}
				
				double windowArea = ifcModel.calculateWindowArea(spaceList);
				Individual owlWAreaQuantity = factory.createAreaQuantity("WindowArea", null, windowArea);
				if(owlWAreaQuantity != null) {
					factory.createIfcRelDefinesByProperties(Arrays.asList(owlBuilding), owlWAreaQuantity);
				}
				
				// create relations from objects to aggregates
				factory.createRelationsFromObjectsToAggregates();
				
			}
		} catch(IfcException e) {
			throw new FactoryException(e);
		} catch (IllegalArgumentException e) {
			LOG.error(e.getMessage());
		}
	}

	@Override
	public Reasoner applyRuleSet(OntologyModel ontologyModel, RuleSet ruleSet) throws ReasoningException {	
		Collection<RuleSet> ruleSets = new ArrayList<>();
		ruleSets.add(ruleSet);
		return applyRuleSets(ontologyModel, ruleSets);
	}
	
	@Override
	public Reasoner applyRuleSets(OntologyModel ontologyModel, Collection<RuleSet> ruleSets) throws ReasoningException {		
		Reasoner reasoner = ontologyModel.setupReasoner();
		try {
			Set<URL> urls = new HashSet<URL>();
			for(RuleSet ruleSet : ruleSets) {
				urls.add(ruleSet.getUri().toURL());
			}
			return reasoner.init(urls);
		} catch (MalformedURLException e) {
			throw new ReasoningException(e);
		}
	}
	
	@Override
	public Reasoner validate(OntologyModel ontologyModel) {
		Reasoner reasoner = ontologyModel.setupReasoner();
		return reasoner.init();
	}
	
	@Override
	public RuleSet loadRuleSet(File ruleFile) throws URISyntaxException, IOException {
		// read file
		// @id: derivationrule @name: Derivation rule set @description: Specifies derivation rules
		// id -> "derivationrule"
		// name -> "Derivation rule set"
		// description -> "Specifies derivation rules"
		RuleSet ruleSet = new RuleSet();
		
		String content = FileService.readFileToString(ruleFile);
		final String ID_PATTERN = "@id:";
		final String NAME_PATTERN = "@name:";
		final String DESCRIPTION_PATTERN = "@description:";
		final String commentEnd = "@end";
		
		int idIndex = content.indexOf(ID_PATTERN);
		int nameIndex = content.indexOf(NAME_PATTERN);
		int descIndex = content.indexOf(DESCRIPTION_PATTERN);
		int commentEndIndex = content.lastIndexOf(commentEnd);
		
		if(idIndex > -1) {
			String id = content.substring(idIndex+ID_PATTERN.length(), nameIndex).trim();
			ruleSet.setId(id);
		}
		if(nameIndex > -1) {
			String name = content.substring(nameIndex+NAME_PATTERN.length(), descIndex).trim();
			ruleSet.setName(name);
		}
		if(descIndex > -1) {
			String description = content.substring(descIndex+DESCRIPTION_PATTERN.length(), commentEndIndex).trim();
			ruleSet.setDescription(description);
		}
		if(commentEndIndex > -1) {
			content = content.substring(commentEndIndex+commentEnd.length());
			ruleSet.setContent(content);
		}		
		
		ruleSet.setUri(ruleFile.toURI());		
		return ruleSet;
	}
	
	@Override
	public Collection<Statement> listStatements(OntologyModel model, com.hp.hpl.jena.rdf.model.Resource S, Property p, com.hp.hpl.jena.rdf.model.Resource O) {
		Collection<Statement> statements = new ArrayList<>();
	    for (StmtIterator i = model.getUnderlyingModel().listStatements(S, p, O); i.hasNext(); ) {
	        Statement s = i.nextStatement();
	        statements.add(s);
	    }
	    return statements;
	}
	
	@Override
	public Collection<RuleSet> listRuleSets() throws URISyntaxException, IOException {
		File ruleDir = new File(configurationService.getProperty(PROPERTIES.PATH_RULES));
		Collection<RuleSet> ruleSets = loadRuleSets(ruleDir);
		return ruleSets;
	}
	
	@Override
	public Collection<RuleSet> loadRuleSets(File directory) throws URISyntaxException, IOException {
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		Set<File> ruleSetFiles = FileService.getFiles(directory, true);
		if(ruleSetFiles != null && ruleSetFiles.size() == 0) return ruleSets;
		
		for(File ruleSetFile : ruleSetFiles) {
			RuleSet ruleSet = loadRuleSet(ruleSetFile);
			ruleSets.add(ruleSet);
		}
		return ruleSets;
	}
	
	@Override
	public void loadSchemesOfLinkedModels(OntologyModel model, String... owlPaths) throws FileNotFoundException {
		if(owlPaths == null) return;
		model.loadSubModels(owlPaths);
	}
	
	@Override
	public String existsRelation(Ifc2x3DataModelProxy ifcModel, String guid, Resource resource, OntologyModel ontologyModel) {
		Individual fromInd = ontologyModel.getIndividualByElementId(ifcModel, guid);
		
		if(fromInd == null) {
//			throw new InformationNotFoundInOntologyException(
//					InformationNotFoundInOntologyExceptionCode.INDIVIDUAL_NOT_FOUND, "The subject: "+fromInd+" is not in the model");
			LOG.info("The subject: {} is not in the model", fromInd);
			return null;
		}
				
		
		StmtIterator iterator = fromInd.listProperties();
		while(iterator.hasNext()) {
			Statement statement = iterator.next();
			RDFNode rdfObject = statement.getObject();
			RDFNode rdfPredicate = statement.getPredicate();
			if(rdfPredicate != null) {
				com.hp.hpl.jena.rdf.model.Resource toResource = rdfPredicate.asResource();
				String toResourceUri = toResource.getURI();		
				
				if(resource instanceof ConstructionTemplate)
				{
					if(toResourceUri.equalsIgnoreCase( EeBIMOntoVocabulary.HAS_CONSTRUCTION.getURI()))
					{
						return rdfObject != null ? rdfObject.asResource().getURI() : null;
					}
				}
				
				if(resource instanceof ClimateLocationTemplate)
				{
					if(toResourceUri.equalsIgnoreCase( EeBIMOntoVocabulary.HAS_CLIMATE_LOCATION.getURI()))
					{
						return rdfObject != null ? rdfObject.asResource().getURI() : null;
					}
				}
				
				if(resource instanceof SpaceTypeTemplate)
				{
					if(toResourceUri.equalsIgnoreCase( EeBIMOntoVocabulary.HAS_SPACE_TYPE.getURI()))
					{
						return rdfObject != null ? rdfObject.asResource().getURI() : null;
					}
				}
					
					
				
				
				
				
				
//				if(toResourceUri != null) {
//					return toResourceUri;
//				}
			}
		}
		return null;
	}
	
	
	
	private String getConstructionTemplateNameFromIfc(Set<EIfcmaterial> materials, EIfcbuildingelement be, Ifc2x3DataModelProxy ifcModel) throws SdaiException, IfcException {
		String description = null;
//		it is a slab or a column
		if(be instanceof EIfcslab || be instanceof EIfccolumn) {			
			for(EIfcmaterial material : materials) {
				String materialName = material.getName(material);
				if(materialName.length() < 1) 
					continue;			
				else 
					return materialName;	
			}
		}
		
		// it is a window or door => search PropertySet
		if((be instanceof EIfcwindow) || (be instanceof EIfcdoor)) {
			description = getDescriptionFromProperty(be, ifcModel);
//			LOG.debug("Description: {} retrieved from window or door: {}",
//					new Object[]{description, be});
			return description;
		}
		
//		it is an IfcWallStandardCase or something like that
		if(be.testName(be)) {
			description = be.getName(be);
//			LOG.debug("Description: {} retrieved from building element: {}",
//					new Object[]{description, be});
		}
		return description;
	}
	
	private String getDescriptionFromProperty(EIfcelement element, Ifc2x3DataModelProxy ifcModel) throws IfcException {
		String description = null;
		if(element instanceof EIfcwindow || element instanceof EIfcdoor) {
			try {
//				retrieve IfcRelFillsElement
				List<EIfcrelfillselement> relFillsElements = ifcModel.getRelFillsElement(element);
				if(relFillsElements != null) {
					for(EIfcrelfillselement relFillsElement : relFillsElements) {
//						retrieve the IfcOpeningElement
						EIfcopeningelement openingElement = relFillsElement.getRelatingopeningelement(relFillsElement);
						if(openingElement != null) {
							EIfcproperty[] properties = ifcModel.getProperties(openingElement);
							if(properties != null) {
								for(EIfcproperty property : properties) {
									if(property instanceof EIfccomplexproperty) {
										EIfccomplexproperty complexProperty = (EIfccomplexproperty) property;
										AIfcproperty aProperty = complexProperty.getHasproperties(complexProperty);
										for(EIfcproperty prop : new IterableAggregate<EIfcproperty>(aProperty)) {
											if(prop instanceof EIfcpropertysinglevalue) {
												EIfcpropertysinglevalue singleValue = (EIfcpropertysinglevalue) prop;
												if(singleValue.testName(singleValue)) {
													if(singleValue.getName(singleValue).equalsIgnoreCase("bezeichnung")) {
														String value = singleValue.getNominalvalue(singleValue, (EIfcdescriptivemeasure) null);
														description = value;
													}
												}
			
											}
										}
									}
								}
							}
						}
					}
				}
			} catch (SdaiException se) {
				throw new IfcException(se);
			}
		}
		return description;
	}
	
	private String getSpaceType(String buildingGuid, String spaceGuid,
			Ifc2x3DataModelProxy ifcModel) throws IfcException {
		EIfcspace[] spaces = ifcModel.getSpaces(buildingGuid);
		for(EIfcspace space : spaces) {
			String guid = ifcModel.getGlobalId(space);
			if(spaceGuid.equals(guid)) {
				try {
					if(space.testDescription(space)) {
						String description = space.getDescription(space);
						description = description.trim();
						Pattern pattern = Pattern.compile(REGEX_SEARCH_ROOM_DIN);
						Matcher matcher = pattern.matcher(description);
						if(matcher.find()) {
							return matcher.group();
						}
					}
				} catch (SdaiException e) {
					throw new IfcException(e);
				}
			}
		}
		return null;
	}
	
	private boolean addHasResource(Individual individual, Individual toIndividual, ObjectProperty relationType, OntologyModel metamodel) {
//		set element references
		if(individual != null && toIndividual != null && relationType != null){	
			individual.addProperty(relationType, toIndividual);	
			LOG.trace("Individual: {} connected through: {} with individual: {} successfully: [{}] => [{}]",
					new Object[]{individual, relationType, toIndividual, individual, toIndividual});
			return true;
		} else {
			throw new InformationNotFoundInOntologyException(
					InformationNotFoundInOntologyExceptionCode.INDIVIDUAL_NOT_FOUND, "IndividualA = "+individual+" individualB = "+toIndividual+" relationType = "+relationType);
		}
	}
	
	private boolean addHasResource(Ifc2x3DataModelProxy fromModel, String fromElementId, Individual toIndividual, 
			ObjectProperty relationType, OntologyModel metamodel) throws LinkException, InformationNotFoundInOntologyException {
		try {			
			IfcIndividualFactory fromIndFactory = new IfcIndividualFactory(metamodel, fromModel, true);
			Individual individualA = fromIndFactory.createIndividual(fromElementId);	
			return addHasResource(individualA, toIndividual, relationType, metamodel);
		} catch (FactoryException e) {
			throw new LinkException(e.getMessage(), e);
		}
	}
	
	@Override
	public boolean addHasClimate(Ifc2x3DataModelProxy ifcModel,
			String fromElementId, ClimateLocationTemplate climate,
			OntologyModel ontologyModel) throws FactoryException {
		ClimateResourceIndividualFactory fctory = new ClimateResourceIndividualFactory(ontologyModel);
		Individual toResource = fctory.createClimateIndividual(climate);
		ObjectProperty hasClimateProperty = EeBIMOntoVocabulary.HAS_CLIMATE_LOCATION;
		return addHasResource(ifcModel, fromElementId, toResource, hasClimateProperty, ontologyModel);
	}
	
	@Override
	public boolean addHasConstruction(Ifc2x3DataModelProxy ifcModel, String fromElementId, 
			ConstructionTemplate constructionResource, OntologyModel ontologyModel) throws FactoryException {
		ConstructionResourceIndividualFactory constructionFactory = new ConstructionResourceIndividualFactory(ontologyModel);
		Individual toResource = constructionFactory.createConstructionIndividual(constructionResource);
		ObjectProperty hasConstructionProperty = EeBIMOntoVocabulary.HAS_CONSTRUCTION;
		return addHasResource(ifcModel, fromElementId, toResource, hasConstructionProperty, ontologyModel);
	}
	
	@Override
	public boolean removeHasConstruction(Ifc2x3DataModelProxy ifcModel, String guid, OntologyModel ontologyModel) {			
		ObjectProperty property = EeBIMOntoVocabulary.HAS_CONSTRUCTION;
		return removeRelationsFromIfcElementOfProperty(ifcModel, guid, property, ontologyModel);
	}
	
	@Override
	public boolean removeHasOccupancy(Ifc2x3DataModelProxy ifcModel, String guid, OntologyModel ontologyModel) {			
		ObjectProperty property = EeBIMOntoVocabulary.HAS_SPACE_TYPE;
		return removeRelationsFromIfcElementOfProperty(ifcModel, guid, property, ontologyModel);
	}
	
	@Override
	public boolean removeHasClimate(Ifc2x3DataModelProxy ifcModel, String guid, OntologyModel ontologyModel) {			
		ObjectProperty property = EeBIMOntoVocabulary.HAS_CLIMATE_LOCATION;
		return removeRelationsFromIfcElementOfProperty(ifcModel, guid, property, ontologyModel);
	}
	
	@Override
	public boolean removeRelationsFromIfcElementOfProperty(Ifc2x3DataModelProxy ifcModel, String guid, ObjectProperty property, OntologyModel ontologyModel) {
		Individual fromInd = ontologyModel.getIndividualByElementId(ifcModel, guid);
		
		if(fromInd == null) throw new InformationNotFoundInOntologyException(
				InformationNotFoundInOntologyExceptionCode.INDIVIDUAL_NOT_FOUND, "The subject: "+guid+" is not in the model");
		if(property == null) throw new InformationNotFoundInOntologyException(
				InformationNotFoundInOntologyExceptionCode.PROPERTY_NOT_FOUND, "The property: "+property+" is not in the model");
		Statement previousStatement = fromInd.getProperty(property);
		if(previousStatement != null) {
			RDFNode toIndividual = previousStatement.getObject();
			if(toIndividual != null) {
				fromInd.removeProperty(property, toIndividual);
				LOG.info("Removed connection: {} from individual: {} with individual: {}",
						new Object[]{property, fromInd, toIndividual});
				return true;
			}
		}

		return false;
	}
	
	@Override
	public void removeRelations(List<Triple> triples, OntologyModel ontologyModel) {
		for(Triple triple : triples) {
			Node subject = triple.getSubject();
			Node predicate = triple.getPredicate();
			Node object = triple.getObject();
			
			Individual fromInd = ontologyModel.getIndividualByURI(subject.getURI());
			ObjectProperty property = ontologyModel.getOntologyProperties().get(predicate.getURI());
			Individual toInd = ontologyModel.getIndividualByURI(object.getURI());
			
//			set element references
			if(fromInd != null && toInd != null && property != null){	
				fromInd.removeProperty(property, toInd);	
				LOG.debug("Remove connection: {} from individual: {} with individual: {}",
						new Object[]{property, fromInd, toInd});
			} else {
				throw new InformationNotFoundInOntologyException(
						InformationNotFoundInOntologyExceptionCode.INDIVIDUAL_NOT_FOUND, "IndividualA = "+fromInd+" individualB = "+toInd+" relationType = "+property);
			}
		}
	}
	
	@Override
	public OntologyModel createEmptyOntologyModel() throws FileNotFoundException {
		LOG.info("Create empty ontology model");
		String workingDirectoryPath = configurationService.getProperty(ConfigurationService.PROPERTIES.PATH_PARSER_REPOSITORY);
		OwlParser parser = createParser(OWL, workingDirectoryPath);
		OntologyModel model = parser.createModel();
		Set<String> pathToSchemes = getSchemePathsOfLinkedModels();
		LOG.debug("Loading following scheme paths: {} of linked model: {}", pathToSchemes, model.getId());
		// we always load the schemes
		loadSchemesOfLinkedModels(model, pathToSchemes.toArray(pathToSchemes.toArray(new String[pathToSchemes.size()])));
		return model;
	}

	@Override
	public void createOntologyModel(EnergyEnhancedBimModel eeBimModel,
			OntologyModel ontModel, Ifc2x3DataModelProxy ifcModel)
			throws IfcException, LinkException, ParsingException, IOException {		
		LOG.info("Start linking");
//		Link IfcSite => ClimateModel			
		Map<String, ClimateLocationTemplate> climateTemplates = eeBimModel.getClimateModel();		
		if(climateTemplates != null) {
			LOG.debug("Linking {} climate templates in ontology", climateTemplates.size());
			for (Map.Entry<String, ClimateLocationTemplate> entry : climateTemplates.entrySet()) {
				String key = entry.getKey();
				ClimateLocationTemplate value = entry.getValue();
				Ifc2ResourceLinker.hasClimateLocation(ifcModel, eeBimModel, key, value, ontModel);		
			}
		}	
//		Link IfcSpace => SpaceType Template
		Map<String, SpaceTypeTemplate> spaceTypeTemplates = eeBimModel.getSpaceTypeTemplates();
		if(spaceTypeTemplates != null) {
			LOG.debug("Linking {} space type templates in ontology", spaceTypeTemplates.size());
			for (Map.Entry<String, SpaceTypeTemplate> entry : spaceTypeTemplates.entrySet()) {
				String key = entry.getKey();
				SpaceTypeTemplate value = entry.getValue();
				Ifc2ResourceLinker.hasSpaceType(ifcModel, eeBimModel, key, value, ontModel);			
			}
		}
//		Link IfcBuildingElement => ConstructionTemplate
		Map<String, ConstructionTemplate> constructionTemplates = eeBimModel.getConstructions();
		if(constructionTemplates != null) {
			LOG.debug("Linking {} construction templates in ontology", constructionTemplates.size());
			for (Map.Entry<String, ConstructionTemplate> entry : constructionTemplates.entrySet()) {
				String key = entry.getKey();
				ConstructionTemplate value = entry.getValue();
				Ifc2ResourceLinker.hasConstruction(ifcModel, eeBimModel, key, value, ontModel);
			}
		}
		LOG.debug("Create all links within a model");
//		creates all links which are given in IFC file like IfcBuilding->IfcBuildingStorey->IfcSpace
//		IfcLinker.createAllLinksWithinModel(ifcModel, ontModel);
		Resource2ResourceLinker.createAllLinksWithinModel(eeBimModel, ontModel);	
		
		LOG.info("Linking completed!");
	}
	
	@Override
	public OwlParser createParser(String type, String workingDirectoryPath) {
//		use the absolute path
		workingDirectoryPath = configurationService.getProperty(
				PROPERTIES.PATH_PARSER_REPOSITORY)+"ontology/";
		
		if(type.equalsIgnoreCase(OWL) || type.equalsIgnoreCase(RDF)) {
			LOG.debug("Creating OWL parser with repository: '{}'", workingDirectoryPath);
			return OwlParser.createParser(workingDirectoryPath, ONTOLOGY_LANGUAGE);
		} 
		
		else {
			return null;
		}
	}

	public Set<String> getSchemePathsOfLinkedModels() {
		Set<String> owlPaths = new HashSet<String>();
		
		String remoteBIMOntoPath = null;
		String remoteEeBIMOntoPath = null;
		
//		// try to get remote OWLs
//		try {
//			remoteIfc2x3Path = configurationService.getProperty(PROPERTIES.ONTOLOGY_IFC_REMOTE);
//			remoteEeBimPath = configurationService.getProperty(PROPERTIES.ONTOLOGY_EEBIM_REMOTE);
//			
//			LOG.debug("Checking reachability of remote OWLs: {} and {}", new Object[]{remoteIfc2x3Path, remoteEeBimPath});
//			
//			HttpURLConnection connection1 = (HttpURLConnection) new URL(remoteIfc2x3Path).openConnection();
//			connection1.setRequestMethod("HEAD");
//			HttpURLConnection connection2 = (HttpURLConnection) new URL(remoteEeBimPath).openConnection();
//			connection2.setRequestMethod("HEAD");
//			int responseCode1 = connection1.getResponseCode();
//			int responseCode2 = connection2.getResponseCode();
//			if (responseCode1 == 200 && responseCode2 == 200) {
//				owlPaths.add(remoteIfc2x3Path);
//				owlPaths.add(remoteEeBimPath);
//				// return paths
//				return owlPaths;
//			} else {
//				LOG.warn("Problem when accessing remote OWLs");
//			}
//		} catch (Exception e) {
//			LOG.error("{}", "Problem when accessing remote OWLs: "+e.getMessage(),e);
//		} 
		
		// remote not accessible => Take the local OWLs
		owlPaths.add(ONTOLOGY_BIMONTO);
		owlPaths.add(ONTOLOGY_EEBIM);
		LOG.info("Take local OWL schemes: {} and {}", 
				new Object[]{ONTOLOGY_BIMONTO, ONTOLOGY_EEBIM});
		
// 		use the FileManager to find the input file
		OntDocumentManager manager = OntDocumentManager.getInstance();
//		this solves UnknownHostException problem if ontology files are on local machine
		try {
			String ifcURL = URI.create("file:"+ONTOLOGY_BIMONTO).toURL().toString();
			manager.addAltEntry(remoteBIMOntoPath != null ? remoteBIMOntoPath : ONTOLOGY_BIMONTO, ifcURL);
			String eeBimURL = URI.create("file:"+ONTOLOGY_EEBIM).toURL().toString();
			manager.addAltEntry(remoteEeBIMOntoPath != null ? remoteEeBIMOntoPath : ONTOLOGY_EEBIM, eeBimURL);
		} catch (MalformedURLException e) {
			LOG.error("{}", e.getMessage(),e);
		}		
		return owlPaths;	
	}
	
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
		ONTOLOGY_LANGUAGE = configurationService.getProperty(PROPERTIES.SETTING_ONTOLOGY_LANGUAGE);
		if(StringUtils.isEmpty(ONTOLOGY_LANGUAGE)) ONTOLOGY_LANGUAGE = "RDF/XML"; 
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
}
