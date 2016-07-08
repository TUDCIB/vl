/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getAssignmentGroups <em>Assignment Groups</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getBIMREF <em>BIMREF</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getCombination <em>Combination</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getCombinations <em>Combinations</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstantTypeVariant <em>Constant Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionType <em>Construction Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionTypeVariables <em>Construction Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionTypeVariant <em>Construction Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getDoorTypeVariables <em>Door Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElement <em>Element</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElementGroup <em>Element Group</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElements <em>Elements</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElevationVariables <em>Elevation Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElevationVariant <em>Elevation Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getLayer <em>Layer</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialType <em>Material Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialTypeVariables <em>Material Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialTypeVariant <em>Material Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOccupancyType <em>Occupancy Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOccupancyTypeVariant <em>Occupancy Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOrientationVariables <em>Orientation Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOrientationVariant <em>Orientation Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getScheduleType <em>Schedule Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getScheduleTypeVariant <em>Schedule Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSimulationMatrix <em>Simulation Matrix</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpace <em>Space</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpaceGroup <em>Space Group</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpaces <em>Spaces</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getTarget <em>Target</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getTargetList <em>Target List</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getTargets <em>Targets</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getUsageTypeVariables <em>Usage Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getVariables <em>Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getVariant <em>Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherDataSeriesVariant <em>Weather Data Series Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherDataSetVariant <em>Weather Data Set Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherVariables <em>Weather Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowType <em>Window Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowTypeVariables <em>Window Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowTypeVariant <em>Window Type Variant</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Assignment Groups</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assignment Groups</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assignment Groups</em>' containment reference.
	 * @see #setAssignmentGroups(TAssignmentGroups)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_AssignmentGroups()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='AssignmentGroups' namespace='##targetNamespace'"
	 * @generated
	 */
	TAssignmentGroups getAssignmentGroups();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getAssignmentGroups <em>Assignment Groups</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assignment Groups</em>' containment reference.
	 * @see #getAssignmentGroups()
	 * @generated
	 */
	void setAssignmentGroups(TAssignmentGroups value);

	/**
	 * Returns the value of the '<em><b>BIMREF</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>BIMREF</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>BIMREF</em>' attribute.
	 * @see #setBIMREF(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_BIMREF()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='BIMREF' namespace='##targetNamespace'"
	 * @generated
	 */
	String getBIMREF();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getBIMREF <em>BIMREF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>BIMREF</em>' attribute.
	 * @see #getBIMREF()
	 * @generated
	 */
	void setBIMREF(String value);

	/**
	 * Returns the value of the '<em><b>Combination</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Combination</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Combination</em>' containment reference.
	 * @see #setCombination(TCombination)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Combination()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Combination' namespace='##targetNamespace'"
	 * @generated
	 */
	TCombination getCombination();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getCombination <em>Combination</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Combination</em>' containment reference.
	 * @see #getCombination()
	 * @generated
	 */
	void setCombination(TCombination value);

	/**
	 * Returns the value of the '<em><b>Combinations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Combinations</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Combinations</em>' containment reference.
	 * @see #setCombinations(TCombinations)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Combinations()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Combinations' namespace='##targetNamespace'"
	 * @generated
	 */
	TCombinations getCombinations();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getCombinations <em>Combinations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Combinations</em>' containment reference.
	 * @see #getCombinations()
	 * @generated
	 */
	void setCombinations(TCombinations value);

	/**
	 * Returns the value of the '<em><b>Constant Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constant Type Variant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constant Type Variant</em>' containment reference.
	 * @see #setConstantTypeVariant(TConstantTypeVariant)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_ConstantTypeVariant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ConstantTypeVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	TConstantTypeVariant getConstantTypeVariant();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstantTypeVariant <em>Constant Type Variant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constant Type Variant</em>' containment reference.
	 * @see #getConstantTypeVariant()
	 * @generated
	 */
	void setConstantTypeVariant(TConstantTypeVariant value);

	/**
	 * Returns the value of the '<em><b>Construction Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Construction Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Construction Type</em>' containment reference.
	 * @see #setConstructionType(TConstructionType)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_ConstructionType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ConstructionType' namespace='##targetNamespace'"
	 * @generated
	 */
	TConstructionType getConstructionType();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionType <em>Construction Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Construction Type</em>' containment reference.
	 * @see #getConstructionType()
	 * @generated
	 */
	void setConstructionType(TConstructionType value);

	/**
	 * Returns the value of the '<em><b>Construction Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Construction Type Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Construction Type Variables</em>' containment reference.
	 * @see #setConstructionTypeVariables(TConstructionTypes)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_ConstructionTypeVariables()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ConstructionTypeVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TConstructionTypes getConstructionTypeVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionTypeVariables <em>Construction Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Construction Type Variables</em>' containment reference.
	 * @see #getConstructionTypeVariables()
	 * @generated
	 */
	void setConstructionTypeVariables(TConstructionTypes value);

	/**
	 * Returns the value of the '<em><b>Construction Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Construction Type Variant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Construction Type Variant</em>' containment reference.
	 * @see #setConstructionTypeVariant(TConstructionTypeVariant)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_ConstructionTypeVariant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ConstructionTypeVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	TConstructionTypeVariant getConstructionTypeVariant();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getConstructionTypeVariant <em>Construction Type Variant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Construction Type Variant</em>' containment reference.
	 * @see #getConstructionTypeVariant()
	 * @generated
	 */
	void setConstructionTypeVariant(TConstructionTypeVariant value);

	/**
	 * Returns the value of the '<em><b>Door Type Variables</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Door Type Variables</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Door Type Variables</em>' attribute.
	 * @see #setDoorTypeVariables(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_DoorTypeVariables()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='DoorTypeVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDoorTypeVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getDoorTypeVariables <em>Door Type Variables</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Door Type Variables</em>' attribute.
	 * @see #getDoorTypeVariables()
	 * @generated
	 */
	void setDoorTypeVariables(String value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' attribute.
	 * @see #setElement(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Element()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Element' namespace='##targetNamespace'"
	 * @generated
	 */
	String getElement();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElement <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' attribute.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(String value);

	/**
	 * Returns the value of the '<em><b>Element Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Group</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Group</em>' containment reference.
	 * @see #setElementGroup(TElementGroup)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_ElementGroup()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ElementGroup' namespace='##targetNamespace'"
	 * @generated
	 */
	TElementGroup getElementGroup();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElementGroup <em>Element Group</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Group</em>' containment reference.
	 * @see #getElementGroup()
	 * @generated
	 */
	void setElementGroup(TElementGroup value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference.
	 * @see #setElements(TElements)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Elements()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Elements' namespace='##targetNamespace'"
	 * @generated
	 */
	TElements getElements();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElements <em>Elements</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elements</em>' containment reference.
	 * @see #getElements()
	 * @generated
	 */
	void setElements(TElements value);

	/**
	 * Returns the value of the '<em><b>Elevation Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elevation Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elevation Variables</em>' containment reference.
	 * @see #setElevationVariables(TElevation)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_ElevationVariables()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ElevationVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TElevation getElevationVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElevationVariables <em>Elevation Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elevation Variables</em>' containment reference.
	 * @see #getElevationVariables()
	 * @generated
	 */
	void setElevationVariables(TElevation value);

	/**
	 * Returns the value of the '<em><b>Elevation Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elevation Variant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elevation Variant</em>' containment reference.
	 * @see #setElevationVariant(TElevationVariant)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_ElevationVariant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ElevationVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	TElevationVariant getElevationVariant();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getElevationVariant <em>Elevation Variant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elevation Variant</em>' containment reference.
	 * @see #getElevationVariant()
	 * @generated
	 */
	void setElevationVariant(TElevationVariant value);

	/**
	 * Returns the value of the '<em><b>Layer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layer</em>' containment reference.
	 * @see #setLayer(TLayer)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Layer()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Layer' namespace='##targetNamespace'"
	 * @generated
	 */
	TLayer getLayer();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getLayer <em>Layer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Layer</em>' containment reference.
	 * @see #getLayer()
	 * @generated
	 */
	void setLayer(TLayer value);

	/**
	 * Returns the value of the '<em><b>Material Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Material Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Material Type</em>' containment reference.
	 * @see #setMaterialType(TMaterialType)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_MaterialType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MaterialType' namespace='##targetNamespace'"
	 * @generated
	 */
	TMaterialType getMaterialType();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialType <em>Material Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Material Type</em>' containment reference.
	 * @see #getMaterialType()
	 * @generated
	 */
	void setMaterialType(TMaterialType value);

	/**
	 * Returns the value of the '<em><b>Material Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Material Type Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Material Type Variables</em>' containment reference.
	 * @see #setMaterialTypeVariables(TMaterialTypes)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_MaterialTypeVariables()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MaterialTypeVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TMaterialTypes getMaterialTypeVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialTypeVariables <em>Material Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Material Type Variables</em>' containment reference.
	 * @see #getMaterialTypeVariables()
	 * @generated
	 */
	void setMaterialTypeVariables(TMaterialTypes value);

	/**
	 * Returns the value of the '<em><b>Material Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Material Type Variant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Material Type Variant</em>' containment reference.
	 * @see #setMaterialTypeVariant(TMaterialTypeVariant)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_MaterialTypeVariant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MaterialTypeVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	TMaterialTypeVariant getMaterialTypeVariant();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getMaterialTypeVariant <em>Material Type Variant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Material Type Variant</em>' containment reference.
	 * @see #getMaterialTypeVariant()
	 * @generated
	 */
	void setMaterialTypeVariant(TMaterialTypeVariant value);

	/**
	 * Returns the value of the '<em><b>Occupancy Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Occupancy Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Occupancy Type</em>' containment reference.
	 * @see #setOccupancyType(TOccupancyType)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_OccupancyType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='OccupancyType' namespace='##targetNamespace'"
	 * @generated
	 */
	TOccupancyType getOccupancyType();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOccupancyType <em>Occupancy Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Occupancy Type</em>' containment reference.
	 * @see #getOccupancyType()
	 * @generated
	 */
	void setOccupancyType(TOccupancyType value);

	/**
	 * Returns the value of the '<em><b>Occupancy Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Occupancy Type Variant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Occupancy Type Variant</em>' containment reference.
	 * @see #setOccupancyTypeVariant(TOccupancyTypeVariant)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_OccupancyTypeVariant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='OccupancyTypeVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	TOccupancyTypeVariant getOccupancyTypeVariant();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOccupancyTypeVariant <em>Occupancy Type Variant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Occupancy Type Variant</em>' containment reference.
	 * @see #getOccupancyTypeVariant()
	 * @generated
	 */
	void setOccupancyTypeVariant(TOccupancyTypeVariant value);

	/**
	 * Returns the value of the '<em><b>Orientation Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orientation Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orientation Variables</em>' containment reference.
	 * @see #setOrientationVariables(TOrientation)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_OrientationVariables()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='OrientationVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TOrientation getOrientationVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOrientationVariables <em>Orientation Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orientation Variables</em>' containment reference.
	 * @see #getOrientationVariables()
	 * @generated
	 */
	void setOrientationVariables(TOrientation value);

	/**
	 * Returns the value of the '<em><b>Orientation Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orientation Variant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orientation Variant</em>' containment reference.
	 * @see #setOrientationVariant(TOrientationVariant)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_OrientationVariant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='OrientationVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	TOrientationVariant getOrientationVariant();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getOrientationVariant <em>Orientation Variant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orientation Variant</em>' containment reference.
	 * @see #getOrientationVariant()
	 * @generated
	 */
	void setOrientationVariant(TOrientationVariant value);

	/**
	 * Returns the value of the '<em><b>Schedule Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schedule Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schedule Type</em>' containment reference.
	 * @see #setScheduleType(TScheduleType)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_ScheduleType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ScheduleType' namespace='##targetNamespace'"
	 * @generated
	 */
	TScheduleType getScheduleType();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getScheduleType <em>Schedule Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schedule Type</em>' containment reference.
	 * @see #getScheduleType()
	 * @generated
	 */
	void setScheduleType(TScheduleType value);

	/**
	 * Returns the value of the '<em><b>Schedule Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schedule Type Variant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schedule Type Variant</em>' containment reference.
	 * @see #setScheduleTypeVariant(TScheduleTypeVariant)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_ScheduleTypeVariant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ScheduleTypeVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	TScheduleTypeVariant getScheduleTypeVariant();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getScheduleTypeVariant <em>Schedule Type Variant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schedule Type Variant</em>' containment reference.
	 * @see #getScheduleTypeVariant()
	 * @generated
	 */
	void setScheduleTypeVariant(TScheduleTypeVariant value);

	/**
	 * Returns the value of the '<em><b>Simulation Matrix</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Simulation Matrix</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simulation Matrix</em>' containment reference.
	 * @see #setSimulationMatrix(TSimulationMatrix)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_SimulationMatrix()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='SimulationMatrix' namespace='##targetNamespace'"
	 * @generated
	 */
	TSimulationMatrix getSimulationMatrix();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSimulationMatrix <em>Simulation Matrix</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simulation Matrix</em>' containment reference.
	 * @see #getSimulationMatrix()
	 * @generated
	 */
	void setSimulationMatrix(TSimulationMatrix value);

	/**
	 * Returns the value of the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space</em>' attribute.
	 * @see #setSpace(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Space()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Space' namespace='##targetNamespace'"
	 * @generated
	 */
	String getSpace();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpace <em>Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space</em>' attribute.
	 * @see #getSpace()
	 * @generated
	 */
	void setSpace(String value);

	/**
	 * Returns the value of the '<em><b>Space Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space Group</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space Group</em>' containment reference.
	 * @see #setSpaceGroup(TSpaceGroup)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_SpaceGroup()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='SpaceGroup' namespace='##targetNamespace'"
	 * @generated
	 */
	TSpaceGroup getSpaceGroup();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpaceGroup <em>Space Group</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space Group</em>' containment reference.
	 * @see #getSpaceGroup()
	 * @generated
	 */
	void setSpaceGroup(TSpaceGroup value);

	/**
	 * Returns the value of the '<em><b>Spaces</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spaces</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spaces</em>' containment reference.
	 * @see #setSpaces(TSpaces)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Spaces()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Spaces' namespace='##targetNamespace'"
	 * @generated
	 */
	TSpaces getSpaces();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getSpaces <em>Spaces</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spaces</em>' containment reference.
	 * @see #getSpaces()
	 * @generated
	 */
	void setSpaces(TSpaces value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' containment reference.
	 * @see #setTarget(TTarget)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Target()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Target' namespace='##targetNamespace'"
	 * @generated
	 */
	TTarget getTarget();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getTarget <em>Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' containment reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(TTarget value);

	/**
	 * Returns the value of the '<em><b>Target List</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target List</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target List</em>' containment reference.
	 * @see #setTargetList(TTargetList)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_TargetList()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='TargetList' namespace='##targetNamespace'"
	 * @generated
	 */
	TTargetList getTargetList();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getTargetList <em>Target List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target List</em>' containment reference.
	 * @see #getTargetList()
	 * @generated
	 */
	void setTargetList(TTargetList value);

	/**
	 * Returns the value of the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Targets</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Targets</em>' containment reference.
	 * @see #setTargets(TTargets)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Targets()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Targets' namespace='##targetNamespace'"
	 * @generated
	 */
	TTargets getTargets();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getTargets <em>Targets</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Targets</em>' containment reference.
	 * @see #getTargets()
	 * @generated
	 */
	void setTargets(TTargets value);

	/**
	 * Returns the value of the '<em><b>Usage Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usage Type Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usage Type Variables</em>' containment reference.
	 * @see #setUsageTypeVariables(TUsage)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_UsageTypeVariables()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='UsageTypeVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TUsage getUsageTypeVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getUsageTypeVariables <em>Usage Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Usage Type Variables</em>' containment reference.
	 * @see #getUsageTypeVariables()
	 * @generated
	 */
	void setUsageTypeVariables(TUsage value);

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference.
	 * @see #setVariables(TVariables)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Variables()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Variables' namespace='##targetNamespace'"
	 * @generated
	 */
	TVariables getVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getVariables <em>Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variables</em>' containment reference.
	 * @see #getVariables()
	 * @generated
	 */
	void setVariables(TVariables value);

	/**
	 * Returns the value of the '<em><b>Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variant</em>' containment reference.
	 * @see #setVariant(TVariant)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_Variant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Variant' namespace='##targetNamespace'"
	 * @generated
	 */
	TVariant getVariant();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getVariant <em>Variant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variant</em>' containment reference.
	 * @see #getVariant()
	 * @generated
	 */
	void setVariant(TVariant value);

	/**
	 * Returns the value of the '<em><b>Weather Data Series Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weather Data Series Variant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weather Data Series Variant</em>' containment reference.
	 * @see #setWeatherDataSeriesVariant(TWeatherDataSeriesVariant)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_WeatherDataSeriesVariant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='WeatherDataSeriesVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	TWeatherDataSeriesVariant getWeatherDataSeriesVariant();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherDataSeriesVariant <em>Weather Data Series Variant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weather Data Series Variant</em>' containment reference.
	 * @see #getWeatherDataSeriesVariant()
	 * @generated
	 */
	void setWeatherDataSeriesVariant(TWeatherDataSeriesVariant value);

	/**
	 * Returns the value of the '<em><b>Weather Data Set Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weather Data Set Variant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weather Data Set Variant</em>' containment reference.
	 * @see #setWeatherDataSetVariant(TWeatherDataSetVariant)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_WeatherDataSetVariant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='WeatherDataSetVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	TWeatherDataSetVariant getWeatherDataSetVariant();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherDataSetVariant <em>Weather Data Set Variant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weather Data Set Variant</em>' containment reference.
	 * @see #getWeatherDataSetVariant()
	 * @generated
	 */
	void setWeatherDataSetVariant(TWeatherDataSetVariant value);

	/**
	 * Returns the value of the '<em><b>Weather Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weather Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weather Variables</em>' containment reference.
	 * @see #setWeatherVariables(TWeather)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_WeatherVariables()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='WeatherVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TWeather getWeatherVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWeatherVariables <em>Weather Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weather Variables</em>' containment reference.
	 * @see #getWeatherVariables()
	 * @generated
	 */
	void setWeatherVariables(TWeather value);

	/**
	 * Returns the value of the '<em><b>Window Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Window Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Window Type</em>' containment reference.
	 * @see #setWindowType(TWindowType)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_WindowType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='WindowType' namespace='##targetNamespace'"
	 * @generated
	 */
	TWindowType getWindowType();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowType <em>Window Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Window Type</em>' containment reference.
	 * @see #getWindowType()
	 * @generated
	 */
	void setWindowType(TWindowType value);

	/**
	 * Returns the value of the '<em><b>Window Type Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Window Type Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Window Type Variables</em>' containment reference.
	 * @see #setWindowTypeVariables(TWindowTypes)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_WindowTypeVariables()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='WindowTypeVariables' namespace='##targetNamespace'"
	 * @generated
	 */
	TWindowTypes getWindowTypeVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowTypeVariables <em>Window Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Window Type Variables</em>' containment reference.
	 * @see #getWindowTypeVariables()
	 * @generated
	 */
	void setWindowTypeVariables(TWindowTypes value);

	/**
	 * Returns the value of the '<em><b>Window Type Variant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Window Type Variant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Window Type Variant</em>' containment reference.
	 * @see #setWindowTypeVariant(TWindowTypeVariant)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getDocumentRoot_WindowTypeVariant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='WindowTypeVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	TWindowTypeVariant getWindowTypeVariant();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot#getWindowTypeVariant <em>Window Type Variant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Window Type Variant</em>' containment reference.
	 * @see #getWindowTypeVariant()
	 * @generated
	 */
	void setWindowTypeVariant(TWindowTypeVariant value);

} // DocumentRoot
