package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.CtaPresupSimpleMapper;
import cl.contraloria.sicogen.mappers.ItemMapper;
import cl.contraloria.sicogen.mappers.SubtituloMapper;
import cl.contraloria.sicogen.model.CtaPresupSimpleVO;
import cl.contraloria.sicogen.model.ItemBO;
import cl.contraloria.sicogen.model.SubTituloBO;
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
public class CatalogoPresupDAO {

    private static final String PKG_FILTRO = "PKG_FILTRO";
    private static final String P_EJERCICIO = "pEJERICIO";
    private static final String CURSOR_SUBTITULOS = "CURSOR_SUBTITULOS";
    private static final String P_SUBTITULO = "pSUBTITULO";
    private static final String CURSOR_ITEMS = "CURSOR_ITEMS";
    private static final String PKG_DIGITACION_TDR_II = "PKG_DIGITACION_TDR_II";
    private static final String ID_CUENTA = "ID_CUENTA";
    private static final String P_CUENTA_PRESU = "P_CUENTA_PRESU";
    private JdbcTemplate jdbcTemplate;

    public CatalogoPresupDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<SubTituloBO> getSubtitulosByEjericio(Integer idEjercicio) {
        SimpleJdbcCall llamada = getSubtitulosByEjericioCall();
        Map<String, Object> parametros = getSubtitulosByEjericioParams(idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<SubTituloBO>) result.get(CURSOR_SUBTITULOS);
    }

    private Map<String, Object> getSubtitulosByEjericioParams(Integer idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_EJERCICIO, idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getSubtitulosByEjericioCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_FILTRO)
                .withProcedureName("GET_SUBTITULOS_BY_EJERICIO")
                .declareParameters(
                        new SqlParameter(P_EJERCICIO, OracleTypes.NUMERIC),
                        new SqlOutParameter(CURSOR_SUBTITULOS, OracleTypes.CURSOR, new SubtituloMapper())
                );
    }

    public List<ItemBO> getItemsBySubTitulo(Integer idEjercicio, Integer idSubtitulo) {
        SimpleJdbcCall llamada = getItemsBySubTituloCall();
        Map<String, Object> parametros = getItemsBySubTituloParams(idEjercicio, idSubtitulo);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<ItemBO>) result.get(CURSOR_ITEMS);
    }

    private Map<String, Object> getItemsBySubTituloParams(Integer idEjercicio, Integer idSubtitulo) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_SUBTITULO, idSubtitulo);
        parametros.put(P_EJERCICIO, idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getItemsBySubTituloCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_FILTRO)
                .withProcedureName("GET_ITEMS_BY_SUBTITULO")
                .declareParameters(
                        new SqlParameter(P_SUBTITULO, OracleTypes.NUMERIC),
                        new SqlParameter(P_EJERCICIO, OracleTypes.NUMERIC),
                        new SqlOutParameter(CURSOR_ITEMS, OracleTypes.CURSOR, new ItemMapper())
                );
    }

    public CtaPresupSimpleVO getCuentaPresupByID(Integer idCuenta) {
        SimpleJdbcCall llamada = getCuentaPresupByIDCall();
        Map<String, Object> parametros = getCuentaPresupByIDParams(idCuenta);
        Map<String, Object> result = llamada.execute(parametros);
        List<CtaPresupSimpleVO> records = (List<CtaPresupSimpleVO>) result.get(P_CUENTA_PRESU);
        CtaPresupSimpleVO record = records.get(0);
        record.setPkCuenta(String.valueOf(idCuenta));
        return record;
    }

    private Map<String, Object> getCuentaPresupByIDParams(Integer idCuenta) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(ID_CUENTA, idCuenta);
        return parametros;
    }

    private SimpleJdbcCall getCuentaPresupByIDCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_DIGITACION_TDR_II)
                .withProcedureName("GET_CUENTAPRESU_ID")
                .declareParameters(
                        new SqlParameter(ID_CUENTA, OracleTypes.NUMBER),
                        new SqlOutParameter(P_CUENTA_PRESU, OracleTypes.CURSOR, new CtaPresupSimpleMapper())
                );
    }
}
