package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.ProgramaPresupuestarioDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartidaEjercicioMapper implements RowMapper<ProgramaPresupuestarioDTO> {

    @Override
    public ProgramaPresupuestarioDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ProgramaPresupuestarioDTO partida = new ProgramaPresupuestarioDTO();

        partida.setIdPartida(Integer.valueOf(resultSet.getString("PARTIDA_ID")));
        partida.setNombrePartida(resultSet.getString("PARTIDA_NOMBRE"));
        partida.setCodPartida(resultSet.getString("ENT_PART_CODIGO"));
        partida.setIdEjercicioPP(resultSet.getInt("EJERCICIO"));

        return partida;
    }
}
