/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TMaterialType;
import de.tudresden.bau.cib.simmatrix.TMaterialTypes;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TMaterial Types</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypesImpl#getMaterialType <em>Material Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TMaterialTypesImpl extends EObjectImpl implements TMaterialTypes {
	/**
	 * The cached value of the '{@link #getMaterialType() <em>Material Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaterialType()
	 * @generated
	 * @ordered
	 */
	protected EList<TMaterialType> materialType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TMaterialTypesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TMATERIAL_TYPES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TMaterialType> getMaterialType() {
		if (materialType == null) {
			materialType = new EObjectContainmentEList<TMaterialType>(TMaterialType.class, this, simmatrixPackage.TMATERIAL_TYPES__MATERIAL_TYPE);
		}
		return materialType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TMATERIAL_TYPES__MATERIAL_TYPE:
				return ((InternalEList<?>)getMaterialType()).basicRemove(otherEnd, msgs);
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
			case simmatrixPackage.TMATERIAL_TYPES__MATERIAL_TYPE:
				return getMaterialType();
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
			case simmatrixPackage.TMATERIAL_TYPES__MATERIAL_TYPE:
				getMaterialType().clear();
				getMaterialType().addAll((Collection<? extends TMaterialType>)newValue);
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
			case simmatrixPackage.TMATERIAL_TYPES__MATERIAL_TYPE:
				getMaterialType().clear();
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
			case simmatrixPackage.TMATERIAL_TYPES__MATERIAL_TYPE:
				return materialType != null && !materialType.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TMaterialTypesImpl
