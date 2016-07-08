package de.tudresden.bau.cib.vl.core.model.ontology.individuals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jsdai.SIfc2x3.AIfcobjectdefinition;
import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingelement;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcelementquantity;
import jsdai.SIfc2x3.EIfcinternalorexternalenum;
import jsdai.SIfc2x3.EIfcmaterial;
import jsdai.SIfc2x3.EIfcmateriallayer;
import jsdai.SIfc2x3.EIfcobject;
import jsdai.SIfc2x3.EIfcobjectdefinition;
import jsdai.SIfc2x3.EIfcphysicalorvirtualenum;
import jsdai.SIfc2x3.EIfcphysicalquantity;
import jsdai.SIfc2x3.EIfcpropertydefinition;
import jsdai.SIfc2x3.EIfcquantityarea;
import jsdai.SIfc2x3.EIfcquantitylength;
import jsdai.SIfc2x3.EIfcquantityvolume;
import jsdai.SIfc2x3.EIfcrelaggregates;
import jsdai.SIfc2x3.EIfcrelassociatesmaterial;
import jsdai.SIfc2x3.EIfcreldefinesbyproperties;
import jsdai.SIfc2x3.EIfcrelspaceboundary;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcsite;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcspatialstructureelement;
import jsdai.lang.SdaiException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;

import de.tudresden.bau.cib.model.utility.IterableAggregate;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy.Quantities;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.AllowedIfcClasses;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.BIMOntoVocabulary;

/**
 * Load instances from IFC-based files to the ontology.
 * 
 * @author Ken
 *
 */
public class IfcIndividualFactory extends AbstractModelIndividualFactory<Ifc2x3DataModelProxy> {
	
	enum PropertyDefinitionNames {
		BaseQuantities,
		SlabQuantities,
		SpaceQuantities,
		WallQuantities
		;
		
		PropertyDefinitionNames() {
			
		}
		
		public static boolean isDefined(String name) {
			boolean isDefined = false;
			PropertyDefinitionNames[] definitions = values();
			for(PropertyDefinitionNames definition : definitions) {
				if(definition.name().equalsIgnoreCase(name)) {
					isDefined = true;
					break;
				}
			}
			return isDefined;
		}
	}

	
	private static final Logger LOG = LoggerFactory.getLogger(IfcIndividualFactory.class);
	
	private final boolean filterIfcClassesFlag;
	
	public IfcIndividualFactory(OntologyModel ontModel, Ifc2x3DataModelProxy ifcModel, boolean filterIfcClasses) {
		super(ontModel, ifcModel);
		this.filterIfcClassesFlag = filterIfcClasses;
	}
	
	public boolean canIfcClassOccurInOntology(String className) {
		if(filterIfcClassesFlag) {
			AllowedIfcClasses[] allowedClasses = AllowedIfcClasses.values();
			for(AllowedIfcClasses clazz : allowedClasses) {
				if(className.equalsIgnoreCase(clazz.getName())) {
					return true;
				}
			}
			return false;	
		}
		return true;
	}

	@Override
	public Set<Individual> createAllIndividuals() throws FactoryException {
		LOG.debug("Read Ifc Model: {} and create all individuals from the instances", sourceModel.getId());
		Set<Individual> ifcIndividuals = null;	
		try {
			EIfcroot[] entities = sourceModel.getIfcEntitiesOf(EIfcroot.class);	
			if(entities != null) ifcIndividuals = new HashSet<Individual>();

			for(EIfcroot root : entities) {				
				String guid = root.getGlobalid(root);
				Individual ind = createIndividual(guid);
				ifcIndividuals.add(ind);
			}
		} catch (IfcException e) {
			throw new FactoryException(e.getMessage(), e);
		} catch (SdaiException e) {
			throw new FactoryException(e.getMessage(), e);
		}
		return ifcIndividuals;
	}

	@Override
	public Individual createIndividual(String elementId) throws FactoryException {
		Individual ind = null;
		try {
			EIfcroot ifcRoot = sourceModel.getIfcEntity(elementId, EIfcroot.class);
			String guid = sourceModel.getGlobalId(ifcRoot);
			String uri = OntologyModel.getUniformResourceIdentifierOfElementInModel(sourceModel, guid);
			ind = ontologyModel.getIndividualByURI(uri);
			//	create IFC individual if not existent
			if(ind == null) {
				String entityClassName = ifcRoot.getClass().getSimpleName();
				entityClassName = entityClassName.substring(1).toLowerCase(); // remove first 'E' or 'C' because of JSDAI and convert to lower case
				//	only allowed IFC classes can occur in the ontology
				if(!canIfcClassOccurInOntology(entityClassName)){
					LOG.warn("IFC class {} cannot occur in ontology. Please change your settings if this is not desired.",
							new Object[]{ifcRoot.getClass()});
					throw new FactoryException("IFC class '"+entityClassName+"' not allowed");
				}

				OntClass ontClass = BIMOntoVocabulary.findOntClass(getUriOfOntologyClassInModelInLowerCase(sourceModel, entityClassName));
//				OntClass ontClass = getUriOfOntologyClassInModelInLowerCase(ifcModel, entityClassName));
				if(ontClass == null) throw new FactoryException("Can't find the appropriate Ontology class for the entity ('"+entityClassName+"')");
				ind = createIndividualByElementId(ontClass, guid);
				
				/**
				 * Add GlobalId, Name and Description
				 */

				//	add globalId		
				addGlobalUniqueId(guid, ind);		

				if(ifcRoot.testName(ifcRoot)) {
					//	add name
					String name = ifcRoot.getName(ifcRoot);
					addName(name, ind);
				}

				if(ifcRoot.testDescription(ifcRoot)) {
					String description = ifcRoot.getDescription(ifcRoot);
					//	add description
					addDescription(description, ind);
				}
				
				/**
				 * Handle all sub classes of IfcRoot according to extension schema which means the most general at first
				 * and the most concrete at last 
				 */

				// handle IfcObject
				if(ifcRoot instanceof EIfcobject) {
					createRelationsToIfcPropertyDefinitions((EIfcobject)ifcRoot);
				}

				// handle IfcSpatialStructureElement
				if(ifcRoot instanceof EIfcspatialstructureelement) {
					handleIfcSpatialStructureElement(ifcRoot, ind);
				}

				// handle IfcRelSpaceBoundary
				if(ifcRoot instanceof EIfcrelspaceboundary) {
					handleIfcRelSpaceBoundary(ind, (EIfcrelspaceboundary) ifcRoot) ;
				}
				
				// handle IfcRelAggregates
				if(ifcRoot instanceof EIfcrelaggregates) {
					handleIfcRelAggregates(ind, (EIfcrelaggregates) ifcRoot);
				}

				// handle IfcRelDefinesByProperties
				if(ifcRoot instanceof EIfcreldefinesbyproperties) {
					handleIfcRelDefinesByProperties(ind, (EIfcreldefinesbyproperties) ifcRoot);
				}

				// handle IfcRelAssociatesMaterial
				if(ifcRoot instanceof EIfcrelassociatesmaterial) {
					handleIfcRelAssociatesMaterial(ind, (EIfcrelassociatesmaterial) ifcRoot);
				}

				// handle IfcElementQuantity
				if(ifcRoot instanceof EIfcelementquantity) {
					handleIfcElementQuantity((EIfcelementquantity) ifcRoot, ind);
				}
			}		

		} catch (SdaiException e) {
			throw new FactoryException(e.getMessage(), e);
		} catch (IfcException e) {
			throw new FactoryException(e.getMessage(), e);
		}

		return ind;
	}

