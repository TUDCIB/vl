package de.tudresden.bau.cib.vl.gui.common.communication;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.User;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;


/**
 * @author Ken
 * 
 */
public enum CommunicationEvents {
	
	
	/**
	 * Event after application is fully loaded;
	 */
	WORKBENCH_READY("WorkbenchReady", null), 
	/**
	 * New ifc file is available/ was uploaded; parameter = FileInformation
	 */
	IFC_FILE("IfcFile", FileInformation.class),
	/**
	 * parameter = Ifc2x3DataModelProxy
	 */
	IFC_MODEL("IfcModel", Ifc2x3DataModelProxy.class),
	
	/**
	 * parameter = collection of GlobalId Strings
	 */
	IFC_SELECTION("IfcSelection", Collection.class),
	/**
	 * parameter = collection of GlobalId Strings
	 */
	IFC_ZONESOFINTERESTS("IfcZonesOfInterests", Collection.class),	
	/**
	 * parameter = Map with Color as keys and collection of GlobalId as value
	 */
	IFC_SELECTION_COLORED("IfcSelectionColored", Map.class),
	
	/**
	 * parameter = collection with GlobalIds which should be uncolored
	 */
	IFC_SELECTION_UNCOLORED("IfcSelectionUnColored", List.class),
	/**
	 * parameter = collection of GlobalId Strings
	 */
	IFC_UNSELECTION("IfcUnselection", Collection.class),
	/**
	 * parameter = collection of GlobalId Strings
	 */
	IFC_SELECTION_SHOW("IfcSelectionShow", Collection.class),	

	IFC_SELECTION_SHOW_ALL("IfcSelectionShowAll", null),	
	/**
	 * parameter = collection of GlobalId Strings
	 */
	IFC_SELECTION_HIDE("IfcSelectionHide", Collection.class),
	/**
	 * parameter = collection of GlobalId Strings
	 */
	IFC_SELECTION_TRANSPARENT("IfcSelectionTransparent", Collection.class),
	/**
	 * parameter = collection of GlobalId Strings
	 */
	IFC_SELECTION_SOLID("IfcSelectionSolid", Collection.class),
	X3D_FILE("X3dFile", FileInformation.class),
	ONTOLOGY_MODEL("OntologyModel", OntologyModel.class),
	ONTOLOGY_SAVE("SaveOntology", OntologyModel.class),
	/**
	 * Parameter = List of ontology triples
	 */
	ONTOLOGY_REMOVE_LINK("RemoveLink",List.class),
	/**
	 * Event after resource was assigned to ifc element 
	 * Parameter = Map of resource URIs as keys and the assigned IFC entities to each resource (Map<String, Set<String>>)
	 */
	RESOURCE_TO_IFC("ResourceToIfc", Map.class),
	/**
	 * Parameter = SimulationProject
	 */
	SIMULATION_STATUS("SimulationStatus", SimulationProject.class),
	/**
	 * Event for starting the check of the simulation status
	 * Parameter = user
	 */
	CHECK_SIMULATION_STATUS("CheckSimulationStatus", User.class),
	PROJECT_SELECTED("ProjectSelected", Project.class),
	SIMULATION_PROJECT_SELECTED("SimulationProjectSelected", SimulationProject.class),
	/**
	 * Parameter = User
	 */
	USER_LOGIN("UserLogin",User.class),
	/**
	 * No parameter
	 */
	USERREPOSITORY_UPDATED("UserRepositoryUpdated", null),
	/**
	 * Parameter = TSimulationMatrix
	 */
	SIMULATION_MATRIX_MODEL("SimulationMatrixModel", TSimulationMatrix.class),
	/**
	 * Parameter = List of Combination Ids from sim matrix
	 */
	SIMULATION_MATRIX_REMOVE_COMBINATION("SimulationMatrixModelRemoveCombination", List.class),
	/**
	 * Parameter = List of pairs of Variation Ids (VREF) and IFC element Ids 
	 */
	SIMULATION_MATRIX_REMOVE_VARIANT("SimulationMatrixModelRemoveVariation", List.class),
	
	/**
	 * Event to notify listeners that loading a Project or SimulationProject is completed. 
	 * Parameter = project or simulation project
	 */
	PROJECT_LOADED("ProjectLoaded", null),
	
	
	

//	//CLIENT_WILDCARD ("client/*"),
//	CLIENT_NEW_FILE ("client/newFile", String.class), // file path
//	CLIENT_NEW_SELECTION ("client/newSelection", String[].class), // array of IDs
//	CLIENT_SHOW_ELEMENTS ("client/showElements", String[].class), // array of IDs
//	CLIENT_HIDE_ELEMENTS ("client/hideElements", String[].class), // array of IDs
//	CLIENT_SHOW_IFC_VIEWER ("client/showIfcViewer", Boolean.class),
//	CLIENT_WORKBENCH_INITIALIZED ("client/workbenchInitialized", Boolean.class),
//	CLIENT_USER_LOGGEDIN ("client/userLoggedIn", User.class),
//	CLIENT_PROJECT_SELECTED ("client/projectSelected", Project.class),
//	/**
//	 * DATA: Map<String,Set<EIfcroot>> with URI as key and Set<EIfcroot> IFC entities
//	 * UI_DATA: Map<String, Object> key: 'mode' - ContentMode, 'node' - TreeNode
//	 */
//	CLIENT_ASSIGN_RESOURCE ("client/assignResource", Map.class), 	
//	
//	
//	//SERVER_WILDCARD ("server/*"),
//	SERVER_NEW_IFCMODEL("server/newIfcModel", Ifc2x3DataModelProxy.class), // published by IFC controller which sends the IfcDataModel
//	SERVER_NEW_IFCFILE ("server/newIfcFile", FileInformation.class), // represents the file path of the IFC file (not the model instance!)
//	SERVER_NEW_FILE_X3D ("server/newFileX3D", FileInformation.class),
//	SERVER_NEW_ONTOLOGYMODEL ("server/newOntologyModel", OntologyModel.class),
//	SERVER_PROJECT_UPDATE ("server/projectUpdate", null), // event for refreshing the project view
	;
	
	private String name;
	private Class<?> type;

	private CommunicationEvents(String eventName, Class<?> type) {
		this.name = eventName;
		this.type = type;
	}

	public String getName() {
		return name;
	}	
	
	@Override
	public String toString() {
		return name;
	}
	
	public static CommunicationEvents findByAbbreviation(String name) {
		CommunicationEvents[] events = values();
		for(CommunicationEvents event : events) {
			if(event.getName().equalsIgnoreCase(name)) return event;
		}
		//if(name.startsWith("client/")) return CLIENT_WILDCARD;
		//if(name.startsWith("server/")) return SERVER_WILDCARD;
		return null;
	}

	public Class<?> getType() {
		return type;
	}
}
