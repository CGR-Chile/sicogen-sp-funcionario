package cl.contraloria.sicogen.controller;

import cl.contraloria.sicogen.model.UsuarioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping(value="/")
	public ModelAndView test(Model model){

		UsuarioDTO usr = new UsuarioDTO();
		usr.setMensaje("0");
		usr.setUserLogin("");
		usr.setPassLogin("");
		model.addAttribute("usuario", usr);
		return new ModelAndView("home");
	}
}
