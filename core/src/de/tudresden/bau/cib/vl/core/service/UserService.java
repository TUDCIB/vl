package de.tudresden.bau.cib.vl.core.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation.TYPE;
import de.tudresden.bau.cib.vl.core.database.domain.OptimizationSimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.Session;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.User;
import de.tudresden.bau.cib.vl.core.database.domain.UserRepository;
import de.tudresden.bau.cib.vl.core.util.VirtualFile;

//propagation: create a new transaction for each user, isolation: degree of isolation to other transactions, read-only: useful for Hibernate
@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.REPEATABLE_READ)
public interface UserService {

	List<User> getAllUsers();
	
	public void deleteUser(int userId);
	
	/**
	 * @param user
	 * @return Row Id of the user in the database.
	 */
	public Integer createUser(User user);
	
	void updateUser(User user);
	
	void updateUserRepository(UserRepository userRepository);

	User getUser(String username, String password);
	
	User getUser(Integer userId);
	
	User getUserByUsername(String username);
	
	boolean existUser(String login);

	boolean checkUserRepositoryPathAccess(String directoryPath, Integer userId);

	Set<VirtualFile> getFilesOfUser(Integer userId, String fileFormat,
			boolean recursively) throws MalformedURLException;

	UserRepository getUserRepositoryByUserId(Integer userId);
	
	String getUserRepositoryPath(Integer userId);

	/**
	 * Uploads a file to the system and reference a user with it through the user repository.
	 * This method is qualified for all files. It generates a new file identifier.
	 * @param userId The user identifier.
	 * @param uploadFile The file which will be copied to the user repository.
	 * @return Returns the file information of the uploaded file.
	 * @throws IOException
	 */
	FileInformation uploadFileToUserRepository(Integer userId, File uploadFile)
			throws IOException;

	/**
	 * Uploads a file to the system and reference a user with it through the user repository.
	 * This method is qualified for all files.
	 * @param userId The user identifier.
	 * @param uploadFile The file which will be copied to the user repository.
	 * @param fileId The used file identifier.
	 * @return Returns the file information of the uploaded file.
	 * @throws IOException
	 */
	FileInformation uploadFileToUserRepository(Integer userId, File uploadFile,
			String fileId) throws IOException;
	
	
	/**
	 * Uploads a file to the project. This method is qualified for all files.
	 * @param userId The user identifier.
	 * @param uploadFile The file which will be copied to the user repository.
	 * @param proj project where files belong to
	 * @return Returns the file information of the uploaded file.
	 * @throws IOException
	 */
	FileInformation uploadFileToProject(Integer userId, File uploadFile,
			Project proj) throws IOException;
	
	/**
	 * Uploads an input stream as file to the project. This method is qualified for all files.
	 * @param userId The user identifier.
	 * @param fileName The name of the new file.
	 * @param uploadFileStream The file stream which will be copied to the user repository.
	 * @param proj project where files belong to
	 * @return Returns the file information of the uploaded file.
	 * @throws IOException
	 */
	FileInformation uploadFileAsStreamToProject(Integer userId, String fileName, InputStream uploadFileStream,
			Project proj) throws IOException;
	

	User getUserBySessionId(String sessionId);


	/**
	 * Uploads a file to the system and reference a user with it through the user repository.
	 * This method is qualified for web files. It generates a new file identifier.
	 * @param userId The user identifier.
	 * @param fileName The file name which will be set after copying it to the file system.
	 * @param is Input stream which represents the file.
	 * @return Returns the file information of the uploaded file.
	 * @throws IOException
	 */
	FileInformation uploadFileToUserRepository(Integer userId, String fileName,
			InputStream is) throws IOException;
	
	FileInformation uploadFileToSimulationProject(Integer userId, SimulationProject simulationProject, String relativePath, File file) throws IOException;
	Set<FileInformation> uploadFilesToSimulationProject(Integer userId, SimulationProject simulationProject, String relativeDirPath, List<File> files) throws IOException;

	FileInformation getFileInformationFromFileName(Integer userId,
			String fileName);

	/**
	 * TODO Check Permissions! -> Vielleicht auch mit Aspekt
	 * @param filePath
	 * @return
	 */
	boolean checkFileAccess(String filePath);

	/**
	 * Convert URL to file path on local disk.
	 * <br/>
	 * <b>Example</b>: http://127.0.0.1:49162/hesmos/file<b>/ken?file=bla.ifc</b><br/>
	 * 
	 * @param urlPathInfo In the example it is <b>'/ken'</b>
	 * @param file In the example <b>bla.ifc</b>
	 * @return The file path where the file exists on local disk.
	 */
	String getUserFilePathFromUrl(String urlPathInfo, String file);

