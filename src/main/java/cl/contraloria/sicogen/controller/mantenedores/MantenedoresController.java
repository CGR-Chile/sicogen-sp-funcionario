package cl.contraloria.sicogen.controller.mantenedores;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import cl.contraloria.sicogen.model.JsonResultJTable;
import cl.contraloria.sicogen.model.JsonResultJTableAdd;
import cl.contraloria.sicogen.model.UsuarioDTO;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.MantenedoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/administracion/mantenedores")
public class MantenedoresController {

    private MantenedoresService mantenedoresService;

    public MantenedoresController(MantenedoresService mantenedoresService) {
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

    @GetMapping(value = "/verMantenedorTblPeriodos")
    public String verMantenedorTblPeriodos()
    {
        return ("administracion/mantenedores/TblPeriodos");
    }

    @PostMapping(value = "/listTblPeriodos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listTblPeriodo(@RequestParam Integer jtStartIndex,
                                                           @RequestParam Integer jtPageSize,
                                                           @RequestParam(required = false) String jtSorting) {
        String order;

        if (jtSorting == null) {
            order = "ID";
        } else {
            order = jtSorting;
        }

        int jtEndIndex = jtStartIndex + jtPageSize;

        List<JsonJTableExpenseBean> records = mantenedoresService.getPeriodoMant(order);

        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }

        JsonResultJTable result = new JsonResultJTable("OK", records.subList(jtStartIndex, jtEndIndex), records.size());

        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);



    }

    @PostMapping(value = "/addTblPeriodos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblPeriodos(
            @RequestParam String codigo,
            @RequestParam String nombre,
            HttpServletRequest request) {

        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {

            JsonJTableExpenseBean resp = mantenedoresService.setPeriodoMant(nombre, codigo, usuario);
            if(resp.getId()> 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblPeriodos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblPeriodos(@RequestParam String id,
                                                              @RequestParam String codigo,
                                                              @RequestParam String nombre,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updPeriodoMant(id,nombre, codigo, usuario);
            if(resp.getId() == 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblPeriodos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblPeriodos(@RequestParam String id,
                                                              HttpServletRequest request) {

        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {

            JsonJTableExpenseBean resp = mantenedoresService.delPeriodoMant(id, usuario);
            if(Integer.valueOf(resp.getId()) >  0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/verMantenedorTblMoneda")
    public String verMantenedorTblMoneda() {
        return "administracion/mantenedores/TblMoneda";
    }

    @PostMapping(value = "/listTblMoneda", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listTblMoneda(@RequestParam Integer jtStartIndex,
                                                          @RequestParam Integer jtPageSize,
                                                          @RequestParam(required = false) String jtSorting) {
        String order;

        if (jtSorting == null) {
            order = "ID";
        } else {
            order = jtSorting;
        }

        int jtEndIndex = jtStartIndex + jtPageSize;

        List<JsonJTableExpenseBean> records = mantenedoresService.getMonedaMant(order);

        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }

        JsonResultJTable result = new JsonResultJTable("OK", records.subList(jtStartIndex, jtEndIndex), records.size());

        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/addTblMoneda", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblMoneda(  @RequestParam String codigo,
                                                              @RequestParam String nombre,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.setMonedaMant(codigo,nombre, usuario);
            if(resp.getId() > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblMoneda", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblMoneda(@RequestParam String areaID,
                                                            @RequestParam String codigo,
                                                            @RequestParam String nombre,
                                                            HttpServletRequest request) {
        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updMonedaMant(areaID,codigo,nombre,usuario);
            if(resp.getId() == 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblMoneda", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblMoneda(@RequestParam String areaID,
                                                            HttpServletRequest request) {

        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {

            JsonJTableExpenseBean resp = mantenedoresService.delMonedaMant(areaID, usuario);
            if(Integer.valueOf(resp.getId()) >  0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/verMantenedorTblRoles")
    public String verMantenedorTblRoles() {
        return "administracion/mantenedores/TblRoles";
    }

    @PostMapping(value = "/listTblRoles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listTblRoles(@RequestParam Integer jtStartIndex,
                                                         @RequestParam Integer jtPageSize,
                                                         @RequestParam(required = false) String jtSorting) {
        String order;

        if (jtSorting == null) {
            order = "ID";
        } else {
            order = jtSorting;
        }

        int jtEndIndex = jtStartIndex + jtPageSize;

        List<JsonJTableExpenseBean> records = mantenedoresService.getRolesMant(order);

        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }

        JsonResultJTable result = new JsonResultJTable("OK", records.subList(jtStartIndex, jtEndIndex), records.size());

        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/addTblRoles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblRoles(  @RequestParam String codigo,
                                                             @RequestParam String nombre,
                                                             HttpServletRequest request) {
        String usuario = getUserName(request);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date currentDate = new Date();
        String fecha = dateFormat.format(currentDate);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.setRolesMant(codigo,nombre, usuario,fecha);
            if(resp.getId() > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblRoles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblRoles(@RequestParam String areaID,
                                                           @RequestParam String codigo,
                                                           @RequestParam String nombre,
                                                           HttpServletRequest request) {
        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updRolesMant(areaID,codigo,nombre,usuario);
            if(resp.getId() == 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblRoles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblRoles(@RequestParam String areaID,
                                                           HttpServletRequest request) {

        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {

            JsonJTableExpenseBean resp = mantenedoresService.delRolesMant(areaID, usuario);
            if(Integer.valueOf(resp.getId()) >  0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/verMantenedorTblProyectosBIP")
    public String verMantenedorTblProyectos() {
        return "administracion/mantenedores/TblProyectos";
    }

    @PostMapping(value = "/listTblProyectos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listTblProyectos(@RequestParam Integer jtStartIndex,
                                                             @RequestParam Integer jtPageSize,
                                                             @RequestParam(required = false) String jtSorting) {
        String order;

        if (jtSorting == null) {
            order = "PROYECTO_ID";
        } else {
            order = jtSorting;
        }

        int jtEndIndex = jtStartIndex + jtPageSize;

        List<JsonJTableExpenseBean> records = mantenedoresService.getProyectosMant(order);

        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }

        JsonResultJTable result = new JsonResultJTable("OK", records.subList(jtStartIndex, jtEndIndex), records.size());

        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/addTblProyectos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblProyectos(  @RequestParam String proyectoCodigo,
                                                                 @RequestParam String proyectoNombre,
                                                                 @RequestParam Integer estadoID,
                                                                 HttpServletRequest request) {
        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.setProyectosMant(proyectoCodigo,proyectoNombre, usuario,estadoID);
            if(resp.getId() > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblProyectos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblProyectos(@RequestParam Integer proyectoId,
                                                               @RequestParam String proyectoCodigo,
                                                               @RequestParam String proyectoNombre,
                                                               @RequestParam Integer estadoID,
                                                               HttpServletRequest request) {
        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updProyectosMant(proyectoId,proyectoCodigo,proyectoNombre,estadoID,usuario);
            if(resp.getId() == 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblProyectos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblProyectos(@RequestParam String proyectoId,
                                                               HttpServletRequest request) {

        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {

            JsonJTableExpenseBean resp = mantenedoresService.delProyectosMant(proyectoId, usuario);
            if(Integer.valueOf(resp.getId()) >  0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/listCmbEstados", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listCmbEstados() {
        List<OptionsJtable> estados = mantenedoresService.getEstados();
        JsonResultJTable result = new JsonResultJTable();
        result.setResult("OK");
        result.setOptions(estados);
        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }
}
