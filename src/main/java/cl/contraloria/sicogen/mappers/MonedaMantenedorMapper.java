package cl.contraloria.sicogen.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
public class MonedaMantenedorMapper extends BaseMapper implements RowMapper<JsonJTableExpenseBean> {
    @Override
    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {
        JsonJTableExpenseBean moneda = new JsonJTableExpenseBean();
        moneda.setAreaID(getString(rs, "ID"));
        moneda.setCodigo(getString(rs, "CODIGO"));
        moneda.setNombre(getString(rs, "NOMBRE"));
        moneda.setAnulacion(getString(rs, "ANULACION"));
        return moneda;
    }
}
