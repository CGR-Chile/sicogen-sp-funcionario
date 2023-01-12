package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.OptionsJtable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CapituloJTableMapper extends BaseMapper implements RowMapper<OptionsJtable> {
    @Override
    public OptionsJtable mapRow(ResultSet resultSet, int i) throws SQLException {
        OptionsJtable capitulo = new OptionsJtable();
        capitulo.setDisplayText(getString(resultSet, "NOMBRE"));
        capitulo.setValue(getString(resultSet, "ID"));
        return capitulo;
    }
}
