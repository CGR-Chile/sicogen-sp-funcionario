package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.Mantenedores;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MantenedoresMapper extends BaseMapper implements RowMapper<Mantenedores> {
    @Override
    public Mantenedores mapRow(ResultSet rs, int i) throws SQLException {
        Mantenedores mantenedores = new Mantenedores();
        mantenedores.setMantId(getString(rs, "MANTENEDOR_ID"));
        mantenedores.setMantCod(getString(rs, "CODIGO"));
        mantenedores.setMantNombre(getString(rs, "MANTENEDOR"));
        mantenedores.setMantAccion(getString(rs, "ACCION"));
        mantenedores.setMantVigencia(getString(rs, "VIGENCIA"));
        return mantenedores;
    }
}
