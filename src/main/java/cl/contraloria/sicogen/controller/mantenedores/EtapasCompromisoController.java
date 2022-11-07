package cl.contraloria.sicogen.controller.mantenedores;


import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import cl.contraloria.sicogen.model.JsonResultJTable;
import cl.contraloria.sicogen.model.JsonResultJTableAdd;
import cl.contraloria.sicogen.model.UsuarioDTO;
import cl.contraloria.sicogen.service.MantenedoresService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/administracion/mantenedores")
public class EtapasCompromisoController {

    private MantenedoresService mantenedoresService;


    public EtapasCompromisoController(MantenedoresService mantenedoresService) {
        this.mantenedoresService = mantenedoresService;

    }

        @GetMapping(value = "/verMantenedorTblEstapasCompromiso")
        public String verMantenedorTblEtapasCompromiso() {
            return "administracion/mantenedores/TblEtapasCompromiso";
        }


    @PostMapping(value = "/listTblEtapasCompromiso", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listTblEtapasCompromiso(@RequestParam Integer jtStartIndex,
                                                             @RequestParam Integer jtPageSize,
                                                             @RequestParam(required = false) String jtSorting) {
        String order;
        if (jtSorting == null) {
            order = "ID";
        } else {
            order = jtSorting;
        }
        int jtEndIndex = jtStartIndex + jtPageSize;
        List<JsonJTableExpenseBean> records = mantenedoresService.getEtapasCompromisoMant(order);
        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }
        JsonResultJTable result = new JsonResultJTable("OK", records.subList(jtStartIndex, jtEndIndex), records.size());
        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }



    @PostMapping(value = "/delTblEtapasCompromiso", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblEtapasCompromiso(@RequestParam String areaID,
                                                               HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.delEtapasCompromisoMant(areaID, usuario);
            if(Integer.valueOf(resp.getCompromiso()) > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }




    @PostMapping(value = "/updTblEtapasCompromiso", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblEtapasCompromiso(@RequestParam String areaID,
                                                                      @RequestParam String etapa,
                                                                      @RequestParam String nombre,
                                                                      @RequestParam String rut,
                                                               HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updEtapasCompromisoMant(areaID, etapa, nombre, rut, usuario);
            if(resp.getId() == null) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }


    @PostMapping(value = "/addTblEtapasCompromiso", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblEtapasCompromiso(@RequestParam String etapa,
                                                                      @RequestParam String nombre,
                                                                      @RequestParam String rut,
                                                               HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {

            JsonJTableExpenseBean resp = mantenedoresService.addEtapasCompromisoMant(etapa, nombre, rut, usuario);

            if(Integer.valueOf(resp.getCompromiso()) > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
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
















    }
