package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.BitacoraSistradocMapper;
import cl.contraloria.sicogen.mappers.CapituloSistradocMapper;
import cl.contraloria.sicogen.mappers.CaratulaDTOMapper;
import cl.contraloria.sicogen.mappers.PartidaSistradocMapper;
import cl.contraloria.sicogen.model.BitacoraSistradocDTO;
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
public class BitacoraSistradocDAO extends BaseDAO {
    private static final String PKG_SISTRADOC = "PKG_SISTRADOC";
    public static final String INS_SISTRADOC_BITACORA = "INS_SISTRADOC_BITACORA";
    private static final String COD = "pCOD";
    private static final String MESSAGE = "pMSG";

    private static final String V_ROWID = "v_Rowid";
    public static final String P_SIS_DOCUMENTO_ID = "P_SIS_DOCUMENTO_ID";
    public static final String P_SIS_ENTIDAD_EMISORA = "P_SIS_ENTIDAD_EMISORA";
    public static final String P_SIS_TIPO_DOC = "P_SIS_TIPO_DOC";
    public static final String P_SIS_NUMERO_DOC = "P_SIS_NUMERO_DOC";
    public static final String P_SIS_FECHA_DOC = "P_SIS_FECHA_DOC";
    public static final String P_SIS_FECHA_RECEP_CGR = "P_SIS_FECHA_RECEP_CGR";
    public static final String P_SIS_ANALISTA = "P_SIS_ANALISTA";
    public static final String P_SIS_MATERIA_GENERAL = "P_SIS_MATERIA_GENERAL";
    public static final String P_SIS_MATERIA_ESPECIFICA = "P_SIS_MATERIA_ESPECIFICA";
    public static final String P_SIS_ANNO = "P_SIS_ANNO";
    public static final String P_SIS_SECUENCIA_REINGRESO = "P_SIS_SECUENCIA_REINGRESO";
    public static final String P_SIB_FECHA_CREACION = "P_SIB_FECHA_CREACION";
    public static final String P_USR_ID = "P_USR_ID";
    public static final String P_SIS_OPERACION = "P_SIS_OPERACION";


    private JdbcTemplate jdbcTemplate;

    public BitacoraSistradocDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

   public List<BitacoraSistradocDTO> getBitacoraSistradoc(String idDocumento) throws SQLException {

        List<BitacoraSistradocDTO> bitacoraSistradocDTOS = new ArrayList<>();
        String query="SELECT  " +
                "BIT.SIB_ID ID_BITACORA,  " +
                "BIT.SIS_DOCUMENTO_ID DOCUMENTO_ID, " +
                "BIT.SIB_FECHA_CREACION FECHA, " +
                "BIT.USR_ID USR_ID,  " +
                "BIT.SIS_OPERACION OPERACION, " +
                "USR.USR_NICK USR_NICK " +
                "FROM TBL_SISTRADOC_BITACORA BIT " +
                "INNER JOIN TBL_USUARIOS USR ON bit.usr_id=usr.usr_id " +
                "WHERE bit.SIS_DOCUMENTO_ID="+ idDocumento;


        Connection cnnConexion = null;
        try {
            ConexionBD cnxConexion = new ConexionBD();
            cnnConexion = cnxConexion.abrirConexionAtencion();
            ResultSet rs = cnnConexion.createStatement().executeQuery(query);
            while (rs.next()) {
                BitacoraSistradocDTO bitacoraSistradocDTO;
                BitacoraSistradocMapper mapper = new BitacoraSistradocMapper();
                bitacoraSistradocDTO = mapper.mapRow(rs,1);
                bitacoraSistradocDTOS.add(bitacoraSistradocDTO);
            }
            cnnConexion.close();
            rs.close();

        } catch (SQLException e) {
            // logger.error("Error SQLException", e);
        } catch (NamingException e) {
            //logger.error("Error NamingException", e);
        } catch (Exception e) {
            //logger.error("Error Exception", e);
        } finally {
            cnnConexion.close();
        }
        return bitacoraSistradocDTOS;
    }
    public ResultadoEjecucion insertBitacoraCaratula(BitacoraSistradocDTO bitacoraSistradocDTO) {
        SimpleJdbcCall llamada = insertBitacoraCaratulaCall();
        Map<String, Object> parametros = insertBitacoraCaratulaParams(bitacoraSistradocDTO);
        Map<String, Object> result = llamada.execute(parametros);
        getResult(result);
        return new ResultadoEjecucion(String.valueOf(result.get(COD)), String.valueOf(result.get(MESSAGE)), Integer.parseInt(result.get(V_ROWID).toString()));

    }

