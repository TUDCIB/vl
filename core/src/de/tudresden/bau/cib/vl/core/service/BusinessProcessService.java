package de.tudresden.bau.cib.vl.core.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import de.tudresden.bau.cib.vl.core.process.BusinessProcessDataContainer;
import de.tudresden.bau.cib.vl.core.process.internal.BusinessProcessDataContainerManager;

public interface BusinessProcessService {

	/**
	 * <p>
	 * Starts the business process by adding the serializable information to the engine {@link org.activiti.engine.RuntimeService}
	 * and adding non-serializable data given as {@link BusinessProcessDataContainer} 
	 * to the {@link BusinessProcessDataContainerManager} so that the process delegates 
	 * have static access. This is important because Activiti handles actually only serializable data.
	 * </p>
	 * @param bpmnFile The BPMN file which defines the process.
	 * @param processId The identifier of the process.
	 * @param serializableData A map with serializable data. The keys are the names of the process variables.
	 * @param nonSerializableDataContainer A data container where non-serializable data can be stored temporarily.
	 * @return Process instance.
	 * @throws IOException
	 */
	ProcessInstance start(File bpmnFile, String processId,
			Map<String, Object> serializableData,
			BusinessProcessDataContainer nonSerializableDataContainer)
			throws IOException;
	
	/**
	 * <p>
	 * Starts the business process by adding the serializable information to the engine {@link org.activiti.engine.RuntimeService}
	 * and adding non-serializable data given as {@link BusinessProcessDataContainer} 
	 * to the {@link BusinessProcessDataContainerManager} so that the process delegates 
	 * have static access. This is important because Activiti handles actually only serializable data.
	 * </p>
	 * @param bpmnFileStream The BPMN file input stream which defines the process.
	 * @param processId The identifier of the process.
	 * @param serializableData A map with serializable data. The keys are the names of the process variables.
	 * @param nonSerializableDataContainer A data container where non-serializable data can be stored temporarily.
	 * @return Process instance.
	 * @throws IOException
	 */
	ProcessInstance start(InputStream bpmnFileStream, String processId,
			Map<String, Object> serializableData,
			BusinessProcessDataContainer nonSerializableDataContainer)
			throws IOException;

	/**
	 * <p>
	 * Retrieves result variables of the process. This means that the latest values are returned (the last value state).
	 * If the process doesn't overwrite values it returns the initial variables.
	 * </p>
	 * @param processInstanceId The process instance identifier returned by {@link #start(File, String, Map, BusinessProcessDataContainer)}
	 * @return Map with variable name as keys and their variable values.
	 */
	Map<String, Object> getResultVariables(String processInstanceId);

	InputStream getDiagramWithCurrentProcessState(ProcessInstance processInstance);

	/**
	 * Completes the user task and let continue the process.
	 * @param processInstance
	 * @param user The user to whom the task is assigned.
	 */
	void completeUserTask(ProcessInstance processInstance, String user);

}
