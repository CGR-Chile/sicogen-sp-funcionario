package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.CtasContablesSimples;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CtasContSimplesMapper extends BaseMapper implements RowMapper<CtasContablesSimples> {
    @Override
    public CtasContablesSimples mapRow(ResultSet resultSet, int i) throws SQLException {
        CtasContablesSimples ccs = new CtasContablesSimples();

        ccs.setAgregacion(resultSet.getString("AGREGACION"));
        ccs.setDescripcion(resultSet.getString("NOMBRE"));
        ccs.setIdRegistro(resultSet.getString("ID_REGISTRO"));
        ccs.setN1(resultSet.getString("NIVEL1"));
        ccs.setN2(resultSet.getString("NIVEL2"));
        ccs.setN3(resultSet.getString("NIVEL3"));
        ccs.setTabla(resultSet.getString("TABLA"));
        return ccs;
    }
}
