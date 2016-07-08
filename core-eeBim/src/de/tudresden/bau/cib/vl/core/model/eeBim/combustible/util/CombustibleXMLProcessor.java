/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.combustible.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CombustibleXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CombustibleXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		CombustiblePackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the CombustibleResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new CombustibleResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new CombustibleResourceFactoryImpl());
		}
		return registrations;
	}

} //CombustibleXMLProcessor
