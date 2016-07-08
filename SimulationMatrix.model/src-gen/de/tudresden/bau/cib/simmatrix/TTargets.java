/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TTargets</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TTargets#getTargetList <em>Target List</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTTargets()
 * @model extendedMetaData="name='T_Targets' kind='elementOnly'"
 * @generated
 */
public interface TTargets extends EObject {
	/**
	 * Returns the value of the '<em><b>Target List</b></em>' containment reference list.
	 * The list contents are of type {@link de.tudresden.bau.cib.simmatrix.TTargetList}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target List</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target List</em>' containment reference list.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTTargets_TargetList()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='TargetList' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TTargetList> getTargetList();

} // TTargets
