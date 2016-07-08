package de.tudresden.bau.cib.vl.core.model.ontology.bpmn;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jsdai.SIfc2x3.EIfcroot;

import org.activiti.engine.HistoryService;

import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.IfcIndividualFactory;
import de.tudresden.bau.cib.vl.core.process.BusinessProcessDataContainer;

/**
 * Container with non-serializable data for the ontology business process execution.
 * 
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class OntologyBusinessProcessDataContainer extends BusinessProcessDataContainer {
	
	private static final String FACTORY = "factory";
	private static final String IFC_ENTITIES = "ifcEntities";


	public OntologyBusinessProcessDataContainer(String id, HistoryService history) {
		super(id, history);
	}
	
	public void addIfcModel(Ifc2x3DataModelProxy ifcModel) {
		putData(ifcModel.getId(), ifcModel);
		addIfcEntities(ifcModel);
	}
	
	private void addIfcEntities(Ifc2x3DataModelProxy ifcModel) {
		try {
			Map<String, EIfcroot> ifcEntities = new HashMap<String, EIfcroot>();
			EIfcroot[] ifcRoots = ifcModel.getIfcEntitiesOf(EIfcroot.class);
			for(EIfcroot ifcRoot : ifcRoots) {
				String guid = ifcModel.getGlobalId(ifcRoot);
				ifcEntities.put(guid, ifcRoot);
			}
			putData(IFC_ENTITIES, ifcEntities);
		} catch (IfcException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, EIfcroot> getIfcEntities() {
		ConcurrentHashMap<String, Object> dataMap = getCacheMap();
		Object ifcEntities = dataMap.get(IFC_ENTITIES);
		if(ifcEntities != null && ifcEntities instanceof Map) {
			return (Map<String, EIfcroot>) ifcEntities; 
		}
		return null;
	}
	
	public Ifc2x3DataModelProxy getIfcModel(String id) {
		ConcurrentHashMap<String, Object> dataMap = getCacheMap();
		Object model = dataMap.get(id);
		if(model != null && model instanceof Ifc2x3DataModelProxy) {
			return (Ifc2x3DataModelProxy) model; 
		}
		return null;
	}
	
	public void addOntologyModel(OntologyModel ontModel) {
		putData(ontModel.getId(), ontModel);
	}
	
	public OntologyModel getOntologyModel(String id) {
		ConcurrentHashMap<String, Object> dataMap = getCacheMap();
		Object model = dataMap.get(id);
		if(model != null && model instanceof OntologyModel) {
			return (OntologyModel) model; 
		}
		return null;
	}
	
	public void addIfcIndividualFactory(IfcIndividualFactory factory) {
		putData(FACTORY, factory);
	}

	public IfcIndividualFactory getFactory() {
		ConcurrentHashMap<String, Object> dataMap = getCacheMap();
		Object factory = dataMap.get(FACTORY);
		if(factory != null && factory instanceof IfcIndividualFactory) {
			return (IfcIndividualFactory) factory; 
		}
		return null;
	}

}
