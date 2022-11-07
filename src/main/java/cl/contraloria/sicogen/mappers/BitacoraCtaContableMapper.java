package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.BitacoraCtaContableBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BitacoraCtaContableMapper implements RowMapper<BitacoraCtaContableBO> {
    @Override
    public BitacoraCtaContableBO mapRow(ResultSet rs, int i) throws SQLException {
        BitacoraCtaContableBO bitacora = new BitacoraCtaContableBO();
        bitacora.setFchaCreacion(rs.getString("CREADO"));
        bitacora.setUserCreacion(rs.getString("CREADOR"));
        bitacora.setFchaEdicion(rs.getString("EDITADO"));
        bitacora.setUserEdicion(rs.getString("MODIFICADOR"));
        bitacora.setFchaDesactivacion(rs.getString("DESACTIVADO"));
        return bitacora;
    }
}
