package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.DatosEntidadInformePIDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class EntidadesInformePIMapper implements RowMapper<DatosEntidadInformePIDTO> {

    @Override
    public DatosEntidadInformePIDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        DatosEntidadInformePIDTO datos = new DatosEntidadInformePIDTO();
        datos.setCodPrograma(rs.getString("COD_PROGRAMA"));
        datos.setCodPartida(rs.getString("COD_PARTIDA"));
        datos.setCodCapitulo(rs.getString("COD_CAPITULO"));
        datos.setTotalGastosCLP(String.format(Locale.GERMAN, "%,d", Long.valueOf(rs.getString("TOTAL_GASTOS_CLP"))));
        datos.setTotalIngresosCLP(String.format(Locale.GERMAN, "%,d", Long.valueOf(rs.getString("TOTAL_INGRESOS_CLP"))));
        datos.setCapitulo(rs.getString("CAPITULO"));
        datos.setPartida(rs.getString("PARTIDA"));
        datos.setPrograma(rs.getString("PROGRAMA"));
        return datos;
    }
}
