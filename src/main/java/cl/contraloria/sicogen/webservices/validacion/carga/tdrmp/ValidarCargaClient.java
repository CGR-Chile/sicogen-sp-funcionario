package cl.contraloria.sicogen.webservices.validacion.carga.tdrmp;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ValidarCargaClient extends WebServiceGatewaySupport {

    public ValidarCargaResponse validarCargaTDRMP(ValidarCarga request) {
        ValidarCargaResponse response = (ValidarCargaResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }
}
