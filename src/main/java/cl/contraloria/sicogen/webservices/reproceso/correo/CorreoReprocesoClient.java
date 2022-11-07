package cl.contraloria.sicogen.webservices.reproceso.correo;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CorreoReprocesoClient extends WebServiceGatewaySupport {

    public void enviarCorreoReprocesoInformesContables(EnviarCorreoReprocesoRequestType request) {
        getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
