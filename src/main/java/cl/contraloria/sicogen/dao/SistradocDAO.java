package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.CapituloSistradocMapper;
import cl.contraloria.sicogen.mappers.CaratulaDTOMapper;
import cl.contraloria.sicogen.mappers.PartidaSistradocMapper;
import cl.contraloria.sicogen.model.CaratulaDTO;
import cl.contraloria.sicogen.model.CatalogarTDRDTO;
import cl.contraloria.sicogen.model.ResultadoEjecucion;
import cl.contraloria.sicogen.utils.ConexionBD;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SistradocDAO extends BaseDAO {
    private static final String PKG_SISTRADOC = "PKG_SISTRADOC";
    private static final String COD = "pCOD";
    private static final String MESSAGE = "pMESSAGE";
    private static final String MESSAGE_INSERT = "PMSG";
    private static final String CURSOR_PERIODO = "CURSOR_PERIODO";
    private static final String CURSOR_SISTRADOC = "pCURSOR_DOC_SISTRADOC";
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
    private static final String P_SIS_ID_DOCUMENTO = "P_SIS_DOCUMENTO_ID";
    private static final String P_SIS_TIPO_DOC = "P_SIS_TIPO_DOC";
    private static final String P_SIS_ESTADO_SISTRADOC = "P_SIS_ESTADO_SISTRADOC";
    private static final String P_SIS_ENTIDAD_EMISORA = "P_SIS_ENTIDAD_EMISORA";
    private static final String P_SIS_ESTADO_SICOGEN = "P_SIS_ESTADO_SICOGEN";
    private static final String P_SIS_NUMERO_DOC = "P_SIS_NUMERO_DOC";
    private static final String P_SIS_FECHA_DOC = "P_SIS_FECHA_DOC";
    private static final String P_SIS_FECHA_RECEP_CGR = "P_SIS_FECHA_RECEP_CGR";
    private static final String P_SIS_ANALISTA = "P_SIS_ANALISTA";
    private static final String P_SIS_MATERIA_GENERAL = "P_SIS_MATERIA_GENERAL";
    private static final String P_SIS_MATERIA_ESPECIFICA = "P_SIS_MATERIA_ESPECIFICA";
    private static final String P_SIS_ANNO = "P_SIS_ANNO";
    private static final String P_PEJR_ID_SISTRADOC = "P_PEJR_ID";
    private static final String P_EJR_ID_SISTRADOC = "P_EJR_ID";
    private static final String P_EJR_NOMBRE = "P_EJR_NOMBRE";
    private static final String P_SIS_SECUENCIA = "P_SIS_SECUENCIA_REINGRESO";

    private static final String V_ROWID = "v_Rowid";
    public static final String SICOGENINTEROP = "sicogeninterop";


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

    public CaratulaDTO getCaratula(String p_SIS_DOCUMENTO_ID) {
        SimpleJdbcCall llamada = getCaratulaCall();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_SIS_DOCUMENTO_ID, p_SIS_DOCUMENTO_ID);
        Map<String, Object> result = llamada.execute(parametros);
        List<CaratulaDTO> caratulaDTO = (List<CaratulaDTO>) result.get(CURSOR_SISTRADOC);
        if (caratulaDTO.size() > 0)
            return caratulaDTO.get(0);
        return null;
    }


    public List<CaratulaDTO> getAllCaratula(String idDocumento, String tipoInforme, String tipoDocumento, String ejercicio, String nroDocumento, String entidadEmisora,String fechaDocumento) throws SQLException {
        List<CaratulaDTO> caratulaDTOS = new ArrayList<>();
        String query = "SELECT SISTRA.SIS_DOCUMENTO_ID ID_SISTRADOC, " +
                "SISTRA.SIS_ESTADO_SISTRADOC ID_ESTADO_SISTRADOC, " +
                "SISTRA.SIS_ENTIDAD_EMISORA ENTIDAD_EMISORA, " +
                "SISTRA.SIS_TIPO_DOC TIPO_DOCUMENTO, " +
                "SISTRA.SIS_NUMERO_DOC NUMERO_DOCUMENTO, " +
                "TO_CHAR(SISTRA.SIS_FECHA_DOC,'DD-MM-YYYY') FECHA_DOCUMENTO, " +
                "TO_CHAR(SISTRA.SIS_FECHA_RECEP_CGR,'DD-MM-YYYY') FECHA_RECEPCION_CGR, " +
                "SISTRA.SIS_ANALISTA ANALISTA, " +
                "SISTRA.SIS_MATERIA_GENERAL MATERIA_GENERAL, " +
                "SISTRA.SIS_MATERIA_ESPECIFICA MATERIA_ESPECIFICA, " +
                "SISTRA.USR_ID, " +
                "SISTRA.SIS_ANNO ANNO, " +
                "SISTRA.SIS_SECUENCIA_REINGRESO SECUENCIA_REINGRESO, " +
                "SISTRA.EJR_ID ID_EJERCICIO, " +
                "EJERCICIO.EJR_NOMBRE EJERCICIO, " +
                "(SELECT TBLU.USR_NAME FROM TBL_USUARIOS TBLU WHERE TBLU.USR_ID=SISTRA.USR_ID) NOMBRE_USUARIO " +
                "FROM TBL_SISTRADOC SISTRA " +
                "INNER JOIN TBL_EJERCICIO EJERCICIO ON EJERCICIO.EJR_ID = SISTRA.EJR_ID " +
                "where 1=1 AND LOWER(SISTRA.SIS_ANALISTA) NOT LIKE '" + SICOGENINTEROP + "'";
        if (idDocumento != null)
            query = query + " AND SISTRA.SIS_DOCUMENTO_ID =" + idDocumento;
        if (tipoDocumento != null)
            query = query + " AND UPPER(SISTRA.SIS_TIPO_DOC) = UPPER(" + " \'" + tipoDocumento + "\')";
        if (ejercicio != null)
            query = query + " AND SISTRA.EJR_ID =" + ejercicio;
        if (nroDocumento != null)
            query = query + " AND SISTRA.SIS_NUMERO_DOC =" + nroDocumento;
        if (entidadEmisora != null)
            query = query + " AND SISTRA.SIS_ENTIDAD_EMISORA =" + " \'" +entidadEmisora + "\'";
        if (fechaDocumento != null)
            query = query + " AND SISTRA.SIS_FECHA_DOC =" +"to_timestamp( " +" \'" +fechaDocumento + "\'"+",'DD/MM/RR HH:MI:SS')";

        Connection cnnConexion = null;
        try {
            ConexionBD cnxConexion = new ConexionBD();
            cnnConexion = cnxConexion.abrirConexionAtencion();
            ResultSet rs = cnnConexion.createStatement().executeQuery(query);
            while (rs.next()) {
                CaratulaDTO caratulaDTO;
                CaratulaDTOMapper mapper = new CaratulaDTOMapper();
                caratulaDTO = mapper.mapRow(rs, 1);
                caratulaDTOS.add(caratulaDTO);
            }
            cnnConexion.close();
            rs.close();

        } catch (SQLException e) {
        } catch (NamingException e) {
        } catch (Exception e) {
        } finally {
            cnnConexion.close();
        }
        return caratulaDTOS;
    }

    private SimpleJdbcCall getCaratulaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SISTRADOC)
                .withProcedureName("GET_CARATULA_SISTRADOC").declareParameters(
                        new SqlParameter(P_SIS_DOCUMENTO_ID, OracleTypes.NUMERIC),
                        new SqlOutParameter(CURSOR_SISTRADOC, OracleTypes.CURSOR, new CaratulaDTOMapper())
                );
    }

    public int eliminarCaratula(String idDocumento) {
        String query = "delete from SICOGENPUB.TBL_SISTRADOC sistra where sistra.SIS_DOCUMENTO_ID=?";
        return jdbcTemplate.update(query, idDocumento);
    }

    public ResultadoEjecucion insertUpdCaratula(CaratulaDTO caratulaDTO) {
        SimpleJdbcCall llamada = insertCaratulaCall();
        Map<String, Object> parametros = insertCaratulaParams(caratulaDTO);
        Map<String, Object> result = llamada.execute(parametros);
        getResult(result);
        return new ResultadoEjecucion(String.valueOf(result.get(COD)), String.valueOf(result.get(MESSAGE_INSERT)), Integer.parseInt(result.get(V_ROWID).toString()));

    }

    private SimpleJdbcCall insertCaratulaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SISTRADOC)
                .withProcedureName("INS_UPD_CARATULA_SISTRADOC")
                .declareParameters(
                        new SqlParameter(P_SIS_TIPO_DOC, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_ID_DOCUMENTO, OracleTypes.NUMERIC),
                        new SqlParameter(P_SIS_ESTADO_SISTRADOC, OracleTypes.NUMERIC),
                        new SqlParameter(P_SIS_ENTIDAD_EMISORA, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_ESTADO_SICOGEN, OracleTypes.NUMERIC),
                        new SqlParameter(P_SIS_NUMERO_DOC, OracleTypes.NUMERIC),
                        new SqlParameter(P_SIS_FECHA_DOC, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_FECHA_RECEP_CGR, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_ANALISTA, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_MATERIA_GENERAL, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_MATERIA_ESPECIFICA, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_ANNO, OracleTypes.NUMERIC),
                        new SqlParameter(P_EJR_ID_SISTRADOC, OracleTypes.NUMERIC),
                        new SqlParameter(P_PEJR_ID_SISTRADOC, OracleTypes.NUMERIC),
                        new SqlParameter(P_USR_ID, OracleTypes.NUMERIC),
                        new SqlParameter(P_SIS_SECUENCIA, OracleTypes.NUMERIC),
                        new SqlOutParameter(V_ROWID, OracleTypes.NUMERIC),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    private Map<String, Object> insertCaratulaParams(CaratulaDTO caratulaDTO) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_SIS_TIPO_DOC, caratulaDTO.getTipoDocumento());
        parametros.put(P_SIS_ID_DOCUMENTO, caratulaDTO.getIdDocumento());
        parametros.put(P_SIS_ESTADO_SISTRADOC, caratulaDTO.getEstadoSistradoc());
        parametros.put(P_SIS_ENTIDAD_EMISORA, caratulaDTO.getEntidadEmisora());
        parametros.put(P_SIS_ESTADO_SICOGEN, caratulaDTO.getEstadoSicogen());
        parametros.put(P_SIS_NUMERO_DOC, caratulaDTO.getNroDocumento());
        parametros.put(P_SIS_FECHA_DOC, caratulaDTO.getFechaDocumento());
        parametros.put(P_SIS_FECHA_RECEP_CGR, caratulaDTO.getFechaRecepcionCGR());
        parametros.put(P_SIS_ANALISTA, caratulaDTO.getAnalista());
        parametros.put(P_SIS_MATERIA_GENERAL, caratulaDTO.getMateriaGeneral());
        parametros.put(P_SIS_MATERIA_ESPECIFICA, caratulaDTO.getMateriaEspecifica());
        parametros.put(P_SIS_ANNO, Integer.parseInt(caratulaDTO.getFechaDocumento().substring(caratulaDTO.getFechaDocumento().length()-4)));
        parametros.put(P_EJR_ID_SISTRADOC, caratulaDTO.getEjercicio().getEjercicioId());
        parametros.put(P_PEJR_ID_SISTRADOC, caratulaDTO.getpEjercicio());
        parametros.put(P_USR_ID, caratulaDTO.getIdUsuario());
        parametros.put(P_SIS_SECUENCIA, caratulaDTO.getSecuenciaReingreso());
        return parametros;
    }

    private String executeCallGetPartidaCapitulo(SimpleJdbcCall llamada,
                                                 int numDoc,
                                                 String tipoDoc,
                                                 int idEjercicio,
                                                 int idPeriodo,
                                                 int idInforme) {
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
