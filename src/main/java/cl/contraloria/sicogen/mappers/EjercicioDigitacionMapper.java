package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.EjerciciosDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EjercicioDigitacionMapper extends BaseMapper implements RowMapper<EjerciciosDTO> {
    @Override
    public EjerciciosDTO mapRow(ResultSet rs, int i) throws SQLException {
        EjerciciosDTO ejerciciosDTO = new EjerciciosDTO();
        ejerciciosDTO.setEjercicioId(getInteger(rs, "EJR_ID"));
        return ejerciciosDTO;
    }
}
