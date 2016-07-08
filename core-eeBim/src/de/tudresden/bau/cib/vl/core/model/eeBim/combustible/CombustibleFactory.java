/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.combustible;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage
 * @generated
 */
public interface CombustibleFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CombustibleFactory eINSTANCE = de.tudresden.bau.cib.vl.core.model.eeBim.combustible.impl.CombustibleFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container</em>'.
	 * @generated
	 */
	CombustibleContainer createCombustibleContainer();

	/**
	 * Returns a new object of class '<em>Combustible</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Combustible</em>'.
	 * @generated
	 */
	Combustible createCombustible();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CombustiblePackage getCombustiblePackage();

} //CombustibleFactory
