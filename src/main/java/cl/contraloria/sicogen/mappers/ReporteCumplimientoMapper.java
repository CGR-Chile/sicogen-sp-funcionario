package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.ReportesCumplimientoDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ReporteCumplimientoMapper implements RowMapper<ReportesCumplimientoDTO> {

    SimpleDateFormat sfmFechaEnvio;
    Date dtmFechaEnvio;
    @Override
    public ReportesCumplimientoDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        sfmFechaEnvio=new SimpleDateFormat("dd-MM-yyyy");
        ReportesCumplimientoDTO reporteCumplimiento = new ReportesCumplimientoDTO();
        reporteCumplimiento.setInforme(resultSet.getString(2));
        reporteCumplimiento.setPerContable(resultSet.getString(4));
        reporteCumplimiento.setPartida(resultSet.getString(5));
        reporteCumplimiento.setCapitulo(resultSet.getString(6));
        reporteCumplimiento.setEnviado(resultSet.getString(7));
        //reporteCumplimiento.setFechEnvio(resultSet.getString(8));

        dtmFechaEnvio=resultSet.getDate(8);
        if(dtmFechaEnvio!=null){
            reporteCumplimiento.setFechEnvio(sfmFechaEnvio.format(dtmFechaEnvio));
        }
        reporteCumplimiento.setFechEnvio(sfmFechaEnvio.format(dtmFechaEnvio));

        return reporteCumplimiento;

    }
}