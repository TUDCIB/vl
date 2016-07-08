/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.DistanceUnit;
import de.tudresden.bau.cib.simmatrix.DocumentRoot;
import de.tudresden.bau.cib.simmatrix.FileFormat;
import de.tudresden.bau.cib.simmatrix.Loads;
import de.tudresden.bau.cib.simmatrix.MaterialUnit;
import de.tudresden.bau.cib.simmatrix.OrientationSide;
import de.tudresden.bau.cib.simmatrix.OrientationUnit;
import de.tudresden.bau.cib.simmatrix.SetPoint;
import de.tudresden.bau.cib.simmatrix.Shading;
import de.tudresden.bau.cib.simmatrix.TAssignmentGroups;
import de.tudresden.bau.cib.simmatrix.TBIMGroup;
import de.tudresden.bau.cib.simmatrix.TCombination;
import de.tudresden.bau.cib.simmatrix.TCombinations;
import de.tudresden.bau.cib.simmatrix.TConstantTypeVariant;
import de.tudresden.bau.cib.simmatrix.TConstructionType;
import de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant;
import de.tudresden.bau.cib.simmatrix.TConstructionTypes;
import de.tudresden.bau.cib.simmatrix.TElementGroup;
import de.tudresden.bau.cib.simmatrix.TElements;
import de.tudresden.bau.cib.simmatrix.TElevation;
import de.tudresden.bau.cib.simmatrix.TElevationVariant;
import de.tudresden.bau.cib.simmatrix.TFloatWithUnits;
import de.tudresden.bau.cib.simmatrix.TLayer;
import de.tudresden.bau.cib.simmatrix.TMaterialType;
import de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant;
import de.tudresden.bau.cib.simmatrix.TMaterialTypes;
import de.tudresden.bau.cib.simmatrix.TOccupancyType;
import de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant;
import de.tudresden.bau.cib.simmatrix.TOrientation;
import de.tudresden.bau.cib.simmatrix.TOrientationVariant;
import de.tudresden.bau.cib.simmatrix.TScheduleType;
import de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant;
import de.tudresden.bau.cib.simmatrix.TSetPersonLoads;
import de.tudresden.bau.cib.simmatrix.TSetPoint;
import de.tudresden.bau.cib.simmatrix.TSetShading;
import de.tudresden.bau.cib.simmatrix.TSetTemperature;
import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.simmatrix.TSpace;
import de.tudresden.bau.cib.simmatrix.TSpaceGroup;
import de.tudresden.bau.cib.simmatrix.TSpaces;
import de.tudresden.bau.cib.simmatrix.TTarget;
import de.tudresden.bau.cib.simmatrix.TTargetList;
import de.tudresden.bau.cib.simmatrix.TTargets;
import de.tudresden.bau.cib.simmatrix.TUsage;
import de.tudresden.bau.cib.simmatrix.TVariables;
import de.tudresden.bau.cib.simmatrix.TVariant;
import de.tudresden.bau.cib.simmatrix.TWeather;
import de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant;
import de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant;
import de.tudresden.bau.cib.simmatrix.TWindowType;
import de.tudresden.bau.cib.simmatrix.TWindowTypeVariant;
import de.tudresden.bau.cib.simmatrix.TWindowTypes;
import de.tudresden.bau.cib.simmatrix.TargetType;
import de.tudresden.bau.cib.simmatrix.Temperature;
import de.tudresden.bau.cib.simmatrix.TimePeriod;
import de.tudresden.bau.cib.simmatrix.WeatherTypes;
import de.tudresden.bau.cib.simmatrix.simmatrixFactory;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import de.tudresden.bau.cib.simmatrix.util.simmatrixValidator;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class simmatrixPackageImpl extends EPackageImpl implements simmatrixPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tAssignmentGroupsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tbimGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCombinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCombinationsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tConstantTypeVariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tConstructionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tConstructionTypesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tConstructionTypeVariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tElementGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tElementsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tElevationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tElevationVariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tFloatWithUnitsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tLayerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tMaterialTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tMaterialTypesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tMaterialTypeVariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tOccupancyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tOccupancyTypeVariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tOrientationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tOrientationVariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tScheduleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tScheduleTypeVariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSetPersonLoadsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSetPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSetShadingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSetTemperatureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSimulationMatrixEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSpaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSpaceGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSpacesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tTargetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tTargetListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tTargetsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tUsageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tVariablesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tVariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tWeatherEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tWeatherDataSeriesVariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tWeatherDataSetVariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tWindowTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tWindowTypesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tWindowTypeVariantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum distanceUnitEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum fileFormatEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum loadsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum materialUnitEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orientationSideEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orientationUnitEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum setPointEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum shadingEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum targetTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum temperatureEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum timePeriodEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum weatherTypesEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType distanceUnitObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType fileFormatObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType loadsObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType materialUnitObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType orientationSideObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType orientationUnitObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType setPointObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType shadingObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType simatTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType targetTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType temperatureObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType timePeriodObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tStringListEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType weatherTypesObjectEDataType = null;

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
	 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private simmatrixPackageImpl() {
		super(eNS_URI, simmatrixFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link simmatrixPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static simmatrixPackage init() {
		if (isInited) return (simmatrixPackage)EPackage.Registry.INSTANCE.getEPackage(simmatrixPackage.eNS_URI);

		// Obtain or create and register package
		simmatrixPackageImpl thesimmatrixPackage = (simmatrixPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof simmatrixPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new simmatrixPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thesimmatrixPackage.createPackageContents();

		// Initialize created meta-data
		thesimmatrixPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(thesimmatrixPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return simmatrixValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		thesimmatrixPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(simmatrixPackage.eNS_URI, thesimmatrixPackage);
		return thesimmatrixPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_AssignmentGroups() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_BIMREF() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Combination() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Combinations() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ConstantTypeVariant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ConstructionType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ConstructionTypeVariables() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ConstructionTypeVariant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_DoorTypeVariables() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Element() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ElementGroup() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Elements() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ElevationVariables() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ElevationVariant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Layer() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MaterialType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MaterialTypeVariables() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MaterialTypeVariant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_OccupancyType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_OccupancyTypeVariant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_OrientationVariables() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_OrientationVariant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ScheduleType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ScheduleTypeVariant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SimulationMatrix() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Space() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SpaceGroup() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Spaces() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Target() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TargetList() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Targets() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_UsageTypeVariables() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Variables() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Variant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_WeatherDataSeriesVariant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_WeatherDataSetVariant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(38);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_WeatherVariables() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(39);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_WindowType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(40);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_WindowTypeVariables() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(41);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_WindowTypeVariant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(42);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTAssignmentGroups() {
		return tAssignmentGroupsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTAssignmentGroups_Spaces() {
		return (EReference)tAssignmentGroupsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTAssignmentGroups_Elements() {
		return (EReference)tAssignmentGroupsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTBIMGroup() {
		return tbimGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTBIMGroup_BIMREF() {
		return (EAttribute)tbimGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTBIMGroup_Id() {
		return (EAttribute)tbimGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCombination() {
		return tCombinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCombination_Variant() {
		return (EReference)tCombinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCombination_Id() {
		return (EAttribute)tCombinationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCombinations() {
		return tCombinationsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCombinations_Combination() {
		return (EReference)tCombinationsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTConstantTypeVariant() {
		return tConstantTypeVariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTConstantTypeVariant_HeatingSetPoint() {
		return (EReference)tConstantTypeVariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTConstantTypeVariant_CoolingSetPoint() {
		return (EReference)tConstantTypeVariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTConstantTypeVariant_PersonLoad() {
		return (EReference)tConstantTypeVariantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTConstantTypeVariant_EquipmentLoad() {
		return (EReference)tConstantTypeVariantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTConstantTypeVariant_Shading() {
		return (EReference)tConstantTypeVariantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConstantTypeVariant_Id() {
		return (EAttribute)tConstantTypeVariantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTConstructionType() {
		return tConstructionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTConstructionType_ConstructionTypeVariant() {
		return (EReference)tConstructionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConstructionType_Orientation() {
		return (EAttribute)tConstructionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConstructionType_Source() {
		return (EAttribute)tConstructionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTConstructionTypes() {
		return tConstructionTypesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTConstructionTypes_ConstructionType() {
		return (EReference)tConstructionTypesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTConstructionTypeVariant() {
		return tConstructionTypeVariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTConstructionTypeVariant_Layer() {
		return (EReference)tConstructionTypeVariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConstructionTypeVariant_Id() {
		return (EAttribute)tConstructionTypeVariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTElementGroup() {
		return tElementGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTElementGroup_Group() {
		return (EAttribute)tElementGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTElementGroup_Element() {
		return (EAttribute)tElementGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTElementGroup_Id() {
		return (EAttribute)tElementGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTElements() {
		return tElementsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTElements_Group() {
		return (EAttribute)tElementsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTElements_ElementGroup() {
		return (EReference)tElementsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTElevation() {
		return tElevationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTElevation_ElevationVariant() {
		return (EReference)tElevationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTElevationVariant() {
		return tElevationVariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTElevationVariant_Value() {
		return (EAttribute)tElevationVariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTElevationVariant_Id() {
		return (EAttribute)tElevationVariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTElevationVariant_Unit() {
		return (EAttribute)tElevationVariantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTFloatWithUnits() {
		return tFloatWithUnitsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTFloatWithUnits_Value() {
		return (EAttribute)tFloatWithUnitsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTFloatWithUnits_Unit() {
		return (EAttribute)tFloatWithUnitsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTLayer() {
		return tLayerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLayer_Value() {
		return (EAttribute)tLayerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLayer_Thickness() {
		return (EAttribute)tLayerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLayer_Unit() {
		return (EAttribute)tLayerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTMaterialType() {
		return tMaterialTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialType_MaterialTypeVariant() {
		return (EReference)tMaterialTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMaterialType_Source() {
		return (EAttribute)tMaterialTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTMaterialTypes() {
		return tMaterialTypesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypes_MaterialType() {
		return (EReference)tMaterialTypesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTMaterialTypeVariant() {
		return tMaterialTypeVariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypeVariant_Density() {
		return (EReference)tMaterialTypeVariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypeVariant_SpecificHeatCapacity() {
		return (EReference)tMaterialTypeVariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypeVariant_Conductivity() {
		return (EReference)tMaterialTypeVariantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypeVariant_WaterVaporDiffusionResistanceFactor() {
		return (EReference)tMaterialTypeVariantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypeVariant_WaterAbsorptionCapacity() {
		return (EReference)tMaterialTypeVariantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypeVariant_OpenPorosity() {
		return (EReference)tMaterialTypeVariantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypeVariant_EffectiveSaturation() {
		return (EReference)tMaterialTypeVariantEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypeVariant_CapillarySaturationContent() {
		return (EReference)tMaterialTypeVariantEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypeVariant_HygroscopicSorption() {
		return (EReference)tMaterialTypeVariantEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypeVariant_ThermalConductivity() {
		return (EReference)tMaterialTypeVariantEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMaterialTypeVariant_LiquidWaterConductivityEffectiveSaturation() {
		return (EReference)tMaterialTypeVariantEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMaterialTypeVariant_Id() {
		return (EAttribute)tMaterialTypeVariantEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTOccupancyType() {
		return tOccupancyTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOccupancyType_Group() {
		return (EAttribute)tOccupancyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTOccupancyType_OccupancyTypeVariant() {
		return (EReference)tOccupancyTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOccupancyType_Source() {
		return (EAttribute)tOccupancyTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTOccupancyTypeVariant() {
		return tOccupancyTypeVariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOccupancyTypeVariant_Value() {
		return (EAttribute)tOccupancyTypeVariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOccupancyTypeVariant_Id() {
		return (EAttribute)tOccupancyTypeVariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOccupancyTypeVariant_Period() {
		return (EAttribute)tOccupancyTypeVariantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOccupancyTypeVariant_Type() {
		return (EAttribute)tOccupancyTypeVariantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTOrientation() {
		return tOrientationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTOrientation_OrientationVariant() {
		return (EReference)tOrientationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTOrientationVariant() {
		return tOrientationVariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOrientationVariant_Value() {
		return (EAttribute)tOrientationVariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOrientationVariant_Id() {
		return (EAttribute)tOrientationVariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOrientationVariant_Unit() {
		return (EAttribute)tOrientationVariantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTScheduleType() {
		return tScheduleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTScheduleType_Group() {
		return (EAttribute)tScheduleTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTScheduleType_ScheduleTypeVariant() {
		return (EReference)tScheduleTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTScheduleType_ConstantTypeVariant() {
		return (EReference)tScheduleTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTScheduleType_Source() {
		return (EAttribute)tScheduleTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTScheduleTypeVariant() {
		return tScheduleTypeVariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTScheduleTypeVariant_HeatingSetPoint() {
		return (EReference)tScheduleTypeVariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTScheduleTypeVariant_CoolingSetPoint() {
		return (EReference)tScheduleTypeVariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTScheduleTypeVariant_PersonLoad() {
		return (EReference)tScheduleTypeVariantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTScheduleTypeVariant_EquipmentLoad() {
		return (EReference)tScheduleTypeVariantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTScheduleTypeVariant_Shading() {
		return (EReference)tScheduleTypeVariantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTScheduleTypeVariant_Id() {
		return (EAttribute)tScheduleTypeVariantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSetPersonLoads() {
		return tSetPersonLoadsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetPersonLoads_Value() {
		return (EAttribute)tSetPersonLoadsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetPersonLoads_Period() {
		return (EAttribute)tSetPersonLoadsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetPersonLoads_Type() {
		return (EAttribute)tSetPersonLoadsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetPersonLoads_Unit() {
		return (EAttribute)tSetPersonLoadsEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSetPoint() {
		return tSetPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetPoint_Value() {
		return (EAttribute)tSetPointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetPoint_Unit() {
		return (EAttribute)tSetPointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSetShading() {
		return tSetShadingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetShading_Value() {
		return (EAttribute)tSetShadingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetShading_Period() {
		return (EAttribute)tSetShadingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetShading_Type() {
		return (EAttribute)tSetShadingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetShading_Unit() {
		return (EAttribute)tSetShadingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSetTemperature() {
		return tSetTemperatureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetTemperature_Value() {
		return (EAttribute)tSetTemperatureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetTemperature_Period() {
		return (EAttribute)tSetTemperatureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetTemperature_Type() {
		return (EAttribute)tSetTemperatureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSetTemperature_Unit() {
		return (EAttribute)tSetTemperatureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSimulationMatrix() {
		return tSimulationMatrixEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSimulationMatrix_Targets() {
		return (EReference)tSimulationMatrixEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSimulationMatrix_Variables() {
		return (EReference)tSimulationMatrixEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSimulationMatrix_AssignmentGroups() {
		return (EReference)tSimulationMatrixEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSimulationMatrix_Combinations() {
		return (EReference)tSimulationMatrixEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSimulationMatrix_Id() {
		return (EAttribute)tSimulationMatrixEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSimulationMatrix_Type() {
		return (EAttribute)tSimulationMatrixEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSpace() {
		return tSpaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSpace_Id() {
		return (EAttribute)tSpaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSpaceGroup() {
		return tSpaceGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSpaceGroup_Group() {
		return (EAttribute)tSpaceGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSpaceGroup_Space() {
		return (EAttribute)tSpaceGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSpaceGroup_Id() {
		return (EAttribute)tSpaceGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSpaces() {
		return tSpacesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSpaces_Group() {
		return (EAttribute)tSpacesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSpaces_SpaceGroup() {
		return (EReference)tSpacesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSpaces_Id() {
		return (EAttribute)tSpacesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTTarget() {
		return tTargetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTTarget_Value() {
		return (EAttribute)tTargetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTTarget_Type() {
		return (EAttribute)tTargetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTTargetList() {
		return tTargetListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTTargetList_Target() {
		return (EReference)tTargetListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTTargetList_Id() {
		return (EAttribute)tTargetListEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTTargets() {
		return tTargetsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTTargets_TargetList() {
		return (EReference)tTargetsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTUsage() {
		return tUsageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTUsage_Group() {
		return (EAttribute)tUsageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTUsage_OccupancyType() {
		return (EReference)tUsageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTUsage_ScheduleType() {
		return (EReference)tUsageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTVariables() {
		return tVariablesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTVariables_WeatherVariables() {
		return (EReference)tVariablesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTVariables_OrientationVariables() {
		return (EReference)tVariablesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTVariables_ElevationVariables() {
		return (EReference)tVariablesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTVariables_UsageTypeVariables() {
		return (EReference)tVariablesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTVariables_ConstructionTypeVariables() {
		return (EReference)tVariablesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTVariables_WindowTypeVariables() {
		return (EReference)tVariablesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTVariables_DoorTypeVariables() {
		return (EAttribute)tVariablesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTVariables_MaterialTypeVariables() {
		return (EReference)tVariablesEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTVariant() {
		return tVariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTVariant_AREF() {
		return (EAttribute)tVariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTVariant_VREF() {
		return (EAttribute)tVariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTWeather() {
		return tWeatherEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTWeather_Group() {
		return (EAttribute)tWeatherEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTWeather_WeatherDataSetVariant() {
		return (EReference)tWeatherEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTWeather_WeatherDataSeriesVariant() {
		return (EReference)tWeatherEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTWeatherDataSeriesVariant() {
		return tWeatherDataSeriesVariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTWeatherDataSeriesVariant_AirTemperature() {
		return (EAttribute)tWeatherDataSeriesVariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTWeatherDataSeriesVariant_ShortWaveDiffuse() {
		return (EAttribute)tWeatherDataSeriesVariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTWeatherDataSeriesVariant_ShortWaveDirect() {
		return (EAttribute)tWeatherDataSeriesVariantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTWeatherDataSeriesVariant_Id() {
		return (EAttribute)tWeatherDataSeriesVariantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTWeatherDataSeriesVariant_Type() {
		return (EAttribute)tWeatherDataSeriesVariantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTWeatherDataSetVariant() {
		return tWeatherDataSetVariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTWeatherDataSetVariant_Value() {
		return (EAttribute)tWeatherDataSetVariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTWeatherDataSetVariant_Id() {
		return (EAttribute)tWeatherDataSetVariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTWeatherDataSetVariant_Type() {
		return (EAttribute)tWeatherDataSetVariantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTWindowType() {
		return tWindowTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTWindowType_WindowTypeVariant() {
		return (EReference)tWindowTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTWindowType_Name() {
		return (EAttribute)tWindowTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTWindowTypes() {
		return tWindowTypesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTWindowTypes_WindowType() {
		return (EReference)tWindowTypesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTWindowTypeVariant() {
		return tWindowTypeVariantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTWindowTypeVariant_GlassFraction() {
		return (EReference)tWindowTypeVariantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTWindowTypeVariant_FrameFraction() {
		return (EReference)tWindowTypeVariantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTWindowTypeVariant_ThermalTransmittance() {
		return (EReference)tWindowTypeVariantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTWindowTypeVariant_SolarHeatGainCoefficient() {
		return (EReference)tWindowTypeVariantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTWindowTypeVariant_ShadingFactor() {
		return (EReference)tWindowTypeVariantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTWindowTypeVariant_Id() {
		return (EAttribute)tWindowTypeVariantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDistanceUnit() {
		return distanceUnitEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getFileFormat() {
		return fileFormatEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLoads() {
		return loadsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMaterialUnit() {
		return materialUnitEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOrientationSide() {
		return orientationSideEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOrientationUnit() {
		return orientationUnitEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSetPoint() {
		return setPointEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getShading() {
		return shadingEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTargetType() {
		return targetTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTemperature() {
		return temperatureEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTimePeriod() {
		return timePeriodEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getWeatherTypes() {
		return weatherTypesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDistanceUnitObject() {
		return distanceUnitObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getFileFormatObject() {
		return fileFormatObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLoadsObject() {
		return loadsObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getMaterialUnitObject() {
		return materialUnitObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getOrientationSideObject() {
		return orientationSideObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getOrientationUnitObject() {
		return orientationUnitObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSetPointObject() {
		return setPointObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getShadingObject() {
		return shadingObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSimatType() {
		return simatTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTargetTypeObject() {
		return targetTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTemperatureObject() {
		return temperatureObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTimePeriodObject() {
		return timePeriodObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTStringList() {
		return tStringListEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getWeatherTypesObject() {
		return weatherTypesObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public simmatrixFactory getsimmatrixFactory() {
		return (simmatrixFactory)getEFactoryInstance();
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
		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ASSIGNMENT_GROUPS);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__BIMREF);
		createEReference(documentRootEClass, DOCUMENT_ROOT__COMBINATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__COMBINATIONS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONSTANT_TYPE_VARIANT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONSTRUCTION_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIABLES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONSTRUCTION_TYPE_VARIANT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__DOOR_TYPE_VARIABLES);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__ELEMENT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ELEMENT_GROUP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ELEMENTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ELEVATION_VARIABLES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ELEVATION_VARIANT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__LAYER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MATERIAL_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MATERIAL_TYPE_VARIABLES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MATERIAL_TYPE_VARIANT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__OCCUPANCY_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__OCCUPANCY_TYPE_VARIANT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ORIENTATION_VARIABLES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ORIENTATION_VARIANT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SCHEDULE_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SCHEDULE_TYPE_VARIANT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SIMULATION_MATRIX);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__SPACE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SPACE_GROUP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SPACES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TARGET);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TARGET_LIST);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TARGETS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__USAGE_TYPE_VARIABLES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__VARIABLES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__VARIANT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WEATHER_DATA_SERIES_VARIANT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WEATHER_DATA_SET_VARIANT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WEATHER_VARIABLES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WINDOW_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WINDOW_TYPE_VARIABLES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WINDOW_TYPE_VARIANT);

		tAssignmentGroupsEClass = createEClass(TASSIGNMENT_GROUPS);
		createEReference(tAssignmentGroupsEClass, TASSIGNMENT_GROUPS__SPACES);
		createEReference(tAssignmentGroupsEClass, TASSIGNMENT_GROUPS__ELEMENTS);

		tbimGroupEClass = createEClass(TBIM_GROUP);
		createEAttribute(tbimGroupEClass, TBIM_GROUP__BIMREF);
		createEAttribute(tbimGroupEClass, TBIM_GROUP__ID);

		tCombinationEClass = createEClass(TCOMBINATION);
		createEReference(tCombinationEClass, TCOMBINATION__VARIANT);
		createEAttribute(tCombinationEClass, TCOMBINATION__ID);

		tCombinationsEClass = createEClass(TCOMBINATIONS);
		createEReference(tCombinationsEClass, TCOMBINATIONS__COMBINATION);

		tConstantTypeVariantEClass = createEClass(TCONSTANT_TYPE_VARIANT);
		createEReference(tConstantTypeVariantEClass, TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT);
		createEReference(tConstantTypeVariantEClass, TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT);
		createEReference(tConstantTypeVariantEClass, TCONSTANT_TYPE_VARIANT__PERSON_LOAD);
		createEReference(tConstantTypeVariantEClass, TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD);
		createEReference(tConstantTypeVariantEClass, TCONSTANT_TYPE_VARIANT__SHADING);
		createEAttribute(tConstantTypeVariantEClass, TCONSTANT_TYPE_VARIANT__ID);

		tConstructionTypeEClass = createEClass(TCONSTRUCTION_TYPE);
		createEReference(tConstructionTypeEClass, TCONSTRUCTION_TYPE__CONSTRUCTION_TYPE_VARIANT);
		createEAttribute(tConstructionTypeEClass, TCONSTRUCTION_TYPE__ORIENTATION);
		createEAttribute(tConstructionTypeEClass, TCONSTRUCTION_TYPE__SOURCE);

		tConstructionTypesEClass = createEClass(TCONSTRUCTION_TYPES);
		createEReference(tConstructionTypesEClass, TCONSTRUCTION_TYPES__CONSTRUCTION_TYPE);

		tConstructionTypeVariantEClass = createEClass(TCONSTRUCTION_TYPE_VARIANT);
		createEReference(tConstructionTypeVariantEClass, TCONSTRUCTION_TYPE_VARIANT__LAYER);
		createEAttribute(tConstructionTypeVariantEClass, TCONSTRUCTION_TYPE_VARIANT__ID);

		tElementGroupEClass = createEClass(TELEMENT_GROUP);
		createEAttribute(tElementGroupEClass, TELEMENT_GROUP__GROUP);
		createEAttribute(tElementGroupEClass, TELEMENT_GROUP__ELEMENT);
		createEAttribute(tElementGroupEClass, TELEMENT_GROUP__ID);

		tElementsEClass = createEClass(TELEMENTS);
		createEAttribute(tElementsEClass, TELEMENTS__GROUP);
		createEReference(tElementsEClass, TELEMENTS__ELEMENT_GROUP);

		tElevationEClass = createEClass(TELEVATION);
		createEReference(tElevationEClass, TELEVATION__ELEVATION_VARIANT);

		tElevationVariantEClass = createEClass(TELEVATION_VARIANT);
		createEAttribute(tElevationVariantEClass, TELEVATION_VARIANT__VALUE);
		createEAttribute(tElevationVariantEClass, TELEVATION_VARIANT__ID);
		createEAttribute(tElevationVariantEClass, TELEVATION_VARIANT__UNIT);

		tFloatWithUnitsEClass = createEClass(TFLOAT_WITH_UNITS);
		createEAttribute(tFloatWithUnitsEClass, TFLOAT_WITH_UNITS__VALUE);
		createEAttribute(tFloatWithUnitsEClass, TFLOAT_WITH_UNITS__UNIT);

		tLayerEClass = createEClass(TLAYER);
		createEAttribute(tLayerEClass, TLAYER__VALUE);
		createEAttribute(tLayerEClass, TLAYER__THICKNESS);
		createEAttribute(tLayerEClass, TLAYER__UNIT);

		tMaterialTypeEClass = createEClass(TMATERIAL_TYPE);
		createEReference(tMaterialTypeEClass, TMATERIAL_TYPE__MATERIAL_TYPE_VARIANT);
		createEAttribute(tMaterialTypeEClass, TMATERIAL_TYPE__SOURCE);

		tMaterialTypesEClass = createEClass(TMATERIAL_TYPES);
		createEReference(tMaterialTypesEClass, TMATERIAL_TYPES__MATERIAL_TYPE);

		tMaterialTypeVariantEClass = createEClass(TMATERIAL_TYPE_VARIANT);
		createEReference(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__DENSITY);
		createEReference(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY);
		createEReference(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__CONDUCTIVITY);
		createEReference(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR);
		createEReference(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY);
		createEReference(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__OPEN_POROSITY);
		createEReference(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION);
		createEReference(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT);
		createEReference(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION);
		createEReference(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY);
		createEReference(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION);
		createEAttribute(tMaterialTypeVariantEClass, TMATERIAL_TYPE_VARIANT__ID);

		tOccupancyTypeEClass = createEClass(TOCCUPANCY_TYPE);
		createEAttribute(tOccupancyTypeEClass, TOCCUPANCY_TYPE__GROUP);
		createEReference(tOccupancyTypeEClass, TOCCUPANCY_TYPE__OCCUPANCY_TYPE_VARIANT);
		createEAttribute(tOccupancyTypeEClass, TOCCUPANCY_TYPE__SOURCE);

		tOccupancyTypeVariantEClass = createEClass(TOCCUPANCY_TYPE_VARIANT);
		createEAttribute(tOccupancyTypeVariantEClass, TOCCUPANCY_TYPE_VARIANT__VALUE);
		createEAttribute(tOccupancyTypeVariantEClass, TOCCUPANCY_TYPE_VARIANT__ID);
		createEAttribute(tOccupancyTypeVariantEClass, TOCCUPANCY_TYPE_VARIANT__PERIOD);
		createEAttribute(tOccupancyTypeVariantEClass, TOCCUPANCY_TYPE_VARIANT__TYPE);

		tOrientationEClass = createEClass(TORIENTATION);
		createEReference(tOrientationEClass, TORIENTATION__ORIENTATION_VARIANT);

		tOrientationVariantEClass = createEClass(TORIENTATION_VARIANT);
		createEAttribute(tOrientationVariantEClass, TORIENTATION_VARIANT__VALUE);
		createEAttribute(tOrientationVariantEClass, TORIENTATION_VARIANT__ID);
		createEAttribute(tOrientationVariantEClass, TORIENTATION_VARIANT__UNIT);

		tScheduleTypeEClass = createEClass(TSCHEDULE_TYPE);
		createEAttribute(tScheduleTypeEClass, TSCHEDULE_TYPE__GROUP);
		createEReference(tScheduleTypeEClass, TSCHEDULE_TYPE__SCHEDULE_TYPE_VARIANT);
		createEReference(tScheduleTypeEClass, TSCHEDULE_TYPE__CONSTANT_TYPE_VARIANT);
		createEAttribute(tScheduleTypeEClass, TSCHEDULE_TYPE__SOURCE);

		tScheduleTypeVariantEClass = createEClass(TSCHEDULE_TYPE_VARIANT);
		createEReference(tScheduleTypeVariantEClass, TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT);
		createEReference(tScheduleTypeVariantEClass, TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT);
		createEReference(tScheduleTypeVariantEClass, TSCHEDULE_TYPE_VARIANT__PERSON_LOAD);
		createEReference(tScheduleTypeVariantEClass, TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD);
		createEReference(tScheduleTypeVariantEClass, TSCHEDULE_TYPE_VARIANT__SHADING);
		createEAttribute(tScheduleTypeVariantEClass, TSCHEDULE_TYPE_VARIANT__ID);

		tSetPersonLoadsEClass = createEClass(TSET_PERSON_LOADS);
		createEAttribute(tSetPersonLoadsEClass, TSET_PERSON_LOADS__VALUE);
		createEAttribute(tSetPersonLoadsEClass, TSET_PERSON_LOADS__PERIOD);
		createEAttribute(tSetPersonLoadsEClass, TSET_PERSON_LOADS__TYPE);
		createEAttribute(tSetPersonLoadsEClass, TSET_PERSON_LOADS__UNIT);

		tSetPointEClass = createEClass(TSET_POINT);
		createEAttribute(tSetPointEClass, TSET_POINT__VALUE);
		createEAttribute(tSetPointEClass, TSET_POINT__UNIT);

		tSetShadingEClass = createEClass(TSET_SHADING);
		createEAttribute(tSetShadingEClass, TSET_SHADING__VALUE);
		createEAttribute(tSetShadingEClass, TSET_SHADING__PERIOD);
		createEAttribute(tSetShadingEClass, TSET_SHADING__TYPE);
		createEAttribute(tSetShadingEClass, TSET_SHADING__UNIT);

		tSetTemperatureEClass = createEClass(TSET_TEMPERATURE);
		createEAttribute(tSetTemperatureEClass, TSET_TEMPERATURE__VALUE);
		createEAttribute(tSetTemperatureEClass, TSET_TEMPERATURE__PERIOD);
		createEAttribute(tSetTemperatureEClass, TSET_TEMPERATURE__TYPE);
		createEAttribute(tSetTemperatureEClass, TSET_TEMPERATURE__UNIT);

		tSimulationMatrixEClass = createEClass(TSIMULATION_MATRIX);
		createEReference(tSimulationMatrixEClass, TSIMULATION_MATRIX__TARGETS);
		createEReference(tSimulationMatrixEClass, TSIMULATION_MATRIX__VARIABLES);
		createEReference(tSimulationMatrixEClass, TSIMULATION_MATRIX__ASSIGNMENT_GROUPS);
		createEReference(tSimulationMatrixEClass, TSIMULATION_MATRIX__COMBINATIONS);
		createEAttribute(tSimulationMatrixEClass, TSIMULATION_MATRIX__ID);
		createEAttribute(tSimulationMatrixEClass, TSIMULATION_MATRIX__TYPE);

		tSpaceEClass = createEClass(TSPACE);
		createEAttribute(tSpaceEClass, TSPACE__ID);

		tSpaceGroupEClass = createEClass(TSPACE_GROUP);
		createEAttribute(tSpaceGroupEClass, TSPACE_GROUP__GROUP);
		createEAttribute(tSpaceGroupEClass, TSPACE_GROUP__SPACE);
		createEAttribute(tSpaceGroupEClass, TSPACE_GROUP__ID);

		tSpacesEClass = createEClass(TSPACES);
		createEAttribute(tSpacesEClass, TSPACES__GROUP);
		createEReference(tSpacesEClass, TSPACES__SPACE_GROUP);
		createEAttribute(tSpacesEClass, TSPACES__ID);

		tTargetEClass = createEClass(TTARGET);
		createEAttribute(tTargetEClass, TTARGET__VALUE);
		createEAttribute(tTargetEClass, TTARGET__TYPE);

		tTargetListEClass = createEClass(TTARGET_LIST);
		createEReference(tTargetListEClass, TTARGET_LIST__TARGET);
		createEAttribute(tTargetListEClass, TTARGET_LIST__ID);

		tTargetsEClass = createEClass(TTARGETS);
		createEReference(tTargetsEClass, TTARGETS__TARGET_LIST);

		tUsageEClass = createEClass(TUSAGE);
		createEAttribute(tUsageEClass, TUSAGE__GROUP);
		createEReference(tUsageEClass, TUSAGE__OCCUPANCY_TYPE);
		createEReference(tUsageEClass, TUSAGE__SCHEDULE_TYPE);

		tVariablesEClass = createEClass(TVARIABLES);
		createEReference(tVariablesEClass, TVARIABLES__WEATHER_VARIABLES);
		createEReference(tVariablesEClass, TVARIABLES__ORIENTATION_VARIABLES);
		createEReference(tVariablesEClass, TVARIABLES__ELEVATION_VARIABLES);
		createEReference(tVariablesEClass, TVARIABLES__USAGE_TYPE_VARIABLES);
		createEReference(tVariablesEClass, TVARIABLES__CONSTRUCTION_TYPE_VARIABLES);
		createEReference(tVariablesEClass, TVARIABLES__WINDOW_TYPE_VARIABLES);
		createEAttribute(tVariablesEClass, TVARIABLES__DOOR_TYPE_VARIABLES);
		createEReference(tVariablesEClass, TVARIABLES__MATERIAL_TYPE_VARIABLES);

		tVariantEClass = createEClass(TVARIANT);
		createEAttribute(tVariantEClass, TVARIANT__AREF);
		createEAttribute(tVariantEClass, TVARIANT__VREF);

		tWeatherEClass = createEClass(TWEATHER);
		createEAttribute(tWeatherEClass, TWEATHER__GROUP);
		createEReference(tWeatherEClass, TWEATHER__WEATHER_DATA_SET_VARIANT);
		createEReference(tWeatherEClass, TWEATHER__WEATHER_DATA_SERIES_VARIANT);

		tWeatherDataSeriesVariantEClass = createEClass(TWEATHER_DATA_SERIES_VARIANT);
		createEAttribute(tWeatherDataSeriesVariantEClass, TWEATHER_DATA_SERIES_VARIANT__AIR_TEMPERATURE);
		createEAttribute(tWeatherDataSeriesVariantEClass, TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIFFUSE);
		createEAttribute(tWeatherDataSeriesVariantEClass, TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIRECT);
		createEAttribute(tWeatherDataSeriesVariantEClass, TWEATHER_DATA_SERIES_VARIANT__ID);
		createEAttribute(tWeatherDataSeriesVariantEClass, TWEATHER_DATA_SERIES_VARIANT__TYPE);

		tWeatherDataSetVariantEClass = createEClass(TWEATHER_DATA_SET_VARIANT);
		createEAttribute(tWeatherDataSetVariantEClass, TWEATHER_DATA_SET_VARIANT__VALUE);
		createEAttribute(tWeatherDataSetVariantEClass, TWEATHER_DATA_SET_VARIANT__ID);
		createEAttribute(tWeatherDataSetVariantEClass, TWEATHER_DATA_SET_VARIANT__TYPE);

		tWindowTypeEClass = createEClass(TWINDOW_TYPE);
		createEReference(tWindowTypeEClass, TWINDOW_TYPE__WINDOW_TYPE_VARIANT);
		createEAttribute(tWindowTypeEClass, TWINDOW_TYPE__NAME);

		tWindowTypesEClass = createEClass(TWINDOW_TYPES);
		createEReference(tWindowTypesEClass, TWINDOW_TYPES__WINDOW_TYPE);

		tWindowTypeVariantEClass = createEClass(TWINDOW_TYPE_VARIANT);
		createEReference(tWindowTypeVariantEClass, TWINDOW_TYPE_VARIANT__GLASS_FRACTION);
		createEReference(tWindowTypeVariantEClass, TWINDOW_TYPE_VARIANT__FRAME_FRACTION);
		createEReference(tWindowTypeVariantEClass, TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE);
		createEReference(tWindowTypeVariantEClass, TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT);
		createEReference(tWindowTypeVariantEClass, TWINDOW_TYPE_VARIANT__SHADING_FACTOR);
		createEAttribute(tWindowTypeVariantEClass, TWINDOW_TYPE_VARIANT__ID);

		// Create enums
		distanceUnitEEnum = createEEnum(DISTANCE_UNIT);
		fileFormatEEnum = createEEnum(FILE_FORMAT);
		loadsEEnum = createEEnum(LOADS);
		materialUnitEEnum = createEEnum(MATERIAL_UNIT);
		orientationSideEEnum = createEEnum(ORIENTATION_SIDE);
		orientationUnitEEnum = createEEnum(ORIENTATION_UNIT);
		setPointEEnum = createEEnum(SET_POINT);
		shadingEEnum = createEEnum(SHADING);
		targetTypeEEnum = createEEnum(TARGET_TYPE);
		temperatureEEnum = createEEnum(TEMPERATURE);
		timePeriodEEnum = createEEnum(TIME_PERIOD);
		weatherTypesEEnum = createEEnum(WEATHER_TYPES);

		// Create data types
		distanceUnitObjectEDataType = createEDataType(DISTANCE_UNIT_OBJECT);
		fileFormatObjectEDataType = createEDataType(FILE_FORMAT_OBJECT);
		loadsObjectEDataType = createEDataType(LOADS_OBJECT);
		materialUnitObjectEDataType = createEDataType(MATERIAL_UNIT_OBJECT);
		orientationSideObjectEDataType = createEDataType(ORIENTATION_SIDE_OBJECT);
		orientationUnitObjectEDataType = createEDataType(ORIENTATION_UNIT_OBJECT);
		setPointObjectEDataType = createEDataType(SET_POINT_OBJECT);
		shadingObjectEDataType = createEDataType(SHADING_OBJECT);
		simatTypeEDataType = createEDataType(SIMAT_TYPE);
		targetTypeObjectEDataType = createEDataType(TARGET_TYPE_OBJECT);
		temperatureObjectEDataType = createEDataType(TEMPERATURE_OBJECT);
		timePeriodObjectEDataType = createEDataType(TIME_PERIOD_OBJECT);
		tStringListEDataType = createEDataType(TSTRING_LIST);
		weatherTypesObjectEDataType = createEDataType(WEATHER_TYPES_OBJECT);
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

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_AssignmentGroups(), this.getTAssignmentGroups(), null, "assignmentGroups", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_BIMREF(), theXMLTypePackage.getString(), "bIMREF", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Combination(), this.getTCombination(), null, "combination", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Combinations(), this.getTCombinations(), null, "combinations", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ConstantTypeVariant(), this.getTConstantTypeVariant(), null, "constantTypeVariant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ConstructionType(), this.getTConstructionType(), null, "constructionType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ConstructionTypeVariables(), this.getTConstructionTypes(), null, "constructionTypeVariables", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ConstructionTypeVariant(), this.getTConstructionTypeVariant(), null, "constructionTypeVariant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_DoorTypeVariables(), theXMLTypePackage.getString(), "doorTypeVariables", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Element(), theXMLTypePackage.getString(), "element", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ElementGroup(), this.getTElementGroup(), null, "elementGroup", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Elements(), this.getTElements(), null, "elements", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ElevationVariables(), this.getTElevation(), null, "elevationVariables", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ElevationVariant(), this.getTElevationVariant(), null, "elevationVariant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Layer(), this.getTLayer(), null, "layer", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MaterialType(), this.getTMaterialType(), null, "materialType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MaterialTypeVariables(), this.getTMaterialTypes(), null, "materialTypeVariables", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MaterialTypeVariant(), this.getTMaterialTypeVariant(), null, "materialTypeVariant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_OccupancyType(), this.getTOccupancyType(), null, "occupancyType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_OccupancyTypeVariant(), this.getTOccupancyTypeVariant(), null, "occupancyTypeVariant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_OrientationVariables(), this.getTOrientation(), null, "orientationVariables", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_OrientationVariant(), this.getTOrientationVariant(), null, "orientationVariant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ScheduleType(), this.getTScheduleType(), null, "scheduleType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ScheduleTypeVariant(), this.getTScheduleTypeVariant(), null, "scheduleTypeVariant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SimulationMatrix(), this.getTSimulationMatrix(), null, "simulationMatrix", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Space(), theXMLTypePackage.getString(), "space", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SpaceGroup(), this.getTSpaceGroup(), null, "spaceGroup", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Spaces(), this.getTSpaces(), null, "spaces", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Target(), this.getTTarget(), null, "target", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TargetList(), this.getTTargetList(), null, "targetList", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Targets(), this.getTTargets(), null, "targets", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_UsageTypeVariables(), this.getTUsage(), null, "usageTypeVariables", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Variables(), this.getTVariables(), null, "variables", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Variant(), this.getTVariant(), null, "variant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_WeatherDataSeriesVariant(), this.getTWeatherDataSeriesVariant(), null, "weatherDataSeriesVariant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_WeatherDataSetVariant(), this.getTWeatherDataSetVariant(), null, "weatherDataSetVariant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_WeatherVariables(), this.getTWeather(), null, "weatherVariables", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_WindowType(), this.getTWindowType(), null, "windowType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_WindowTypeVariables(), this.getTWindowTypes(), null, "windowTypeVariables", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_WindowTypeVariant(), this.getTWindowTypeVariant(), null, "windowTypeVariant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(tAssignmentGroupsEClass, TAssignmentGroups.class, "TAssignmentGroups", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTAssignmentGroups_Spaces(), this.getTSpaces(), null, "spaces", null, 0, 1, TAssignmentGroups.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTAssignmentGroups_Elements(), this.getTElements(), null, "elements", null, 0, 1, TAssignmentGroups.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tbimGroupEClass, TBIMGroup.class, "TBIMGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTBIMGroup_BIMREF(), theXMLTypePackage.getString(), "bIMREF", null, 1, -1, TBIMGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTBIMGroup_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TBIMGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tCombinationEClass, TCombination.class, "TCombination", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTCombination_Variant(), this.getTVariant(), null, "variant", null, 1, -1, TCombination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTCombination_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TCombination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tCombinationsEClass, TCombinations.class, "TCombinations", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTCombinations_Combination(), this.getTCombination(), null, "combination", null, 1, -1, TCombinations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tConstantTypeVariantEClass, TConstantTypeVariant.class, "TConstantTypeVariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTConstantTypeVariant_HeatingSetPoint(), this.getTSetPoint(), null, "heatingSetPoint", null, 0, 1, TConstantTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTConstantTypeVariant_CoolingSetPoint(), this.getTSetPoint(), null, "coolingSetPoint", null, 0, 1, TConstantTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTConstantTypeVariant_PersonLoad(), this.getTSetPoint(), null, "personLoad", null, 0, 1, TConstantTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTConstantTypeVariant_EquipmentLoad(), this.getTSetPoint(), null, "equipmentLoad", null, 0, 1, TConstantTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTConstantTypeVariant_Shading(), this.getTSetPoint(), null, "shading", null, 0, 1, TConstantTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTConstantTypeVariant_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TConstantTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tConstructionTypeEClass, TConstructionType.class, "TConstructionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTConstructionType_ConstructionTypeVariant(), this.getTConstructionTypeVariant(), null, "constructionTypeVariant", null, 1, -1, TConstructionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTConstructionType_Orientation(), this.getOrientationSide(), "orientation", null, 0, 1, TConstructionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTConstructionType_Source(), theXMLTypePackage.getAnyURI(), "source", null, 1, 1, TConstructionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tConstructionTypesEClass, TConstructionTypes.class, "TConstructionTypes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTConstructionTypes_ConstructionType(), this.getTConstructionType(), null, "constructionType", null, 1, -1, TConstructionTypes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tConstructionTypeVariantEClass, TConstructionTypeVariant.class, "TConstructionTypeVariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTConstructionTypeVariant_Layer(), this.getTLayer(), null, "layer", null, 1, -1, TConstructionTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTConstructionTypeVariant_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TConstructionTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tElementGroupEClass, TElementGroup.class, "TElementGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTElementGroup_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TElementGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTElementGroup_Element(), theXMLTypePackage.getString(), "element", null, 1, -1, TElementGroup.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTElementGroup_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, TElementGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tElementsEClass, TElements.class, "TElements", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTElements_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTElements_ElementGroup(), this.getTElementGroup(), null, "elementGroup", null, 1, -1, TElements.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(tElevationEClass, TElevation.class, "TElevation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTElevation_ElevationVariant(), this.getTElevationVariant(), null, "elevationVariant", null, 1, -1, TElevation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tElevationVariantEClass, TElevationVariant.class, "TElevationVariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTElevationVariant_Value(), theXMLTypePackage.getDouble(), "value", null, 0, 1, TElevationVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTElevationVariant_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TElevationVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTElevationVariant_Unit(), this.getDistanceUnit(), "unit", "m", 0, 1, TElevationVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tFloatWithUnitsEClass, TFloatWithUnits.class, "TFloatWithUnits", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTFloatWithUnits_Value(), theXMLTypePackage.getDouble(), "value", null, 0, 1, TFloatWithUnits.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTFloatWithUnits_Unit(), this.getMaterialUnit(), "unit", null, 1, 1, TFloatWithUnits.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tLayerEClass, TLayer.class, "TLayer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTLayer_Value(), theXMLTypePackage.getAnyURI(), "value", null, 0, 1, TLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTLayer_Thickness(), theXMLTypePackage.getDouble(), "thickness", null, 1, 1, TLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTLayer_Unit(), this.getDistanceUnit(), "unit", "m", 0, 1, TLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tMaterialTypeEClass, TMaterialType.class, "TMaterialType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTMaterialType_MaterialTypeVariant(), this.getTMaterialTypeVariant(), null, "materialTypeVariant", null, 1, -1, TMaterialType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTMaterialType_Source(), theXMLTypePackage.getAnyURI(), "source", null, 1, 1, TMaterialType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tMaterialTypesEClass, TMaterialTypes.class, "TMaterialTypes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTMaterialTypes_MaterialType(), this.getTMaterialType(), null, "materialType", null, 1, -1, TMaterialTypes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tMaterialTypeVariantEClass, TMaterialTypeVariant.class, "TMaterialTypeVariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTMaterialTypeVariant_Density(), this.getTFloatWithUnits(), null, "density", null, 0, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTMaterialTypeVariant_SpecificHeatCapacity(), this.getTFloatWithUnits(), null, "specificHeatCapacity", null, 0, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTMaterialTypeVariant_Conductivity(), this.getTFloatWithUnits(), null, "conductivity", null, 0, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTMaterialTypeVariant_WaterVaporDiffusionResistanceFactor(), this.getTFloatWithUnits(), null, "waterVaporDiffusionResistanceFactor", null, 0, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTMaterialTypeVariant_WaterAbsorptionCapacity(), this.getTFloatWithUnits(), null, "waterAbsorptionCapacity", null, 0, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTMaterialTypeVariant_OpenPorosity(), this.getTFloatWithUnits(), null, "openPorosity", null, 0, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTMaterialTypeVariant_EffectiveSaturation(), this.getTFloatWithUnits(), null, "effectiveSaturation", null, 0, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTMaterialTypeVariant_CapillarySaturationContent(), this.getTFloatWithUnits(), null, "capillarySaturationContent", null, 0, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTMaterialTypeVariant_HygroscopicSorption(), this.getTFloatWithUnits(), null, "hygroscopicSorption", null, 0, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTMaterialTypeVariant_ThermalConductivity(), this.getTFloatWithUnits(), null, "thermalConductivity", null, 0, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTMaterialTypeVariant_LiquidWaterConductivityEffectiveSaturation(), this.getTFloatWithUnits(), null, "liquidWaterConductivityEffectiveSaturation", null, 0, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTMaterialTypeVariant_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TMaterialTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tOccupancyTypeEClass, TOccupancyType.class, "TOccupancyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTOccupancyType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TOccupancyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTOccupancyType_OccupancyTypeVariant(), this.getTOccupancyTypeVariant(), null, "occupancyTypeVariant", null, 1, -1, TOccupancyType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTOccupancyType_Source(), theXMLTypePackage.getAnyURI(), "source", null, 1, 1, TOccupancyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tOccupancyTypeVariantEClass, TOccupancyTypeVariant.class, "TOccupancyTypeVariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTOccupancyTypeVariant_Value(), theXMLTypePackage.getAnyURI(), "value", null, 0, 1, TOccupancyTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTOccupancyTypeVariant_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TOccupancyTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTOccupancyTypeVariant_Period(), this.getTimePeriod(), "period", "annual", 0, 1, TOccupancyTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTOccupancyTypeVariant_Type(), this.getFileFormat(), "type", "CSV", 0, 1, TOccupancyTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tOrientationEClass, TOrientation.class, "TOrientation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTOrientation_OrientationVariant(), this.getTOrientationVariant(), null, "orientationVariant", null, 1, -1, TOrientation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tOrientationVariantEClass, TOrientationVariant.class, "TOrientationVariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTOrientationVariant_Value(), theXMLTypePackage.getDouble(), "value", null, 0, 1, TOrientationVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTOrientationVariant_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TOrientationVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTOrientationVariant_Unit(), this.getOrientationUnit(), "unit", "Deg", 0, 1, TOrientationVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tScheduleTypeEClass, TScheduleType.class, "TScheduleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTScheduleType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TScheduleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTScheduleType_ScheduleTypeVariant(), this.getTScheduleTypeVariant(), null, "scheduleTypeVariant", null, 0, -1, TScheduleType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTScheduleType_ConstantTypeVariant(), this.getTConstantTypeVariant(), null, "constantTypeVariant", null, 0, -1, TScheduleType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTScheduleType_Source(), theXMLTypePackage.getAnyURI(), "source", null, 1, 1, TScheduleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tScheduleTypeVariantEClass, TScheduleTypeVariant.class, "TScheduleTypeVariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTScheduleTypeVariant_HeatingSetPoint(), this.getTSetTemperature(), null, "heatingSetPoint", null, 0, 1, TScheduleTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTScheduleTypeVariant_CoolingSetPoint(), this.getTSetTemperature(), null, "coolingSetPoint", null, 0, 1, TScheduleTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTScheduleTypeVariant_PersonLoad(), this.getTSetPersonLoads(), null, "personLoad", null, 0, 1, TScheduleTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTScheduleTypeVariant_EquipmentLoad(), this.getTSetPersonLoads(), null, "equipmentLoad", null, 0, 1, TScheduleTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTScheduleTypeVariant_Shading(), this.getTSetShading(), null, "shading", null, 0, 1, TScheduleTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTScheduleTypeVariant_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TScheduleTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tSetPersonLoadsEClass, TSetPersonLoads.class, "TSetPersonLoads", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTSetPersonLoads_Value(), theXMLTypePackage.getAnyURI(), "value", null, 0, 1, TSetPersonLoads.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSetPersonLoads_Period(), this.getTimePeriod(), "period", "annual", 0, 1, TSetPersonLoads.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSetPersonLoads_Type(), this.getFileFormat(), "type", "CSV", 0, 1, TSetPersonLoads.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSetPersonLoads_Unit(), this.getLoads(), "unit", "W", 0, 1, TSetPersonLoads.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tSetPointEClass, TSetPoint.class, "TSetPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTSetPoint_Value(), theXMLTypePackage.getDouble(), "value", null, 0, 1, TSetPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSetPoint_Unit(), this.getSetPoint(), "unit", "W", 0, 1, TSetPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tSetShadingEClass, TSetShading.class, "TSetShading", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTSetShading_Value(), theXMLTypePackage.getAnyURI(), "value", null, 0, 1, TSetShading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSetShading_Period(), this.getTimePeriod(), "period", "annual", 0, 1, TSetShading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSetShading_Type(), this.getFileFormat(), "type", "CSV", 0, 1, TSetShading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSetShading_Unit(), this.getShading(), "unit", "Frac", 0, 1, TSetShading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tSetTemperatureEClass, TSetTemperature.class, "TSetTemperature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTSetTemperature_Value(), theXMLTypePackage.getAnyURI(), "value", null, 0, 1, TSetTemperature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSetTemperature_Period(), this.getTimePeriod(), "period", "annual", 0, 1, TSetTemperature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSetTemperature_Type(), this.getFileFormat(), "type", "CSV", 0, 1, TSetTemperature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSetTemperature_Unit(), this.getTemperature(), "unit", "C", 0, 1, TSetTemperature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tSimulationMatrixEClass, TSimulationMatrix.class, "TSimulationMatrix", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTSimulationMatrix_Targets(), this.getTTargets(), null, "targets", null, 0, 1, TSimulationMatrix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTSimulationMatrix_Variables(), this.getTVariables(), null, "variables", null, 1, 1, TSimulationMatrix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTSimulationMatrix_AssignmentGroups(), this.getTAssignmentGroups(), null, "assignmentGroups", null, 0, 1, TSimulationMatrix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTSimulationMatrix_Combinations(), this.getTCombinations(), null, "combinations", null, 0, 1, TSimulationMatrix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSimulationMatrix_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, TSimulationMatrix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSimulationMatrix_Type(), this.getSimatType(), "type", null, 1, 1, TSimulationMatrix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tSpaceEClass, TSpace.class, "TSpace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTSpace_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, TSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tSpaceGroupEClass, TSpaceGroup.class, "TSpaceGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTSpaceGroup_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TSpaceGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSpaceGroup_Space(), theXMLTypePackage.getString(), "space", null, 1, -1, TSpaceGroup.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSpaceGroup_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, TSpaceGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tSpacesEClass, TSpaces.class, "TSpaces", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTSpaces_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TSpaces.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTSpaces_SpaceGroup(), this.getTSpaceGroup(), null, "spaceGroup", null, 1, -1, TSpaces.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTSpaces_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, TSpaces.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tTargetEClass, TTarget.class, "TTarget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTTarget_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, TTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTTarget_Type(), this.getTargetType(), "type", "Space", 0, 1, TTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tTargetListEClass, TTargetList.class, "TTargetList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTTargetList_Target(), this.getTTarget(), null, "target", null, 1, -1, TTargetList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTTargetList_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, TTargetList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tTargetsEClass, TTargets.class, "TTargets", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTTargets_TargetList(), this.getTTargetList(), null, "targetList", null, 1, -1, TTargets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tUsageEClass, TUsage.class, "TUsage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTUsage_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTUsage_OccupancyType(), this.getTOccupancyType(), null, "occupancyType", null, 0, -1, TUsage.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTUsage_ScheduleType(), this.getTScheduleType(), null, "scheduleType", null, 0, -1, TUsage.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(tVariablesEClass, TVariables.class, "TVariables", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTVariables_WeatherVariables(), this.getTWeather(), null, "weatherVariables", null, 0, 1, TVariables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTVariables_OrientationVariables(), this.getTOrientation(), null, "orientationVariables", null, 0, 1, TVariables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTVariables_ElevationVariables(), this.getTElevation(), null, "elevationVariables", null, 0, 1, TVariables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTVariables_UsageTypeVariables(), this.getTUsage(), null, "usageTypeVariables", null, 0, 1, TVariables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTVariables_ConstructionTypeVariables(), this.getTConstructionTypes(), null, "constructionTypeVariables", null, 0, 1, TVariables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTVariables_WindowTypeVariables(), this.getTWindowTypes(), null, "windowTypeVariables", null, 0, 1, TVariables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTVariables_DoorTypeVariables(), theXMLTypePackage.getString(), "doorTypeVariables", null, 0, 1, TVariables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTVariables_MaterialTypeVariables(), this.getTMaterialTypes(), null, "materialTypeVariables", null, 0, 1, TVariables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tVariantEClass, TVariant.class, "TVariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTVariant_AREF(), theXMLTypePackage.getIDREF(), "aREF", null, 0, 1, TVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTVariant_VREF(), theXMLTypePackage.getIDREF(), "vREF", null, 1, 1, TVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tWeatherEClass, TWeather.class, "TWeather", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTWeather_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TWeather.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTWeather_WeatherDataSetVariant(), this.getTWeatherDataSetVariant(), null, "weatherDataSetVariant", null, 0, -1, TWeather.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTWeather_WeatherDataSeriesVariant(), this.getTWeatherDataSeriesVariant(), null, "weatherDataSeriesVariant", null, 0, -1, TWeather.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(tWeatherDataSeriesVariantEClass, TWeatherDataSeriesVariant.class, "TWeatherDataSeriesVariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTWeatherDataSeriesVariant_AirTemperature(), theXMLTypePackage.getAnyURI(), "airTemperature", null, 0, 1, TWeatherDataSeriesVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTWeatherDataSeriesVariant_ShortWaveDiffuse(), theXMLTypePackage.getAnyURI(), "shortWaveDiffuse", null, 0, 1, TWeatherDataSeriesVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTWeatherDataSeriesVariant_ShortWaveDirect(), theXMLTypePackage.getAnyURI(), "shortWaveDirect", null, 0, 1, TWeatherDataSeriesVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTWeatherDataSeriesVariant_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TWeatherDataSeriesVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTWeatherDataSeriesVariant_Type(), this.getWeatherTypes(), "type", "CCD", 0, 1, TWeatherDataSeriesVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tWeatherDataSetVariantEClass, TWeatherDataSetVariant.class, "TWeatherDataSetVariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTWeatherDataSetVariant_Value(), theXMLTypePackage.getAnyURI(), "value", null, 0, 1, TWeatherDataSetVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTWeatherDataSetVariant_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TWeatherDataSetVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTWeatherDataSetVariant_Type(), this.getWeatherTypes(), "type", "TMY", 0, 1, TWeatherDataSetVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tWindowTypeEClass, TWindowType.class, "TWindowType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTWindowType_WindowTypeVariant(), this.getTWindowTypeVariant(), null, "windowTypeVariant", null, 1, -1, TWindowType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTWindowType_Name(), theXMLTypePackage.getAnyURI(), "name", null, 1, 1, TWindowType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tWindowTypesEClass, TWindowTypes.class, "TWindowTypes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTWindowTypes_WindowType(), this.getTWindowType(), null, "windowType", null, 1, -1, TWindowTypes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tWindowTypeVariantEClass, TWindowTypeVariant.class, "TWindowTypeVariant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTWindowTypeVariant_GlassFraction(), this.getTFloatWithUnits(), null, "glassFraction", null, 0, 1, TWindowTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTWindowTypeVariant_FrameFraction(), this.getTFloatWithUnits(), null, "frameFraction", null, 0, 1, TWindowTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTWindowTypeVariant_ThermalTransmittance(), this.getTFloatWithUnits(), null, "thermalTransmittance", null, 0, 1, TWindowTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTWindowTypeVariant_SolarHeatGainCoefficient(), this.getTFloatWithUnits(), null, "solarHeatGainCoefficient", null, 0, 1, TWindowTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTWindowTypeVariant_ShadingFactor(), this.getTFloatWithUnits(), null, "shadingFactor", null, 0, 1, TWindowTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTWindowTypeVariant_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TWindowTypeVariant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(distanceUnitEEnum, DistanceUnit.class, "DistanceUnit");
		addEEnumLiteral(distanceUnitEEnum, DistanceUnit.M);
		addEEnumLiteral(distanceUnitEEnum, DistanceUnit.CM);
		addEEnumLiteral(distanceUnitEEnum, DistanceUnit.MM);
		addEEnumLiteral(distanceUnitEEnum, DistanceUnit.YD);
		addEEnumLiteral(distanceUnitEEnum, DistanceUnit.IN);
		addEEnumLiteral(distanceUnitEEnum, DistanceUnit.FT);

		initEEnum(fileFormatEEnum, FileFormat.class, "FileFormat");
		addEEnumLiteral(fileFormatEEnum, FileFormat.CSV);
		addEEnumLiteral(fileFormatEEnum, FileFormat.TSV);
		addEEnumLiteral(fileFormatEEnum, FileFormat.XML);

		initEEnum(loadsEEnum, Loads.class, "Loads");
		addEEnumLiteral(loadsEEnum, Loads.W);
		addEEnumLiteral(loadsEEnum, Loads.KW);
		addEEnumLiteral(loadsEEnum, Loads.WM2);

		initEEnum(materialUnitEEnum, MaterialUnit.class, "MaterialUnit");
		addEEnumLiteral(materialUnitEEnum, MaterialUnit.J);
		addEEnumLiteral(materialUnitEEnum, MaterialUnit.JKG_K);
		addEEnumLiteral(materialUnitEEnum, MaterialUnit.WM2K);
		addEEnumLiteral(materialUnitEEnum, MaterialUnit.WMK);
		addEEnumLiteral(materialUnitEEnum, MaterialUnit.KG_M3);
		addEEnumLiteral(materialUnitEEnum, MaterialUnit._);
		addEEnumLiteral(materialUnitEEnum, MaterialUnit.M3_M3);
		addEEnumLiteral(materialUnitEEnum, MaterialUnit.KG_M2S05);
		addEEnumLiteral(materialUnitEEnum, MaterialUnit._1);
		addEEnumLiteral(materialUnitEEnum, MaterialUnit.FRAC);

		initEEnum(orientationSideEEnum, OrientationSide.class, "OrientationSide");
		addEEnumLiteral(orientationSideEEnum, OrientationSide.A);
		addEEnumLiteral(orientationSideEEnum, OrientationSide.B);
		addEEnumLiteral(orientationSideEEnum, OrientationSide.LEFT);
		addEEnumLiteral(orientationSideEEnum, OrientationSide.RIGHT);

		initEEnum(orientationUnitEEnum, OrientationUnit.class, "OrientationUnit");
		addEEnumLiteral(orientationUnitEEnum, OrientationUnit.DEG);
		addEEnumLiteral(orientationUnitEEnum, OrientationUnit.RAD);

		initEEnum(setPointEEnum, SetPoint.class, "SetPoint");
		addEEnumLiteral(setPointEEnum, SetPoint._);
		addEEnumLiteral(setPointEEnum, SetPoint.FRAC);
		addEEnumLiteral(setPointEEnum, SetPoint.W);
		addEEnumLiteral(setPointEEnum, SetPoint.KW);
		addEEnumLiteral(setPointEEnum, SetPoint.WM2);
		addEEnumLiteral(setPointEEnum, SetPoint.C);
		addEEnumLiteral(setPointEEnum, SetPoint.R);
		addEEnumLiteral(setPointEEnum, SetPoint.F);
		addEEnumLiteral(setPointEEnum, SetPoint.K);

		initEEnum(shadingEEnum, Shading.class, "Shading");
		addEEnumLiteral(shadingEEnum, Shading._);
		addEEnumLiteral(shadingEEnum, Shading.FRAC);

		initEEnum(targetTypeEEnum, TargetType.class, "TargetType");
		addEEnumLiteral(targetTypeEEnum, TargetType.ZONE);
		addEEnumLiteral(targetTypeEEnum, TargetType.SPACE);
		addEEnumLiteral(targetTypeEEnum, TargetType.STOREY);
		addEEnumLiteral(targetTypeEEnum, TargetType.BUILDING);

		initEEnum(temperatureEEnum, Temperature.class, "Temperature");
		addEEnumLiteral(temperatureEEnum, Temperature.C);
		addEEnumLiteral(temperatureEEnum, Temperature.R);
		addEEnumLiteral(temperatureEEnum, Temperature.F);
		addEEnumLiteral(temperatureEEnum, Temperature.K);

		initEEnum(timePeriodEEnum, TimePeriod.class, "TimePeriod");
		addEEnumLiteral(timePeriodEEnum, TimePeriod.DAILY);
		addEEnumLiteral(timePeriodEEnum, TimePeriod.WEEKLY);
		addEEnumLiteral(timePeriodEEnum, TimePeriod.MONTHLY);
		addEEnumLiteral(timePeriodEEnum, TimePeriod.ANNUAL);

		initEEnum(weatherTypesEEnum, WeatherTypes.class, "WeatherTypes");
		addEEnumLiteral(weatherTypesEEnum, WeatherTypes.CCD);
		addEEnumLiteral(weatherTypesEEnum, WeatherTypes.TMY);

		// Initialize data types
		initEDataType(distanceUnitObjectEDataType, DistanceUnit.class, "DistanceUnitObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(fileFormatObjectEDataType, FileFormat.class, "FileFormatObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(loadsObjectEDataType, Loads.class, "LoadsObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(materialUnitObjectEDataType, MaterialUnit.class, "MaterialUnitObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(orientationSideObjectEDataType, OrientationSide.class, "OrientationSideObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(orientationUnitObjectEDataType, OrientationUnit.class, "OrientationUnitObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(setPointObjectEDataType, SetPoint.class, "SetPointObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(shadingObjectEDataType, Shading.class, "ShadingObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(simatTypeEDataType, String.class, "SimatType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(targetTypeObjectEDataType, TargetType.class, "TargetTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(temperatureObjectEDataType, Temperature.class, "TemperatureObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(timePeriodObjectEDataType, TimePeriod.class, "TimePeriodObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(tStringListEDataType, List.class, "TStringList", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(weatherTypesObjectEDataType, WeatherTypes.class, "WeatherTypesObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "qualified", "false"
		   });		
		addAnnotation
		  (distanceUnitEEnum, 
		   source, 
		   new String[] {
			 "name", "distanceUnit"
		   });		
		addAnnotation
		  (distanceUnitObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "distanceUnit:Object",
			 "baseType", "distanceUnit"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_AssignmentGroups(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "AssignmentGroups",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_BIMREF(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BIMREF",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Combination(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Combination",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Combinations(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Combinations",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ConstantTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ConstantTypeVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ConstructionType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ConstructionType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ConstructionTypeVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ConstructionTypeVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ConstructionTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ConstructionTypeVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_DoorTypeVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DoorTypeVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Element(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Element",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ElementGroup(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ElementGroup",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Elements(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Elements",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ElevationVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ElevationVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ElevationVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ElevationVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Layer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Layer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_MaterialType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MaterialType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_MaterialTypeVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MaterialTypeVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_MaterialTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MaterialTypeVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_OccupancyType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OccupancyType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_OccupancyTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OccupancyTypeVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_OrientationVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OrientationVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_OrientationVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OrientationVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ScheduleType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ScheduleType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ScheduleTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ScheduleTypeVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_SimulationMatrix(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SimulationMatrix",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Space(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Space",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_SpaceGroup(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SpaceGroup",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Spaces(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Spaces",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Target(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Target",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_TargetList(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TargetList",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Targets(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Targets",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_UsageTypeVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "UsageTypeVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Variables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Variables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Variant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Variant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_WeatherDataSeriesVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WeatherDataSeriesVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_WeatherDataSetVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WeatherDataSetVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_WeatherVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WeatherVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_WindowType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WindowType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_WindowTypeVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WindowTypeVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_WindowTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WindowTypeVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (fileFormatEEnum, 
		   source, 
		   new String[] {
			 "name", "fileFormat"
		   });		
		addAnnotation
		  (fileFormatObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "fileFormat:Object",
			 "baseType", "fileFormat"
		   });		
		addAnnotation
		  (loadsEEnum, 
		   source, 
		   new String[] {
			 "name", "loads"
		   });		
		addAnnotation
		  (loadsObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "loads:Object",
			 "baseType", "loads"
		   });		
		addAnnotation
		  (materialUnitEEnum, 
		   source, 
		   new String[] {
			 "name", "materialUnit"
		   });		
		addAnnotation
		  (materialUnitObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "materialUnit:Object",
			 "baseType", "materialUnit"
		   });		
		addAnnotation
		  (orientationSideEEnum, 
		   source, 
		   new String[] {
			 "name", "orientationSide"
		   });		
		addAnnotation
		  (orientationSideObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "orientationSide:Object",
			 "baseType", "orientationSide"
		   });		
		addAnnotation
		  (orientationUnitEEnum, 
		   source, 
		   new String[] {
			 "name", "orientationUnit"
		   });		
		addAnnotation
		  (orientationUnitObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "orientationUnit:Object",
			 "baseType", "orientationUnit"
		   });		
		addAnnotation
		  (setPointEEnum, 
		   source, 
		   new String[] {
			 "name", "setPoint"
		   });		
		addAnnotation
		  (setPointObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "setPoint:Object",
			 "baseType", "setPoint"
		   });		
		addAnnotation
		  (shadingEEnum, 
		   source, 
		   new String[] {
			 "name", "shading"
		   });		
		addAnnotation
		  (shadingObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "shading:Object",
			 "baseType", "shading"
		   });		
		addAnnotation
		  (simatTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "simatType",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string",
			 "pattern", "SensitivityAnalysis|DesignAlternatives|StochasticSimulation"
		   });		
		addAnnotation
		  (targetTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "targetType"
		   });		
		addAnnotation
		  (targetTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "targetType:Object",
			 "baseType", "targetType"
		   });		
		addAnnotation
		  (tAssignmentGroupsEClass, 
		   source, 
		   new String[] {
			 "name", "T_AssignmentGroups",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTAssignmentGroups_Spaces(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Spaces",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTAssignmentGroups_Elements(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Elements",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tbimGroupEClass, 
		   source, 
		   new String[] {
			 "name", "T_BIMGroup",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTBIMGroup_BIMREF(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BIMREF",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTBIMGroup_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tCombinationEClass, 
		   source, 
		   new String[] {
			 "name", "T_Combination",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTCombination_Variant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Variant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTCombination_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tCombinationsEClass, 
		   source, 
		   new String[] {
			 "name", "T_Combinations",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTCombinations_Combination(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Combination",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tConstantTypeVariantEClass, 
		   source, 
		   new String[] {
			 "name", "T_ConstantTypeVariant",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTConstantTypeVariant_HeatingSetPoint(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "HeatingSetPoint",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTConstantTypeVariant_CoolingSetPoint(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "CoolingSetPoint",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTConstantTypeVariant_PersonLoad(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PersonLoad",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTConstantTypeVariant_EquipmentLoad(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EquipmentLoad",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTConstantTypeVariant_Shading(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Shading",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTConstantTypeVariant_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tConstructionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "T_ConstructionType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTConstructionType_ConstructionTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ConstructionTypeVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTConstructionType_Orientation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "orientation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTConstructionType_Source(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "source",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tConstructionTypesEClass, 
		   source, 
		   new String[] {
			 "name", "T_ConstructionTypes",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTConstructionTypes_ConstructionType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ConstructionType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tConstructionTypeVariantEClass, 
		   source, 
		   new String[] {
			 "name", "T_ConstructionTypeVariant",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTConstructionTypeVariant_Layer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Layer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTConstructionTypeVariant_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tElementGroupEClass, 
		   source, 
		   new String[] {
			 "name", "T_ElementGroup",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTElementGroup_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTElementGroup_Element(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Element",
			 "namespace", "##targetNamespace",
			 "group", "group:0"
		   });		
		addAnnotation
		  (getTElementGroup_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tElementsEClass, 
		   source, 
		   new String[] {
			 "name", "T_Elements",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTElements_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTElements_ElementGroup(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ElementGroup",
			 "namespace", "##targetNamespace",
			 "group", "group:0"
		   });		
		addAnnotation
		  (tElevationEClass, 
		   source, 
		   new String[] {
			 "name", "T_Elevation",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTElevation_ElevationVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ElevationVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tElevationVariantEClass, 
		   source, 
		   new String[] {
			 "name", "T_ElevationVariant",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTElevationVariant_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTElevationVariant_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTElevationVariant_Unit(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (temperatureEEnum, 
		   source, 
		   new String[] {
			 "name", "temperature"
		   });		
		addAnnotation
		  (temperatureObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "temperature:Object",
			 "baseType", "temperature"
		   });		
		addAnnotation
		  (tFloatWithUnitsEClass, 
		   source, 
		   new String[] {
			 "name", "T_FloatWithUnits",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTFloatWithUnits_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTFloatWithUnits_Unit(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (timePeriodEEnum, 
		   source, 
		   new String[] {
			 "name", "timePeriod"
		   });		
		addAnnotation
		  (timePeriodObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "timePeriod:Object",
			 "baseType", "timePeriod"
		   });		
		addAnnotation
		  (tLayerEClass, 
		   source, 
		   new String[] {
			 "name", "T_Layer",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTLayer_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTLayer_Thickness(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "thickness",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTLayer_Unit(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tMaterialTypeEClass, 
		   source, 
		   new String[] {
			 "name", "T_MaterialType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTMaterialType_MaterialTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MaterialTypeVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialType_Source(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "source",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tMaterialTypesEClass, 
		   source, 
		   new String[] {
			 "name", "T_MaterialTypes",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTMaterialTypes_MaterialType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MaterialType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tMaterialTypeVariantEClass, 
		   source, 
		   new String[] {
			 "name", "T_MaterialTypeVariant",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_Density(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Density",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_SpecificHeatCapacity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SpecificHeatCapacity",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_Conductivity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Conductivity",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_WaterVaporDiffusionResistanceFactor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WaterVaporDiffusionResistanceFactor",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_WaterAbsorptionCapacity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WaterAbsorptionCapacity",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_OpenPorosity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OpenPorosity",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_EffectiveSaturation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EffectiveSaturation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_CapillarySaturationContent(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "CapillarySaturationContent",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_HygroscopicSorption(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "HygroscopicSorption",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_ThermalConductivity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ThermalConductivity",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_LiquidWaterConductivityEffectiveSaturation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "LiquidWaterConductivityEffectiveSaturation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTMaterialTypeVariant_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tOccupancyTypeEClass, 
		   source, 
		   new String[] {
			 "name", "T_OccupancyType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTOccupancyType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTOccupancyType_OccupancyTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OccupancyTypeVariant",
			 "namespace", "##targetNamespace",
			 "group", "group:0"
		   });		
		addAnnotation
		  (getTOccupancyType_Source(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "source",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tOccupancyTypeVariantEClass, 
		   source, 
		   new String[] {
			 "name", "T_OccupancyTypeVariant",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTOccupancyTypeVariant_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTOccupancyTypeVariant_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTOccupancyTypeVariant_Period(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "period",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTOccupancyTypeVariant_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tOrientationEClass, 
		   source, 
		   new String[] {
			 "name", "T_Orientation",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTOrientation_OrientationVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OrientationVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tOrientationVariantEClass, 
		   source, 
		   new String[] {
			 "name", "T_OrientationVariant",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTOrientationVariant_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTOrientationVariant_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTOrientationVariant_Unit(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tScheduleTypeEClass, 
		   source, 
		   new String[] {
			 "name", "T_ScheduleType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTScheduleType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTScheduleType_ScheduleTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ScheduleTypeVariant",
			 "namespace", "##targetNamespace",
			 "group", "group:0"
		   });		
		addAnnotation
		  (getTScheduleType_ConstantTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ConstantTypeVariant",
			 "namespace", "##targetNamespace",
			 "group", "group:0"
		   });		
		addAnnotation
		  (getTScheduleType_Source(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "source",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tScheduleTypeVariantEClass, 
		   source, 
		   new String[] {
			 "name", "T_ScheduleTypeVariant",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTScheduleTypeVariant_HeatingSetPoint(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "HeatingSetPoint",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTScheduleTypeVariant_CoolingSetPoint(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "CoolingSetPoint",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTScheduleTypeVariant_PersonLoad(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PersonLoad",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTScheduleTypeVariant_EquipmentLoad(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EquipmentLoad",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTScheduleTypeVariant_Shading(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Shading",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTScheduleTypeVariant_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tSetPersonLoadsEClass, 
		   source, 
		   new String[] {
			 "name", "T_SetPersonLoads",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTSetPersonLoads_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTSetPersonLoads_Period(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "period",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTSetPersonLoads_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTSetPersonLoads_Unit(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tSetPointEClass, 
		   source, 
		   new String[] {
			 "name", "T_SetPoint",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTSetPoint_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTSetPoint_Unit(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tSetShadingEClass, 
		   source, 
		   new String[] {
			 "name", "T_SetShading",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTSetShading_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTSetShading_Period(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "period",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTSetShading_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTSetShading_Unit(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tSetTemperatureEClass, 
		   source, 
		   new String[] {
			 "name", "T_SetTemperature",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTSetTemperature_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTSetTemperature_Period(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "period",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTSetTemperature_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTSetTemperature_Unit(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tSimulationMatrixEClass, 
		   source, 
		   new String[] {
			 "name", "T_SimulationMatrix",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTSimulationMatrix_Targets(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Targets",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTSimulationMatrix_Variables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Variables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTSimulationMatrix_AssignmentGroups(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "AssignmentGroups",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTSimulationMatrix_Combinations(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Combinations",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTSimulationMatrix_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTSimulationMatrix_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tSpaceEClass, 
		   source, 
		   new String[] {
			 "name", "T_Space",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getTSpace_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tSpaceGroupEClass, 
		   source, 
		   new String[] {
			 "name", "T_SpaceGroup",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTSpaceGroup_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTSpaceGroup_Space(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Space",
			 "namespace", "##targetNamespace",
			 "group", "group:0"
		   });		
		addAnnotation
		  (getTSpaceGroup_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tSpacesEClass, 
		   source, 
		   new String[] {
			 "name", "T_Spaces",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTSpaces_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTSpaces_SpaceGroup(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SpaceGroup",
			 "namespace", "##targetNamespace",
			 "group", "group:0"
		   });		
		addAnnotation
		  (getTSpaces_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tStringListEDataType, 
		   source, 
		   new String[] {
			 "name", "T_StringList",
			 "itemType", "http://www.eclipse.org/emf/2003/XMLType#string"
		   });		
		addAnnotation
		  (tTargetEClass, 
		   source, 
		   new String[] {
			 "name", "T_Target",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTTarget_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTTarget_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tTargetListEClass, 
		   source, 
		   new String[] {
			 "name", "T_TargetList",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTTargetList_Target(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Target",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTTargetList_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tTargetsEClass, 
		   source, 
		   new String[] {
			 "name", "T_Targets",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTTargets_TargetList(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TargetList",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tUsageEClass, 
		   source, 
		   new String[] {
			 "name", "T_Usage",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTUsage_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTUsage_OccupancyType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OccupancyType",
			 "namespace", "##targetNamespace",
			 "group", "group:0"
		   });		
		addAnnotation
		  (getTUsage_ScheduleType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ScheduleType",
			 "namespace", "##targetNamespace",
			 "group", "group:0"
		   });		
		addAnnotation
		  (tVariablesEClass, 
		   source, 
		   new String[] {
			 "name", "T_Variables",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTVariables_WeatherVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WeatherVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTVariables_OrientationVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "OrientationVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTVariables_ElevationVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ElevationVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTVariables_UsageTypeVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "UsageTypeVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTVariables_ConstructionTypeVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ConstructionTypeVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTVariables_WindowTypeVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WindowTypeVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTVariables_DoorTypeVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DoorTypeVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTVariables_MaterialTypeVariables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MaterialTypeVariables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tVariantEClass, 
		   source, 
		   new String[] {
			 "name", "T_Variant",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getTVariant_AREF(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "AREF",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTVariant_VREF(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "VREF",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tWeatherEClass, 
		   source, 
		   new String[] {
			 "name", "T_Weather",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTWeather_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTWeather_WeatherDataSetVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WeatherDataSetVariant",
			 "namespace", "##targetNamespace",
			 "group", "group:0"
		   });		
		addAnnotation
		  (getTWeather_WeatherDataSeriesVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WeatherDataSeriesVariant",
			 "namespace", "##targetNamespace",
			 "group", "group:0"
		   });		
		addAnnotation
		  (tWeatherDataSeriesVariantEClass, 
		   source, 
		   new String[] {
			 "name", "T_WeatherDataSeriesVariant",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTWeatherDataSeriesVariant_AirTemperature(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "AirTemperature",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTWeatherDataSeriesVariant_ShortWaveDiffuse(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ShortWaveDiffuse",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTWeatherDataSeriesVariant_ShortWaveDirect(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ShortWaveDirect",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTWeatherDataSeriesVariant_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTWeatherDataSeriesVariant_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tWeatherDataSetVariantEClass, 
		   source, 
		   new String[] {
			 "name", "T_WeatherDataSetVariant",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTWeatherDataSetVariant_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getTWeatherDataSetVariant_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTWeatherDataSetVariant_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tWindowTypeEClass, 
		   source, 
		   new String[] {
			 "name", "T_WindowType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTWindowType_WindowTypeVariant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WindowTypeVariant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTWindowType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tWindowTypesEClass, 
		   source, 
		   new String[] {
			 "name", "T_WindowTypes",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTWindowTypes_WindowType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WindowType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (tWindowTypeVariantEClass, 
		   source, 
		   new String[] {
			 "name", "T_WindowTypeVariant",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTWindowTypeVariant_GlassFraction(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "GlassFraction",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTWindowTypeVariant_FrameFraction(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "FrameFraction",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTWindowTypeVariant_ThermalTransmittance(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ThermalTransmittance",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTWindowTypeVariant_SolarHeatGainCoefficient(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SolarHeatGainCoefficient",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTWindowTypeVariant_ShadingFactor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ShadingFactor",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTWindowTypeVariant_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (weatherTypesEEnum, 
		   source, 
		   new String[] {
			 "name", "weatherTypes"
		   });		
		addAnnotation
		  (weatherTypesObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "weatherTypes:Object",
			 "baseType", "weatherTypes"
		   });
	}

} //simmatrixPackageImpl
