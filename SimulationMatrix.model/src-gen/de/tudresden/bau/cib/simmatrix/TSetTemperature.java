/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TSet Temperature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getValue <em>Value</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getPeriod <em>Period</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getType <em>Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getUnit <em>Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSetTemperature()
 * @model extendedMetaData="name='T_SetTemperature' kind='simple'"
 * @generated
 */
public interface TSetTemperature extends EObject {
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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSetTemperature_Value()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="name=':0' kind='simple'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSetTemperature_Period()
	 * @model default="annual" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='period' namespace='##targetNamespace'"
	 * @generated
	 */
	TimePeriod getPeriod();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getPeriod <em>Period</em>}' attribute.
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
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPeriod()
	 * @see #getPeriod()
	 * @see #setPeriod(TimePeriod)
	 * @generated
	 */
	void unsetPeriod();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getPeriod <em>Period</em>}' attribute is set.
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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSetTemperature_Type()
	 * @model default="CSV" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	FileFormat getType();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getType <em>Type</em>}' attribute.
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
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(FileFormat)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(FileFormat)
	 * @generated
	 */
	boolean isSetType();

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * The default value is <code>"C"</code>.
	 * The literals are from the enumeration {@link de.tudresden.bau.cib.simmatrix.Temperature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.Temperature
	 * @see #isSetUnit()
	 * @see #unsetUnit()
	 * @see #setUnit(Temperature)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSetTemperature_Unit()
	 * @model default="C" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='unit' namespace='##targetNamespace'"
	 * @generated
	 */
	Temperature getUnit();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.Temperature
	 * @see #isSetUnit()
	 * @see #unsetUnit()
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(Temperature value);

	/**
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUnit()
	 * @see #getUnit()
	 * @see #setUnit(Temperature)
	 * @generated
	 */
	void unsetUnit();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature#getUnit <em>Unit</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Unit</em>' attribute is set.
	 * @see #unsetUnit()
	 * @see #getUnit()
	 * @see #setUnit(Temperature)
	 * @generated
	 */
	boolean isSetUnit();

} // TSetTemperature
