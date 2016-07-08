/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TAssignment Groups</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TAssignmentGroups#getSpaces <em>Spaces</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TAssignmentGroups#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTAssignmentGroups()
 * @model extendedMetaData="name='T_AssignmentGroups' kind='elementOnly'"
 * @generated
 */
public interface TAssignmentGroups extends EObject {
	/**
	 * Returns the value of the '<em><b>Spaces</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spaces</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spaces</em>' containment reference.
	 * @see #setSpaces(TSpaces)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTAssignmentGroups_Spaces()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Spaces' namespace='##targetNamespace'"
	 * @generated
	 */
	TSpaces getSpaces();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TAssignmentGroups#getSpaces <em>Spaces</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spaces</em>' containment reference.
	 * @see #getSpaces()
	 * @generated
	 */
	void setSpaces(TSpaces value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference.
	 * @see #setElements(TElements)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTAssignmentGroups_Elements()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Elements' namespace='##targetNamespace'"
	 * @generated
	 */
	TElements getElements();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TAssignmentGroups#getElements <em>Elements</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elements</em>' containment reference.
	 * @see #getElements()
	 * @generated
	 */
	void setElements(TElements value);

} // TAssignmentGroups
