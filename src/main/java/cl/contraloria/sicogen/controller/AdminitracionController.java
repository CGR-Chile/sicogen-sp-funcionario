package cl.contraloria.sicogen.controller;

import cl.contraloria.sicogen.model.Mantenedores;
import cl.contraloria.sicogen.model.UsuarioDTO;
import cl.contraloria.sicogen.service.MantenedoresService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/administracion")
public class AdminitracionController {

    private MantenedoresService mantenedoresService;

    public AdminitracionController(MantenedoresService mantenedoresService) {
        this.mantenedoresService = mantenedoresService;
    }

    @GetMapping(value = "/")
    public String getAdministracion(HttpServletRequest request,
                                    Model model) {
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("usr");
        List<Mantenedores> listaMant = mantenedoresService.getMantededores(user.getUserLogin());
        model.addAttribute("listaMant", listaMant);
        return "administracion/menuAdministracion";
    }
}