	private void handleIfcRelAggregates(Individual owlRelAggregates, EIfcrelaggregates ifcRelAggregates) throws FactoryException {
		try {
			if(ifcRelAggregates.testRelatedobjects(ifcRelAggregates)) {
				AIfcobjectdefinition ifcobjectdefinitions = ifcRelAggregates.getRelatedobjects(ifcRelAggregates);
				for(EIfcobjectdefinition ifcobjectdefinition : new IterableAggregate<EIfcobjectdefinition>(ifcobjectdefinitions)) {
					Individual owlObjectDefinition = createIndividual(sourceModel.getGlobalId(ifcobjectdefinition));
					
					ObjectProperty relatedObjectsElement = BIMOntoVocabulary.RELATED_OBJECTS;
					if(relatedObjectsElement != null) {
						this.ontologyModel.addRelation(owlRelAggregates, relatedObjectsElement, owlObjectDefinition);
						LOG.trace("Added object definition: {} to aggregate: {}", new Object[]{owlObjectDefinition, owlRelAggregates});
					}
				}
			}
			
			if(ifcRelAggregates.testRelatingobject(ifcRelAggregates)) {
				EIfcobjectdefinition ifcobjectdefinition = ifcRelAggregates.getRelatingobject(ifcRelAggregates);
				Individual owlObjectDefinition = createIndividual(sourceModel.getGlobalId(ifcobjectdefinition));
				
				ObjectProperty relatingObject = BIMOntoVocabulary.RELATING_OBJECT;
				if(relatingObject != null) {
					this.ontologyModel.addRelation(owlRelAggregates, relatingObject, owlObjectDefinition);
					LOG.trace("Added object definition: {} to aggregate: {}", new Object[]{owlObjectDefinition, owlRelAggregates});
				}
			}
		} catch (SdaiException e) {
			throw new FactoryException(e);
		} catch (IfcException e) {
			throw new FactoryException(e);
		}
	}

	protected void handleIfcRelAssociatesMaterial(Individual owlRelMaterial, EIfcrelassociatesmaterial relMaterial) throws FactoryException {
		try {
			for(EIfcroot root : new IterableAggregate<EIfcroot>(relMaterial.getRelatedobjects(relMaterial))){
				try {
					if(!(root instanceof EIfcbuildingelement)) throw new IllegalArgumentException("Entity is no building element: "+root);
					EIfcbuildingelement be = (EIfcbuildingelement) root;
					String guid = sourceModel.getGlobalId(root);
					Individual owlBuildingElement = createIndividual(guid);
					
					// convenience call because of DRY principle even it is with less performance
					List<EIfcmateriallayer> layers = sourceModel.getSortedMaterialLayers(be);
					
					for(EIfcmateriallayer layer : layers) {					
						Individual owlMaterialLayer = handleIfcMaterialLayer(layer);
						if(owlMaterialLayer != null) {
							ObjectProperty relatingMaterial = BIMOntoVocabulary.RELATING_MATERIAL;
							if(relatingMaterial != null) {
								this.ontologyModel.addRelation(owlRelMaterial, relatingMaterial, owlMaterialLayer);
								LOG.trace("Added material layer: {} to material relation: {}", new Object[]{owlMaterialLayer, owlRelMaterial});
							}
						}
					}			
					ObjectProperty relatedObjects = BIMOntoVocabulary.RELATED_OBJECTS;
					if(relatedObjects != null) {
						this.ontologyModel.addRelation(owlRelMaterial, relatedObjects, owlBuildingElement);
						LOG.trace("Added building element: {} to material relation: {}", new Object[]{owlBuildingElement, owlRelMaterial});
					}
				} catch (IllegalArgumentException e) {
					LOG.warn(e.getMessage());
					continue;
				}
			}
		} catch (SdaiException e) {
			throw new FactoryException(e);
		} catch (IfcException e) {
			throw new FactoryException(e);
		}
	}
	
