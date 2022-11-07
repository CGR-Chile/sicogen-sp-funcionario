package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.ReportesCumplimientoDTO;
import cl.contraloria.sicogen.model.ReportesEnvioDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ReporteEnvioMapper implements RowMapper<ReportesEnvioDTO> {


    @Override
    public ReportesEnvioDTO mapRow(ResultSet resultSet, int i) throws SQLException {

        ReportesEnvioDTO reporteEnvio = new ReportesEnvioDTO();

        reporteEnvio.setnTransaccion(resultSet.getString(6));
        reporteEnvio.setPerContable(resultSet.getString(3));
        reporteEnvio.setEnviado(resultSet.getString(4));
        reporteEnvio.setFechEnvio(resultSet.getString(5));
        reporteEnvio.setCertEnvio(resultSet.getString(6));
        //reporteCumplimiento.setFechEnvio(resultSet.getString(8));

        return reporteEnvio;

    }
}