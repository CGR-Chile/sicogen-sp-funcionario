package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.CuentaParticularPresupDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CtasPartDepenMapper extends BaseMapper implements RowMapper<CuentaParticularPresupDTO> {
    @Override
    public CuentaParticularPresupDTO mapRow(ResultSet rs, int i) throws SQLException {
        CuentaParticularPresupDTO cta = new CuentaParticularPresupDTO();
        String subt = getString(rs, "cuen_ent_subtitulo");
        String itm = getString(rs, "cuen_ent_item");
        String asig = getString(rs, "cuen_ent_asignacion");
        String subasig = getString(rs, "cuen_ent_subasignacion");
        cta.setCodCuenta(subt.concat(".").concat(itm).concat(".").concat(asig).concat(".").concat(subasig));
        cta.setDescripcion(getString(rs, "cuen_ent_nombre"));
        return cta;
    }
}
