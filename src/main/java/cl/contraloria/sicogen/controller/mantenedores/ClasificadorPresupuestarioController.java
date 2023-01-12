package cl.contraloria.sicogen.controller.mantenedores;

import cl.contraloria.sicogen.exceptions.SicogenException;
import cl.contraloria.sicogen.mappers.CuentaParticularMapper;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.FiltrosService;
import cl.contraloria.sicogen.service.InformesService;
import cl.contraloria.sicogen.service.MantenedoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/administracion/mantenedores")
public class ClasificadorPresupuestarioController {

    private InformesService informesService;
    private FiltrosService filtrosService;
    private MantenedoresService mantenedoresService;

    public ClasificadorPresupuestarioController(InformesService informesService,
                                                FiltrosService filtrosService,
                                                MantenedoresService mantenedoresService) {
        this.informesService = informesService;
        this.filtrosService = filtrosService;
        this.mantenedoresService = mantenedoresService;
    }

    @GetMapping(value = "/verMantenedorTblPlanCuentasPresup")
    public String getClasificadorPresupesutario(Model model) throws SQLException {
        List<EjerciciosDTO> listaEjercicios = informesService.getEjercicios();
        model.addAttribute("listaEjercicios", listaEjercicios);
        model.addAttribute("listaPartidas", informesService.getlistaPartida(listaEjercicios.get(0).getEjercicioId()));
        return "administracion/mantenedores/TblClasificadorPresupuestario";
    }

    @GetMapping(value = "/get-partidas")
    public ResponseEntity<List<ProgramaPresupuestarioDTO>> getPartidas(@RequestParam Integer idEjercicio) throws SQLException {
        return new ResponseEntity<List<ProgramaPresupuestarioDTO>>(informesService.getlistaPartida(idEjercicio), HttpStatus.OK);
    }

    @GetMapping(value = "/get-capitulos")
    public ResponseEntity<List<ProgramaPresupuestarioDTO>> getCapitulos(@RequestParam Integer idEjercicio,
                                                                        @RequestParam Integer idPartida) throws SQLException {
        return new ResponseEntity<List<ProgramaPresupuestarioDTO>>(informesService.getlistaCapitulo(idPartida, idEjercicio), HttpStatus.OK);
    }

    @GetMapping(value = "/get-programas")
    public ResponseEntity<List<ProgramaBO>> getProgramas(@RequestParam String idEjercicio,
                                                         @RequestParam String idCapitulo) {
        return new ResponseEntity<List<ProgramaBO>>(filtrosService.getProgramaByCapituloId(idCapitulo, idEjercicio), HttpStatus.OK);
    }

    @PostMapping(value = "/verMantenedorTblPlanCuentasPresupPost")
    public String getClasificadorPresupesutarioPost(Model model) throws SQLException {
        List<EjerciciosDTO> listaEjercicios = informesService.getEjercicios();
        model.addAttribute("listaEjercicios", listaEjercicios);
        model.addAttribute("listaPartidas", informesService.getlistaPartida(listaEjercicios.get(0).getEjercicioId()));
        return "administracion/mantenedores/TblClasificadorPresupuestario";
    }

