package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.CapituloSistradocMapper;
import cl.contraloria.sicogen.mappers.PartidaSistradocMapper;
import cl.contraloria.sicogen.model.CatalogarTDRDTO;
import cl.contraloria.sicogen.model.ResultadoEjecucion;

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
public class SistradocDAO extends BaseDAO {
    private static final String PKG_SISTRADOC = "PKG_SISTRADOC";
    private static final String COD = "pCOD";
    private static final String MESSAGE = "pMESSAGE";
    private static final String CURSOR_PERIODO = "CURSOR_PERIODO";
    private static final String NUMERO_DOC = "pNUMERO_DOC";
    private static final String TIPO_DOC = "pTIPO_DOC";
    private static final String EJERCICIO = "pEJERCICIO";
    private static final String PEJR_ID = "pPEJR_ID";
    private static final String INFORME = "pINFORME";
    private static final String P_EJR_ID = "p_ejr_id";
    private static final String P_INF_ID = "p_inf_id";
    private static final String P_PEJR_ID = "p_pejr_id";
    private static final String P_SIS_DOCUMENTO_ID = "p_sis_documento_id";
    private static final String P_USR_ID = "p_usr_id";
    private JdbcTemplate jdbcTemplate;

    public SistradocDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getPartida(int numDoc,
                             String tipoDoc,
                             int idEjercicio,
                             int idPeriodo,
                             int idInforme) {
        SimpleJdbcCall llamada = getPartidaCall();
        return executeCallGetPartidaCapitulo(llamada, numDoc, tipoDoc, idEjercicio, idPeriodo, idInforme);
    }

    private Map<String, Object> getPartidaCapituloParams(int numDoc, String tipoDoc, int idEjercicio, int idPeriodo, int idInforme) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(NUMERO_DOC, numDoc);
        parametros.put(TIPO_DOC, tipoDoc);
        parametros.put(EJERCICIO, idEjercicio);
        parametros.put(PEJR_ID, idPeriodo);
        parametros.put(INFORME, idInforme);
        return parametros;
    }

    private SimpleJdbcCall getPartidaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SISTRADOC)
                .withProcedureName("GET_PARTIDA_CAP_BY_NRO_DOC").declareParameters(
                        new SqlParameter(NUMERO_DOC, OracleTypes.NUMERIC),
                        new SqlParameter(TIPO_DOC, OracleTypes.VARCHAR),
                        new SqlParameter(EJERCICIO, OracleTypes.NUMERIC),
                        new SqlParameter(PEJR_ID, OracleTypes.NUMERIC),
                        new SqlParameter(INFORME, OracleTypes.NUMERIC),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR),
                        new SqlOutParameter(CURSOR_PERIODO, OracleTypes.CURSOR, new PartidaSistradocMapper())
                );
    }

    public String getCapitulo(int numDoc,
                             String tipoDoc,
                             int idEjercicio,
                             int idPeriodo,
                             int idInforme) {
        SimpleJdbcCall llamada = getCapituloCall();
        return executeCallGetPartidaCapitulo(llamada, numDoc, tipoDoc, idEjercicio, idPeriodo, idInforme);
    }

    private SimpleJdbcCall getCapituloCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SISTRADOC)
                .withProcedureName("GET_PARTIDA_CAP_BY_NRO_DOC").declareParameters(
                        new SqlParameter(NUMERO_DOC, OracleTypes.NUMERIC),
                        new SqlParameter(TIPO_DOC, OracleTypes.VARCHAR),
                        new SqlParameter(EJERCICIO, OracleTypes.NUMERIC),
                        new SqlParameter(PEJR_ID, OracleTypes.NUMERIC),
                        new SqlParameter(INFORME, OracleTypes.NUMERIC),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR),
                        new SqlOutParameter(CURSOR_PERIODO, OracleTypes.CURSOR, new CapituloSistradocMapper())
                );
    }

    private String executeCallGetPartidaCapitulo(SimpleJdbcCall llamada,
                                                 int numDoc,
                                                 String tipoDoc,
                                                 int idEjercicio,
                                                 int idPeriodo,
                                                 int idInforme){
        Map<String, Object> parametros = getPartidaCapituloParams(numDoc,
                tipoDoc,
                idEjercicio,
                idPeriodo,
                idInforme);
        Map<String, Object> resultado = llamada.execute(parametros);
        getResult(resultado);
        List<String> resultList = (List<String>) resultado.get(CURSOR_PERIODO);
        return resultList.get(0);
    }

    public ResultadoEjecucion asignarTDR(CatalogarTDRDTO catalogarTDRDTO) {
        SimpleJdbcCall llamada = asignarTDRCall();
        Map<String, Object> parametros = asignarTDRParams(catalogarTDRDTO);
        Map<String, Object> result = llamada.execute(parametros);
        getResult(result);
        return new ResultadoEjecucion(String.valueOf(result.get(COD)), String.valueOf(result.get(MESSAGE)));
    }

    private Map<String, Object> asignarTDRParams(CatalogarTDRDTO catalogarTDRDTO) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_EJR_ID, catalogarTDRDTO.getIdEjercicio());
        parametros.put(P_INF_ID, catalogarTDRDTO.getIdInforme());
        parametros.put(P_PEJR_ID, catalogarTDRDTO.getIdPeriodo());
        parametros.put(P_SIS_DOCUMENTO_ID, catalogarTDRDTO.getIdDocs());
        parametros.put(P_USR_ID, catalogarTDRDTO.getIdUser());
        return parametros;
    }

    private SimpleJdbcCall asignarTDRCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SISTRADOC)
                .withProcedureName("asociar_archivo_sistradoc_v2")
                .declareParameters(
                        new SqlParameter(P_EJR_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_INF_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_PEJR_ID, OracleTypes.NUMBER),
                        new SqlParameter(P_SIS_DOCUMENTO_ID, OracleTypes.VARCHAR),
                        new SqlParameter(P_USR_ID, OracleTypes.NUMBER),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }
}
