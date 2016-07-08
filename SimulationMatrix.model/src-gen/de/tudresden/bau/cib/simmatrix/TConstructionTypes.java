/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TConstruction Types</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstructionTypes#getConstructionType <em>Construction Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstructionTypes()
 * @model extendedMetaData="name='T_ConstructionTypes' kind='elementOnly'"
 * @generated
 */
public interface TConstructionTypes extends EObject {
	/**
	 * Returns the value of the '<em><b>Construction Type</b></em>' containment reference list.
	 * The list contents are of type {@link de.tudresden.bau.cib.simmatrix.TConstructionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Construction Type</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Construction Type</em>' containment reference list.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstructionTypes_ConstructionType()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='ConstructionType' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TConstructionType> getConstructionType();

} // TConstructionTypes
