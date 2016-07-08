/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TWindow Type Variant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getGlassFraction <em>Glass Fraction</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getFrameFraction <em>Frame Fraction</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getThermalTransmittance <em>Thermal Transmittance</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getSolarHeatGainCoefficient <em>Solar Heat Gain Coefficient</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getShadingFactor <em>Shading Factor</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWindowTypeVariant()
 * @model extendedMetaData="name='T_WindowTypeVariant' kind='elementOnly'"
 * @generated
 */
public interface TWindowTypeVariant extends EObject {
	/**
	 * Returns the value of the '<em><b>Glass Fraction</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Glass Fraction</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Glass Fraction</em>' containment reference.
	 * @see #setGlassFraction(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWindowTypeVariant_GlassFraction()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='GlassFraction' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getGlassFraction();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getGlassFraction <em>Glass Fraction</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Glass Fraction</em>' containment reference.
	 * @see #getGlassFraction()
	 * @generated
	 */
	void setGlassFraction(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Frame Fraction</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Frame Fraction</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Frame Fraction</em>' containment reference.
	 * @see #setFrameFraction(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWindowTypeVariant_FrameFraction()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='FrameFraction' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getFrameFraction();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getFrameFraction <em>Frame Fraction</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frame Fraction</em>' containment reference.
	 * @see #getFrameFraction()
	 * @generated
	 */
	void setFrameFraction(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Thermal Transmittance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thermal Transmittance</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Thermal Transmittance</em>' containment reference.
	 * @see #setThermalTransmittance(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWindowTypeVariant_ThermalTransmittance()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ThermalTransmittance' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getThermalTransmittance();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getThermalTransmittance <em>Thermal Transmittance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Thermal Transmittance</em>' containment reference.
	 * @see #getThermalTransmittance()
	 * @generated
	 */
	void setThermalTransmittance(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Solar Heat Gain Coefficient</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Solar Heat Gain Coefficient</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Solar Heat Gain Coefficient</em>' containment reference.
	 * @see #setSolarHeatGainCoefficient(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWindowTypeVariant_SolarHeatGainCoefficient()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='SolarHeatGainCoefficient' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getSolarHeatGainCoefficient();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getSolarHeatGainCoefficient <em>Solar Heat Gain Coefficient</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Solar Heat Gain Coefficient</em>' containment reference.
	 * @see #getSolarHeatGainCoefficient()
	 * @generated
	 */
	void setSolarHeatGainCoefficient(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Shading Factor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shading Factor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shading Factor</em>' containment reference.
	 * @see #setShadingFactor(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWindowTypeVariant_ShadingFactor()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ShadingFactor' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getShadingFactor();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getShadingFactor <em>Shading Factor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shading Factor</em>' containment reference.
	 * @see #getShadingFactor()
	 * @generated
	 */
	void setShadingFactor(TFloatWithUnits value);

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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTWindowTypeVariant_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // TWindowTypeVariant
