package cl.contraloria.sicogen.config;

import cl.contraloria.sicogen.webservices.validacion.carga.tdrmp.ValidarCargaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CargaTDRMPClientConfig {

    @Bean
    public Jaxb2Marshaller marshallerCarga() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cl.contraloria.sicogen.webservices.validacion.carga.tdrmp");
        return marshaller;
    }
    @Bean
    public ValidarCargaClient validarCargaClient(Jaxb2Marshaller marshallerCarga) {
        ValidarCargaClient client = new ValidarCargaClient();
        client.setDefaultUri("https://testing.contraloria.cl/systemws12c/SP_Carga_OSB/Sector_Publico_TDR/CargaArchivo/v1/Exposicion/Carga/PS/Servicio/validacionCarga");
        client.setMarshaller(marshallerCarga);
        client.setUnmarshaller(marshallerCarga);
        return client;
    }
}
