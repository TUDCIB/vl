package de.tudresden.bau.cib.vl.core.database.exception;

import org.hibernate.HibernateException;

public class DatabaseServiceException extends HibernateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1777430825842877606L;

	DatabaseServiceExceptionCode databaseServiceExceptionCode;
	
	private Exception hiddenException;
	private String message;

	public DatabaseServiceException(String error, Exception exception) {
		super(error);
		hiddenException = exception;
	}

	public DatabaseServiceException(DatabaseServiceExceptionCode databaseServiceExceptionCode, Exception exception) {
		super(databaseServiceExceptionCode.getMessage());
		this.databaseServiceExceptionCode = databaseServiceExceptionCode;
		this.hiddenException = exception;
	}
	
	public DatabaseServiceException(			
			DatabaseServiceExceptionCode code,
			String details,
			Exception exception) {
		this(code, exception);
		this.message = code.getMessage() +" Details: "+details;
	}

	public Exception getHiddenException() {
		return (hiddenException);
	}
	
	public String getMessage() {
		return (message == null) ? (databaseServiceExceptionCode.getMessage()) : databaseServiceExceptionCode.getMessage()+": "+message;
	}

}
