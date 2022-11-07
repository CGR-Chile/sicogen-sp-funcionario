package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinciasMapper extends BaseMapper implements RowMapper<JsonJTableExpenseBean> {
    @Override
    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {
        JsonJTableExpenseBean provincias = new JsonJTableExpenseBean();
        provincias.setId(getInteger(rs, "ID"));
        provincias.setCodigo(getString(rs, "CODIGO"));
        provincias.setNombre(getString(rs, "NOMBRE"));
        provincias.setAnulacion(getString(rs, "ANULACION"));
        provincias.setRegion(getInteger(rs, "REG_ID"));
        return provincias;
    }
}
