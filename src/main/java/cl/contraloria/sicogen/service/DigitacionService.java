package cl.contraloria.sicogen.service;

import cl.contraloria.sicogen.dao.*;
import cl.contraloria.sicogen.exceptions.SicogenException;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.webservices.validacion.carga.tdrmp.ValidarCarga;
import cl.contraloria.sicogen.webservices.validacion.carga.tdrmp.ValidarCargaClient;
import cl.contraloria.sicogen.webservices.validacion.carga.tdrmp.ValidarCargaResponse;
import cl.contraloria.sicogen.webservices.validacion.informe.tdrii.ValidarInformeTDRIIClient;
import cl.contraloria.sicogen.webservices.validacion.informe.tdrmp.ValidarInforme;
import cl.contraloria.sicogen.webservices.validacion.informe.tdrmp.ValidarInformeClient;
import cl.contraloria.sicogen.webservices.validacion.informe.tdrmp.ValidarInformeResponse;
import cl.contraloria.sicogen.xml.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

@Service
public class DigitacionService {

    private DigitacionMPDAO digitacionMPDAO;
    private SistradocDAO sistradocDAO;
    private ValidarCargaClient validarCargaClient;
    private ValidarInformeClient validarInformeClient;
    private DigitacionIIDAO digitacionIIDAO;
    private CatalogoPresupDAO catalogoPresupDAO;
    private ParametrosDAO parametrosDAO;
    private ValidarInformeTDRIIClient validarInformeTDRIIClient;
    private InformesDAO informesDAO;

    public DigitacionService(DigitacionMPDAO digitacionMPDAO,
                             SistradocDAO sistradocDAO,
                             ValidarCargaClient validarCargaClient,
                             ValidarInformeClient validarInformeClient,
                             DigitacionIIDAO digitacionIIDAO,
                             CatalogoPresupDAO catalogoPresupDAO,
                             ParametrosDAO parametrosDAO,
                             ValidarInformeTDRIIClient validarInformeTDRIIClient,
                             InformesDAO informesDAO) {
        this.digitacionMPDAO = digitacionMPDAO;
        this.sistradocDAO = sistradocDAO;
        this.validarCargaClient = validarCargaClient;
        this.validarInformeClient = validarInformeClient;
        this.digitacionIIDAO = digitacionIIDAO;
        this.catalogoPresupDAO = catalogoPresupDAO;
        this.parametrosDAO = parametrosDAO;
        this.validarInformeTDRIIClient = validarInformeTDRIIClient;
        this.informesDAO = informesDAO;
    }

    public int getIdEjercicio(int idEjercicio) {
        return digitacionMPDAO.getIdEjercicio(idEjercicio);
    }

    public List<DigitacionMPBO> buscarMPDigitacionTDR(int idEjercicio,
                                                      int numDoc,
                                                      int idPeriodo,
                                                      String tipoDocumento){
        return digitacionMPDAO.buscarMPDigitacionTDR(idEjercicio, numDoc, idPeriodo, tipoDocumento);
    }

    public int getIdCapitulo(String codPartida, int idPartida) {
        return digitacionMPDAO.getIdCapitulo(codPartida, idPartida);
    }

    public int getIdPartida(String codPartida) {
        return digitacionMPDAO.getIdPartida(codPartida);
    }

    public void insertarMPDigitacionTDR(String idPartida,
                                        String idCapitulo,
                                        String idPrograma,
                                        int idEjercicio,
                                        int numDoc,
                                        int idPeriodo,
                                        String tipoDoc) {
        digitacionMPDAO.insertarMPDigitacionTDR(idPartida, idCapitulo, idPrograma, idEjercicio, numDoc, idPeriodo, tipoDoc);
    }

    public void deleteMPDigitacionTDR(int idFila) {
        digitacionMPDAO.deleteMPDigitacionTDR(idFila);
    }

    public CuentaPresupValidacion getDescripcionCuenta(String codCuenta, int idPrograma, int idEjercicio) {
        return digitacionMPDAO.getDescripcionCuenta(codCuenta, idPrograma, idEjercicio);
    }

    public Integer createCuentaParticularEntidad(Integer ejercicioId,
                                                 String codPartida,
                                                 String codCapitulo,
                                                 String codPrograma,
                                                 String codCuenta,
                                                 String nomCuenta,
                                                 Integer idCuentaGen,
                                                 Integer idDocumento,
                                                 String usuario) {
        return digitacionMPDAO.createCuentaParticularEntidad(ejercicioId, codPartida, codCapitulo, codPrograma, codCuenta, nomCuenta, idCuentaGen, idDocumento, usuario);
    }

    public List<DetTDRMPDTO> getListDetTDRMP(Integer idTDRMP, Integer idEjercicio) {
        return digitacionMPDAO.getListDetTDRMP(idTDRMP, idEjercicio);
    }

    public void insertarAumentoTDRMP(Integer idTDRMPR,
                                     Integer idCuenEnt,
                                     String desMoneda,
                                     Integer monto) {
        digitacionMPDAO.insertarAumentoTDRMP(idTDRMPR, idCuenEnt, desMoneda, monto);
    }

    public void insertarDisminucionTDRMP(Integer idTDRMPR,
                                     Integer idCuenEnt,
                                     String desMoneda,
                                     Integer monto) {
        digitacionMPDAO.insertarDisminucionTDRMP(idTDRMPR, idCuenEnt, desMoneda, monto);
    }

    public void deleteDetalleMPDigitacionTDR(Integer idDetTDRMP) {
        digitacionMPDAO.deleteDetalleMPDigitacionTDR(idDetTDRMP);
    }

    public void updateDetalleAumDisDigitacionMP(Integer idDetTDRMP,
                                                Integer montoAumento,
                                                Integer montoDisminucion) {
        digitacionMPDAO.updateDetalleAumDisDigitacionMP(idDetTDRMP, montoAumento, montoDisminucion);
    }

