package de.tudresden.bau.cib.vl.core.model;



/**
 * The link model contains references from models to model and their content.
 * 
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public abstract class LinkModel extends Model {

	public static final String URI_MODEL_PATTERN = "?modelId=";
	public static final String URI_MODEL_ELEMENT_PATTERN = "elementId=";
	
	
	/**
	 * Build a unique URI.
	 * @param namespace The namespace of the model.
	 * @param modelType The model type.
	 * @param modelId The model identifier.
	 * @param elementId The element identifier in the model.
	 * @return URI for an individual, e.g. http://141.30.143.53/ontology/BIMOnto#IFC?modelId=ISES_BESTAND&elementId=3AyA6xos11EPGiRkkXQuxG
	 */
	public final static String getUniformResourceIdentifierOfElementInModel(String namespace, String modelType, String modelId, String elementId) {
		if(namespace == null || modelType == null || modelId == null || elementId == null) return null;
		String uri = namespace + modelType+URI_MODEL_PATTERN + modelId+"&"+URI_MODEL_ELEMENT_PATTERN+elementId;
		return uri;
	}
	
	/**
	 * Build a unique URI.
	 * @param model The model.
	 * @param elementId The element identifer in the model.
	 * @return A unique identifier of the model element in the link model.
	 */
	public final static String getUniformResourceIdentifierOfElementInModel(Model model, String elementId) {
		return getUniformResourceIdentifierOfElementInModel(
				model.getNameSpace(), model.getModelType(), model.getId(), elementId);
	}
	
	/**
	 * Retrieves the element identifier in a model from a given URI.
	 * @param uri
	 * @return The element identifier.
	 */
	public final static String getElementIdentifierFromUri(String uri) {
		int index = uri.lastIndexOf(URI_MODEL_ELEMENT_PATTERN)+URI_MODEL_ELEMENT_PATTERN.length();
		String id = uri.substring(index);
		return id;
	}
	
	/**
	 * Retrieves the model identifier from a given URI.
	 * @param uri
	 * @return The model identifier.
	 */
	public final static String getModelIdentifierFromUri(String uri) {
		// FIXME use better patterns because this currently not work!
		int modelIndex = uri.lastIndexOf(URI_MODEL_PATTERN);
		int elementIndex = uri.lastIndexOf(URI_MODEL_ELEMENT_PATTERN);
		String id = uri.substring(modelIndex+1, elementIndex);
		return id;
	}
	
	/**
	 * Build the model identifier.
	 * 14.11.2014 example: http://141.30.143.53/ontology/ifcOWL#IFC:ISES_BESTAND::3AyA6xos11EPGiRkkXQuxG
	 * @param model
	 * @return A unique identifier of the model in the link model.
	 */
	public final static String getUniformResourceIdentifierOfModel(Model model) {
		String uri = model.getNameSpace() + model.getModelType()+URI_MODEL_PATTERN + model.getId();
		return uri;
	}
}
