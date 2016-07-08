package de.tudresden.bau.cib.vl.core.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectJAroundTraceInterceptor {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AspectJAroundTraceInterceptor.class);

	@Around("execution(* de.tudresden.bau.cib.vl..*.*(..))")
	public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.trace("Entering "+pjp.getStaticPart().toLongString()+"...");
		try {
			return pjp.proceed();
		} catch (Throwable ex) {
			LOGGER.trace("Exception "+ex.getMessage());
			throw ex;
		} finally {
			LOGGER.trace("Exiting "+pjp.getStaticPart().toLongString()+"...");
		}
	}
}
