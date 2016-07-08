/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.combustible.impl;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleFactory;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CombustiblePackageImpl extends EPackageImpl implements CombustiblePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass combustibleContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass combustibleEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see cib.project.combustible.model.combustible.CombustiblePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CombustiblePackageImpl() {
		super(eNS_URI, CombustibleFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link CombustiblePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CombustiblePackage init() {
		if (isInited) return (CombustiblePackage)EPackage.Registry.INSTANCE.getEPackage(CombustiblePackage.eNS_URI);

		// Obtain or create and register package
		CombustiblePackageImpl theCombustiblePackage = (CombustiblePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CombustiblePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CombustiblePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theCombustiblePackage.createPackageContents();

		// Initialize created meta-data
		theCombustiblePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCombustiblePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CombustiblePackage.eNS_URI, theCombustiblePackage);
		return theCombustiblePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCombustibleContainer() {
		return combustibleContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCombustibleContainer_Combustibles() {
		return (EReference)combustibleContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCombustible() {
		return combustibleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_Id() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_Name() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_Unit() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_HeatingValueHi() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_UsefulHeatHs() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_RatioHsToHi() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_PricePerKWH() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_PricePerUnit() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_BasePricePerAnno() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_StorageYield() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_PrimaryEnergyFactor() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_EmissionCO2() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_EmissionSO2() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombustible_EmissionNOX() {
		return (EAttribute)combustibleEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CombustibleFactory getCombustibleFactory() {
		return (CombustibleFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		combustibleContainerEClass = createEClass(COMBUSTIBLE_CONTAINER);
		createEReference(combustibleContainerEClass, COMBUSTIBLE_CONTAINER__COMBUSTIBLES);

		combustibleEClass = createEClass(COMBUSTIBLE);
		createEAttribute(combustibleEClass, COMBUSTIBLE__ID);
		createEAttribute(combustibleEClass, COMBUSTIBLE__NAME);
		createEAttribute(combustibleEClass, COMBUSTIBLE__UNIT);
		createEAttribute(combustibleEClass, COMBUSTIBLE__HEATING_VALUE_HI);
		createEAttribute(combustibleEClass, COMBUSTIBLE__USEFUL_HEAT_HS);
		createEAttribute(combustibleEClass, COMBUSTIBLE__RATIO_HS_TO_HI);
		createEAttribute(combustibleEClass, COMBUSTIBLE__PRICE_PER_KWH);
		createEAttribute(combustibleEClass, COMBUSTIBLE__PRICE_PER_UNIT);
		createEAttribute(combustibleEClass, COMBUSTIBLE__BASE_PRICE_PER_ANNO);
		createEAttribute(combustibleEClass, COMBUSTIBLE__STORAGE_YIELD);
		createEAttribute(combustibleEClass, COMBUSTIBLE__PRIMARY_ENERGY_FACTOR);
		createEAttribute(combustibleEClass, COMBUSTIBLE__EMISSION_CO2);
		createEAttribute(combustibleEClass, COMBUSTIBLE__EMISSION_SO2);
		createEAttribute(combustibleEClass, COMBUSTIBLE__EMISSION_NOX);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(combustibleContainerEClass, CombustibleContainer.class, "CombustibleContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCombustibleContainer_Combustibles(), this.getCombustible(), null, "combustibles", null, 0, -1, CombustibleContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(combustibleEClass, Combustible.class, "Combustible", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCombustible_Id(), ecorePackage.getEString(), "id", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_Name(), ecorePackage.getEString(), "name", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_Unit(), ecorePackage.getEString(), "unit", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_HeatingValueHi(), ecorePackage.getEDouble(), "heatingValueHi", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_UsefulHeatHs(), ecorePackage.getEDouble(), "usefulHeatHs", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_RatioHsToHi(), ecorePackage.getEDouble(), "ratioHsToHi", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_PricePerKWH(), ecorePackage.getEDouble(), "pricePerKWH", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_PricePerUnit(), ecorePackage.getEDouble(), "pricePerUnit", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_BasePricePerAnno(), ecorePackage.getEDouble(), "basePricePerAnno", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_StorageYield(), ecorePackage.getEDouble(), "storageYield", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_PrimaryEnergyFactor(), ecorePackage.getEDouble(), "primaryEnergyFactor", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_EmissionCO2(), ecorePackage.getEDouble(), "emissionCO2", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_EmissionSO2(), ecorePackage.getEDouble(), "emissionSO2", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCombustible_EmissionNOX(), ecorePackage.getEDouble(), "emissionNOX", null, 0, 1, Combustible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //CombustiblePackageImpl