	private Individual handleIfcMaterialLayer(EIfcmateriallayer layer) throws FactoryException {
		try {
			EIfcmaterial material = layer.getMaterial(layer);
			Individual owlMaterial = handleIfcMaterial(material);
			if(owlMaterial != null) {
				double thickness = layer.getLayerthickness(layer);
				
				// add material to layer
				OntClass ifcMaterialLayerClass = BIMOntoVocabulary.IFC_MATERIAL_LAYER;
				Individual owlMaterialLayer = createIndividualByElementId(ifcMaterialLayerClass, UUID.randomUUID().toString());
				ObjectProperty materialOP = BIMOntoVocabulary.MATERIAL;
				if(materialOP != null) {
					this.ontologyModel.addRelation(owlMaterialLayer, materialOP, owlMaterial);
					LOG.trace("Added material: {} to material layer: {}", new Object[]{owlMaterial, owlMaterialLayer});
				}
				
				// add thickness to layer
				DatatypeProperty doubleValueProperty = BIMOntoVocabulary.DOUBLE_VALUE;
				if(doubleValueProperty != null) {
					owlMaterialLayer.addLiteral(doubleValueProperty, thickness);
					LOG.trace("Added thickness: {} to material layer: {}", new Object[]{thickness, owlMaterialLayer});
				}
				// add isVentilated
	
				if(layer.testIsventilated(layer)) {
					OntClass ifcLogicalClass = BIMOntoVocabulary.IFC_LOGICAL;
					Individual ifcLogicalInd = createIndividualByElementId(ifcLogicalClass, UUID.randomUUID().toString());
					int isVentilated = layer.getIsventilated(layer);
					ObjectProperty isVentilatedProperty = BIMOntoVocabulary.IS_VENTILATED;
					if(isVentilatedProperty != null){					
						DatatypeProperty integerValueProperty = BIMOntoVocabulary.INTEGER_VALUE;
						if(integerValueProperty != null) {
							ifcLogicalInd.addLiteral(integerValueProperty, isVentilated);
							this.ontologyModel.addRelation(owlMaterialLayer, isVentilatedProperty, ifcLogicalInd);
							LOG.trace("Added isVentilated: {} to material layer: {}", new Object[]{isVentilated, owlMaterialLayer});
						}
					}	
				}		
		
				return owlMaterialLayer;
			}
		} catch (SdaiException e) {
			throw new FactoryException(e);
		}
		return null;
	}

	private Individual handleIfcMaterial(EIfcmaterial material) throws FactoryException {
		try {
			String materialName = material.getName(material);
			if(StringUtils.isNotEmpty(materialName)) {
				materialName = StringUtils.trim(materialName);
				return createIfcMaterial(materialName);
			}
		} catch (SdaiException e) {
			throw new FactoryException(e);
		}
		return null;
	}
	
	private Individual createIfcMaterial(String materialName) throws FactoryException {
		OntClass ifcMaterialClass = BIMOntoVocabulary.IFC_MATERIAL;
		String uriName = StringUtils.stripAccents(materialName);
		uriName = StringUtils.deleteWhitespace(uriName);
		Individual owlMaterial = null;
		
		String uri = OntologyModel.getUniformResourceIdentifierOfElementInModel(sourceModel, uriName);
		owlMaterial = ontologyModel.getIndividualByURI(uri);
		
		if(owlMaterial == null) {
			owlMaterial = createIndividual(ifcMaterialClass, uriName);
			// add Name
			addName(materialName, owlMaterial);
		} 
		return owlMaterial;
	}

	protected void handleIfcSpatialStructureElement(EIfcroot ifcRoot, Individual ind)
			throws FactoryException {
		try {
			EIfcspatialstructureelement spatialStructureElement = (EIfcspatialstructureelement) ifcRoot;
			if(spatialStructureElement.testLongname(spatialStructureElement)) {
				String longName = spatialStructureElement.getLongname(spatialStructureElement);
				addLongName(longName, ind);
			}
		} catch (SdaiException e) {
			throw new FactoryException(e);
		}
	}
	
	protected void handleIfcRelSpaceBoundary(Individual owlSpaceBoundary, 
			EIfcrelspaceboundary ifcRelSpaceBoundary) throws FactoryException {
		try {
			if(ifcRelSpaceBoundary.testInternalorexternalboundary(ifcRelSpaceBoundary)) {
				int value = ifcRelSpaceBoundary.getInternalorexternalboundary(ifcRelSpaceBoundary);
				addInternalOrExternalBoundary(value, owlSpaceBoundary);
			}
			
			if(ifcRelSpaceBoundary.testPhysicalorvirtualboundary(ifcRelSpaceBoundary)) {
				int value = ifcRelSpaceBoundary.getPhysicalorvirtualboundary(ifcRelSpaceBoundary);
				addPhysicalOrVirtualBoundary(value, owlSpaceBoundary);
			}
			
			// link to related element
			EIfcelement element = ifcRelSpaceBoundary.getRelatedbuildingelement(ifcRelSpaceBoundary);
			Individual owlElement = createIndividual(sourceModel.getGlobalId(element));
			ObjectProperty relatedElement = BIMOntoVocabulary.RELATED_BUILDING_ELEMENT;
			if(relatedElement != null) {
				this.ontologyModel.addRelation(owlSpaceBoundary, relatedElement, owlElement);
				LOG.trace("Added element: {} to space boundary: {}", new Object[]{owlElement, owlSpaceBoundary});
				this.ontologyModel.addRelation(owlElement, BIMOntoVocabulary.PROVIDES_BOUNDARIES, owlSpaceBoundary);
				LOG.trace("Added space boundary: {} to element: {} through property: {}", 
						new Object[]{owlSpaceBoundary, owlElement, BIMOntoVocabulary.PROVIDES_BOUNDARIES});
			}
			
			// link to related space
			EIfcspace space = ifcRelSpaceBoundary.getRelatingspace(ifcRelSpaceBoundary);
			String spaceGuid = sourceModel.getGlobalId(space);
			Individual owlSpace = createIndividual(spaceGuid);
			ObjectProperty relatingSpace = BIMOntoVocabulary.RELATING_SPACE;
			if(relatingSpace != null) {
				this.ontologyModel.addRelation(owlSpaceBoundary, relatingSpace, owlSpace);
				LOG.trace("Added space: {} to space boundary: {}", new Object[]{owlSpace, owlSpaceBoundary});
			}
		} catch (SdaiException e) {
			throw new FactoryException(e);
		} catch (IfcException e) {
			throw new FactoryException(e);
		}
	}
	
