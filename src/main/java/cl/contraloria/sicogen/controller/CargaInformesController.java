package cl.contraloria.sicogen.controller;


import cl.contraloria.sicogen.exceptions.SicogenException;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.FiltrosService;
import cl.contraloria.sicogen.service.InformesPersistencia;
import cl.contraloria.sicogen.service.InformesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
@RequestMapping("/carga")
public class CargaInformesController extends HttpServlet {

    private InformesService informesService;
    private FiltrosService filtrosService;

    public CargaInformesController(InformesService informesService,
                                   FiltrosService filtrosService) {
        this.informesService = informesService;
        this.filtrosService = filtrosService;
    }

    @RequestMapping(value = "/cargaInformes", method = RequestMethod.GET)
    public String getAll(ModelMap model) {
        List<Informe> informesPresupuestarios;
        informesPresupuestarios = informesService.getInformesPresupuestarios();
        model.addAttribute("informesPresupuestarios", informesPresupuestarios);
        return "cargaInformes";
    }

    @RequestMapping(value = "/getResumenErroresTDR", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ReporteErroresDTO> erroresTDR(@RequestParam Integer idFileUpload,
                                                        HttpServletRequest request,
                                                        ModelMap model) throws Exception {

        List<ReporteErroresDTO> listaReporteErrores = new ArrayList<ReporteErroresDTO>();

        String xmlErr = "";

        xmlErr = informesService.obtieneXMLErroresIC(idFileUpload);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document doc = null;

        db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xmlErr));

