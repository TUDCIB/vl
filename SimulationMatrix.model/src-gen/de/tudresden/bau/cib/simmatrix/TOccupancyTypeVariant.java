/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TOccupancy Type Variant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getValue <em>Value</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getId <em>Id</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getPeriod <em>Period</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTOccupancyTypeVariant()
 * @model extendedMetaData="name='T_OccupancyTypeVariant' kind='simple'"
 * @generated
 */
public interface TOccupancyTypeVariant extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTOccupancyTypeVariant_Value()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="name=':0' kind='simple'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTOccupancyTypeVariant_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Period</b></em>' attribute.
	 * The default value is <code>"annual"</code>.
	 * The literals are from the enumeration {@link de.tudresden.bau.cib.simmatrix.TimePeriod}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Period</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Period</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.TimePeriod
	 * @see #isSetPeriod()
	 * @see #unsetPeriod()
	 * @see #setPeriod(TimePeriod)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTOccupancyTypeVariant_Period()
	 * @model default="annual" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='period' namespace='##targetNamespace'"
	 * @generated
	 */
	TimePeriod getPeriod();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.TimePeriod
	 * @see #isSetPeriod()
	 * @see #unsetPeriod()
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(TimePeriod value);

	/**
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPeriod()
	 * @see #getPeriod()
	 * @see #setPeriod(TimePeriod)
	 * @generated
	 */
	void unsetPeriod();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getPeriod <em>Period</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Period</em>' attribute is set.
	 * @see #unsetPeriod()
	 * @see #getPeriod()
	 * @see #setPeriod(TimePeriod)
	 * @generated
	 */
	boolean isSetPeriod();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"CSV"</code>.
	 * The literals are from the enumeration {@link de.tudresden.bau.cib.simmatrix.FileFormat}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.FileFormat
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(FileFormat)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTOccupancyTypeVariant_Type()
	 * @model default="CSV" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	FileFormat getType();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.FileFormat
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(FileFormat value);

	/**
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(FileFormat)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(FileFormat)
	 * @generated
	 */
	boolean isSetType();

} // TOccupancyTypeVariant
