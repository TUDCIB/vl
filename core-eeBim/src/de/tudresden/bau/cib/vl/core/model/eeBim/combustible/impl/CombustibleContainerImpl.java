/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.combustible.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleContainerImpl#getCombustibles <em>Combustibles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CombustibleContainerImpl extends EObjectImpl implements CombustibleContainer {
	/**
	 * The cached value of the '{@link #getCombustibles() <em>Combustibles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCombustibles()
	 * @generated
	 * @ordered
	 */
	protected EList<Combustible> combustibles;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CombustibleContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CombustiblePackage.Literals.COMBUSTIBLE_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Combustible> getCombustibles() {
		if (combustibles == null) {
			combustibles = new EObjectContainmentEList<Combustible>(Combustible.class, this, CombustiblePackage.COMBUSTIBLE_CONTAINER__COMBUSTIBLES);
		}
		return combustibles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CombustiblePackage.COMBUSTIBLE_CONTAINER__COMBUSTIBLES:
				return ((InternalEList<?>)getCombustibles()).basicRemove(otherEnd, msgs);
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
			case CombustiblePackage.COMBUSTIBLE_CONTAINER__COMBUSTIBLES:
				return getCombustibles();
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
			case CombustiblePackage.COMBUSTIBLE_CONTAINER__COMBUSTIBLES:
				getCombustibles().clear();
				getCombustibles().addAll((Collection<? extends Combustible>)newValue);
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
			case CombustiblePackage.COMBUSTIBLE_CONTAINER__COMBUSTIBLES:
				getCombustibles().clear();
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
			case CombustiblePackage.COMBUSTIBLE_CONTAINER__COMBUSTIBLES:
				return combustibles != null && !combustibles.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CombustibleContainerImpl
