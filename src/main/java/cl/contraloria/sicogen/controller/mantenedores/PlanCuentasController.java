package cl.contraloria.sicogen.controller.mantenedores;

import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.FiltrosService;
import cl.contraloria.sicogen.service.InformesService;
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
public class PlanCuentasController {

    private MantenedoresService mantenedoresService;
    private FiltrosService filtrosService;
    private InformesService informesService;

    public PlanCuentasController(MantenedoresService mantenedoresService, FiltrosService filtrosService, InformesService informesService) {
        this.mantenedoresService = mantenedoresService;
        this.filtrosService = filtrosService;
        this.informesService = informesService;
    }

    @GetMapping(value = "/verBitacoraPlanCuenta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CambiosBitacora>> verBitacoraPlanCuenta(@RequestParam String idTabla1,
                                                                       @RequestParam String idTabla2,
                                                                       HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String idEjercicio = (String) session.getAttribute("idEjercicio");
        BitacoraCtaContableBO bitacora = mantenedoresService.getRegistroBitacora(idEjercicio, idTabla1, idTabla2);
        List<CambiosBitacora> cambiosBitacoraVO = new ArrayList<CambiosBitacora>();

        if (bitacora.getFchaCreacion() != null){
            CambiosBitacora cambioBitacora = new CambiosBitacora();
            cambioBitacora.setEstado("Creado");
            cambioBitacora.setFecha(bitacora.getFchaCreacion());
            cambioBitacora.setUsuario(bitacora.getUserCreacion());
            cambiosBitacoraVO.add(cambioBitacora);
        }
        if (bitacora.getFchaEdicion() != null){
            CambiosBitacora cambioBitacora = new CambiosBitacora();
            cambioBitacora.setEstado("Editado");
            cambioBitacora.setFecha(bitacora.getFchaEdicion());
            cambioBitacora.setUsuario(bitacora.getUserEdicion());
            cambiosBitacoraVO.add(cambioBitacora);
        }
        if (bitacora.getFchaDesactivacion() != null){
            CambiosBitacora cambioBitacora = new CambiosBitacora();
            cambioBitacora.setEstado("Desactivado");
            cambioBitacora.setFecha(bitacora.getFchaDesactivacion());
            cambioBitacora.setUsuario(bitacora.getUserEdicion());
            cambiosBitacoraVO.add(cambioBitacora);
        }

        return new ResponseEntity<List<CambiosBitacora>>(cambiosBitacoraVO, HttpStatus.OK);
    }

    @GetMapping(value = "/verMantenedorTblPLanCuenta")
    public String verMantenedorTblPLanCuenta(Model model) {
        List<EjerciciosDTO> ejercicios = filtrosService.getEjercicios();
        model.addAttribute("listaEjercicios", ejercicios);
        return "administracion/mantenedores/TblPlanCuenta3";
    }

