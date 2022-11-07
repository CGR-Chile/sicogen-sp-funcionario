package cl.contraloria.sicogen.controller;


import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.ExcelService;
import cl.contraloria.sicogen.service.InformesPersistencia;
import cl.contraloria.sicogen.service.InformesService;
import cl.contraloria.sicogen.utils.Convertidor;
import cl.contraloria.sicogen.utils.Utils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Map;

@Controller
@RequestMapping("/validacion")
public class ValidacionReporteController {

    private InformesService informesService;
    private String htmlRV;
    private InformacionGeneralRV infoGeneral = null;
    private String usuario;
    private int entidad;
    private int estado;
    private String mensaje;
    private Convertidor convertidor;
    private ExcelService excelService;
    private ServletContext servletContext;

    public ValidacionReporteController(InformesService informesService,
                                       InformesPersistencia informe,
                                       Convertidor convertidor,
                                       ExcelService excelService,
                                       ServletContext servletContext) {
        this.informesService = informesService;
        //this.informe = informe;
        this.convertidor = convertidor;
        this.excelService = excelService;
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/Reporte", method = RequestMethod.GET)
    public String obtenerValidacionIC(ModelMap model, @RequestParam Integer idFileUp, HttpServletRequest request){

        String xmlPI = "";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document doc = null;

        try{

            xmlPI = informesService.obtieneXMLInformePI(idFileUp);
            //logger.info("String PI: "+xmlPI);

            // Convertir el String en un XML con JAXB !!
            db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlPI));

            doc = db.parse(is);
            String message = doc.getDocumentElement().getTextContent();
            //logger.info("XML PI: "+message);

        }catch(Exception ex){
            ex.printStackTrace();
            return "error";
        }


        String xmlErrPI = "";
        //Llamar al DAO de PI para rescatar el XML con Errores del Informe
        try{
            xmlErrPI = informesService.obtieneXMLErroresPI(idFileUp);
           // logger.info("XML ERRORES PI: "+xmlErrPI);

        }catch(Exception ex){
            ex.printStackTrace();
            return "ERROR";
        }


        Element root = doc.getDocumentElement();
        Node nodo = doc.createElement("listaErrores");
        root.appendChild(nodo);

        //logger.info("root: "+root.toString());

        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            xmlPI = writer.toString();
            //logger.info("XML PI Formato String: \n" + xmlPI);

        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Reemplazar el TAG Vacio de Errores por la Lista !!
        String xmlRVPI = xmlPI.replace("<listaErrores/>", xmlErrPI);
        //logger.info("xmlRVPI con Errores: \n"+xmlRVPI);

        // Forma de implementar la JSP para desplegar la informacion OK !!
        try{

            this.htmlRV = convertidor.obtieneHtmlRV(xmlRVPI);
            //logger.info("htmlRV: "+htmlRV);

        }catch(Exception ex){
            ex.printStackTrace();
            return "ERROR";
        }


