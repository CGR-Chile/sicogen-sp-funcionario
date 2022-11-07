package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.ProgramaPresupuestarioDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CapituloMapper implements RowMapper<ProgramaPresupuestarioDTO> {

    @Override
    public ProgramaPresupuestarioDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ProgramaPresupuestarioDTO capitulo = new ProgramaPresupuestarioDTO();

        capitulo.setIdCapitulo(Integer.valueOf(resultSet.getString("ID")));
        capitulo.setNombreCapitulo(resultSet.getString("NOMBRE"));
        capitulo.setCodCapitulo(resultSet.getString("CODCAPITULO"));

        return capitulo;
    }
}
