/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.OrientationSide;
import de.tudresden.bau.cib.simmatrix.TConstructionType;
import de.tudresden.bau.cib.simmatrix.TConstructionTypeVariant;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TConstruction Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TConstructionTypeImpl#getConstructionTypeVariant <em>Construction Type Variant</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TConstructionTypeImpl#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TConstructionTypeImpl#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TConstructionTypeImpl extends EObjectImpl implements TConstructionType {
	/**
	 * The cached value of the '{@link #getConstructionTypeVariant() <em>Construction Type Variant</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstructionTypeVariant()
	 * @generated
	 * @ordered
	 */
	protected EList<TConstructionTypeVariant> constructionTypeVariant;

	/**
	 * The default value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrientation()
	 * @generated
	 * @ordered
	 */
	protected static final OrientationSide ORIENTATION_EDEFAULT = OrientationSide.A;

	/**
	 * The cached value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrientation()
	 * @generated
	 * @ordered
	 */
	protected OrientationSide orientation = ORIENTATION_EDEFAULT;

	/**
	 * This is true if the Orientation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean orientationESet;

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
	protected TConstructionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TCONSTRUCTION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TConstructionTypeVariant> getConstructionTypeVariant() {
		if (constructionTypeVariant == null) {
			constructionTypeVariant = new EObjectContainmentEList<TConstructionTypeVariant>(TConstructionTypeVariant.class, this, simmatrixPackage.TCONSTRUCTION_TYPE__CONSTRUCTION_TYPE_VARIANT);
		}
		return constructionTypeVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrientationSide getOrientation() {
		return orientation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrientation(OrientationSide newOrientation) {
		OrientationSide oldOrientation = orientation;
		orientation = newOrientation == null ? ORIENTATION_EDEFAULT : newOrientation;
		boolean oldOrientationESet = orientationESet;
		orientationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTRUCTION_TYPE__ORIENTATION, oldOrientation, orientation, !oldOrientationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetOrientation() {
		OrientationSide oldOrientation = orientation;
		boolean oldOrientationESet = orientationESet;
		orientation = ORIENTATION_EDEFAULT;
		orientationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, simmatrixPackage.TCONSTRUCTION_TYPE__ORIENTATION, oldOrientation, ORIENTATION_EDEFAULT, oldOrientationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetOrientation() {
		return orientationESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TCONSTRUCTION_TYPE__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TCONSTRUCTION_TYPE__CONSTRUCTION_TYPE_VARIANT:
				return ((InternalEList<?>)getConstructionTypeVariant()).basicRemove(otherEnd, msgs);
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
			case simmatrixPackage.TCONSTRUCTION_TYPE__CONSTRUCTION_TYPE_VARIANT:
				return getConstructionTypeVariant();
			case simmatrixPackage.TCONSTRUCTION_TYPE__ORIENTATION:
				return getOrientation();
			case simmatrixPackage.TCONSTRUCTION_TYPE__SOURCE:
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
			case simmatrixPackage.TCONSTRUCTION_TYPE__CONSTRUCTION_TYPE_VARIANT:
				getConstructionTypeVariant().clear();
				getConstructionTypeVariant().addAll((Collection<? extends TConstructionTypeVariant>)newValue);
				return;
			case simmatrixPackage.TCONSTRUCTION_TYPE__ORIENTATION:
				setOrientation((OrientationSide)newValue);
				return;
			case simmatrixPackage.TCONSTRUCTION_TYPE__SOURCE:
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
			case simmatrixPackage.TCONSTRUCTION_TYPE__CONSTRUCTION_TYPE_VARIANT:
				getConstructionTypeVariant().clear();
				return;
			case simmatrixPackage.TCONSTRUCTION_TYPE__ORIENTATION:
				unsetOrientation();
				return;
			case simmatrixPackage.TCONSTRUCTION_TYPE__SOURCE:
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
			case simmatrixPackage.TCONSTRUCTION_TYPE__CONSTRUCTION_TYPE_VARIANT:
				return constructionTypeVariant != null && !constructionTypeVariant.isEmpty();
			case simmatrixPackage.TCONSTRUCTION_TYPE__ORIENTATION:
				return isSetOrientation();
			case simmatrixPackage.TCONSTRUCTION_TYPE__SOURCE:
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
		result.append(" (orientation: ");
		if (orientationESet) result.append(orientation); else result.append("<unset>");
		result.append(", source: ");
		result.append(source);
		result.append(')');
		return result.toString();
	}

} //TConstructionTypeImpl
