/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.combustible.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Activator;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage;

/**
 * This is the item provider adapter for a {@link cib.project.combustible.model.combustible.Combustible} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CombustibleItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CombustibleItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addIdPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addUnitPropertyDescriptor(object);
			addHeatingValueHiPropertyDescriptor(object);
			addUsefulHeatHsPropertyDescriptor(object);
			addRatioHsToHiPropertyDescriptor(object);
			addPricePerKWHPropertyDescriptor(object);
			addPricePerUnitPropertyDescriptor(object);
			addBasePricePerAnnoPropertyDescriptor(object);
			addStorageYieldPropertyDescriptor(object);
			addPrimaryEnergyFactorPropertyDescriptor(object);
			addEmissionCO2PropertyDescriptor(object);
			addEmissionSO2PropertyDescriptor(object);
			addEmissionNOXPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_id_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_name_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Unit feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUnitPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_unit_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_unit_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__UNIT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Heating Value Hi feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHeatingValueHiPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_heatingValueHi_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_heatingValueHi_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__HEATING_VALUE_HI,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Useful Heat Hs feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUsefulHeatHsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_usefulHeatHs_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_usefulHeatHs_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__USEFUL_HEAT_HS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Ratio Hs To Hi feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRatioHsToHiPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_ratioHsToHi_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_ratioHsToHi_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__RATIO_HS_TO_HI,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Price Per KWH feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPricePerKWHPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_pricePerKWH_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_pricePerKWH_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__PRICE_PER_KWH,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Price Per Unit feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPricePerUnitPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_pricePerUnit_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_pricePerUnit_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__PRICE_PER_UNIT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Base Price Per Anno feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBasePricePerAnnoPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_basePricePerAnno_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_basePricePerAnno_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__BASE_PRICE_PER_ANNO,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Storage Yield feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStorageYieldPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_storageYield_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_storageYield_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__STORAGE_YIELD,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Primary Energy Factor feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPrimaryEnergyFactorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_primaryEnergyFactor_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_primaryEnergyFactor_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__PRIMARY_ENERGY_FACTOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Emission CO2 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEmissionCO2PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_emissionCO2_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_emissionCO2_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__EMISSION_CO2,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Emission SO2 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEmissionSO2PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_emissionSO2_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_emissionSO2_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__EMISSION_SO2,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Emission NOX feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEmissionNOXPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Combustible_emissionNOX_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Combustible_emissionNOX_feature", "_UI_Combustible_type"),
				 CombustiblePackage.Literals.COMBUSTIBLE__EMISSION_NOX,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns Combustible.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Combustible"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Combustible)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Combustible_type") :
			getString("_UI_Combustible_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Combustible.class)) {
			case CombustiblePackage.COMBUSTIBLE__ID:
			case CombustiblePackage.COMBUSTIBLE__NAME:
			case CombustiblePackage.COMBUSTIBLE__UNIT:
			case CombustiblePackage.COMBUSTIBLE__HEATING_VALUE_HI:
			case CombustiblePackage.COMBUSTIBLE__USEFUL_HEAT_HS:
			case CombustiblePackage.COMBUSTIBLE__RATIO_HS_TO_HI:
			case CombustiblePackage.COMBUSTIBLE__PRICE_PER_KWH:
			case CombustiblePackage.COMBUSTIBLE__PRICE_PER_UNIT:
			case CombustiblePackage.COMBUSTIBLE__BASE_PRICE_PER_ANNO:
			case CombustiblePackage.COMBUSTIBLE__STORAGE_YIELD:
			case CombustiblePackage.COMBUSTIBLE__PRIMARY_ENERGY_FACTOR:
			case CombustiblePackage.COMBUSTIBLE__EMISSION_CO2:
			case CombustiblePackage.COMBUSTIBLE__EMISSION_SO2:
			case CombustiblePackage.COMBUSTIBLE__EMISSION_NOX:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return Activator.INSTANCE;
	}

}
