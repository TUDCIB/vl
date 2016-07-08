package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;

public class StdDatabaseDescriptor extends ResourceDescriptor implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1533487127896227753L;
	private String connectionURL;
	private String connectionURL2;
	private String connectionDriverName;
	private String sqlStatement;
	private String sqlParameters;

	public StdDatabaseDescriptor() {
		super.setResourceName("StdDbResource");
		super.setResourceClassName("ises.dataframework.stp.resources.StdDbResource");
	}

	public void setConnectionDetails(String userName, String userPassword, String url, String driver){
		setUserName(userName);
		setUserPassword(userPassword);
		this.connectionURL = url;
		this.connectionDriverName = driver;
	}
	
	
    public String getConnectionURL() {
        return this.connectionURL;
    }
    
    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

	public String getConnectionURL2() {
		return connectionURL2;
	}

	public void setConnectionURL2(String connectionURL2) {
		this.connectionURL2 = connectionURL2;
	}

    public String getConnectionDriverName() {
        return this.connectionDriverName;
    }
    
    /** 
     * Sets the JDBC driver class. 
     */
    public void setConnectionDriverName(String connectionDriverName) {
        this.connectionDriverName = connectionDriverName;
    }

	 /** 
     * @return the DB sql statement
     */
    public String getSqlStatement() {
        return this.sqlStatement;
    }
    
    /** 
     * Sets the DB sql statement
     */
    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }
    
	public String getSqlParameters() {
		return sqlParameters;
	}

	public void setSqlParameters(String sqlParameters) {
		this.sqlParameters = sqlParameters;
	}



	@Override
	public String toString() {
		return "StdDatabaseDescriptor ["+super.toString()+", userName=" + getUserName()
				+ ", userPassword=" + getUserPassword() + ", connectionURL="
				+ connectionURL + ", connectionURL2=" + connectionURL2
				+ ", connectionDriverName=" + connectionDriverName
				+ ", sqlStatement=" + sqlStatement + "]";
	}

}
