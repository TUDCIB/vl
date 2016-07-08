/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TElevation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TElevation#getElevationVariant <em>Elevation Variant</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTElevation()
 * @model extendedMetaData="name='T_Elevation' kind='elementOnly'"
 * @generated
 */
public interface TElevation extends EObject {
	/**
	 * Returns the value of the '<em><b>Elevation Variant</b></em>' containment reference list.
	 * The list contents are of type {@link de.tudresden.bau.cib.simmatrix.TElevationVariant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elevation Variant</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elevation Variant</em>' containment reference list.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTElevation_ElevationVariant()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='ElevationVariant' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TElevationVariant> getElevationVariant();

} // TElevation
