/**
 * ValidarInforme.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.cgr.www.OSB.SectorPublico.PI.V1.EXP.ValidacionInforme;

public interface ValidarInforme extends java.rmi.Remote {
    public void validarInforme(java.math.BigDecimal idFileUpload, javax.xml.rpc.holders.StringHolder estado, javax.xml.rpc.holders.StringHolder mensaje, cl.cgr.www.OSB.SectorPublico.PI.V1.EXP.ValidacionInforme.holders.ListaErroresHolder listaErrores, javax.xml.rpc.holders.StringHolder excepcion, javax.xml.rpc.holders.StringHolder fechaInicio, javax.xml.rpc.holders.StringHolder fechaFin) throws java.rmi.RemoteException;
}
