package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.ProgramaCompletoBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramaCompletoMapper extends BaseMapper implements RowMapper<ProgramaCompletoBO> {
    @Override
    public ProgramaCompletoBO mapRow(ResultSet rs, int i) throws SQLException {
        ProgramaCompletoBO programaCompletoBO = new ProgramaCompletoBO();
        programaCompletoBO.setCodigoPrograma(getString(rs, "ENT_PROG_CODIGO"));
        programaCompletoBO.setNombrePrograma(getString(rs, "ENT_PROG_NOMBRE"));
        programaCompletoBO.setCodigoCapitulo(getString(rs, "ENT_CAP_CODIGO"));
        programaCompletoBO.setNombreCapitulo(getString(rs, "ENT_CAP_NOMBRE"));
        programaCompletoBO.setCodigoPartida(getString(rs, "ENT_PART_CODIGO"));
        programaCompletoBO.setNombrePartida(getString(rs, "ENT_PART_NOMBRE"));
        return programaCompletoBO;
    }
}
