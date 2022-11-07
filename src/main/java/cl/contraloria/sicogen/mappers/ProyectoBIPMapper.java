package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProyectoBIPMapper extends BaseMapper implements RowMapper<JsonJTableExpenseBean> {
    @Override
    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {
        JsonJTableExpenseBean proy = new JsonJTableExpenseBean();
        proy.setProyectoId(getString(rs, "PROYECTO_ID"));
        proy.setProyectoCodigo(getString(rs, "PROYECTO_CODIGO"));
        proy.setProyectoNombre(getString(rs, "PROYECTO_NOMBRE"));
        proy.setProyectoNombreEstado(getString(rs, "PROYECTO_NOMBRE_ESTADO"));
        proy.setValor1(getString(rs, "PROYECTO_ESTADO_ID"));
        return proy;
    }
}
