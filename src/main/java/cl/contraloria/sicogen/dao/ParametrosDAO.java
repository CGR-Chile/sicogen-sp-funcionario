package cl.contraloria.sicogen.dao;

import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class ParametrosDAO {

    private static final String PKG_PARAMETROS = "PKG_PARAMETROS";
    private static final String P_PARAM_CODIGO = "pParamCodigo";
    private static final String P_PARAM_VALUE = "pParamValue";
    private JdbcTemplate jdbcTemplate;

    public ParametrosDAO(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public String getParametroValue(String codigoParametro) {
        SimpleJdbcCall call = getParametroValueCall();
        Map<String, Object> params = getParametroValueParams(codigoParametro);
        Map<String, Object> result = call.execute(params);
        return (String) result.get(P_PARAM_VALUE);
    }

    private Map<String, Object> getParametroValueParams(String codigoParametro) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(P_PARAM_CODIGO, codigoParametro);
        return params;
    }

    private SimpleJdbcCall getParametroValueCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_PARAMETROS)
                .withProcedureName("GET_PARAMETRO_VALUE_1")
                .declareParameters(
                        new SqlParameter(P_PARAM_CODIGO, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_PARAM_VALUE, OracleTypes.VARCHAR)
                );
    }
}
