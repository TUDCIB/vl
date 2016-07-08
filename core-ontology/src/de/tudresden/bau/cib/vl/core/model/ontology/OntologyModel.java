package de.tudresden.bau.cib.vl.core.model.ontology;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import de.tudresden.bau.cib.vl.core.model.LinkModel;
import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.ModelElementNotFoundException;
import de.tudresden.bau.cib.vl.core.model.ontology.parser.OwlParser;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.Reasoner;

/**
 * The ontology meta model for the virtual laboratory.
 * 
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public class OntologyModel extends LinkModel implements Cloneable {
	
	private OntModel ontModel;
	private Collection<com.hp.hpl.jena.rdf.model.Model> schemes;
	private Set<String> namespaces;

	/**
	 * Cache of the ontology classes
	 */
	private OntClassMap ontClassMap;

	/**
	 * Cache of the ontology object properties
	 */
	private ObjectPropertyMap objectPropertiesMap;
	
	/**
	 * Cache of the ontology datatype properties
	 */
	private DatatypePropertyMap datatypePropertiesMap;
	
	
	private OntologyModel() {		
		schemes = new ArrayList<com.hp.hpl.jena.rdf.model.Model>();
		namespaces = new HashSet<String>();
	}
	
	public OntologyModel(String id, OntModel metamodel) {
		this();
		this.id = id;
		this.ontModel = metamodel;
	}
	
	public OntologyModel(String id, OntModel metamodel, String filePathToModel) {
		this(id, metamodel);
		this.sourceFilePath = filePathToModel;
	}
	
	@Override
	public int hashCode() {
		return ontModel.hashCode() + schemes.hashCode() + namespaces.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		OntologyModel otherOntologyModel = (OntologyModel) other;
		return this.hashCode() == otherOntologyModel.hashCode() 
				&& this.namespaces.equals(otherOntologyModel.namespaces)
				&& ((objectPropertiesMap != null) ? (this.objectPropertiesMap.equals(otherOntologyModel.objectPropertiesMap)) : true)
				&& ((datatypePropertiesMap != null) ? (this.datatypePropertiesMap.equals(otherOntologyModel.datatypePropertiesMap)) : true)
				&& ((ontClassMap != null) ? (this.ontClassMap.equals(otherOntologyModel.ontClassMap)) : true)
				&& this.ontModel.equals(otherOntologyModel.ontModel);
	}
	
	/**
	 * 
	 * @param owlBaseNSToFilePaths
	 * @throws FileNotFoundException
	 */
	public void loadSubModels(Map<String, String> owlBaseNSToFilePaths) throws FileNotFoundException {		
		for(Map.Entry<String, String> entry : owlBaseNSToFilePaths.entrySet()) {
			String owlPath = entry.getValue();
			// read the RDF/XML file		
			com.hp.hpl.jena.rdf.model.Model subModel = FileManager.get().loadModel(owlPath, entry.getKey(), null);	
			ontModel.add(subModel);
			
			schemes.add(subModel);
		}
		namespaces.addAll(owlBaseNSToFilePaths.keySet());
	}
	
	/**
	 * 
	 * @param additionalModels
	 * @throws FileNotFoundException
	 */
	public void loadSubModels(String... additionalModels) throws FileNotFoundException {		
		for(String owlPath : additionalModels) {
			// read the RDF/XML file		
			com.hp.hpl.jena.rdf.model.Model subModel = FileManager.get().loadModel(owlPath, null);	
			ontModel.add(subModel);
			
			schemes.add(subModel);
		}
	}
	
	public Collection<Individual> listIndividuals() {
		Collection<Individual> individualCollection = new HashSet<>();
		ExtendedIterator<Individual> individuals = ontModel.listIndividuals();
		while(individuals.hasNext()) {
			individualCollection.add(individuals.next());
		}
		return individualCollection;
	}
	
	public Collection<Individual> listIndividualsOfResource(Resource resource) {
		Collection<Individual> individualCollection = new HashSet<>();
		ExtendedIterator<Individual> individuals = ontModel.listIndividuals(resource);
		while(individuals.hasNext()) {
			individualCollection.add(individuals.next());
		}
		return individualCollection;		
	}
	
	public void removeAllIndividualsOfType(OntClass type) throws ModelElementNotFoundException{
		ExtendedIterator<Individual> individuals = ontModel.listIndividuals(type);
		List<Individual> synchronizedList = Collections.synchronizedList(individuals.toList());
		for(Individual individual : synchronizedList) {
			removeIndividual(individual.getURI());
		}
	}
	
	public void removeIndividual(Model model, String elementId) throws ModelElementNotFoundException {
		String uri = getUniformResourceIdentifierOfElementInModel(model, elementId);
		removeIndividual(uri);
	}
	
	public void removeIndividual(String uri) throws ModelElementNotFoundException{
		if(uri != null && !uri.isEmpty()){			
			Individual ind = getElementInModelByElementId(uri);
			ind.remove();
		}
	}
	
	public Reasoner setupReasoner() {	
		Reasoner reasoner = new Reasoner(this);		
		return reasoner;
	}
	
	/**
	 * Use this method wisely!
	 * @return The Jena model.
	 */
	public OntModel getUnderlyingModel() {
		return ontModel;
	}
	
	/**
	 * @return Map with URI as keys and OntClass as values
	 */
	public OntClassMap getOntologyClasses() {	
		if(ontClassMap != null) return ontClassMap;
//		map with available class names for a comfortable corresponding JSDAI class mapping
		this.ontClassMap = new OntClassMap();
//		Fill the ontClassMap with all available classes
		ExtendedIterator<OntClass> classIterator = ontModel.listClasses();
		while(classIterator.hasNext()){
			OntClass c = classIterator.next();
			if(c != null) {
				String ontClassURI = c.getURI();
				if(ontClassURI != null) {
					ontClassMap.put(ontClassURI, c);
				}
			}
		}
		return ontClassMap;
	}
	
	/**
	 * @return Map with URI as keys and DatatypeProperty as values
	 */
	public DatatypePropertyMap getDatatypeProperties() {
		if(this.datatypePropertiesMap != null) return datatypePropertiesMap;
		this.datatypePropertiesMap = new DatatypePropertyMap();
		
		ExtendedIterator<DatatypeProperty> datatypePropertyIterator = ontModel.listDatatypeProperties();
		while(datatypePropertyIterator.hasNext()) {
			DatatypeProperty datatypeProperty = datatypePropertyIterator.next();
			if(datatypeProperty != null) {
				String datatypePropertyURI = datatypeProperty.getURI();
				if(datatypePropertyURI != null) {
					datatypePropertiesMap.put(datatypePropertyURI, datatypeProperty);
				}
			}
		}
		return datatypePropertiesMap;
	}
	
	/**
	 * @return Map with URI as keys and ObjectProperty as values
	 */
	public ObjectPropertyMap getOntologyProperties() {
		if(objectPropertiesMap != null) return objectPropertiesMap;
//		IFC attributes
		this.objectPropertiesMap = new ObjectPropertyMap();
		
//		Fill the objectPropertiesMap with all available object properties
		ExtendedIterator<ObjectProperty> objectPropertyIterator = ontModel.listObjectProperties();
		while(objectPropertyIterator.hasNext()){
			ObjectProperty op = objectPropertyIterator.next();
			if(op != null) {
				String objectPropertyURI = op.getURI();
				if(objectPropertyURI != null) {
					objectPropertiesMap.put(objectPropertyURI, op);
				}
			}
		}
		return objectPropertiesMap;
	}

	public Individual getElementInModelByElementId(String uri)
			throws ModelElementNotFoundException {
		ExtendedIterator<Individual> individualsIterator = ontModel.listIndividuals();
		while(individualsIterator.hasNext()){
			Individual individual = individualsIterator.next();
			String currentUri = individual.getURI();
			if(currentUri.equals(uri)) {
				return individual;
			}
		}
		return null;
	}
	
	@Override
	public String getNameSpace() {
		return "";
	}

	@Override
	public String getModelType() {
		return "";
	}
	
	public void addRelation(Individual fromInd, ObjectProperty property, Individual toInd) {
		ontModel.add(fromInd, property, toInd);
	}
	
	@Override
	public OntologyModel clone() throws CloneNotSupportedException {
		OntModel cloneModel = ModelFactory.createOntologyModel(OwlParser.DEFAULT_MODEL_SPEC);
		cloneModel.add(ontModel);
		return new OntologyModel(id, cloneModel, sourceFilePath);
	};
	
	/**
	 * Creates the individual if not already present in the ontology.
	 * If the individual already exists it will be returned.
	 * @param clazz
	 * @param uri
	 * @return
	 */
	public Individual createIndividual(OntClass clazz, String uri) {
		Individual ind = ontModel.getIndividual(uri);
		if(ind == null) {
			ind = ontModel.createIndividual(uri, clazz);
//			ind = clazz.createIndividual(uri);
			
		}
		return ind;
	}
	
	/**
	 * Returns the individual by the given URI if available.
	 * @param uri
	 * @return
	 */
	public Individual getIndividualByURI(String uri) {
		return ontModel.getIndividual(uri);
	}
	
	/**
	 * Returns the individual which belongs to the given model and element identifier.
	 * @param model
	 * @param elementId
	 * @return
	 */
	public Individual getIndividualByElementId(Model model, String elementId) {
		String uri = getUniformResourceIdentifierOfElementInModel(model, elementId);
		return getIndividualByURI(uri);
	}
	
	/**
	 * Retrieves the element identifier from a given URI.
	 * @param ind
	 * @return
	 */
	public static final String getElementId(Individual ind) {
		String uri = ind.getURI();
		int regex = uri.lastIndexOf(URI_MODEL_ELEMENT_PATTERN)+1;
		return uri.substring(regex);
	}
	
	public List<QuerySolution> executeSparqlQuery(String query) {
		Query q = QueryFactory.create(query);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(q, ontModel);
		ResultSet results = qe.execSelect();
		
		List<QuerySolution> solutions = new ArrayList<QuerySolution>();
		
		while(results.hasNext()){
			solutions.add(results.next());
		}
		
//		// Output query results
//		ResultSetFormatter.out(System.out, results, q);
		
		// Important - free up resources used running the query
		qe.close();
		
		return solutions;
	}
	
	public class OntClassMap extends HashMap<String, OntClass> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6880444868043186392L;
		
		@Override
		public OntClass put(String key, OntClass value) {
			key = key.toLowerCase();
			return super.put(key, value);
		}
		
		@Override
		public OntClass get(Object key) {
			String k = (String) key;
			k = k.toLowerCase();
			return super.get(k);
		}
	}
	
	public class DatatypePropertyMap extends HashMap<String, DatatypeProperty> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 749483564277765446L;
		
		@Override
		public DatatypeProperty put(String key, DatatypeProperty value) {
			key = key.toLowerCase();
			return super.put(key, value);
		}
		
		@Override
		public DatatypeProperty get(Object key) {
			String k = (String) key;
			k = k.toLowerCase();
			return super.get(k);
		}
	}
	
	public class ObjectPropertyMap extends HashMap<String, ObjectProperty> {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6898714527069991929L;
		
		@Override
		public ObjectProperty put(String key, ObjectProperty value) {
			key = key.toLowerCase();
			return super.put(key, value);
		}
		
		@Override
		public ObjectProperty get(Object key) {
			String k = (String) key;
			k = k.toLowerCase();
			return super.get(k);
		}
	}

	public Set<String> getNamespaces() {
		return namespaces;
	}

	public Collection<com.hp.hpl.jena.rdf.model.Model> getSchemes() {
		return schemes;
	}

}