	protected void handleIfcRelDefinesByProperties(Individual owlRelDefinesByProperties, 
			EIfcreldefinesbyproperties ifcRelDefinesByProperties) throws FactoryException {
		try {
			EIfcpropertydefinition propDef = sourceModel.getRelatingPropertyDefinition(ifcRelDefinesByProperties);
			String name = propDef.getName(propDef);
			if(PropertyDefinitionNames.isDefined(name)) {
				Individual owlPropertyDef = createIndividual(sourceModel.getGlobalId(propDef));
				
				ObjectProperty owlRelatingPropertyDefinition = BIMOntoVocabulary.RELATING_PROPERTY_DEFINITION;		
				this.ontologyModel.addRelation(owlRelDefinesByProperties, owlRelatingPropertyDefinition, owlPropertyDef);
				
				Set<EIfcobject> ifcObjects = sourceModel.getRelatedObjects(ifcRelDefinesByProperties);		
				ObjectProperty owlRelatedObjects = BIMOntoVocabulary.RELATED_OBJECTS;
				
				for(EIfcobject ifcObject : ifcObjects) {
					Individual owlObject = createIndividual(sourceModel.getGlobalId(ifcObject));
					this.ontologyModel.addRelation(owlRelDefinesByProperties, owlRelatedObjects, owlObject);	
				}
			}
		} catch (IfcException e) {
			throw new FactoryException(e);
		} catch (SdaiException e) {
			throw new FactoryException(e);
		}
	}
	
	public Individual createIfcRelDefinesByProperties(Collection<Individual> owlObjects, 
			Individual owlPropertyDef) throws FactoryException {		
		OntClass ontClass = BIMOntoVocabulary.IFC_REL_DEFINES_BY_PROPERTIES;
		String uuid = UUID.randomUUID().toString();
		Individual owlRelDefinesByProperties = createIndividualByElementId(ontClass, uuid);

//		add globalId		
		addGlobalUniqueId(uuid, owlRelDefinesByProperties);		
		
		ObjectProperty owlRelatingPropertyDefinition = BIMOntoVocabulary.RELATING_PROPERTY_DEFINITION;		
		this.ontologyModel.addRelation(owlRelDefinesByProperties, owlRelatingPropertyDefinition, owlPropertyDef);
			
		ObjectProperty owlRelatedObjects = BIMOntoVocabulary.RELATED_OBJECTS;
		
		for(Individual owlObject : owlObjects) {
			this.ontologyModel.addRelation(owlRelDefinesByProperties, owlRelatedObjects, owlObject);	
		}
		return owlRelDefinesByProperties;
	}
	
	protected void addGlobalUniqueId(String guid, Individual ind) throws FactoryException {
		if(StringUtils.isEmpty(guid)) throw new NullPointerException("The GlobalId of individual: "+ind+" is null");
		OntClass globallyUniqueIdClass = BIMOntoVocabulary.IFC_GLOBALLY_UNIQUE_ID;
		Individual globallyUniqueIdInd = createIndividualByElementId(globallyUniqueIdClass, UUID.randomUUID().toString());		

		ObjectProperty globalIdProperty = BIMOntoVocabulary.GLOBAL_ID;
		if(globalIdProperty != null){					
			DatatypeProperty stringValueProperty = BIMOntoVocabulary.STRING_VALUE;
			if(stringValueProperty != null) {
				globallyUniqueIdInd.addLiteral(stringValueProperty, guid);				
				ind.addProperty(globalIdProperty, globallyUniqueIdInd);
				LOG.trace("Added GlobalId: {} to individual: {}", new Object[]{guid, ind});
			}
		}
	}
	
	protected void addName(String name, Individual ind) throws FactoryException {
		if(StringUtils.isEmpty(name)) return;
		OntClass ifcLabelClass = BIMOntoVocabulary.IFC_LABEL;
		Individual ifcLabelInd = createIndividualByElementId(ifcLabelClass, UUID.randomUUID().toString());

		ObjectProperty nameProperty = BIMOntoVocabulary.NAME;
		if(nameProperty != null){					
			DatatypeProperty stringValueProperty = BIMOntoVocabulary.STRING_VALUE;
			if(stringValueProperty != null) {
				ifcLabelInd.addLiteral(stringValueProperty, name);
				ind.addProperty(nameProperty, ifcLabelInd);
				LOG.trace("Added name: {} to individual: {}", new Object[]{name, ind});
			}
		}
	}
	
	protected void addLongName(String longName, Individual ind) throws FactoryException {
		if(StringUtils.isEmpty(longName)) return;
		OntClass ifcLabelClass = BIMOntoVocabulary.IFC_LABEL;
		Individual ifcLabelInd = createIndividualByElementId(ifcLabelClass, UUID.randomUUID().toString());

		ObjectProperty longNameProperty = BIMOntoVocabulary.LONG_NAME;
		if(longNameProperty != null){					
			DatatypeProperty stringValueProperty = BIMOntoVocabulary.STRING_VALUE;
			if(stringValueProperty != null) {
				ifcLabelInd.addLiteral(stringValueProperty, longName);
				ind.addProperty(longNameProperty, ifcLabelInd);
				LOG.trace("Added LongName: {} to individual: {}", new Object[]{longName, ind});
			}
		}
	}
	
	protected void addDescription(String description, Individual ind) throws FactoryException {
		if(StringUtils.isEmpty(description)) return;
		OntClass ifcTextClass = BIMOntoVocabulary.IFC_TEXT;
		Individual ifcTextInd = createIndividualByElementId(ifcTextClass, UUID.randomUUID().toString());
	
		ObjectProperty descriptionProperty = BIMOntoVocabulary.DESCRIPTION;
		if(descriptionProperty != null){					
			DatatypeProperty stringValueProperty = BIMOntoVocabulary.STRING_VALUE;
			if(stringValueProperty != null) {
				ifcTextInd.addLiteral(stringValueProperty, description);			
				ind.addProperty(descriptionProperty, ifcTextInd);
				LOG.trace("Added Description {} to individual {}",
						new Object[]{description, ind});
			}
		}
	}
	
