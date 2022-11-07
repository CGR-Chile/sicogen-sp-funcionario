package cl.contraloria.sicogen.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/inicio")
public class InicioController {

    @RequestMapping(value = "/noticias", method = RequestMethod.GET)
    public String getAll() {

    return "seccionNoticias";

    }

}
