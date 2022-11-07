package cl.contraloria.sicogen.controller.mantenedores;

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
public class ProvinciasController {

    private MantenedoresService mantenedoresService;
    private FiltrosService filtrosService;

    public ProvinciasController(MantenedoresService mantenedoresService, FiltrosService filtrosService) {
        this.mantenedoresService = mantenedoresService;
        this.filtrosService = filtrosService;
    }

    @GetMapping(value = "/verMantenedorTblProvincias")
    public String verMantenedorTblProvincias(Model model) {
        List<Region> listRegiones = this.filtrosService.getRegiones();
        model.addAttribute("listRegiones", listRegiones);
        return "administracion/mantenedores/TblProvincias";
    }

    @PostMapping(value = "/listTblProvincias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listTblProvincias(@RequestParam Integer regionId,
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
        List<JsonJTableExpenseBean> records = mantenedoresService.getProvinciasMant(regionId, order);
        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }
        JsonResultJTable result = new JsonResultJTable("OK", records.subList(jtStartIndex, jtEndIndex), records.size());
        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/delTblProvincias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblProvincias(@RequestParam int id,
                                                                HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.delProvinciasMant(id, usuario);
            if (Integer.valueOf(resp.getNombre()) == null) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblProvincias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblProvincias(@RequestParam int id,
                                                                @RequestParam String nombre,
                                                                @RequestParam String codigo,
                                                                HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updProvinciasMant(id, nombre, codigo, usuario);
            if (resp.getId() != null) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addTblProvincias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblProvincias(@RequestParam int region,
                                                                @RequestParam String codigo,
                                                                @RequestParam String nombre,
                                                                HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.addProvinciasMant(region, codigo, nombre, usuario);
            if (Integer.valueOf(resp.getNombre()) > 0) {
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
        } catch (Exception e) {

        }
        return usuario;
    }
}
