package de.tudresden.bau.cib.vl.core.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import de.tudresden.bau.cib.vl.core.process.BusinessProcessDataContainer;
import de.tudresden.bau.cib.vl.core.process.internal.BusinessProcessDataContainerManager;
import de.tudresden.bau.cib.vl.core.service.BusinessProcessService;

/**
 * <p>
 * Business process executed by Activiti. It prepares, executes and perform a follow-up of the process.
 * </p>
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class BusinessProcessServiceImpl implements BusinessProcessService {

	private RuntimeService runtimeService;
	private RepositoryService repositoryService;
	private HistoryService historyService;
	private TaskService taskService;
	
	
	@Override
	public final ProcessInstance start(File bpmnFile, String processId, Map<String, 
			Object> serializableData, BusinessProcessDataContainer nonSerializableDataContainer) throws IOException {	
		return start(new FileInputStream(bpmnFile), processId, serializableData, nonSerializableDataContainer);
	}
	
	@Override
	public ProcessInstance start(InputStream bpmnFileStream, String processId,
			Map<String, Object> serializableData,
			BusinessProcessDataContainer nonSerializableDataContainer)
			throws IOException {
		try {
			if(nonSerializableDataContainer != null) {
				// defensive copy of the container
				BusinessProcessDataContainer container = nonSerializableDataContainer.clone();
				// add non-serializable data to the manager
				BusinessProcessDataContainerManager.getInstance().addDataToCache(processId, container);
			}
			
			repositoryService.createDeployment().addInputStream(processId+".bpmn20.xml",
					bpmnFileStream).deploy();
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processId, serializableData);
			// remove non-serializable data from the manager (Garbage-collector-like)
			BusinessProcessDataContainerManager.getInstance().removeFromCache(processId);	
			return processInstance;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void completeUserTask(ProcessInstance processInstance, String user) {
//		List<Task> tasks = taskService.createTaskQuery().list();
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).taskAssignee(user).singleResult();
//		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).taskDefinitionKey(taskId).singleResult();
		taskService.complete(task.getId());
	}
	
	@Override
	public Map<String, Object> getResultVariables(String processInstanceId) {
		Map<String, Object> lastUpdatedValues = new HashMap<String, Object>();
		List<HistoricDetail> historicUpdates = historyService.createHistoricDetailQuery()
				.variableUpdates()
				.processInstanceId(processInstanceId)
				.orderByVariableName()
				.asc()
				.list();
		for(HistoricDetail historicDetail : historicUpdates) {
			HistoricVariableUpdate historicUpdate = (HistoricVariableUpdate) historicDetail;
			lastUpdatedValues.put(historicUpdate.getVariableName(), historicUpdate.getValue());
		}
		return lastUpdatedValues;
	}
	
	@Override
	public InputStream getDiagramWithCurrentProcessState(ProcessInstance processInstance) {	
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(
				processInstance.getProcessDefinitionId());
	 
	    if (processDefinition != null && processDefinition.isGraphicalNotationDefined() && !processInstance.isEnded()) {
	 
	      InputStream definitionImageStream = ProcessDiagramGenerator.generateDiagram(
	    		  processDefinition, "png", 
	    		  runtimeService.getActiveActivityIds(processInstance.getId()));
	      return definitionImageStream;
	    }
	    return null;
    }

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
}
