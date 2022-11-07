package cl.contraloria.sicogen.config;

import cl.contraloria.sicogen.webservices.validacion.informe.pi.ValidarInformeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ValidarInformePIWSConfig {

    @Bean
    public Jaxb2Marshaller marshallerValidarInformePI() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cl.contraloria.sicogen.webservices.validacion.informe.pi");
        return marshaller;
    }

    @Bean
    public ValidarInformeClient validarInformePIClient(Jaxb2Marshaller marshallerValidarInformePI) {
        ValidarInformeClient client = new ValidarInformeClient();
        client.setDefaultUri("https://testing.contraloria.cl/systemws12c/Informes/SectorPublico/PI/v2/PS/Servicios/ValidarInforme");
        client.setMarshaller(marshallerValidarInformePI);
        client.setUnmarshaller(marshallerValidarInformePI);
        return client;
    }
}
