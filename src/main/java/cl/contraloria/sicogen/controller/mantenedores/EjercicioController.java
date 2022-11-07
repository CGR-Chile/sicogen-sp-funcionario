package cl.contraloria.sicogen.controller.mantenedores;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import cl.contraloria.sicogen.model.JsonResultJTable;
import cl.contraloria.sicogen.model.JsonResultJTableAdd;
import cl.contraloria.sicogen.model.UsuarioDTO;
import cl.contraloria.sicogen.service.MantenedoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import cl.contraloria.sicogen.model.UsuarioDTO;
import cl.contraloria.sicogen.service.MantenedoresService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/administracion/mantenedores")
public class EjercicioController {

    private MantenedoresService mantenedoresService;

    public EjercicioController(MantenedoresService mantenedoresService) {
        this.mantenedoresService = mantenedoresService;
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

    @GetMapping(value = "/verMantenedorTblEjercicio")
    public String verMantenedorTblEjercicio() {
        return "administracion/mantenedores/TblEjercicio";
    }

    @PostMapping(value = "/listTblEjercicio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listTblEjercicio(@RequestParam Integer jtStartIndex,
                                                             @RequestParam Integer jtPageSize,
                                                             @RequestParam(required = false) String jtSorting) {
        String order;
        if (jtSorting == null) {
            order = "ID";
        } else {
            order = jtSorting;
        }
        int jtEndIndex = jtStartIndex + jtPageSize;
        List<JsonJTableExpenseBean> records = mantenedoresService.getEjercicioMant(order);
        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }
        JsonResultJTable result = new JsonResultJTable("OK", records.subList(jtStartIndex, jtEndIndex), records.size());
        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/delTblEjercicio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblEjercicio(@RequestParam String areaID,
                                                               HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.delEjercicioMant(areaID, usuario);
            if(Integer.valueOf(resp.getEjercicio()) > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblEjercicio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblEjercicio(@RequestParam String areaID,
                                                               @RequestParam String nombre,
                                                               HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updEjercicioMant(areaID, nombre, nombre, usuario);
            if(resp.getId() == null) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addTblEjercicio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblEjercicio(@RequestParam String nombre,
                                                               HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {

            JsonJTableExpenseBean resp = mantenedoresService.addEjercicioMant(nombre, nombre, 1, null, usuario);
            if(Integer.valueOf(resp.getEjercicio()) > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }
}
