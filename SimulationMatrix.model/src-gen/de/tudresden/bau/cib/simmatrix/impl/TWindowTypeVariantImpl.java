/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TFloatWithUnits;
import de.tudresden.bau.cib.simmatrix.TWindowTypeVariant;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TWindow Type Variant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypeVariantImpl#getGlassFraction <em>Glass Fraction</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypeVariantImpl#getFrameFraction <em>Frame Fraction</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypeVariantImpl#getThermalTransmittance <em>Thermal Transmittance</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypeVariantImpl#getSolarHeatGainCoefficient <em>Solar Heat Gain Coefficient</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypeVariantImpl#getShadingFactor <em>Shading Factor</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TWindowTypeVariantImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TWindowTypeVariantImpl extends EObjectImpl implements TWindowTypeVariant {
	/**
	 * The cached value of the '{@link #getGlassFraction() <em>Glass Fraction</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlassFraction()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits glassFraction;

	/**
	 * The cached value of the '{@link #getFrameFraction() <em>Frame Fraction</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrameFraction()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits frameFraction;

	/**
	 * The cached value of the '{@link #getThermalTransmittance() <em>Thermal Transmittance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThermalTransmittance()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits thermalTransmittance;

	/**
	 * The cached value of the '{@link #getSolarHeatGainCoefficient() <em>Solar Heat Gain Coefficient</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSolarHeatGainCoefficient()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits solarHeatGainCoefficient;

	/**
	 * The cached value of the '{@link #getShadingFactor() <em>Shading Factor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShadingFactor()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits shadingFactor;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TWindowTypeVariantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TWINDOW_TYPE_VARIANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getGlassFraction() {
		return glassFraction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlassFraction(TFloatWithUnits newGlassFraction, NotificationChain msgs) {
		TFloatWithUnits oldGlassFraction = glassFraction;
		glassFraction = newGlassFraction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWINDOW_TYPE_VARIANT__GLASS_FRACTION, oldGlassFraction, newGlassFraction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlassFraction(TFloatWithUnits newGlassFraction) {
		if (newGlassFraction != glassFraction) {
			NotificationChain msgs = null;
			if (glassFraction != null)
				msgs = ((InternalEObject)glassFraction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TWINDOW_TYPE_VARIANT__GLASS_FRACTION, null, msgs);
			if (newGlassFraction != null)
				msgs = ((InternalEObject)newGlassFraction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TWINDOW_TYPE_VARIANT__GLASS_FRACTION, null, msgs);
			msgs = basicSetGlassFraction(newGlassFraction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWINDOW_TYPE_VARIANT__GLASS_FRACTION, newGlassFraction, newGlassFraction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getFrameFraction() {
		return frameFraction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFrameFraction(TFloatWithUnits newFrameFraction, NotificationChain msgs) {
		TFloatWithUnits oldFrameFraction = frameFraction;
		frameFraction = newFrameFraction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWINDOW_TYPE_VARIANT__FRAME_FRACTION, oldFrameFraction, newFrameFraction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrameFraction(TFloatWithUnits newFrameFraction) {
		if (newFrameFraction != frameFraction) {
			NotificationChain msgs = null;
			if (frameFraction != null)
				msgs = ((InternalEObject)frameFraction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TWINDOW_TYPE_VARIANT__FRAME_FRACTION, null, msgs);
			if (newFrameFraction != null)
				msgs = ((InternalEObject)newFrameFraction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TWINDOW_TYPE_VARIANT__FRAME_FRACTION, null, msgs);
			msgs = basicSetFrameFraction(newFrameFraction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWINDOW_TYPE_VARIANT__FRAME_FRACTION, newFrameFraction, newFrameFraction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getThermalTransmittance() {
		return thermalTransmittance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThermalTransmittance(TFloatWithUnits newThermalTransmittance, NotificationChain msgs) {
		TFloatWithUnits oldThermalTransmittance = thermalTransmittance;
		thermalTransmittance = newThermalTransmittance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE, oldThermalTransmittance, newThermalTransmittance);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThermalTransmittance(TFloatWithUnits newThermalTransmittance) {
		if (newThermalTransmittance != thermalTransmittance) {
			NotificationChain msgs = null;
			if (thermalTransmittance != null)
				msgs = ((InternalEObject)thermalTransmittance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE, null, msgs);
			if (newThermalTransmittance != null)
				msgs = ((InternalEObject)newThermalTransmittance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE, null, msgs);
			msgs = basicSetThermalTransmittance(newThermalTransmittance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE, newThermalTransmittance, newThermalTransmittance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getSolarHeatGainCoefficient() {
		return solarHeatGainCoefficient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSolarHeatGainCoefficient(TFloatWithUnits newSolarHeatGainCoefficient, NotificationChain msgs) {
		TFloatWithUnits oldSolarHeatGainCoefficient = solarHeatGainCoefficient;
		solarHeatGainCoefficient = newSolarHeatGainCoefficient;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT, oldSolarHeatGainCoefficient, newSolarHeatGainCoefficient);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSolarHeatGainCoefficient(TFloatWithUnits newSolarHeatGainCoefficient) {
		if (newSolarHeatGainCoefficient != solarHeatGainCoefficient) {
			NotificationChain msgs = null;
			if (solarHeatGainCoefficient != null)
				msgs = ((InternalEObject)solarHeatGainCoefficient).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT, null, msgs);
			if (newSolarHeatGainCoefficient != null)
				msgs = ((InternalEObject)newSolarHeatGainCoefficient).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT, null, msgs);
			msgs = basicSetSolarHeatGainCoefficient(newSolarHeatGainCoefficient, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT, newSolarHeatGainCoefficient, newSolarHeatGainCoefficient));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getShadingFactor() {
		return shadingFactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetShadingFactor(TFloatWithUnits newShadingFactor, NotificationChain msgs) {
		TFloatWithUnits oldShadingFactor = shadingFactor;
		shadingFactor = newShadingFactor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWINDOW_TYPE_VARIANT__SHADING_FACTOR, oldShadingFactor, newShadingFactor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShadingFactor(TFloatWithUnits newShadingFactor) {
		if (newShadingFactor != shadingFactor) {
			NotificationChain msgs = null;
			if (shadingFactor != null)
				msgs = ((InternalEObject)shadingFactor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TWINDOW_TYPE_VARIANT__SHADING_FACTOR, null, msgs);
			if (newShadingFactor != null)
				msgs = ((InternalEObject)newShadingFactor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TWINDOW_TYPE_VARIANT__SHADING_FACTOR, null, msgs);
			msgs = basicSetShadingFactor(newShadingFactor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWINDOW_TYPE_VARIANT__SHADING_FACTOR, newShadingFactor, newShadingFactor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TWINDOW_TYPE_VARIANT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__GLASS_FRACTION:
				return basicSetGlassFraction(null, msgs);
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__FRAME_FRACTION:
				return basicSetFrameFraction(null, msgs);
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE:
				return basicSetThermalTransmittance(null, msgs);
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT:
				return basicSetSolarHeatGainCoefficient(null, msgs);
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__SHADING_FACTOR:
				return basicSetShadingFactor(null, msgs);
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
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__GLASS_FRACTION:
				return getGlassFraction();
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__FRAME_FRACTION:
				return getFrameFraction();
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE:
				return getThermalTransmittance();
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT:
				return getSolarHeatGainCoefficient();
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__SHADING_FACTOR:
				return getShadingFactor();
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__ID:
				return getId();
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
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__GLASS_FRACTION:
				setGlassFraction((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__FRAME_FRACTION:
				setFrameFraction((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE:
				setThermalTransmittance((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT:
				setSolarHeatGainCoefficient((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__SHADING_FACTOR:
				setShadingFactor((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__ID:
				setId((String)newValue);
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
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__GLASS_FRACTION:
				setGlassFraction((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__FRAME_FRACTION:
				setFrameFraction((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE:
				setThermalTransmittance((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT:
				setSolarHeatGainCoefficient((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__SHADING_FACTOR:
				setShadingFactor((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__ID:
				setId(ID_EDEFAULT);
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
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__GLASS_FRACTION:
				return glassFraction != null;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__FRAME_FRACTION:
				return frameFraction != null;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__THERMAL_TRANSMITTANCE:
				return thermalTransmittance != null;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__SOLAR_HEAT_GAIN_COEFFICIENT:
				return solarHeatGainCoefficient != null;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__SHADING_FACTOR:
				return shadingFactor != null;
			case simmatrixPackage.TWINDOW_TYPE_VARIANT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //TWindowTypeVariantImpl
