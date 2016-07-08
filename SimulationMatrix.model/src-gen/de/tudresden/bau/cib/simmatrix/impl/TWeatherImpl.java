/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TWeather;
import de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant;
import de.tudresden.bau.cib.simmatrix.TWeatherDataSetVariant;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TWeather</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherImpl#getWeatherDataSetVariant <em>Weather Data Set Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherImpl#getWeatherDataSeriesVariant <em>Weather Data Series Variant</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TWeatherImpl extends EObjectImpl implements TWeather {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TWeatherImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TWEATHER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, simmatrixPackage.TWEATHER__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TWeatherDataSetVariant> getWeatherDataSetVariant() {
		return getGroup().list(simmatrixPackage.Literals.TWEATHER__WEATHER_DATA_SET_VARIANT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TWeatherDataSeriesVariant> getWeatherDataSeriesVariant() {
		return getGroup().list(simmatrixPackage.Literals.TWEATHER__WEATHER_DATA_SERIES_VARIANT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TWEATHER__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case simmatrixPackage.TWEATHER__WEATHER_DATA_SET_VARIANT:
				return ((InternalEList<?>)getWeatherDataSetVariant()).basicRemove(otherEnd, msgs);
			case simmatrixPackage.TWEATHER__WEATHER_DATA_SERIES_VARIANT:
				return ((InternalEList<?>)getWeatherDataSeriesVariant()).basicRemove(otherEnd, msgs);
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
			case simmatrixPackage.TWEATHER__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case simmatrixPackage.TWEATHER__WEATHER_DATA_SET_VARIANT:
				return getWeatherDataSetVariant();
			case simmatrixPackage.TWEATHER__WEATHER_DATA_SERIES_VARIANT:
				return getWeatherDataSeriesVariant();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case simmatrixPackage.TWEATHER__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case simmatrixPackage.TWEATHER__WEATHER_DATA_SET_VARIANT:
				getWeatherDataSetVariant().clear();
				getWeatherDataSetVariant().addAll((Collection<? extends TWeatherDataSetVariant>)newValue);
				return;
			case simmatrixPackage.TWEATHER__WEATHER_DATA_SERIES_VARIANT:
				getWeatherDataSeriesVariant().clear();
				getWeatherDataSeriesVariant().addAll((Collection<? extends TWeatherDataSeriesVariant>)newValue);
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
			case simmatrixPackage.TWEATHER__GROUP:
				getGroup().clear();
				return;
			case simmatrixPackage.TWEATHER__WEATHER_DATA_SET_VARIANT:
				getWeatherDataSetVariant().clear();
				return;
			case simmatrixPackage.TWEATHER__WEATHER_DATA_SERIES_VARIANT:
				getWeatherDataSeriesVariant().clear();
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
			case simmatrixPackage.TWEATHER__GROUP:
				return group != null && !group.isEmpty();
			case simmatrixPackage.TWEATHER__WEATHER_DATA_SET_VARIANT:
				return !getWeatherDataSetVariant().isEmpty();
			case simmatrixPackage.TWEATHER__WEATHER_DATA_SERIES_VARIANT:
				return !getWeatherDataSeriesVariant().isEmpty();
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
		result.append(" (group: ");
		result.append(group);
		result.append(')');
		return result.toString();
	}

} //TWeatherImpl
