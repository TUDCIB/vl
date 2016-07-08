/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TSet Person Loads</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getValue <em>Value</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getPeriod <em>Period</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getType <em>Type</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getUnit <em>Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSetPersonLoads()
 * @model extendedMetaData="name='T_SetPersonLoads' kind='simple'"
 * @generated
 */
public interface TSetPersonLoads extends EObject {
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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSetPersonLoads_Value()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="name=':0' kind='simple'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getValue <em>Value</em>}' attribute.
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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSetPersonLoads_Period()
	 * @model default="annual" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='period' namespace='##targetNamespace'"
	 * @generated
	 */
	TimePeriod getPeriod();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getPeriod <em>Period</em>}' attribute.
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
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPeriod()
	 * @see #getPeriod()
	 * @see #setPeriod(TimePeriod)
	 * @generated
	 */
	void unsetPeriod();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getPeriod <em>Period</em>}' attribute is set.
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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSetPersonLoads_Type()
	 * @model default="CSV" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	FileFormat getType();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getType <em>Type</em>}' attribute.
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
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(FileFormat)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getType <em>Type</em>}' attribute is set.
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
	 * The default value is <code>"W"</code>.
	 * The literals are from the enumeration {@link de.tudresden.bau.cib.simmatrix.Loads}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.Loads
	 * @see #isSetUnit()
	 * @see #unsetUnit()
	 * @see #setUnit(Loads)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSetPersonLoads_Unit()
	 * @model default="W" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='unit' namespace='##targetNamespace'"
	 * @generated
	 */
	Loads getUnit();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.Loads
	 * @see #isSetUnit()
	 * @see #unsetUnit()
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(Loads value);

	/**
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUnit()
	 * @see #getUnit()
	 * @see #setUnit(Loads)
	 * @generated
	 */
	void unsetUnit();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads#getUnit <em>Unit</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Unit</em>' attribute is set.
	 * @see #unsetUnit()
	 * @see #getUnit()
	 * @see #setUnit(Loads)
	 * @generated
	 */
	boolean isSetUnit();

} // TSetPersonLoads
