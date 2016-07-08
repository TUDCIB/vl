package de.tudresden.bau.cib.vl.core.database.dao;

import java.util.List;
import java.util.Set;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;

public interface FileInformationDao {

	FileInformation getFileInformation(Integer id);
	
	List<FileInformation> getFileInformationByOwnerId(Integer ownerId);
	
	Integer insertFileEntry(FileInformation fmi);
	
	/**
	 * Deletes the entry in the database.
	 * This deletes not the file!
	 * @param fileId
	 */
	void deleteFileEntry(Integer fileInformationId);
	
	void deleteFileInformation(FileInformation fileInfo);
	
	boolean existFileEntry(Integer fileInformationId);
	
	Set<Integer> getAllFileIdentifiers();

	String getFilePath(Integer fileInformationId);

	FileInformation getFileInformationByFilePath(String filePath);
	
	FileInformation getFileInformationByFilePathAndOwnerId(String filePath, Integer ownerId);
}