	protected void addInternalOrExternalBoundary(Integer value, Individual owlSpaceBoundary) {
		if(value == null) return;
		DatatypeProperty internalOrExternalBoundaryProperty = BIMOntoVocabulary.IFC_INTERNAL_OR_EXTERNAL_ENUM;
		String enumValue = EIfcinternalorexternalenum.toString(value);
		if(internalOrExternalBoundaryProperty != null && enumValue != null){					
			owlSpaceBoundary.addProperty(internalOrExternalBoundaryProperty, enumValue);
			LOG.trace("Added InternalOrExternal: {} to individual: {}", 
					new Object[]{enumValue, owlSpaceBoundary});
		}
	}
	
	protected void addPhysicalOrVirtualBoundary(Integer value, Individual owlSpaceBoundary) {
		if(value == null) return;
		DatatypeProperty physicalOrVirtualBoundaryProperty = BIMOntoVocabulary.IFC_PHYSICAL_OR_VIRTUAL_ENUM;
		String enumValue = EIfcphysicalorvirtualenum.toString(value);
		if(physicalOrVirtualBoundaryProperty != null && enumValue != null){					
			owlSpaceBoundary.addProperty(physicalOrVirtualBoundaryProperty, enumValue);
			LOG.trace("Added PhysicalOrVirtual {} to individual {}",
					new Object[]{enumValue, owlSpaceBoundary});
		}
	}
	
	private Individual handleGrossArea(EIfcphysicalquantity grossAreaQuantity) throws FactoryException {
		if(!(grossAreaQuantity instanceof EIfcquantityarea)) throw new IllegalArgumentException("Quantity is no IfcQuantityArea");
		try {	
			EIfcquantityarea ifcQuantityArea = (EIfcquantityarea) grossAreaQuantity; 
			
			String name = ifcQuantityArea.getName(ifcQuantityArea);
			String description = null;
			if(ifcQuantityArea.testDescription(ifcQuantityArea)) {
				description = ifcQuantityArea.getDescription(ifcQuantityArea);
			}
			double area = ifcQuantityArea.getAreavalue(ifcQuantityArea);
			
			Individual owlQuantityArea = createAreaQuantity(name, description, area);
			return owlQuantityArea;
		} catch(SdaiException se) {
			throw new FactoryException(se);
		}
	}
	
	public Individual createAreaQuantity(String name, String description, double area) throws FactoryException {
		if(name != null && area > 0.0){
			OntClass ifcQuantityAreaClass = BIMOntoVocabulary.IFC_QUANTITY_AREA;
			Individual owlQuantityArea = createIndividualByElementId(ifcQuantityAreaClass, UUID.randomUUID().toString());
			ObjectProperty areaValueObjectProperty = BIMOntoVocabulary.AREA_VALUE;	
			
			//	add name
			addName(name, owlQuantityArea);			
			//	add description
			addDescription(description, owlQuantityArea);
			
			OntClass ifcAreaMeasureClass = BIMOntoVocabulary.IFC_AREA_MEASURE;
			Individual owlAreaMeasure = createIndividualByElementId(ifcAreaMeasureClass, UUID.randomUUID().toString());

			DatatypeProperty floatValueProperty = BIMOntoVocabulary.FLOAT_VALUE;
			if(floatValueProperty != null) {
				owlAreaMeasure.addLiteral(floatValueProperty, area);
				owlQuantityArea.addProperty(areaValueObjectProperty, owlAreaMeasure);
			}
			return owlQuantityArea;
		}
		return null;
	}
	
	private Individual handleGrossVolume(EIfcphysicalquantity grossVolumeQuantity) throws FactoryException {
		if(!(grossVolumeQuantity instanceof EIfcquantityvolume)) throw new IllegalArgumentException("Quantity is no IfcQuantityVolume");
		try {
			EIfcquantityvolume ifcQuantityVolume = (EIfcquantityvolume) grossVolumeQuantity; 
			
			String name = ifcQuantityVolume.getName(ifcQuantityVolume);
			String description = null;
			if(ifcQuantityVolume.testDescription(ifcQuantityVolume)) {
				description = ifcQuantityVolume.getDescription(ifcQuantityVolume);
			}
			double volume = ifcQuantityVolume.getVolumevalue(ifcQuantityVolume);
			
			Individual owlQuantityVolume = createVolumeQuantity(name, description, volume);
			return owlQuantityVolume;
		} catch(SdaiException se) {
			throw new FactoryException(se);
		}
	}
	
	public Individual createVolumeQuantity(String name, String description, double volume) throws FactoryException {
		if(name != null && volume > 0.0) {
			OntClass ifcQuantityVolumeClass = BIMOntoVocabulary.IFC_QUANTITY_VOLUME;
			Individual owlQuantityVolume = createIndividualByElementId(ifcQuantityVolumeClass, UUID.randomUUID().toString());
			ObjectProperty volumeValueObjectProperty = BIMOntoVocabulary.VOLUME_VALUE;	
		
			//	add name
			addName(name, owlQuantityVolume);
			//	add description
			addDescription(description, owlQuantityVolume);
			
			OntClass ifcVolumeMeasureClass = BIMOntoVocabulary.IFC_VOLUME_MEASURE;
			Individual owlVolumeMeasure = createIndividualByElementId(ifcVolumeMeasureClass, UUID.randomUUID().toString());
			
			DatatypeProperty floatValueProperty = BIMOntoVocabulary.FLOAT_VALUE;
			if(floatValueProperty != null) {
				owlVolumeMeasure.addLiteral(floatValueProperty, volume);				
				owlQuantityVolume.addProperty(volumeValueObjectProperty, owlVolumeMeasure);
			}
			return owlQuantityVolume;
		}
		return null;
	}
	
