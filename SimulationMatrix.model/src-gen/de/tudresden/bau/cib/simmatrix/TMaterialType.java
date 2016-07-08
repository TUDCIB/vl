/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TMaterial Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialType#getMaterialTypeVariant <em>Material Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialType#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialType()
 * @model extendedMetaData="name='T_MaterialType' kind='elementOnly'"
 * @generated
 */
public interface TMaterialType extends EObject {
	/**
	 * Returns the value of the '<em><b>Material Type Variant</b></em>' containment reference list.
	 * The list contents are of type {@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Material Type Variant</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Material Type Variant</em>' containment reference list.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialType_MaterialTypeVariant()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='MaterialTypeVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TMaterialTypeVariant> getMaterialTypeVariant();

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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialType_Source()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI" required="true"
	 *        extendedMetaData="kind='attribute' name='source' namespace='##targetNamespace'"
	 * @generated
	 */
	String getSource();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialType#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(String value);

} // TMaterialType
