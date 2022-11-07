package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.InformePI;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InformePIMapper extends BaseMapper implements RowMapper<InformePI> {
    @Override
    public InformePI mapRow(ResultSet rs, int i) throws SQLException {
        InformePI informePI = new InformePI();
        informePI.setNombre(getString(rs, "NOMBRE_INF"));
        informePI.setCodigo(getString(rs, "CODIGO"));

        try {
            informePI.setEstadoSicogen(getString(rs, "ESTADO"));
            informePI.setNombreArchivo(getString(rs, "NOMBRE_XML"));
            informePI.setIdFileUpload(getString(rs, "ID_ARCHIVO"));
        } catch (Exception ex) {
            return informePI;
        }

        return informePI;
    }
}
