package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;

import java.net.URI;
import java.net.URISyntaxException;

public class StdFileDescriptor extends ResourceDescriptor implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5513235430045716335L;
	private String fileStore = null;
	private String fileURIString = null;
	private URI fileURI = null;
	
	public StdFileDescriptor() {
		super.setResourceName("StdFileResource");
		super.setResourceClassName("ises.dataframework.stp.resources.StdFileResource");
	}


	public String getFileStore() {
		return fileStore;
	}
	public void setFileStore(String fileStore) {
		this.fileStore = fileStore;
	}

	public String getFileURIString() {
		if(fileURI != null) {
			this.fileURIString = fileURI.toString();
		}
		return fileURIString;
	}
	public void setFileURIString(String fileURIString) throws URISyntaxException  {
		String tfileURIString = null;
		URI fileUri = null;
		try {
			
			if(fileURIString != null && !fileURIString.isEmpty())
			{
				tfileURIString = fileURIString.replace(" ", "%20");
				fileUri = new URI(tfileURIString);
			}
			
		} catch (URISyntaxException e) {
			throw e;
		}
		this.fileURI = fileUri;
		this.fileURIString = tfileURIString;
	}
	public URI getFileURI() {
		return fileURI;
	}
	public void setFileURI(URI fileURI) {
		this.fileURI = fileURI;
	}


	@Override
	public String toString() {
		return "StdFileDescriptor ["+super.toString()+", fileStore=" + fileStore + ", fileURIString="
				+ fileURIString + ", fileURI=" + fileURI.toString() + "]";
	}


}
