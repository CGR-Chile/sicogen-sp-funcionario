package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.PeriodoAbrev;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodoAbrevMapper  implements RowMapper<PeriodoAbrev> {
    @Override
    public PeriodoAbrev mapRow(ResultSet resultSet, int i) throws SQLException {
        PeriodoAbrev periodo = new PeriodoAbrev(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4)
        );
        return periodo;
    }
}
