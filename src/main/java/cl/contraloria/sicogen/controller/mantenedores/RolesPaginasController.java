package cl.contraloria.sicogen.controller.mantenedores;

import cl.contraloria.sicogen.model.*;
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
public class RolesPaginasController {

    private MantenedoresService mantenedoresService;

    public RolesPaginasController(MantenedoresService mantenedoresService) {
        this.mantenedoresService = mantenedoresService;
    }

    @GetMapping(value = "/verMantenedorTblRolesPaginas")
    public String verMantenedorTblRolesPaginas(Model model) {
        List<Rol> listaRoles = this.mantenedoresService.getRoles();
        model.addAttribute("listaRoles", listaRoles);
        return "administracion/mantenedores/TblRolesPaginas";
    }

    @PostMapping(value = "/listTblRolesPaginas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listTblRolesPaginas(@RequestParam Integer rolID,
                                                                @RequestParam Integer jtStartIndex,
                                                                @RequestParam Integer jtPageSize) {
        int jtEndIndex = jtStartIndex + jtPageSize;
        List<JsonJTableExpenseBean> records = mantenedoresService.getRolesPaginasMant(rolID);
        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }
        JsonResultJTable result = new JsonResultJTable("OK", records.subList(jtStartIndex, jtEndIndex), records.size());
        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/delTblRolesPaginas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblRolesPaginas(@RequestParam Integer rolpagID,
                                                                  HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.delRolesPaginasMant(rolpagID, usuario);
            if (Integer.valueOf(resp.getNombre()) == 1) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblRolesPaginas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblRolesPaginas(@RequestParam Integer paginas,
                                                                  @RequestParam Integer rolID,
                                                                  @RequestParam Integer rolpagID,
                                                                  HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updRolesPaginasMant(rolpagID, rolID, paginas, usuario);
            if (resp.getId() == 1) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addTblRolesPaginas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblRolesPaginas(@RequestParam Integer rolID,
                                                                  @RequestParam Integer paginas,
                                                                  HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.addRolesPaginasMant(rolID, paginas, usuario);
            if (Integer.valueOf(resp.getNombre()) > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/loadPaginasOption", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> loadPaginasOption() {
        List<OptionsJtable> resp = mantenedoresService.loadPaginasOption();
        JsonResultJTable json = new JsonResultJTable();
        json.setResult("OK");
        json.setOptions(resp);
        return new ResponseEntity<JsonResultJTable>(json, HttpStatus.OK);
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
