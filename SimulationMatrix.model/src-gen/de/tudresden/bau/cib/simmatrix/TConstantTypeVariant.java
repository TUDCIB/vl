/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TConstant Type Variant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getHeatingSetPoint <em>Heating Set Point</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getCoolingSetPoint <em>Cooling Set Point</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getPersonLoad <em>Person Load</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getEquipmentLoad <em>Equipment Load</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getShading <em>Shading</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstantTypeVariant()
 * @model extendedMetaData="name='T_ConstantTypeVariant' kind='elementOnly'"
 * @generated
 */
public interface TConstantTypeVariant extends EObject {
	/**
	 * Returns the value of the '<em><b>Heating Set Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Heating Set Point</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Heating Set Point</em>' containment reference.
	 * @see #setHeatingSetPoint(TSetPoint)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstantTypeVariant_HeatingSetPoint()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='HeatingSetPoint' namespace='##targetNamespace'"
	 * @generated
	 */
	TSetPoint getHeatingSetPoint();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getHeatingSetPoint <em>Heating Set Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Heating Set Point</em>' containment reference.
	 * @see #getHeatingSetPoint()
	 * @generated
	 */
	void setHeatingSetPoint(TSetPoint value);

	/**
	 * Returns the value of the '<em><b>Cooling Set Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cooling Set Point</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cooling Set Point</em>' containment reference.
	 * @see #setCoolingSetPoint(TSetPoint)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstantTypeVariant_CoolingSetPoint()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='CoolingSetPoint' namespace='##targetNamespace'"
	 * @generated
	 */
	TSetPoint getCoolingSetPoint();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getCoolingSetPoint <em>Cooling Set Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cooling Set Point</em>' containment reference.
	 * @see #getCoolingSetPoint()
	 * @generated
	 */
	void setCoolingSetPoint(TSetPoint value);

	/**
	 * Returns the value of the '<em><b>Person Load</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Person Load</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Person Load</em>' containment reference.
	 * @see #setPersonLoad(TSetPoint)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstantTypeVariant_PersonLoad()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='PersonLoad' namespace='##targetNamespace'"
	 * @generated
	 */
	TSetPoint getPersonLoad();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getPersonLoad <em>Person Load</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Person Load</em>' containment reference.
	 * @see #getPersonLoad()
	 * @generated
	 */
	void setPersonLoad(TSetPoint value);

	/**
	 * Returns the value of the '<em><b>Equipment Load</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Equipment Load</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Equipment Load</em>' containment reference.
	 * @see #setEquipmentLoad(TSetPoint)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstantTypeVariant_EquipmentLoad()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='EquipmentLoad' namespace='##targetNamespace'"
	 * @generated
	 */
	TSetPoint getEquipmentLoad();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getEquipmentLoad <em>Equipment Load</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Equipment Load</em>' containment reference.
	 * @see #getEquipmentLoad()
	 * @generated
	 */
	void setEquipmentLoad(TSetPoint value);

	/**
	 * Returns the value of the '<em><b>Shading</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shading</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shading</em>' containment reference.
	 * @see #setShading(TSetPoint)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstantTypeVariant_Shading()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Shading' namespace='##targetNamespace'"
	 * @generated
	 */
	TSetPoint getShading();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getShading <em>Shading</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shading</em>' containment reference.
	 * @see #getShading()
	 * @generated
	 */
	void setShading(TSetPoint value);

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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstantTypeVariant_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // TConstantTypeVariant
