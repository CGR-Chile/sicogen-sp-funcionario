package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.Informe;
import cl.contraloria.sicogen.model.UsuarioDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ByTipoInformeMapper implements RowMapper<Informe> {

    @Override
    public Informe mapRow(ResultSet resultSet, int i) throws SQLException {
        Informe informeTipo = new Informe();
        informeTipo.setId(resultSet.getString("INFORME_ID")); //setId( Integer.valueOf(rs.getInt("PERIODO_ID")) );
        informeTipo.setNombre(resultSet.getString("NOMBRE"));
        return informeTipo;
    }
}
