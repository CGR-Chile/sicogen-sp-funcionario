package cl.contraloria.sicogen.webservices.validacion.informe.tdrii;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ValidarInformeTDRIIClient extends WebServiceGatewaySupport {

    public ValidarInformeResponse validarInformeTDRII(ValidarInforme request) {
        return (ValidarInformeResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
