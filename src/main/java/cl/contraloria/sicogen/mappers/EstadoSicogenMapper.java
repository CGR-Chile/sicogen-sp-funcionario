package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.EstadoSicogenDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstadoSicogenMapper implements RowMapper<EstadoSicogenDTO> {
    @Override
    public EstadoSicogenDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        EstadoSicogenDTO estadoSicogenDTO = new EstadoSicogenDTO();
        estadoSicogenDTO.setEstadoId(resultSet.getInt("ESTADO_ID"));
        estadoSicogenDTO.setEstadoNombre(resultSet.getString("ESTADO_NOMBRE"));
        return estadoSicogenDTO;
    }
}
