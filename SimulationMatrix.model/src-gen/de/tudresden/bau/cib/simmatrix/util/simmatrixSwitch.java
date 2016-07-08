/**
 */
package de.tudresden.bau.cib.simmatrix.util;

import de.tudresden.bau.cib.simmatrix.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage
 * @generated
 */
public class simmatrixSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static simmatrixPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public simmatrixSwitch() {
		if (modelPackage == null) {
			modelPackage = simmatrixPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case simmatrixPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TASSIGNMENT_GROUPS: {
				TAssignmentGroups tAssignmentGroups = (TAssignmentGroups)theEObject;
				T result = caseTAssignmentGroups(tAssignmentGroups);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TBIM_GROUP: {
				TBIMGroup tbimGroup = (TBIMGroup)theEObject;
				T result = caseTBIMGroup(tbimGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TCOMBINATION: {
				TCombination tCombination = (TCombination)theEObject;
				T result = caseTCombination(tCombination);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TCOMBINATIONS: {
				TCombinations tCombinations = (TCombinations)theEObject;
				T result = caseTCombinations(tCombinations);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT: {
				TConstantTypeVariant tConstantTypeVariant = (TConstantTypeVariant)theEObject;
				T result = caseTConstantTypeVariant(tConstantTypeVariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TCONSTRUCTION_TYPE: {
				TConstructionType tConstructionType = (TConstructionType)theEObject;
				T result = caseTConstructionType(tConstructionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TCONSTRUCTION_TYPES: {
				TConstructionTypes tConstructionTypes = (TConstructionTypes)theEObject;
				T result = caseTConstructionTypes(tConstructionTypes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TCONSTRUCTION_TYPE_VARIANT: {
				TConstructionTypeVariant tConstructionTypeVariant = (TConstructionTypeVariant)theEObject;
				T result = caseTConstructionTypeVariant(tConstructionTypeVariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TELEMENT_GROUP: {
				TElementGroup tElementGroup = (TElementGroup)theEObject;
				T result = caseTElementGroup(tElementGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TELEMENTS: {
				TElements tElements = (TElements)theEObject;
				T result = caseTElements(tElements);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TELEVATION: {
				TElevation tElevation = (TElevation)theEObject;
				T result = caseTElevation(tElevation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TELEVATION_VARIANT: {
				TElevationVariant tElevationVariant = (TElevationVariant)theEObject;
				T result = caseTElevationVariant(tElevationVariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TFLOAT_WITH_UNITS: {
				TFloatWithUnits tFloatWithUnits = (TFloatWithUnits)theEObject;
				T result = caseTFloatWithUnits(tFloatWithUnits);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TLAYER: {
				TLayer tLayer = (TLayer)theEObject;
				T result = caseTLayer(tLayer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TMATERIAL_TYPE: {
				TMaterialType tMaterialType = (TMaterialType)theEObject;
				T result = caseTMaterialType(tMaterialType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TMATERIAL_TYPES: {
				TMaterialTypes tMaterialTypes = (TMaterialTypes)theEObject;
				T result = caseTMaterialTypes(tMaterialTypes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT: {
				TMaterialTypeVariant tMaterialTypeVariant = (TMaterialTypeVariant)theEObject;
				T result = caseTMaterialTypeVariant(tMaterialTypeVariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TOCCUPANCY_TYPE: {
				TOccupancyType tOccupancyType = (TOccupancyType)theEObject;
				T result = caseTOccupancyType(tOccupancyType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TOCCUPANCY_TYPE_VARIANT: {
				TOccupancyTypeVariant tOccupancyTypeVariant = (TOccupancyTypeVariant)theEObject;
				T result = caseTOccupancyTypeVariant(tOccupancyTypeVariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TORIENTATION: {
				TOrientation tOrientation = (TOrientation)theEObject;
				T result = caseTOrientation(tOrientation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TORIENTATION_VARIANT: {
				TOrientationVariant tOrientationVariant = (TOrientationVariant)theEObject;
				T result = caseTOrientationVariant(tOrientationVariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TSCHEDULE_TYPE: {
				TScheduleType tScheduleType = (TScheduleType)theEObject;
				T result = caseTScheduleType(tScheduleType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT: {
				TScheduleTypeVariant tScheduleTypeVariant = (TScheduleTypeVariant)theEObject;
				T result = caseTScheduleTypeVariant(tScheduleTypeVariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TSET_PERSON_LOADS: {
				TSetPersonLoads tSetPersonLoads = (TSetPersonLoads)theEObject;
				T result = caseTSetPersonLoads(tSetPersonLoads);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TSET_POINT: {
				TSetPoint tSetPoint = (TSetPoint)theEObject;
				T result = caseTSetPoint(tSetPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TSET_SHADING: {
				TSetShading tSetShading = (TSetShading)theEObject;
				T result = caseTSetShading(tSetShading);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TSET_TEMPERATURE: {
				TSetTemperature tSetTemperature = (TSetTemperature)theEObject;
				T result = caseTSetTemperature(tSetTemperature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TSIMULATION_MATRIX: {
				TSimulationMatrix tSimulationMatrix = (TSimulationMatrix)theEObject;
				T result = caseTSimulationMatrix(tSimulationMatrix);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TSPACE: {
				TSpace tSpace = (TSpace)theEObject;
				T result = caseTSpace(tSpace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TSPACE_GROUP: {
				TSpaceGroup tSpaceGroup = (TSpaceGroup)theEObject;
				T result = caseTSpaceGroup(tSpaceGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TSPACES: {
				TSpaces tSpaces = (TSpaces)theEObject;
				T result = caseTSpaces(tSpaces);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TTARGET: {
				TTarget tTarget = (TTarget)theEObject;
				T result = caseTTarget(tTarget);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TTARGET_LIST: {
				TTargetList tTargetList = (TTargetList)theEObject;
				T result = caseTTargetList(tTargetList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TTARGETS: {
				TTargets tTargets = (TTargets)theEObject;
				T result = caseTTargets(tTargets);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TUSAGE: {
				TUsage tUsage = (TUsage)theEObject;
				T result = caseTUsage(tUsage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TVARIABLES: {
				TVariables tVariables = (TVariables)theEObject;
				T result = caseTVariables(tVariables);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TVARIANT: {
				TVariant tVariant = (TVariant)theEObject;
				T result = caseTVariant(tVariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TWEATHER: {
				TWeather tWeather = (TWeather)theEObject;
				T result = caseTWeather(tWeather);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT: {
				TWeatherDataSeriesVariant tWeatherDataSeriesVariant = (TWeatherDataSeriesVariant)theEObject;
				T result = caseTWeatherDataSeriesVariant(tWeatherDataSeriesVariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TWEATHER_DATA_SET_VARIANT: {
				TWeatherDataSetVariant tWeatherDataSetVariant = (TWeatherDataSetVariant)theEObject;
				T result = caseTWeatherDataSetVariant(tWeatherDataSetVariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TWINDOW_TYPE: {
				TWindowType tWindowType = (TWindowType)theEObject;
				T result = caseTWindowType(tWindowType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TWINDOW_TYPES: {
				TWindowTypes tWindowTypes = (TWindowTypes)theEObject;
				T result = caseTWindowTypes(tWindowTypes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case simmatrixPackage.TWINDOW_TYPE_VARIANT: {
				TWindowTypeVariant tWindowTypeVariant = (TWindowTypeVariant)theEObject;
				T result = caseTWindowTypeVariant(tWindowTypeVariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TAssignment Groups</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TAssignment Groups</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTAssignmentGroups(TAssignmentGroups object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TBIM Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TBIM Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTBIMGroup(TBIMGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCombination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCombination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCombination(TCombination object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCombinations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCombinations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCombinations(TCombinations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TConstant Type Variant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TConstant Type Variant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTConstantTypeVariant(TConstantTypeVariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TConstruction Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TConstruction Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTConstructionType(TConstructionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TConstruction Types</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TConstruction Types</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTConstructionTypes(TConstructionTypes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TConstruction Type Variant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TConstruction Type Variant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTConstructionTypeVariant(TConstructionTypeVariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TElement Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TElement Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTElementGroup(TElementGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TElements</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TElements</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTElements(TElements object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TElevation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TElevation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTElevation(TElevation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TElevation Variant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TElevation Variant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTElevationVariant(TElevationVariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TFloat With Units</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TFloat With Units</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTFloatWithUnits(TFloatWithUnits object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TLayer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TLayer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTLayer(TLayer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TMaterial Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TMaterial Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTMaterialType(TMaterialType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TMaterial Types</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TMaterial Types</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTMaterialTypes(TMaterialTypes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TMaterial Type Variant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TMaterial Type Variant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTMaterialTypeVariant(TMaterialTypeVariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TOccupancy Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TOccupancy Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTOccupancyType(TOccupancyType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TOccupancy Type Variant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TOccupancy Type Variant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTOccupancyTypeVariant(TOccupancyTypeVariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TOrientation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TOrientation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTOrientation(TOrientation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TOrientation Variant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TOrientation Variant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTOrientationVariant(TOrientationVariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSchedule Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSchedule Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTScheduleType(TScheduleType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSchedule Type Variant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSchedule Type Variant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTScheduleTypeVariant(TScheduleTypeVariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSet Person Loads</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSet Person Loads</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSetPersonLoads(TSetPersonLoads object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSet Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSet Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSetPoint(TSetPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSet Shading</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSet Shading</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSetShading(TSetShading object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSet Temperature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSet Temperature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSetTemperature(TSetTemperature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSimulation Matrix</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSimulation Matrix</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSimulationMatrix(TSimulationMatrix object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSpace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSpace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSpace(TSpace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSpace Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSpace Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSpaceGroup(TSpaceGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSpaces</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSpaces</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSpaces(TSpaces object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TTarget</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TTarget</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTTarget(TTarget object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TTarget List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TTarget List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTTargetList(TTargetList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TTargets</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TTargets</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTTargets(TTargets object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TUsage</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TUsage</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTUsage(TUsage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TVariables</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TVariables</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTVariables(TVariables object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TVariant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TVariant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTVariant(TVariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TWeather</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TWeather</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTWeather(TWeather object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TWeather Data Series Variant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TWeather Data Series Variant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTWeatherDataSeriesVariant(TWeatherDataSeriesVariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TWeather Data Set Variant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TWeather Data Set Variant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTWeatherDataSetVariant(TWeatherDataSetVariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TWindow Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TWindow Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTWindowType(TWindowType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TWindow Types</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TWindow Types</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTWindowTypes(TWindowTypes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TWindow Type Variant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TWindow Type Variant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTWindowTypeVariant(TWindowTypeVariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //simmatrixSwitch
