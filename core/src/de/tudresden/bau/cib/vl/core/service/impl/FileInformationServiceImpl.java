//package de.tudresden.bau.cib.vl.core.service.impl;
//
//import java.io.File;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.hibernate.HibernateException;
//
//import de.tudresden.bau.cib.vl.core.database.dao.FileInformationDao;
//import de.tudresden.bau.cib.vl.core.database.dao.UserDao;
//import de.tudresden.bau.cib.vl.core.database.dao.UserRepositoryDao;
//import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
//import de.tudresden.bau.cib.vl.core.database.domain.User;
//import de.tudresden.bau.cib.vl.core.database.domain.UserRepository;
//import de.tudresden.bau.cib.vl.core.database.exception.DatabaseServiceException;
//import de.tudresden.bau.cib.vl.core.database.exception.DatabaseServiceExceptionCode;
//import de.tudresden.bau.cib.vl.core.service.FileInformationService;
//
//public class FileInformationServiceImpl implements FileInformationService {
//	
//	FileInformationDao fileInformationDao;
//	UserDao userDao;
//	UserRepositoryDao userRepositoryDao;
//
//
//	@Override
//	public Integer insertFileEntry(FileInformation fileMetaInformation) {
//		try {
//			Integer retValue = fileInformationDao.insertFileEntry(fileMetaInformation);
//			return retValue;
//		} catch(HibernateException e) {
//			throw new DatabaseServiceException(DatabaseServiceExceptionCode.INSERT_FAILED, e);
//		}
//	}
//
//	@Override
//	public void deleteFileEntry(Integer fileInformationId) {	
//		try {
//			fileInformationDao.deleteFileEntry(fileInformationId);
//		} catch(HibernateException e) {
//			throw new DatabaseServiceException(DatabaseServiceExceptionCode.DELETE_FAILED, e);
//		}
//	}
//
//	@Override
//	public boolean existFileEntry(Integer fileInformationId) {
//		return fileInformationDao.existFileEntry(fileInformationId);
//	}
//
//	@Override
//	public Set<Integer> getAllFileIdentifiers() {
//		return fileInformationDao.getAllFileIdentifiers();
//	}
//
//	public void setFileInformationDao(FileInformationDao fileInformationDao) {
//		this.fileInformationDao = fileInformationDao;
//	}
//
//	@Override
//	public String getFilePath(Integer fileInformationId) {
//		return fileInformationDao.getFilePath(fileInformationId);
//	}
//
//	@Override
//	public FileInformation getFileInformationByFilePath(String filePath) {
//		return fileInformationDao.getFileInformationByFilePath(filePath);
//	}
//
//	@Override
//	public List<FileInformation> getFileInformationByOwnerId(Integer userId) {
//		return fileInformationDao.getFileInformationByOwnerId(userId);
//	}
//
//	@Override
//	public FileInformation getFileInformationByFileId(Integer fileInformationId) {
//		return fileInformationDao.getFileInformation(fileInformationId);
//	}
//
//	@Override
//	public FileInformation getFileInformationByOwnerIdAndFileId(Integer userId,
//			Integer fileId) {
//		List<FileInformation> filesOfUser = fileInformationDao.getFileInformationByOwnerId(userId);
//		for(FileInformation fi : filesOfUser) {
//			if(fi.getId() == fileId) {
//				return fi;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public Set<User> getUsersWhichHaveAccessToFile(Integer fileInformationId) {
//		Set<User> users = null; 
//		FileInformation fi = fileInformationDao.getFileInformation(fileInformationId);
//		Set<UserRepository> repositories = fi.getUserRepositories();
//		for(UserRepository ur : repositories) {
//			int userId = ur.getUserId();
//			User user = userDao.getUser(userId);
//			if(user != null) {
//				if(users == null) users = new HashSet<User>();
//				users.add(user);
//			}
//		}
//		return users;
//	}
//
//	@Override
//	public FileInformation getFileInformationByOwnerIdAndFile(Integer userId,
//			File file) {
//		List<FileInformation> filesOfUser = fileInformationDao.getFileInformationByOwnerId(userId);
//		for(FileInformation fi : filesOfUser) {
//			if(fi.getFilePath().equals(file.getAbsolutePath())) {
//				return fi;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public void insertFileEntryAndReferenceToOwner(
//			FileInformation fileInformation, Integer ownerId) {
//		try {
//			Integer retValue = fileInformationDao.insertFileEntry(fileInformation);
//			if(retValue > 0) {
//				UserRepository ur = userRepositoryDao.getUserRepositoryByUserId(ownerId);
//				List<FileInformation> files = ur.getFiles();
//				files.add(fileInformation);
//				ur.setFiles(files);
//				userRepositoryDao.updateUserRepository(ur);
//			}
//		} catch(HibernateException e) {
//			throw new DatabaseServiceException(DatabaseServiceExceptionCode.INSERT_FAILED, e);
//		}
//	}
//
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
//
//	public void setUserRepositoryDao(UserRepositoryDao userRepositoryDao) {
//		this.userRepositoryDao = userRepositoryDao;
//	}
//
//}
