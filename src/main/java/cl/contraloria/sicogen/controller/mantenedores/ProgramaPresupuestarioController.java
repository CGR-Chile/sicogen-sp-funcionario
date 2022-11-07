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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administracion/mantenedores")
public class ProgramaPresupuestarioController {

    private MantenedoresService mantenedoresService;
    private FiltrosService filtrosService;

    public ProgramaPresupuestarioController(MantenedoresService mantenedoresService, FiltrosService filtrosService) {
        this.mantenedoresService = mantenedoresService;
        this.filtrosService = filtrosService;
    }

    @GetMapping(value = "/verMantenedorTblProgramaPresupuestario")
    public String verMantenedorTblProgramaPresupuestario(Model model) {
        List<EjerciciosDTO> ejercicios = filtrosService.getEjercicios();
        model.addAttribute("listaEjercicios", ejercicios);
        return "administracion/mantenedores/TblProgramaPresupuestario";
    }

    @GetMapping(value = "/verBitacoraPrograma")
    public ResponseEntity<List<CambiosBitacora>> verBitacoraPrograma(@RequestParam Integer idPP) {
        Bitacora bitacora = mantenedoresService.getRegistroBitacoraPrograma(idPP);
        List<CambiosBitacora> cambiosBitacora = new ArrayList<CambiosBitacora>();
        cambiosBitacora.add(getCambiosBitacora(bitacora));
        return new ResponseEntity<List<CambiosBitacora>>(cambiosBitacora, HttpStatus.OK);
    }

    @GetMapping(value = "/verBitacoraPartida")
    public ResponseEntity<List<CambiosBitacora>> verBitacoraPartida(@RequestParam Integer idPP) {

        Bitacora bitacora = mantenedoresService.getRegistroBitacoraPartida(idPP);
        List<CambiosBitacora> cambiosBitacora = new ArrayList<CambiosBitacora>();
        cambiosBitacora.add(getCambiosBitacora(bitacora));
        return new ResponseEntity<List<CambiosBitacora>>(cambiosBitacora, HttpStatus.OK);
    }

    @GetMapping(value = "/verBitacoraCapitulo")
    public ResponseEntity<List<CambiosBitacora>> verBitacoraCapitulo(@RequestParam Integer idPP) {
        Bitacora bitacora = mantenedoresService.getRegistroBitacoraCapitulo(idPP);
        List<CambiosBitacora> cambiosBitacora = new ArrayList<CambiosBitacora>();
        cambiosBitacora.add(getCambiosBitacora(bitacora));
        return new ResponseEntity<List<CambiosBitacora>>(cambiosBitacora, HttpStatus.OK);
    }

    private CambiosBitacora getCambiosBitacora(Bitacora bitacora) {
        CambiosBitacora cambioBitacora = new CambiosBitacora();
        if (bitacora.getFchaCreacion() != null) {
            cambioBitacora.setEstado("Creado");
            cambioBitacora.setFecha(bitacora.getFchaCreacion());
            cambioBitacora.setUsuario(bitacora.getUserCreacion());
        }
        if (bitacora.getFchaEdicion() != null) {
            cambioBitacora.setEstado("Editado");
            cambioBitacora.setFecha(bitacora.getFchaEdicion());
            cambioBitacora.setUsuario(bitacora.getUserEdicion());
        }
        if (bitacora.getFchaDesactivacion() != null) {
            cambioBitacora.setEstado("Desactivado");
            cambioBitacora.setFecha(bitacora.getFchaDesactivacion());
            cambioBitacora.setUsuario(bitacora.getUserEdicion());
        }
        return cambioBitacora;
    }