    @PostMapping(value = "/editarCuentaGastos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonJTableExpenseBean> editarCuentaGastos(@RequestParam String pnCtasGastosSelected,
                                                                    HttpServletRequest request) {

        HttpSession session = request.getSession();
        PlanCuentasContables cuentaContableDetalle = new PlanCuentasContables();
        try {
            cuentaContableDetalle = (PlanCuentasContables) session.getAttribute("cuentaPadre");
        } catch (Exception e) {
        }

        //TODO: duda con el retorno
        String usuario = getUserName(request);
        JsonJTableExpenseBean jtb = new JsonJTableExpenseBean();
        if (!usuario.equalsIgnoreCase("")) {
            if (cuentaContableDetalle != null) {
                jtb = mantenedoresService.actualizarAsociacionCtasGastos(cuentaContableDetalle.getIdTabla1(),
                        cuentaContableDetalle.getIdTabla2(), pnCtasGastosSelected, usuario);
                return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.OK);
            }
            return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/editarCuentaIngresos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonJTableExpenseBean> editarCuentaIngresos(@RequestParam String pnCtasIngresosSelected,
                                                                      HttpServletRequest request) {
        HttpSession session = request.getSession();
        PlanCuentasContables cuentaContableDetalle = new PlanCuentasContables();
        try {
            cuentaContableDetalle = (PlanCuentasContables) session.getAttribute("cuentaPadre");
        } catch (Exception e) {
        }

        //TODO: duda con el retorno
        String usuario = getUserName(request);
        JsonJTableExpenseBean jtb = new JsonJTableExpenseBean();
        if (!usuario.equalsIgnoreCase("")) {
            if (cuentaContableDetalle != null) {
                jtb = mantenedoresService.actualizarAsociacionCtasIngresos(cuentaContableDetalle.getIdTabla1(),
                        cuentaContableDetalle.getIdTabla2(), pnCtasIngresosSelected, usuario);
                return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.OK);
            }
            return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/editarCuentaContable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonJTableExpenseBean> editarCuentaContable(@RequestParam String editDescripcion,
                                                                      @RequestParam(required = false) String pnNaturaleza,
                                                                      @RequestParam(required = false) String pnAux,
                                                                      @RequestParam String editImputacion,
                                                                      @RequestParam(required = false) String pnClasificacion,
                                                                      @RequestParam(required = false) String pntipoSaldoAcreedor,
                                                                      @RequestParam(required = false) String pntipoSaldoDeudor,
                                                                      HttpServletRequest request) {
        HttpSession session = request.getSession();
        PlanCuentasContables cuentaContableDetalle = new PlanCuentasContables();
        try {
            cuentaContableDetalle = (PlanCuentasContables) session.getAttribute("cuentaPadre");
        } catch (Exception e) {
        }

        cuentaContableDetalle.setDescripcion(editDescripcion);
        cuentaContableDetalle.setCartera(pnNaturaleza);
        cuentaContableDetalle.setUsoAuxiliar(pnAux);
        cuentaContableDetalle.setImputacionPresup(editImputacion);
        cuentaContableDetalle.setClasificacion(pnClasificacion);

        cuentaContableDetalle.setTipoSaldo(getTipoSaldoFromCheckBox(pntipoSaldoAcreedor, pntipoSaldoDeudor));

        String idEjercicio = (String) session.getAttribute("idEjercicio");
        //TODO: duda con el retorno
        String usuario = getUserName(request);
        JsonJTableExpenseBean jtb = new JsonJTableExpenseBean();
        if (!usuario.equalsIgnoreCase("")) {
            if (cuentaContableDetalle != null) {
                jtb = mantenedoresService.editarCuentaContable(cuentaContableDetalle, usuario, idEjercicio);
                return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.OK);
            }
            return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/postPeriodosHabilitado", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonJTableExpenseBean> postPeriodosHabilitado(@RequestParam String periodos,
                                                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        PlanCuentasContables cuentaContableDetalle = new PlanCuentasContables();
        try {
            cuentaContableDetalle = (PlanCuentasContables) session.getAttribute("cuentaPadre");
        } catch (Exception e) {
        }
        //TODO: duda con el retorno
        JsonJTableExpenseBean jtb = new JsonJTableExpenseBean();
        String usuario = getUserName(request);
        if (!usuario.equalsIgnoreCase("")) {
            if (cuentaContableDetalle != null) {
                jtb = mantenedoresService.actualizarPeriodosHabilitados(cuentaContableDetalle.getIdTabla1(), cuentaContableDetalle.getIdTabla2(), periodos, usuario);
                return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.OK);
            }
            return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonJTableExpenseBean>(jtb, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/postAgregacion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaPair> postAgregacion(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RespuestaPair respuestaPair = new RespuestaPair(-1, "", "");
        try {
            String idEjercicio = (String) session.getAttribute("idEjercicio");
            if (idEjercicio != null) {
                PlanCuentaTbl registro = new PlanCuentaTbl();

                registro.setAgrDesc(request.getParameter("agrDesc"));
                registro.setGrupo(request.getParameter("agrGru"));
                registro.setSubGrupo(request.getParameter("agrSub"));
                registro.setTitulo(request.getParameter("agrTit"));
                registro.setCartera(request.getParameter("pnNaturaleza"));
                registro.setUsoAuxiliar(request.getParameter("pnAux"));
                registro.setImputacionPresup(request.getParameter("pnImp"));
                registro.setClasificacion(request.getParameter("pnClasificacion"));
                registro.setTipoSaldo(getTipoSaldoFromCheckBox(request.getParameter("pntipoSaldoAcreedor"), request.getParameter("pntipoSaldoDeudor")));
                registro.setIdEjercicio(idEjercicio);

                String usuario = getUserName(request);
                if (!usuario.equalsIgnoreCase("")) {
                    registro.setUserCreator(usuario);
                }
                respuestaPair = mantenedoresService.postAgregacion(registro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<RespuestaPair>(respuestaPair, HttpStatus.OK);
    }

    @PostMapping(value = "/postDesactivarCuentasContables2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> postDesactivarCuentasContables(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PlanCuentasContables cuentaContableDetalle = (PlanCuentasContables) session.getAttribute("cuentaPadre");
        String idEjercicio = (String) session.getAttribute("idEjercicio");

        JsonJTableExpenseBean resp = new JsonJTableExpenseBean();
        if (cuentaContableDetalle != null && idEjercicio != null) {
            String usuario = getUserName(request);

            String pAgr = cuentaContableDetalle.getAgregacion();
            String pN1 = cuentaContableDetalle.getCodPrimerNivel();
            String pN2 = cuentaContableDetalle.getCodSegundoNivel();
            String pN3 = cuentaContableDetalle.getCodTercerNivel();

            resp = mantenedoresService.postDesactivarCuentasContables(usuario, pAgr, pN1, pN2, pN3, idEjercicio);
        }
        return new ResponseEntity<Integer>(resp.getId(), HttpStatus.OK);
    }

    @GetMapping(value = "/getCtaContableByDesactivar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CtasContablesSimples>> getCtaContableByDesactivar(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        PlanCuentasContables cuentaContableDetalle = (PlanCuentasContables) session.getAttribute("cuentaPadre");

        List<CtasContablesSimples> cuentasContSimples = new ArrayList<CtasContablesSimples>();
        if (cuentaContableDetalle != null) {
            String idEjercicio = (String) session.getAttribute("idEjercicio");

            cuentasContSimples = mantenedoresService.getCtaContableByDesactivar(cuentaContableDetalle.getAgregacion(),
                    cuentaContableDetalle.getCodPrimerNivel(),
                    cuentaContableDetalle.getCodSegundoNivel(),
                    cuentaContableDetalle.getCodTercerNivel(), idEjercicio);
        }
        return new ResponseEntity<List<CtasContablesSimples>>(cuentasContSimples, HttpStatus.OK);
    }

    @PostMapping(value = "/getCatalogoPresup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCatalogoPresup(@RequestParam Integer idPP,
                                                    @RequestParam String idEjercicio) {

        //TODO: metodo no encontrado en codigo antiguo
        JsonJTableExpenseBean resp = mantenedoresService.getCodigoPartidaByIdCap(idPP, idEjercicio);
        return new ResponseEntity<String>(resp.getCodPartida(), HttpStatus.OK);
    }

    @GetMapping(value = "/getTitulosPlanCuenta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlanCuentaTitulo>> getTitulosPlanCuenta(@RequestParam String ejercicio,
                                                                       HttpServletRequest request) {


        HttpSession session = request.getSession(false);
        if (ejercicio != null) {
            session.setAttribute("idEjercicio", ejercicio);
        }

        List<PlanCuentaTitulo> pct = mantenedoresService.getTitulosPlanCuenta(ejercicio);
        return new ResponseEntity<List<PlanCuentaTitulo>>(pct, HttpStatus.OK);
    }

    @GetMapping(value = "/getGruposPlanCuenta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlanCuentaGrupo>> getGruposPlanCuenta(@RequestParam String ejercicio,
                                                                     @RequestParam String idTitulo) {
        List<PlanCuentaGrupo> resp = mantenedoresService.getGruposPlanCuenta(ejercicio, idTitulo);
        return new ResponseEntity<List<PlanCuentaGrupo>>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/getSubGruposPlanCuenta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlanCuentaSubGrupo>> getSubGruposPlanCuenta(@RequestParam String ejercicio,
                                                                           @RequestParam String idTitulo,
                                                                           @RequestParam String idGrupo) {
        List<PlanCuentaSubGrupo> resp = mantenedoresService.getSubGruposPlanCuenta(ejercicio, idTitulo, idGrupo);
        return new ResponseEntity<List<PlanCuentaSubGrupo>>(resp, HttpStatus.OK);
    }

    @PostMapping(value = "/getPlanCuentasContable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableCC> getPlanCuentasContable(@RequestParam Integer jtStartIndex,
                                                                     @RequestParam Integer jtPageSize,
                                                                     @RequestParam String ejercicio,
                                                                     @RequestParam String idTitulo,
                                                                     @RequestParam String idGrupo,
                                                                     @RequestParam String idSubGrupo,
                                                                     @RequestParam(required = false) String isActivo) {
        int jtEndIndex = jtStartIndex + jtPageSize;
        List<PlanCuentasContables> records = mantenedoresService.getPlanCuentasContable(ejercicio, idTitulo, idGrupo, idSubGrupo, isActivo);
        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }
        JsonResultJTableCC result = new JsonResultJTableCC("OK", records.subList(jtStartIndex, jtEndIndex), records.size());
        return new ResponseEntity<JsonResultJTableCC>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getCtaContable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DatosCuentaContable> getCtaContable(@RequestParam String idTabla1,
                                                 @RequestParam String idTabla2,
                                                 HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String idEjercicio = (String) session.getAttribute("idEjercicio");
        DatosCuentaContable datosCuentaContable = new DatosCuentaContable();

        if (idTabla1 != null && idTabla2 != null) {
            PlanCuentasContables pcc = mantenedoresService.getCtaContable(idEjercicio, idTabla1, idTabla2);
            datosCuentaContable.setCuentaContableDetalle(pcc);
            session.setAttribute("cuentaPadre", pcc);
            if (idEjercicio != null) {
                datosCuentaContable.setCtasPresupuestariasVO(mantenedoresService.catalogoPresupuestario(idEjercicio));
                datosCuentaContable.setListPeriodos(informesService.getPeriodos(Integer.parseInt(idEjercicio)));
            }
        }
        return new ResponseEntity<DatosCuentaContable>(datosCuentaContable, HttpStatus.OK);
    }

    @PostMapping(value = "/crearNuevoNivel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaPair> crearNuevoNivel(@RequestParam String descripcion,
                                                               @RequestParam String pnNaturaleza,
                                                               @RequestParam String pnAux,
                                                               @RequestParam String pnImp,
                                                               @RequestParam String pntipoSaldoAcreedor,
                                                               @RequestParam String pntipoSaldoDeudor,
                                                               @RequestParam(required = false) String nvlPrimero,
                                                               @RequestParam(required = false) String nvlSegundo,
                                                               @RequestParam(required = false) String nvlTercero,
                                                               HttpServletRequest request) {
        String usuario = getUserName(request);
        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("usr");

        PlanCuentasContables ctaNueva = new PlanCuentasContables();

        PlanCuentasContables cuentaContableDetalle = (PlanCuentasContables) session.getAttribute("cuentaPadre");

        String ctaAgrega = cuentaContableDetalle.getAgregacion();
        String tipoSaldo = getTipoSaldoFromCheckBox(pntipoSaldoAcreedor, pntipoSaldoDeudor);

        ctaNueva.setAgregacion(ctaAgrega);
        ctaNueva.setTipoSaldo(tipoSaldo);
        ctaNueva.setDescripcion(descripcion);
        ctaNueva.setCartera(pnNaturaleza);
        ctaNueva.setUsoAuxiliar(pnAux);
        ctaNueva.setImputacionPresup(pnImp);

        if ("00".equals(cuentaContableDetalle.getCodTercerNivel()) &&
                "00".equals(cuentaContableDetalle.getCodSegundoNivel()) &&
                "00".equals(cuentaContableDetalle.getCodPrimerNivel())) {

            ctaNueva.setCodPrimerNivel(request.getParameter("nvlPrimero"));
            ctaNueva.setCodSegundoNivel("00");
            ctaNueva.setCodTercerNivel("00");
        }

        if ("00".equals(cuentaContableDetalle.getCodTercerNivel()) &&
                "00".equals(cuentaContableDetalle.getCodSegundoNivel()) &&
                !"00".equals(cuentaContableDetalle.getCodPrimerNivel())) {

            ctaNueva.setCodPrimerNivel(cuentaContableDetalle.getCodPrimerNivel());
            ctaNueva.setCodSegundoNivel(request.getParameter("nvlSegundo"));
            ctaNueva.setCodTercerNivel("00");
        }

        if ("00".equals(cuentaContableDetalle.getCodTercerNivel()) &&
                !"00".equals(cuentaContableDetalle.getCodSegundoNivel()) &&
                !"00".equals(cuentaContableDetalle.getCodPrimerNivel())) {

            ctaNueva.setCodPrimerNivel(cuentaContableDetalle.getCodPrimerNivel());
            ctaNueva.setCodSegundoNivel(cuentaContableDetalle.getCodSegundoNivel());
            ctaNueva.setCodTercerNivel(request.getParameter("nvlTercero"));
        }
        String idEjercicio = (String) session.getAttribute("idEjercicio");

        String aux = cuentaContableDetalle.getIdTabla1() + cuentaContableDetalle.getIdTabla2();
        int fatherId = Integer.valueOf(aux.replace(" ", ""));

        RespuestaPair respuestaPair = mantenedoresService.crearCuentaContable(ctaNueva, usuario, idEjercicio, fatherId);
        return new ResponseEntity<RespuestaPair>(respuestaPair, HttpStatus.OK);
    }

    private String getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String usuario = "";
        try {
            UsuarioDTO usuarioSesion = (UsuarioDTO) session.getAttribute("usr");
            usuario = usuarioSesion.getUserLogin();
        } catch (Exception e) { }
        return usuario;
    }

    private String getTipoSaldoFromCheckBox(String pntipoSaldoAcreedor, String pntipoSaldoDeudor) {
        if (pntipoSaldoAcreedor != null && pntipoSaldoDeudor != null)
            return "Acreedor, Deudor";
        if (pntipoSaldoAcreedor == null && pntipoSaldoDeudor != null)
            return "Deudor";
        if (pntipoSaldoAcreedor != null && pntipoSaldoDeudor == null)
            return "Acreedor";
        return "";
    }
}
