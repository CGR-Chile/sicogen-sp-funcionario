package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.Partida;
import cl.contraloria.sicogen.model.PartidaDTO;
import cl.contraloria.sicogen.model.Periodos;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoPartidaMapper implements RowMapper<Partida> {

    @Override
    public Partida mapRow(ResultSet resultSet, int i) throws SQLException {
        Partida tipoPartida = new Partida();
        tipoPartida.setId(resultSet.getInt(1));
        tipoPartida.setNombre(resultSet.getString(2));
        tipoPartida.setCodigo(resultSet.getString(3));
        return tipoPartida;
    }
}
