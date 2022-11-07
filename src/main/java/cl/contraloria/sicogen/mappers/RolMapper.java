package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.Rol;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolMapper extends BaseMapper implements RowMapper<Rol> {
    @Override
    public Rol mapRow(ResultSet resultSet, int i) throws SQLException {
        Rol rol = new Rol();
        rol.setCodigo(getString(resultSet, "CODIGO"));
        rol.setFecha(getString(resultSet, "FECHA"));
        rol.setNombre(getString(resultSet, "NOMBRE"));
        rol.setRolId(getInteger(resultSet, "ROL_ID"));
        rol.setUsuario(getString(resultSet, "USUARIO"));
        rol.setVigencia(getString(resultSet, "VIGENCIA"));
        return rol;
    }
}
