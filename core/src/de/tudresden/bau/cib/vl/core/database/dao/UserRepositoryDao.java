package de.tudresden.bau.cib.vl.core.database.dao;

import java.util.List;
import java.util.Set;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.UserRepository;

public interface UserRepositoryDao {

	Integer insertUserRepository(UserRepository userRepository);
	
	UserRepository getUserRepositoryByRepositoryId(int userRepositoryId);
	
	UserRepository getUserRepositoryByUserId(Integer userId);

	/**
	 * @param userId
	 * @return Set of file identifiers of the user.
	 */
	Set<FileInformation> getFilesOfUser(Integer userId);
	
	void updateUserRepository(UserRepository userRepository);

	List<UserRepository> getUserRepositories();
}
