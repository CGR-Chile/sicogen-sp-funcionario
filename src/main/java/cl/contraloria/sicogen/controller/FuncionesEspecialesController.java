package cl.contraloria.sicogen.controller;


import cl.contraloria.sicogen.model.InformeContableDTO;
import cl.contraloria.sicogen.model.ResultadoEjecucion;
import cl.contraloria.sicogen.service.InformesService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/funciones-especiales")
public class FuncionesEspecialesController {

    private InformesService informesService;

    public FuncionesEspecialesController(InformesService informesService) {
        this.informesService = informesService;
    }

    @GetMapping(value = "/")
    public String getFuncionesEspeciales() {
        return "funciones-especiales/funciones-especiales";
    }

    @GetMapping(value = "/getInformesReproceso")
    public String getInformesReproceso(Model model) {
        List<InformeContableDTO> informesReproceso = informesService.getInformesReproceso();
        model.addAttribute("informesReproceso", informesReproceso);
        return "funciones-especiales/tabla-informes-reproceso";
    }

    @PostMapping(value = "/autorizarReprocesos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> autorizarReprocesos(@RequestBody List<Integer> listaFileuId) {
        try {
            return new ResponseEntity<ResultadoEjecucion>(informesService.reprocesarInformesContables(listaFileuId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ResultadoEjecucion>(new ResultadoEjecucion("-1", ExceptionUtils.getStackTrace(ex)), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/correo-reproceso", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> runCorreoReproceso() {
        ResultadoEjecucion result = new ResultadoEjecucion();
        result.setCodEjec("0");
        result.setMsgEjec("Proceso ejecutado correctamente");
        try {
            informesService.enviaCorreoReprocesoInformesContables();
            return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
        } catch (Exception ex) {
            result.setCodEjec("-1");
            result.setMsgEjec(ex.getLocalizedMessage());
            return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}