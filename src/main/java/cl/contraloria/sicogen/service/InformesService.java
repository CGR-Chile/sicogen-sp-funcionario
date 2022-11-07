package cl.contraloria.sicogen.service;

import cl.contraloria.sicogen.constants.ReporteValidacionConstants;
import cl.contraloria.sicogen.dao.*;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.utils.TestXML;
import cl.contraloria.sicogen.utils.Utils;
import cl.contraloria.sicogen.webservices.reproceso.correo.CorreoReprocesoClient;
import cl.contraloria.sicogen.webservices.reproceso.correo.EnviarCorreoReprocesoRequestType;
import cl.contraloria.sicogen.webservices.reproceso.ic.*;
import cl.contraloria.sicogen.webservices.validacion.carga.pi.*;
import cl.contraloria.sicogen.webservices.validacion.informe.tdrmp.ValidarInforme;
import cl.contraloria.sicogen.webservices.validacion.informe.tdrmp.ValidarInformeClient;
import cl.contraloria.sicogen.webservices.validacion.informe.tdrmp.ValidarInformeResponse;
import cl.contraloria.sicogen.xml.ListaErrores;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.codec.binary.Base64;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.SocketException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

import static cl.contraloria.sicogen.utils.InfoRed.getListHostAdresses;
import static cl.contraloria.sicogen.utils.QRCodeGenerator.generateQRCodeImage;

@Service
public class InformesService {

    private InformesDAO informesDAO;
    private EstadoInformesDAO estadoInformesDAO;
    private InformesTDRDAO informesTDRDAO;
    private ProcesarInformesClient procesarInformesClient;
    private CorreoReprocesoClient correoReprocesoClient;
    private ValidarInformeClient validarInformeClient;
    private PiDAO piDAO;
    private ParametrosDAO parametrosDAO;
    private ValidarCargaClient validarCargaPIClient;
    private cl.contraloria.sicogen.webservices.validacion.informe.pi.ValidarInformeClient validarInformePIClient;

    public InformesService(InformesDAO informesDAO,
                           EstadoInformesDAO estadoInformesDAO,
                           InformesTDRDAO informesTDRDAO,
                           ProcesarInformesClient procesarInformesClient,
                           CorreoReprocesoClient correoReprocesoClient,
                           ValidarInformeClient validarInformeClient,
                           PiDAO piDAO,
                           ParametrosDAO parametrosDAO,
                           ValidarCargaClient validarCargaPIClient,
                           cl.contraloria.sicogen.webservices.validacion.informe.pi.ValidarInformeClient validarInformePIClient) {
        this.informesDAO = informesDAO;
        this.estadoInformesDAO = estadoInformesDAO;
        this.informesTDRDAO = informesTDRDAO;
        this.procesarInformesClient = procesarInformesClient;
        this.correoReprocesoClient = correoReprocesoClient;
        this.validarInformeClient = validarInformeClient;
        this.piDAO = piDAO;
        this.parametrosDAO = parametrosDAO;
        this.validarCargaPIClient = validarCargaPIClient;
        this.validarInformePIClient = validarInformePIClient;
    }

    public List<EjerciciosDTO> getEjercicios() throws SQLException {

        List<EjerciciosDTO> resultado = new ArrayList();
        resultado = informesDAO.getEjercicios();

        return resultado;
    }

    public List<TipoInformeDTO> gettipoInformes() throws SQLException {

        List<TipoInformeDTO> resultado = new ArrayList();
        resultado = informesDAO.gettipoInformes();

        return resultado;
    }

    public List<ProgramaPresupuestarioDTO> getlistaPartida(Integer idEjercicio) throws SQLException {

        List<ProgramaPresupuestarioDTO> resultado = new ArrayList();
        resultado = informesDAO.getlistaPartida(idEjercicio);

        return resultado;
    }

    public List<ProgramaPresupuestarioDTO> getlistaCapitulo(Integer idPartida, Integer idEjercicio) throws SQLException {

        List<ProgramaPresupuestarioDTO> resultado = new ArrayList();
        resultado = informesDAO.getlistaCapitulo(idPartida, idEjercicio);

        return resultado;
    }

    public InformesEstadosAnualBO getEstadoInformeAnual(String codPartida, String codCapitulo, Integer ejercicioId, Integer tipoInforme) throws SQLException {

        InformesEstadosAnualBO resultado = new InformesEstadosAnualBO();
        resultado = estadoInformesDAO.getEstadoInformeAnual(codPartida, codCapitulo, ejercicioId, tipoInforme);
        //List resultado = usuarioDAO.getUsuario(usuario, loggin);
        return resultado;
    }

    public List<Informes> getInformesForSend(Integer idEntidad, Integer idEjercicio, Integer idTipoInforme) {

        List<Informes> resultado = new ArrayList();
        resultado = informesDAO.getInformesForSend(idEntidad, idEjercicio, idTipoInforme);

        return resultado;
    }

    public Vector<EstadoInformeAnualDTO> getEstadoInformesAnual2(Integer entidad, Integer ejercicio, Integer tipoInforme) {

        Vector<EstadoInformeAnualDTO> _listaEstadoInformesAnual = null;
        _listaEstadoInformesAnual = informesDAO.getEstadoInformeAnual2(entidad, ejercicio, tipoInforme);

        return _listaEstadoInformesAnual;
    }

    public List<Informes> getCorreccionesSeguimientoByCodigoEntidad(String codPartida, String codCapitulo, int idEjercicio, int idTipoInforme) throws Exception {

        List<Informes> correcciones = new ArrayList<Informes>();
        correcciones = informesDAO.getCorreccionesSeguimientoByCodigoEntidad(codPartida, codCapitulo, idEjercicio, idTipoInforme);

        return correcciones;
    }

    public List<Periodos> getPeriodosByEjercicio(Integer idEjercicio) throws Exception {

        List<Periodos> resultado = new ArrayList();
        resultado = informesDAO.getPeriodosByEjercicio(idEjercicio);
        System.out.println(resultado);
        return resultado;
    }

    public List<PeriodoAbrev> getPeriodoAbrevByEjercicio(Integer idEjercicio) throws Exception {

        List<PeriodoAbrev> resultado = new ArrayList();
        resultado = informesDAO.getPeriodoAbrevByEjercicio(idEjercicio);
        System.out.println(resultado);
        return resultado;
    }

