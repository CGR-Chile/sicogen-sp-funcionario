package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.Informe;
import cl.contraloria.sicogen.model.TipoReporteDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoComboPeriodoMapper implements RowMapper<Informe> {

    @Override
    public Informe mapRow(ResultSet resultSet, int i) throws SQLException {
        Informe tipoInforme = new Informe();
        tipoInforme.setId(resultSet.getString(1));
        tipoInforme.setNombre(resultSet.getString(2));
        return tipoInforme;

    }
}