    @PostMapping(value = "/listTblPP", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTablePP> listTblPP(@RequestParam Integer ejercicio,
                                                        @RequestParam String partida,
                                                        @RequestParam String capitulo,
                                                        @RequestParam Integer jtStartIndex,
                                                        @RequestParam Integer jtPageSize) {
        int jtEndIndex = jtStartIndex + jtPageSize;
        List<ProgramaPresupuestarioDTO> records = mantenedoresService.getPP(ejercicio, partida, capitulo);
        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }
        JsonResultJTablePP result = new JsonResultJTablePP("OK", records.subList(jtStartIndex, jtEndIndex), records.size());
        return new ResponseEntity<JsonResultJTablePP>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/updTblPrograma", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblPrograma(@RequestParam Integer idPP,
                                                              @RequestParam String nombre,
                                                              @RequestParam String codigo,
                                                              @RequestParam Integer idCapitulo,
                                                              @RequestParam String ejercicio,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updPrograma(idPP, idCapitulo, codigo, nombre, ejercicio, usuario);
            if (resp.getId() == -6 && "SE HA ACTUALIZADO EL PROGRAMA".equalsIgnoreCase(resp.getMessage())) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblCapitulo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblCapitulo(@RequestParam Integer idPP,
                                                              @RequestParam String nombre,
                                                              @RequestParam String codigo,
                                                              @RequestParam Integer idPartida,
                                                              @RequestParam String ejercicio,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updCapitulo(idPP, idPartida, codigo, nombre, ejercicio, usuario);
            if (resp.getId() > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblPartida", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblPartida(@RequestParam String nombre,
                                                             HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");

        if (!usuario.equalsIgnoreCase("")) {
            HttpSession session = request.getSession();
            Integer ejercicioSelected = Integer.valueOf(session.getAttribute("ejercicioSelected").toString());
            Integer idPartida = Integer.valueOf(session.getAttribute("idPartidaSelected").toString());
            JsonJTableExpenseBean resp = mantenedoresService.updPartida(idPartida, nombre, usuario, ejercicioSelected);
            if (resp.getId() == null) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblPartida", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblPartida(@RequestParam Integer idPP,
                                                             HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.delPartida(idPP, usuario);
            jsonAdd.setRecord(resp);
            if (Integer.valueOf(resp.getNombre()) > 0)
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblCapitulo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblCapitulo(@RequestParam Integer idPP,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.delCapitulo(idPP, usuario);
            jsonAdd.setRecord(resp);
            if (Integer.valueOf(resp.getNombre()) > 0)
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblPrograma", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblPrograma(@RequestParam Integer idPP,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.delPrograma(idPP, usuario);
            jsonAdd.setRecord(resp);
            if (Integer.valueOf(resp.getNombre()) > 0)
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/getCodigoPartidaById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCodigoPartidaById(@RequestParam Integer idPP,
                                                       @RequestParam String idEjercicio) {
        JsonJTableExpenseBean resp = mantenedoresService.getCodigoPartidaById(idPP, idEjercicio);
        return new ResponseEntity<String>(resp.getCodPartida(), HttpStatus.OK);
    }

    @PostMapping(value = "/getCodigoCapituloById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCodigoCapituloById(@RequestParam Integer idPP,
                                                        @RequestParam String idEjercicio) {
        JsonJTableExpenseBean resp = mantenedoresService.getCodigoCapituloById(idPP, idEjercicio);
        return new ResponseEntity<String>(resp.getCodCapitulo(), HttpStatus.OK);
    }

    @PostMapping(value = "/getCodigoPartidaByIdCap", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCodigoPartidaByIdCap(@RequestParam Integer idPP,
                                                          @RequestParam String idEjercicio) {
        JsonJTableExpenseBean resp = mantenedoresService.getCodigoPartidaByIdCap(idPP, idEjercicio);
        return new ResponseEntity<String>(resp.getCodPartida(), HttpStatus.OK);
    }

    @PostMapping(value = "/getCapituloById", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getCapituloById(@RequestParam Integer idPP,
                                                  @RequestParam String idEjercicio) {
        JsonJTableExpenseBean resp = mantenedoresService.getCapituloById(idPP, idEjercicio);
        return new ResponseEntity<String>(resp.getNombre(), HttpStatus.OK);
    }

    @PostMapping(value = "/getProgramaById", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getProgramaById(@RequestParam Integer idPP,
                                                  @RequestParam String idEjercicio) {
        JsonJTableExpenseBean resp = mantenedoresService.getProgramaById(idPP, idEjercicio);
        return new ResponseEntity<String>(resp.getNombre(), HttpStatus.OK);
    }

    @PostMapping(value = "/getPartidaById", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getPartidaById(@RequestParam Integer idPP,
                                                 @RequestParam String idEjercicio) {
        JsonJTableExpenseBean resp = mantenedoresService.getPartidaById(idPP, idEjercicio);
        return new ResponseEntity<String>(resp.getNombre(), HttpStatus.OK);
    }

    @PostMapping(value = "/addTblCapitulo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblCapitulo(@RequestParam Integer idPP,
                                                              @RequestParam String capitulo,
                                                              @RequestParam String nombre,
                                                              @RequestParam String ejercicio,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("usr");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.addCapitulo(capitulo, nombre, idPP, ejercicio, user.getUserLogin(), user.getEntidadId());
            if (Integer.valueOf(resp.getNombre()) > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addTblPP", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblPP(@RequestParam String partida,
                                                        @RequestParam String nombre,
                                                        @RequestParam(required = false) Integer ejericio,
                                                        HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");

        HttpSession session = request.getSession();
        Integer ejercicioSelected;
        if (session.getAttribute("ejercicioSelected") != null) {
            ejercicioSelected = (Integer) session.getAttribute("ejercicioSelected");
        } else {
            ejercicioSelected = ejericio;
        }

        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.addPartida(ejercicioSelected, partida, nombre, usuario);
            jsonAdd.setRecord(resp);
            if (Integer.valueOf(resp.getNombre()) > 0) {
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addTblPrograma", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblPrograma(@RequestParam Integer idPP,
                                                              @RequestParam String programa,
                                                              @RequestParam String nombre,
                                                              @RequestParam String ejercicio,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.addPrograma(programa, nombre, idPP, ejercicio, usuario);
            if (Integer.valueOf(resp.getNombre()) > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/loadPartida", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProgramaPresupuestarioDTO>> loadPartida(HttpServletRequest request,
                                                                       @RequestParam Integer ejercicioId) {
        List<ProgramaPresupuestarioDTO> partidas = mantenedoresService.getPartida(ejercicioId);

        HttpSession sesion = request.getSession();
        sesion.setAttribute("ejercicioSelected", ejercicioId);
        return new ResponseEntity<List<ProgramaPresupuestarioDTO>>(partidas, HttpStatus.OK);
    }

    @PostMapping(value = "/loadCapitulo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProgramaPresupuestarioDTO>> loadCapitulo(HttpServletRequest request,
                                                                        @RequestParam String idEjercicio,
                                                                        @RequestParam Integer partidaId) {
        List<ProgramaPresupuestarioDTO> caps = mantenedoresService.getCapitulo(idEjercicio, partidaId);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("idPartidaSelected", String.valueOf(partidaId));
        return new ResponseEntity<List<ProgramaPresupuestarioDTO>>(caps, HttpStatus.OK);
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
