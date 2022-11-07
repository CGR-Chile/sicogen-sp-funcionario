package cl.contraloria.sicogen.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CapituloIdMapper implements RowMapper<Integer> {
    @Override
    public Integer mapRow(ResultSet rs, int i) throws SQLException {
        return rs.getInt("ENT_CAP_ID");
    }
}
