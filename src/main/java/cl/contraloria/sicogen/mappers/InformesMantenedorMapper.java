package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InformesMantenedorMapper extends BaseMapper implements RowMapper<JsonJTableExpenseBean> {
    @Override
    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {
        JsonJTableExpenseBean informe = new JsonJTableExpenseBean();
        informe.setId(getInteger(rs, "INFORME_ID"));
        informe.setSubTipoID(getInteger(rs, "SUBTIPO"));
        informe.setCodigo(getString(rs, "CODIGO"));
        informe.setNombre(getString(rs, "NOMBRE"));
        informe.setDescripcion(getString(rs, "DESCRIPCION"));
        informe.setIsValid(getString(rs, "ISVALID"));
        return informe;
    }
}
