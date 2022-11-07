package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.Region;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionMapper extends BaseMapper implements RowMapper<Region> {

    @Override
    public Region mapRow(ResultSet resultSet, int i) throws SQLException {
        Region regiones = new Region();
        regiones.setRegId(getInteger(resultSet, "REG_ID"));
        regiones.setCodigo(getString(resultSet, "REG_CODIGO"));
        regiones.setNombre(getString(resultSet, "REG_NOMBRE"));
        regiones.setIsValid(getString(resultSet, "REG_ISVALID"));
        return regiones;
    }
}
