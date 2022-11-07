package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.Informe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoComboPresupuestarioMapper implements RowMapper<Informe> {

    @Override
    public Informe mapRow(ResultSet resultSet, int i) throws SQLException {
        Informe presupuestario = new Informe();
        presupuestario.setId(resultSet.getString("ID"));
        presupuestario.setNombre(resultSet.getString("NOMBRE"));
        presupuestario.setCodigo(resultSet.getString("CODIGO"));
        return presupuestario;

    }
}