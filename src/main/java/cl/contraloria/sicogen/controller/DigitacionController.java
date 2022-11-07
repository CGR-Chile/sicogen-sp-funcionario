package cl.contraloria.sicogen.controller;

import cl.contraloria.sicogen.exceptions.SicogenException;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.*;
import cl.contraloria.sicogen.xml.XMLDocumentoTDR_II;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/digitacion")
public class DigitacionController {

    private static final String ID_EJERCICIO = "idEjercicio";
    private static final String ID_PERIODO = "idPeriodo";
    private static final String ID_TBL_SISTRADOC = "idTblSistradoc";
    private static final String EJERCICIO = "ejercicio";
    private static final String NRO_SISTRADOC = "nroSistradoc";
    private static final String OBJ_XML_II = "objXmlII";
    private static final String ID_PERIODO_DIGITACION = "idPeriodoDigitacion";
    private static final String ID_EJERCICIO_DIGITACION = "idEjercicioDigitacion";
    private static final String ID_INFORME_DIGITACION = "idInformeDigitacion";

    private InformesService informesService;
    private DigitacionService digitacionService;
    private FiltrosService filtrosService;
    private CatalogoPresupService catalogoPresupService;
    private PlanDeCuentasService planDeCuentasService;
    private MantenedoresService mantenedoresService;

    public DigitacionController(InformesService informesService,
                                DigitacionService digitacionService,
                                FiltrosService filtrosService,
                                CatalogoPresupService catalogoPresupService,
                                PlanDeCuentasService planDeCuentasService,
                                MantenedoresService mantenedoresService) {
        this.informesService = informesService;
        this.digitacionService = digitacionService;
        this.filtrosService = filtrosService;
        this.catalogoPresupService = catalogoPresupService;
        this.planDeCuentasService = planDeCuentasService;
        this.mantenedoresService = mantenedoresService;
    }

    @GetMapping(value = "/obtenerDigitacionTDR")
    public String obtenerDigitacionTDR(@RequestParam Integer nroSistradoc,
                                       @RequestParam String ejercicio,
                                       @RequestParam Integer idEjercicio,
                                       @RequestParam String informe,
                                       @RequestParam String tipoInforme,
                                       @RequestParam String idInforme,
                                       @RequestParam String tipDoc,
                                       @RequestParam String estado,
                                       @RequestParam String entEmisora,
                                       @RequestParam String fechaDocumento,
                                       @RequestParam String FechaRecep,
                                       @RequestParam String idTblSistradoc,
                                       @RequestParam Integer idPeriodo,
                                       Model model,
                                       HttpServletRequest request) throws IOException, SQLException {
        HttpSession session = request.getSession(false);
        List<Informe> informesPresupuestarios = informesService.getInformesPresupuestarios();
        String codInforme = "";

        for (Informe inf : informesPresupuestarios) {
            if (inf.getId().equals(idInforme)) {
                codInforme = inf.getCodigo();
                break;
            }
        }

        List<Partida> partidas = informesService.getListPartida(idEjercicio);

        model.addAttribute("tipoInforme", tipoInforme);
        model.addAttribute("informe", informe);
        model.addAttribute("estadoSicogen", estado);
        model.addAttribute(EJERCICIO, ejercicio);
        model.addAttribute("entidadEmisora", entEmisora);
        model.addAttribute("tipoDocumento", tipDoc);
        model.addAttribute("nroDocumento", nroSistradoc);
        model.addAttribute("fechaDocumento", fechaDocumento);
        model.addAttribute("fechaRecepcionCGR", FechaRecep);
        model.addAttribute(ID_PERIODO, idPeriodo);
        model.addAttribute("idSistradoc", idTblSistradoc);
        model.addAttribute("partidas", partidas);

        session.setAttribute(ID_PERIODO_DIGITACION, idPeriodo);

        if (codInforme.equals("TDRMP")) {
            InformeSistradocBO informeTDR = informesService.getInformeTDR(idTblSistradoc);
            List<DigitacionMPBO> listaDigitacion = digitacionService.buscarMPDigitacionTDR(idEjercicio, nroSistradoc, idPeriodo, tipDoc);
            model.addAttribute("informeTDR", informeTDR);
            model.addAttribute("listaDigitacion", listaDigitacion);
            session.setAttribute("idDocuementoDig", idTblSistradoc);
            return "digitacion/tabla-procesa-archivo-mp";
        } else if (codInforme.equals("TDRII")) {
            session.setAttribute(ID_TBL_SISTRADOC, idTblSistradoc);
            session.setAttribute(EJERCICIO, ejercicio);
            session.setAttribute(NRO_SISTRADOC, nroSistradoc);
            session.setAttribute(ID_EJERCICIO_DIGITACION, idEjercicio);
            session.setAttribute(ID_INFORME_DIGITACION, idInforme);
            model.addAttribute("listaMonedas", digitacionService.getMonedasValidas());
            model.addAttribute("listSubt", catalogoPresupService.getSubtitulosByEjericio(idEjercicio));
            model.addAttribute(OBJ_XML_II, digitacionService.obtieneDigitacionXML(Integer.valueOf(idTblSistradoc), ejercicio, nroSistradoc));
            return "digitacion/tabla-procesa-archivo-ii";
        } else {
            return "";
        }
    }