	private Individual handleFinishCeilingHigh(EIfcphysicalquantity finishCeilingHighQuantity) throws FactoryException {
		try {
			if(!(finishCeilingHighQuantity instanceof EIfcquantitylength)) throw new IllegalArgumentException("Quantity is no IfcQuantityLength");
			EIfcquantitylength ifcQuantityFinishCeilingHighLength = (EIfcquantitylength) finishCeilingHighQuantity; 
			
			OntClass ifcQuantityLengthClass = BIMOntoVocabulary.IFC_QUANTITY_LENGTH;
			Individual owlQuantityFinishCeilingHighLength = createIndividualByElementId(ifcQuantityLengthClass, UUID.randomUUID().toString());
			ObjectProperty highLengthValueObjectProperty = BIMOntoVocabulary.LENGTH_VALUE;	
			
			//	add name
			if(ifcQuantityFinishCeilingHighLength.testName(ifcQuantityFinishCeilingHighLength)) {
				addName(ifcQuantityFinishCeilingHighLength.getName(ifcQuantityFinishCeilingHighLength), owlQuantityFinishCeilingHighLength);
			}
			//	add description
			if(ifcQuantityFinishCeilingHighLength.testDescription(ifcQuantityFinishCeilingHighLength)) {
				addDescription(ifcQuantityFinishCeilingHighLength.getDescription(ifcQuantityFinishCeilingHighLength), owlQuantityFinishCeilingHighLength);
			}
			
			OntClass ifcLengthMeasureClass = BIMOntoVocabulary.IFC_LENGTH_MEASURE;
			Individual owlLengthMeasure = createIndividualByElementId(ifcLengthMeasureClass, UUID.randomUUID().toString());
			
			DatatypeProperty floatValueProperty = BIMOntoVocabulary.FLOAT_VALUE;
			if(floatValueProperty != null) {
				if(ifcQuantityFinishCeilingHighLength.testLengthvalue(ifcQuantityFinishCeilingHighLength)) {
					double lengthValue = ifcQuantityFinishCeilingHighLength.getLengthvalue(ifcQuantityFinishCeilingHighLength);
					owlLengthMeasure.addLiteral(floatValueProperty, lengthValue);
					
					owlQuantityFinishCeilingHighLength.addProperty(highLengthValueObjectProperty, owlLengthMeasure);	
				}
			}
			return owlQuantityFinishCeilingHighLength;
		} catch(SdaiException se) {
			throw new FactoryException(se);
		}
	}
	
	private Individual handleCrossSectionArea(EIfcphysicalquantity crossSectionArea) throws FactoryException {
		try {
			if(!(crossSectionArea instanceof EIfcquantityarea)) throw new IllegalArgumentException("Quantity is no IfcQuantityArea");
			EIfcquantityarea ifcQuantityCrossSectionArea = (EIfcquantityarea) crossSectionArea; 
			
			OntClass ifcQuantityAreaClass = BIMOntoVocabulary.IFC_QUANTITY_AREA;
			Individual owlQuantityCrossSectionArea = createIndividualByElementId(ifcQuantityAreaClass, UUID.randomUUID().toString());
			ObjectProperty areaValueObjectProperty = BIMOntoVocabulary.AREA_VALUE;	
			
			//	add name
			if(ifcQuantityCrossSectionArea.testName(ifcQuantityCrossSectionArea)) {
				addName(ifcQuantityCrossSectionArea.getName(ifcQuantityCrossSectionArea), owlQuantityCrossSectionArea);
			}
			//	add description
			if(ifcQuantityCrossSectionArea.testDescription(ifcQuantityCrossSectionArea)) {
				addDescription(ifcQuantityCrossSectionArea.getDescription(ifcQuantityCrossSectionArea), owlQuantityCrossSectionArea);
			}
			
			OntClass ifcAreaMeasureClass = BIMOntoVocabulary.IFC_AREA_MEASURE;
			Individual owlAreaMeasure = createIndividualByElementId(ifcAreaMeasureClass, UUID.randomUUID().toString());
			
			DatatypeProperty floatValueProperty = BIMOntoVocabulary.FLOAT_VALUE;
			if(floatValueProperty != null) {
				if(ifcQuantityCrossSectionArea.testAreavalue(ifcQuantityCrossSectionArea)) {
					double areaValue = ifcQuantityCrossSectionArea.getAreavalue(ifcQuantityCrossSectionArea);
					owlAreaMeasure.addLiteral(floatValueProperty, areaValue);
					
					owlQuantityCrossSectionArea.addProperty(areaValueObjectProperty, owlAreaMeasure);	
				}
			}
			return owlQuantityCrossSectionArea;
		} catch(SdaiException se) {
			throw new FactoryException(se);
		}
	}
	
	protected void handleIfcElementQuantity(EIfcelementquantity quantity, Individual indElementQuantity) throws FactoryException {
		try {			
			ObjectProperty quantitiesObjectProperty = BIMOntoVocabulary.QUANTITIES;
			
			if(quantity.testQuantities(quantity) && quantitiesObjectProperty != null) {		
				// GrossArea
				EIfcphysicalquantity grossAreaQuantity = sourceModel.getQuantity(quantity, Quantities.GrossSideArea);
				if(grossAreaQuantity != null) {
					Individual owlQuantityArea = handleGrossArea(grossAreaQuantity);
					if(owlQuantityArea != null) indElementQuantity.addProperty(quantitiesObjectProperty, owlQuantityArea);
				}
				
				// CrossSectionArea
				EIfcphysicalquantity crossSectionArea = sourceModel.getQuantity(quantity, Quantities.CrossSectionArea);
				if(crossSectionArea != null) {
					Individual owlQuantityCrossSectionArea = handleCrossSectionArea(crossSectionArea);
					if(owlQuantityCrossSectionArea != null)	indElementQuantity.addProperty(quantitiesObjectProperty, owlQuantityCrossSectionArea);
				}
				
				// GrossVolume
				EIfcphysicalquantity grossVolumeQuantity = sourceModel.getQuantity(quantity, Quantities.GrossVolume);		
				if(grossVolumeQuantity != null) {
					Individual owlQuantityVolume = handleGrossVolume(grossVolumeQuantity);
					if(owlQuantityVolume != null) indElementQuantity.addProperty(quantitiesObjectProperty, owlQuantityVolume);
				}
				
				// FinishCeilingHigh
				EIfcphysicalquantity finishCeilingHighQuantity = sourceModel.getQuantity(quantity, Quantities.FinishCeilingHigh);
				if(finishCeilingHighQuantity != null) {
					Individual owlLengthMeasure = handleFinishCeilingHigh(finishCeilingHighQuantity);
					if(owlLengthMeasure != null) indElementQuantity.addProperty(quantitiesObjectProperty, owlLengthMeasure);
				}
			}
		} catch(SdaiException se) {
			throw new FactoryException(se);
		} catch (IfcException e) {
			throw new FactoryException(e);
		}
	}

