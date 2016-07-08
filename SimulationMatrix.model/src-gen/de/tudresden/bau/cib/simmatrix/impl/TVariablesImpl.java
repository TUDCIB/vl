/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TConstructionTypes;
import de.tudresden.bau.cib.simmatrix.TElevation;
import de.tudresden.bau.cib.simmatrix.TMaterialTypes;
import de.tudresden.bau.cib.simmatrix.TOrientation;
import de.tudresden.bau.cib.simmatrix.TUsage;
import de.tudresden.bau.cib.simmatrix.TVariables;
import de.tudresden.bau.cib.simmatrix.TWeather;
import de.tudresden.bau.cib.simmatrix.TWindowTypes;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TVariables</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl#getWeatherVariables <em>Weather Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl#getOrientationVariables <em>Orientation Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl#getElevationVariables <em>Elevation Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl#getUsageTypeVariables <em>Usage Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl#getConstructionTypeVariables <em>Construction Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl#getWindowTypeVariables <em>Window Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl#getDoorTypeVariables <em>Door Type Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TVariablesImpl#getMaterialTypeVariables <em>Material Type Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TVariablesImpl extends EObjectImpl implements TVariables {
	/**
	 * The cached value of the '{@link #getWeatherVariables() <em>Weather Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeatherVariables()
	 * @generated
	 * @ordered
	 */
	protected TWeather weatherVariables;

	/**
	 * The cached value of the '{@link #getOrientationVariables() <em>Orientation Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrientationVariables()
	 * @generated
	 * @ordered
	 */
	protected TOrientation orientationVariables;

	/**
	 * The cached value of the '{@link #getElevationVariables() <em>Elevation Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElevationVariables()
	 * @generated
	 * @ordered
	 */
	protected TElevation elevationVariables;

	/**
	 * The cached value of the '{@link #getUsageTypeVariables() <em>Usage Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsageTypeVariables()
	 * @generated
	 * @ordered
	 */
	protected TUsage usageTypeVariables;

	/**
	 * The cached value of the '{@link #getConstructionTypeVariables() <em>Construction Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstructionTypeVariables()
	 * @generated
	 * @ordered
	 */
	protected TConstructionTypes constructionTypeVariables;

	/**
	 * The cached value of the '{@link #getWindowTypeVariables() <em>Window Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindowTypeVariables()
	 * @generated
	 * @ordered
	 */
	protected TWindowTypes windowTypeVariables;

	/**
	 * The default value of the '{@link #getDoorTypeVariables() <em>Door Type Variables</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDoorTypeVariables()
	 * @generated
	 * @ordered
	 */
	protected static final String DOOR_TYPE_VARIABLES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDoorTypeVariables() <em>Door Type Variables</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDoorTypeVariables()
	 * @generated
	 * @ordered
	 */
	protected String doorTypeVariables = DOOR_TYPE_VARIABLES_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMaterialTypeVariables() <em>Material Type Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaterialTypeVariables()
	 * @generated
	 * @ordered
	 */
	protected TMaterialTypes materialTypeVariables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TVariablesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TVARIABLES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWeather getWeatherVariables() {
		return weatherVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWeatherVariables(TWeather newWeatherVariables, NotificationChain msgs) {
		TWeather oldWeatherVariables = weatherVariables;
		weatherVariables = newWeatherVariables;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__WEATHER_VARIABLES, oldWeatherVariables, newWeatherVariables);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeatherVariables(TWeather newWeatherVariables) {
		if (newWeatherVariables != weatherVariables) {
			NotificationChain msgs = null;
			if (weatherVariables != null)
				msgs = ((InternalEObject)weatherVariables).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__WEATHER_VARIABLES, null, msgs);
			if (newWeatherVariables != null)
				msgs = ((InternalEObject)newWeatherVariables).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__WEATHER_VARIABLES, null, msgs);
			msgs = basicSetWeatherVariables(newWeatherVariables, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__WEATHER_VARIABLES, newWeatherVariables, newWeatherVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOrientation getOrientationVariables() {
		return orientationVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOrientationVariables(TOrientation newOrientationVariables, NotificationChain msgs) {
		TOrientation oldOrientationVariables = orientationVariables;
		orientationVariables = newOrientationVariables;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__ORIENTATION_VARIABLES, oldOrientationVariables, newOrientationVariables);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrientationVariables(TOrientation newOrientationVariables) {
		if (newOrientationVariables != orientationVariables) {
			NotificationChain msgs = null;
			if (orientationVariables != null)
				msgs = ((InternalEObject)orientationVariables).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__ORIENTATION_VARIABLES, null, msgs);
			if (newOrientationVariables != null)
				msgs = ((InternalEObject)newOrientationVariables).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__ORIENTATION_VARIABLES, null, msgs);
			msgs = basicSetOrientationVariables(newOrientationVariables, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__ORIENTATION_VARIABLES, newOrientationVariables, newOrientationVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TElevation getElevationVariables() {
		return elevationVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElevationVariables(TElevation newElevationVariables, NotificationChain msgs) {
		TElevation oldElevationVariables = elevationVariables;
		elevationVariables = newElevationVariables;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__ELEVATION_VARIABLES, oldElevationVariables, newElevationVariables);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElevationVariables(TElevation newElevationVariables) {
		if (newElevationVariables != elevationVariables) {
			NotificationChain msgs = null;
			if (elevationVariables != null)
				msgs = ((InternalEObject)elevationVariables).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__ELEVATION_VARIABLES, null, msgs);
			if (newElevationVariables != null)
				msgs = ((InternalEObject)newElevationVariables).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__ELEVATION_VARIABLES, null, msgs);
			msgs = basicSetElevationVariables(newElevationVariables, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__ELEVATION_VARIABLES, newElevationVariables, newElevationVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TUsage getUsageTypeVariables() {
		return usageTypeVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUsageTypeVariables(TUsage newUsageTypeVariables, NotificationChain msgs) {
		TUsage oldUsageTypeVariables = usageTypeVariables;
		usageTypeVariables = newUsageTypeVariables;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__USAGE_TYPE_VARIABLES, oldUsageTypeVariables, newUsageTypeVariables);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsageTypeVariables(TUsage newUsageTypeVariables) {
		if (newUsageTypeVariables != usageTypeVariables) {
			NotificationChain msgs = null;
			if (usageTypeVariables != null)
				msgs = ((InternalEObject)usageTypeVariables).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__USAGE_TYPE_VARIABLES, null, msgs);
			if (newUsageTypeVariables != null)
				msgs = ((InternalEObject)newUsageTypeVariables).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__USAGE_TYPE_VARIABLES, null, msgs);
			msgs = basicSetUsageTypeVariables(newUsageTypeVariables, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__USAGE_TYPE_VARIABLES, newUsageTypeVariables, newUsageTypeVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConstructionTypes getConstructionTypeVariables() {
		return constructionTypeVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConstructionTypeVariables(TConstructionTypes newConstructionTypeVariables, NotificationChain msgs) {
		TConstructionTypes oldConstructionTypeVariables = constructionTypeVariables;
		constructionTypeVariables = newConstructionTypeVariables;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__CONSTRUCTION_TYPE_VARIABLES, oldConstructionTypeVariables, newConstructionTypeVariables);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstructionTypeVariables(TConstructionTypes newConstructionTypeVariables) {
		if (newConstructionTypeVariables != constructionTypeVariables) {
			NotificationChain msgs = null;
			if (constructionTypeVariables != null)
				msgs = ((InternalEObject)constructionTypeVariables).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__CONSTRUCTION_TYPE_VARIABLES, null, msgs);
			if (newConstructionTypeVariables != null)
				msgs = ((InternalEObject)newConstructionTypeVariables).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__CONSTRUCTION_TYPE_VARIABLES, null, msgs);
			msgs = basicSetConstructionTypeVariables(newConstructionTypeVariables, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__CONSTRUCTION_TYPE_VARIABLES, newConstructionTypeVariables, newConstructionTypeVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TWindowTypes getWindowTypeVariables() {
		return windowTypeVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWindowTypeVariables(TWindowTypes newWindowTypeVariables, NotificationChain msgs) {
		TWindowTypes oldWindowTypeVariables = windowTypeVariables;
		windowTypeVariables = newWindowTypeVariables;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__WINDOW_TYPE_VARIABLES, oldWindowTypeVariables, newWindowTypeVariables);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWindowTypeVariables(TWindowTypes newWindowTypeVariables) {
		if (newWindowTypeVariables != windowTypeVariables) {
			NotificationChain msgs = null;
			if (windowTypeVariables != null)
				msgs = ((InternalEObject)windowTypeVariables).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__WINDOW_TYPE_VARIABLES, null, msgs);
			if (newWindowTypeVariables != null)
				msgs = ((InternalEObject)newWindowTypeVariables).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__WINDOW_TYPE_VARIABLES, null, msgs);
			msgs = basicSetWindowTypeVariables(newWindowTypeVariables, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__WINDOW_TYPE_VARIABLES, newWindowTypeVariables, newWindowTypeVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDoorTypeVariables() {
		return doorTypeVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDoorTypeVariables(String newDoorTypeVariables) {
		String oldDoorTypeVariables = doorTypeVariables;
		doorTypeVariables = newDoorTypeVariables;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__DOOR_TYPE_VARIABLES, oldDoorTypeVariables, doorTypeVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMaterialTypes getMaterialTypeVariables() {
		return materialTypeVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMaterialTypeVariables(TMaterialTypes newMaterialTypeVariables, NotificationChain msgs) {
		TMaterialTypes oldMaterialTypeVariables = materialTypeVariables;
		materialTypeVariables = newMaterialTypeVariables;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__MATERIAL_TYPE_VARIABLES, oldMaterialTypeVariables, newMaterialTypeVariables);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaterialTypeVariables(TMaterialTypes newMaterialTypeVariables) {
		if (newMaterialTypeVariables != materialTypeVariables) {
			NotificationChain msgs = null;
			if (materialTypeVariables != null)
				msgs = ((InternalEObject)materialTypeVariables).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__MATERIAL_TYPE_VARIABLES, null, msgs);
			if (newMaterialTypeVariables != null)
				msgs = ((InternalEObject)newMaterialTypeVariables).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TVARIABLES__MATERIAL_TYPE_VARIABLES, null, msgs);
			msgs = basicSetMaterialTypeVariables(newMaterialTypeVariables, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIABLES__MATERIAL_TYPE_VARIABLES, newMaterialTypeVariables, newMaterialTypeVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TVARIABLES__WEATHER_VARIABLES:
				return basicSetWeatherVariables(null, msgs);
			case simmatrixPackage.TVARIABLES__ORIENTATION_VARIABLES:
				return basicSetOrientationVariables(null, msgs);
			case simmatrixPackage.TVARIABLES__ELEVATION_VARIABLES:
				return basicSetElevationVariables(null, msgs);
			case simmatrixPackage.TVARIABLES__USAGE_TYPE_VARIABLES:
				return basicSetUsageTypeVariables(null, msgs);
			case simmatrixPackage.TVARIABLES__CONSTRUCTION_TYPE_VARIABLES:
				return basicSetConstructionTypeVariables(null, msgs);
			case simmatrixPackage.TVARIABLES__WINDOW_TYPE_VARIABLES:
				return basicSetWindowTypeVariables(null, msgs);
			case simmatrixPackage.TVARIABLES__MATERIAL_TYPE_VARIABLES:
				return basicSetMaterialTypeVariables(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case simmatrixPackage.TVARIABLES__WEATHER_VARIABLES:
				return getWeatherVariables();
			case simmatrixPackage.TVARIABLES__ORIENTATION_VARIABLES:
				return getOrientationVariables();
			case simmatrixPackage.TVARIABLES__ELEVATION_VARIABLES:
				return getElevationVariables();
			case simmatrixPackage.TVARIABLES__USAGE_TYPE_VARIABLES:
				return getUsageTypeVariables();
			case simmatrixPackage.TVARIABLES__CONSTRUCTION_TYPE_VARIABLES:
				return getConstructionTypeVariables();
			case simmatrixPackage.TVARIABLES__WINDOW_TYPE_VARIABLES:
				return getWindowTypeVariables();
			case simmatrixPackage.TVARIABLES__DOOR_TYPE_VARIABLES:
				return getDoorTypeVariables();
			case simmatrixPackage.TVARIABLES__MATERIAL_TYPE_VARIABLES:
				return getMaterialTypeVariables();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case simmatrixPackage.TVARIABLES__WEATHER_VARIABLES:
				setWeatherVariables((TWeather)newValue);
				return;
			case simmatrixPackage.TVARIABLES__ORIENTATION_VARIABLES:
				setOrientationVariables((TOrientation)newValue);
				return;
			case simmatrixPackage.TVARIABLES__ELEVATION_VARIABLES:
				setElevationVariables((TElevation)newValue);
				return;
			case simmatrixPackage.TVARIABLES__USAGE_TYPE_VARIABLES:
				setUsageTypeVariables((TUsage)newValue);
				return;
			case simmatrixPackage.TVARIABLES__CONSTRUCTION_TYPE_VARIABLES:
				setConstructionTypeVariables((TConstructionTypes)newValue);
				return;
			case simmatrixPackage.TVARIABLES__WINDOW_TYPE_VARIABLES:
				setWindowTypeVariables((TWindowTypes)newValue);
				return;
			case simmatrixPackage.TVARIABLES__DOOR_TYPE_VARIABLES:
				setDoorTypeVariables((String)newValue);
				return;
			case simmatrixPackage.TVARIABLES__MATERIAL_TYPE_VARIABLES:
				setMaterialTypeVariables((TMaterialTypes)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case simmatrixPackage.TVARIABLES__WEATHER_VARIABLES:
				setWeatherVariables((TWeather)null);
				return;
			case simmatrixPackage.TVARIABLES__ORIENTATION_VARIABLES:
				setOrientationVariables((TOrientation)null);
				return;
			case simmatrixPackage.TVARIABLES__ELEVATION_VARIABLES:
				setElevationVariables((TElevation)null);
				return;
			case simmatrixPackage.TVARIABLES__USAGE_TYPE_VARIABLES:
				setUsageTypeVariables((TUsage)null);
				return;
			case simmatrixPackage.TVARIABLES__CONSTRUCTION_TYPE_VARIABLES:
				setConstructionTypeVariables((TConstructionTypes)null);
				return;
			case simmatrixPackage.TVARIABLES__WINDOW_TYPE_VARIABLES:
				setWindowTypeVariables((TWindowTypes)null);
				return;
			case simmatrixPackage.TVARIABLES__DOOR_TYPE_VARIABLES:
				setDoorTypeVariables(DOOR_TYPE_VARIABLES_EDEFAULT);
				return;
			case simmatrixPackage.TVARIABLES__MATERIAL_TYPE_VARIABLES:
				setMaterialTypeVariables((TMaterialTypes)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case simmatrixPackage.TVARIABLES__WEATHER_VARIABLES:
				return weatherVariables != null;
			case simmatrixPackage.TVARIABLES__ORIENTATION_VARIABLES:
				return orientationVariables != null;
			case simmatrixPackage.TVARIABLES__ELEVATION_VARIABLES:
				return elevationVariables != null;
			case simmatrixPackage.TVARIABLES__USAGE_TYPE_VARIABLES:
				return usageTypeVariables != null;
			case simmatrixPackage.TVARIABLES__CONSTRUCTION_TYPE_VARIABLES:
				return constructionTypeVariables != null;
			case simmatrixPackage.TVARIABLES__WINDOW_TYPE_VARIABLES:
				return windowTypeVariables != null;
			case simmatrixPackage.TVARIABLES__DOOR_TYPE_VARIABLES:
				return DOOR_TYPE_VARIABLES_EDEFAULT == null ? doorTypeVariables != null : !DOOR_TYPE_VARIABLES_EDEFAULT.equals(doorTypeVariables);
			case simmatrixPackage.TVARIABLES__MATERIAL_TYPE_VARIABLES:
				return materialTypeVariables != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (doorTypeVariables: ");
		result.append(doorTypeVariables);
		result.append(')');
		return result.toString();
	}

} //TVariablesImpl
