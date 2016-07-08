//package de.tudresden.bau.cib.vl.core.platform.job;
//
//import java.util.Date;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.tudresden.bau.cib.vl.core.database.domain.Session;
//import de.tudresden.bau.cib.vl.core.service.UserService;
//
//public class SessionExpiredTask {
//
//	private UserService userService;
//	
//	private static final Logger LOG = LoggerFactory.getLogger(SessionExpiredTask.class);
//	
//	
//	public void destroySessions() {
//		Date date = new Date();
//		LOG.trace("Executing SessionJob on: {}", date);
//		List<Session> sessions = userService.getExpiredSessions(date);
//		if(sessions != null) {
//			for(Session session : sessions) {
//				String sessionId = session.getSessionId();
//				boolean isValid = userService.isSessionIdValid(sessionId);
//				if(!isValid) {
//					LOG.trace("Session: {} expired on: {} and will be deleted",
//							new Object[]{session.getSessionId(), session.getExpireDate()});
//	//				Session expired - delete the old session
//					boolean destroyed = userService.deleteSession(sessionId);
//					LOG.trace("Session: {} was destroyed {}",
//							new Object[]{sessionId, destroyed ? "successfully" : "not successfully"});
//				}
//			}
//		}
//		LOG.trace("SessionJob finished on: {}", new Date());
//	}
//
//
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}
//
//}
