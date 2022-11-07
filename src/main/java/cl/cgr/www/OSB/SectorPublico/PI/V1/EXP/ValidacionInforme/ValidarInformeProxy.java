package cl.cgr.www.OSB.SectorPublico.PI.V1.EXP.ValidacionInforme;

public class ValidarInformeProxy implements ValidarInforme {
  private String _endpoint = null;
  private ValidarInforme validarInforme = null;
  
  public ValidarInformeProxy() {
    _initValidarInformeProxy();
  }
  
  /**
   * cbriones: Se sobrescribe constructor para obtener URL Validacion
   */
  public ValidarInformeProxy(int idWS) {
	  _initValidarInformeProxy(idWS);
  }
  
  public ValidarInformeProxy(String endpoint) {
    _endpoint = endpoint;
    _initValidarInformeProxy();
  }
  
  private void _initValidarInformeProxy() {
    try {
      validarInforme = (new ValidarInformeSOAPQSServiceLocator()).getValidarInformeSOAPQSPort();
      if (validarInforme != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)validarInforme)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)validarInforme)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  
  /**
   * cbriones: Se sobrescribe metodo para obtener URL Validacion
   */
  private void _initValidarInformeProxy(int idWS) {
    try {
      validarInforme = (new ValidarInformeSOAPQSServiceLocator(idWS)).getValidarInformeSOAPQSPort();
      if (validarInforme != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)validarInforme)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)validarInforme)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (validarInforme != null)
      ((javax.xml.rpc.Stub)validarInforme)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ValidarInforme getValidarInforme() {
    if (validarInforme == null)
      _initValidarInformeProxy();
    return validarInforme;
  }
  
  public void validarInforme(java.math.BigDecimal idFileUpload, javax.xml.rpc.holders.StringHolder estado, javax.xml.rpc.holders.StringHolder mensaje, cl.cgr.www.OSB.SectorPublico.PI.V1.EXP.ValidacionInforme.holders.ListaErroresHolder listaErrores, javax.xml.rpc.holders.StringHolder excepcion, javax.xml.rpc.holders.StringHolder fechaInicio, javax.xml.rpc.holders.StringHolder fechaFin) throws java.rmi.RemoteException{
    if (validarInforme == null)
      _initValidarInformeProxy();
    validarInforme.validarInforme(idFileUpload, estado, mensaje, listaErrores, excepcion, fechaInicio, fechaFin);
  }
  
  
}