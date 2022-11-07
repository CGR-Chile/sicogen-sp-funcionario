package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.DetTDRMPDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetTDRMPMapper extends BaseMapper implements RowMapper<DetTDRMPDTO> {
    @Override
    public DetTDRMPDTO mapRow(ResultSet rs, int i) throws SQLException {
        DetTDRMPDTO detTDRMPDTO = new DetTDRMPDTO();
        detTDRMPDTO.setCodCuenta(getString(rs, "CODIGO_CUENTA"));
        detTDRMPDTO.setDesMoneda(getString(rs, "MONEDA_DESCRIPCION"));
        detTDRMPDTO.setDetTDRMPId(getInteger(rs, "DET_TDRMP_ID"));
        detTDRMPDTO.setMontoDisminucion(getInteger(rs, "MONTO_DISMINUCION"));
        detTDRMPDTO.setMontoIncremento(getInteger(rs, "MONTO_INCREMENTO"));
        detTDRMPDTO.setNomCuenta(getString(rs, "CUEN_ENT_NOMBRE"));
        detTDRMPDTO.setTDRMPId(getInteger(rs, "TDRMP_ID"));
        detTDRMPDTO.setTipoModCuenta(getString(rs, "TIPO_CUENTA"));
        return detTDRMPDTO;
    }
}
