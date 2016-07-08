/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TSimulation Matrix</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getTargets <em>Targets</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getVariables <em>Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getAssignmentGroups <em>Assignment Groups</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getCombinations <em>Combinations</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getId <em>Id</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSimulationMatrix()
 * @model extendedMetaData="name='T_SimulationMatrix' kind='elementOnly'"
 * @generated
 */
public interface TSimulationMatrix extends EObject {
	/**
	 * Returns the value of the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Targets</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Targets</em>' containment reference.
	 * @see #setTargets(TTargets)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSimulationMatrix_Targets()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Targets' namespace='##targetNamespace'"
	 * @generated
	 */
	TTargets getTargets();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getTargets <em>Targets</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Targets</em>' containment reference.
	 * @see #getTargets()
	 * @generated
	 */
	void setTargets(TTargets value);

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference.
	 * @see #setVariables(TVariables)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSimulationMatrix_Variables()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='Variables' namespace='##targetNamespace'"
	 * @generated
	 */
	TVariables getVariables();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getVariables <em>Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variables</em>' containment reference.
	 * @see #getVariables()
	 * @generated
	 */
	void setVariables(TVariables value);

	/**
	 * Returns the value of the '<em><b>Assignment Groups</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assignment Groups</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assignment Groups</em>' containment reference.
	 * @see #setAssignmentGroups(TAssignmentGroups)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSimulationMatrix_AssignmentGroups()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='AssignmentGroups' namespace='##targetNamespace'"
	 * @generated
	 */
	TAssignmentGroups getAssignmentGroups();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getAssignmentGroups <em>Assignment Groups</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assignment Groups</em>' containment reference.
	 * @see #getAssignmentGroups()
	 * @generated
	 */
	void setAssignmentGroups(TAssignmentGroups value);

	/**
	 * Returns the value of the '<em><b>Combinations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Combinations</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Combinations</em>' containment reference.
	 * @see #setCombinations(TCombinations)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSimulationMatrix_Combinations()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Combinations' namespace='##targetNamespace'"
	 * @generated
	 */
	TCombinations getCombinations();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getCombinations <em>Combinations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Combinations</em>' containment reference.
	 * @see #getCombinations()
	 * @generated
	 */
	void setCombinations(TCombinations value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSimulationMatrix_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTSimulationMatrix_Type()
	 * @model dataType="de.tudresden.bau.cib.simmatrix.SimatType" required="true"
	 *        extendedMetaData="kind='attribute' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // TSimulationMatrix
