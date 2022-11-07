package cl.contraloria.sicogen.webservices.reproceso.ic;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ProcesarInformesClient extends WebServiceGatewaySupport {

    public ProcesarInformesResponse procesarInformesContables(ProcesarInformesRequest request) {
        return (ProcesarInformesResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