    public List<CorreccionPendienteBO> getCorreccionesPendientes(String idPart, String idCap) throws Exception {

        List<CorreccionPendienteBO> resultado = new ArrayList();
        resultado = informesDAO.getCorreccionesPendientes(idPart, idCap);

        return resultado;
    }

    public List<Informes> getInformesForUpload(Integer tipoInforme, Integer entidad, Integer periodo) throws Exception {

        List<Informes> resultado = null;
        resultado = informesDAO.getInformesForUpload(tipoInforme, entidad, periodo);

        return resultado;
    }

    public String obtieneXMLInformeIC(Integer idFileUp) throws Exception {

        String xml = "";

        xml = informesDAO.obtieneXMLInforme(idFileUp);

        return xml;
    }

    public String obtieneXMLErroresIC(int idFileUp) throws Exception {

        String xml = "";

        xml = informesDAO.obtieneXMLListaErrores(idFileUp);

        return xml;
    }

    public InformacionGeneralRV obtieneInfoGeneralRV(Integer idFileUp) {

        List<InformacionGeneralRV> listaInfo = informesDAO.obtieneInfoGeneralRV(idFileUp);

        return listaInfo.get(0);
    }

    public String obtieneXMLInformePI(Integer idFileUp) throws Exception {

        String xml = "";
        xml = informesDAO.obtieneXMLInforme(idFileUp);

        return xml;
    }

    public String obtieneXMLErroresPI(Integer idFileUp) throws Exception {

        String xml = "";
        xml = informesDAO.obtieneXMLListaErrores(idFileUp);

        return xml;
    }

    public List<AnnoDTO> getAnnosDocumento() throws Exception {

        List<AnnoDTO> annos = new ArrayList<AnnoDTO>();
        annos = informesDAO.getAnnosDocumento();

        return annos;
    }

    public List<PartidaDTO> getEntidadesSistrador() throws Exception {

        List<PartidaDTO> entidades = new ArrayList<PartidaDTO>();
        entidades = informesDAO.getEntidadesSistrador();

        return entidades;
    }

    public List<TipoDocumentoDTO> getTipoDocumento() throws Exception {

        List<TipoDocumentoDTO> tipoDocumento = new ArrayList<TipoDocumentoDTO>();
        tipoDocumento = informesDAO.getTipoDocumento();

        return tipoDocumento;
    }

    public List<DocumentoDTO> getFiltradoDocumentos(String entidad, Integer anno, String tipo, String numero) throws SQLException {

        List<DocumentoDTO> busquedaDocumentos = new ArrayList<DocumentoDTO>();
        busquedaDocumentos = informesDAO.getFiltraTipoDocumentos(entidad, anno, tipo, numero);

        return busquedaDocumentos;
    }

    public InformeArchivoDet getArchivo(int idFileUp) throws Exception {
        return informesDAO.getArchivo(idFileUp);
    }

    public List<EntidadEmisoraDTO> getEntidadesEmisoras() {

        List<EntidadEmisoraDTO> listaEntidades = new ArrayList<EntidadEmisoraDTO>();
        listaEntidades = informesDAO.getEntidadesEmisoras();

        return listaEntidades;
    }

    public List<TipoDocumentoTDRDTO> getTipoDocumentosTDR() {
        List<TipoDocumentoTDRDTO> tipoDocumentoTDR = new ArrayList<TipoDocumentoTDRDTO>();
        tipoDocumentoTDR = informesDAO.getTipoDocumentosTDR();

        return tipoDocumentoTDR;
    }

    public List<TDRRevalidacionDTO> getTDRparaRevalidacion(Integer agnoDoc, Integer numeroDoc, String entidadEmisora, String tipoDoc) {

        List<TDRRevalidacionDTO> tdrValidacion = new ArrayList<TDRRevalidacionDTO>();
        tdrValidacion = informesDAO.getTDRparaRevalidacion(agnoDoc, numeroDoc, entidadEmisora, tipoDoc);

        return tdrValidacion;
    }

    public List<Informe> getInformesByTipoInformes(Integer tpInformes) {
        List<Informe> byTipoInforme = new ArrayList<Informe>();
        byTipoInforme = informesDAO.getInformesByTipoInformes(tpInformes);

        return byTipoInforme;
    }

    public List<TipoReporteDTO> getReporteTipo() {
        List<TipoReporteDTO> tipoReporte = new ArrayList<TipoReporteDTO>();
        tipoReporte = informesDAO.getListaTipoReporte();

        return tipoReporte;
    }

    public List<Periodos> getPeriodos(Integer idEjercicio) {
        List<Periodos> peridos = new ArrayList<Periodos>();
        peridos = informesDAO.getPeriodos(idEjercicio);

        return peridos;
    }

    public List<Partida> getListPartida(Integer idEjercicio) {
        List<Partida> partidas = new ArrayList<Partida>();
        partidas = informesDAO.getListPartida(idEjercicio);

        return partidas;
    }

    public List<Informe> getInformesByPeriodo(Integer idPeriodo) {
        List<Informe> periodos = new ArrayList<Informe>();
        periodos = informesDAO.getInformesByPeriodo(idPeriodo);

        return periodos;
    }

    public List<Informe> getInformesPresupuestarios() {
        List<Informe> informesPresupuestarios = new ArrayList<Informe>();
        informesPresupuestarios = informesDAO.getInformesPresupuestarios();

        return informesPresupuestarios;
    }

    public CertificadoEnvioDTO getCertificadoEnvioByCert(Integer certificado) throws Exception {
        return informesDAO.getCertificadoEnvioByCert(certificado);
    }

    public List<Informe> obtieneListaInformesTDR() {
        List<Informe> lista = new ArrayList<Informe>();

        Informe informe = new Informe();
        informe.setId("-1");
        informe.setCodigo("SELECT");
        informe.setNombre("Selec. Tipo Informe");
        lista.add(informe);

        informe = new Informe();
        informe.setId("2");
        informe.setCodigo("TDRII");
        informe.setNombre("TDR Presupuesto iniciativas de inversión");
        lista.add(informe);

        informe = new Informe();
        informe.setId("11");
        informe.setCodigo("TDRMP");
        informe.setNombre("TDR Modificaciones de presupuesto");
        lista.add(informe);

        informe = new Informe();
        informe.setId("12");
        informe.setCodigo("TDRPI");
        informe.setNombre("TDR Presupuesto inicial otras entidades");
        lista.add(informe);

        return lista;
    }

