package de.tudresden.bau.cib.vl.core.model.ontology.vocabulary;

/**
 * TODO JavaDoc
 * @author  <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 */
public enum AllowedIfcClasses {
	/**
	 * @uml.property  name="iFC_BEAM"
	 * @uml.associationEnd  
	 */
	IFC_BEAM ("IfcBeam"),
	/**
	 * @uml.property  name="iFC_BUILDING"
	 * @uml.associationEnd  
	 */
	IFC_BUILDING ("IfcBuilding"),
	/**
	 * @uml.property  name="iFC_BUILDINGELEMENT"
	 * @uml.associationEnd  
	 */
	IFC_BUILDINGELEMENT ("IfcBuildingElement"),
	/**
	 * @uml.property  name="iFC_BUILDINGELEMENTPROXY"
	 * @uml.associationEnd  
	 */
	IFC_BUILDINGELEMENTPROXY ("IfcBuildingElementProxy"),
	/**
	 * @uml.property  name="iFC_BUILDINGSTOREY"
	 * @uml.associationEnd  
	 */
	IFC_BUILDINGSTOREY ("IfcBuildingStorey"), 
	/**
	 * @uml.property  name="iFC_COLUMN"
	 * @uml.associationEnd  
	 */
	IFC_COLUMN ("IfcColumn"),
	/**
	 * @uml.property  name="iFC_CURTAINWALL"
	 * @uml.associationEnd  
	 */
	IFC_CURTAINWALL ("IfcCurtainWall"),
	/**
	 * @uml.property  name="iFC_DOOR"
	 * @uml.associationEnd  
	 */
	IFC_DOOR ("IfcDoor"),
	/**
	 * @uml.property  name="iFC_ELEMENTQUANTITY"
	 * @uml.associationEnd  
	 */
	IFC_ELEMENTQUANTITY ("IfcElementQuantity"),
	/**
	 * @uml.property  name="iFC_FOOTING"
	 * @uml.associationEnd  
	 */
	IFC_FOOTING ("IfcFooting"),
	/**
	 * @uml.property  name="iFC_GLOBALLYUNIQUEID"
	 * @uml.associationEnd  
	 */
	IFC_GLOBALLYUNIQUEID ("IfcGloballyUniqueId"),
	/**
	 * @uml.property  name="iFC_LABEL"
	 * @uml.associationEnd  
	 */
	IFC_LABEL ("IfcLabel"),
	/**
	 * @uml.property  name="iFC_AREAMEASURE"
	 * @uml.associationEnd  
	 */
	IFC_AREAMEASURE ("IfcAreaMeasure"),
	/**
	 * @uml.property  name="iFC_LENGTHMEASURE"
	 * @uml.associationEnd  
	 */
	IFC_LENGTHMEASURE ("IfcLengthMeasure"),
	/**
	 * @uml.property  name="iFC_VOLUMEMEASURE"
	 * @uml.associationEnd  
	 */
	IFC_VOLUMEMEASURE ("IfcVolumeMeasure"),
	
	IFC_MATERIAL ("IfcMaterial"),
	IFC_MATERIALPROPERTIES ("IfcMaterialProperties"),
	IFC_MATERIALLAYER ("IfcMaterialLayer"),
	IFC_MATERIALLAYERSET ("IfcMaterialLayerSet"),
	IFC_MATERIALLAYERSETUSAGE ("IfcMaterialLayerSetUsage"),
	
	/**
	 * @uml.property  name="iFC_OPENINGELEMENT"
	 * @uml.associationEnd  
	 */
	IFC_OPENINGELEMENT ("IfcOpeningElement"),
	/**
	 * @uml.property  name="iFC_PILE"
	 * @uml.associationEnd  
	 */
	IFC_PILE("IfcPile"),
	
	IFC_PROPERTY ("IfcProperty"),
	
	IFC_PROPERTYSET ("IfcPropertySet"),
	/**
	 * @uml.property  name="iFC_PROPERTYSETDEFINITION"
	 * @uml.associationEnd  
	 */
	IFC_PROPERTYSETDEFINITION ("IfcPropertySetDefinition"),
	/**
	 * @uml.property  name="iFC_QUANTITYLENGTH"
	 * @uml.associationEnd  
	 */
	IFC_QUANTITYLENGTH ("IfcQuantityLength"),
	/**
	 * @uml.property  name="iFC_RELAGGREGATES"
	 * @uml.associationEnd  
	 */
	IFC_RELAGGREGATES ("IfcRelAggregates"),
	
	IFC_RELASSOCIATESMATERIAL ("IfcRelAssociatesMaterial"),
	/**
	 * @uml.property  name="iFC_RELCONTAINEDINSPATIALSTRUCTURE"
	 * @uml.associationEnd  
	 */
	IFC_RELCONTAINEDINSPATIALSTRUCTURE ("IfcRelContainedInSpatialStructure"),
	/**
	 * @uml.property  name="iFC_RELDEFINESBYPROPERTIES"
	 * @uml.associationEnd  
	 */
	IFC_RELDEFINESBYPROPERTIES ("IfcRelDefinesByProperties"),
	
	/**
	 * @uml.property  name="iFC_RELFILLSELEMENT"
	 * @uml.associationEnd  
	 */
	IFC_RELFILLSELEMENT ("IfcRelFillsElement"), // important for windows
	/**
	 * @uml.property  name="iFC_RELSPACEBOUNDARY"
	 * @uml.associationEnd  
	 */
	IFC_RELSPACEBOUNDARY ("IfcRelSpaceBoundary"),
	/**
	 * @uml.property  name="iFC_RELVOIDSELEMENT"
	 * @uml.associationEnd  
	 */
	IFC_RELVOIDSELEMENT ("IfcRelVoidsElement"), // important for windows
	/**
	 * @uml.property  name="iFC_ROOF"
	 * @uml.associationEnd  
	 */
	IFC_ROOF ("IfcRoof"),
	/**
	 * @uml.property  name="iFC_SITE"
	 * @uml.associationEnd  
	 */
	IFC_SITE ("IfcSite"),
	/**
	 * @uml.property  name="iFC_SLAB"
	 * @uml.associationEnd  
	 */
	IFC_SLAB ("IfcSlab"),
	/**
	 * @uml.property  name="iFC_SPACE"
	 * @uml.associationEnd  
	 */
	IFC_SPACE ("IfcSpace"),
	/**
	 * @uml.property  name="iFC_STAIR"
	 * @uml.associationEnd  
	 */
	IFC_STAIR ("IfcStair"),
	/**
	 * @uml.property  name="iFC_TEXT"
	 * @uml.associationEnd  
	 */
	IFC_TEXT ("IfcText"),
	/**
	 * @uml.property  name="iFC_WALL"
	 * @uml.associationEnd  
	 */
	IFC_WALL ("IfcWall"),
	/**
	 * @uml.property  name="iFC_WALLSTANDARDCASE"
	 * @uml.associationEnd  
	 */
	IFC_WALLSTANDARDCASE ("IfcWallStandardCase"),
	/**
	 * @uml.property  name="iFC_WINDOW"
	 * @uml.associationEnd  
	 */
	IFC_WINDOW ("IfcWindow"),
	/**
	 * @uml.property  name="iFC_VIRTUALELEMENT"
	 * @uml.associationEnd  
	 */
	IFC_VIRTUALELEMENT ("IfcVirtualElement");
	
	private final String name;
	
	private AllowedIfcClasses(String name) {
		this.name = name;
	}

	/**
	 * TODO javadoc
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	
	public String getNameToLowerCase() {
		return name.toLowerCase();
	}
}