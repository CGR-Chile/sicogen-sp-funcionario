package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.ComboDTO;
import cl.contraloria.sicogen.model.ReportesBitacoraDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComboTDRMapper implements RowMapper<ComboDTO> {

    @Override
    public ComboDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ComboDTO comboTDR = new ComboDTO();

        comboTDR.setCodPrograma(resultSet.getString("CODIGOPROGRAMA"));
        comboTDR.setNombreCombo(resultSet.getString("NOMBREPROGRAMA"));

       return comboTDR;
    }
}