    @GetMapping(value = "/getCapitulosDigitacionTDRMP", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CapituloBO>> getCapitulosDigitacionTDRMP(@RequestParam String codPartida,
                                                                        HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        List<CapituloBO> capitulos = filtrosService.getCapituloByPartidaId(codPartida, String.valueOf(idEjercicio));
        return new ResponseEntity<List<CapituloBO>>(capitulos, HttpStatus.OK);
    }

    @GetMapping(value = "/getProgramasDigitacionTDRMP", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProgramaBO>> getProgramasDigitacionTDRMP(@RequestParam String codPartida,
                                                                        @RequestParam String codCapitulo,
                                                                        HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        Integer idPartida = digitacionService.getIdPartida(codPartida);
        Integer idCapitulo = digitacionService.getIdCapitulo(codCapitulo, idPartida);
        List<ProgramaBO> programas = filtrosService.getProgramaByCapituloId(String.valueOf(idCapitulo), String.valueOf(idEjercicio));
        return new ResponseEntity<List<ProgramaBO>>(programas, HttpStatus.OK);
    }

    @GetMapping(value = "/insertMPDigitacionTDR", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> insertMPDigitacionTDR(@RequestParam String codPartida,
                                                                    @RequestParam String codCapitulo,
                                                                    @RequestParam String idPrograma,
                                                                    @RequestParam Integer numDoc,
                                                                    @RequestParam String tipoDoc,
                                                                    HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        Integer idPeriodo = (Integer) session.getAttribute(ID_PERIODO);
        ResultadoEjecucion result;

        try {
            digitacionService.insertarMPDigitacionTDR(codPartida, codCapitulo, idPrograma, idEjercicio, numDoc, idPeriodo, tipoDoc);
            result = new ResultadoEjecucion("0", "OK");
        } catch (SicogenException ex) {
            result = new ResultadoEjecucion(ex.getCodError(), ex.getMensaje());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getGrillaMPDigitacionTDR")
    public String getGrillaMPDigitacionTDR(@RequestParam Integer numDoc,
                                           @RequestParam String tipoDoc,
                                           HttpServletRequest request,
                                           Model model) {
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        Integer idPeriodo = (Integer) session.getAttribute(ID_PERIODO);
        List<DigitacionMPBO> listaDigitacion = digitacionService.buscarMPDigitacionTDR(idEjercicio, numDoc, idPeriodo, tipoDoc);
        model.addAttribute("listaDigitacion", listaDigitacion);
        return "digitacion/tabla-digitacion-presupuesto";
    }

    @GetMapping(value = "/deleteMPDigitacionTDR", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> deleteMPDigitacionTDR(@RequestParam Integer idFila) {
        ResultadoEjecucion result;

        try {
            digitacionService.deleteMPDigitacionTDR(idFila);
            result = new ResultadoEjecucion("0", "OK");
        } catch (SicogenException ex) {
            result = new ResultadoEjecucion(ex.getCodError(), ex.getMensaje());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarCuentaPIDigitacionTDRDetalle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaPresupValidacion> buscarCuentaPIDigitacionTDRDetalle(@RequestParam String codCuentaCompleta,
                                                                                     @RequestParam Integer idPrograma,
                                                                                     HttpServletRequest request) {
        String codCuenta = codCuentaCompleta.replace(".", "");
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        CuentaPresupValidacion cuentaPresupValidacion = digitacionService.getDescripcionCuenta(codCuenta, idPrograma, idEjercicio);

        return new ResponseEntity<CuentaPresupValidacion>(cuentaPresupValidacion, HttpStatus.OK);
    }

    @GetMapping(value = "/createCuentaParticularEntidad", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> createCuentaParticularEntidad(@RequestParam String codPartida,
                                                                            @RequestParam String codCapitulo,
                                                                            @RequestParam String codPrograma,
                                                                            @RequestParam String codCuenta,
                                                                            @RequestParam String nomCuenta,
                                                                            @RequestParam Integer idCuentaGen,
                                                                            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        String idDocumento = (String) session.getAttribute("idDocuementoDig");
        String codCuentaReplace = codCuenta.replace(".", "");
        UsuarioDTO usr = (UsuarioDTO) session.getAttribute("usr");
        ResultadoEjecucion result;

        try {
            Integer newId = digitacionService.createCuentaParticularEntidad(idEjercicio, codPartida, codCapitulo, codPrograma, codCuentaReplace, nomCuenta, idCuentaGen, Integer.valueOf(idDocumento), usr.getUserLogin());
            result = new ResultadoEjecucion("0", "OK");
            result.setNewId(newId);
        } catch (SicogenException ex) {
            result = new ResultadoEjecucion(ex.getCodError(), ex.getMensaje());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarDetalleMPDigitacionTDR")
    public String buscarDetalleMPDigitacionTDR(@RequestParam Integer idTDRMP,
                                               HttpServletRequest request,
                                               Model model) {
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        List<DetTDRMPDTO> listaDetalleTDRMP = digitacionService.getListDetTDRMP(idTDRMP, idEjercicio);
        model.addAttribute("listaDetalleTDRMP", listaDetalleTDRMP);
        return "digitacion/tabla-det-tdr-mp";
    }

    @GetMapping(value = "/insertarTDRMPAumentoDisminucion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> insertarTDRMPAumentoDisminucion(@RequestParam Integer idTDRMP,
                                                                              @RequestParam Integer idCuenEnt,
                                                                              @RequestParam Integer checkOpcion,
                                                                              @RequestParam String desMoneda,
                                                                              @RequestParam Integer monto) {
        ResultadoEjecucion result;

        try {
            if (checkOpcion == 0) {
                digitacionService.insertarAumentoTDRMP(idTDRMP, idCuenEnt, desMoneda, monto);
            } else {
                digitacionService.insertarDisminucionTDRMP(idTDRMP, idCuenEnt, desMoneda, monto);
            }
            result = new ResultadoEjecucion("0", "OK");
        } catch (SicogenException ex) {
            result = new ResultadoEjecucion(ex.getCodError(), ex.getMensaje());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/deleteDetalleDigitacionTDRMP", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> deleteDetalleDigitacionTDRMP(@RequestParam Integer idDetTDRMP) {
        ResultadoEjecucion result;

        try {
            digitacionService.deleteDetalleMPDigitacionTDR(idDetTDRMP);
            result = new ResultadoEjecucion("0", "OK");
        } catch (SicogenException ex) {
            result = new ResultadoEjecucion(ex.getCodError(), ex.getMensaje());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/updateDetalleAumDisDigitacionMP", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> updateDetalleAumDisDigitacionMP(@RequestParam Integer idDetTDRMP,
                                                                              @RequestParam Integer montoAumento,
                                                                              @RequestParam Integer montoDisminucion) {
        ResultadoEjecucion result;

        try {
            digitacionService.updateDetalleAumDisDigitacionMP(idDetTDRMP, montoAumento, montoDisminucion);
            result = new ResultadoEjecucion("0", "OK");
        } catch (SicogenException ex) {
            result = new ResultadoEjecucion(ex.getCodError(), ex.getMensaje());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/guardarMPDigitacionTDR", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> guardarMPDigitacionTDR(@RequestParam int numDoc,
                                                                     @RequestParam String tipoDoc,
                                                                     HttpServletRequest request) {
        ResultadoEjecucion result = new ResultadoEjecucion();
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        Integer idPeriodo = (Integer) session.getAttribute(ID_PERIODO_DIGITACION);

        try {
            digitacionService.guardarDigitacion(idEjercicio, idPeriodo, numDoc, tipoDoc);
            result.setCodEjec("0");
            result.setMsgEjec("Informe Guardado");
        } catch (SicogenException ex) {
            result.setCodEjec("-1");
            result.setMsgEjec(ex.getMensaje());
        } catch (Exception ex) {
            result.setCodEjec("-1");
            result.setMsgEjec(ex.getMessage());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getDialogGlosas")
    public String getDialogGlosas(@RequestParam Integer idTDRMP,
                            @RequestParam Integer idDetTDRMP,
                            @RequestParam String codPartida,
                            @RequestParam String codCapitulo,
                            @RequestParam String codPrograma,
                            Model model) {
        List<GlosaTDRMPDTO> glosas = digitacionService.getGlosas(idTDRMP, idDetTDRMP, codPartida, codCapitulo, codPrograma);
        model.addAttribute("glosas", glosas);
        return "digitacion/dialog-glosas";
    }

    @GetMapping(value = "/getGlosas")
    public String getGlosas(@RequestParam Integer idTDRMP,
                            @RequestParam Integer idDetTDRMP,
                            @RequestParam String codPartida,
                            @RequestParam String codCapitulo,
                            @RequestParam String codPrograma,
                            Model model) {
        List<GlosaTDRMPDTO> glosas = digitacionService.getGlosas(idTDRMP, idDetTDRMP, codPartida, codCapitulo, codPrograma);
        model.addAttribute("glosas", glosas);
        return "digitacion/tabla-det-glosas";
    }

    @PostMapping(value = "/createGlosa", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> createGlosa(@RequestBody GlosaTDRMPDTO glosa) {
        ResultadoEjecucion result;

        try {
            digitacionService.createGlosa(
                    glosa.getIdTDRMP(),
                    glosa.getIdDetTDRMP(),
                    "01",
                    glosa.getNumGlosa(),
                    glosa.getTextoGlosa(),
                    glosa.getIndPresup(),
                    glosa.getCodPart(),
                    glosa.getCodCap(),
                    glosa.getCodProg(),
                    glosa.getNomPart(),
                    glosa.getNomCap(),
                    glosa.getNomProg()
            );
            result = new ResultadoEjecucion("0", "OK");
        } catch (SicogenException ex) {
            result = new ResultadoEjecucion(ex.getCodError(), ex.getMensaje());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/updateGlosa", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> updateGlosa(@RequestBody GlosaTDRMPDTO glosa) {
        ResultadoEjecucion result;

        try {
            digitacionService.updateGlosa(glosa);
            result = new ResultadoEjecucion("0", "OK");
        } catch (SicogenException ex) {
            result = new ResultadoEjecucion(ex.getCodError(), ex.getMensaje());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/deleteGlosa", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> deleteGlosa(@RequestParam Integer idGloTDRMP) {
        ResultadoEjecucion result;

        try {
            digitacionService.deleteGlosa(idGloTDRMP);
            result = new ResultadoEjecucion("0", "OK");
        } catch (SicogenException ex) {
            result = new ResultadoEjecucion(ex.getCodError(), ex.getMensaje());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/volverBusquedaTDR")
    public String volverBusquedaTDR(Model model,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        Integer idEjercicio = (Integer) session.getAttribute("idEjercicioDigitacionTDR");
        Integer idInforme = (Integer) session.getAttribute("idInformeDigitacionTDR");
        Integer idEstadoSicogen = (Integer) session.getAttribute("idEstadoSicogenDigitacionTDR");
        Integer idPeriodo = (Integer) session.getAttribute("idPeriodoDigitacionTDR");

        List<InformeSistradocBO> informes = informesService.getInformesTDR(idInforme, idEjercicio, idEstadoSicogen, idPeriodo);
        model.addAttribute("informes", informes);
        return "carga-informe/tabla-documentos-sistradoc";
    }

    @PostMapping(value = "/validarMPDigitacionTDR", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> validarMPDigitacionTDR(@RequestBody ValidacionInformeTDRMPDTO datos,
                                                                     HttpServletRequest request) {
        ResultadoEjecucion result = new ResultadoEjecucion();
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        UsuarioDTO userSession = (UsuarioDTO) session.getAttribute("usr");

        try {
            result = digitacionService.validarMPDigitacionTDR(
                    datos.getIdPeriodo(), datos.getNumDoc(),
                    datos.getDia(), datos.getMes(),
                    datos.getTipoDoc(),
                    datos.getIdInforme(),
                    idEjercicio,
                    datos.getIdSistradoc(),
                    datos.getEjercicio(),
                    userSession.getUserLogin()
            );
        } catch (SicogenException ex) {
            result.setCodEjec("-1");
            result.setMsgEjec(ex.getMensaje());
        } catch (Exception ex) {
            result.setCodEjec("-1");
            result.setMsgEjec(ex.getMessage());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getCapitulosDigitacionTDRII", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CapituloBO>> getCapitulosDigitacionTDRII(@RequestParam String codPartida,
                                                                        HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        return new ResponseEntity<List<CapituloBO>>(filtrosService.getCapituloByPartidaId(codPartida, String.valueOf(idEjercicio)), HttpStatus.OK);
    }

    @GetMapping(value = "/getProgramasDigitacionTDRII", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProgramaBO>> getProgramasDigitacionTDRII(@RequestParam String idCapitulo,
                                                                        HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        return new ResponseEntity<List<ProgramaBO>>(filtrosService.getProgramaByCapituloId(idCapitulo, String.valueOf(idEjercicio)), HttpStatus.OK);
    }

    @GetMapping(value = "/getItemsBySubTitulo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemBO>> getItemsBySubTitulo(@RequestParam Integer idSubtitulo,
                                                                        HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        return new ResponseEntity<List<ItemBO>>(catalogoPresupService.getItemsBySubTitulo(idEjercicio, idSubtitulo), HttpStatus.OK);
    }

    @PostMapping(value = "/insertIIDigitacionTDR", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> insertIIDigitacionTDR(@RequestParam Integer idPrograma,
                                                                    @RequestParam Integer idCuenta,
                                                                    @RequestParam String moneda,
                                                                    HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
            return new ResponseEntity<ResultadoEjecucion>(digitacionService.insertIIDigitacionTDR(idPrograma, idCuenta, Integer.valueOf(idTblSistradoc), moneda), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ResultadoEjecucion>(new ResultadoEjecucion("-1", ex.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/insertIIDigitacionTDR_modi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> insertIIDigitacionTDR_modi(@RequestParam Integer idPrograma,
                                                                         @RequestParam Integer idCuenta,
                                                                         @RequestParam String moneda,
                                                                         HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
            return new ResponseEntity<ResultadoEjecucion>(digitacionService.insertIIDigitacionTDR_modi(idPrograma, idCuenta, Integer.valueOf(idTblSistradoc), moneda), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ResultadoEjecucion>(new ResultadoEjecucion("-1", ex.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getDetalleDigitacionIIIdent", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDetalleDigitacionIIIdent(Model model,
                                         HttpServletRequest request) throws IOException, SQLException {
        model.addAttribute(OBJ_XML_II, obtieneDigitacionXML(request));
        return "digitacion/tabla-det-tdr-ii-ident";
    }

    @GetMapping(value = "/getDetalleDigitacionIIMod", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDetalleDigitacionIIMod(Model model,
                                            HttpServletRequest request) throws IOException, SQLException {
        model.addAttribute(OBJ_XML_II, obtieneDigitacionXML(request));
        return "digitacion/tabla-det-tdr-ii-mod";
    }

    private XMLDocumentoTDR_II obtieneDigitacionXML(HttpServletRequest request) throws IOException, SQLException {
        HttpSession session = request.getSession(false);
        String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
        String ejercicio = (String) session.getAttribute(EJERCICIO);
        Integer nroSistradoc = (Integer) session.getAttribute(NRO_SISTRADOC);
        return digitacionService.obtieneDigitacionXML(Integer.valueOf(idTblSistradoc), ejercicio, nroSistradoc);
    }

    @PostMapping(value = "/deleteSeccionDecretoIdentificacion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> deleteSeccionDecretoIdentificacion(@RequestParam String codPart,
                                                                                 @RequestParam String codCap,
                                                                                 @RequestParam String codProg,
                                                                                 @RequestParam String codSubt,
                                                                                 @RequestParam String codItem,
                                                                                 @RequestParam String moneda,
                                                                                 HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
            return new ResponseEntity<ResultadoEjecucion>(digitacionService.deleteSeccionDecretoIdentificacion(
                    Integer.valueOf(idTblSistradoc),
                    codPart,
                    codCap,
                    codProg,
                    codSubt,
                    codItem,
                    moneda
            ), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ResultadoEjecucion>(new ResultadoEjecucion("-1", ex.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/deleteSeccionDecretoModificacion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> deleteSeccionDecretoModificacion(@RequestParam String codPart,
                                                                            @RequestParam String codCap,
                                                                            @RequestParam String codProg,
                                                                            @RequestParam String codSubt,
                                                                            @RequestParam String codItem,
                                                                            @RequestParam String moneda,
                                                                            HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
            return new ResponseEntity<ResultadoEjecucion>(digitacionService.deleteSeccionDecretoModificacion(
                    Integer.valueOf(idTblSistradoc),
                    codPart,
                    codCap,
                    codProg,
                    codSubt,
                    codItem,
                    moneda
            ), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ResultadoEjecucion>(new ResultadoEjecucion("-1", ex.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getProyectosByDigitacionIdent")
    public String getProyectosByDigitacionIdent(@RequestParam String codPart,
                                           @RequestParam String codCap,
                                           @RequestParam String codProg,
                                           @RequestParam String codSubt,
                                           @RequestParam String codItem,
                                           Model model,
                                           HttpServletRequest request) {
        getProyectosByDigitacion(codPart, codCap, codProg, codSubt, codItem, model, request);
        return "digitacion/dialog-add-proyecto-bip-ident";
    }

    @GetMapping(value = "/getProyectosByDigitacionMod")
    public String getProyectosByDigitacionMod(@RequestParam String codPart,
                                              @RequestParam String codCap,
                                              @RequestParam String codProg,
                                              @RequestParam String codSubt,
                                              @RequestParam String codItem,
                                              Model model,
                                              HttpServletRequest request) {
        getProyectosByDigitacion(codPart, codCap, codProg, codSubt, codItem, model, request);
        return "digitacion/dialog-add-proyecto-bip-mod";
    }

    public void getProyectosByDigitacion(String codPart,
                                         String codCap,
                                         String codProg,
                                         String codSubt,
                                         String codItem,
                                         Model model,
                                         HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO);
        model.addAttribute("listaAsignaciones", planDeCuentasService.getCuentasPresupTDRII(codSubt, codItem, String.valueOf(idEjercicio)));
        session.setAttribute("codPart", codPart);
        session.setAttribute("codCap", codCap);
        session.setAttribute("codProg", codProg);
        session.setAttribute("codSubt", codSubt);
        session.setAttribute("codItem", codItem);
    }

    @GetMapping(value = "/getProyectoByCodigo")
    public ResponseEntity<List<JsonJTableExpenseBean>> getProyectoByCodigo(@RequestParam String cdgProyectBuscado) {
        return new ResponseEntity<List<JsonJTableExpenseBean>>(digitacionService.getProyectoByCodigo(cdgProyectBuscado), HttpStatus.OK);
    }

    @PostMapping(value = "/addProyectoNuevo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonJTableExpenseBean> addProyectoNuevo(@RequestParam String addCodProyecto,
                                                                  @RequestParam String addNombreProyecto,
                                                                  @RequestParam String addEstadoProyecto,
                                                                  HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("usr");
        return new ResponseEntity<JsonJTableExpenseBean>(mantenedoresService.setProyectos(
                addCodProyecto, addNombreProyecto, user.getUserLogin(), addEstadoProyecto), HttpStatus.OK);
    }

    @PostMapping(value = "/postProyectosIIIdent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> postProyectosIIIdent(@RequestParam String codProyecto,
                                                                   HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
        String codPart = (String) session.getAttribute("codPart");
        String codCap = (String) session.getAttribute("codCap");
        String codProg = (String) session.getAttribute("codProg");
        String codSubt = (String) session.getAttribute("codSubt");
        String codItem = (String) session.getAttribute("codItem");

        try {
            return new ResponseEntity<ResultadoEjecucion>(digitacionService.postProyectosIIIdent(codProyecto, codPart,
                    codCap, codProg, codSubt, codItem, Integer.valueOf(idTblSistradoc), request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ResultadoEjecucion>(new ResultadoEjecucion("-1", ex.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/postProyectosIIMod", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> postProyectosIIMod(@RequestParam String codProyecto,
                                                                   HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
        String codPart = (String) session.getAttribute("codPart");
        String codCap = (String) session.getAttribute("codCap");
        String codProg = (String) session.getAttribute("codProg");
        String codSubt = (String) session.getAttribute("codSubt");
        String codItem = (String) session.getAttribute("codItem");

        try {
            return new ResponseEntity<ResultadoEjecucion>(digitacionService.postProyectosIIMod(codProyecto, codPart,
                    codCap, codProg, codSubt, codItem, Integer.valueOf(idTblSistradoc), request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ResultadoEjecucion>(new ResultadoEjecucion("-1", ex.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getEditarAsigProyectos")
    public String getEditarAsigProyectos(Model model,
                                         @RequestParam String idDecreto,
                                         @RequestParam String idMIIProyectos,
                                         @RequestParam String idDetalleMIIProyectos,
                                         @RequestParam String flagIdentificacion,
                                         HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession(false);
        String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
        model.addAttribute("montosAsignacion", digitacionService.getEditarAsigProyectos(idDecreto, idMIIProyectos, idDetalleMIIProyectos, flagIdentificacion, Integer.valueOf(idTblSistradoc)));
        session.setAttribute("idDecretoEditAsig", idDecreto);
        session.setAttribute("idMIIProyectosEditAsig", idMIIProyectos);
        session.setAttribute("idDetalleMIIProyectosEditAsig", idDetalleMIIProyectos);
        session.setAttribute("flagIdentificacionEditAsig", flagIdentificacion);
        return "digitacion/dialog-edit-asignacion-proyectos";
    }

    @PostMapping(value = "/postEditarAsigProyectos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> postEditarAsigProyectos(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession(false);
        String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
        String idDecreto = (String) session.getAttribute("idDecretoEditAsig");
        String idMIIProyectos = (String) session.getAttribute("idMIIProyectosEditAsig");
        String idDetalleMIIProyectos = (String) session.getAttribute("idDetalleMIIProyectosEditAsig");
        String flagIdentificacion = (String) session.getAttribute("flagIdentificacionEditAsig");
        digitacionService.postEditarAsigProyectos(request.getParameterMap(), Integer.valueOf(idTblSistradoc), idDecreto, idMIIProyectos, idDetalleMIIProyectos, flagIdentificacion);
        return new ResponseEntity<ResultadoEjecucion>(HttpStatus.OK);
    }

    @PostMapping(value = "/setDeleteProyectAsignacion")
    public ResponseEntity<ResultadoEjecucion> setDeleteProyectAsignacion(HttpServletRequest request,
                                                                         @RequestParam String codPart,
                                                                         @RequestParam String codCap,
                                                                         @RequestParam String codProg,
                                                                         @RequestParam String codSubt,
                                                                         @RequestParam String codItem,
                                                                         @RequestParam String codigoBIP) {
        HttpSession session = request.getSession(false);
        session.setAttribute("codPartDelAsig", codPart);
        session.setAttribute("codCapDelAsig", codCap);
        session.setAttribute("codProgDelAsig", codProg);
        session.setAttribute("codSubtDelAsig", codSubt);
        session.setAttribute("codItemDelAsig", codItem);
        session.setAttribute("codigoBIPDelAsig", codigoBIP);
        return new ResponseEntity<ResultadoEjecucion>(HttpStatus.OK);
    }

    @PostMapping(value = "/deleteProyectAsignacion")
    public ResponseEntity<ResultadoEjecucion> deleteProyectAsignacion(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession(false);
        String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
        String codPart = (String) session.getAttribute("codPartDelAsig");
        String codCap = (String) session.getAttribute("codCapDelAsig");
        String codProg = (String) session.getAttribute("codProgDelAsig");
        String codSubt = (String) session.getAttribute("codSubtDelAsig");
        String codItem = (String) session.getAttribute("codItemDelAsig");
        String codigoBIP = (String) session.getAttribute("codigoBIPDelAsig");
        digitacionService.deleteProyectAsignacion(Integer.valueOf(idTblSistradoc), codPart, codCap, codProg, codSubt, codItem, codigoBIP);
        return new ResponseEntity<ResultadoEjecucion>(HttpStatus.OK);
    }

    @GetMapping(value = "/getLimitesFuturos")
    public String getLimitesFuturos(HttpServletRequest request,
                                         Model model,
                                         @RequestParam String codigoBIP,
                                         @RequestParam String dvProyecto,
                                         @RequestParam String moneda,
                                         @RequestParam String codPart,
                                         @RequestParam String codCap,
                                         @RequestParam String codProg) {
        HttpSession session = request.getSession(false);
        String ejercicio = (String) session.getAttribute(EJERCICIO);
        model.addAttribute("limiteTemp", digitacionService.getLimitesFuturos(ejercicio, codigoBIP, dvProyecto, moneda));
        session.setAttribute("codigoBipAddLimFut", codigoBIP);
        session.setAttribute("dvProyectoAddLimFut", dvProyecto);
        session.setAttribute("monedaAddLimFut", moneda);
        session.setAttribute("codPartAddLimFut", codPart);
        session.setAttribute("codCapAddLimFut", codCap);
        session.setAttribute("codProgAddLimFut", codProg);
        return "digitacion/dialog-add-limites-futuro-ident";
    }

    @GetMapping(value = "/getLimitesFuturosMI")
    public String getLimitesFuturosMI(HttpServletRequest request,
                                      Model model,
                                      @RequestParam String codigoBIP,
                                      @RequestParam String dvProyecto,
                                      @RequestParam String moneda,
                                      @RequestParam String codPart,
                                      @RequestParam String codCap,
                                      @RequestParam String codProg) {
        HttpSession session = request.getSession(false);
        String ejercicio = (String) session.getAttribute(EJERCICIO);
        model.addAttribute("limiteTemp", digitacionService.getLimitesFuturos(ejercicio, codigoBIP, dvProyecto, moneda));
        session.setAttribute("codigoBipAddLimFut", codigoBIP);
        session.setAttribute("dvProyectoAddLimFut", dvProyecto);
        session.setAttribute("monedaAddLimFut", moneda);
        session.setAttribute("codPartAddLimFut", codPart);
        session.setAttribute("codCapAddLimFut", codCap);
        session.setAttribute("codProgAddLimFut", codProg);
        return "digitacion/dialog-add-limites-futuro-mod";
    }

    @PostMapping(value = "/agregarLimitesFuturos")
    public ResponseEntity<ResultadoEjecucion> agregarLimitesFuturos(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession(false);
        String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
        String codigoBIP = (String) session.getAttribute("codigoBipAddLimFut");
        String dvProyecto = (String) session.getAttribute("dvProyectoAddLimFut");
        String moneda = (String) session.getAttribute("monedaAddLimFut");
        String codPart = (String) session.getAttribute("codPartAddLimFut");
        String codCap = (String) session.getAttribute("codCapAddLimFut");
        String codProg = (String) session.getAttribute("codProgAddLimFut");
        digitacionService.agregarLimitesFuturos(request.getParameterMap(), Integer.valueOf(idTblSistradoc), codigoBIP, dvProyecto, moneda, codPart, codCap, codProg);
        return new ResponseEntity<ResultadoEjecucion>(HttpStatus.OK);
    }

    @GetMapping(value = "/getEditarLimitesFuturos")
    public String getEditarLimitesFuturos(HttpServletRequest request,
                                          Model model,
                                          @RequestParam String idDecreto,
                                          @RequestParam String idLimiteCompromiso) throws SQLException {
        HttpSession session = request.getSession(false);
        String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
        model.addAttribute("limiteTemp", digitacionService.getEditarLimitesFuturos(Integer.valueOf(idTblSistradoc), idDecreto, idLimiteCompromiso));
        session.setAttribute("idDecretoEditLimFut", idDecreto);
        session.setAttribute("idLimiteCompromisoEditLimFut", idLimiteCompromiso);
        return "digitacion/dialog-edit-limites-futuro";
    }

    @PostMapping(value = "/editarLimitesPost")
    public ResponseEntity<ResultadoEjecucion> editarLimitesPost(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession(false);
        String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
        String idDecreto = (String) session.getAttribute("idDecretoEditLimFut");
        String idLimiteCompromiso = (String) session.getAttribute("idLimiteCompromisoEditLimFut");
        digitacionService.editarLimitesPost(request.getParameterMap(), Integer.valueOf(idTblSistradoc), idDecreto, idLimiteCompromiso);
        return new ResponseEntity<ResultadoEjecucion>(HttpStatus.OK);
    }

    @PostMapping(value = "/setDeleteLimiteFuturo")
    public ResponseEntity<ResultadoEjecucion> setDeleteLimiteFuturo(HttpServletRequest request,
                                                                    @RequestParam String codPart,
                                                                    @RequestParam String codCap,
                                                                    @RequestParam String codProg,
                                                                    @RequestParam String codigoBIP) {
        HttpSession session = request.getSession(false);
        session.setAttribute("codigoBipDelLimFut", codigoBIP);
        session.setAttribute("codPartDelLimFut", codPart);
        session.setAttribute("codCapDelLimFut", codCap);
        session.setAttribute("codProgDelLimFut", codProg);
        return new ResponseEntity<ResultadoEjecucion>(HttpStatus.OK);
    }

    @PostMapping(value = "/deleteLimiteFuturo")
    public ResponseEntity<ResultadoEjecucion> deleteLimiteFuturo(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession(false);
        String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
        String codigoBIP = (String) session.getAttribute("codigoBipDelLimFut");
        String codPart = (String) session.getAttribute("codPartDelLimFut");
        String codCap = (String) session.getAttribute("codCapDelLimFut");
        String codProg = (String) session.getAttribute("codProgDelLimFut");
        digitacionService.deleteLimiteFuturo(Integer.valueOf(idTblSistradoc), codPart, codCap, codProg, codigoBIP);
        return new ResponseEntity<ResultadoEjecucion>(HttpStatus.OK);
    }

    @PostMapping(value = "/validarDigitacionTDRII", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> validarDigitacionTDRII(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UsuarioDTO usr = (UsuarioDTO) session.getAttribute("usr");
        String idTblSistradoc = (String) session.getAttribute(ID_TBL_SISTRADOC);
        Integer idEjercicio = (Integer) session.getAttribute(ID_EJERCICIO_DIGITACION);
        Integer idPeriodo = (Integer) session.getAttribute(ID_PERIODO_DIGITACION);
        String idInforme = (String) session.getAttribute(ID_INFORME_DIGITACION);
        String ejercicio = (String) session.getAttribute(EJERCICIO);
        InformeSistradocBO informeTDR = informesService.getInformeTDR(idTblSistradoc);

        ResultadoEjecucion resultadoEjecucion = new ResultadoEjecucion();

        try {
            resultadoEjecucion = digitacionService.validarDigitacionTDRII(
                    Integer.valueOf(idTblSistradoc),
                    informeTDR.getNroDocumento(),
                    informeTDR.getTipoDocumento(),
                    idEjercicio,
                    idPeriodo,
                    Integer.valueOf(idInforme),
                    ejercicio,
                    usr.getUserLogin()
            );
            return new ResponseEntity<ResultadoEjecucion>(resultadoEjecucion, HttpStatus.OK);
        } catch (Exception ex) {
            resultadoEjecucion.setCodEjec("-1");
            resultadoEjecucion.setMsgEjec(ex.getLocalizedMessage());
            return new ResponseEntity<ResultadoEjecucion>(resultadoEjecucion, HttpStatus.OK);
        }
    }

}
