package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.DetalleInformePIDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class CuentasInformePIMapper implements RowMapper<DetalleInformePIDTO> {

    @Override
    public DetalleInformePIDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        DetalleInformePIDTO detalle = new DetalleInformePIDTO();
        detalle.setMontoUSD(String.format(Locale.GERMAN, "%,d", Long.valueOf(rs.getString("MONTO_USD"))));
        detalle.setMontoCLP(String.format(Locale.GERMAN, "%,d", Long.valueOf(rs.getString("MONTO_CLP"))));
        detalle.setMensajeRegla1(rs.getString("MENSAJE_REGLA_1"));
        detalle.setGlosas(rs.getString("GLOSAS"));
        detalle.setDenominacionCuenta(rs.getString("DENOMICACION_CUENTA"));
        detalle.setCuenta(rs.getString("CTA_COMPLETA"));
        detalle.setTipoCuenta(rs.getString("TIPO_CUENTA"));
        detalle.setEstadoRegla1(rs.getString("ESTADO_REGLA_1"));
        detalle.setCodCapitulo(rs.getString("COD_CAPITULO"));
        detalle.setCodPartida(rs.getString("COD_PARTIDA"));
        detalle.setCodPrograma(rs.getString("COD_PROGRAMA"));
        detalle.setTipoErrorRegla(rs.getString("TIPO_ERROR_REGLA"));
        return detalle;
    }
}
