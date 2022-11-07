package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.OptionsJtable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaginasMapper extends BaseMapper implements RowMapper<OptionsJtable> {
    @Override
    public OptionsJtable mapRow(ResultSet resultSet, int i) throws SQLException {
        OptionsJtable pagina = new OptionsJtable();
        pagina.setDisplayText(getString(resultSet, "NOMBRE"));
        pagina.setValue(getString(resultSet, "PAGINA_ID"));
        return pagina;
    }
}
