package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.BitacoraSistradocDTO;
import cl.contraloria.sicogen.model.ReportesBitacoraDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BitacoraSistradocMapper implements RowMapper<BitacoraSistradocDTO> {

    @Override
    public BitacoraSistradocDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        BitacoraSistradocDTO bitacora = new BitacoraSistradocDTO();

        bitacora.setIdBitacora(resultSet.getInt("ID_BITACORA"));
        bitacora.setIdDocumento(resultSet.getInt("DOCUMENTO_ID"));
        bitacora.setIdUsuario(resultSet.getInt("USR_ID"));
        bitacora.setNombreUsuario(resultSet.getString("USR_NICK"));
        bitacora.setOperacion(resultSet.getString("OPERACION"));
        bitacora.setFechaCreacion(resultSet.getString("FECHA"));

       return bitacora;
    }
}