    private SimpleJdbcCall insertBitacoraCaratulaCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SISTRADOC)
                .withProcedureName(INS_SISTRADOC_BITACORA)
                .declareParameters(
                        new SqlParameter(P_SIS_DOCUMENTO_ID, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_ENTIDAD_EMISORA, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_TIPO_DOC, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_NUMERO_DOC, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_FECHA_DOC, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_FECHA_RECEP_CGR, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_ANALISTA, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_MATERIA_GENERAL, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_MATERIA_ESPECIFICA, OracleTypes.VARCHAR),
                        new SqlParameter(P_SIS_ANNO, OracleTypes.NUMERIC),
                        new SqlParameter(P_SIS_SECUENCIA_REINGRESO, OracleTypes.NUMERIC),
                        new SqlParameter(P_SIB_FECHA_CREACION, OracleTypes.VARCHAR),
                        new SqlParameter(P_USR_ID, OracleTypes.NUMERIC),
                        new SqlParameter(P_SIS_OPERACION, OracleTypes.VARCHAR),
                        new SqlOutParameter(V_ROWID, OracleTypes.NUMERIC),
                        new SqlOutParameter(COD, OracleTypes.INTEGER),
                        new SqlOutParameter(MESSAGE, OracleTypes.VARCHAR)
                );
    }

    private Map<String, Object> insertBitacoraCaratulaParams(BitacoraSistradocDTO bitacoraSistradocDTO) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_SIS_DOCUMENTO_ID, bitacoraSistradocDTO.getIdDocumento());
        parametros.put(P_SIS_TIPO_DOC, bitacoraSistradocDTO.getTipoDocumento());
        parametros.put(P_SIS_ENTIDAD_EMISORA, bitacoraSistradocDTO.getEntidadEmisora());
        parametros.put(P_SIS_TIPO_DOC, bitacoraSistradocDTO.getTipoDocumento());
        parametros.put(P_SIS_NUMERO_DOC, bitacoraSistradocDTO.getNroDocumento());
        parametros.put(P_SIS_FECHA_DOC, bitacoraSistradocDTO.getFechaDocumento());
        parametros.put(P_SIS_FECHA_RECEP_CGR, bitacoraSistradocDTO.getFechaRecepcionCGR());
        parametros.put(P_SIS_ANALISTA, bitacoraSistradocDTO.getAnalista());
        parametros.put(P_SIS_MATERIA_GENERAL, bitacoraSistradocDTO.getMateriaGeneral());
        parametros.put(P_SIS_MATERIA_ESPECIFICA, bitacoraSistradocDTO.getMateriaEspecifica());
        parametros.put(P_SIS_ANNO, bitacoraSistradocDTO.getAnno());
        parametros.put(P_SIS_SECUENCIA_REINGRESO, bitacoraSistradocDTO.getSecuenciaReingreso());
        parametros.put(P_SIB_FECHA_CREACION, bitacoraSistradocDTO.getFechaCreacion());
        parametros.put(P_USR_ID, bitacoraSistradocDTO.getIdUsuario());
        parametros.put(P_SIS_OPERACION, bitacoraSistradocDTO.getOperacion());
        return parametros;
    }


}
