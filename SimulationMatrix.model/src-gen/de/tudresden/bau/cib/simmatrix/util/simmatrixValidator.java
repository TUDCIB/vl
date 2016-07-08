/**
 */
package de.tudresden.bau.cib.simmatrix.util;

import de.tudresden.bau.cib.simmatrix.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage
 * @generated
 */
public class simmatrixValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final simmatrixValidator INSTANCE = new simmatrixValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "de.tudresden.bau.cib.simmatrix";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XMLTypeValidator xmlTypeValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public simmatrixValidator() {
		super();
		xmlTypeValidator = XMLTypeValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return simmatrixPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case simmatrixPackage.DOCUMENT_ROOT:
				return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
			case simmatrixPackage.TASSIGNMENT_GROUPS:
				return validateTAssignmentGroups((TAssignmentGroups)value, diagnostics, context);
			case simmatrixPackage.TBIM_GROUP:
				return validateTBIMGroup((TBIMGroup)value, diagnostics, context);
			case simmatrixPackage.TCOMBINATION:
				return validateTCombination((TCombination)value, diagnostics, context);
			case simmatrixPackage.TCOMBINATIONS:
				return validateTCombinations((TCombinations)value, diagnostics, context);
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT:
				return validateTConstantTypeVariant((TConstantTypeVariant)value, diagnostics, context);
			case simmatrixPackage.TCONSTRUCTION_TYPE:
				return validateTConstructionType((TConstructionType)value, diagnostics, context);
			case simmatrixPackage.TCONSTRUCTION_TYPES:
				return validateTConstructionTypes((TConstructionTypes)value, diagnostics, context);
			case simmatrixPackage.TCONSTRUCTION_TYPE_VARIANT:
				return validateTConstructionTypeVariant((TConstructionTypeVariant)value, diagnostics, context);
			case simmatrixPackage.TELEMENT_GROUP:
				return validateTElementGroup((TElementGroup)value, diagnostics, context);
			case simmatrixPackage.TELEMENTS:
				return validateTElements((TElements)value, diagnostics, context);
			case simmatrixPackage.TELEVATION:
				return validateTElevation((TElevation)value, diagnostics, context);
			case simmatrixPackage.TELEVATION_VARIANT:
				return validateTElevationVariant((TElevationVariant)value, diagnostics, context);
			case simmatrixPackage.TFLOAT_WITH_UNITS:
				return validateTFloatWithUnits((TFloatWithUnits)value, diagnostics, context);
			case simmatrixPackage.TLAYER:
				return validateTLayer((TLayer)value, diagnostics, context);
			case simmatrixPackage.TMATERIAL_TYPE:
				return validateTMaterialType((TMaterialType)value, diagnostics, context);
			case simmatrixPackage.TMATERIAL_TYPES:
				return validateTMaterialTypes((TMaterialTypes)value, diagnostics, context);
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT:
				return validateTMaterialTypeVariant((TMaterialTypeVariant)value, diagnostics, context);
			case simmatrixPackage.TOCCUPANCY_TYPE:
				return validateTOccupancyType((TOccupancyType)value, diagnostics, context);
			case simmatrixPackage.TOCCUPANCY_TYPE_VARIANT:
				return validateTOccupancyTypeVariant((TOccupancyTypeVariant)value, diagnostics, context);
			case simmatrixPackage.TORIENTATION:
				return validateTOrientation((TOrientation)value, diagnostics, context);
			case simmatrixPackage.TORIENTATION_VARIANT:
				return validateTOrientationVariant((TOrientationVariant)value, diagnostics, context);
			case simmatrixPackage.TSCHEDULE_TYPE:
				return validateTScheduleType((TScheduleType)value, diagnostics, context);
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT:
				return validateTScheduleTypeVariant((TScheduleTypeVariant)value, diagnostics, context);
			case simmatrixPackage.TSET_PERSON_LOADS:
				return validateTSetPersonLoads((TSetPersonLoads)value, diagnostics, context);
			case simmatrixPackage.TSET_POINT:
				return validateTSetPoint((TSetPoint)value, diagnostics, context);
			case simmatrixPackage.TSET_SHADING:
				return validateTSetShading((TSetShading)value, diagnostics, context);
			case simmatrixPackage.TSET_TEMPERATURE:
				return validateTSetTemperature((TSetTemperature)value, diagnostics, context);
			case simmatrixPackage.TSIMULATION_MATRIX:
				return validateTSimulationMatrix((TSimulationMatrix)value, diagnostics, context);
			case simmatrixPackage.TSPACE:
				return validateTSpace((TSpace)value, diagnostics, context);
			case simmatrixPackage.TSPACE_GROUP:
				return validateTSpaceGroup((TSpaceGroup)value, diagnostics, context);
			case simmatrixPackage.TSPACES:
				return validateTSpaces((TSpaces)value, diagnostics, context);
			case simmatrixPackage.TTARGET:
				return validateTTarget((TTarget)value, diagnostics, context);
			case simmatrixPackage.TTARGET_LIST:
				return validateTTargetList((TTargetList)value, diagnostics, context);
			case simmatrixPackage.TTARGETS:
				return validateTTargets((TTargets)value, diagnostics, context);
			case simmatrixPackage.TUSAGE:
				return validateTUsage((TUsage)value, diagnostics, context);
			case simmatrixPackage.TVARIABLES:
				return validateTVariables((TVariables)value, diagnostics, context);
			case simmatrixPackage.TVARIANT:
				return validateTVariant((TVariant)value, diagnostics, context);
			case simmatrixPackage.TWEATHER:
				return validateTWeather((TWeather)value, diagnostics, context);
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT:
				return validateTWeatherDataSeriesVariant((TWeatherDataSeriesVariant)value, diagnostics, context);
			case simmatrixPackage.TWEATHER_DATA_SET_VARIANT:
				return validateTWeatherDataSetVariant((TWeatherDataSetVariant)value, diagnostics, context);
			case simmatrixPackage.TWINDOW_TYPE:
				return validateTWindowType((TWindowType)value, diagnostics, context);
			case simmatrixPackage.TWINDOW_TYPES:
				return validateTWindowTypes((TWindowTypes)value, diagnostics, context);
			case simmatrixPackage.TWINDOW_TYPE_VARIANT:
				return validateTWindowTypeVariant((TWindowTypeVariant)value, diagnostics, context);
			case simmatrixPackage.DISTANCE_UNIT:
				return validateDistanceUnit((DistanceUnit)value, diagnostics, context);
			case simmatrixPackage.FILE_FORMAT:
				return validateFileFormat((FileFormat)value, diagnostics, context);
			case simmatrixPackage.LOADS:
				return validateLoads((Loads)value, diagnostics, context);
			case simmatrixPackage.MATERIAL_UNIT:
				return validateMaterialUnit((MaterialUnit)value, diagnostics, context);
			case simmatrixPackage.ORIENTATION_SIDE:
				return validateOrientationSide((OrientationSide)value, diagnostics, context);
			case simmatrixPackage.ORIENTATION_UNIT:
				return validateOrientationUnit((OrientationUnit)value, diagnostics, context);
			case simmatrixPackage.SET_POINT:
				return validateSetPoint((SetPoint)value, diagnostics, context);
			case simmatrixPackage.SHADING:
				return validateShading((Shading)value, diagnostics, context);
			case simmatrixPackage.TARGET_TYPE:
				return validateTargetType((TargetType)value, diagnostics, context);
			case simmatrixPackage.TEMPERATURE:
				return validateTemperature((Temperature)value, diagnostics, context);
			case simmatrixPackage.TIME_PERIOD:
				return validateTimePeriod((TimePeriod)value, diagnostics, context);
			case simmatrixPackage.WEATHER_TYPES:
				return validateWeatherTypes((WeatherTypes)value, diagnostics, context);
			case simmatrixPackage.DISTANCE_UNIT_OBJECT:
				return validateDistanceUnitObject((DistanceUnit)value, diagnostics, context);
			case simmatrixPackage.FILE_FORMAT_OBJECT:
				return validateFileFormatObject((FileFormat)value, diagnostics, context);
			case simmatrixPackage.LOADS_OBJECT:
				return validateLoadsObject((Loads)value, diagnostics, context);
			case simmatrixPackage.MATERIAL_UNIT_OBJECT:
				return validateMaterialUnitObject((MaterialUnit)value, diagnostics, context);
			case simmatrixPackage.ORIENTATION_SIDE_OBJECT:
				return validateOrientationSideObject((OrientationSide)value, diagnostics, context);
			case simmatrixPackage.ORIENTATION_UNIT_OBJECT:
				return validateOrientationUnitObject((OrientationUnit)value, diagnostics, context);
			case simmatrixPackage.SET_POINT_OBJECT:
				return validateSetPointObject((SetPoint)value, diagnostics, context);
			case simmatrixPackage.SHADING_OBJECT:
				return validateShadingObject((Shading)value, diagnostics, context);
			case simmatrixPackage.SIMAT_TYPE:
				return validateSimatType((String)value, diagnostics, context);
			case simmatrixPackage.TARGET_TYPE_OBJECT:
				return validateTargetTypeObject((TargetType)value, diagnostics, context);
			case simmatrixPackage.TEMPERATURE_OBJECT:
				return validateTemperatureObject((Temperature)value, diagnostics, context);
			case simmatrixPackage.TIME_PERIOD_OBJECT:
				return validateTimePeriodObject((TimePeriod)value, diagnostics, context);
			case simmatrixPackage.TSTRING_LIST:
				return validateTStringList((List<?>)value, diagnostics, context);
			case simmatrixPackage.WEATHER_TYPES_OBJECT:
				return validateWeatherTypesObject((WeatherTypes)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTAssignmentGroups(TAssignmentGroups tAssignmentGroups, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tAssignmentGroups, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTBIMGroup(TBIMGroup tbimGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tbimGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCombination(TCombination tCombination, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCombination, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCombinations(TCombinations tCombinations, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCombinations, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTConstantTypeVariant(TConstantTypeVariant tConstantTypeVariant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tConstantTypeVariant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTConstructionType(TConstructionType tConstructionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tConstructionType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTConstructionTypes(TConstructionTypes tConstructionTypes, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tConstructionTypes, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTConstructionTypeVariant(TConstructionTypeVariant tConstructionTypeVariant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tConstructionTypeVariant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTElementGroup(TElementGroup tElementGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tElementGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTElements(TElements tElements, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tElements, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTElevation(TElevation tElevation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tElevation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTElevationVariant(TElevationVariant tElevationVariant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tElevationVariant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTFloatWithUnits(TFloatWithUnits tFloatWithUnits, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tFloatWithUnits, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTLayer(TLayer tLayer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tLayer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTMaterialType(TMaterialType tMaterialType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tMaterialType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTMaterialTypes(TMaterialTypes tMaterialTypes, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tMaterialTypes, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTMaterialTypeVariant(TMaterialTypeVariant tMaterialTypeVariant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tMaterialTypeVariant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTOccupancyType(TOccupancyType tOccupancyType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tOccupancyType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTOccupancyTypeVariant(TOccupancyTypeVariant tOccupancyTypeVariant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tOccupancyTypeVariant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTOrientation(TOrientation tOrientation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tOrientation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTOrientationVariant(TOrientationVariant tOrientationVariant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tOrientationVariant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTScheduleType(TScheduleType tScheduleType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tScheduleType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTScheduleTypeVariant(TScheduleTypeVariant tScheduleTypeVariant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tScheduleTypeVariant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSetPersonLoads(TSetPersonLoads tSetPersonLoads, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSetPersonLoads, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSetPoint(TSetPoint tSetPoint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSetPoint, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSetShading(TSetShading tSetShading, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSetShading, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSetTemperature(TSetTemperature tSetTemperature, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSetTemperature, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSimulationMatrix(TSimulationMatrix tSimulationMatrix, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSimulationMatrix, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSpace(TSpace tSpace, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSpace, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSpaceGroup(TSpaceGroup tSpaceGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSpaceGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSpaces(TSpaces tSpaces, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSpaces, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTarget(TTarget tTarget, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tTarget, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTargetList(TTargetList tTargetList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tTargetList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTargets(TTargets tTargets, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tTargets, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTUsage(TUsage tUsage, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tUsage, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTVariables(TVariables tVariables, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tVariables, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTVariant(TVariant tVariant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tVariant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTWeather(TWeather tWeather, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tWeather, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTWeatherDataSeriesVariant(TWeatherDataSeriesVariant tWeatherDataSeriesVariant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tWeatherDataSeriesVariant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTWeatherDataSetVariant(TWeatherDataSetVariant tWeatherDataSetVariant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tWeatherDataSetVariant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTWindowType(TWindowType tWindowType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tWindowType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTWindowTypes(TWindowTypes tWindowTypes, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tWindowTypes, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTWindowTypeVariant(TWindowTypeVariant tWindowTypeVariant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tWindowTypeVariant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDistanceUnit(DistanceUnit distanceUnit, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFileFormat(FileFormat fileFormat, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLoads(Loads loads, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMaterialUnit(MaterialUnit materialUnit, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrientationSide(OrientationSide orientationSide, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrientationUnit(OrientationUnit orientationUnit, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSetPoint(SetPoint setPoint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateShading(Shading shading, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTargetType(TargetType targetType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTemperature(Temperature temperature, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimePeriod(TimePeriod timePeriod, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWeatherTypes(WeatherTypes weatherTypes, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDistanceUnitObject(DistanceUnit distanceUnitObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFileFormatObject(FileFormat fileFormatObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLoadsObject(Loads loadsObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMaterialUnitObject(MaterialUnit materialUnitObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrientationSideObject(OrientationSide orientationSideObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrientationUnitObject(OrientationUnit orientationUnitObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSetPointObject(SetPoint setPointObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateShadingObject(Shading shadingObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSimatType(String simatType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateSimatType_Pattern(simatType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateSimatType_Pattern
	 */
	public static final  PatternMatcher [][] SIMAT_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("SensitivityAnalysis|DesignAlternatives|StochasticSimulation")
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Simat Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSimatType_Pattern(String simatType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(simmatrixPackage.Literals.SIMAT_TYPE, simatType, SIMAT_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTargetTypeObject(TargetType targetTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTemperatureObject(Temperature temperatureObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimePeriodObject(TimePeriod timePeriodObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTStringList(List<?> tStringList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateTStringList_ItemType(tStringList, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ItemType constraint of '<em>TString List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTStringList_ItemType(List<?> tStringList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = true;
		for (Iterator<?> i = tStringList.iterator(); i.hasNext() && (result || diagnostics != null); ) {
			Object item = i.next();
			if (XMLTypePackage.Literals.STRING.isInstance(item)) {
				result &= xmlTypeValidator.validateString((String)item, diagnostics, context);
			}
			else {
				result = false;
				reportDataValueTypeViolation(XMLTypePackage.Literals.STRING, item, diagnostics, context);
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWeatherTypesObject(WeatherTypes weatherTypesObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

} //simmatrixValidator
