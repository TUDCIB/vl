/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant;
import de.tudresden.bau.cib.simmatrix.TSetPersonLoads;
import de.tudresden.bau.cib.simmatrix.TSetShading;
import de.tudresden.bau.cib.simmatrix.TSetTemperature;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TSchedule Type Variant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeVariantImpl#getHeatingSetPoint <em>Heating Set Point</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeVariantImpl#getCoolingSetPoint <em>Cooling Set Point</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeVariantImpl#getPersonLoad <em>Person Load</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeVariantImpl#getEquipmentLoad <em>Equipment Load</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeVariantImpl#getShading <em>Shading</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeVariantImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TScheduleTypeVariantImpl extends EObjectImpl implements TScheduleTypeVariant {
	/**
	 * The cached value of the '{@link #getHeatingSetPoint() <em>Heating Set Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeatingSetPoint()
	 * @generated
	 * @ordered
	 */
	protected TSetTemperature heatingSetPoint;

	/**
	 * The cached value of the '{@link #getCoolingSetPoint() <em>Cooling Set Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoolingSetPoint()
	 * @generated
	 * @ordered
	 */
	protected TSetTemperature coolingSetPoint;

	/**
	 * The cached value of the '{@link #getPersonLoad() <em>Person Load</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersonLoad()
	 * @generated
	 * @ordered
	 */
	protected TSetPersonLoads personLoad;

	/**
	 * The cached value of the '{@link #getEquipmentLoad() <em>Equipment Load</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEquipmentLoad()
	 * @generated
	 * @ordered
	 */
	protected TSetPersonLoads equipmentLoad;

	/**
	 * The cached value of the '{@link #getShading() <em>Shading</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShading()
	 * @generated
	 * @ordered
	 */
	protected TSetShading shading;

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
	protected TScheduleTypeVariantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TSCHEDULE_TYPE_VARIANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetTemperature getHeatingSetPoint() {
		return heatingSetPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHeatingSetPoint(TSetTemperature newHeatingSetPoint, NotificationChain msgs) {
		TSetTemperature oldHeatingSetPoint = heatingSetPoint;
		heatingSetPoint = newHeatingSetPoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT, oldHeatingSetPoint, newHeatingSetPoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeatingSetPoint(TSetTemperature newHeatingSetPoint) {
		if (newHeatingSetPoint != heatingSetPoint) {
			NotificationChain msgs = null;
			if (heatingSetPoint != null)
				msgs = ((InternalEObject)heatingSetPoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT, null, msgs);
			if (newHeatingSetPoint != null)
				msgs = ((InternalEObject)newHeatingSetPoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT, null, msgs);
			msgs = basicSetHeatingSetPoint(newHeatingSetPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT, newHeatingSetPoint, newHeatingSetPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetTemperature getCoolingSetPoint() {
		return coolingSetPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCoolingSetPoint(TSetTemperature newCoolingSetPoint, NotificationChain msgs) {
		TSetTemperature oldCoolingSetPoint = coolingSetPoint;
		coolingSetPoint = newCoolingSetPoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT, oldCoolingSetPoint, newCoolingSetPoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoolingSetPoint(TSetTemperature newCoolingSetPoint) {
		if (newCoolingSetPoint != coolingSetPoint) {
			NotificationChain msgs = null;
			if (coolingSetPoint != null)
				msgs = ((InternalEObject)coolingSetPoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT, null, msgs);
			if (newCoolingSetPoint != null)
				msgs = ((InternalEObject)newCoolingSetPoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT, null, msgs);
			msgs = basicSetCoolingSetPoint(newCoolingSetPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT, newCoolingSetPoint, newCoolingSetPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetPersonLoads getPersonLoad() {
		return personLoad;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPersonLoad(TSetPersonLoads newPersonLoad, NotificationChain msgs) {
		TSetPersonLoads oldPersonLoad = personLoad;
		personLoad = newPersonLoad;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE_VARIANT__PERSON_LOAD, oldPersonLoad, newPersonLoad);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersonLoad(TSetPersonLoads newPersonLoad) {
		if (newPersonLoad != personLoad) {
			NotificationChain msgs = null;
			if (personLoad != null)
				msgs = ((InternalEObject)personLoad).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSCHEDULE_TYPE_VARIANT__PERSON_LOAD, null, msgs);
			if (newPersonLoad != null)
				msgs = ((InternalEObject)newPersonLoad).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSCHEDULE_TYPE_VARIANT__PERSON_LOAD, null, msgs);
			msgs = basicSetPersonLoad(newPersonLoad, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE_VARIANT__PERSON_LOAD, newPersonLoad, newPersonLoad));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetPersonLoads getEquipmentLoad() {
		return equipmentLoad;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEquipmentLoad(TSetPersonLoads newEquipmentLoad, NotificationChain msgs) {
		TSetPersonLoads oldEquipmentLoad = equipmentLoad;
		equipmentLoad = newEquipmentLoad;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD, oldEquipmentLoad, newEquipmentLoad);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEquipmentLoad(TSetPersonLoads newEquipmentLoad) {
		if (newEquipmentLoad != equipmentLoad) {
			NotificationChain msgs = null;
			if (equipmentLoad != null)
				msgs = ((InternalEObject)equipmentLoad).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD, null, msgs);
			if (newEquipmentLoad != null)
				msgs = ((InternalEObject)newEquipmentLoad).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD, null, msgs);
			msgs = basicSetEquipmentLoad(newEquipmentLoad, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD, newEquipmentLoad, newEquipmentLoad));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetShading getShading() {
		return shading;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetShading(TSetShading newShading, NotificationChain msgs) {
		TSetShading oldShading = shading;
		shading = newShading;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE_VARIANT__SHADING, oldShading, newShading);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShading(TSetShading newShading) {
		if (newShading != shading) {
			NotificationChain msgs = null;
			if (shading != null)
				msgs = ((InternalEObject)shading).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSCHEDULE_TYPE_VARIANT__SHADING, null, msgs);
			if (newShading != null)
				msgs = ((InternalEObject)newShading).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSCHEDULE_TYPE_VARIANT__SHADING, null, msgs);
			msgs = basicSetShading(newShading, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE_VARIANT__SHADING, newShading, newShading));
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
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE_VARIANT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT:
				return basicSetHeatingSetPoint(null, msgs);
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT:
				return basicSetCoolingSetPoint(null, msgs);
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__PERSON_LOAD:
				return basicSetPersonLoad(null, msgs);
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD:
				return basicSetEquipmentLoad(null, msgs);
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__SHADING:
				return basicSetShading(null, msgs);
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
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT:
				return getHeatingSetPoint();
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT:
				return getCoolingSetPoint();
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__PERSON_LOAD:
				return getPersonLoad();
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD:
				return getEquipmentLoad();
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__SHADING:
				return getShading();
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__ID:
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
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT:
				setHeatingSetPoint((TSetTemperature)newValue);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT:
				setCoolingSetPoint((TSetTemperature)newValue);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__PERSON_LOAD:
				setPersonLoad((TSetPersonLoads)newValue);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD:
				setEquipmentLoad((TSetPersonLoads)newValue);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__SHADING:
				setShading((TSetShading)newValue);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__ID:
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
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT:
				setHeatingSetPoint((TSetTemperature)null);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT:
				setCoolingSetPoint((TSetTemperature)null);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__PERSON_LOAD:
				setPersonLoad((TSetPersonLoads)null);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD:
				setEquipmentLoad((TSetPersonLoads)null);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__SHADING:
				setShading((TSetShading)null);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__ID:
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
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__HEATING_SET_POINT:
				return heatingSetPoint != null;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__COOLING_SET_POINT:
				return coolingSetPoint != null;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__PERSON_LOAD:
				return personLoad != null;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__EQUIPMENT_LOAD:
				return equipmentLoad != null;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__SHADING:
				return shading != null;
			case simmatrixPackage.TSCHEDULE_TYPE_VARIANT__ID:
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

} //TScheduleTypeVariantImpl
