/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.util.CombustibleResourceFactoryImpl;

/**
 * The Class CombustibleContainerDeserializer.
 */
public final class CombustibleContainerDeserializer {

	
	/**
	 * Instantiates a new combustible container deserializer.
	 */
	private CombustibleContainerDeserializer() {
		throw new AssertionError();
	}
	
	/**
	 * Deserialize.
	 *
	 * @param combustiblePath the combustible path
	 * @return the e list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static EList<Combustible> deserialize(String combustiblePath) throws IOException {
//		File file = new File(combustiblePath);
//		ResourceSet resourceSet = new ResourceSetImpl();
////		programmatically registration of Ecore like in plugin.xml
//		resourceSet.getPackageRegistry().put(CombustiblePackage.eINSTANCE.getNsURI(), CombustiblePackage.eINSTANCE);
//		Map<String,Object> factoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
//		factoryMap.put("xmi", new CombustibleResourceFactoryImpl());
//		factoryMap.put("xml", new CombustibleResourceFactoryImpl());
//		
//		URI uri = URI.createFileURI(file.getAbsolutePath());
//		Resource resource = resourceSet.getResource(uri, true);
//		EList<EObject> contents = resource.getContents();
//		CombustibleContainer combustibleContainer = (CombustibleContainer) contents.get(0); //alle Brennwerte = Container | Combustible ein Brennelement
//		return combustibleContainer.getCombustibles();
		
		File file = new File(combustiblePath);
		ResourceSet resourceSet = new ResourceSetImpl();
//		programmatically registration of Ecore like in plugin.xml
		resourceSet.getPackageRegistry().put(CombustiblePackage.eINSTANCE.getNsURI(), CombustiblePackage.eINSTANCE);
		CombustiblePackage.eINSTANCE.eClass();
		CombustibleResourceFactoryImpl factory = new CombustibleResourceFactoryImpl();
		Map<String,Object> factoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		factoryMap.put("xmi", factory);
		
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource res = factory.createResource(uri);
		res.load(new FileInputStream(file), null);
//		Resource resource = resourceSet.getResource(uri, true);
		EList<EObject> contents = res.getContents();
		CombustibleContainer combustibleContainer = (CombustibleContainer) contents.get(0); //alle Brennwerte = Container | Combustible ein Brennelement

		return combustibleContainer.getCombustibles();
	}
}
