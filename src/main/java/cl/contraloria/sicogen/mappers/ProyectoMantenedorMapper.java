package cl.contraloria.sicogen.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;

public class ProyectoMantenedorMapper extends BaseMapper implements RowMapper<JsonJTableExpenseBean>{
    @Override
    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {
        JsonJTableExpenseBean proyectos = new JsonJTableExpenseBean();
        //String codigo=getString(rs, "PROYECTO_CODIGO");
       // Integer largo= codigo.length();
        proyectos.setProyectoId(getString(rs, "PROYECTO_ID"));
        //proyectos.setProyectoCodigo(codigo.substring(0,largo-2));
        proyectos.setProyectoCodigo(getString(rs, "PROYECTO_CODIGO"));
       // proyectos.setProyectoDV(codigo.substring(largo-1,largo));
        proyectos.setProyectoNombre(getString(rs, "PROYECTO_NOMBRE"));
        proyectos.setProyectoNombreEstado(getString(rs, "PROYECTO_NOMBRE_ESTADO"));
        proyectos.setEstadoId(getInteger(rs, "PROYECTO_ESTADO_ID"));
        proyectos.setAnulacion(getString(rs, "ANULACION"));
        return proyectos;
    }
}
