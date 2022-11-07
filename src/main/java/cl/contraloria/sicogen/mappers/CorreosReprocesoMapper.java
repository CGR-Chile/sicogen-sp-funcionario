package cl.contraloria.sicogen.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CorreosReprocesoMapper extends BaseMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet rs, int i) throws SQLException {
        return getString(rs, "USR_MAIL");
    }
}
