package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.ProgramaBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramaBOMapper extends BaseMapper implements RowMapper<ProgramaBO> {
    @Override
    public ProgramaBO mapRow(ResultSet rs, int i) throws SQLException {
        ProgramaBO programaBO = new ProgramaBO();
        programaBO.setIdPrograma(getString(rs, "ENT_PROG_ID"));
        programaBO.setCodPrograma(getString(rs, "ENT_PROG_CODIGO"));
        programaBO.setNombre(getString(rs, "ENT_PROG_NOMBRE"));
        return programaBO;
    }
}
