package cl.contraloria.sicogen.controller;


import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.InformesPersistencia;
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
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/documentos")
public class DocumentosController extends HttpServlet {

    private InformesService informesService;
    private InformesPersistencia informe;
    HttpSession session = null;
    UsuarioDTO u = null;

    public DocumentosController(InformesService informesService, InformesPersistencia informe) {
        this.informesService = informesService;
        this.informe = informe;
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

    @RequestMapping(value = "/cargaDocumentos", method = RequestMethod.GET)
    public String getAll(ModelMap model) {

        List<AnnoDTO> annos = new ArrayList<AnnoDTO>();
        List<PartidaDTO> entidades = new ArrayList<PartidaDTO>();
        List<TipoDocumentoDTO> tipoDocumento = new ArrayList<TipoDocumentoDTO>();

        try {
            annos = informesService.getAnnosDocumento();
            entidades = informesService.getEntidadesSistrador();
            tipoDocumento = informesService.getTipoDocumento();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("entidades", entidades);
        model.addAttribute("annos", annos);
        model.addAttribute("tipoDocumento", tipoDocumento);
        return "buscarDocumento";

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam String entidad,
                                 @RequestParam Integer anno,
                                 @RequestParam String tipo,
                                 @RequestParam String numero,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {


        List<DocumentoDTO> listDocumentos = new ArrayList<DocumentoDTO>();
        DocumentoDTO form = null;

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        BufferedWriter pr = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"));
        PrintWriter f = new PrintWriter(pr);
        f.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        f.println("<resultados>");


        try {
            listDocumentos = informesService.getFiltradoDocumentos(entidad, anno, tipo, numero);

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Iterator it = listDocumentos.iterator(); it.hasNext(); ) {
            form = (DocumentoDTO) it.next();
            f.println("<listaDocumentos>");
            f.println("<entidad>" + form.getEntidad() + "</entidad>");
            f.println("<numero>" + form.getNumero() + "</numero>");
            f.println("<tipo>" + form.getTipo() + "</tipo>");
            f.println("<reingreso>" + form.getReingreso() + "</reingreso>");
            f.println("<idFileUpload>" + form.getIdFileUpload() + "</idFileUpload>");
            f.println("<anno>" + form.getAnno() + "</anno>");
            f.println("<rowClass>" + form.getRowClass() + "</rowClass>");
            f.println("</listaDocumentos>");
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
}