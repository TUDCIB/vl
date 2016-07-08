package de.tudresden.bau.cib.vl.core.database.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;

import de.tudresden.bau.cib.vl.core.database.dao.SessionDao;
import de.tudresden.bau.cib.vl.core.database.domain.Session;

public class SessionDaoImpl implements SessionDao {
	
	private SessionFactory sessionFactory;
	private static final String TABLE = Session.class.getSimpleName();

	@Override
	public Integer insertSession(Session session) {
    	Integer ret = (Integer) sessionFactory.getCurrentSession().save(session);
    	return ret;
	}

	@Override
	public Session getSessionByUserId(Integer userId) {	
		String hql = "from "+TABLE+" as s where s.userId = :userId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId);
		
		@SuppressWarnings("unchecked")
		List<Session> resultList = query.list();
		if(resultList.size() >= 1) {
			return resultList.get(0);
		} 
        return null;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean deleteSession(String sessionId) {
 		String hql = "from "+TABLE+" as s where s.sessionId = :sessionId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("sessionId", sessionId);

		Session s = null;
		
		@SuppressWarnings("unchecked")
		List<Session> list = query.list();
		if (list.size() == 0) {
			throw new DataAccessResourceFailureException("No session with ID "+sessionId+" available.");
		} else if (list.size() == 1) {
			s = list.get(0);
		} else {
			throw new DataIntegrityViolationException(
					"There exists more sessions with the same ID.");
		}
		sessionFactory.getCurrentSession().delete(s);
		return true;
	}

	@Override
	public Session getSession(String sessionId) {
		String hql = "from "+TABLE+" as s where s.sessionId = :sessionId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("sessionId", sessionId);
		
		@SuppressWarnings("unchecked")
		List<Session> resultList = query.list();
		if (resultList.size() == 0) {
			throw new DataAccessResourceFailureException("No session with sessionId '"+sessionId+"' available.");
		}
		if(resultList.size() >= 1) {
			return resultList.get(0);
		} 
        return null;
	}

	@Override
	public void updateSession(Session session) {
		sessionFactory.getCurrentSession().update(session);
	}

	@Override
	public List<Session> getExpiredSessions(Date compareDate) {
		String hql = "from "+TABLE+" as s where s.expireDate <= :now";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("now", compareDate);
		
		@SuppressWarnings("unchecked")
		List<Session> resultList = query.list();
		if(resultList.size() >= 1) {
			return resultList;
		} 
        return null;
	}

}