    public List<Periodos> getPeriodosByInforme(Integer ejercicio, Integer informe) {
        return informesDAO.getPeriodosByInforme(ejercicio, informe);
    }

    public List<InformeSistradocBO> getInformesAsigTDR() throws Exception {
        List<InformeSistradocBO> informesTDR = new ArrayList<InformeSistradocBO>();
        informesTDR = informesDAO.getInformesAsigTDR();

        return informesTDR;
    }

    public AsignacionDTO asignarTDR(Integer idInforme, Integer idEjercicio, Integer idPeriodo, String idDocs) {
        AsignacionDTO asignacion = new AsignacionDTO();
        try {
            asignacion = informesDAO.asignarTDR(idInforme, idEjercicio, idPeriodo, idDocs);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return asignacion;
    }

    public List<ReportesBitacoraDTO> listaReporteBitacora(Integer idFile) {
        return informesDAO.listaReporteBitacora(idFile);
    }

    public String obtieneXMLErroresPI(int idFileUp) throws Exception {

        String xml = "";

        xml = informesDAO.obtieneXMLListaErrores(idFileUp);

        return xml;
    }

    public List<TiposDeInformesDTO> listaInformes() {
        return informesDAO.listaInformes();
    }

    public List<ReportesDTO> listaReportes(Integer informe) {
        return informesDAO.listaReportes(informe);
    }

    public Disponibilidades getReporteCuadraturaDisponibilidades(long idFileUpload) throws SQLException {
        return informesDAO.getReporteCuaDisponibilidades(idFileUpload);
    }

    public List<ComboDTO> getDatosCombo(String partida, String capitulo, Integer tipoInforme, Integer ejercicio, Integer informe, Integer periodo2) {
        List<ComboDTO> comboTDR = new ArrayList<ComboDTO>();
        comboTDR = informesDAO.getCombos(partida, capitulo, tipoInforme, ejercicio, informe, periodo2);

        return comboTDR;
    }

    public List<DatosEmitidosDTO> getDatosEmitidosPrograma(String partida, String capitulo, String codigo, Integer ejercicio, Integer informe, Integer periodo2) {
        return informesDAO.getDatosEmitidosPrograma(partida, capitulo, codigo, ejercicio, informe, periodo2);
    }

    public DatosInformePIDTO getDatosInformePI(Integer idFileUp) {

        List<InformacionGeneralRV> listaInfoGeneral = informesDAO.obtieneInfoGeneralRV(idFileUp);
        InformacionGeneralRV infoGeneral = listaInfoGeneral.get(0);
        DatosInformePIDTO datosInforme = informesDAO.obtieneDetalleInformePI(idFileUp);
        datosInforme.setEjercicio(infoGeneral.getEjercicio());
        datosInforme.setEntdad(infoGeneral.getEntidad());
        datosInforme.setEstadoValidacion(infoGeneral.getEstado());
        datosInforme.setNombreInforme(infoGeneral.getInforme());
        datosInforme.setPeriodo(infoGeneral.getPeriodo());
        datosInforme.setTipoInforme(infoGeneral.getTipoInforme());
        datosInforme.setUsuario(infoGeneral.getUsuario());
        datosInforme.setIdFileUp(idFileUp);
        return datosInforme;
    }

    public InformePI obtenerArchivoPI(String idEjercicio) {
        return informesDAO.obtenerArchivoPI(idEjercicio);
    }

    public InformeSistradocBO getInformeTDR(String idSistradoc) {
        return informesTDRDAO.getInformeTDR(idSistradoc);
    }

    public InformeTDRRV getDatosXMLInformeTDR(Integer idFileUp) throws Exception {
        InformeArchivoDet informeArchivoDet = informesDAO.getArchivo(idFileUp);
        Document doc = Utils.convertStringToXMLDocument(informeArchivoDet.getListaDetalle().get(0).getLinea());
        Document listaErrores = null;
        Element listaErroresEle = null;
        String xmlErrores = informesDAO.obtieneXMLListaErrores(idFileUp);

        if (!xmlErrores.isEmpty()) {
            listaErrores = Utils.convertStringToXMLDocument(xmlErrores);
        }

        doc.getDocumentElement().normalize();

        NodeList cabecera = doc.getElementsByTagName("cabecera");
        Element datosCabecera = (Element) cabecera.item(0);

        InformeTDRRV datosXMLInformeTDR = new InformeTDRRV();
        datosXMLInformeTDR.setEjercicio(datosCabecera.getElementsByTagName("ejercicio").item(0).getTextContent());
        datosXMLInformeTDR.setDia(datosCabecera.getElementsByTagName("dia").item(0).getTextContent());
        datosXMLInformeTDR.setFechaRegistro(datosCabecera.getElementsByTagName("fechaRegistro").item(0).getTextContent());
        datosXMLInformeTDR.setIdDecreto(datosCabecera.getElementsByTagName("IdDecreto").item(0).getTextContent());
        datosXMLInformeTDR.setMes(datosCabecera.getElementsByTagName("mes").item(0).getTextContent());
        datosXMLInformeTDR.setMontoTotal(datosCabecera.getElementsByTagName("montoTotal").item(0).getTextContent());
        datosXMLInformeTDR.setNumeroRegistro(datosCabecera.getElementsByTagName("numeroRegistro").item(0).getTextContent());
        datosXMLInformeTDR.setSectorResponsable(datosCabecera.getElementsByTagName("sectorResponsable").item(0).getTextContent());
        datosXMLInformeTDR.setTipoRegistro(datosCabecera.getElementsByTagName("tipoDeRegistro").item(0).getTextContent());

        if (listaErrores != null) {
            listaErrores.getDocumentElement().normalize();
            NodeList listaErroresNL = listaErrores.getElementsByTagName("listaErrores");
            listaErroresEle = (Element) listaErroresNL.item(0);
        }

        if (listaErroresEle != null) {
            NodeList erroresGenericosNL = listaErroresEle.getElementsByTagName("erroresGeneral");
            Element erroresGenericosEle = (Element) erroresGenericosNL.item(0);
            NodeList listaErroresGenNL = erroresGenericosEle.getElementsByTagName("error");
            List<ErrorGenerico> errorGenericos = new ArrayList<ErrorGenerico>();

            for (int i = 0; i < listaErroresGenNL.getLength(); i++) {
                Node errorNode = listaErroresGenNL.item(i);

                if (errorNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element error = (Element) errorNode;
                    ErrorGenerico errorGenerico = new ErrorGenerico();
                    errorGenerico.setTipoError(Integer.valueOf(error.getElementsByTagName("tipoError").item(0).getTextContent()));
                    errorGenerico.setMensaje(error.getElementsByTagName("mensajeError").item(0).getTextContent());
                    errorGenericos.add(errorGenerico);
                }
            }

            datosXMLInformeTDR.setErroresGenericos(errorGenericos);
        }

        NodeList detalleTDRMPNL = doc.getElementsByTagName("DecretoModificacionPresupuestaria");
        NodeList erroresDetalleNL = listaErroresEle != null ? listaErroresEle.getElementsByTagName("erroresDetalle") : null;
        Element erroresDetalleEleAux = erroresDetalleNL != null ? (Element) erroresDetalleNL.item(0) : null;
        List<DetalleInformeTDRMP> listaDetalle = new ArrayList<DetalleInformeTDRMP>();

        if (erroresDetalleEleAux != null) {
            NodeList listaErroresDetNL = erroresDetalleEleAux.getElementsByTagName("error");

            for (int i = 0; i < detalleTDRMPNL.getLength(); i++) {
                Node nNode = detalleTDRMPNL.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element detalle = (Element) nNode;
                    DetalleInformeTDRMP detalleTDRMP = new DetalleInformeTDRMP();
                    detalleTDRMP.setCapitulo(detalle.getElementsByTagName("codigoCapitulo").item(0).getTextContent().concat(" - ").concat(detalle.getElementsByTagName("nombreCapitulo").item(0).getTextContent()));
                    detalleTDRMP.setPartida(detalle.getElementsByTagName("codigoPartida").item(0).getTextContent().concat(" - ").concat(detalle.getElementsByTagName("nombrePartida").item(0).getTextContent()));
                    detalleTDRMP.setPrograma(detalle.getElementsByTagName("codigoPrograma").item(0).getTextContent().concat(" - ").concat(detalle.getElementsByTagName("nombrePrograma").item(0).getTextContent()));
                    NodeList cuentasTDRMPNL = detalle.getElementsByTagName("cuenta");
                    List<CuentaTDRMP> listaCuentaTDRMP = new ArrayList<CuentaTDRMP>();

                    for (int j = 0; j < cuentasTDRMPNL.getLength(); j++) {
                        Node nNodeCuenta = cuentasTDRMPNL.item(j);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element cuenta = (Element) nNodeCuenta;
                            CuentaTDRMP cuentaTDRMP = new CuentaTDRMP();
                            String mensajesErrores = "";
                            List<ErrorDetalle> erroresDetalle = new ArrayList<ErrorDetalle>();
                            cuentaTDRMP.setAsignacion(cuenta.getElementsByTagName("subAsignacion").item(0).getTextContent());
                            cuentaTDRMP.setDenominacion(cuenta.getElementsByTagName("denominacion").item(0).getTextContent());
                            cuentaTDRMP.setIncremento(String.format(Locale.GERMAN,"%,d", Long.valueOf(cuenta.getElementsByTagName("montoIncremento").item(0).getTextContent())));
                            cuentaTDRMP.setItem(cuenta.getElementsByTagName("item").item(0).getTextContent());
                            cuentaTDRMP.setReduccion(String.format(Locale.GERMAN,"%,d", Long.valueOf(cuenta.getElementsByTagName("montoReduccion").item(0).getTextContent())));
                            cuentaTDRMP.setSubtitulo(cuenta.getElementsByTagName("subtitulo").item(0).getTextContent());
                            cuentaTDRMP.setError(Boolean.FALSE);

                            for (int k = 0; k < erroresDetalleNL.getLength(); k++) {
                                Node erroresDetalleNode = erroresDetalleNL.item(k);

                                if (erroresDetalleNode != null && erroresDetalleNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element erroresDetalleEle = (Element) erroresDetalleNode;
                                    NodeList errores = erroresDetalleEle.getElementsByTagName("error");

                                    for (int l = 0; l < errores.getLength(); l++) {
                                        Node errorNode = errores.item(l);

                                        if (errorNode != null && errorNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element errorEle = (Element) errorNode;

                                            if (cuenta.getAttribute("codigoCuenta").equals(errorEle.getElementsByTagName("cuenta").item(0).getTextContent())) {
                                                cuentaTDRMP.setError(Boolean.TRUE);
                                                String mensajeError = errorEle.getElementsByTagName("mensaje").item(0).getTextContent();
                                                mensajesErrores = mensajesErrores.concat(mensajeError).concat("&lt;br/>");
                                                ErrorDetalle errorDetalle = new ErrorDetalle();
                                                errorDetalle.setTipoError(Integer.valueOf(errorEle.getElementsByTagName("tipoError").item(0).getTextContent()));
                                                errorDetalle.setMensaje(mensajeError);
                                                erroresDetalle.add(errorDetalle);
                                            }
                                        }
                                    }


                                }
                            }

                            if (mensajesErrores.length() > 0) {
                                mensajesErrores = mensajesErrores.substring(0, mensajesErrores.length() - 9);
                            }

                            cuentaTDRMP.setMensajesErrores(mensajesErrores);
                            cuentaTDRMP.setErroresDetalle(erroresDetalle);

                            listaCuentaTDRMP.add(cuentaTDRMP);
                        }
                    }

                    detalleTDRMP.setCuentasTDRMP(listaCuentaTDRMP);
                    listaDetalle.add(detalleTDRMP);
                }
            }

            datosXMLInformeTDR.setDetalle(listaDetalle);
        } else {
            for (int i = 0; i < detalleTDRMPNL.getLength(); i++) {
                Node nNode = detalleTDRMPNL.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element detalle = (Element) nNode;
                    DetalleInformeTDRMP detalleTDRMP = new DetalleInformeTDRMP();
                    detalleTDRMP.setCapitulo(detalle.getElementsByTagName("codigoCapitulo").item(0).getTextContent().concat(" - ").concat(detalle.getElementsByTagName("nombreCapitulo").item(0).getTextContent()));
                    detalleTDRMP.setPartida(detalle.getElementsByTagName("codigoPartida").item(0).getTextContent().concat(" - ").concat(detalle.getElementsByTagName("nombrePartida").item(0).getTextContent()));
                    detalleTDRMP.setPrograma(detalle.getElementsByTagName("codigoPrograma").item(0).getTextContent().concat(" - ").concat(detalle.getElementsByTagName("nombrePrograma").item(0).getTextContent()));
                    NodeList cuentasTDRMPNL = detalle.getElementsByTagName("cuenta");
                    List<CuentaTDRMP> listaCuentaTDRMP = new ArrayList<CuentaTDRMP>();

                    for (int j = 0; j < cuentasTDRMPNL.getLength(); j++) {
                        Node nNodeCuenta = cuentasTDRMPNL.item(j);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element cuenta = (Element) nNodeCuenta;
                            CuentaTDRMP cuentaTDRMP = new CuentaTDRMP();
                            cuentaTDRMP.setAsignacion(cuenta.getElementsByTagName("subAsignacion").item(0).getTextContent());
                            cuentaTDRMP.setDenominacion(cuenta.getElementsByTagName("denominacion").item(0).getTextContent());
                            cuentaTDRMP.setIncremento(String.format(Locale.GERMAN,"%,d", Long.valueOf(cuenta.getElementsByTagName("montoIncremento").item(0).getTextContent())));
                            cuentaTDRMP.setItem(cuenta.getElementsByTagName("item").item(0).getTextContent());
                            cuentaTDRMP.setReduccion(String.format(Locale.GERMAN,"%,d", Long.valueOf(cuenta.getElementsByTagName("montoReduccion").item(0).getTextContent())));
                            cuentaTDRMP.setSubtitulo(cuenta.getElementsByTagName("subtitulo").item(0).getTextContent());
                            cuentaTDRMP.setError(Boolean.FALSE);
                            listaCuentaTDRMP.add(cuentaTDRMP);
                        }
                    }

                    detalleTDRMP.setCuentasTDRMP(listaCuentaTDRMP);
                    listaDetalle.add(detalleTDRMP);
                }
            }

            datosXMLInformeTDR.setDetalle(listaDetalle);
        }

        return datosXMLInformeTDR;
    }

    public InformeContableDTO obtieneDatosInformeContable(Integer idArchivo) {
        return informesDAO.obtieneDatosInformeContable(idArchivo);
    }

    public ReporteValidacionPaginacion obtieneReporteValidacionPaginacion(Integer idArchivo,
                                                                          Integer numPagina,
                                                                          Integer numRegistros,
                                                                          Integer draw,
                                                                          String flagAll) {

        Map<String, Object> resultado = informesDAO.obtieneReporteValidacionPaginacion(idArchivo, numPagina, numRegistros, flagAll);
        List<ReporteValidacionData> reporteValidacionDataList = new ArrayList<ReporteValidacionData>();
        List<ReporteValidacionDTO> reporteValidacionDTOList = (List<ReporteValidacionDTO>) resultado.get(ReporteValidacionConstants.LISTA_DETALLES_KEY);
        ReporteValidacionPaginacion reporteValidacionPaginacion = new ReporteValidacionPaginacion();
        reporteValidacionPaginacion.setDraw(draw);
        reporteValidacionPaginacion.setRecordsTotal((Integer) resultado.get(ReporteValidacionConstants.NUM_TOTAL_REGISTROS_KEY));
        reporteValidacionPaginacion.setRecordsFiltered((Integer) resultado.get(ReporteValidacionConstants.NUM_TOTAL_REGISTROS_KEY));

        for (ReporteValidacionDTO reporteValidacionDTO : reporteValidacionDTOList) {
            ReporteValidacionData data = new ReporteValidacionData();
            data.setAreaTransaccional(reporteValidacionDTO.getCodigoAreaTransaccional());
            data.setCodigoBIP(reporteValidacionDTO.getCodigoBIP().concat("-").concat(reporteValidacionDTO.getDigitoV()));
            data.setCuentaContable(reporteValidacionDTO
                    .getCCAgrupacion()
                    .concat(".")
                    .concat(reporteValidacionDTO.getCCCtaN1())
                    .concat(".")
                    .concat(reporteValidacionDTO.getCCCtaN2())
                    .concat(".")
                    .concat(reporteValidacionDTO.getCCCtaN3()));
            data.setCuentaPresupuestaria(reporteValidacionDTO
                    .getCPSubtitulo()
                    .concat(".")
                    .concat(reporteValidacionDTO.getCPItem())
                    .concat(".")
                    .concat(reporteValidacionDTO.getCPAsignacion())
                    .concat(".")
                    .concat(reporteValidacionDTO.getCPSubasignacion()));
            data.setDebeCLP(String.format(Locale.GERMAN,"%,d", Long.valueOf(reporteValidacionDTO.getDebeCLP())));
            data.setDebeUSD(reporteValidacionDTO.getDebeUSD());
            data.setDenominacionCuenta(reporteValidacionDTO.getDenominacionCuenta());
            data.setDenominacionProyecto(reporteValidacionDTO.getDenominacionProyecto());
            data.setFolioContable(String.valueOf(reporteValidacionDTO.getFolioContable()));
            data.setHaberCLP(String.format(Locale.GERMAN,"%,d", Long.valueOf(reporteValidacionDTO.getHaberCLP())));
            data.setHaberUSD(reporteValidacionDTO.getHaberUSD());
            data.setMoneda(reporteValidacionDTO.getTipoMoneda());
            data.setMovimiento(reporteValidacionDTO.getTipoMovimiento());
            data.setPrograma(reporteValidacionDTO.getCodigoPrograma());
            data.setTipoTransaccion(reporteValidacionDTO.getTipoTransaccion());
            reporteValidacionDataList.add(data);
            data.setEstadpRegla2(reporteValidacionDTO.getEstadoRegla2());
            data.setEstadpRegla3(reporteValidacionDTO.getEstadoRegla3());
            data.setMensajeRegla2(reporteValidacionDTO.getMensajeRegla2());
            data.setMensajeRegla3(reporteValidacionDTO.getMensajeRegla3());
            data.setTipoErrorRegla2(reporteValidacionDTO.getTipoErrorRegla2());
            data.setTipoErrorRegla3(reporteValidacionDTO.getTipoErrorRegla3());
        }

        reporteValidacionPaginacion.setData(reporteValidacionDataList);
        return reporteValidacionPaginacion;
    }

    public List<InformeSistradocBO> getInformesTDR(Integer idInforme,
                                                   Integer idEjercicio,
                                                   Integer idEstadoSicogen,
                                                   Integer idPeriodo) {
        return informesTDRDAO.getInformesTDR(idInforme, idEjercicio, idEstadoSicogen, idPeriodo);
    }

    public List<InformeContableDTO> getInformesReproceso() {
        return informesDAO.getInformesReproceso();
    }

    public ResultadoEjecucion reprocesarInformesContables(List<Integer> listaFileuId) {

        List<InformeContableDTO> informesReproceso = informesDAO.getInformesReproceso();
        List<InformeType> listaInformesProcesar = new ArrayList<InformeType>();

        for (Integer id : listaFileuId) {
            int indexDel = 0;

            for (InformeContableDTO inf : informesReproceso) {
                if (inf.getFileuId().equals(id)) {
                    InformeType informeType = new InformeType();
                    informeType.setCapitulo(inf.getCodCapitulo());
                    informeType.setEjercicio(inf.getEjercicio());
                    informeType.setFiluId(String.valueOf(id));
                    informeType.setPartida(inf.getCodPartida());
                    informeType.setPeriodo(inf.getCodPeriodo());
                    informeType.setUuid(inf.getUuid());
                    listaInformesProcesar.add(informeType);
                    indexDel = informesReproceso.indexOf(inf);
                    break;
                }
            }

            informesReproceso.remove(indexDel);
        }

        ProcesarInformesRequest request = new ProcesarInformesRequest();
        ListaInformesType listaInformesType = new ListaInformesType();
        listaInformesType.setInforme(listaInformesProcesar);
        request.setListaInformes(listaInformesType);

        ProcesarInformesResponse response = procesarInformesClient.procesarInformesContables(request);
        BigInteger codigo = response.getCodigo();

        return new ResultadoEjecucion(codigo.toString(), response.getMensaje());
    }

    public String getPdfCertificadoEnvioByFileuId(Integer fileuId,
                                                  URL logoSicogen,
                                                  URL cuadrados) throws Exception {
        Integer certId = informesDAO.getCertIdByFileuId(fileuId);
        CertificadoEnvioDTO certEnv = informesDAO.getCertificadoEnvioByCert(certId);
        String fecha = certEnv.getFecha().split(" ")[0];
        String hora = certEnv.getFecha().split(" ")[1];
        String textoCert = "Yo, " + certEnv.getUsuario() + ", declaro que con fecha " + fecha + " a las " + hora +
                " hrs, remito a la Contraloria General de la Republica los siguientes informes, los cuales son copias " +
                "fiel de la informacion contenida en esta entidad.";
        List<Informes> informesCert = certEnv.getDetInf();
        Informes informeCert = informesCert.get(0);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4, 20, 10, 10, 10);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        BaseColor bckHeader = new BaseColor(115, 116, 120);
        BaseColor colWhite = new BaseColor(255, 255, 255);
        BaseColor bckPrimary = new BaseColor(63, 135, 193);

        Font fontTituloCert = new Font(Font.getFamily("Arial"), 14, Font.BOLD);
        Font fontTexto = new Font(Font.getFamily("Arial"), 8);
        Font fontTextoBlanco = new Font(Font.getFamily("Arial"), 8, Font.BOLD);
        Font fontTextoQR = new Font(Font.getFamily("Arial"), 6, Font.BOLD);

        fontTituloCert.setColor(BaseColor.BLACK);
        fontTexto.setColor(BaseColor.BLACK);
        fontTextoBlanco.setColor(colWhite);
        fontTextoQR.setColor(BaseColor.BLACK);

        // Logo sicogen
        Image image = Image.getInstance(logoSicogen);
        image.setAlignment(Image.ALIGN_CENTER);
        image.setBorder(Image.NO_BORDER);
        image.scaleToFit(250, 40);

        Image codigoQR = Image.getInstance(generateQRCodeImage(certEnv.getUsuario() + " " + certEnv.getEntidad() + " " + fecha + " " + hora + " " + certEnv.getCertificado()));
        codigoQR.setAlignment(Image.LEFT);
        codigoQR.setBorder(Image.NO_BORDER);
        codigoQR.scaleToFit(60, 40);

        // Logo sicogen
        Image imageCuadrados = Image.getInstance(cuadrados);
        imageCuadrados.setAlignment(Image.LEFT);
        imageCuadrados.setBorder(Image.NO_BORDER);
        imageCuadrados.scaleToFit(30, 10);

        PdfPTable tblHeader = new PdfPTable(2);
        tblHeader.setWidths(new int[]{250, 60});

        PdfPCell cellImgSicogen = new PdfPCell(image);
        cellImgSicogen.setBackgroundColor(bckHeader);
        cellImgSicogen.setBorder(PdfPCell.NO_BORDER);
        cellImgSicogen.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellImgSicogen.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

        PdfPCell cellImgQR = new PdfPCell();
        Phrase phrase = new Phrase("N# Certificado: " + certEnv.getCertificado(), fontTextoQR);
        cellImgQR.addElement(phrase);
        cellImgQR.addElement(codigoQR);
        cellImgQR.setBorder(PdfPCell.NO_BORDER);
        cellImgQR.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellImgQR.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

        tblHeader.addCell(cellImgSicogen);
        tblHeader.addCell(cellImgQR);
        document.add(tblHeader);

        PdfPTable tblTituloCert = new PdfPTable(1);

        PdfPCell cellTit1 = new PdfPCell(new Phrase("CERTIFICADO", fontTituloCert));
        cellTit1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        cellTit1.setBorder(PdfPCell.NO_BORDER);

        PdfPCell cellTit2 = new PdfPCell(new Phrase("DE ENVIO DE INFORMES A CGR", fontTituloCert));
        cellTit2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        cellTit2.setBorder(PdfPCell.NO_BORDER);

        tblTituloCert.addCell(cellTit1);
        tblTituloCert.addCell(cellTit2);
        document.add(tblTituloCert);

        PdfPTable tblTextoCert = new PdfPTable(1);
        PdfPCell cellTextCert = new PdfPCell(new Phrase(textoCert, fontTexto));
        cellTextCert.setBorder(PdfPCell.NO_BORDER);
        tblTextoCert.addCell(cellTextCert);
        tblTextoCert.setSpacingBefore(3);
        document.add(tblTextoCert);

        PdfPTable tblDetalleInf = new PdfPTable(5);

        PdfPCell cellEncTbl1 = new PdfPCell(new Phrase("Tipo de Informe", fontTextoBlanco));
        cellEncTbl1.setBorder(PdfPCell.NO_BORDER);
        cellEncTbl1.setBackgroundColor(bckPrimary);
        cellEncTbl1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tblDetalleInf.addCell(cellEncTbl1);

        PdfPCell cellEncTbl2 = new PdfPCell(new Phrase("Informes", fontTextoBlanco));
        cellEncTbl2.setBorder(PdfPCell.NO_BORDER);
        cellEncTbl2.setBackgroundColor(bckPrimary);
        cellEncTbl2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tblDetalleInf.addCell(cellEncTbl2);

        PdfPCell cellEncTbl3 = new PdfPCell(new Phrase("Periodo", fontTextoBlanco));
        cellEncTbl3.setBorder(PdfPCell.NO_BORDER);
        cellEncTbl3.setBackgroundColor(bckPrimary);
        cellEncTbl3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tblDetalleInf.addCell(cellEncTbl3);

        PdfPCell cellEncTbl4 = new PdfPCell(new Phrase("Ejercicio", fontTextoBlanco));
        cellEncTbl4.setBorder(PdfPCell.NO_BORDER);
        cellEncTbl4.setBackgroundColor(bckPrimary);
        cellEncTbl4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tblDetalleInf.addCell(cellEncTbl4);

        PdfPCell cellEncTbl5 = new PdfPCell(new Phrase("Nota", fontTextoBlanco));
        cellEncTbl5.setBorder(PdfPCell.NO_BORDER);
        cellEncTbl5.setBackgroundColor(bckPrimary);
        cellEncTbl5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tblDetalleInf.addCell(cellEncTbl5);

        PdfPCell cellDataTbl1 = new PdfPCell(new Phrase(informeCert.getTipoInformeNombre(), fontTexto));
        cellDataTbl1.setBorder(PdfPCell.NO_BORDER);
        cellDataTbl1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tblDetalleInf.addCell(cellDataTbl1);

        PdfPCell cellDataTbl2 = new PdfPCell(new Phrase(informeCert.getInformeNombre(), fontTexto));
        cellDataTbl2.setBorder(PdfPCell.NO_BORDER);
        cellDataTbl2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tblDetalleInf.addCell(cellDataTbl2);

        PdfPCell cellDataTbl3 = new PdfPCell(new Phrase(informeCert.getPeriodoNombre(), fontTexto));
        cellDataTbl3.setBorder(PdfPCell.NO_BORDER);
        cellDataTbl3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tblDetalleInf.addCell(cellDataTbl3);

        PdfPCell cellDataTbl4 = new PdfPCell(new Phrase(informeCert.getEjercicioNombre(), fontTexto));
        cellDataTbl4.setBorder(PdfPCell.NO_BORDER);
        cellDataTbl4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tblDetalleInf.addCell(cellDataTbl4);

        PdfPCell cellDataTbl5 = new PdfPCell(new Phrase(informeCert.getInformeMensaje(), fontTexto));
        cellDataTbl5.setBorder(PdfPCell.NO_BORDER);
        cellDataTbl5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tblDetalleInf.addCell(cellDataTbl5);

        tblDetalleInf.setSpacingBefore(5);
        document.add(tblDetalleInf);

        PdfPTable tblSaludo = new PdfPTable(1);

        PdfPCell cellCuadros = new PdfPCell(imageCuadrados);
        cellCuadros.setBorder(PdfPCell.NO_BORDER);

        PdfPCell cellAtt = new PdfPCell(new Phrase("Atentamente,\n" + certEnv.getUsuario() +  "\n" + certEnv.getEntidad(), fontTexto));
        cellAtt.setBorder(PdfPCell.NO_BORDER);

        tblSaludo.addCell(cellCuadros);
        tblSaludo.addCell(cellAtt);
        tblSaludo.setSpacingBefore(10);
        document.add(tblSaludo);

        document.close();

        Base64 codec = new Base64();

        return codec.encodeBase64String(baos.toByteArray());
    }

    @Scheduled(cron = "0 0 7 * * 1-5")
    public void enviaCorreoReprocesoInformesContables() throws SocketException {

        List<String> hostsAdresses = getListHostAdresses();
        String ipNodoAsignado = parametrosDAO.getParametroValue("IP_NODO_ENVIA_CORREO");

        for(String ip : hostsAdresses) {
            if (ip.equals(ipNodoAsignado)) {
                List<String> correos = informesDAO.getCorreosReproceso();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                EnviarCorreoReprocesoRequestType request = new EnviarCorreoReprocesoRequestType();
                request.setCorreo(correos);
                request.setFecha(sdf.format(new Date()));
                correoReprocesoClient.enviarCorreoReprocesoInformesContables(request);
                break;
            }
        }

    }

    public String validaInformeTDRMP(Integer idFileUp, String usuario) {
        ValidarInforme validarInformeReq = new ValidarInforme();
        validarInformeReq.setUsuario(usuario);
        validarInformeReq.setIdFileUpload(new BigDecimal(idFileUp));

        ValidarInformeResponse respValidarInforme = validarInformeClient.validarInformeTDRMP(validarInformeReq);
        return respValidarInforme.getMensaje();
    }

    public CargaInformePIDTO ejecutaCargaArchivoPI(String anio,
                                                   String nomUser,
                                                   MultipartFile fileUpload,
                                                   Integer idEntidad,
                                                   String informeName,
                                                   String idInforme) throws IOException {
        String fileUploadFileName = fileUpload.getOriginalFilename();
        int idPerdInf = 1;
        String perInf = piDAO.obtienePeriodoEjercicio(anio);
        if(perInf != null){
            idPerdInf = Integer.valueOf(perInf);
        }

        int tpArchivo = 0;
        String varPeriodo = "00";
        String varInforme = "LEYPI";

        String tamanioMaximoStr = parametrosDAO.getParametroValue("SIZE_FILE_MAX");
        long tamanioMaximo = Integer.valueOf(tamanioMaximoStr)*1024*1024;
        long tamanioArchivo = fileUpload.getSize();
        InformePI informePI = new InformePI();
        List<ValidacionReglaBO> errores = new ArrayList<ValidacionReglaBO>();

        if(tamanioArchivo > tamanioMaximo){

            informePI.setEstadoSicogen("-1");
            informePI.setNombreArchivo(fileUploadFileName);
            informePI.setNombre(informeName);

            ValidacionReglaBO reglaBO = new ValidacionReglaBO();
            reglaBO.setMensajeError("El archivo supera el peso máximo permitido: "+tamanioMaximoStr+" MB.");
            reglaBO.setIdRegla((short)1);

            errores.add(reglaBO);

            return new CargaInformePIDTO(informePI, errores);
        }

        TestXML valXML = new TestXML();
        if(valXML.testXML(fileUpload.getInputStream())){
            informePI.setEstadoSicogen("-1");
            informePI.setNombreArchivo(fileUploadFileName);
            informePI.setNombre(informeName);

            ValidacionReglaBO reglaBO = new ValidacionReglaBO();
            reglaBO.setMensajeError("Sr. Usuario, el contenido del archivo debe ser un XML segun el esquema.");
            reglaBO.setIdRegla((short)1);

            errores.add(reglaBO);

            return new CargaInformePIDTO(informePI, errores);
        }

        ValidarCarga request = new ValidarCarga();
        request.setNombreArchivo(fileUploadFileName);
        request.setVarInforme(varInforme);
        request.setVarPeriodo(varPeriodo);
        request.setVarEjercicio(anio);
        request.setPeriodo(new BigDecimal(idPerdInf));
        request.setIdEntidad(new BigDecimal(idEntidad));
        request.setIdInforme(new BigDecimal(idInforme));
        request.setUsuario(nomUser);
        request.setTipoArchivo(new BigDecimal(tpArchivo));
        request.setDocumento(new String(fileUpload.getBytes(), "UTF-8"));

        ValidarCargaResponse res = validarCargaPIClient.validarCargaPI(request);
        String estadoEjec = res.getEstado();

        if (estadoEjec.equals("0")) {
            informePI.setEstadoSicogen("-1");
            errores = erroresCargaToBOPI(res.getListaValidaciones());
        } else if (estadoEjec.equals("-1")) {
            informePI.setEstadoSicogen("0");
        } else {
            informePI.setEstadoSicogen("1");
            informePI.setIdFileUpload(res.getIdFileUpload().toString());
        }

        informePI.setNombreArchivo(fileUploadFileName);
        informePI.setNombre(informeName);

        return new CargaInformePIDTO(informePI, errores);
    }

    private List<ValidacionReglaBO> erroresCargaToBOPI(ListaValidaciones listaValidacionesResponse) {

        List<ValidacionReglaBO> reglasCargaBO = new ArrayList<ValidacionReglaBO>();
        List<ValidacionRegla> lt = listaValidacionesResponse.getValidacionRegla();

        for(ValidacionRegla validacione : lt){
            ValidacionReglaBO reglaBO = new ValidacionReglaBO();
            reglaBO.setMensajeError(validacione.getMensajeError());
            reglaBO.setIdRegla(validacione.getIdRegla());
            reglaBO.setSeccionArchivo(validacione.getSeccionArchivo());
            reglasCargaBO.add(reglaBO);
        }
        return reglasCargaBO;
    }

    public void validarNegocioPI(String idFileUp, String usuario, InformePI informePI) {
        cl.contraloria.sicogen.webservices.validacion.informe.pi.ValidarInforme request = new cl.contraloria.sicogen.webservices.validacion.informe.pi.ValidarInforme();
        request.setIdFileUpload(new BigDecimal(idFileUp));
        request.setUsuario(usuario);

        cl.contraloria.sicogen.webservices.validacion.informe.pi.ValidarInformeResponse res = validarInformePIClient.validarInformePI(request);

        informePI.setEstadoSicogen(res.getEstado());
    }

    public ListaErrores obtenerListaErroresTDR(Integer idFileUp) throws Exception {
        String listaErrores = informesDAO.obtieneXMLListaErrores(idFileUp);

        if (listaErrores.isEmpty()) {
            listaErrores = "<listaErrores>\n" +
                    "\t<erroresDetalle>\n" +
                    "\t\t<error>\n" +
                    "\t\t\t<codigoPartida>12</codigoPartida>\n" +
                    "\t\t\t<codigoCapitulo>02</codigoCapitulo>\n" +
                    "\t\t\t<codigoPrograma>04</codigoPrograma>\n" +
                    "\t\t\t<cuenta>31.02.001.000</cuenta>\n" +
                    "\t\t\t<mensaje>R013. Sr Usuario, el presupuesto actualizado de la asignación [001], debe ser mayor o igual a cero.</mensaje>\n" +
                    "\t\t\t<tipoError>1</tipoError>\n" +
                    "\t\t</error>\n" +
                    "\t</erroresDetalle>\n" +
                    "\t<erroresGeneral>\n" +
                    "\t\t<error>\n" +
                    "\t\t\t<estadoRegla>0</estadoRegla>\n" +
                    "\t\t\t<idRegla>8</idRegla>\n" +
                    "\t\t\t<mensajeError>R008. Sr. Usuario la entidad Tesoro Público presenta diferencias entre la sumatoria de gastos y la sumatoria de Ingresos a nivel de entidad.</mensajeError>\n" +
                    "\t\t\t<tipoError>1</tipoError>\n" +
                    "\t\t\t<identificaSalida>-1</identificaSalida>\n" +
                    "\t\t</error>\n" +
                    "\t</erroresGeneral>\n" +
                    "</listaErrores>";
        }

        return parseXmlToObject(listaErrores);
    }

    private ListaErrores parseXmlToObject(String listaErrores) {

        ListaErrores errores = null;

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(ListaErrores.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(listaErrores);
            errores = (ListaErrores) unmarshaller.unmarshal(reader);

        }catch(Exception e){
            e.printStackTrace();
        }

        return errores;
    }
}