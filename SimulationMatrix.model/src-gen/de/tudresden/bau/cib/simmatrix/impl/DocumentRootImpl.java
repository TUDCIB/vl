/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.DocumentRoot;
import de.tudresden.bau.cib.simmatrix.TAssignmentGroups;
import de.tudresden.bau.cib.simmatrix.TCombination;
import de.tudresden.bau.cib.simmatrix.TCombinations;
import de.tudresden.bau.cib.simmatrix.TConstantTypeVariant;
import de.tudresden.bau.cib.simmatrix.TConstructionType;
import de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant;
import de.tudresden.bau.cib.simmatrix.TConstructionTypes;
import de.tudresden.bau.cib.simmatrix.TElementGroup;
import de.tudresden.bau.cib.simmatrix.TElements;
import de.tudresden.bau.cib.simmatrix.TElevation;
import de.tudresden.bau.cib.simmatrix.TElevationVariant;
import de.tudresden.bau.cib.simmatrix.TLayer;
import de.tudresden.bau.cib.simmatrix.TMaterialType;
import de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant;
import de.tudresden.bau.cib.simmatrix.TMaterialTypes;
import de.tudresden.bau.cib.simmatrix.TOccupancyType;
import de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant;
import de.tudresden.bau.cib.simmatrix.TOrientation;
import de.tudresden.bau.cib.simmatrix.TOrientationVariant;
import de.tudresden.bau.cib.simmatrix.TScheduleType;
import de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant;
import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.simmatrix.TSpaceGroup;
import de.tudresden.bau.cib.simmatrix.TSpaces;
import de.tudresden.bau.cib.simmatrix.TTarget;
import de.tudresden.bau.cib.simmatrix.TTargetList;
import de.tudresden.bau.cib.simmatrix.TTargets;
import de.tudresden.bau.cib.simmatrix.TUsage;
import de.tudresden.bau.cib.simmatrix.TVariables;
import de.tudresden.bau.cib.simmatrix.TVariant;
import de.tudresden.bau.cib.simmatrix.TWeather;
import de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant;
import de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant;
import de.tudresden.bau.cib.simmatrix.TWindowType;
import de.tudresden.bau.cib.simmatrix.TWindowTypeVariant;
import de.tudresden.bau.cib.simmatrix.TWindowTypes;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getAssignmentGroups <em>Assignment Groups</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getBIMREF <em>BIMREF</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getCombination <em>Combination</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getCombinations <em>Combinations</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getConstantTypeVariant <em>Constant Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getConstructionType <em>Construction Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getConstructionTypeVariables <em>Construction Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getConstructionTypeVariant <em>Construction Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getDoorTypeVariables <em>Door Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getElement <em>Element</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getElementGroup <em>Element Group</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getElevationVariables <em>Elevation Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getElevationVariant <em>Elevation Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getLayer <em>Layer</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getMaterialType <em>Material Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getMaterialTypeVariables <em>Material Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getMaterialTypeVariant <em>Material Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getOccupancyType <em>Occupancy Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getOccupancyTypeVariant <em>Occupancy Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getOrientationVariables <em>Orientation Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getOrientationVariant <em>Orientation Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getScheduleType <em>Schedule Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getScheduleTypeVariant <em>Schedule Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getSimulationMatrix <em>Simulation Matrix</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getSpace <em>Space</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getSpaceGroup <em>Space Group</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getSpaces <em>Spaces</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getTargetList <em>Target List</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getTargets <em>Targets</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getUsageTypeVariables <em>Usage Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getVariant <em>Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getWeatherDataSeriesVariant <em>Weather Data Series Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getWeatherDataSetVariant <em>Weather Data Set Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getWeatherVariables <em>Weather Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getWindowType <em>Window Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getWindowTypeVariables <em>Window Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.DocumentRootImpl#getWindowTypeVariant <em>Window Type Variant</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * The default value of the '{@link #getBIMREF() <em>BIMREF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBIMREF()
	 * @generated
	 * @ordered
	 */
	protected static final String BIMREF_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDoorTypeVariables() <em>Door Type Variables</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDoorTypeVariables()
	 * @generated
	 * @ordered
	 */
	protected static final String DOOR_TYPE_VARIABLES_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getElement() <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getSpace() <em>Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String SPACE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, simmatrixPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, simmatrixPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, simmatrixPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAssignmentGroups getAssignmentGroups() {
		return (TAssignmentGroups)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__ASSIGNMENT_GROUPS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssignmentGroups(TAssignmentGroups newAssignmentGroups, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__ASSIGNMENT_GROUPS, newAssignmentGroups, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignmentGroups(TAssignmentGroups newAssignmentGroups) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__ASSIGNMENT_GROUPS, newAssignmentGroups);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBIMREF() {
		return (String)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__BIMREF, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBIMREF(String newBIMREF) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__BIMREF, newBIMREF);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCombination getCombination() {
		return (TCombination)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__COMBINATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCombination(TCombination newCombination, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__COMBINATION, newCombination, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCombination(TCombination newCombination) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__COMBINATION, newCombination);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCombinations getCombinations() {
		return (TCombinations)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__COMBINATIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCombinations(TCombinations newCombinations, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__COMBINATIONS, newCombinations, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCombinations(TCombinations newCombinations) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__COMBINATIONS, newCombinations);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConstantTypeVariant getConstantTypeVariant() {
		return (TConstantTypeVariant)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTANT_TYPE_VARIANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConstantTypeVariant(TConstantTypeVariant newConstantTypeVariant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTANT_TYPE_VARIANT, newConstantTypeVariant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstantTypeVariant(TConstantTypeVariant newConstantTypeVariant) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTANT_TYPE_VARIANT, newConstantTypeVariant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConstructionType getConstructionType() {
		return (TConstructionType)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTRUCTION_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConstructionType(TConstructionType newConstructionType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTRUCTION_TYPE, newConstructionType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstructionType(TConstructionType newConstructionType) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTRUCTION_TYPE, newConstructionType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConstructionTypes getConstructionTypeVariables() {
		return (TConstructionTypes)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIABLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConstructionTypeVariables(TConstructionTypes newConstructionTypeVariables, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIABLES, newConstructionTypeVariables, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstructionTypeVariables(TConstructionTypes newConstructionTypeVariables) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIABLES, newConstructionTypeVariables);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConstructionTypeVariant getConstructionTypeVariant() {
		return (TConstructionTypeVariant)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConstructionTypeVariant(TConstructionTypeVariant newConstructionTypeVariant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIANT, newConstructionTypeVariant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstructionTypeVariant(TConstructionTypeVariant newConstructionTypeVariant) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIANT, newConstructionTypeVariant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDoorTypeVariables() {
		return (String)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__DOOR_TYPE_VARIABLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDoorTypeVariables(String newDoorTypeVariables) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__DOOR_TYPE_VARIABLES, newDoorTypeVariables);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElement() {
		return (String)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(String newElement) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEMENT, newElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TElementGroup getElementGroup() {
		return (TElementGroup)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEMENT_GROUP, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElementGroup(TElementGroup newElementGroup, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEMENT_GROUP, newElementGroup, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementGroup(TElementGroup newElementGroup) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEMENT_GROUP, newElementGroup);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TElements getElements() {
		return (TElements)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEMENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElements(TElements newElements, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEMENTS, newElements, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElements(TElements newElements) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEMENTS, newElements);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TElevation getElevationVariables() {
		return (TElevation)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEVATION_VARIABLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElevationVariables(TElevation newElevationVariables, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEVATION_VARIABLES, newElevationVariables, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElevationVariables(TElevation newElevationVariables) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEVATION_VARIABLES, newElevationVariables);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TElevationVariant getElevationVariant() {
		return (TElevationVariant)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEVATION_VARIANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElevationVariant(TElevationVariant newElevationVariant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEVATION_VARIANT, newElevationVariant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElevationVariant(TElevationVariant newElevationVariant) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__ELEVATION_VARIANT, newElevationVariant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLayer getLayer() {
		return (TLayer)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__LAYER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLayer(TLayer newLayer, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__LAYER, newLayer, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLayer(TLayer newLayer) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__LAYER, newLayer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMaterialType getMaterialType() {
		return (TMaterialType)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__MATERIAL_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMaterialType(TMaterialType newMaterialType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__MATERIAL_TYPE, newMaterialType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaterialType(TMaterialType newMaterialType) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__MATERIAL_TYPE, newMaterialType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMaterialTypes getMaterialTypeVariables() {
		return (TMaterialTypes)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__MATERIAL_TYPE_VARIABLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMaterialTypeVariables(TMaterialTypes newMaterialTypeVariables, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__MATERIAL_TYPE_VARIABLES, newMaterialTypeVariables, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaterialTypeVariables(TMaterialTypes newMaterialTypeVariables) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__MATERIAL_TYPE_VARIABLES, newMaterialTypeVariables);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMaterialTypeVariant getMaterialTypeVariant() {
		return (TMaterialTypeVariant)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__MATERIAL_TYPE_VARIANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMaterialTypeVariant(TMaterialTypeVariant newMaterialTypeVariant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__MATERIAL_TYPE_VARIANT, newMaterialTypeVariant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaterialTypeVariant(TMaterialTypeVariant newMaterialTypeVariant) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__MATERIAL_TYPE_VARIANT, newMaterialTypeVariant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOccupancyType getOccupancyType() {
		return (TOccupancyType)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__OCCUPANCY_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOccupancyType(TOccupancyType newOccupancyType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__OCCUPANCY_TYPE, newOccupancyType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOccupancyType(TOccupancyType newOccupancyType) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__OCCUPANCY_TYPE, newOccupancyType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOccupancyTypeVariant getOccupancyTypeVariant() {
		return (TOccupancyTypeVariant)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__OCCUPANCY_TYPE_VARIANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOccupancyTypeVariant(TOccupancyTypeVariant newOccupancyTypeVariant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__OCCUPANCY_TYPE_VARIANT, newOccupancyTypeVariant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOccupancyTypeVariant(TOccupancyTypeVariant newOccupancyTypeVariant) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__OCCUPANCY_TYPE_VARIANT, newOccupancyTypeVariant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOrientation getOrientationVariables() {
		return (TOrientation)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__ORIENTATION_VARIABLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOrientationVariables(TOrientation newOrientationVariables, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__ORIENTATION_VARIABLES, newOrientationVariables, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrientationVariables(TOrientation newOrientationVariables) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__ORIENTATION_VARIABLES, newOrientationVariables);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOrientationVariant getOrientationVariant() {
		return (TOrientationVariant)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__ORIENTATION_VARIANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOrientationVariant(TOrientationVariant newOrientationVariant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__ORIENTATION_VARIANT, newOrientationVariant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrientationVariant(TOrientationVariant newOrientationVariant) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__ORIENTATION_VARIANT, newOrientationVariant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TScheduleType getScheduleType() {
		return (TScheduleType)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__SCHEDULE_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScheduleType(TScheduleType newScheduleType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__SCHEDULE_TYPE, newScheduleType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScheduleType(TScheduleType newScheduleType) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__SCHEDULE_TYPE, newScheduleType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TScheduleTypeVariant getScheduleTypeVariant() {
		return (TScheduleTypeVariant)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__SCHEDULE_TYPE_VARIANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScheduleTypeVariant(TScheduleTypeVariant newScheduleTypeVariant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__SCHEDULE_TYPE_VARIANT, newScheduleTypeVariant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScheduleTypeVariant(TScheduleTypeVariant newScheduleTypeVariant) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__SCHEDULE_TYPE_VARIANT, newScheduleTypeVariant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSimulationMatrix getSimulationMatrix() {
		return (TSimulationMatrix)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__SIMULATION_MATRIX, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSimulationMatrix(TSimulationMatrix newSimulationMatrix, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__SIMULATION_MATRIX, newSimulationMatrix, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSimulationMatrix(TSimulationMatrix newSimulationMatrix) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__SIMULATION_MATRIX, newSimulationMatrix);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpace() {
		return (String)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__SPACE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpace(String newSpace) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__SPACE, newSpace);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSpaceGroup getSpaceGroup() {
		return (TSpaceGroup)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__SPACE_GROUP, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpaceGroup(TSpaceGroup newSpaceGroup, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__SPACE_GROUP, newSpaceGroup, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpaceGroup(TSpaceGroup newSpaceGroup) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__SPACE_GROUP, newSpaceGroup);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSpaces getSpaces() {
		return (TSpaces)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__SPACES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpaces(TSpaces newSpaces, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__SPACES, newSpaces, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpaces(TSpaces newSpaces) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__SPACES, newSpaces);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTarget getTarget() {
		return (TTarget)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__TARGET, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(TTarget newTarget, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__TARGET, newTarget, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(TTarget newTarget) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTargetList getTargetList() {
		return (TTargetList)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__TARGET_LIST, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetList(TTargetList newTargetList, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__TARGET_LIST, newTargetList, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetList(TTargetList newTargetList) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__TARGET_LIST, newTargetList);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTargets getTargets() {
		return (TTargets)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__TARGETS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargets(TTargets newTargets, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__TARGETS, newTargets, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargets(TTargets newTargets) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__TARGETS, newTargets);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TUsage getUsageTypeVariables() {
		return (TUsage)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__USAGE_TYPE_VARIABLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUsageTypeVariables(TUsage newUsageTypeVariables, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__USAGE_TYPE_VARIABLES, newUsageTypeVariables, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsageTypeVariables(TUsage newUsageTypeVariables) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__USAGE_TYPE_VARIABLES, newUsageTypeVariables);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TVariables getVariables() {
		return (TVariables)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__VARIABLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariables(TVariables newVariables, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__VARIABLES, newVariables, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariables(TVariables newVariables) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__VARIABLES, newVariables);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TVariant getVariant() {
		return (TVariant)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__VARIANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariant(TVariant newVariant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__VARIANT, newVariant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariant(TVariant newVariant) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__VARIANT, newVariant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWeatherDataSeriesVariant getWeatherDataSeriesVariant() {
		return (TWeatherDataSeriesVariant)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__WEATHER_DATA_SERIES_VARIANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWeatherDataSeriesVariant(TWeatherDataSeriesVariant newWeatherDataSeriesVariant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__WEATHER_DATA_SERIES_VARIANT, newWeatherDataSeriesVariant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeatherDataSeriesVariant(TWeatherDataSeriesVariant newWeatherDataSeriesVariant) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__WEATHER_DATA_SERIES_VARIANT, newWeatherDataSeriesVariant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWeatherDataSetVariant getWeatherDataSetVariant() {
		return (TWeatherDataSetVariant)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__WEATHER_DATA_SET_VARIANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWeatherDataSetVariant(TWeatherDataSetVariant newWeatherDataSetVariant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__WEATHER_DATA_SET_VARIANT, newWeatherDataSetVariant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeatherDataSetVariant(TWeatherDataSetVariant newWeatherDataSetVariant) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__WEATHER_DATA_SET_VARIANT, newWeatherDataSetVariant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWeather getWeatherVariables() {
		return (TWeather)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__WEATHER_VARIABLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWeatherVariables(TWeather newWeatherVariables, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__WEATHER_VARIABLES, newWeatherVariables, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeatherVariables(TWeather newWeatherVariables) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__WEATHER_VARIABLES, newWeatherVariables);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWindowType getWindowType() {
		return (TWindowType)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__WINDOW_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWindowType(TWindowType newWindowType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__WINDOW_TYPE, newWindowType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWindowType(TWindowType newWindowType) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__WINDOW_TYPE, newWindowType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWindowTypes getWindowTypeVariables() {
		return (TWindowTypes)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__WINDOW_TYPE_VARIABLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWindowTypeVariables(TWindowTypes newWindowTypeVariables, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__WINDOW_TYPE_VARIABLES, newWindowTypeVariables, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWindowTypeVariables(TWindowTypes newWindowTypeVariables) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__WINDOW_TYPE_VARIABLES, newWindowTypeVariables);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWindowTypeVariant getWindowTypeVariant() {
		return (TWindowTypeVariant)getMixed().get(simmatrixPackage.Literals.DOCUMENT_ROOT__WINDOW_TYPE_VARIANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWindowTypeVariant(TWindowTypeVariant newWindowTypeVariant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(simmatrixPackage.Literals.DOCUMENT_ROOT__WINDOW_TYPE_VARIANT, newWindowTypeVariant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWindowTypeVariant(TWindowTypeVariant newWindowTypeVariant) {
		((FeatureMap.Internal)getMixed()).set(simmatrixPackage.Literals.DOCUMENT_ROOT__WINDOW_TYPE_VARIANT, newWindowTypeVariant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__ASSIGNMENT_GROUPS:
				return basicSetAssignmentGroups(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__COMBINATION:
				return basicSetCombination(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__COMBINATIONS:
				return basicSetCombinations(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__CONSTANT_TYPE_VARIANT:
				return basicSetConstantTypeVariant(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE:
				return basicSetConstructionType(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIABLES:
				return basicSetConstructionTypeVariables(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIANT:
				return basicSetConstructionTypeVariant(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENT_GROUP:
				return basicSetElementGroup(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENTS:
				return basicSetElements(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__ELEVATION_VARIABLES:
				return basicSetElevationVariables(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__ELEVATION_VARIANT:
				return basicSetElevationVariant(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__LAYER:
				return basicSetLayer(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE:
				return basicSetMaterialType(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE_VARIABLES:
				return basicSetMaterialTypeVariables(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE_VARIANT:
				return basicSetMaterialTypeVariant(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__OCCUPANCY_TYPE:
				return basicSetOccupancyType(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__OCCUPANCY_TYPE_VARIANT:
				return basicSetOccupancyTypeVariant(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__ORIENTATION_VARIABLES:
				return basicSetOrientationVariables(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__ORIENTATION_VARIANT:
				return basicSetOrientationVariant(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__SCHEDULE_TYPE:
				return basicSetScheduleType(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__SCHEDULE_TYPE_VARIANT:
				return basicSetScheduleTypeVariant(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__SIMULATION_MATRIX:
				return basicSetSimulationMatrix(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__SPACE_GROUP:
				return basicSetSpaceGroup(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__SPACES:
				return basicSetSpaces(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__TARGET:
				return basicSetTarget(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__TARGET_LIST:
				return basicSetTargetList(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__TARGETS:
				return basicSetTargets(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__USAGE_TYPE_VARIABLES:
				return basicSetUsageTypeVariables(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__VARIABLES:
				return basicSetVariables(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__VARIANT:
				return basicSetVariant(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_DATA_SERIES_VARIANT:
				return basicSetWeatherDataSeriesVariant(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_DATA_SET_VARIANT:
				return basicSetWeatherDataSetVariant(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_VARIABLES:
				return basicSetWeatherVariables(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE:
				return basicSetWindowType(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE_VARIABLES:
				return basicSetWindowTypeVariables(null, msgs);
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE_VARIANT:
				return basicSetWindowTypeVariant(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case simmatrixPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case simmatrixPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case simmatrixPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case simmatrixPackage.DOCUMENT_ROOT__ASSIGNMENT_GROUPS:
				return getAssignmentGroups();
			case simmatrixPackage.DOCUMENT_ROOT__BIMREF:
				return getBIMREF();
			case simmatrixPackage.DOCUMENT_ROOT__COMBINATION:
				return getCombination();
			case simmatrixPackage.DOCUMENT_ROOT__COMBINATIONS:
				return getCombinations();
			case simmatrixPackage.DOCUMENT_ROOT__CONSTANT_TYPE_VARIANT:
				return getConstantTypeVariant();
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE:
				return getConstructionType();
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIABLES:
				return getConstructionTypeVariables();
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIANT:
				return getConstructionTypeVariant();
			case simmatrixPackage.DOCUMENT_ROOT__DOOR_TYPE_VARIABLES:
				return getDoorTypeVariables();
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENT:
				return getElement();
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENT_GROUP:
				return getElementGroup();
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENTS:
				return getElements();
			case simmatrixPackage.DOCUMENT_ROOT__ELEVATION_VARIABLES:
				return getElevationVariables();
			case simmatrixPackage.DOCUMENT_ROOT__ELEVATION_VARIANT:
				return getElevationVariant();
			case simmatrixPackage.DOCUMENT_ROOT__LAYER:
				return getLayer();
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE:
				return getMaterialType();
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE_VARIABLES:
				return getMaterialTypeVariables();
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE_VARIANT:
				return getMaterialTypeVariant();
			case simmatrixPackage.DOCUMENT_ROOT__OCCUPANCY_TYPE:
				return getOccupancyType();
			case simmatrixPackage.DOCUMENT_ROOT__OCCUPANCY_TYPE_VARIANT:
				return getOccupancyTypeVariant();
			case simmatrixPackage.DOCUMENT_ROOT__ORIENTATION_VARIABLES:
				return getOrientationVariables();
			case simmatrixPackage.DOCUMENT_ROOT__ORIENTATION_VARIANT:
				return getOrientationVariant();
			case simmatrixPackage.DOCUMENT_ROOT__SCHEDULE_TYPE:
				return getScheduleType();
			case simmatrixPackage.DOCUMENT_ROOT__SCHEDULE_TYPE_VARIANT:
				return getScheduleTypeVariant();
			case simmatrixPackage.DOCUMENT_ROOT__SIMULATION_MATRIX:
				return getSimulationMatrix();
			case simmatrixPackage.DOCUMENT_ROOT__SPACE:
				return getSpace();
			case simmatrixPackage.DOCUMENT_ROOT__SPACE_GROUP:
				return getSpaceGroup();
			case simmatrixPackage.DOCUMENT_ROOT__SPACES:
				return getSpaces();
			case simmatrixPackage.DOCUMENT_ROOT__TARGET:
				return getTarget();
			case simmatrixPackage.DOCUMENT_ROOT__TARGET_LIST:
				return getTargetList();
			case simmatrixPackage.DOCUMENT_ROOT__TARGETS:
				return getTargets();
			case simmatrixPackage.DOCUMENT_ROOT__USAGE_TYPE_VARIABLES:
				return getUsageTypeVariables();
			case simmatrixPackage.DOCUMENT_ROOT__VARIABLES:
				return getVariables();
			case simmatrixPackage.DOCUMENT_ROOT__VARIANT:
				return getVariant();
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_DATA_SERIES_VARIANT:
				return getWeatherDataSeriesVariant();
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_DATA_SET_VARIANT:
				return getWeatherDataSetVariant();
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_VARIABLES:
				return getWeatherVariables();
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE:
				return getWindowType();
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE_VARIABLES:
				return getWindowTypeVariables();
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE_VARIANT:
				return getWindowTypeVariant();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case simmatrixPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ASSIGNMENT_GROUPS:
				setAssignmentGroups((TAssignmentGroups)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__BIMREF:
				setBIMREF((String)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__COMBINATION:
				setCombination((TCombination)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__COMBINATIONS:
				setCombinations((TCombinations)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTANT_TYPE_VARIANT:
				setConstantTypeVariant((TConstantTypeVariant)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE:
				setConstructionType((TConstructionType)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIABLES:
				setConstructionTypeVariables((TConstructionTypes)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIANT:
				setConstructionTypeVariant((TConstructionTypeVariant)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__DOOR_TYPE_VARIABLES:
				setDoorTypeVariables((String)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENT:
				setElement((String)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENT_GROUP:
				setElementGroup((TElementGroup)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENTS:
				setElements((TElements)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ELEVATION_VARIABLES:
				setElevationVariables((TElevation)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ELEVATION_VARIANT:
				setElevationVariant((TElevationVariant)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__LAYER:
				setLayer((TLayer)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE:
				setMaterialType((TMaterialType)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE_VARIABLES:
				setMaterialTypeVariables((TMaterialTypes)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE_VARIANT:
				setMaterialTypeVariant((TMaterialTypeVariant)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__OCCUPANCY_TYPE:
				setOccupancyType((TOccupancyType)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__OCCUPANCY_TYPE_VARIANT:
				setOccupancyTypeVariant((TOccupancyTypeVariant)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ORIENTATION_VARIABLES:
				setOrientationVariables((TOrientation)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ORIENTATION_VARIANT:
				setOrientationVariant((TOrientationVariant)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SCHEDULE_TYPE:
				setScheduleType((TScheduleType)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SCHEDULE_TYPE_VARIANT:
				setScheduleTypeVariant((TScheduleTypeVariant)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SIMULATION_MATRIX:
				setSimulationMatrix((TSimulationMatrix)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SPACE:
				setSpace((String)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SPACE_GROUP:
				setSpaceGroup((TSpaceGroup)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SPACES:
				setSpaces((TSpaces)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__TARGET:
				setTarget((TTarget)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__TARGET_LIST:
				setTargetList((TTargetList)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__TARGETS:
				setTargets((TTargets)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__USAGE_TYPE_VARIABLES:
				setUsageTypeVariables((TUsage)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__VARIABLES:
				setVariables((TVariables)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__VARIANT:
				setVariant((TVariant)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_DATA_SERIES_VARIANT:
				setWeatherDataSeriesVariant((TWeatherDataSeriesVariant)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_DATA_SET_VARIANT:
				setWeatherDataSetVariant((TWeatherDataSetVariant)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_VARIABLES:
				setWeatherVariables((TWeather)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE:
				setWindowType((TWindowType)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE_VARIABLES:
				setWindowTypeVariables((TWindowTypes)newValue);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE_VARIANT:
				setWindowTypeVariant((TWindowTypeVariant)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case simmatrixPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case simmatrixPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case simmatrixPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ASSIGNMENT_GROUPS:
				setAssignmentGroups((TAssignmentGroups)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__BIMREF:
				setBIMREF(BIMREF_EDEFAULT);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__COMBINATION:
				setCombination((TCombination)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__COMBINATIONS:
				setCombinations((TCombinations)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTANT_TYPE_VARIANT:
				setConstantTypeVariant((TConstantTypeVariant)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE:
				setConstructionType((TConstructionType)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIABLES:
				setConstructionTypeVariables((TConstructionTypes)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIANT:
				setConstructionTypeVariant((TConstructionTypeVariant)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__DOOR_TYPE_VARIABLES:
				setDoorTypeVariables(DOOR_TYPE_VARIABLES_EDEFAULT);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENT:
				setElement(ELEMENT_EDEFAULT);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENT_GROUP:
				setElementGroup((TElementGroup)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENTS:
				setElements((TElements)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ELEVATION_VARIABLES:
				setElevationVariables((TElevation)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ELEVATION_VARIANT:
				setElevationVariant((TElevationVariant)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__LAYER:
				setLayer((TLayer)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE:
				setMaterialType((TMaterialType)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE_VARIABLES:
				setMaterialTypeVariables((TMaterialTypes)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE_VARIANT:
				setMaterialTypeVariant((TMaterialTypeVariant)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__OCCUPANCY_TYPE:
				setOccupancyType((TOccupancyType)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__OCCUPANCY_TYPE_VARIANT:
				setOccupancyTypeVariant((TOccupancyTypeVariant)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ORIENTATION_VARIABLES:
				setOrientationVariables((TOrientation)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__ORIENTATION_VARIANT:
				setOrientationVariant((TOrientationVariant)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SCHEDULE_TYPE:
				setScheduleType((TScheduleType)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SCHEDULE_TYPE_VARIANT:
				setScheduleTypeVariant((TScheduleTypeVariant)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SIMULATION_MATRIX:
				setSimulationMatrix((TSimulationMatrix)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SPACE:
				setSpace(SPACE_EDEFAULT);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SPACE_GROUP:
				setSpaceGroup((TSpaceGroup)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__SPACES:
				setSpaces((TSpaces)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__TARGET:
				setTarget((TTarget)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__TARGET_LIST:
				setTargetList((TTargetList)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__TARGETS:
				setTargets((TTargets)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__USAGE_TYPE_VARIABLES:
				setUsageTypeVariables((TUsage)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__VARIABLES:
				setVariables((TVariables)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__VARIANT:
				setVariant((TVariant)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_DATA_SERIES_VARIANT:
				setWeatherDataSeriesVariant((TWeatherDataSeriesVariant)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_DATA_SET_VARIANT:
				setWeatherDataSetVariant((TWeatherDataSetVariant)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_VARIABLES:
				setWeatherVariables((TWeather)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE:
				setWindowType((TWindowType)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE_VARIABLES:
				setWindowTypeVariables((TWindowTypes)null);
				return;
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE_VARIANT:
				setWindowTypeVariant((TWindowTypeVariant)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case simmatrixPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case simmatrixPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case simmatrixPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case simmatrixPackage.DOCUMENT_ROOT__ASSIGNMENT_GROUPS:
				return getAssignmentGroups() != null;
			case simmatrixPackage.DOCUMENT_ROOT__BIMREF:
				return BIMREF_EDEFAULT == null ? getBIMREF() != null : !BIMREF_EDEFAULT.equals(getBIMREF());
			case simmatrixPackage.DOCUMENT_ROOT__COMBINATION:
				return getCombination() != null;
			case simmatrixPackage.DOCUMENT_ROOT__COMBINATIONS:
				return getCombinations() != null;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTANT_TYPE_VARIANT:
				return getConstantTypeVariant() != null;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE:
				return getConstructionType() != null;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIABLES:
				return getConstructionTypeVariables() != null;
			case simmatrixPackage.DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIANT:
				return getConstructionTypeVariant() != null;
			case simmatrixPackage.DOCUMENT_ROOT__DOOR_TYPE_VARIABLES:
				return DOOR_TYPE_VARIABLES_EDEFAULT == null ? getDoorTypeVariables() != null : !DOOR_TYPE_VARIABLES_EDEFAULT.equals(getDoorTypeVariables());
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENT:
				return ELEMENT_EDEFAULT == null ? getElement() != null : !ELEMENT_EDEFAULT.equals(getElement());
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENT_GROUP:
				return getElementGroup() != null;
			case simmatrixPackage.DOCUMENT_ROOT__ELEMENTS:
				return getElements() != null;
			case simmatrixPackage.DOCUMENT_ROOT__ELEVATION_VARIABLES:
				return getElevationVariables() != null;
			case simmatrixPackage.DOCUMENT_ROOT__ELEVATION_VARIANT:
				return getElevationVariant() != null;
			case simmatrixPackage.DOCUMENT_ROOT__LAYER:
				return getLayer() != null;
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE:
				return getMaterialType() != null;
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE_VARIABLES:
				return getMaterialTypeVariables() != null;
			case simmatrixPackage.DOCUMENT_ROOT__MATERIAL_TYPE_VARIANT:
				return getMaterialTypeVariant() != null;
			case simmatrixPackage.DOCUMENT_ROOT__OCCUPANCY_TYPE:
				return getOccupancyType() != null;
			case simmatrixPackage.DOCUMENT_ROOT__OCCUPANCY_TYPE_VARIANT:
				return getOccupancyTypeVariant() != null;
			case simmatrixPackage.DOCUMENT_ROOT__ORIENTATION_VARIABLES:
				return getOrientationVariables() != null;
			case simmatrixPackage.DOCUMENT_ROOT__ORIENTATION_VARIANT:
				return getOrientationVariant() != null;
			case simmatrixPackage.DOCUMENT_ROOT__SCHEDULE_TYPE:
				return getScheduleType() != null;
			case simmatrixPackage.DOCUMENT_ROOT__SCHEDULE_TYPE_VARIANT:
				return getScheduleTypeVariant() != null;
			case simmatrixPackage.DOCUMENT_ROOT__SIMULATION_MATRIX:
				return getSimulationMatrix() != null;
			case simmatrixPackage.DOCUMENT_ROOT__SPACE:
				return SPACE_EDEFAULT == null ? getSpace() != null : !SPACE_EDEFAULT.equals(getSpace());
			case simmatrixPackage.DOCUMENT_ROOT__SPACE_GROUP:
				return getSpaceGroup() != null;
			case simmatrixPackage.DOCUMENT_ROOT__SPACES:
				return getSpaces() != null;
			case simmatrixPackage.DOCUMENT_ROOT__TARGET:
				return getTarget() != null;
			case simmatrixPackage.DOCUMENT_ROOT__TARGET_LIST:
				return getTargetList() != null;
			case simmatrixPackage.DOCUMENT_ROOT__TARGETS:
				return getTargets() != null;
			case simmatrixPackage.DOCUMENT_ROOT__USAGE_TYPE_VARIABLES:
				return getUsageTypeVariables() != null;
			case simmatrixPackage.DOCUMENT_ROOT__VARIABLES:
				return getVariables() != null;
			case simmatrixPackage.DOCUMENT_ROOT__VARIANT:
				return getVariant() != null;
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_DATA_SERIES_VARIANT:
				return getWeatherDataSeriesVariant() != null;
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_DATA_SET_VARIANT:
				return getWeatherDataSetVariant() != null;
			case simmatrixPackage.DOCUMENT_ROOT__WEATHER_VARIABLES:
				return getWeatherVariables() != null;
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE:
				return getWindowType() != null;
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE_VARIABLES:
				return getWindowTypeVariables() != null;
			case simmatrixPackage.DOCUMENT_ROOT__WINDOW_TYPE_VARIANT:
				return getWindowTypeVariant() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