    public ResultadoEjecucion validarMPDigitacionTDR(int idPeriodo,
                                                       int numDoc,
                                                       String dia,
                                                       String mes,
                                                       String tipoDoc,
                                                       int idInforme,
                                                       int idEjercicio,
                                                       String idSistradoc,
                                                       int ejercicio,
                                                       String nomUsuario) {
        String varPartida = sistradocDAO.getPartida(numDoc, tipoDoc, idEjercicio, idPeriodo, idInforme);
        String varCapitulo = sistradocDAO.getCapitulo(numDoc, tipoDoc, idEjercicio, idPeriodo, idInforme);
        List<DecretoModificacionPresupestariaDTO> datosTDRMP = digitacionMPDAO.DatosTDRXmlMP(idEjercicio, idPeriodo, numDoc, tipoDoc);
        List<GlosaTDRMPDTO> glosas = digitacionMPDAO.getGlosasByNumDoc(numDoc);

        int cantReg = datosTDRMP.size();
        int montoTotal = 0;
        XMLDocumentoTDRMP xmlTDRMP = new XMLDocumentoTDRMP();

        List<FirmaMinistro> firmaM = new ArrayList<FirmaMinistro>();
        FirmaMinistro firmaMinistro = new FirmaMinistro();

        firmaMinistro.setNombre("texto 80 caracteres");
        firmaMinistro.setCargo("texto cargo 80 caracteres");
        firmaMinistro.setFirmaTimbre("cGVwaXRvIGxvcyBwYWxvdGVzDQo=");
        firmaM.add(firmaMinistro);


        CabeceraTDRMP cabeceraTDRMP = new CabeceraTDRMP(ejercicio, mes, dia, cantReg, montoTotal, 12, 123456, "texto.. 10", "20160722", "01", 0, 0, 0, 0, 0, "", 0, "",
                "", "", "", "", "", "", "", firmaM, "", "");

        xmlTDRMP.setCabeceraMP(cabeceraTDRMP);

        List<ModificacionPresupuestaria> detalleDecretoModificacionPresupuestaria = new ArrayList<ModificacionPresupuestaria>();
        ListaGlosaInstitucional listaGlosasInst = new ListaGlosaInstitucional();
        List<Glosas> glosasInst = new ArrayList<Glosas>();

        for (int i = 0; i < glosas.size(); i++) {
            GlosaTDRMPDTO glosaDTO = glosas.get(i);

            if (glosaDTO.getIdDetTDRMP() == null) {
                Glosas glosaInst = new Glosas();
                glosaInst.setCodigoPartida(glosaDTO.getCodPart());
                glosaInst.setNombrePartida(glosaDTO.getNomPart());
                glosaInst.setCodigoSubpartida("00");
                glosaInst.setNombreSubpartida("");
                glosaInst.setCodigoCapitulo(glosaDTO.getCodCap());
                glosaInst.setNombreCapitulo(glosaDTO.getNomCap() != null ? glosaDTO.getNomCap() : "");
                glosaInst.setCodigoPrograma(glosaDTO.getCodProg());
                glosaInst.setNombrePrograma(glosaDTO.getNomProg() != null ? glosaDTO.getNomProg() : "");
                glosaInst.setTipoGlosa(glosaDTO.getTipo());
                glosaInst.setNumeroGlosa(String.valueOf(Integer.valueOf(glosaDTO.getNumGlosa())));

                TextoPublicado textoPublicado = new TextoPublicado();
                Incremento incremento = new Incremento();
                Reduccion reduccion = new Reduccion();
                List<String> lineasString = new ArrayList<String>();
                List<Long> lineasInc = new ArrayList<Long>();
                List<Long> lineasReduc = new ArrayList<Long>();
                lineasString.add(glosaDTO.getTextoGlosa());
                lineasInc.add(0L);
                lineasReduc.add(0L);

                textoPublicado.setLinea(lineasString);
                incremento.setLinea(lineasInc);
                reduccion.setLinea(lineasReduc);

                glosaInst.setTextoPublicado(textoPublicado);
                glosaInst.setIncremento(incremento);
                glosaInst.setReduccion(reduccion);
                glosasInst.add(glosaInst);
            }
        }

        listaGlosasInst.setGlosas(glosasInst);

        for (DecretoModificacionPresupestariaDTO decreto : datosTDRMP) {
            ModificacionPresupuestaria mod = new ModificacionPresupuestaria();
            ListaGlosaPresupuestaria listGloPresup =  new ListaGlosaPresupuestaria();
            List<GlosaPresupuestaria> glosaCuentas = new ArrayList<GlosaPresupuestaria>();


            mod.setPartida(decreto.getCodPartida());
            mod.setCapitulo(decreto.getCodCapitulo());
            mod.setPrograma(decreto.getCodPrograma());
            mod.setTipoMoneda("CLP");
            mod.setCodigoPartida(decreto.getCodPartida());
            mod.setNombrePartida(decreto.getNomPartida());
            mod.setCodigoCapitulo(decreto.getCodCapitulo());
            mod.setNombreCapitulo(decreto.getNomCapitulo());
            mod.setCodigoPrograma(decreto.getCodPrograma());
            mod.setNombrePrograma(decreto.getNomPrograma());

            List<CuentaTDRMPDTO> cuentasDecreto = decreto.getCuentas();
            List<Movimiento> cuentas = new ArrayList<Movimiento>();

            for (CuentaTDRMPDTO cuenta : cuentasDecreto) {
                Movimiento mov = new Movimiento();
                mov.setCodigoCuenta(cuenta.getCodCuenta());
                mov.setTipoCuenta(cuenta.getTipoCuenta());
                mov.setTipoMoneda(cuenta.getTipoMoneda());
                mov.setSubtitulo(cuenta.getSubtitulo());
                mov.setItem(cuenta.getItem());
                mov.setAsignacion(cuenta.getAsignacion());
                mov.setSubAsignacion("");
                mov.setDenominacion(cuenta.getDenominacion());
                mov.setMontoIncremento(cuenta.getMtoIncremento());
                mov.setMontoReduccion(cuenta.getMtoDisminucion());
                if (cuenta.getIsDecreto().equals(1)) {
                    mov.setTipoModificacionCuenta("C");
                }
                mov.setNivel(getNivelCuenta(cuenta));
                cuentas.add(mov);

                for (int i = 0; i < glosas.size(); i++) {
                    GlosaTDRMPDTO glosaDTO = glosas.get(i);
                    Long gloIdDetTDRMP = glosaDTO.getIdDetTDRMP() != null ? glosaDTO.getIdDetTDRMP().longValue() : 0L;

                    if (gloIdDetTDRMP.equals(cuenta.getIdDetTDRMP())) {
                        GlosaPresupuestaria glosaPresup = new GlosaPresupuestaria();
                        glosaPresup.setCodigoPartida(decreto.getCodPartida());
                        glosaPresup.setNombrePartida(decreto.getNomPartida());
                        glosaPresup.setCodigoCapitulo(decreto.getCodCapitulo());
                        glosaPresup.setCodigoPrograma(decreto.getCodPrograma());
                        glosaPresup.setCodigoSubtitulo(cuenta.getSubtitulo());
                        glosaPresup.setCodigoItem(cuenta.getItem());
                        glosaPresup.setCodigoAsignacion(cuenta.getAsignacion());
                        glosaPresup.setIndicadorPresupuestoGlosa(glosaDTO.getIndPresup());
                        glosaPresup.setTipoGlosa(String.valueOf(Integer.valueOf(glosaDTO.getTipo())));
                        glosaPresup.setNumeroGlosa(glosaDTO.getNumGlosa());

                        TextoPublicado textoPublicado = new TextoPublicado();
                        Incremento incremento = new Incremento();
                        Reduccion reduccion = new Reduccion();
                        List<String> lineasString = new ArrayList<String>();
                        List<Long> lineasInc = new ArrayList<Long>();
                        List<Long> lineasReduc = new ArrayList<Long>();
                        lineasString.add(glosaDTO.getTextoGlosa());
                        lineasInc.add(0L);
                        lineasReduc.add(0L);

                        textoPublicado.setLinea(lineasString);
                        incremento.setLinea(lineasInc);
                        reduccion.setLinea(lineasReduc);

                        glosaPresup.setTextoPublicado(textoPublicado);
                        glosaPresup.setIncremento(incremento);
                        glosaPresup.setReduccion(reduccion);
                        glosaCuentas.add(glosaPresup);
                        break;
                    }
                }
            }

            listGloPresup.setGlosaPresupuestaria(glosaCuentas);
            mod.setMovimiento(cuentas);
            mod.setListaGlosaPresupuestaria(listGloPresup);
            detalleDecretoModificacionPresupuestaria.add(mod);
        }

        xmlTDRMP.setModificacionPresupuestaria(detalleDecretoModificacionPresupuestaria);
        xmlTDRMP.setListaGlosaInstitucional(listaGlosasInst);

        String xmlStringMP = null;

        try {
            JAXBContext context = JAXBContext.newInstance(XMLDocumentoTDRMP.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(xmlTDRMP, sw);

            xmlStringMP = sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String numDocFileName = "";
        String numDocString = String.valueOf(numDoc);

        if (numDocString.length() > 4) {
            numDocFileName = numDocString.substring(numDocString.length() - 4, numDocString.length() - 1);
        } else if (String.valueOf(numDoc).length() == 3) {
            numDocFileName = "0".concat(String.valueOf(numDoc));
        } else if (String.valueOf(numDoc).length() == 2) {
            numDocFileName = "00".concat(String.valueOf(numDoc));
        } else if (String.valueOf(numDoc).length() == 1) {
            numDocFileName = "000".concat(String.valueOf(numDoc));
        } else if (String.valueOf(numDoc).length() == 0) {
            numDocFileName = "0000";
        }

        String fileUploadFileName = "TDRMP"+numDocFileName+varPartida+varCapitulo+ejercicio+".xml";
        int tpArchivo = 0;
        String tipoInforme = idSistradoc;
        String varInforme = "TDRMP";
        String idEntidadUser = varPartida + varCapitulo;

        ValidarCarga reqValidarCarga = new ValidarCarga();
        reqValidarCarga.setDocumento(xmlStringMP);
        reqValidarCarga.setIdEntidad(new BigDecimal(idEntidadUser));
        reqValidarCarga.setIdInforme(new BigDecimal(idInforme));
        reqValidarCarga.setNombreArchivo(fileUploadFileName);
        reqValidarCarga.setPeriodo(new BigDecimal(idPeriodo));
        reqValidarCarga.setTipoArchivo(new BigDecimal(tpArchivo));
        reqValidarCarga.setTipoInforme(tipoInforme);
        reqValidarCarga.setUsuario(nomUsuario);
        reqValidarCarga.setVarCapitulo(varCapitulo);
        reqValidarCarga.setVarEjercicio(String.valueOf(ejercicio));
        reqValidarCarga.setVarInforme(varInforme);
        reqValidarCarga.setVarNroDocumento(numDocFileName);
        reqValidarCarga.setVarPartida(varPartida);

        ValidarCargaResponse respValidarCarga = validarCargaClient.validarCargaTDRMP(reqValidarCarga);
        BigDecimal idFileUploadBD = respValidarCarga.getIdFileUpload();

        Integer idFileUpload = idFileUploadBD.intValue();

        if (idFileUpload > 0) {
            ValidarInforme validarInformeReq = new ValidarInforme();
            validarInformeReq.setUsuario(nomUsuario);
            validarInformeReq.setIdFileUpload(idFileUploadBD);

            ValidarInformeResponse respValidarInforme = validarInformeClient.validarInformeTDRMP(validarInformeReq);
            String estadoVal = respValidarInforme.getEstado();
            String mensajeValidacion = respValidarInforme.getMensaje();

            if (!estadoVal.equals("3") && !estadoVal.equals("4") && !estadoVal.equals("5")) {
                throw new SicogenException(mensajeValidacion);
            } else {
                ResultadoEjecucion result = new ResultadoEjecucion();
                result.setCodEjec("0");
                result.setMsgEjec(mensajeValidacion);
                result.setNewId(idFileUpload);
                return result;
            }

        } else {
            throw new SicogenException(respValidarCarga.getMensajeCarga());
        }

    }

    private String getNivelCuenta(CuentaTDRMPDTO cuenta) {

        if (cuenta.getItem().equals("000")) {
            return "1";
        } else if (cuenta.getAsignacion().equals("000")){
            return "2";
        } else {
            return "3";
        }
    }

    public void createGlosa(Integer idTDRMP,
                            Integer idDetTDRMP,
                            String tipo,
                            String numero,
                            String texto,
                            String indPresup,
                            String codPartida,
                            String codCapitulo,
                            String codPrograma,
                            String nomPartida,
                            String nomCapitulo,
                            String nomPrograma) {
        digitacionMPDAO.createGlosa(
                idTDRMP,
                idDetTDRMP,
                tipo,
                numero,
                texto,
                indPresup,
                codPartida,
                codCapitulo,
                codPrograma,
                nomPartida,
                nomCapitulo,
                nomPrograma
        );
    }

    public List<GlosaTDRMPDTO> getGlosas(Integer idTDRMP,
                                         Integer idDetTDRMP,
                                         String codPartida,
                                         String codCapitulo,
                                         String codPrograma) {
        return digitacionMPDAO.getGlosas(idTDRMP, idDetTDRMP, codPartida, codCapitulo, codPrograma);
    }

    public void updateGlosa(GlosaTDRMPDTO glosa) {
        digitacionMPDAO.updateGlosa(glosa);
    }

    public void deleteGlosa(Integer idGloTDRMP) {
        digitacionMPDAO.deleteGlosa(idGloTDRMP);
    }

    public void guardarDigitacion(Integer idEjercicio,
                                  Integer idPeriodo,
                                  Integer numDoc,
                                  String tipoDocumento) {
        digitacionMPDAO.guardarDigitacion(idEjercicio, idPeriodo, numDoc, tipoDocumento);
    }

    public List<MonedaBO> getMonedasValidas() {
        return digitacionIIDAO.getMonedasValidas();
    }

    public ResultadoEjecucion insertIIDigitacionTDR(Integer idPrograma,
                                        Integer idCuenta,
                                        Integer idTblSistradoc,
                                        String moneda) throws SQLException {
        ProgramaCompletoBO programaCompleto = digitacionIIDAO.getProgramaCompleto(idPrograma);
        CtaPresupSimpleVO ctaPresupSimpleVO = catalogoPresupDAO.getCuentaPresupByID(idCuenta);
        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = new XMLDocumentoTDR_II();

        if (xmlStoredAsString != null) {

            objXmlII = parseXmlToObject(xmlStoredAsString);

            if(programaCompleto!=null && programaCompleto.getCodigoPartida()!=null &&
                    programaCompleto.getCodigoCapitulo()!=null && programaCompleto.getCodigoPrograma()!=null ){

                boolean decretoAgregado = false;
                if (objXmlII.getDecreto_II() != null)
                {
                    for( int i = 0; i< objXmlII.getDecreto_II().size() ; i++)
                    {
                        Decreto_II decretoBO =  objXmlII.getDecreto_II().get(i);
                        if( programaCompleto.getCodigoPartida().equals(decretoBO.getCodigoPartida()) &&
                                programaCompleto.getCodigoCapitulo().equals(decretoBO.getCodigoCapitulo()) &&
                                programaCompleto.getCodigoPrograma().equals(decretoBO.getCodigoPrograma()))
                        {
                            decretoAgregado = true;

                            IdentificacionProyectos identificacion =
                                    new IdentificacionProyectos(ctaPresupSimpleVO.getCodSubTitulo(),
                                            ctaPresupSimpleVO.getCodItem(), moneda);

                            if (objXmlII.getDecreto_II().get(i).getIdentificacionProyectos() != null) {

                                objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().add(identificacion);
                            } else {
                                objXmlII.getDecreto_II().get(i).setIdentificacionProyectos(new ArrayList<IdentificacionProyectos>());
                                objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().add(identificacion);
                            }
                        }
                    }
                } else {
                    objXmlII.setDecreto_II(new ArrayList<Decreto_II>());
                }

                if( !decretoAgregado ) {
                    Decreto_II decreto =
                            new Decreto_II(programaCompleto.getCodigoPartida(), programaCompleto.getNombrePartida(),
                                    programaCompleto.getCodigoCapitulo(), programaCompleto.getNombreCapitulo(),
                                    programaCompleto.getCodigoPrograma(), programaCompleto.getNombrePrograma());


                    IdentificacionProyectos identificacion =
                            new IdentificacionProyectos(ctaPresupSimpleVO.getCodSubTitulo(),
                                    ctaPresupSimpleVO.getCodItem(),moneda);

                    if (decreto.getIdentificacionProyectos() != null) {

                        decreto.getIdentificacionProyectos().add(identificacion);
                    } else {
                        decreto.setIdentificacionProyectos(new ArrayList<IdentificacionProyectos>());
                        decreto.getIdentificacionProyectos().add(identificacion);
                    }

                    if (objXmlII.getDecreto_II() != null) {
                        objXmlII.getDecreto_II().add(decreto);
                    } else {
                        objXmlII.setDecreto_II(new ArrayList<Decreto_II>());
                        objXmlII.getDecreto_II().add(decreto);
                    }
                }
            }


        } else {
            Decreto_II decreto = new Decreto_II(programaCompleto.getCodigoPartida(), programaCompleto.getNombrePartida(),
                    programaCompleto.getCodigoCapitulo(), programaCompleto.getNombreCapitulo(),
                    programaCompleto.getCodigoPrograma(), programaCompleto.getNombrePrograma());

            objXmlII.getDecreto_II().add(decreto);

        }

        String xmlAsString = parseToXML(objXmlII);
        digitacionIIDAO.updateArchivoXML(xmlAsString, idTblSistradoc);
        return new ResultadoEjecucion("0", "OK");
    }

    public ResultadoEjecucion insertIIDigitacionTDR_modi(Integer idPrograma,
                                                    Integer idCuenta,
                                                    Integer idTblSistradoc,
                                                    String moneda) throws SQLException {
        ProgramaCompletoBO programaCompleto = digitacionIIDAO.getProgramaCompleto(idPrograma);
        CtaPresupSimpleVO ctaPresupSimpleVO = catalogoPresupDAO.getCuentaPresupByID(idCuenta);
        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = new XMLDocumentoTDR_II();

        if (xmlStoredAsString != null) {
            objXmlII = parseXmlToObject(xmlStoredAsString);

            if(programaCompleto!=null && programaCompleto.getCodigoPartida()!=null &&
                    programaCompleto.getCodigoCapitulo()!=null && programaCompleto.getCodigoPrograma()!=null ){

                boolean decretoAgregado = false;
                if (objXmlII.getDecreto_II() != null) {
                    for( int i = 0; i< objXmlII.getDecreto_II().size() ; i++)
                    {
                        Decreto_II decretoBO =  objXmlII.getDecreto_II().get(i);

                        if( programaCompleto.getCodigoPartida().equals(decretoBO.getCodigoPartida()) &&
                                programaCompleto.getCodigoCapitulo().equals(decretoBO.getCodigoCapitulo()) &&
                                programaCompleto.getCodigoPrograma().equals(decretoBO.getCodigoPrograma())){
                            decretoAgregado = true;

                            ModificacionProyectos moProyectos = new ModificacionProyectos(
                                    ctaPresupSimpleVO.getCodSubTitulo(),
                                    ctaPresupSimpleVO.getCodItem(),
                                    moneda);

                            if (objXmlII.getDecreto_II().get(i).getModificacionProyectos() != null) {

                                objXmlII.getDecreto_II().get(i).getModificacionProyectos().add(moProyectos);
                            } else {
                                objXmlII.getDecreto_II().get(i).setModificacionProyectos(new ArrayList<ModificacionProyectos>());
                                objXmlII.getDecreto_II().get(i).getModificacionProyectos().add(moProyectos);
                            }

                        }
                    }
                } else {
                    objXmlII.setDecreto_II(new ArrayList<Decreto_II>());
                }

                if( !decretoAgregado ) {
                    Decreto_II decreto = new Decreto_II(programaCompleto.getCodigoPartida(), programaCompleto.getNombrePartida(),
                            programaCompleto.getCodigoCapitulo(), programaCompleto.getNombreCapitulo(),
                            programaCompleto.getCodigoPrograma(), programaCompleto.getNombrePrograma());

                    ModificacionProyectos moProyectos = new ModificacionProyectos(ctaPresupSimpleVO.getCodSubTitulo(),
                            ctaPresupSimpleVO.getCodItem(),
                            moneda);

                    decreto.setModificacionProyectos(new ArrayList<ModificacionProyectos>());
                    decreto.getModificacionProyectos().add(moProyectos);

                    if (objXmlII.getDecreto_II() != null) {
                        objXmlII.getDecreto_II().add(decreto);
                    } else {
                        objXmlII.getDecreto_II().add(decreto);
                    }
                }
            }
        }

        String xmlAsString = parseToXML(objXmlII);
        digitacionIIDAO.updateArchivoXML(xmlAsString, idTblSistradoc);
        return new ResultadoEjecucion("0", "OK");
    }

    private XMLDocumentoTDR_II parseXmlToObject(String tdrIIAsString) {

        XMLDocumentoTDR_II xmlDocumentoTDR_II = null;

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLDocumentoTDR_II.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(tdrIIAsString);
            xmlDocumentoTDR_II = (XMLDocumentoTDR_II) unmarshaller.unmarshal(reader);

        }catch(Exception e){
            e.printStackTrace();
        }

        return xmlDocumentoTDR_II;
    }

    private String parseToXML(XMLDocumentoTDR_II tdrII) {

        StringWriter sw = new StringWriter();
        String xmlAsString = new String();

        try{
            JAXBContext context = JAXBContext.newInstance(XMLDocumentoTDR_II.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(tdrII,  sw);

            System.out.println( sw.toString() );
            xmlAsString = sw.toString();

            sw.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return xmlAsString;
    }

    public XMLDocumentoTDR_II obtieneDigitacionXML(Integer idTblSistradoc,
                                                   String ejercicio,
                                                   Integer nroDocumento) throws IOException, SQLException {
        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII;

        if (xmlStoredAsString != null) {
            objXmlII = parseXmlToObject(xmlStoredAsString);
        } else {
            objXmlII = new XMLDocumentoTDR_II();

            CabeceraTDR_II cabecera = new CabeceraTDR_II();

            cabecera.setEjercicio(Integer.parseInt(ejercicio));
            cabecera.setMes("01");
            cabecera.setDia("25");
            cabecera.setCantidadDeRegistros(69);
            cabecera.setMontoTotal(39);
            cabecera.setIdDecreto(nroDocumento);
            cabecera.setSectorResponsable(98);
            cabecera.setNumeroRegistro(String.valueOf(nroDocumento));
            cabecera.setFechaRegistro("2016-01-27");
            cabecera.setTipoDeRegistro("01");
            cabecera.setNumeroTP(123456);
            cabecera.setNumeroFC(123457);
            cabecera.setNumeroIN(123458);
            cabecera.setNumeroBAV(1234567890);
            cabecera.setNumeroE(1234567890);

            cabecera.setEstadoDecreto("A");
            cabecera.setEstadoDelRegistro(1);

            cabecera.setFirmaJefeSector("cGVwaXRvIGxvcyBwYWxvdGVzDQo=");
            cabecera.setInicialesJefeSector("PBE");

            cabecera.setFirmaJefeDAP("cGVwaXRvIGxvcyBwYWxvdGVzDQo=");
            cabecera.setInicialesJefeDAP("ABC");

            cabecera.setFirmaSubdirector("cGVwaXRvIGxvcyBwYWxvdGVzDQo=");
            cabecera.setInicialesSubdirector("RFG");

            cabecera.setFirmaDirector("cGVwaXRvIGxvcyBwYWxvdGVzDQo=");
            cabecera.setInicialesDirector("TTY");


            List<FirmaMinistro> firmaMinistros = new ArrayList<FirmaMinistro>();
            FirmaMinistro firmaMinistro = new FirmaMinistro();

            firmaMinistro.setNombre("texto 80 caracteres");
            firmaMinistro.setCargo("texto cargo 80 caracteres");
            firmaMinistro.setFirmaTimbre("cGVwaXRvIGxvcyBwYWxvdGVzDQo=");
            firmaMinistros.add(firmaMinistro);

            cabecera.setFirmaMinistros(firmaMinistros);

            cabecera.setTimbreLateralCGR("cGVwaXRvIGxvcyBwYWxvdGVzDQo=");
            cabecera.setLogoGobierno("cGVwaXRvIGxvcyBwYWxvdGVzDQo=");

            objXmlII.setCabecera(cabecera);

            String xmlAsString = parseToXML(objXmlII);

            File file = new File("temp");

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(xmlAsString);

            fileWriter.flush();
            fileWriter.close();

            FileInputStream fstream = new FileInputStream(file);

            StringBuilder builder = new StringBuilder();
            int ch;
            while((ch = fstream.read()) != -1){
                builder.append((char)ch);
            }

            digitacionIIDAO.insertArchivoXML(builder.toString(), idTblSistradoc);

        }

        return objXmlII;
    }

    public ResultadoEjecucion deleteSeccionDecretoIdentificacion(Integer idTblSistradoc,
                                                                 String codPart,
                                                                 String codCap,
                                                                 String codProg,
                                                                 String codSubt,
                                                                 String codItem,
                                                                 String moneda) throws SQLException {
        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);

        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);

        if(objXmlII.getDecreto_II() != null){

            for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){

                if(codPart.equals( objXmlII.getDecreto_II().get(i).getCodigoPartida()) ){

                    if(codCap.equals( objXmlII.getDecreto_II().get(i).getCodigoCapitulo()) ){

                        if(codProg.equals( objXmlII.getDecreto_II().get(i).getCodigoPrograma()) ){

                            if(objXmlII.getDecreto_II().get(i).getIdentificacionProyectos() != null){

                                for(int j=0 ; j < objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().size() ; j++){

                                    if(codSubt.equals( objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getCodigoSubtitulo()) ){

                                        if(codItem.equals( objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getCodigoItem()) ){

                                            if(moneda.equals( objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getMoneda()) ){
                                                objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().remove(j);

                                                if( objXmlII.getDecreto_II().get(i).getModificacionProyectos().size() == 0 &&
                                                        objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().size() == 0 ){
                                                    objXmlII.getDecreto_II().remove(i);
                                                }

                                                String xmlUpdated =  parseToXML(objXmlII);
                                                digitacionIIDAO.updateArchivoXML(xmlUpdated, idTblSistradoc);

                                                return new ResultadoEjecucion("0", "OK");
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

            }
        }

        String xmlUpdated =  parseToXML(objXmlII);
        digitacionIIDAO.updateArchivoXML(xmlUpdated, idTblSistradoc);

        return new ResultadoEjecucion("0", "OK");
    }

    public ResultadoEjecucion deleteSeccionDecretoModificacion(Integer idTblSistradoc,
                                                               String codPart,
                                                               String codCap,
                                                               String codProg,
                                                               String codSubt,
                                                               String codItem,
                                                               String moneda) throws SQLException {
        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(Integer.valueOf(idTblSistradoc));

        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);

        if(objXmlII.getDecreto_II() != null){

            for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){

                if(codPart.equals( objXmlII.getDecreto_II().get(i).getCodigoPartida())){
                    if(codCap.equals( objXmlII.getDecreto_II().get(i).getCodigoCapitulo())){
                        if(codProg.equals( objXmlII.getDecreto_II().get(i).getCodigoPrograma())){

                            if(objXmlII.getDecreto_II().get(i).getModificacionProyectos() != null){

                                for(int j=0 ; j < objXmlII.getDecreto_II().get(i).getModificacionProyectos().size() ; j++){

                                    if(codSubt.equals( objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getCodigoSubtitulo()) ){
                                        if(codItem.equals( objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getCodigoItem()) ){
                                            if(moneda.equals( objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getMoneda()) ){

                                                objXmlII.getDecreto_II().get(i).getModificacionProyectos().remove(j);

                                                if( objXmlII.getDecreto_II().get(i).getModificacionProyectos().size() == 0 &&
                                                        objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().size() == 0 )
                                                {
                                                    objXmlII.getDecreto_II().remove(i);
                                                }

                                                String xmlUpdated =  parseToXML(objXmlII);
                                                digitacionIIDAO.updateArchivoXML(xmlUpdated, idTblSistradoc);
                                                return new ResultadoEjecucion("0", "OK");
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

            }
        }

        return new ResultadoEjecucion("0", "OK");
    }

    public List<JsonJTableExpenseBean> getProyectoByCodigo(String codigoProyecto) {
        return digitacionIIDAO.getProyectoByCodigo(codigoProyecto);
    }

    public ResultadoEjecucion postProyectosIIIdent(String codProyecto,
                                                   String codPart,
                                                   String codCap,
                                                   String codProg,
                                                   String codSubt,
                                                   String codItem,
                                                   Integer idTblSistradoc,
                                                   HttpServletRequest request) throws SQLException {
        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);

        String[] parts = codProyecto.split("-");

        DetalleIdentificacionProyectos detalle = new DetalleIdentificacionProyectos(parts[0].trim(),
                parts[1].trim(), digitacionIIDAO.getDenominacionProyecto(parts[0].trim()));

        List<MontosAsignacion> montosAsignacion = new ArrayList<MontosAsignacion>();
        MontosAsignacion montoAsignacion;
        Map<String, Object> hashMap = new HashMap<String, Object>();

        Map<String,Object> allMap = request.getParameterMap();
        for(String key:allMap.keySet())
        {

            if(key.contains("asig_ident_"))
            {
                String[] strArr=(String[])allMap.get(key);
                for(String val:strArr)
                {
                    montoAsignacion = new MontosAsignacion();

                    String asignacionText = key.replace("asig_ident_", "");
                    String[] parts2 = asignacionText.split("_");
                    String part1 = parts2[0];
                    String part2 = parts2[1];

                    montoAsignacion.setCodigoAsignacion(part1);
                    montoAsignacion.setNombreAsignacion(part2);
                    montoAsignacion.setMonto(val);

                    hashMap.put(montoAsignacion.getCodigoAsignacion() , montoAsignacion);
                }
            }
        }

        SortedSet<String> keys = new TreeSet<String>(hashMap.keySet());
        java.util.Iterator<String> it = keys.iterator();

        while (it.hasNext()) {
            String key = it.next();
            MontosAsignacion value = (MontosAsignacion) hashMap.get(key);
            montosAsignacion.add(value);
        }

        detalle.setMontosAsignacion(montosAsignacion);

        setAsignacionesToXMLIdent(codPart, codCap, codProg, codSubt, codItem, detalle, objXmlII);

        String xmlUpdated = parseToXML(objXmlII);

        digitacionIIDAO.updateArchivoXML(xmlUpdated, idTblSistradoc);

        return new ResultadoEjecucion("0", "OK");
    }

    private void setAsignacionesToXMLIdent(String codPart,
                                           String codCap,
                                           String codProg,
                                           String codSubt,
                                           String codItem,
                                           DetalleIdentificacionProyectos detalle,
                                           XMLDocumentoTDR_II objXmlII){

        if(codPart!=null && codCap!=null && codProg!=null && codSubt!=null && codItem!=null ){

            if(objXmlII.getDecreto_II() != null){

                for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){
                    if(codPart.equals( objXmlII.getDecreto_II().get(i).getCodigoPartida()) ){
                        if(codCap.equals( objXmlII.getDecreto_II().get(i).getCodigoCapitulo()) ){
                            if(codProg.equals( objXmlII.getDecreto_II().get(i).getCodigoPrograma()) ){

                                if(objXmlII.getDecreto_II().get(i).getIdentificacionProyectos() != null){
                                    for(int j=0 ; j < objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().size() ; j++)
                                    {
                                        if(codSubt.equals( objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getCodigoSubtitulo()) ){
                                            if(codItem.equals( objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getCodigoItem()) ){

                                                if(objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getDetalleIdentificacionProyectos()!=null)
                                                {
                                                    objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getDetalleIdentificacionProyectos().add(detalle);
                                                } else {
                                                    List<DetalleIdentificacionProyectos> list = new ArrayList<DetalleIdentificacionProyectos>();
                                                    list.add(detalle);
                                                    objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).setDetalleIdentificacionProyectos(list);
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }

                }
            }
        }
    }

    public ResultadoEjecucion postProyectosIIMod(String codProyecto,
                                                   String codPart,
                                                   String codCap,
                                                   String codProg,
                                                   String codSubt,
                                                   String codItem,
                                                   Integer idTblSistradoc,
                                                   HttpServletRequest request) throws SQLException {
        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);
        DetalleModificacionProyectos detalleModificacionProyectos = null;

        String[] parts = codProyecto.split("-");

        if(parts != null && parts.length > 1){
            detalleModificacionProyectos =
                    new DetalleModificacionProyectos(parts[0].trim(),parts[1].trim(),
                            digitacionIIDAO.getDenominacionProyecto(parts[0].trim()));

        } else if (parts != null && parts.length == 1){
            detalleModificacionProyectos =
                    new DetalleModificacionProyectos(parts[0].trim(),"",
                            digitacionIIDAO.getDenominacionProyecto(parts[0].trim()));
        }

        List<MontosAsignacion> montosAsignacion = new ArrayList<MontosAsignacion>();
        MontosAsignacion montoAsignacion = null;

        Map<String, Object> hashMap = new HashMap<String, Object>();

        Map<String,Object> allMap=request.getParameterMap();
        for(String key:allMap.keySet())
        {
            if(key.contains("asig_mod_"))
            {
                String[] strArr=(String[])allMap.get(key);
                for(String val:strArr)
                {
                    montoAsignacion = new MontosAsignacion();
                    String asignacionText = key.replace("asig_mod_", "");

                    String[] parts2 = asignacionText.split("_");
                    String part1 = parts2[0];
                    String part2 = parts2[1];

                    montoAsignacion.setCodigoAsignacion(part1);
                    montoAsignacion.setNombreAsignacion(part2);
                    montoAsignacion.setMonto(val);

                    hashMap.put(montoAsignacion.getCodigoAsignacion() , montoAsignacion);
                }
            }
        }

        SortedSet<String> keys = new TreeSet<String>(hashMap.keySet());
        java.util.Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            MontosAsignacion value = (MontosAsignacion) hashMap.get(key);
            montosAsignacion.add(value);
        }

        detalleModificacionProyectos.setMontosAsignacion(montosAsignacion);

        setAsignacionesToXMLMod(codPart, codCap, codProg, codSubt, codItem,detalleModificacionProyectos, objXmlII);

        String xmlUpdated =  parseToXML(objXmlII);

        digitacionIIDAO.updateArchivoXML(xmlUpdated, idTblSistradoc);

        return new ResultadoEjecucion("0", "OK");
    }

    private XMLDocumentoTDR_II setAsignacionesToXMLMod(String codPart,
                                                       String codCap,
                                                       String codProg,
                                                       String codSubt,String codItem,
                                                       DetalleModificacionProyectos detalle,
                                                       XMLDocumentoTDR_II objXmlII){
        if(objXmlII.getDecreto_II() != null){

            for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){
                if(codPart.equals( objXmlII.getDecreto_II().get(i).getCodigoPartida()) ){
                    if(codCap.equals( objXmlII.getDecreto_II().get(i).getCodigoCapitulo()) ){
                        if(codProg.equals( objXmlII.getDecreto_II().get(i).getCodigoPrograma()) ){

                            if(objXmlII.getDecreto_II().get(i).getModificacionProyectos() != null)
                            {
                                for(int j=0 ; j < objXmlII.getDecreto_II().get(i).getModificacionProyectos().size() ; j++)
                                {
                                    if(codSubt.equals( objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getCodigoSubtitulo()) ){
                                        if(codItem.equals( objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getCodigoItem()) ){

                                            if(objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getDetalleModificacionProyectos()!=null)
                                            {
                                                objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getDetalleModificacionProyectos().add(detalle);
                                            } else {
                                                List<DetalleModificacionProyectos> list = new ArrayList<DetalleModificacionProyectos>();

                                                list.add(detalle);

                                                objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).setDetalleModificacionProyectos(list);
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

            }
        }

        return objXmlII;

    }

    public List<MontosAsignacion> getEditarAsigProyectos(String idDecreto,
                                                         String idMIIProyectos,
                                                         String idDetalleMIIProyectos,
                                                         String flagIdentificacion,
                                                         Integer idTblSistradoc) throws SQLException {

        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);
        List<MontosAsignacion> montosAsignacion = new ArrayList<MontosAsignacion>();

        if(flagIdentificacion!=null && idDecreto!=null && idMIIProyectos!=null && idDetalleMIIProyectos!=null ){
            if(Boolean.parseBoolean(flagIdentificacion) && objXmlII.getDecreto_II() != null){
                // Edita Identificaci�n de Iniciativas
                for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){ // Decretos
                    if( idDecreto.equals(objXmlII.getDecreto_II().get(i).get_IdDecreto_II()) ){
                        // Iteracion : Identificaci�n de Iniciativas
                        for(int j=0 ; j < objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().size() ; j++){

                            if(idMIIProyectos.equals( objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).get_idIdentificacionProyectos()) ){
                                if(objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getDetalleIdentificacionProyectos() != null){

                                    for(int k=0; k < objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getDetalleIdentificacionProyectos().size() ; k++){

                                        if(idDetalleMIIProyectos.equals(objXmlII.getDecreto_II().get(i).
                                                getIdentificacionProyectos().get(j).
                                                getDetalleIdentificacionProyectos().get(k).
                                                get_idDetalleIdentificacionProyectos()))
                                        {
                                            montosAsignacion = objXmlII.getDecreto_II().get(i).
                                                    getIdentificacionProyectos().get(j).
                                                    getDetalleIdentificacionProyectos().get(k).getMontosAsignacion();
                                        }
                                    }
                                }
                            }

                        }
                    }

                }
            } else {
                // Edita Modificacion de Iniciativas
                for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){ // Decretos
                    if( idDecreto.equals( objXmlII.getDecreto_II().get(i).get_IdDecreto_II()) ){
                        // Iteracion : Modificacion de Iniciativas
                        for(int j=0 ; j < objXmlII.getDecreto_II().get(i).getModificacionProyectos().size() ; j++){
                            if(idMIIProyectos.equals( objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).get_IdModificacionProyectos()) ){
                                if(objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getDetalleModificacionProyectos() != null){
                                    for(int k=0; k < objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getDetalleModificacionProyectos().size() ; k++){

                                        if(idDetalleMIIProyectos.equals(objXmlII.getDecreto_II().get(i).
                                                getModificacionProyectos().get(j).
                                                getDetalleModificacionProyectos().get(k).
                                                get_idDetalleModificacionProyectos()))
                                        {

                                            montosAsignacion = objXmlII.getDecreto_II().get(i).
                                                    getModificacionProyectos().get(j).
                                                    getDetalleModificacionProyectos().get(k).getMontosAsignacion();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return montosAsignacion;
    }

    public void postEditarAsigProyectos(Map<String,Object> allMap,
                                        Integer idTblSistradoc,
                                        String idDecreto,
                                        String idMIIProyectos,
                                        String idDetalleMIIProyectos,
                                        String flagIdentificacion) throws SQLException {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        MontosAsignacion _montoAsignacion;
        for(String key:allMap.keySet())
        {
            if(key.contains("asig"))
            {
                String[] strArr=(String[])allMap.get(key);
                for(String val:strArr)
                {
                    _montoAsignacion = new MontosAsignacion();
                    String asignacionText = key.replace("asig_edit_", "");

                    String[] parts2 = asignacionText.split("_");
                    String part1 = parts2[0];
                    String part2 = parts2[1];

                    _montoAsignacion.setCodigoAsignacion(part1);
                    _montoAsignacion.setNombreAsignacion(part2);
                    _montoAsignacion.setMonto(val);

                    hashMap.put(_montoAsignacion.getCodigoAsignacion() , _montoAsignacion);
                }
            }
        }

        List<MontosAsignacion> _montosAsignacion = new ArrayList<MontosAsignacion>();
        SortedSet<String> keys = new TreeSet<String>(hashMap.keySet());
        java.util.Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            MontosAsignacion value = (MontosAsignacion) hashMap.get(key);
            _montosAsignacion.add(value);
        }

        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);

        if(flagIdentificacion!=null && idDecreto!=null && idMIIProyectos!=null && idDetalleMIIProyectos!=null ){

            if(Boolean.parseBoolean(flagIdentificacion) && objXmlII.getDecreto_II() != null){
                // Edita Identificaci�n de Iniciativas
                for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){ // Decretos
                    if( idDecreto.equals( objXmlII.getDecreto_II().get(i).get_IdDecreto_II()) ){
                        // Iteracion : Identificaci�n de Iniciativas
                        for(int j=0 ; j < objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().size() ; j++){

                            if(idMIIProyectos.equals( objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).get_idIdentificacionProyectos()) ){
                                if(objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getDetalleIdentificacionProyectos() != null){

                                    for(int k=0; k < objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getDetalleIdentificacionProyectos().size() ; k++){

                                        if(idDetalleMIIProyectos.equals(objXmlII.getDecreto_II().get(i).
                                                getIdentificacionProyectos().get(j).
                                                getDetalleIdentificacionProyectos().get(k).
                                                get_idDetalleIdentificacionProyectos()))
                                        {
                                            objXmlII.getDecreto_II().get(i).
                                                    getIdentificacionProyectos().get(j).
                                                    getDetalleIdentificacionProyectos().get(k).setMontosAsignacion(_montosAsignacion);
                                        }
                                    }
                                }
                            }

                        }
                    }

                }
            } else {
                // Edita Modificacion de Iniciativas
                for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){ // Decretos
                    if( idDecreto.equals( objXmlII.getDecreto_II().get(i).get_IdDecreto_II()) ){
                        // Iteracion : Modificacion de Iniciativas
                        for(int j=0 ; j < objXmlII.getDecreto_II().get(i).getModificacionProyectos().size() ; j++){
                            if(idMIIProyectos.equals( objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).get_IdModificacionProyectos()) ){
                                if(objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getDetalleModificacionProyectos() != null){
                                    for(int k=0; k < objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getDetalleModificacionProyectos().size() ; k++){

                                        if(idDetalleMIIProyectos.equals(objXmlII.getDecreto_II().get(i).
                                                getModificacionProyectos().get(j).
                                                getDetalleModificacionProyectos().get(k).
                                                get_idDetalleModificacionProyectos()))
                                        {

                                            objXmlII.getDecreto_II().get(i).
                                                    getModificacionProyectos().get(j).
                                                    getDetalleModificacionProyectos().get(k).setMontosAsignacion(_montosAsignacion);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        String xmlUpdated =  parseToXML(objXmlII);

        digitacionIIDAO.updateArchivoXML(xmlUpdated, idTblSistradoc);
    }

    public void deleteProyectAsignacion(Integer idTblSistradoc,
                                        String codPart,
                                        String codCap,
                                        String codProg,
                                        String codSubt,
                                        String codItem,
                                        String codigoBIP) throws SQLException {

        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);

        if(objXmlII.getDecreto_II() !=  null){

            for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){

                if(codPart.equals( objXmlII.getDecreto_II().get(i).getCodigoPartida()) ){

                    if(codCap.equals( objXmlII.getDecreto_II().get(i).getCodigoCapitulo()) ){

                        if(codProg.equals( objXmlII.getDecreto_II().get(i).getCodigoPrograma()) ){

                            if(objXmlII.getDecreto_II().get(i).getIdentificacionProyectos()!=null){

                                for(int j=0 ; j < objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().size() ; j++){

                                    if(codSubt.equals( objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getCodigoSubtitulo()) ){

                                        if(codItem.equals( objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getCodigoItem()) ){

                                            if(objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getDetalleIdentificacionProyectos() != null){

                                                for(int k=0; k < objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getDetalleIdentificacionProyectos().size() ; k++){

                                                    if(objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getDetalleIdentificacionProyectos().get(k).getCodigoBIP().equals(codigoBIP)){
                                                        objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getDetalleIdentificacionProyectos().remove(k);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            if(objXmlII.getDecreto_II().get(i).getModificacionProyectos()!=null){

                                for(int j=0 ; j < objXmlII.getDecreto_II().get(i).getModificacionProyectos().size() ; j++){

                                    if(codSubt.equals( objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getCodigoSubtitulo()) ){

                                        if(codItem.equals( objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getCodigoItem()) ){

                                            if(objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getDetalleModificacionProyectos() != null){

                                                for(int k=0; k < objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getDetalleModificacionProyectos().size() ; k++){

                                                    if(objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getDetalleModificacionProyectos().get(k).getCodigoBIP().equals(codigoBIP)){
                                                        objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getDetalleModificacionProyectos().remove(k);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

            }
        }

        String xmlUpdated =  parseToXML(objXmlII);
        digitacionIIDAO.updateArchivoXML(xmlUpdated, idTblSistradoc);
    }

    public LimiteMaximoCompromiso getLimitesFuturos(String anioDocumento,
                                                    String codigoBIP,
                                                    String dvProyecto,
                                                    String moneda) {
        List<CompromisoFuturo> montosAnio = new ArrayList<CompromisoFuturo>();
        LimiteMaximoCompromiso limiteTemp = new LimiteMaximoCompromiso();

        int anioSiguiente, anioActual;

        try{
            anioActual = Integer.parseInt(anioDocumento);

        }catch(Exception e){
            Calendar cal= Calendar.getInstance();
            anioActual= cal.get(Calendar.YEAR);
            e.printStackTrace();
        }
        CompromisoFuturo compromisosFuturos = null;

        String aniosFuturos = parametrosDAO.getParametroValue("LIM_COMPROMISO");
        if(aniosFuturos!=null){
            Integer aniosFutorosInt = Integer.parseInt(aniosFuturos);
            for(int i = 0 ; i< aniosFutorosInt; i++){
                compromisosFuturos = new CompromisoFuturo();
                anioSiguiente = anioActual + (i+1);
                compromisosFuturos.setAnio(String.valueOf(anioSiguiente));
                compromisosFuturos.setMonto("0");
                montosAnio.add(compromisosFuturos);
            }
        }

        limiteTemp.setCodigoBIP(codigoBIP);
        limiteTemp.setCodigoInternoDecretoSIAP(dvProyecto);
        limiteTemp.setDenominacion("ASAsasasasa");
        limiteTemp.setMoneda(moneda);
        limiteTemp.setMontosAnio(montosAnio);

        return limiteTemp;
    }

    public void agregarLimitesFuturos(Map<String,Object> allMap,
                                      Integer idTblSistradoc,
                                      String codigoBIP,
                                      String dvProyecto,
                                      String moneda,
                                      String codPart,
                                      String codCap,
                                      String codProg) throws SQLException {
        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);
        List<CompromisoFuturo> listaCompromisoFuturo = new ArrayList<CompromisoFuturo>();
        CompromisoFuturo compromisoFuturo = null;
        Map<String, String> hashMap = new HashMap<String, String>();

        for(String key:allMap.keySet())
        {
            {
                String[] strArr=(String[])allMap.get(key);
                for(String val:strArr)
                {
                    if(key.contains("add_lim_fut_")){

                        hashMap.put(key.replace("add_lim_fut_", ""), val);

                    }
                }
            }
        }

        SortedSet<String> keys = new TreeSet<String>(hashMap.keySet());
        java.util.Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = hashMap.get(key);

            compromisoFuturo = new CompromisoFuturo();
            compromisoFuturo.setMonto( value);
            compromisoFuturo.setAnio( key );
            listaCompromisoFuturo.add(compromisoFuturo);
        }

        LimiteMaximoCompromiso limite = new LimiteMaximoCompromiso(codigoBIP,dvProyecto,
                digitacionIIDAO.getDenominacionProyecto(codigoBIP), moneda);

        limite.setMontosAnio(listaCompromisoFuturo);

        if(objXmlII.getDecreto_II() != null){

            for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){

                if(codPart.equals( objXmlII.getDecreto_II().get(i).getCodigoPartida()) ){
                    if(codCap.equals( objXmlII.getDecreto_II().get(i).getCodigoCapitulo()) ){
                        if(codProg.equals( objXmlII.getDecreto_II().get(i).getCodigoPrograma()) ){

                            if(objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos() != null ){
                                objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos().add(limite);
                            } else {
                                List<LimiteMaximoCompromiso> limiteMaximoCompromisos = new ArrayList<LimiteMaximoCompromiso>();
                                limiteMaximoCompromisos.add(limite);
                                objXmlII.getDecreto_II().get(i).setLimiteMaximoCompromisos(limiteMaximoCompromisos);
                            }
                        }
                    }
                }

            }
        }

        String xmlUpdated =  parseToXML(objXmlII);
        digitacionIIDAO.updateArchivoXML(xmlUpdated, idTblSistradoc);
    }

    public LimiteMaximoCompromiso getEditarLimitesFuturos(Integer idTblSistradoc,
                                                          String idDecreto,
                                                          String idLimiteCompromiso) throws SQLException {

        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);
        LimiteMaximoCompromiso limiteTemp = new LimiteMaximoCompromiso();

        if (idDecreto!=null & idLimiteCompromiso!=null) {

            if(objXmlII.getDecreto_II() != null){
                for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){
                    if(objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos()!=null
                            && idDecreto.equals( objXmlII.getDecreto_II().get(i).get_IdDecreto_II()) )
                    {
                        for(int k = 0 ; k < objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos().size() ; k++){
                            if(idLimiteCompromiso.equals(
                                    objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos().get(k).get_idLimiteMaximoCompromiso()))
                            {
                                limiteTemp = objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos().get(k);
                            }
                        }
                    }
                }
            }
        }

        return limiteTemp;
    }

    public void editarLimitesPost(Map<String,Object> allMap,
                                  Integer idTblSistradoc,
                                  String idDecreto,
                                  String idLimiteCompromiso) throws SQLException {
        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);
        List<CompromisoFuturo> _listaCompromisoFuturo = new ArrayList<CompromisoFuturo>();
        CompromisoFuturo compromisoFuturo = null;
        Map<String, String> hashMap = new HashMap<String, String>();

        for(String key:allMap.keySet())
        {
            {
                String[] strArr=(String[])allMap.get(key);
                for(String val:strArr)
                {
                    if(key.contains("edit_lim_fut_")){

                        hashMap.put(key.replace("edit_lim_fut_", ""), val);

                    }
                }
            }
        }

        SortedSet<String> keys = new TreeSet<String>(hashMap.keySet());
        java.util.Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = hashMap.get(key);

            compromisoFuturo = new CompromisoFuturo();
            compromisoFuturo.setMonto( value);
            compromisoFuturo.setAnio( key );
            _listaCompromisoFuturo.add(compromisoFuturo);
        }

        if(idDecreto != null && idLimiteCompromiso != null){

            if(objXmlII.getDecreto_II() != null){
                for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){
                    if(objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos()!=null
                            && idDecreto.equals( objXmlII.getDecreto_II().get(i).get_IdDecreto_II()) )
                    {
                        for(int k = 0 ; k < objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos().size() ; k++){
                            if(idLimiteCompromiso.equals(
                                    objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos().get(k).get_idLimiteMaximoCompromiso()))
                            {
                                objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos().get(k).setMontosAnio(_listaCompromisoFuturo);
                            }
                        }
                    }
                }
            }
        }

        String xmlUpdated =  parseToXML(objXmlII);
        digitacionIIDAO.updateArchivoXML(xmlUpdated, idTblSistradoc);
    }

    public void deleteLimiteFuturo(Integer idTblSistradoc,
                                   String codPart,
                                   String codCap,
                                   String codProg,
                                   String codigoBIP) throws SQLException {
        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);

        if(objXmlII.getDecreto_II() != null){

            for(int i = 0 ; i < objXmlII.getDecreto_II().size() ; i++ ){

                if(codPart.equals( objXmlII.getDecreto_II().get(i).getCodigoPartida()) ){

                    if(codCap.equals( objXmlII.getDecreto_II().get(i).getCodigoCapitulo()) ){

                        if(codProg.equals( objXmlII.getDecreto_II().get(i).getCodigoPrograma()) ){

                            if(objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos() != null){

                                for(int j=0 ; j < objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos().size() ; j++){

                                    if(codigoBIP.equals( objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos().get(j).getCodigoBIP()) ){
                                        objXmlII.getDecreto_II().get(i).getLimiteMaximoCompromisos().remove(j);
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        String xmlUpdated =  parseToXML(objXmlII);
        digitacionIIDAO.updateArchivoXML(xmlUpdated, idTblSistradoc);
    }

    public ResultadoEjecucion validarDigitacionTDRII(Integer idTblSistradoc,
                                                     Integer numDoc,
                                                     String tipoDoc,
                                                     Integer idEjercicio,
                                                     Integer idPeriodo,
                                                     Integer idInforme,
                                                     String ejercicio,
                                                     String usuario) throws SQLException {
        String xmlStoredAsString = digitacionIIDAO.obtieneDigitacionXML(idTblSistradoc);
        XMLDocumentoTDR_II objXmlII = parseXmlToObject(xmlStoredAsString);

        if(objXmlII.getDecreto_II() == null){
            return new ResultadoEjecucion("-1", "Debe completar el Documento antes de validar");
        } else {
            for (int i=0; i < objXmlII.getDecreto_II().size() ; i++){

                if (objXmlII.getDecreto_II().get(i).getModificacionProyectos() != null){
                    for (int j =0 ; objXmlII.getDecreto_II().get(i).getModificacionProyectos().size() > j ; j++){
                        if(objXmlII.getDecreto_II().get(i).getModificacionProyectos().get(j).getDetalleModificacionProyectos() == null){
                            return new ResultadoEjecucion("-1", "Debe completar la Modificación de Iniciativas antes de validar");
                        }

                    }
                }
            }

            for (int i=0; i < objXmlII.getDecreto_II().size() ; i++){

                if (objXmlII.getDecreto_II().get(i).getIdentificacionProyectos() != null){
                    for (int j =0 ; objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().size() > j ; j++){
                        if(objXmlII.getDecreto_II().get(i).getIdentificacionProyectos().get(j).getDetalleIdentificacionProyectos() == null){
                            return new ResultadoEjecucion("-1", "Debe completar la Identificación de Iniciativas antes de validar");
                        }

                    }
                }
            }
        }

        String varInforme = "TDRII";
        String varPartida = sistradocDAO.getPartida(numDoc, tipoDoc, idEjercicio, idPeriodo, idInforme);
        String varCapitulo = sistradocDAO.getCapitulo(numDoc, tipoDoc, idEjercicio, idPeriodo, idInforme);
        String idEntidadUser = varPartida + varCapitulo;
        String fileUploadFileName = varInforme + numDoc + varPartida + varCapitulo + ejercicio;

        ValidarCarga validarCargaReq = new ValidarCarga();
        validarCargaReq.setVarInforme(varInforme);
        validarCargaReq.setVarNroDocumento(String.valueOf(numDoc));
        validarCargaReq.setVarPartida(varPartida);
        validarCargaReq.setVarCapitulo(varCapitulo);
        validarCargaReq.setVarEjercicio(ejercicio);
        validarCargaReq.setPeriodo(new BigDecimal(idPeriodo));
        validarCargaReq.setIdEntidad(new BigDecimal(idEntidadUser));
        validarCargaReq.setUsuario(usuario);
        validarCargaReq.setTipoArchivo(new BigDecimal(0));
        validarCargaReq.setIdInforme(new BigDecimal(idInforme));
        validarCargaReq.setNombreArchivo(fileUploadFileName);
        validarCargaReq.setTipoInforme(String.valueOf(idTblSistradoc));
        validarCargaReq.setDocumento(xmlStoredAsString);

        ValidarCargaResponse validarCargaResp = validarCargaClient.validarCargaTDRMP(validarCargaReq);
        BigDecimal idFileUploadBD = validarCargaResp.getIdFileUpload();

        Integer idFileUpload = idFileUploadBD.intValue();

        if (idFileUpload > 0) {
            cl.contraloria.sicogen.webservices.validacion.informe.tdrii.ValidarInforme validarInformeReq = new cl.contraloria.sicogen.webservices.validacion.informe.tdrii.ValidarInforme();
            validarInformeReq.setUsuario(usuario);
            validarInformeReq.setIdFileUpload(idFileUploadBD);

            cl.contraloria.sicogen.webservices.validacion.informe.tdrii.ValidarInformeResponse validarInformeResp = validarInformeTDRIIClient.validarInformeTDRII(validarInformeReq);

            String estadoVal = validarInformeResp.getEstado();
            String mensajeValidacion = validarInformeResp.getMensaje();

            if (!estadoVal.equals("3") && !estadoVal.equals("4") && !estadoVal.equals("5")) {
                return new ResultadoEjecucion(estadoVal, mensajeValidacion);
            } else {
                ResultadoEjecucion result = new ResultadoEjecucion();
                result.setCodEjec("0");
                result.setMsgEjec(mensajeValidacion);
                result.setNewId(idFileUpload);
                return result;
            }

        } else {
            return new ResultadoEjecucion("-1", validarCargaResp.getMensajeCarga());
        }


    }

    public XMLDocumentoTDR_II obtenerXmlTDRII(Integer idFileUp) throws Exception {
        return parseXmlToObject(informesDAO.obtieneXMLInforme(idFileUp));
    }
}