	public Individual createIfcBuilding(String buildingGuid)
			throws FactoryException {
		return createIndividual(buildingGuid);
	}

	public void createIfcBuildingStoreys(EIfcbuilding building)
			throws IfcException, FactoryException {
		EIfcbuildingstorey[] storeys = sourceModel.getBuildingStoreys(building);
		if(storeys.length < 1) throw new IllegalArgumentException("No building storeys in the model");
		for(EIfcbuildingstorey storey : storeys) {
			String storeyGuid = sourceModel.getGlobalId(storey);
			createIndividual(storeyGuid);
		}
	}

	public void createIfcSite()
			throws IfcException, FactoryException {
		EIfcsite[] ifcSites = sourceModel.getIfcEntitiesOf(EIfcsite.class);
		if(ifcSites.length > 0) {
			String ifcSiteGuid = sourceModel.getGlobalId(ifcSites[0]);
			createIndividual(ifcSiteGuid);
		} 
		else {
//			throw new IllegalArgumentException("No IfcSite in the model");
			LOG.warn("No IfcSite in the model");
		}
	}

	/**
	 * Creates building and virtual elements, adds the direct attributes (like GlobalId, Name etc.) and creates relations to property definitions
	 * (only including properties given from IFC).
	 * @param elementGuids
	 * @param ifcModel
	 * @throws IfcException
	 * @throws FactoryException
	 */
	public void createIfcElements(Set<String> elementGuids) throws IfcException, FactoryException {
		try {
			LOG.trace("Analyse elements: {}", 
					new Object[]{elementGuids});
			for(String buildingElementGuid : elementGuids) {
				EIfcelement ifcElement = sourceModel.getIfcEntity(buildingElementGuid, EIfcelement.class);	
				if(ifcElement == null) throw new NullPointerException("GlobalId: "+buildingElementGuid+" is no IfcElement");
				
				// create OWL IfcElement
				createIndividual(buildingElementGuid);				
				// create relations to properties and quantities
				createRelationsToIfcPropertyDefinitions(ifcElement);
			}
		} catch(IfcException e) {
			throw new FactoryException(e);
		}
	}

	public void createIfcSpaces(List<EIfcspace> spaceList) throws FactoryException {
		try {			
			LOG.trace("Analyse rooms: {}", 
					new Object[]{spaceList});
			for(EIfcspace ifcSpace : spaceList) {
				String spaceGuid = sourceModel.getGlobalId(ifcSpace);
				// create OWL IfcSpace
				Individual spaceInd = createIndividual(spaceGuid);
				
				Set<EIfcelement> elements = sourceModel.getBuildingElementsInZone(spaceGuid);
				Set<String> buildingElementGuids = sourceModel.getGlobalIds(elements);
				// create building elements
				createIfcElements(buildingElementGuids);
					
				// create relations to IfcSpace
				createRelationsFromIfcSpaceToBuildingElements(ifcSpace, buildingElementGuids);
				
				// create relations to properties and quantities
				createRelationsToIfcPropertyDefinitions(ifcSpace);
			}
		} catch (SdaiException e) {
			throw new FactoryException(e);
		} catch (IfcException e) {
			throw new FactoryException(e);
		}
	}

	public void createRelationsToIfcPropertyDefinitions(EIfcobject ifcObject) throws FactoryException {
		try {
			List<EIfcreldefinesbyproperties> relDefinesByProperties = sourceModel.getRelationsToPropertySetDefinitions(ifcObject);
			LOG.trace("Create relations: {} to object: {}", 
					new Object[]{relDefinesByProperties, ifcObject});
			for(EIfcreldefinesbyproperties relDefinesbyproperty : relDefinesByProperties) {
				String relDefinesByPropertiesGuid = sourceModel.getGlobalId(relDefinesbyproperty);
				createIndividual(relDefinesByPropertiesGuid);
			}
		} catch (IfcException e) {
			throw new FactoryException(e);
		}
	}
	
	public void createRelationsFromObjectsToAggregates() throws FactoryException {
		try {
			EIfcrelaggregates[] relAggregates = sourceModel.getBasicModel().getEntitiesOf(EIfcrelaggregates.class);
			for(EIfcrelaggregates relAggregate : relAggregates) {
				String guid = sourceModel.getGlobalId(relAggregate);
				createIndividual(guid);
			}
		} catch (SdaiException e) {
			throw new FactoryException(e);
		} catch (IfcException e) {
			throw new FactoryException(e);
		}
	}

	/**
	 * Includes IfcRelSpaceBoundary
	 * @param ifcSpace
	 * @param buildingElementGuids
	 * @return
	 * @throws FactoryException
	 */
	public List<String> createRelationsFromIfcSpaceToBuildingElements(EIfcspace ifcSpace, 
			Set<String> buildingElementGuids) throws FactoryException {
		try {
			LOG.trace("Create relations of building elements: {} to space: {}", 
					new Object[]{buildingElementGuids, ifcSpace});
			// create IfcRelSpaceBoundary individuals
			List<String> ifcRelSpaceBoundaryGuids = new ArrayList<String>();
			for(String buildingElementGuid : buildingElementGuids) {
				EIfcelement element = sourceModel.getIfcEntity(buildingElementGuid, EIfcelement.class);
				EIfcrelspaceboundary[] spaceBoundaries = sourceModel.getSpaceBoundariesBetweenSpaceAndElement(ifcSpace, element);
				for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
					String sbGuid = spaceBoundary.getGlobalid(spaceBoundary);
					createIndividual(sbGuid);
					ifcRelSpaceBoundaryGuids.add(sbGuid);
				}
			}
			
			// create IfcRelDefinesByProperties
			return ifcRelSpaceBoundaryGuids;
		} catch(SdaiException e) {
			throw new FactoryException(e);
		} catch(IfcException e) {
			throw new FactoryException(e);
		}
	}
	
