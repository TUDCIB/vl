/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.tudresden.bau.cib.simmatrix.simmatrixFactory
 * @model kind="package"
 *        extendedMetaData="qualified='false'"
 * @generated
 */
public interface simmatrixPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "simmatrix";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "file:/D:/Nutzer/ken/Workspaces/VirtualLaboratory/SimulationMatrix/schema/SimMatrix_ISES_PREFINAL.xsd";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "simmatrix";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	simmatrixPackage eINSTANCE = de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 0;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Assignment Groups</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ASSIGNMENT_GROUPS = 3;

	/**
	 * The feature id for the '<em><b>BIMREF</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BIMREF = 4;

	/**
	 * The feature id for the '<em><b>Combination</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMBINATION = 5;

	/**
	 * The feature id for the '<em><b>Combinations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMBINATIONS = 6;

	/**
	 * The feature id for the '<em><b>Constant Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONSTANT_TYPE_VARIANT = 7;

	/**
	 * The feature id for the '<em><b>Construction Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONSTRUCTION_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Construction Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIABLES = 9;

	/**
	 * The feature id for the '<em><b>Construction Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIANT = 10;

	/**
	 * The feature id for the '<em><b>Door Type Variables</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DOOR_TYPE_VARIABLES = 11;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ELEMENT = 12;

	/**
	 * The feature id for the '<em><b>Element Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ELEMENT_GROUP = 13;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ELEMENTS = 14;

	/**
	 * The feature id for the '<em><b>Elevation Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ELEVATION_VARIABLES = 15;

	/**
	 * The feature id for the '<em><b>Elevation Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ELEVATION_VARIANT = 16;

	/**
	 * The feature id for the '<em><b>Layer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LAYER = 17;

	/**
	 * The feature id for the '<em><b>Material Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MATERIAL_TYPE = 18;

	/**
	 * The feature id for the '<em><b>Material Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MATERIAL_TYPE_VARIABLES = 19;

	/**
	 * The feature id for the '<em><b>Material Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MATERIAL_TYPE_VARIANT = 20;

	/**
	 * The feature id for the '<em><b>Occupancy Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OCCUPANCY_TYPE = 21;

	/**
	 * The feature id for the '<em><b>Occupancy Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OCCUPANCY_TYPE_VARIANT = 22;

	/**
	 * The feature id for the '<em><b>Orientation Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ORIENTATION_VARIABLES = 23;

	/**
	 * The feature id for the '<em><b>Orientation Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ORIENTATION_VARIANT = 24;

	/**
	 * The feature id for the '<em><b>Schedule Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SCHEDULE_TYPE = 25;

	/**
	 * The feature id for the '<em><b>Schedule Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SCHEDULE_TYPE_VARIANT = 26;

	/**
	 * The feature id for the '<em><b>Simulation Matrix</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SIMULATION_MATRIX = 27;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SPACE = 28;

	/**
	 * The feature id for the '<em><b>Space Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SPACE_GROUP = 29;

	/**
	 * The feature id for the '<em><b>Spaces</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SPACES = 30;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TARGET = 31;

	/**
	 * The feature id for the '<em><b>Target List</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TARGET_LIST = 32;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TARGETS = 33;

	/**
	 * The feature id for the '<em><b>Usage Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__USAGE_TYPE_VARIABLES = 34;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VARIABLES = 35;

	/**
	 * The feature id for the '<em><b>Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VARIANT = 36;

	/**
	 * The feature id for the '<em><b>Weather Data Series Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WEATHER_DATA_SERIES_VARIANT = 37;

	/**
	 * The feature id for the '<em><b>Weather Data Set Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WEATHER_DATA_SET_VARIANT = 38;

	/**
	 * The feature id for the '<em><b>Weather Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WEATHER_VARIABLES = 39;

	/**
	 * The feature id for the '<em><b>Window Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WINDOW_TYPE = 40;

	/**
	 * The feature id for the '<em><b>Window Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WINDOW_TYPE_VARIABLES = 41;

	/**
	 * The feature id for the '<em><b>Window Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WINDOW_TYPE_VARIANT = 42;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 43;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TAssignmentGroupsImpl <em>TAssignment Groups</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TAssignmentGroupsImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTAssignmentGroups()
	 * @generated
	 */
	int TASSIGNMENT_GROUPS = 1;

	/**
	 * The feature id for the '<em><b>Spaces</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSIGNMENT_GROUPS__SPACES = 0;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSIGNMENT_GROUPS__ELEMENTS = 1;

	/**
	 * The number of structural features of the '<em>TAssignment Groups</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSIGNMENT_GROUPS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TBIMGroupImpl <em>TBIM Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TBIMGroupImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTBIMGroup()
	 * @generated
	 */
	int TBIM_GROUP = 2;

	/**
	 * The feature id for the '<em><b>BIMREF</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBIM_GROUP__BIMREF = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBIM_GROUP__ID = 1;

	/**
	 * The number of structural features of the '<em>TBIM Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBIM_GROUP_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TCombinationImpl <em>TCombination</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TCombinationImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTCombination()
	 * @generated
	 */
	int TCOMBINATION = 3;

	/**
	 * The feature id for the '<em><b>Variant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMBINATION__VARIANT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMBINATION__ID = 1;

	/**
	 * The number of structural features of the '<em>TCombination</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMBINATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TCombinationsImpl <em>TCombinations</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TCombinationsImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTCombinations()
	 * @generated
	 */
	int TCOMBINATIONS = 4;

	/**
	 * The feature id for the '<em><b>Combination</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMBINATIONS__COMBINATION = 0;

	/**
	 * The number of structural features of the '<em>TCombinations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMBINATIONS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TConstantTypeVariantImpl <em>TConstant Type Variant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TConstantTypeVariantImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTConstantTypeVariant()
	 * @generated
	 */
	int TCONSTANT_TYPE_VARIANT = 5;

	/**
	 * The feature id for the '<em><b>Heating Set Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT = 0;

	/**
	 * The feature id for the '<em><b>Cooling Set Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT = 1;

	/**
	 * The feature id for the '<em><b>Person Load</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTANT_TYPE_VARIANT__PERSON_LOAD = 2;

	/**
	 * The feature id for the '<em><b>Equipment Load</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD = 3;

	/**
	 * The feature id for the '<em><b>Shading</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTANT_TYPE_VARIANT__SHADING = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTANT_TYPE_VARIANT__ID = 5;

	/**
	 * The number of structural features of the '<em>TConstant Type Variant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTANT_TYPE_VARIANT_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TConstructionTypeImpl <em>TConstruction Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TConstructionTypeImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTConstructionType()
	 * @generated
	 */
	int TCONSTRUCTION_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Construction Type Variant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTRUCTION_TYPE__CONSTRUCTION_TYPE_VARIANT = 0;

	/**
	 * The feature id for the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTRUCTION_TYPE__ORIENTATION = 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTRUCTION_TYPE__SOURCE = 2;

	/**
	 * The number of structural features of the '<em>TConstruction Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTRUCTION_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TConstructionTypesImpl <em>TConstruction Types</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TConstructionTypesImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTConstructionTypes()
	 * @generated
	 */
	int TCONSTRUCTION_TYPES = 7;

	/**
	 * The feature id for the '<em><b>Construction Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTRUCTION_TYPES__CONSTRUCTION_TYPE = 0;

	/**
	 * The number of structural features of the '<em>TConstruction Types</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTRUCTION_TYPES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TConstructionTypeVariantImpl <em>TConstruction Type Variant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TConstructionTypeVariantImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTConstructionTypeVariant()
	 * @generated
	 */
	int TCONSTRUCTION_TYPE_VARIANT = 8;

	/**
	 * The feature id for the '<em><b>Layer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTRUCTION_TYPE_VARIANT__LAYER = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTRUCTION_TYPE_VARIANT__ID = 1;

	/**
	 * The number of structural features of the '<em>TConstruction Type Variant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONSTRUCTION_TYPE_VARIANT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TElementGroupImpl <em>TElement Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TElementGroupImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTElementGroup()
	 * @generated
	 */
	int TELEMENT_GROUP = 9;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEMENT_GROUP__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEMENT_GROUP__ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEMENT_GROUP__ID = 2;

	/**
	 * The number of structural features of the '<em>TElement Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEMENT_GROUP_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TElementsImpl <em>TElements</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TElementsImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTElements()
	 * @generated
	 */
	int TELEMENTS = 10;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEMENTS__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Element Group</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEMENTS__ELEMENT_GROUP = 1;

	/**
	 * The number of structural features of the '<em>TElements</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEMENTS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TElevationImpl <em>TElevation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TElevationImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTElevation()
	 * @generated
	 */
	int TELEVATION = 11;

	/**
	 * The feature id for the '<em><b>Elevation Variant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEVATION__ELEVATION_VARIANT = 0;

	/**
	 * The number of structural features of the '<em>TElevation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEVATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TElevationVariantImpl <em>TElevation Variant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TElevationVariantImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTElevationVariant()
	 * @generated
	 */
	int TELEVATION_VARIANT = 12;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEVATION_VARIANT__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEVATION_VARIANT__ID = 1;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEVATION_VARIANT__UNIT = 2;

	/**
	 * The number of structural features of the '<em>TElevation Variant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEVATION_VARIANT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TFloatWithUnitsImpl <em>TFloat With Units</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TFloatWithUnitsImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTFloatWithUnits()
	 * @generated
	 */
	int TFLOAT_WITH_UNITS = 13;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOAT_WITH_UNITS__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOAT_WITH_UNITS__UNIT = 1;

	/**
	 * The number of structural features of the '<em>TFloat With Units</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOAT_WITH_UNITS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TLayerImpl <em>TLayer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TLayerImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTLayer()
	 * @generated
	 */
	int TLAYER = 14;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLAYER__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Thickness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLAYER__THICKNESS = 1;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLAYER__UNIT = 2;

	/**
	 * The number of structural features of the '<em>TLayer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLAYER_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeImpl <em>TMaterial Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTMaterialType()
	 * @generated
	 */
	int TMATERIAL_TYPE = 15;

	/**
	 * The feature id for the '<em><b>Material Type Variant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE__MATERIAL_TYPE_VARIANT = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE__SOURCE = 1;

	/**
	 * The number of structural features of the '<em>TMaterial Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypesImpl <em>TMaterial Types</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TMaterialTypesImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTMaterialTypes()
	 * @generated
	 */
	int TMATERIAL_TYPES = 16;

	/**
	 * The feature id for the '<em><b>Material Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPES__MATERIAL_TYPE = 0;

	/**
	 * The number of structural features of the '<em>TMaterial Types</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl <em>TMaterial Type Variant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTMaterialTypeVariant()
	 * @generated
	 */
	int TMATERIAL_TYPE_VARIANT = 17;

	/**
	 * The feature id for the '<em><b>Density</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__DENSITY = 0;

	/**
	 * The feature id for the '<em><b>Specific Heat Capacity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY = 1;

	/**
	 * The feature id for the '<em><b>Conductivity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__CONDUCTIVITY = 2;

	/**
	 * The feature id for the '<em><b>Water Vapor Diffusion Resistance Factor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR = 3;

	/**
	 * The feature id for the '<em><b>Water Absorption Capacity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY = 4;

	/**
	 * The feature id for the '<em><b>Open Porosity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__OPEN_POROSITY = 5;

	/**
	 * The feature id for the '<em><b>Effective Saturation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION = 6;

	/**
	 * The feature id for the '<em><b>Capillary Saturation Content</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT = 7;

	/**
	 * The feature id for the '<em><b>Hygroscopic Sorption</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION = 8;

	/**
	 * The feature id for the '<em><b>Thermal Conductivity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY = 9;

	/**
	 * The feature id for the '<em><b>Liquid Water Conductivity Effective Saturation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT__ID = 11;

	/**
	 * The number of structural features of the '<em>TMaterial Type Variant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMATERIAL_TYPE_VARIANT_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TOccupancyTypeImpl <em>TOccupancy Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TOccupancyTypeImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTOccupancyType()
	 * @generated
	 */
	int TOCCUPANCY_TYPE = 18;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOCCUPANCY_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Occupancy Type Variant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOCCUPANCY_TYPE__OCCUPANCY_TYPE_VARIANT = 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOCCUPANCY_TYPE__SOURCE = 2;

	/**
	 * The number of structural features of the '<em>TOccupancy Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOCCUPANCY_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TOccupancyTypeVariantImpl <em>TOccupancy Type Variant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TOccupancyTypeVariantImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTOccupancyTypeVariant()
	 * @generated
	 */
	int TOCCUPANCY_TYPE_VARIANT = 19;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOCCUPANCY_TYPE_VARIANT__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOCCUPANCY_TYPE_VARIANT__ID = 1;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOCCUPANCY_TYPE_VARIANT__PERIOD = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOCCUPANCY_TYPE_VARIANT__TYPE = 3;

	/**
	 * The number of structural features of the '<em>TOccupancy Type Variant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOCCUPANCY_TYPE_VARIANT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TOrientationImpl <em>TOrientation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TOrientationImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTOrientation()
	 * @generated
	 */
	int TORIENTATION = 20;

	/**
	 * The feature id for the '<em><b>Orientation Variant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORIENTATION__ORIENTATION_VARIANT = 0;

	/**
	 * The number of structural features of the '<em>TOrientation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORIENTATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TOrientationVariantImpl <em>TOrientation Variant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TOrientationVariantImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTOrientationVariant()
	 * @generated
	 */
	int TORIENTATION_VARIANT = 21;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORIENTATION_VARIANT__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORIENTATION_VARIANT__ID = 1;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORIENTATION_VARIANT__UNIT = 2;

	/**
	 * The number of structural features of the '<em>TOrientation Variant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORIENTATION_VARIANT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeImpl <em>TSchedule Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTScheduleType()
	 * @generated
	 */
	int TSCHEDULE_TYPE = 22;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Schedule Type Variant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE__SCHEDULE_TYPE_VARIANT = 1;

	/**
	 * The feature id for the '<em><b>Constant Type Variant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE__CONSTANT_TYPE_VARIANT = 2;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE__SOURCE = 3;

	/**
	 * The number of structural features of the '<em>TSchedule Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeVariantImpl <em>TSchedule Type Variant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeVariantImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTScheduleTypeVariant()
	 * @generated
	 */
	int TSCHEDULE_TYPE_VARIANT = 23;

	/**
	 * The feature id for the '<em><b>Heating Set Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT = 0;

	/**
	 * The feature id for the '<em><b>Cooling Set Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT = 1;

	/**
	 * The feature id for the '<em><b>Person Load</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE_VARIANT__PERSON_LOAD = 2;

	/**
	 * The feature id for the '<em><b>Equipment Load</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD = 3;

	/**
	 * The feature id for the '<em><b>Shading</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE_VARIANT__SHADING = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE_VARIANT__ID = 5;

	/**
	 * The number of structural features of the '<em>TSchedule Type Variant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCHEDULE_TYPE_VARIANT_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSetPersonLoadsImpl <em>TSet Person Loads</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TSetPersonLoadsImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSetPersonLoads()
	 * @generated
	 */
	int TSET_PERSON_LOADS = 24;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_PERSON_LOADS__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_PERSON_LOADS__PERIOD = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_PERSON_LOADS__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_PERSON_LOADS__UNIT = 3;

	/**
	 * The number of structural features of the '<em>TSet Person Loads</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_PERSON_LOADS_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSetPointImpl <em>TSet Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TSetPointImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSetPoint()
	 * @generated
	 */
	int TSET_POINT = 25;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_POINT__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_POINT__UNIT = 1;

	/**
	 * The number of structural features of the '<em>TSet Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_POINT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSetShadingImpl <em>TSet Shading</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TSetShadingImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSetShading()
	 * @generated
	 */
	int TSET_SHADING = 26;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_SHADING__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_SHADING__PERIOD = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_SHADING__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_SHADING__UNIT = 3;

	/**
	 * The number of structural features of the '<em>TSet Shading</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_SHADING_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSetTemperatureImpl <em>TSet Temperature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TSetTemperatureImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSetTemperature()
	 * @generated
	 */
	int TSET_TEMPERATURE = 27;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_TEMPERATURE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_TEMPERATURE__PERIOD = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_TEMPERATURE__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_TEMPERATURE__UNIT = 3;

	/**
	 * The number of structural features of the '<em>TSet Temperature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSET_TEMPERATURE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSimulationMatrixImpl <em>TSimulation Matrix</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TSimulationMatrixImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSimulationMatrix()
	 * @generated
	 */
	int TSIMULATION_MATRIX = 28;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIMULATION_MATRIX__TARGETS = 0;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIMULATION_MATRIX__VARIABLES = 1;

	/**
	 * The feature id for the '<em><b>Assignment Groups</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIMULATION_MATRIX__ASSIGNMENT_GROUPS = 2;

	/**
	 * The feature id for the '<em><b>Combinations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIMULATION_MATRIX__COMBINATIONS = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIMULATION_MATRIX__ID = 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIMULATION_MATRIX__TYPE = 5;

	/**
	 * The number of structural features of the '<em>TSimulation Matrix</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIMULATION_MATRIX_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSpaceImpl <em>TSpace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TSpaceImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSpace()
	 * @generated
	 */
	int TSPACE = 29;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSPACE__ID = 0;

	/**
	 * The number of structural features of the '<em>TSpace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSPACE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSpaceGroupImpl <em>TSpace Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TSpaceGroupImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSpaceGroup()
	 * @generated
	 */
	int TSPACE_GROUP = 30;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSPACE_GROUP__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSPACE_GROUP__SPACE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSPACE_GROUP__ID = 2;

	/**
	 * The number of structural features of the '<em>TSpace Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSPACE_GROUP_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSpacesImpl <em>TSpaces</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TSpacesImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSpaces()
	 * @generated
	 */
	int TSPACES = 31;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSPACES__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Space Group</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSPACES__SPACE_GROUP = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSPACES__ID = 2;

	/**
	 * The number of structural features of the '<em>TSpaces</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSPACES_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TTargetImpl <em>TTarget</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TTargetImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTTarget()
	 * @generated
	 */
	int TTARGET = 32;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTARGET__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTARGET__TYPE = 1;

	/**
	 * The number of structural features of the '<em>TTarget</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTARGET_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TTargetListImpl <em>TTarget List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TTargetListImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTTargetList()
	 * @generated
	 */
	int TTARGET_LIST = 33;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTARGET_LIST__TARGET = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTARGET_LIST__ID = 1;

	/**
	 * The number of structural features of the '<em>TTarget List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTARGET_LIST_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TTargetsImpl <em>TTargets</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TTargetsImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTTargets()
	 * @generated
	 */
	int TTARGETS = 34;

	/**
	 * The feature id for the '<em><b>Target List</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTARGETS__TARGET_LIST = 0;

	/**
	 * The number of structural features of the '<em>TTargets</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTARGETS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TUsageImpl <em>TUsage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TUsageImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTUsage()
	 * @generated
	 */
	int TUSAGE = 35;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSAGE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Occupancy Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSAGE__OCCUPANCY_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Schedule Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSAGE__SCHEDULE_TYPE = 2;

	/**
	 * The number of structural features of the '<em>TUsage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSAGE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl <em>TVariables</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTVariables()
	 * @generated
	 */
	int TVARIABLES = 36;

	/**
	 * The feature id for the '<em><b>Weather Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIABLES__WEATHER_VARIABLES = 0;

	/**
	 * The feature id for the '<em><b>Orientation Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIABLES__ORIENTATION_VARIABLES = 1;

	/**
	 * The feature id for the '<em><b>Elevation Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIABLES__ELEVATION_VARIABLES = 2;

	/**
	 * The feature id for the '<em><b>Usage Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIABLES__USAGE_TYPE_VARIABLES = 3;

	/**
	 * The feature id for the '<em><b>Construction Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIABLES__CONSTRUCTION_TYPE_VARIABLES = 4;

	/**
	 * The feature id for the '<em><b>Window Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIABLES__WINDOW_TYPE_VARIABLES = 5;

	/**
	 * The feature id for the '<em><b>Door Type Variables</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIABLES__DOOR_TYPE_VARIABLES = 6;

	/**
	 * The feature id for the '<em><b>Material Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIABLES__MATERIAL_TYPE_VARIABLES = 7;

	/**
	 * The number of structural features of the '<em>TVariables</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIABLES_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TVariantImpl <em>TVariant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TVariantImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTVariant()
	 * @generated
	 */
	int TVARIANT = 37;

	/**
	 * The feature id for the '<em><b>AREF</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIANT__AREF = 0;

	/**
	 * The feature id for the '<em><b>VREF</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIANT__VREF = 1;

	/**
	 * The number of structural features of the '<em>TVariant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TVARIANT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherImpl <em>TWeather</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TWeatherImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWeather()
	 * @generated
	 */
	int TWEATHER = 38;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Weather Data Set Variant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER__WEATHER_DATA_SET_VARIANT = 1;

	/**
	 * The feature id for the '<em><b>Weather Data Series Variant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER__WEATHER_DATA_SERIES_VARIANT = 2;

	/**
	 * The number of structural features of the '<em>TWeather</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSeriesVariantImpl <em>TWeather Data Series Variant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSeriesVariantImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWeatherDataSeriesVariant()
	 * @generated
	 */
	int TWEATHER_DATA_SERIES_VARIANT = 39;

	/**
	 * The feature id for the '<em><b>Air Temperature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER_DATA_SERIES_VARIANT__AIR_TEMPERATURE = 0;

	/**
	 * The feature id for the '<em><b>Short Wave Diffuse</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIFFUSE = 1;

	/**
	 * The feature id for the '<em><b>Short Wave Direct</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIRECT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER_DATA_SERIES_VARIANT__ID = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER_DATA_SERIES_VARIANT__TYPE = 4;

	/**
	 * The number of structural features of the '<em>TWeather Data Series Variant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER_DATA_SERIES_VARIANT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSetVariantImpl <em>TWeather Data Set Variant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSetVariantImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWeatherDataSetVariant()
	 * @generated
	 */
	int TWEATHER_DATA_SET_VARIANT = 40;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER_DATA_SET_VARIANT__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER_DATA_SET_VARIANT__ID = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER_DATA_SET_VARIANT__TYPE = 2;

	/**
	 * The number of structural features of the '<em>TWeather Data Set Variant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWEATHER_DATA_SET_VARIANT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypeImpl <em>TWindow Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TWindowTypeImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWindowType()
	 * @generated
	 */
	int TWINDOW_TYPE = 41;

	/**
	 * The feature id for the '<em><b>Window Type Variant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPE__WINDOW_TYPE_VARIANT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPE__NAME = 1;

	/**
	 * The number of structural features of the '<em>TWindow Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypesImpl <em>TWindow Types</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TWindowTypesImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWindowTypes()
	 * @generated
	 */
	int TWINDOW_TYPES = 42;

	/**
	 * The feature id for the '<em><b>Window Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPES__WINDOW_TYPE = 0;

	/**
	 * The number of structural features of the '<em>TWindow Types</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypeVariantImpl <em>TWindow Type Variant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.impl.TWindowTypeVariantImpl
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWindowTypeVariant()
	 * @generated
	 */
	int TWINDOW_TYPE_VARIANT = 43;

	/**
	 * The feature id for the '<em><b>Glass Fraction</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPE_VARIANT__GLASS_FRACTION = 0;

	/**
	 * The feature id for the '<em><b>Frame Fraction</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPE_VARIANT__FRAME_FRACTION = 1;

	/**
	 * The feature id for the '<em><b>Thermal Transmittance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE = 2;

	/**
	 * The feature id for the '<em><b>Solar Heat Gain Coefficient</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT = 3;

	/**
	 * The feature id for the '<em><b>Shading Factor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPE_VARIANT__SHADING_FACTOR = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPE_VARIANT__ID = 5;

	/**
	 * The number of structural features of the '<em>TWindow Type Variant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWINDOW_TYPE_VARIANT_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.DistanceUnit <em>Distance Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.DistanceUnit
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getDistanceUnit()
	 * @generated
	 */
	int DISTANCE_UNIT = 44;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.FileFormat <em>File Format</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.FileFormat
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getFileFormat()
	 * @generated
	 */
	int FILE_FORMAT = 45;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.Loads <em>Loads</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.Loads
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getLoads()
	 * @generated
	 */
	int LOADS = 46;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.MaterialUnit <em>Material Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.MaterialUnit
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getMaterialUnit()
	 * @generated
	 */
	int MATERIAL_UNIT = 47;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.OrientationSide <em>Orientation Side</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.OrientationSide
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getOrientationSide()
	 * @generated
	 */
	int ORIENTATION_SIDE = 48;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.OrientationUnit <em>Orientation Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.OrientationUnit
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getOrientationUnit()
	 * @generated
	 */
	int ORIENTATION_UNIT = 49;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.SetPoint <em>Set Point</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.SetPoint
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getSetPoint()
	 * @generated
	 */
	int SET_POINT = 50;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.Shading <em>Shading</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.Shading
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getShading()
	 * @generated
	 */
	int SHADING = 51;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.TargetType <em>Target Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.TargetType
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTargetType()
	 * @generated
	 */
	int TARGET_TYPE = 52;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.Temperature <em>Temperature</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.Temperature
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTemperature()
	 * @generated
	 */
	int TEMPERATURE = 53;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.TimePeriod <em>Time Period</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.TimePeriod
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTimePeriod()
	 * @generated
	 */
	int TIME_PERIOD = 54;

	/**
	 * The meta object id for the '{@link de.tudresden.bau.cib.simmatrix.WeatherTypes <em>Weather Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.WeatherTypes
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getWeatherTypes()
	 * @generated
	 */
	int WEATHER_TYPES = 55;

	/**
	 * The meta object id for the '<em>Distance Unit Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.DistanceUnit
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getDistanceUnitObject()
	 * @generated
	 */
	int DISTANCE_UNIT_OBJECT = 56;

	/**
	 * The meta object id for the '<em>File Format Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.FileFormat
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getFileFormatObject()
	 * @generated
	 */
	int FILE_FORMAT_OBJECT = 57;

	/**
	 * The meta object id for the '<em>Loads Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.Loads
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getLoadsObject()
	 * @generated
	 */
	int LOADS_OBJECT = 58;

	/**
	 * The meta object id for the '<em>Material Unit Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.MaterialUnit
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getMaterialUnitObject()
	 * @generated
	 */
	int MATERIAL_UNIT_OBJECT = 59;

	/**
	 * The meta object id for the '<em>Orientation Side Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.OrientationSide
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getOrientationSideObject()
	 * @generated
	 */
	int ORIENTATION_SIDE_OBJECT = 60;

	/**
	 * The meta object id for the '<em>Orientation Unit Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.OrientationUnit
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getOrientationUnitObject()
	 * @generated
	 */
	int ORIENTATION_UNIT_OBJECT = 61;

	/**
	 * The meta object id for the '<em>Set Point Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.SetPoint
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getSetPointObject()
	 * @generated
	 */
	int SET_POINT_OBJECT = 62;

	/**
	 * The meta object id for the '<em>Shading Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.Shading
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getShadingObject()
	 * @generated
	 */
	int SHADING_OBJECT = 63;

	/**
	 * The meta object id for the '<em>Simat Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getSimatType()
	 * @generated
	 */
	int SIMAT_TYPE = 64;

	/**
	 * The meta object id for the '<em>Target Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.TargetType
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTargetTypeObject()
	 * @generated
	 */
	int TARGET_TYPE_OBJECT = 65;

	/**
	 * The meta object id for the '<em>Temperature Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.Temperature
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTemperatureObject()
	 * @generated
	 */
	int TEMPERATURE_OBJECT = 66;

	/**
	 * The meta object id for the '<em>Time Period Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.TimePeriod
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTimePeriodObject()
	 * @generated
	 */
	int TIME_PERIOD_OBJECT = 67;

	/**
	 * The meta object id for the '<em>TString List</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTStringList()
	 * @generated
	 */
	int TSTRING_LIST = 68;

	/**
	 * The meta object id for the '<em>Weather Types Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tudresden.bau.cib.simmatrix.WeatherTypes
	 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getWeatherTypesObject()
	 * @generated
	 */
	int WEATHER_TYPES_OBJECT = 69;


	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getAssignmentGroups <em>Assignment Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Assignment Groups</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getAssignmentGroups()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_AssignmentGroups();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getBIMREF <em>BIMREF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BIMREF</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getBIMREF()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_BIMREF();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getCombination <em>Combination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Combination</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getCombination()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Combination();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getCombinations <em>Combinations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Combinations</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getCombinations()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Combinations();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstantTypeVariant <em>Constant Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Constant Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstantTypeVariant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConstantTypeVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionType <em>Construction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Construction Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConstructionType();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionTypeVariables <em>Construction Type Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Construction Type Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionTypeVariables()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConstructionTypeVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionTypeVariant <em>Construction Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Construction Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionTypeVariant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConstructionTypeVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getDoorTypeVariables <em>Door Type Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Door Type Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getDoorTypeVariables()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_DoorTypeVariables();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getElement()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Element();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElementGroup <em>Element Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Element Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getElementGroup()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ElementGroup();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Elements</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getElements()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Elements();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElevationVariables <em>Elevation Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Elevation Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getElevationVariables()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ElevationVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElevationVariant <em>Elevation Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Elevation Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getElevationVariant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ElevationVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getLayer <em>Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Layer</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getLayer()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Layer();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialType <em>Material Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Material Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MaterialType();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialTypeVariables <em>Material Type Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Material Type Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialTypeVariables()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MaterialTypeVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialTypeVariant <em>Material Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Material Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialTypeVariant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MaterialTypeVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOccupancyType <em>Occupancy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Occupancy Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getOccupancyType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_OccupancyType();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOccupancyTypeVariant <em>Occupancy Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Occupancy Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getOccupancyTypeVariant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_OccupancyTypeVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOrientationVariables <em>Orientation Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Orientation Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getOrientationVariables()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_OrientationVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOrientationVariant <em>Orientation Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Orientation Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getOrientationVariant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_OrientationVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getScheduleType <em>Schedule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Schedule Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getScheduleType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ScheduleType();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getScheduleTypeVariant <em>Schedule Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Schedule Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getScheduleTypeVariant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ScheduleTypeVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSimulationMatrix <em>Simulation Matrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Simulation Matrix</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getSimulationMatrix()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SimulationMatrix();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpace()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Space();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpaceGroup <em>Space Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Space Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpaceGroup()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SpaceGroup();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpaces <em>Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Spaces</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpaces()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Spaces();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getTarget()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Target();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getTargetList <em>Target List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target List</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getTargetList()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TargetList();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getTargets <em>Targets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Targets</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getTargets()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Targets();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getUsageTypeVariables <em>Usage Type Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Usage Type Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getUsageTypeVariables()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_UsageTypeVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getVariables()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Variables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getVariant <em>Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getVariant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Variant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherDataSeriesVariant <em>Weather Data Series Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Weather Data Series Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherDataSeriesVariant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_WeatherDataSeriesVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherDataSetVariant <em>Weather Data Set Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Weather Data Set Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherDataSetVariant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_WeatherDataSetVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherVariables <em>Weather Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Weather Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherVariables()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_WeatherVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowType <em>Window Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Window Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_WindowType();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowTypeVariables <em>Window Type Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Window Type Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowTypeVariables()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_WindowTypeVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowTypeVariant <em>Window Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Window Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowTypeVariant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_WindowTypeVariant();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TAssignmentGroups <em>TAssignment Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TAssignment Groups</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TAssignmentGroups
	 * @generated
	 */
	EClass getTAssignmentGroups();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TAssignmentGroups#getSpaces <em>Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Spaces</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TAssignmentGroups#getSpaces()
	 * @see #getTAssignmentGroups()
	 * @generated
	 */
	EReference getTAssignmentGroups_Spaces();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TAssignmentGroups#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Elements</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TAssignmentGroups#getElements()
	 * @see #getTAssignmentGroups()
	 * @generated
	 */
	EReference getTAssignmentGroups_Elements();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TBIMGroup <em>TBIM Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TBIM Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TBIMGroup
	 * @generated
	 */
	EClass getTBIMGroup();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.TBIMGroup#getBIMREF <em>BIMREF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>BIMREF</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TBIMGroup#getBIMREF()
	 * @see #getTBIMGroup()
	 * @generated
	 */
	EAttribute getTBIMGroup_BIMREF();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TBIMGroup#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TBIMGroup#getId()
	 * @see #getTBIMGroup()
	 * @generated
	 */
	EAttribute getTBIMGroup_Id();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TCombination <em>TCombination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCombination</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TCombination
	 * @generated
	 */
	EClass getTCombination();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TCombination#getVariant <em>Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TCombination#getVariant()
	 * @see #getTCombination()
	 * @generated
	 */
	EReference getTCombination_Variant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TCombination#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TCombination#getId()
	 * @see #getTCombination()
	 * @generated
	 */
	EAttribute getTCombination_Id();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TCombinations <em>TCombinations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCombinations</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TCombinations
	 * @generated
	 */
	EClass getTCombinations();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TCombinations#getCombination <em>Combination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Combination</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TCombinations#getCombination()
	 * @see #getTCombinations()
	 * @generated
	 */
	EReference getTCombinations_Combination();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant <em>TConstant Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TConstant Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstantTypeVariant
	 * @generated
	 */
	EClass getTConstantTypeVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getHeatingSetPoint <em>Heating Set Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Heating Set Point</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getHeatingSetPoint()
	 * @see #getTConstantTypeVariant()
	 * @generated
	 */
	EReference getTConstantTypeVariant_HeatingSetPoint();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getCoolingSetPoint <em>Cooling Set Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cooling Set Point</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getCoolingSetPoint()
	 * @see #getTConstantTypeVariant()
	 * @generated
	 */
	EReference getTConstantTypeVariant_CoolingSetPoint();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getPersonLoad <em>Person Load</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Person Load</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getPersonLoad()
	 * @see #getTConstantTypeVariant()
	 * @generated
	 */
	EReference getTConstantTypeVariant_PersonLoad();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getEquipmentLoad <em>Equipment Load</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Equipment Load</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getEquipmentLoad()
	 * @see #getTConstantTypeVariant()
	 * @generated
	 */
	EReference getTConstantTypeVariant_EquipmentLoad();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getShading <em>Shading</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shading</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getShading()
	 * @see #getTConstantTypeVariant()
	 * @generated
	 */
	EReference getTConstantTypeVariant_Shading();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getId()
	 * @see #getTConstantTypeVariant()
	 * @generated
	 */
	EAttribute getTConstantTypeVariant_Id();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TConstructionType <em>TConstruction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TConstruction Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionType
	 * @generated
	 */
	EClass getTConstructionType();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TConstructionType#getConstructionTypeVariant <em>Construction Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Construction Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionType#getConstructionTypeVariant()
	 * @see #getTConstructionType()
	 * @generated
	 */
	EReference getTConstructionType_ConstructionTypeVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TConstructionType#getOrientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Orientation</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionType#getOrientation()
	 * @see #getTConstructionType()
	 * @generated
	 */
	EAttribute getTConstructionType_Orientation();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TConstructionType#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionType#getSource()
	 * @see #getTConstructionType()
	 * @generated
	 */
	EAttribute getTConstructionType_Source();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TConstructionTypes <em>TConstruction Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TConstruction Types</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionTypes
	 * @generated
	 */
	EClass getTConstructionTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TConstructionTypes#getConstructionType <em>Construction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Construction Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionTypes#getConstructionType()
	 * @see #getTConstructionTypes()
	 * @generated
	 */
	EReference getTConstructionTypes_ConstructionType();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant <em>TConstruction Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TConstruction Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant
	 * @generated
	 */
	EClass getTConstructionTypeVariant();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant#getLayer <em>Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Layer</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant#getLayer()
	 * @see #getTConstructionTypeVariant()
	 * @generated
	 */
	EReference getTConstructionTypeVariant_Layer();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant#getId()
	 * @see #getTConstructionTypeVariant()
	 * @generated
	 */
	EAttribute getTConstructionTypeVariant_Id();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TElementGroup <em>TElement Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TElement Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElementGroup
	 * @generated
	 */
	EClass getTElementGroup();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.TElementGroup#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElementGroup#getGroup()
	 * @see #getTElementGroup()
	 * @generated
	 */
	EAttribute getTElementGroup_Group();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.TElementGroup#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Element</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElementGroup#getElement()
	 * @see #getTElementGroup()
	 * @generated
	 */
	EAttribute getTElementGroup_Element();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TElementGroup#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElementGroup#getId()
	 * @see #getTElementGroup()
	 * @generated
	 */
	EAttribute getTElementGroup_Id();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TElements <em>TElements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TElements</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElements
	 * @generated
	 */
	EClass getTElements();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.TElements#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElements#getGroup()
	 * @see #getTElements()
	 * @generated
	 */
	EAttribute getTElements_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TElements#getElementGroup <em>Element Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Element Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElements#getElementGroup()
	 * @see #getTElements()
	 * @generated
	 */
	EReference getTElements_ElementGroup();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TElevation <em>TElevation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TElevation</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElevation
	 * @generated
	 */
	EClass getTElevation();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TElevation#getElevationVariant <em>Elevation Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elevation Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElevation#getElevationVariant()
	 * @see #getTElevation()
	 * @generated
	 */
	EReference getTElevation_ElevationVariant();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TElevationVariant <em>TElevation Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TElevation Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElevationVariant
	 * @generated
	 */
	EClass getTElevationVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TElevationVariant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElevationVariant#getValue()
	 * @see #getTElevationVariant()
	 * @generated
	 */
	EAttribute getTElevationVariant_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TElevationVariant#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElevationVariant#getId()
	 * @see #getTElevationVariant()
	 * @generated
	 */
	EAttribute getTElevationVariant_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TElevationVariant#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TElevationVariant#getUnit()
	 * @see #getTElevationVariant()
	 * @generated
	 */
	EAttribute getTElevationVariant_Unit();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits <em>TFloat With Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TFloat With Units</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TFloatWithUnits
	 * @generated
	 */
	EClass getTFloatWithUnits();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getValue()
	 * @see #getTFloatWithUnits()
	 * @generated
	 */
	EAttribute getTFloatWithUnits_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getUnit()
	 * @see #getTFloatWithUnits()
	 * @generated
	 */
	EAttribute getTFloatWithUnits_Unit();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TLayer <em>TLayer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TLayer</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TLayer
	 * @generated
	 */
	EClass getTLayer();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TLayer#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TLayer#getValue()
	 * @see #getTLayer()
	 * @generated
	 */
	EAttribute getTLayer_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TLayer#getThickness <em>Thickness</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Thickness</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TLayer#getThickness()
	 * @see #getTLayer()
	 * @generated
	 */
	EAttribute getTLayer_Thickness();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TLayer#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TLayer#getUnit()
	 * @see #getTLayer()
	 * @generated
	 */
	EAttribute getTLayer_Unit();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TMaterialType <em>TMaterial Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TMaterial Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialType
	 * @generated
	 */
	EClass getTMaterialType();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TMaterialType#getMaterialTypeVariant <em>Material Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Material Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialType#getMaterialTypeVariant()
	 * @see #getTMaterialType()
	 * @generated
	 */
	EReference getTMaterialType_MaterialTypeVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TMaterialType#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialType#getSource()
	 * @see #getTMaterialType()
	 * @generated
	 */
	EAttribute getTMaterialType_Source();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypes <em>TMaterial Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TMaterial Types</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypes
	 * @generated
	 */
	EClass getTMaterialTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypes#getMaterialType <em>Material Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Material Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypes#getMaterialType()
	 * @see #getTMaterialTypes()
	 * @generated
	 */
	EReference getTMaterialTypes_MaterialType();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant <em>TMaterial Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TMaterial Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant
	 * @generated
	 */
	EClass getTMaterialTypeVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getDensity <em>Density</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Density</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getDensity()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EReference getTMaterialTypeVariant_Density();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getSpecificHeatCapacity <em>Specific Heat Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Specific Heat Capacity</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getSpecificHeatCapacity()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EReference getTMaterialTypeVariant_SpecificHeatCapacity();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getConductivity <em>Conductivity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Conductivity</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getConductivity()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EReference getTMaterialTypeVariant_Conductivity();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getWaterVaporDiffusionResistanceFactor <em>Water Vapor Diffusion Resistance Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Water Vapor Diffusion Resistance Factor</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getWaterVaporDiffusionResistanceFactor()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EReference getTMaterialTypeVariant_WaterVaporDiffusionResistanceFactor();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getWaterAbsorptionCapacity <em>Water Absorption Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Water Absorption Capacity</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getWaterAbsorptionCapacity()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EReference getTMaterialTypeVariant_WaterAbsorptionCapacity();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getOpenPorosity <em>Open Porosity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Open Porosity</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getOpenPorosity()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EReference getTMaterialTypeVariant_OpenPorosity();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getEffectiveSaturation <em>Effective Saturation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Effective Saturation</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getEffectiveSaturation()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EReference getTMaterialTypeVariant_EffectiveSaturation();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getCapillarySaturationContent <em>Capillary Saturation Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Capillary Saturation Content</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getCapillarySaturationContent()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EReference getTMaterialTypeVariant_CapillarySaturationContent();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getHygroscopicSorption <em>Hygroscopic Sorption</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Hygroscopic Sorption</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getHygroscopicSorption()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EReference getTMaterialTypeVariant_HygroscopicSorption();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getThermalConductivity <em>Thermal Conductivity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Thermal Conductivity</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getThermalConductivity()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EReference getTMaterialTypeVariant_ThermalConductivity();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getLiquidWaterConductivityEffectiveSaturation <em>Liquid Water Conductivity Effective Saturation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Liquid Water Conductivity Effective Saturation</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getLiquidWaterConductivityEffectiveSaturation()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EReference getTMaterialTypeVariant_LiquidWaterConductivityEffectiveSaturation();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getId()
	 * @see #getTMaterialTypeVariant()
	 * @generated
	 */
	EAttribute getTMaterialTypeVariant_Id();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TOccupancyType <em>TOccupancy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TOccupancy Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOccupancyType
	 * @generated
	 */
	EClass getTOccupancyType();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.TOccupancyType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOccupancyType#getGroup()
	 * @see #getTOccupancyType()
	 * @generated
	 */
	EAttribute getTOccupancyType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TOccupancyType#getOccupancyTypeVariant <em>Occupancy Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Occupancy Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOccupancyType#getOccupancyTypeVariant()
	 * @see #getTOccupancyType()
	 * @generated
	 */
	EReference getTOccupancyType_OccupancyTypeVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TOccupancyType#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOccupancyType#getSource()
	 * @see #getTOccupancyType()
	 * @generated
	 */
	EAttribute getTOccupancyType_Source();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant <em>TOccupancy Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TOccupancy Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant
	 * @generated
	 */
	EClass getTOccupancyTypeVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getValue()
	 * @see #getTOccupancyTypeVariant()
	 * @generated
	 */
	EAttribute getTOccupancyTypeVariant_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getId()
	 * @see #getTOccupancyTypeVariant()
	 * @generated
	 */
	EAttribute getTOccupancyTypeVariant_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getPeriod()
	 * @see #getTOccupancyTypeVariant()
	 * @generated
	 */
	EAttribute getTOccupancyTypeVariant_Period();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getType()
	 * @see #getTOccupancyTypeVariant()
	 * @generated
	 */
	EAttribute getTOccupancyTypeVariant_Type();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TOrientation <em>TOrientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TOrientation</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOrientation
	 * @generated
	 */
	EClass getTOrientation();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TOrientation#getOrientationVariant <em>Orientation Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Orientation Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOrientation#getOrientationVariant()
	 * @see #getTOrientation()
	 * @generated
	 */
	EReference getTOrientation_OrientationVariant();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TOrientationVariant <em>TOrientation Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TOrientation Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOrientationVariant
	 * @generated
	 */
	EClass getTOrientationVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TOrientationVariant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOrientationVariant#getValue()
	 * @see #getTOrientationVariant()
	 * @generated
	 */
	EAttribute getTOrientationVariant_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TOrientationVariant#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOrientationVariant#getId()
	 * @see #getTOrientationVariant()
	 * @generated
	 */
	EAttribute getTOrientationVariant_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TOrientationVariant#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TOrientationVariant#getUnit()
	 * @see #getTOrientationVariant()
	 * @generated
	 */
	EAttribute getTOrientationVariant_Unit();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TScheduleType <em>TSchedule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSchedule Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleType
	 * @generated
	 */
	EClass getTScheduleType();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.TScheduleType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleType#getGroup()
	 * @see #getTScheduleType()
	 * @generated
	 */
	EAttribute getTScheduleType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TScheduleType#getScheduleTypeVariant <em>Schedule Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Schedule Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleType#getScheduleTypeVariant()
	 * @see #getTScheduleType()
	 * @generated
	 */
	EReference getTScheduleType_ScheduleTypeVariant();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TScheduleType#getConstantTypeVariant <em>Constant Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constant Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleType#getConstantTypeVariant()
	 * @see #getTScheduleType()
	 * @generated
	 */
	EReference getTScheduleType_ConstantTypeVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TScheduleType#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleType#getSource()
	 * @see #getTScheduleType()
	 * @generated
	 */
	EAttribute getTScheduleType_Source();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant <em>TSchedule Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSchedule Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant
	 * @generated
	 */
	EClass getTScheduleTypeVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getHeatingSetPoint <em>Heating Set Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Heating Set Point</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getHeatingSetPoint()
	 * @see #getTScheduleTypeVariant()
	 * @generated
	 */
	EReference getTScheduleTypeVariant_HeatingSetPoint();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getCoolingSetPoint <em>Cooling Set Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cooling Set Point</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getCoolingSetPoint()
	 * @see #getTScheduleTypeVariant()
	 * @generated
	 */
	EReference getTScheduleTypeVariant_CoolingSetPoint();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getPersonLoad <em>Person Load</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Person Load</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getPersonLoad()
	 * @see #getTScheduleTypeVariant()
	 * @generated
	 */
	EReference getTScheduleTypeVariant_PersonLoad();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getEquipmentLoad <em>Equipment Load</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Equipment Load</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getEquipmentLoad()
	 * @see #getTScheduleTypeVariant()
	 * @generated
	 */
	EReference getTScheduleTypeVariant_EquipmentLoad();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getShading <em>Shading</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shading</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getShading()
	 * @see #getTScheduleTypeVariant()
	 * @generated
	 */
	EReference getTScheduleTypeVariant_Shading();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getId()
	 * @see #getTScheduleTypeVariant()
	 * @generated
	 */
	EAttribute getTScheduleTypeVariant_Id();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads <em>TSet Person Loads</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSet Person Loads</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetPersonLoads
	 * @generated
	 */
	EClass getTSetPersonLoads();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getValue()
	 * @see #getTSetPersonLoads()
	 * @generated
	 */
	EAttribute getTSetPersonLoads_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getPeriod()
	 * @see #getTSetPersonLoads()
	 * @generated
	 */
	EAttribute getTSetPersonLoads_Period();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getType()
	 * @see #getTSetPersonLoads()
	 * @generated
	 */
	EAttribute getTSetPersonLoads_Type();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getUnit()
	 * @see #getTSetPersonLoads()
	 * @generated
	 */
	EAttribute getTSetPersonLoads_Unit();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TSetPoint <em>TSet Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSet Point</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetPoint
	 * @generated
	 */
	EClass getTSetPoint();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetPoint#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetPoint#getValue()
	 * @see #getTSetPoint()
	 * @generated
	 */
	EAttribute getTSetPoint_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetPoint#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetPoint#getUnit()
	 * @see #getTSetPoint()
	 * @generated
	 */
	EAttribute getTSetPoint_Unit();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TSetShading <em>TSet Shading</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSet Shading</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetShading
	 * @generated
	 */
	EClass getTSetShading();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetShading#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetShading#getValue()
	 * @see #getTSetShading()
	 * @generated
	 */
	EAttribute getTSetShading_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetShading#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetShading#getPeriod()
	 * @see #getTSetShading()
	 * @generated
	 */
	EAttribute getTSetShading_Period();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetShading#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetShading#getType()
	 * @see #getTSetShading()
	 * @generated
	 */
	EAttribute getTSetShading_Type();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetShading#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetShading#getUnit()
	 * @see #getTSetShading()
	 * @generated
	 */
	EAttribute getTSetShading_Unit();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature <em>TSet Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSet Temperature</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetTemperature
	 * @generated
	 */
	EClass getTSetTemperature();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetTemperature#getValue()
	 * @see #getTSetTemperature()
	 * @generated
	 */
	EAttribute getTSetTemperature_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetTemperature#getPeriod()
	 * @see #getTSetTemperature()
	 * @generated
	 */
	EAttribute getTSetTemperature_Period();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetTemperature#getType()
	 * @see #getTSetTemperature()
	 * @generated
	 */
	EAttribute getTSetTemperature_Type();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSetTemperature#getUnit()
	 * @see #getTSetTemperature()
	 * @generated
	 */
	EAttribute getTSetTemperature_Unit();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix <em>TSimulation Matrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSimulation Matrix</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSimulationMatrix
	 * @generated
	 */
	EClass getTSimulationMatrix();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getTargets <em>Targets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Targets</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getTargets()
	 * @see #getTSimulationMatrix()
	 * @generated
	 */
	EReference getTSimulationMatrix_Targets();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getVariables()
	 * @see #getTSimulationMatrix()
	 * @generated
	 */
	EReference getTSimulationMatrix_Variables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getAssignmentGroups <em>Assignment Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Assignment Groups</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getAssignmentGroups()
	 * @see #getTSimulationMatrix()
	 * @generated
	 */
	EReference getTSimulationMatrix_AssignmentGroups();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getCombinations <em>Combinations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Combinations</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getCombinations()
	 * @see #getTSimulationMatrix()
	 * @generated
	 */
	EReference getTSimulationMatrix_Combinations();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getId()
	 * @see #getTSimulationMatrix()
	 * @generated
	 */
	EAttribute getTSimulationMatrix_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getType()
	 * @see #getTSimulationMatrix()
	 * @generated
	 */
	EAttribute getTSimulationMatrix_Type();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TSpace <em>TSpace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSpace</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSpace
	 * @generated
	 */
	EClass getTSpace();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSpace#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSpace#getId()
	 * @see #getTSpace()
	 * @generated
	 */
	EAttribute getTSpace_Id();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TSpaceGroup <em>TSpace Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSpace Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSpaceGroup
	 * @generated
	 */
	EClass getTSpaceGroup();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.TSpaceGroup#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSpaceGroup#getGroup()
	 * @see #getTSpaceGroup()
	 * @generated
	 */
	EAttribute getTSpaceGroup_Group();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.TSpaceGroup#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Space</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSpaceGroup#getSpace()
	 * @see #getTSpaceGroup()
	 * @generated
	 */
	EAttribute getTSpaceGroup_Space();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSpaceGroup#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSpaceGroup#getId()
	 * @see #getTSpaceGroup()
	 * @generated
	 */
	EAttribute getTSpaceGroup_Id();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TSpaces <em>TSpaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSpaces</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSpaces
	 * @generated
	 */
	EClass getTSpaces();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.TSpaces#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSpaces#getGroup()
	 * @see #getTSpaces()
	 * @generated
	 */
	EAttribute getTSpaces_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TSpaces#getSpaceGroup <em>Space Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Space Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSpaces#getSpaceGroup()
	 * @see #getTSpaces()
	 * @generated
	 */
	EReference getTSpaces_SpaceGroup();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TSpaces#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TSpaces#getId()
	 * @see #getTSpaces()
	 * @generated
	 */
	EAttribute getTSpaces_Id();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TTarget <em>TTarget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TTarget</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TTarget
	 * @generated
	 */
	EClass getTTarget();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TTarget#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TTarget#getValue()
	 * @see #getTTarget()
	 * @generated
	 */
	EAttribute getTTarget_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TTarget#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TTarget#getType()
	 * @see #getTTarget()
	 * @generated
	 */
	EAttribute getTTarget_Type();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TTargetList <em>TTarget List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TTarget List</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TTargetList
	 * @generated
	 */
	EClass getTTargetList();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TTargetList#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Target</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TTargetList#getTarget()
	 * @see #getTTargetList()
	 * @generated
	 */
	EReference getTTargetList_Target();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TTargetList#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TTargetList#getId()
	 * @see #getTTargetList()
	 * @generated
	 */
	EAttribute getTTargetList_Id();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TTargets <em>TTargets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TTargets</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TTargets
	 * @generated
	 */
	EClass getTTargets();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TTargets#getTargetList <em>Target List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Target List</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TTargets#getTargetList()
	 * @see #getTTargets()
	 * @generated
	 */
	EReference getTTargets_TargetList();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TUsage <em>TUsage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TUsage</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TUsage
	 * @generated
	 */
	EClass getTUsage();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.TUsage#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TUsage#getGroup()
	 * @see #getTUsage()
	 * @generated
	 */
	EAttribute getTUsage_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TUsage#getOccupancyType <em>Occupancy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Occupancy Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TUsage#getOccupancyType()
	 * @see #getTUsage()
	 * @generated
	 */
	EReference getTUsage_OccupancyType();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TUsage#getScheduleType <em>Schedule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Schedule Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TUsage#getScheduleType()
	 * @see #getTUsage()
	 * @generated
	 */
	EReference getTUsage_ScheduleType();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TVariables <em>TVariables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TVariables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariables
	 * @generated
	 */
	EClass getTVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TVariables#getWeatherVariables <em>Weather Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Weather Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariables#getWeatherVariables()
	 * @see #getTVariables()
	 * @generated
	 */
	EReference getTVariables_WeatherVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TVariables#getOrientationVariables <em>Orientation Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Orientation Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariables#getOrientationVariables()
	 * @see #getTVariables()
	 * @generated
	 */
	EReference getTVariables_OrientationVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TVariables#getElevationVariables <em>Elevation Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Elevation Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariables#getElevationVariables()
	 * @see #getTVariables()
	 * @generated
	 */
	EReference getTVariables_ElevationVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TVariables#getUsageTypeVariables <em>Usage Type Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Usage Type Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariables#getUsageTypeVariables()
	 * @see #getTVariables()
	 * @generated
	 */
	EReference getTVariables_UsageTypeVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TVariables#getConstructionTypeVariables <em>Construction Type Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Construction Type Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariables#getConstructionTypeVariables()
	 * @see #getTVariables()
	 * @generated
	 */
	EReference getTVariables_ConstructionTypeVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TVariables#getWindowTypeVariables <em>Window Type Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Window Type Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariables#getWindowTypeVariables()
	 * @see #getTVariables()
	 * @generated
	 */
	EReference getTVariables_WindowTypeVariables();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TVariables#getDoorTypeVariables <em>Door Type Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Door Type Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariables#getDoorTypeVariables()
	 * @see #getTVariables()
	 * @generated
	 */
	EAttribute getTVariables_DoorTypeVariables();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TVariables#getMaterialTypeVariables <em>Material Type Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Material Type Variables</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariables#getMaterialTypeVariables()
	 * @see #getTVariables()
	 * @generated
	 */
	EReference getTVariables_MaterialTypeVariables();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TVariant <em>TVariant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TVariant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariant
	 * @generated
	 */
	EClass getTVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TVariant#getAREF <em>AREF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>AREF</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariant#getAREF()
	 * @see #getTVariant()
	 * @generated
	 */
	EAttribute getTVariant_AREF();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TVariant#getVREF <em>VREF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VREF</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TVariant#getVREF()
	 * @see #getTVariant()
	 * @generated
	 */
	EAttribute getTVariant_VREF();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TWeather <em>TWeather</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TWeather</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeather
	 * @generated
	 */
	EClass getTWeather();

	/**
	 * Returns the meta object for the attribute list '{@link de.tudresden.bau.cib.simmatrix.TWeather#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeather#getGroup()
	 * @see #getTWeather()
	 * @generated
	 */
	EAttribute getTWeather_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TWeather#getWeatherDataSetVariant <em>Weather Data Set Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Weather Data Set Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeather#getWeatherDataSetVariant()
	 * @see #getTWeather()
	 * @generated
	 */
	EReference getTWeather_WeatherDataSetVariant();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TWeather#getWeatherDataSeriesVariant <em>Weather Data Series Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Weather Data Series Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeather#getWeatherDataSeriesVariant()
	 * @see #getTWeather()
	 * @generated
	 */
	EReference getTWeather_WeatherDataSeriesVariant();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant <em>TWeather Data Series Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TWeather Data Series Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant
	 * @generated
	 */
	EClass getTWeatherDataSeriesVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getAirTemperature <em>Air Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Air Temperature</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getAirTemperature()
	 * @see #getTWeatherDataSeriesVariant()
	 * @generated
	 */
	EAttribute getTWeatherDataSeriesVariant_AirTemperature();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getShortWaveDiffuse <em>Short Wave Diffuse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Short Wave Diffuse</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getShortWaveDiffuse()
	 * @see #getTWeatherDataSeriesVariant()
	 * @generated
	 */
	EAttribute getTWeatherDataSeriesVariant_ShortWaveDiffuse();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getShortWaveDirect <em>Short Wave Direct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Short Wave Direct</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getShortWaveDirect()
	 * @see #getTWeatherDataSeriesVariant()
	 * @generated
	 */
	EAttribute getTWeatherDataSeriesVariant_ShortWaveDirect();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getId()
	 * @see #getTWeatherDataSeriesVariant()
	 * @generated
	 */
	EAttribute getTWeatherDataSeriesVariant_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant#getType()
	 * @see #getTWeatherDataSeriesVariant()
	 * @generated
	 */
	EAttribute getTWeatherDataSeriesVariant_Type();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant <em>TWeather Data Set Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TWeather Data Set Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant
	 * @generated
	 */
	EClass getTWeatherDataSetVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant#getValue()
	 * @see #getTWeatherDataSetVariant()
	 * @generated
	 */
	EAttribute getTWeatherDataSetVariant_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant#getId()
	 * @see #getTWeatherDataSetVariant()
	 * @generated
	 */
	EAttribute getTWeatherDataSetVariant_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant#getType()
	 * @see #getTWeatherDataSetVariant()
	 * @generated
	 */
	EAttribute getTWeatherDataSetVariant_Type();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TWindowType <em>TWindow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TWindow Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowType
	 * @generated
	 */
	EClass getTWindowType();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TWindowType#getWindowTypeVariant <em>Window Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Window Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowType#getWindowTypeVariant()
	 * @see #getTWindowType()
	 * @generated
	 */
	EReference getTWindowType_WindowTypeVariant();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TWindowType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowType#getName()
	 * @see #getTWindowType()
	 * @generated
	 */
	EAttribute getTWindowType_Name();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TWindowTypes <em>TWindow Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TWindow Types</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowTypes
	 * @generated
	 */
	EClass getTWindowTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.simmatrix.TWindowTypes#getWindowType <em>Window Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Window Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowTypes#getWindowType()
	 * @see #getTWindowTypes()
	 * @generated
	 */
	EReference getTWindowTypes_WindowType();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant <em>TWindow Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TWindow Type Variant</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowTypeVariant
	 * @generated
	 */
	EClass getTWindowTypeVariant();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getGlassFraction <em>Glass Fraction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Glass Fraction</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getGlassFraction()
	 * @see #getTWindowTypeVariant()
	 * @generated
	 */
	EReference getTWindowTypeVariant_GlassFraction();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getFrameFraction <em>Frame Fraction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Frame Fraction</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getFrameFraction()
	 * @see #getTWindowTypeVariant()
	 * @generated
	 */
	EReference getTWindowTypeVariant_FrameFraction();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getThermalTransmittance <em>Thermal Transmittance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Thermal Transmittance</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getThermalTransmittance()
	 * @see #getTWindowTypeVariant()
	 * @generated
	 */
	EReference getTWindowTypeVariant_ThermalTransmittance();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getSolarHeatGainCoefficient <em>Solar Heat Gain Coefficient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Solar Heat Gain Coefficient</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getSolarHeatGainCoefficient()
	 * @see #getTWindowTypeVariant()
	 * @generated
	 */
	EReference getTWindowTypeVariant_SolarHeatGainCoefficient();

	/**
	 * Returns the meta object for the containment reference '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getShadingFactor <em>Shading Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shading Factor</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getShadingFactor()
	 * @see #getTWindowTypeVariant()
	 * @generated
	 */
	EReference getTWindowTypeVariant_ShadingFactor();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getId()
	 * @see #getTWindowTypeVariant()
	 * @generated
	 */
	EAttribute getTWindowTypeVariant_Id();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.DistanceUnit <em>Distance Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Distance Unit</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DistanceUnit
	 * @generated
	 */
	EEnum getDistanceUnit();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.FileFormat <em>File Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>File Format</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.FileFormat
	 * @generated
	 */
	EEnum getFileFormat();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.Loads <em>Loads</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Loads</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.Loads
	 * @generated
	 */
	EEnum getLoads();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.MaterialUnit <em>Material Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Material Unit</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.MaterialUnit
	 * @generated
	 */
	EEnum getMaterialUnit();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.OrientationSide <em>Orientation Side</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Orientation Side</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.OrientationSide
	 * @generated
	 */
	EEnum getOrientationSide();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.OrientationUnit <em>Orientation Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Orientation Unit</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.OrientationUnit
	 * @generated
	 */
	EEnum getOrientationUnit();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.SetPoint <em>Set Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Set Point</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.SetPoint
	 * @generated
	 */
	EEnum getSetPoint();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.Shading <em>Shading</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Shading</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.Shading
	 * @generated
	 */
	EEnum getShading();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.TargetType <em>Target Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Target Type</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TargetType
	 * @generated
	 */
	EEnum getTargetType();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.Temperature <em>Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Temperature</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.Temperature
	 * @generated
	 */
	EEnum getTemperature();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.TimePeriod <em>Time Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Time Period</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TimePeriod
	 * @generated
	 */
	EEnum getTimePeriod();

	/**
	 * Returns the meta object for enum '{@link de.tudresden.bau.cib.simmatrix.WeatherTypes <em>Weather Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Weather Types</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.WeatherTypes
	 * @generated
	 */
	EEnum getWeatherTypes();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.DistanceUnit <em>Distance Unit Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Distance Unit Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.DistanceUnit
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.DistanceUnit"
	 *        extendedMetaData="name='distanceUnit:Object' baseType='distanceUnit'"
	 * @generated
	 */
	EDataType getDistanceUnitObject();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.FileFormat <em>File Format Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>File Format Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.FileFormat
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.FileFormat"
	 *        extendedMetaData="name='fileFormat:Object' baseType='fileFormat'"
	 * @generated
	 */
	EDataType getFileFormatObject();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.Loads <em>Loads Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Loads Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.Loads
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.Loads"
	 *        extendedMetaData="name='loads:Object' baseType='loads'"
	 * @generated
	 */
	EDataType getLoadsObject();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.MaterialUnit <em>Material Unit Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Material Unit Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.MaterialUnit
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.MaterialUnit"
	 *        extendedMetaData="name='materialUnit:Object' baseType='materialUnit'"
	 * @generated
	 */
	EDataType getMaterialUnitObject();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.OrientationSide <em>Orientation Side Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Orientation Side Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.OrientationSide
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.OrientationSide"
	 *        extendedMetaData="name='orientationSide:Object' baseType='orientationSide'"
	 * @generated
	 */
	EDataType getOrientationSideObject();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.OrientationUnit <em>Orientation Unit Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Orientation Unit Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.OrientationUnit
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.OrientationUnit"
	 *        extendedMetaData="name='orientationUnit:Object' baseType='orientationUnit'"
	 * @generated
	 */
	EDataType getOrientationUnitObject();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.SetPoint <em>Set Point Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Set Point Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.SetPoint
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.SetPoint"
	 *        extendedMetaData="name='setPoint:Object' baseType='setPoint'"
	 * @generated
	 */
	EDataType getSetPointObject();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.Shading <em>Shading Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Shading Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.Shading
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.Shading"
	 *        extendedMetaData="name='shading:Object' baseType='shading'"
	 * @generated
	 */
	EDataType getShadingObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Simat Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Simat Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='simatType' baseType='http://www.eclipse.org/emf/2003/XMLType#string' pattern='SensitivityAnalysis|DesignAlternatives|StochasticSimulation'"
	 * @generated
	 */
	EDataType getSimatType();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.TargetType <em>Target Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Target Type Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TargetType
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.TargetType"
	 *        extendedMetaData="name='targetType:Object' baseType='targetType'"
	 * @generated
	 */
	EDataType getTargetTypeObject();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.Temperature <em>Temperature Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Temperature Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.Temperature
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.Temperature"
	 *        extendedMetaData="name='temperature:Object' baseType='temperature'"
	 * @generated
	 */
	EDataType getTemperatureObject();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.TimePeriod <em>Time Period Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Time Period Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.TimePeriod
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.TimePeriod"
	 *        extendedMetaData="name='timePeriod:Object' baseType='timePeriod'"
	 * @generated
	 */
	EDataType getTimePeriodObject();

	/**
	 * Returns the meta object for data type '{@link java.util.List <em>TString List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TString List</em>'.
	 * @see java.util.List
	 * @model instanceClass="java.util.List"
	 *        extendedMetaData="name='T_StringList' itemType='http://www.eclipse.org/emf/2003/XMLType#string'"
	 * @generated
	 */
	EDataType getTStringList();

	/**
	 * Returns the meta object for data type '{@link de.tudresden.bau.cib.simmatrix.WeatherTypes <em>Weather Types Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Weather Types Object</em>'.
	 * @see de.tudresden.bau.cib.simmatrix.WeatherTypes
	 * @model instanceClass="de.tudresden.bau.cib.simmatrix.WeatherTypes"
	 *        extendedMetaData="name='weatherTypes:Object' baseType='weatherTypes'"
	 * @generated
	 */
	EDataType getWeatherTypesObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	simmatrixFactory getsimmatrixFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Assignment Groups</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ASSIGNMENT_GROUPS = eINSTANCE.getDocumentRoot_AssignmentGroups();

		/**
		 * The meta object literal for the '<em><b>BIMREF</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__BIMREF = eINSTANCE.getDocumentRoot_BIMREF();

		/**
		 * The meta object literal for the '<em><b>Combination</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__COMBINATION = eINSTANCE.getDocumentRoot_Combination();

		/**
		 * The meta object literal for the '<em><b>Combinations</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__COMBINATIONS = eINSTANCE.getDocumentRoot_Combinations();

		/**
		 * The meta object literal for the '<em><b>Constant Type Variant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONSTANT_TYPE_VARIANT = eINSTANCE.getDocumentRoot_ConstantTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Construction Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONSTRUCTION_TYPE = eINSTANCE.getDocumentRoot_ConstructionType();

		/**
		 * The meta object literal for the '<em><b>Construction Type Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIABLES = eINSTANCE.getDocumentRoot_ConstructionTypeVariables();

		/**
		 * The meta object literal for the '<em><b>Construction Type Variant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIANT = eINSTANCE.getDocumentRoot_ConstructionTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Door Type Variables</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__DOOR_TYPE_VARIABLES = eINSTANCE.getDocumentRoot_DoorTypeVariables();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__ELEMENT = eINSTANCE.getDocumentRoot_Element();

		/**
		 * The meta object literal for the '<em><b>Element Group</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ELEMENT_GROUP = eINSTANCE.getDocumentRoot_ElementGroup();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ELEMENTS = eINSTANCE.getDocumentRoot_Elements();

		/**
		 * The meta object literal for the '<em><b>Elevation Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ELEVATION_VARIABLES = eINSTANCE.getDocumentRoot_ElevationVariables();

		/**
		 * The meta object literal for the '<em><b>Elevation Variant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ELEVATION_VARIANT = eINSTANCE.getDocumentRoot_ElevationVariant();

		/**
		 * The meta object literal for the '<em><b>Layer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__LAYER = eINSTANCE.getDocumentRoot_Layer();

		/**
		 * The meta object literal for the '<em><b>Material Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MATERIAL_TYPE = eINSTANCE.getDocumentRoot_MaterialType();

		/**
		 * The meta object literal for the '<em><b>Material Type Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MATERIAL_TYPE_VARIABLES = eINSTANCE.getDocumentRoot_MaterialTypeVariables();

		/**
		 * The meta object literal for the '<em><b>Material Type Variant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MATERIAL_TYPE_VARIANT = eINSTANCE.getDocumentRoot_MaterialTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Occupancy Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OCCUPANCY_TYPE = eINSTANCE.getDocumentRoot_OccupancyType();

		/**
		 * The meta object literal for the '<em><b>Occupancy Type Variant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OCCUPANCY_TYPE_VARIANT = eINSTANCE.getDocumentRoot_OccupancyTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Orientation Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ORIENTATION_VARIABLES = eINSTANCE.getDocumentRoot_OrientationVariables();

		/**
		 * The meta object literal for the '<em><b>Orientation Variant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ORIENTATION_VARIANT = eINSTANCE.getDocumentRoot_OrientationVariant();

		/**
		 * The meta object literal for the '<em><b>Schedule Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SCHEDULE_TYPE = eINSTANCE.getDocumentRoot_ScheduleType();

		/**
		 * The meta object literal for the '<em><b>Schedule Type Variant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SCHEDULE_TYPE_VARIANT = eINSTANCE.getDocumentRoot_ScheduleTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Simulation Matrix</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SIMULATION_MATRIX = eINSTANCE.getDocumentRoot_SimulationMatrix();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__SPACE = eINSTANCE.getDocumentRoot_Space();

		/**
		 * The meta object literal for the '<em><b>Space Group</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SPACE_GROUP = eINSTANCE.getDocumentRoot_SpaceGroup();

		/**
		 * The meta object literal for the '<em><b>Spaces</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SPACES = eINSTANCE.getDocumentRoot_Spaces();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TARGET = eINSTANCE.getDocumentRoot_Target();

		/**
		 * The meta object literal for the '<em><b>Target List</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TARGET_LIST = eINSTANCE.getDocumentRoot_TargetList();

		/**
		 * The meta object literal for the '<em><b>Targets</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TARGETS = eINSTANCE.getDocumentRoot_Targets();

		/**
		 * The meta object literal for the '<em><b>Usage Type Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__USAGE_TYPE_VARIABLES = eINSTANCE.getDocumentRoot_UsageTypeVariables();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__VARIABLES = eINSTANCE.getDocumentRoot_Variables();

		/**
		 * The meta object literal for the '<em><b>Variant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__VARIANT = eINSTANCE.getDocumentRoot_Variant();

		/**
		 * The meta object literal for the '<em><b>Weather Data Series Variant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WEATHER_DATA_SERIES_VARIANT = eINSTANCE.getDocumentRoot_WeatherDataSeriesVariant();

		/**
		 * The meta object literal for the '<em><b>Weather Data Set Variant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WEATHER_DATA_SET_VARIANT = eINSTANCE.getDocumentRoot_WeatherDataSetVariant();

		/**
		 * The meta object literal for the '<em><b>Weather Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WEATHER_VARIABLES = eINSTANCE.getDocumentRoot_WeatherVariables();

		/**
		 * The meta object literal for the '<em><b>Window Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WINDOW_TYPE = eINSTANCE.getDocumentRoot_WindowType();

		/**
		 * The meta object literal for the '<em><b>Window Type Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WINDOW_TYPE_VARIABLES = eINSTANCE.getDocumentRoot_WindowTypeVariables();

		/**
		 * The meta object literal for the '<em><b>Window Type Variant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WINDOW_TYPE_VARIANT = eINSTANCE.getDocumentRoot_WindowTypeVariant();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TAssignmentGroupsImpl <em>TAssignment Groups</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TAssignmentGroupsImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTAssignmentGroups()
		 * @generated
		 */
		EClass TASSIGNMENT_GROUPS = eINSTANCE.getTAssignmentGroups();

		/**
		 * The meta object literal for the '<em><b>Spaces</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASSIGNMENT_GROUPS__SPACES = eINSTANCE.getTAssignmentGroups_Spaces();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASSIGNMENT_GROUPS__ELEMENTS = eINSTANCE.getTAssignmentGroups_Elements();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TBIMGroupImpl <em>TBIM Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TBIMGroupImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTBIMGroup()
		 * @generated
		 */
		EClass TBIM_GROUP = eINSTANCE.getTBIMGroup();

		/**
		 * The meta object literal for the '<em><b>BIMREF</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBIM_GROUP__BIMREF = eINSTANCE.getTBIMGroup_BIMREF();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBIM_GROUP__ID = eINSTANCE.getTBIMGroup_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TCombinationImpl <em>TCombination</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TCombinationImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTCombination()
		 * @generated
		 */
		EClass TCOMBINATION = eINSTANCE.getTCombination();

		/**
		 * The meta object literal for the '<em><b>Variant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOMBINATION__VARIANT = eINSTANCE.getTCombination_Variant();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCOMBINATION__ID = eINSTANCE.getTCombination_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TCombinationsImpl <em>TCombinations</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TCombinationsImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTCombinations()
		 * @generated
		 */
		EClass TCOMBINATIONS = eINSTANCE.getTCombinations();

		/**
		 * The meta object literal for the '<em><b>Combination</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOMBINATIONS__COMBINATION = eINSTANCE.getTCombinations_Combination();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TConstantTypeVariantImpl <em>TConstant Type Variant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TConstantTypeVariantImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTConstantTypeVariant()
		 * @generated
		 */
		EClass TCONSTANT_TYPE_VARIANT = eINSTANCE.getTConstantTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Heating Set Point</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT = eINSTANCE.getTConstantTypeVariant_HeatingSetPoint();

		/**
		 * The meta object literal for the '<em><b>Cooling Set Point</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT = eINSTANCE.getTConstantTypeVariant_CoolingSetPoint();

		/**
		 * The meta object literal for the '<em><b>Person Load</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCONSTANT_TYPE_VARIANT__PERSON_LOAD = eINSTANCE.getTConstantTypeVariant_PersonLoad();

		/**
		 * The meta object literal for the '<em><b>Equipment Load</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD = eINSTANCE.getTConstantTypeVariant_EquipmentLoad();

		/**
		 * The meta object literal for the '<em><b>Shading</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCONSTANT_TYPE_VARIANT__SHADING = eINSTANCE.getTConstantTypeVariant_Shading();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONSTANT_TYPE_VARIANT__ID = eINSTANCE.getTConstantTypeVariant_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TConstructionTypeImpl <em>TConstruction Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TConstructionTypeImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTConstructionType()
		 * @generated
		 */
		EClass TCONSTRUCTION_TYPE = eINSTANCE.getTConstructionType();

		/**
		 * The meta object literal for the '<em><b>Construction Type Variant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCONSTRUCTION_TYPE__CONSTRUCTION_TYPE_VARIANT = eINSTANCE.getTConstructionType_ConstructionTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Orientation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONSTRUCTION_TYPE__ORIENTATION = eINSTANCE.getTConstructionType_Orientation();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONSTRUCTION_TYPE__SOURCE = eINSTANCE.getTConstructionType_Source();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TConstructionTypesImpl <em>TConstruction Types</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TConstructionTypesImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTConstructionTypes()
		 * @generated
		 */
		EClass TCONSTRUCTION_TYPES = eINSTANCE.getTConstructionTypes();

		/**
		 * The meta object literal for the '<em><b>Construction Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCONSTRUCTION_TYPES__CONSTRUCTION_TYPE = eINSTANCE.getTConstructionTypes_ConstructionType();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TConstructionTypeVariantImpl <em>TConstruction Type Variant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TConstructionTypeVariantImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTConstructionTypeVariant()
		 * @generated
		 */
		EClass TCONSTRUCTION_TYPE_VARIANT = eINSTANCE.getTConstructionTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Layer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCONSTRUCTION_TYPE_VARIANT__LAYER = eINSTANCE.getTConstructionTypeVariant_Layer();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONSTRUCTION_TYPE_VARIANT__ID = eINSTANCE.getTConstructionTypeVariant_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TElementGroupImpl <em>TElement Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TElementGroupImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTElementGroup()
		 * @generated
		 */
		EClass TELEMENT_GROUP = eINSTANCE.getTElementGroup();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TELEMENT_GROUP__GROUP = eINSTANCE.getTElementGroup_Group();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TELEMENT_GROUP__ELEMENT = eINSTANCE.getTElementGroup_Element();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TELEMENT_GROUP__ID = eINSTANCE.getTElementGroup_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TElementsImpl <em>TElements</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TElementsImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTElements()
		 * @generated
		 */
		EClass TELEMENTS = eINSTANCE.getTElements();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TELEMENTS__GROUP = eINSTANCE.getTElements_Group();

		/**
		 * The meta object literal for the '<em><b>Element Group</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TELEMENTS__ELEMENT_GROUP = eINSTANCE.getTElements_ElementGroup();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TElevationImpl <em>TElevation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TElevationImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTElevation()
		 * @generated
		 */
		EClass TELEVATION = eINSTANCE.getTElevation();

		/**
		 * The meta object literal for the '<em><b>Elevation Variant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TELEVATION__ELEVATION_VARIANT = eINSTANCE.getTElevation_ElevationVariant();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TElevationVariantImpl <em>TElevation Variant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TElevationVariantImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTElevationVariant()
		 * @generated
		 */
		EClass TELEVATION_VARIANT = eINSTANCE.getTElevationVariant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TELEVATION_VARIANT__VALUE = eINSTANCE.getTElevationVariant_Value();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TELEVATION_VARIANT__ID = eINSTANCE.getTElevationVariant_Id();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TELEVATION_VARIANT__UNIT = eINSTANCE.getTElevationVariant_Unit();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TFloatWithUnitsImpl <em>TFloat With Units</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TFloatWithUnitsImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTFloatWithUnits()
		 * @generated
		 */
		EClass TFLOAT_WITH_UNITS = eINSTANCE.getTFloatWithUnits();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TFLOAT_WITH_UNITS__VALUE = eINSTANCE.getTFloatWithUnits_Value();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TFLOAT_WITH_UNITS__UNIT = eINSTANCE.getTFloatWithUnits_Unit();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TLayerImpl <em>TLayer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TLayerImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTLayer()
		 * @generated
		 */
		EClass TLAYER = eINSTANCE.getTLayer();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLAYER__VALUE = eINSTANCE.getTLayer_Value();

		/**
		 * The meta object literal for the '<em><b>Thickness</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLAYER__THICKNESS = eINSTANCE.getTLayer_Thickness();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLAYER__UNIT = eINSTANCE.getTLayer_Unit();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeImpl <em>TMaterial Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTMaterialType()
		 * @generated
		 */
		EClass TMATERIAL_TYPE = eINSTANCE.getTMaterialType();

		/**
		 * The meta object literal for the '<em><b>Material Type Variant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE__MATERIAL_TYPE_VARIANT = eINSTANCE.getTMaterialType_MaterialTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMATERIAL_TYPE__SOURCE = eINSTANCE.getTMaterialType_Source();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypesImpl <em>TMaterial Types</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TMaterialTypesImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTMaterialTypes()
		 * @generated
		 */
		EClass TMATERIAL_TYPES = eINSTANCE.getTMaterialTypes();

		/**
		 * The meta object literal for the '<em><b>Material Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPES__MATERIAL_TYPE = eINSTANCE.getTMaterialTypes_MaterialType();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl <em>TMaterial Type Variant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTMaterialTypeVariant()
		 * @generated
		 */
		EClass TMATERIAL_TYPE_VARIANT = eINSTANCE.getTMaterialTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Density</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE_VARIANT__DENSITY = eINSTANCE.getTMaterialTypeVariant_Density();

		/**
		 * The meta object literal for the '<em><b>Specific Heat Capacity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY = eINSTANCE.getTMaterialTypeVariant_SpecificHeatCapacity();

		/**
		 * The meta object literal for the '<em><b>Conductivity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE_VARIANT__CONDUCTIVITY = eINSTANCE.getTMaterialTypeVariant_Conductivity();

		/**
		 * The meta object literal for the '<em><b>Water Vapor Diffusion Resistance Factor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR = eINSTANCE.getTMaterialTypeVariant_WaterVaporDiffusionResistanceFactor();

		/**
		 * The meta object literal for the '<em><b>Water Absorption Capacity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY = eINSTANCE.getTMaterialTypeVariant_WaterAbsorptionCapacity();

		/**
		 * The meta object literal for the '<em><b>Open Porosity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE_VARIANT__OPEN_POROSITY = eINSTANCE.getTMaterialTypeVariant_OpenPorosity();

		/**
		 * The meta object literal for the '<em><b>Effective Saturation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION = eINSTANCE.getTMaterialTypeVariant_EffectiveSaturation();

		/**
		 * The meta object literal for the '<em><b>Capillary Saturation Content</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT = eINSTANCE.getTMaterialTypeVariant_CapillarySaturationContent();

		/**
		 * The meta object literal for the '<em><b>Hygroscopic Sorption</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION = eINSTANCE.getTMaterialTypeVariant_HygroscopicSorption();

		/**
		 * The meta object literal for the '<em><b>Thermal Conductivity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY = eINSTANCE.getTMaterialTypeVariant_ThermalConductivity();

		/**
		 * The meta object literal for the '<em><b>Liquid Water Conductivity Effective Saturation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION = eINSTANCE.getTMaterialTypeVariant_LiquidWaterConductivityEffectiveSaturation();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMATERIAL_TYPE_VARIANT__ID = eINSTANCE.getTMaterialTypeVariant_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TOccupancyTypeImpl <em>TOccupancy Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TOccupancyTypeImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTOccupancyType()
		 * @generated
		 */
		EClass TOCCUPANCY_TYPE = eINSTANCE.getTOccupancyType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOCCUPANCY_TYPE__GROUP = eINSTANCE.getTOccupancyType_Group();

		/**
		 * The meta object literal for the '<em><b>Occupancy Type Variant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOCCUPANCY_TYPE__OCCUPANCY_TYPE_VARIANT = eINSTANCE.getTOccupancyType_OccupancyTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOCCUPANCY_TYPE__SOURCE = eINSTANCE.getTOccupancyType_Source();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TOccupancyTypeVariantImpl <em>TOccupancy Type Variant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TOccupancyTypeVariantImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTOccupancyTypeVariant()
		 * @generated
		 */
		EClass TOCCUPANCY_TYPE_VARIANT = eINSTANCE.getTOccupancyTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOCCUPANCY_TYPE_VARIANT__VALUE = eINSTANCE.getTOccupancyTypeVariant_Value();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOCCUPANCY_TYPE_VARIANT__ID = eINSTANCE.getTOccupancyTypeVariant_Id();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOCCUPANCY_TYPE_VARIANT__PERIOD = eINSTANCE.getTOccupancyTypeVariant_Period();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOCCUPANCY_TYPE_VARIANT__TYPE = eINSTANCE.getTOccupancyTypeVariant_Type();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TOrientationImpl <em>TOrientation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TOrientationImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTOrientation()
		 * @generated
		 */
		EClass TORIENTATION = eINSTANCE.getTOrientation();

		/**
		 * The meta object literal for the '<em><b>Orientation Variant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TORIENTATION__ORIENTATION_VARIANT = eINSTANCE.getTOrientation_OrientationVariant();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TOrientationVariantImpl <em>TOrientation Variant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TOrientationVariantImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTOrientationVariant()
		 * @generated
		 */
		EClass TORIENTATION_VARIANT = eINSTANCE.getTOrientationVariant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TORIENTATION_VARIANT__VALUE = eINSTANCE.getTOrientationVariant_Value();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TORIENTATION_VARIANT__ID = eINSTANCE.getTOrientationVariant_Id();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TORIENTATION_VARIANT__UNIT = eINSTANCE.getTOrientationVariant_Unit();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeImpl <em>TSchedule Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTScheduleType()
		 * @generated
		 */
		EClass TSCHEDULE_TYPE = eINSTANCE.getTScheduleType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSCHEDULE_TYPE__GROUP = eINSTANCE.getTScheduleType_Group();

		/**
		 * The meta object literal for the '<em><b>Schedule Type Variant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSCHEDULE_TYPE__SCHEDULE_TYPE_VARIANT = eINSTANCE.getTScheduleType_ScheduleTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Constant Type Variant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSCHEDULE_TYPE__CONSTANT_TYPE_VARIANT = eINSTANCE.getTScheduleType_ConstantTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSCHEDULE_TYPE__SOURCE = eINSTANCE.getTScheduleType_Source();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeVariantImpl <em>TSchedule Type Variant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeVariantImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTScheduleTypeVariant()
		 * @generated
		 */
		EClass TSCHEDULE_TYPE_VARIANT = eINSTANCE.getTScheduleTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Heating Set Point</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT = eINSTANCE.getTScheduleTypeVariant_HeatingSetPoint();

		/**
		 * The meta object literal for the '<em><b>Cooling Set Point</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT = eINSTANCE.getTScheduleTypeVariant_CoolingSetPoint();

		/**
		 * The meta object literal for the '<em><b>Person Load</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSCHEDULE_TYPE_VARIANT__PERSON_LOAD = eINSTANCE.getTScheduleTypeVariant_PersonLoad();

		/**
		 * The meta object literal for the '<em><b>Equipment Load</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD = eINSTANCE.getTScheduleTypeVariant_EquipmentLoad();

		/**
		 * The meta object literal for the '<em><b>Shading</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSCHEDULE_TYPE_VARIANT__SHADING = eINSTANCE.getTScheduleTypeVariant_Shading();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSCHEDULE_TYPE_VARIANT__ID = eINSTANCE.getTScheduleTypeVariant_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSetPersonLoadsImpl <em>TSet Person Loads</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TSetPersonLoadsImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSetPersonLoads()
		 * @generated
		 */
		EClass TSET_PERSON_LOADS = eINSTANCE.getTSetPersonLoads();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_PERSON_LOADS__VALUE = eINSTANCE.getTSetPersonLoads_Value();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_PERSON_LOADS__PERIOD = eINSTANCE.getTSetPersonLoads_Period();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_PERSON_LOADS__TYPE = eINSTANCE.getTSetPersonLoads_Type();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_PERSON_LOADS__UNIT = eINSTANCE.getTSetPersonLoads_Unit();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSetPointImpl <em>TSet Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TSetPointImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSetPoint()
		 * @generated
		 */
		EClass TSET_POINT = eINSTANCE.getTSetPoint();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_POINT__VALUE = eINSTANCE.getTSetPoint_Value();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_POINT__UNIT = eINSTANCE.getTSetPoint_Unit();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSetShadingImpl <em>TSet Shading</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TSetShadingImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSetShading()
		 * @generated
		 */
		EClass TSET_SHADING = eINSTANCE.getTSetShading();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_SHADING__VALUE = eINSTANCE.getTSetShading_Value();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_SHADING__PERIOD = eINSTANCE.getTSetShading_Period();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_SHADING__TYPE = eINSTANCE.getTSetShading_Type();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_SHADING__UNIT = eINSTANCE.getTSetShading_Unit();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSetTemperatureImpl <em>TSet Temperature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TSetTemperatureImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSetTemperature()
		 * @generated
		 */
		EClass TSET_TEMPERATURE = eINSTANCE.getTSetTemperature();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_TEMPERATURE__VALUE = eINSTANCE.getTSetTemperature_Value();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_TEMPERATURE__PERIOD = eINSTANCE.getTSetTemperature_Period();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_TEMPERATURE__TYPE = eINSTANCE.getTSetTemperature_Type();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSET_TEMPERATURE__UNIT = eINSTANCE.getTSetTemperature_Unit();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSimulationMatrixImpl <em>TSimulation Matrix</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TSimulationMatrixImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSimulationMatrix()
		 * @generated
		 */
		EClass TSIMULATION_MATRIX = eINSTANCE.getTSimulationMatrix();

		/**
		 * The meta object literal for the '<em><b>Targets</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSIMULATION_MATRIX__TARGETS = eINSTANCE.getTSimulationMatrix_Targets();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSIMULATION_MATRIX__VARIABLES = eINSTANCE.getTSimulationMatrix_Variables();

		/**
		 * The meta object literal for the '<em><b>Assignment Groups</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSIMULATION_MATRIX__ASSIGNMENT_GROUPS = eINSTANCE.getTSimulationMatrix_AssignmentGroups();

		/**
		 * The meta object literal for the '<em><b>Combinations</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSIMULATION_MATRIX__COMBINATIONS = eINSTANCE.getTSimulationMatrix_Combinations();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSIMULATION_MATRIX__ID = eINSTANCE.getTSimulationMatrix_Id();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSIMULATION_MATRIX__TYPE = eINSTANCE.getTSimulationMatrix_Type();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSpaceImpl <em>TSpace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TSpaceImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSpace()
		 * @generated
		 */
		EClass TSPACE = eINSTANCE.getTSpace();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSPACE__ID = eINSTANCE.getTSpace_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSpaceGroupImpl <em>TSpace Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TSpaceGroupImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSpaceGroup()
		 * @generated
		 */
		EClass TSPACE_GROUP = eINSTANCE.getTSpaceGroup();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSPACE_GROUP__GROUP = eINSTANCE.getTSpaceGroup_Group();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSPACE_GROUP__SPACE = eINSTANCE.getTSpaceGroup_Space();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSPACE_GROUP__ID = eINSTANCE.getTSpaceGroup_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TSpacesImpl <em>TSpaces</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TSpacesImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTSpaces()
		 * @generated
		 */
		EClass TSPACES = eINSTANCE.getTSpaces();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSPACES__GROUP = eINSTANCE.getTSpaces_Group();

		/**
		 * The meta object literal for the '<em><b>Space Group</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSPACES__SPACE_GROUP = eINSTANCE.getTSpaces_SpaceGroup();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSPACES__ID = eINSTANCE.getTSpaces_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TTargetImpl <em>TTarget</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TTargetImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTTarget()
		 * @generated
		 */
		EClass TTARGET = eINSTANCE.getTTarget();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TTARGET__VALUE = eINSTANCE.getTTarget_Value();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TTARGET__TYPE = eINSTANCE.getTTarget_Type();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TTargetListImpl <em>TTarget List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TTargetListImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTTargetList()
		 * @generated
		 */
		EClass TTARGET_LIST = eINSTANCE.getTTargetList();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TTARGET_LIST__TARGET = eINSTANCE.getTTargetList_Target();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TTARGET_LIST__ID = eINSTANCE.getTTargetList_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TTargetsImpl <em>TTargets</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TTargetsImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTTargets()
		 * @generated
		 */
		EClass TTARGETS = eINSTANCE.getTTargets();

		/**
		 * The meta object literal for the '<em><b>Target List</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TTARGETS__TARGET_LIST = eINSTANCE.getTTargets_TargetList();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TUsageImpl <em>TUsage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TUsageImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTUsage()
		 * @generated
		 */
		EClass TUSAGE = eINSTANCE.getTUsage();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUSAGE__GROUP = eINSTANCE.getTUsage_Group();

		/**
		 * The meta object literal for the '<em><b>Occupancy Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUSAGE__OCCUPANCY_TYPE = eINSTANCE.getTUsage_OccupancyType();

		/**
		 * The meta object literal for the '<em><b>Schedule Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUSAGE__SCHEDULE_TYPE = eINSTANCE.getTUsage_ScheduleType();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl <em>TVariables</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTVariables()
		 * @generated
		 */
		EClass TVARIABLES = eINSTANCE.getTVariables();

		/**
		 * The meta object literal for the '<em><b>Weather Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TVARIABLES__WEATHER_VARIABLES = eINSTANCE.getTVariables_WeatherVariables();

		/**
		 * The meta object literal for the '<em><b>Orientation Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TVARIABLES__ORIENTATION_VARIABLES = eINSTANCE.getTVariables_OrientationVariables();

		/**
		 * The meta object literal for the '<em><b>Elevation Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TVARIABLES__ELEVATION_VARIABLES = eINSTANCE.getTVariables_ElevationVariables();

		/**
		 * The meta object literal for the '<em><b>Usage Type Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TVARIABLES__USAGE_TYPE_VARIABLES = eINSTANCE.getTVariables_UsageTypeVariables();

		/**
		 * The meta object literal for the '<em><b>Construction Type Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TVARIABLES__CONSTRUCTION_TYPE_VARIABLES = eINSTANCE.getTVariables_ConstructionTypeVariables();

		/**
		 * The meta object literal for the '<em><b>Window Type Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TVARIABLES__WINDOW_TYPE_VARIABLES = eINSTANCE.getTVariables_WindowTypeVariables();

		/**
		 * The meta object literal for the '<em><b>Door Type Variables</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TVARIABLES__DOOR_TYPE_VARIABLES = eINSTANCE.getTVariables_DoorTypeVariables();

		/**
		 * The meta object literal for the '<em><b>Material Type Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TVARIABLES__MATERIAL_TYPE_VARIABLES = eINSTANCE.getTVariables_MaterialTypeVariables();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TVariantImpl <em>TVariant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TVariantImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTVariant()
		 * @generated
		 */
		EClass TVARIANT = eINSTANCE.getTVariant();

		/**
		 * The meta object literal for the '<em><b>AREF</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TVARIANT__AREF = eINSTANCE.getTVariant_AREF();

		/**
		 * The meta object literal for the '<em><b>VREF</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TVARIANT__VREF = eINSTANCE.getTVariant_VREF();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherImpl <em>TWeather</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TWeatherImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWeather()
		 * @generated
		 */
		EClass TWEATHER = eINSTANCE.getTWeather();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TWEATHER__GROUP = eINSTANCE.getTWeather_Group();

		/**
		 * The meta object literal for the '<em><b>Weather Data Set Variant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TWEATHER__WEATHER_DATA_SET_VARIANT = eINSTANCE.getTWeather_WeatherDataSetVariant();

		/**
		 * The meta object literal for the '<em><b>Weather Data Series Variant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TWEATHER__WEATHER_DATA_SERIES_VARIANT = eINSTANCE.getTWeather_WeatherDataSeriesVariant();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSeriesVariantImpl <em>TWeather Data Series Variant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSeriesVariantImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWeatherDataSeriesVariant()
		 * @generated
		 */
		EClass TWEATHER_DATA_SERIES_VARIANT = eINSTANCE.getTWeatherDataSeriesVariant();

		/**
		 * The meta object literal for the '<em><b>Air Temperature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TWEATHER_DATA_SERIES_VARIANT__AIR_TEMPERATURE = eINSTANCE.getTWeatherDataSeriesVariant_AirTemperature();

		/**
		 * The meta object literal for the '<em><b>Short Wave Diffuse</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIFFUSE = eINSTANCE.getTWeatherDataSeriesVariant_ShortWaveDiffuse();

		/**
		 * The meta object literal for the '<em><b>Short Wave Direct</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIRECT = eINSTANCE.getTWeatherDataSeriesVariant_ShortWaveDirect();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TWEATHER_DATA_SERIES_VARIANT__ID = eINSTANCE.getTWeatherDataSeriesVariant_Id();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TWEATHER_DATA_SERIES_VARIANT__TYPE = eINSTANCE.getTWeatherDataSeriesVariant_Type();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSetVariantImpl <em>TWeather Data Set Variant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSetVariantImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWeatherDataSetVariant()
		 * @generated
		 */
		EClass TWEATHER_DATA_SET_VARIANT = eINSTANCE.getTWeatherDataSetVariant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TWEATHER_DATA_SET_VARIANT__VALUE = eINSTANCE.getTWeatherDataSetVariant_Value();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TWEATHER_DATA_SET_VARIANT__ID = eINSTANCE.getTWeatherDataSetVariant_Id();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TWEATHER_DATA_SET_VARIANT__TYPE = eINSTANCE.getTWeatherDataSetVariant_Type();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypeImpl <em>TWindow Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TWindowTypeImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWindowType()
		 * @generated
		 */
		EClass TWINDOW_TYPE = eINSTANCE.getTWindowType();

		/**
		 * The meta object literal for the '<em><b>Window Type Variant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TWINDOW_TYPE__WINDOW_TYPE_VARIANT = eINSTANCE.getTWindowType_WindowTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TWINDOW_TYPE__NAME = eINSTANCE.getTWindowType_Name();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypesImpl <em>TWindow Types</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TWindowTypesImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWindowTypes()
		 * @generated
		 */
		EClass TWINDOW_TYPES = eINSTANCE.getTWindowTypes();

		/**
		 * The meta object literal for the '<em><b>Window Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TWINDOW_TYPES__WINDOW_TYPE = eINSTANCE.getTWindowTypes_WindowType();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypeVariantImpl <em>TWindow Type Variant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.impl.TWindowTypeVariantImpl
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTWindowTypeVariant()
		 * @generated
		 */
		EClass TWINDOW_TYPE_VARIANT = eINSTANCE.getTWindowTypeVariant();

		/**
		 * The meta object literal for the '<em><b>Glass Fraction</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TWINDOW_TYPE_VARIANT__GLASS_FRACTION = eINSTANCE.getTWindowTypeVariant_GlassFraction();

		/**
		 * The meta object literal for the '<em><b>Frame Fraction</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TWINDOW_TYPE_VARIANT__FRAME_FRACTION = eINSTANCE.getTWindowTypeVariant_FrameFraction();

		/**
		 * The meta object literal for the '<em><b>Thermal Transmittance</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE = eINSTANCE.getTWindowTypeVariant_ThermalTransmittance();

		/**
		 * The meta object literal for the '<em><b>Solar Heat Gain Coefficient</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT = eINSTANCE.getTWindowTypeVariant_SolarHeatGainCoefficient();

		/**
		 * The meta object literal for the '<em><b>Shading Factor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TWINDOW_TYPE_VARIANT__SHADING_FACTOR = eINSTANCE.getTWindowTypeVariant_ShadingFactor();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TWINDOW_TYPE_VARIANT__ID = eINSTANCE.getTWindowTypeVariant_Id();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.DistanceUnit <em>Distance Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.DistanceUnit
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getDistanceUnit()
		 * @generated
		 */
		EEnum DISTANCE_UNIT = eINSTANCE.getDistanceUnit();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.FileFormat <em>File Format</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.FileFormat
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getFileFormat()
		 * @generated
		 */
		EEnum FILE_FORMAT = eINSTANCE.getFileFormat();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.Loads <em>Loads</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.Loads
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getLoads()
		 * @generated
		 */
		EEnum LOADS = eINSTANCE.getLoads();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.MaterialUnit <em>Material Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.MaterialUnit
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getMaterialUnit()
		 * @generated
		 */
		EEnum MATERIAL_UNIT = eINSTANCE.getMaterialUnit();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.OrientationSide <em>Orientation Side</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.OrientationSide
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getOrientationSide()
		 * @generated
		 */
		EEnum ORIENTATION_SIDE = eINSTANCE.getOrientationSide();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.OrientationUnit <em>Orientation Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.OrientationUnit
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getOrientationUnit()
		 * @generated
		 */
		EEnum ORIENTATION_UNIT = eINSTANCE.getOrientationUnit();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.SetPoint <em>Set Point</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.SetPoint
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getSetPoint()
		 * @generated
		 */
		EEnum SET_POINT = eINSTANCE.getSetPoint();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.Shading <em>Shading</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.Shading
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getShading()
		 * @generated
		 */
		EEnum SHADING = eINSTANCE.getShading();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.TargetType <em>Target Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.TargetType
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTargetType()
		 * @generated
		 */
		EEnum TARGET_TYPE = eINSTANCE.getTargetType();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.Temperature <em>Temperature</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.Temperature
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTemperature()
		 * @generated
		 */
		EEnum TEMPERATURE = eINSTANCE.getTemperature();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.TimePeriod <em>Time Period</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.TimePeriod
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTimePeriod()
		 * @generated
		 */
		EEnum TIME_PERIOD = eINSTANCE.getTimePeriod();

		/**
		 * The meta object literal for the '{@link de.tudresden.bau.cib.simmatrix.WeatherTypes <em>Weather Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.WeatherTypes
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getWeatherTypes()
		 * @generated
		 */
		EEnum WEATHER_TYPES = eINSTANCE.getWeatherTypes();

		/**
		 * The meta object literal for the '<em>Distance Unit Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.DistanceUnit
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getDistanceUnitObject()
		 * @generated
		 */
		EDataType DISTANCE_UNIT_OBJECT = eINSTANCE.getDistanceUnitObject();

		/**
		 * The meta object literal for the '<em>File Format Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.FileFormat
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getFileFormatObject()
		 * @generated
		 */
		EDataType FILE_FORMAT_OBJECT = eINSTANCE.getFileFormatObject();

		/**
		 * The meta object literal for the '<em>Loads Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.Loads
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getLoadsObject()
		 * @generated
		 */
		EDataType LOADS_OBJECT = eINSTANCE.getLoadsObject();

		/**
		 * The meta object literal for the '<em>Material Unit Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.MaterialUnit
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getMaterialUnitObject()
		 * @generated
		 */
		EDataType MATERIAL_UNIT_OBJECT = eINSTANCE.getMaterialUnitObject();

		/**
		 * The meta object literal for the '<em>Orientation Side Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.OrientationSide
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getOrientationSideObject()
		 * @generated
		 */
		EDataType ORIENTATION_SIDE_OBJECT = eINSTANCE.getOrientationSideObject();

		/**
		 * The meta object literal for the '<em>Orientation Unit Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.OrientationUnit
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getOrientationUnitObject()
		 * @generated
		 */
		EDataType ORIENTATION_UNIT_OBJECT = eINSTANCE.getOrientationUnitObject();

		/**
		 * The meta object literal for the '<em>Set Point Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.SetPoint
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getSetPointObject()
		 * @generated
		 */
		EDataType SET_POINT_OBJECT = eINSTANCE.getSetPointObject();

		/**
		 * The meta object literal for the '<em>Shading Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.Shading
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getShadingObject()
		 * @generated
		 */
		EDataType SHADING_OBJECT = eINSTANCE.getShadingObject();

		/**
		 * The meta object literal for the '<em>Simat Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getSimatType()
		 * @generated
		 */
		EDataType SIMAT_TYPE = eINSTANCE.getSimatType();

		/**
		 * The meta object literal for the '<em>Target Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.TargetType
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTargetTypeObject()
		 * @generated
		 */
		EDataType TARGET_TYPE_OBJECT = eINSTANCE.getTargetTypeObject();

		/**
		 * The meta object literal for the '<em>Temperature Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.Temperature
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTemperatureObject()
		 * @generated
		 */
		EDataType TEMPERATURE_OBJECT = eINSTANCE.getTemperatureObject();

		/**
		 * The meta object literal for the '<em>Time Period Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.TimePeriod
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTimePeriodObject()
		 * @generated
		 */
		EDataType TIME_PERIOD_OBJECT = eINSTANCE.getTimePeriodObject();

		/**
		 * The meta object literal for the '<em>TString List</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.List
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getTStringList()
		 * @generated
		 */
		EDataType TSTRING_LIST = eINSTANCE.getTStringList();

		/**
		 * The meta object literal for the '<em>Weather Types Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tudresden.bau.cib.simmatrix.WeatherTypes
		 * @see de.tudresden.bau.cib.simmatrix.impl.simmatrixPackageImpl#getWeatherTypesObject()
		 * @generated
		 */
		EDataType WEATHER_TYPES_OBJECT = eINSTANCE.getWeatherTypesObject();

	}

} //simmatrixPackage
