package cl.contraloria.sicogen.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;


public class PeriodoEjercicioMantenedorMapper  extends BaseMapper implements RowMapper<JsonJTableExpenseBean> {
    @Override
    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {
        JsonJTableExpenseBean periodos = new JsonJTableExpenseBean();
        periodos.setId(getInteger(rs, "ID"));
        periodos.setPadreId(getInteger(rs, "EJR_ID"));
        periodos.setNombrePadre(getString(rs, "NOMBREPADRE"));
        periodos.setPadreId2(getInteger(rs, "PERD_ID"));
        periodos.setNombrePadre2(getString(rs, "NOMBREPADRE2"));
        periodos.setAnulacion(getString(rs, "ANULACION"));
        periodos.setUsuario(getString(rs, "USUARIO"));
        periodos.setFecha(getString(rs, "FECHA"));
        return periodos;
    }
}
