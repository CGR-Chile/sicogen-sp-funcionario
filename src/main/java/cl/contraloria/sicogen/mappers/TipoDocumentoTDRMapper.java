package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.EntidadEmisoraDTO;
import cl.contraloria.sicogen.model.TipoDocumentoTDRDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoDocumentoTDRMapper implements RowMapper<TipoDocumentoTDRDTO> {

    @Override
    public TipoDocumentoTDRDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        TipoDocumentoTDRDTO tipo = new TipoDocumentoTDRDTO();

        tipo.setId(resultSet.getInt("TIPO_ID"));
        tipo.setNombre(resultSet.getString("TIPO_NOMBRE"));
        tipo.setCodigoInterno(resultSet.getString("TIPO_COD_INTERNO"));

       return tipo;
    }
}
