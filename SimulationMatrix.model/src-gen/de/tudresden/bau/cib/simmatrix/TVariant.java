/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TVariant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TVariant#getAREF <em>AREF</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TVariant#getVREF <em>VREF</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariant()
 * @model extendedMetaData="name='T_Variant' kind='empty'"
 * @generated
 */
public interface TVariant extends EObject {
	/**
	 * Returns the value of the '<em><b>AREF</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AREF</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AREF</em>' attribute.
	 * @see #setAREF(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariant_AREF()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
	 *        extendedMetaData="kind='attribute' name='AREF' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAREF();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TVariant#getAREF <em>AREF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AREF</em>' attribute.
	 * @see #getAREF()
	 * @generated
	 */
	void setAREF(String value);

	/**
	 * Returns the value of the '<em><b>VREF</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>VREF</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>VREF</em>' attribute.
	 * @see #setVREF(String)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTVariant_VREF()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF" required="true"
	 *        extendedMetaData="kind='attribute' name='VREF' namespace='##targetNamespace'"
	 * @generated
	 */
	String getVREF();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TVariant#getVREF <em>VREF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VREF</em>' attribute.
	 * @see #getVREF()
	 * @generated
	 */
	void setVREF(String value);

} // TVariant
