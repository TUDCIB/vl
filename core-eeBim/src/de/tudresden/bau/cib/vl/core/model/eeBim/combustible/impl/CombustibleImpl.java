/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.combustible.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Combustible</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getId <em>Id</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getName <em>Name</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getHeatingValueHi <em>Heating Value Hi</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getUsefulHeatHs <em>Useful Heat Hs</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getRatioHsToHi <em>Ratio Hs To Hi</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getPricePerKWH <em>Price Per KWH</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getPricePerUnit <em>Price Per Unit</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getBasePricePerAnno <em>Base Price Per Anno</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getStorageYield <em>Storage Yield</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getPrimaryEnergyFactor <em>Primary Energy Factor</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getEmissionCO2 <em>Emission CO2</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getEmissionSO2 <em>Emission SO2</em>}</li>
 *   <li>{@link cib.project.combustible.model.combustible.impl.CombustibleImpl#getEmissionNOX <em>Emission NOX</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CombustibleImpl extends EObjectImpl implements Combustible {
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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected static final String UNIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected String unit = UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeatingValueHi() <em>Heating Value Hi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeatingValueHi()
	 * @generated
	 * @ordered
	 */
	protected static final double HEATING_VALUE_HI_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getHeatingValueHi() <em>Heating Value Hi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeatingValueHi()
	 * @generated
	 * @ordered
	 */
	protected double heatingValueHi = HEATING_VALUE_HI_EDEFAULT;

	/**
	 * The default value of the '{@link #getUsefulHeatHs() <em>Useful Heat Hs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsefulHeatHs()
	 * @generated
	 * @ordered
	 */
	protected static final double USEFUL_HEAT_HS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getUsefulHeatHs() <em>Useful Heat Hs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsefulHeatHs()
	 * @generated
	 * @ordered
	 */
	protected double usefulHeatHs = USEFUL_HEAT_HS_EDEFAULT;

	/**
	 * The default value of the '{@link #getRatioHsToHi() <em>Ratio Hs To Hi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRatioHsToHi()
	 * @generated
	 * @ordered
	 */
	protected static final double RATIO_HS_TO_HI_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getRatioHsToHi() <em>Ratio Hs To Hi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRatioHsToHi()
	 * @generated
	 * @ordered
	 */
	protected double ratioHsToHi = RATIO_HS_TO_HI_EDEFAULT;

	/**
	 * The default value of the '{@link #getPricePerKWH() <em>Price Per KWH</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPricePerKWH()
	 * @generated
	 * @ordered
	 */
	protected static final double PRICE_PER_KWH_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPricePerKWH() <em>Price Per KWH</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPricePerKWH()
	 * @generated
	 * @ordered
	 */
	protected double pricePerKWH = PRICE_PER_KWH_EDEFAULT;

	/**
	 * The default value of the '{@link #getPricePerUnit() <em>Price Per Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPricePerUnit()
	 * @generated
	 * @ordered
	 */
	protected static final double PRICE_PER_UNIT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPricePerUnit() <em>Price Per Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPricePerUnit()
	 * @generated
	 * @ordered
	 */
	protected double pricePerUnit = PRICE_PER_UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getBasePricePerAnno() <em>Base Price Per Anno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBasePricePerAnno()
	 * @generated
	 * @ordered
	 */
	protected static final double BASE_PRICE_PER_ANNO_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getBasePricePerAnno() <em>Base Price Per Anno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBasePricePerAnno()
	 * @generated
	 * @ordered
	 */
	protected double basePricePerAnno = BASE_PRICE_PER_ANNO_EDEFAULT;

	/**
	 * The default value of the '{@link #getStorageYield() <em>Storage Yield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStorageYield()
	 * @generated
	 * @ordered
	 */
	protected static final double STORAGE_YIELD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getStorageYield() <em>Storage Yield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStorageYield()
	 * @generated
	 * @ordered
	 */
	protected double storageYield = STORAGE_YIELD_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrimaryEnergyFactor() <em>Primary Energy Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimaryEnergyFactor()
	 * @generated
	 * @ordered
	 */
	protected static final double PRIMARY_ENERGY_FACTOR_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPrimaryEnergyFactor() <em>Primary Energy Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimaryEnergyFactor()
	 * @generated
	 * @ordered
	 */
	protected double primaryEnergyFactor = PRIMARY_ENERGY_FACTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getEmissionCO2() <em>Emission CO2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmissionCO2()
	 * @generated
	 * @ordered
	 */
	protected static final double EMISSION_CO2_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getEmissionCO2() <em>Emission CO2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmissionCO2()
	 * @generated
	 * @ordered
	 */
	protected double emissionCO2 = EMISSION_CO2_EDEFAULT;

	/**
	 * The default value of the '{@link #getEmissionSO2() <em>Emission SO2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmissionSO2()
	 * @generated
	 * @ordered
	 */
	protected static final double EMISSION_SO2_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getEmissionSO2() <em>Emission SO2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmissionSO2()
	 * @generated
	 * @ordered
	 */
	protected double emissionSO2 = EMISSION_SO2_EDEFAULT;

	/**
	 * The default value of the '{@link #getEmissionNOX() <em>Emission NOX</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmissionNOX()
	 * @generated
	 * @ordered
	 */
	protected static final double EMISSION_NOX_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getEmissionNOX() <em>Emission NOX</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmissionNOX()
	 * @generated
	 * @ordered
	 */
	protected double emissionNOX = EMISSION_NOX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CombustibleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CombustiblePackage.Literals.COMBUSTIBLE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnit(String newUnit) {
		String oldUnit = unit;
		unit = newUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__UNIT, oldUnit, unit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getHeatingValueHi() {
		return heatingValueHi;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeatingValueHi(double newHeatingValueHi) {
		double oldHeatingValueHi = heatingValueHi;
		heatingValueHi = newHeatingValueHi;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__HEATING_VALUE_HI, oldHeatingValueHi, heatingValueHi));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getUsefulHeatHs() {
		return usefulHeatHs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsefulHeatHs(double newUsefulHeatHs) {
		double oldUsefulHeatHs = usefulHeatHs;
		usefulHeatHs = newUsefulHeatHs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__USEFUL_HEAT_HS, oldUsefulHeatHs, usefulHeatHs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRatioHsToHi() {
		return ratioHsToHi;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRatioHsToHi(double newRatioHsToHi) {
		double oldRatioHsToHi = ratioHsToHi;
		ratioHsToHi = newRatioHsToHi;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__RATIO_HS_TO_HI, oldRatioHsToHi, ratioHsToHi));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPricePerKWH() {
		return pricePerKWH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPricePerKWH(double newPricePerKWH) {
		double oldPricePerKWH = pricePerKWH;
		pricePerKWH = newPricePerKWH;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__PRICE_PER_KWH, oldPricePerKWH, pricePerKWH));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPricePerUnit() {
		return pricePerUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPricePerUnit(double newPricePerUnit) {
		double oldPricePerUnit = pricePerUnit;
		pricePerUnit = newPricePerUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__PRICE_PER_UNIT, oldPricePerUnit, pricePerUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getBasePricePerAnno() {
		return basePricePerAnno;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBasePricePerAnno(double newBasePricePerAnno) {
		double oldBasePricePerAnno = basePricePerAnno;
		basePricePerAnno = newBasePricePerAnno;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__BASE_PRICE_PER_ANNO, oldBasePricePerAnno, basePricePerAnno));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getStorageYield() {
		return storageYield;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStorageYield(double newStorageYield) {
		double oldStorageYield = storageYield;
		storageYield = newStorageYield;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__STORAGE_YIELD, oldStorageYield, storageYield));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPrimaryEnergyFactor() {
		return primaryEnergyFactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimaryEnergyFactor(double newPrimaryEnergyFactor) {
		double oldPrimaryEnergyFactor = primaryEnergyFactor;
		primaryEnergyFactor = newPrimaryEnergyFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__PRIMARY_ENERGY_FACTOR, oldPrimaryEnergyFactor, primaryEnergyFactor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getEmissionCO2() {
		return emissionCO2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmissionCO2(double newEmissionCO2) {
		double oldEmissionCO2 = emissionCO2;
		emissionCO2 = newEmissionCO2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__EMISSION_CO2, oldEmissionCO2, emissionCO2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getEmissionSO2() {
		return emissionSO2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmissionSO2(double newEmissionSO2) {
		double oldEmissionSO2 = emissionSO2;
		emissionSO2 = newEmissionSO2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__EMISSION_SO2, oldEmissionSO2, emissionSO2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getEmissionNOX() {
		return emissionNOX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmissionNOX(double newEmissionNOX) {
		double oldEmissionNOX = emissionNOX;
		emissionNOX = newEmissionNOX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CombustiblePackage.COMBUSTIBLE__EMISSION_NOX, oldEmissionNOX, emissionNOX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CombustiblePackage.COMBUSTIBLE__ID:
				return getId();
			case CombustiblePackage.COMBUSTIBLE__NAME:
				return getName();
			case CombustiblePackage.COMBUSTIBLE__UNIT:
				return getUnit();
			case CombustiblePackage.COMBUSTIBLE__HEATING_VALUE_HI:
				return getHeatingValueHi();
			case CombustiblePackage.COMBUSTIBLE__USEFUL_HEAT_HS:
				return getUsefulHeatHs();
			case CombustiblePackage.COMBUSTIBLE__RATIO_HS_TO_HI:
				return getRatioHsToHi();
			case CombustiblePackage.COMBUSTIBLE__PRICE_PER_KWH:
				return getPricePerKWH();
			case CombustiblePackage.COMBUSTIBLE__PRICE_PER_UNIT:
				return getPricePerUnit();
			case CombustiblePackage.COMBUSTIBLE__BASE_PRICE_PER_ANNO:
				return getBasePricePerAnno();
			case CombustiblePackage.COMBUSTIBLE__STORAGE_YIELD:
				return getStorageYield();
			case CombustiblePackage.COMBUSTIBLE__PRIMARY_ENERGY_FACTOR:
				return getPrimaryEnergyFactor();
			case CombustiblePackage.COMBUSTIBLE__EMISSION_CO2:
				return getEmissionCO2();
			case CombustiblePackage.COMBUSTIBLE__EMISSION_SO2:
				return getEmissionSO2();
			case CombustiblePackage.COMBUSTIBLE__EMISSION_NOX:
				return getEmissionNOX();
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
			case CombustiblePackage.COMBUSTIBLE__ID:
				setId((String)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__NAME:
				setName((String)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__UNIT:
				setUnit((String)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__HEATING_VALUE_HI:
				setHeatingValueHi((Double)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__USEFUL_HEAT_HS:
				setUsefulHeatHs((Double)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__RATIO_HS_TO_HI:
				setRatioHsToHi((Double)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__PRICE_PER_KWH:
				setPricePerKWH((Double)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__PRICE_PER_UNIT:
				setPricePerUnit((Double)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__BASE_PRICE_PER_ANNO:
				setBasePricePerAnno((Double)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__STORAGE_YIELD:
				setStorageYield((Double)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__PRIMARY_ENERGY_FACTOR:
				setPrimaryEnergyFactor((Double)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__EMISSION_CO2:
				setEmissionCO2((Double)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__EMISSION_SO2:
				setEmissionSO2((Double)newValue);
				return;
			case CombustiblePackage.COMBUSTIBLE__EMISSION_NOX:
				setEmissionNOX((Double)newValue);
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
			case CombustiblePackage.COMBUSTIBLE__ID:
				setId(ID_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__UNIT:
				setUnit(UNIT_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__HEATING_VALUE_HI:
				setHeatingValueHi(HEATING_VALUE_HI_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__USEFUL_HEAT_HS:
				setUsefulHeatHs(USEFUL_HEAT_HS_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__RATIO_HS_TO_HI:
				setRatioHsToHi(RATIO_HS_TO_HI_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__PRICE_PER_KWH:
				setPricePerKWH(PRICE_PER_KWH_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__PRICE_PER_UNIT:
				setPricePerUnit(PRICE_PER_UNIT_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__BASE_PRICE_PER_ANNO:
				setBasePricePerAnno(BASE_PRICE_PER_ANNO_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__STORAGE_YIELD:
				setStorageYield(STORAGE_YIELD_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__PRIMARY_ENERGY_FACTOR:
				setPrimaryEnergyFactor(PRIMARY_ENERGY_FACTOR_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__EMISSION_CO2:
				setEmissionCO2(EMISSION_CO2_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__EMISSION_SO2:
				setEmissionSO2(EMISSION_SO2_EDEFAULT);
				return;
			case CombustiblePackage.COMBUSTIBLE__EMISSION_NOX:
				setEmissionNOX(EMISSION_NOX_EDEFAULT);
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
			case CombustiblePackage.COMBUSTIBLE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case CombustiblePackage.COMBUSTIBLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CombustiblePackage.COMBUSTIBLE__UNIT:
				return UNIT_EDEFAULT == null ? unit != null : !UNIT_EDEFAULT.equals(unit);
			case CombustiblePackage.COMBUSTIBLE__HEATING_VALUE_HI:
				return heatingValueHi != HEATING_VALUE_HI_EDEFAULT;
			case CombustiblePackage.COMBUSTIBLE__USEFUL_HEAT_HS:
				return usefulHeatHs != USEFUL_HEAT_HS_EDEFAULT;
			case CombustiblePackage.COMBUSTIBLE__RATIO_HS_TO_HI:
				return ratioHsToHi != RATIO_HS_TO_HI_EDEFAULT;
			case CombustiblePackage.COMBUSTIBLE__PRICE_PER_KWH:
				return pricePerKWH != PRICE_PER_KWH_EDEFAULT;
			case CombustiblePackage.COMBUSTIBLE__PRICE_PER_UNIT:
				return pricePerUnit != PRICE_PER_UNIT_EDEFAULT;
			case CombustiblePackage.COMBUSTIBLE__BASE_PRICE_PER_ANNO:
				return basePricePerAnno != BASE_PRICE_PER_ANNO_EDEFAULT;
			case CombustiblePackage.COMBUSTIBLE__STORAGE_YIELD:
				return storageYield != STORAGE_YIELD_EDEFAULT;
			case CombustiblePackage.COMBUSTIBLE__PRIMARY_ENERGY_FACTOR:
				return primaryEnergyFactor != PRIMARY_ENERGY_FACTOR_EDEFAULT;
			case CombustiblePackage.COMBUSTIBLE__EMISSION_CO2:
				return emissionCO2 != EMISSION_CO2_EDEFAULT;
			case CombustiblePackage.COMBUSTIBLE__EMISSION_SO2:
				return emissionSO2 != EMISSION_SO2_EDEFAULT;
			case CombustiblePackage.COMBUSTIBLE__EMISSION_NOX:
				return emissionNOX != EMISSION_NOX_EDEFAULT;
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
		result.append(", name: ");
		result.append(name);
		result.append(", unit: ");
		result.append(unit);
		result.append(", heatingValueHi: ");
		result.append(heatingValueHi);
		result.append(", usefulHeatHs: ");
		result.append(usefulHeatHs);
		result.append(", ratioHsToHi: ");
		result.append(ratioHsToHi);
		result.append(", pricePerKWH: ");
		result.append(pricePerKWH);
		result.append(", pricePerUnit: ");
		result.append(pricePerUnit);
		result.append(", basePricePerAnno: ");
		result.append(basePricePerAnno);
		result.append(", storageYield: ");
		result.append(storageYield);
		result.append(", primaryEnergyFactor: ");
		result.append(primaryEnergyFactor);
		result.append(", emissionCO2: ");
		result.append(emissionCO2);
		result.append(", emissionSO2: ");
		result.append(emissionSO2);
		result.append(", emissionNOX: ");
		result.append(emissionNOX);
		result.append(')');
		return result.toString();
	}

} //CombustibleImpl
