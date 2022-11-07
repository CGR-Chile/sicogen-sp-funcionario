package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolPaginaMapper extends BaseMapper implements RowMapper<JsonJTableExpenseBean> {

    @Override
    public JsonJTableExpenseBean mapRow(ResultSet resultSet, int i) throws SQLException {
        JsonJTableExpenseBean rp = new JsonJTableExpenseBean();
        rp.setAccion(getString(resultSet, "ACCION"));
        rp.setUpdate(getString(resultSet, "FECHA_MODIFICACION"));
        rp.setNombre(getString(resultSet, "NOMBRE"));
        rp.setId(getInteger(resultSet, "PAGINA_ID"));
        rp.setRolID(getInteger(resultSet, "ROL_ID"));
        rp.setRolpagID(getInteger(resultSet, "ROLPAG_ID"));
        rp.setUserupdate(getString(resultSet, "USUARIO_MODIFICACION"));
        rp.setVigente(getString(resultSet, "VIGENTE"));
        return rp;
    }
}
