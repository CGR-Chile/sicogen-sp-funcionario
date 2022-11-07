package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EtapasCompromisoMantenedorMapper extends BaseMapper implements RowMapper<JsonJTableExpenseBean> {
    @Override
    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {
        JsonJTableExpenseBean informes = new JsonJTableExpenseBean();
        informes.setAreaID(getString(rs, "ID"));
        informes.setEtapa(getString(rs, "ETAPA"));
        informes.setNombre(getString(rs, "NOMBRE"));
        informes.setRut(getString(rs, "RUT"));
        informes.setAnulacion(getString(rs, "ANULACION"));
        return informes;
    }





}
