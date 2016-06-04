/**
 * MyWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WS;

public class MyWSServiceLocator extends org.apache.axis.client.Service implements WS.MyWSService {

    public MyWSServiceLocator() {
    }


    public MyWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MyWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MyWS
    private java.lang.String MyWS_address = "http://localhost:8080/Project_WebService/services/MyWS";

    public java.lang.String getMyWSAddress() {
        return MyWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MyWSWSDDServiceName = "MyWS";

    public java.lang.String getMyWSWSDDServiceName() {
        return MyWSWSDDServiceName;
    }

    public void setMyWSWSDDServiceName(java.lang.String name) {
        MyWSWSDDServiceName = name;
    }

    public WS.MyWS getMyWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MyWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMyWS(endpoint);
    }

    public WS.MyWS getMyWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            WS.MyWSSoapBindingStub _stub = new WS.MyWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getMyWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMyWSEndpointAddress(java.lang.String address) {
        MyWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (WS.MyWS.class.isAssignableFrom(serviceEndpointInterface)) {
                WS.MyWSSoapBindingStub _stub = new WS.MyWSSoapBindingStub(new java.net.URL(MyWS_address), this);
                _stub.setPortName(getMyWSWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MyWS".equals(inputPortName)) {
            return getMyWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://WS", "MyWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://WS", "MyWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MyWS".equals(portName)) {
            setMyWSEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
