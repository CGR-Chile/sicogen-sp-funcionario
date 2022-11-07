package cl.contraloria.sicogen.config;

import cl.contraloria.sicogen.webservices.reproceso.correo.CorreoReprocesoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CorreoReprocesoWSConfig {

    @Bean
    public Jaxb2Marshaller marshallerCorreoReproceso() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cl.contraloria.sicogen.webservices.reproceso.correo");
        return marshaller;
    }

    @Bean
    public CorreoReprocesoClient correoReprocesoClient(Jaxb2Marshaller marshallerCorreoReproceso) {
        CorreoReprocesoClient client = new CorreoReprocesoClient();
        client.setDefaultUri("https://testing.contraloria.cl/systemws12c/Informes/SectorPublico/IC/v2/SIGFE/PS/Servicios/CorreoReproceso");
        client.setMarshaller(marshallerCorreoReproceso);
        client.setUnmarshaller(marshallerCorreoReproceso);
        return client;
    }
}
