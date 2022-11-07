package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.OptionsJtable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubTiposMantenedorMapper extends BaseMapper implements RowMapper<OptionsJtable> {
    @Override
    public OptionsJtable mapRow(ResultSet rs, int i) throws SQLException {
        OptionsJtable optionsJtable = new OptionsJtable();
        optionsJtable.setValue(getString(rs, "ID"));
        optionsJtable.setDisplayText(getString(rs, "NOMBRE"));
        return optionsJtable;
    }
}
