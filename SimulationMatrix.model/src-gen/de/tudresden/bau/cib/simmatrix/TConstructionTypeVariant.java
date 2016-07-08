/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TConstruction Type Variant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant#getLayer <em>Layer</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstructionTypeVariant()
 * @model extendedMetaData="name='T_ConstructionTypeVariant' kind='elementOnly'"
 * @generated
 */
public interface TConstructionTypeVariant extends EObject {
	/**
	 * Returns the value of the '<em><b>Layer</b></em>' containment reference list.
	 * The list contents are of type {@link de.tudresden.bau.cib.simmatrix.TLayer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layer</em>' containment reference list.
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstructionTypeVariant_Layer()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='Layer' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TLayer> getLayer();

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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTConstructionTypeVariant_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // TConstructionTypeVariant
