package de.tudresden.bau.cib.vl.gui.bim.controller;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Derivation;
import com.hp.hpl.jena.reasoner.ValidityReport;

import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.ontology.EnergyEnhancedBimModel;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.ReasoningException;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.Reasoner;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.RuleSet;
import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
import de.tudresden.bau.cib.vl.core.model.ontology.sparql.EeBimQueryExecutor;
import de.tudresden.bau.cib.vl.core.model.ontology.sparql.EeBimQueryExecutor.VARIABLES;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.BIMOntoVocabulary;
import de.tudresden.bau.cib.vl.gui.bim.view.OntologyView;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.OntologyModelValidationDialog;
import de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer.OntologyTreeNode;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;

public class OntologyController extends FileController<OntologyView> {
	
	private OntologyService ontologyService;
	
	private List<OntologyTreeNode> selectedNodes = new ArrayList<OntologyTreeNode>();
	
	private boolean isDirty = false;
	

	
	public boolean isDirty()
	{
		return isDirty;
	}
	
	public void applyRuleSets(final Collection<RuleSet> ruleSets) {
		try {
			final OntologyModel ontologyModel = ((OntologyModel)model).clone();
			Reasoner reasoner = ontologyService.applyRuleSets(ontologyModel, ruleSets);
			
			// we assume the model is valid
			final OntologyController controller = this;
			final InfModel infModel = reasoner.getInferenceModel();
			
			// add the inferences
			ontologyModel.getUnderlyingModel().add(infModel);
			// we temporarily add the inferences to the model and let the user recreate the state if wants
			
			Display.getCurrent().asyncExec(new Runnable() {

				@Override
				public void run() {
//					OntologyInferenceModelDialog dialog = new OntologyInferenceModelDialog(
//							Display.getCurrent().getActiveShell(), infModel, ruleSets, controller);
//					dialog.open();
//					if(dialog.open() == Window.OK) {
//						boolean useInferredModel = MessageDialog.openQuestion(
//								Display.getCurrent().getActiveShell(), 
//								"Use the inferred model?", 
//								"Do you want to use the inferred information from rules in the further process?");
//						if(useInferredModel) {
//							// add inferences to model
//							model = ontologyModelAfterRules;
//						}
//					} else { // add inferences NOT to the model
//						// recreate the state before the rules
//						model = ontologyModelBeforeRules;
//					}
					// apply inference results e.g. to color elements in a viewer
					applyInferResult(ruleSets, ontologyModel);
				}

				private void applyInferResult(Collection<RuleSet> ruleSets, OntologyModel model) {
					for(RuleSet rs : ruleSets) {
						if(rs.getId().startsWith("colored")) {
							Map<Color, Collection<String>> colorOfEntities = new HashMap<Color, Collection<String>>();
							// critical elements
							List<Map<String,String>> criticalElementSolutions = EeBimQueryExecutor.findBuildingElementsWithSummary(model, 0);
							// warning elements
							List<Map<String,String>> warningElementSolutions = EeBimQueryExecutor.findBuildingElementsWithSummary(model, 1);
							// information elements
							List<Map<String,String>> informationElementSolutions = EeBimQueryExecutor.findBuildingElementsWithSummary(model, 2);
														
							if(criticalElementSolutions != null) {
								Set<String> criticalElements = new HashSet<String>();
								for(Map<String,String> ces : criticalElementSolutions) {
									String guid = ces.get(VARIABLES.guid.name());
									criticalElements.add(guid);
								}
								colorOfEntities.put(new Color(Display.getDefault(),255,0,0), criticalElements);
							}
							if(warningElementSolutions != null) {
								Set<String> warningElements = new HashSet<String>();
								for(Map<String,String> wes : warningElementSolutions) {
									String guid = wes.get(VARIABLES.guid.name());
									warningElements.add(guid);
								}
								colorOfEntities.put(new Color(Display.getDefault(),255,165,0), warningElements);
							}
							if(informationElementSolutions != null) {
								Set<String> infoElements = new HashSet<String>();
								for(Map<String,String> ies : informationElementSolutions) {
									String guid = ies.get(VARIABLES.guid.name());
									infoElements.add(guid);
								}
								colorOfEntities.put(new Color(Display.getDefault(),0,255,0), infoElements);
							}						
							
							sendSync(CommunicationEvents.IFC_SELECTION_COLORED, colorOfEntities);
						}
					}
				}			
			});

		} catch (ReasoningException e) {
			LOG.error("Rule set application problem: {}", 
					new Object[]{e.getMessage()}, e);
			showMessage("Apply rule set problem", "The rule sets cannot be applied \n"+e.getMessage(), true);
		} catch (CloneNotSupportedException e) {
			LOG.error("OntologyModel copy problem: {}", 
					new Object[]{e.getMessage()}, e);
			showMessage("OntologyModel copy problem", "The ontology model cannot be cloned \n"+e.getMessage(), true);
		}
	}
	
