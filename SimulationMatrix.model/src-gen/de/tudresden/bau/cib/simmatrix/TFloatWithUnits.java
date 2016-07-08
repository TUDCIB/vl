/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TFloat With Units</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getValue <em>Value</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getUnit <em>Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTFloatWithUnits()
 * @model extendedMetaData="name='T_FloatWithUnits' kind='simple'"
 * @generated
 */
public interface TFloatWithUnits extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #isSetValue()
	 * @see #unsetValue()
	 * @see #setValue(double)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTFloatWithUnits_Value()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double"
	 *        extendedMetaData="name=':0' kind='simple'"
	 * @generated
	 */
	double getValue();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #isSetValue()
	 * @see #unsetValue()
	 * @see #getValue()
	 * @generated
	 */
	void setValue(double value);

	/**
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetValue()
	 * @see #getValue()
	 * @see #setValue(double)
	 * @generated
	 */
	void unsetValue();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getValue <em>Value</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Value</em>' attribute is set.
	 * @see #unsetValue()
	 * @see #getValue()
	 * @see #setValue(double)
	 * @generated
	 */
	boolean isSetValue();

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * The literals are from the enumeration {@link de.tudresden.bau.cib.simmatrix.MaterialUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.MaterialUnit
	 * @see #isSetUnit()
	 * @see #unsetUnit()
	 * @see #setUnit(MaterialUnit)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTFloatWithUnits_Unit()
	 * @model unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='unit' namespace='##targetNamespace'"
	 * @generated
	 */
	MaterialUnit getUnit();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.MaterialUnit
	 * @see #isSetUnit()
	 * @see #unsetUnit()
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(MaterialUnit value);

	/**
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUnit()
	 * @see #getUnit()
	 * @see #setUnit(MaterialUnit)
	 * @generated
	 */
	void unsetUnit();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits#getUnit <em>Unit</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Unit</em>' attribute is set.
	 * @see #unsetUnit()
	 * @see #getUnit()
	 * @see #setUnit(MaterialUnit)
	 * @generated
	 */
	boolean isSetUnit();

} // TFloatWithUnits
