package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.Bitacora;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BitacoraBOMapper extends BaseMapper implements RowMapper<Bitacora> {
    @Override
    public Bitacora mapRow(ResultSet resultSet, int i) throws SQLException {
        Bitacora bp = new Bitacora();
        bp.setFchaCreacion(getString(resultSet, "CREADO"));
        bp.setUserCreacion(getString(resultSet, "CREADOR"));
        bp.setFchaEdicion(getString(resultSet, "MODIFICADO"));
        bp.setUserEdicion(getString(resultSet, "MODIFICADOR"));
        bp.setFchaDesactivacion(getString(resultSet, "DESACTIVADO"));
        return bp;
    }
}
