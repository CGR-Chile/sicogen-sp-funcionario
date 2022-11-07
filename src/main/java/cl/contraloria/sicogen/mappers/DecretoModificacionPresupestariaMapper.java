package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.DecretoModificacionPresupestariaDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DecretoModificacionPresupestariaMapper extends BaseMapper implements RowMapper<DecretoModificacionPresupestariaDTO> {
    @Override
    public DecretoModificacionPresupestariaDTO mapRow(ResultSet rs, int i) throws SQLException {
        DecretoModificacionPresupestariaDTO decretoModificacionPresupestariaDTO = new DecretoModificacionPresupestariaDTO();
        decretoModificacionPresupestariaDTO.setIdTDRMP(getLong(rs, "tdrmp_id"));
        decretoModificacionPresupestariaDTO.setCodPartida(getString(rs, "codigopartida"));
        decretoModificacionPresupestariaDTO.setNomPartida(getString(rs, "nombrepartida"));
        decretoModificacionPresupestariaDTO.setCodCapitulo(getString(rs, "codigocapitulo"));
        decretoModificacionPresupestariaDTO.setNomCapitulo(getString(rs, "nombrecapitulo"));
        decretoModificacionPresupestariaDTO.setCodPrograma(getString(rs, "codigoprograma"));
        decretoModificacionPresupestariaDTO.setNomPrograma(getString(rs, "nombreprograma"));
        return decretoModificacionPresupestariaDTO;
    }
}
