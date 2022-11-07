package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.ProgramaPresupuestarioDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PPMapper extends BaseMapper implements RowMapper<ProgramaPresupuestarioDTO> {
    @Override
    public ProgramaPresupuestarioDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ProgramaPresupuestarioDTO partida = new ProgramaPresupuestarioDTO();
        partida.setIdPP(Integer.valueOf(resultSet.getString("ID")));
        partida.setPartida(getString(resultSet, "PARTIDA"));
        partida.setCapitulo(getString(resultSet, "CAPITULO"));
        partida.setPrograma(getString(resultSet, "PROGRAMA"));
        partida.setNombre(getString(resultSet, "NOMBRE"));
        partida.setEstado(getString(resultSet, "ESTADO"));
        return partida;
    }
}