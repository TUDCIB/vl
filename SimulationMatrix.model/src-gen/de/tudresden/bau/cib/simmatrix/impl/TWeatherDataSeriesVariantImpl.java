/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TWeatherDataSeriesVariant;
import de.tudresden.bau.cib.simmatrix.WeatherTypes;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TWeather Data Series Variant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSeriesVariantImpl#getAirTemperature <em>Air Temperature</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSeriesVariantImpl#getShortWaveDiffuse <em>Short Wave Diffuse</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSeriesVariantImpl#getShortWaveDirect <em>Short Wave Direct</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSeriesVariantImpl#getId <em>Id</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWeatherDataSeriesVariantImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TWeatherDataSeriesVariantImpl extends EObjectImpl implements TWeatherDataSeriesVariant {
	/**
	 * The default value of the '{@link #getAirTemperature() <em>Air Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAirTemperature()
	 * @generated
	 * @ordered
	 */
	protected static final String AIR_TEMPERATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAirTemperature() <em>Air Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAirTemperature()
	 * @generated
	 * @ordered
	 */
	protected String airTemperature = AIR_TEMPERATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getShortWaveDiffuse() <em>Short Wave Diffuse</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortWaveDiffuse()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_WAVE_DIFFUSE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortWaveDiffuse() <em>Short Wave Diffuse</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortWaveDiffuse()
	 * @generated
	 * @ordered
	 */
	protected String shortWaveDiffuse = SHORT_WAVE_DIFFUSE_EDEFAULT;

	/**
	 * The default value of the '{@link #getShortWaveDirect() <em>Short Wave Direct</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortWaveDirect()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_WAVE_DIRECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortWaveDirect() <em>Short Wave Direct</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortWaveDirect()
	 * @generated
	 * @ordered
	 */
	protected String shortWaveDirect = SHORT_WAVE_DIRECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final WeatherTypes TYPE_EDEFAULT = WeatherTypes.CCD;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected WeatherTypes type = TYPE_EDEFAULT;

	/**
	 * This is true if the Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean typeESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TWeatherDataSeriesVariantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TWEATHER_DATA_SERIES_VARIANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAirTemperature() {
		return airTemperature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAirTemperature(String newAirTemperature) {
		String oldAirTemperature = airTemperature;
		airTemperature = newAirTemperature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__AIR_TEMPERATURE, oldAirTemperature, airTemperature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getShortWaveDiffuse() {
		return shortWaveDiffuse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortWaveDiffuse(String newShortWaveDiffuse) {
		String oldShortWaveDiffuse = shortWaveDiffuse;
		shortWaveDiffuse = newShortWaveDiffuse;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIFFUSE, oldShortWaveDiffuse, shortWaveDiffuse));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getShortWaveDirect() {
		return shortWaveDirect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortWaveDirect(String newShortWaveDirect) {
		String oldShortWaveDirect = shortWaveDirect;
		shortWaveDirect = newShortWaveDirect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIRECT, oldShortWaveDirect, shortWaveDirect));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeatherTypes getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(WeatherTypes newType) {
		WeatherTypes oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		boolean oldTypeESet = typeESet;
		typeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__TYPE, oldType, type, !oldTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetType() {
		WeatherTypes oldType = type;
		boolean oldTypeESet = typeESet;
		type = TYPE_EDEFAULT;
		typeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__TYPE, oldType, TYPE_EDEFAULT, oldTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetType() {
		return typeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__AIR_TEMPERATURE:
				return getAirTemperature();
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIFFUSE:
				return getShortWaveDiffuse();
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIRECT:
				return getShortWaveDirect();
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__ID:
				return getId();
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__TYPE:
				return getType();
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
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__AIR_TEMPERATURE:
				setAirTemperature((String)newValue);
				return;
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIFFUSE:
				setShortWaveDiffuse((String)newValue);
				return;
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIRECT:
				setShortWaveDirect((String)newValue);
				return;
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__ID:
				setId((String)newValue);
				return;
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__TYPE:
				setType((WeatherTypes)newValue);
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
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__AIR_TEMPERATURE:
				setAirTemperature(AIR_TEMPERATURE_EDEFAULT);
				return;
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIFFUSE:
				setShortWaveDiffuse(SHORT_WAVE_DIFFUSE_EDEFAULT);
				return;
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIRECT:
				setShortWaveDirect(SHORT_WAVE_DIRECT_EDEFAULT);
				return;
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__ID:
				setId(ID_EDEFAULT);
				return;
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__TYPE:
				unsetType();
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
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__AIR_TEMPERATURE:
				return AIR_TEMPERATURE_EDEFAULT == null ? airTemperature != null : !AIR_TEMPERATURE_EDEFAULT.equals(airTemperature);
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIFFUSE:
				return SHORT_WAVE_DIFFUSE_EDEFAULT == null ? shortWaveDiffuse != null : !SHORT_WAVE_DIFFUSE_EDEFAULT.equals(shortWaveDiffuse);
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__SHORT_WAVE_DIRECT:
				return SHORT_WAVE_DIRECT_EDEFAULT == null ? shortWaveDirect != null : !SHORT_WAVE_DIRECT_EDEFAULT.equals(shortWaveDirect);
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case simmatrixPackage.TWEATHER_DATA_SERIES_VARIANT__TYPE:
				return isSetType();
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
		result.append(" (airTemperature: ");
		result.append(airTemperature);
		result.append(", shortWaveDiffuse: ");
		result.append(shortWaveDiffuse);
		result.append(", shortWaveDirect: ");
		result.append(shortWaveDirect);
		result.append(", id: ");
		result.append(id);
		result.append(", type: ");
		if (typeESet) result.append(type); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TWeatherDataSeriesVariantImpl
