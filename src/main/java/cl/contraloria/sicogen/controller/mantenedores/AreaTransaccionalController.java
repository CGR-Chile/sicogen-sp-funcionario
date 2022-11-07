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
public class AreaTransaccionalController {

    private MantenedoresService mantenedoresService;
    private FiltrosService filtrosService;

    public AreaTransaccionalController(MantenedoresService mantenedoresService, FiltrosService filtrosService) {
        this.mantenedoresService = mantenedoresService;
        this.filtrosService = filtrosService;
    }

    @GetMapping(value = "/verMantenedorTblAreasTransaccionales")
    public String verMantenedorTblAreaTransaccional(Model model) {
        List<EjerciciosDTO> ejercicios = filtrosService.getEjercicios();
        model.addAttribute("listaEjercicios", ejercicios);
        return "administracion/mantenedores/TblAreaTransaccional";
    }

    @GetMapping(value = "/verBitacoraAT")
    public ResponseEntity<List<CambiosBitacora>> verBitacoraAT(@RequestParam Integer idAT) {
        Bitacora bitacora = mantenedoresService.getRegistroBitacoraAT(idAT);
        List<CambiosBitacora> cambiosBitacora = new ArrayList<CambiosBitacora>();
        //cambiosBitacora.add(getCambiosBitacora(bitacora));
        cambiosBitacora = getCambiosBitacora(bitacora);
        return new ResponseEntity<List<CambiosBitacora>>(cambiosBitacora, HttpStatus.OK);
    }

    @GetMapping(value = "/verBitacoraSector")
    public ResponseEntity<List<CambiosBitacora>> verBitacoraSector(@RequestParam Integer idAT) {

        Bitacora bitacora = mantenedoresService.getRegistroBitacoraSector(idAT);
        List<CambiosBitacora> cambiosBitacora = new ArrayList<CambiosBitacora>();
        //cambiosBitacora.add(getCambiosBitacora(bitacora));
        cambiosBitacora = getCambiosBitacora(bitacora);
        return new ResponseEntity<List<CambiosBitacora>>(cambiosBitacora, HttpStatus.OK);
    }

    @GetMapping(value = "/verBitacoraInstitucion")
    public ResponseEntity<List<CambiosBitacora>> verBitacoraInstitucion(@RequestParam Integer idAT) {
        Bitacora bitacora = mantenedoresService.getRegistroBitacoraInstitucion(idAT);
        List<CambiosBitacora> cambiosBitacora = new ArrayList<CambiosBitacora>();
        //cambiosBitacora.add(getCambiosBitacora(bitacora));
        cambiosBitacora = getCambiosBitacora(bitacora);
        return new ResponseEntity<List<CambiosBitacora>>(cambiosBitacora, HttpStatus.OK);
    }

    private List<CambiosBitacora> getCambiosBitacora(Bitacora bitacora) {

        List<CambiosBitacora> listaFinal = new ArrayList<CambiosBitacora>();
        if (bitacora.getFchaCreacion() != "") {
            CambiosBitacora cambioBitacora = new CambiosBitacora();
            cambioBitacora.setEstado("Creado");
            cambioBitacora.setFecha(bitacora.getFchaCreacion());
            cambioBitacora.setUsuario(bitacora.getUserCreacion());
            listaFinal.add(cambioBitacora);
        }
        if (bitacora.getFchaEdicion() != "") {
            CambiosBitacora cambioBitacora = new CambiosBitacora();
            cambioBitacora.setEstado("Editado");
            cambioBitacora.setFecha(bitacora.getFchaEdicion());
            cambioBitacora.setUsuario(bitacora.getUserEdicion());
            listaFinal.add(cambioBitacora);
        }
        if (bitacora.getFchaDesactivacion() != "") {
            CambiosBitacora cambioBitacora = new CambiosBitacora();
            cambioBitacora.setEstado("Desactivado");
            cambioBitacora.setFecha(bitacora.getFchaDesactivacion());
            cambioBitacora.setUsuario(bitacora.getUserEdicion());
            listaFinal.add(cambioBitacora);
        }
        return listaFinal;
    }

