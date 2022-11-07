package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.CuentaTDRMPDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaTDRMPMapper extends BaseMapper implements RowMapper<CuentaTDRMPDTO> {
    @Override
    public CuentaTDRMPDTO mapRow(ResultSet rs, int i) throws SQLException {
        CuentaTDRMPDTO cuentaTDRMPDTO = new CuentaTDRMPDTO();
        cuentaTDRMPDTO.setIdDetTDRMP(getLong(rs, "det_tdrmp_id"));
        cuentaTDRMPDTO.setIdTDRMP(getLong(rs, "tdrmp_id"));
        cuentaTDRMPDTO.setCodCuenta(getString(rs, "codigocuenta"));
        cuentaTDRMPDTO.setTipoCuenta(getString(rs, "tipocuenta"));
        cuentaTDRMPDTO.setSubtitulo(getString(rs, "subtitulo"));
        cuentaTDRMPDTO.setItem(getString(rs, "item"));
        cuentaTDRMPDTO.setAsignacion(getString(rs, "asignacion"));
        cuentaTDRMPDTO.setSubAsignacion(getString(rs, "subasignacion"));
        cuentaTDRMPDTO.setDenominacion(getString(rs, "denominacion"));
        cuentaTDRMPDTO.setTipoMoneda(getString(rs, "tipomoneda"));
        cuentaTDRMPDTO.setMtoIncremento(getInteger(rs, "det_tdrmp_monto_incremento"));
        cuentaTDRMPDTO.setMtoDisminucion(getInteger(rs, "det_tdrmp_monto_disminucion"));
        cuentaTDRMPDTO.setIsDecreto(getInteger(rs, "cuen_ent_isdecreto"));
        return cuentaTDRMPDTO;
    }
}
