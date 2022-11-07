package cl.contraloria.sicogen.controller.mantenedores;

import cl.contraloria.sicogen.model.EjerciciosDTO;
import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import cl.contraloria.sicogen.model.JsonResultJTable;
import cl.contraloria.sicogen.model.UsuarioDTO;
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
public class MigracionMantenedorController {

    private MantenedoresService mantenedoresService;
    private FiltrosService filtrosService;

    public MigracionMantenedorController(MantenedoresService mantenedoresService,
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

    @GetMapping(value = "/verMantenedorTblMigracion")
    public String verMantenedorTblMigracion(Model model)
    {
        List<EjerciciosDTO> listEjercicios = filtrosService.getEjercicios();
        model.addAttribute("listEjercicios", listEjercicios);

        return ("administracion/mantenedores/TblMigracion");
    }

    @PostMapping(value = "/migrarEjercicio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> migrarEjercicio(@RequestParam Integer ejercicioId,
                                                               @RequestParam Integer ejercicioId2,
                                                               HttpServletRequest request) {
        String usuario = getUserName(request);

        JsonResultJTable json = new JsonResultJTable();
        json.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.setMigrarEjercicioMant(ejercicioId,ejercicioId2, usuario);
            json.setMessage(resp.getMessage());
            if(resp.getId() > 0) {

                return new ResponseEntity<JsonResultJTable>(json, HttpStatus.OK);
            } else
                json.setResult("NOOK");
                return new ResponseEntity<JsonResultJTable>(json, HttpStatus.OK);
        }
        return new ResponseEntity<JsonResultJTable>(json, HttpStatus.BAD_REQUEST);
    }
}
