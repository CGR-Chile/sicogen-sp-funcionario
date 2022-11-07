package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.PlanDeCuentasMapper;
import cl.contraloria.sicogen.model.PlanDeCuentas;
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
public class PlanDeCuentasDAO {

    private static final String PKG_CUENTAS = "PKG_CUENTAS";
    private static final String P_SUBTITULO = "pSubTitulo";
    private static final String P_ITEM = "pItem";
    private static final String CURSOR_PLAN_CUENTAS_TDRII = "CURSOR_PLAN_CUENTAS_TDRII";
    private static final String ID_EJERCICIO = "idEjercicio";
    private JdbcTemplate jdbcTemplate;

    public PlanDeCuentasDAO(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public List<PlanDeCuentas> getCuentasPresupTDRII(String subTitulo, String item, String idEjercicio){
        SimpleJdbcCall llamada = getCuentasPresupTDRIICall();
        Map<String, Object> parametros = getCuentasPresupTDRIIParams(subTitulo, item, idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<PlanDeCuentas>) result.get(CURSOR_PLAN_CUENTAS_TDRII);
    }

    private Map<String, Object> getCuentasPresupTDRIIParams(String subTitulo, String item, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_SUBTITULO, subTitulo);
        parametros.put(P_ITEM, item);
        parametros.put(ID_EJERCICIO, idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getCuentasPresupTDRIICall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CUENTAS)
                .withProcedureName("GET_PLAN_CUENTA_TDRII")
                .declareParameters(
                        new SqlParameter(P_SUBTITULO, OracleTypes.VARCHAR),
                        new SqlParameter(P_ITEM, OracleTypes.VARCHAR),
                        new SqlParameter(ID_EJERCICIO, OracleTypes.VARCHAR),
                        new SqlOutParameter(CURSOR_PLAN_CUENTAS_TDRII, OracleTypes.CURSOR, new PlanDeCuentasMapper())
                );
    }
}
