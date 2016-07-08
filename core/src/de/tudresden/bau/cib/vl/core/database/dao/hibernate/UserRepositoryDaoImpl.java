package de.tudresden.bau.cib.vl.core.database.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import de.tudresden.bau.cib.vl.core.database.dao.UserRepositoryDao;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.UserRepository;

public class UserRepositoryDaoImpl implements UserRepositoryDao {
	
	private SessionFactory sessionFactory;
	private static final String TABLE = UserRepository.class.getSimpleName();
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer insertUserRepository(UserRepository userRepository) {
    	Integer ret = (Integer) sessionFactory.getCurrentSession().save(userRepository);
        return ret;
	}

	@Override
	public UserRepository getUserRepositoryByRepositoryId(int userRepositoryId) {
		String hql = "FROM "+TABLE+" AS lm WHERE lm.userRepositoryId = :urId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("urId", userRepositoryId);
		
		@SuppressWarnings("unchecked")
		List<UserRepository> resultList = query.list();
		if(resultList.size() >= 1) {
			UserRepository ur = resultList.get(0);
			return ur;
		} 
        return null;
	}
	
	@Override
	public UserRepository getUserRepositoryByUserId(Integer userId) {
		String hql = "FROM "+TABLE+" AS lm WHERE lm.userId = :userId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId);
		
		@SuppressWarnings("unchecked")
		List<UserRepository> resultList = query.list();
		if(resultList.size() >= 1) {
			UserRepository ur = resultList.get(0);
			return ur;
		} 
        return null;
	}

	@Override
	public Set<FileInformation> getFilesOfUser(Integer userId) {
		String hql = "FROM "+TABLE+" AS ur WHERE ur.userId = :userId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId);
		
		@SuppressWarnings("unchecked")
		List<UserRepository> list = query.list();
		if(list.size() >= 1) {
			UserRepository ur = list.get(0);
			return ur.getFiles();
		}
		return null;
	}

	@Override
	public void updateUserRepository(UserRepository userRepository) {
		sessionFactory.getCurrentSession().saveOrUpdate(userRepository);
		
	}

	@Override
	public List<UserRepository> getUserRepositories() {
		String hql = "FROM "+TABLE;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<UserRepository> resultList = query.list();
		if(resultList.size() >= 1) {
			return resultList;
		} 
        return null;
	}

}
