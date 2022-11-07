package cl.contraloria.sicogen.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import cl.contraloria.sicogen.model.JsonJTableExpenseBean;

public class RolesMantenedorMapper extends BaseMapper implements RowMapper<JsonJTableExpenseBean> {
    @Override
    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {
        JsonJTableExpenseBean roles = new JsonJTableExpenseBean();
        roles.setAreaID(getString(rs, "ID"));
        roles.setCodigo(getString(rs, "CODIGO"));
        roles.setNombre(getString(rs, "NOMBRE"));
        roles.setUsuario(getString(rs, "USUARIO"));
        roles.setFecha(getString(rs, "FECHA"));
        roles.setAnulacion(getString(rs, "ANULACION"));
        return roles;
    }
}
