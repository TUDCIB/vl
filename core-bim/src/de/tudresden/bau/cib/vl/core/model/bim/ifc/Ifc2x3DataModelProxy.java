package de.tudresden.bau.cib.vl.core.model.bim.ifc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import jsdai.SIfc2x3.AIfcmateriallayer;
import jsdai.SIfc2x3.AIfcobject;
import jsdai.SIfc2x3.AIfcphysicalquantity;
import jsdai.SIfc2x3.AIfcreldefines;
import jsdai.SIfc2x3.AIfcroot;
import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingelement;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcelementquantity;
import jsdai.SIfc2x3.EIfcelementtype;
import jsdai.SIfc2x3.EIfcinternalorexternalenum;
import jsdai.SIfc2x3.EIfcmaterial;
import jsdai.SIfc2x3.EIfcmateriallayer;
import jsdai.SIfc2x3.EIfcmateriallayerset;
import jsdai.SIfc2x3.EIfcmateriallayersetusage;
import jsdai.SIfc2x3.EIfcmateriallist;
import jsdai.SIfc2x3.EIfcobject;
import jsdai.SIfc2x3.EIfcphysicalorvirtualenum;
import jsdai.SIfc2x3.EIfcphysicalquantity;
import jsdai.SIfc2x3.EIfcproduct;
import jsdai.SIfc2x3.EIfcproject;
import jsdai.SIfc2x3.EIfcproperty;
import jsdai.SIfc2x3.EIfcpropertydefinition;
import jsdai.SIfc2x3.EIfcquantitylength;
import jsdai.SIfc2x3.EIfcquantityvolume;
import jsdai.SIfc2x3.EIfcrelassociatesmaterial;
import jsdai.SIfc2x3.EIfcrelcontainedinspatialstructure;
import jsdai.SIfc2x3.EIfcreldefines;
import jsdai.SIfc2x3.EIfcreldefinesbyproperties;
import jsdai.SIfc2x3.EIfcreldefinesbytype;
import jsdai.SIfc2x3.EIfcrelfillselement;
import jsdai.SIfc2x3.EIfcrelspaceboundary;
import jsdai.SIfc2x3.EIfcroof;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcsiprefix;
import jsdai.SIfc2x3.EIfcsite;
import jsdai.SIfc2x3.EIfcsiunit;
import jsdai.SIfc2x3.EIfcsiunitname;
import jsdai.SIfc2x3.EIfcslab;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcspatialstructureelement;
import jsdai.SIfc2x3.EIfcstair;
import jsdai.SIfc2x3.EIfctypeobject;
import jsdai.SIfc2x3.EIfcunitenum;
import jsdai.SIfc2x3.EIfcvirtualelement;
import jsdai.SIfc2x3.EIfcwall;
import jsdai.SIfc2x3.EIfcwindow;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;
import jsdai.lang.SdaiIterator;
import de.tudresden.bau.cib.exceptions.ifc.StoreySortException;
import de.tudresden.bau.cib.filter.ifc.semantic.IfcDataModel;
import de.tudresden.bau.cib.filter.ifc.semantic.IfcDataModelImpl;
import de.tudresden.bau.cib.model.StepDataModel;
import de.tudresden.bau.cib.model.utility.IterableAggregate;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.step.StepDataModelProxy;

/**
 * Wrapper class for IfcDataModel of the Bimfit library.
 * 
 * @author Ken
 *
 */
public class Ifc2x3DataModelProxy extends StepDataModelProxy {
	
	public static final String NS_IFCOWL = "http://openeebim.bau.tu-dresden.de/dev/ontology/BIMOnto#";
//	public static final String NS_IFCOWL = "http://141.30.143.53/ontology/ifcOWL#";
	public static final String TYPE_IFC = "IFC";
	private IfcDataModel ifcModel;
	
	public static enum IfcSIUnitNameEnum {
		AMPERE,
		BECQUEREL,
		CANDELA,
		COULOMB,
		CUBIC_METRE,
		DEGREE_CELSIUS,
		FARAD,
		GRAM,
		GRAY,
		HENRY,
		HERTZ,
		JOULE,
		KELVIN,
		LUMEN,
		LUX,
		METRE,
		MOLE,
		NEWTON,
		OHM,
		PASCAL,
		RADIAN,
		SECOND,
		SIEMENS,
		SIEVERT,
		SQUARE_METRE,
		STERADIAN,
		TESLA,
		VOLT,
		WATT,
		WEBER
	}
	
	public enum Directions {
		NORTH(0),
		NORTH_EAST(1),
		EAST(2),
		SOUTH_EAST(3),
		SOUTH(4),
		SOUTH_WEST(5),
		WEST(6),
		NORTH_WEST(7)
		;
		
		private int direction = -1;
		
		private Directions(int direction) {
			this.direction = direction;
		}
		
		public int getDirection() {
			return direction;
		}
		
		public static Directions toDirection(int value) {
		    for (Directions direction : Directions.values()) {
		        if (direction.getDirection() == value)
		            return direction;
		    }
		    return null;
		}
	}
	
	public enum IfcSIPrefix {
		EXA (1000000000000000000.0),
		PETA (1000000000000000.0),
		TERA (1000000000000.0),
		GIGA (1000000000.0),
		MEGA (1000000.0),
		KILO (1000.0),
		HECTO (100.0),
		DECA (10.0),
		DECI (0.1),
		CENTI (0.01),
		MILLI (0.001),
		MICRO (0.000001),
		NANO (0.000000001),
		PICO (0.000000000001),
		FEMTO (0.000000000000001),
		ATTO (0.000000000000000001);
		
		double conversionValue;
		
		IfcSIPrefix(double conversionValue) {
			this.conversionValue = conversionValue;
		}
		
		public double getConversionValue() {
			return conversionValue;
		}
	}
	
	public enum Quantities {
		GrossWallArea,
		GrossVolume,
		GrossSideArea,
		GrossFloorArea,
		CrossSectionArea,
		FinishCeilingHigh,
		azimuth,
		tilt
	}
	
	public static final String BSPRO_AREA_PATTERN = "#spaceboundary_area=";	
	

	public Ifc2x3DataModelProxy(StepDataModel model) {
		super(model);
		ifcModel = new IfcDataModelImpl(model);
	}
	
