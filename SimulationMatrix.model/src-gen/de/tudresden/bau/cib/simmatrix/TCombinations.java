/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TCombinations</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TCombinations#getCombination <em>Combination</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTCombinations()
 * @model extendedMetaData="name='T_Combinations' kind='elementOnly'"
 * @generated
 */
public interface TCombinations extends EObject {
	/**
	 * Returns the value of the '<em><b>Combination</b></em>' containment reference list.
	 * The list contents are of type {@link de.tudresden.bau.cib.simmatrix.TCombination}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Combination</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Combination</em>' containment reference list.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTCombinations_Combination()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='Combination' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TCombination> getCombination();

} // TCombinations
