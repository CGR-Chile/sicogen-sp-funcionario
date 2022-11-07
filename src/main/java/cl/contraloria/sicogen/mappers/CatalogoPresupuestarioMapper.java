package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.CatalogoPresupuestario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogoPresupuestarioMapper extends BaseMapper implements RowMapper<CatalogoPresupuestario> {
    @Override
    public CatalogoPresupuestario mapRow(ResultSet rs, int i) throws SQLException {
        CatalogoPresupuestario catalogoPresupuestario = new CatalogoPresupuestario();
        catalogoPresupuestario.setCuenta(getString(rs, "cod_cta"));
        catalogoPresupuestario.setNombre(getString(rs, "nom_cta"));
        return catalogoPresupuestario;
    }
}