    @PostMapping(value = "/get-partidas-post")
    public ResponseEntity<JsonResultJTable> getPartidasPost(@RequestParam Integer idEjercicio) throws SQLException {
        List<OptionsJtable> partidas = informesService.getlistaPartidaJTable(idEjercicio);
        JsonResultJTable result = new JsonResultJTable();
        result.setResult("OK");
        result.setOptions(partidas);
        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/get-capitulos-post")
    public ResponseEntity<JsonResultJTable> getCapitulosPost(@RequestParam Integer idEjercicio,
                                                                        @RequestParam Integer idPartida) throws SQLException {
        List<OptionsJtable> capitulos = informesService.getlistaCapituloJTable(idPartida, idEjercicio);
        JsonResultJTable result = new JsonResultJTable();
        result.setResult("OK");
        result.setOptions(capitulos);
        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/get-programas-post")
    public ResponseEntity<JsonResultJTable> getProgramasPost(@RequestParam String idEjercicio,
                                                         @RequestParam String idCapitulo) {
        List<OptionsJtable> programas = filtrosService.getProgramaByCapituloIdJTable(idCapitulo, idEjercicio);
        JsonResultJTable result = new JsonResultJTable();
        result.setResult("OK");
        result.setOptions(programas);
        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/listTblCuentasParticulares", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableCuentasParticulares> listTblCuentasParticulares(@RequestParam Integer idEjercicio,
                                                                                          @RequestParam Integer idPartida,
                                                                                          @RequestParam Integer idCapitulo,
                                                                                          @RequestParam Integer idPrograma,
                                                                                          @RequestParam Integer jtStartIndex,
                                                                                          @RequestParam Integer jtPageSize,
                                                                                          @RequestParam(required = false) String jtSorting) {
        int jtEndIndex = jtStartIndex + jtPageSize;

        List<CuentaParticularPresupDTO> records = mantenedoresService.getCuentasParticularesPresup(idEjercicio, idPartida, idCapitulo, idPrograma);

        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }

        JsonResultJTableCuentasParticulares result = new JsonResultJTableCuentasParticulares();
        result.setResult("OK");
        result.setRecords(records.subList(jtStartIndex, jtEndIndex));
        result.setTotalRecordCount(records.size());

        return new ResponseEntity<JsonResultJTableCuentasParticulares>(result, HttpStatus.OK);
    }

//    @PostMapping(value = "/create-cta-particular", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<JsonResultJTableAdd> createCtaParticularPresup(HttpServletRequest req) {
//
//        CuentaParticularPresupDTO cta = CuentaParticularMapper.mapRow(req);
//        HttpSession session = req.getSession(false);
//        UsuarioDTO usr = (UsuarioDTO) session.getAttribute("usr");
//        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
//        jsonAdd.setResult("OK");
//        try {
//            if (!"".equalsIgnoreCase(usr.getUserLogin())) {
//                JsonJTableExpenseBean resp =  mantenedoresService.createCtaParticularPresup(cta, usr.getUserLogin());
//                if (resp.getCodigo()!=null) {
//                    jsonAdd.setRecord(resp);
//                    return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
//                } else
//                    return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
//        } catch (SicogenException ex) {
//            jsonAdd.setResult(ex.getCodError());
//            jsonAdd.setMessage(ex.getMensaje());
//            return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
//        }
//    }

    @PostMapping(value = "/create-cta-particular", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> createCtaParticularPresup(HttpServletRequest req, @RequestBody CuentaParticularPresupDTO cta) {

        //cta = CuentaParticularMapper.mapRow(req);
        HttpSession session = req.getSession(false);
        UsuarioDTO usr = (UsuarioDTO) session.getAttribute("usr");
        ResultadoEjecucion resultadoEjecucion = new ResultadoEjecucion();
        resultadoEjecucion.setCodEjec("0");
        String[] codCuentaSplit = cta.getCodCuenta().split("\\.");
        cta.setCodSubtitulo(codCuentaSplit[0]);
        cta.setCodItem(codCuentaSplit[1]);
        cta.setCodAsignacion(codCuentaSplit[2]);
        cta.setCodSubAsignacion(codCuentaSplit[3]);
        try {
             mantenedoresService.createCtaParticularPresup(cta, usr.getUserLogin());
            return new ResponseEntity<ResultadoEjecucion>(resultadoEjecucion, HttpStatus.OK);
            }
        catch (SicogenException ex) {
        resultadoEjecucion.setCodEjec(ex.getCodError());
        resultadoEjecucion.setMsgEjec(ex.getMensaje());
            return new ResponseEntity<ResultadoEjecucion>(resultadoEjecucion, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/get-cta-particular")
    public String getCtaParticularPresup(Model model,
                                         @RequestParam Integer idCuenta,
                                         HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession(false);
        session.setAttribute("idCuentaParticularEdit", idCuenta);
        CuentaParticularPresupDTO cta = mantenedoresService.getCtaParticularPresup(idCuenta);
        List<EjerciciosDTO> listaEjercicios = informesService.getEjercicios();
        Integer idEjercicio = cta.getIdEjercicio();
        Integer idPartida = cta.getIdPartida();
        Integer idCapitulo = cta.getIdCapitulo();
        model.addAttribute("ctaParticular", cta);
        model.addAttribute("listaEjercicios", listaEjercicios);
        model.addAttribute("listaPartidas", informesService.getlistaPartida(listaEjercicios.get(0).getEjercicioId()));
        model.addAttribute("listaCapitulos", informesService.getlistaCapitulo(idPartida, idEjercicio));
        model.addAttribute("listaProgramas", filtrosService.getProgramaByCapituloId(String.valueOf(idCapitulo), String.valueOf(idEjercicio)));
        return "administracion/mantenedores/TblEditCtaParticular";
    }

    @PostMapping(value = "/update-cta-particular", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> updateCtaParticular(HttpServletRequest req,
                                                                  @RequestBody CuentaParticularPresupDTO cta) {
        HttpSession session = req.getSession(false);
        UsuarioDTO usr = (UsuarioDTO) session.getAttribute("usr");
        Integer idCta = (Integer) session.getAttribute("idCuentaParticularEdit");
        ResultadoEjecucion result = new ResultadoEjecucion();
        result.setCodEjec("0");
        String[] codCuentaSplit = cta.getCodCuenta().split("\\.");
        cta.setCodSubtitulo(codCuentaSplit[0]);
        cta.setCodItem(codCuentaSplit[1]);
        cta.setCodAsignacion(codCuentaSplit[2]);
        cta.setCodSubAsignacion(codCuentaSplit[3]);
        cta.setId(idCta);

        try {
            mantenedoresService.updateCtaParticularPresup(cta, usr.getUserLogin());
            return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
        } catch (SicogenException ex) {
            result.setCodEjec(ex.getCodError());
            result.setMsgEjec(ex.getMensaje());
            return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/get-ctas-dependientes")
    public String getCtasDependientes(@RequestParam Integer idCuenta,
                                      Model model,
                                      HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        session.setAttribute("idCuentaParticularDesAct", idCuenta);
        model.addAttribute("listaCtasDependientes", mantenedoresService.getCtasParticularesDepend(idCuenta));
        return "administracion/mantenedores/TblCtasDependientes";
    }

    @PostMapping(value = "/desactivar-cta-particular")
    public ResponseEntity<ResultadoEjecucion> desactivarCtaParticular(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        UsuarioDTO usr = (UsuarioDTO) session.getAttribute("usr");
        Integer idCta = (Integer) session.getAttribute("idCuentaParticularDesAct");
        mantenedoresService.desactivarCtaParticulares(idCta, usr.getUserLogin());
        return new ResponseEntity<ResultadoEjecucion>(HttpStatus.OK);
    }

    @PostMapping(value = "/eliminar-cta-particular")
    public ResponseEntity<JsonResultJTable> eliminarCtaParticular(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        UsuarioDTO usr = (UsuarioDTO) session.getAttribute("usr");
        Integer idCta = (Integer) session.getAttribute("idCuentaParticularDesAct");
        mantenedoresService.eliminarCtaParticulares(idCta, usr.getUserLogin());
        JsonResultJTable result = new JsonResultJTable();
        result.setResult("OK");
        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/activar-cta-particular")
    public ResponseEntity<ResultadoEjecucion> activarCtaParticular(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        UsuarioDTO usr = (UsuarioDTO) session.getAttribute("usr");
        Integer idCta = (Integer) session.getAttribute("idCuentaParticularDesAct");
        mantenedoresService.activarCtaParticulares(idCta, usr.getUserLogin());
        return new ResponseEntity<ResultadoEjecucion>(HttpStatus.OK);
    }

    @GetMapping(value = "/verBitacoraCatalogoPresup")
    public String verBitacoraCatalogoPresup(Model model,
                                            @RequestParam Integer idCuenta) {
        model.addAttribute("bitacora", mantenedoresService.getRegistroBitacora(idCuenta));
        return "administracion/mantenedores/TblBitacoraCtasParticulares";
    }
}
