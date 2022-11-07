package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.InformeSistradocBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InformeSistradocMapper extends BaseMapper implements RowMapper<InformeSistradocBO> {
    @Override
    public InformeSistradocBO mapRow(ResultSet rs, int i) throws SQLException {
        InformeSistradocBO informeSistradocBO = new InformeSistradocBO();
        informeSistradocBO.setIdDocumento(getInteger(rs, "ID_SISTRADOC"));
        informeSistradocBO.setEstadoSicogen(getString(rs, "ESTADO_SICOGEN"));
        informeSistradocBO.setEstadoSistradoc(getString(rs, "ESTADO_SISTRADOC"));
        informeSistradocBO.setEntidadEmisora(getString(rs, "ENTIDAD_EMISORA"));
        informeSistradocBO.setTipoDocumento(getString(rs, "TIPO_DOCUMENTO"));
        informeSistradocBO.setNroDocumento(getInteger(rs, "NUMERO_DOCUMENTO"));
        informeSistradocBO.setFechaDocumento(getString(rs, "FECHA_DOCUMENTO"));
        informeSistradocBO.setFechaRecepcionCGR(getString(rs, "FECHA_RECEPCION_CGR"));
        informeSistradocBO.setAnalista(getString(rs, "ANALISTA"));
        informeSistradocBO.setMateria(getString(rs, "MATERIA"));
        informeSistradocBO.setIdUsuario(getString(rs, "USR_ID"));
        return informeSistradocBO;
    }
}
