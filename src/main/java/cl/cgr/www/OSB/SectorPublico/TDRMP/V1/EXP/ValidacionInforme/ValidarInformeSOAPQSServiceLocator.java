/**
 * ValidarInformeSOAPQSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme;

import cl.contraloria.sicogen.dao.WebServicesPersistencia;
import cl.contraloria.sicogen.model.WebServices;


public class ValidarInformeSOAPQSServiceLocator extends org.apache.axis.client.Service implements cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.ValidarInformeSOAPQSService {

	/**
	 * cbriones: se agrega TimeOut para invocacion al Servicio
	 */
	private int timeOut;
	
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	
    public ValidarInformeSOAPQSServiceLocator() {
    }


    /**
     * cbriones: se sobrescribe Constructor para obtener URL Validacion
     * @param idWS
     */
    public ValidarInformeSOAPQSServiceLocator(int idWS) {
    	
    	WebServices ws = WebServicesPersistencia.getUrlWSValidacion(idWS);
    	ValidarInformeSOAPQSPort_address = ws.getUrl();
    	setTimeOut(ws.getTimeout());

    }
    
    public ValidarInformeSOAPQSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ValidarInformeSOAPQSServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ValidarInformeSOAPQSPort
    private String ValidarInformeSOAPQSPort_address = "http://sinapoc-PC:8011/Informes/SectorPublico/TDRMP/v1/Validacion/Exposicion/PS/Servicio/validarInforme";

    public String getValidarInformeSOAPQSPortAddress() {
        return ValidarInformeSOAPQSPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String ValidarInformeSOAPQSPortWSDDServiceName = "ValidarInformeSOAPQSPort";

    public String getValidarInformeSOAPQSPortWSDDServiceName() {
        return ValidarInformeSOAPQSPortWSDDServiceName;
    }

    public void setValidarInformeSOAPQSPortWSDDServiceName(String name) {
        ValidarInformeSOAPQSPortWSDDServiceName = name;
    }

    public cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.ValidarInforme getValidarInformeSOAPQSPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ValidarInformeSOAPQSPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getValidarInformeSOAPQSPort(endpoint);
    }

    public cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.ValidarInforme getValidarInformeSOAPQSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.ValidarInformeSOAPStub _stub = new cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.ValidarInformeSOAPStub(portAddress, this);
            _stub.setPortName(getValidarInformeSOAPQSPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setValidarInformeSOAPQSPortEndpointAddress(String address) {
        ValidarInformeSOAPQSPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.ValidarInforme.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.ValidarInformeSOAPStub _stub = new cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.ValidarInformeSOAPStub(new java.net.URL(ValidarInformeSOAPQSPort_address), this);
                _stub.setPortName(getValidarInformeSOAPQSPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
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
        String inputPortName = portName.getLocalPart();
        if ("ValidarInformeSOAPQSPort".equals(inputPortName)) {
            return getValidarInformeSOAPQSPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.cgr.cl/OSB/SectorPublico/TDRMP/V1/EXP/ValidacionInforme", "ValidarInformeSOAPQSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.cgr.cl/OSB/SectorPublico/TDRMP/V1/EXP/ValidacionInforme", "ValidarInformeSOAPQSPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("ValidarInformeSOAPQSPort".equals(portName)) {
            setValidarInformeSOAPQSPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
