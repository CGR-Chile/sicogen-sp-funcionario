package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.exceptions.SicogenException;
import cl.contraloria.sicogen.mappers.MonedaMapper;
import cl.contraloria.sicogen.mappers.ProgramaCompletoMapper;
import cl.contraloria.sicogen.mappers.ProyectoBIPMapper;
import cl.contraloria.sicogen.mappers.ProyectosIIMapper;
import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import cl.contraloria.sicogen.model.MonedaBO;
import cl.contraloria.sicogen.model.ProgramaCompletoBO;
import cl.contraloria.sicogen.model.ProyectosIIBO;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DigitacionIIDAO extends BaseDAO {

    private static final String PKG_DIGITACION_TDR_II = "PKG_DIGITACION_TDR_II";
    private static final String P_MONEDA_CURSOR = "pMONEDA_CURSOR";
    private static final String ID_PROGRAMA = "ID_PROGRAMA";
    private static final String P_PADRES = "P_PADRES";
    private static final String ID_TBL_SISTRADOC = "ID_TBL_SISTRADOC";
    private static final String XML_FILE = "XML_FILE";
    private static final String P_COD = "pCOD";
    private static final String P_MSG = "pMSG";
    private static final String ID_TABLE = "ID_TABLE";
    private static final String DOCUMENTO = "DOCUMENTO";
    private static final String V_ROWID = "v_Rowid";
    private static final String CURSOR_ROYECTOS_II = "CURSOR_ROYECTOS_II";
    private static final String P_CODIGO = "pCodigo";
    private static final String P_DENOMINACION = "pDenominacion";
    private static final String PKG_MANTENEDORES = "PKG_MANTENEDORES";
    private static final String P_PROYECTO_CURSOR = "pPROYECTO_CURSOR";
    private static final String P_PROYECTOS = "pPROYECTOS";
    private static final String P_MESSAGE = "pMESSAGE";
    private JdbcTemplate jdbcTemplate;

    public DigitacionIIDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<MonedaBO> getMonedasValidas() {
        SimpleJdbcCall llamada = getMonedasValidasCall();
        Map<String, Object> result = llamada.execute();
        return (List<MonedaBO>) result.get(P_MONEDA_CURSOR);
    }

    private SimpleJdbcCall getMonedasValidasCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION_TDR_II)
                .withProcedureName("GET_MONEDAS")
                .declareParameters(
                        new SqlOutParameter(P_MONEDA_CURSOR, OracleTypes.CURSOR, new MonedaMapper())
                );
    }

    public ProgramaCompletoBO getProgramaCompleto(Integer idPrograma) {
        SimpleJdbcCall llamada = getProgramaCompletoCall();
        Map<String, Object> parametros = getProgramaCompletoParams(idPrograma);
        Map<String, Object> result = llamada.execute(parametros);
        List<ProgramaCompletoBO> list = (List<ProgramaCompletoBO>) result.get(P_PADRES);
        ProgramaCompletoBO record = list.get(0);
        record.setIdPrograma(String.valueOf(idPrograma));
        return record;
    }

    private Map<String, Object> getProgramaCompletoParams(Integer idPrograma) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(ID_PROGRAMA, idPrograma);
        return parametros;
    }

    private SimpleJdbcCall getProgramaCompletoCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION_TDR_II)
                .withProcedureName("GET_PARTIDA_BY_PROGRAMA")
                .declareParameters(
                        new SqlParameter(ID_PROGRAMA, OracleTypes.NUMBER),
                        new SqlOutParameter(P_PADRES, OracleTypes.CURSOR, new ProgramaCompletoMapper())
                );
    }

    public String obtieneDigitacionXML(int idTblSistradoc) throws SQLException {
        SimpleJdbcCall llamada = obtieneDigitacionXMLCall();
        Map<String, Object> parametros = obtieneDigitacionXMLParams(idTblSistradoc);
        Map<String, Object> result = llamada.execute(parametros);
        Integer codEjec = (Integer) result.get(P_COD);

        if (codEjec == 0) {
            Clob clobData = (Clob) result.get(XML_FILE);
            return clobData.getSubString(1, (int) clobData.length());
        } else {
            return null;
        }
    }

    private Map<String, Object> obtieneDigitacionXMLParams(int idTblSistradoc) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(ID_TBL_SISTRADOC, idTblSistradoc);
        return parametros;
    }

    private SimpleJdbcCall obtieneDigitacionXMLCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION_TDR_II)
                .withProcedureName("OBTIENE_XML_DIGITACION")
                .declareParameters(
                        new SqlParameter(ID_TBL_SISTRADOC, OracleTypes.NUMBER),
                        new SqlOutParameter(XML_FILE, OracleTypes.CLOB),
                        new SqlOutParameter(P_COD, OracleTypes.INTEGER),
                        new SqlOutParameter(P_MSG, OracleTypes.VARCHAR)
                );
    }

    public Integer updateArchivoXML(String strfile, Integer idFileUpload) {
        SimpleJdbcCall llamada = updateArchivoXMLCall();
        Map<String, Object> parametros = updateArchivoXMLParams(strfile, idFileUpload);
        Map<String, Object> result = llamada.execute(parametros);
        getResult(result, P_COD, P_MSG);
        return (Integer) result.get(V_ROWID);
    }

    private Map<String, Object> updateArchivoXMLParams(String strfile, Integer idFileUpload) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(ID_TABLE, idFileUpload);
        parametros.put(DOCUMENTO, strfile);
        return parametros;
    }

    private SimpleJdbcCall updateArchivoXMLCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION_TDR_II)
                .withProcedureName("UPDATE_XML_TEMP")
                .declareParameters(
                        new SqlParameter(ID_TABLE, OracleTypes.NUMBER),
                        new SqlParameter(DOCUMENTO, OracleTypes.CLOB),
                        new SqlOutParameter(V_ROWID, OracleTypes.INTEGER),
                        new SqlOutParameter(P_COD, OracleTypes.INTEGER),
                        new SqlOutParameter(P_MSG, OracleTypes.VARCHAR)
                );
    }

    public Integer insertArchivoXML(String xml, Integer idTblSistradoc) {
        SimpleJdbcCall llamada = insertArchivoXMLCall();
        Map<String, Object> parametros = insertArchivoXMLParams(xml, idTblSistradoc);
        Map<String, Object> result = llamada.execute(parametros);
        getResult(result, P_COD, P_MSG);
        return (Integer) result.get(V_ROWID);
    }

    private Map<String, Object> insertArchivoXMLParams(String xml, Integer idTblSistradoc) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(ID_TBL_SISTRADOC, idTblSistradoc);
        parametros.put(DOCUMENTO, xml);
        return parametros;
    }

    private SimpleJdbcCall insertArchivoXMLCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION_TDR_II)
                .withProcedureName("INSERT_XML_TEMP")
                .declareParameters(
                        new SqlParameter(ID_TBL_SISTRADOC, OracleTypes.NUMBER),
                        new SqlParameter(DOCUMENTO, OracleTypes.CLOB),
                        new SqlOutParameter(V_ROWID, OracleTypes.INTEGER),
                        new SqlOutParameter(P_COD, OracleTypes.INTEGER),
                        new SqlOutParameter(P_MSG, OracleTypes.VARCHAR)
                );
    }

    public List<ProyectosIIBO> getProyectosII() {
        SimpleJdbcCall llamada = getProyectosIICall();
        Map<String, Object> result = llamada.execute();
        return (List<ProyectosIIBO>) result.get(CURSOR_ROYECTOS_II);
    }

    private SimpleJdbcCall getProyectosIICall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION_TDR_II)
                .withProcedureName("GET_PROYECTOS_II")
                .declareParameters(
                        new SqlOutParameter(CURSOR_ROYECTOS_II, OracleTypes.CURSOR, new ProyectosIIMapper())
                );
    }

    public String getDenominacionProyecto(String pCodigo) {
        SimpleJdbcCall call = getDenominacionProyectoCall();
        Map<String, Object> params = getDenominacionProyectoParams(pCodigo);
        Map<String, Object> result = call.execute(params);
        return (String) result.get(P_DENOMINACION);
    }

    private Map<String, Object> getDenominacionProyectoParams(String pCodigo) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_CODIGO, pCodigo);
        return parametros;
    }

    private SimpleJdbcCall getDenominacionProyectoCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION_TDR_II)
                .withProcedureName("GET_PROYECTO_DENOMINACION")
                .declareParameters(
                        new SqlParameter(P_CODIGO, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_DENOMINACION, OracleTypes.VARCHAR)
                );
    }

    public List<JsonJTableExpenseBean> getProyectoByCodigo(String codigoProyecto) {
        SimpleJdbcCall call = getProyectoByCodigoCall();
        Map<String, Object> params = getProyectoByCodigoParams(codigoProyecto);
        Map<String, Object> result = call.execute(params);
        return (List<JsonJTableExpenseBean>) result.get(P_PROYECTO_CURSOR);
    }

    private Map<String, Object> getProyectoByCodigoParams(String codigoProyecto) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(P_CODIGO, codigoProyecto);
        return params;
    }

    private SimpleJdbcCall getProyectoByCodigoCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_PROYECTOS_by_COD")
                .declareParameters(
                        new SqlParameter(P_CODIGO, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_PROYECTO_CURSOR, OracleTypes.CURSOR, new ProyectoBIPMapper()),
                        new SqlOutParameter(P_PROYECTOS, OracleTypes.INTEGER),
                        new SqlOutParameter(P_MESSAGE, OracleTypes.VARCHAR)
                );
    }
}
