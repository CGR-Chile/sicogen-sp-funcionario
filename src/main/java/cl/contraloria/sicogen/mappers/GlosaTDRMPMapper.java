package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.GlosaTDRMPDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GlosaTDRMPMapper extends BaseMapper implements RowMapper<GlosaTDRMPDTO> {
    @Override
    public GlosaTDRMPDTO mapRow(ResultSet rs, int i) throws SQLException {
        GlosaTDRMPDTO glosaTDRMPDTO = new GlosaTDRMPDTO();
        glosaTDRMPDTO.setIdGloTDRMP(getInteger(rs, "glo_tdrmp_id"));
        glosaTDRMPDTO.setNumGlosa(getString(rs, "glo_tdrmp_num"));
        glosaTDRMPDTO.setTextoGlosa(getString(rs, "glo_tdrmp_texto"));
        return glosaTDRMPDTO;
    }
}
