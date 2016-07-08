/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TOrientation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TOrientation#getOrientationVariant <em>Orientation Variant</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTOrientation()
 * @model extendedMetaData="name='T_Orientation' kind='elementOnly'"
 * @generated
 */
public interface TOrientation extends EObject {
	/**
	 * Returns the value of the '<em><b>Orientation Variant</b></em>' containment reference list.
	 * The list contents are of type {@link de.tudresden.bau.cib.simmatrix.TOrientationVariant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orientation Variant</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orientation Variant</em>' containment reference list.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTOrientation_OrientationVariant()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='OrientationVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TOrientationVariant> getOrientationVariant();

} // TOrientation
