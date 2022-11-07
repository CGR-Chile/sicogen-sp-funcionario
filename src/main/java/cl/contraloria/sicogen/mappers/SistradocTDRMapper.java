package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.InformeSistradocBO;
import cl.contraloria.sicogen.model.ReportesEnvioDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SistradocTDRMapper implements RowMapper<InformeSistradocBO> {


    @Override
    public InformeSistradocBO mapRow(ResultSet resultSet, int i) throws SQLException {

        InformeSistradocBO informe = new InformeSistradocBO();

        informe.setIdDocumento(resultSet.getInt(1));
        informe.setEstadoSicogen(resultSet.getString(2));
        informe.setEstadoSistradoc(resultSet.getString(3));
        informe.setEntidadEmisora(resultSet.getString(4));
        informe.setTipoDocumento(resultSet.getString(5));
        informe.setNroDocumento(resultSet.getInt(6));
        informe.setFechaDocumento(resultSet.getString(7));
        informe.setFechaRecepcionCGR(resultSet.getString(8));
        informe.setAnalista(resultSet.getString(9));
        informe.setMateria(resultSet.getString(10));
        return informe;

    }
}