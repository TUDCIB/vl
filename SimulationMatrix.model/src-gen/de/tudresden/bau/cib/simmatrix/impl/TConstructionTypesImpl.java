/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TConstructionType;
import de.tudresden.bau.cib.simmatrix.TConstructionTypes;
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
 * An implementation of the model object '<em><b>TConstruction Types</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TConstructionTypesImpl#getConstructionType <em>Construction Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TConstructionTypesImpl extends EObjectImpl implements TConstructionTypes {
	/**
	 * The cached value of the '{@link #getConstructionType() <em>Construction Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstructionType()
	 * @generated
	 * @ordered
	 */
	protected EList<TConstructionType> constructionType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TConstructionTypesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TCONSTRUCTION_TYPES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TConstructionType> getConstructionType() {
		if (constructionType == null) {
			constructionType = new EObjectContainmentEList<TConstructionType>(TConstructionType.class, this, simmatrixPackage.TCONSTRUCTION_TYPES__CONSTRUCTION_TYPE);
		}
		return constructionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TCONSTRUCTION_TYPES__CONSTRUCTION_TYPE:
				return ((InternalEList<?>)getConstructionType()).basicRemove(otherEnd, msgs);
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
			case simmatrixPackage.TCONSTRUCTION_TYPES__CONSTRUCTION_TYPE:
				return getConstructionType();
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
			case simmatrixPackage.TCONSTRUCTION_TYPES__CONSTRUCTION_TYPE:
				getConstructionType().clear();
				getConstructionType().addAll((Collection<? extends TConstructionType>)newValue);
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
			case simmatrixPackage.TCONSTRUCTION_TYPES__CONSTRUCTION_TYPE:
				getConstructionType().clear();
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
			case simmatrixPackage.TCONSTRUCTION_TYPES__CONSTRUCTION_TYPE:
				return constructionType != null && !constructionType.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TConstructionTypesImpl
