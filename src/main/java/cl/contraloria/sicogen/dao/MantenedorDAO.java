package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.EjercicioMantenedorMapper;
import cl.contraloria.sicogen.mappers.InformesMantenedorMapper;
import cl.contraloria.sicogen.mappers.SubTipoInformeMapper;
import cl.contraloria.sicogen.mappers.SubTiposMantenedorMapper;
import cl.contraloria.sicogen.mappers.RegionMantenedorMapper;
import cl.contraloria.sicogen.mappers.EtapasCompromisoMantenedorMapper;
import cl.contraloria.sicogen.mappers.AreaTransaccMantenedorMapper;
import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import cl.contraloria.sicogen.model.OptionsJtable;
import cl.contraloria.sicogen.model.SubTipoInforme;
import cl.contraloria.sicogen.mappers.*;
import cl.contraloria.sicogen.model.*;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MantenedorDAO extends BaseDAO {

    private static final String PKG_ENTIDAD = "PKG_ENTIDAD";
    private static final String PKG_MANTENEDORES = "PKG_MANTENEDORES";
    private static final String PKG_USUARIOS = "PKG_USUARIOS";
    private static final String PKG_INFORMES = "PKG_INFORMES";
    private static final String PKG_PLAN_DE_CUENTAS= "PKG_PLAN_DE_CUENTAS";
    private static final String PKG_SALDOS_CERO="PKG_SALDOS_CERO";
    private static final String P_TIPO = "pTIPO";
    private static final String P_SUBTIPOS = "pSUBTIPOS";
    private static final String P_SUBTIPO = "pSUBTIPO";
    private static final String P_INFORMES = "pINFORMES";
    private static final String P_TIPOS_CURSOR = "pTIPOS_CURSOR";
    private static final String P_SUBTIPO_INF = "pSUBTIPOINF";
    private static final String P_CODIGO = "pCODIGO";
    private static final String P_NOMBRE = "pNOMBRE";
    private static final String P_DESCRIPCION = "pDESCRIPCION";
    private static final String P_USUARIO = "pUSUARIO";
    private static final String P_INF = "pINF";
    private static final String P_MESSAGE = "pMESSAGE";
    private static final String INFORME_ID = "INFORME_ID";
    private static final String P_TIPO_INF = "pTIPOINF";
    private static final String P_ESTADO = "pESTADO";
    private static final String P_PROYECTO = "pPROYECTO";
    private static final String PKG_CUENTAS = "PKG_CUENTAS";
    private static final String P_EJR_ID = "p_ejr_id";
    private static final String P_ENT_PART_ID = "p_ent_part_id";
    private static final String P_ENT_CAP_ID = "p_ent_cap_id";
    private static final String P_ENT_PROG_ID = "p_ent_prog_id";
    private static final String CURSOR_CUENTAS_PARTICULARES = "cursor_cuentas_particulares";
    private static final String P_CUEN_ENT_SUBTITULO = "p_cuen_ent_subtitulo";
    private static final String P_CUEN_ENT_ITEM = "p_cuen_ent_item";
    private static final String P_CUEN_ENT_ASIGNACION = "p_cuen_ent_asignacion";
    private static final String P_CUEN_ENT_SUBASIGNACION = "p_cuen_ent_subasignacion";
    private static final String P_CUEN_ENT_NOMBRE = "p_cuen_ent_nombre";
    private static final String P_USUARIO2 = "p_usuario";
    private static final String P_IMPUTACION = "p_imputacion";
    private static final String P_IS_DECRETO = "p_is_decreto";
    private static final String P_NUM_DOCUMENTO = "p_num_documento";
    private static final String P_ANIO_DOCUMENTO = "p_anio_documento";
    private static final String P_COD = "p_cod";
    private static final String P_MSG = "p_msg";
    private static final String P_CUEN_ENT_ID = "p_cuen_ent_id";
    private static final String P_CUEN_ENT_DATE_CREATE = "p_cuen_ent_date_create";
    private static final String P_CUEN_ENT_USER_CREATE = "p_cuen_ent_user_create";
    private static final String P_CUEN_ENT_DATE_UPDATE = "p_cuen_ent_date_update";
    private static final String P_CUEN_ENT_USER_UPDATE = "p_cuen_ent_user_update";
    private static final String P_CUEN_ENT_ISVALID = "p_cuen_ent_isvalid";

    private static final String P_EJERCICIO = "pEJERCICIO";
    private static final String P_SECTOR = "pSECTOR";
    private static final String P_INSTITUCION = "pINSTITUCION";
    private static final String P_AT_CURSOR = "pAT_CURSOR";
    private static final String P_RUT = "pRUT";
    private static final String p_DV = "pDV";

    private static final String PKG_FILTRO = "PKG_FILTRO";

    private JdbcTemplate jdbcTemplate;

    public MantenedorDAO(DataSource source) {
        this.jdbcTemplate = new JdbcTemplate(source);
    }

    //** Crear Cuenta Contable
    public RespuestaPair crearCuentaContable(PlanCuentasContables ctaNueva, String user, String idEjercicio, Integer idFather) {

        if ("".equals(ctaNueva.getDescripcion().trim())) {
            return new RespuestaPair(-2, "Descripción cuenta no puede ser vacío", "aux");
        }

        SimpleJdbcCall llamada = crearCuentaContableCall();
        Map<String, Object> parametros = crearCuentaContableParams(ctaNueva, user, idEjercicio, idFather);
        Map<String, Object> result = llamada.execute(parametros);
        Integer codResp = (Integer) result.get("pCOD");

        switch (codResp) {
            case -1:
                return new RespuestaPair(codResp, "No se ha podido crear el registro", "as");
            case -2:
                return new RespuestaPair(codResp, "La cuenta ya existe para el ejercicio seleccionado", "as");
            default:
                return new RespuestaPair(codResp, "Cuenta creada exitosamente", "as");
        }
    }

    private Map<String, Object> crearCuentaContableParams(PlanCuentasContables ctaNueva, String user, String idEjercicio, Integer idFather) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEjercicio", idEjercicio);
        parametros.put("pAgr", ctaNueva.getAgregacion());
        parametros.put("pN1", ctaNueva.getCodPrimerNivel());
        parametros.put("pN2", ctaNueva.getCodSegundoNivel());
        parametros.put("pN3", ctaNueva.getCodTercerNivel());
        parametros.put("pDesc", ctaNueva.getDescripcion());
        parametros.put("pTipoSaldo", ctaNueva.getTipoSaldo());
        parametros.put("pNatCartera", ctaNueva.getCartera());
        parametros.put("pUsoAux", ctaNueva.getUsoAuxiliar());
        parametros.put("pClasificacion", ctaNueva.getClasificacion());
        parametros.put("pImputacion", ctaNueva.getImputacionPresup());
        parametros.put("pUserConected", user);
        parametros.put("pIdFATHER", idFather);
        return parametros;
    }

    private SimpleJdbcCall crearCuentaContableCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("CREATE_CUENTAS_GRAL2").declareParameters(
                        new SqlParameter("pEjericioId", OracleTypes.VARCHAR),
                        new SqlParameter("pAgr", OracleTypes.VARCHAR),
                        new SqlParameter("pN1", OracleTypes.VARCHAR),
                        new SqlParameter("pN2", OracleTypes.VARCHAR),
                        new SqlParameter("pN3", OracleTypes.VARCHAR),
                        new SqlParameter("pDesc", OracleTypes.VARCHAR),
                        new SqlParameter("pTipoSaldo", OracleTypes.VARCHAR),
                        new SqlParameter("pNatCartera", OracleTypes.VARCHAR),
                        new SqlParameter("pUsoAux", OracleTypes.VARCHAR),
                        new SqlParameter("pClasificacion", OracleTypes.VARCHAR),
                        new SqlParameter("pImputacion", OracleTypes.VARCHAR),
                        new SqlParameter("pUserConected", OracleTypes.VARCHAR),
                        new SqlParameter("pIdFATHER", OracleTypes.INTEGER),
                        new SqlOutParameter("pCOD", OracleTypes.INTEGER)
                );
    }

    //** Get Catalogo Presupuestario
    public CuentasPresupuestarias catalogoPresupuestario(String idEjercicio) {
        SimpleJdbcCall llamada = catalogoPresupuestarioCall();
        Map<String, Object> parametros = catalogoPresupuestarioParams(idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        CuentasPresupuestarias cuentasPres = new CuentasPresupuestarias();
        cuentasPres.setListCtasPresupDeGastos((List<CatalogoPresupuestario>) result.get("CURSOR_CUENTAS_PRESUP_GASTOS"));
        cuentasPres.setListCtasPresupDeIngreso((List<CatalogoPresupuestario>) result.get("CURSOR_CUENTAS_PRESUP_INGRESO"));
        return cuentasPres;
    }

    private Map<String, Object> catalogoPresupuestarioParams(String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEjericioId", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall catalogoPresupuestarioCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("GET_CUENTAS_PRESUP").declareParameters(
                        new SqlParameter("pEjericioId", OracleTypes.NUMERIC),
                        new SqlOutParameter("CURSOR_CUENTAS_PRESUP_INGRESO", OracleTypes.CURSOR, new CatalogoPresupuestarioMapper()),
                        new SqlOutParameter("CURSOR_CUENTAS_PRESUP_GASTOS", OracleTypes.CURSOR, new CatalogoPresupuestarioMapper())
                );
    }

    //** Get Cuenta Contable
    public PlanCuentasContables getCtaContable(String idEjercicio, String idTabla1, String idTabla2) {
        SimpleJdbcCall llamada = getCtaContableCall();
        Map<String, Object> parametros = getCtaContableParams(idEjercicio, idTabla1, idTabla2);
        Map<String, Object> result = llamada.execute(parametros);
        List<PlanCuentasContables> pct = (List<PlanCuentasContables>) result.get("CURSOR_CTAS_CONT");
        if (pct != null)
            return pct.get(0);
        return null;
    }

    private Map<String, Object> getCtaContableParams(String idEjercicio, String idTabla1, String idTabla2) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEjercicio", idEjercicio);
        parametros.put("idTabla1", idTabla1);
        parametros.put("idTabla2", idTabla2);
        return parametros;
    }

    private SimpleJdbcCall getCtaContableCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("GET_CTA_CONTABLE_MANT")
                .declareParameters(
                        new SqlParameter("pEjercicio", OracleTypes.NUMERIC),
                        new SqlParameter("idTabla1", OracleTypes.VARCHAR),
                        new SqlParameter("idTabla2", OracleTypes.VARCHAR),
                        new SqlOutParameter("CURSOR_CTAS_CONT", OracleTypes.CURSOR, new PlanCuentasContableMapper())
                );
    }

    //** Get Plan Cuentas Contables
    public List<PlanCuentasContables> getPlanCuentasContable(String idEjercicio, String idTitulo, String idGrupo, String idSubGrupo, String isHabilitado) {
        if(idSubGrupo == null){
            idSubGrupo = "0";
        }
        if(idGrupo == null){
            idGrupo = "0";
        }
        if(idTitulo == null){
            idTitulo = "0";
        }
        if (isHabilitado == null){
            isHabilitado = "1";
        }
        SimpleJdbcCall llamada = getPlanCuentasContableCall();
        Map<String, Object> parametros = getPlanCuentasContableParams(idEjercicio, idTitulo, idGrupo, idSubGrupo, isHabilitado);
        Map<String, Object> result = llamada.execute(parametros);
        List<PlanCuentasContables> pct = (List<PlanCuentasContables>) result.get("CURSOR_PLAN_CTAS_CONT");
        return pct;
    }

    private Map<String, Object> getPlanCuentasContableParams(String idEjercicio, String idTitulo, String idGrupo, String idSubGrupo, String isHabilitado) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEjercicio", idEjercicio);
        parametros.put("pTitulo", idTitulo);
        parametros.put("pGrupo", idGrupo);
        parametros.put("pSubGrupo", idSubGrupo);
        parametros.put("pHabilitado", isHabilitado);
        return parametros;
    }

    private SimpleJdbcCall getPlanCuentasContableCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("GET_PLAN_CUENTAS_CONTABLE").declareParameters(
                        new SqlParameter("pEjercicio", OracleTypes.NUMERIC),
                        new SqlParameter("pTitulo", OracleTypes.NUMERIC),
                        new SqlParameter("pGrupo", OracleTypes.NUMERIC),
                        new SqlParameter("pSubGrupo", OracleTypes.NUMERIC),
                        new SqlParameter("pHabilitado", OracleTypes.NUMERIC),
                        new SqlOutParameter("CURSOR_PLAN_CTAS_CONT", OracleTypes.CURSOR, new PlanCuentasContableMapper())
                );
    }

    //** Get Sub Grupos Plan Cuentas
    public List<PlanCuentaSubGrupo> getSubGruposPlanCuenta(String idEjercicio, String idTitulo, String idGrupo) {
        SimpleJdbcCall llamada = getSubGruposPlanCuentaCall();
        Map<String, Object> parametros = getSubGruposPlanCuentaParams(idEjercicio, idTitulo, idGrupo);
        Map<String, Object> result = llamada.execute(parametros);
        List<PlanCuentaSubGrupo> pct = (List<PlanCuentaSubGrupo>) result.get("CURSOR_SUBGRUPOS");
        return pct;
    }

    private Map<String, Object> getSubGruposPlanCuentaParams(String idEjercicio, String idTitulo, String idGrupo) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pTitulo", idTitulo);
        parametros.put("pEJERICIO", idEjercicio);
        parametros.put("pGRUPO", idGrupo);
        return parametros;
    }

    private SimpleJdbcCall getSubGruposPlanCuentaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_FILTRO)
                .withProcedureName("GET_SUBGRUPOS_BY_GRUPO").declareParameters(
                        new SqlParameter("pGRUPO", OracleTypes.NUMERIC),
                        new SqlParameter("pTitulo", OracleTypes.NUMERIC),
                        new SqlParameter("pEJERICIO", OracleTypes.NUMERIC),
                        new SqlOutParameter("CURSOR_SUBGRUPOS", OracleTypes.CURSOR, new PlanCuentaSubGrupoMapper())
                );
    }

    //** Get Grupos Plan Cuentas
    public List<PlanCuentaGrupo> getGruposPlanCuenta(String idEjercicio, String idTitulo) {
        SimpleJdbcCall llamada = getGruposPlanCuentaCall();
        Map<String, Object> parametros = getGruposPlanCuentaParams(idEjercicio, idTitulo);
        Map<String, Object> result = llamada.execute(parametros);
        List<PlanCuentaGrupo> pct = (List<PlanCuentaGrupo>) result.get("CURSOR_GRUPOS");
        return pct;
    }

    private Map<String, Object> getGruposPlanCuentaParams(String idEjercicio, String idTitulo) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pTITULO", idTitulo);
        parametros.put("pEJERICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getGruposPlanCuentaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_FILTRO)
                .withProcedureName("GET_GRUPOS_BY_TITULO").declareParameters(
                        new SqlParameter("pTITULO", OracleTypes.NUMERIC),
                        new SqlParameter("pEJERICIO", OracleTypes.NUMERIC),
                        new SqlOutParameter("CURSOR_GRUPOS", OracleTypes.CURSOR, new PlanCuentaGruposMapper())
                );
    }

    //** Get Titulos Plan Cuentas
    public List<PlanCuentaTitulo> getTitulosPlanCuenta(String idEjercicio) {
        SimpleJdbcCall llamada = getTitulosPlanCuentaCall();
        Map<String, Object> parametros = getTitulosPlanCuentaParams(idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);

        List<PlanCuentaTitulo> pct = (List<PlanCuentaTitulo>) result.get("CURSOR_TITULOS");
        return pct;
    }

    private Map<String, Object> getTitulosPlanCuentaParams(String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJERICIO", idEjercicio);

        return parametros;
    }

    private SimpleJdbcCall getTitulosPlanCuentaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_FILTRO)
                .withProcedureName("GET_TITULOS_BY_EJERICIO").declareParameters(
                        new SqlParameter("pEJERICIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("CURSOR_TITULOS", OracleTypes.CURSOR, new PlanCuentaTituloMapper())
                );
    }

    //** Get Cuentas Contables By Desactivar
    public List<CtasContablesSimples> getCtaContableByDesactivar(String pAgr, String pN1, String pN2, String pN3, String idEjercicio) {
        SimpleJdbcCall llamada = getCtaContableByDesactivarCall();
        Map<String, Object> parametros = getCtaContableByDesactivarParams(pAgr, pN1, pN2, pN3, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);

        List<CtasContablesSimples> cuentasContSimples = (List<CtasContablesSimples>) result.get("pCursorDependientes");
        return cuentasContSimples;
    }

    private Map<String, Object> getCtaContableByDesactivarParams(String pAgr, String pN1, String pN2, String pN3, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEjericioId", idEjercicio);
        parametros.put("pAgr", pAgr);
        parametros.put("pN1", pN1);
        parametros.put("pN2", pN2);
        parametros.put("pN3", pN3);
        return parametros;
    }

    private SimpleJdbcCall getCtaContableByDesactivarCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("LIST_CTAS_DEPENDIENTES").declareParameters(
                        new SqlParameter("pEjericioId", OracleTypes.VARCHAR),
                        new SqlParameter("pAgr", OracleTypes.VARCHAR),
                        new SqlParameter("pN1", OracleTypes.VARCHAR),
                        new SqlParameter("pN2", OracleTypes.VARCHAR),
                        new SqlParameter("pN3", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCursorDependientes", OracleTypes.CURSOR, new CtasContSimplesMapper()),
                        new SqlOutParameter("pCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("result_msg", OracleTypes.VARCHAR)
                );
    }

    //** Actualizar Asociacion Ctas Contables
    public JsonJTableExpenseBean postDesactivarCuentasContables(String userConected, String pAgr, String pN1,
                                                                String pN2, String pN3, String idEjercicio) {
        SimpleJdbcCall llamada = postDesactivarCuentasContablesCall();
        Map<String, Object> parametros = postDesactivarCuentasContablesParams(userConected, pAgr, pN1, pN2, pN3, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("result_msg")));
        jteb.setId((Integer) result.get("pCOD"));
        return jteb;
    }

    private Map<String, Object> postDesactivarCuentasContablesParams(String userConected, String pAgr, String pN1,
                                                                     String pN2, String pN3, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEjericioId", idEjercicio);
        parametros.put("pAgr", pAgr);
        parametros.put("pN1", pN1);
        parametros.put("pN2", pN2);
        parametros.put("pN3", pN3);
        parametros.put("pUSUARIO", userConected);
        return parametros;
    }

    private SimpleJdbcCall postDesactivarCuentasContablesCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("DESACTIVAR_CTAS_CTBLES").declareParameters(
                        new SqlParameter("pEjericioId", OracleTypes.VARCHAR),
                        new SqlParameter("pAgr", OracleTypes.VARCHAR),
                        new SqlParameter("pN1", OracleTypes.VARCHAR),
                        new SqlParameter("pN2", OracleTypes.VARCHAR),
                        new SqlParameter("pN3", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("result_msg", OracleTypes.VARCHAR)
                );
    }

    //** Post Agregacion
    public RespuestaPair postAgregacion(PlanCuentaTbl registro) {

        if ("".equals(registro.getAgrDesc().trim())) {
            return new RespuestaPair(-1, "Descipción no puede ser vacía", "Aux");
        }

        String idEjericio = registro.getIdEjercicio();
        String codTitulo = registro.getTitulo();
        String codGrupo = registro.getGrupo();
        String codSubG = registro.getSubGrupo();

        RespuestaPair existencia = this.validarExistencia(idEjericio, codTitulo, codGrupo, codSubG);

        if (existencia.getCodido() < 0) { // -1 : Ya existe la cuenta en la BD, No puede duplicarse
            return existencia;
        }

        RespuestaPair existenciaPadre = this.validarExistenciaPadre(idEjericio, codTitulo, codGrupo, codSubG);

        if (!"OK".equals(existenciaPadre.getMensajeAux())) { // El Padre no Existe, No puede crear cuenta hijo sin padre
            return existenciaPadre;
        }

        RespuestaPair creacion = this.crearAgregacion(registro, existenciaPadre.getValAux()); //Se crea el registro ya que paso ambas validaciones
        return creacion;
    }

    //** Crear Agregacion
    private RespuestaPair crearAgregacion(PlanCuentaTbl registro, Integer idFather) {
        SimpleJdbcCall llamada = crearAgregacionCall();
        Map<String, Object> parametros = crearAgregacionParams(registro, idFather);
        Map<String, Object> result = llamada.execute(parametros);

        Integer codRespuesta = (Integer) result.get("pCOD");
        String msgResp = String.valueOf(result.get("pMSG"));

        RespuestaPair resp;

        if ("OK".equals(msgResp))
            resp = new RespuestaPair(codRespuesta, "Cuenta creada exitosamente", msgResp);
        else
            resp = new RespuestaPair(-1, "No se ha podido crear el registro", null);

        return resp;
    }

    private Map<String, Object> crearAgregacionParams(PlanCuentaTbl registro, Integer idFather) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEjercicio", registro.getIdEjercicio());
        parametros.put("pTitulo", registro.getTitulo());
        parametros.put("pGrupo", registro.getGrupo());
        parametros.put("pSubGrupo", registro.getSubGrupo());
        parametros.put("pDescripcion", registro.getAgrDesc());
        parametros.put("pUserConected", registro.getUserCreator());
        parametros.put("pUsoAuxiliar", registro.getUsoAuxiliar());
        parametros.put("pImputacionPresup", registro.getImputacionPresup());
        parametros.put("pClasificacion", registro.getClasificacion());
        parametros.put("pNaturalezacartera", registro.getCartera());
        parametros.put("pTipoSaldo", registro.getTipoSaldo());
        parametros.put("pIdFATHER", idFather);
        return parametros;
    }

    private SimpleJdbcCall crearAgregacionCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("CREATE_CUENTA_CONTABLE").declareParameters(
                        new SqlParameter("pEjercicio", OracleTypes.INTEGER),
                        new SqlParameter("pTitulo", OracleTypes.VARCHAR),
                        new SqlParameter("pGrupo", OracleTypes.VARCHAR),
                        new SqlParameter("pSubGrupo", OracleTypes.VARCHAR),
                        new SqlParameter("pDescripcion", OracleTypes.VARCHAR),
                        new SqlParameter("pUserConected", OracleTypes.VARCHAR),
                        new SqlParameter("pUsoAuxiliar", OracleTypes.VARCHAR),
                        new SqlParameter("pImputacionPresup", OracleTypes.VARCHAR),
                        new SqlParameter("pClasificacion", OracleTypes.VARCHAR),
                        new SqlParameter("pNaturalezacartera", OracleTypes.VARCHAR),
                        new SqlParameter("pTipoSaldo", OracleTypes.VARCHAR),
                        new SqlParameter("pIdFATHER", OracleTypes.INTEGER),
                        new SqlOutParameter("pCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("pMSG", OracleTypes.VARCHAR)
                );
    }

    //** Validar Existencia
    private RespuestaPair validarExistencia(String idEjericio, String codTitulo,
                                            String codGrupo, String codSubG) {
        SimpleJdbcCall llamada = validarExistenciaCall();
        Map<String, Object> parametros = validarExistenciaParams(idEjericio, codTitulo, codGrupo, codSubG);
        Map<String, Object> result = llamada.execute(parametros);

        Integer codRespuesta = (Integer) result.get("pCOD");
        String msgResp = String.valueOf(result.get("pMSG"));

        RespuestaPair resp;

        if (codRespuesta > 0)
            resp = new RespuestaPair(-1, "La cuenta ya existe en el Plan de Cuentas", null);
        else
            resp = new RespuestaPair(1, "La  cuenta no existe en el Plan de Cuentas", msgResp);

        return resp;
    }

    private Map<String, Object> validarExistenciaParams(String idEjericio,
                                                        String codTitulo, String codGrupo, String codSubG) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEjercicio", idEjericio);
        parametros.put("pTitulo", codTitulo);
        parametros.put("pGrupo", codGrupo);
        parametros.put("pSubGrupo", codSubG);
        return parametros;
    }

    private SimpleJdbcCall validarExistenciaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("VALIDAR_EXISTENCIA_CUENTA").declareParameters(
                        new SqlParameter("pEjercicio", OracleTypes.INTEGER),
                        new SqlParameter("pTitulo", OracleTypes.VARCHAR),
                        new SqlParameter("pGrupo", OracleTypes.VARCHAR),
                        new SqlParameter("pSubGrupo", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("pMSG", OracleTypes.VARCHAR)
                );
    }

    //** Validar Existencia Padre
    private RespuestaPair validarExistenciaPadre(String idEjericio,
                                                 String codTitulo, String codGrupo, String codSubG) {
        SimpleJdbcCall llamada = validarExistenciaPadreCall();
        Map<String, Object> parametros = validarExistenciaPadreParams(idEjericio, codTitulo, codGrupo, codSubG);
        Map<String, Object> result = llamada.execute(parametros);

        String codRespuesta = String.valueOf(result.get("pCOD"));
        String msgRespuesta = String.valueOf(result.get("pMSG"));
        Integer idFather = (Integer) result.get("idFather");

        RespuestaPair respuesta;

        if ("NOK".equals(codRespuesta)) {
            respuesta = new RespuestaPair(-1, msgRespuesta, codRespuesta);
        } else {
            respuesta = new RespuestaPair(1, msgRespuesta, codRespuesta);
            respuesta.setValAux(idFather);
        }

        return respuesta;
    }

    private Map<String, Object> validarExistenciaPadreParams(String idEjericio,
                                                             String codTitulo, String codGrupo, String codSubG) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEjercicio", idEjericio);
        parametros.put("pTitulo", codTitulo);
        parametros.put("pGrupo", codGrupo);
        parametros.put("pSubGrupo", codSubG);
        return parametros;
    }

    private SimpleJdbcCall validarExistenciaPadreCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("VALIDAR_EXISTENCIA_CTA_PADRE").declareParameters(
                        new SqlParameter("pEjercicio", OracleTypes.INTEGER),
                        new SqlParameter("pTitulo", OracleTypes.VARCHAR),
                        new SqlParameter("pGrupo", OracleTypes.VARCHAR),
                        new SqlParameter("pSubGrupo", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCOD", OracleTypes.VARCHAR),
                        new SqlOutParameter("pMSG", OracleTypes.VARCHAR),
                        new SqlOutParameter("idFather", OracleTypes.INTEGER)
                );
    }

    //** Actualizar Asociacion Ctas Contables
    public JsonJTableExpenseBean actualizarPeriodosHabilitados(String idTabla1, String idTabla2,
                                                               String periodosHabilitados, String userConected) {
        SimpleJdbcCall llamada = actualizarPeriodosHabilitadosCall();
        Map<String, Object> parametros = actualizarPeriodosHabilitadosParams(idTabla1, idTabla2, periodosHabilitados, userConected);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("result_msg")));
        jteb.setId((Integer) result.get("ppCOD"));
        return jteb;
    }

    private Map<String, Object> actualizarPeriodosHabilitadosParams(String idTabla1, String idTabla2,
                                                                    String periodosHabilitados, String userConected) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idTabla1", idTabla1);
        parametros.put("idTabla2", idTabla2);
        parametros.put("pPERIODOS_HABILITADOS", periodosHabilitados);
        parametros.put("pUserConected", userConected);
        return parametros;
    }

    private SimpleJdbcCall actualizarPeriodosHabilitadosCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("UPDATE_PERIODOS_HABILITADOS").declareParameters(
                        new SqlParameter("idTabla1", OracleTypes.VARCHAR),
                        new SqlParameter("idTabla2", OracleTypes.VARCHAR),
                        new SqlParameter("pPERIODOS_HABILITADOS", OracleTypes.VARCHAR),
                        new SqlParameter("pUserConected", OracleTypes.VARCHAR),
                        new SqlOutParameter("ppCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("result_msg", OracleTypes.VARCHAR)
                );
    }

    //** Actualizar Asociacion Ctas Contables
    public JsonJTableExpenseBean editarCuentaContable(PlanCuentasContables cuentaContableDetalle2, String userConected, String idEjercicio) {
        SimpleJdbcCall llamada = editarCuentaContableCall();
        Map<String, Object> parametros = editarCuentaContableParams(cuentaContableDetalle2, userConected, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("result_msg")));
        jteb.setId((Integer) result.get("ppCOD"));
        return jteb;
    }

    private Map<String, Object> editarCuentaContableParams(PlanCuentasContables cuentaContableDetalle2, String userConected, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEjericioId", idEjercicio);
        parametros.put("idTabla1", cuentaContableDetalle2.getIdTabla1());
        parametros.put("idTabla2", cuentaContableDetalle2.getIdTabla2());
        parametros.put("pCTA_DENOMINACION", cuentaContableDetalle2.getDescripcion());
        parametros.put("pP_TIPO_SALDO", cuentaContableDetalle2.getTipoSaldo());
        parametros.put("pP_NAT_CART", cuentaContableDetalle2.getCartera());
        parametros.put("pP_CLASIFICACION", cuentaContableDetalle2.getClasificacion());
        parametros.put("pP_USO_AUX", cuentaContableDetalle2.getUsoAuxiliar());
        parametros.put("pP_IMPUTACION_PRESUP", cuentaContableDetalle2.getImputacionPresup());
        parametros.put("pCTA_USER_UPDATE", userConected);
        return parametros;
    }

    private SimpleJdbcCall editarCuentaContableCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("UPDATE_CUENTAS_GRAL2").declareParameters(
                        new SqlParameter("pEjericioId", OracleTypes.VARCHAR),
                        new SqlParameter("idTabla1", OracleTypes.VARCHAR),
                        new SqlParameter("idTabla2", OracleTypes.VARCHAR),
                        new SqlParameter("pCTA_DENOMINACION", OracleTypes.VARCHAR),
                        new SqlParameter("pP_TIPO_SALDO", OracleTypes.VARCHAR),
                        new SqlParameter("pP_NAT_CART", OracleTypes.VARCHAR),
                        new SqlParameter("pP_CLASIFICACION", OracleTypes.VARCHAR),
                        new SqlParameter("pP_USO_AUX", OracleTypes.VARCHAR),
                        new SqlParameter("pP_IMPUTACION_PRESUP", OracleTypes.VARCHAR),
                        new SqlParameter("pCTA_USER_UPDATE", OracleTypes.VARCHAR),
                        new SqlOutParameter("ppCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("result_msg", OracleTypes.VARCHAR)
                );
    }

    //** Actualizar Asociacion Ctas Ingresos
    public JsonJTableExpenseBean actualizarAsociacionCtasIngresos(String idTabla1,
                                                                  String idTabla2, String pnCtasIngresosSelected, String userLogin) {
        SimpleJdbcCall llamada = actualizarAsociacionCtasIngresosCall();
        Map<String, Object> parametros = actualizarAsociacionCtasIngresosParams(idTabla1, idTabla2, pnCtasIngresosSelected, userLogin);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("result_msg")));
        jteb.setId((Integer) result.get("ppCOD"));
        return jteb;
    }

    private Map<String, Object> actualizarAsociacionCtasIngresosParams(String idTabla1, String idTabla2,
                                                                       String pnCtasIngresosSelected, String userLogin) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idTabla1", idTabla1);
        parametros.put("idTabla2", idTabla2);
        parametros.put("pASOC_PRESUP_INGRESOS", pnCtasIngresosSelected);
        parametros.put("pUserConected", userLogin);
        return parametros;
    }

    private SimpleJdbcCall actualizarAsociacionCtasIngresosCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("UPDATE_ASOCIACION_PRESUP_INGRE").declareParameters(
                        new SqlParameter("idTabla1", OracleTypes.VARCHAR),
                        new SqlParameter("idTabla2", OracleTypes.VARCHAR),
                        new SqlParameter("pASOC_PRESUP_INGRESOS", OracleTypes.VARCHAR),
                        new SqlParameter("pUserConected", OracleTypes.VARCHAR),
                        new SqlOutParameter("ppCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("result_msg", OracleTypes.VARCHAR)
                );
    }

    //** Actualizar Asociacion Ctas Gastos
    public JsonJTableExpenseBean actualizarAsociacionCtasGastos(String idTabla1, String idTabla2,
                                                                String pnCtasGastosSelected, String userLogin) {
        SimpleJdbcCall llamada = actualizarAsociacionCtasGastosCall();
        Map<String, Object> parametros = actualizarAsociacionCtasGastosParams(idTabla1, idTabla2, pnCtasGastosSelected, userLogin);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("result_msg")));
        jteb.setId((Integer) result.get("ppCOD"));
        return jteb;
    }

    private Map<String, Object> actualizarAsociacionCtasGastosParams(String idTabla1, String idTabla2,
                                                                     String pnCtasGastosSelected, String userLogin) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idTabla1", idTabla1);
        parametros.put("idTabla2", idTabla2);
        parametros.put("pASOC_PRESUP_GASTOS", pnCtasGastosSelected);
        parametros.put("pUserConected", userLogin);
        return parametros;
    }

    private SimpleJdbcCall actualizarAsociacionCtasGastosCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("UPDATE_ASOCIACION_PRESUPU_GAST").declareParameters(
                        new SqlParameter("idTabla1", OracleTypes.VARCHAR),
                        new SqlParameter("idTabla2", OracleTypes.VARCHAR),
                        new SqlParameter("pASOC_PRESUP_GASTOS", OracleTypes.VARCHAR),
                        new SqlParameter("pUserConected", OracleTypes.VARCHAR),
                        new SqlOutParameter("ppCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("result_msg", OracleTypes.VARCHAR)
                );
    }

    //** Delete Programa
    public JsonJTableExpenseBean delPrograma(Integer idCapitulo, String usuario) {
        SimpleJdbcCall llamada = delProgramaCall();
        Map<String, Object> parametros = delProgramaParams(idCapitulo, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pPROGRAMA")));
        return jteb;
    }

    private Map<String, Object> delProgramaParams(Integer idCapitulo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", idCapitulo);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delProgramaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("DEL_PROGRAMA").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPROGRAMA", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    //** Delete Capitulo
    public JsonJTableExpenseBean delCapitulo(Integer idCapitulo, String usuario) {
        SimpleJdbcCall llamada = delCapituloCall();
        Map<String, Object> parametros = delCapituloParams(idCapitulo, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pCAPITULO")));
        return jteb;
    }

    private Map<String, Object> delCapituloParams(Integer idCapitulo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", idCapitulo);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delCapituloCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("DEL_CAPITULO").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCAPITULO", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    //** Delete Partida
    public JsonJTableExpenseBean delPartida(Integer idPartida, String usuario) {
        SimpleJdbcCall llamada = delPartidaCall();
        Map<String, Object> parametros = delPartidaParams(idPartida, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pPARTIDA")));
        return jteb;
    }

    private Map<String, Object> delPartidaParams(Integer idPartida, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", idPartida);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delPartidaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("DEL_PARTIDA").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPARTIDA", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    //** Update Programa
    public JsonJTableExpenseBean updPrograma(Integer id, Integer idCapitulo, String codigo, String nombre, String ejercicio, String usuario) {
        SimpleJdbcCall llamada = updProgramaCall();
        Map<String, Object> parametros = updProgramaParams(id, idCapitulo, codigo, nombre, ejercicio, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pPROGRAMA"));
        return jteb;
    }

    private Map<String, Object> updProgramaParams(Integer id, Integer idCapitulo, String codigo, String nombre, String ejercicio, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pENT_CAP_ID", idCapitulo);
        parametros.put("pENT_PROG_CODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        parametros.put("pEJERCICIO", ejercicio);
        return parametros;
    }

    private SimpleJdbcCall updProgramaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("UPD_PROGRAMA").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pENT_CAP_ID", OracleTypes.INTEGER),
                        new SqlParameter("pENT_PROG_CODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pPROGRAMA", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    //** Update Partida
    public JsonJTableExpenseBean updPartida(Integer id, String nombre, String usuario, Integer idEjercicio) {
        SimpleJdbcCall llamada = updPartidaCall();
        Map<String, Object> parametros = updPartidaParams(id, nombre, usuario, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pPARTIDA"));
        return jteb;
    }

    private Map<String, Object> updPartidaParams(Integer id, String nombre, String usuario, Integer idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pIdEjercicio", idEjercicio);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updPartidaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("UPD_PARTIDA").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pIdEjercicio", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPARTIDA", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    //** Get Programa By Id.
    public JsonJTableExpenseBean getProgramaById(Integer id, String idEjercicio) {
        SimpleJdbcCall llamada = getProgramaByIdCall();
        Map<String, Object> parametros = getProgramaByIdParams(id, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pNOMBRE_PROGRAMA")));
        return jteb;
    }

    private Map<String, Object> getProgramaByIdParams(Integer id, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pEJERCICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getProgramaByIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_PROGRAMA_BY_ID").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pNOMBRE_PROGRAMA", OracleTypes.VARCHAR)
                );
    }

    //** Get Capitulo By Id.
    public JsonJTableExpenseBean getCapituloById(Integer id, String idEjercicio) {
        SimpleJdbcCall llamada = getCapituloByIdCall();
        Map<String, Object> parametros = getCapituloByIdParams(id, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pNOMBRE_CAPITULO")));
        return jteb;
    }

    private Map<String, Object> getCapituloByIdParams(Integer id, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pEJERCICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getCapituloByIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_CAPITULO_BY_ID").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pNOMBRE_CAPITULO", OracleTypes.VARCHAR)
                );
    }

    //** Get Partida By Id.
    public JsonJTableExpenseBean getPartidaById(Integer id, String idEjercicio) {
        SimpleJdbcCall llamada = getPartidaByIdCall();
        Map<String, Object> parametros = getPartidaByIdParams(id, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pNOMBRE_PARTIDA")));
        return jteb;
    }

    private Map<String, Object> getPartidaByIdParams(Integer id, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pEJERCICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getPartidaByIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_PARTIDA_BY_ID").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pNOMBRE_PARTIDA", OracleTypes.VARCHAR)
                );
    }

    //** Add Capitulo
    public JsonJTableExpenseBean addCapitulo(String codCapitulo, String nomCapitulo, Integer id, String ejercicio, String usuario, Integer entidad) {
        SimpleJdbcCall llamada = addCapituloCall();
        Map<String, Object> parametros = addCapituloParams(codCapitulo, nomCapitulo, id, ejercicio, usuario, entidad);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pID")));
        return jteb;
    }

    private Map<String, Object> addCapituloParams(String codCapitulo, String nomCapitulo, Integer id, String ejercicio, String usuario, Integer entidad) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codCapitulo);
        parametros.put("pNOMBRE", nomCapitulo);
        parametros.put("pENTIDAD", entidad);
        parametros.put("pPARTIDA", id);
        parametros.put("pUSUARIO", usuario);
        parametros.put("pEJERCICIO", ejercicio);
        return parametros;
    }

    private SimpleJdbcCall addCapituloCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("SET_CAPITULO").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pENTIDAD", OracleTypes.INTEGER),
                        new SqlParameter("pPARTIDA", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pID", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    //** Add Programa
    public JsonJTableExpenseBean addPrograma(String codigo, String nombre, Integer id, String ejercicio, String usuario) {
        SimpleJdbcCall llamada = addProgramaCall();
        Map<String, Object> parametros = addProgramaParams(codigo, nombre, id, ejercicio, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pID")));
        return jteb;
    }

    private Map<String, Object> addProgramaParams(String codigo, String nombre, Integer id, String ejercicio, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pCAPITULO", id);
        parametros.put("pUSUARIO", usuario);
        parametros.put("pEJERCICIO", ejercicio);
        return parametros;
    }

    private SimpleJdbcCall addProgramaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("SET_PROGRAMA").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pCAPITULO", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pID", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    //** Get Codigo Partida by Id Capitulo
    public JsonJTableExpenseBean getCodigoPartidaByIdCap(Integer id, String idEjercicio) {
        SimpleJdbcCall llamada = getCodigoPartidaByIdCapCall();
        Map<String, Object> parametros = getCodigoPartidaByIdCapParams(id, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setCodPartida(String.valueOf(result.get("pCODIGO_PAR")));
        return jteb;
    }

    private Map<String, Object> getCodigoPartidaByIdCapParams(Integer id, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pEJERCICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getCodigoPartidaByIdCapCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_CODIGO_PARTIDA_BY_ID_CAP").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pCODIGO_PAR", OracleTypes.VARCHAR)
                );
    }

    //** Get Codigo Capitulo by Id
    public JsonJTableExpenseBean getCodigoCapituloById(Integer id, String idEjercicio) {
        SimpleJdbcCall llamada = getCodigoCapituloByIdCall();
        Map<String, Object> parametros = getCodigoCapituloByIdParams(id, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setCodCapitulo(String.valueOf(result.get("pCODIGO_CAPITULO")));
        return jteb;
    }

    private Map<String, Object> getCodigoCapituloByIdParams(Integer id, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pEJERCICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getCodigoCapituloByIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_CODIGO_CAPITULO_BY_ID").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pCODIGO_CAPITULO", OracleTypes.VARCHAR)
                );
    }

    //** Update Capitulo
    public JsonJTableExpenseBean updCapitulo(Integer id, Integer idPartida, String codigo, String nombre, String ejercicio, String usuario) {
        SimpleJdbcCall llamada = updCapituloCall();
        Map<String, Object> parametros = updCapituloParams(id, idPartida, codigo, nombre, ejercicio, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pCAPITULO"));
        return jteb;
    }

    private Map<String, Object> updCapituloParams(Integer id, Integer idPartida, String codigo, String nombre, String ejercicio, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pPARTIDA_ID", idPartida);
        parametros.put("pENT_CAP_CODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        parametros.put("pEJERCICIO", ejercicio);
        return parametros;
    }

    private SimpleJdbcCall updCapituloCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("UPD_CAPITULO").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pPARTIDA_ID", OracleTypes.INTEGER),
                        new SqlParameter("pENT_CAP_CODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pCAPITULO", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    //** Get Codigo Partida by Id
    public JsonJTableExpenseBean getCodigoPartidaById(Integer id, String idEjercicio) {
        SimpleJdbcCall llamada = getCodigoPartidaByIdCall();
        Map<String, Object> parametros = getCodigoPartidaByIdParams(id, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setCodPartida(String.valueOf(result.get("pCODIGO_PARTIDA")));
        return jteb;
    }

    private Map<String, Object> getCodigoPartidaByIdParams(Integer id, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pEJERCICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getCodigoPartidaByIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_CODIGO_PARTIDA_BY_ID").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pCODIGO_PARTIDA", OracleTypes.VARCHAR)
                );
    }

    //** Add Partida
    public JsonJTableExpenseBean addPartida(Integer ejercicio, String codigo, String nombre, String usuario) {
        SimpleJdbcCall llamada = addPartidaCall();
        Map<String, Object> parametros = addPartidaParams(ejercicio, codigo, nombre, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pID")));
        return jteb;
    }

    private Map<String, Object> addPartidaParams(Integer ejercicio, String codigo, String nombre, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJERCICIO", ejercicio);
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall addPartidaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("SET_PARTIDA").declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMBER),
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pID", OracleTypes.NUMBER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    //** Registro Bitacora Programa
    public Bitacora getRegistroBitacoraPrograma(Integer pid) {
        SimpleJdbcCall llamada = getRegistroBitacoraProgramaCall();
        Map<String, Object> parametros = getRegistroBitacoraProgramaParams(pid);
        Map<String, Object> result = llamada.execute(parametros);
        List<Bitacora> bitacoras = (List<Bitacora>) result.get("PCURSOR");
        return bitacoras.get(0);
    }

    private Map<String, Object> getRegistroBitacoraProgramaParams(Integer pid) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", pid);
        return parametros;
    }

    private SimpleJdbcCall getRegistroBitacoraProgramaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_BITACORA_PROGRAMA").declareParameters(
                        new SqlParameter("pID", OracleTypes.NUMBER),
                        new SqlOutParameter("PCURSOR", OracleTypes.CURSOR, new BitacoraBOMapper())
                );
    }

    //** Registro bitacora capitulo
    public Bitacora getRegistroBitacoraCapitulo(Integer pid) {
        SimpleJdbcCall llamada = getRegistroBitacoraCapituloCall();
        Map<String, Object> parametros = getRegistroBitacoraCapituloParams(pid);
        Map<String, Object> result = llamada.execute(parametros);
        List<Bitacora> bitacoras = (List<Bitacora>) result.get("PCURSOR");
        return bitacoras.get(0);
    }

    private Map<String, Object> getRegistroBitacoraCapituloParams(Integer pid) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", pid);
        return parametros;
    }

    private SimpleJdbcCall getRegistroBitacoraCapituloCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_BITACORA_CAPITULO").declareParameters(
                        new SqlParameter("pID", OracleTypes.NUMBER),
                        new SqlOutParameter("PCURSOR", OracleTypes.CURSOR, new BitacoraBOMapper())
                );
    }

    //** Registro Bitacora Partida
    public Bitacora getRegistroBitacoraPartida(Integer pid) {
        SimpleJdbcCall llamada = getRegistroBitacoraPartidaCall();
        Map<String, Object> parametros = getRegistroBitacoraPartidaParams(pid);
        Map<String, Object> result = llamada.execute(parametros);
        List<Bitacora> bitacoras = (List<Bitacora>) result.get("PCURSOR");
        return bitacoras.get(0);
    }

    private Map<String, Object> getRegistroBitacoraPartidaParams(Integer pid) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", pid);
        return parametros;
    }

    private SimpleJdbcCall getRegistroBitacoraPartidaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_BITACORA_PARTIDA").declareParameters(
                        new SqlParameter("pID", OracleTypes.NUMBER),
                        new SqlOutParameter("PCURSOR", OracleTypes.CURSOR, new BitacoraBOMapper())
                );
    }

    //** PP
    public List<ProgramaPresupuestarioDTO> getPP(Integer ejercicio, String partida, String capitulo) {
        SimpleJdbcCall llamada = getPPCall();
        Map<String, Object> parametros = getPPParams(ejercicio, partida, capitulo);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<ProgramaPresupuestarioDTO>) result.get("PPCURSOR");
    }

    private Map<String, Object> getPPParams(Integer ejercicio, String partida, String capitulo) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pPARTIDA", partida);
        parametros.put("pEJERCICIO", ejercicio);
        parametros.put("pCAPITULO", capitulo);
        return parametros;
    }

    private SimpleJdbcCall getPPCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_PROGRAMA_PRESUPUESTARIO").declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMBER),
                        new SqlParameter("pPARTIDA", OracleTypes.NUMBER),
                        new SqlParameter("pCAPITULO", OracleTypes.NUMBER),
                        new SqlOutParameter("PPCURSOR", OracleTypes.CURSOR, new PPMapper())
                );
    }

    //** Capitulo
    public List<ProgramaPresupuestarioDTO> getCapitulo(String ejercicioId, Integer codPartida) {
        SimpleJdbcCall llamada = getCapituloCall();
        Map<String, Object> parametros = getCapituloParams(ejercicioId, codPartida);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<ProgramaPresupuestarioDTO>) result.get("pCAPITULO_CURSOR");
    }

    private Map<String, Object> getCapituloParams(String ejercicioId, Integer codPartida) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pPARTIDA", codPartida);
        parametros.put("pIDEJERCICIO", ejercicioId);
        return parametros;
    }

    private SimpleJdbcCall getCapituloCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("OBTENER_CAPITULO_PARTIDA").declareParameters(
                        new SqlParameter("pPARTIDA", OracleTypes.NUMBER),
                        new SqlParameter("pIDEJERCICIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCAPITULO_CURSOR", OracleTypes.CURSOR, new CapituloMapper())
                );
    }

    //** Partida
    public List<ProgramaPresupuestarioDTO> getPartida(Integer ejercicioId) {
        SimpleJdbcCall llamada = getPartidaCall();
        Map<String, Object> parametros = getPartidaParams(ejercicioId);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<ProgramaPresupuestarioDTO>) result.get("pPARTIDA_CURSOR");
    }

    private Map<String, Object> getPartidaParams(Integer ejercicioId) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJERCICIO", ejercicioId);
        return parametros;
    }

    private SimpleJdbcCall getPartidaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("OBTENER_PARTIDA_EJERCICIO").declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMBER),
                        new SqlOutParameter("pPARTIDA_CURSOR", OracleTypes.CURSOR, new PartidaEjercicioMapper())
                );
    }

    //*******************************************************
    public List<OptionsJtable> loadPaginasOption() {
        SimpleJdbcCall llamada = loadPaginasOptionCall();
        Map<String, Object> result = llamada.execute();
        return (List<OptionsJtable>) result.get("cPAGES");
    }

    private SimpleJdbcCall loadPaginasOptionCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_USUARIOS)
                .withProcedureName("GET_PAGES").declareParameters(
                        new SqlOutParameter("cPAGES", OracleTypes.CURSOR, new PaginasMapper())
                );
    }

    public JsonJTableExpenseBean addRolesPaginasMant(Integer rol, Integer pagina, String usuario) {
        SimpleJdbcCall llamada = addRolesPaginasCall();
        Map<String, Object> parametros = addRolesPaginasParams(rol, pagina, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pROLPAGE")));
        return jteb;
    }

    private Map<String, Object> addRolesPaginasParams(Integer rol, Integer pagina, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pROL", rol);
        parametros.put("pPAGINA", pagina);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall addRolesPaginasCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_USUARIOS)
                .withProcedureName("INS_ROLES_PAGES").declareParameters(
                        new SqlParameter("pROL", OracleTypes.NUMERIC),
                        new SqlParameter("pPAGINA", OracleTypes.NUMERIC),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pROLPAGE", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean updRolesPaginasMant(Integer rolPage, Integer rol, Integer pagina, String usuario) {

        SimpleJdbcCall llamada = updRolesPaginasCall();
        Map<String, Object> parametros = updRolesPaginasParams(rolPage, rol, pagina, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pRESULT"));
        return jteb;
    }

    private Map<String, Object> updRolesPaginasParams(Integer rolPage, Integer rol, Integer pagina, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pROLPAGE", rolPage);
        parametros.put("pROL", rol);
        parametros.put("pPAGINA", pagina);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updRolesPaginasCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_USUARIOS)
                .withProcedureName("UPD_ROLES_PAGES").declareParameters(
                        new SqlParameter("pROLPAGE", OracleTypes.INTEGER),
                        new SqlParameter("pROL", OracleTypes.INTEGER),
                        new SqlParameter("pPAGINA", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pRESULT", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean delRolesPaginasMant(Integer idPerInf, String usuario) {
        SimpleJdbcCall llamada = delRolesPaginasCall();
        Map<String, Object> parametros = delRolesPaginasParams(idPerInf, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pRESULT")));
        return jteb;
    }

    private Map<String, Object> delRolesPaginasParams(Integer rolPage, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pROLPAGE", rolPage);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delRolesPaginasCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_USUARIOS)
                .withProcedureName("DEL_ROLES_PAGES").declareParameters(
                        new SqlParameter("pROLPAGE", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pRESULT", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public List<JsonJTableExpenseBean> getRolesPaginasMant(Integer rol) {
        SimpleJdbcCall llamada = getRolesPaginasCall();
        Map<String, Object> parametros = getRolesPaginasParams(rol);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("cPAGES");
    }

    private Map<String, Object> getRolesPaginasParams(Integer rol) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pROL", rol);
        return parametros;
    }

    private SimpleJdbcCall getRolesPaginasCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_USUARIOS)
                .withProcedureName("GET_PAGES_ByRol").declareParameters(
                        new SqlParameter("pROL", OracleTypes.NUMERIC),
                        new SqlOutParameter("cPAGES", OracleTypes.CURSOR, new RolPaginaMapper())
                );
    }

    public JsonJTableExpenseBean addPeriodoInformeMant(Integer infId, Integer perEjerId, Integer perInfSec, String usuario) {

        SimpleJdbcCall llamada = addPeriodoInformeCall();
        Map<String, Object> parametros = addPeriodoInformeParams(infId, perEjerId, perInfSec, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pCODE_RESULT")));
        return jteb;
    }

    private Map<String, Object> addPeriodoInformeParams(Integer infId, Integer perEjerId, Integer perInfSec, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pINFID", infId);
        parametros.put("pPEJRID", perEjerId);
        parametros.put("pERINF_SEC", perInfSec);
        parametros.put("USUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall addPeriodoInformeCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("SET_TBL_PERIODO_INFORME").declareParameters(
                        new SqlParameter("pINFID", OracleTypes.NUMERIC),
                        new SqlParameter("pPEJRID", OracleTypes.NUMERIC),
                        new SqlParameter("pERINF_SEC", OracleTypes.NUMERIC),
                        new SqlParameter("USUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCODE_RESULT", OracleTypes.NUMBER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean updPeriodoInformeMant(Integer perInfId, Integer infId, Integer perEjerId, Integer perInfSec, String usuario) {

        SimpleJdbcCall llamada = updPeriodoInformeCall();
        Map<String, Object> parametros = updPeriodoInformeParams(perInfId, infId, perEjerId, perInfSec, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pCODE_RESULT"));
        return jteb;
    }

    private Map<String, Object> updPeriodoInformeParams(Integer perInfId, Integer infId, Integer perEjerId, Integer perInfSec, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pPERINFID", perInfId);
        parametros.put("pINFID", infId);
        parametros.put("pPEJRID", perEjerId);
        parametros.put("pERINF_SEC", perInfSec);
        parametros.put("USUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updPeriodoInformeCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("UPD_TBL_PERIODO_INFORME").declareParameters(
                        new SqlParameter("pPERINFID", OracleTypes.INTEGER),
                        new SqlParameter("pINFID", OracleTypes.INTEGER),
                        new SqlParameter("pPEJRID", OracleTypes.INTEGER),
                        new SqlParameter("pERINF_SEC", OracleTypes.INTEGER),
                        new SqlParameter("USUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCODE_RESULT", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean delPeriodoInformeMant(Integer idPerInf, String usuario) {

        SimpleJdbcCall llamada = delPeriodoInformeCall();
        Map<String, Object> parametros = delPeriodoInformeParams(idPerInf, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pCODE_RESULT")));
        return jteb;
    }

    private Map<String, Object> delPeriodoInformeParams(Integer idPerInf, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pPERINFID", idPerInf);
        parametros.put("USUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delPeriodoInformeCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("DEL_TBL_PERIODO_INFORME").declareParameters(
                        new SqlParameter("pPERINFID", OracleTypes.INTEGER),
                        new SqlParameter("USUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCODE_RESULT", OracleTypes.NUMBER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public List<JsonJTableExpenseBean> getPeriodoInformeMant(Integer ejercicio, Integer informe, String order) {
        SimpleJdbcCall llamada = getPeriodoInformeCall();
        Map<String, Object> parametros = getPeriodoInformeParams(ejercicio, informe, order);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("CURSOR_PERIODO");
    }

    private Map<String, Object> getPeriodoInformeParams(Integer ejercicio, Integer informe, String order) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJERCICIO", ejercicio);
        parametros.put("pINFORME", informe);
        parametros.put("pORDER", order);
        return parametros;
    }

    private SimpleJdbcCall getPeriodoInformeCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("GET_TBL_PERIODO_INFORME").declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMERIC),
                        new SqlParameter("pINFORME", OracleTypes.NUMERIC),
                        new SqlParameter("pORDER", OracleTypes.VARCHAR),
                        new SqlOutParameter("CURSOR_PERIODO", OracleTypes.CURSOR, new PeriodoInformeMapper())
                );
    }

    public JsonJTableExpenseBean addProvinciasMant(int regionId, String provCod, String provNom, String usuario) {

        SimpleJdbcCall llamada = addProvinciasMantCall();
        Map<String, Object> parametros = addProvinciasMantParams(regionId, provCod, provNom, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pCODE_RESULT")));
        return jteb;
    }

    private Map<String, Object> addProvinciasMantParams(int regionId, String provCod, String provNom, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pREG_ID", regionId);
        parametros.put("pPROV_CODIGO", provCod);
        parametros.put("pPROV_NOMBRE", provNom);
        parametros.put("USUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall addProvinciasMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("SET_PROVINCIA").declareParameters(
                        new SqlParameter("pREG_ID", OracleTypes.NUMERIC),
                        new SqlParameter("pPROV_CODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pPROV_NOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("USUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCODE_RESULT", OracleTypes.NUMBER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean updProvinciasMant(int provId, String nombre, String codigo, String usuario) {

        SimpleJdbcCall llamada = updProvinciasMantCall();
        Map<String, Object> parametros = updProvinciasMantParams(provId, nombre, codigo, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pPROVINCIA"));
        return jteb;
    }

    private Map<String, Object> updProvinciasMantParams(int provId, String nombre, String codigo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pPROV_ID", provId);
        parametros.put("pPROV_CODIGO", codigo);
        parametros.put("pPROV_NOMBRE", nombre);
        parametros.put("USUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updProvinciasMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("UPD_PROVINCIA").declareParameters(
                        new SqlParameter("pPROV_ID", OracleTypes.INTEGER),
                        new SqlParameter("pPROV_CODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pPROV_NOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("USUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPROVINCIA", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean delProvinciasMant(int idProvincia, String usuario) {

        SimpleJdbcCall llamada = delProvinciasMantCall();
        Map<String, Object> parametros = delProvinciasMantParams(idProvincia, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pPROVINCIA")));
        return jteb;
    }

    private Map<String, Object> delProvinciasMantParams(int idProvincia, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pPROV_ID", idProvincia);
        parametros.put("USUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delProvinciasMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("DEL_PROVINCIA").declareParameters(
                        new SqlParameter("pPROV_ID", OracleTypes.INTEGER),
                        new SqlParameter("USUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPROVINCIA", OracleTypes.NUMBER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public List<JsonJTableExpenseBean> getProvinciasRegMant(int region) {
        SimpleJdbcCall llamada = getProvinciasRegMantCall();
        Map<String, Object> parametros = getProvinciasRegMantParams(region);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("pPROVINCIAS_CURSOR");
    }

    private Map<String, Object> getProvinciasRegMantParams(int region) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pREG_ID", region);
        return parametros;
    }

    private SimpleJdbcCall getProvinciasRegMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_PROVINCIAS_ByRegion").declareParameters(
                        new SqlParameter("pREG_ID", OracleTypes.NUMERIC),
                        new SqlOutParameter("pPROVINCIAS_CURSOR", OracleTypes.CURSOR, new ProvinciasMapper())
                );
    }

    public List<JsonJTableExpenseBean> getProvinciasMant(int region, String order) {
        SimpleJdbcCall llamada = getProvinciasMantCall();
        Map<String, Object> parametros = getProvinciasMantParams(region, order);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("pPROVINCIAS_CURSOR");
    }

    private Map<String, Object> getProvinciasMantParams(int region, String order) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pREG_ID", region);
        parametros.put("pORDER", order);
        return parametros;
    }

    private SimpleJdbcCall getProvinciasMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_PROVINCIAS_MANT").declareParameters(
                        new SqlParameter("pREG_ID", OracleTypes.NUMERIC),
                        new SqlParameter("pORDER", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPROVINCIAS_CURSOR", OracleTypes.CURSOR, new ProvinciasMapper())
                );
    }

    public JsonJTableExpenseBean addRegionMant(String codigo, String nombre, String usuario) {

        SimpleJdbcCall llamada = addRegionMantCall();
        Map<String, Object> parametros = addRegionMantParams(codigo, nombre, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pREGION")));
        return jteb;
    }

    private Map<String, Object> addRegionMantParams(String codigo, String nombre, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall addRegionMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("SET_REGION").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pREGION", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean updRegionMant(String areaId, String nombre, String codigo, String usuario) {

        SimpleJdbcCall llamada = updRegionMantCall();
        Map<String, Object> parametros = updRegionMantParams(areaId, nombre, codigo, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pREGION"));
        return jteb;
    }

    private Map<String, Object> updRegionMantParams(String areaId, String nombre, String codigo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", areaId);
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updRegionMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("UPDATE_REGION").declareParameters(
                        new SqlParameter("pID", OracleTypes.VARCHAR),
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pREGION", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean delRegionMant(String codigo, String usuario) {

        SimpleJdbcCall llamada = delRegionMantCall();
        Map<String, Object> parametros = delRegionMantParams(codigo, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pREGION")));
        return jteb;
    }

    private Map<String, Object> delRegionMantParams(String codigo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delRegionMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("DEL_REGION").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pREGION", OracleTypes.NUMBER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public List<JsonJTableExpenseBean> getRegionMant(String order) {
        SimpleJdbcCall llamada = getRegionMantCall();
        Map<String, Object> parametros = getRegionMantParams(order);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("pREGIONES_CURSOR");
    }

    private Map<String, Object> getRegionMantParams(String order) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pORDER", order);
        return parametros;
    }

    private SimpleJdbcCall getRegionMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("GET_REGION_MANT_2").declareParameters(
                        new SqlParameter("pORDER", OracleTypes.VARCHAR),
                        new SqlOutParameter("pREGIONES_CURSOR", OracleTypes.CURSOR, new RegionMantenedorMapper())
                );
    }

    public JsonJTableExpenseBean addEjercicioMant(String codigo, String nombre, int tipo, String comuna, String usuario) {

        SimpleJdbcCall llamada = addEjercicioMantCall();
        Map<String, Object> parametros = addEjercicioMantParams(codigo, nombre, tipo, comuna, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setEjercicio(String.valueOf(result.get("pEJERCICIO")));
        return jteb;
    }

    private Map<String, Object> addEjercicioMantParams(String codigo, String nombre, int tipo, String comuna, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pTIPO", tipo);
        parametros.put("pCOMUNA", comuna);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall addEjercicioMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("SET_EJERCICIO").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pTIPO", OracleTypes.NUMERIC),
                        new SqlParameter("pCOMUNA", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pEJERCICIO", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean updEjercicioMant(String areaId, String nombre, String codigo, String usuario) {

        SimpleJdbcCall llamada = updEjercicioMantCall();
        Map<String, Object> parametros = updEjercicioMantParams(areaId, nombre, codigo, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pEJERCICIO"));
        return jteb;
    }

    private Map<String, Object> updEjercicioMantParams(String areaId, String nombre, String codigo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", areaId);
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updEjercicioMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("UPDATE_EJERCICIO").declareParameters(
                        new SqlParameter("pID", OracleTypes.VARCHAR),
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean delEjercicioMant(String codigo, String usuario) {

        SimpleJdbcCall llamada = delEjercicioMantCall();
        Map<String, Object> parametros = delEjercicioMantParams(codigo, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setEjercicio(String.valueOf(result.get("pEJERCICIO")));
        return jteb;
    }

    private Map<String, Object> delEjercicioMantParams(String codigo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delEjercicioMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("DEL_EJERCICIO").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pEJERCICIO", OracleTypes.NUMBER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public List<JsonJTableExpenseBean> getEjercicioMant(String order) {
        SimpleJdbcCall llamada = getEjercicioMantCall();
        Map<String, Object> parametros = getEjercicioMantParams(order);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("pEJERCICIO_CURSOR");
    }

    private Map<String, Object> getEjercicioMantParams(String order) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pORDER", order);
        return parametros;
    }

    private SimpleJdbcCall getEjercicioMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("GET_EJERCICIO_MANT").declareParameters(
                        new SqlParameter("pORDER", OracleTypes.VARCHAR),
                        new SqlOutParameter("pEJERCICIO_CURSOR", OracleTypes.CURSOR, new EjercicioMantenedorMapper())
                );
    }

    public List<JsonJTableExpenseBean> getEtapasCompromisoMant(String order) {
        SimpleJdbcCall llamada = getEtapasCompromisoMantCall();
        Map<String, Object> parametros = getEtapasCompromisoMantParams(order);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("pCOMPROMISO_CURSOR");
    }

    private Map<String, Object> getEtapasCompromisoMantParams(String order) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pORDER", order);
        return parametros;
    }

    private SimpleJdbcCall getEtapasCompromisoMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_ETAPAS_COMPROMISO").declareParameters(
                        new SqlParameter("pORDER", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCOMPROMISO_CURSOR", OracleTypes.CURSOR, new EtapasCompromisoMantenedorMapper())
                );
    }


    public JsonJTableExpenseBean addEtapasCompromisoMant(String etapa, String nombre, String rut, String usuario) {

        SimpleJdbcCall llamada = addEtapasCompromisoMantCall();
        Map<String, Object> parametros = addEtapasCompromisoMantParams(etapa, nombre, rut, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setCompromiso(String.valueOf(result.get("pCOMPROMISO")));
        return jteb;
    }

    private Map<String, Object> addEtapasCompromisoMantParams(String etapa, String nombre, String rut, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pETAPA", etapa);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pRUT", rut);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall addEtapasCompromisoMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("SET_ETAPAS_COMPROMISO").declareParameters(
                        new SqlParameter("pETAPA", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pRUT", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCOMPROMISO", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean updEtapasCompromisoMant(String areaId, String etapa, String nombre, String rut, String usuario) {

        SimpleJdbcCall llamada = updEtapasCompromisoMantCall();
        Map<String, Object> parametros = updEtapasCompromisoMantParams(areaId, etapa, nombre, rut, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pCOMPROMISO"));
        return jteb;
    }

    private Map<String, Object> updEtapasCompromisoMantParams(String areaId, String etapa, String nombre, String rut, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", areaId);
        parametros.put("pETAPA", etapa);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pRUT", rut);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updEtapasCompromisoMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("UPDATE_ETAPAS_COMPROMISO").declareParameters(
                        new SqlParameter("pID", OracleTypes.VARCHAR),
                        new SqlParameter("pETAPA", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pRUT", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCOMPROMISO", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }


    public JsonJTableExpenseBean delEtapasCompromisoMant(String areaId, String usuario) {

        SimpleJdbcCall llamada = delEtapasCompromisoMantCall();
        Map<String, Object> parametros = delEtapasCompromisoMantParams(areaId, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setCompromiso(String.valueOf(result.get("pCOMPROMISO")));
        return jteb;
    }

    private Map<String, Object> delEtapasCompromisoMantParams(String areaId, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", areaId);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delEtapasCompromisoMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("DEL_ETAPAS_COMPROMISO").declareParameters(
                        new SqlParameter("pID", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCOMPROMISO", OracleTypes.NUMBER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }


    public List<SubTipoInforme> loadMantenedorTblInforme(int tipo) {
        SimpleJdbcCall llamada = loadMantenedorTblInformeCall();
        Map<String, Object> parametros = loadMantenedorTblInformeParams(tipo);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<SubTipoInforme>) result.get(P_SUBTIPOS);
    }

    private Map<String, Object> loadMantenedorTblInformeParams(int tipo) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_TIPO, tipo);
        return parametros;
    }

    private SimpleJdbcCall loadMantenedorTblInformeCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("GET_SUBTIPO_INFORMES_MANT").declareParameters(
                        new SqlParameter(P_TIPO, OracleTypes.NUMERIC),
                        new SqlOutParameter(P_SUBTIPOS, OracleTypes.CURSOR, new SubTipoInformeMapper())
                );
    }

    public List<JsonJTableExpenseBean> getTblInformes(int subTipoInfo) {
        SimpleJdbcCall llamada = getTblInformesCall();
        Map<String, Object> parametros = getTblInformesParams(subTipoInfo);
        Map<String, Object> resutl = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) resutl.get(P_INFORMES);
    }

    private Map<String, Object> getTblInformesParams(int subTipoInfo) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_SUBTIPO, subTipoInfo);
        return parametros;
    }

    private SimpleJdbcCall getTblInformesCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("GET_INFORMES_X_SUBTIPO").declareParameters(
                        new SqlParameter(P_SUBTIPO, OracleTypes.NUMERIC),
                        new SqlOutParameter(P_INFORMES, OracleTypes.CURSOR, new InformesMantenedorMapper())
                );
    }

    public List<OptionsJtable> getSubTipos() {
        SimpleJdbcCall llamada = getSubTiposCall();
        Map<String, Object> result = llamada.execute(new HashMap<String, Object>());
        return (List<OptionsJtable>) result.get(P_TIPOS_CURSOR);
    }

    private SimpleJdbcCall getSubTiposCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_SUBTIPOS")
                .declareParameters(
                        new SqlOutParameter(P_TIPOS_CURSOR, OracleTypes.CURSOR, new SubTiposMantenedorMapper())
                );
    }


    public JsonJTableExpenseBean addTblInforme(int subTipoInfo, String codigo, String nombre, String descripcion, String usuario) {

        SimpleJdbcCall llamada = addTblInformeCall();
        Map<String, Object> parametros = addTblInformeParams(
                subTipoInfo,
                codigo,
                nombre,
                descripcion,
                usuario
        );
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jsonJTableExpenseBean = new JsonJTableExpenseBean();
        jsonJTableExpenseBean.setId((Integer) result.get(P_INF));
        jsonJTableExpenseBean.setNombre((String) result.get(P_MESSAGE));
        return jsonJTableExpenseBean;
    }

    private Map<String, Object> addTblInformeParams(int subTipoInfo, String codigo, String nombre, String descripcion, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_SUBTIPO_INF, subTipoInfo);
        parametros.put(P_CODIGO, codigo);
        parametros.put(P_NOMBRE, nombre);
        parametros.put(P_DESCRIPCION, descripcion);
        parametros.put(P_USUARIO, usuario);
        return parametros;
    }

    private SimpleJdbcCall addTblInformeCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("SET_INFORME")
                .declareParameters(
                        new SqlParameter(P_SUBTIPO_INF, OracleTypes.NUMERIC),
                        new SqlParameter(P_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter(P_NOMBRE, OracleTypes.VARCHAR),
                        new SqlParameter(P_DESCRIPCION, OracleTypes.VARCHAR),
                        new SqlParameter(P_USUARIO, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_INF, OracleTypes.INTEGER),
                        new SqlOutParameter(P_MESSAGE, OracleTypes.VARCHAR)
                );
    }



    public JsonJTableExpenseBean updateTblInforme(JsonJTableExpenseBean Informe) {
        SimpleJdbcCall llamada = updateTblInformeCall();
        Map<String, Object> parametros = updateTblInformeParams(
                Informe.getId(),
                Informe.getCodigo(),
                Informe.getNombre(),
                Informe.getDescripcion(),
                Informe.getUsuarioUpdate()
        );
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jsonJTableExpenseBean = new JsonJTableExpenseBean();
        jsonJTableExpenseBean.setId((Integer) result.get(P_TIPO_INF));
        jsonJTableExpenseBean.setNombre((String) result.get(P_MESSAGE));
        return jsonJTableExpenseBean;
    }

    private Map<String, Object> updateTblInformeParams(Integer id, String codigo, String nombre, String descripcion, String usuarioUpdate) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(INFORME_ID, id);
        parametros.put(P_CODIGO, codigo);
        parametros.put(P_NOMBRE, nombre);
        parametros.put(P_DESCRIPCION, descripcion);
        parametros.put(P_USUARIO, usuarioUpdate);
        return parametros;
    }

    private SimpleJdbcCall updateTblInformeCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("UPD_INFORME").declareParameters(
                        new SqlParameter(INFORME_ID, OracleTypes.NUMERIC),
                        new SqlParameter(P_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter(P_NOMBRE, OracleTypes.VARCHAR),
                        new SqlParameter(P_DESCRIPCION, OracleTypes.VARCHAR),
                        new SqlParameter(P_USUARIO, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_TIPO_INF, OracleTypes.INTEGER),
                        new SqlOutParameter(P_MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean delTblInforme(String informeID, int usuario) {
        SimpleJdbcCall llamada = delTblInformeCall();
        Map<String, Object> parametros = delTblInformeParams(informeID, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jsonJTableExpenseBean = new JsonJTableExpenseBean();
        jsonJTableExpenseBean.setId((Integer) result.get(P_TIPO_INF));
        jsonJTableExpenseBean.setNombre((String) result.get(P_MESSAGE));
        return jsonJTableExpenseBean;
    }

    private Map<String, Object> delTblInformeParams(String informeID, int usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(INFORME_ID, informeID);
        parametros.put(P_USUARIO, usuario);
        return parametros;
    }

    private SimpleJdbcCall delTblInformeCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("DEL_INFORME").declareParameters(
                        new SqlParameter(INFORME_ID, OracleTypes.NUMERIC),
                        new SqlParameter(P_USUARIO, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_TIPO_INF, OracleTypes.INTEGER),
                        new SqlOutParameter(P_MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean setProyectos(String codigo, String nombre,String usuario, String estado) {
        SimpleJdbcCall call = setProyectosCall();
        Map<String, Object> params = setProyectosParams(codigo, nombre, usuario, estado);
        Map<String, Object> result = call.execute(params);
        JsonJTableExpenseBean record = new JsonJTableExpenseBean();
        record.setId((Integer) result.get(P_PROYECTO));
        record.setNombre((String) result.get(P_MESSAGE));
        return record;
    }

    private Map<String, Object> setProyectosParams(String codigo, String nombre, String usuario, String estado) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(P_CODIGO, codigo);
        params.put(P_NOMBRE, nombre);
        params.put(P_USUARIO, usuario);
        params.put(P_ESTADO, estado);
        return params;
    }

    private SimpleJdbcCall setProyectosCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("SET_PROYECTOS")
                .declareParameters(
                        new SqlParameter(P_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter(P_NOMBRE, OracleTypes.VARCHAR),
                        new SqlParameter(P_USUARIO, OracleTypes.VARCHAR),
                        new SqlParameter(P_ESTADO, OracleTypes.NUMBER),
                        new SqlOutParameter(P_PROYECTO, OracleTypes.INTEGER),
                        new SqlOutParameter(P_MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public List<JsonJTableExpenseBean> getPeriodoMant(String order) {
        SimpleJdbcCall llamada = getPeriodoMantCall();
        Map<String, Object> parametros = getPeriodoMantParams(order);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("pPERIODOS_CURSOR");
    }
    
    private Map<String, Object> getPeriodoMantParams(String order) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pORDER", order);
        return parametros;
    }
    
    private SimpleJdbcCall getPeriodoMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("GET_PERIODOS_MANT").declareParameters(
                        new SqlParameter("pORDER", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPERIODOS_CURSOR", OracleTypes.CURSOR, new PeriodoMantenedorMapper())
                );
    }
    
    public JsonJTableExpenseBean setPeriodoMant(String nombre, String codigo, String usuario) {
        SimpleJdbcCall llamada = setPeriodoMantCall();
        Map<String, Object> parametros = setPeriodoMantParams(nombre,codigo,usuario);
        Map<String, Object> result = llamada.execute(parametros);
        
        
        JsonJTableExpenseBean per = new JsonJTableExpenseBean();
        per.setId(result.get("pPERIODOS")!=null? (Integer) result.get("pPERIODOS"): 0);
        per.setMessage((String)result.get("pMESSAGE"));
        return per;

    }
    
    private Map<String, Object> setPeriodoMantParams(String nombre, String codigo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pNOMBRE", nombre);
        parametros.put("pCODIGO", codigo);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }
    
    private SimpleJdbcCall setPeriodoMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("SET_PERIODOS").declareParameters(
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPERIODOS", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }
    
    public JsonJTableExpenseBean updPeriodoMant(String id,String nombre, String codigo, String usuario) {
        SimpleJdbcCall llamada = updPeriodoMantCall();
        Map<String, Object> parametros = updPeriodoMantParams(id,nombre,codigo,usuario);
        Map<String, Object> result = llamada.execute(parametros);
        
        JsonJTableExpenseBean per = new JsonJTableExpenseBean();
        per.setId(result.get("pPERIODOS")!=null? (Integer) result.get("pPERIODOS"): 0);
        per.setMessage((String)result.get("pMESSAGE"));
        return per;
    }
    
    private Map<String, Object> updPeriodoMantParams(String id,String nombre, String codigo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pCODIGO", codigo);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }
    
    private SimpleJdbcCall updPeriodoMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("UPDATE_PERIODOS").declareParameters(
                        new SqlParameter("pID", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPERIODOS", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }
    
    public JsonJTableExpenseBean delPeriodoMant(String codigo, String usuario) {
        SimpleJdbcCall llamada = delPeriodoMantCall();
        Map<String, Object> parametros = delPeriodoMantParams(codigo,usuario);
        Map<String, Object> result = llamada.execute(parametros);
        
        JsonJTableExpenseBean per = new JsonJTableExpenseBean();
        per.setId(result.get("pPERIODOS")!=null? (Integer) result.get("pPERIODOS"): 0);
        per.setMessage((String)result.get("pMESSAGE"));
        return per;
    }
    
    private Map<String, Object> delPeriodoMantParams(String codigo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }
    
    private SimpleJdbcCall delPeriodoMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("DEL_PERIODOS").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPERIODOS", OracleTypes.INTEGER)
                );
    }

    public JsonJTableExpenseBean delArea(Integer idArea, Integer ejercicio, String usuario) {
        SimpleJdbcCall llamada = delAreaCall();
        Map<String, Object> parametros = delAreaParams(idArea, ejercicio, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pAT")));
        return jteb;
    }

    private Map<String, Object> delAreaParams(Integer idArea, Integer ejercicio, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", idArea);
        parametros.put("pEJERCICIO", ejercicio);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delAreaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("DEL_AT").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMERIC),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pAT", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    //** Delete Institucion
    public JsonJTableExpenseBean delInstitucion(Integer idInstitucion, String usuario, Integer ejercicio) {
        SimpleJdbcCall llamada = delInstitucionCall();
        Map<String, Object> parametros = delInstitucionParams(idInstitucion, usuario, ejercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pINS")));
        return jteb;
    }

    private Map<String, Object> delInstitucionParams(Integer idInstitucion, String usuario, Integer ejercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", idInstitucion);
        parametros.put("pUSUARIO", usuario);
        parametros.put("pEJERCICIO", ejercicio);
        return parametros;
    }

    private SimpleJdbcCall delInstitucionCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("DEL_INSTITUCION").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMERIC),
                        new SqlOutParameter("pINS", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public List<JsonJTableExpenseBean> getMonedaMant(String order) {
        SimpleJdbcCall llamada = getMonedaMantCall();
        Map<String, Object> parametros = getMonedaMantParams(order);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("pMONEDA_CURSOR");
    }

    private Map<String, Object> getMonedaMantParams(String order) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pORDER", order);
        return parametros;
    }

    private SimpleJdbcCall getMonedaMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("GET_MONEDA_MANT").declareParameters(
                        new SqlParameter("pORDER", OracleTypes.VARCHAR),
                        new SqlOutParameter("pMONEDA_CURSOR", OracleTypes.CURSOR, new MonedaMantenedorMapper())
                );
    }

    public JsonJTableExpenseBean setMonedaMant(String codigo, String nombre, String usuario) {
        SimpleJdbcCall llamada = setMonedaMantCall();
        Map<String, Object> parametros = setMonedaMantParams(codigo,nombre,usuario);
        Map<String, Object> result = llamada.execute(parametros);


        JsonJTableExpenseBean moneda = new JsonJTableExpenseBean();
        moneda.setId(result.get("pMONEDA")!=null? (Integer) result.get("pMONEDA"): 0);
        moneda.setMessage((String)result.get("pMESSAGE"));
        return moneda;

    }

    private Map<String, Object> setMonedaMantParams(String codigo, String nombre, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        return parametros;
    }

    public JsonJTableExpenseBean delSector(Integer idSector, String usuario) {
        SimpleJdbcCall llamada = delSectorCall();
        Map<String, Object> parametros = delSectorParams(idSector, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pSECTOR")));
        return jteb;
    }

    private Map<String, Object> delSectorParams(Integer idSector, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", idSector);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delSectorCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("DEL_SECTOR").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pSECTOR", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    //** Update Area
    public JsonJTableExpenseBean updArea(Integer ejercicio, Integer id, Integer idInstitucion,
                                         String codigoArea, String rut, String dv, String nombre, String usuario) {
        SimpleJdbcCall llamada = updAreaCall();
        Map<String, Object> parametros = updAreaParams(ejercicio, id, idInstitucion, codigoArea, rut, dv, nombre, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pAT"));
        return jteb;
    }

    private Map<String, Object> updAreaParams(Integer ejercicio, Integer id, Integer idInstitucion,
                                              String codigoArea, String rut, String dv, String nombre, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJERCICIO", ejercicio);
        parametros.put("pID", id);
        parametros.put("pINS_ID", idInstitucion);
        parametros.put("pCODIGO_AT", codigoArea);
        parametros.put("pRUT", rut);
        parametros.put("pDV", dv);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall setMonedaMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("SET_MONEDA").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pMONEDA", OracleTypes.INTEGER)
                );
    }

    private SimpleJdbcCall updAreaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("UPD_AT").declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pINS_ID", OracleTypes.INTEGER),
                        new SqlParameter("pCODIGO_AT", OracleTypes.VARCHAR),
                        new SqlParameter("pRUT", OracleTypes.VARCHAR),
                        new SqlParameter("pDV", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pAT", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean updMonedaMant(String id,String codigo,String nombre,String usuario) {
        SimpleJdbcCall llamada = updMonedaMantCall();
        Map<String, Object> parametros = updMonedaMantParams(id,codigo,nombre,usuario);
        Map<String, Object> result = llamada.execute(parametros);

        JsonJTableExpenseBean moneda = new JsonJTableExpenseBean();
        moneda.setId(result.get("pMONEDA")!=null? (Integer) result.get("pMONEDA"): 0);
        moneda.setMessage((String)result.get("pMESSAGE"));
        return moneda;
    }

    private Map<String, Object> updMonedaMantParams(String id,String codigo, String nombre,String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pCODIGO", codigo);
        return parametros;
    }

    public JsonJTableExpenseBean updSector(Integer id, String nombre, String usuario) {
        SimpleJdbcCall llamada = updSectorCall();
        Map<String, Object> parametros = updSectorParams(id, nombre, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pID"));
        return jteb;
    }

    private Map<String, Object> updSectorParams(Integer id, String nombre, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID_SECTOR", id);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updMonedaMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("UPDATE_MONEDA").declareParameters(
                        new SqlParameter("pID", OracleTypes.VARCHAR),
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPERIODOS", OracleTypes.INTEGER)
                );
    }

    private SimpleJdbcCall updSectorCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("UPD_SECTOR").declareParameters(
                        new SqlParameter("pID_SECTOR", OracleTypes.INTEGER),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pID", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean delMonedaMant(String codigo, String usuario) {
        SimpleJdbcCall llamada = delMonedaMantCall();
        Map<String, Object> parametros = delMonedaMantParams(codigo,usuario);
        Map<String, Object> result = llamada.execute(parametros);

        JsonJTableExpenseBean moneda = new JsonJTableExpenseBean();
        moneda.setId(result.get("pMONEDA")!=null? (Integer) result.get("pMONEDA"): 0);
        moneda.setMessage((String)result.get("pMESSAGE"));
        return moneda;
    }

    private Map<String, Object> delMonedaMantParams(String codigo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delMonedaMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("DEL_MONEDA").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pMONEDA", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public List<JsonJTableExpenseBean> getRolesMant(String order) {
        SimpleJdbcCall llamada = getRolesMantCall();
        Map<String, Object> parametros = getRolesMantParams(order);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("pROLES_CURSOR");
    }

    private Map<String, Object> getRolesMantParams(String order) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pORDER", order);
        return parametros;
    }

    private SimpleJdbcCall getRolesMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("GET_ROLES").declareParameters(
                        new SqlParameter("pORDER", OracleTypes.VARCHAR),
                        new SqlOutParameter("pROLES_CURSOR", OracleTypes.CURSOR, new RolesMantenedorMapper())
                );
    }

    public JsonJTableExpenseBean setRolesMant(String codigo, String nombre, String usuario,String fecha) {
        SimpleJdbcCall llamada = setRolesMantCall();
        Map<String, Object> parametros = setRolesMantParams(codigo,nombre,usuario,fecha);
        Map<String, Object> result = llamada.execute(parametros);


        JsonJTableExpenseBean roles = new JsonJTableExpenseBean();
        roles.setId(result.get("pROL")!=null? (Integer) result.get("pROL"): 0);
        roles.setMessage((String)result.get("pMESSAGE"));
        return roles;

    }

    private Map<String, Object> setRolesMantParams(String codigo, String nombre, String usuario, String fecha) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        parametros.put("pFECHA", fecha);
        return parametros;
    }

    private SimpleJdbcCall setRolesMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("SET_ROLES").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlParameter("pFECHA", OracleTypes.VARCHAR),
                        new SqlOutParameter("pROL", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean updRolesMant(String id,String codigo,String nombre,String usuario) {
        SimpleJdbcCall llamada = updRolesMantCall();
        Map<String, Object> parametros = updRolesMantParams(id,codigo,nombre,usuario);
        Map<String, Object> result = llamada.execute(parametros);

        JsonJTableExpenseBean roles = new JsonJTableExpenseBean();
        roles.setId(result.get("pROLES")!=null? (Integer) result.get("pROLES"): 0);
        roles.setMessage((String)result.get("pMESSAGE"));
        return roles;
    }

    private Map<String, Object> updRolesMantParams(String id,String codigo, String nombre,String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updRolesMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("UPD_ROLES").declareParameters(
                        new SqlParameter("pID", OracleTypes.VARCHAR),
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pROLES", OracleTypes.INTEGER)
                );
    }

    //** Get Area By Id.
    public JsonJTableExpenseBean getAreaById(Integer id, String idEjercicio) {
        SimpleJdbcCall llamada = getAreaByIdCall();
        Map<String, Object> parametros = getAreaByIdParams(id, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pNOMBRE_AT")));
        return jteb;
    }

    private Map<String, Object> getAreaByIdParams(Integer id, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pEJERCICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getAreaByIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_AT_BY_ID").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pNOMBRE_AT", OracleTypes.VARCHAR)
                );
    }

    //** Get Institucion By Id.
    public JsonJTableExpenseBean getInstitucionById(Integer id, String idEjercicio) {
        SimpleJdbcCall llamada = getInstitucionByIdCall();
        Map<String, Object> parametros = getInstitucionByIdParams(id, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pNOMBRE_INSTITUCION")));
        return jteb;
    }

    private Map<String, Object> getInstitucionByIdParams(Integer id, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pEJERCICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getInstitucionByIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_INSTITUCION_BY_ID").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pNOMBRE_INSTITUCION", OracleTypes.VARCHAR)
                );
    }

    //** Get Sector By Id.
    public JsonJTableExpenseBean getSectorById(Integer id) {
        SimpleJdbcCall llamada = getSectorByIdCall();
        Map<String, Object> parametros = getSectorByIdParams(id);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setNombre(String.valueOf(result.get("pNOMBRE_SECTOR")));
        return jteb;
    }

    private Map<String, Object> getSectorByIdParams(Integer id) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        return parametros;
    }

    private SimpleJdbcCall getSectorByIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_SECTOR_BY_ID").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlOutParameter("pNOMBRE_SECTOR", OracleTypes.VARCHAR)
                );
    }

    //** Add Institucion
    public JsonJTableExpenseBean addInstitucion(String codInstitucion, String nomInstitucion, String codigoPadre,Integer sector, String usuario, Integer ejercicio) {
        SimpleJdbcCall llamada = addInstitucionCall();
        Map<String, Object> parametros = addInstitucionParams(codInstitucion, nomInstitucion, codigoPadre, sector, usuario, ejercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pID")));
        return jteb;
    }

    private Map<String, Object> addInstitucionParams(String codInstitucion, String nomInstitucion, String codigoPadre,Integer sector, String usuario, Integer ejercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codInstitucion);
        parametros.put("pNOMBRE", nomInstitucion);
        parametros.put("pCODIGO_PADRE", codigoPadre);
        parametros.put("pSECTOR", sector);
        parametros.put("pUSUARIO", usuario);
        parametros.put("pEJERCICIO", ejercicio);
        return parametros;
    }

    private SimpleJdbcCall addInstitucionCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("SET_INSTITUCION").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pCODIGO_PADRE", OracleTypes.VARCHAR),
                        new SqlParameter("pSECTOR", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pID", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean delRolesMant(String codigo, String usuario) {
        SimpleJdbcCall llamada = delRolesMantCall();
        Map<String, Object> parametros = delRolesMantParams(codigo,usuario);
        Map<String, Object> result = llamada.execute(parametros);

        JsonJTableExpenseBean roles = new JsonJTableExpenseBean();
        roles.setId(result.get("pROLES")!=null? (Integer) result.get("pROLES"): 0);
        roles.setMessage((String)result.get("pMESSAGE"));
        return roles;
    }

    private Map<String, Object> delRolesMantParams(String codigo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delRolesMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("DEL_ROLES").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pROLES", OracleTypes.INTEGER)
                );
    }

    public JsonJTableExpenseBean addArea(String codigo, String nombre, String rut, String digitoV, String codigoSector,
                                         String codigoInstitucion, Integer institucion, String usuario, Integer ejercicio) {
        SimpleJdbcCall llamada = addAreaCall();
        Map<String, Object> parametros = addAreaParams(codigo, nombre, rut, digitoV, codigoSector, codigoInstitucion, institucion, usuario, ejercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pID")));
        return jteb;
    }

    private Map<String, Object> addAreaParams(String codigo, String nombre, String rut, String digitoV, String codigoSector,
                                              String codigoInstitucion, Integer institucion, String usuario, Integer ejercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pRUT", rut);
        parametros.put("pDV", digitoV);
        parametros.put("pCODIGO_SECTOR", codigoSector);
        parametros.put("pCODIGO_INSTITUCION", codigoInstitucion);
        parametros.put("pINSTITUCION", institucion);
        parametros.put("pUSUARIO", usuario);
        parametros.put("pEJERCICIO", ejercicio);
        return parametros;
    }

    private SimpleJdbcCall addAreaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("SET_AT").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pRUT", OracleTypes.VARCHAR),
                        new SqlParameter("pDV", OracleTypes.VARCHAR),
                        new SqlParameter("pCODIGO_SECTOR", OracleTypes.VARCHAR),
                        new SqlParameter("pCODIGO_INSTITUCION", OracleTypes.VARCHAR),
                        new SqlParameter("pINSTITUCION", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pID", OracleTypes.NUMERIC),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public List<JsonJTableExpenseBean> getProyectosMant(String order) {
        SimpleJdbcCall llamada = getProyectosMantCall();
        Map<String, Object> parametros = getProyectosMantParams(order);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("pPROYECTO_CURSOR");
    }

    private Map<String, Object> getProyectosMantParams(String order) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pORDER", order);
        return parametros;
    }

    private SimpleJdbcCall getProyectosMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_PROYECTOS_BIP").declareParameters(
                        new SqlParameter("pORDER", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPROYECTO_CURSOR", OracleTypes.CURSOR, new ProyectoMantenedorMapper())
                );
    }

    public JsonJTableExpenseBean setProyectosMant(String codigo, String nombre, String usuario,Integer estado) {
        SimpleJdbcCall llamada = setProyectosMantCall();
        Map<String, Object> parametros = setProyectosMantParams(codigo,nombre,usuario,estado);
        Map<String, Object> result = llamada.execute(parametros);


        JsonJTableExpenseBean proyectos = new JsonJTableExpenseBean();
        proyectos.setId(result.get("pPROYECTO")!=null? (Integer) result.get("pPROYECTO"): 0);
        proyectos.setMessage((String)result.get("pMESSAGE"));
        return proyectos;

    }

    private Map<String, Object> setProyectosMantParams(String codigo, String nombre, String usuario, Integer estado) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        parametros.put("pESTADO", estado);
        return parametros;
    }

    private SimpleJdbcCall setProyectosMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("SET_PROYECTOS").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlParameter("pESTADO", OracleTypes.INTEGER),
                        new SqlOutParameter("pPROYECTO", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean updProyectosMant(Integer id,String codigo,String nombre,Integer estado,String usuario) {
        SimpleJdbcCall llamada = updProyectosMantCall();
        Map<String, Object> parametros = updProyectosMantParams(id,codigo,nombre,estado,usuario);
        Map<String, Object> result = llamada.execute(parametros);

        JsonJTableExpenseBean proyectos = new JsonJTableExpenseBean();
        proyectos.setId(result.get("pPROYECTO")!=null? (Integer) result.get("pPROYECTO"): 0);
        proyectos.setMessage((String)result.get("pMESSAGE"));
        return proyectos;
    }

    private Map<String, Object> updProyectosMantParams(Integer id,String codigo, String nombre,Integer estado,String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pESTADO", estado);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updProyectosMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("UPD_PROYECTOS").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pESTADO", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPROYECTO", OracleTypes.INTEGER)
                );
    }

    //** Get Codigo Sector by Id Institucion
    public JsonJTableExpenseBean getCodigoSectorByIdIns(Integer id, Integer idEjercicio) {
        SimpleJdbcCall llamada = getCodigoSectorByIdInsCall();
        Map<String, Object> parametros = getCodigoSectorByIdInsParams(id, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setCodSector(String.valueOf(result.get("pCODIGO_SEC")));
        return jteb;
    }

    private Map<String, Object> getCodigoSectorByIdInsParams(Integer id, Integer idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pEJERCICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getCodigoSectorByIdInsCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_CODIGO_SECTOR_BY_ID_INS").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pCODIGO_SEC", OracleTypes.VARCHAR)
                );
    }

    //** Get Codigo Institucion by Id
    public JsonJTableExpenseBean getCodigoInstitucionById(Integer id, String idEjercicio) {
        SimpleJdbcCall llamada = getCodigoInstitucionByIdCall();
        Map<String, Object> parametros = getCodigoInstitucionByIdParams(id, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setCodInstitucion(String.valueOf(result.get("pCODIGO_INS")));
        return jteb;
    }

    private Map<String, Object> getCodigoInstitucionByIdParams(Integer id, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pEJERCICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getCodigoInstitucionByIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_CODIGO_INSTITUCION_BY_ID").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pCODIGO_INS", OracleTypes.VARCHAR)
                );
    }

    //** Update Institucion
    public JsonJTableExpenseBean updInstitucion(Integer id, String instCodigo, Integer sectorId, String nombre, String usuario, Integer ejercicio) {
        SimpleJdbcCall llamada = updInstitucionCall();
        Map<String, Object> parametros = updInstitucionParams(id, instCodigo, sectorId, nombre, usuario, ejercicio);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setId((Integer) result.get("pINS"));
        return jteb;
    }

    private Map<String, Object> updInstitucionParams(Integer id, String instCodigo, Integer sectorId, String nombre, String usuario, Integer ejercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pINS_CODIGO", instCodigo);
        parametros.put("pSECTOR_ID", sectorId);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);
        parametros.put("pEJERCICIO", ejercicio);
        return parametros;
    }

    private SimpleJdbcCall updInstitucionCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("UPD_INSTITUCION").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlParameter("pINS_CODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pSECTOR_ID", OracleTypes.INTEGER),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlParameter("pEJERCICIO", OracleTypes.INTEGER),
                        new SqlOutParameter("pINS", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean delProyectosMant(String codigo, String usuario) {
        SimpleJdbcCall llamada = delProyectosMantCall();
        Map<String, Object> parametros = delProyectosMantParams(codigo,usuario);
        Map<String, Object> result = llamada.execute(parametros);

        JsonJTableExpenseBean proyectos = new JsonJTableExpenseBean();
        proyectos.setId(result.get("pPROYECTOS")!=null? (Integer) result.get("pPROYECTOS"): 0);
        proyectos.setMessage((String)result.get("pMESSAGE"));
        return proyectos;
    }

    private Map<String, Object> delProyectosMantParams(String codigo, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCODIGO", codigo);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delProyectosMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("DEL_PROYECTOS").declareParameters(
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pPROYECTOS", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR));
    }

    public List<OptionsJtable> getEstados() {
        SimpleJdbcCall llamada = getEstadosCall();
        Map<String, Object> result = llamada.execute(new HashMap<String, Object>());
        return (List<OptionsJtable>) result.get("pESTADOS_CURSOR");
    }

    private SimpleJdbcCall getEstadosCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_ESTADOS")
                .declareParameters(
                        new SqlOutParameter("pESTADOS_CURSOR", OracleTypes.CURSOR, new EstadoMantenedorMapper())
                );
    }

    public List<JsonJTableExpenseBean> getPeriodoEjercicioMant(Integer ejercicio,String order) {
        SimpleJdbcCall llamada = getPeriodoEjercicioCall();
        Map<String, Object> parametros = getPeriodoEjercicioParams(ejercicio,order);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("CURSOR_PERIODO");
    }

    private Map<String, Object> getPeriodoEjercicioParams(Integer ejercicio,String order) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJERCICIO", ejercicio);
        parametros.put("pORDER", order);
        return parametros;
    }

    private SimpleJdbcCall getPeriodoEjercicioCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_ENTIDAD)
                .withProcedureName("GET_TBL_PERIODO_EJERCICIO").declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMERIC),
                        new SqlParameter("pORDER", OracleTypes.VARCHAR),
                        new SqlOutParameter("CURSOR_PERIODO", OracleTypes.CURSOR, new PeriodoEjercicioMantenedorMapper())
                );
    }

    public JsonJTableExpenseBean setPeriodoEjercicioMant(Integer ejercicioId, Integer periodoId, String usuario) {
        SimpleJdbcCall llamada = setPeriodoEjercicioMantCall();
        Map<String, Object> parametros = setPeriodoEjercicioMantParams(ejercicioId,periodoId,usuario);
        Map<String, Object> result = llamada.execute(parametros);


        JsonJTableExpenseBean per = new JsonJTableExpenseBean();
        per.setId(result.get("pCODE_RESULT")!=null? (Integer) result.get("pCODE_RESULT"): 0);
        per.setMessage((String)result.get("pMESSAGE"));
        return per;

    }

    private Map<String, Object> setPeriodoEjercicioMantParams(Integer ejercicioId, Integer periodoId, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJRID", ejercicioId);
        parametros.put("pPERID", periodoId);
        parametros.put("USUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall setPeriodoEjercicioMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("SET_TBL_PERIODO_EJERCICIO").declareParameters(
                        new SqlParameter("pEJRID", OracleTypes.INTEGER),
                        new SqlParameter("pPERID", OracleTypes.INTEGER),
                        new SqlParameter("USUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCODE_RESULT", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public List<CuentaParticularPresupDTO> getCuentasParticularesPresup(Integer idEjercicio,
                                                                        Integer idPartida,
                                                                        Integer idCapitulo,
                                                                        Integer idPrograma) {
        SimpleJdbcCall call = getCuentasParticularesPresupCall();
        Map<String, Object> params = getCuentasParticularesPresupParams(idEjercicio, idPartida, idCapitulo, idPrograma);
        Map<String, Object> result = call.execute(params);
        return (List<CuentaParticularPresupDTO>) result.get(CURSOR_CUENTAS_PARTICULARES);
    }

    private Map<String, Object> getCuentasParticularesPresupParams(Integer idEjercicio,
                                                                   Integer idPartida,
                                                                   Integer idCapitulo,
                                                                   Integer idPrograma) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(P_EJR_ID, idEjercicio);
        params.put(P_ENT_PART_ID, idPartida);
        params.put(P_ENT_CAP_ID, idCapitulo);
        params.put(P_ENT_PROG_ID, idPrograma);
        return params;
    }

    private SimpleJdbcCall getCuentasParticularesPresupCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("get_cuen_presup_particular")
                .declareParameters(
                        new SqlParameter(P_EJR_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_ENT_PART_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_ENT_CAP_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_ENT_PROG_ID, OracleTypes.NUMBER),
                        new SqlOutParameter(CURSOR_CUENTAS_PARTICULARES, OracleTypes.CURSOR, new CuentaParticularPresupMapper())
                );
    }

    public void createCtaParticularPresup(CuentaParticularPresupDTO cta, String usuario) {
        SimpleJdbcCall call = createCtaParticularPresupCall();
        Map<String, Object> params = createCtaParticularPresupParams(cta, usuario);
        Map<String, Object> result = call.execute(params);
        getResult(result, P_COD, P_MSG);
    }

    private Map<String, Object> createCtaParticularPresupParams(CuentaParticularPresupDTO cta, String usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(P_ENT_PART_ID, cta.getIdPartida());
        params.put(P_ENT_CAP_ID, cta.getIdCapitulo());
        params.put(P_ENT_PROG_ID, cta.getIdPrograma());
        params.put(P_EJR_ID, cta.getIdEjercicio());
        params.put(P_CUEN_ENT_SUBTITULO, cta.getCodSubtitulo());
        params.put(P_CUEN_ENT_ITEM, cta.getCodItem());
        params.put(P_CUEN_ENT_ASIGNACION, cta.getCodAsignacion());
        params.put(P_CUEN_ENT_SUBASIGNACION, cta.getCodSubAsignacion());
        params.put(P_CUEN_ENT_NOMBRE, cta.getDescripcion().toUpperCase());
        params.put(P_USUARIO2, usuario);
        params.put(P_IMPUTACION, cta.getIsImputable());
        params.put(P_IS_DECRETO, cta.getIsDecreto());
        params.put(P_NUM_DOCUMENTO, cta.getNumeroDoc());
        params.put(P_ANIO_DOCUMENTO, cta.getAnioDocumento());
        return params;
    }

    private SimpleJdbcCall createCtaParticularPresupCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("create_cuen_presup_particular")
                .declareParameters(
                        new SqlParameter(P_ENT_PART_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_ENT_CAP_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_ENT_PROG_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_EJR_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_CUEN_ENT_SUBTITULO, OracleTypes.VARCHAR),
                        new SqlParameter(P_CUEN_ENT_ITEM, OracleTypes.VARCHAR),
                        new SqlParameter(P_CUEN_ENT_ASIGNACION, OracleTypes.VARCHAR),
                        new SqlParameter(P_CUEN_ENT_SUBASIGNACION, OracleTypes.VARCHAR),
                        new SqlParameter(P_CUEN_ENT_NOMBRE, OracleTypes.VARCHAR),
                        new SqlParameter(P_USUARIO2, OracleTypes.VARCHAR),
                        new SqlParameter(P_IMPUTACION, OracleTypes.NUMBER),
                        new SqlParameter(P_IS_DECRETO, OracleTypes.NUMBER),
                        new SqlParameter(P_NUM_DOCUMENTO, OracleTypes.NUMBER),
                        new SqlParameter(P_ANIO_DOCUMENTO, OracleTypes.NUMBER),
                        new SqlOutParameter(P_COD, OracleTypes.INTEGER),
                        new SqlOutParameter(P_MSG, OracleTypes.VARCHAR)
                );
    }

    public CuentaParticularPresupDTO getCtaParticularPresup(Integer idCuenta) {
        SimpleJdbcCall call = callGetCtaParticularPresup();
        Map<String, Object> params = paramsGetCtaParticularPresup(idCuenta);
        Map<String, Object> result = call.execute(params);
        CuentaParticularPresupDTO cta = new CuentaParticularPresupDTO();
        cta.setIdPartida((Integer) result.get(P_ENT_PART_ID));
        cta.setIdCapitulo((Integer) result.get(P_ENT_CAP_ID));
        cta.setIdPrograma((Integer) result.get(P_ENT_PROG_ID));
        cta.setIdEjercicio((Integer) result.get(P_EJR_ID));
        cta.setCodSubtitulo((String) result.get(P_CUEN_ENT_SUBTITULO));
        cta.setCodItem((String) result.get(P_CUEN_ENT_ITEM));
        cta.setCodAsignacion((String) result.get(P_CUEN_ENT_ASIGNACION));
        cta.setCodSubAsignacion((String) result.get(P_CUEN_ENT_SUBASIGNACION));
        cta.setDescripcion((String) result.get(P_CUEN_ENT_NOMBRE));
        cta.setIsImputable((Integer) result.get(P_IMPUTACION));
        cta.setIsDecreto((Integer) result.get(P_IS_DECRETO));
        cta.setNumeroDoc((Integer) result.get(P_NUM_DOCUMENTO));
        cta.setAnioDocumento((Integer) result.get(P_ANIO_DOCUMENTO));
        return cta;
    }

    private Map<String, Object> paramsGetCtaParticularPresup(Integer idCuenta) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(P_CUEN_ENT_ID, idCuenta);
        return params;
    }

    private SimpleJdbcCall callGetCtaParticularPresup() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("get_cta_particular")
                .declareParameters(
                        new SqlParameter(P_CUEN_ENT_ID, OracleTypes.NUMBER),
                        new SqlOutParameter(P_ENT_PART_ID, OracleTypes.INTEGER),
                        new SqlOutParameter(P_ENT_CAP_ID, OracleTypes.INTEGER),
                        new SqlOutParameter(P_ENT_PROG_ID, OracleTypes.INTEGER),
                        new SqlOutParameter(P_EJR_ID, OracleTypes.INTEGER),
                        new SqlOutParameter(P_CUEN_ENT_SUBTITULO, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_CUEN_ENT_ITEM, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_CUEN_ENT_ASIGNACION, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_CUEN_ENT_SUBASIGNACION, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_CUEN_ENT_NOMBRE, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_IMPUTACION, OracleTypes.INTEGER),
                        new SqlOutParameter(P_IS_DECRETO, OracleTypes.INTEGER),
                        new SqlOutParameter(P_NUM_DOCUMENTO, OracleTypes.INTEGER),
                        new SqlOutParameter(P_ANIO_DOCUMENTO, OracleTypes.INTEGER)
                );
    }

    public void updateCtaParticularPresup(CuentaParticularPresupDTO cta, String usuario) {
        SimpleJdbcCall call = callupdateCtaParticularPresup();
        Map<String, Object> params = paramsUpdateCtaParticularPresup(cta, usuario);
        Map<String, Object> result = call.execute(params);
        getResult(result, P_COD, P_MSG);
    }

    private Map<String, Object> paramsUpdateCtaParticularPresup(CuentaParticularPresupDTO cta, String usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(P_CUEN_ENT_ID, cta.getId());
        params.put(P_ENT_PART_ID, cta.getIdPartida());
        params.put(P_ENT_CAP_ID, cta.getIdCapitulo());
        params.put(P_ENT_PROG_ID, cta.getIdPrograma());
        params.put(P_EJR_ID, cta.getIdEjercicio());
        params.put(P_CUEN_ENT_SUBTITULO, cta.getCodSubtitulo());
        params.put(P_CUEN_ENT_ITEM, cta.getCodItem());
        params.put(P_CUEN_ENT_ASIGNACION, cta.getCodAsignacion());
        params.put(P_CUEN_ENT_SUBASIGNACION, cta.getCodSubAsignacion());
        params.put(P_CUEN_ENT_NOMBRE, cta.getDescripcion().toUpperCase());
        params.put(P_USUARIO2, usuario);
        params.put(P_IMPUTACION, cta.getIsImputable());
        params.put(P_IS_DECRETO, cta.getIsDecreto());
        params.put(P_NUM_DOCUMENTO, cta.getNumeroDoc());
        params.put(P_ANIO_DOCUMENTO, cta.getAnioDocumento());
        return params;
    }

    private SimpleJdbcCall callupdateCtaParticularPresup() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("update_cta_particular")
                .declareParameters(
                        new SqlParameter(P_CUEN_ENT_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_ENT_PART_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_ENT_CAP_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_ENT_PROG_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_EJR_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_CUEN_ENT_SUBTITULO, OracleTypes.VARCHAR),
                        new SqlParameter(P_CUEN_ENT_ITEM, OracleTypes.VARCHAR),
                        new SqlParameter(P_CUEN_ENT_ASIGNACION, OracleTypes.VARCHAR),
                        new SqlParameter(P_CUEN_ENT_SUBASIGNACION, OracleTypes.VARCHAR),
                        new SqlParameter(P_CUEN_ENT_NOMBRE, OracleTypes.VARCHAR),
                        new SqlParameter(P_USUARIO2, OracleTypes.VARCHAR),
                        new SqlParameter(P_IMPUTACION, OracleTypes.NUMBER),
                        new SqlParameter(P_IS_DECRETO, OracleTypes.NUMBER),
                        new SqlParameter(P_NUM_DOCUMENTO, OracleTypes.NUMBER),
                        new SqlParameter(P_ANIO_DOCUMENTO, OracleTypes.NUMBER),
                        new SqlOutParameter(P_COD, OracleTypes.INTEGER),
                        new SqlOutParameter(P_MSG, OracleTypes.VARCHAR)
                );
    }

    public List<CuentaParticularPresupDTO> getCtasParticularesDepend(Integer idCuenta) {
        SimpleJdbcCall call = callGetCtasParticularesDepend(idCuenta);
        Map<String, Object> params = paramsGetCtasParticularesDepend(idCuenta);
        Map<String, Object> result = call.execute(params);
        return (List<CuentaParticularPresupDTO>) result.get(CURSOR_CUENTAS_PARTICULARES);
    }

    private Map<String, Object> paramsGetCtasParticularesDepend(Integer idCuenta) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(P_CUEN_ENT_ID, idCuenta);
        return params;
    }

    private SimpleJdbcCall callGetCtasParticularesDepend(Integer idCuenta) {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("get_ctas_part_dependientes")
                .declareParameters(
                        new SqlParameter(P_CUEN_ENT_ID, OracleTypes.NUMBER),
                        new SqlOutParameter(CURSOR_CUENTAS_PARTICULARES, OracleTypes.CURSOR, new CtasPartDepenMapper())
                );
    }

    public void desactivarCtaParticulares(Integer idCuenta, String usuario) {
        SimpleJdbcCall call = callDesactivarCtaParticulares();
        Map<String, Object> params = paramsDesActCtaParticulares(idCuenta, usuario);
        call.execute(params);
    }

    public void activarCtaParticulares(Integer idCuenta, String usuario) {
        SimpleJdbcCall call = callActivarCtaParticulares();
        Map<String, Object> params = paramsDesActCtaParticulares(idCuenta, usuario);
        call.execute(params);
    }

    private Map<String, Object> paramsDesActCtaParticulares(Integer idCuenta, String usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(P_CUEN_ENT_ID, idCuenta);
        params.put(P_USUARIO2, usuario);
        return params;
    }

    private SimpleJdbcCall callDesactivarCtaParticulares() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("desactivar_ctas_particular")
                .declareParameters(
                        new SqlParameter(P_CUEN_ENT_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_USUARIO2, OracleTypes.VARCHAR)
                );
    }

    private SimpleJdbcCall callActivarCtaParticulares() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("activar_ctas_particular")
                .declareParameters(
                        new SqlParameter(P_CUEN_ENT_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_USUARIO2, OracleTypes.VARCHAR)
                );
    }

    public BitacoraCtaParticularDTO getRegistroBitacora(Integer idCuenta) {
        SimpleJdbcCall call = callGetRegistroBitacora();
        Map<String, Object> params = paramsGetRegistroBitacora(idCuenta);
        Map<String, Object> result = call.execute(params);
        BitacoraCtaParticularDTO bitacora = new BitacoraCtaParticularDTO();
        bitacora.setUserCreacion((String) result.get(P_CUEN_ENT_USER_CREATE));
        bitacora.setFchaCreacion((String) result.get(P_CUEN_ENT_DATE_CREATE));
        bitacora.setUserEdicion((String) result.get(P_CUEN_ENT_USER_UPDATE));
        bitacora.setFchaEdicion((String) result.get(P_CUEN_ENT_DATE_UPDATE));
        bitacora.setFchaDesactivacion((String) result.get(P_CUEN_ENT_ISVALID));
        return bitacora;
    }

    private Map<String, Object> paramsGetRegistroBitacora(Integer idCuenta) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(P_CUEN_ENT_ID, idCuenta);
        return params;
    }

    private SimpleJdbcCall callGetRegistroBitacora() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("bitacora_cta_particular")
                .declareParameters(
                        new SqlParameter(P_CUEN_ENT_ID, OracleTypes.NUMBER),
                        new SqlOutParameter(P_CUEN_ENT_DATE_CREATE, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_CUEN_ENT_USER_CREATE, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_CUEN_ENT_DATE_UPDATE, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_CUEN_ENT_USER_UPDATE, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_CUEN_ENT_ISVALID, OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean updPeriodosEjercicioMant(Integer id,Integer padreId,Integer padreId2,String usuario) {
        SimpleJdbcCall llamada = updPeriodosEjercicioMantCall();
        Map<String, Object> parametros = updPeriodosEjercicioMantParams(id,padreId,padreId2,usuario);
        Map<String, Object> result = llamada.execute(parametros);

        JsonJTableExpenseBean periodoEjer = new JsonJTableExpenseBean();
        periodoEjer.setId(result.get("pCODE_RESULT")!=null? (Integer) result.get("pCODE_RESULT"): 0);
        periodoEjer.setMessage((String)result.get("pMESSAGE"));
        return periodoEjer;
    }

    private Map<String, Object> updPeriodosEjercicioMantParams(Integer id,Integer padreId,Integer padreId2,String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pPEJRID", id);
        parametros.put("pEJRID", padreId);
        parametros.put("pPERID", padreId2);
        parametros.put("USUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updPeriodosEjercicioMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("UPD_TBL_PERIODO_EJERCICIO").declareParameters(
                        new SqlParameter("pPEJRID", OracleTypes.INTEGER),
                        new SqlParameter("pEJRID", OracleTypes.INTEGER),
                        new SqlParameter("pPERID", OracleTypes.INTEGER),
                        new SqlParameter("USUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCODE_RESULT", OracleTypes.INTEGER)
                );
    }

    //** Get Codigo Sector by Id
    public JsonJTableExpenseBean getCodigoSectorById(Integer id) {
        SimpleJdbcCall llamada = getCodigoSectorByIdCall();
        Map<String, Object> parametros = getCodigoSectorByIdParams(id);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setCodSector(String.valueOf(result.get("pCODIGO_SECTOR")));
        return jteb;
    }

    private Map<String, Object> getCodigoSectorByIdParams(Integer id) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        return parametros;
    }

    private SimpleJdbcCall getCodigoSectorByIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_CODIGO_SECTOR_BY_ID").declareParameters(
                        new SqlParameter("pID", OracleTypes.INTEGER),
                        new SqlOutParameter("pCODIGO_SECTOR", OracleTypes.VARCHAR)
                );
    }

    //** Add Sector
    public JsonJTableExpenseBean addSector(Integer ejercicio, String codigo, String nombre, String usuario) {
        SimpleJdbcCall llamada = addSectorCall();
        Map<String, Object> parametros = addSectorParams(ejercicio, codigo, nombre, usuario);
        Map<String, Object> result = llamada.execute(parametros);
        JsonJTableExpenseBean jteb = new JsonJTableExpenseBean();
        jteb.setMessage(String.valueOf(result.get("pMESSAGE")));
        jteb.setNombre(String.valueOf(result.get("pID")));
        jteb.setNombre(String.valueOf(result.get("pCODE_RESULT")));
        return jteb;
    }

    private Map<String, Object> addSectorParams(Integer ejercicio, String codigo, String nombre, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJERCICIO", ejercicio);
        parametros.put("pCODIGO", codigo);
        parametros.put("pNOMBRE", nombre);
        parametros.put("pUSUARIO", usuario);

        return parametros;
    }

    private SimpleJdbcCall addSectorCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("SET_SECTOR").declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMBER),
                        new SqlParameter("pCODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pNOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pID", OracleTypes.NUMBER),
                        new SqlOutParameter("pCODE_RESULT", OracleTypes.NUMBER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean delPeriodosEjercicioMant(Integer id, String usuario) {
        SimpleJdbcCall llamada = delPeriodosEjercicioMantCall();
        Map<String, Object> parametros = delPeriodosEjercicioMantParams(id,usuario);
        Map<String, Object> result = llamada.execute(parametros);

        JsonJTableExpenseBean periodoEjer = new JsonJTableExpenseBean();
        periodoEjer.setId(result.get("pCODE_RESULT")!=null? (Integer) result.get("pCODE_RESULT"): 0);
        periodoEjer.setMessage((String)result.get("pMESSAGE"));
        return periodoEjer;
    }

    private Map<String, Object> delPeriodosEjercicioMantParams(Integer id, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pPEJRID", id);
        parametros.put("USUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delPeriodosEjercicioMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("DEL_TBL_PERIODO_EJERCICIO").declareParameters(
                        new SqlParameter("pPEJRID", OracleTypes.INTEGER),
                        new SqlParameter("USUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCODE_RESULT", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR));
    }

    public JsonJTableExpenseBean setMigrarEjercicioMant(Integer ejercicioId, Integer ejercicioId2, String usuario) {
        SimpleJdbcCall llamada = setMigrarEjercicioMantCall();
        Map<String, Object> parametros = setMigrarEjercicioMantParams(ejercicioId,ejercicioId2,usuario);
        Map<String, Object> result = llamada.execute(parametros);


        JsonJTableExpenseBean per = new JsonJTableExpenseBean();
        per.setId(result.get("pRESULT")!=null? (Integer) result.get("pRESULT"): 0);
        per.setMessage((String)result.get("pMENSAJE"));
        return per;

    }

    private Map<String, Object> setMigrarEjercicioMantParams(Integer ejercicioId, Integer ejercicioId2, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pORIGEN", ejercicioId);
        parametros.put("pDESTINO", ejercicioId2);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall setMigrarEjercicioMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_PLAN_DE_CUENTAS)
                .withProcedureName("SET_MIGRACION_EJERCICIO").declareParameters(
                        new SqlParameter("pORIGEN", OracleTypes.INTEGER),
                        new SqlParameter("pDESTINO", OracleTypes.INTEGER),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pRESULT", OracleTypes.INTEGER),
                        new SqlOutParameter("pMENSAJE", OracleTypes.VARCHAR)
                );
    }

    public List<JsonJTableExpenseBean> getSaldoInicialMant(String order) {
        SimpleJdbcCall llamada = getSaldoInicialCall();
        Map<String, Object> parametros = getSaldoInicialParams(order);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<JsonJTableExpenseBean>) result.get("pSALDO_INICIAL_CURSOR");
    }

    private Map<String, Object> getSaldoInicialParams(String order) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pORDER", order);
        return parametros;
    }

    private SimpleJdbcCall getSaldoInicialCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_SALDO_INICIAL").declareParameters(
                        new SqlParameter("pORDER", OracleTypes.VARCHAR),
                        new SqlOutParameter("pSALDO_INICIAL_CURSOR", OracleTypes.CURSOR, new SaldoInicialMantenedorMapper())
                );
    }

    public JsonJTableExpenseBean setSaldoInicialMant(Integer ejercicio,String moneda,String codPartida,String codCapitulo,String codPrograma,String cuentaAgregacion,String cuentaN1,String cuentaN2,String cuentaN3,String saldoDeudor,String saldoAcreedor,String usuario) {
        SimpleJdbcCall llamada = setSaldoInicialMantCall();
        Map<String, Object> parametros = setSaldoInicialMantParams(ejercicio,moneda,codPartida,codCapitulo,codPrograma, cuentaAgregacion,cuentaN1,cuentaN2,cuentaN3,saldoDeudor,saldoAcreedor,usuario);
        Map<String, Object> result = llamada.execute(parametros);


        JsonJTableExpenseBean saldo = new JsonJTableExpenseBean();
        saldo.setId(result.get("pID")!=null? (Integer) result.get("pID"): 0);
        saldo.setMessage((String)result.get("pMESSAGE"));
        return saldo;

    }

    private Map<String, Object> setSaldoInicialMantParams(Integer ejercicio,String moneda,String codPartida,String codCapitulo,String codPrograma,String cuentaAgregacion,String cuentaN1,String cuentaN2,String cuentaN3,String saldoDeudor,String saldoAcreedor,String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCOD_MONEDA", moneda);
        parametros.put("pDEUDOR", saldoDeudor);
        parametros.put("pACREEDOR", saldoAcreedor);
        parametros.put("pEJERCICIO", ejercicio);
        parametros.put("pCOD_PART", codPartida);
        parametros.put("pCOD_CAP", codCapitulo);
        parametros.put("pCOD_PROG", codPrograma);
        parametros.put("pAGREGACION", cuentaAgregacion);
        parametros.put("pCTAN1", cuentaN1);
        parametros.put("pCTAN2", cuentaN2);
        parametros.put("pCTAN3", cuentaN3);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall setSaldoInicialMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("SET_SALDO_INICIAL").declareParameters(
                        new SqlParameter("pCOD_MONEDA", OracleTypes.VARCHAR),
                        new SqlParameter("pDEUDOR", OracleTypes.NUMERIC),
                        new SqlParameter("pACREEDOR", OracleTypes.NUMERIC),
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMERIC),
                        new SqlParameter("pCOD_PART", OracleTypes.VARCHAR),
                        new SqlParameter("pCOD_CAP", OracleTypes.VARCHAR),
                        new SqlParameter("pCOD_PROG", OracleTypes.VARCHAR),
                        new SqlParameter("pAGREGACION", OracleTypes.VARCHAR),
                        new SqlParameter("pCTAN1", OracleTypes.VARCHAR),
                        new SqlParameter("pCTAN2", OracleTypes.VARCHAR),
                        new SqlParameter("pCTAN3", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pID", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean updSaldoInicialMant(Integer id,Integer ejercicio,String moneda,String codPartida,String codCapitulo,String codPrograma,String cuentaAgregacion,String cuentaN1,String cuentaN2,String cuentaN3,String saldoDeudor,String saldoAcreedor,String usuario) {
        SimpleJdbcCall llamada = updSaldoInicialMantCall();
        Map<String, Object> parametros = updSaldoInicialMantParams(id,ejercicio,moneda,codPartida,codCapitulo,codPrograma,cuentaAgregacion,cuentaN1,cuentaN2,cuentaN3,saldoDeudor,saldoAcreedor,usuario);
        Map<String, Object> result = llamada.execute(parametros);

        JsonJTableExpenseBean saldo = new JsonJTableExpenseBean();
        saldo.setId(result.get("pCOD")!=null? (Integer) result.get("pCOD"): 0);
        saldo.setMessage((String)result.get("pMESSAGE"));
        return saldo;
    }

    private Map<String, Object> updSaldoInicialMantParams(Integer id,Integer ejercicio,String moneda,String codPartida,String codCapitulo,String codPrograma,String cuentaAgregacion,String cuentaN1,String cuentaN2,String cuentaN3,String saldoDeudor,String saldoAcreedor,String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pDEUDOR", saldoDeudor);
        parametros.put("pACREEDOR", saldoAcreedor);
        parametros.put("pEJERCICIO", ejercicio);
        parametros.put("pCOD_PART", codPartida);
        parametros.put("pCOD_CAP", codCapitulo);
        parametros.put("pCOD_PROG", codPrograma);
        parametros.put("pCOD_MONEDA", moneda);
        parametros.put("pAGREGACION", cuentaAgregacion);
        parametros.put("pCTAN1", cuentaN1);
        parametros.put("pCTAN2", cuentaN2);
        parametros.put("pCTAN3", cuentaN3);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall updSaldoInicialMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("UPD_SALDO_INICIAL").declareParameters(
                        new SqlParameter("pID", OracleTypes.VARCHAR),
                        new SqlParameter("pDEUDOR", OracleTypes.NUMERIC),
                        new SqlParameter("pACREEDOR", OracleTypes.NUMERIC),
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMERIC),
                        new SqlParameter("pCOD_PART", OracleTypes.VARCHAR),
                        new SqlParameter("pCOD_CAP", OracleTypes.VARCHAR),
                        new SqlParameter("pCOD_PROG", OracleTypes.VARCHAR),
                        new SqlParameter("pCOD_MONEDA", OracleTypes.VARCHAR),
                        new SqlParameter("pAGREGACION", OracleTypes.VARCHAR),
                        new SqlParameter("pCTAN1", OracleTypes.VARCHAR),
                        new SqlParameter("pCTAN2", OracleTypes.VARCHAR),
                        new SqlParameter("pCTAN3", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public JsonJTableExpenseBean delSaldoInicialMant(Integer id, String usuario) {
        SimpleJdbcCall llamada = delSaldoInicialMantCall();
        Map<String, Object> parametros = delSaldoInicialMantParams(id,usuario);
        Map<String, Object> result = llamada.execute(parametros);

        JsonJTableExpenseBean saldo = new JsonJTableExpenseBean();
        saldo.setId(result.get("pCODE_RESULT")!=null? (Integer) result.get("pCODE_RESULT"): 0);
        saldo.setMessage((String)result.get("pMESSAGE"));
        return saldo;
    }

    private Map<String, Object> delSaldoInicialMantParams(Integer id, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", id);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall delSaldoInicialMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("DEL_SALDO_INICIAL").declareParameters(
                        new SqlParameter("pID", OracleTypes.VARCHAR),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR));
    }

    public List<ErroresSaldoInicial> getErroresSaldoInicialMant() {
        SimpleJdbcCall llamada = getErroresSaldoInicialCall();
        Map<String, Object> result = llamada.execute();
        return (List<ErroresSaldoInicial>) result.get("OUT_CURSOR");
    }

    private SimpleJdbcCall getErroresSaldoInicialCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SALDOS_CERO)
                .withProcedureName("GET_SALDO_CERO_ERROR").declareParameters(
                        new SqlOutParameter("OUT_CURSOR", OracleTypes.CURSOR, new ErroresSaldoInicialMapper())
                );
    }

    public void deleteSaldosCero() {
        SimpleJdbcCall llamada = deleteSaldosCeroMantCall();
        Map<String, Object> result = llamada.execute();
    }

    private SimpleJdbcCall deleteSaldosCeroMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SALDOS_CERO)
                .withProcedureName("DEL_SALDOS_CERO");
    }

    public void insertarSaldosCero(String anio, String moneda, String codPartida,String codCapitulo,String codPrograma, String ctaAgregacionMasN1, String saldoDeudor,String saldoAcreedor, String usuario) {
        SimpleJdbcCall llamada = insertarSaldosCeroMantCall();
        Map<String, Object> parametros = insertarSaldosCeroMantParams(anio,moneda,codPartida,codCapitulo,codPrograma,ctaAgregacionMasN1, saldoDeudor,saldoAcreedor, usuario);
        Map<String, Object> result = llamada.execute(parametros);
    }

    private Map<String, Object> insertarSaldosCeroMantParams(String anio, String moneda, String codPartida,String codCapitulo,String codPrograma, String ctaAgregacionMasN1, String saldoDeudor,String saldoAcreedor, String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID_EJERCICIO", anio);
        parametros.put("pMON_ID", moneda);
        parametros.put("pENT_PART_ID", codPartida);
        parametros.put("pENT_CAP_ID", codCapitulo);
        parametros.put("pENT_PROG_ID", codPrograma);
        parametros.put("pCTA_AGREGACION", ctaAgregacionMasN1);
        parametros.put("pSALDO_DEUDOR", saldoDeudor);
        parametros.put("pSALDOS_USER_CREATE",usuario );
        parametros.put("pSALDO_ACREEDOR", saldoAcreedor);
        return parametros;
    }

    private SimpleJdbcCall insertarSaldosCeroMantCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SALDOS_CERO)
                .withProcedureName("INS_SALDOS_CERO").declareParameters(
                        new SqlParameter("pID_EJERCICIO", OracleTypes.VARCHAR),
                        new SqlParameter("pMON_ID", OracleTypes.VARCHAR),
                        new SqlParameter("pENT_PART_ID", OracleTypes.VARCHAR),
                        new SqlParameter("pENT_CAP_ID", OracleTypes.VARCHAR),
                        new SqlParameter("pENT_PROG_ID", OracleTypes.VARCHAR),
                        new SqlParameter("pCTA_AGREGACION", OracleTypes.VARCHAR),
                        new SqlParameter("pSALDO_DEUDOR", OracleTypes.VARCHAR),
                        new SqlParameter("pSALDOS_USER_CREATE", OracleTypes.VARCHAR),
                        new SqlParameter("pSALDO_ACREEDOR", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCODE_RESULT", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR)
                );
    }

    public List<LogCarga> getLogCarga() {
        SimpleJdbcCall llamada =getLogCargaCall();
        Map<String, Object> result = llamada.execute();
        return (List<LogCarga>) result.get("OUT_CURSOR");
    }

    private SimpleJdbcCall getLogCargaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SALDOS_CERO)
                .withProcedureName("GET_LOG_CARGA").declareParameters(
                        new SqlOutParameter("OUT_CURSOR", OracleTypes.CURSOR, new LogCargaMapper())
                );
    }

    //** Registro Bitacora Area
    public Bitacora getRegistroBitacoraAT(Integer pid) {
        SimpleJdbcCall llamada = getRegistroBitacoraATCall();
        Map<String, Object> parametros = getRegistroBitacoraATParams(pid);
        Map<String, Object> result = llamada.execute(parametros);
        List<Bitacora> bitacoras = (List<Bitacora>) result.get("ATCURSOR");
        return bitacoras.get(bitacoras.size() -1);
    }

    private Map<String, Object> getRegistroBitacoraATParams(Integer pid) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", pid);
        return parametros;
    }

    private SimpleJdbcCall getRegistroBitacoraATCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_BITACORA_AT").declareParameters(
                        new SqlParameter("pID", OracleTypes.NUMBER),
                        new SqlOutParameter("ATCURSOR", OracleTypes.CURSOR, new BitacoraBOMapper())
                );
    }

    //** Registro bitacora institucion
    public Bitacora getRegistroBitacoraInstitucion(Integer pid) {
        SimpleJdbcCall llamada = getRegistroBitacoraInstitucionCall();
        Map<String, Object> parametros = getRegistroBitacoraInstitucionParams(pid);
        Map<String, Object> result = llamada.execute(parametros);
        List<Bitacora> bitacoras = (List<Bitacora>) result.get("ICURSOR");
        return bitacoras.get(bitacoras.size() -1);
    }

    private Map<String, Object> getRegistroBitacoraInstitucionParams(Integer pid) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", pid);
        return parametros;
    }

    private SimpleJdbcCall getRegistroBitacoraInstitucionCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_BITACORA_INSTITUCION").declareParameters(
                        new SqlParameter("pID", OracleTypes.NUMBER),
                        new SqlOutParameter("ICURSOR", OracleTypes.CURSOR, new BitacoraBOMapper())
                );
    }

    //** Registro Bitacora Sector
    public Bitacora getRegistroBitacoraSector(Integer pid) {
        SimpleJdbcCall llamada = getRegistroBitacoraSectorCall();
        Map<String, Object> parametros = getRegistroBitacoraSectorParams(pid);
        Map<String, Object> result = llamada.execute(parametros);
        List<Bitacora> bitacoras = (List<Bitacora>) result.get("SCURSOR");
        return bitacoras.get(bitacoras.size() -1);
    }

    private Map<String, Object> getRegistroBitacoraSectorParams(Integer pid) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pID", pid);
        return parametros;
    }

    private SimpleJdbcCall getRegistroBitacoraSectorCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_BITACORA_SECTOR").declareParameters(
                        new SqlParameter("pID", OracleTypes.NUMBER),
                        new SqlOutParameter("SCURSOR", OracleTypes.CURSOR, new BitacoraBOMapper())
                );
    }

    //** AT
    public List<AreaTransaccionalDTO> getAT(Integer ejercicio, String sector, String institucion) {
        SimpleJdbcCall llamada = getATCall();
        Map<String, Object> parametros = getATParams(ejercicio, sector, institucion);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<AreaTransaccionalDTO>) result.get("pAT_CURSOR");
    }

    private Map<String, Object> getATParams(Integer ejercicio, String sector, String institucion) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pSECTOR", sector);
        parametros.put("pEJERCICIO", ejercicio);
        parametros.put("pINSTITUCION", institucion);
        return parametros;
    }

    private SimpleJdbcCall getATCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_AT").declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMBER),
                        new SqlParameter("pSECTOR", OracleTypes.NUMBER),
                        new SqlParameter("pINSTITUCION", OracleTypes.NUMBER),
                        new SqlOutParameter("pAT_CURSOR", OracleTypes.CURSOR, new ATMapper())
                );
    }

    //** Institucion
    public List<AreaTransaccionalDTO> getInstitucion(Integer ejercicioId, Integer codSector) {
        SimpleJdbcCall llamada = getInstitucionCall();
        Map<String, Object> parametros = getInstitucionParams(ejercicioId, codSector);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<AreaTransaccionalDTO>) result.get("pINSTITUCION_CURSOR");
    }

    private Map<String, Object> getInstitucionParams(Integer ejercicioId, Integer codSector) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pSECTOR", codSector);
        parametros.put("pEJERCICIO", ejercicioId);
        return parametros;
    }

    private SimpleJdbcCall getInstitucionCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("OBTENER_INSTITUCION_SECTOR").declareParameters(
                        new SqlParameter("pSECTOR", OracleTypes.NUMBER),
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMBER),
                        new SqlOutParameter("pINSTITUCION_CURSOR", OracleTypes.CURSOR, new InstitucionMapper())
                );
    }

    //** Sector
    public List<AreaTransaccionalDTO> getSector(Integer ejercicioId) {
        SimpleJdbcCall llamada = getSectorCall();
        Map<String, Object> parametros = getSectorParams(ejercicioId);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<AreaTransaccionalDTO>) result.get("pSECTOR_CURSOR");
    }

    private Map<String, Object> getSectorParams(Integer ejercicioId) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJERCICIO", ejercicioId);
        return parametros;
    }

    private SimpleJdbcCall getSectorCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("OBTENER_SECTOR_EJERCICIO").declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMBER),
                        new SqlOutParameter("pSECTOR_CURSOR", OracleTypes.CURSOR, new SectorEjercicioMapper())
                );
    }

    public BitacoraCtaContableBO getRegistroBitacora(String idEjercicio,
                                                      String idTabla1,
                                                      String idTabla2) {
        SimpleJdbcCall call = callGetRegistroBitacoraCtaContable();
        Map<String, Object> params = paramsGetRegistroBitacoraCtaContable(idEjercicio, idTabla1, idTabla2);
        Map<String, Object> result = call.execute(params);
        List<BitacoraCtaContableBO> resultList = (List<BitacoraCtaContableBO>) result.get("pCursorBitacora");
        return resultList.get(0);
    }

    private Map<String, Object> paramsGetRegistroBitacoraCtaContable(String idEjercicio, String idTabla1, String idTabla2) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pEJERCICIO", idEjercicio);
        params.put("idTabla1", idTabla1);
        params.put("idTabla2", idTabla2);
        return params;
    }

    private SimpleJdbcCall callGetRegistroBitacoraCtaContable() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("GET_BITACORA")
                .declareParameters(
                        new SqlParameter("idTabla1", OracleTypes.VARCHAR),
                        new SqlParameter("idTabla2", OracleTypes.VARCHAR),
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMBER),
                        new SqlOutParameter("pCursorBitacora", OracleTypes.CURSOR, new BitacoraCtaContableMapper()),
                        new SqlOutParameter("pCOD", OracleTypes.NUMBER),
                        new SqlOutParameter("result_msg", OracleTypes.VARCHAR)
                );
    }

}
