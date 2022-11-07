package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.GlosaTDRMPDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GlosaXmlTDRPMapper extends BaseMapper implements RowMapper<GlosaTDRMPDTO> {
    @Override
    public GlosaTDRMPDTO mapRow(ResultSet rs, int i) throws SQLException {
        GlosaTDRMPDTO glosaTDRMPDTO = new GlosaTDRMPDTO();
        glosaTDRMPDTO.setIdTDRMP(getInteger(rs, "tdrmp_id"));
        glosaTDRMPDTO.setIdDetTDRMP(getInteger(rs, "det_tdrmp_id"));
        glosaTDRMPDTO.setTipo(getString(rs, "glo_tdrmp_tipo"));
        glosaTDRMPDTO.setNumGlosa(getString(rs, "glo_tdrmp_num"));
        glosaTDRMPDTO.setTextoGlosa(getString(rs, "glo_tdrmp_texto"));
        glosaTDRMPDTO.setIndPresup(getString(rs, "glo_tdrmp_ind_presup"));
        glosaTDRMPDTO.setCodPart(getString(rs, "glo_tdrmp_cod_part"));
        glosaTDRMPDTO.setCodCap(getString(rs, "glo_tdrmp_cod_cap"));
        glosaTDRMPDTO.setCodProg(getString(rs, "glo_tdrmp_cod_prog"));
        glosaTDRMPDTO.setNomPart(getString(rs, "glo_tdrmp_nom_part"));
        glosaTDRMPDTO.setNomCap(getString(rs, "glo_tdrmp_nom_cap"));
        glosaTDRMPDTO.setNomProg(getString(rs, "glo_tdrmp_nom_prog"));
        return glosaTDRMPDTO;
    }
}