	/**
	 * Convert URL to file input stream from local disk.
	 * <br/>
	 * <b>Example</b>: http://127.0.0.1:49162/hesmos/file<b>/ken?file=bla.ifc</b><br/>
	 * 
	 * @param urlPathInfo
	 * @param fileUrl
	 * @return
	 * @throws FileNotFoundException
	 */
	InputStream getUserFileStreamFromUrl(String urlPathInfo, String fileUrl)
			throws FileNotFoundException;

//	/**
//	 * This method parses a file and create the references to a user.
//	 * 
//	 * @param userId
//	 * @param filePath
//	 * @return
//	 * @throws ManagementException
//	 */
//	Model parseFile(Integer userId, String filePath) throws ServiceException;
//
//	/**
//	 * This method is only for parsing file without creating references to users.
//	 * 
//	 * @param filePath
//	 * @return
//	 * @throws ManagementException
//	 */
//	Model parseFile(String filePath) throws ServiceException;
//
//	Model parseFileURL(URL url) throws ServiceException;

	boolean deleteFile(int userId, File file);

	boolean deleteFiles(int userId, File[] files);

	/**
	 * Convert URL to file paths on local disk.
	 * <br/>
	 * <b>Example</b>: http://127.0.0.1:49162/hesmos/file<b>/ken?file=bla.ifc&blubb.ifc</b><br/>
	 * 
	 * @param urlPathInfo In the example it is <b>'/ken'</b>
	 * @param params In the example <b>[bla.ifc,blubb.ifc]</b>
	 * @return The file path where the file exists on local disk.
	 */
	List<String> getUserFilePathsFromUrl(String urlPathInfo, String[] files);

	File getFileOfUser(Integer userId, int fileId);

	/**
	 * Uploads a file to a directory in the user repository.
	 * @param userId The user identifier.
	 * @param uploadFile The file which will be copied to the user repository.
	 * @param relativeFilePathInUserRepository Relative file path in the user repository, e.g. user repository have the path 'D:/urXYZ' and given relativeFilePathInUserRepository is './climate' refers to the directory 'D:/urXYZ/climate'
	 * @return Returns the file information of the uploaded directory.
	 * @throws IOException
	 */
	FileInformation uploadFileToDirectoryInUserRepository(Integer userId,
			File uploadFile, String relativeFilePathInUserRepository)
			throws IOException;
	
	FileInformation replaceFileInUserRepository(Integer userId, File newFile, FileInformation oldFile) throws IOException;

	/**
	 * Save the references of multiple files to a user.
	 * <br/>
	 * {@link #updateUserRepository(UserRepository)} is called automatically.
	 * 
	 * @param files
	 * @param ur
	 */
	void saveFileReferences(Set<FileInformation> newFiles, UserRepository ur);

	/**
	 * Save the references of a file to a user repository.
	 * 
	 * @param file
	 * @param ur
	 */
	void saveFileReference(FileInformation file, UserRepository ur);
	
	public Set<Project> listProjectsByUserId(Integer userId);

	/**
	 * Starts the session for a user if validation is successfully. 
	 * Creates the user runtime model or load the one of a previous session.
	 * @param username 	User username
	 * @param password 	User password
	 * @return Session identifier of the session.
	 */
	Session loginUser(String username, String password);

	boolean logoutUser(String username, String password);

	boolean logoutUser(int userId);

	Session createSession(Integer userId);

	Integer getUserIdBySessionId(String sessionId);

	boolean isSessionIdValid(String sessionId);

	boolean loginUserWithSessionId(String username, String password,
			String sessionId);

	List<Session> getExpiredSessions(Date compareDate);

	boolean deleteSession(String sessionId);
	
	Project getProjectByUserIdAndFileInformation(Integer userId, FileInformation file);

	Set<Project> getProjectsOfUser(Integer userId);
	
	Project getProjectOfSimulationProject(Integer userId, SimulationProject simProject);
	
	SimulationProject getSimulationProjectById(Integer id);
	
	String createDirectoryInUserRepository(Integer userId, String directoryName);
	
	void insertProject(Project proj);

	void updateProject(Project proj);

	void deleteProject(Project project, UserRepository repo);
	void deleteProject(Project project);

	void deleteFile(FileInformation selectedFile);
	
	Integer insertSimulationProject(Project project, SimulationProject simulationProject);

	void updateSimulationProject(SimulationProject simProject);

	TYPE getFileTypeByExtension(String ext);

	TYPE getFileTypeByFilePathOrFileName(String pathOrFileName);

	FileInformation uploadFileAsStreamToDirectoryInUserRepository(
			Integer userId, String inputFileName, InputStream stream,
			String relativeFilePathInUserRepository) throws IOException;

	FileInformation uploadFileAsStreamToSimulationProject(Integer userId,
			SimulationProject simulationProject, String relativePath,
			InputStream stream, String inputFileName) throws IOException;
	
	Project createProject(String projectName, UserRepository repo);

	/**
	 * Creates a simulation project and add it to the parent project (and insert it in the database).
	 * @param project The parent project.
	 * @param simProjectName Simulation project name.
	 * @return The newly created simulation project.
	 */
	SimulationProject createSimulationProject(Project project, String simProjectName);
	
	OptimizationSimulationProject createOptimizationSimulationProject(
			Project project, String simProjectName);
	
	/**
	 * Deletes a simulation project and updates the parent project.
	 * @param userId
	 * @param simProject
	 * @return The updated parent project.
	 */
	void deleteSimulationProject(Integer userId, SimulationProject simProject);

	void deleteSimulationProject(SimulationProject simProject);

}
