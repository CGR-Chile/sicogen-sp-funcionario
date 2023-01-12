package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.OptionsJtable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramaJTableMapper extends BaseMapper implements RowMapper<OptionsJtable> {
    @Override
    public OptionsJtable mapRow(ResultSet resultSet, int i) throws SQLException {
        OptionsJtable partida = new OptionsJtable();
        partida.setDisplayText(getString(resultSet, "ENT_PROG_NOMBRE"));
        partida.setValue(getString(resultSet, "ENT_PROG_ID"));
        return partida;
    }
}
