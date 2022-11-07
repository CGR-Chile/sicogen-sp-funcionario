package cl.contraloria.sicogen.webservices.validacion.carga.pi;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ValidarCargaClient extends WebServiceGatewaySupport {

    public ValidarCargaResponse validarCargaPI(ValidarCarga request) {
        return (ValidarCargaResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
