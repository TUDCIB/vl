/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.combustible;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer#getCombustibles <em>Combustibles</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustibleContainer()
 * @model
 * @generated
 */
public interface CombustibleContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Combustibles</b></em>' containment reference list.
	 * The list contents are of type {@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Combustibles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Combustibles</em>' containment reference list.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustibleContainer_Combustibles()
	 * @model containment="true"
	 * @generated
	 */
	EList<Combustible> getCombustibles();

} // CombustibleContainer