	public void validateInferenceModel() {
		Reasoner reasoner = ontologyService.validate((OntologyModel)model);
		ValidityReport validityReport = reasoner.getValidityReport();
		if(!validityReport.isValid()) { // not valid
			OntologyModelValidationDialog dialog = new OntologyModelValidationDialog(
					Display.getCurrent().getActiveShell(), validityReport);
			if(dialog.open() == Window.OK) {
				
			} else {
				
			}
//			StringBuilder summaryBuilder = new StringBuilder();
//			Iterator<Report> reportIterator = validityReport.getReports();
//			while(reportIterator.hasNext()) {
//				Report report = reportIterator.next();
//				summaryBuilder.append(report.getDescription()+"\n");
//			}
//			showMessage("Reasoning error", summaryBuilder.toString(), true);
		}
	}
	
	private void printStatements(Resource S, Property p, Resource O, PrintWriter writer, InfModel inferenceModel) {
	    for (StmtIterator i = inferenceModel.listStatements(S, p, O); i.hasNext(); ) {
	        Statement s = i.nextStatement();
	        System.out.println("Statement is "+s);
	        for (Iterator<Derivation> id = inferenceModel.getDerivation(s); id.hasNext(); ) {
	            Derivation deriv = id.next();
	            deriv.printTrace(writer, true);
	        }
	    }
	}
	
	public Collection<Resource> listResourceClasses() {
		Set<Resource> resources = new HashSet<Resource>();
		// take eeBIM classes
		resources.add(EeBIMOntoVocabulary.CLIMATE_LOCATION);
		resources.add(EeBIMOntoVocabulary.COMBUSTIBLE);
		resources.add(EeBIMOntoVocabulary.CONSTRUCTION);
		resources.add(EeBIMOntoVocabulary.SPACE_TYPE);
		resources.add(EeBIMOntoVocabulary.SUMMARY);
		// take only sub classes of IfcRoot
		resources.add(BIMOntoVocabulary.IFC_BEAM);
		resources.add(BIMOntoVocabulary.IFC_BUILDING);
		resources.add(BIMOntoVocabulary.IFC_BUILDING_STOREY);
		resources.add(BIMOntoVocabulary.IFC_COLUMN);
		resources.add(BIMOntoVocabulary.IFC_CURTAIN_WALL);
		resources.add(BIMOntoVocabulary.IFC_DOOR);
		resources.add(BIMOntoVocabulary.IFC_PILE);
		resources.add(BIMOntoVocabulary.IFC_ROOF);
		resources.add(BIMOntoVocabulary.IFC_SITE);
		resources.add(BIMOntoVocabulary.IFC_SLAB);
		resources.add(BIMOntoVocabulary.IFC_SPACE);
		resources.add(BIMOntoVocabulary.IFC_STAIR);
		resources.add(BIMOntoVocabulary.IFC_WALL);
		resources.add(BIMOntoVocabulary.IFC_WALL_STANDARD_CASE);
		resources.add(BIMOntoVocabulary.IFC_WINDOW);

		return resources;
	}
	
	/**
	 * Lists all properties of the EeBimVocabulary through reflections.
	 * @return All DatatypeProperty and ObjectProperty instances
	 */
	public Collection<Property> listEnergyRelatedProperties() {
		Set<Property> properties = new HashSet<Property>();
		try {
			Field[] fields = EeBIMOntoVocabulary.class.getFields();
			for(Field field : fields) {
				Class<?> type = field.getType();
				// we only want to have Property types (e.g. DatatypeProperty and ObjectProperty)
				if(Property.class.isAssignableFrom(type)) { 
					Object fieldValue = field.get(null);
					if(fieldValue instanceof Property) {
						Property property = (Property) fieldValue;
						properties.add(property);
					}
				}
			}
		} catch (IllegalArgumentException e) {
			LOG.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			LOG.error(e.getMessage(), e);
		}
		return properties;
	}
	
	public Collection<RuleSet> listRuleSets() {
		try {
			return ontologyService.listRuleSets();
		} catch (Exception e) {
			showMessage("Apply rule set problem", e.getMessage(), true);
		}
		return new HashSet<RuleSet>();
	}
	
