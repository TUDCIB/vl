/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TConstantTypeVariant;
import de.tudresden.bau.cib.simmatrix.TSetPoint;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TConstant Type Variant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TConstantTypeVariantImpl#getHeatingSetPoint <em>Heating Set Point</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TConstantTypeVariantImpl#getCoolingSetPoint <em>Cooling Set Point</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TConstantTypeVariantImpl#getPersonLoad <em>Person Load</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TConstantTypeVariantImpl#getEquipmentLoad <em>Equipment Load</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TConstantTypeVariantImpl#getShading <em>Shading</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TConstantTypeVariantImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TConstantTypeVariantImpl extends EObjectImpl implements TConstantTypeVariant {
	/**
	 * The cached value of the '{@link #getHeatingSetPoint() <em>Heating Set Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeatingSetPoint()
	 * @generated
	 * @ordered
	 */
	protected TSetPoint heatingSetPoint;

	/**
	 * The cached value of the '{@link #getCoolingSetPoint() <em>Cooling Set Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoolingSetPoint()
	 * @generated
	 * @ordered
	 */
	protected TSetPoint coolingSetPoint;

	/**
	 * The cached value of the '{@link #getPersonLoad() <em>Person Load</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersonLoad()
	 * @generated
	 * @ordered
	 */
	protected TSetPoint personLoad;

	/**
	 * The cached value of the '{@link #getEquipmentLoad() <em>Equipment Load</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEquipmentLoad()
	 * @generated
	 * @ordered
	 */
	protected TSetPoint equipmentLoad;

	/**
	 * The cached value of the '{@link #getShading() <em>Shading</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShading()
	 * @generated
	 * @ordered
	 */
	protected TSetPoint shading;

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
	protected TConstantTypeVariantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TCONSTANT_TYPE_VARIANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetPoint getHeatingSetPoint() {
		return heatingSetPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHeatingSetPoint(TSetPoint newHeatingSetPoint, NotificationChain msgs) {
		TSetPoint oldHeatingSetPoint = heatingSetPoint;
		heatingSetPoint = newHeatingSetPoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT, oldHeatingSetPoint, newHeatingSetPoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeatingSetPoint(TSetPoint newHeatingSetPoint) {
		if (newHeatingSetPoint != heatingSetPoint) {
			NotificationChain msgs = null;
			if (heatingSetPoint != null)
				msgs = ((InternalEObject)heatingSetPoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT, null, msgs);
			if (newHeatingSetPoint != null)
				msgs = ((InternalEObject)newHeatingSetPoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT, null, msgs);
			msgs = basicSetHeatingSetPoint(newHeatingSetPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT, newHeatingSetPoint, newHeatingSetPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetPoint getCoolingSetPoint() {
		return coolingSetPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCoolingSetPoint(TSetPoint newCoolingSetPoint, NotificationChain msgs) {
		TSetPoint oldCoolingSetPoint = coolingSetPoint;
		coolingSetPoint = newCoolingSetPoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT, oldCoolingSetPoint, newCoolingSetPoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoolingSetPoint(TSetPoint newCoolingSetPoint) {
		if (newCoolingSetPoint != coolingSetPoint) {
			NotificationChain msgs = null;
			if (coolingSetPoint != null)
				msgs = ((InternalEObject)coolingSetPoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT, null, msgs);
			if (newCoolingSetPoint != null)
				msgs = ((InternalEObject)newCoolingSetPoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT, null, msgs);
			msgs = basicSetCoolingSetPoint(newCoolingSetPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT, newCoolingSetPoint, newCoolingSetPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetPoint getPersonLoad() {
		return personLoad;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPersonLoad(TSetPoint newPersonLoad, NotificationChain msgs) {
		TSetPoint oldPersonLoad = personLoad;
		personLoad = newPersonLoad;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTANT_TYPE_VARIANT__PERSON_LOAD, oldPersonLoad, newPersonLoad);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersonLoad(TSetPoint newPersonLoad) {
		if (newPersonLoad != personLoad) {
			NotificationChain msgs = null;
			if (personLoad != null)
				msgs = ((InternalEObject)personLoad).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TCONSTANT_TYPE_VARIANT__PERSON_LOAD, null, msgs);
			if (newPersonLoad != null)
				msgs = ((InternalEObject)newPersonLoad).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TCONSTANT_TYPE_VARIANT__PERSON_LOAD, null, msgs);
			msgs = basicSetPersonLoad(newPersonLoad, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTANT_TYPE_VARIANT__PERSON_LOAD, newPersonLoad, newPersonLoad));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetPoint getEquipmentLoad() {
		return equipmentLoad;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEquipmentLoad(TSetPoint newEquipmentLoad, NotificationChain msgs) {
		TSetPoint oldEquipmentLoad = equipmentLoad;
		equipmentLoad = newEquipmentLoad;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD, oldEquipmentLoad, newEquipmentLoad);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEquipmentLoad(TSetPoint newEquipmentLoad) {
		if (newEquipmentLoad != equipmentLoad) {
			NotificationChain msgs = null;
			if (equipmentLoad != null)
				msgs = ((InternalEObject)equipmentLoad).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD, null, msgs);
			if (newEquipmentLoad != null)
				msgs = ((InternalEObject)newEquipmentLoad).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD, null, msgs);
			msgs = basicSetEquipmentLoad(newEquipmentLoad, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD, newEquipmentLoad, newEquipmentLoad));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSetPoint getShading() {
		return shading;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetShading(TSetPoint newShading, NotificationChain msgs) {
		TSetPoint oldShading = shading;
		shading = newShading;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTANT_TYPE_VARIANT__SHADING, oldShading, newShading);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShading(TSetPoint newShading) {
		if (newShading != shading) {
			NotificationChain msgs = null;
			if (shading != null)
				msgs = ((InternalEObject)shading).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TCONSTANT_TYPE_VARIANT__SHADING, null, msgs);
			if (newShading != null)
				msgs = ((InternalEObject)newShading).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TCONSTANT_TYPE_VARIANT__SHADING, null, msgs);
			msgs = basicSetShading(newShading, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTANT_TYPE_VARIANT__SHADING, newShading, newShading));
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
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTANT_TYPE_VARIANT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT:
				return basicSetHeatingSetPoint(null, msgs);
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT:
				return basicSetCoolingSetPoint(null, msgs);
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__PERSON_LOAD:
				return basicSetPersonLoad(null, msgs);
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD:
				return basicSetEquipmentLoad(null, msgs);
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__SHADING:
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
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT:
				return getHeatingSetPoint();
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT:
				return getCoolingSetPoint();
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__PERSON_LOAD:
				return getPersonLoad();
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD:
				return getEquipmentLoad();
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__SHADING:
				return getShading();
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__ID:
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
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT:
				setHeatingSetPoint((TSetPoint)newValue);
				return;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT:
				setCoolingSetPoint((TSetPoint)newValue);
				return;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__PERSON_LOAD:
				setPersonLoad((TSetPoint)newValue);
				return;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD:
				setEquipmentLoad((TSetPoint)newValue);
				return;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__SHADING:
				setShading((TSetPoint)newValue);
				return;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__ID:
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
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT:
				setHeatingSetPoint((TSetPoint)null);
				return;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT:
				setCoolingSetPoint((TSetPoint)null);
				return;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__PERSON_LOAD:
				setPersonLoad((TSetPoint)null);
				return;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD:
				setEquipmentLoad((TSetPoint)null);
				return;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__SHADING:
				setShading((TSetPoint)null);
				return;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__ID:
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
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__HEATING_SET_POINT:
				return heatingSetPoint != null;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__COOLING_SET_POINT:
				return coolingSetPoint != null;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__PERSON_LOAD:
				return personLoad != null;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__EQUIPMENT_LOAD:
				return equipmentLoad != null;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__SHADING:
				return shading != null;
			case simmatrixPackage.TCONSTANT_TYPE_VARIANT__ID:
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

} //TConstantTypeVariantImpl
