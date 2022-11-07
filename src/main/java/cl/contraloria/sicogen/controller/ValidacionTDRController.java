package cl.contraloria.sicogen.controller;


import cl.cgr.www.OSB.SectorPublico.TDRPI.V1.EXP.ValidacionInforme.ValidarInforme;
import cl.contraloria.sicogen.dao.InformesTDRDAO;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.*;
import cl.contraloria.sicogen.xml.XMLDocumentoTDR_II;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/validacion")
public class ValidacionTDRController extends HttpServlet {

    private InformesService informesService;
    private InformesPersistencia informe;
    HttpSession session = null;
    UsuarioDTO u = null;
    private ExcelService excelService;
    private ServletContext servletContext;
    private PdfService pdfService;
    private InformesTDRDAO informesTDRDAO;
    private DigitacionService digitacionService;

    public ValidacionTDRController(InformesService informesService,
                                   InformesPersistencia informe,
                                   ExcelService excelService,
                                   ServletContext servletContext,
                                   PdfService pdfService,
                                   InformesTDRDAO informesTDRDAO,
                                   DigitacionService digitacionService) {
        this.informesService = informesService;
        this.informe = informe;
        this.excelService = excelService;
        this.servletContext = servletContext;
        this.pdfService = pdfService;
        this.informesTDRDAO = informesTDRDAO;
        this.digitacionService = digitacionService;
    }

    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).
                favorParameter(true).
                parameterName("mediaType").
                ignoreAcceptHeader(true).
                useJaf(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("json", MediaType.APPLICATION_JSON);
    }
    @RequestMapping(value = "/cargaValidacion", method = RequestMethod.GET)
    public String getAll(ModelMap model) {

        List<EntidadEmisoraDTO> entidadEmisora = new ArrayList<EntidadEmisoraDTO>();
        List<TipoDocumentoTDRDTO> tipoDocumentoTDR = new ArrayList<TipoDocumentoTDRDTO>();

        entidadEmisora = informesService.getEntidadesEmisoras();
        tipoDocumentoTDR = informesService.getTipoDocumentosTDR();


        model.addAttribute("entidadEmisora", entidadEmisora);
        model.addAttribute("tipoDocumentoTDR", tipoDocumentoTDR);
        return "validacionTDR";

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam String entidad,
                                 @RequestParam String anno,
                                 @RequestParam String tipo,
                                 @RequestParam String numero,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {


        List<TDRRevalidacionDTO> listaRevalidaciones = new ArrayList<TDRRevalidacionDTO>();
        TDRRevalidacionDTO form = null;

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");

        if(numero.trim().equals("")){
            numero = "-1";
        }

        int Anno = 0;
        if(!anno.equals("")){
           Anno = Integer.parseInt(anno);
        }
        if(entidad.equals("")){
            entidad = "-1";
        }
        try {
            listaRevalidaciones = informesService.getTDRparaRevalidacion(Anno, Integer.parseInt(numero), entidad, tipo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        int fila = 0;
        for (Iterator it = listaRevalidaciones.iterator(); it.hasNext(); ) {

            form = (TDRRevalidacionDTO) it.next();
            form.setRowClass("row"+fila);
            f.println("<listaDocumentosTDR>");
            f.println("<IdValidacion>" + form.getId() + "</IdValidacion>");
            f.println("<EstadoSicogen>" + form.getEstadoSicogen()  + "</EstadoSicogen>");
            f.println("<IdEstadoSicogen>" + form.getIdEstadoSicogen() + "</IdEstadoSicogen>");
            f.println("<EstadoSistradoc>" + form.getEstadoSistradoc() + "</EstadoSistradoc>");
            f.println("<EntidadEmisora>" + form.getEntidadEmisora() + "</EntidadEmisora>");
            f.println("<TipoDoc>" + form.getTipoDoc() + "</TipoDoc>");
            f.println("<NumeroDoc>" + form.getNumeroDoc() + "</NumeroDoc>");
            f.println("<FechaDoc>" + form.getFechaDoc() + "</FechaDoc>");
            f.println("<IdFileUpload>" + form.getIdFileUpload() + "</IdFileUpload>");
            f.println("<EjercicioID>" + form.getEjercicioID() + "</EjercicioID>");
            f.println("<TipoInforme>" + form.getTipoInforme() + "</TipoInforme>");
            f.println("<FechaValidacion>" + form.getFechaValidacion() + "</FechaValidacion>");
            f.println("<rowClass>" + form.getRowClass() + "</rowClass>");
            f.println("</listaDocumentosTDR>");
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


    @RequestMapping(value = "/descargaDoc", method = RequestMethod.GET)
    public ResponseEntity geFile(@RequestParam Integer idArchivo,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        session = request.getSession(false);
         u = (UsuarioDTO) session.getAttribute("usr");


        InformeArchivoDet informeArchivo;
        InputStream inputStream = null;
        String datos = "";
        String nombreArchivo = "";
        informeArchivo = informesService.getArchivo(idArchivo);

        nombreArchivo=informeArchivo.getListaDetalle().get(0).getNombre();
        String cad = "";
        int i = 0;

        try {
            for(i=0; i<informeArchivo.getListaDetalle().size(); i++) {
                if (i == informeArchivo.getListaDetalle().size()-1) {
                    cad = cad + informeArchivo.getListaDetalle().get(i).getLinea();
                } else if(i != informeArchivo.getListaDetalle().size()-1) {
                    cad = cad + informeArchivo.getListaDetalle().get(i).getLinea() + "\r\n";
                }
            }
        }catch (Exception ex){
            ex.fillInStackTrace();
        }
        inputStream=new ByteArrayInputStream(cad.getBytes("UTF-8"));


        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + nombreArchivo);


            OutputStream out = response.getOutputStream();

            byte[] buffer = new byte[4096];

            int numBytesRead;
            while ((numBytesRead = inputStream.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }

       out.flush();


        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/validarNegocioTDR", method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam String idFileUpload,
                                 @RequestParam String ejercicio,
                                 @RequestParam String nroSistradoc,
                                 @RequestParam String informe,
                                 @RequestParam String tipoInforme,
                                 @RequestParam String idInforme,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {

        InformeSistradocBO informeSistradoc = new InformeSistradocBO();
        InformeTDR informeTDR = new InformeTDR();
        String userName = "";
        session = request.getSession(false);
        u = (UsuarioDTO) session.getAttribute("usr");
        userName = u.getUserLogin();

        try {

            javax.xml.rpc.holders.StringHolder responseEstado = new javax.xml.rpc.holders.StringHolder();
            javax.xml.rpc.holders.StringHolder responseMensaje =  new javax.xml.rpc.holders.StringHolder();
            javax.xml.rpc.holders.StringHolder responseException =  new javax.xml.rpc.holders.StringHolder();
            javax.xml.rpc.holders.StringHolder responseFechaInicio =  new javax.xml.rpc.holders.StringHolder();
            javax.xml.rpc.holders.StringHolder responseFechaFin =  new javax.xml.rpc.holders.StringHolder();

            int idTDR = Integer.parseInt(idInforme);
            switch (idTDR) {

                case 2: // TDR II
                    // ID_WS = 10 Corresponde a Validacion TDR II
                    cl.cgr.www.OSB.SectorPublico.TDRII.V1.EXP.ValidacionInforme.ValidarInforme validarInformeTDRII = new cl.cgr.www.OSB.SectorPublico.TDRII.V1.EXP.ValidacionInforme.ValidarInformeProxy(10);
                    cl.cgr.www.OSB.SectorPublico.TDRII.V1.EXP.ValidacionInforme.holders.ListaErroresHolder responseListaErroresHolderII = new cl.cgr.www.OSB.SectorPublico.TDRII.V1.EXP.ValidacionInforme.holders.ListaErroresHolder();

                    validarInformeTDRII.validarInforme(userName, new BigDecimal(String.valueOf(idFileUpload)),
                            responseEstado, responseMensaje, responseListaErroresHolderII, responseException, responseFechaInicio, responseFechaFin);

                    break;

                case 11: // TDR MP
                    // ID_WS = 9 Corresponde a Validacion TDR MP
                    cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.ValidarInforme validarInformeTDRMP = new cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.ValidarInformeProxy(9);
                    cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.holders.ListaErroresHolder responseListaErroresHolderMP = new cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme.holders.ListaErroresHolder();

                    validarInformeTDRMP.validarInforme(userName, new BigDecimal(String.valueOf(idFileUpload)),
                            responseEstado, responseMensaje, responseListaErroresHolderMP, responseException, responseFechaInicio, responseFechaFin);

                    break;

                case 12: // TDR PI
                    // ID_WS = 8 Corresponde a Validacion TDR PI
                    cl.cgr.www.OSB.SectorPublico.TDRPI.V1.EXP.ValidacionInforme.ValidarInforme validarInformeTDRPI = (ValidarInforme) new cl.cgr.www.OSB.SectorPublico.TDRPI.V1.EXP.ValidacionInforme.ValidarInformeProxy(8);
                    cl.cgr.www.OSB.SectorPublico.TDRPI.V1.EXP.ValidacionInforme.holders.ListaErroresHolder responseListaErroresHolderPI = new cl.cgr.www.OSB.SectorPublico.TDRPI.V1.EXP.ValidacionInforme.holders.ListaErroresHolder();

                    validarInformeTDRPI.validarInforme(userName, new BigDecimal(String.valueOf(idFileUpload)),
                            responseEstado, responseMensaje, responseListaErroresHolderPI, responseException, responseFechaInicio, responseFechaFin);

                    break;

                default: // No existen mas TDR !!!
            }


        informeSistradoc = informesTDRDAO.getInformeTDR(nroSistradoc);

        informeSistradoc.setEjercicio(ejercicio);
        informeSistradoc.setInforme(informe);
        informeSistradoc.setTipoInforme(tipoInforme);
        informeSistradoc.setNombre(informe);

        informeTDR = informesTDRDAO.obtenerArchivoTDR(ejercicio, nroSistradoc, idInforme);

        informeTDR.setNombre(informe);
        informeTDR.setEstadoSicogen(responseEstado.value);
        }catch (Exception ex){
            ex.fillInStackTrace();
        }




        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/obtenerValidacionTDR")
    public String obtenerValidacionTDR(ModelMap model,
                                       @RequestParam Integer idFileUp,
                                       @RequestParam String idSistradoc,
                                       HttpServletRequest request) throws Exception {
        InformacionGeneralRV datosInforme = informesService.obtieneInfoGeneralRV(idFileUp);
        InformeSistradocBO datosSistradoc = informesService.getInformeTDR(idSistradoc);
        InformeTDRRV datosXML = informesService.getDatosXMLInformeTDR(idFileUp);
        model.addAttribute("datosInforme", datosInforme);
        model.addAttribute("datosSistradoc", datosSistradoc);
        model.addAttribute("idFileUp", idFileUp);
        model.addAttribute("idSistradoc", idSistradoc);
        model.addAttribute("datosXML", datosXML);

        HttpSession session = request.getSession(false);
        Integer informe = (Integer) session.getAttribute("infSegTDRMP");

        if (informe.equals(11)) {
            return "ReporteValidacionTDRMP";
        } else {
            model.addAttribute("objXmlII", digitacionService.obtenerXmlTDRII(idFileUp));
            model.addAttribute("listaErrores", informesService.obtenerListaErroresTDR(idFileUp));
            return "ReporteValidacionTDRII";
        }
    }

    @GetMapping(path = "/obtenerValidacionTDRII")
    public String obtenerValidacionTDRII(ModelMap model,
                                       @RequestParam Integer idFileUp,
                                       @RequestParam String idSistradoc) throws Exception {
        InformacionGeneralRV datosInforme = informesService.obtieneInfoGeneralRV(idFileUp);
        InformeSistradocBO datosSistradoc = informesService.getInformeTDR(idSistradoc);
        InformeTDRRV datosXML = informesService.getDatosXMLInformeTDR(idFileUp);
        XMLDocumentoTDR_II objXmlII = digitacionService.obtenerXmlTDRII(Integer.valueOf(idSistradoc));
        model.addAttribute("datosInforme", datosInforme);
        model.addAttribute("datosSistradoc", datosSistradoc);
        model.addAttribute("idFileUp", idFileUp);
        model.addAttribute("idSistradoc", idSistradoc);
        model.addAttribute("datosXML", datosXML);
        model.addAttribute("objXmlII", objXmlII);
        return "ReporteValidacionTDRII";
    }

    @GetMapping(path = "/downExcelTDRMP")
    public ResponseEntity downExcelTDRMP(@RequestParam Integer idFileUp,
                                            @RequestParam String idSistradoc) {
        try {
            Map<String, InputStreamResource> result = excelService.generaExcelReporteValidacionTDR(
                    servletContext.getResource("/resources/img/sicogenii_logo.png"),
                    idFileUp,
                    idSistradoc,
                    servletContext.getResource("/resources/img/Bloqueante1.png"),
                    servletContext.getResource("/resources/img/Bloqueante2.png"),
                    servletContext.getResource("/resources/img/Bloqueante3.png"));
            String filename = null;

            for (Map.Entry<String, InputStreamResource> entry : result.entrySet()) {
                filename = entry.getKey();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                    .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    .header(HttpHeaders.SET_COOKIE, "fileDownload=true; path=/")
                    .body(result.get(filename));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    @GetMapping(path = "/downPDFValidacionTDRMP")
    public ResponseEntity downPDFValidacionTDRMP(@RequestParam Integer idFileUp,
                                         @RequestParam String idSistradoc) {
        try {
            Map<String, InputStreamResource> result = pdfService.generaPdfReporteValidacionTDRMP(
                    servletContext.getResource("/resources/img/sicogenii_logo.png"),
                    idFileUp,
                    idSistradoc,
                    servletContext.getResource("/resources/img/Bloqueante1.png"),
                    servletContext.getResource("/resources/img/Bloqueante2.png"),
                    servletContext.getResource("/resources/img/Bloqueante3.png"));
            String filename = null;

            for (Map.Entry<String, InputStreamResource> entry : result.entrySet()) {
                filename = entry.getKey();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                    .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    .header(HttpHeaders.SET_COOKIE, "fileDownload=true; path=/")
                    .body(result.get(filename));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }
}