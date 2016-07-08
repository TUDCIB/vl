package de.tudresden.bau.cib.vl.core.model.ontology.bpmn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import jsdai.SIfc2x3.EIfcbuildingelement;
import jsdai.SIfc2x3.EIfcdoor;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcroof;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcslab;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcspatialstructureelement;
import jsdai.SIfc2x3.EIfcwall;
import jsdai.SIfc2x3.EIfcwindow;
import jsdai.lang.SdaiException;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.IfcIndividualFactory;
import de.tudresden.bau.cib.vl.core.process.AbstractBusinessProcessDelegate;

public class EnergyModelFactoryBusinessProxy extends AbstractBusinessProcessDelegate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3457234742374695757L;
	
	private String ifcModelId;
	private String ontologyModelId;
	
	
	public EnergyModelFactoryBusinessProxy(String processId, String ifcModelId, String ontologyModelId) {
		super(processId);
		this.ifcModelId = ifcModelId;
		this.ontologyModelId = ontologyModelId;
	}

	/**
	 * @param buildingGuid
	 * @return The GlobalIds of all spaces in the building.
	 * @throws ExecutionException 
	 */
	public List<String> evaluateRoomModel(String buildingGuid) throws ExecutionException {
		System.out.println("Evaluate room model of building: "+buildingGuid);
		Ifc2x3DataModelProxy ifcModel = getIfcModel();
		OntologyModel ontModel = getOntologyModel();
		
		List<String> list = new ArrayList<String>();
		try {
			EIfcspace[] spacesOfBuilding = ifcModel.getSpacesOfBuilding(buildingGuid);
			if(spacesOfBuilding != null) {
				for(EIfcspace space : spacesOfBuilding) {
					list.add(space.getGlobalid(space));
				}
			}
		} catch (IfcException e) {
			throw new RuntimeException(e);
		} catch (SdaiException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * @param spaceGuid
	 * @return
	 * @throws FactoryException 
	 * @throws IfcException 
	 * @throws SdaiException 
	 * @throws ExecutionException 
	 */
	public String evaluateSpace(String spaceGuid) throws FactoryException, IfcException, SdaiException, ExecutionException {
		System.out.println("Evaluate space: "+spaceGuid);
		Ifc2x3DataModelProxy ifcModel = getIfcModel();
		EIfcspatialstructureelement spatialStructureElement = ifcModel.getIfcEntity(spaceGuid, EIfcspatialstructureelement.class);
		if(spatialStructureElement != null && spatialStructureElement instanceof EIfcspace) {
			EIfcspace space = (EIfcspace) spatialStructureElement;
			String globalId = ifcModel.getGlobalId(space);
			getFactory().createIndividual(globalId);
			return space.getGlobalid(space);
		}
		return null;
	}
	
	public List<String> evaluateConstructionsOfSpace(String spaceGuid) throws ExecutionException {
		System.out.println("Evaluate constructions of space: "+spaceGuid);
		List<String> constructionGuidsOfSpace = new ArrayList<String>();
		try {
			Ifc2x3DataModelProxy ifcModel = getIfcModel();
			EIfcspatialstructureelement spatialStructureElement = ifcModel.getIfcEntity(spaceGuid, EIfcspatialstructureelement.class);
			if(spatialStructureElement != null && spatialStructureElement instanceof EIfcspace) {
				EIfcspace space = (EIfcspace) spatialStructureElement;
				Set<EIfcelement> elementsOfSpace = ifcModel.getBoundingElementsOfSpace(space);
				
				for(EIfcelement element : elementsOfSpace) {
					String constructionGuid = element.getGlobalid(element);
					constructionGuidsOfSpace.add(constructionGuid);
					System.out.println("added: "+constructionGuid);
				}
			}
			System.out.println("Returning: "+constructionGuidsOfSpace);
			return constructionGuidsOfSpace;
		} catch (SdaiException e) {
			throw new RuntimeException(e);
		} catch (IfcException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String evaluateConstruction(String constructionGuidOfSpace) throws ExecutionException {
		System.out.println("Evaluate construction: "+constructionGuidOfSpace);
		try {
			Ifc2x3DataModelProxy ifcModel = getIfcModel();
			EIfcelement element = ifcModel.getIfcEntity(constructionGuidOfSpace, EIfcelement.class);
			if(element != null) {
				String globalId = ifcModel.getGlobalId(element);
				getFactory().createIndividual(globalId);
				System.out.println(element);
			}
			return "";
		} catch (IfcException e) {
			throw new RuntimeException(e);
		} catch (FactoryException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<String> getBuildings() throws ExecutionException {
		Ifc2x3DataModelProxy ifcModel = getIfcModel();
		try {
			return ifcModel.getBuildingsIDs();
		} catch (IfcException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<String> getSpaces(String buildingGuid) throws ExecutionException {
		Ifc2x3DataModelProxy ifcModel = getIfcModel();
		List<String> spaceGuids = new ArrayList<String>();
		try {
			EIfcspace[] spaces = ifcModel.getSpacesOfBuilding(buildingGuid);
			for(EIfcspace space : spaces) {
				spaceGuids.add(ifcModel.getGlobalId(space));
			}
		} catch (IfcException e) {
			throw new RuntimeException(e);
		}
		return spaceGuids;
	}
	
	public boolean isOutdoor(String guid) throws IfcException, ExecutionException {
		Ifc2x3DataModelProxy ifcModel = getIfcModel();
		EIfcbuildingelement buildingElement = ifcModel.getIfcEntity(guid, EIfcbuildingelement.class);
		return ifcModel.isOutdoorElement(buildingElement);
	}
	
	public boolean isWindow(String guid) throws IfcException, ExecutionException {
		Ifc2x3DataModelProxy ifcModel = getIfcModel();
		EIfcwindow window = ifcModel.getIfcEntity(guid, EIfcwindow.class);
		return window != null;
	}
	
	public boolean isDoor(String guid) throws IfcException, ExecutionException {
		Ifc2x3DataModelProxy ifcModel = getIfcModel();
		EIfcdoor door = ifcModel.getIfcEntity(guid, EIfcdoor.class);
		return door != null;
	}
	
	public boolean isSlab(String guid) throws IfcException, ExecutionException {
		Ifc2x3DataModelProxy ifcModel = getIfcModel();
		EIfcslab slab = ifcModel.getIfcEntity(guid, EIfcslab.class);
		return slab != null;
	}
	
	public boolean isRoof(String guid) throws IfcException, ExecutionException {
		Ifc2x3DataModelProxy ifcModel = getIfcModel();
		EIfcroof roof = ifcModel.getIfcEntity(guid, EIfcroof.class);
		return roof != null;
	}
	
	public boolean isWall(String guid) throws IfcException, ExecutionException {
		Ifc2x3DataModelProxy ifcModel = getIfcModel();
		EIfcwall wall = ifcModel.getIfcEntity(guid, EIfcwall.class);
		return wall != null;
	}
	
	private Map<String, EIfcroot> getIfcEntities() throws ExecutionException {
		OntologyBusinessProcessDataContainer container = (OntologyBusinessProcessDataContainer)getContainer();
		return container.getIfcEntities();
	}
	
	private Ifc2x3DataModelProxy getIfcModel() throws ExecutionException {
		OntologyBusinessProcessDataContainer container = (OntologyBusinessProcessDataContainer)getContainer();
		return container.getIfcModel(ifcModelId);
	}
	
	private OntologyModel getOntologyModel() throws ExecutionException {
		OntologyBusinessProcessDataContainer container = (OntologyBusinessProcessDataContainer)getContainer();
		return container.getOntologyModel(ontologyModelId);
	}
	
	private IfcIndividualFactory getFactory() throws ExecutionException {
		OntologyBusinessProcessDataContainer container = (OntologyBusinessProcessDataContainer)getContainer();
		return container.getFactory();
	}
}
