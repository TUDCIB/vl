/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.combustible.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleFactory;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CombustibleFactoryImpl extends EFactoryImpl implements CombustibleFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CombustibleFactory init() {
		try {
			CombustibleFactory theCombustibleFactory = (CombustibleFactory)EPackage.Registry.INSTANCE.getEFactory("http.//tu-dresden.de/cib/project/combustible/1.0.0"); 
			if (theCombustibleFactory != null) {
				return theCombustibleFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CombustibleFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CombustibleFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CombustiblePackage.COMBUSTIBLE_CONTAINER: return createCombustibleContainer();
			case CombustiblePackage.COMBUSTIBLE: return createCombustible();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CombustibleContainer createCombustibleContainer() {
		CombustibleContainerImpl combustibleContainer = new CombustibleContainerImpl();
		return combustibleContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Combustible createCombustible() {
		CombustibleImpl combustible = new CombustibleImpl();
		return combustible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CombustiblePackage getCombustiblePackage() {
		return (CombustiblePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CombustiblePackage getPackage() {
		return CombustiblePackage.eINSTANCE;
	}

} //CombustibleFactoryImpl
