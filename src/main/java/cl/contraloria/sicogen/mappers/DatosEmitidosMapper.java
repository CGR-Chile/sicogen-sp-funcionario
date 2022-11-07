package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.DatosEmitidosDTO;
import cl.contraloria.sicogen.model.ReportesBitacoraDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatosEmitidosMapper implements RowMapper<DatosEmitidosDTO> {

    @Override
    public DatosEmitidosDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        DatosEmitidosDTO datosEmitidos = new DatosEmitidosDTO();

        datosEmitidos.setDocumentoID(resultSet.getString("SIS_DOCUMENTO_ID"));
        datosEmitidos.setEntidadEmisora(resultSet.getString("SIS_ENTIDAD_EMISORA"));
        datosEmitidos.setTipoDoc(resultSet.getString("SIS_TIPO_DOC"));
        datosEmitidos.setNumeroDoc(resultSet.getString("SIS_NUMERO_DOC"));
        datosEmitidos.setFechaDoc(resultSet.getString("SIS_FECHA_DOC"));
        datosEmitidos.setFechaRecep(resultSet.getString("SIS_FECHA_RECEP_CGR"));
        datosEmitidos.setEstadoSistradoc(resultSet.getString("SIS_ESTADO_SISTRADOC"));
        datosEmitidos.setEstadoSicogen(resultSet.getString("SIS_ESTADO_SICOGEN"));
        datosEmitidos.setFechaTramitacion(resultSet.getString("SIS_FECHA_TRAMITACION"));
        datosEmitidos.setHoraTramitacion(resultSet.getString("HORA_TRAMITACION"));
        datosEmitidos.setFileID(resultSet.getString("FILEU_ID"));

       return datosEmitidos;
    }
}