	public List<String> getBuildingsIDs() throws IfcException {
		try {
			return ifcModel.getBuildingIDs();
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	public EIfcrelspaceboundary[] getSpaceBoundariesOfElement(EIfcelement element) throws SdaiException {
		return ifcModel.getSpaceBoundaries(element);
	}

	public EIfcrelspaceboundary[] getSpaceBoundariesBetweenSpaceAndElement(
		EIfcspace space, EIfcelement element) throws IfcException {
		try {
			return ifcModel.getSpaceBoundariesBetweenSpaceAndElement(space, element);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}

	public EIfcsite getIfcSite() throws IfcException {
		EEntity[] sites;
		try {
			sites = ifcModel.getBasicModel().getEntitiesOf(EIfcsite.class);
			if(sites != null && sites.length > 0) return (EIfcsite) sites[0];
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
		return null;
	}
	
	public Set<EIfcsiunit> getIfcSIUnits(EIfcproject project) throws IfcException {
		try {
			return ifcModel.getIfcSIUnits(project);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
		
	}

	public String getGlobalId(EIfcroot root) throws IfcException {
		try {
			return ifcModel.getGlobalId(root);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * Retrieves all the GlobalIds of the given IFC entities. 
	 * @param ifcRoots
	 * @return
	 * @throws IfcException
	 */
	public <T extends EIfcroot> Set<String> getGlobalIds(Collection<T> ifcRoots) throws IfcException {
		Set<String> globalIds = new HashSet<String>();
		for(T ifcRoot : ifcRoots) {
			String guid = getGlobalId(ifcRoot);
			globalIds.add(guid);
		}
		return globalIds;
	}

	public Set<EIfcrelspaceboundary> getSpaceBoundaries() throws IfcException {
		Set<EIfcrelspaceboundary> spaceBoundaries = new HashSet<EIfcrelspaceboundary>();
		EEntity[] entities;
		try {
			entities = ifcModel.getBasicModel().getEntitiesOf(EIfcrelspaceboundary.class);
			for(EEntity e : entities) {
				if(e instanceof EIfcrelspaceboundary) {
					EIfcrelspaceboundary sb = (EIfcrelspaceboundary)e;
					spaceBoundaries.add(sb);
				}
			}
		} catch (SdaiException e1) {
			throw new IfcException(e1);
		}
		return spaceBoundaries;
	}
	
	/**
	 * Retrieves the IfcElementQuantity entities by searching the IfcRelDefinesByProperties of the IfcObject.
	 * It calls {@link #getRelationsToPropertySetDefinitions(EIfcobject)} and {@link #getRelatingPropertyDefinition(EIfcreldefinesbyproperties)}.
	 * @param obj
	 * @return
	 * @throws IfcException
	 */
	public List<EIfcelementquantity> getElementQuantities(EIfcobject obj) throws IfcException {	
		List<EIfcelementquantity> quantitySet = new ArrayList<EIfcelementquantity>(); 
		List<EIfcreldefinesbyproperties> relations = getRelationsToPropertySetDefinitions(obj);
		for(EIfcreldefinesbyproperties relDefine : relations) {
			EIfcpropertydefinition propDef = getRelatingPropertyDefinition(relDefine);
			if(propDef instanceof EIfcelementquantity) {
				EIfcelementquantity quant = (EIfcelementquantity)propDef;
				quantitySet.add(quant);
			}
		}
	   return quantitySet;	
	}
	
	public Set<EIfcobject> getRelatedObjects(EIfcreldefinesbyproperties ifcRelDefinesByProperties) throws IfcException {
		try {
			Set<EIfcobject> ifcObjects = new HashSet<EIfcobject>();
			AIfcobject objectAggregate = ifcRelDefinesByProperties.getRelatedobjects(ifcRelDefinesByProperties);
			IterableAggregate<EIfcobject> iterator = new IterableAggregate<EIfcobject>(objectAggregate);
			for(EIfcobject ifcObject : iterator) {
				ifcObjects.add(ifcObject);
			}
			return ifcObjects;
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	public EIfcpropertydefinition getRelatingPropertyDefinition(EIfcreldefinesbyproperties ifcRelDefinesByProperties) throws IfcException {	 
		try {
			EIfcpropertydefinition propDef = ifcRelDefinesByProperties.getRelatingpropertydefinition(ifcRelDefinesByProperties);
			return propDef;
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * Retrieves the IfcRelDefinesByProperties of the IfcObject.
	 * @param obj
	 * @return
	 * @throws IfcException
	 */
	public List<EIfcreldefinesbyproperties> getRelationsToPropertySetDefinitions(EIfcobject obj) throws IfcException {	
		List<EIfcreldefinesbyproperties> relations = new ArrayList<EIfcreldefinesbyproperties>(); 
		try {
			AIfcreldefines relDefines = obj.getIsdefinedby(obj, null);
			IterableAggregate<EIfcreldefines> iterator = new IterableAggregate<EIfcreldefines>(relDefines);
			for(EIfcreldefines relDefine : iterator) {
				if(relDefine instanceof EIfcreldefinesbyproperties) {
					relations.add((EIfcreldefinesbyproperties)relDefine);
				}
			}
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	   return relations;	
	}
	
	public EIfcphysicalquantity getQuantity(EIfcelementquantity elementQuantity, Quantities quantityName) throws IfcException {
		List<EIfcphysicalquantity> quantities = new ArrayList<EIfcphysicalquantity>(); 
		try {
			AIfcphysicalquantity physQuant = elementQuantity.getQuantities(elementQuantity);
			for(EIfcphysicalquantity pq : new IterableAggregate<EIfcphysicalquantity>(physQuant)) {
				if(quantityName == null || pq.getName(pq).equalsIgnoreCase(quantityName.name())) {
					quantities.add(pq);
				}
			}
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
		if(quantities.size() > 1) throw new RuntimeException("Too many quantities with name: "+quantityName.name());
		if(quantities.size() == 1) return quantities.get(0);
		return null;
	}

	public List<EIfcphysicalquantity> getElementQuantity(EIfcobject object, Quantities quantity) throws IfcException{
		try {
			return ifcModel.getElementQuantity(object, quantity.name());
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}

	public EIfcspace getSpace(String guid) throws IfcException {
		try {
			return (EIfcspace) ifcModel.getIfcEntity(guid);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	public EIfcbuildingstorey getStorey(String guid) throws IfcException {
		try {
			return (EIfcbuildingstorey) ifcModel.getIfcEntity(guid);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	public EIfcspace[] getSpacesOfBuilding(String buildingGuid) throws IfcException {
		try {
			return ifcModel.getSpacesOfBuilding(buildingGuid);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}

	public Set<EIfcelement> getPhysicalSpaceBoundary(EIfcspace space) {
		return ifcModel.getPhysicalSpaceBoundary(space);
	}

	public EIfcwall[] getWallBoundaries(EIfcspace space) {
		return ifcModel.getWallBoundaries(space);
	}

	public Set<EIfcelement> getFillingElement(EIfcbuildingelement wall,
			Class<EIfcwindow> class1) {
		return ifcModel.getFillingElement(wall, class1);
	}
	
	/**
	 * Note: Currently only usable with second level space boundaries created through BSPro.
	 * @param element Given IfcElement.
	 * @return The area of the element.
	 */
	public double getElementArea(EIfcelement element) throws IfcException {
		try {
			double overallArea = 0.0;
			EIfcrelspaceboundary[] spaceBoundaries = getSpaceBoundariesOfElement(element);
			for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
				overallArea += getSpaceBoundaryArea(spaceBoundary);
			}
			return overallArea;
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 *  Note: Currently only usable with second level space boundaries created through BSPro.
	 * @param spaceBoundary
	 * @return
	 * @throws IfcException
	 */
	public double getSpaceBoundaryArea(EIfcrelspaceboundary spaceBoundary) throws IfcException {
		try {
			double area	= 0.0;
			if(spaceBoundary.testDescription(spaceBoundary)) {
				String description = spaceBoundary.getDescription(spaceBoundary);								
				if(description != null) {
					description = description.replace(description.substring(0, description.indexOf(BSPRO_AREA_PATTERN)),"");
					String areaText = description.replaceFirst(BSPRO_AREA_PATTERN, "");
					if(areaText != null) {
						area = Double.valueOf(areaText);
						return area;
					}
				}
			}
			return area;
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * Note: Only usable with BSPro.
	 * @param spaces
	 * @return
	 * @throws IfcException
	 */
	public double calculateBuildingShellArea(List<EIfcspace> spaces) throws IfcException {
		double buildingShellArea = 0.0;
		// Set with GlobalIds to prohibit that one GlobalId is used multiple times
		Set<String> cachedGuids = new HashSet<String>();
		try{
			for(EIfcspace space : spaces) {						
				EIfcrelspaceboundary[] spaceBoundaries = getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
				for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
					if(cachedGuids.add(spaceBoundary.getGlobalid(spaceBoundary))) {
						//	non-energy-related costs for external elements
						int internalOrExternal = spaceBoundary.getInternalorexternalboundary(spaceBoundary);
						if(internalOrExternal == EIfcinternalorexternalenum.EXTERNAL) { // facade element: window or other concrete element
							EIfcelement element = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
							if(element instanceof EIfcbuildingelement) {
								EIfcbuildingelement be = (EIfcbuildingelement) element;
	
								if(!(be instanceof EIfcwindow)) {
									double area	= 0.0;
									if(spaceBoundary.testDescription(spaceBoundary)) {
										String description = spaceBoundary.getDescription(spaceBoundary);								
										if(description != null) {
											description = description.replace(description.substring(0, description.indexOf(BSPRO_AREA_PATTERN)),"");
											String areaText = description.replaceFirst(BSPRO_AREA_PATTERN, "");
											if(areaText != null) {
												area = Double.valueOf(areaText);
											}
										}
									}
									buildingShellArea += area;
								}
							}
						}
					}
				}
			}
//			round building shell area
			buildingShellArea = Math.round(buildingShellArea*100)/100;
		} catch(SdaiException se) {
			throw new IfcException(se);
		}
		return buildingShellArea;
	}
	
	/**
	 * Note: Only usable with BSPro.
	 * @param spaces
	 * @return
	 * @throws IfcException
	 */
	public double calculateWindowArea(List<EIfcspace> spaces) throws IfcException {
		double windowArea = 0.0;
		// Set with GlobalIds to prohibit that one GlobalId is used multiple times
		Set<String> cachedGuids = new HashSet<String>();
		try {
			for(EIfcspace space : spaces) {						
				EIfcrelspaceboundary[] spaceBoundaries = getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
				for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
					if(cachedGuids.add(spaceBoundary.getGlobalid(spaceBoundary))) {
						//	non-energy-related costs for external elements
						int internalOrExternal = spaceBoundary.getInternalorexternalboundary(spaceBoundary);
						if(internalOrExternal == EIfcinternalorexternalenum.EXTERNAL) { // facade element: window or other concrete element
							EIfcelement element = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
							if(element instanceof EIfcwindow) {
								double area	= 0.0;
								if(spaceBoundary.testDescription(spaceBoundary)) {
									String description = spaceBoundary.getDescription(spaceBoundary);								
									if(description != null) {
										description = description.replace(description.substring(0, description.indexOf(BSPRO_AREA_PATTERN)),"");
										String areaText = description.replaceFirst(BSPRO_AREA_PATTERN, "");
										if(areaText != null) {
											area = Double.valueOf(areaText);
										}
									}
								}
								windowArea += area;
							}
						}
					}
				}
			}
			// round window area
			windowArea = Math.round(windowArea*100)/100;
		} catch (SdaiException se) {
			throw new IfcException(se);
		}
		return windowArea;
	}
	
	public double calculatesSpaceVolumes(Collection<EIfcspace> spaces) throws IfcException {
		double volume = 0.0;
		for(EIfcspace space : spaces) {
			volume += getSpaceVolume(space);
		}
		return volume;
	}
	
	public double getSpaceFinishCeilingHigh(EIfcspace space) throws IfcException {
		List<EIfcelementquantity> quantities = getElementQuantities(space);
		for(EIfcelementquantity quantity : quantities) {
			EIfcphysicalquantity grossLengthQuantity = getQuantity(quantity, Quantities.FinishCeilingHigh);
			if(!(grossLengthQuantity instanceof EIfcquantitylength)) throw new IllegalArgumentException("Quantity: "+grossLengthQuantity+" is no IfcQuantityVolume entity");
			EIfcquantitylength finishCeilingHigh = (EIfcquantitylength) grossLengthQuantity;
			try {
				return finishCeilingHigh.getLengthvalue(finishCeilingHigh);
			} catch (SdaiException e) {
				throw new IfcException(e);
			}
		}
		return 0.0;
	}
	
	public double getSpaceVolume(EIfcspace space) throws IfcException {
		List<EIfcelementquantity> quantities = getElementQuantities(space);
		for(EIfcelementquantity quantity : quantities) {
			EIfcphysicalquantity grossVolumeQuantity = getQuantity(quantity, Quantities.GrossVolume);
			if(!(grossVolumeQuantity instanceof EIfcquantityvolume)) throw new IllegalArgumentException("Quantity: "+grossVolumeQuantity+" is no IfcQuantityVolume entity");
			EIfcquantityvolume volume = (EIfcquantityvolume) grossVolumeQuantity;
			try {
				return volume.getVolumevalue(volume);
			} catch (SdaiException e) {
				throw new IfcException(e);
			}
		}
		return 0.0;
	}

	public Set<EIfcmaterial> getAssociatedMaterial(EIfcbuildingelement element) {
		return ifcModel.getAssociatedMaterial(element);
	}

	public Set<EIfcmateriallayer> getAssociatedMaterialLayers(
			EIfcbuildingelement element) {
//		List<EIfcmateriallayer> assignedMaterialLayer = new ArrayList<EIfcmateriallayer>();
//		
//		try {
//			EEntity[] relA = ifcModel.getBasicModel().getEntitiesOf(EIfcrelassociatesmaterial.class);
//			for(EEntity relAentity : relA) {
//				try {
//					AIfcroot relatedObjects = (AIfcroot) ifcModel.getBasicModel().getValueOfAttribute(relAentity, "relatedobjects", false);
//					if(relatedObjects.isMember(element) || element == null) {
//						
//						EEntity matSelect = ((EIfcrelassociatesmaterial) relAentity).getRelatingmaterial((EIfcrelassociatesmaterial) relAentity);
//						
//						if(matSelect instanceof EIfcmaterial) { continue; }
//						
//						if(matSelect instanceof EIfcmateriallist) {	continue; }
//						
//						if(matSelect instanceof EIfcmateriallayersetusage) {
//							EIfcmateriallayersetusage layerSetUsage = (EIfcmateriallayersetusage) matSelect;
//							EIfcmateriallayerset layerSet = layerSetUsage.getForlayerset(layerSetUsage);
//							AIfcmateriallayer layer = layerSet.getMateriallayers(layerSet); 
//							SdaiIterator it = layer.createIterator();
//							while(it.next()) { 
//								EIfcmateriallayer l = (EIfcmateriallayer) layer.getCurrentMemberEntity(it); 
//								assignedMaterialLayer.add(l); 
//							}
//							continue;
//						}
//						
//						if(matSelect instanceof EIfcmateriallayerset) {
//							EIfcmateriallayerset layerSet = (EIfcmateriallayerset) matSelect;
//							AIfcmateriallayer layer = layerSet.getMateriallayers(layerSet); 
//							SdaiIterator it = layer.createIterator();
//							while(it.next()) { 
//								EIfcmateriallayer l = (EIfcmateriallayer) layer.getCurrentMemberEntity(it);
//								assignedMaterialLayer.add(l); 
//							}
//							continue;
//						}
//						
//						if(matSelect instanceof EIfcmateriallayer) {
//							EIfcmateriallayer layer = (EIfcmateriallayer)matSelect;
//							assignedMaterialLayer.add(layer);
//							continue;
//						}
//						
//						
//						
//					}
//				}
//				catch(SdaiException sdai) { 
//					continue; 
//				}
//				
//			}
//		} catch(SdaiException e) { }		
//		
//		return assignedMaterialLayer;
		return ifcModel.getAssociatedMaterialLayers(element);
	}

	public EIfcspace[] getSpacesInStorey(EIfcbuildingstorey storey) throws IfcException {
		try {
			return ifcModel.getSpacesInStorey(storey);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	public EIfcbuilding getBuildingOfSpace(EIfcspace space) throws IfcException {
		try {
			Map<String, EIfcbuilding> buildingMap = ifcModel.getBuildings();
			for(Map.Entry<String, EIfcbuilding> entry : buildingMap.entrySet()) {
				EIfcspace[] spacesInBuilding = ifcModel.getSpacesOfBuilding(entry.getKey());
				for(EIfcspace spaceInBuilding : spacesInBuilding) {
					if(space == spaceInBuilding) {
						return entry.getValue();
					}
				}
			}
		} catch(SdaiException se) {
			throw new IfcException(se);
		}
		return null;
	}
	
	public EIfcbuilding getBuildingOfStorey(EIfcbuildingstorey storey) throws IfcException {
		try {
			Map<String, EIfcbuilding> buildingMap = ifcModel.getBuildings();
			for(Map.Entry<String, EIfcbuilding> entry : buildingMap.entrySet()) {
				EIfcbuildingstorey[] storeysInBuilding = ifcModel.getBuildingStoreys(entry.getValue());
				for(EIfcbuildingstorey storeyInBuilding : storeysInBuilding) {
					if(storey == storeyInBuilding) {
						return entry.getValue();
					}
				}
			}
		} catch(SdaiException se) {
			throw new IfcException(se);
		}
		return null;
	}
	
	public Set<EIfcbuildingstorey> getBuildingStoreysOfSpace(EIfcspace space) throws IfcException {
		try {
			Set<EIfcbuildingstorey> buildingStoreySet = new HashSet<EIfcbuildingstorey>();
			EIfcbuildingstorey[] storeys = ifcModel.getBuildingStoreys();
			for(EIfcbuildingstorey storey : storeys) {
				EIfcspace[] spacesInStorey = ifcModel.getSpacesInStorey(storey);
				for(EIfcspace spaceInStorey : spacesInStorey) {
					if(space == spaceInStorey) {
						buildingStoreySet.add(storey);
						break;
					}
				}
			}
			return buildingStoreySet;
		} catch(SdaiException se) {
			throw new IfcException(se);
		}
	}

	public EIfcbuildingstorey[] getBuildingStoreys(EIfcbuilding building) throws IfcException {
		try {
			return ifcModel.getBuildingStoreys(building);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	public EIfcbuildingstorey[] getBuildingStoreySort(EIfcbuilding building) throws IfcException {
		try {
			return ifcModel.getBuildingStoreySort(building);
		} catch (SdaiException e) {
			throw new IfcException(e);
		} catch (StoreySortException e) {			
			throw new IfcException(e);
		}
	}

	public Set<EIfcelement> getWindows(EIfcbuildingelement element) {
		return ifcModel.getFillingElement(element, EIfcwindow.class);
	}

	public StepDataModel getBasicModel() {
		return ifcModel.getBasicModel();
	}
	
	public List<EIfcspace> getSpaces() throws IfcException {
		try {
			return Arrays.asList(ifcModel.getSpaces());
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}

	public EIfcspace[] getSpaces(String buildingGuid) throws IfcException {
		try {
			return ifcModel.getSpacesOfBuilding(buildingGuid);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * Retrieves the space boundaries in a given spatial structure (building, storey or room). 
	 * It uses the {@link #getSpacesOfSpatialStructure(String)} to search the spaces in the spatial structure and than retrieve the space boundaries of each space.
	 * @param spatialStructureGlobalId The GlobalId of the IfcSpace, IfcBuildingStorey or IfcBuilding.
	 * @param physicalOrVirtual Specify which space boundaries are needed.
	 * @return A set with unique entries of space boundaries.
	 * @throws IfcException If spaces can't be found.
	 */
	public Set<EIfcrelspaceboundary> getSpaceBoundariesInSpatialStructure(String spatialStructureGlobalId, int physicalOrVirtual) throws IfcException {
		Set<EIfcrelspaceboundary> spaceBoundaries = new HashSet<EIfcrelspaceboundary>();
		Set<String> spaceGuidsOfSpatialStructure = getSpacesOfSpatialStructure(spatialStructureGlobalId);
		
		for(String spaceGuid : spaceGuidsOfSpatialStructure) {
			EIfcspace space = getSpace(spaceGuid);
			if(space == null) continue;
			EIfcrelspaceboundary[] sbArray = getSpaceBoundaries(space, physicalOrVirtual);
			if(sbArray != null) spaceBoundaries.addAll(Arrays.asList(sbArray));
		}		
		return spaceBoundaries;
	}
	
	/**
	 * Iterates over the given IFC GlobalIds and looks if it is a building or a storey and searches related rooms.
	 * @param guids
	 * @param ifcModel
	 * @return
	 * @throws IfcException
	 */
	public Set<String> getSpacesOfSpatialStructure(String guid) throws IfcException {
		Set<String> newGuidSet = new HashSet<String>();
		EIfcspatialstructureelement spatialStructureElement = getIfcEntity(guid, EIfcspatialstructureelement.class);
		if(spatialStructureElement instanceof EIfcspace) { // do nothing => already a zone
			newGuidSet.add(guid);
		} else if(spatialStructureElement instanceof EIfcbuildingstorey) { // search the zones in the storey
			EIfcbuildingstorey storey = (EIfcbuildingstorey) spatialStructureElement;
			EIfcspace[] spacesInStorey = getSpacesInStorey(storey);
			for(EIfcspace space : spacesInStorey) {
				String spaceId = getGlobalId(space);
				newGuidSet.add(spaceId);
			}
		} else if(spatialStructureElement instanceof EIfcbuilding) { // search the zones in the building
			EIfcbuilding building = (EIfcbuilding) spatialStructureElement;
			EIfcspace[] spacesInBuilding = getSpacesOfBuilding(getGlobalId(building));
			for(EIfcspace space : spacesInBuilding) {
				String spaceId = getGlobalId(space);
				newGuidSet.add(spaceId);
			}
		}

		return newGuidSet;
	}

	public <T extends EIfcphysicalquantity> Set<T> getElementQuantity(EIfcspace space, Class<T> class1) throws IfcException {
		try {
			return ifcModel.getElementQuantity(space, class1);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * @param type <code>null</code> is not permitted.
	 * @return
	 * @throws IfcException
	 */
	public <T extends EIfcroot> T[] getIfcEntitiesOf(Class<T> type) throws IfcException {
		try {
			return ifcModel.getBasicModel().getEntitiesOf(type);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * Retrieves all IfcRoot entities (and the instances of their sub classes).
	 * @return
	 * @throws IfcException
	 */
	public List<EIfcroot> getIfcEntities() throws IfcException {
		try {
			EIfcroot[] ifcRoots = ifcModel.getBasicModel().getEntitiesOf(EIfcroot.class);
			if(ifcRoots == null) throw new IllegalArgumentException("No IfcRoot entities");
			return Arrays.asList(ifcRoots);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends EIfcroot> T getIfcEntity(String guidElement, Class<T> class1) throws IfcException {
		try {
			if(class1 == null) return (T) ifcModel.getIfcEntity(guidElement);
			
			return (T) ifcModel.getIfcEntity(guidElement, class1);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	public EIfcproject getIfcProject() throws IfcException {
		try {
			return ifcModel.getIfcProject();
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * <p>Retrieve internal or external space boundaries by iterating over all space boundaries of the given IfcSpace.</p>
	 * @param space
	 * @param internalOrExternal
	 * @return
	 * @throws IfcException
	 */
	public Set<EIfcrelspaceboundary> getInternalOrExternalSpaceBoundaries(EIfcspace space, int internalOrExternal) throws IfcException {
		Set<EIfcrelspaceboundary> spaceBoundaries = new HashSet<EIfcrelspaceboundary>();
		EIfcrelspaceboundary[] sb1 = getSpaceBoundaries(space, EIfcphysicalorvirtualenum.NOTDEFINED);
		spaceBoundaries.addAll(Arrays.asList(sb1));
		EIfcrelspaceboundary[] sb2 = getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
		spaceBoundaries.addAll(Arrays.asList(sb2));
		EIfcrelspaceboundary[] sb3 = getSpaceBoundaries(space, EIfcphysicalorvirtualenum.VIRTUAL);
		spaceBoundaries.addAll(Arrays.asList(sb3));
		
		try {
			Iterator<EIfcrelspaceboundary> iter = spaceBoundaries.iterator();
			while( iter.hasNext()) {
				EIfcrelspaceboundary sb = iter.next();
				if(sb.getInternalorexternalboundary(sb) != internalOrExternal) iter.remove(); 
			}
			return spaceBoundaries;
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * <p>Retrieve internal or external space boundaries by iterating over all space boundaries of the given IfcElement.</p>
	 * @param space
	 * @param internalOrExternal
	 * @return
	 * @throws IfcException
	 */
	public Set<EIfcrelspaceboundary> getInternalOrExternalSpaceBoundaries(EIfcelement element, int internalOrExternal) throws IfcException {
		try {
			Set<EIfcrelspaceboundary> spaceBoundaries = new HashSet<EIfcrelspaceboundary>();
			EIfcrelspaceboundary[] sb1 = getSpaceBoundariesOfElement(element); 
			spaceBoundaries.addAll(Arrays.asList(sb1));		
		
			Iterator<EIfcrelspaceboundary> iter = spaceBoundaries.iterator();
			while( iter.hasNext()) {
				EIfcrelspaceboundary sb = iter.next();
				if(sb.getInternalorexternalboundary(sb) != internalOrExternal) iter.remove(); 
			}
			return spaceBoundaries;
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
	}

	public EIfcrelspaceboundary[] getSpaceBoundaries(EIfcspace space, int physical) {
		return ifcModel.getSpaceBoundaries(space, physical);
	}
	
	public IfcDataModel getOrigin() {
		return ifcModel;
	}

	public Map<String,EIfcbuilding> getBuildings() throws IfcException {
		try {
			return ifcModel.getBuildings();
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	public Set<EIfcelement> getElementsInBuilding(String buildingGuid) throws IfcException {
		Set<EIfcelement> buildingElements = new HashSet<EIfcelement>();
		EIfcbuilding building = getIfcEntity(buildingGuid, EIfcbuilding.class);
		EIfcbuildingstorey[] storeys = getBuildingStoreys(building);
		for(EIfcbuildingstorey storey : storeys) {
			buildingElements.addAll(getElementsInStorey(storey));
		}
		return buildingElements;
	}
	
	public Set<EIfcelement> getElementsInStorey(EIfcbuildingstorey storey) throws IfcException {
		Set<EIfcelement> elementsInStorey = new HashSet<EIfcelement>();
		EIfcspace[] spaces = getSpacesInStorey(storey);
		for(EIfcspace space : spaces) {
			Set<EIfcelement> elements = getBoundingElementsOfSpace(space);
			elementsInStorey.addAll(elements);
		}
		return elementsInStorey;
	}
	
	public Set<EIfcelement> getElementsByType(String buildingGuid, String type) throws IfcException {
		try {
			Set<EIfcelement> resultSet = new HashSet<EIfcelement>();
			EIfcreldefinesbytype[] relDefinesByTypes = ifcModel.getBasicModel().getEntitiesOf(EIfcreldefinesbytype.class);

			for(EIfcreldefinesbytype relDefinesByType : relDefinesByTypes) {
				EIfctypeobject relatingType = relDefinesByType.getRelatingtype(relDefinesByType);
				if(relatingType instanceof EIfcelementtype) {
					EIfcelementtype eType = (EIfcelementtype) relatingType;
					if(eType.testElementtype(eType)) {
						String label = eType.getElementtype(eType);
						if(label.equalsIgnoreCase(type)) {

							AIfcobject objects = relDefinesByType.getRelatedobjects(relDefinesByType);
							IterableAggregate<EIfcobject> iterable = new IterableAggregate<EIfcobject>(objects);
							for(EIfcobject object : iterable) {
								if(object instanceof EIfcelement) {
									resultSet.add((EIfcelement)object);
								}
							}
						}
					}
				}
			}					
			return resultSet;
		} catch (SdaiException e) {
			throw new IfcException(e);
		}		
	}
	
	/**
	 * Search the building and virtual elements and make a decision by iterating over building -> storeys -> rooms
	 * This method uses the space boundaries.
	 * @param guid
	 * @return
	 * @throws IfcException
	 * @throws SdaiException
	 */
	public Set<EIfcelement> getBuildingElementsInZone(String guid) throws IfcException, SdaiException {
		Set<EIfcelement> buildingElements = new HashSet<EIfcelement>();
		Map<String, EIfcbuilding> buildings = getBuildings();
		for (Map.Entry<String, EIfcbuilding> entry : buildings.entrySet()) {
			String key = entry.getKey();
			EIfcbuilding value = entry.getValue();
			if(key.equals(guid)) {
				return getElementsInBuilding(value.getGlobalid(value));
			}
			EIfcbuildingstorey[] storeys = getBuildingStoreys(value);
			for(EIfcbuildingstorey storey : storeys) {
				if(getGlobalId(storey).equals(guid)) {
					return getElementsInStorey(storey);
				}
				EIfcspace[] spaces = getSpacesInStorey(storey);
				for(EIfcspace space : spaces) {
					if(getGlobalId(space).equals(guid)) {
						Set<EIfcelement> elements = getBoundingElementsOfSpace(space);
						for(EIfcelement element : elements) {
							if(element instanceof EIfcelement) {
								buildingElements.add((EIfcelement)element);
							}
						}
					}
				}
			}	
		}
		return buildingElements;
	}
	
	public List<EIfcrelfillselement> getRelFillsElement(EIfcelement element) throws IfcException {
		if(element == null) return null;
		List<EIfcrelfillselement> resultList = null;
		try {
			String guid = element.getGlobalid(element);
			EIfcrelfillselement[] relFillselements = getIfcEntitiesOf(EIfcrelfillselement.class);
			for(EIfcrelfillselement relFillsElement : relFillselements) {
				EIfcelement compareElement = relFillsElement.getRelatedbuildingelement(relFillsElement);
				String compareGuid = compareElement.getGlobalid(compareElement);
				if(compareGuid.equals(guid)) {
					if(resultList == null) resultList = new ArrayList<EIfcrelfillselement>();
					resultList.add(relFillsElement);
				}
			}
			return resultList;
		} catch (SdaiException e) {
			throw new IfcException(e);
		}	
	}

	public EIfcsite getSite() throws IfcException {
		EIfcsite[] ifcSite;
		try {
			ifcSite = ifcModel.getBasicModel().getEntitiesOf(EIfcsite.class);
			if(ifcSite != null && ifcSite.length > 0) {
				return ifcSite[0];
			}
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
		return null;
	}
	
	/**
	 * Method for finding the bounding elements of a space by using the IfcRelSpaceBoundary (physical and virtual elements)
	 * @param space Space of interest.
	 * @return Set of building and virtual elements.
	 * @throws SdaiException
	 */
	public Set<EIfcelement> getBoundingElementsOfSpace(EIfcspace space) throws IfcException {
		Set<EIfcelement> elements = new HashSet<EIfcelement>();
		Collection<EIfcrelspaceboundary> spaceBoundaries = new HashSet<>();
		EIfcrelspaceboundary[] physicalSpaceBoundaries = ifcModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
		EIfcrelspaceboundary[] virtualSpaceBoundaries = ifcModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.VIRTUAL);
		spaceBoundaries.addAll(Arrays.asList(physicalSpaceBoundaries));
		spaceBoundaries.addAll(Arrays.asList(virtualSpaceBoundaries));
		for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
			EIfcelement element;
			try {
				if(spaceBoundary.testRelatedbuildingelement(spaceBoundary)) {
					element = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
					elements.add(element);
				} else {
					System.out.println("SpaceBoundary: "+spaceBoundary+" has no related building or virtual element");
				}
			} catch (SdaiException e) {
				throw new IfcException(e);
			}
		}
		return elements;
	}
	
	public Set<EIfcwall> getInnerWallsOfBuilding(String buildingGuid) throws IfcException {
		Set<EIfcwall> walls = new HashSet<EIfcwall>();
		
		try {
			EIfcspace[] allSpacesInBuilding = getSpaces(buildingGuid);
			for(EIfcspace space : allSpacesInBuilding) {
				EIfcrelspaceboundary[] secondLevelSpaceBoundaries = ifcModel.get2ndLevelSpaceBoundaries(
						space, EIfcphysicalorvirtualenum.PHYSICAL, EIfcinternalorexternalenum.INTERNAL);
				for(EIfcrelspaceboundary spaceBoundary : secondLevelSpaceBoundaries) {
					EIfcelement relatedIfcElement = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
					if(relatedIfcElement instanceof EIfcwall) { // only walls permitted
						walls.add((EIfcwall) relatedIfcElement);
					}			
				}
			}
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
		return walls;
	}
	
	public Set<EIfcwall> getInnerWallsOfStorey(String buildingStoreyGuid) throws IfcException {
		Set<EIfcwall> walls = new HashSet<EIfcwall>();
		
		try{
			EIfcbuildingstorey storey = getStorey(buildingStoreyGuid);
			EIfcspace[] allSpacesInStorey = ifcModel.getSpacesInStorey(storey);
			for(EIfcspace space : allSpacesInStorey) {
				EIfcrelspaceboundary[] internalSecondLevelSpaceBoundaries = ifcModel.get2ndLevelSpaceBoundaries(
						space, EIfcphysicalorvirtualenum.PHYSICAL, EIfcinternalorexternalenum.INTERNAL);
				for(EIfcrelspaceboundary spaceBoundary : internalSecondLevelSpaceBoundaries) {
					EIfcelement relatedIfcElement = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
					if(relatedIfcElement instanceof EIfcwall) { // only walls permitted
						walls.add((EIfcwall) relatedIfcElement);
					}			
				}
			}	
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
		return walls;
	}
	
	/**
	 * <b>Note: Currently only usable with second level space boundaries.</b>
	 * <p>
	 * 	Search all outer building elements.
	 * </p>
	 * @param buildingGuid
	 * @return A set with all outer building elements.
	 * @throws IfcException
	 */
	public Set<EIfcbuildingelement> getOuterElements(String buildingGuid) throws IfcException {
		Set<EIfcbuildingelement> elements = new HashSet<EIfcbuildingelement>();
		try{
			EIfcspace[] allSpacesInBuilding = getSpaces(buildingGuid);
			for(EIfcspace space : allSpacesInBuilding) {
				EIfcrelspaceboundary[] secondLevelSpaceBoundaries = ifcModel.get2ndLevelSpaceBoundaries(
						space, EIfcphysicalorvirtualenum.PHYSICAL, EIfcinternalorexternalenum.EXTERNAL);
				for(EIfcrelspaceboundary spaceBoundary : secondLevelSpaceBoundaries) {
					int internalOrExternal = spaceBoundary.getInternalorexternalboundary(spaceBoundary);
					if(EIfcinternalorexternalenum.EXTERNAL == internalOrExternal) {
						EIfcelement relatedElement = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
						if(relatedElement instanceof EIfcbuildingelement) {
							elements.add((EIfcbuildingelement)relatedElement);
						}
					}
				}
			}
			return elements;
		} catch(SdaiException e) {
			throw new IfcException(e);
		}		
	}
	
	
	public List<EIfcelement> getOuterElementsEx(String buildingGuid) throws IfcException, SdaiException {
		List<EIfcelement> elements = new ArrayList<EIfcelement>();
		for(int direction = 0; direction <= 7; direction++) {
			List<EIfcelement> list = getOuterElementsOfDirection(buildingGuid,direction);
			elements.addAll(list);
		}
		return elements;
	}
	
	
	/**
	 * <b>Note: Currently only usable with second level space boundaries created through BSPro.</b>
	 * <p>
	 * 	Search the building and virtual elements in a specific direction.
	 * </p>
	 * @param buildingGuid
	 * @param direction
	 * <ul>
	 * 	<li>0: North</li>
	 * 	<li>1: North East</li>
	 * 	<li>2: East</li>
	 * 	<li>3: South East</li>
	 * 	<li>4: South</li>
	 * 	<li>5: South West</li>
	 * 	<li>6: West</li>
	 * 	<li>7: North West</li>
	 * </ul>
	 * @return A list with building elements in the given direction.
	 * @throws IfcException
	 * @throws SdaiException
	 */
	public List<EIfcelement> getElementsOfDirection(String buildingGuid, int direction) throws IfcException, SdaiException {
		List<EIfcelement> list = new ArrayList<EIfcelement>();
		Set<EIfcelement> elementSet = getElementsInBuilding(buildingGuid);
		if(elementSet != null) {
			for(EIfcelement element : elementSet) {				
				if(isDirectedTo(element, direction)) {
					list.add(element);
				}
			}
		}
		return list;
	}
	
	/**
	 * <b>Note: Currently only usable with second level space boundaries created through BSPro.</b>
	 * <p>
	 * </p>
	 * @param element
	 * @param direction
	 * 	<ul>
	 * 	<li>0: North</li>
	 * 	<li>1: North East</li>
	 * 	<li>2: East</li>
	 * 	<li>3: South East</li>
	 * 	<li>4: South</li>
	 * 	<li>5: South West</li>
	 * 	<li>6: West</li>
	 * 	<li>7: North West</li>
	 * </ul>
	 * @return
	 * @throws IfcException
	 * @throws SdaiException
	 */
	public boolean isDirectedTo(EIfcelement element, int direction) throws IfcException, SdaiException {	
		List<EIfcphysicalquantity> azimuthQuantities = getElementQuantity(element, Quantities.azimuth);
		if(azimuthQuantities == null) return false;
		if(azimuthQuantities.size() == 0) return false;
		EIfcquantitylength quantitiyLength = (EIfcquantitylength) azimuthQuantities.get(0);
		if(quantitiyLength == null) return false;
//		it is circular measure
		double azimuthCircularMeasureValue = quantitiyLength.getLengthvalue(quantitiyLength);	
//		transform it to degree measure							
		double azimuthDegreeMeasure = azimuthCircularMeasureValue * (180/Math.PI);
		
		Directions di = Directions.toDirection(direction);
			
		switch(di) {
		case NORTH: // North direction
			if(azimuthDegreeMeasure >= 0.0 && azimuthDegreeMeasure < 45.00) {		
				return true;
			}
			break;
		case NORTH_EAST: // North East direction
			if(azimuthDegreeMeasure >= 45.0 && azimuthDegreeMeasure < 90.00) {
				return true;
			}
			break;
		case EAST: // East direction
			if(azimuthDegreeMeasure >= 90.0 && azimuthDegreeMeasure < 135.00) {
				return true;
			}
			break;
		case SOUTH_EAST: // South East direction
			if(azimuthDegreeMeasure >= 135.0 && azimuthDegreeMeasure < 180.00) {
				return true;
			}
			break;
		case SOUTH: // South direction
			if(azimuthDegreeMeasure >= 180.0 && azimuthDegreeMeasure < 225.00) {
				return true;
			}
			break;
		case SOUTH_WEST: // South West direction
			if(azimuthDegreeMeasure >= 225.0 && azimuthDegreeMeasure < 270.00) {
				return true;
			}
			break;
		case WEST: // West direction
			if(azimuthDegreeMeasure >= 270.0 && azimuthDegreeMeasure < 315.00) {
				return true;
			}
			break;
		case NORTH_WEST: // North West direction
			if(azimuthDegreeMeasure >= 315.0 && azimuthDegreeMeasure <= 360.00) {
				return true;
			}
			break;			
		default: 
			return false;				
		}
		return false;
	}
	
	/**
	 * <b>Note: Currently only usable with second level space boundaries created through BSPro.</b>
	 * <p>
	 * 	Search the outer building and virtual elements in a specific direction.
	 * </p>
	 * @param buildingGuid
	 * @param direction
	 * <ul>
	 * 	<li>0: North</li>
	 * 	<li>1: North East</li>
	 * 	<li>2: East</li>
	 * 	<li>3: South East</li>
	 * 	<li>4: South</li>
	 * 	<li>5: South West</li>
	 * 	<li>6: West</li>
	 * 	<li>7: North West</li>
	 * </ul>
	 * @return A list with building elements in the given direction.
	 * @throws IfcException
	 * @throws SdaiException
	 */
	public List<EIfcelement> getOuterElementsOfDirection(String buildingGuid, int direction) throws IfcException, SdaiException {
		List<EIfcelement> list = new ArrayList<EIfcelement>();
		
		for(EIfcelement element : getElementsOfDirection(buildingGuid, direction)) {
			if(isOutdoorElement(element)) {
				list.add(element);
			}
		}
		return list;
	}
	
	/**
	 * <p>Checks if the element is a facade element. Currently IfcSlab and IfcRoof is not permitted</p>
	 * 
	 * @param buildingElement
	 * @return True if the building element is a facade element.
	 * @throws IfcException
	 */
	public boolean isFacadeElement(EIfcelement element) throws IfcException {
		if(!(element instanceof EIfcslab) 
				&& !(element instanceof EIfcroof)
				&& !(element instanceof EIfcvirtualelement)
				&& isOutdoorElement(element)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the element is an external element (specified through second level space boundaries).
	 * @param element
	 * @return
	 * @throws IfcException
	 */
	public boolean isOutdoorElement(EIfcelement element) throws IfcException {
		try {
			EIfcrelspaceboundary[] spaceBoundaries = ifcModel.getSpaceBoundaries(element);
			for(EIfcrelspaceboundary sb : spaceBoundaries) {
				if(sb.testInternalorexternalboundary(sb)){
					int internalOrExternal = sb.getInternalorexternalboundary(sb);
					if(EIfcinternalorexternalenum.EXTERNAL == internalOrExternal) {
						return true;
					}
				}
			}
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
		return false;
	}
	
	/**
	 * <p><b>EXPERIMENTAL METHOD!</b></p>
	 * <b>Note: Currently only usable with second level space boundaries created through BSPro.</b>
	 * <p>
	 * It search the space boundaries connected to a given IfcSpace, retrieves all connected IfcElements and iterate over their
	 * IfcRelSpaceBoundaries and compares the area. The assumption is that space boundaries with the same area are connected to the same element.
	 * If this is the case their relating space is taken which could be adjacent with each other.
	 *
	 * </p>
	 * @param spaceGuid Given IfcSpace GlobalID
	 * @param notAllowedElementTypes Set of IFC classes which are not allowed by retrieving adjacent spaces. 
	 * 		For example, if IfcSlab is given as not allowed type the spaces under and above a given space are not permitted.
	 * 		In another case someone don't want to have neighbouring spaces so IfcWallStandardCase can be applied.
	 * @param optionalNotPermittedAdjacentSpaceLongName Not allowed LongName value of possible adjacent spaces => will not be analysed.
	 * @return List of adjacent spaces.
	 * @throws IfcException 
	 */
	public Set<EIfcspace> getAdjacentSpaces(String spaceGuid, String optionalNotPermittedAdjacentSpaceLongName, Set<Class<? extends EIfcelement>> notAllowedElementTypes) throws IfcException {
		Set<EIfcspace> adjacentSpaces = new HashSet<EIfcspace>();
		try {
			EIfcspace space = getIfcEntity(spaceGuid, EIfcspace.class);
			if(space == null) throw new IllegalArgumentException("GlobalId: "+spaceGuid+" is no IfcSpace");
			
			/*  
			 *  xx		<- given space
			 *   		
			 */
			// we only take internal space boundaries
			Set<EIfcrelspaceboundary> spaceBoundaries = getInternalOrExternalSpaceBoundaries(space, EIfcinternalorexternalenum.INTERNAL);		
			
			if(spaceBoundaries.size() > 0) {
				/*  -- 
				 * |xx|		<- search related elements
				 *  -- 		
				 */
				Set<EIfcelement> relatedElements = new HashSet<EIfcelement>();
				for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
					relatedElements.add(spaceBoundary.getRelatedbuildingelement(spaceBoundary));
				}
				elementLoop : for(EIfcelement element : relatedElements) { 
					// accept only specific types
					if(notAllowedElementTypes != null) {
						for(Class<? extends EIfcelement> type : notAllowedElementTypes) {
							if(element.isKindOf(type)) continue elementLoop;
						}
						
					}
					if(element instanceof EIfcslab) continue;
					Map<Double, Set<EIfcrelspaceboundary>> areas = new HashMap<Double, Set<EIfcrelspaceboundary>>();
					/*   == 
					 * ||xx||		<- collect all space boundaries of the related elements (also the ones before the for-loop)
					 *   == 		
					 */
					Set<EIfcrelspaceboundary> spaceBoundariesOfRelatedElements = getInternalOrExternalSpaceBoundaries(element, EIfcinternalorexternalenum.INTERNAL);
					
					// if less than 2 space boundaries are retrieved than no comparison is possible
					if(spaceBoundariesOfRelatedElements.size() < 2) continue;					
					
					for(EIfcrelspaceboundary sb : spaceBoundariesOfRelatedElements) {
						EIfcspace s = sb.getRelatingspace(sb);
						// don't take the space boundaries of the given space for comparison
						if(s.getGlobalid(s).equalsIgnoreCase(spaceGuid)) continue;
						if(s.testLongname(s) && optionalNotPermittedAdjacentSpaceLongName != null) {
							String longName = s.getLongname(s).trim().toLowerCase();
							if(longName.contains(optionalNotPermittedAdjacentSpaceLongName.toLowerCase())) continue;
						}
						
						double sbArea = getSpaceBoundaryArea(sb);
						if(areas.containsKey(sbArea)) {
							Set<EIfcrelspaceboundary> areaToSbs = areas.get(sbArea);
							areaToSbs.add(sb);
						} else {
							Set<EIfcrelspaceboundary> sbList = new HashSet<EIfcrelspaceboundary>();
							sbList.add(sb);
							areas.put(sbArea, sbList);
						}
					}
					/*   ==  ==
					 * ||xx||oo|		
					 *   ==  
					 * ||oo|		
					 *   == 
					 */
					// search the space boundaries which have the same area because they are adjacent candidates		
					for(Map.Entry<Double, Set<EIfcrelspaceboundary>> entry : areas.entrySet()) {
						Set<EIfcrelspaceboundary> values = entry.getValue();
		
						if(values.size() == 2) {
							Iterator<EIfcrelspaceboundary> sbIterator = values.iterator();
							EIfcrelspaceboundary firstBoundary = sbIterator.next();
							EIfcrelspaceboundary secondBoundary = sbIterator.next();
							adjacentSpaces.add(firstBoundary.getRelatingspace(firstBoundary));
							adjacentSpaces.add(secondBoundary.getRelatingspace(secondBoundary));
						}
						else {
							// if more than 2 space boundaries have the same area we don't know exactly where they are located 
						}
					}
				}
				

			}
			return adjacentSpaces;
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * <b>Note: Currently only usable with second level space boundaries created through BSPro.</b>
	 * <p>
	 * It search the internal space boundaries connected to a given IfcElement, retrieves all connected IfcSpaces and iterate over their
	 * IfcRelSpaceBoundaries and compares the area. The assumption is that space boundaries with the same area are connected to the same element.
	 *
	 * </p>
	 * @param element
	 * @return
	 * @throws SdaiException
	 * @throws IfcException 
	 */
	public boolean isSeparatingSpaces(EIfcelement element) throws SdaiException, IfcException {
		Set<EIfcrelspaceboundary> relatedSpaceBoundaries = new HashSet<EIfcrelspaceboundary>();		
		EIfcrelspaceboundary[] spaceBoundaries = getSpaceBoundariesOfElement(element);
		
		if(spaceBoundaries != null) {
			// get all internal space boundaries of the element
			for(EIfcrelspaceboundary sb : spaceBoundaries) {
				if(sb.getInternalorexternalboundary(sb) == EIfcinternalorexternalenum.INTERNAL) relatedSpaceBoundaries.add(sb);
			}
			// no internal space boundaries means no separating element
			if(relatedSpaceBoundaries.size() == 0) return false;
			
			String referenceElementGuid = element.getGlobalid(element);			
			/*  
			 * | == == == | 		<- element between rooms -> get all space boundaries of the element and the space which are related 
			 */ 
			// collect all spaces so that a new iteration can start over them to get space boundaries which connects to the same IfcElement.
			Set<EIfcspace> relatedSpaces = new HashSet<EIfcspace>();
			Map<Double, Set<EIfcrelspaceboundary>> areas = new HashMap<Double, Set<EIfcrelspaceboundary>>();
			for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) { // take the relating spaces
				EIfcspace relatedSpace = spaceBoundary.getRelatingspace(spaceBoundary);
				relatedSpaces.add(relatedSpace);
			}
			/*  -- -- --
			 * |oo|oo|oo|
			 *  == == ==		<- retrieve boundary information (spaces and their space boundaries)
			 * |oo|xx|oo|
			 *  -- -- --
			 */
			// get all internal space boundaries of all relating spaces
			for(EIfcspace space : relatedSpaces) {
				Set<EIfcrelspaceboundary> internalOrExternal = getInternalOrExternalSpaceBoundaries(space, EIfcinternalorexternalenum.INTERNAL);
				relatedSpaceBoundaries.addAll(internalOrExternal);
			}
			// if less than 2 internal space boundaries are retrieved than no comparison is possible => element separates no spaces
			if(relatedSpaceBoundaries.size() < 2) return false;
			for(EIfcrelspaceboundary sb : relatedSpaceBoundaries) {
				EIfcelement relatedElement = sb.getRelatedbuildingelement(sb);
				// we only want to compare the reference element with the related element (because the spaces which have a relationship to it only makes sense)
				if(relatedElement.getGlobalid(relatedElement).equals(referenceElementGuid)) {
					double sbArea = getSpaceBoundaryArea(sb);
					if(areas.containsKey(sbArea)) {
						Set<EIfcrelspaceboundary> areaToSbs = areas.get(sbArea);
						areaToSbs.add(sb);
					} else {
						Set<EIfcrelspaceboundary> sbList = new HashSet<EIfcrelspaceboundary>();
						sbList.add(sb);
						areas.put(sbArea, sbList);
					}
				}
			}
			// when no areas are added it seems that it is an outdoor element which separates no spaces 
			if(areas.size() == 0) return false;

			for(Map.Entry<Double, Set<EIfcrelspaceboundary>> entry : areas.entrySet()) {
				Set<EIfcrelspaceboundary> values = entry.getValue();
//				if(values.size() < 2) {
//					throw new RuntimeException("There is a problem with element: "+element+" (currently checking space boundary: "+values+")");
//				} else if(values.size() > 2 && (!(element instanceof EIfcslab))) {
//					throw new RuntimeException("Not sure if element: "+element+" is separating spaces (currently checking space boundary: "+values+")");
//				} else {
//					return true;
//				}
				if(values.size() == 2) return true;
			}
		}
		return false;
	}
	
	/**
	 * <p><b>04.02.2014 - Warning: Currently not really filterable from IFC (except a space has the name 'cellar'). It is an experimental method.</b></p>
	 * <b>Note: Currently only usable with second level space boundaries created through BSPro.</b>
	 * <p>
	 * Retrieve the slabs which are separating the cellar from the higher storeys of the building.
	 * </p>
	 * @param optionalBuildingGuid For faster performance (optional)
	 * @return
	 * @throws IfcException
	 */
	public List<EIfcslab> getCellarSeparatingSlabs(String optionalBuildingGuid) throws IfcException {
		try {
			List<EIfcslab> slabsOfPossibleCellar = new ArrayList<EIfcslab>();
			
			EIfcbuildingstorey[] storeys = null;
			EIfcbuilding building = getIfcEntity(optionalBuildingGuid, EIfcbuilding.class);
			if(building == null) {
				storeys = ifcModel.getBuildingStoreys();
			} else {
				storeys = getBuildingStoreys(building);
			}
			List<EIfcbuildingstorey> possibleStoreys = new ArrayList<EIfcbuildingstorey>();
			
			for(EIfcbuildingstorey storey : storeys) {			
				// take only a storey with spaces
				EIfcspace[] spacesInStorey = getSpacesInStorey(storey);
				if(spacesInStorey == null) continue;
				// add the storey to the set
				possibleStoreys.add(storey);
			}
			if(possibleStoreys.size() < 2) return null; // too few storeys
			// order the storeys ascending by their elevations
			Collections.sort(possibleStoreys, new IfcBuildingStoreyComparatorAscending());
			// now take the first storey and retrieve the slabs
			EIfcbuildingstorey possibleCellar = possibleStoreys.get(0);
			// get all slabs of the possible cellar which are separating spaces
			
			Set<EIfcelement> elementsInPossibleCellar = getElementsInStorey(possibleCellar);
			for(EIfcelement elementInPossibleCellar : elementsInPossibleCellar) {
				if(elementInPossibleCellar instanceof EIfcslab) {
					EIfcslab possibleCellarSlab = (EIfcslab) elementInPossibleCellar;
					if(isSeparatingSpaces(possibleCellarSlab)) {
						slabsOfPossibleCellar.add(possibleCellarSlab);
					}
				}
			}
			return slabsOfPossibleCellar;
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * <b>Note: Currently only usable with second level space boundaries and if the 'Elevation' is set in IfcBuildingStorey entities.</b>
	 * <p>
	 * Retrieve the soil slab(s) of the building.
	 * </p>
	 * @param optionalBuildingGuid For faster performance (optional)
	 * @return The soil slab(s) of the building.
	 * @throws IfcException
	 */
	public List<EIfcslab> getSoilSlabs(String optionalBuildingGuid) throws IfcException {
		try {
			EIfcbuildingstorey[] storeys = null;
			EIfcbuilding building = getIfcEntity(optionalBuildingGuid, EIfcbuilding.class);
			if(building == null) {
				storeys = ifcModel.getBuildingStoreys();
			} else {
				storeys = getBuildingStoreys(building);
			}
			List<EIfcbuildingstorey> possibleStoreys = new ArrayList<EIfcbuildingstorey>();
			
			for(EIfcbuildingstorey storey : storeys) {			
				// take only a storey with spaces
				EIfcspace[] spacesInStorey = getSpacesInStorey(storey);
				if(spacesInStorey == null) continue;
				// add the storey to the set
				possibleStoreys.add(storey);
			}
			// order the storeys ascending by their elevations
			Collections.sort(possibleStoreys, new IfcBuildingStoreyComparatorAscending());
			// now take the first storey and retrieve the slabs
			EIfcbuildingstorey possibleCellar = possibleStoreys.get(0);
			
			// get all slabs of the storey
			List<EIfcslab> soilSlabs = new ArrayList<EIfcslab>();
			Set<EIfcelement> elementsInPossibleCellar = getElementsInStorey(possibleCellar);
			for(EIfcelement elementInPossibleCellar : elementsInPossibleCellar) {
				if(elementInPossibleCellar instanceof EIfcslab) {
					EIfcslab possibleCellarSlab = (EIfcslab) elementInPossibleCellar;
					if(!isSeparatingSpaces(possibleCellarSlab)) {
						soilSlabs.add(possibleCellarSlab);
					}
				}
			}
			return soilSlabs;
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * <p><b>04.02.2014 - Warning: Currently not really filterable from IFC. It is an experimental method of the ISES project.</b></p>
	 * <b>Note: Currently only usable with second level space boundaries created through BSPro.</b>
	 * <p>
	 * Retrieve the slabs which are separating the rooms of the building.
	 * </p>
	 * @param optionalBuildingGuid For faster performance (optional)
	 * @return
	 * @throws IfcException
	 */
	public List<EIfcslab> getRoomSeparatingSlabs(String optionalBuildingGuid) throws IfcException {
		try {
			List<EIfcslab> roomSeparatingSlabs = new ArrayList<EIfcslab>();
			EIfcbuildingstorey[] storeys = null;
			EIfcbuilding building = getIfcEntity(optionalBuildingGuid, EIfcbuilding.class);
			if(building == null) {
				storeys = ifcModel.getBuildingStoreys();
			} else {
				storeys = getBuildingStoreys(building);
			}
			List<EIfcbuildingstorey> possibleStoreys = new ArrayList<EIfcbuildingstorey>();
			
			for(EIfcbuildingstorey storey : storeys) {			
				// take only a storey with spaces
				EIfcspace[] spacesInStorey = getSpacesInStorey(storey);
				if(spacesInStorey == null) continue;
				// add the storey to the set
				possibleStoreys.add(storey);
			}
			if(possibleStoreys.size() < 2) return null; // too few storeys
			// order the storeys ascending by their elevations
			Collections.sort(possibleStoreys, new IfcBuildingStoreyComparatorAscending());
			// now take the storeys between the first and the last and retrieve the slabs
			possibleStoreys.remove(possibleStoreys.size()-1);
			possibleStoreys.remove(0);
			for(EIfcbuildingstorey storey : possibleStoreys) {
				// get all slabs of the possible cellar which are separating spaces
				Set<EIfcelement> elementsInPossibleCellar = getElementsInStorey(storey);
				for(EIfcelement elementInPossibleCellar : elementsInPossibleCellar) {
					if(elementInPossibleCellar instanceof EIfcslab) {
						EIfcslab possibleCellarSlab = (EIfcslab) elementInPossibleCellar;
						if(isSeparatingSpaces(possibleCellarSlab)) {
							roomSeparatingSlabs.add(possibleCellarSlab);
						}
					}
				}
			}
			return roomSeparatingSlabs;
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * <p><b>04.02.2014 - Warning: Currently not really filterable from IFC (except a space has the name 'roof'). 
	 * It is an experimental method of the ISES project.</b></p>
	 * 
	 * <b>Note: Currently only usable with second level space boundaries created through BSPro.</b>
	 * <p>
	 * Retrieve the slabs which are separating the roof from the lower storeys of the building.
	 * </p>
	 * @param optionalBuildingGuid For faster performance (optional)
	 * @return
	 * @throws IfcException
	 */
	public List<EIfcslab> getRoofSeparatingSlabs(String optionalBuildingGuid) throws IfcException {
		try {
			EIfcbuildingstorey[] storeys = null;
			EIfcbuilding building = getIfcEntity(optionalBuildingGuid, EIfcbuilding.class);
			if(building == null) {
				storeys = ifcModel.getBuildingStoreys();
			} else {
				storeys = getBuildingStoreys(building);
			}
			List<EIfcbuildingstorey> possibleStoreys = new ArrayList<EIfcbuildingstorey>();
			
			for(EIfcbuildingstorey storey : storeys) {			
				// take only a storey with spaces
				EIfcspace[] spacesInStorey = getSpacesInStorey(storey);
				if(spacesInStorey == null) continue;
				// add the storey to the set
				possibleStoreys.add(storey);
			}
			if(possibleStoreys.size() < 2) return null; // too few storeys
			// order the storeys descending by their elevations
			Collections.sort(possibleStoreys, new IfcBuildingStoreyComparatorDescending());
			// now take the first storey and retrieve the slabs
			EIfcbuildingstorey possibleRoof = possibleStoreys.get(0);
			// get all slabs of the possible roof which are separating spaces
			List<EIfcslab> slabsOfPossibleRoof = new ArrayList<EIfcslab>();
			Set<EIfcelement> elementsInPossibleRoof = getElementsInStorey(possibleRoof);
			for(EIfcelement elementInPossibleRoof : elementsInPossibleRoof) {
				if(elementInPossibleRoof instanceof EIfcslab) {
					EIfcslab possibleRoofSlab = (EIfcslab) elementInPossibleRoof;
					if(isSeparatingSpaces(possibleRoofSlab)) {
						slabsOfPossibleRoof.add(possibleRoofSlab);
					}
				}
			}
			return slabsOfPossibleRoof;
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/**
	 * <p><b>04.02.2014 - Warning: Currently not really filterable from IFC (except a space has the name 'cellar'). 
	 * It is an experimental method of the ISES project.</b></p>
	 * @param slabGuid
	 * @param optionalBuildingGuid
	 * @return
	 * @throws IfcException
	 */
	public boolean isCellarSeparatingSlab(String slabGuid, String optionalBuildingGuid) throws IfcException {
		EIfcslab slab = getIfcEntity(slabGuid, EIfcslab.class);
		List<EIfcslab> cellarSeparatingSlabs = getCellarSeparatingSlabs(optionalBuildingGuid);
		if(cellarSeparatingSlabs != null && cellarSeparatingSlabs.contains(slab)) return true;
		return false;
	}
	
	/**
	 * <p><b>04.02.2014 - Warning: Currently not really filterable from IFC (except a space has the name 'roof'). 
	 * It is an experimental method of the ISES project.</b></p>
	 * @param slabGuid
	 * @param optionalBuildingGuid
	 * @return
	 * @throws IfcException
	 */
	public boolean isRoofSeparatingSlab(String slabGuid, String optionalBuildingGuid) throws IfcException {
		EIfcslab slab = getIfcEntity(slabGuid, EIfcslab.class);
		List<EIfcslab> roofSeparatingSlabs = getRoofSeparatingSlabs(optionalBuildingGuid);
		if(roofSeparatingSlabs != null && roofSeparatingSlabs.contains(slab)) return true;
		return false;
	}
	
	/**
	 * <p><b>04.02.2014 - Warning: Currently not really filterable from IFC. 
	 * It is an experimental method of the ISES project.</b></p>
	 * @param slabGuid
	 * @param optionalBuildingGuid
	 * @return
	 * @throws IfcException
	 */
	public boolean isRoomSeparatingSlab(String slabGuid, String optionalBuildingGuid) throws IfcException {
		EIfcslab slab = getIfcEntity(slabGuid, EIfcslab.class);
		List<EIfcslab> roomSeparatingSlabs = getRoomSeparatingSlabs(optionalBuildingGuid);
		if(roomSeparatingSlabs != null && roomSeparatingSlabs.contains(slab)) return true;
		return false;
	}
	
	public boolean isSoilSlab(String slabGuid, String optionalBuildingGuid) throws IfcException {
		EIfcslab slab = getIfcEntity(slabGuid, EIfcslab.class);
		List<EIfcslab> roomSeparatingSlabs = getSoilSlabs(optionalBuildingGuid);
		if(roomSeparatingSlabs.contains(slab)) return true;
		return false;
	}
	
	public List<EIfcstair> getStairsInBuildingStorey(EIfcbuildingstorey storey) throws IfcException {
		try {
			List<EIfcstair> stairs = new ArrayList<EIfcstair>();
			String referenceStoreyGuid = getGlobalId(storey);
			EIfcrelcontainedinspatialstructure[] relContains = getIfcEntitiesOf(EIfcrelcontainedinspatialstructure.class);
			for(EIfcrelcontainedinspatialstructure relContain : relContains) {
				EIfcspatialstructureelement relatingStructure = relContain.getRelatingstructure(relContain);
				// search the given storey
				if(relatingStructure instanceof EIfcbuildingstorey) {
					String storeyGuid = relatingStructure.getGlobalid(relatingStructure);
					if(referenceStoreyGuid.equals(storeyGuid)) {
						// search building elements of type IfcStair
						for(EIfcproduct product : new IterableAggregate<EIfcproduct>(relContain.getRelatedelements(relContain))){
							if(product instanceof EIfcstair) {
								EIfcstair stair = (EIfcstair) product;
								stairs.add(stair);
							}
						}
					}
				}
			}
			return stairs;
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
	}

	public Set<String> getOuterRoomsOfBuilding(String buildingGuid) throws IfcException {
		Set<String> guids = new HashSet<String>();
		try {
			EIfcspace[] allSpacesInBuilding = getSpaces(buildingGuid);
			for(EIfcspace space : allSpacesInBuilding) {
				EIfcrelspaceboundary[] secondLevelSpaceBoundaries = ifcModel.get2ndLevelSpaceBoundaries(
						space, EIfcphysicalorvirtualenum.PHYSICAL, EIfcinternalorexternalenum.EXTERNAL);
				sb: for(EIfcrelspaceboundary spaceBoundary : secondLevelSpaceBoundaries) {
					int internalOrExternal = spaceBoundary.getInternalorexternalboundary(spaceBoundary);
					if(EIfcinternalorexternalenum.EXTERNAL == internalOrExternal) {
						EIfcspace relatedSpace = spaceBoundary.getRelatingspace(spaceBoundary);
						guids.add(getGlobalId(relatedSpace));
						break sb; // we need no further checking if one building element is an outer element
					}
				}
			}
		} catch (SdaiException e) {
			throw new IfcException(e);
		}

		return guids;
	}
	
	public Set<EIfcspace> getOuterRoomsOfStorey(String buildingStoreyGuid) throws IfcException {
		Set<EIfcspace> spaces = new HashSet<EIfcspace>();
		try {
			EIfcbuildingstorey storey = getStorey(buildingStoreyGuid);
			EIfcspace[] allSpacesInStorey = ifcModel.getSpacesInStorey(storey);
			for(EIfcspace space : allSpacesInStorey) {
				EIfcrelspaceboundary[] secondLevelSpaceBoundaries = ifcModel.get2ndLevelSpaceBoundaries(
						space, EIfcphysicalorvirtualenum.PHYSICAL, EIfcinternalorexternalenum.EXTERNAL);
				sb: for(EIfcrelspaceboundary spaceBoundary : secondLevelSpaceBoundaries) {
					int internalOrExternal = spaceBoundary.getInternalorexternalboundary(spaceBoundary);
					if(EIfcinternalorexternalenum.EXTERNAL == internalOrExternal) {
						EIfcspace relatedSpace = spaceBoundary.getRelatingspace(spaceBoundary);
						spaces.add(relatedSpace);
						break sb; // we need no further checking if one building element is an outer element
					}
				}
			}
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
		return spaces;
	}
	
	public Set<EIfcspace> getOuterRoomsAsIfcSpace(String buildingGuid) throws IfcException {
		Set<EIfcspace> spaces = new HashSet<EIfcspace>();
		try{
			EIfcspace[] allSpacesInBuilding = getSpaces(buildingGuid);
			for(EIfcspace space : allSpacesInBuilding) {
				EIfcrelspaceboundary[] secondLevelSpaceBoundaries = ifcModel.get2ndLevelSpaceBoundaries(
						space, EIfcphysicalorvirtualenum.PHYSICAL, EIfcinternalorexternalenum.EXTERNAL);
				sb: for(EIfcrelspaceboundary spaceBoundary : secondLevelSpaceBoundaries) {
					int internalOrExternal = spaceBoundary.getInternalorexternalboundary(spaceBoundary);
					if(EIfcinternalorexternalenum.EXTERNAL == internalOrExternal) {
						EIfcspace relatedSpace = spaceBoundary.getRelatingspace(spaceBoundary);
						spaces.add(relatedSpace);
						break sb; // we need no further checking if one building element is an outer element
					}
				}
			}
		} catch(SdaiException e) {
			throw new IfcException(e);
		}

		return spaces;
	}
	
	public EIfcproperty[] getProperties(EIfcobject ifcObject) throws IfcException {
		try {
			EIfcproperty[] properties = ifcModel.getAssignedProperties(ifcObject);
			return properties;
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	public Object getPropertyValue(EIfcproperty property) throws IfcException {
		try {
			return ifcModel.getPropertyValue(property);
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	public String getTypeForElement(String buildingElementGuid) throws IfcException {	
		try {
			EIfcreldefinesbytype[] relDefinesByTypes = ifcModel.getBasicModel().getEntitiesOf(EIfcreldefinesbytype.class);
			
			for(EIfcreldefinesbytype relDefinesByType : relDefinesByTypes) {
				EIfctypeobject relatingType = relDefinesByType.getRelatingtype(relDefinesByType);
				if(relatingType instanceof EIfcelementtype) {
					EIfcelementtype eType = (EIfcelementtype) relatingType;
					if(eType.testElementtype(eType)) {
						AIfcobject objects = relDefinesByType.getRelatedobjects(relDefinesByType);
						IterableAggregate<EIfcobject> iterable = new IterableAggregate<EIfcobject>(objects);
						for(EIfcobject object : iterable) {
							if(object instanceof EIfcelement) {
								EIfcelement element = (EIfcelement)object;
								String guid = element.getGlobalid(element);
								if(guid.equals(buildingElementGuid)){
									String label = eType.getElementtype(eType);
									return label;
								}
							}
						}
					}

				}
			}					
		} catch (SdaiException e) {
			throw new IfcException(e);
		}	
		return null;
	}
	
	public boolean has2ndLevelSpaceBoundaries() throws IfcException {
		try {
			return ifcModel.exists2ndLevelSpaceBoundaries();
		} catch (SdaiException e) {
			throw new IfcException(e);
		}
	}
	
	/** 
	 * Creates a unique Global Identifier for the IFC element.
	 * @return The created GlobalId for the IFC entity.
	 * @throws IfcException 
	 */
	public String createGuid() throws IfcException {
		String guid = UUID.randomUUID().toString().replaceAll("-", "").substring(0,22);
//		HINT: recursion!
		if(getGlobalIds(getIfcEntities()).contains(guid)) {
			guid = createGuid();
		}
		return guid;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Ifc2x3DataModelProxy)
		{
			Ifc2x3DataModelProxy other = (Ifc2x3DataModelProxy)obj;
			if(this.ifcModel.equals(other.ifcModel)) {
				return true;
			}
			return super.equals(obj);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return ifcModel.hashCode();
	}
	
	public double getIfcLengthConversionFactor() throws SdaiException, IfcException {
		double approximativeValue = 1.0; // approximative value is 1 metre
		Set<EIfcsiunit> siUnits = getIfcSIUnits(getIfcProject());
		double ifcLengthUnitConversionFactorPerMetre = 1.0;
		for(EIfcsiunit siUnit: siUnits) {
			if(siUnit.getUnittype(siUnit) == EIfcunitenum.LENGTHUNIT) {								
				double conversionValue = approximativeValue; 
				int siUnitNameEnum = siUnit.getName(siUnit);
	//			should be METRE
				IfcSIUnitNameEnum unitName = IfcSIUnitNameEnum.valueOf(EIfcsiunitname.toString(siUnitNameEnum));
				if(unitName != null) {		
					if(siUnit.testPrefix(siUnit)){
						int siPrefix = siUnit.getPrefix(siUnit);
						IfcSIPrefix prefix = IfcSIPrefix.valueOf(EIfcsiprefix.toString(siPrefix));
						conversionValue = approximativeValue/prefix.getConversionValue(); // conversion factor per approximative value
					}
				}// no prefix given -> take approximative value which is in IFC 1 metre
				
				ifcLengthUnitConversionFactorPerMetre = conversionValue;
			}
		}
		return ifcLengthUnitConversionFactorPerMetre;
	}

	@Override
	public String getNameSpace() {
		return NS_IFCOWL;
	}
	
	public Set<EIfcmaterial> getMaterialsOfBuildingElement(String buildingElementGuid) throws IfcException {
		EIfcbuildingelement element = getIfcEntity(buildingElementGuid, EIfcbuildingelement.class);
		if(element != null) {
			Set<EIfcmaterial> materials = ifcModel.getAssociatedMaterial(element);
			return materials;
		}
		return new HashSet<EIfcmaterial>();
	}
	
	public List<EIfcmateriallayer> getSortedMaterialLayers(EIfcbuildingelement element) throws IfcException {
		List<EIfcmateriallayer> matLayers = new ArrayList<EIfcmateriallayer>();
		
		try {
			EIfcrelassociatesmaterial[] relA = getIfcEntitiesOf(EIfcrelassociatesmaterial.class);
			for(EIfcrelassociatesmaterial relAentity : relA) {
				AIfcroot relatedObjects = relAentity.getRelatedobjects(relAentity);
				if(relatedObjects.isMember(element) || element == null) {	
					EEntity matSelect = ((EIfcrelassociatesmaterial) relAentity).getRelatingmaterial((EIfcrelassociatesmaterial) relAentity);
						
					if(matSelect instanceof EIfcmaterial) throw new IllegalArgumentException("Element: "+element+" references directly materials - use getMaterialsOfBuildingElement() instead");					
					if(matSelect instanceof EIfcmateriallist) throw new IllegalArgumentException("Element: "+element+" references IfcMaterialList which is currently not permitted");
						
					if(matSelect instanceof EIfcmateriallayersetusage) {
						EIfcmateriallayersetusage layerSetUsage = (EIfcmateriallayersetusage) matSelect;
						EIfcmateriallayerset layerSet = layerSetUsage.getForlayerset(layerSetUsage);
						AIfcmateriallayer layer = layerSet.getMateriallayers(layerSet); 
						SdaiIterator it = layer.createIterator();
						while(it.next()) { 
							EIfcmateriallayer l = (EIfcmateriallayer) layer.getCurrentMemberEntity(it); 
							matLayers.add(l); 
						}
						continue;
					}
						
					if(matSelect instanceof EIfcmateriallayerset) {
						EIfcmateriallayerset layerSet = (EIfcmateriallayerset) matSelect;
						AIfcmateriallayer layer = layerSet.getMateriallayers(layerSet); 
						SdaiIterator it = layer.createIterator();
						while(it.next()) { 
							EIfcmateriallayer l = (EIfcmateriallayer) layer.getCurrentMemberEntity(it); 
							matLayers.add(l); }
						continue;
					}
						
					if(matSelect instanceof EIfcmateriallayer) {
						EIfcmateriallayer layer = (EIfcmateriallayer)matSelect;
						matLayers.add(layer);
						continue;
					}
				}
			}
		} catch(SdaiException e) { 
			throw new IfcException(e); 
		}
		return matLayers;
	}
	
	public EIfcrelassociatesmaterial getRelationToMaterials(String buildingElementGuid) throws IfcException {
		try {
			EIfcrelassociatesmaterial[] relMaterials = getIfcEntitiesOf(EIfcrelassociatesmaterial.class);
			for(EIfcrelassociatesmaterial relMaterial : relMaterials) {
				for(EIfcroot root : new IterableAggregate<EIfcroot>(relMaterial.getRelatedobjects(relMaterial))){
					String guid = getGlobalId(root);
					if(guid.equals(buildingElementGuid)) return relMaterial;
				}
			}
		} catch(SdaiException e) {
			throw new IfcException(e);
		}
		return null;
	}

	@Override
	public String getModelType() {
		return TYPE_IFC;
	}
	
	class IfcBuildingStoreyComparatorAscending implements Comparator<EIfcbuildingstorey>  {

		@Override
		public int compare(EIfcbuildingstorey storey1, EIfcbuildingstorey storey2) {
			double elevationStorey1 = 0;
			double elevationStorey2 = 0;
			try {
				elevationStorey1 = storey1.getElevation(storey1);
				elevationStorey2 = storey2.getElevation(storey1);
			}
			catch (SdaiException e) {
			
			}
			if(elevationStorey1 < elevationStorey2) return -1;
			if(elevationStorey1 > elevationStorey2) return 1;
			return 0;
		}
	}
	
	class IfcBuildingStoreyComparatorDescending implements Comparator<EIfcbuildingstorey>  {

		@Override
		public int compare(EIfcbuildingstorey storey1, EIfcbuildingstorey storey2) {
			double elevationStorey1 = 0;
			double elevationStorey2 = 0;
			try {
				elevationStorey1 = storey1.getElevation(storey1);
				elevationStorey2 = storey2.getElevation(storey1);
			}
			catch (SdaiException e) {
			
			}
			if(elevationStorey1 < elevationStorey2) return 1;
			if(elevationStorey1 > elevationStorey2) return -1;
			return 0;
		}
	}

	public Set<EIfcroot> getIfcEntitiesByGlobalIds(Collection<String> guids) throws IfcException {
		Set<EIfcroot> entities = new HashSet<EIfcroot>();
		for(String guid : guids) {
			EIfcroot ifcEntity = getIfcEntity(guid, null);
			if(ifcEntity != null) entities.add(ifcEntity);
		}
		return entities;
	}
		
	public <T extends EIfcroot> Set<T> getIfcEntitiesByGlobalIds(Collection<String> guids, Class<T> clazz) throws IfcException {
		Set<T> entities = new HashSet<T>();
		for(String guid : guids) {
			T ifcEntity = getIfcEntity(guid, clazz);
			if(ifcEntity != null) entities.add(ifcEntity);
		}
		return entities;
	}
}
