package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.PeriodoEjercicioMapper;
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
public class PiDAO {

    private static final String PKG_INFORMES = "PKG_INFORMES";
    private static final String P_EJE_CODIGO = "pEJECODIGO";
    private static final String CURSOR_PERINFO = "CURSOR_PERINFO";
    private static final String P_COD = "pCOD";
    private static final String P_MSG = "pMSG";
    private JdbcTemplate jdbcTemplate;

    public PiDAO(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public String obtienePeriodoEjercicio(String ejercicio) {
        SimpleJdbcCall call = obtienePeriodoEjercicioCall();
        Map<String, Object> params = obtienePeriodoEjercicioParams(ejercicio);
        Map<String, Object> result = call.execute(params);
        List<String> data = (List<String>) result.get(CURSOR_PERINFO);
        return data.get(0);
    }

    private Map<String, Object> obtienePeriodoEjercicioParams(String ejercicio) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(P_EJE_CODIGO, ejercicio);
        return params;
    }

    private SimpleJdbcCall obtienePeriodoEjercicioCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("OBTIENE_PERIODO_INFID")
                .declareParameters(
                        new SqlParameter(P_EJE_CODIGO, OracleTypes.NUMERIC),
                        new SqlOutParameter(CURSOR_PERINFO, OracleTypes.CURSOR, new PeriodoEjercicioMapper()),
                        new SqlOutParameter(P_COD, OracleTypes.INTEGER),
                        new SqlOutParameter(P_MSG, OracleTypes.VARCHAR)
                );
    }
}