        // Invocar al DAO para obtener Informacion General del Reporte de Validacion
        try{
            infoGeneral = informesService.obtieneInfoGeneralRV(idFileUp);
           // logger.info("Informacion General PI: "+infoGeneral.getInforme());

            infoGeneral.setIdFileIp(idFileUp.toString());
        }catch(Exception ex){
            ex.printStackTrace();
            return "ERROR";
        }
        return "ReporteValidacionIC";
    }

    @RequestMapping (value = "/EnvioInforme", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity getValidandose(HttpServletRequest request, @RequestParam Integer periodo){

       HttpSession session = null;
       InformesPendientes informesValidandose =new InformesPendientes();
       Utils util = new Utils();

        this.estado = 0;
        //int periodo = 0;
        session = request.getSession(false);
        UsuarioDTO u = (UsuarioDTO) session.getAttribute("usr");
        try{
            setEntidad(Integer.valueOf(u.getEntidadID()));
            setUsuario(u.getUserLogin());
        }
        catch(Exception ex){
            //logger.error(ex.getMessage());
            setEstado(-1);
            this.setMensaje("Error al recuperar el usuario cargado en sesion");
            //return SUCCESS;
        }

        //InformesDAO informesdao = new InformesDAO();
        //this.informesValidandose = informesdao.getInformesValidandose(this.entidad,periodo,ejercicio,tipoArchivo);
        if(informesValidandose.getInformesPendientes().size()>0){
            for(int i=0;i<informesValidandose.getInformesEstados().size();i++){
                Informes inf2=new Informes();
                inf2=util.getImagenEstado(Integer.parseInt(informesValidandose.getInformesEstados().get(i).getInformeEstadoId()),
                        informesValidandose.getInformesEstados().get(i).getInformeId()	);

                informesValidandose.getInformesEstados().get(i).setInformeMensaje(" ");
                informesValidandose.getInformesEstados().get(i).setImgCarga(inf2.getImgCarga());
                informesValidandose.getInformesEstados().get(i).setImgValid(inf2.getImgValid());
                informesValidandose.getInformesEstados().get(i).setImgRV(inf2.getImgRV());
                informesValidandose.getInformesEstados().get(i).setInformeAccion(inf2.getInformeAccion());
                informesValidandose.getInformesEstados().get(i).setInformeMensaje(inf2.getInformeMensaje());

                if(("2").equals(informesValidandose.getInformesEstados().get(i).getInfEstadoFlujo())){
                    this.setEstado(99);
                    setMensaje("Se estan validando informes actualmente");
                }
            }
            for(int i=0;i<informesValidandose.getInformesPendientes().size();i++){
                Informes inf2=new Informes();
                inf2=util.getImagenEstado(Integer.parseInt(informesValidandose.getInformesPendientes().get(i).getInformeEstadoId()),
                        informesValidandose.getInformesPendientes().get(i).getInformeId());

                informesValidandose.getInformesPendientes().get(i).setInformeMensaje(" ");
                informesValidandose.getInformesPendientes().get(i).setImgCarga(inf2.getImgCarga());
                informesValidandose.getInformesPendientes().get(i).setImgValid(inf2.getImgValid());
                informesValidandose.getInformesPendientes().get(i).setImgRV(inf2.getImgRV());
                informesValidandose.getInformesPendientes().get(i).setInformeAccion(inf2.getInformeAccion());
                informesValidandose.getInformesPendientes().get(i).setInformeMensaje(inf2.getInformeMensaje());

                if(("2").equals(informesValidandose.getInformesPendientes().get(i).getInfEstadoFlujo())){
                    this.setEstado(99);
                    setMensaje("Se estan validando informes actualmente");
                }
            }
        }
        if(this.getEstado() !=99){
            this.setMensaje("No hay Informes pendiente");
        }

        return new ResponseEntity(informesValidandose, HttpStatus.OK);
    }

    @GetMapping(path = "/reportes/informes/pi")
    public String obtieneReportePI(ModelMap model, @RequestParam Integer idFileUp) {
        model.addAttribute("datosInforme", informesService.getDatosInformePI(idFileUp));
        model.addAttribute("idFileUp", idFileUp);
        return "ReporteValidacionPI";
    }

    @GetMapping(path = "/obtenerValidacionIC")
    public String obtenerValidacionIC(ModelMap model, @RequestParam Integer idFileUp) {
        InformacionGeneralRV infoGeneralRV = informesService.obtieneInfoGeneralRV(idFileUp);
        infoGeneralRV.setIdFileIp(idFileUp.toString());
        model.addAttribute("infoGeneralRV", infoGeneralRV);
        model.addAttribute("idArchivo", idFileUp);
        model.addAttribute("datosInforme", informesService.obtieneDatosInformeContable(idFileUp));
        return "ReporteValidacionIC";
    }

    @PostMapping(path = "/datosPaginacion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReporteValidacionPaginacion> obtieneDatosPaginacion(HttpServletRequest  request) {
        return new ResponseEntity<ReporteValidacionPaginacion>(informesService.obtieneReporteValidacionPaginacion(
                Integer.valueOf(request.getParameter("idArchivo")),
                Integer.valueOf(request.getParameter("start")),
                Integer.valueOf(request.getParameter("length")),
                Integer.valueOf(request.getParameter("draw")),
                "0"
        ), HttpStatus.OK);
    }

    @GetMapping(path = "/downExcelIC")
    public ResponseEntity descargaRvExcelIC(@RequestParam Integer idFileUp) {
        try {
            Map<String, InputStreamResource> result = excelService.generaExcelReporteValidacion(
                    servletContext.getResource("/resources/img/sicogenii_logo.png"),
                    idFileUp,
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

    @GetMapping(path = "/downExcelValidacionPI")
    public ResponseEntity downExcelValidacionPI(@RequestParam Integer idFileUp) {
        try {
            Map<String, InputStreamResource> result = excelService.generaExcelReporteValidacionPI(
                    servletContext.getResource("/resources/img/sicogenii_logo.png"),
                    idFileUp,
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getEntidad() {
        return entidad;
    }

    public void setEntidad(int entidad) {
        this.entidad = entidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
