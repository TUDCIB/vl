package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import java.io.File;

import de.tudresden.bau.cib.vl.core.exception.ToolException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;

public interface SpaceBoundaryService {


	/**
	 * Converts first level boundaries to second level boundaries based on the space boundary implementation agreement by buildingSMART.
	 * Therefore, the IFC2x3 model must contain IfcRelSpaceBoundary entities from type level 1.
	 * 
	 * This method calls the BSPro library of Granlund and retrieves the geometry for the space boundaries (area...)
	 * and geometry for rooms (area, volume...). After that it deletes the old first level space boundaries,
	 * load the XML file with the geometry information from BSPro and creates the second level space boundaries
	 * (type 2a and 2b) based on the input. 
	 * <p>
	 * <b>Note: Currently, the geometry is not created in IFC representation in the right manner.
	 * This means that a viewer visualize the boundaries but not at the right coordinates because they are currently wrong (in general - in some cases it is ok).
	 * But this affects not the simulation because the covnerter only takes the calculated areas,
	 * volumes and heights etc.
	 * </b>
	 * </p>
	 * 
	 * @param ifcModel The BIMFit model of the IFC2x3 file with first level space boundaries.
	 * @param file The IFC2x3 file with first level space boundaries for BSPro.
	 * @return The newly created IFC2x3 file with IfcRelSpaceBoundary entities of type 2a and 2b and the geometry information in IfcPropertySets (area, volume, height...).
	 * 
	 * @throws ToolException If a problem occurs by calling BSPro.
	 */
	File convertSpaceBoundaries(Ifc2x3DataModelProxy ifcModel, File file) throws ToolException;
}
