package cl.contraloria.sicogen.controller;


import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.InformesPersistencia;
import cl.contraloria.sicogen.service.InformesService;
import cl.contraloria.sicogen.service.ReportesService;
import com.sun.org.apache.bcel.internal.generic.FieldOrMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ReportesController {

    private InformesService informesService;
    private ReportesService reportesService;
    private String nroCertificado;

    public ReportesController(InformesService informesService, ReportesService reportesService) {
        this.informesService = informesService;
        this.reportesService = reportesService;

    }
    public String getNroCertificado() {
        return nroCertificado;
    }


    public void setNroCertificado(String nroCertificado) {
        this.nroCertificado = nroCertificado;
    }

    @RequestMapping(value = "/cargaReportes", method = RequestMethod.GET)
    public String getAll(ModelMap model) {

    List<TipoInformeDTO> tipoInforme = new ArrayList<TipoInformeDTO>();
    List<EjerciciosDTO> ejercicios = new ArrayList<EjerciciosDTO>();
    List<TipoReporteDTO> tipoReportes = new ArrayList<TipoReporteDTO>();

        try {
            tipoInforme = informesService.gettipoInformes();
            ejercicios = informesService.getEjercicios();
            tipoReportes = informesService.getReporteTipo();

            tipoReportes.remove(3);
            tipoReportes.remove(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("tipoInforme", tipoInforme);
        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("tipoReportes", tipoReportes);
    return "reportes";

    }

    @RequestMapping(value = "/listReporteCumpl", method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam String tpInforme,
                                 @RequestParam Integer reporte,
                                 @RequestParam Integer ejercicio,
                                 @RequestParam Integer periodo,
                                 @RequestParam Integer informe,
                                 @RequestParam String partida,
                                 @RequestParam String capitulo,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {


        List<ReportesCumplimientoDTO> listReporteCumplimiento = new ArrayList<ReportesCumplimientoDTO>();
        ReportesCumplimientoDTO form = null;

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");


        try {
            listReporteCumplimiento = reportesService.getReportesCumplimiento(tpInforme, reporte, ejercicio, periodo, informe, partida, capitulo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        int fila = 0;
        for (Iterator it = listReporteCumplimiento.iterator(); it.hasNext(); ) {
            form = (ReportesCumplimientoDTO) it.next();
            form.setRowClass("row"+fila);
            f.println("<listaReportes>");
            f.println("<INFORME>" + form.getInforme() + "</INFORME>");
            f.println("<PERIODO>" + form.getPerContable() + "</PERIODO>");
            f.println("<PARTIDA>" + form.getPartida() + "</PARTIDA>");
            f.println("<CAPITULO>" + form.getCapitulo() + "</CAPITULO>");
            f.println("<RESPONSABLE_ENVIO>" + form.getEnviado() + "</RESPONSABLE_ENVIO>");
            f.println("<FECHA_ENVIO>" + form.getFechEnvio() + "</FECHA_ENVIO>");
            f.println("<rowClass>" + form.getRowClass() + "</rowClass>");
            f.println("</listaReportes>");

            if(fila == 0){
                fila=1;
            }
            else{
                fila=0;
            }
        }
        f.println("</resultados>");
        f.close();


        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/listReporteEnvioEntidad", method = RequestMethod.GET)
    public ResponseEntity getReporteEnvio(@RequestParam String tpInforme,
                                 @RequestParam String partida,
                                 @RequestParam Integer ejercicio,
                                 @RequestParam Integer periodo,
                                 @RequestParam String capitulo,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {


        List<ReportesEnvioDTO> listReporteEnvio = new ArrayList<ReportesEnvioDTO>();
        ReportesEnvioDTO form = null;

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");


        try {
            listReporteEnvio = reportesService.getReportesEnvio(tpInforme, partida, ejercicio, periodo, capitulo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        int fila = 0;
        for (Iterator it = listReporteEnvio.iterator(); it.hasNext(); ) {
            form = (ReportesEnvioDTO) it.next();
            form.setRowClass("row"+fila);
            f.println("<listaEnvio>");
            f.println("<TRANSACCION>" + form.getnTransaccion() + "</TRANSACCION>");
            f.println("<PERIODO>" + form.getPerContable() + "</PERIODO>");
            f.println("<RESPONSABLE_ENVIO>" + form.getEnviado() + "</RESPONSABLE_ENVIO>");
            f.println("<FECHA_ENVIO>" + form.getFechEnvio() + "</FECHA_ENVIO>");
            f.println("<CERT_ENVIO>" + form.getCertEnvio() + "</CERT_ENVIO>");
            f.println("<rowClass>" + form.getRowClass() + "</rowClass>");
            f.println("</listaEnvio>");

            if(fila == 0){
                fila=1;
            }
            else{
                fila=0;
            }
        }
        f.println("</resultados>");
        f.close();


        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/certificadoEnvio", method = RequestMethod.GET)
    public String getCertificado(ModelMap model, @RequestParam Integer certificado) {

        String nroCertificado ="";
        String nombreEntidad ="";
        String usuario ="";
        String fecha ="";
        String hora ="";
        String ejercicioCod ="";
        List<Informes>  salidaEnvioCgr = null;
        String qr ="";
        CertificadoEnvioDTO cert = new CertificadoEnvioDTO();

        try {
            cert = informesService.getCertificadoEnvioByCert(certificado);
            nroCertificado=cert.getCertificado();
            nombreEntidad=cert.getEntidad();
            usuario=cert.getUsuario();
            fecha=cert.getFecha().split(" ")[0];
            hora=cert.getFecha().split(" ")[1];
            ejercicioCod=cert.getEjercicio();
            salidaEnvioCgr=cert.getDetInf();
            qr=cert.getUsuario() + " " + cert.getEntidad() + " " + fecha + " " + hora + " " + getNroCertificado();
        } catch (Exception e) {
            e.printStackTrace();
        }


        model.addAttribute("salidaEnvioCgr", salidaEnvioCgr);
        model.addAttribute("qr", qr);
        model.addAttribute("usuario", usuario);
        model.addAttribute("nombreEntidad", nombreEntidad);
        model.addAttribute("ejercicioCod", ejercicioCod);
        model.addAttribute("nroCertificado", nroCertificado);
        model.addAttribute("fecha", fecha);
        model.addAttribute("hora", hora);
        return "reporteEnvio2";

    }


}
