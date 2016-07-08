package de.tudresden.bau.cib.vl.core.model.eeBim.service;

import java.util.List;

import de.tudresden.bau.cib.vl.core.model.eeBim.exception.ResourceServiceException;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.OmniClass;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.ClassifiableAnnotable;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.Eetemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.ResourceDescriptor;


/**
 * TODO JavaDoc
 * 
 * Currently defined under:
 * <a href="http://130.208.198.50:8080/dfservice/?_wadl">http://130.208.198.50:8080/dfservice/?_wadl</a>
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public interface DataResourceService extends TemplateService {
	
	enum CLASS_TYPE {
		WALL ("wall")
		;
		
		String type;
		
		private CLASS_TYPE(String type) {
			this.type = type;
		}
		
		public String getClassification() {
			return type;
		}
	}
	
	ResourceDescriptor getResource(String id) throws ResourceServiceException;

	void deleteResource(String id) throws ResourceServiceException;

	void postResource(ResourceDescriptor rd) throws ResourceServiceException;
	/**
	 * Search for all resources for example for catalogues like construction or material catalogues
	 * @return
	 * @throws ResourceServiceException
	 */
	List<ClassifiableAnnotable> listResourceCatalogues() throws ResourceServiceException;
	
	ClassifiableAnnotable getResourceDescriptor(String id) throws ResourceServiceException;
	
	List<ResourceDescriptor> getAllResources() throws ResourceServiceException;
	
	void putResource(ResourceDescriptor rd) throws ResourceServiceException;

	List<Eetemplate> getAllTemplates() throws ResourceServiceException;

	List<ClassifiableAnnotable> getAllEntities() throws ResourceServiceException;

	Eetemplate getTemplate(String id) throws ResourceServiceException;
	
	List<Eetemplate> getTemplatesWhichContains(String pattern) throws ResourceServiceException;
	
	List<Eetemplate> getTemplatesOfType(CLASS_TYPE type) throws ResourceServiceException;
	
	List<Eetemplate> getTemplatesByOmniClass(OmniClass oc) throws ResourceServiceException;
	
	String getContentOfResource(String id) throws ResourceServiceException;
	
	String getAllResourcesFromCatalogue(String catalogueId) throws ResourceServiceException;

	String getResourceFromCatalogue(String resourceId, String catalogueId)
			throws ResourceServiceException;
	
	ClassifiableAnnotable searchProfile(String tableId, String omniClassId) throws ResourceServiceException;

}