	public Collection<Statement> listEeBimStatements() {		
		Collection<Statement> statements = new ArrayList<Statement>();
		OntologyModel ontologyModel = (OntologyModel) model;
		
		List<Map<String, String>> resultList = EeBimQueryExecutor.findBuildings(ontologyModel);
		for(int i = 0; i < resultList.size(); i++) {
			Map<String, String> resultEntry = resultList.get(i);
			String uri = resultEntry.get(VARIABLES.x);
			Individual building = ontologyModel.getIndividualByURI(uri);
			
			Statement stmtCombustibleConsumption = findOrCreateStatement(
					building, 
					EeBIMOntoVocabulary.COMBUSTIBLE_CONSUMPTION, 
					ontologyModel.getUnderlyingModel().createTypedLiteral(new Double(0)));
			statements.add(stmtCombustibleConsumption);
			
			Individual heatingSystem1 = ontologyModel.createIndividual(EeBIMOntoVocabulary.HEATING_SYSTEM, 
					EnergyEnhancedBimModel.NS_EEBIM+UUID.randomUUID().toString()+"_enev_heatingSystem_"+i);
			Statement stmtHeatingSystem = findOrCreateStatement(
					building, 
					EeBIMOntoVocabulary.HAS_HEATING_SYSTEM, 
					heatingSystem1);
			statements.add(stmtHeatingSystem);
			
			Individual petroleumGas = EeBIMOntoVocabulary._PETROLEUM_GAS;
			Statement stmtGas = findOrCreateStatement(
					heatingSystem1, 
					EeBIMOntoVocabulary.HAS_COMBUSTIBLE, 
					petroleumGas);
			statements.add(stmtGas);
			
			Individual centralHotWaterPrep = EeBIMOntoVocabulary._CENTRAL_HOT_WATER_PREPARATION;
			Statement stmtCentralHotWater = findOrCreateStatement(
					building, 
					EeBIMOntoVocabulary.HAS_WATER_PREPARATION, 
					centralHotWaterPrep);
			statements.add(stmtCentralHotWater);
			
			Statement stmtPersonLoad = findOrCreateStatement(
					building, 
					EeBIMOntoVocabulary.PERSON_LOAD, 
					ontologyModel.getUnderlyingModel().createTypedLiteral(new Long(0)));
			statements.add(stmtPersonLoad);
			
			Statement stmtDwellingArea = findOrCreateStatement(
					building, 
					EeBIMOntoVocabulary.DWELLING_AREA, 
					ontologyModel.getUnderlyingModel().createTypedLiteral(new Double(0)));
			statements.add(stmtDwellingArea);
			
			Statement stmtWindowArea = findOrCreateStatement(
					building, 
					EeBIMOntoVocabulary.WINDOW_AREA, 
					ontologyModel.getUnderlyingModel().createTypedLiteral(new Double(0)));
			statements.add(stmtWindowArea);
			
			Statement stmtBuildingEnvelopeArea = findOrCreateStatement(
					building, 
					EeBIMOntoVocabulary.BUILDING_ENVELOPE_AREA, 
					ontologyModel.getUnderlyingModel().createTypedLiteral(new Double(0)));
			statements.add(stmtBuildingEnvelopeArea);
			
			Statement stmtDecreasedThermalBridge = findOrCreateStatement(
					building, 
					EeBIMOntoVocabulary.DECREASED_THERMAL_BRIDGE_OVERHEAD, 
					ontologyModel.getUnderlyingModel().createTypedLiteral(new Float(0)));
			statements.add(stmtDecreasedThermalBridge);
			
			Statement stmtOverallUValueProduct = findOrCreateStatement(
					building, 
					EeBIMOntoVocabulary.OVERALL_AREA_UVALUE_PRODUCT, 
					ontologyModel.getUnderlyingModel().createTypedLiteral(new Double(0)));
			statements.add(stmtOverallUValueProduct);
			
			Statement stmtIsIsolated = findOrCreateStatement(
					building, 
					EeBIMOntoVocabulary.IS_ISOLATED, 
					ontologyModel.getUnderlyingModel().createTypedLiteral(new Boolean(true)));
			statements.add(stmtIsIsolated);
		}
		
		
		return statements;
	}
	
	private Statement findOrCreateStatement(Resource subject, Property predicate, RDFNode defaultValue) {
		OntologyModel ontologyModel = (OntologyModel) model;
		StmtIterator iter = ontologyModel.getUnderlyingModel().listStatements(subject, predicate, (RDFNode)null);
		List<Statement> iterList = iter.toList();
		if(iterList.size() > 1) throw new IllegalStateException("Too many statements for subject: "+subject+" and property: "+predicate);
		if(iterList.size() == 1) return iterList.get(0);
		Statement stmt = ontologyModel.getUnderlyingModel().createStatement(
				subject, 
				predicate, 
				defaultValue);
		ontologyModel.getUnderlyingModel().add(stmt);
		return stmt;
	}
	
