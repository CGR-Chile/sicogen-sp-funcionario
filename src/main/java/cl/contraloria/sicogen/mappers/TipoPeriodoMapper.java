package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.Periodos;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoPeriodoMapper implements RowMapper<Periodos> {

    @Override
    public Periodos mapRow(ResultSet resultSet, int i) throws SQLException {
        Periodos tipoPeriodo = new Periodos(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        tipoPeriodo.setPeriodoId(resultSet.getInt(1));
        tipoPeriodo.setPeriodoNombre(resultSet.getString(2));
        tipoPeriodo.setPeriodoCodigo(resultSet.getString(3));
        return tipoPeriodo;
    }
}
