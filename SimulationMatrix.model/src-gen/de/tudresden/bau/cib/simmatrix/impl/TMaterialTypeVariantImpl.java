/**
 */
package de.tudresden.bau.cib.simmatrix.impl;

import de.tudresden.bau.cib.simmatrix.TFloatWithUnits;
import de.tudresden.bau.cib.simmatrix.TMaterialTypeVariant;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TMaterial Type Variant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getDensity <em>Density</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getSpecificHeatCapacity <em>Specific Heat Capacity</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getConductivity <em>Conductivity</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getWaterVaporDiffusionResistanceFactor <em>Water Vapor Diffusion Resistance Factor</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getWaterAbsorptionCapacity <em>Water Absorption Capacity</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getOpenPorosity <em>Open Porosity</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getEffectiveSaturation <em>Effective Saturation</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getCapillarySaturationContent <em>Capillary Saturation Content</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getHygroscopicSorption <em>Hygroscopic Sorption</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getThermalConductivity <em>Thermal Conductivity</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getLiquidWaterConductivityEffectiveSaturation <em>Liquid Water Conductivity Effective Saturation</em>}</li>
 *   <li>{@link de.tudresden.bau.cib.simmatrix.impl.TMaterialTypeVariantImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TMaterialTypeVariantImpl extends EObjectImpl implements TMaterialTypeVariant {
	/**
	 * The cached value of the '{@link #getDensity() <em>Density</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDensity()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits density;

	/**
	 * The cached value of the '{@link #getSpecificHeatCapacity() <em>Specific Heat Capacity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecificHeatCapacity()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits specificHeatCapacity;

	/**
	 * The cached value of the '{@link #getConductivity() <em>Conductivity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConductivity()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits conductivity;

	/**
	 * The cached value of the '{@link #getWaterVaporDiffusionResistanceFactor() <em>Water Vapor Diffusion Resistance Factor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaterVaporDiffusionResistanceFactor()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits waterVaporDiffusionResistanceFactor;

	/**
	 * The cached value of the '{@link #getWaterAbsorptionCapacity() <em>Water Absorption Capacity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaterAbsorptionCapacity()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits waterAbsorptionCapacity;

	/**
	 * The cached value of the '{@link #getOpenPorosity() <em>Open Porosity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpenPorosity()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits openPorosity;

	/**
	 * The cached value of the '{@link #getEffectiveSaturation() <em>Effective Saturation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEffectiveSaturation()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits effectiveSaturation;

	/**
	 * The cached value of the '{@link #getCapillarySaturationContent() <em>Capillary Saturation Content</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapillarySaturationContent()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits capillarySaturationContent;

	/**
	 * The cached value of the '{@link #getHygroscopicSorption() <em>Hygroscopic Sorption</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHygroscopicSorption()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits hygroscopicSorption;

	/**
	 * The cached value of the '{@link #getThermalConductivity() <em>Thermal Conductivity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThermalConductivity()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits thermalConductivity;

	/**
	 * The cached value of the '{@link #getLiquidWaterConductivityEffectiveSaturation() <em>Liquid Water Conductivity Effective Saturation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLiquidWaterConductivityEffectiveSaturation()
	 * @generated
	 * @ordered
	 */
	protected TFloatWithUnits liquidWaterConductivityEffectiveSaturation;

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
	protected TMaterialTypeVariantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return simmatrixPackage.Literals.TMATERIAL_TYPE_VARIANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getDensity() {
		return density;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDensity(TFloatWithUnits newDensity, NotificationChain msgs) {
		TFloatWithUnits oldDensity = density;
		density = newDensity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__DENSITY, oldDensity, newDensity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDensity(TFloatWithUnits newDensity) {
		if (newDensity != density) {
			NotificationChain msgs = null;
			if (density != null)
				msgs = ((InternalEObject)density).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__DENSITY, null, msgs);
			if (newDensity != null)
				msgs = ((InternalEObject)newDensity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__DENSITY, null, msgs);
			msgs = basicSetDensity(newDensity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__DENSITY, newDensity, newDensity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getSpecificHeatCapacity() {
		return specificHeatCapacity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpecificHeatCapacity(TFloatWithUnits newSpecificHeatCapacity, NotificationChain msgs) {
		TFloatWithUnits oldSpecificHeatCapacity = specificHeatCapacity;
		specificHeatCapacity = newSpecificHeatCapacity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY, oldSpecificHeatCapacity, newSpecificHeatCapacity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecificHeatCapacity(TFloatWithUnits newSpecificHeatCapacity) {
		if (newSpecificHeatCapacity != specificHeatCapacity) {
			NotificationChain msgs = null;
			if (specificHeatCapacity != null)
				msgs = ((InternalEObject)specificHeatCapacity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY, null, msgs);
			if (newSpecificHeatCapacity != null)
				msgs = ((InternalEObject)newSpecificHeatCapacity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY, null, msgs);
			msgs = basicSetSpecificHeatCapacity(newSpecificHeatCapacity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY, newSpecificHeatCapacity, newSpecificHeatCapacity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getConductivity() {
		return conductivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConductivity(TFloatWithUnits newConductivity, NotificationChain msgs) {
		TFloatWithUnits oldConductivity = conductivity;
		conductivity = newConductivity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__CONDUCTIVITY, oldConductivity, newConductivity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConductivity(TFloatWithUnits newConductivity) {
		if (newConductivity != conductivity) {
			NotificationChain msgs = null;
			if (conductivity != null)
				msgs = ((InternalEObject)conductivity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__CONDUCTIVITY, null, msgs);
			if (newConductivity != null)
				msgs = ((InternalEObject)newConductivity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__CONDUCTIVITY, null, msgs);
			msgs = basicSetConductivity(newConductivity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__CONDUCTIVITY, newConductivity, newConductivity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getWaterVaporDiffusionResistanceFactor() {
		return waterVaporDiffusionResistanceFactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWaterVaporDiffusionResistanceFactor(TFloatWithUnits newWaterVaporDiffusionResistanceFactor, NotificationChain msgs) {
		TFloatWithUnits oldWaterVaporDiffusionResistanceFactor = waterVaporDiffusionResistanceFactor;
		waterVaporDiffusionResistanceFactor = newWaterVaporDiffusionResistanceFactor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR, oldWaterVaporDiffusionResistanceFactor, newWaterVaporDiffusionResistanceFactor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWaterVaporDiffusionResistanceFactor(TFloatWithUnits newWaterVaporDiffusionResistanceFactor) {
		if (newWaterVaporDiffusionResistanceFactor != waterVaporDiffusionResistanceFactor) {
			NotificationChain msgs = null;
			if (waterVaporDiffusionResistanceFactor != null)
				msgs = ((InternalEObject)waterVaporDiffusionResistanceFactor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR, null, msgs);
			if (newWaterVaporDiffusionResistanceFactor != null)
				msgs = ((InternalEObject)newWaterVaporDiffusionResistanceFactor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR, null, msgs);
			msgs = basicSetWaterVaporDiffusionResistanceFactor(newWaterVaporDiffusionResistanceFactor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR, newWaterVaporDiffusionResistanceFactor, newWaterVaporDiffusionResistanceFactor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getWaterAbsorptionCapacity() {
		return waterAbsorptionCapacity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWaterAbsorptionCapacity(TFloatWithUnits newWaterAbsorptionCapacity, NotificationChain msgs) {
		TFloatWithUnits oldWaterAbsorptionCapacity = waterAbsorptionCapacity;
		waterAbsorptionCapacity = newWaterAbsorptionCapacity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY, oldWaterAbsorptionCapacity, newWaterAbsorptionCapacity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWaterAbsorptionCapacity(TFloatWithUnits newWaterAbsorptionCapacity) {
		if (newWaterAbsorptionCapacity != waterAbsorptionCapacity) {
			NotificationChain msgs = null;
			if (waterAbsorptionCapacity != null)
				msgs = ((InternalEObject)waterAbsorptionCapacity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY, null, msgs);
			if (newWaterAbsorptionCapacity != null)
				msgs = ((InternalEObject)newWaterAbsorptionCapacity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY, null, msgs);
			msgs = basicSetWaterAbsorptionCapacity(newWaterAbsorptionCapacity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY, newWaterAbsorptionCapacity, newWaterAbsorptionCapacity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getOpenPorosity() {
		return openPorosity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOpenPorosity(TFloatWithUnits newOpenPorosity, NotificationChain msgs) {
		TFloatWithUnits oldOpenPorosity = openPorosity;
		openPorosity = newOpenPorosity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__OPEN_POROSITY, oldOpenPorosity, newOpenPorosity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOpenPorosity(TFloatWithUnits newOpenPorosity) {
		if (newOpenPorosity != openPorosity) {
			NotificationChain msgs = null;
			if (openPorosity != null)
				msgs = ((InternalEObject)openPorosity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__OPEN_POROSITY, null, msgs);
			if (newOpenPorosity != null)
				msgs = ((InternalEObject)newOpenPorosity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__OPEN_POROSITY, null, msgs);
			msgs = basicSetOpenPorosity(newOpenPorosity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__OPEN_POROSITY, newOpenPorosity, newOpenPorosity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getEffectiveSaturation() {
		return effectiveSaturation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEffectiveSaturation(TFloatWithUnits newEffectiveSaturation, NotificationChain msgs) {
		TFloatWithUnits oldEffectiveSaturation = effectiveSaturation;
		effectiveSaturation = newEffectiveSaturation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION, oldEffectiveSaturation, newEffectiveSaturation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEffectiveSaturation(TFloatWithUnits newEffectiveSaturation) {
		if (newEffectiveSaturation != effectiveSaturation) {
			NotificationChain msgs = null;
			if (effectiveSaturation != null)
				msgs = ((InternalEObject)effectiveSaturation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION, null, msgs);
			if (newEffectiveSaturation != null)
				msgs = ((InternalEObject)newEffectiveSaturation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION, null, msgs);
			msgs = basicSetEffectiveSaturation(newEffectiveSaturation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION, newEffectiveSaturation, newEffectiveSaturation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getCapillarySaturationContent() {
		return capillarySaturationContent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCapillarySaturationContent(TFloatWithUnits newCapillarySaturationContent, NotificationChain msgs) {
		TFloatWithUnits oldCapillarySaturationContent = capillarySaturationContent;
		capillarySaturationContent = newCapillarySaturationContent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT, oldCapillarySaturationContent, newCapillarySaturationContent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCapillarySaturationContent(TFloatWithUnits newCapillarySaturationContent) {
		if (newCapillarySaturationContent != capillarySaturationContent) {
			NotificationChain msgs = null;
			if (capillarySaturationContent != null)
				msgs = ((InternalEObject)capillarySaturationContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT, null, msgs);
			if (newCapillarySaturationContent != null)
				msgs = ((InternalEObject)newCapillarySaturationContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT, null, msgs);
			msgs = basicSetCapillarySaturationContent(newCapillarySaturationContent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT, newCapillarySaturationContent, newCapillarySaturationContent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getHygroscopicSorption() {
		return hygroscopicSorption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHygroscopicSorption(TFloatWithUnits newHygroscopicSorption, NotificationChain msgs) {
		TFloatWithUnits oldHygroscopicSorption = hygroscopicSorption;
		hygroscopicSorption = newHygroscopicSorption;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION, oldHygroscopicSorption, newHygroscopicSorption);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHygroscopicSorption(TFloatWithUnits newHygroscopicSorption) {
		if (newHygroscopicSorption != hygroscopicSorption) {
			NotificationChain msgs = null;
			if (hygroscopicSorption != null)
				msgs = ((InternalEObject)hygroscopicSorption).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION, null, msgs);
			if (newHygroscopicSorption != null)
				msgs = ((InternalEObject)newHygroscopicSorption).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION, null, msgs);
			msgs = basicSetHygroscopicSorption(newHygroscopicSorption, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION, newHygroscopicSorption, newHygroscopicSorption));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getThermalConductivity() {
		return thermalConductivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThermalConductivity(TFloatWithUnits newThermalConductivity, NotificationChain msgs) {
		TFloatWithUnits oldThermalConductivity = thermalConductivity;
		thermalConductivity = newThermalConductivity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY, oldThermalConductivity, newThermalConductivity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThermalConductivity(TFloatWithUnits newThermalConductivity) {
		if (newThermalConductivity != thermalConductivity) {
			NotificationChain msgs = null;
			if (thermalConductivity != null)
				msgs = ((InternalEObject)thermalConductivity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY, null, msgs);
			if (newThermalConductivity != null)
				msgs = ((InternalEObject)newThermalConductivity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY, null, msgs);
			msgs = basicSetThermalConductivity(newThermalConductivity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY, newThermalConductivity, newThermalConductivity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFloatWithUnits getLiquidWaterConductivityEffectiveSaturation() {
		return liquidWaterConductivityEffectiveSaturation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLiquidWaterConductivityEffectiveSaturation(TFloatWithUnits newLiquidWaterConductivityEffectiveSaturation, NotificationChain msgs) {
		TFloatWithUnits oldLiquidWaterConductivityEffectiveSaturation = liquidWaterConductivityEffectiveSaturation;
		liquidWaterConductivityEffectiveSaturation = newLiquidWaterConductivityEffectiveSaturation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION, oldLiquidWaterConductivityEffectiveSaturation, newLiquidWaterConductivityEffectiveSaturation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLiquidWaterConductivityEffectiveSaturation(TFloatWithUnits newLiquidWaterConductivityEffectiveSaturation) {
		if (newLiquidWaterConductivityEffectiveSaturation != liquidWaterConductivityEffectiveSaturation) {
			NotificationChain msgs = null;
			if (liquidWaterConductivityEffectiveSaturation != null)
				msgs = ((InternalEObject)liquidWaterConductivityEffectiveSaturation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION, null, msgs);
			if (newLiquidWaterConductivityEffectiveSaturation != null)
				msgs = ((InternalEObject)newLiquidWaterConductivityEffectiveSaturation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - simmatrixPackage.TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION, null, msgs);
			msgs = basicSetLiquidWaterConductivityEffectiveSaturation(newLiquidWaterConductivityEffectiveSaturation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION, newLiquidWaterConductivityEffectiveSaturation, newLiquidWaterConductivityEffectiveSaturation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, simmatrixPackage.TMATERIAL_TYPE_VARIANT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__DENSITY:
				return basicSetDensity(null, msgs);
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY:
				return basicSetSpecificHeatCapacity(null, msgs);
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__CONDUCTIVITY:
				return basicSetConductivity(null, msgs);
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR:
				return basicSetWaterVaporDiffusionResistanceFactor(null, msgs);
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY:
				return basicSetWaterAbsorptionCapacity(null, msgs);
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__OPEN_POROSITY:
				return basicSetOpenPorosity(null, msgs);
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION:
				return basicSetEffectiveSaturation(null, msgs);
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT:
				return basicSetCapillarySaturationContent(null, msgs);
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION:
				return basicSetHygroscopicSorption(null, msgs);
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY:
				return basicSetThermalConductivity(null, msgs);
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION:
				return basicSetLiquidWaterConductivityEffectiveSaturation(null, msgs);
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
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__DENSITY:
				return getDensity();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY:
				return getSpecificHeatCapacity();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__CONDUCTIVITY:
				return getConductivity();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR:
				return getWaterVaporDiffusionResistanceFactor();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY:
				return getWaterAbsorptionCapacity();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__OPEN_POROSITY:
				return getOpenPorosity();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION:
				return getEffectiveSaturation();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT:
				return getCapillarySaturationContent();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION:
				return getHygroscopicSorption();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY:
				return getThermalConductivity();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION:
				return getLiquidWaterConductivityEffectiveSaturation();
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__ID:
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
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__DENSITY:
				setDensity((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY:
				setSpecificHeatCapacity((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__CONDUCTIVITY:
				setConductivity((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR:
				setWaterVaporDiffusionResistanceFactor((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY:
				setWaterAbsorptionCapacity((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__OPEN_POROSITY:
				setOpenPorosity((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION:
				setEffectiveSaturation((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT:
				setCapillarySaturationContent((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION:
				setHygroscopicSorption((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY:
				setThermalConductivity((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION:
				setLiquidWaterConductivityEffectiveSaturation((TFloatWithUnits)newValue);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__ID:
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
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__DENSITY:
				setDensity((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY:
				setSpecificHeatCapacity((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__CONDUCTIVITY:
				setConductivity((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR:
				setWaterVaporDiffusionResistanceFactor((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY:
				setWaterAbsorptionCapacity((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__OPEN_POROSITY:
				setOpenPorosity((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION:
				setEffectiveSaturation((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT:
				setCapillarySaturationContent((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION:
				setHygroscopicSorption((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY:
				setThermalConductivity((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION:
				setLiquidWaterConductivityEffectiveSaturation((TFloatWithUnits)null);
				return;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__ID:
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
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__DENSITY:
				return density != null;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__SPECIFIC_HEAT_CAPACITY:
				return specificHeatCapacity != null;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__CONDUCTIVITY:
				return conductivity != null;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_VAPOR_DIFFUSION_RESISTANCE_FACTOR:
				return waterVaporDiffusionResistanceFactor != null;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__WATER_ABSORPTION_CAPACITY:
				return waterAbsorptionCapacity != null;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__OPEN_POROSITY:
				return openPorosity != null;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__EFFECTIVE_SATURATION:
				return effectiveSaturation != null;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__CAPILLARY_SATURATION_CONTENT:
				return capillarySaturationContent != null;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__HYGROSCOPIC_SORPTION:
				return hygroscopicSorption != null;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__THERMAL_CONDUCTIVITY:
				return thermalConductivity != null;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__LIQUID_WATER_CONDUCTIVITY_EFFECTIVE_SATURATION:
				return liquidWaterConductivityEffectiveSaturation != null;
			case simmatrixPackage.TMATERIAL_TYPE_VARIANT__ID:
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

} //TMaterialTypeVariantImpl
