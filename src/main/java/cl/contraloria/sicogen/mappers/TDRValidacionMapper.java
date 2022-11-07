package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.EntidadEmisoraDTO;
import cl.contraloria.sicogen.model.TDRRevalidacionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TDRValidacionMapper implements RowMapper<TDRRevalidacionDTO> {

    @Override
    public TDRRevalidacionDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        TDRRevalidacionDTO revalidacion = new TDRRevalidacionDTO();

        revalidacion.setId(resultSet.getInt("SIS_DOCUMENTO_ID"));
        revalidacion.setEstadoSicogen(resultSet.getString("ESTADO_NOMBRE"));
        revalidacion.setIdEstadoSicogen(resultSet.getInt("SIS_ESTADO_SICOGEN"));
        revalidacion.setEstadoSistradoc(resultSet.getString("SIS_ESTADO_SISTRADOC"));
        revalidacion.setEntidadEmisora(resultSet.getString("SIS_ENTIDAD_EMISORA"));
        revalidacion.setTipoDoc(resultSet.getString("SIS_TIPO_DOC"));
        revalidacion.setNumeroDoc(resultSet.getInt("SIS_NUMERO_DOC"));
        revalidacion.setFechaDoc(resultSet.getString("SIS_FECHA_DOC"));
        revalidacion.setFechaRecepcionCGR(resultSet.getString("SIS_FECHA_RECEP_CGR"));
        revalidacion.setIdFileUpload(resultSet.getInt("SIS_FILEU_ID"));
        revalidacion.setEjercicioID(resultSet.getInt("EJR_ID"));
        revalidacion.setTipoInforme(resultSet.getInt("INF_ID"));
        revalidacion.setFechaValidacion(resultSet.getString("FILEU_DATE_VALIDATE"));

       return revalidacion;
    }
}
