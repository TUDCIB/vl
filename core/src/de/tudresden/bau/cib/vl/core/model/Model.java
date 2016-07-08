package de.tudresden.bau.cib.vl.core.model;

import java.io.FileNotFoundException;

/**
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public abstract class Model {
	
	protected String id;
	protected String sourceFilePath;

	/**
	 * @return Returns the file path which was used to fill/generate the model.
	 * In the case of a building model it will return the IFC file which was used.
	 */
	public final String getSourceFilePath() {
		return sourceFilePath;
	}
	
	public final void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	/**
	 * Saves the model to the given path.
	 * @param filePath, local file path
	 * @throws Exception
	 */
	protected void save(String filePath) throws FileNotFoundException {
		this.sourceFilePath = filePath;
		save();
	}
	
	/**
	 * Can be used internally to save the model if needed.
	 */
	protected void save() throws FileNotFoundException {
		
	}
	
	
	/**
	 * @return The model identifier.
	 */
	public final String getId() {
		return id;
	}
	
	public final void setId(String id) {
		this.id = id;
	}

	/**
	 * e.g. IFC, OWL...
	 */
	public abstract String getModelType();
	
	public abstract String getNameSpace();
	
	@Override
	public String toString() {
		return super.toString()+"(id: "+id+ " ,sourceFilePath: "+sourceFilePath+")";
	}
}
