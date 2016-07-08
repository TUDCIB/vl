/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TAssignmentGroups;
import de.tudresden.bau.cib.simmatrix.TElements;
import de.tudresden.bau.cib.simmatrix.TSpaces;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TAssignment Groups</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TAssignmentGroupsImpl#getSpaces <em>Spaces</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TAssignmentGroupsImpl#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TAssignmentGroupsImpl extends EObjectImpl implements TAssignmentGroups {
	/**
	 * The cached value of the '{@link #getSpaces() <em>Spaces</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpaces()
	 * @generated
	 * @ordered
	 */
	protected TSpaces spaces;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected TElements elements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TAssignmentGroupsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TASSIGNMENT_GROUPS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSpaces getSpaces() {
		return spaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpaces(TSpaces newSpaces, NotificationChain msgs) {
		TSpaces oldSpaces = spaces;
		spaces = newSpaces;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TASSIGNMENT_GROUPS__SPACES, oldSpaces, newSpaces);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpaces(TSpaces newSpaces) {
		if (newSpaces != spaces) {
			NotificationChain msgs = null;
			if (spaces != null)
				msgs = ((InternalEObject)spaces).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TASSIGNMENT_GROUPS__SPACES, null, msgs);
			if (newSpaces != null)
				msgs = ((InternalEObject)newSpaces).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TASSIGNMENT_GROUPS__SPACES, null, msgs);
			msgs = basicSetSpaces(newSpaces, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TASSIGNMENT_GROUPS__SPACES, newSpaces, newSpaces));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TElements getElements() {
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElements(TElements newElements, NotificationChain msgs) {
		TElements oldElements = elements;
		elements = newElements;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TASSIGNMENT_GROUPS__ELEMENTS, oldElements, newElements);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElements(TElements newElements) {
		if (newElements != elements) {
			NotificationChain msgs = null;
			if (elements != null)
				msgs = ((InternalEObject)elements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TASSIGNMENT_GROUPS__ELEMENTS, null, msgs);
			if (newElements != null)
				msgs = ((InternalEObject)newElements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TASSIGNMENT_GROUPS__ELEMENTS, null, msgs);
			msgs = basicSetElements(newElements, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TASSIGNMENT_GROUPS__ELEMENTS, newElements, newElements));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TASSIGNMENT_GROUPS__SPACES:
				return basicSetSpaces(null, msgs);
			case simmatrixPackage.TASSIGNMENT_GROUPS__ELEMENTS:
				return basicSetElements(null, msgs);
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
			case simmatrixPackage.TASSIGNMENT_GROUPS__SPACES:
				return getSpaces();
			case simmatrixPackage.TASSIGNMENT_GROUPS__ELEMENTS:
				return getElements();
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
			case simmatrixPackage.TASSIGNMENT_GROUPS__SPACES:
				setSpaces((TSpaces)newValue);
				return;
			case simmatrixPackage.TASSIGNMENT_GROUPS__ELEMENTS:
				setElements((TElements)newValue);
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
			case simmatrixPackage.TASSIGNMENT_GROUPS__SPACES:
				setSpaces((TSpaces)null);
				return;
			case simmatrixPackage.TASSIGNMENT_GROUPS__ELEMENTS:
				setElements((TElements)null);
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
			case simmatrixPackage.TASSIGNMENT_GROUPS__SPACES:
				return spaces != null;
			case simmatrixPackage.TASSIGNMENT_GROUPS__ELEMENTS:
				return elements != null;
		}
		return super.eIsSet(featureID);
	}

} //TAssignmentGroupsImpl
