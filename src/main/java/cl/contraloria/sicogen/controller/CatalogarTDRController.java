package cl.contraloria.sicogen.controller;

import cl.contraloria.sicogen.exceptions.SicogenException;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.InformesService;
import cl.contraloria.sicogen.service.SistradocService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/catalogar-tdr")
public class CatalogarTDRController extends HttpServlet {

    private InformesService informesService;
    private SistradocService sistradocService;

    public CatalogarTDRController(InformesService informesService,
                                  SistradocService sistradocService) {
        this.informesService = informesService;
        this.sistradocService = sistradocService;
    }

    @GetMapping(value = "/inicio")
    public String getAll(ModelMap model) throws Exception {

        List<Informe> informesPresupuestarios = informesService.getInformesPresupuestarios();
        List<EjerciciosDTO> ejercicios = informesService.getEjercicios();
        List<InformeSistradocBO> informesSistradoc = informesService.getInformesAsigTDR();

        List<Informe> informesPresupuestariosFiltrado = new ArrayList<Informe>();

        for (Informe inf : informesPresupuestarios) {
            if (inf.getCodigo().contains("TDR")) {
                informesPresupuestariosFiltrado.add(inf);
            }
        }

        model.addAttribute("listaInformesTDR", informesPresupuestariosFiltrado);
        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("informesSistradoc", informesSistradoc);

        return "catalogar-tdr/catalogar-tdr";

    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getAll(HttpServletResponse response) throws IOException {


        List<InformeSistradocBO> listinformesTDR = new ArrayList<InformeSistradocBO>();
        InformeSistradocBO form = null;

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");


        try {
            listinformesTDR = informesService.getInformesAsigTDR();

        } catch (Exception e) {
            e.printStackTrace();
        }
        int fila = 0;
        for (Iterator it = listinformesTDR.iterator(); it.hasNext(); ) {
            form = (InformeSistradocBO) it.next();
            form.setRowClass("row" + fila);
            f.println("<listaTDR>");
            f.println("<ENTIDAD_EMISORA>" + form.getIdDocumento() + "-" + form.getEntidadEmisora() + "</ENTIDAD_EMISORA>");
            f.println("<TIPO_DOCUMENTO>" + form.getTipoDocumento() + "</TIPO_DOCUMENTO>");
            f.println("<NUMERO_DOCUMENTO>" + form.getNroDocumento() + "</NUMERO_DOCUMENTO>");
            f.println("<FECHA_DOCUMENTO>" + form.getFechaDocumento() + "</FECHA_DOCUMENTO>");
            f.println("<FECHA_RECEPCION>" + form.getFechaRecepcionCGR() + "</FECHA_RECEPCION>");
            f.println("<CATALOGADO>" + form.getEstadoSistradoc() + "</CATALOGADO>");
            f.println("<ID_SISTRADOC>" + form.getIdDocumento() + "</ID_SISTRADOC>");
            f.println("<rowClass>" + form.getRowClass() + "</rowClass>");
            if (fila == 0) {
                fila = 1;
            } else {
                fila = 0;
            }
            f.println("</listaTDR>");
        }
        f.println("</resultados>");
        f.close();


        return new ResponseEntity<Object>(HttpStatus.OK);

    }


    @RequestMapping(value = "/asignarDocumentosTDR", method = RequestMethod.GET)
    public ResponseEntity getAsignarDocumentosTDR(@RequestParam Integer idInforme,
                                                  @RequestParam Integer idEjercicio,
                                                  @RequestParam Integer idPeriodo,
                                                  @RequestParam String idocs,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response) throws IOException {

        AsignacionDTO asigBO = new AsignacionDTO();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");

        try {
            f.println("<mensaje>");

            asigBO = informesService.asignarTDR(idInforme, idEjercicio, idPeriodo, idocs);
            f.println("<estado>" + asigBO.getCodigo() + "</estado>");
            f.println("<texto>" + asigBO.getMensaje() + "</texto>");
            f.println("</mensaje>");

        } catch (Exception e) {
            e.printStackTrace();
        }
        f.println("</resultados>");
        f.close();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/buscarAsignacionTDR")
    public String cargaTablaInformesSistradoc(Model model) throws Exception {
        List<InformeSistradocBO> informesSistradoc = informesService.getInformesAsigTDR();
        model.addAttribute("informesSistradoc", informesSistradoc);
        return "catalogar-tdr/tabla-informes-sistradoc";
    }

    @PostMapping(value = "/asignarDocumentosTDR", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> asignarDocumentosTDR(@RequestBody CatalogarTDRDTO catalogarTDRDTO,
                                                                   HttpServletRequest request) {
        ResultadoEjecucion result = new ResultadoEjecucion();
        HttpSession session = request.getSession();
        UsuarioDTO userSession = (UsuarioDTO) session.getAttribute("usr");

        try {
            String idDocs = catalogarTDRDTO.getIdDocs();
            idDocs = idDocs.substring(1);
            idDocs = idDocs.replace('$',',');
            catalogarTDRDTO.setIdDocs(idDocs);
            catalogarTDRDTO.setIdUser(userSession.getUserId());
            result = sistradocService.asignarTDR(catalogarTDRDTO);
        } catch (SicogenException ex) {
            result.setCodEjec("-1");
            result.setMsgEjec(ex.getMensaje());
        } catch (Exception ex) {
            result.setCodEjec("-1");
            result.setMsgEjec(ex.getMessage());
        }

        return new ResponseEntity<ResultadoEjecucion>(result, HttpStatus.OK);
    }
}