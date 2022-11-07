package cl.contraloria.sicogen.controller.mantenedores;

import cl.contraloria.sicogen.model.UsuarioDTO;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.FiltrosService;
import cl.contraloria.sicogen.service.MantenedoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.util.List;

@Controller
@RequestMapping("/administracion/mantenedores")
public class PeriodoEjercicioController {

    private MantenedoresService mantenedoresService;
    private FiltrosService filtrosService;


    public PeriodoEjercicioController(MantenedoresService mantenedoresService,
                                      FiltrosService filtrosService) {
        this.mantenedoresService = mantenedoresService;
        this.filtrosService = filtrosService;

    }

    private String getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String usuario = "";
        try {
            UsuarioDTO usuarioSesion = (UsuarioDTO) session.getAttribute("usr");
            usuario = usuarioSesion.getUserLogin();
        } catch(Exception e) {

        }
        return usuario;
    }

    @GetMapping(value = "/verMantenedorTblPeriodoEjercicio")
    public String verMantenedorTblPeriodos(Model model)
    {
        List<EjerciciosDTO> listEjercicios = filtrosService.getEjercicios();
        model.addAttribute("listEjercicios", listEjercicios);

        List<Periodos> listPeriodos = filtrosService.getPeriodos();
        model.addAttribute("listPeriodos", listPeriodos);
        return ("administracion/mantenedores/TblPeriodoEjercicio");
    }

    @PostMapping(value = "/listTblPeriodoEjercicio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listTblPeriodoEjercicio(@RequestParam Integer ejercicio,
                                                                    @RequestParam Integer jtStartIndex,
                                                                    @RequestParam Integer jtPageSize,
                                                                    @RequestParam(required = false) String jtSorting) {
        String order;

        if (jtSorting == null) {
            order = "ID";
        } else {
            order = jtSorting;
        }

        int jtEndIndex = jtStartIndex + jtPageSize;

        List<JsonJTableExpenseBean> records = mantenedoresService.getPeriodoEjercicioMant(ejercicio,order);

        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }

        JsonResultJTable result = new JsonResultJTable("OK", records.subList(jtStartIndex, jtEndIndex), records.size());

        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/addTblPeriodoEjercicio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblProyectos(  @RequestParam Integer padreId,
                                                                 @RequestParam Integer padreId2,
                                                                 HttpServletRequest request) {
        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.setPeriodoEjerciciosMant(padreId,padreId2, usuario);
            if(resp.getId() > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblPeriodoEjercicio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblPeriodoEjercicio(@RequestParam Integer id,
                                                                      @RequestParam Integer padreId,
                                                                      @RequestParam Integer padreId2,
                                                               HttpServletRequest request) {
        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updPeriodosEjercicioMant(id,padreId,padreId2,usuario);
            if(resp.getId() == 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblPeriodoEjercicio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblPeriodoEjercicio(@RequestParam Integer id,
                                                               HttpServletRequest request) {

        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {

            JsonJTableExpenseBean resp = mantenedoresService.delPeriodosEjercicioMant(id, usuario);
            if(Integer.valueOf(resp.getId()) ==  0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

}
