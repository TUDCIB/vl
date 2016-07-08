/**
 */
package de.tudresden.bau.cib.simmatrix;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TMaterial Type Variant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getDensity <em>Density</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getSpecificHeatCapacity <em>Specific Heat Capacity</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getConductivity <em>Conductivity</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getWaterVaporDiffusionResistanceFactor <em>Water Vapor Diffusion Resistance Factor</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getWaterAbsorptionCapacity <em>Water Absorption Capacity</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getOpenPorosity <em>Open Porosity</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getEffectiveSaturation <em>Effective Saturation</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getCapillarySaturationContent <em>Capillary Saturation Content</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getHygroscopicSorption <em>Hygroscopic Sorption</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getThermalConductivity <em>Thermal Conductivity</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getLiquidWaterConductivityEffectiveSaturation <em>Liquid Water Conductivity Effective Saturation</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant()
 * @model extendedMetaData="name='T_MaterialTypeVariant' kind='elementOnly'"
 * @generated
 */
public interface TMaterialTypeVariant extends EObject {
	/**
	 * Returns the value of the '<em><b>Density</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Density</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Density</em>' containment reference.
	 * @see #setDensity(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_Density()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Density' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getDensity();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getDensity <em>Density</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Density</em>' containment reference.
	 * @see #getDensity()
	 * @generated
	 */
	void setDensity(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Specific Heat Capacity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specific Heat Capacity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specific Heat Capacity</em>' containment reference.
	 * @see #setSpecificHeatCapacity(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_SpecificHeatCapacity()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='SpecificHeatCapacity' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getSpecificHeatCapacity();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getSpecificHeatCapacity <em>Specific Heat Capacity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specific Heat Capacity</em>' containment reference.
	 * @see #getSpecificHeatCapacity()
	 * @generated
	 */
	void setSpecificHeatCapacity(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Conductivity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conductivity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conductivity</em>' containment reference.
	 * @see #setConductivity(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_Conductivity()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Conductivity' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getConductivity();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getConductivity <em>Conductivity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conductivity</em>' containment reference.
	 * @see #getConductivity()
	 * @generated
	 */
	void setConductivity(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Water Vapor Diffusion Resistance Factor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Water Vapor Diffusion Resistance Factor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Water Vapor Diffusion Resistance Factor</em>' containment reference.
	 * @see #setWaterVaporDiffusionResistanceFactor(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_WaterVaporDiffusionResistanceFactor()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='WaterVaporDiffusionResistanceFactor' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getWaterVaporDiffusionResistanceFactor();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getWaterVaporDiffusionResistanceFactor <em>Water Vapor Diffusion Resistance Factor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Water Vapor Diffusion Resistance Factor</em>' containment reference.
	 * @see #getWaterVaporDiffusionResistanceFactor()
	 * @generated
	 */
	void setWaterVaporDiffusionResistanceFactor(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Water Absorption Capacity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Water Absorption Capacity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Water Absorption Capacity</em>' containment reference.
	 * @see #setWaterAbsorptionCapacity(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_WaterAbsorptionCapacity()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='WaterAbsorptionCapacity' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getWaterAbsorptionCapacity();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getWaterAbsorptionCapacity <em>Water Absorption Capacity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Water Absorption Capacity</em>' containment reference.
	 * @see #getWaterAbsorptionCapacity()
	 * @generated
	 */
	void setWaterAbsorptionCapacity(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Open Porosity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Open Porosity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Open Porosity</em>' containment reference.
	 * @see #setOpenPorosity(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_OpenPorosity()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='OpenPorosity' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getOpenPorosity();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getOpenPorosity <em>Open Porosity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Open Porosity</em>' containment reference.
	 * @see #getOpenPorosity()
	 * @generated
	 */
	void setOpenPorosity(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Effective Saturation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Effective Saturation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Effective Saturation</em>' containment reference.
	 * @see #setEffectiveSaturation(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_EffectiveSaturation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='EffectiveSaturation' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getEffectiveSaturation();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getEffectiveSaturation <em>Effective Saturation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Effective Saturation</em>' containment reference.
	 * @see #getEffectiveSaturation()
	 * @generated
	 */
	void setEffectiveSaturation(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Capillary Saturation Content</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capillary Saturation Content</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capillary Saturation Content</em>' containment reference.
	 * @see #setCapillarySaturationContent(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_CapillarySaturationContent()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='CapillarySaturationContent' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getCapillarySaturationContent();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getCapillarySaturationContent <em>Capillary Saturation Content</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capillary Saturation Content</em>' containment reference.
	 * @see #getCapillarySaturationContent()
	 * @generated
	 */
	void setCapillarySaturationContent(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Hygroscopic Sorption</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hygroscopic Sorption</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hygroscopic Sorption</em>' containment reference.
	 * @see #setHygroscopicSorption(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_HygroscopicSorption()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='HygroscopicSorption' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getHygroscopicSorption();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getHygroscopicSorption <em>Hygroscopic Sorption</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hygroscopic Sorption</em>' containment reference.
	 * @see #getHygroscopicSorption()
	 * @generated
	 */
	void setHygroscopicSorption(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Thermal Conductivity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thermal Conductivity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Thermal Conductivity</em>' containment reference.
	 * @see #setThermalConductivity(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_ThermalConductivity()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ThermalConductivity' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getThermalConductivity();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getThermalConductivity <em>Thermal Conductivity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Thermal Conductivity</em>' containment reference.
	 * @see #getThermalConductivity()
	 * @generated
	 */
	void setThermalConductivity(TFloatWithUnits value);

	/**
	 * Returns the value of the '<em><b>Liquid Water Conductivity Effective Saturation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Liquid Water Conductivity Effective Saturation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Liquid Water Conductivity Effective Saturation</em>' containment reference.
	 * @see #setLiquidWaterConductivityEffectiveSaturation(TFloatWithUnits)
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_LiquidWaterConductivityEffectiveSaturation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='LiquidWaterConductivityEffectiveSaturation' namespace='##targetNamespace'"
	 * @generated
	 */
	TFloatWithUnits getLiquidWaterConductivityEffectiveSaturation();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getLiquidWaterConductivityEffectiveSaturation <em>Liquid Water Conductivity Effective Saturation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Liquid Water Conductivity Effective Saturation</em>' containment reference.
	 * @see #getLiquidWaterConductivityEffectiveSaturation()
	 * @generated
	 */
	void setLiquidWaterConductivityEffectiveSaturation(TFloatWithUnits value);

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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#getTMaterialTypeVariant_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // TMaterialTypeVariant
