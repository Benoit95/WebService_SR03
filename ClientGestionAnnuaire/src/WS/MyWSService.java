/**
 * MyWSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WS;

public interface MyWSService extends javax.xml.rpc.Service {
    public java.lang.String getMyWSAddress();

    public WS.MyWS getMyWS() throws javax.xml.rpc.ServiceException;

    public WS.MyWS getMyWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
