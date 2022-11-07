package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.DatosXmlTDRMP;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatosXmlTDRMPMapper extends BaseMapper implements RowMapper<DatosXmlTDRMP> {
    @Override
    public DatosXmlTDRMP mapRow(ResultSet rs, int i) throws SQLException {
        DatosXmlTDRMP datosXmlTDRMP = new DatosXmlTDRMP();
        datosXmlTDRMP.setIdTdrMp(getInteger(rs, "tdrmp_id"));
        datosXmlTDRMP.setCodigoPartida(getString(rs, "codigopartida"));
        datosXmlTDRMP.setNombrePartida(getString(rs, "nombrepartida"));
        datosXmlTDRMP.setCodigoCapitulo(getString(rs, "codigocapitulo"));
        datosXmlTDRMP.setNombreCapitulo(getString(rs, "nombrecapitulo"));
        datosXmlTDRMP.setCodigoPrograma(getString(rs, "codigoprograma"));
        datosXmlTDRMP.setNombrePrograma(getString(rs, "nombreprograma"));
        datosXmlTDRMP.setCodigoPartidaContraparte(getString(rs, "codigopartidacontraparte"));
        datosXmlTDRMP.setNombrePartidaContraparte(getString(rs, "nombrepartidacontraparte"));
        datosXmlTDRMP.setCodigoCapituloContraparte(getString(rs, "codigocapitulocontraparte"));
        datosXmlTDRMP.setNombreCapituloContraparte(getString(rs, "nombrecapitulocontraparte"));
        datosXmlTDRMP.setCodigoProgramaContraparte(getString(rs, "codigoprogramacontraparte"));
        datosXmlTDRMP.setNOMBRE_PROGRAMA_CONTRAPARTE(getString(rs, "nombre_programa_contraparte"));
        datosXmlTDRMP.setNRO_TRANSFERENCIA(getString(rs, "nro_transferencia"));
        datosXmlTDRMP.setCodigoCuenta(getString(rs, "codigocuenta"));
        datosXmlTDRMP.setTipoCuenta(getString(rs, "subtitulo"));
        datosXmlTDRMP.setSubtitulo(getString(rs, "subtitulo"));
        datosXmlTDRMP.setItem(getString(rs, "item"));
        datosXmlTDRMP.setAsignacion(getString(rs, "asignacion"));
        datosXmlTDRMP.setSubAsignacion(getString(rs, "subasignacion"));
        datosXmlTDRMP.setDenominacion(getString(rs, "denominacion"));
        datosXmlTDRMP.setTipoMoneda(getString(rs, "tipomoneda"));
        datosXmlTDRMP.setSUBTITULO_CONTRAPARTE(getString(rs, "subtitulo_contraparte"));
        datosXmlTDRMP.setITEM_CONTRAPARTE(getString(rs, "item_contraparte"));
        datosXmlTDRMP.setASIGNACION_CONTRAPARTE(getString(rs, "asignacion_contraparte"));
        datosXmlTDRMP.setDENOMINACION_CONTRAPARTE(getString(rs, "denominacion_contraparte"));
        datosXmlTDRMP.setDET_TDRMP_MONTO_INCREMENTO(getString(rs, "det_tdrmp_monto_incremento"));
        datosXmlTDRMP.setDET_TDRMP_MONTO_DISMINUCION(getString(rs, "det_tdrmp_monto_disminucion"));
        return datosXmlTDRMP;
    }
}
