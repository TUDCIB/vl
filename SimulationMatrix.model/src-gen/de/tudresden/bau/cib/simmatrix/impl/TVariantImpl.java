/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TVariant;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TVariant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TVariantImpl#getAREF <em>AREF</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TVariantImpl#getVREF <em>VREF</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TVariantImpl extends EObjectImpl implements TVariant {
	/**
	 * The default value of the '{@link #getAREF() <em>AREF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAREF()
	 * @generated
	 * @ordered
	 */
	protected static final String AREF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAREF() <em>AREF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAREF()
	 * @generated
	 * @ordered
	 */
	protected String aREF = AREF_EDEFAULT;

	/**
	 * The default value of the '{@link #getVREF() <em>VREF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVREF()
	 * @generated
	 * @ordered
	 */
	protected static final String VREF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVREF() <em>VREF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVREF()
	 * @generated
	 * @ordered
	 */
	protected String vREF = VREF_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TVariantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TVARIANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAREF() {
		return aREF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAREF(String newAREF) {
		String oldAREF = aREF;
		aREF = newAREF;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIANT__AREF, oldAREF, aREF));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVREF() {
		return vREF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVREF(String newVREF) {
		String oldVREF = vREF;
		vREF = newVREF;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TVARIANT__VREF, oldVREF, vREF));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case simmatrixPackage.TVARIANT__AREF:
				return getAREF();
			case simmatrixPackage.TVARIANT__VREF:
				return getVREF();
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
			case simmatrixPackage.TVARIANT__AREF:
				setAREF((String)newValue);
				return;
			case simmatrixPackage.TVARIANT__VREF:
				setVREF((String)newValue);
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
			case simmatrixPackage.TVARIANT__AREF:
				setAREF(AREF_EDEFAULT);
				return;
			case simmatrixPackage.TVARIANT__VREF:
				setVREF(VREF_EDEFAULT);
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
			case simmatrixPackage.TVARIANT__AREF:
				return AREF_EDEFAULT == null ? aREF != null : !AREF_EDEFAULT.equals(aREF);
			case simmatrixPackage.TVARIANT__VREF:
				return VREF_EDEFAULT == null ? vREF != null : !VREF_EDEFAULT.equals(vREF);
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
		result.append(" (aREF: ");
		result.append(aREF);
		result.append(", vREF: ");
		result.append(vREF);
		result.append(')');
		return result.toString();
	}

} //TVariantImpl
