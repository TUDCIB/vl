package de.tudresden.bau.cib.vl.core.database.dao;


import java.util.Date;
import java.util.List;

import de.tudresden.bau.cib.vl.core.database.domain.Session;

public interface SessionDao {

    /**
     * @param userId
     * @param expireDate
     * @return The session identifier.
     */
    Integer insertSession(Session session);
    boolean deleteSession(String sessionId);
	Session getSession(String sessionId);
	Session getSessionByUserId(Integer userId);
	void updateSession(Session session);
	List<Session> getExpiredSessions(Date compareDate);
}
