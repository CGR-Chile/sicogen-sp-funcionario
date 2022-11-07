package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.ProyectosIIBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProyectosIIMapper extends BaseMapper implements RowMapper<ProyectosIIBO> {
    @Override
    public ProyectosIIBO mapRow(ResultSet rs, int i) throws SQLException {
        ProyectosIIBO proyectosIIBO = new ProyectosIIBO();
        proyectosIIBO.setCodigo(getString(rs, "PRYBIP_CODIGO"));
        proyectosIIBO.setDigito(getString(rs, "PRYBIP_DV"));
        proyectosIIBO.setNombre(getString(rs, "PRYBIP_NOMBRE"));
        return proyectosIIBO;
    }
}
