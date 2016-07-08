package de.tudresden.bau.cib.vl.core.model.ontology.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import jsdai.SIfc2x3.EIfcroot;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Statement;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.EnergyEnhancedBimModel;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.LinkException;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.ReasoningException;
import de.tudresden.bau.cib.vl.core.model.ontology.parser.OwlParser;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.Reasoner;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.RuleSet;

public interface OntologyService {
	
	static final String REGEX_SEARCH_ROOM_DIN = "[0-9]+.[0-9]+";
	static final String AREA_PATTERN = "#spaceboundary_area=";	
	static final String OWL = "owl";
	static final String RDF = "rdf";
	
	static final String ONTOLOGY_EEBIM = "resources/eeBIMOnto.owl";
	static final String ONTOLOGY_BIMONTO = "resources/BIMOnto.owl";
	
	/**
	 * <p>Creates the whole architectural domain model by collecting all building elements, rooms, storeys, 
	 * buildings and keep the relation defined in IFC. The individuals are fully conform to the IFC schema.</p>
	 * @param ifcModel
	 * @param model
	 * @throws FactoryException
	 */
	void createArchitecturalDomain(Ifc2x3DataModelProxy ifcModel, OntologyModel model) throws FactoryException;
	
	/**
	 * <p>Creates the architectural domain model of a subset defined through 'ifcEntities'. 
	 * The same approach defined in {@link #createArchitecturalDomain(Ifc2x3DataModelProxy, OntologyModel)} is applied 
	 * but only the given entities will be instantiated. Therefore the IFC model can be invalidated if the given entities are not complete 
	 * regarding the IFC specification.</p>
	 * @param ifcElements The subset of the whole IFC model which will be instantiated.
	 * @param ifcModel
	 * @param model
	 * @throws FactoryException
	 */
	void createArchitecturalDomainView(Collection<? extends EIfcroot> ifcEntities, 
			Ifc2x3DataModelProxy ifcModel, OntologyModel model) throws FactoryException;
	
	/**
	 * <p>
	 * 	Appends Relations to materials etc. like IfcRelAssociatesMaterial.
	 * </p>
	 * @param eeBim
	 * @param ifcModel
	 * @param model
	 * @throws FactoryException
	 */
	void createEnergyRelatedDomain(EnergyEnhancedBimModel eeBim, Ifc2x3DataModelProxy ifcModel, OntologyModel model) throws FactoryException;
	
	/**
	 * <p>Apply rule sets to the given data</p>
	 * @param ontologyModel
	 * @param ruleSets
	 * @return
	 */
	Reasoner applyRuleSets(OntologyModel ontologyModel, Collection<RuleSet> ruleSets) throws ReasoningException;
	
	/**
	 * <p>Apply one rule set to the given data</p>
	 * @param ontologyModel
	 * @param ruleSet
	 * @return
	 * @throws ReasoningException
	 */
	Reasoner applyRuleSet(OntologyModel ontologyModel, RuleSet ruleSet) throws ReasoningException;
	
	OntologyModel createEmptyOntologyModel() throws FileNotFoundException;

	void createOntologyModel(EnergyEnhancedBimModel eeBimModel,
			OntologyModel ontModel, Ifc2x3DataModelProxy ifcModel)
			throws IfcException, LinkException, ParsingException, IOException;
	
	/**
	 * Checks if there already is an relation from the IFC entity to the resource entity.
	 * @param ifcModel
	 * @param guid
	 * @param resource
	 * @param ontologyModel
	 * @return The URI of the previous related object.
	 */
	String existsRelation(Ifc2x3DataModelProxy ifcModel, String guid, Resource resource, OntologyModel ontologyModel);
	
	/**
	 * Saves the RDF file 
	 * @param model
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 */
	File saveRDF(OntologyModel model, String filePath) throws FileNotFoundException;

	OntologyModel loadOntologyModel(String filePath) throws ParsingException, FileNotFoundException;
	
	/**
	 * Load rule sets from a directory <b>but don't apply them</b>.
	 * @param directory The directory with rule files. Each file represents one rule set.
	 * @throws URISyntaxException when retrieving the URI from the files in the directory.
	 * @return A collection with rules.
	 * @throws IOException 
	 */
	Collection<RuleSet> loadRuleSets(File directory) throws URISyntaxException, IOException;
	
	/**
	 * Lists all statements for the given subject, property and/or object. 
	 * It is possible to request the statements by apply only at least one parameter. 
	 * @param model
	 * @param S The subject (optional)
	 * @param p The property (optional)
	 * @param O The object (optional)
	 * @return A collection with the statements retrieved by the requested parameter(s).
	 */
	Collection<Statement> listStatements(OntologyModel model, com.hp.hpl.jena.rdf.model.Resource S, Property p, com.hp.hpl.jena.rdf.model.Resource O);
	
	/**
	 * List all available rule sets <b>but don't apply them</b>.
	 * @throws URISyntaxException when retrieving the URI from the files in the directory.
	 * @return A collection with rules.
	 * @throws IOException 
	 */
	Collection<RuleSet> listRuleSets() throws URISyntaxException, IOException;
	
	/**
	 * Load one rule set <b>but don't apply it</b>.
	 * @param ruleFile
	 * @return
	 * @throws URISyntaxException when retrieving the URI out of the file.
	 * @throws IOException 
	 */
	RuleSet loadRuleSet(File ruleFile) throws URISyntaxException, IOException;

	boolean addHasConstruction(Ifc2x3DataModelProxy ifcModel,
			String fromElementId, ConstructionTemplate constructionResource,
			OntologyModel ontologyModel) throws FactoryException;
	
	boolean removeHasConstruction(Ifc2x3DataModelProxy ifcModel, String guid, OntologyModel ontologyModel);
	
	boolean addHasClimate(Ifc2x3DataModelProxy ifcModel,
			String fromElementId, ClimateLocationTemplate climate,
			OntologyModel ontologyModel) throws FactoryException;
	
	boolean removeHasClimate(Ifc2x3DataModelProxy ifcModel, String guid, OntologyModel ontologyModel);
	
	/**
	 * Remove the properties given through triples. It doesn't delete the subject and object individuals.
	 * @param triples
	 * @param ontologyModel
	 */
	void removeRelations(List<Triple> triples, OntologyModel ontologyModel);

	boolean removeRelationsFromIfcElementOfProperty(
			Ifc2x3DataModelProxy ifcModel, String guid,
			ObjectProperty property, OntologyModel ontologyModel);

	boolean removeHasOccupancy(Ifc2x3DataModelProxy ifcModel, String guid,
			OntologyModel ontologyModel);

	Reasoner validate(OntologyModel ontologyModel);

	OwlParser createParser(String type, String workingDirectoryPath);

	/**
	 * 
	 * @param owlPaths
	 * @throws FileNotFoundException
	 */
	void loadSchemesOfLinkedModels(OntologyModel model, String... owlPaths) throws FileNotFoundException;

	/**
	 * Saves the RDF file 
	 * @param ontologyModel The given ontology model.
	 * @param filePath The target location path.
	 * @param format The RDF format.
	 * @param saveWithSchemes If the schemes should be included
	 * @return The new file.
	 * @throws FileNotFoundException If a file cannot be found.
	 */
	File saveRDFToDisk(OntologyModel ontologyModel, String filePath,
			String format, boolean saveWithSchemes)
			throws FileNotFoundException;
	
}
