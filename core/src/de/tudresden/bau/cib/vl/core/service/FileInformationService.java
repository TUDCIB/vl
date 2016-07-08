//package de.tudresden.bau.cib.vl.core.service;
//
//import java.io.File;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
//import de.tudresden.bau.cib.vl.core.database.domain.User;
//
//
///**
// * Representing more information about files like which user have access to it or owns it.
// * 
// * @author Ken Baumgaertel 
// * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
// *
// */
////propagation: create a new transaction for each user, isolation: degree of isolation to other transactions, read-only: useful for Hibernate
//@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.REPEATABLE_READ)
//public interface FileInformationService {
//	
//	List<FileInformation> getFileInformationByOwnerId(Integer userId);
//	
//	/**
//	 * Checks if user is owner of the file and return the information of it.
//	 * @param userId User identifier.
//	 * @param fileId File information identifier.
//	 * @return Information about the file which owns the user.
//	 */
//	FileInformation getFileInformationByOwnerIdAndFileId(Integer userId, Integer fileId);
//	
//	/**
//	 * Checks if user is owner of the file and return the information of it.
//	 * @param userId User identifier.
//	 * @param file File represented by an <b>absolute path</b>.
//	 * @return Information about the file which owns the user.
//	 */
//	FileInformation getFileInformationByOwnerIdAndFile(Integer userId, File file);
//	
//	FileInformation getFileInformationByFileId(Integer fileInformationId);
//	
//	FileInformation getFileInformationByFilePath(String filePath);
//	
//	/**
//	 * @param fileInformationId
//	 * @return The user which have access to the given file identifier.
//	 */
//	Set<User> getUsersWhichHaveAccessToFile(Integer fileInformationId);
//	
//	Integer insertFileEntry(FileInformation fileInformation);
//	
//	void insertFileEntryAndReferenceToOwner(FileInformation fileInformation, Integer ownerId);
//	
//	/**
//	 * Deletes the entry in the database.
//	 * This deletes not the file!
//	 * @param fileId
//	 */
//	void deleteFileEntry(Integer fileInformationId);
//	
//	boolean existFileEntry(Integer fileInformationId);
//	
//	Set<Integer> getAllFileIdentifiers();
//	
//	String getFilePath(Integer fileInformationId);
//}
