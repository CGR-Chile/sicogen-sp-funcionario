package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.MonedaBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonedaMapper extends BaseMapper implements RowMapper<MonedaBO> {
    @Override
    public MonedaBO mapRow(ResultSet rs, int i) throws SQLException {
        MonedaBO monedaBO = new MonedaBO();
        monedaBO.setIdMoneda(getInteger(rs, "ID"));
        monedaBO.setCodMoneda(getString(rs, "CODIGO"));
        monedaBO.setDescMoneda(getString(rs, "NOMBRE"));
        return monedaBO;
    }
}