        try {
            doc = db.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("error");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    ReporteErroresDTO rep = new ReporteErroresDTO();
                    rep.setTipoError(eElement.getElementsByTagName("tipoError").item(0).getTextContent());
                    rep.setMensajeError(eElement.getElementsByTagName("mensaje").item(0).getTextContent());
                    listaReporteErrores.add(rep);
                }
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(listaReporteErrores, HttpStatus.OK);
    }

    @RequestMapping(value = "/getResumenErrores", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ReporteErroresDTO> errores(@RequestParam Integer idFileUpload,
                                                     HttpServletRequest request,
                                                     ModelMap model) throws Exception {

        List<ReporteErroresDTO> listaReporteErrores = new ArrayList<ReporteErroresDTO>();

        String xmlErr = "";

        xmlErr = informesService.obtieneXMLErroresIC(idFileUpload);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document doc = null;

        db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xmlErr));

        try {
            doc = db.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("error");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    ReporteErroresDTO rep = new ReporteErroresDTO();
                    rep.setTipoError(eElement.getElementsByTagName("tipoError").item(0).getTextContent());
                    rep.setMensajeError(eElement.getElementsByTagName("mensajeError").item(0).getTextContent());
                    listaReporteErrores.add(rep);
                }
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(listaReporteErrores, HttpStatus.OK);
    }

    @RequestMapping(value = "/getResumenErroresPI", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ReporteErroresDTO> erroresPI(@RequestParam Integer idFileUpload,
                                                       HttpServletRequest request,
                                                       ModelMap model) throws Exception {

        List<ReporteErroresDTO> listaReporteErrores = new ArrayList<ReporteErroresDTO>();

        String xmlErr;

        xmlErr = informesService.obtieneXMLErroresPI(idFileUpload);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document doc;

        db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xmlErr));

        try {
            doc = db.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("error");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    ReporteErroresDTO rep = new ReporteErroresDTO();
                    rep.setTipoError(eElement.getElementsByTagName("tipoError").item(0).getTextContent());
                    rep.setMensajeError(eElement.getElementsByTagName("mensajeError").item(0).getTextContent());
                    listaReporteErrores.add(rep);
                }
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(listaReporteErrores, HttpStatus.OK);
    }

    @RequestMapping(value = "/informeCargaSelected", method = RequestMethod.GET)
    public String informeCargaSelected(@RequestParam Integer idInforme,
                                       @RequestParam String informeName,
                                       Model model,
                                       HttpServletRequest request) {
        String page;
        List<EjerciciosDTO> ejercicios = filtrosService.getEjercicios();

        if (idInforme != 1) {
            List<EstadoSicogenDTO> estadosSicogen = filtrosService.getEstadosSicogen();
            model.addAttribute("estadosSicogen", estadosSicogen);
            page = "carga-informe/filtros-tdr";
        } else {
            HttpSession session = request.getSession(false);
            session.setAttribute("informeSelected", idInforme);
            session.setAttribute("informeName", informeName);
            page = "carga-informe/filtros-pi";
        }

        model.addAttribute("ejercicios", ejercicios);
        return page;
    }

    @RequestMapping(value = "/getPeriodoByInforme", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Periodos>> getPeriodoByInforme(@RequestParam Integer idEjercicio,
                                                              @RequestParam Integer idInforme) {
        List<Periodos> periodos = informesService.getPeriodosByInforme(idEjercicio, idInforme);
        return new ResponseEntity<List<Periodos>>(periodos, HttpStatus.OK);
    }

    @RequestMapping(value = "/obtenerListaTDR", method = RequestMethod.GET)
    public String obtenerListaTDR(@RequestParam Integer idInforme,
                                  @RequestParam Integer idEjercicio,
                                  @RequestParam Integer idEstadoSicogen,
                                  @RequestParam Integer idPeriodo,
                                  Model model,
                                  HttpServletRequest request) {
        List<InformeSistradocBO> informes = informesService.getInformesTDR(idInforme, idEjercicio, idEstadoSicogen, idPeriodo);
        HttpSession session = request.getSession();
        session.setAttribute("idEjercicio", idEjercicio);
        session.setAttribute("idPeriodo", idPeriodo);
        session.setAttribute("idEjercicioDigitacionTDR", idEjercicio);
        session.setAttribute("idInformeDigitacionTDR", idInforme);
        session.setAttribute("idEstadoSicogenDigitacionTDR", idEstadoSicogen);
        session.setAttribute("idPeriodoDigitacionTDR", idPeriodo);
        model.addAttribute("informes", informes);

        return "carga-informe/tabla-documentos-sistradoc";
    }

    @RequestMapping(value = "/cargaDigitacionTDR", method = RequestMethod.GET)
    public String cargaDigitacionTDR(@RequestParam Integer idInforme,
                                  @RequestParam Integer idEjercicio,
                                  @RequestParam Integer idEstadoSicogen,
                                  @RequestParam Integer idPeriodo,
                                  Model model,
                                  HttpServletRequest request) {

        List<InformeSistradocBO> informes = informesService.getInformesTDR(idInforme, idEjercicio, idEstadoSicogen, idPeriodo);
        model.addAttribute("informes", informes);
        return "carga-informe/tabla-documentos-sistradoc";
    }

    @GetMapping(value = "/obtenerArchivoPI")
    public String obtenerArchivoPI(@RequestParam String idEjercicio, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        InformePI informePI = informesService.obtenerArchivoPI(idEjercicio);
        session.setAttribute("informePICarga", informePI);
        model.addAttribute("informePI", informePI);
        model.addAttribute("validacionReglaBO", new ArrayList<ValidacionReglaBO>());
        return "carga-informe/tabla-carga-archivo-leypi";
    }

    @PostMapping(value = "/uploadFilePI")
    public String uploadFilePI(Model model,
                               HttpServletRequest request,
                               @RequestParam String anio,
                               @RequestParam("file") MultipartFile file) throws IOException {
        HttpSession session = request.getSession(false);
        UsuarioDTO usr = (UsuarioDTO) session.getAttribute("usr");
        String informeName = (String) session.getAttribute("informeName");
        Integer idInforme = (Integer) session.getAttribute("informeSelected");
        CargaInformePIDTO resultCarga = informesService.ejecutaCargaArchivoPI(
                anio,
                usr.getUserLogin(),
                file,
                Integer.valueOf(usr.getEntidadID()),
                informeName,
                String.valueOf(idInforme)
        );
        InformePI informePI = resultCarga.getInforme();
        session.setAttribute("informePICarga", informePI);
        model.addAttribute("informePI", informePI);
        model.addAttribute("validacionReglaBO", resultCarga.getErrores());
        return "carga-informe/tabla-carga-archivo-leypi";
    }

    @PostMapping(value = "/validarNegocioPI")
    public String validarNegocioPI(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        InformePI informePI = (InformePI) session.getAttribute("informePICarga");
        UsuarioDTO usr = (UsuarioDTO) session.getAttribute("usr");

        informesService.validarNegocioPI(informePI.getIdFileUpload(), usr.getUserLogin(), informePI);
        session.setAttribute("informePICarga", informePI);
        model.addAttribute("informePI", informePI);
        model.addAttribute("validacionReglaBO", new ArrayList<ValidacionReglaBO>());
        return "carga-informe/tabla-carga-archivo-leypi";
    }
}
