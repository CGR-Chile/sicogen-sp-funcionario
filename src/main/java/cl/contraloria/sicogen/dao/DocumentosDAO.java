package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.DocumentoBOMapper;
import cl.contraloria.sicogen.model.DocumentoBO;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class
 DocumentosDAO {

    private static final String PKG_SISTRADOC = "PKG_SISTRADOC";
    private JdbcTemplate jdbcTemplate;

    public DocumentosDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public DocumentoBO getDocumento(long idDocumento) {
        SimpleJdbcCall llamada = getDocumentoCall();
        Map<String, Object> parametros = getDocumentoParams(idDocumento);
        Map<String, Object> respuesta = llamada.execute(parametros);
        List<DocumentoBO> results = (List<DocumentoBO>) respuesta.get("CURSOR_DOCUMENTO");
        return results.get(0);
    }

    private Map<String, Object> getDocumentoParams(long idDocumento) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pDOCUMENTO_ID", idDocumento);
        return parametros;
    }

    private SimpleJdbcCall getDocumentoCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SISTRADOC)
                .withProcedureName("GET_PROGRAMA_BY_DOCUMENTO")
                .declareParameters(
                        new SqlParameter("pDOCUMENTO_ID", OracleTypes.NUMERIC),
                        new SqlOutParameter("CURSOR_DOCUMENTO", OracleTypes.CURSOR, new DocumentoBOMapper())
                );
    }
}
