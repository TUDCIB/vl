/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim.serdes;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;

/**
 * The Class CombustibleContainerSerializer.
 */
public final class CombustibleContainerSerializer {

	
	/**
	 * Instantiates a new combustible container serializer.
	 */
	private CombustibleContainerSerializer() {
		throw new AssertionError();
	}
	
	/**
	 * Serialize.
	 *
	 * @param combustibleContainer the combustible container
	 * @param destPath the dest path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void serialize(CombustibleContainer combustibleContainer, String destPath) throws IOException {
		File file = new File(destPath);
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(uri);
		EList<EObject> contents = resource.getContents();
		contents.add(combustibleContainer);

		resource.save(null);
	}
}