    @PostMapping(value = "/listTblAT", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultTableAT> listTblAT(@RequestParam Integer ejercicio,
                                                        @RequestParam String sector,
                                                        @RequestParam String institucion,
                                                        @RequestParam Integer jtStartIndex,
                                                        @RequestParam Integer jtPageSize) {
        int jtEndIndex = jtStartIndex + jtPageSize;
        List<AreaTransaccionalDTO> records = mantenedoresService.getAT(ejercicio, sector, institucion);
        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }
        JsonResultTableAT result = new JsonResultTableAT("OK", records.subList(jtStartIndex, jtEndIndex), records.size());
        return new ResponseEntity<JsonResultTableAT>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/updTblArea", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblArea(@RequestParam Integer ejercicio,
                                                              @RequestParam Integer id,
                                                              @RequestParam Integer idInstitucion,
                                                              @RequestParam String codigoArea,
                                                              @RequestParam String rut,
                                                              @RequestParam String dv,
                                                              @RequestParam String nombre,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updArea(ejercicio, id, idInstitucion, codigoArea, rut, dv, nombre, usuario);
            if (resp.getId() == 0 && "SE HA ACTUALIZADO LA AREA TRANSACCIONAL".equalsIgnoreCase(resp.getMessage())) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblInstitucion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblInstitucion(@RequestParam Integer id,
                                                              @RequestParam String instCodigo,
                                                              @RequestParam String nombre,
                                                              @RequestParam Integer ejercicio,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");

        if (!usuario.equalsIgnoreCase("")) {
            HttpSession session = request.getSession();
            Integer sectorId = Integer.valueOf(session.getAttribute("idSectorSelected").toString());
            JsonJTableExpenseBean resp = mantenedoresService.updInstitucion(id, instCodigo, sectorId, nombre, usuario, ejercicio);
            if (resp.getId() == null) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblSector", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblSector(@RequestParam String nombre,
                                                             HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");

        if (!usuario.equalsIgnoreCase("")) {
            HttpSession session = request.getSession();
            Integer ejercicioSelected = Integer.valueOf(session.getAttribute("ejercicioSelected").toString());
            Integer idSector = Integer.valueOf(session.getAttribute("idSectorSelected").toString());
            JsonJTableExpenseBean resp = mantenedoresService.updSector(idSector, nombre, usuario);
            if (resp.getId() == null) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblSector", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblSector(@RequestParam Integer idSector,
                                                             HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.delSector(idSector, usuario);
            jsonAdd.setRecord(resp);
            if (Integer.valueOf(resp.getNombre()) > 0)
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblInstitucion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblInstitucion(@RequestParam Integer id,
                                                                 @RequestParam Integer ejercicio,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.delInstitucion(id, usuario, ejercicio);
            jsonAdd.setRecord(resp);
            if (Integer.valueOf(resp.getNombre()) > 0)
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblAreaT", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblAreaT(@RequestParam Integer id,
                                                          @RequestParam Integer ejercicio,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.delArea(id, ejercicio, usuario);
            jsonAdd.setRecord(resp);
            if (Integer.valueOf(resp.getNombre()) > 0)
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/getCodigoSectorById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCodigoSectorById(@RequestParam Integer idAT) {

        JsonJTableExpenseBean resp = mantenedoresService.getCodigoSectorById(idAT);
        return new ResponseEntity<String>(resp.getCodSector(), HttpStatus.OK);
    }

    @PostMapping(value = "/getCodigoInstitucionById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCodigoInstitucionById(@RequestParam Integer idAT,
                                                        @RequestParam String idEjercicio) {
        JsonJTableExpenseBean resp = mantenedoresService.getCodigoInstitucionById(idAT, idEjercicio);
        return new ResponseEntity<String>(resp.getCodInstitucion(), HttpStatus.OK);
    }

    @PostMapping(value = "/getCodigoSectorByIdIns", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCodigoSectorByIdIns(@RequestParam Integer idAT,
                                                          @RequestParam Integer idEjercicio) {
        JsonJTableExpenseBean resp = mantenedoresService.getCodigoSectorByIdIns(idAT, idEjercicio);
        return new ResponseEntity<String>(resp.getCodSector(), HttpStatus.OK);
    }

    @PostMapping(value = "/getInstitucionById", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getInstitucionById(@RequestParam Integer idAT,
                                                  @RequestParam String idEjercicio) {
        JsonJTableExpenseBean resp = mantenedoresService.getInstitucionById(idAT, idEjercicio);
        return new ResponseEntity<String>(resp.getNombre(), HttpStatus.OK);
    }

    @PostMapping(value = "/getATById", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getATById(@RequestParam Integer idAT,
                                                  @RequestParam String idEjercicio) {
        JsonJTableExpenseBean resp = mantenedoresService.getAreaById(idAT, idEjercicio);
        return new ResponseEntity<String>(resp.getNombre(), HttpStatus.OK);
    }

    @PostMapping(value = "/getSectorById", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getSectorById(@RequestParam Integer idAT) {
        JsonJTableExpenseBean resp = mantenedoresService.getSectorById(idAT);
        return new ResponseEntity<String>(resp.getNombre(), HttpStatus.OK);
    }

    @PostMapping(value = "/addTblInstitucion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblInstitucion(@RequestParam String codInstitucion,
                                                              @RequestParam String nomInstitucion,
                                                              @RequestParam String codigoPadre,
                                                              @RequestParam Integer sector,
                                                                 @RequestParam Integer ejercicio,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("usr");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.addInstitucion(codInstitucion, nomInstitucion, codigoPadre, sector, usuario, ejercicio);
            if (Integer.valueOf(resp.getNombre()) > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addTblAT", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblAT(@RequestParam String sector,
                                                        @RequestParam String nombre,
                                                        @RequestParam(required = false) Integer ejercicio,
                                                        HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");

        HttpSession session = request.getSession();
        Integer ejercicioSelected;
        if (session.getAttribute("ejercicioSelected") != null) {
            ejercicioSelected = (Integer) session.getAttribute("ejercicioSelected");
        } else {
            ejercicioSelected = ejercicio;
        }

        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.addSector(ejercicioSelected, sector, nombre, usuario);
            jsonAdd.setRecord(resp);
            if (Integer.valueOf(resp.getNombre()) > 0) {
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addTblArea", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblArea(@RequestParam String codigo,
                                                          @RequestParam String nombre,
                                                          @RequestParam String rut,
                                                          @RequestParam String digitoV,
                                                          @RequestParam String codigoSector,
                                                          @RequestParam String codigoInstitucion,
                                                          @RequestParam Integer institucion,
                                                          @RequestParam Integer ejercicio,
                                                              HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if (!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.addArea(codigo, nombre, rut, digitoV, codigoSector, codigoInstitucion, institucion, usuario, ejercicio);
            if (Integer.valueOf(resp.getNombre()) > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/loadSector", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AreaTransaccionalDTO>> loadSector(HttpServletRequest request,
                                                                       @RequestParam Integer ejercicioId) {
        List<AreaTransaccionalDTO> sectores = mantenedoresService.getSector(ejercicioId);

        HttpSession sesion = request.getSession();
        sesion.setAttribute("ejercicioSelected", ejercicioId);
        sesion.setAttribute("sectoresLista", sectores);
        return new ResponseEntity<List<AreaTransaccionalDTO>>(sectores, HttpStatus.OK);
    }

    @PostMapping(value = "/loadInstitucion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AreaTransaccionalDTO>> loadInstitucion(HttpServletRequest request,
                                                                      @RequestParam Integer ejercicioId,
                                                                      @RequestParam Integer sectorId2,
                                                                      @RequestParam Integer sectorId){
        List<AreaTransaccionalDTO> ins = mantenedoresService.getInstitucion(ejercicioId, sectorId2);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("idSectorSelected", String.valueOf(sectorId));




        return new ResponseEntity<List<AreaTransaccionalDTO>>(ins, HttpStatus.OK);
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
