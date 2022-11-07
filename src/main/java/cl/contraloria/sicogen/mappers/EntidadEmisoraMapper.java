package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.EntidadEmisoraDTO;
import cl.contraloria.sicogen.model.ProgramaPresupuestarioDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntidadEmisoraMapper implements RowMapper<EntidadEmisoraDTO> {

    @Override
    public EntidadEmisoraDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        EntidadEmisoraDTO entidad = new EntidadEmisoraDTO();

        entidad.setId(resultSet.getInt("ENT_EMI_ID"));
        entidad.setNombre(resultSet.getString("ENT_EMI_NOMBRE"));
        entidad.setCodigoInterno(resultSet.getString("ENT_EMI_COD_INTERNO"));

        return entidad;
    }
}
