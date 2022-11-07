package cl.contraloria.sicogen.config;

import cl.contraloria.sicogen.webservices.validacion.carga.pi.ValidarCargaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ValidarCargaPIWSConfig {

    @Bean
    public Jaxb2Marshaller marshallerValidarCargaPI() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cl.contraloria.sicogen.webservices.validacion.carga.pi");
        return marshaller;
    }

    @Bean
    public ValidarCargaClient validarCargaPIClient(Jaxb2Marshaller marshallerValidarCargaPI) {
        ValidarCargaClient client = new ValidarCargaClient();
        client.setDefaultUri("https://testing.contraloria.cl/systemws12c/Informes/SectorPublico/PI/v2/PS/Servicios/ValidacionCarga");
        client.setMarshaller(marshallerValidarCargaPI);
        client.setUnmarshaller(marshallerValidarCargaPI);
        return client;
    }
}
