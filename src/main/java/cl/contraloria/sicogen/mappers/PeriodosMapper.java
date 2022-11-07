package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.Periodos;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodosMapper extends BaseMapper implements RowMapper<Periodos> {

    @Override
    public Periodos mapRow(ResultSet resultSet, int i) throws SQLException {
        Periodos periodo = new Periodos(
                getInteger(resultSet, "PERIODO_ID"),
                getString(resultSet,"PERIODO_NOMBRE"),
                getString(resultSet,"PERIODO_CODIGO")
        );

        periodo.setPeriodoCodigo(getString(resultSet,"PERIODO_CODIGO"));
        periodo.setPeriodoId(getInteger(resultSet, "PERIODO_ID"));
        periodo.setPeriodoNombre(getString(resultSet,"PERIODO_NOMBRE"));
        return periodo;
    }
}
