package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.AreaTransaccionalDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstitucionMapper implements RowMapper<AreaTransaccionalDTO>{

    @Override
    public AreaTransaccionalDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        AreaTransaccionalDTO institucion = new AreaTransaccionalDTO();

        institucion.setIdInstitucion(Integer.valueOf(resultSet.getString("ID")));
        institucion.setCodInstitucion(resultSet.getString("CODIGO"));
        institucion.setNombreInstitucion(resultSet.getString("NOMBRE"));
        institucion.setSectorIdInstitucion(Integer.valueOf(resultSet.getString("SECTOR_ID")));
        institucion.setSectorNombreInstitucion(resultSet.getString("SECTOR_NOMBRE"));

        return institucion;
    }
}
