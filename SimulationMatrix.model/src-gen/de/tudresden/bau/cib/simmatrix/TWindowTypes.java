/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TWindow Types</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWindowTypes#getWindowType <em>Window Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWindowTypes()
 * @model extendedMetaData="name='T_WindowTypes' kind='elementOnly'"
 * @generated
 */
public interface TWindowTypes extends EObject {
	/**
	 * Returns the value of the '<em><b>Window Type</b></em>' containment reference list.
	 * The list contents are of type {@link de.tudresden.bau.cib.simmatrix.TWindowType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Window Type</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Window Type</em>' containment reference list.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWindowTypes_WindowType()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='WindowType' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TWindowType> getWindowType();

} // TWindowTypes
