package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.CuentaParticularPresupDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaParticularPresupMapper extends BaseMapper implements RowMapper<CuentaParticularPresupDTO> {
    @Override
    public CuentaParticularPresupDTO mapRow(ResultSet rs, int i) throws SQLException {
        CuentaParticularPresupDTO cta = new CuentaParticularPresupDTO();
        cta.setId(getInteger(rs, "cuen_ent_id"));
        cta.setCodPartida(getString(rs, "ent_part_codigo"));
        cta.setCodCapitulo(getString(rs, "ent_cap_codigo"));
        cta.setCodPrograma(getString(rs, "ent_prog_codigo"));
        cta.setCodSubtitulo(getString(rs, "cuen_ent_subtitulo"));
        cta.setCodItem(getString(rs, "cuen_ent_item"));
        cta.setCodAsignacion(getString(rs, "cuen_ent_asignacion"));
        cta.setCodSubAsignacion(getString(rs, "cuen_ent_subasignacion"));
        cta.setDescripcion(getString(rs, "cuen_ent_nombre"));
        cta.setEjercicio(getString(rs, "ejr_nombre"));
        cta.setNumeroDoc(getInteger(rs, "sis_numero_doc"));

        Integer numDoc = getInteger(rs, "sis_numero_doc");

        if (numDoc != null) {
            cta.setFolioAnio(numDoc.toString().concat(" / ").concat(getString(rs, "sis_anno")));
            cta.setIsDecreto(1);
        } else {
            cta.setFolioAnio("- / -");
            cta.setIsDecreto(0);
        }

        Integer flagImputacion = getInteger(rs, "cuen_ent_imputacion");

        if (flagImputacion.equals(1)) {
            cta.setImputable("SI");
        } else {
            cta.setImputable("NO");
        }

        String isValid = getString(rs, "cuen_ent_isvalid");

        if (isValid.isEmpty()) {
            cta.setActivo("SI");
        } else {
            cta.setActivo("NO");
        }

        return cta;
    }
}
