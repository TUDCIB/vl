package de.tudresden.bau.cib.vl.core.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import de.tudresden.bau.cib.vl.core.database.dao.FileInformationDao;
import de.tudresden.bau.cib.vl.core.database.dao.ProjectDao;
import de.tudresden.bau.cib.vl.core.database.dao.SessionDao;
import de.tudresden.bau.cib.vl.core.database.dao.UserDao;
import de.tudresden.bau.cib.vl.core.database.dao.UserRepositoryDao;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation.TYPE;
import de.tudresden.bau.cib.vl.core.database.domain.OptimizationSimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.Session;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.User;
import de.tudresden.bau.cib.vl.core.database.domain.UserRepository;
import de.tudresden.bau.cib.vl.core.database.exception.DatabaseServiceException;
import de.tudresden.bau.cib.vl.core.database.exception.DatabaseServiceExceptionCode;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.service.UserService;
import de.tudresden.bau.cib.vl.core.util.VirtualFile;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private FileInformationDao fileInformationDao;
	private UserRepositoryDao userRepositoryDao;
	private FileService fileService;
	private ConfigurationService configuration;
	private SessionDao sessionDao;
	
	private ProjectDao projectDao;


	private String sessionTimeInMinutes;
	
	private Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getUserList();
	}
	
	@Override
	public boolean deleteFile(int userId, File file) {
		List<FileInformation> filesOfUser = fileInformationDao.getFileInformationByOwnerId(userId);
		for(FileInformation fi : filesOfUser) {
			if(fi.getFilePath().equals(file.getAbsolutePath())) {
				fileInformationDao.deleteFileEntry(fi.getId());
				return FileService.deleteFile(file);
			}
		}
		return false;
	}
	
	@Override
	public boolean deleteFiles(int userId, File[] files) {
		boolean deleted = true;
		for(File f : files)
			deleted &= deleteFile(userId, f);
		return deleted;
	}
	
	@Override
	public boolean deleteSession(String sessionId) {
		try {
			return sessionDao.deleteSession(sessionId);
		} catch(DataAccessException e) {
			throw new DatabaseServiceException(DatabaseServiceExceptionCode.DELETE_FAILED, e);
		}		
	}

	@Override
	public void deleteUser(int userId) {	
		try {
			userDao.deleteUser(userId);
		} catch(HibernateException e) {
			throw new DatabaseServiceException(DatabaseServiceExceptionCode.DELETE_FAILED, e);
		}
	}
	
	@Override
	public Session createSession(Integer userId) {
		Date currentDate = new Date();
		
		long sessionTimeInMS = Integer.parseInt(sessionTimeInMinutes) * 60 * 1000;
		Date expireDate = new Date(currentDate.getTime()+sessionTimeInMS);
		
		String sessionId = null;
		try {
			Session session = sessionDao.getSessionByUserId(userId);
			if(session != null) {
				long currentTime = System.currentTimeMillis();
				if(session.getExpireDate().getTime() < currentTime) {
//					session expired -> start a new one
					session = new Session();
					sessionId = UUID.randomUUID().toString();
					session.setExpireDate(expireDate);
					session.setUserId(userId);
					sessionDao.insertSession(session);
				} else {
					sessionId = session.getSessionId();
				}
			} else {
				session = new Session();
				sessionId = UUID.randomUUID().toString();
				session.setSessionId(sessionId);
				session.setExpireDate(expireDate);
				session.setUserId(userId);
				
				sessionDao.insertSession(session);
			}
	    	return session;
		} catch(DataAccessException e) {
			throw new DatabaseServiceException(DatabaseServiceExceptionCode.INSERT_FAILED, e);
		}
	}
	
	@Override
	public Project createProject(String projectName, UserRepository repo) {
		String strDirectoryPath = repo.getUserRepositoryPath() + File.separator + projectName;		
		FileService.createDirectory(new File(strDirectoryPath));
		
		
		Project newProj = new Project();
		newProj.setName(projectName);
		newProj.setPathDirectory(strDirectoryPath);	
		newProj.setUserRepository(repo);
		
		insertProject(newProj);
		
		
		repo.addProject(newProj);
		
		
		updateUserRepository(repo);
		return newProj;	
	}
	
	@Override
	public SimulationProject createSimulationProject(Project project, String simProjectName) {		
		String strDirectoryPath = project.getPathDirectory() + File.separator + simProjectName;		
		FileService.createDirectory(new File(strDirectoryPath));		
		
		String strDirectoryInputPath = strDirectoryPath + File.separator + SimulationProject.DEFAULT_RELATIVE_INPUT_DIR;
		String strDirectoryOutputPath = strDirectoryPath + File.separator + SimulationProject.DEFAULT_RELATIVE_OUTPUT_DIR;
		FileService.createDirectory(new File(strDirectoryInputPath));
		FileService.createDirectory(new File(strDirectoryOutputPath));
		
		
		SimulationProject simProject = new SimulationProject();
		simProject.setProject(project);
		simProject.setName(simProjectName);
		simProject.setPathDirectory(strDirectoryPath);
		
		insertSimulationProject(project, simProject);
		updateProject(project);	
		return simProject;
	}
	
	@Override
	public OptimizationSimulationProject createOptimizationSimulationProject(Project project, String simProjectName) {		
		String strDirectoryPath = project.getPathDirectory() + File.separator + simProjectName;		
		FileService.createDirectory(new File(strDirectoryPath));				
		
		String strDirectoryInputPath = strDirectoryPath + File.separator + SimulationProject.DEFAULT_RELATIVE_INPUT_DIR;
		String strDirectoryOutputPath = strDirectoryPath + File.separator + SimulationProject.DEFAULT_RELATIVE_OUTPUT_DIR;
		FileService.createDirectory(new File(strDirectoryInputPath));
		FileService.createDirectory(new File(strDirectoryOutputPath));
		
		OptimizationSimulationProject simProject = new OptimizationSimulationProject();
		simProject.setProject(project);
		simProject.setName(simProjectName);
		simProject.setPathDirectory(strDirectoryPath);	
		
		insertSimulationProject(project, simProject);
		updateProject(project);	
		return simProject;
	}
	

	/**
	 * @deprecated  Use methode deleteSimulationProject(SimulationProject simProject) instead
	 */
	@Deprecated
	@Override
	public void deleteSimulationProject(Integer userId, SimulationProject simProject) {
		// first delete al file informations
		Set<FileInformation> inputFiles = Collections.synchronizedSet(simProject.getInputFiles());
		Set<FileInformation> outputFiles = Collections.synchronizedSet(simProject.getOutputFiles());
		Iterator<FileInformation> iter = inputFiles.iterator();
		while(iter.hasNext()) {
			FileInformation fileInfo = iter.next();
			iter.remove();
			simProject.removeInputFile(fileInfo);
			deleteFile(fileInfo);
		}
		iter = outputFiles.iterator();
		while(iter.hasNext()) {
			FileInformation fileInfo = iter.next();
			iter.remove();
			simProject.removeOutputFile(fileInfo);
			deleteFile(fileInfo);
		}
//		Project project = getProjectOfSimulationProject(userId, simProject);
		Project project = simProject.getProject();
		project.removeSimulationProject(simProject);
		updateProject(project);
		
		projectDao.deleteSimulationProject(simProject);
		
		
	}
	
	@Override
	public void deleteSimulationProject(SimulationProject simProject) {
//		// first delete al file informations
//		Set<FileInformation> inputFiles = Collections.synchronizedSet(simProject.getInputFiles());
//		Set<FileInformation> outputFiles = Collections.synchronizedSet(simProject.getOutputFiles());
//		Iterator<FileInformation> iter = inputFiles.iterator();
//		while(iter.hasNext()) {
//			FileInformation fileInfo = iter.next();
//			iter.remove();
//			simProject.removeInputFile(fileInfo);
//			deleteFile(fileInfo);
//		}
//		iter = outputFiles.iterator();
//		while(iter.hasNext()) {
//			FileInformation fileInfo = iter.next();
//			iter.remove();
//			simProject.removeOutputFile(fileInfo);
//			deleteFile(fileInfo);
//		}
		
		
		if(FileService.deleteFile(new File(simProject.getPathDirectory())))
		{
			Project project = simProject.getProject();
			project.removeSimulationProject(simProject);
			updateProject(project);
			
			projectDao.deleteSimulationProject(simProject);
		}


		
		
	}

	@Override
	public Integer createUser(User user) {
		int retValueInsertUser = -1;
		String userRepositoryPath = configuration.getProperty(ConfigurationService.PROPERTIES.PATH_USERS);
		if(user != null) {
			if(existUser(user.getUsername())) {
				return -1;
			}
			try {
				retValueInsertUser = userDao.insertUser(user);
			} catch(HibernateException e) {
				throw new DatabaseServiceException(DatabaseServiceExceptionCode.INSERT_FAILED, e);
			}
			
			if(retValueInsertUser > -1) {
				LOG.debug("User: {} created successfully", user);
				userRepositoryPath += user.getUsername();
				FileService.createDirectories(userRepositoryPath);
				LOG.debug("Create user path on disk: {}", userRepositoryPath);
				UserRepository userRepository = new UserRepository();
				userRepository.setUserId(user.getId());
//				userRepository.setFiles(null);
				userRepository.setUserRepositoryPath(userRepositoryPath);
				Integer retValueInsertUserRepository = userRepositoryDao.insertUserRepository(userRepository);
				LOG.debug("User Repository created "+ ((retValueInsertUserRepository > -1) ? "successfully" : "not successfully"));
			} else {
				LOG.debug("User: {} already exists", user.getUsername());
				User existentUser = getUserByUsername(user.getUsername());
				if(existentUser != null) retValueInsertUser = existentUser.getId();
			}
		}	
		return retValueInsertUser;
	}

	@Override
	public void updateUser(User user) {		
		try {
			userDao.updateUser(user);
		} catch(HibernateException e) {
			throw new DatabaseServiceException(DatabaseServiceExceptionCode.UPDATE_FAILED, e);
		}
	}

	public void updateUserRepository(UserRepository userRepository) {		
		try {
			userRepositoryDao.updateUserRepository(userRepository);
		} catch(HibernateException e) {
			throw new DatabaseServiceException(DatabaseServiceExceptionCode.UPDATE_FAILED, e);
		}
	}
	
	@Override
	public boolean checkFileAccess(String filePath) {
		return true;
	}
	
	
	@Override
	public boolean checkUserRepositoryPathAccess(String directoryPath, Integer userId) {
		UserRepository ur = userRepositoryDao.getUserRepositoryByUserId(userId);
		String urPath = ur.getUserRepositoryPath();
		if(FileService.isPathEqual(urPath, directoryPath)){
			return true;
		}
		return false;
	}
	
	@Override
	public Set<VirtualFile> getFilesOfUser(Integer userId, String fileFormat, boolean recursively) throws MalformedURLException {
		File userDirectory = new File(getUserRepositoryPath(userId));
		boolean access = checkUserRepositoryPathAccess(userDirectory.getAbsolutePath(), userId);
		if(access) {
			return fileService.getVirtualFiles(userDirectory, fileFormat, recursively);
		}
		return null;
	}
	
	@Override
	public UserRepository getUserRepositoryByUserId(Integer userId) {
		return userRepositoryDao.getUserRepositoryByUserId(userId);
	}
	
	@Override
	public String getUserRepositoryPath(Integer userId) {
		LOG.debug("Searching user path for User ID {}", userId);
		UserRepository userRepository = userRepositoryDao.getUserRepositoryByUserId(userId);
		return userRepository.getUserRepositoryPath();
	}

	@Override
	public User getUser(String login, String password) {
		try {
			User user = userDao.getUser(login, password);
			return user;
		} catch (DataAccessException e) {
			LOG.info(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<String> getUserFilePathsFromUrl(String urlPathInfo, String[] files) {
		List<String> filePaths = new ArrayList<String>();
		String path = urlPathInfo.substring(1);
		String userBaseDirectory = configuration.getProperty(ConfigurationService.PROPERTIES.PATH_USERS.toString());
		for(String file : files) {
			String filePath = userBaseDirectory+path+File.separator+path+File.separator+file;
			filePaths.add(filePath);
		}
		return filePaths;
	}

	@Override
	public User getUser(Integer userId) {
		return userDao.getUser(userId);
	}

	@Override
	public boolean existUser(String login) {
		return userDao.existUser(login);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
	
	@Override
	public String getUserFilePathFromUrl(String urlPathInfo, String file) {
		String path = urlPathInfo;
//		String path = urlPathInfo.substring(1);
		String baseDirectory = configuration.getProperty(
				ConfigurationService.PROPERTIES.PATH_BASE);
		String filePath = baseDirectory+path+File.separator+file;
		if(checkFileAccess(filePath)){
			LOG.info("Evaluating file '"+file+"' from URL with path info '"+urlPathInfo+"'... found following file '"+filePath+"'");
			return filePath;
		}
		return null;
	}
	
	@Override
	public InputStream getUserFileStreamFromUrl(String urlPathInfo, String fileUrl) throws FileNotFoundException {
		String localFilePath = getUserFilePathFromUrl(urlPathInfo, fileUrl);
		File file = new File(localFilePath);
		InputStream in = new FileInputStream(file);
		return in;
	}
	
	@Override
	public File getFileOfUser(Integer userId, int fileId) {
		FileInformation fileInformation = getFileInformation(userId, fileId);
		if(fileInformation != null) {
			File file = new File(fileInformation.getFilePath());
			if(file.exists()) {
				return file;
			}
		}
		return null;
	}
	
	@Override
	public List<Session> getExpiredSessions(Date compareDate) {
		return sessionDao.getExpiredSessions(compareDate);
	}
	
	private FileInformation getFileInformation(Integer userId, int fileId) {
		Set<FileInformation> files = userRepositoryDao.getFilesOfUser(userId);
		for(FileInformation fp : files) {
			if(fp.getId().equals(fileId)) {
				return fp;
			}
		}
		return null;
	}
	
	@Override
	public FileInformation getFileInformationFromFileName(Integer userId, String fileName) {
		Set<Integer> fileIds = getFilesOfUser(userId);
		for(Integer fileId : fileIds) {
			FileInformation fi = fileInformationDao.getFileInformation(fileId);
			String filePath = fi.getFilePath();
			File file = new File(filePath);
			if(file.exists()) {
				if(file.getName().equals(fileName)) {
					return fi;
				}
			}
		}
		return null;
	}
	
	private Set<Integer> getFilesOfUser(Integer userId) {
		Set<FileInformation> files = userRepositoryDao.getFilesOfUser(userId);
		Set<Integer> resultSet = new HashSet<Integer>();
		for(FileInformation fp : files) {
			resultSet.add(fp.getId());
		}
		return resultSet;
	}
	
	@Override
	public Integer getUserIdBySessionId(String sessionId) {
		User user = getUserBySessionId(sessionId);
		if(user != null) return user.getId();
		else return null;
	}
	
	@Override
	public User getUserBySessionId(String sessionId) {
		try {
			Session session = sessionDao.getSession(sessionId);
			if(session != null) {
				Integer userId = session.getUserId();
				if(userId != null) {
					return getUser(userId);
				}
			}
		} catch(DatabaseServiceException dse) {
			LOG.info("Can't get a user by session identifier: {}", new Object[]{sessionId});
		}
		return null;
	}
	
	@Override
	public boolean isSessionIdValid(String sessionId) {
//		LOG.trace("Checking session ID: {}",sessionId);
		try {
			Session session =  sessionDao.getSession(sessionId);
			if(session == null) return false;
			Date expireDate = session.getExpireDate();
			Date now = new Date();
			if(expireDate.getTime() < now.getTime()) {
				LOG.debug("Session: {} expired on: {} and will be deleted",
						new Object[]{session.getSessionId(), expireDate});
				deleteSession(session.getSessionId());
				return false;
			}
			return true;
		} catch (DatabaseServiceException dse) { // for example no result set was found
			return false;
		}
	}
	
	@Override
	public Session loginUser(String username, String password) {
		Session session = null;
		if(existUser(username)) {
			User user = getUser(username, password);
			session = createSession(user.getId());			
		} else {
			LOG.debug("User: {} does not exist", username);
		}
		return session;
	}
	
	@Override
	public boolean loginUserWithSessionId(String username, String password, String sessionId) {
		Session session = loginUser(username, password);
		if(session != null) {
			session.setSessionId(sessionId);
			sessionDao.updateSession(session);
			LOG.debug("Created sessionId: {} for user: {}",
					new Object[]{sessionId, username});
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean logoutUser(String username, String password) {
		User user = getUser(username, password);
		if(user != null) {
			int userId = user.getId();
			Session session = sessionDao.getSessionByUserId(userId);
			if(session != null) {
				String sessionId = session.getSessionId();
				return sessionDao.deleteSession(sessionId);
			}
		}
		return false;
	}
	
	@Override
	public boolean logoutUser(int userId) {
		Session session = sessionDao.getSessionByUserId(userId);
		if(session != null) {
			String sessionId = session.getSessionId();
			return deleteSession(sessionId);
		}
		return false;
	}
	
	@Override
	public void saveFileReference(FileInformation file, UserRepository ur) {
		try {
			Integer retValue = fileInformationDao.insertFileEntry(file);
			if(retValue > 0) {
				//file.addUserRepository(ur);
				ur.addFile(file);
				updateUserRepository(ur);
			}
		} catch(HibernateException e) {
			throw new DatabaseServiceException(DatabaseServiceExceptionCode.INSERT_FAILED, e);
		}
	}
	
	@Override
	public void saveFileReferences(Set<FileInformation> newFiles,
			UserRepository ur) {	
			Set<FileInformation> files = new HashSet<FileInformation>(ur.getFiles());
			for(FileInformation fmi : newFiles) {
				try {
					Integer retValue = fileInformationDao.insertFileEntry(fmi);
					if(retValue >= 0) {
						files.add(fmi);
					}
				} catch(HibernateException e) {
					throw new DatabaseServiceException(DatabaseServiceExceptionCode.INSERT_FAILED, e);
				}
			}
			ur.setFiles(files);
			updateUserRepository(ur);
	}
	

	@Override
	public FileInformation uploadFileToUserRepository(Integer userId, File uploadFile) throws IOException {
		String fileId = FileService.createId(userId);
		FileInformation fileInfo = uploadFileToUserRepository(userId, uploadFile, fileId);
		return fileInfo;
	}
	
	@Override
	public FileInformation uploadFileToUserRepository(Integer userId, File uploadFile, String fileId) throws IOException {
		FileInformation file = uploadFileToDirectoryInUserRepository(userId, uploadFile, "/");
//		UserRepository ur = getUserRepositoryByUserId(userId);
//		File userDirectory = new File(ur.getUserRepositoryPath());
//		String fileName = fileService.convertFileName(uploadFile.getName());
//		String filePath = userDirectory+"/"+fileName;
//		LOG.debug("Uploading file of User: {} and file: {} with fileId: {} to user repository path: {}", 
//				new Object[] {userId, uploadFile, fileId, filePath});
//		File to = new File(filePath);
////		copy the file on the disk
//		fileService.copyFile(uploadFile, to);
//		FileInformation file = new FileInformation();
//		file.setFilePath(filePath);
//		file.setUrl(fileService.getVirtualFile(to).getUrl() );
//		fileInformationDao.insertFileEntry(file);
////		file.setFileId(fileId);
////		save file entry it database
//		saveFileReference(file, ur);
		return file;
	}
	
	@Override
	public FileInformation uploadFileToProject(Integer userId, File uploadFile,
			Project proj) throws IOException {
		LOG.debug("Uploading file of User: {} and file: {} with project: {} to user repository path: {}", 
				new Object[] {userId, uploadFile, proj.getName(), proj.getPathDirectory()});		


		File toDir = new File(proj.getPathDirectory());
		String destinationFilePath = toDir.getAbsolutePath()+File.separator+FileService.convertFileName(uploadFile.getName());;
		File toFile = new File(destinationFilePath);
		
//		copy the file on the disk
		if(uploadFile.isDirectory()) {
			FileService.copyDirectory(uploadFile, toFile);
		} else {
			FileService.copyFile(uploadFile, toFile);
		}

		FileInformation file = createFileInformation(userId, uploadFile.getName(), toFile);
		
		
		//file.setProj(proj);
		proj.addFile(file);		
		projectDao.updateProject(proj);

		return file;
	}
	
	@Override
	public FileInformation uploadFileAsStreamToProject(Integer userId, String fileName,
			InputStream uploadFileStream, Project proj) throws IOException {
		LOG.debug("Uploading file of User: {} and file: {} with project: {} to user repository path: {}", 
				new Object[] {userId, fileName, proj.getName(), proj.getPathDirectory()});
		FileInformation file = uploadFileAsStreamToDirectoryInUserRepository(userId, fileName, uploadFileStream, proj.getName());		
		
		proj.addFile(file);		
		projectDao.updateProject(proj);
		return file;
	}
	
	@Override
	public FileInformation.TYPE getFileTypeByExtension(String ext) {
		return FileInformation.TYPE.findByAbbreviation(ext);
	}
	
	@Override
	public FileInformation.TYPE getFileTypeByFilePathOrFileName(String pathOrFileName) {
		String extension = pathOrFileName.substring(pathOrFileName.lastIndexOf(".")+1);
		return getFileTypeByExtension(extension);
	}
	
	
	@Override
	public FileInformation uploadFileToUserRepository(Integer userId, String fileName, InputStream is) throws IOException {
//		long time = System.currentTimeMillis();
//		String fileName = userId+"_"+time+".tmp";
		String tempDir = configuration.getProperty(ConfigurationService.PROPERTIES.PATH_TEMP_DIRECTORY);
		File outFile = new File(tempDir,fileName);
		OutputStream out = new FileOutputStream(outFile);
		
		int read = 0;
		byte[] bytes = new byte[1024];
	 
		while ((read = is.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
	 
		is.close();
		out.close();
		
		return uploadFileToUserRepository(userId, outFile);
	}
	
	@Override
	public FileInformation uploadFileToDirectoryInUserRepository(Integer userId,
			File uploadFile, String relativeFilePathInUserRepository)
			throws IOException {
		UserRepository ur = getUserRepositoryByUserId(userId);
		LOG.debug("Upload file: {} to user repository: {} of user: {} to relative path: {}", 
				new Object[]{uploadFile, ur.getUserRepositoryPath(), userId, relativeFilePathInUserRepository});
		File userDirectory = new File(ur.getUserRepositoryPath());
//		
		String fileName = FileService.convertFileName(uploadFile.getName());
		String absolutePathFromRelative = userDirectory.getAbsolutePath()+File.separator+relativeFilePathInUserRepository;

		File toDir = new File(absolutePathFromRelative);
		String destinationFilePath = toDir.getAbsolutePath()+File.separator+fileName;
		File toFile = new File(destinationFilePath);
//		copy the file on the disk
		if(uploadFile.isDirectory()) {
			FileService.copyDirectory(uploadFile, toFile);
		} else {
			FileService.copyFile(uploadFile, toFile);
		}
		return createFileInformation(userId, ur, uploadFile.getName(), toFile);

	}
	
	
	private FileInformation createFileInformation(Integer userId, UserRepository ur, String uploadFileName, File toFile) throws MalformedURLException {
		FileInformation file = new FileInformation();
		file.setName(toFile.getName());
		String generalFilePath = FileService.convertWindowsPathToUnix(toFile.getAbsolutePath());
		file.setFilePath(generalFilePath);
		file.setOwnerId(userId);
		file.setUrl(fileService.getVirtualFile(toFile).getUrl());
		TYPE fileType = getFileTypeByFilePathOrFileName(uploadFileName);
		file.setFileType(fileType);
		Integer fileInfoId = fileInformationDao.insertFileEntry(file);
		LOG.debug("Saved file information in database. FileInformationIdentifier is: {}",fileInfoId);
		
		return file;
	}
	
	private FileInformation createFileInformation(Integer userId,  String uploadFileName, File toFile) throws MalformedURLException {
		FileInformation file = new FileInformation();
		file.setName(toFile.getName());
		String generalFilePath = FileService.convertWindowsPathToUnix(toFile.getAbsolutePath());
		file.setFilePath(generalFilePath);
		file.setOwnerId(userId);
		file.setUrl(fileService.getVirtualFile(toFile).getUrl());
		TYPE fileType = getFileTypeByFilePathOrFileName(uploadFileName);
		file.setFileType(fileType);	
		Integer fileInfoId = fileInformationDao.insertFileEntry(file);
		LOG.debug("Saved file information in database. FileInformationIdentifier is: {}",fileInfoId);
		
		return file;
	}
	
	@Override
	public FileInformation uploadFileAsStreamToDirectoryInUserRepository(Integer userId, String inputFileName,
			InputStream stream, String relativeFilePathInUserRepository)
			throws IOException {
		UserRepository ur = getUserRepositoryByUserId(userId);
		LOG.debug("Upload stream with input file name: {} to user repository: {} of user: {} to relative path: {}", 
				new Object[]{inputFileName, ur.getUserRepositoryPath(), userId, relativeFilePathInUserRepository});
		File userDirectory = new File(ur.getUserRepositoryPath());
		
		String fileName = null;
		if(StringUtils.isNotEmpty(inputFileName)) {
			fileName = FileService.convertFileName(inputFileName);
		} else {
			fileName = UUID.randomUUID().toString();
		}
		String absolutePathFromRelative = userDirectory.getAbsolutePath()+File.separator+relativeFilePathInUserRepository;
		File toDir = new File(absolutePathFromRelative);
		String destinationFilePath = toDir.getAbsolutePath()+File.separator+fileName;
		File toFile = new File(destinationFilePath);
//		copy to file on the disk
		FileService.copyToFile(stream, toFile, false);
		return createFileInformation(userId, ur, fileName, toFile);
	}

	public void setFileInformationDao(FileInformationDao fileInformationDao) {
		this.fileInformationDao = fileInformationDao;
	}

	public void setUserRepositoryDao(UserRepositoryDao userRepositoryDao) {
		this.userRepositoryDao = userRepositoryDao;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public void setConfiguration(ConfigurationService configuration) {
		this.configuration = configuration;
		sessionTimeInMinutes = this.configuration.getProperty(ConfigurationService.PROPERTIES.SESSION_TIME_MINUTES);
		LOG.debug("Load Session time from Configuration: "+sessionTimeInMinutes);
	}

	public void setSessionDao(SessionDao sessionDao) {
		this.sessionDao = sessionDao;
	}		
	
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public Set<Project> getProjectsOfUser(Integer userId) {
		
		UserRepository repository =  userRepositoryDao.getUserRepositoryByUserId(userId);
		return repository.getProjects();

	}

	@Override
	public String createDirectoryInUserRepository(Integer userId, String directoryName) {
		// TODO Auto-generated method stub
		UserRepository repository =  userRepositoryDao.getUserRepositoryByUserId(userId);
		String strDirectoryPath = repository.getUserRepositoryPath() + File.separator + directoryName;
		
		if(FileService.createDirectory(new File(strDirectoryPath)))
			return strDirectoryPath;
		else
			return new String("");		
		
	}
	
	public void insertProject(Project proj)
	{
		projectDao.insertProject(proj);
	}
	
	public void insertSimProject(SimulationProject simProj)
	{
		projectDao.insertSimProject(simProj);
	}
	
	public void updateProject(Project proj)
	{
		projectDao.updateProject(proj);
	}
	
	
	public void deleteProject(Project project, UserRepository repo)
	{
		if(FileService.deleteFile(new File(project.getPathDirectory())))
		{		
			repo.removeProject(project);
			
			updateUserRepository(repo);		
			
			projectDao.deleteProject(project);
			
		}
	}
	
	public void deleteProject(Project project)
	{
		if(FileService.deleteFile(new File(project.getPathDirectory())))
		{	
			UserRepository repo = project.getUserRepository();
			if(repo != null)
			{
				repo.removeProject(project);
				
				updateUserRepository(repo);	
			}	
			
			
			projectDao.deleteProject(project);
			
		}
	}
	
	public void deleteFile(FileInformation fileInfo)
	{
		if(FileService.deleteFile(new File(fileInfo.getFilePath())))
		{		
			
			fileInformationDao.deleteFileInformation(fileInfo);
			String filePath = fileInfo.getFilePath();
			File file = new File(filePath);
			file.delete();
		}
	}

	@Override
	public FileInformation replaceFileInUserRepository(Integer userId,
			File newFile, FileInformation oldFile) throws IOException {
		String oldFilePath = oldFile.getFilePath();
		FileService.copyFile(newFile, new File(oldFilePath));
		return oldFile;
	}

	@Override
	public Project getProjectByUserIdAndFileInformation(Integer userId,
			FileInformation file) {
		Set<Project> projects = getProjectsOfUser(userId);
		for(Project project : projects) {
			Set<FileInformation> files = project.getFiles();
			for(FileInformation fileInProject : files) {
				if(file.getId().intValue() == fileInProject.getId().intValue()) return project;
			}
		}
		return null;
	}
	
	/**
	 * TODO javadoc
	 * @param project
	 * @param simulationProject
	 * @return SimulationProject ID
	 */
	@Override
	public Integer insertSimulationProject(Project project, SimulationProject simulationProject) {
		
		projectDao.insertSimProject(simulationProject);		
		project.addSimulationProject(simulationProject);
		projectDao.updateProject(project);
		return simulationProject.getId();
	}
	
	@Override
	public void updateSimulationProject(SimulationProject simProject) {
		projectDao.updateSimulationProject(simProject);	
	}

	@Override
	public Set<FileInformation> uploadFilesToSimulationProject(Integer userId, SimulationProject simulationProject, 
			String relativeDirPath, List<File> files) throws IOException {
		Set<FileInformation> fileInfos = new HashSet<FileInformation>();
		for(File file : files) {
			FileInformation fileInfo = uploadFileToSimulationProject(userId, simulationProject, relativeDirPath, file);
			if(fileInfo != null) fileInfos.add(fileInfo);
		}
		return fileInfos;
	}
	
	@Override
	public FileInformation uploadFileAsStreamToSimulationProject(Integer userId, SimulationProject simulationProject, 
			String relativePath, InputStream stream, String inputFileName) throws IOException {
		//Project parentProject = projectDao.getProjectBySimulationProject(simulationProject);
		Project parentProject = simulationProject.getProject();
		String filePath = parentProject.getName()+File.separator+simulationProject.getName()+File.separator+relativePath;
		FileInformation fileInfo = uploadFileAsStreamToDirectoryInUserRepository(userId, inputFileName, stream, filePath);
		updateSimulationProject(simulationProject);
		return fileInfo;
	}

	@Override
	public FileInformation uploadFileToSimulationProject(Integer userId, SimulationProject simulationProject, String relativePath, File file) throws IOException {
		//Project parentProject = projectDao.getProjectBySimulationProject(simulationProject);		
		Project parentProject = simulationProject.getProject();
		String filePath = parentProject.getName()+File.separator+simulationProject.getName()+File.separator+relativePath;
		FileInformation fileInfo = uploadFileToDirectoryInUserRepository(userId, file, filePath);
		updateSimulationProject(simulationProject);
		return fileInfo;
	}

	@Override
	public Set<Project> listProjectsByUserId(Integer userId) {
		UserRepository ur = userRepositoryDao.getUserRepositoryByUserId(userId);
		return ur.getProjects();
	}

	@Override
	public SimulationProject getSimulationProjectById(Integer id) {
		return projectDao.getSimulationProjectById(id);
	}

	@Override
	public Project getProjectOfSimulationProject(Integer userId,
			SimulationProject simProject) {
		UserRepository ur = getUserRepositoryByUserId(userId);
		Set<Project> projects = ur.getProjects();
		for(Project project : projects) {
			Set<SimulationProject> simProjectsOfProject = project.getSimProjects();
			for(SimulationProject simProjectOfProject : simProjectsOfProject) {
				if(simProjectOfProject.getId().intValue() == simProject.getId().intValue()) return project;
			}
		}
		return null;
	}	
	
}
