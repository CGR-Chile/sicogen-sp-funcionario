package cl.contraloria.sicogen.webservices.validacion.informe.tdrmp;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ValidarInformeClient extends WebServiceGatewaySupport {

    public ValidarInformeResponse validarInformeTDRMP(ValidarInforme request) {
        ValidarInformeResponse response = (ValidarInformeResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }
}
