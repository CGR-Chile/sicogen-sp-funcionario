package cl.contraloria.sicogen.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodoEjercicioMapper extends BaseMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet rs, int i) throws SQLException {
        try {
            return getString(rs, "PEJR_ID");
        } catch (Exception ex) {
            return null;
        }
    }
}
