package cl.contraloria.sicogen.service;

//import cl.contraloria.sicogen.dao.EstadoInformesDAO;
import cl.contraloria.sicogen.dao.EstadoInformesDAO;
import cl.contraloria.sicogen.dao.InformesDAO;
import cl.contraloria.sicogen.dao.ReportesDAO;
import cl.contraloria.sicogen.model.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Service
public class ReportesService {

    private ReportesDAO reportesDAO;


    public ReportesService(ReportesDAO reportesDAO) {
        this.reportesDAO = reportesDAO;
    }

    public List<ReportesCumplimientoDTO> getReportesCumplimiento(String tpInforme, Integer reporte, Integer ejercicio, Integer periodo, Integer informe, String partidaRC, String capituloRC) throws SQLException {

        List<ReportesCumplimientoDTO> resultado = new ArrayList();
        resultado = reportesDAO.getReportesCumplimiento(tpInforme, reporte, ejercicio, periodo, informe, partidaRC, capituloRC);

        return resultado;
    }

    public List<ReportesEnvioDTO> getReportesEnvio(String tpInforme, String partidaRE, Integer ejercicio, Integer periodo, String capituloRE) throws SQLException {

        List<ReportesEnvioDTO> resultado = new ArrayList();
        resultado = reportesDAO.getReportesEnvio(tpInforme, partidaRE, ejercicio,periodo, capituloRE);

        return resultado;
    }


}
