package cl.contraloria.sicogen.webservices.validacion.informe.pi;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ValidarInformeClient extends WebServiceGatewaySupport {

    public ValidarInformeResponse validarInformePI(ValidarInforme request) {
        return (ValidarInformeResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
