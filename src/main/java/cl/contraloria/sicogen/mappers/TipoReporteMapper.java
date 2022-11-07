package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.TipoInformeDTO;
import cl.contraloria.sicogen.model.TipoReporteDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoReporteMapper implements RowMapper<TipoReporteDTO> {

    @Override
    public TipoReporteDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        TipoReporteDTO tipoReporte = new TipoReporteDTO();
        tipoReporte.setId(resultSet.getInt(1));
        tipoReporte.setNombre(resultSet.getString(3));

        return tipoReporte;

    }
}