//	protected void handleIfcElementQuantity(Ifc2x3DataModelProxy ifcModel, EIfcelementquantity quantity, Individual indElementQuantity) throws FactoryException {
//		try {
//			ObjectProperty quantitiesObjectProperty = IFC2X3_TC1Vocabulary.QUANTITIES;
//			if(quantity.testQuantities(quantity) && quantitiesObjectProperty != null) {	
//				OntClass ifcQuantityLengthClass = IFC2X3_TC1Vocabulary.IFC_QUANTITY_LENGTH;
//				Individual ifcQuantityLengthInd = createIndividual(ifcQuantityLengthClass, ifcModel, UUID.randomUUID().toString());
//				AIfcphysicalquantity physicalQuantities = quantity.getQuantities(quantity);
//				for(EIfcphysicalquantity physicalQuantity : new IterableAggregate<EIfcphysicalquantity>(physicalQuantities)) {
//					if(physicalQuantity instanceof EIfcquantitylength) {
//						EIfcquantitylength quantityLength = (EIfcquantitylength) physicalQuantity;
//						ObjectProperty lengthValueObjectProperty = IFC2X3_TC1Vocabulary.LENGTH_VALUE;					
//						OntClass ifcLengthMeasureClass = IFC2X3_TC1Vocabulary.IFC_LENGTH_MEASURE;
//						Individual ifcLengthMeasureInd = createIndividual(ifcLengthMeasureClass, ifcModel, UUID.randomUUID().toString());
////						add name
//						if(quantityLength.testName(quantityLength)) {
//							addName(ifcModel, quantityLength.getName(quantityLength), ifcLengthMeasureInd);
//						}
////						add description
//						if(quantityLength.testDescription(quantityLength)) {
//							addDescription(ifcModel, quantityLength.getDescription(quantityLength), ifcLengthMeasureInd);
//						}
//						DatatypeProperty floatValueProperty = IFC2X3_TC1Vocabulary.FLOAT_VALUE;
//						if(floatValueProperty != null) {
//							if(quantityLength.testLengthvalue(quantityLength)) {
//								double lengthValue = quantityLength.getLengthvalue(quantityLength);
//								ifcLengthMeasureInd.addLiteral(floatValueProperty, lengthValue);
//								
//								ifcQuantityLengthInd.addProperty(lengthValueObjectProperty, ifcLengthMeasureInd);
//								indElementQuantity.addProperty(quantitiesObjectProperty, ifcQuantityLengthInd);
//							}
//						}
//					} else if(physicalQuantity instanceof EIfcquantityarea) {
//						EIfcquantityarea quantityArea = (EIfcquantityarea) physicalQuantity;
//						ObjectProperty areaValueObjectProperty = IFC2X3_TC1Vocabulary.AREA_VALUE;
//						OntClass ifcAreaMeasureClass = IFC2X3_TC1Vocabulary.IFC_AREA_MEASURE;
//						Individual ifcAreaMeasureInd = createIndividual(ifcAreaMeasureClass, ifcModel, UUID.randomUUID().toString());
////						add name
//						if(quantityArea.testName(quantityArea)) {
//							addName(ifcModel, quantityArea.getName(quantityArea), ifcAreaMeasureInd);
//						}
////						add description
//						if(quantityArea.testDescription(quantityArea)) {
//							addDescription(ifcModel, quantityArea.getDescription(quantityArea), ifcAreaMeasureInd);
//						}
//						DatatypeProperty floatValueProperty = IFC2X3_TC1Vocabulary.FLOAT_VALUE;
//						if(floatValueProperty != null) {
//							if(quantityArea.testAreavalue(quantityArea)) {
//								double areaValue = quantityArea.getAreavalue(quantityArea);
//								ifcAreaMeasureInd.addLiteral(floatValueProperty, areaValue);
//								
//								ifcQuantityLengthInd.addProperty(areaValueObjectProperty, ifcAreaMeasureInd);
//								indElementQuantity.addProperty(quantitiesObjectProperty, ifcQuantityLengthInd);
//							}
//						}
//					} else if(physicalQuantity instanceof EIfcquantityvolume) {
//						EIfcquantityvolume quantityVolume = (EIfcquantityvolume) physicalQuantity;
//						ObjectProperty volumeValueObjectProperty = IFC2X3_TC1Vocabulary.VOLUME_VALUE;
//						OntClass ifcVolumeMeasureClass = IFC2X3_TC1Vocabulary.IFC_VOLUME_MEASURE;
//						Individual ifcVolumeMeasureInd = createIndividual(ifcVolumeMeasureClass, ifcModel, UUID.randomUUID().toString());
////						add name
//						if(quantityVolume.testName(quantityVolume)) {
//							addName(ifcModel, quantityVolume.getName(quantityVolume), ifcVolumeMeasureInd);
//						}
////						add description
//						if(quantityVolume.testDescription(quantityVolume)) {
//							addDescription(ifcModel, quantityVolume.getDescription(quantityVolume), ifcVolumeMeasureInd);
//						}
//						DatatypeProperty floatValueProperty = IFC2X3_TC1Vocabulary.FLOAT_VALUE;
//						if(floatValueProperty != null) {
//							if(quantityVolume.testVolumevalue(quantityVolume)) {
//								double volumeValue = quantityVolume.getVolumevalue(quantityVolume);
//								ifcVolumeMeasureInd.addLiteral(floatValueProperty, volumeValue);
//								
//								ifcQuantityLengthInd.addProperty(volumeValueObjectProperty, ifcVolumeMeasureInd);
//								indElementQuantity.addProperty(quantitiesObjectProperty, ifcQuantityLengthInd);
//							}
//						}
//					}
//
//				}
//			}
//		} catch(SdaiException se) {
//			throw new FactoryException(se);
//		}
//	}
}
