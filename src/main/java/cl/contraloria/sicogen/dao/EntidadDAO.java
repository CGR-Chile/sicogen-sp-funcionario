package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.MantenedoresMapper;
import cl.contraloria.sicogen.mappers.RolMapper;
import cl.contraloria.sicogen.model.Mantenedores;
import cl.contraloria.sicogen.model.Rol;
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
public class EntidadDAO {
    private static final String PKG_MANTENEDORES = "PKG_MANTENEDORES";
    private static final String PKG_USUARIOS = "PKG_USUARIOS";
    private static final String P_USUARIO = "pUsuario";
    private static final String P_TABLAS_CURSOR = "pTABLAS_CURSOR";
    private JdbcTemplate jdbcTemplate;

    public EntidadDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Rol> getRoles() {
        SimpleJdbcCall llamada = getRolesCall();
        Map<String, Object> result = llamada.execute();
        return (List<Rol>) result.get("cROLES");
    }

    private SimpleJdbcCall getRolesCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_USUARIOS)
                .withProcedureName("GET_ROLES")
                .declareParameters(
                        new SqlOutParameter("cROLES", OracleTypes.CURSOR, new RolMapper())
                );
    }

    public List<Mantenedores> getMantenedores(String user) {
        SimpleJdbcCall llamada = getMantenedoresCall();
        Map<String, Object> parametros = getMantenedoresParams(user);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<Mantenedores>) result.get(P_TABLAS_CURSOR);
    }

    private Map<String, Object> getMantenedoresParams(String user) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(P_USUARIO, user);
        return parametros;
    }

    private SimpleJdbcCall getMantenedoresCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_MANTENEDORES_ByUser")
                .declareParameters(
                        new SqlParameter(P_USUARIO, OracleTypes.VARCHAR),
                        new SqlOutParameter(P_TABLAS_CURSOR, OracleTypes.CURSOR, new MantenedoresMapper())
                );
    }
}
