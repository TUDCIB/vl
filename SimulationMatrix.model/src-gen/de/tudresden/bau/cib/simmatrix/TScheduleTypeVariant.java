/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TSchedule Type Variant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getHeatingSetPoint <em>Heating Set Point</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getCoolingSetPoint <em>Cooling Set Point</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getPersonLoad <em>Person Load</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getEquipmentLoad <em>Equipment Load</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getShading <em>Shading</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTScheduleTypeVariant()
 * @model extendedMetaData="name='T_ScheduleTypeVariant' kind='elementOnly'"
 * @generated
 */
public interface TScheduleTypeVariant extends EObject {
	/**
	 * Returns the value of the '<em><b>Heating Set Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Heating Set Point</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Heating Set Point</em>' containment reference.
	 * @see #setHeatingSetPoint(TSetTemperature)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTScheduleTypeVariant_HeatingSetPoint()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='HeatingSetPoint' namespace='##targetNamespace'"
	 * @generated
	 */
	TSetTemperature getHeatingSetPoint();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getHeatingSetPoint <em>Heating Set Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Heating Set Point</em>' containment reference.
	 * @see #getHeatingSetPoint()
	 * @generated
	 */
	void setHeatingSetPoint(TSetTemperature value);

	/**
	 * Returns the value of the '<em><b>Cooling Set Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cooling Set Point</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cooling Set Point</em>' containment reference.
	 * @see #setCoolingSetPoint(TSetTemperature)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTScheduleTypeVariant_CoolingSetPoint()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='CoolingSetPoint' namespace='##targetNamespace'"
	 * @generated
	 */
	TSetTemperature getCoolingSetPoint();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getCoolingSetPoint <em>Cooling Set Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cooling Set Point</em>' containment reference.
	 * @see #getCoolingSetPoint()
	 * @generated
	 */
	void setCoolingSetPoint(TSetTemperature value);

	/**
	 * Returns the value of the '<em><b>Person Load</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Person Load</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Person Load</em>' containment reference.
	 * @see #setPersonLoad(TSetPersonLoads)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTScheduleTypeVariant_PersonLoad()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='PersonLoad' namespace='##targetNamespace'"
	 * @generated
	 */
	TSetPersonLoads getPersonLoad();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getPersonLoad <em>Person Load</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Person Load</em>' containment reference.
	 * @see #getPersonLoad()
	 * @generated
	 */
	void setPersonLoad(TSetPersonLoads value);

	/**
	 * Returns the value of the '<em><b>Equipment Load</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Equipment Load</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Equipment Load</em>' containment reference.
	 * @see #setEquipmentLoad(TSetPersonLoads)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTScheduleTypeVariant_EquipmentLoad()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='EquipmentLoad' namespace='##targetNamespace'"
	 * @generated
	 */
	TSetPersonLoads getEquipmentLoad();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getEquipmentLoad <em>Equipment Load</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Equipment Load</em>' containment reference.
	 * @see #getEquipmentLoad()
	 * @generated
	 */
	void setEquipmentLoad(TSetPersonLoads value);

	/**
	 * Returns the value of the '<em><b>Shading</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shading</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shading</em>' containment reference.
	 * @see #setShading(TSetShading)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTScheduleTypeVariant_Shading()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Shading' namespace='##targetNamespace'"
	 * @generated
	 */
	TSetShading getShading();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getShading <em>Shading</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shading</em>' containment reference.
	 * @see #getShading()
	 * @generated
	 */
	void setShading(TSetShading value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTScheduleTypeVariant_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // TScheduleTypeVariant
