/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TConstruction Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstructionType#getConstructionTypeVariant <em>Construction Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstructionType#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstructionType#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstructionType()
 * @model extendedMetaData="name='T_ConstructionType' kind='elementOnly'"
 * @generated
 */
public interface TConstructionType extends EObject {
	/**
	 * Returns the value of the '<em><b>Construction Type Variant</b></em>' containment reference list.
	 * The list contents are of type {@link de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Construction Type Variant</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Construction Type Variant</em>' containment reference list.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstructionType_ConstructionTypeVariant()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='ConstructionTypeVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TConstructionTypeVariant> getConstructionTypeVariant();

	/**
	 * Returns the value of the '<em><b>Orientation</b></em>' attribute.
	 * The literals are from the enumeration {@link de.tudresden.bau.cib.simmatrix.OrientationSide}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orientation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orientation</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.OrientationSide
	 * @see #isSetOrientation()
	 * @see #unsetOrientation()
	 * @see #setOrientation(OrientationSide)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstructionType_Orientation()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='orientation' namespace='##targetNamespace'"
	 * @generated
	 */
	OrientationSide getOrientation();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TConstructionType#getOrientation <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orientation</em>' attribute.
	 * @see de.tudresden.bau.cib.simmatrix.OrientationSide
	 * @see #isSetOrientation()
	 * @see #unsetOrientation()
	 * @see #getOrientation()
	 * @generated
	 */
	void setOrientation(OrientationSide value);

	/**
	 * Unsets the value of the '{@link de.tudresden.bau.cib.simmatrix.TConstructionType#getOrientation <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOrientation()
	 * @see #getOrientation()
	 * @see #setOrientation(OrientationSide)
	 * @generated
	 */
	void unsetOrientation();

	/**
	 * Returns whether the value of the '{@link de.tudresden.bau.cib.simmatrix.TConstructionType#getOrientation <em>Orientation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Orientation</em>' attribute is set.
	 * @see #unsetOrientation()
	 * @see #getOrientation()
	 * @see #setOrientation(OrientationSide)
	 * @generated
	 */
	boolean isSetOrientation();

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstructionType_Source()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI" required="true"
	 *        extendedMetaData="kind='attribute' name='source' namespace='##targetNamespace'"
	 * @generated
	 */
	String getSource();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TConstructionType#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(String value);

} // TConstructionType
