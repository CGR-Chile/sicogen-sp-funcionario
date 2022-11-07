package cl.contraloria.sicogen.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartidaIdMapper implements RowMapper<Integer> {
    @Override
    public Integer mapRow(ResultSet rs, int i) throws SQLException {
        return rs.getInt("ENT_PART_ID");
    }
}
