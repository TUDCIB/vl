package de.tudresden.bau.cib.vl.core.aspects;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TraceInterceptor implements MethodInterceptor {

	private static Logger LOGGER = LoggerFactory.getLogger(TraceInterceptor.class);
	
	@Around("execution(* de.tudresden.bau.cib.vl..*.*(..))")
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("Inside TraceInterceptor");
		Object[] objects = invocation.getArguments();
		String arguments = objects.length == 0 ? "" : " with arguments: ";
		for(Object o : objects) {
			arguments += "'"+o.toString()+"'";
		}
		LOGGER.trace("Entering "+invocation.getMethod().getName()+arguments+"...");
		
		try {
			Object rval = invocation.proceed();
			if(rval != null)
				LOGGER.trace("...return value = "+rval+"..-");
		} catch (Throwable ex) {
			LOGGER.trace("Exception "+ex.getMessage());
			throw ex;
		} finally {
			LOGGER.trace("Exiting "+invocation.getMethod().getName()+"...");
		}
		return null;
	}

}
