/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class simmatrixFactoryImpl extends EFactoryImpl implements simmatrixFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static simmatrixFactory init() {
		try {
			simmatrixFactory thesimmatrixFactory = (simmatrixFactory)EPackage.Registry.INSTANCE.getEFactory(simmatrixPackage.eNS_URI);
			if (thesimmatrixFactory != null) {
				return thesimmatrixFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new simmatrixFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public simmatrixFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case simmatrixPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case simmatrixPackage.TASSIGNMENT_GROUPS: return createTAssignmentGroups();
			case simmatrixPackage.TBIM_GROUP: return createTBIMGroup();
			case simmatrixPackage.TCOMBINATION: return createTCombination();
			case simmatrixPackage.TCOMBINATIONS: return createTCombinations();
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT: return createTConstantTypeVariant();
			case simmatrixPackage.TCONSTRUCTION_TYPE: return createTConstructionType();
			case simmatrixPackage.TCONSTRUCTION_TYPES: return createTConstructionTypes();
			case simmatrixPackage.TCONSTRUCTION_TYPE_VARIANT: return createTConstructionTypeVariant();
			case simmatrixPackage.TELEMENT_GROUP: return createTElementGroup();
			case simmatrixPackage.TELEMENTS: return createTElements();
			case simmatrixPackage.TELEVATION: return createTElevation();
			case simmatrixPackage.TELEVATION_VARIANT: return createTElevationVariant();
			case simmatrixPackage.TFLOAT_WITH_UNITS: return createTFloatWithUnits();
			case simmatrixPackage.TLAYER: return createTLayer();
			case simmatrixPackage.TMATERIAL_TYPE: return createTMaterialType();
			case simmatrixPackage.TMATERIAL_TYPES: return createTMaterialTypes();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT: return createTMaterialTypeVariant();
			case simmatrixPackage.TOCCUPANCY_TYPE: return createTOccupancyType();
			case simmatrixPackage.TOCCUPANCY_TYPE_VARIANT: return createTOccupancyTypeVariant();
			case simmatrixPackage.TORIENTATION: return createTOrientation();
			case simmatrixPackage.TORIENTATION_VARIANT: return createTOrientationVariant();
			case simmatrixPackage.TSCHEDULE_TYPE: return createTScheduleType();
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT: return createTScheduleTypeVariant();
			case simmatrixPackage.TSET_PERSON_LOADS: return createTSetPersonLoads();
			case simmatrixPackage.TSET_POINT: return createTSetPoint();
			case simmatrixPackage.TSET_SHADING: return createTSetShading();
			case simmatrixPackage.TSET_TEMPERATURE: return createTSetTemperature();
			case simmatrixPackage.TSIMULATION_MATRIX: return createTSimulationMatrix();
			case simmatrixPackage.TSPACE: return createTSpace();
			case simmatrixPackage.TSPACE_GROUP: return createTSpaceGroup();
			case simmatrixPackage.TSPACES: return createTSpaces();
			case simmatrixPackage.TTARGET: return createTTarget();
			case simmatrixPackage.TTARGET_LIST: return createTTargetList();
			case simmatrixPackage.TTARGETS: return createTTargets();
			case simmatrixPackage.TUSAGE: return createTUsage();
			case simmatrixPackage.TVARIABLES: return createTVariables();
			case simmatrixPackage.TVARIANT: return createTVariant();
			case simmatrixPackage.TWEATHER: return createTWeather();
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT: return createTWeatherDataSeriesVariant();
			case simmatrixPackage.TWEATHER_DATA_SET_VARIANT: return createTWeatherDataSetVariant();
			case simmatrixPackage.TWINDOW_TYPE: return createTWindowType();
			case simmatrixPackage.TWINDOW_TYPES: return createTWindowTypes();
			case simmatrixPackage.TWINDOW_TYPE_VARIANT: return createTWindowTypeVariant();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case simmatrixPackage.DISTANCE_UNIT:
				return createDistanceUnitFromString(eDataType, initialValue);
			case simmatrixPackage.FILE_FORMAT:
				return createFileFormatFromString(eDataType, initialValue);
			case simmatrixPackage.LOADS:
				return createLoadsFromString(eDataType, initialValue);
			case simmatrixPackage.MATERIAL_UNIT:
				return createMaterialUnitFromString(eDataType, initialValue);
			case simmatrixPackage.ORIENTATION_SIDE:
				return createOrientationSideFromString(eDataType, initialValue);
			case simmatrixPackage.ORIENTATION_UNIT:
				return createOrientationUnitFromString(eDataType, initialValue);
			case simmatrixPackage.SET_POINT:
				return createSetPointFromString(eDataType, initialValue);
			case simmatrixPackage.SHADING:
				return createShadingFromString(eDataType, initialValue);
			case simmatrixPackage.TARGET_TYPE:
				return createTargetTypeFromString(eDataType, initialValue);
			case simmatrixPackage.TEMPERATURE:
				return createTemperatureFromString(eDataType, initialValue);
			case simmatrixPackage.TIME_PERIOD:
				return createTimePeriodFromString(eDataType, initialValue);
			case simmatrixPackage.WEATHER_TYPES:
				return createWeatherTypesFromString(eDataType, initialValue);
			case simmatrixPackage.DISTANCE_UNIT_OBJECT:
				return createDistanceUnitObjectFromString(eDataType, initialValue);
			case simmatrixPackage.FILE_FORMAT_OBJECT:
				return createFileFormatObjectFromString(eDataType, initialValue);
			case simmatrixPackage.LOADS_OBJECT:
				return createLoadsObjectFromString(eDataType, initialValue);
			case simmatrixPackage.MATERIAL_UNIT_OBJECT:
				return createMaterialUnitObjectFromString(eDataType, initialValue);
			case simmatrixPackage.ORIENTATION_SIDE_OBJECT:
				return createOrientationSideObjectFromString(eDataType, initialValue);
			case simmatrixPackage.ORIENTATION_UNIT_OBJECT:
				return createOrientationUnitObjectFromString(eDataType, initialValue);
			case simmatrixPackage.SET_POINT_OBJECT:
				return createSetPointObjectFromString(eDataType, initialValue);
			case simmatrixPackage.SHADING_OBJECT:
				return createShadingObjectFromString(eDataType, initialValue);
			case simmatrixPackage.SIMAT_TYPE:
				return createSimatTypeFromString(eDataType, initialValue);
			case simmatrixPackage.TARGET_TYPE_OBJECT:
				return createTargetTypeObjectFromString(eDataType, initialValue);
			case simmatrixPackage.TEMPERATURE_OBJECT:
				return createTemperatureObjectFromString(eDataType, initialValue);
			case simmatrixPackage.TIME_PERIOD_OBJECT:
				return createTimePeriodObjectFromString(eDataType, initialValue);
			case simmatrixPackage.TSTRING_LIST:
				return createTStringListFromString(eDataType, initialValue);
			case simmatrixPackage.WEATHER_TYPES_OBJECT:
				return createWeatherTypesObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case simmatrixPackage.DISTANCE_UNIT:
				return convertDistanceUnitToString(eDataType, instanceValue);
			case simmatrixPackage.FILE_FORMAT:
				return convertFileFormatToString(eDataType, instanceValue);
			case simmatrixPackage.LOADS:
				return convertLoadsToString(eDataType, instanceValue);
			case simmatrixPackage.MATERIAL_UNIT:
				return convertMaterialUnitToString(eDataType, instanceValue);
			case simmatrixPackage.ORIENTATION_SIDE:
				return convertOrientationSideToString(eDataType, instanceValue);
			case simmatrixPackage.ORIENTATION_UNIT:
				return convertOrientationUnitToString(eDataType, instanceValue);
			case simmatrixPackage.SET_POINT:
				return convertSetPointToString(eDataType, instanceValue);
			case simmatrixPackage.SHADING:
				return convertShadingToString(eDataType, instanceValue);
			case simmatrixPackage.TARGET_TYPE:
				return convertTargetTypeToString(eDataType, instanceValue);
			case simmatrixPackage.TEMPERATURE:
				return convertTemperatureToString(eDataType, instanceValue);
			case simmatrixPackage.TIME_PERIOD:
				return convertTimePeriodToString(eDataType, instanceValue);
			case simmatrixPackage.WEATHER_TYPES:
				return convertWeatherTypesToString(eDataType, instanceValue);
			case simmatrixPackage.DISTANCE_UNIT_OBJECT:
				return convertDistanceUnitObjectToString(eDataType, instanceValue);
			case simmatrixPackage.FILE_FORMAT_OBJECT:
				return convertFileFormatObjectToString(eDataType, instanceValue);
			case simmatrixPackage.LOADS_OBJECT:
				return convertLoadsObjectToString(eDataType, instanceValue);
			case simmatrixPackage.MATERIAL_UNIT_OBJECT:
				return convertMaterialUnitObjectToString(eDataType, instanceValue);
			case simmatrixPackage.ORIENTATION_SIDE_OBJECT:
				return convertOrientationSideObjectToString(eDataType, instanceValue);
			case simmatrixPackage.ORIENTATION_UNIT_OBJECT:
				return convertOrientationUnitObjectToString(eDataType, instanceValue);
			case simmatrixPackage.SET_POINT_OBJECT:
				return convertSetPointObjectToString(eDataType, instanceValue);
			case simmatrixPackage.SHADING_OBJECT:
				return convertShadingObjectToString(eDataType, instanceValue);
			case simmatrixPackage.SIMAT_TYPE:
				return convertSimatTypeToString(eDataType, instanceValue);
			case simmatrixPackage.TARGET_TYPE_OBJECT:
				return convertTargetTypeObjectToString(eDataType, instanceValue);
			case simmatrixPackage.TEMPERATURE_OBJECT:
				return convertTemperatureObjectToString(eDataType, instanceValue);
			case simmatrixPackage.TIME_PERIOD_OBJECT:
				return convertTimePeriodObjectToString(eDataType, instanceValue);
			case simmatrixPackage.TSTRING_LIST:
				return convertTStringListToString(eDataType, instanceValue);
			case simmatrixPackage.WEATHER_TYPES_OBJECT:
				return convertWeatherTypesObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAssignmentGroups createTAssignmentGroups() {
		TAssignmentGroupsImpl tAssignmentGroups = new TAssignmentGroupsImpl();
		return tAssignmentGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TBIMGroup createTBIMGroup() {
		TBIMGroupImpl tbimGroup = new TBIMGroupImpl();
		return tbimGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCombination createTCombination() {
		TCombinationImpl tCombination = new TCombinationImpl();
		return tCombination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCombinations createTCombinations() {
		TCombinationsImpl tCombinations = new TCombinationsImpl();
		return tCombinations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConstantTypeVariant createTConstantTypeVariant() {
		TConstantTypeVariantImpl tConstantTypeVariant = new TConstantTypeVariantImpl();
		return tConstantTypeVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConstructionType createTConstructionType() {
		TConstructionTypeImpl tConstructionType = new TConstructionTypeImpl();
		return tConstructionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConstructionTypes createTConstructionTypes() {
		TConstructionTypesImpl tConstructionTypes = new TConstructionTypesImpl();
		return tConstructionTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConstructionTypeVariant createTConstructionTypeVariant() {
		TConstructionTypeVariantImpl tConstructionTypeVariant = new TConstructionTypeVariantImpl();
		return tConstructionTypeVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TElementGroup createTElementGroup() {
		TElementGroupImpl tElementGroup = new TElementGroupImpl();
		return tElementGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TElements createTElements() {
		TElementsImpl tElements = new TElementsImpl();
		return tElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TElevation createTElevation() {
		TElevationImpl tElevation = new TElevationImpl();
		return tElevation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TElevationVariant createTElevationVariant() {
		TElevationVariantImpl tElevationVariant = new TElevationVariantImpl();
		return tElevationVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits createTFloatWithUnits() {
		TFloatWithUnitsImpl tFloatWithUnits = new TFloatWithUnitsImpl();
		return tFloatWithUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLayer createTLayer() {
		TLayerImpl tLayer = new TLayerImpl();
		return tLayer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMaterialType createTMaterialType() {
		TMaterialTypeImpl tMaterialType = new TMaterialTypeImpl();
		return tMaterialType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMaterialTypes createTMaterialTypes() {
		TMaterialTypesImpl tMaterialTypes = new TMaterialTypesImpl();
		return tMaterialTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMaterialTypeVariant createTMaterialTypeVariant() {
		TMaterialTypeVariantImpl tMaterialTypeVariant = new TMaterialTypeVariantImpl();
		return tMaterialTypeVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOccupancyType createTOccupancyType() {
		TOccupancyTypeImpl tOccupancyType = new TOccupancyTypeImpl();
		return tOccupancyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOccupancyTypeVariant createTOccupancyTypeVariant() {
		TOccupancyTypeVariantImpl tOccupancyTypeVariant = new TOccupancyTypeVariantImpl();
		return tOccupancyTypeVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOrientation createTOrientation() {
		TOrientationImpl tOrientation = new TOrientationImpl();
		return tOrientation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOrientationVariant createTOrientationVariant() {
		TOrientationVariantImpl tOrientationVariant = new TOrientationVariantImpl();
		return tOrientationVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TScheduleType createTScheduleType() {
		TScheduleTypeImpl tScheduleType = new TScheduleTypeImpl();
		return tScheduleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TScheduleTypeVariant createTScheduleTypeVariant() {
		TScheduleTypeVariantImpl tScheduleTypeVariant = new TScheduleTypeVariantImpl();
		return tScheduleTypeVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetPersonLoads createTSetPersonLoads() {
		TSetPersonLoadsImpl tSetPersonLoads = new TSetPersonLoadsImpl();
		return tSetPersonLoads;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetPoint createTSetPoint() {
		TSetPointImpl tSetPoint = new TSetPointImpl();
		return tSetPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetShading createTSetShading() {
		TSetShadingImpl tSetShading = new TSetShadingImpl();
		return tSetShading;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetTemperature createTSetTemperature() {
		TSetTemperatureImpl tSetTemperature = new TSetTemperatureImpl();
		return tSetTemperature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSimulationMatrix createTSimulationMatrix() {
		TSimulationMatrixImpl tSimulationMatrix = new TSimulationMatrixImpl();
		return tSimulationMatrix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSpace createTSpace() {
		TSpaceImpl tSpace = new TSpaceImpl();
		return tSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSpaceGroup createTSpaceGroup() {
		TSpaceGroupImpl tSpaceGroup = new TSpaceGroupImpl();
		return tSpaceGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSpaces createTSpaces() {
		TSpacesImpl tSpaces = new TSpacesImpl();
		return tSpaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTarget createTTarget() {
		TTargetImpl tTarget = new TTargetImpl();
		return tTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTargetList createTTargetList() {
		TTargetListImpl tTargetList = new TTargetListImpl();
		return tTargetList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTargets createTTargets() {
		TTargetsImpl tTargets = new TTargetsImpl();
		return tTargets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TUsage createTUsage() {
		TUsageImpl tUsage = new TUsageImpl();
		return tUsage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TVariables createTVariables() {
		TVariablesImpl tVariables = new TVariablesImpl();
		return tVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TVariant createTVariant() {
		TVariantImpl tVariant = new TVariantImpl();
		return tVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWeather createTWeather() {
		TWeatherImpl tWeather = new TWeatherImpl();
		return tWeather;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWeatherDataSeriesVariant createTWeatherDataSeriesVariant() {
		TWeatherDataSeriesVariantImpl tWeatherDataSeriesVariant = new TWeatherDataSeriesVariantImpl();
		return tWeatherDataSeriesVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWeatherDataSetVariant createTWeatherDataSetVariant() {
		TWeatherDataSetVariantImpl tWeatherDataSetVariant = new TWeatherDataSetVariantImpl();
		return tWeatherDataSetVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWindowType createTWindowType() {
		TWindowTypeImpl tWindowType = new TWindowTypeImpl();
		return tWindowType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWindowTypes createTWindowTypes() {
		TWindowTypesImpl tWindowTypes = new TWindowTypesImpl();
		return tWindowTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWindowTypeVariant createTWindowTypeVariant() {
		TWindowTypeVariantImpl tWindowTypeVariant = new TWindowTypeVariantImpl();
		return tWindowTypeVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DistanceUnit createDistanceUnit(String literal) {
		DistanceUnit result = DistanceUnit.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.DISTANCE_UNIT.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DistanceUnit createDistanceUnitFromString(EDataType eDataType, String initialValue) {
		return createDistanceUnit(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDistanceUnit(DistanceUnit instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDistanceUnitToString(EDataType eDataType, Object instanceValue) {
		return convertDistanceUnit((DistanceUnit)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileFormat createFileFormat(String literal) {
		FileFormat result = FileFormat.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.FILE_FORMAT.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileFormat createFileFormatFromString(EDataType eDataType, String initialValue) {
		return createFileFormat(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFileFormat(FileFormat instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFileFormatToString(EDataType eDataType, Object instanceValue) {
		return convertFileFormat((FileFormat)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Loads createLoads(String literal) {
		Loads result = Loads.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.LOADS.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Loads createLoadsFromString(EDataType eDataType, String initialValue) {
		return createLoads(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLoads(Loads instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLoadsToString(EDataType eDataType, Object instanceValue) {
		return convertLoads((Loads)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaterialUnit createMaterialUnit(String literal) {
		MaterialUnit result = MaterialUnit.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.MATERIAL_UNIT.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaterialUnit createMaterialUnitFromString(EDataType eDataType, String initialValue) {
		return createMaterialUnit(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMaterialUnit(MaterialUnit instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMaterialUnitToString(EDataType eDataType, Object instanceValue) {
		return convertMaterialUnit((MaterialUnit)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationSide createOrientationSide(String literal) {
		OrientationSide result = OrientationSide.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.ORIENTATION_SIDE.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationSide createOrientationSideFromString(EDataType eDataType, String initialValue) {
		return createOrientationSide(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationSide(OrientationSide instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationSideToString(EDataType eDataType, Object instanceValue) {
		return convertOrientationSide((OrientationSide)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationUnit createOrientationUnit(String literal) {
		OrientationUnit result = OrientationUnit.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.ORIENTATION_UNIT.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationUnit createOrientationUnitFromString(EDataType eDataType, String initialValue) {
		return createOrientationUnit(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationUnit(OrientationUnit instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationUnitToString(EDataType eDataType, Object instanceValue) {
		return convertOrientationUnit((OrientationUnit)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetPoint createSetPoint(String literal) {
		SetPoint result = SetPoint.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.SET_POINT.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetPoint createSetPointFromString(EDataType eDataType, String initialValue) {
		return createSetPoint(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSetPoint(SetPoint instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSetPointToString(EDataType eDataType, Object instanceValue) {
		return convertSetPoint((SetPoint)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Shading createShading(String literal) {
		Shading result = Shading.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.SHADING.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Shading createShadingFromString(EDataType eDataType, String initialValue) {
		return createShading(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertShading(Shading instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertShadingToString(EDataType eDataType, Object instanceValue) {
		return convertShading((Shading)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TargetType createTargetType(String literal) {
		TargetType result = TargetType.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.TARGET_TYPE.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TargetType createTargetTypeFromString(EDataType eDataType, String initialValue) {
		return createTargetType(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTargetType(TargetType instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTargetTypeToString(EDataType eDataType, Object instanceValue) {
		return convertTargetType((TargetType)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Temperature createTemperature(String literal) {
		Temperature result = Temperature.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.TEMPERATURE.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Temperature createTemperatureFromString(EDataType eDataType, String initialValue) {
		return createTemperature(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTemperature(Temperature instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTemperatureToString(EDataType eDataType, Object instanceValue) {
		return convertTemperature((Temperature)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimePeriod createTimePeriod(String literal) {
		TimePeriod result = TimePeriod.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.TIME_PERIOD.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimePeriod createTimePeriodFromString(EDataType eDataType, String initialValue) {
		return createTimePeriod(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTimePeriod(TimePeriod instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTimePeriodToString(EDataType eDataType, Object instanceValue) {
		return convertTimePeriod((TimePeriod)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeatherTypes createWeatherTypes(String literal) {
		WeatherTypes result = WeatherTypes.get(literal);
		if (result == null) throw new IllegalArgumentException("The value '" + literal + "' is not a valid enumerator of '" + simmatrixPackage.Literals.WEATHER_TYPES.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeatherTypes createWeatherTypesFromString(EDataType eDataType, String initialValue) {
		return createWeatherTypes(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWeatherTypes(WeatherTypes instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWeatherTypesToString(EDataType eDataType, Object instanceValue) {
		return convertWeatherTypes((WeatherTypes)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DistanceUnit createDistanceUnitObject(String literal) {
		return createDistanceUnit(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DistanceUnit createDistanceUnitObjectFromString(EDataType eDataType, String initialValue) {
		return createDistanceUnitFromString(simmatrixPackage.Literals.DISTANCE_UNIT, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDistanceUnitObject(DistanceUnit instanceValue) {
		return convertDistanceUnit(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDistanceUnitObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDistanceUnitToString(simmatrixPackage.Literals.DISTANCE_UNIT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileFormat createFileFormatObject(String literal) {
		return createFileFormat(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileFormat createFileFormatObjectFromString(EDataType eDataType, String initialValue) {
		return createFileFormatFromString(simmatrixPackage.Literals.FILE_FORMAT, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFileFormatObject(FileFormat instanceValue) {
		return convertFileFormat(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFileFormatObjectToString(EDataType eDataType, Object instanceValue) {
		return convertFileFormatToString(simmatrixPackage.Literals.FILE_FORMAT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Loads createLoadsObject(String literal) {
		return createLoads(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Loads createLoadsObjectFromString(EDataType eDataType, String initialValue) {
		return createLoadsFromString(simmatrixPackage.Literals.LOADS, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLoadsObject(Loads instanceValue) {
		return convertLoads(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLoadsObjectToString(EDataType eDataType, Object instanceValue) {
		return convertLoadsToString(simmatrixPackage.Literals.LOADS, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaterialUnit createMaterialUnitObject(String literal) {
		return createMaterialUnit(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaterialUnit createMaterialUnitObjectFromString(EDataType eDataType, String initialValue) {
		return createMaterialUnitFromString(simmatrixPackage.Literals.MATERIAL_UNIT, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMaterialUnitObject(MaterialUnit instanceValue) {
		return convertMaterialUnit(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMaterialUnitObjectToString(EDataType eDataType, Object instanceValue) {
		return convertMaterialUnitToString(simmatrixPackage.Literals.MATERIAL_UNIT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationSide createOrientationSideObject(String literal) {
		return createOrientationSide(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationSide createOrientationSideObjectFromString(EDataType eDataType, String initialValue) {
		return createOrientationSideFromString(simmatrixPackage.Literals.ORIENTATION_SIDE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationSideObject(OrientationSide instanceValue) {
		return convertOrientationSide(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationSideObjectToString(EDataType eDataType, Object instanceValue) {
		return convertOrientationSideToString(simmatrixPackage.Literals.ORIENTATION_SIDE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationUnit createOrientationUnitObject(String literal) {
		return createOrientationUnit(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationUnit createOrientationUnitObjectFromString(EDataType eDataType, String initialValue) {
		return createOrientationUnitFromString(simmatrixPackage.Literals.ORIENTATION_UNIT, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationUnitObject(OrientationUnit instanceValue) {
		return convertOrientationUnit(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrientationUnitObjectToString(EDataType eDataType, Object instanceValue) {
		return convertOrientationUnitToString(simmatrixPackage.Literals.ORIENTATION_UNIT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetPoint createSetPointObject(String literal) {
		return createSetPoint(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetPoint createSetPointObjectFromString(EDataType eDataType, String initialValue) {
		return createSetPointFromString(simmatrixPackage.Literals.SET_POINT, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSetPointObject(SetPoint instanceValue) {
		return convertSetPoint(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSetPointObjectToString(EDataType eDataType, Object instanceValue) {
		return convertSetPointToString(simmatrixPackage.Literals.SET_POINT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Shading createShadingObject(String literal) {
		return createShading(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Shading createShadingObjectFromString(EDataType eDataType, String initialValue) {
		return createShadingFromString(simmatrixPackage.Literals.SHADING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertShadingObject(Shading instanceValue) {
		return convertShading(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertShadingObjectToString(EDataType eDataType, Object instanceValue) {
		return convertShadingToString(simmatrixPackage.Literals.SHADING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createSimatType(String literal) {
		return XMLTypeFactory.eINSTANCE.createString(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createSimatTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSimatType(String instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSimatTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TargetType createTargetTypeObject(String literal) {
		return createTargetType(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TargetType createTargetTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTargetTypeFromString(simmatrixPackage.Literals.TARGET_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTargetTypeObject(TargetType instanceValue) {
		return convertTargetType(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTargetTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTargetTypeToString(simmatrixPackage.Literals.TARGET_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Temperature createTemperatureObject(String literal) {
		return createTemperature(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Temperature createTemperatureObjectFromString(EDataType eDataType, String initialValue) {
		return createTemperatureFromString(simmatrixPackage.Literals.TEMPERATURE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTemperatureObject(Temperature instanceValue) {
		return convertTemperature(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTemperatureObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTemperatureToString(simmatrixPackage.Literals.TEMPERATURE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimePeriod createTimePeriodObject(String literal) {
		return createTimePeriod(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimePeriod createTimePeriodObjectFromString(EDataType eDataType, String initialValue) {
		return createTimePeriodFromString(simmatrixPackage.Literals.TIME_PERIOD, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTimePeriodObject(TimePeriod instanceValue) {
		return convertTimePeriod(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTimePeriodObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTimePeriodToString(simmatrixPackage.Literals.TIME_PERIOD, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> createTStringList(String literal) {
		if (literal == null) return null;
		List<String> result = new ArrayList<String>();
		for (StringTokenizer stringTokenizer = new StringTokenizer(literal); stringTokenizer.hasMoreTokens(); ) {
			String item = stringTokenizer.nextToken();
			result.add(XMLTypeFactory.eINSTANCE.createString(item));
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> createTStringListFromString(EDataType eDataType, String initialValue) {
		return createTStringList(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTStringList(List<? extends String> instanceValue) {
		if (instanceValue == null) return null;
		if (instanceValue.isEmpty()) return "";
		StringBuffer result = new StringBuffer();
		for (Object item : instanceValue) {
			result.append(XMLTypeFactory.eINSTANCE.convertString((String)item));
			result.append(' ');
		}
		return result.substring(0, result.length() - 1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public String convertTStringListToString(EDataType eDataType, Object instanceValue) {
		return convertTStringList((List<? extends String>)instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeatherTypes createWeatherTypesObject(String literal) {
		return createWeatherTypes(literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeatherTypes createWeatherTypesObjectFromString(EDataType eDataType, String initialValue) {
		return createWeatherTypesFromString(simmatrixPackage.Literals.WEATHER_TYPES, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWeatherTypesObject(WeatherTypes instanceValue) {
		return convertWeatherTypes(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWeatherTypesObjectToString(EDataType eDataType, Object instanceValue) {
		return convertWeatherTypesToString(simmatrixPackage.Literals.WEATHER_TYPES, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public simmatrixPackage getsimmatrixPackage() {
		return (simmatrixPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static simmatrixPackage getPackage() {
		return simmatrixPackage.eINSTANCE;
	}

} //simmatrixFactoryImpl
