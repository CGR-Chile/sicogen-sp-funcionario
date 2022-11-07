package cl.contraloria.sicogen.controller;

import cl.contraloria.sicogen.model.UsuarioDTO;
import cl.contraloria.sicogen.service.InformesService;
import cl.contraloria.sicogen.service.UsuarioService;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.SocketException;
import java.security.Principal;
import java.sql.SQLException;

import static cl.contraloria.sicogen.utils.InfoRed.getListHostAdresses;

@Controller
public class UsuarioController {

    private UsuarioService usuarioService;
    private InformesService informesService;
    private ServletContext servletContext;

    public UsuarioController(UsuarioService usuarioService,
                             InformesService informesService,
                             ServletContext servletContext) {
        this.usuarioService = usuarioService;
        this.informesService = informesService;
        this.servletContext = servletContext;
    }

    @PostMapping("/main")
    public String login(ModelMap model, HttpServletRequest req, @RequestParam String userLogin,
                        @RequestParam String passLogin) {

        UsuarioDTO usr = null;
        UsuarioDTO usr2 = new UsuarioDTO();
       // String nomUsuario = req.
        //String password = "user2";//usuario.getPassLogin();

        try {
            usr = usuarioService.getUsuario(userLogin,passLogin);
        } catch (SQLException e) {
            if(usr==null){
                usr2.setUserLogin("");
                usr2.setPassLogin("");
                usr2.setMensaje("Usuario o Contraseña Invalida");
                model.addAttribute("usuario", usr2);
                return "home";
            }
            e.printStackTrace();
        } catch (Exception e) {
            if(usr==null){

                usr2.setUserLogin("");
                usr2.setPassLogin("");
                usr2.setMensaje("Usuario o Contraseña Invalida");
                model.addAttribute("usuario", usr2);
                return "home";
            }
            e.printStackTrace();
        }

        HttpSession session = req.getSession(false);
        session.setAttribute("usr", usr);
        model.addAttribute("usuario", usr);
        return "listadogeneral";
    }

    @RequestMapping(value = "/validarLoginCGR", method = RequestMethod.GET)
    public String loginCGRGet(ModelMap model, HttpServletRequest req) {
        String usuarioCGR = "";
        UsuarioDTO usr = null;
        String error = null;

        try {
            usuarioCGR = req.getRemoteUser();
            usr = usuarioService.obtenerUsuarioPorCodigo(usuarioCGR);
        } catch (Exception e) {
            error = e.getLocalizedMessage();
        }

        HttpSession session = req.getSession();
        session.setAttribute("usr", usr);
        model.addAttribute("usuario", usr);
        model.addAttribute("usuarioLogin", usuarioCGR);
        model.addAttribute("error", error);
        return "listadogeneral";
    }

    @RequestMapping(value = "/validarLoginCGR", method = RequestMethod.POST)
    public String loginCGRPost(ModelMap model, HttpServletRequest req) {
        String usuarioCGR = "";
        UsuarioDTO usr = null;
        String error = null;

        try {
            usuarioCGR = req.getRemoteUser();
            usr = usuarioService.obtenerUsuarioPorCodigo(usuarioCGR);
        } catch (Exception e) {
            error = e.getLocalizedMessage();
        }

        HttpSession session = req.getSession();
        session.setAttribute("usr", usr);
        model.addAttribute("usuario", usr);
        model.addAttribute("usuarioLogin", usuarioCGR);
        model.addAttribute("error", error);
        return "listadogeneral";
    }
}
