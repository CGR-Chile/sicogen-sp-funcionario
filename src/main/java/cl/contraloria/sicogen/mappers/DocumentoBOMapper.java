package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.DocumentoBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentoBOMapper extends BaseMapper implements RowMapper<DocumentoBO> {
    @Override
    public DocumentoBO mapRow(ResultSet rs, int i) throws SQLException {
        DocumentoBO documentoBO = new DocumentoBO();
        documentoBO.setEntidadEmisora(getString(rs, "SIS_ENTIDAD_EMISORA"));
        documentoBO.setEstadoSicogen(getInteger(rs, "SIS_ESTADO_SICOGEN"));
        documentoBO.setEstadoSitradoc(getInteger(rs, "SIS_ESTADO_SISTRADOC"));
        documentoBO.setFileUId(getInteger(rs, "SIS_FILEU_ID"));
        documentoBO.setNumeroDoc(getInteger(rs, "SIS_NUMERO_DOC"));
        documentoBO.setId(getInteger(rs, "SIS_DOCUMENTO_ID"));
        return documentoBO;
    }
}
