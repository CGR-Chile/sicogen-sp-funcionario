package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.*;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.utils.ConexionBD;
import cl.contraloria.sicogen.utils.Utils;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

//import cl.contraloria.sicogen.mappers.PeriodosMapper;

@Component
public class ReportesDAO {

    /* JdbcTemplate */
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReportesDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //Combo Periodos
    public List<ReportesCumplimientoDTO> getReportesCumplimiento(String tpInforme, Integer reporte, Integer ejercicio, Integer periodo, Integer informe, String partidaRC, String capituloRC){
        SimpleJdbcCall llamada = getReporteCumpl();
        Map<String, Object> parametros = getParametrosReporteCumpl(tpInforme, reporte, ejercicio, periodo, informe, partidaRC, capituloRC);
        return (List<ReportesCumplimientoDTO>) llamada.execute(parametros).get("CURSOR_CUMPLI");
    }

    private Map<String, Object> getParametrosReporteCumpl(String tpInforme, Integer reporte, Integer ejercicio, Integer periodo, Integer informe, String partidaRC, String capituloRC) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pTIPO_INFORME", tpInforme);
        parametros.put("pEJER_ID", ejercicio);
        parametros.put("pPEJR_ID", periodo);
        parametros.put("pINF_ID", informe);
        parametros.put("pENT_PART_CODIGO", partidaRC);
        parametros.put("pENT_CAP_CODIGO", capituloRC);

        return parametros;
    }

    private SimpleJdbcCall getReporteCumpl(){
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_REPORTES")
                .withProcedureName("GET_REPORTE_CUMPLIMIENTO")
                .declareParameters(
                        new SqlParameter("pTIPO_INFORME", OracleTypes.INTEGER),
                        new SqlParameter("pEJER_ID", OracleTypes.INTEGER),
                        new SqlParameter("pPEJR_ID", OracleTypes.INTEGER),
                        new SqlParameter("pINF_ID", OracleTypes.INTEGER),
                        new SqlParameter("pENT_PART_CODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pENT_CAP_CODIGO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR),
                        new SqlOutParameter("CURSOR_CUMPLI", OracleTypes.CURSOR, new ReporteCumplimientoMapper())
                )
                .withoutProcedureColumnMetaDataAccess();
    }


    public List<ReportesEnvioDTO> getReportesEnvio(String tpInforme, String partidaRE, Integer ejercicio, Integer periodo, String capituloRE){
        SimpleJdbcCall llamada = getReporteEnvio();
        Map<String, Object> parametros = getParametrosReporteEnvio(tpInforme, partidaRE, ejercicio, periodo, capituloRE);
        return (List<ReportesEnvioDTO>) llamada.execute(parametros).get("CURSOR_CUMPLI");
}

    private Map<String, Object> getParametrosReporteEnvio(String tpInforme, String partidaRE, Integer ejercicio, Integer periodo, String capituloRE) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pENTIDAD", 1);
        parametros.put("pTIPO_INFORME", tpInforme);
        parametros.put("pEJER_ID", ejercicio);
        parametros.put("pPEJR_ID", periodo);
        parametros.put("pENT_PART_CODIGO", capituloRE);
        parametros.put("pENT_CAP_CODIGO", partidaRE);

        return parametros;
    }

    private SimpleJdbcCall getReporteEnvio(){
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_REPORTES")
                .withProcedureName("GET_REPORTE_ENVIOS_X_ENTIDAD")
                .declareParameters(
                        new SqlParameter("pENTIDAD", OracleTypes.INTEGER),
                        new SqlParameter("pTIPO_INFORME", OracleTypes.INTEGER),
                        new SqlParameter("pEJER_ID", OracleTypes.INTEGER),
                        new SqlParameter("pPEJR_ID", OracleTypes.INTEGER),
                        new SqlParameter("pENT_PART_CODIGO", OracleTypes.VARCHAR),
                        new SqlParameter("pENT_CAP_CODIGO", OracleTypes.VARCHAR),
                        new SqlOutParameter("pCOD", OracleTypes.INTEGER),
                        new SqlOutParameter("pMESSAGE", OracleTypes.VARCHAR),
                        new SqlOutParameter("CURSOR_CUMPLI", OracleTypes.CURSOR, new ReporteEnvioMapper())
                )
                .withoutProcedureColumnMetaDataAccess();
    }





}
