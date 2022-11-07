package cl.contraloria.sicogen.config;

import cl.contraloria.sicogen.webservices.validacion.informe.tdrmp.ValidarInformeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ValidacionTDRMPClientConfig {

    @Bean
    public Jaxb2Marshaller marshallerValidacion() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cl.contraloria.sicogen.webservices.validacion.informe.tdrmp");
        return marshaller;
    }
    @Bean
    public ValidarInformeClient validarInformeClient(Jaxb2Marshaller marshallerValidacion) {
        ValidarInformeClient client = new ValidarInformeClient();
        client.setDefaultUri("https://testing.contraloria.cl/systemws12c/Informes/SectorPublico/TDRMP/v2/Validacion/Exposicion/PS/Servicio/ValidarInforme");
        client.setMarshaller(marshallerValidacion);
        client.setUnmarshaller(marshallerValidacion);
        return client;
    }
}
