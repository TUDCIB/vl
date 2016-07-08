/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.combustible;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleFactory
 * @model kind="package"
 * @generated
 */
public interface CombustiblePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "combustible";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http.//tu-dresden.de/cib/project/combustible/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "combustible";

	/**
	 * The package content type ID.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eCONTENT_TYPE = "cib.project.combustible.model#1.0.0";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CombustiblePackage eINSTANCE = de.tudresden.bau.cib.vl.core.model.eeBim.combustible.impl.CombustiblePackageImpl.init();

	/**
	 * The meta object id for the '{@link cib.project.combustible.model.combustible.impl.CombustibleContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cib.project.combustible.model.combustible.impl.CombustibleContainerImpl
	 * @see cib.project.combustible.model.combustible.impl.CombustiblePackageImpl#getCombustibleContainer()
	 * @generated
	 */
	int COMBUSTIBLE_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Combustibles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE_CONTAINER__COMBUSTIBLES = 0;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link cib.project.combustible.model.combustible.impl.CombustibleImpl <em>Combustible</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cib.project.combustible.model.combustible.impl.CombustibleImpl
	 * @see cib.project.combustible.model.combustible.impl.CombustiblePackageImpl#getCombustible()
	 * @generated
	 */
	int COMBUSTIBLE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__UNIT = 2;

	/**
	 * The feature id for the '<em><b>Heating Value Hi</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__HEATING_VALUE_HI = 3;

	/**
	 * The feature id for the '<em><b>Useful Heat Hs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__USEFUL_HEAT_HS = 4;

	/**
	 * The feature id for the '<em><b>Ratio Hs To Hi</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__RATIO_HS_TO_HI = 5;

	/**
	 * The feature id for the '<em><b>Price Per KWH</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__PRICE_PER_KWH = 6;

	/**
	 * The feature id for the '<em><b>Price Per Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__PRICE_PER_UNIT = 7;

	/**
	 * The feature id for the '<em><b>Base Price Per Anno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__BASE_PRICE_PER_ANNO = 8;

	/**
	 * The feature id for the '<em><b>Storage Yield</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__STORAGE_YIELD = 9;

	/**
	 * The feature id for the '<em><b>Primary Energy Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__PRIMARY_ENERGY_FACTOR = 10;

	/**
	 * The feature id for the '<em><b>Emission CO2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__EMISSION_CO2 = 11;

	/**
	 * The feature id for the '<em><b>Emission SO2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__EMISSION_SO2 = 12;

	/**
	 * The feature id for the '<em><b>Emission NOX</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE__EMISSION_NOX = 13;

	/**
	 * The number of structural features of the '<em>Combustible</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBUSTIBLE_FEATURE_COUNT = 14;


	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer
	 * @generated
	 */
	EClass getCombustibleContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer#getCombustibles <em>Combustibles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Combustibles</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer#getCombustibles()
	 * @see #getCombustibleContainer()
	 * @generated
	 */
	EReference getCombustibleContainer_Combustibles();

	/**
	 * Returns the meta object for class '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible <em>Combustible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Combustible</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible
	 * @generated
	 */
	EClass getCombustible();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getId()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getName()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getUnit()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_Unit();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getHeatingValueHi <em>Heating Value Hi</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Heating Value Hi</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getHeatingValueHi()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_HeatingValueHi();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getUsefulHeatHs <em>Useful Heat Hs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Useful Heat Hs</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getUsefulHeatHs()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_UsefulHeatHs();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getRatioHsToHi <em>Ratio Hs To Hi</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ratio Hs To Hi</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getRatioHsToHi()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_RatioHsToHi();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPricePerKWH <em>Price Per KWH</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Price Per KWH</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPricePerKWH()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_PricePerKWH();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPricePerUnit <em>Price Per Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Price Per Unit</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPricePerUnit()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_PricePerUnit();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getBasePricePerAnno <em>Base Price Per Anno</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Price Per Anno</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getBasePricePerAnno()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_BasePricePerAnno();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getStorageYield <em>Storage Yield</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Storage Yield</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getStorageYield()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_StorageYield();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPrimaryEnergyFactor <em>Primary Energy Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Primary Energy Factor</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getPrimaryEnergyFactor()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_PrimaryEnergyFactor();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionCO2 <em>Emission CO2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emission CO2</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionCO2()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_EmissionCO2();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionSO2 <em>Emission SO2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emission SO2</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionSO2()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_EmissionSO2();

	/**
	 * Returns the meta object for the attribute '{@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionNOX <em>Emission NOX</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emission NOX</em>'.
	 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible#getEmissionNOX()
	 * @see #getCombustible()
	 * @generated
	 */
	EAttribute getCombustible_EmissionNOX();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CombustibleFactory getCombustibleFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link cib.project.combustible.model.combustible.impl.CombustibleContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cib.project.combustible.model.combustible.impl.CombustibleContainerImpl
		 * @see cib.project.combustible.model.combustible.impl.CombustiblePackageImpl#getCombustibleContainer()
		 * @generated
		 */
		EClass COMBUSTIBLE_CONTAINER = eINSTANCE.getCombustibleContainer();

		/**
		 * The meta object literal for the '<em><b>Combustibles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMBUSTIBLE_CONTAINER__COMBUSTIBLES = eINSTANCE.getCombustibleContainer_Combustibles();

		/**
		 * The meta object literal for the '{@link cib.project.combustible.model.combustible.impl.CombustibleImpl <em>Combustible</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cib.project.combustible.model.combustible.impl.CombustibleImpl
		 * @see cib.project.combustible.model.combustible.impl.CombustiblePackageImpl#getCombustible()
		 * @generated
		 */
		EClass COMBUSTIBLE = eINSTANCE.getCombustible();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__ID = eINSTANCE.getCombustible_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__NAME = eINSTANCE.getCombustible_Name();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__UNIT = eINSTANCE.getCombustible_Unit();

		/**
		 * The meta object literal for the '<em><b>Heating Value Hi</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__HEATING_VALUE_HI = eINSTANCE.getCombustible_HeatingValueHi();

		/**
		 * The meta object literal for the '<em><b>Useful Heat Hs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__USEFUL_HEAT_HS = eINSTANCE.getCombustible_UsefulHeatHs();

		/**
		 * The meta object literal for the '<em><b>Ratio Hs To Hi</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__RATIO_HS_TO_HI = eINSTANCE.getCombustible_RatioHsToHi();

		/**
		 * The meta object literal for the '<em><b>Price Per KWH</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__PRICE_PER_KWH = eINSTANCE.getCombustible_PricePerKWH();

		/**
		 * The meta object literal for the '<em><b>Price Per Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__PRICE_PER_UNIT = eINSTANCE.getCombustible_PricePerUnit();

		/**
		 * The meta object literal for the '<em><b>Base Price Per Anno</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__BASE_PRICE_PER_ANNO = eINSTANCE.getCombustible_BasePricePerAnno();

		/**
		 * The meta object literal for the '<em><b>Storage Yield</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__STORAGE_YIELD = eINSTANCE.getCombustible_StorageYield();

		/**
		 * The meta object literal for the '<em><b>Primary Energy Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__PRIMARY_ENERGY_FACTOR = eINSTANCE.getCombustible_PrimaryEnergyFactor();

		/**
		 * The meta object literal for the '<em><b>Emission CO2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__EMISSION_CO2 = eINSTANCE.getCombustible_EmissionCO2();

		/**
		 * The meta object literal for the '<em><b>Emission SO2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__EMISSION_SO2 = eINSTANCE.getCombustible_EmissionSO2();

		/**
		 * The meta object literal for the '<em><b>Emission NOX</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBUSTIBLE__EMISSION_NOX = eINSTANCE.getCombustible_EmissionNOX();

	}

} //CombustiblePackage
