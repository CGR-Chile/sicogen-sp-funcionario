package cl.contraloria.sicogen.controller.mantenedores;

import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.FiltrosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/administracion/mantenedores")
public class CombosController {

    private FiltrosService filtrosService;

    public CombosController(FiltrosService filtrosService) {
        this.filtrosService = filtrosService;
    }

    @PostMapping(value = "/getInfAndPerd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PeriodosInformes> getPeriodosAndIformes(@RequestParam Integer ejercicio) {
        PeriodosInformes periodosInformes = new PeriodosInformes();
        periodosInformes.setListInformes(this.filtrosService.getInformes());
        periodosInformes.setListPeriodos(this.filtrosService.getPeriodosByEjercicio(ejercicio));
        return new ResponseEntity<PeriodosInformes>(periodosInformes, HttpStatus.OK);
    }
}
