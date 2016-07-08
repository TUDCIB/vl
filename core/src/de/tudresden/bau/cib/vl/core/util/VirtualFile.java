package de.tudresden.bau.cib.vl.core.util;

import java.io.File;
import java.net.URL;

/**
 * Extension of the normal file. It additionally represents the correspondent VL URL of the file 
 * so that an uploaded files is represented by its local path on disk and its URL for downloading or sharing it.
 * 
 * @author Ken
 *
 */
public class VirtualFile extends File {

	/**
	 * 
	 */
	private static final long serialVersionUID = 423809311086427689L;
	private URL url;

	
	public VirtualFile(URL url, String localDiskFilePath) {
		super(localDiskFilePath);
		this.url = url;
//		this.contextUrl = contextUrl;
//		this.relativeUrlPath = relativeUrlPath;
	}
	
	public VirtualFile(URL url, File file) {
		this(url,file.getAbsolutePath());
	}
	
	/**
	 * @return A URL to download the file, e.g. "http://141.30.143.53:8080/ivel/file/temp?file=isesResourceTagSchema.xsd"
	 */
	public URL getUrl() {
		return url;
	}

}
