/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.combustible;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Combustible</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getId <em>Id</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getName <em>Name</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getUnit <em>Unit</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getHeatingValueHi <em>Heating Value Hi</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getUsefulHeatHs <em>Useful Heat Hs</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getRatioHsToHi <em>Ratio Hs To Hi</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPricePerKWH <em>Price Per KWH</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPricePerUnit <em>Price Per Unit</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getBasePricePerAnno <em>Base Price Per Anno</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getStorageYield <em>Storage Yield</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPrimaryEnergyFactor <em>Primary Energy Factor</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionCO2 <em>Emission CO2</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionSO2 <em>Emission SO2</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionNOX <em>Emission NOX</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible()
 * @model
 * @generated
 */
public interface Combustible extends EObject {
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
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see #setUnit(String)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_Unit()
	 * @model
	 * @generated
	 */
	String getUnit();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(String value);

	/**
	 * Returns the value of the '<em><b>Heating Value Hi</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Heating Value Hi</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Heating Value Hi</em>' attribute.
	 * @see #setHeatingValueHi(double)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_HeatingValueHi()
	 * @model
	 * @generated
	 */
	double getHeatingValueHi();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getHeatingValueHi <em>Heating Value Hi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Heating Value Hi</em>' attribute.
	 * @see #getHeatingValueHi()
	 * @generated
	 */
	void setHeatingValueHi(double value);

	/**
	 * Returns the value of the '<em><b>Useful Heat Hs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Useful Heat Hs</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Useful Heat Hs</em>' attribute.
	 * @see #setUsefulHeatHs(double)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_UsefulHeatHs()
	 * @model
	 * @generated
	 */
	double getUsefulHeatHs();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getUsefulHeatHs <em>Useful Heat Hs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Useful Heat Hs</em>' attribute.
	 * @see #getUsefulHeatHs()
	 * @generated
	 */
	void setUsefulHeatHs(double value);

	/**
	 * Returns the value of the '<em><b>Ratio Hs To Hi</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ratio Hs To Hi</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ratio Hs To Hi</em>' attribute.
	 * @see #setRatioHsToHi(double)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_RatioHsToHi()
	 * @model
	 * @generated
	 */
	double getRatioHsToHi();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getRatioHsToHi <em>Ratio Hs To Hi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ratio Hs To Hi</em>' attribute.
	 * @see #getRatioHsToHi()
	 * @generated
	 */
	void setRatioHsToHi(double value);

	/**
	 * Returns the value of the '<em><b>Price Per KWH</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Price Per KWH</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Price Per KWH</em>' attribute.
	 * @see #setPricePerKWH(double)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_PricePerKWH()
	 * @model
	 * @generated
	 */
	double getPricePerKWH();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPricePerKWH <em>Price Per KWH</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Price Per KWH</em>' attribute.
	 * @see #getPricePerKWH()
	 * @generated
	 */
	void setPricePerKWH(double value);

	/**
	 * Returns the value of the '<em><b>Price Per Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Price Per Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Price Per Unit</em>' attribute.
	 * @see #setPricePerUnit(double)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_PricePerUnit()
	 * @model
	 * @generated
	 */
	double getPricePerUnit();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPricePerUnit <em>Price Per Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Price Per Unit</em>' attribute.
	 * @see #getPricePerUnit()
	 * @generated
	 */
	void setPricePerUnit(double value);

	/**
	 * Returns the value of the '<em><b>Base Price Per Anno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Price Per Anno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Price Per Anno</em>' attribute.
	 * @see #setBasePricePerAnno(double)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_BasePricePerAnno()
	 * @model
	 * @generated
	 */
	double getBasePricePerAnno();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getBasePricePerAnno <em>Base Price Per Anno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Price Per Anno</em>' attribute.
	 * @see #getBasePricePerAnno()
	 * @generated
	 */
	void setBasePricePerAnno(double value);

	/**
	 * Returns the value of the '<em><b>Storage Yield</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Storage Yield</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Storage Yield</em>' attribute.
	 * @see #setStorageYield(double)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_StorageYield()
	 * @model
	 * @generated
	 */
	double getStorageYield();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getStorageYield <em>Storage Yield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Storage Yield</em>' attribute.
	 * @see #getStorageYield()
	 * @generated
	 */
	void setStorageYield(double value);

	/**
	 * Returns the value of the '<em><b>Primary Energy Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary Energy Factor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Energy Factor</em>' attribute.
	 * @see #setPrimaryEnergyFactor(double)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_PrimaryEnergyFactor()
	 * @model
	 * @generated
	 */
	double getPrimaryEnergyFactor();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPrimaryEnergyFactor <em>Primary Energy Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Energy Factor</em>' attribute.
	 * @see #getPrimaryEnergyFactor()
	 * @generated
	 */
	void setPrimaryEnergyFactor(double value);

	/**
	 * Returns the value of the '<em><b>Emission CO2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Emission CO2</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Emission CO2</em>' attribute.
	 * @see #setEmissionCO2(double)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_EmissionCO2()
	 * @model
	 * @generated
	 */
	double getEmissionCO2();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionCO2 <em>Emission CO2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emission CO2</em>' attribute.
	 * @see #getEmissionCO2()
	 * @generated
	 */
	void setEmissionCO2(double value);

	/**
	 * Returns the value of the '<em><b>Emission SO2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Emission SO2</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Emission SO2</em>' attribute.
	 * @see #setEmissionSO2(double)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_EmissionSO2()
	 * @model
	 * @generated
	 */
	double getEmissionSO2();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionSO2 <em>Emission SO2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emission SO2</em>' attribute.
	 * @see #getEmissionSO2()
	 * @generated
	 */
	void setEmissionSO2(double value);

	/**
	 * Returns the value of the '<em><b>Emission NOX</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Emission NOX</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Emission NOX</em>' attribute.
	 * @see #setEmissionNOX(double)
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage#getCombustible_EmissionNOX()
	 * @model
	 * @generated
	 */
	double getEmissionNOX();

	/**
	 * Sets the value of the '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionNOX <em>Emission NOX</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emission NOX</em>' attribute.
	 * @see #getEmissionNOX()
	 * @generated
	 */
	void setEmissionNOX(double value);

} // Combustible
