package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.exceptions.SicogenException;
import cl.contraloria.sicogen.mappers.*;
import cl.contraloria.sicogen.model.*;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DigitacionMPDAO extends BaseDAO {

    private static final String PKG_DIGITACION = "PKG_DIGITACION";
    private static final String COD = "pCOD";
    private static final String MESSAGE = "pMESSAGE";
    private static final String ENT_PART_CODIGO = "pENT_PART_CODIGO";
    private static final String ENT_CAP_CODIGO = "pENT_CAP_CODIGO";
    private static final String ENT_PROG_CODIGO = "pENT_PROG_CODIGO";
    private static final String EJER_ID = "pEJER_ID";
    private static final String NRO_DOCUMENTO = "pNRO_DOCUMENTO";
    private static final String PERIODO = "pPERIODO";
    private static final String TIPODOCUMENTO = "pTIPODOCUMENTO";
    private static final String CUEN_ENT_ID = "pCUEN_ENT_ID";
    private static final String EJR_ID = "pEJR_ID";
    private static final String TDRMP_ID = "pTDRMP_ID";
    private static final String MONEDA_DES = "pMONEDA_DES";
    private static final String MONTO = "pMONTO";
    private static final String DET_PRESUP_ID = "pDET_PRESUP_ID";
    private static final String P_TDRMP_ID = "p_tdrmp_id";
    private static final String P_DET_TDRMP_ID = "p_det_tdrmp_id";
    private static final String P_GLO_TDRMP_COD_PART = "p_glo_tdrmp_cod_part";
    private static final String P_GLO_TDRMP_COD_CAP = "p_glo_tdrmp_cod_cap";
    private static final String P_GLO_TDRMP_COD_PROG = "p_glo_tdrmp_cod_prog";
    private static final String P_GLO_TDRMP_NOM_PART = "p_glo_tdrmp_nom_part";
    private static final String P_GLO_TDRMP_NOM_CAP = "p_glo_tdrmp_nom_cap";
    private static final String P_GLO_TDRMP_NOM_PROG = "p_glo_tdrmp_nom_prog";
    private static final String P_GLO_TDRMP_ID = "p_glo_tdrmp_id";
    private static final String P_GLO_TDRMP_NUM = "p_glo_tdrmp_num";
    private static final String P_GLO_TDRMP_TEXTO = "p_glo_tdrmp_texto";
    private JdbcTemplate jdbcTemplate;

    public DigitacionMPDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<DigitacionMPBO> buscarMPDigitacionTDR(int idEjercicio,
                                                      int numDoc,
                                                      int idPeriodo,
                                                      String tipoDocumento) {
        SimpleJdbcCall llamada = buscarMPDigitacionTDRCall();
        Map<String, Object> parametros = buscarMPDigitacionParams(idEjercicio, numDoc, idPeriodo, tipoDocumento);
        Map<String, Object> respuesta = llamada.execute(parametros);
        Integer codEjec = (Integer) respuesta.get(COD);

        if (codEjec == 0) {
            return (List<DigitacionMPBO>) respuesta.get("CURSOR_PRESU");
        } else {
            String msgError = (String) respuesta.get(MESSAGE);
            throw new SicogenException(codEjec, msgError);
        }

    }

    private Map<String, Object> buscarMPDigitacionParams(int idEjercicio, int numDoc, int idPeriodo, String tipoDocumento) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(ENT_PART_CODIGO, "0");
        parametros.put(ENT_CAP_CODIGO, "0");
        parametros.put(ENT_PROG_CODIGO, "0");
        parametros.put(EJER_ID, idEjercicio);
        parametros.put(NRO_DOCUMENTO, numDoc);
        parametros.put(PERIODO, idPeriodo);
        parametros.put(TIPODOCUMENTO, tipoDocumento);
        return parametros;
    }

    private SimpleJdbcCall buscarMPDigitacionTDRCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("GET_TDR_MP").declareParameters(
                        new SqlParameter(ENT_PART_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter(ENT_CAP_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter(ENT_PROG_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter(EJER_ID, OracleTypes.NUMERIC),
                        new SqlParameter(NRO_DOCUMENTO, OracleTypes.NUMERIC),
                        new SqlParameter(PERIODO, OracleTypes.NUMERIC),
                        new SqlParameter(TIPODOCUMENTO, OracleTypes.VARCHAR),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR),
                        new SqlOutParameter("CURSOR_PRESU", OracleTypes.CURSOR, new DigitacionMPMapper())
                );
    }

    public int getIdEjercicio(int idEjercicio) {
        SimpleJdbcCall llamada = getIdEjercicioCall();
        Map<String, Object> parametros = getIdEjercicioParams(idEjercicio);
        Map<String, Object> respuesta = llamada.execute(parametros);
        Integer codEjec = (Integer) respuesta.get(COD);

        if (codEjec == 0) {
            List<EjerciciosDTO> result = (List<EjerciciosDTO>) respuesta.get("CURSOR_ID_EJR");
            return result.get(0).getEjercicioId();
        } else {
            String msgError = (String) respuesta.get(MESSAGE);
            throw new SicogenException(codEjec, msgError);
        }
    }

    private Map<String, Object> getIdEjercicioParams(int idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJR", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getIdEjercicioCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("GET_ID_EJERCICIO").declareParameters(
                        new SqlParameter("pEJR", OracleTypes.NUMERIC),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR),
                        new SqlOutParameter("CURSOR_ID_EJR", OracleTypes.CURSOR, new EjercicioDigitacionMapper())
                );
    }

    public void insertarMPDigitacionTDR(String idPartida,
                                        String idCapitulo,
                                        String idPrograma,
                                        int idEjercicio,
                                        int numDoc,
                                        int idPeriodo,
                                        String tipoDoc) {
        SimpleJdbcCall llamada = insertarMPDigitacionTDRCall();
        Map<String, Object> parametros = insertarMPDigitacionTDRParams(idPartida,
                                                                       idCapitulo,
                                                                       idPrograma,
                                                                       idEjercicio,
                                                                       numDoc,
                                                                       idPeriodo,
                                                                       tipoDoc);
        Map<String, Object> respuesta = llamada.execute(parametros);
        Integer codEjec = (Integer) respuesta.get(COD);

        if (codEjec == -1) {
            String msgErr = (String) respuesta.get(MESSAGE);
            throw new SicogenException(codEjec, msgErr);
        }
    }

    private Map<String, Object> insertarMPDigitacionTDRParams(String idPartida,
                                                              String idCapitulo,
                                                              String idPrograma,
                                                              int idEjercicio,
                                                              int numDoc,
                                                              int idPeriodo,
                                                              String tipoDoc) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(EJER_ID, idEjercicio);
        parametros.put(ENT_PART_CODIGO, idPartida);
        parametros.put(ENT_CAP_CODIGO, idCapitulo);
        parametros.put(ENT_PROG_CODIGO, idPrograma);
        parametros.put(NRO_DOCUMENTO, numDoc);
        parametros.put(PERIODO, idPeriodo);
        parametros.put(TIPODOCUMENTO, tipoDoc);
        return parametros;
    }

    private SimpleJdbcCall insertarMPDigitacionTDRCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("SET_TDR_MP")
                .declareParameters(
                        new SqlParameter(EJER_ID, OracleTypes.NUMERIC),
                        new SqlParameter(ENT_PART_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter(ENT_CAP_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter(ENT_PROG_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter(NRO_DOCUMENTO, OracleTypes.NUMERIC),
                        new SqlParameter(PERIODO, OracleTypes.NUMERIC),
                        new SqlParameter(TIPODOCUMENTO, OracleTypes.VARCHAR),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public int getIdCapitulo(String codPartida, int idPartida) {
        SimpleJdbcCall llamada = getIdCapituloCall();
        Map<String, Object> parametros = getIdCapituloParams(codPartida, idPartida);
        Map<String, Object> respuesta = llamada.execute(parametros);
        List<Integer> result = (List<Integer>) respuesta.get("CURSOR_COD_CAPITULO");
        return result.get(0);
    }

    private Map<String, Object> getIdCapituloParams(String codPartida, int idPartida) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCOD_CAPITULO", codPartida);
        parametros.put("pENT_PART_ID", idPartida);
        return parametros;
    }

    private SimpleJdbcCall getIdCapituloCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("RECUPERA_ID_CAPITULO_SP")
                .declareParameters(
                        new SqlParameter("pCOD_CAPITULO", OracleTypes.VARCHAR),
                        new SqlParameter("pENT_PART_ID", OracleTypes.NUMERIC),
                        new SqlOutParameter("CURSOR_COD_CAPITULO", OracleTypes.CURSOR, new CapituloIdMapper())
                );
    }

    public int getIdPartida(String codPartida) {
        SimpleJdbcCall llamada = getIdPartidaCall();
        Map<String, Object> parametros = getIdPartidaParams(codPartida);
        Map<String, Object> respuesta = llamada.execute(parametros);
        List<Integer> result = (List<Integer>) respuesta.get("CURSOR_COD_PARTIDA");
        return result.get(0);
    }

    private Map<String, Object> getIdPartidaParams(String codPartida) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCOD_PARTIDA", codPartida);
        return parametros;
    }

    private SimpleJdbcCall getIdPartidaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("RECUPERA_ID_PARTIDA_SP")
                .declareParameters(
                        new SqlParameter("pCOD_PARTIDA", OracleTypes.VARCHAR),
                        new SqlOutParameter("CURSOR_COD_PARTIDA", OracleTypes.CURSOR, new PartidaIdMapper())
                );
    }

    public void deleteMPDigitacionTDR(int idFila) {
        SimpleJdbcCall llamada = deleteMPDigitacionTDRCall();
        Map<String, Object> parametros = deleteMPDigitacionTDRParams(idFila);
        Map<String, Object> resultMap = llamada.execute(parametros);
        getResult(resultMap);
    }

    private Map<String, Object> deleteMPDigitacionTDRParams(int idFila) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pPRESUP_ID", idFila);
        return parametros;
    }

    private SimpleJdbcCall deleteMPDigitacionTDRCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("DEL_MOD_PRE")
                .declareParameters(
                        new SqlParameter("pPRESUP_ID", OracleTypes.NUMERIC),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public CuentaPresupValidacion getDescripcionCuenta(String codCuenta, int idPrograma, int idEjercicio) {
        SimpleJdbcCall llamada = getDescripcionCuentaCall();
        Map<String, Object> parametros = getDescripcionCuentaParams(codCuenta, idPrograma, idEjercicio);
        Map<String, Object> respuesta = llamada.execute(parametros);

        CuentaPresupValidacion cuentaPresupValidacion = new CuentaPresupValidacion();
        cuentaPresupValidacion.setCodEjec((Integer) respuesta.get(COD));
        cuentaPresupValidacion.setMsgEjec((String) respuesta.get(MESSAGE));
        cuentaPresupValidacion.setFlagHabilitado((Integer) respuesta.get("pFLAG_HABILITADO"));
        cuentaPresupValidacion.setNombreCuenta((String) respuesta.get("pCTASPRESUP_NOMBRE"));
        cuentaPresupValidacion.setIdCuenEnt((Integer) respuesta.get(CUEN_ENT_ID));

        return cuentaPresupValidacion;
    }

    private Map<String, Object> getDescripcionCuentaParams(String codCuenta, int idPrograma, int idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pCTASPRESUP_CODIGO", codCuenta);
        parametros.put("pENT_PROG_ID", idPrograma);
        parametros.put(EJR_ID, idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getDescripcionCuentaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("GET_NOMBRE_CUENTA_PRESUP_v2")
                .declareParameters(
                        new SqlParameter("pCTASPRESUP_CODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pENT_PROG_ID", OracleTypes.NUMERIC),
                        new SqlParameter(EJR_ID, OracleTypes.NUMERIC),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR),
                        new SqlOutParameter("pCTASPRESUP_NOMBRE", OracleTypes.VARCHAR),
                        new SqlOutParameter(CUEN_ENT_ID, OracleTypes.INTEGER),
                        new SqlOutParameter("pFLAG_HABILITADO", OracleTypes.INTEGER)
                );
    }

    public Integer createCuentaParticularEntidad(Integer ejercicioId,
                                                 String codPartida,
                                                 String codCapitulo,
                                                 String codPrograma,
                                                 String codCuenta,
                                                 String nomCuenta,
                                                 Integer idCuentaGen,
                                                 Integer idDocumento,
                                                 String usuario) {
        SimpleJdbcCall llamada = createCuentaPartilcuarEntidadCall();
        Map<String, Object> parametros = createCuentaPartilcuarEntidadParams(ejercicioId, codPartida, codCapitulo, codPrograma, codCuenta, nomCuenta, idCuentaGen, idDocumento, usuario);
        Map<String, Object> resultMap = llamada.execute(parametros);
        getResult(resultMap);
        return (Integer) resultMap.get(CUEN_ENT_ID);
    }

    private Map<String, Object> createCuentaPartilcuarEntidadParams(Integer ejercicioId,
                                                                    String codPartida,
                                                                    String codCapitulo,
                                                                    String codPrograma,
                                                                    String codCuenta,
                                                                    String nomCuenta,
                                                                    Integer idCuentaGen,
                                                                    Integer idDocumento,
                                                                    String usuario) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(EJR_ID, ejercicioId);
        parametros.put(ENT_PART_CODIGO, codPartida);
        parametros.put(ENT_CAP_CODIGO, codCapitulo);
        parametros.put(ENT_PROG_CODIGO, codPrograma);
        parametros.put("pCTAPRESUP_CODIGO", codCuenta);
        parametros.put("pCTAPRESUP_NOMBRE", nomCuenta);
        parametros.put("pCTAPRESUP_ID", idCuentaGen);
        parametros.put("pSIS_DOCUMENTO_ID", idDocumento);
        parametros.put("pUSUARIO", usuario);
        return parametros;
    }

    private SimpleJdbcCall createCuentaPartilcuarEntidadCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("SET_CUENTA_PRESUP_ENT")
                .declareParameters(
                        new SqlParameter(EJR_ID, OracleTypes.NUMERIC),
                        new SqlParameter(ENT_PART_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter(ENT_CAP_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter(ENT_PROG_CODIGO, OracleTypes.VARCHAR),
                        new SqlParameter("pCTAPRESUP_CODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pCTAPRESUP_NOMBRE", OracleTypes.VARCHAR),
                        new SqlParameter("pCTAPRESUP_ID", OracleTypes.NUMERIC),
                        new SqlParameter("pSIS_DOCUMENTO_ID", OracleTypes.NUMERIC),
                        new SqlParameter("pUSUARIO", OracleTypes.VARCHAR),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR),
                        new SqlOutParameter(CUEN_ENT_ID, OracleTypes.INTEGER)
                );
    }

    public List<DetTDRMPDTO> getListDetTDRMP(Integer idTDRMP, Integer idEjercicio) {
        SimpleJdbcCall llamada = getListDetTDRMPCall();
        Map<String, Object> parametros = getListDetTDRMPParams(idTDRMP, idEjercicio);
        Map<String, Object> resultado = llamada.execute(parametros);
        getResult(resultado);
        return (List<DetTDRMPDTO>) resultado.get("CURSOR_DETALLE_TDR_MP");
    }

    private Map<String, Object> getListDetTDRMPParams(Integer idTDRMP, Integer idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pTDR_MP_ID", idTDRMP);
        parametros.put(EJER_ID, idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getListDetTDRMPCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("GET_DET_TDR_MP_INICIAL_v2").declareParameters(
                        new SqlParameter("pTDR_MP_ID", OracleTypes.NUMERIC),
                        new SqlParameter(EJER_ID, OracleTypes.NUMERIC),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR),
                        new SqlOutParameter("CURSOR_DETALLE_TDR_MP", OracleTypes.CURSOR, new DetTDRMPMapper())
                );
    }

    public void insertarAumentoTDRMP(Integer idTDRMPR,
                                     Integer idCuenEnt,
                                     String desMoneda,
                                     Integer monto) {
        SimpleJdbcCall llamada = insertarAumentoTDRMPCall();
        Map<String, Object> parametros = insertarTDRMPParams(idTDRMPR, idCuenEnt, desMoneda, monto);
        Map<String, Object> resultado = llamada.execute(parametros);
        getResult(resultado);
    }

    private Map<String, Object> insertarTDRMPParams(Integer idTDRMPR,
                                                    Integer idCuenEnt,
                                                    String desMoneda,
                                                    Integer monto) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(TDRMP_ID, idTDRMPR);
        parametros.put(CUEN_ENT_ID, idCuenEnt);
        parametros.put(MONEDA_DES, desMoneda);
        parametros.put(MONTO, monto);
        return parametros;
    }

    private SimpleJdbcCall insertarAumentoTDRMPCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("SET_AUMENTO_MP_v2").declareParameters(
                        new SqlParameter(TDRMP_ID, OracleTypes.NUMBER),
                        new SqlParameter(CUEN_ENT_ID, OracleTypes.NUMBER),
                        new SqlParameter(MONEDA_DES, OracleTypes.VARCHAR),
                        new SqlParameter(MONTO, OracleTypes.NUMBER),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public void insertarDisminucionTDRMP(Integer idTDRMPR,
                                         Integer idCuenEnt,
                                         String desMoneda,
                                         Integer monto) {
        SimpleJdbcCall llamada = insertarDisminucionTDRMPCall();
        Map<String, Object> parametros = insertarTDRMPParams(idTDRMPR, idCuenEnt, desMoneda, monto);
        Map<String, Object> resultado = llamada.execute(parametros);
        getResult(resultado);
    }

    private SimpleJdbcCall insertarDisminucionTDRMPCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("SET_DISMINUCION_MP_v2").declareParameters(
                        new SqlParameter(TDRMP_ID, OracleTypes.NUMBER),
                        new SqlParameter(CUEN_ENT_ID, OracleTypes.NUMBER),
                        new SqlParameter(MONEDA_DES, OracleTypes.VARCHAR),
                        new SqlParameter(MONTO, OracleTypes.NUMBER),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public void deleteDetalleMPDigitacionTDR(Integer idDetTDRMP) {
        SimpleJdbcCall llamada = deleteDetalleMPDigitacionTDRCall();
        Map<String, Object> parametros = deleteDetalleMPDigitacionTDRParams(idDetTDRMP);
        Map<String, Object> resultado = llamada.execute(parametros);
        getResult(resultado);
    }

    private Map<String, Object> deleteDetalleMPDigitacionTDRParams(Integer idDetTDRMP) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(DET_PRESUP_ID, idDetTDRMP);
        return parametros;
    }

    private SimpleJdbcCall deleteDetalleMPDigitacionTDRCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("DEL_DET_TDR_MP_v2").declareParameters(
                        new SqlParameter(DET_PRESUP_ID, OracleTypes.NUMERIC),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public void updateDetalleAumDisDigitacionMP(Integer idDetTDRMP,
                                                Integer montoAumento,
                                                Integer montoDisminucion) {
        SimpleJdbcCall llamada = updateDetalleAumDisDigitacionMPCall();
        Map<String, Object> parametros = updateDetalleAumDisDigitacionMPParams(idDetTDRMP, montoAumento, montoDisminucion);
        Map<String, Object> resultado = llamada.execute(parametros);
        getResult(resultado);
    }

    private Map<String, Object> updateDetalleAumDisDigitacionMPParams(Integer idDetTDRMP,
                                                                      Integer montoAumento,
                                                                      Integer montoDisminucion) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(DET_PRESUP_ID, idDetTDRMP);
        parametros.put("pMONTO_INCREMENTO", montoAumento);
        parametros.put("pMONTO_DISMINUCION", montoDisminucion);
        return parametros;
    }

    private SimpleJdbcCall updateDetalleAumDisDigitacionMPCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("UPD_DET_TDR_MP_v2").declareParameters(
                        new SqlParameter(DET_PRESUP_ID, OracleTypes.NUMERIC),
                        new SqlParameter("pMONTO_INCREMENTO", OracleTypes.NUMERIC),
                        new SqlParameter("pMONTO_DISMINUCION", OracleTypes.NUMERIC),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public List<DecretoModificacionPresupestariaDTO> DatosTDRXmlMP(int ejercicio,
                                             int idPeriodo,
                                             int numDoc,
                                             String tipoDoc){
        SimpleJdbcCall llamada = DatosTDRXmlMPCall();
        Map<String, Object> parametros = DatosTDRXmlMPParams(ejercicio, idPeriodo, numDoc, tipoDoc);
        Map<String, Object> resultado = llamada.execute(parametros);
        getResult(resultado);

        List<DecretoModificacionPresupestariaDTO> decretos = (List<DecretoModificacionPresupestariaDTO>) resultado.get("CURSOR_TDRMP");
        List<CuentaTDRMPDTO> cuentas = (List<CuentaTDRMPDTO>) resultado.get("CURSOR_DETALE_TDRMP");

        for (DecretoModificacionPresupestariaDTO decreto : decretos) {
            List<CuentaTDRMPDTO> cuentasFiltradas = new ArrayList<CuentaTDRMPDTO>();

            for (int i = 0; i < cuentas.size(); i++) {
                CuentaTDRMPDTO cuenta = cuentas.get(i);
                if (cuenta.getIdTDRMP().equals(decreto.getIdTDRMP())) {
                    cuentasFiltradas.add(cuenta);
                }
            }

            decreto.setCuentas(cuentasFiltradas);
        }

        return decretos;
    }

    private Map<String, Object> DatosTDRXmlMPParams(int ejercicio,
                                                    int idPeriodo,
                                                    int numDoc,
                                                    String tipoDoc) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJERCICIO", ejercicio);
        parametros.put(PERIODO, idPeriodo);
        parametros.put("pNRO_DOCTO", numDoc);
        parametros.put("pTIPO_DOCUMENTO", tipoDoc);
        return parametros;
    }

    private SimpleJdbcCall DatosTDRXmlMPCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("GET_TDR_MP_PARA_XML_v2").declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMERIC),
                        new SqlParameter(PERIODO, OracleTypes.NUMERIC),
                        new SqlParameter("pNRO_DOCTO", OracleTypes.NUMERIC),
                        new SqlParameter("pTIPO_DOCUMENTO", OracleTypes.VARCHAR),
                        new SqlOutParameter("CURSOR_TDRMP", OracleTypes.CURSOR, new DecretoModificacionPresupestariaMapper()),
                        new SqlOutParameter("CURSOR_DETALE_TDRMP", OracleTypes.CURSOR, new CuentaTDRMPMapper()),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public void createGlosa(Integer idTDRMP,
                            Integer idDetTDRMP,
                            String tipo,
                            String numero,
                            String texto,
                            String indPresup,
                            String codPartida,
                            String codCapitulo,
                            String codPrograma,
                            String nomPartida,
                            String nomCapitulo,
                            String nomPrograma) {
        SimpleJdbcCall llamada = createGlosaCall();
        Map<String, Object> parametros = createGlosaParams(idTDRMP,
                idDetTDRMP,
                tipo,
                numero,
                texto,
                indPresup,
                codPartida,
                codCapitulo,
                codPrograma,
                nomPartida,
                nomCapitulo,
                nomPrograma
        );
        Map<String, Object> resultado = llamada.execute(parametros);
        getResult(resultado);
    }

    private Map<String, Object> createGlosaParams(Integer idTDRMP,
                                                  Integer idDetTDRMP,
                                                  String tipo,
                                                  String numero,
                                                  String texto,
                                                  String indPresup,
                                                  String codPartida,
                                                  String codCapitulo,
                                                  String codPrograma,
                                                  String nomPartida,
                                                  String nomCapitulo,
                                                  String nomPrograma) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_TDRMP_ID, idTDRMP);
        parametros.put(P_DET_TDRMP_ID, idDetTDRMP);
        parametros.put("p_glo_tdrmp_tipo", tipo);
        parametros.put(P_GLO_TDRMP_NUM, numero);
        parametros.put(P_GLO_TDRMP_TEXTO, texto);
        parametros.put("p_glo_tdrmp_ind_presup", indPresup);
        parametros.put(P_GLO_TDRMP_COD_PART, codPartida);
        parametros.put(P_GLO_TDRMP_COD_CAP, codCapitulo);
        parametros.put(P_GLO_TDRMP_COD_PROG, codPrograma);
        parametros.put(P_GLO_TDRMP_NOM_PART, nomPartida);
        parametros.put(P_GLO_TDRMP_NOM_CAP, nomCapitulo);
        parametros.put(P_GLO_TDRMP_NOM_PROG, nomPrograma);
        return parametros;
    }

    private SimpleJdbcCall createGlosaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("set_glo_tdr_mp").declareParameters(
                        new SqlParameter(P_TDRMP_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_DET_TDRMP_ID, OracleTypes.NUMBER),
                        new SqlParameter("p_glo_tdrmp_tipo", OracleTypes.VARCHAR),
                        new SqlParameter(P_GLO_TDRMP_NUM, OracleTypes.VARCHAR),
                        new SqlParameter(P_GLO_TDRMP_TEXTO, OracleTypes.VARCHAR),
                        new SqlParameter("p_glo_tdrmp_ind_presup", OracleTypes.VARCHAR),
                        new SqlParameter(P_GLO_TDRMP_COD_PART, OracleTypes.VARCHAR),
                        new SqlParameter(P_GLO_TDRMP_COD_CAP, OracleTypes.VARCHAR),
                        new SqlParameter(P_GLO_TDRMP_COD_PROG, OracleTypes.VARCHAR),
                        new SqlParameter(P_GLO_TDRMP_NOM_PART, OracleTypes.VARCHAR),
                        new SqlParameter(P_GLO_TDRMP_NOM_CAP, OracleTypes.VARCHAR),
                        new SqlParameter(P_GLO_TDRMP_NOM_PROG, OracleTypes.VARCHAR),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public List<GlosaTDRMPDTO> getGlosas(Integer idTDRMP,
                                         Integer idDetTDRMP,
                                         String codPartida,
                                         String codCapitulo,
                                         String codPrograma) {
        SimpleJdbcCall llamada = getGlosasCall();
        Map<String, Object> parametros = getGlosasParams(idTDRMP, idDetTDRMP, codPartida, codCapitulo, codPrograma);
        Map<String, Object> resultado = llamada.execute(parametros);
        getResult(resultado);
        return (List<GlosaTDRMPDTO>) resultado.get("cursos_glosas_tdrmp");
    }

    private Map<String, Object> getGlosasParams(Integer idTDRMP,
                                                Integer idDetTDRMP,
                                                String codPartida,
                                                String codCapitulo,
                                                String codPrograma) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_TDRMP_ID, idTDRMP);
        parametros.put(P_DET_TDRMP_ID, idDetTDRMP);
        parametros.put(P_GLO_TDRMP_COD_PART, codPartida);
        parametros.put(P_GLO_TDRMP_COD_CAP, codCapitulo);
        parametros.put(P_GLO_TDRMP_COD_PROG, codPrograma);
        return parametros;
    }

    private SimpleJdbcCall getGlosasCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("get_glo_tdr_mp").declareParameters(
                        new SqlParameter(P_TDRMP_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_DET_TDRMP_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_GLO_TDRMP_COD_PART, OracleTypes.VARCHAR),
                        new SqlParameter(P_GLO_TDRMP_COD_CAP, OracleTypes.VARCHAR),
                        new SqlParameter(P_GLO_TDRMP_COD_PROG, OracleTypes.VARCHAR),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR),
                        new SqlOutParameter("cursos_glosas_tdrmp", OracleTypes.CURSOR, new GlosaTDRMPMapper())
                );
    }

    public void updateGlosa(GlosaTDRMPDTO glosa) {
        SimpleJdbcCall llamada = updateGlosaCall();
        Map<String, Object> parametros = updateGlosaParams(glosa.getIdGloTDRMP(), glosa.getNumGlosa(), glosa.getTextoGlosa());
        Map<String, Object> result = llamada.execute(parametros);
        getResult(result);
    }

    private Map<String, Object> updateGlosaParams(Integer idGloTDRMP, String numero, String texto) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_GLO_TDRMP_ID, idGloTDRMP);
        parametros.put(P_GLO_TDRMP_NUM, numero);
        parametros.put(P_GLO_TDRMP_TEXTO, texto);
        return parametros;
    }

    private SimpleJdbcCall updateGlosaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("upd_glo_tdr_mp")
                .declareParameters(
                        new SqlParameter(P_GLO_TDRMP_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_GLO_TDRMP_NUM, OracleTypes.VARCHAR),
                        new SqlParameter(P_GLO_TDRMP_TEXTO, OracleTypes.VARCHAR),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public void deleteGlosa(Integer idGloTDRMP) {
        SimpleJdbcCall llamada = deleteGlosaCall();
        Map<String, Object> parametros = deleteGlosaParams(idGloTDRMP);
        Map<String, Object> result = llamada.execute(parametros);
        getResult(result);
    }

    private Map<String, Object> deleteGlosaParams(Integer idGloTDRMP) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_GLO_TDRMP_ID, idGloTDRMP);
        return parametros;
    }

    private SimpleJdbcCall deleteGlosaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("del_glo_tdr_mp")
                .declareParameters(
                        new SqlParameter(P_GLO_TDRMP_ID, OracleTypes.NUMBER),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public void guardarDigitacion(Integer idEjercicio,
                                  Integer idPeriodo,
                                  Integer numDoc,
                                  String tipoDocumento) {
        SimpleJdbcCall llamada = guardarDigitacionCall();
        Map<String, Object> parametros = guardarDigitacionParams(idEjercicio, idPeriodo, numDoc, tipoDocumento);
        Map<String, Object> result = llamada.execute(parametros);
        getResult(result);
    }

    private Map<String, Object> guardarDigitacionParams(Integer idEjercicio,
                                                        Integer idPeriodo,
                                                        Integer numDoc,
                                                        String tipoDocumento) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("p_ejr_id", idEjercicio);
        parametros.put("p_pejr_id", idPeriodo);
        parametros.put("p_sis_numero_doc", numDoc);
        parametros.put("p_sis_tipo_doc", tipoDocumento);
        return parametros;
    }

    private SimpleJdbcCall guardarDigitacionCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("guardar_digitacion")
                .declareParameters(
                        new SqlParameter("p_ejr_id", OracleTypes.NUMBER),
                        new SqlParameter("p_pejr_id", OracleTypes.NUMBER),
                        new SqlParameter("p_sis_numero_doc", OracleTypes.NUMBER),
                        new SqlParameter("p_sis_tipo_doc", OracleTypes.VARCHAR),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    public List<GlosaTDRMPDTO> getGlosasByNumDoc(Integer numDoc) {
        SimpleJdbcCall llamada = getGlosasByNumDocCall();
        Map<String, Object> parametros = getGlosasByNumDocParams(numDoc);
        Map<String, Object> result = llamada.execute(parametros);
        getResult(result);
        return (List<GlosaTDRMPDTO>) result.get("cursor_glo_tdrmp");
    }

    private Map<String, Object> getGlosasByNumDocParams(Integer numDoc) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("p_sis_numero_doc", numDoc);
        return parametros;
    }

    private SimpleJdbcCall getGlosasByNumDocCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION)
                .withProcedureName("get_glo_tdr_doc").declareParameters(
                        new SqlParameter("p_sis_numero_doc", OracleTypes.NUMBER),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR),
                        new SqlOutParameter("cursor_glo_tdrmp", OracleTypes.CURSOR, new GlosaXmlTDRPMapper())
                );
    }

}
