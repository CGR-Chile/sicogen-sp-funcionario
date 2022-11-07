package cl.contraloria.sicogen.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;

public class PeriodoMantenedorMapper extends BaseMapper implements RowMapper<JsonJTableExpenseBean>{
	 @Override
	    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {
	        JsonJTableExpenseBean periodos = new JsonJTableExpenseBean();
	        periodos.setId(getInteger(rs, "ID"));
	        periodos.setCodigo(getString(rs, "CODIGO"));
	        periodos.setNombre(getString(rs, "NOMBRE"));
	        periodos.setAnulacion(getString(rs, "ANULACION"));
	        periodos.setUsuario(getString(rs, "USUARIO"));
	        periodos.setFecha(getString(rs, "FECHA"));
	        return periodos;
	    }
}
