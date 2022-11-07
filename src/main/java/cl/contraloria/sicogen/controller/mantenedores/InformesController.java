package cl.contraloria.sicogen.controller.mantenedores;

import cl.contraloria.sicogen.service.InformesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("/informes")
public class InformesController {

    private InformesService informesService;
    private ServletContext servletContext;

    public InformesController(InformesService informesService, ServletContext servletContext) {
        this.informesService = informesService;
        this.servletContext = servletContext;
    }

    @PostMapping(value = "/getPdfCertificadoEnvioByFileuId", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getPdfCertificadoEnvioByFileuId(@RequestParam Integer fileuId) throws Exception {
        return new ResponseEntity<String>(informesService.getPdfCertificadoEnvioByFileuId(
                fileuId, servletContext.getResource("/resources/img/sicogen2.png"),
                servletContext.getResource("/resources/img/cuadrados2.png")), HttpStatus.OK);
    }
}
