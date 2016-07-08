package de.tudresden.bau.cib.vl.core.service.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.util.VirtualFile;


public class FileServiceImpl implements FileService {
	
	private ConfigurationService configuration;

	@Override
	public Set<VirtualFile> getVirtualFiles(File directory, String fileFormat,
			boolean recursively) throws MalformedURLException {
		Set<File> localFiles = FileService.getFiles(directory, fileFormat, recursively);
		Set<VirtualFile> virtualFiles = new HashSet<VirtualFile>();		
		
		for(File f : localFiles) {
			URL url = getVirtualFileURL(f);
			VirtualFile vFile = new VirtualFile(url, f.getAbsolutePath());
			virtualFiles.add(vFile);
		}
		return virtualFiles;
	}
	
	private URL getVirtualFileURL(File f) throws MalformedURLException {
		String baseUrl = configuration.getBaseURL();
		if(StringUtils.isNotEmpty(baseUrl)) {
			//	for example -> ../data/VirtualLaboratory/
			String vlHomeDirectory = configuration.getProperty(ConfigurationService.PROPERTIES.PATH_BASE);
			String localFilePath = FileService.convertWindowsPathToUnix(f.getAbsolutePath());
			String localFilePathWithoutHomedirectory = localFilePath.replaceAll(vlHomeDirectory, "");
			int lastSlash = localFilePathWithoutHomedirectory.lastIndexOf("/");
			String pureFile = localFilePathWithoutHomedirectory.substring(lastSlash+1);
			String urlFilePathToLocalDirectory = localFilePathWithoutHomedirectory.substring(0,lastSlash);
			String relativePathUrl = "file"+urlFilePathToLocalDirectory+"?file="+pureFile;
			return new URL(baseUrl+relativePathUrl);
		} else {
			return f.toURI().toURL();
		}
	}

	@Override
	public Set<VirtualFile> getVirtualFiles(File directory, boolean recursively) throws MalformedURLException {
		return getVirtualFiles(directory, null, recursively);
	}
	
	@Override
	public VirtualFile getVirtualFile(File directory, String fileName)
			throws MalformedURLException {
		Set<File> localFiles = FileService.getFiles(directory, false);
		
		for(File f : localFiles) {
			if(f.getName().equalsIgnoreCase(fileName)) {
				URL url = getVirtualFileURL(f);
				VirtualFile vFile = new VirtualFile(url, f.getAbsolutePath());
				return vFile;
			}
		}
		return null;
	}
	
	public VirtualFile getVirtualFile(File file) throws MalformedURLException {		
		URL url = getVirtualFileURL(file);	
		VirtualFile vFile = new VirtualFile(url, file.getAbsolutePath());
		return vFile;		
		
	}  
	
	public void setConfiguration(ConfigurationService configuration) {
		this.configuration = configuration;
	}

	@Override
	public String getTempDirectoryPath() {
		return configuration.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
	}
}
