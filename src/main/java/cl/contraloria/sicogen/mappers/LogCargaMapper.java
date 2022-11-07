package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.LogCarga;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogCargaMapper implements RowMapper<LogCarga> {
    @Override
    public LogCarga mapRow(ResultSet rs, int i) throws SQLException {
        LogCarga carga = new LogCarga();
        carga.setCantRegistro(rs.getString("CANT"));
        carga.setEstado(rs.getString( "ESTADO"));
        return carga;
    }
}
