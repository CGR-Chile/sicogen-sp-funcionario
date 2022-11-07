package cl.contraloria.sicogen.config;

import cl.contraloria.sicogen.webservices.reproceso.ic.ProcesarInformesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ProcesarInformesConfig {

    @Bean
    public Jaxb2Marshaller marshallerReproceso() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cl.contraloria.sicogen.webservices.reproceso.ic");
        return marshaller;
    }
    @Bean
    public ProcesarInformesClient procesarInformesClient(Jaxb2Marshaller marshallerReproceso) {
        ProcesarInformesClient client = new ProcesarInformesClient();
        client.setDefaultUri("https://testing.contraloria.cl/systemws12c/Informes/SectorPublico/IC/v2/SIGFE/PS/Servicios/ProcesarInformes");
        client.setMarshaller(marshallerReproceso);
        client.setUnmarshaller(marshallerReproceso);
        return client;
    }
}
