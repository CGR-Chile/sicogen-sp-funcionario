package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.CapituloBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CapituloBOMapper extends BaseMapper implements RowMapper<CapituloBO> {
    @Override
    public CapituloBO mapRow(ResultSet rs, int i) throws SQLException {
        CapituloBO capituloBO = new CapituloBO();
        capituloBO.setIdCapitulo(getString(rs, "ENT_CAP_ID"));
        capituloBO.setCodCapitulo(getString(rs, "ENT_CAP_CODIGO"));
        capituloBO.setNombre(getString(rs, "ENT_CAP_NOMBRE"));
        return capituloBO;
    }
}
