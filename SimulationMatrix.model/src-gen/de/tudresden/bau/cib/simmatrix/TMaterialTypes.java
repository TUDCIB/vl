/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TMaterial Types</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypes#getMaterialType <em>Material Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypes()
 * @model extendedMetaData="name='T_MaterialTypes' kind='elementOnly'"
 * @generated
 */
public interface TMaterialTypes extends EObject {
	/**
	 * Returns the value of the '<em><b>Material Type</b></em>' containment reference list.
	 * The list contents are of type {@link de.tudresden.bau.cib.simmatrix.TMaterialType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Material Type</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Material Type</em>' containment reference list.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypes_MaterialType()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='MaterialType' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TMaterialType> getMaterialType();

} // TMaterialTypes
