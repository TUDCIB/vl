/**
 */
package de.tudresden.bau.cib.simmatrix.util;

import de.tudresden.bau.cib.simmatrix.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.tudresden.bau.cib.simmatrix.simmatrixPackage
 * @generated
 */
public class simmatrixAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static simmatrixPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public simmatrixAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = simmatrixPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected simmatrixSwitch<Adapter> modelSwitch =
		new simmatrixSwitch<Adapter>() {
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseTAssignmentGroups(TAssignmentGroups object) {
				return createTAssignmentGroupsAdapter();
			}
			@Override
			public Adapter caseTBIMGroup(TBIMGroup object) {
				return createTBIMGroupAdapter();
			}
			@Override
			public Adapter caseTCombination(TCombination object) {
				return createTCombinationAdapter();
			}
			@Override
			public Adapter caseTCombinations(TCombinations object) {
				return createTCombinationsAdapter();
			}
			@Override
			public Adapter caseTConstantTypeVariant(TConstantTypeVariant object) {
				return createTConstantTypeVariantAdapter();
			}
			@Override
			public Adapter caseTConstructionType(TConstructionType object) {
				return createTConstructionTypeAdapter();
			}
			@Override
			public Adapter caseTConstructionTypes(TConstructionTypes object) {
				return createTConstructionTypesAdapter();
			}
			@Override
			public Adapter caseTConstructionTypeVariant(TConstructionTypeVariant object) {
				return createTConstructionTypeVariantAdapter();
			}
			@Override
			public Adapter caseTElementGroup(TElementGroup object) {
				return createTElementGroupAdapter();
			}
			@Override
			public Adapter caseTElements(TElements object) {
				return createTElementsAdapter();
			}
			@Override
			public Adapter caseTElevation(TElevation object) {
				return createTElevationAdapter();
			}
			@Override
			public Adapter caseTElevationVariant(TElevationVariant object) {
				return createTElevationVariantAdapter();
			}
			@Override
			public Adapter caseTFloatWithUnits(TFloatWithUnits object) {
				return createTFloatWithUnitsAdapter();
			}
			@Override
			public Adapter caseTLayer(TLayer object) {
				return createTLayerAdapter();
			}
			@Override
			public Adapter caseTMaterialType(TMaterialType object) {
				return createTMaterialTypeAdapter();
			}
			@Override
			public Adapter caseTMaterialTypes(TMaterialTypes object) {
				return createTMaterialTypesAdapter();
			}
			@Override
			public Adapter caseTMaterialTypeVariant(TMaterialTypeVariant object) {
				return createTMaterialTypeVariantAdapter();
			}
			@Override
			public Adapter caseTOccupancyType(TOccupancyType object) {
				return createTOccupancyTypeAdapter();
			}
			@Override
			public Adapter caseTOccupancyTypeVariant(TOccupancyTypeVariant object) {
				return createTOccupancyTypeVariantAdapter();
			}
			@Override
			public Adapter caseTOrientation(TOrientation object) {
				return createTOrientationAdapter();
			}
			@Override
			public Adapter caseTOrientationVariant(TOrientationVariant object) {
				return createTOrientationVariantAdapter();
			}
			@Override
			public Adapter caseTScheduleType(TScheduleType object) {
				return createTScheduleTypeAdapter();
			}
			@Override
			public Adapter caseTScheduleTypeVariant(TScheduleTypeVariant object) {
				return createTScheduleTypeVariantAdapter();
			}
			@Override
			public Adapter caseTSetPersonLoads(TSetPersonLoads object) {
				return createTSetPersonLoadsAdapter();
			}
			@Override
			public Adapter caseTSetPoint(TSetPoint object) {
				return createTSetPointAdapter();
			}
			@Override
			public Adapter caseTSetShading(TSetShading object) {
				return createTSetShadingAdapter();
			}
			@Override
			public Adapter caseTSetTemperature(TSetTemperature object) {
				return createTSetTemperatureAdapter();
			}
			@Override
			public Adapter caseTSimulationMatrix(TSimulationMatrix object) {
				return createTSimulationMatrixAdapter();
			}
			@Override
			public Adapter caseTSpace(TSpace object) {
				return createTSpaceAdapter();
			}
			@Override
			public Adapter caseTSpaceGroup(TSpaceGroup object) {
				return createTSpaceGroupAdapter();
			}
			@Override
			public Adapter caseTSpaces(TSpaces object) {
				return createTSpacesAdapter();
			}
			@Override
			public Adapter caseTTarget(TTarget object) {
				return createTTargetAdapter();
			}
			@Override
			public Adapter caseTTargetList(TTargetList object) {
				return createTTargetListAdapter();
			}
			@Override
			public Adapter caseTTargets(TTargets object) {
				return createTTargetsAdapter();
			}
			@Override
			public Adapter caseTUsage(TUsage object) {
				return createTUsageAdapter();
			}
			@Override
			public Adapter caseTVariables(TVariables object) {
				return createTVariablesAdapter();
			}
			@Override
			public Adapter caseTVariant(TVariant object) {
				return createTVariantAdapter();
			}
			@Override
			public Adapter caseTWeather(TWeather object) {
				return createTWeatherAdapter();
			}
			@Override
			public Adapter caseTWeatherDataSeriesVariant(TWeatherDataSeriesVariant object) {
				return createTWeatherDataSeriesVariantAdapter();
			}
			@Override
			public Adapter caseTWeatherDataSetVariant(TWeatherDataSetVariant object) {
				return createTWeatherDataSetVariantAdapter();
			}
			@Override
			public Adapter caseTWindowType(TWindowType object) {
				return createTWindowTypeAdapter();
			}
			@Override
			public Adapter caseTWindowTypes(TWindowTypes object) {
				return createTWindowTypesAdapter();
			}
			@Override
			public Adapter caseTWindowTypeVariant(TWindowTypeVariant object) {
				return createTWindowTypeVariantAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TAssignmentGroups <em>TAssignment Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TAssignmentGroups
	 * @generated
	 */
	public Adapter createTAssignmentGroupsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TBIMGroup <em>TBIM Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TBIMGroup
	 * @generated
	 */
	public Adapter createTBIMGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TCombination <em>TCombination</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TCombination
	 * @generated
	 */
	public Adapter createTCombinationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TCombinations <em>TCombinations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TCombinations
	 * @generated
	 */
	public Adapter createTCombinationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TConstantTypeVariant <em>TConstant Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TConstantTypeVariant
	 * @generated
	 */
	public Adapter createTConstantTypeVariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TConstructionType <em>TConstruction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionType
	 * @generated
	 */
	public Adapter createTConstructionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TConstructionTypes <em>TConstruction Types</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionTypes
	 * @generated
	 */
	public Adapter createTConstructionTypesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant <em>TConstruction Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant
	 * @generated
	 */
	public Adapter createTConstructionTypeVariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TElementGroup <em>TElement Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TElementGroup
	 * @generated
	 */
	public Adapter createTElementGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TElements <em>TElements</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TElements
	 * @generated
	 */
	public Adapter createTElementsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TElevation <em>TElevation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TElevation
	 * @generated
	 */
	public Adapter createTElevationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TElevationVariant <em>TElevation Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TElevationVariant
	 * @generated
	 */
	public Adapter createTElevationVariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TFloatWithUnits <em>TFloat With Units</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TFloatWithUnits
	 * @generated
	 */
	public Adapter createTFloatWithUnitsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TLayer <em>TLayer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TLayer
	 * @generated
	 */
	public Adapter createTLayerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TMaterialType <em>TMaterial Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialType
	 * @generated
	 */
	public Adapter createTMaterialTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypes <em>TMaterial Types</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypes
	 * @generated
	 */
	public Adapter createTMaterialTypesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant <em>TMaterial Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant
	 * @generated
	 */
	public Adapter createTMaterialTypeVariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TOccupancyType <em>TOccupancy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TOccupancyType
	 * @generated
	 */
	public Adapter createTOccupancyTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant <em>TOccupancy Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TOccupancyTypeVariant
	 * @generated
	 */
	public Adapter createTOccupancyTypeVariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TOrientation <em>TOrientation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TOrientation
	 * @generated
	 */
	public Adapter createTOrientationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TOrientationVariant <em>TOrientation Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TOrientationVariant
	 * @generated
	 */
	public Adapter createTOrientationVariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TScheduleType <em>TSchedule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleType
	 * @generated
	 */
	public Adapter createTScheduleTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant <em>TSchedule Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant
	 * @generated
	 */
	public Adapter createTScheduleTypeVariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TSetPersonLoads <em>TSet Person Loads</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TSetPersonLoads
	 * @generated
	 */
	public Adapter createTSetPersonLoadsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TSetPoint <em>TSet Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TSetPoint
	 * @generated
	 */
	public Adapter createTSetPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TSetShading <em>TSet Shading</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TSetShading
	 * @generated
	 */
	public Adapter createTSetShadingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TSetTemperature <em>TSet Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TSetTemperature
	 * @generated
	 */
	public Adapter createTSetTemperatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TSimulationMatrix <em>TSimulation Matrix</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TSimulationMatrix
	 * @generated
	 */
	public Adapter createTSimulationMatrixAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TSpace <em>TSpace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TSpace
	 * @generated
	 */
	public Adapter createTSpaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TSpaceGroup <em>TSpace Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TSpaceGroup
	 * @generated
	 */
	public Adapter createTSpaceGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TSpaces <em>TSpaces</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TSpaces
	 * @generated
	 */
	public Adapter createTSpacesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TTarget <em>TTarget</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TTarget
	 * @generated
	 */
	public Adapter createTTargetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TTargetList <em>TTarget List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TTargetList
	 * @generated
	 */
	public Adapter createTTargetListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TTargets <em>TTargets</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TTargets
	 * @generated
	 */
	public Adapter createTTargetsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TUsage <em>TUsage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TUsage
	 * @generated
	 */
	public Adapter createTUsageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TVariables <em>TVariables</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TVariables
	 * @generated
	 */
	public Adapter createTVariablesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TVariant <em>TVariant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TVariant
	 * @generated
	 */
	public Adapter createTVariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TWeather <em>TWeather</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TWeather
	 * @generated
	 */
	public Adapter createTWeatherAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant <em>TWeather Data Series Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant
	 * @generated
	 */
	public Adapter createTWeatherDataSeriesVariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant <em>TWeather Data Set Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant
	 * @generated
	 */
	public Adapter createTWeatherDataSetVariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TWindowType <em>TWindow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowType
	 * @generated
	 */
	public Adapter createTWindowTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TWindowTypes <em>TWindow Types</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowTypes
	 * @generated
	 */
	public Adapter createTWindowTypesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.tudresden.bau.cib.simmatrix.TWindowTypeVariant <em>TWindow Type Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.tudresden.bau.cib.simmatrix.TWindowTypeVariant
	 * @generated
	 */
	public Adapter createTWindowTypeVariantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //simmatrixAdapterFactory
