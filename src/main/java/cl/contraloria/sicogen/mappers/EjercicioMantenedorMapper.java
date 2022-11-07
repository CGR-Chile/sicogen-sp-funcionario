package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EjercicioMantenedorMapper extends BaseMapper implements RowMapper<JsonJTableExpenseBean> {
    @Override
    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {
        JsonJTableExpenseBean informes = new JsonJTableExpenseBean();
        informes.setAreaID(getString(rs, "ID"));
        informes.setCodigo(getString(rs, "CODIGO"));
        informes.setNombre(getString(rs, "NOMBRE"));
        informes.setAnulacion(getString(rs, "ANULACION"));
        return informes;
    }
}
