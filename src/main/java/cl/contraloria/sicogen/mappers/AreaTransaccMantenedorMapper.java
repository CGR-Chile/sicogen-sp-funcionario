package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaTransaccMantenedorMapper extends BaseMapper implements RowMapper<JsonJTableExpenseBean> {
    @Override
    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {

        JsonJTableExpenseBean informe = new JsonJTableExpenseBean();
        informe.setId(getInteger(rs, "ID"));
        /*
        informe.setSector(getString(rs, "SECTOR"));
        informe.setInstitucion(getString(rs, "INSTITUCION"));
        informe.setAt(getString(rs, "AT"));
        informe.setNombre(getString(rs, "NOMBRE"));
        informe.setRut(getString(rs, "RUT"));
        informe.setDiv(getString(rs, "DIV"));
        informe.setCodPadre(getString(rs, "COD_PADRE"));
        informe.setTipo(getString(rs, "TIPO"));
        informe.setEstado(getString(rs, "ESTADO"));
        */
        return informe;
    }


}
