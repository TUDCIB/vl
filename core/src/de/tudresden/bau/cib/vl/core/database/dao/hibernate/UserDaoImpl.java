package de.tudresden.bau.cib.vl.core.database.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;

import de.tudresden.bau.cib.vl.core.database.dao.UserDao;
import de.tudresden.bau.cib.vl.core.database.domain.User;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	private static final String TABLE = User.class.getSimpleName();
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<User> getUserList() {
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		return list;
	}

	@Override
	public User getUser(Integer id) {
		User found = (User) sessionFactory.getCurrentSession().get(User.class,
				id);
        return found;
	}
	
	@Override
	public User getUser(String username, String password) {
		String hql = "from "+TABLE+" as u where u.username = :username AND u.password = :password";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		@SuppressWarnings("unchecked")
		List<User> resultList = query.list();
		if (resultList.size() == 0) {
			throw new DataAccessResourceFailureException("No user with username '"+username+"' available.");
		}
		if(resultList.size() >= 1) {
			return resultList.get(0);
		} 
        return null;
	}

	@Override
	public Integer insertUser(User user) {
    	Integer ret = (Integer) sessionFactory.getCurrentSession().save(user);
        return ret;
	}

	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void deleteUser(int userId) {
		String hql = "from "+TABLE+" as u where u.userId = :userId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId);

		User u = null;
		
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		if (list.size() == 0) {
			throw new DataAccessResourceFailureException("No user with ID "+userId+" available.");
		} else if (list.size() == 1) {
			u = list.get(0);
		} else {
			throw new DataIntegrityViolationException(
					"There exists more users with the same ID.");
		}
		sessionFactory.getCurrentSession().delete(u);
	}

	@Override
	public boolean existUser(String username) {
		String hql = "from "+TABLE+" as u where u.username = :username";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		
		@SuppressWarnings("unchecked")
		List<User> resultList = query.list();
		if(resultList.size() >= 1) {
			return true;
		} 
        return false;
	}

//	@Override
//	public User getUser(String userId) {
//		String hql = "from "+TABLE+" as u where u.userId = :userId";
//		Query query = sessionFactory.getCurrentSession().createQuery(hql);
//		query.setParameter("userId", userId);
//		
//		@SuppressWarnings("unchecked")
//		List<User> resultList = query.list();
//		if(resultList.size() >= 1) {
//			return resultList.get(0);
//		} 
//        return null;
//	}

	@Override
	public User getUserByUsername(String username) {
		String hql = "from "+TABLE+" as u where u.username = :username";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		
		@SuppressWarnings("unchecked")
		List<User> resultList = query.list();
		if(resultList.size() >= 1) {
			return resultList.get(0);
		} 
        return null;
	}
}