	public Collection<Statement> listEnergyRelatedStatementsOfResource(Resource selectedResource) {
		Collection<Statement> statements = new ArrayList<Statement>();
		OntologyModel ontologyModel = (OntologyModel) model;
		Collection<Individual> individuals = ontologyModel.listIndividualsOfResource(selectedResource);
		Collection<Property> properties = listEnergyRelatedProperties();
		for(Individual ind : individuals) {
			for(Property prop : properties) {
				Collection<Statement> individualStatements = ontologyService.listStatements(ontologyModel, ind, prop, null);
				statements.addAll(individualStatements);
			}
			
		}
		
		return statements;
	}
	

	@Override
	public void parseFile()
	{
		//TODO
		
	}	
	
	public void setSelectedNodes(List<OntologyTreeNode> selectedNodes)
	{
		this.selectedNodes= new ArrayList<OntologyTreeNode>(selectedNodes);
		
		
	}
	
	
	public List<OntologyTreeNode> getSelectedNodes() {
		return selectedNodes;
	}
	
	
	
	

	
	@Override
	protected Set<CommunicationEvents> defineReceivedEvents() {
		Set<CommunicationEvents> events = new HashSet<CommunicationEvents>();
		events.add(CommunicationEvents.ONTOLOGY_MODEL);
		return events;
	}
	
	@Override
	protected void handleEvent(CommunicationEvents event, Map<String, Object> dataMap) {
		LOG.debug("Retrieving data: {} with topic: {}", 
				new Object[]{dataMap, event.getName()});
		Object data = dataMap.get(CommunicationService.PROPERTIES_KEY_DATA);
		
		switch(event) {
		case ONTOLOGY_MODEL: 				
			OntologyModel ontologyModel = (OntologyModel) data;
			setModel(ontologyModel);			
			break;	
		
	
		default:	
			break;
		}
	}
	
	@Override
	public void setModel(Model model)
	{
		if(this.model != null)
		{
			isDirty = true;
		}		
		
		
		
		super.setModel(model);	
			
		
	}

	
	public String getNameOfIfcRoot(Resource res) {
		String name = "";
		OntologyModel ontologyModel = (OntologyModel) model;
		Collection<Statement> nameStatements = ontologyService.listStatements(ontologyModel, res, BIMOntoVocabulary.NAME, null);
		if(nameStatements.size() != 1) {
			return "unknown";
//			throw new IllegalStateException("Too many or no names");
		}
		Statement nameStatement = nameStatements.iterator().next();
		Collection<Statement> stringValueStatements = ontologyService.listStatements(
				ontologyModel, nameStatement.getObject().asResource(), BIMOntoVocabulary.STRING_VALUE, null);
		if(stringValueStatements.size() != 1){
			return "unknown";
//			throw new IllegalStateException("Too many or no string values");
		}
		name = stringValueStatements.iterator().next().getObject().asLiteral().getString();
		return name;
	}
	
	public void onDropEvent(Object eventData, ContentMode mode, Object target)
	{
		Map<String, Object> dataMap 						= new HashMap<String, Object>();
		Map<String, Set<String>> resourceToIfcEntitiesMap 	= new HashMap<String, Set<String>>();
		Set<String> ifcEntities								= new HashSet<String>();
		Map<String, Object> uiData 							= new HashMap<String, Object>();
		
		String resourceUri = (String) eventData;
		
		ifcEntities.add(((OntologyTreeNode)target).getStrURI());
		uiData.put("mode", mode);
		uiData.put("node", (OntologyTreeNode)target);			
		
		resourceToIfcEntitiesMap.put(resourceUri, ifcEntities);		
		
		
		dataMap.put(CommunicationService.PROPERTIES_KEY_DATA, resourceToIfcEntitiesMap);
		dataMap.put(CommunicationService.PROPERTIES_KEY_UI_DATA, uiData);
		
		sendSync(CommunicationEvents.RESOURCE_TO_IFC, dataMap);
		
	}
	
	
	public void publishRemovingOfLink()
	{
		
		List<Triple> triples = new ArrayList<Triple>();		
		
		for(OntologyTreeNode node : selectedNodes)
		{
			if(node.getTriple() != null)
			{
				triples.add(node.getTriple());
			}
		}		
		
		if(triples.size() > 0)
		{
			sendSync(CommunicationEvents.ONTOLOGY_REMOVE_LINK, triples);
			isDirty = true;
		}
		
		
	}

	public void saveToFile()
	{
				
		sendSync(CommunicationEvents.ONTOLOGY_SAVE, null);
		
		isDirty = false;
	}


	public void setOntologyService(OntologyService ontologyService) {
		this.ontologyService = ontologyService;
	}

}
