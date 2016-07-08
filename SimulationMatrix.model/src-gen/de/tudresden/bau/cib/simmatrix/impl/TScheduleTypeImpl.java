/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TConstantTypeVariant;
import de.tudresden.bau.cib.simmatrix.TScheduleType;
import de.tudresden.bau.cib.simmatrix.TScheduleTypeVariant;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TSchedule Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeImpl#getScheduleTypeVariant <em>Schedule Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeImpl#getConstantTypeVariant <em>Constant Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TScheduleTypeImpl#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TScheduleTypeImpl extends EObjectImpl implements TScheduleType {
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
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected String source = SOURCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TScheduleTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TSCHEDULE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, simmatrixPackage.TSCHEDULE_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TScheduleTypeVariant> getScheduleTypeVariant() {
		return getGroup().list(simmatrixPackage.Literals.TSCHEDULE_TYPE__SCHEDULE_TYPE_VARIANT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TConstantTypeVariant> getConstantTypeVariant() {
		return getGroup().list(simmatrixPackage.Literals.TSCHEDULE_TYPE__CONSTANT_TYPE_VARIANT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(String newSource) {
		String oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSCHEDULE_TYPE__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TSCHEDULE_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case simmatrixPackage.TSCHEDULE_TYPE__SCHEDULE_TYPE_VARIANT:
				return ((InternalEList<?>)getScheduleTypeVariant()).basicRemove(otherEnd, msgs);
			case simmatrixPackage.TSCHEDULE_TYPE__CONSTANT_TYPE_VARIANT:
				return ((InternalEList<?>)getConstantTypeVariant()).basicRemove(otherEnd, msgs);
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
			case simmatrixPackage.TSCHEDULE_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case simmatrixPackage.TSCHEDULE_TYPE__SCHEDULE_TYPE_VARIANT:
				return getScheduleTypeVariant();
			case simmatrixPackage.TSCHEDULE_TYPE__CONSTANT_TYPE_VARIANT:
				return getConstantTypeVariant();
			case simmatrixPackage.TSCHEDULE_TYPE__SOURCE:
				return getSource();
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
			case simmatrixPackage.TSCHEDULE_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE__SCHEDULE_TYPE_VARIANT:
				getScheduleTypeVariant().clear();
				getScheduleTypeVariant().addAll((Collection<? extends TScheduleTypeVariant>)newValue);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE__CONSTANT_TYPE_VARIANT:
				getConstantTypeVariant().clear();
				getConstantTypeVariant().addAll((Collection<? extends TConstantTypeVariant>)newValue);
				return;
			case simmatrixPackage.TSCHEDULE_TYPE__SOURCE:
				setSource((String)newValue);
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
			case simmatrixPackage.TSCHEDULE_TYPE__GROUP:
				getGroup().clear();
				return;
			case simmatrixPackage.TSCHEDULE_TYPE__SCHEDULE_TYPE_VARIANT:
				getScheduleTypeVariant().clear();
				return;
			case simmatrixPackage.TSCHEDULE_TYPE__CONSTANT_TYPE_VARIANT:
				getConstantTypeVariant().clear();
				return;
			case simmatrixPackage.TSCHEDULE_TYPE__SOURCE:
				setSource(SOURCE_EDEFAULT);
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
			case simmatrixPackage.TSCHEDULE_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case simmatrixPackage.TSCHEDULE_TYPE__SCHEDULE_TYPE_VARIANT:
				return !getScheduleTypeVariant().isEmpty();
			case simmatrixPackage.TSCHEDULE_TYPE__CONSTANT_TYPE_VARIANT:
				return !getConstantTypeVariant().isEmpty();
			case simmatrixPackage.TSCHEDULE_TYPE__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
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
		result.append(", source: ");
		result.append(source);
		result.append(')');
		return result.toString();
	}

} //TScheduleTypeImpl
