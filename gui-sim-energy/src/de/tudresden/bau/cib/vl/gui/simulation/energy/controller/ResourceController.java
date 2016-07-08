package de.tudresden.bau.cib.vl.gui.simulation.energy.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.SpaceTypeTemplate;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.common.controller.AbstractViewController;
import de.tudresden.bau.cib.vl.gui.core.controller.SessionManagementController;
import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.ResourceView;


public class ResourceController extends AbstractViewController<ResourceView> {

	private TemplateService templateService;	
	private List<ConstructionTemplate> constructionResources;
	private List<SpaceTypeTemplate> occupancyResources;
	private List<ClimateLocationTemplate> climateResources;
	
	private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class);
	
	public enum ResourceType {
		CONSTRUCTION,
		OCCUPANCY,
		CLIMATE
	}
	
	
	public void loadConstructionResources() {
		final Integer userId = SessionManagementController.getInstance().getUser().getId();
		Job job = new Job("Load construction data Job") {
			  @Override
			  protected IStatus run(IProgressMonitor monitor) {
					try {
						constructionResources = templateService.listConstructionResources(userId);
						view.setInput(ResourceType.CONSTRUCTION);
						return Status.OK_STATUS;
					} catch (Exception e) {
						LOG.error("{}", e.getMessage(), e);
					} 
					return Status.CANCEL_STATUS;
			  }
		};
		// Start the Job
		job.setPriority(Job.LONG);
		job.schedule(); 
	}

	public void loadOccupancyResources() {
		final Integer userId = SessionManagementController.getInstance().getUser().getId();
		Job job = new Job("Load occupancy data Job") {
			  @Override
			  protected IStatus run(IProgressMonitor monitor) {
					try {
						occupancyResources = templateService.listOccupancyResources(userId);	
						view.setInput(ResourceType.OCCUPANCY);
						return Status.OK_STATUS;
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
					return Status.CANCEL_STATUS;
			  }
		};
		// Start the Job
		job.setPriority(Job.LONG);
		job.schedule(); 
	}
	
	public void loadClimateResources() {
		final Integer userId = SessionManagementController.getInstance().getUser().getId();
		Job job = new Job("Load climate data Job") {
			  @Override
			  protected IStatus run(IProgressMonitor monitor) {
					try {
						climateResources = templateService.listClimateResources(userId);
						view.setInput(ResourceType.CLIMATE);
						return Status.OK_STATUS;
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
					return Status.CANCEL_STATUS;
			  }
		};
		// Start the Job
		job.setPriority(Job.LONG);
		job.schedule(); 

	}
	
	@Override
	protected Set<CommunicationEvents> defineReceivedEvents() {
		return null;
	}

	@Override
	protected BundleContext getBundleContext() {
		return Activator.getDefault().getContext();
	}

	@Override
	protected void handleEvent(CommunicationEvents event,
			Map<String, Object> dataMap) {
		
	}
	
	public List<ConstructionTemplate> getConstructionResources() {
		return constructionResources;
	}

	public List<SpaceTypeTemplate> getOccupancyResources() {
		return occupancyResources;
	}

	public List<ClimateLocationTemplate> getClimateResources() {
		return climateResources;
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
}
