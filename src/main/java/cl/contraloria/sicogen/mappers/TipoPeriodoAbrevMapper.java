package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.PeriodoAbrev;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoPeriodoAbrevMapper implements RowMapper<PeriodoAbrev> {
    @Override
    public PeriodoAbrev mapRow(ResultSet resultSet, int i) throws SQLException {
        PeriodoAbrev tipoPeriodo = new PeriodoAbrev(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        tipoPeriodo.setPeriodoId(resultSet.getInt(1));
        tipoPeriodo.setPeriodoNombre(resultSet.getString(2));
        tipoPeriodo.setPeriodoCodigo(resultSet.getString(3));
        tipoPeriodo.setPeriodoAbrev(resultSet.getString(4));
        return tipoPeriodo;
    }

}
