/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TAssignmentGroups;
import de.tudresden.bau.cib.simmatrix.TCombinations;
import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.simmatrix.TTargets;
import de.tudresden.bau.cib.simmatrix.TVariables;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TSimulation Matrix</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TSimulationMatrixImpl#getTargets <em>Targets</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TSimulationMatrixImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TSimulationMatrixImpl#getAssignmentGroups <em>Assignment Groups</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TSimulationMatrixImpl#getCombinations <em>Combinations</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TSimulationMatrixImpl#getId <em>Id</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TSimulationMatrixImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TSimulationMatrixImpl extends EObjectImpl implements TSimulationMatrix {
	/**
	 * The cached value of the '{@link #getTargets() <em>Targets</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargets()
	 * @generated
	 * @ordered
	 */
	protected TTargets targets;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected TVariables variables;

	/**
	 * The cached value of the '{@link #getAssignmentGroups() <em>Assignment Groups</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignmentGroups()
	 * @generated
	 * @ordered
	 */
	protected TAssignmentGroups assignmentGroups;

	/**
	 * The cached value of the '{@link #getCombinations() <em>Combinations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCombinations()
	 * @generated
	 * @ordered
	 */
	protected TCombinations combinations;

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
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TSimulationMatrixImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TSIMULATION_MATRIX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTargets getTargets() {
		return targets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargets(TTargets newTargets, NotificationChain msgs) {
		TTargets oldTargets = targets;
		targets = newTargets;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSIMULATION_MATRIX__TARGETS, oldTargets, newTargets);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargets(TTargets newTargets) {
		if (newTargets != targets) {
			NotificationChain msgs = null;
			if (targets != null)
				msgs = ((InternalEObject)targets).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSIMULATION_MATRIX__TARGETS, null, msgs);
			if (newTargets != null)
				msgs = ((InternalEObject)newTargets).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSIMULATION_MATRIX__TARGETS, null, msgs);
			msgs = basicSetTargets(newTargets, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSIMULATION_MATRIX__TARGETS, newTargets, newTargets));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TVariables getVariables() {
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariables(TVariables newVariables, NotificationChain msgs) {
		TVariables oldVariables = variables;
		variables = newVariables;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSIMULATION_MATRIX__VARIABLES, oldVariables, newVariables);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariables(TVariables newVariables) {
		if (newVariables != variables) {
			NotificationChain msgs = null;
			if (variables != null)
				msgs = ((InternalEObject)variables).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSIMULATION_MATRIX__VARIABLES, null, msgs);
			if (newVariables != null)
				msgs = ((InternalEObject)newVariables).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSIMULATION_MATRIX__VARIABLES, null, msgs);
			msgs = basicSetVariables(newVariables, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSIMULATION_MATRIX__VARIABLES, newVariables, newVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAssignmentGroups getAssignmentGroups() {
		return assignmentGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssignmentGroups(TAssignmentGroups newAssignmentGroups, NotificationChain msgs) {
		TAssignmentGroups oldAssignmentGroups = assignmentGroups;
		assignmentGroups = newAssignmentGroups;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSIMULATION_MATRIX__ASSIGNMENT_GROUPS, oldAssignmentGroups, newAssignmentGroups);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignmentGroups(TAssignmentGroups newAssignmentGroups) {
		if (newAssignmentGroups != assignmentGroups) {
			NotificationChain msgs = null;
			if (assignmentGroups != null)
				msgs = ((InternalEObject)assignmentGroups).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSIMULATION_MATRIX__ASSIGNMENT_GROUPS, null, msgs);
			if (newAssignmentGroups != null)
				msgs = ((InternalEObject)newAssignmentGroups).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSIMULATION_MATRIX__ASSIGNMENT_GROUPS, null, msgs);
			msgs = basicSetAssignmentGroups(newAssignmentGroups, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSIMULATION_MATRIX__ASSIGNMENT_GROUPS, newAssignmentGroups, newAssignmentGroups));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCombinations getCombinations() {
		return combinations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCombinations(TCombinations newCombinations, NotificationChain msgs) {
		TCombinations oldCombinations = combinations;
		combinations = newCombinations;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSIMULATION_MATRIX__COMBINATIONS, oldCombinations, newCombinations);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCombinations(TCombinations newCombinations) {
		if (newCombinations != combinations) {
			NotificationChain msgs = null;
			if (combinations != null)
				msgs = ((InternalEObject)combinations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSIMULATION_MATRIX__COMBINATIONS, null, msgs);
			if (newCombinations != null)
				msgs = ((InternalEObject)newCombinations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TSIMULATION_MATRIX__COMBINATIONS, null, msgs);
			msgs = basicSetCombinations(newCombinations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSIMULATION_MATRIX__COMBINATIONS, newCombinations, newCombinations));
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
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSIMULATION_MATRIX__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TSIMULATION_MATRIX__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TSIMULATION_MATRIX__TARGETS:
				return basicSetTargets(null, msgs);
			case simmatrixPackage.TSIMULATION_MATRIX__VARIABLES:
				return basicSetVariables(null, msgs);
			case simmatrixPackage.TSIMULATION_MATRIX__ASSIGNMENT_GROUPS:
				return basicSetAssignmentGroups(null, msgs);
			case simmatrixPackage.TSIMULATION_MATRIX__COMBINATIONS:
				return basicSetCombinations(null, msgs);
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
			case simmatrixPackage.TSIMULATION_MATRIX__TARGETS:
				return getTargets();
			case simmatrixPackage.TSIMULATION_MATRIX__VARIABLES:
				return getVariables();
			case simmatrixPackage.TSIMULATION_MATRIX__ASSIGNMENT_GROUPS:
				return getAssignmentGroups();
			case simmatrixPackage.TSIMULATION_MATRIX__COMBINATIONS:
				return getCombinations();
			case simmatrixPackage.TSIMULATION_MATRIX__ID:
				return getId();
			case simmatrixPackage.TSIMULATION_MATRIX__TYPE:
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
			case simmatrixPackage.TSIMULATION_MATRIX__TARGETS:
				setTargets((TTargets)newValue);
				return;
			case simmatrixPackage.TSIMULATION_MATRIX__VARIABLES:
				setVariables((TVariables)newValue);
				return;
			case simmatrixPackage.TSIMULATION_MATRIX__ASSIGNMENT_GROUPS:
				setAssignmentGroups((TAssignmentGroups)newValue);
				return;
			case simmatrixPackage.TSIMULATION_MATRIX__COMBINATIONS:
				setCombinations((TCombinations)newValue);
				return;
			case simmatrixPackage.TSIMULATION_MATRIX__ID:
				setId((String)newValue);
				return;
			case simmatrixPackage.TSIMULATION_MATRIX__TYPE:
				setType((String)newValue);
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
			case simmatrixPackage.TSIMULATION_MATRIX__TARGETS:
				setTargets((TTargets)null);
				return;
			case simmatrixPackage.TSIMULATION_MATRIX__VARIABLES:
				setVariables((TVariables)null);
				return;
			case simmatrixPackage.TSIMULATION_MATRIX__ASSIGNMENT_GROUPS:
				setAssignmentGroups((TAssignmentGroups)null);
				return;
			case simmatrixPackage.TSIMULATION_MATRIX__COMBINATIONS:
				setCombinations((TCombinations)null);
				return;
			case simmatrixPackage.TSIMULATION_MATRIX__ID:
				setId(ID_EDEFAULT);
				return;
			case simmatrixPackage.TSIMULATION_MATRIX__TYPE:
				setType(TYPE_EDEFAULT);
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
			case simmatrixPackage.TSIMULATION_MATRIX__TARGETS:
				return targets != null;
			case simmatrixPackage.TSIMULATION_MATRIX__VARIABLES:
				return variables != null;
			case simmatrixPackage.TSIMULATION_MATRIX__ASSIGNMENT_GROUPS:
				return assignmentGroups != null;
			case simmatrixPackage.TSIMULATION_MATRIX__COMBINATIONS:
				return combinations != null;
			case simmatrixPackage.TSIMULATION_MATRIX__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case simmatrixPackage.TSIMULATION_MATRIX__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
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
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //TSimulationMatrixImpl
