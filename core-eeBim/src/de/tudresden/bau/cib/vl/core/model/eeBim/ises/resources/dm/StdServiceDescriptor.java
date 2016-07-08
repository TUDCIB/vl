package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm;

public class StdServiceDescriptor extends ResourceDescriptor implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8991853183978187415L;
	private String targetNamespace; //name of wsdl:service
	private String serviceName; //name of wsdl:service
	private String portName; // of wsdl:porttype
	private String wsdlLocation; //relative or absolute URL
	private String serviceEndpointInterface;
	private String operationName; //name of wsdl:operation
	private String operationParmeters; //parameters to wsdl:operation
	private String serviceType; //SOAP, REST XML-RPC

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public StdServiceDescriptor() {
		super.setResourceName("StdServiceResource");
		super.setResourceClassName("ises.dataframework.stp.resources.StdServiceResource");
	}

	public void setConnectionDetails(String userName, String passWord, String wsdlLocation){
		super.setUserName(userName);
		super.setUserPassword(passWord);
		this.wsdlLocation = wsdlLocation;
	}
	public void setConnectionDetails( String wsdlLocation){
		this.wsdlLocation = wsdlLocation;
	}



	public String getTargetNamespace() {
		return targetNamespace;
	}

	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getWsdlLocation() {
		return wsdlLocation;
	}

	public void setWsdlLocation(String wsdlLocation) {
		this.wsdlLocation = wsdlLocation;
	}

	public String getServiceEndpointInterface() {
		return serviceEndpointInterface;
	}

	public void setServiceEndpointInterface(String serviceEndpointInterface) {
		this.serviceEndpointInterface = serviceEndpointInterface;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getOperationParmeters() {
		return this.getPropertiesAsString();
	}

	public void setOperationParmeters(String operationParmeters) {
		this.operationParmeters = operationParmeters;
		this.setPropertiesAsString(operationParmeters);
	}

	@Override
	public String toString() {
		return "StdServiceDescriptor ["+super.toString()+", targetNamespace="
				+ targetNamespace + ", serviceName=" + serviceName
				+ ", portName=" + portName + ", wsdlLocation=" + wsdlLocation
				+ ", serviceEndpointInterface=" + serviceEndpointInterface
				+ ", operationName=" + operationName + ", operationParameters="
				+ operationParmeters + "]";
	}


	


}
