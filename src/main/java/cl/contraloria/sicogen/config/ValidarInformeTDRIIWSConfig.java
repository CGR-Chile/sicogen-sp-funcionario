package cl.contraloria.sicogen.config;

import cl.contraloria.sicogen.webservices.validacion.informe.tdrii.ValidarInformeTDRIIClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ValidarInformeTDRIIWSConfig {

    @Bean
    public Jaxb2Marshaller marshallerValidarInformeTDRII() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cl.contraloria.sicogen.webservices.validacion.informe.tdrii");
        return marshaller;
    }

    @Bean
    public ValidarInformeTDRIIClient validarInformeTDRIIClient(Jaxb2Marshaller marshallerValidarInformeTDRII) {
        ValidarInformeTDRIIClient client = new ValidarInformeTDRIIClient();
        client.setDefaultUri("https://testing.contraloria.cl/systemws12c/Informes/SectorPublico/TDRII/v2/Validacion/Exposicion/PS/Servicio/ValidarInforme");
        client.setMarshaller(marshallerValidarInformeTDRII);
        client.setUnmarshaller(marshallerValidarInformeTDRII);
        return client;
    }
}
