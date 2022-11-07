package cl.contraloria.sicogen.controller;


import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.InformesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
@RequestMapping("/cargacombos")
public class CargaCombosController extends HttpServlet {

    private InformesService informesService;

     public CargaCombosController(InformesService informesService) {
        this.informesService = informesService;

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

    @RequestMapping (value = "/partida", method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam Integer ejercicioId,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response) throws IOException {


        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");
        List<ProgramaPresupuestarioDTO> listaPartida = null;
        ProgramaPresupuestarioDTO form = null;
        try {
            listaPartida = informesService.getlistaPartida(ejercicioId);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //for (Iterator it = informesEstadosAnualBO.getEstados().iterator(); it.hasNext(); ) {
        for (Iterator it = listaPartida.iterator(); it.hasNext(); ) {
            form = (ProgramaPresupuestarioDTO) it.next();
            f.println("<listado>");

            f.println("<PARTIDA_ID>" + form.getIdPartida() + "</PARTIDA_ID>");
            f.println("<PARTIDA_NOMBRE>" + form.getNombrePartida() + "</PARTIDA_NOMBRE>");
            f.println("<PARTIDA_CODIGO>" + form.getCodPartida() + "</PARTIDA_CODIGO>");
            f.println("</listado>");
          }

        f.println("</resultados>");
        f.close();

    //model.addAttribute("listEstInfAnual", listEstInfAnual);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping (value = "/capitulo", method = RequestMethod.GET)
    public ResponseEntity getCapitulo(@RequestParam Integer ejercicioId,
                                      @RequestParam Integer idPartida,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {


        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");
        List<ProgramaPresupuestarioDTO> listaCapitulo = null;
        ProgramaPresupuestarioDTO form = null;
        try {
            listaCapitulo = informesService.getlistaCapitulo(idPartida, ejercicioId);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //for (Iterator it = informesEstadosAnualBO.getEstados().iterator(); it.hasNext(); ) {
        for (Iterator it = listaCapitulo.iterator(); it.hasNext(); ) {
            form = (ProgramaPresupuestarioDTO) it.next();
            f.println("<listadoCapitulo>");

            f.println("<CAPITULO_ID>" + form.getIdCapitulo() + "</CAPITULO_ID>");
            f.println("<CAPITULO_NOMBRE>" + form.getNombreCapitulo() + "</CAPITULO_NOMBRE>");
            f.println("<CAPITULO_CODIGO>" + form.getCodCapitulo() + "</CAPITULO_CODIGO>");
            f.println("</listadoCapitulo>");
        }

        f.println("</resultados>");
        f.close();

        //model.addAttribute("listEstInfAnual", listEstInfAnual);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping (value = "/informesTipo", method = RequestMethod.GET)
    public ResponseEntity getInformes(@RequestParam Integer tpInformes,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {


        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");
        List<Informe> informeTipo = new ArrayList<Informe>();
        Informe form = null;
        try {
            informeTipo = informesService.getInformesByTipoInformes(tpInformes);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //for (Iterator it = informesEstadosAnualBO.getEstados().iterator(); it.hasNext(); ) {
        for (Iterator it = informeTipo.iterator(); it.hasNext(); ) {
            form = (Informe) it.next();
            f.println("<listado>");
            f.println("<ID_TIPOINFORME>" + form.getId() + "</ID_TIPOINFORME>");
            f.println("<NOMBRE_INFORME>" + form.getNombre() + "</NOMBRE_INFORME>");
            f.println("</listado>");
        }

        f.println("</resultados>");
        f.close();

        //model.addAttribute("listEstInfAnual", listEstInfAnual);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping (value = "/listPeriodos", method = RequestMethod.GET)
    public ResponseEntity getPeriodos(@RequestParam Integer idEjercicio,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {


        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");
        List<Periodos> listPeriodos = new ArrayList<Periodos>();
        Periodos form = null;
        try {
            listPeriodos = informesService.getPeriodos(idEjercicio);

        } catch (Exception e) {
            e.printStackTrace();
        }

         for (Iterator it = listPeriodos.iterator(); it.hasNext(); ) {
            form = (Periodos) it.next();
            f.println("<listado>");
            f.println("<ID_PERIODO>" + form.getPeriodoId() + "</ID_PERIODO>");
            f.println("<PERIODO_CODIGO>" + form.getPeriodoCodigo() + "</PERIODO_CODIGO>");
            f.println("</listado>");
        }
        f.println("</resultados>");
        f.close();

        //model.addAttribute("listEstInfAnual", listEstInfAnual);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping (value = "/listPartidas", method = RequestMethod.GET)
    public ResponseEntity getPartidas(@RequestParam Integer idEjercicio,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {


        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");
        List<Partida> listPartida = new ArrayList<Partida>();
        Partida form = null;
        try {
            listPartida = informesService.getListPartida(idEjercicio);

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Iterator it = listPartida.iterator(); it.hasNext(); ) {
            form = (Partida) it.next();
            f.println("<listado>");
            f.println("<ID_PARTIDA>" + form.getNombre() + "</ID_PARTIDA>");
            f.println("<PARTIDA_CODIGO>" + form.getCodigo() + "</PARTIDA_CODIGO>");
            f.println("</listado>");
        }
        f.println("</resultados>");
        f.close();

        //model.addAttribute("listEstInfAnual", listEstInfAnual);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping (value = "/listInformesPeriodo", method = RequestMethod.GET)
    public ResponseEntity getInformesPeridos(@RequestParam Integer idPeriodo,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {


        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");
        List<Informe> listInformesByPeriodo = new ArrayList<Informe>();
        Informe form = null;
        try {
            listInformesByPeriodo = informesService.getInformesByPeriodo(idPeriodo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Iterator it = listInformesByPeriodo.iterator(); it.hasNext(); ) {
            form = (Informe) it.next();
            f.println("<listado>");
            f.println("<ID_INFORME>" + form.getId() + "</ID_INFORME>");
            f.println("<NOMBRE_INFORME>" + form.getNombre() + "</NOMBRE_INFORME>");
            f.println("</listado>");
        }
        f.println("</resultados>");
        f.close();

        //model.addAttribute("listEstInfAnual", listEstInfAnual);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping (value = "/getPeriodosByInformes", method = RequestMethod.GET)
    public ResponseEntity getPeridosInforme(@RequestParam Integer idEjercicio,
                                            @RequestParam Integer idInforme,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws IOException {


        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");
        List<Periodos> listaPeriodos = new ArrayList<Periodos>();
        Periodos form = null;
        try {
            listaPeriodos = informesService.getPeriodosByInforme(idEjercicio, idInforme);

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Iterator it = listaPeriodos.iterator(); it.hasNext(); ) {
            form = (Periodos) it.next();
            f.println("<listado>");
            f.println("<ID_PERIODO>" + form.getPeriodoId() + "</ID_PERIODO>");
            f.println("<NOMBRE_PERIODO>" + form.getPeriodoNombre() + "</NOMBRE_PERIODO>");
            f.println("</listado>");
        }
        f.println("</resultados>");
        f.close();

        //model.addAttribute("listEstInfAnual", listEstInfAnual);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/combosTDR", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Periodos> listaCombosTDR(@RequestParam String partida,
                                                   @RequestParam String capitulo,
                                                   @RequestParam Integer tipoInforme,
                                                   @RequestParam Integer ejercicio,
                                                   @RequestParam Integer informe,
                                                   @RequestParam Integer periodo2,
                                                  HttpServletRequest request,
                                                  ModelMap model) {

        List<ComboDTO> listaCombo =  new ArrayList<ComboDTO>();

        listaCombo = informesService.getDatosCombo(partida, capitulo, tipoInforme, ejercicio, informe, periodo2);


        return new ResponseEntity(listaCombo, HttpStatus.OK);
    }


}
