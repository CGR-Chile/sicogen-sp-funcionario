package cl.contraloria.sicogen.service;

import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.utils.CustomBorderPDF;
import cl.contraloria.sicogen.utils.SolidLine;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PdfService {

    private InformesService informesService;

    public PdfService(InformesService informesService) {
        this.informesService = informesService;
    }

    public Map<String, InputStreamResource> generaPdfReporteValidacionTDRMP(URL logoSicogen,
                                                                            Integer idArchivo,
                                                                            String idSistradoc,
                                                                            URL tipError1,
                                                                            URL tipError2,
                                                                            URL tipError3) throws Exception {

        InformacionGeneralRV datosGeneralInforme = informesService.obtieneInfoGeneralRV(idArchivo);
        InformeSistradocBO datosSistradoc = informesService.getInformeTDR(idSistradoc);
        InformeTDRRV datosXML = informesService.getDatosXMLInformeTDR(idArchivo);
        InformeArchivoDet datosArchivo = informesService.getArchivo(idArchivo);

        Document document = new Document(PageSize.A4, 20, 10, 10, 10);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        // Base Colors
        BaseColor bckTabla = new BaseColor(215,215,217);
        BaseColor bckTitulos = new BaseColor(63,135,193);
        BaseColor colWhite = new BaseColor(255, 255, 255);
        BaseColor bcTextosEncabezado = new BaseColor(102, 102, 102);
        BaseColor colTextEncTblDet = new BaseColor(69, 70, 72);

        // Fonts
        Font tituloReporteFont = new Font(Font.getFamily("Arial"), 14, Font.BOLD);
        Font fntTitulos = new Font(Font.getFamily("Arial"), 10, Font.BOLD);
        Font fntTitulosEncabezados = new Font(Font.getFamily("Arial"), 10, Font.BOLD);
        Font fntDatosEncabezados = new Font(Font.getFamily("Arial"), 10);
        Font fntEncTblDet = new Font(Font.getFamily("Arial"), 7, Font.BOLD);
        Font fntDatosEncabezadosSmall = new Font(Font.getFamily("Arial"), 8);
        Font fntTitulosSmall = new Font(Font.getFamily("Arial"), 7, Font.BOLD);
        fntTitulos.setColor(colWhite);
        fntTitulosEncabezados.setColor(bcTextosEncabezado);
        fntDatosEncabezados.setColor(bcTextosEncabezado);
        fntDatosEncabezadosSmall.setColor(bcTextosEncabezado);
        fntEncTblDet.setColor(colTextEncTblDet);
        fntTitulosSmall.setColor(colWhite);

        // Logo sicogen
        Image image = Image.getInstance(logoSicogen);
        image.setAlignment(Image.LEFT | 0);
        image.setBorder(Image.NO_BORDER);
        image.scaleToFit(200, 50);

        PdfPTable logoSicogenTable = new PdfPTable(1);
        PdfPCell logoSicogenCell = new PdfPCell(image);
        logoSicogenCell.setBorder(PdfPCell.NO_BORDER);
        logoSicogenTable.addCell(logoSicogenCell);
        document.add(logoSicogenTable);

        // Tiutulo Reporte
        PdfPTable tituloRepTable = new PdfPTable(1);
        PdfPCell tituloRepCell = new PdfPCell(new Phrase("REPORTE DE VALIDACIÓN", tituloReporteFont));
        tituloRepCell.setBorder(PdfPCell.NO_BORDER);
        tituloRepCell.setBackgroundColor(bckTabla);
        tituloRepCell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        tituloRepCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tituloRepTable.addCell(tituloRepCell);
        tituloRepTable.setSpacingBefore(2);
        document.add(tituloRepTable);

        // Informacion General - Titulo
        PdfPTable infoGenTable = new PdfPTable(2);

        PdfPCell infoGenCell = new PdfPCell(new Phrase("Información General", fntTitulos));
        infoGenCell.setBorder(PdfPCell.NO_BORDER);
        infoGenCell.setBackgroundColor(bckTitulos);
        infoGenCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        infoGenCell.setColspan(2);
        infoGenTable.addCell(infoGenCell);

        // Informacion General - Datos
        addCellToTable(infoGenTable, bckTabla, fntTitulosEncabezados, "ESTADO VALIDACIÓN", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, bckTabla, fntDatosEncabezados, datosGeneralInforme.getEstado(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, null, fntTitulosEncabezados, "TIPO DE INFORME", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, null, fntDatosEncabezados, datosGeneralInforme.getTipoInforme(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, bckTabla, fntTitulosEncabezados, "INFORME", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, bckTabla, fntDatosEncabezados, datosGeneralInforme.getInforme(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, null, fntTitulosEncabezados, "PERÍODO", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, null, fntDatosEncabezados, datosGeneralInforme.getPeriodo(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, bckTabla, fntTitulosEncabezados, "EJERCICIO", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, bckTabla, fntDatosEncabezados, datosGeneralInforme.getEjercicio(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, null, fntTitulosEncabezados, "USUARIO", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, null, fntDatosEncabezados, datosGeneralInforme.getUsuario(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, bckTabla, fntTitulosEncabezados, "ENTIDAD", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenTable, bckTabla, fntDatosEncabezados, datosGeneralInforme.getEntidad(), PdfPCell.ALIGN_LEFT, 1);

        infoGenTable.setSpacingBefore(3);
        infoGenTable.setWidths(new int[]{30, 70});
        document.add(infoGenTable);

        PdfPTable infoSistradocTable = new PdfPTable(2);

        PdfPCell infoSistradocCell = new PdfPCell(new Phrase("Información SISTRADOC", fntTitulos));
        infoSistradocCell.setBorder(PdfPCell.NO_BORDER);
        infoSistradocCell.setBackgroundColor(bckTitulos);
        infoSistradocCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        infoSistradocCell.setColspan(2);
        infoSistradocTable.addCell(infoSistradocCell);

        // Informacion General - Datos
        addCellToTable(infoSistradocTable, bckTabla, fntTitulosEncabezados, "ANALISTA SISTRADOC", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, bckTabla, fntDatosEncabezados, datosSistradoc.getAnalista(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, null, fntTitulosEncabezados, "ESTADO SICOGEN", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, null, fntDatosEncabezados, datosSistradoc.getEstadoSicogen(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, bckTabla, fntTitulosEncabezados, "INFORME", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, bckTabla, fntDatosEncabezados, datosSistradoc.getInforme(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, null, fntTitulosEncabezados, "ENTIDAD EMISORA", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, null, fntDatosEncabezados, datosSistradoc.getEntidadEmisora(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, bckTabla, fntTitulosEncabezados, "TIPO DOCUMENTO", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, bckTabla, fntDatosEncabezados, datosSistradoc.getTipoDocumento(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, null, fntTitulosEncabezados, "NRO. DOCUMENTO", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, null, fntDatosEncabezados, String.valueOf(datosSistradoc.getNroDocumento()), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, bckTabla, fntTitulosEncabezados, "FECHA DOCUMENTO", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, bckTabla, fntDatosEncabezados, datosSistradoc.getFechaDocumento(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, null, fntTitulosEncabezados, "FECHA RECEPCIÓN CGR", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoSistradocTable, null, fntDatosEncabezados, datosSistradoc.getFechaRecepcionCGR(), PdfPCell.ALIGN_LEFT, 1);

        infoSistradocTable.setSpacingBefore(3);
        infoSistradocTable.setWidths(new int[]{30, 70});
        document.add(infoSistradocTable);

        PdfPTable infoGenXMLTable = new PdfPTable(4);

        PdfPCell infoGenXMLCell = new PdfPCell(new Phrase("Información General XML", fntTitulos));
        infoGenXMLCell.setBorder(PdfPCell.NO_BORDER);
        infoGenXMLCell.setBackgroundColor(bckTitulos);
        infoGenXMLCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        infoGenXMLCell.setColspan(4);
        infoGenXMLTable.addCell(infoGenXMLCell);

        addCellToTable(infoGenXMLTable, bckTabla, fntTitulosEncabezados, "Ejercicio", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, bckTabla, fntDatosEncabezados, datosXML.getEjercicio(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, bckTabla, fntTitulosEncabezados, "ID DECRETO", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, bckTabla, fntDatosEncabezados, datosXML.getIdDecreto(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, null, fntTitulosEncabezados, "Mes", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, null, fntDatosEncabezados, datosXML.getMes(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, null, fntTitulosEncabezados, "TIPO DE REGISTRO", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, null, fntDatosEncabezados, datosXML.getTipoRegistro(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, bckTabla, fntTitulosEncabezados, "Dia", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, bckTabla, fntDatosEncabezados, datosXML.getDia(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, bckTabla, fntTitulosEncabezados, "NUMERO REGISTRO", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, bckTabla, fntDatosEncabezados, datosXML.getNumeroRegistro(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, null, fntTitulosEncabezados, "SECTOR RESPONSABLE", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, null, fntDatosEncabezados, datosXML.getSectorResponsable(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, null, fntTitulosEncabezados, "FECHA REGISTRO", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, null, fntDatosEncabezados, datosXML.getFechaRegistro(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, bckTabla, fntTitulosEncabezados, "MONTO TOTAL", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, bckTabla, fntDatosEncabezados, datosXML.getMontoTotal(), PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, bckTabla, fntTitulosEncabezados, "", PdfPCell.ALIGN_LEFT, 1);
        addCellToTable(infoGenXMLTable, bckTabla, fntDatosEncabezados, "", PdfPCell.ALIGN_LEFT, 1);

        infoGenXMLTable.setSpacingBefore(3);
        infoGenXMLTable.setWidths(new int[]{30, 20, 30, 20});
        document.add(infoGenXMLTable);

        PdfPTable errGenTable = new PdfPTable(2);
        PdfPCell errGenLCell = new PdfPCell(new Phrase("Errores Genéricos", fntTitulos));
        errGenLCell.setBorder(PdfPCell.NO_BORDER);
        errGenLCell.setBackgroundColor(bckTitulos);
        errGenLCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        errGenLCell.setColspan(2);
        errGenTable.addCell(errGenLCell);

        /* Errores Genericos */
        if (datosXML.getErroresGenericos() != null && !datosXML.getErroresGenericos().isEmpty()) {
            for (int i = 0 ; i < datosXML.getErroresGenericos().size(); i++) {
                ErrorGenerico error = datosXML.getErroresGenericos().get(i);
                Image imgSignExlm = null;

                if (error.getTipoError() == 1) {
                    imgSignExlm = Image.getInstance(tipError1);
                    imgSignExlm.setBorder(Image.NO_BORDER);
                    imgSignExlm.scaleToFit(6, 10);
                } else if (error.getTipoError() == 2) {
                    imgSignExlm = Image.getInstance(tipError2);
                    imgSignExlm.setBorder(Image.NO_BORDER);
                    imgSignExlm.scaleToFit(6, 10);
                } else if (error.getTipoError() == 3) {
                    imgSignExlm = Image.getInstance(tipError3);
                    imgSignExlm.setBorder(Image.NO_BORDER);
                    imgSignExlm.scaleToFit(6, 10);
                }

                PdfPCell cellTiErr = new PdfPCell(imgSignExlm);
                cellTiErr.setBorder(PdfPCell.NO_BORDER);
                cellTiErr.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cellTiErr.setColspan(1);

                if (i%2 == 0) {
                    cellTiErr.setBackgroundColor(bckTabla);
                    errGenTable.addCell(cellTiErr);
                    addCellToTable(errGenTable, bckTabla, fntDatosEncabezados, error.getMensaje(), PdfPCell.ALIGN_LEFT, 1);
                } else {
                    errGenTable.addCell(cellTiErr);
                    addCellToTable(errGenTable, null, fntDatosEncabezados, error.getMensaje(), PdfPCell.ALIGN_LEFT, 1);
                }

            }
        } else {
            PdfPCell cellSinErr = new PdfPCell(new Phrase("El informe no presenta errores genéricos", fntDatosEncabezados));
            cellSinErr.setBorder(PdfPCell.NO_BORDER);
            cellSinErr.setBackgroundColor(bckTabla);
            cellSinErr.setColspan(2);
            errGenTable.addCell(cellSinErr);
        }

        errGenTable.setSpacingBefore(3);
        errGenTable.setWidths(new int[]{2, 98});
        document.add(errGenTable);

        PdfPTable tblDetArchivo = new PdfPTable(1);
        PdfPCell cellDetArchivo = new PdfPCell(new Phrase("Detalle de Archivo Cargado", fntTitulos));
        cellDetArchivo.setBorder(PdfPCell.NO_BORDER);
        cellDetArchivo.setBackgroundColor(bckTitulos);
        cellDetArchivo.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellDetArchivo.setColspan(1);
        tblDetArchivo.addCell(cellDetArchivo);

        tblDetArchivo.setSpacingBefore(3);
        document.add(tblDetArchivo);



        for (int i = 0; i < datosXML.getDetalle().size(); i++) {
            DetalleInformeTDRMP detalleInf = datosXML.getDetalle().get(i);

            PdfPTable tblDetalle = new PdfPTable(2);

            addCellToTable(tblDetalle, bckTabla, fntTitulosEncabezados, "PARTIDA", PdfPCell.ALIGN_LEFT, 1);
            addCellToTable(tblDetalle, bckTabla, fntDatosEncabezados, detalleInf.getPartida(), PdfPCell.ALIGN_LEFT, 1);
            addCellToTable(tblDetalle, null, fntTitulosEncabezados, "CAPITULO", PdfPCell.ALIGN_LEFT, 1);
            addCellToTable(tblDetalle, null, fntDatosEncabezados, detalleInf.getCapitulo(), PdfPCell.ALIGN_LEFT, 1);
            addCellToTable(tblDetalle, bckTabla, fntTitulosEncabezados, "PROGRAMA", PdfPCell.ALIGN_LEFT, 1);
            addCellToTable(tblDetalle, bckTabla, fntDatosEncabezados, detalleInf.getPrograma(), PdfPCell.ALIGN_LEFT, 1);

            tblDetalle.setSpacingAfter(6);
            tblDetalle.setWidths(new int[]{30, 70});
            document.add(tblDetalle);

            PdfPTable tblDetalleDatos = new PdfPTable(7);
            createEncTblDet(tblDetalleDatos, fntEncTblDet, "SUBTITULO");
            createEncTblDet(tblDetalleDatos, fntEncTblDet, "ITEM");
            createEncTblDet(tblDetalleDatos, fntEncTblDet, "ASIGNACION");
            createEncTblDet(tblDetalleDatos, fntEncTblDet, "SUBASIGNACION");
            createEncTblDet(tblDetalleDatos, fntEncTblDet, "DENOMINACION");
            createEncTblDet(tblDetalleDatos, fntEncTblDet, "INCREMENTO");
            createEncTblDet(tblDetalleDatos, fntEncTblDet, "REDUCCION");

            tblDetalleDatos.setWidths(new int[]{10, 8, 12, 13, 25, 12, 10});
            document.add(tblDetalleDatos);

            List<CuentaTDRMP> cuentas = detalleInf.getCuentasTDRMP();
            int lastIndex = cuentas.size() - 1;

            for (int j = 0; j < cuentas.size(); j++) {
                CuentaTDRMP cuenta = cuentas.get(j);
                PdfPTable tblDetalleDatosRow = new PdfPTable(7);

                if (j%2 == 0) {
                    addCellToTable(tblDetalleDatosRow, bckTabla, fntDatosEncabezadosSmall, cuenta.getSubtitulo(), PdfPCell.ALIGN_CENTER, 1);
                    addCellToTable(tblDetalleDatosRow, bckTabla, fntDatosEncabezadosSmall, cuenta.getItem(), PdfPCell.ALIGN_CENTER, 1);
                    addCellToTable(tblDetalleDatosRow, bckTabla, fntDatosEncabezadosSmall, cuenta.getAsignacion(), PdfPCell.ALIGN_CENTER, 1);
                    addCellToTable(tblDetalleDatosRow, bckTabla, fntDatosEncabezadosSmall, cuenta.getSubasignacion(), PdfPCell.ALIGN_CENTER, 1);
                    addCellToTable(tblDetalleDatosRow, bckTabla, fntDatosEncabezadosSmall, cuenta.getDenominacion(), PdfPCell.ALIGN_LEFT, 1);
                    addCellToTable(tblDetalleDatosRow, bckTabla, fntDatosEncabezadosSmall, cuenta.getIncremento(), PdfPCell.ALIGN_RIGHT, 1);
                    addCellToTable(tblDetalleDatosRow, bckTabla, fntDatosEncabezadosSmall, cuenta.getReduccion(), PdfPCell.ALIGN_RIGHT, 1);
                } else {
                    addCellToTable(tblDetalleDatosRow, null, fntDatosEncabezadosSmall, cuenta.getSubtitulo(), PdfPCell.ALIGN_CENTER, 1);
                    addCellToTable(tblDetalleDatosRow, null, fntDatosEncabezadosSmall, cuenta.getItem(), PdfPCell.ALIGN_CENTER, 1);
                    addCellToTable(tblDetalleDatosRow, null, fntDatosEncabezadosSmall, cuenta.getAsignacion(), PdfPCell.ALIGN_CENTER, 1);
                    addCellToTable(tblDetalleDatosRow, null, fntDatosEncabezadosSmall, cuenta.getSubasignacion(), PdfPCell.ALIGN_CENTER, 1);
                    addCellToTable(tblDetalleDatosRow, null, fntDatosEncabezadosSmall, cuenta.getDenominacion(), PdfPCell.ALIGN_LEFT, 1);
                    addCellToTable(tblDetalleDatosRow, null, fntDatosEncabezadosSmall, cuenta.getIncremento(), PdfPCell.ALIGN_RIGHT, 1);
                    addCellToTable(tblDetalleDatosRow, null, fntDatosEncabezadosSmall, cuenta.getReduccion(), PdfPCell.ALIGN_RIGHT, 1);
                }

                tblDetalleDatosRow.setWidths(new int[]{10, 8, 12, 13, 25, 12, 10});

                if (cuenta.getError()) {
                    document.add(tblDetalleDatosRow);

                    PdfPTable tblErrorDetalle = new PdfPTable(2);
                    addCellToTable(tblErrorDetalle, bckTitulos, fntTitulosSmall, "ERRORES DETECTADOS LÍNEA", PdfPCell.ALIGN_LEFT, 2);

                    List<ErrorDetalle> erroresDetalle = cuenta.getErroresDetalle();

                    for (int k = 0; k < erroresDetalle.size(); k++) {
                        ErrorDetalle errorDetalle = erroresDetalle.get(k);
                        Image imgTipErr = null;

                        if (errorDetalle.getTipoError() == 1) {
                            imgTipErr = Image.getInstance(tipError1);
                            imgTipErr.setBorder(Image.NO_BORDER);
                            imgTipErr.scaleToFit(6, 10);
                        } else if (errorDetalle.getTipoError() == 2) {
                            imgTipErr = Image.getInstance(tipError2);
                            imgTipErr.setBorder(Image.NO_BORDER);
                            imgTipErr.scaleToFit(6, 10);
                        } else if (errorDetalle.getTipoError() == 3) {
                            imgTipErr = Image.getInstance(tipError3);
                            imgTipErr.setBorder(Image.NO_BORDER);
                            imgTipErr.scaleToFit(6, 10);
                        }

                        PdfPCell cellTipErr = new PdfPCell(imgTipErr);
                        cellTipErr.setBorder(PdfPCell.NO_BORDER);
                        cellTipErr.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        cellTipErr.setColspan(1);
                        cellTipErr.setBackgroundColor(bckTabla);
                        tblErrorDetalle.addCell(cellTipErr);

                        addCellToTable(tblErrorDetalle, bckTabla, fntDatosEncabezadosSmall, errorDetalle.getMensaje(), PdfPCell.ALIGN_LEFT, 1);
                    }

                    tblErrorDetalle.setWidths(new int[]{2, 98});

                    if (lastIndex == j) {
                        tblErrorDetalle.setSpacingAfter(20);
                    }

                    document.add(tblErrorDetalle);
                } else {
                    if (lastIndex == j) {
                        tblDetalleDatosRow.setSpacingAfter(20);
                    }

                    document.add(tblDetalleDatosRow);
                }
            }
        }

        document.close();
        InputStream is = new ByteArrayInputStream(baos.toByteArray());

        Map<String, InputStreamResource> result = new HashMap<String, InputStreamResource>();
        result.put(generaNombreArchivo(datosArchivo.getListaDetalle().get(0).getNombre()), new InputStreamResource(is));

        return result;
    }

    private String generaNombreArchivo(String uuid) {

        String extensionArchivo = ".pdf";

        if (uuid.toLowerCase().contains(".xml")) {
            return uuid.substring(0, uuid.length() - 4).concat(extensionArchivo);
        } else {
            return uuid.concat(extensionArchivo);
        }
    }

    private void addCellToTable(PdfPTable table, BaseColor bckColor, Font fnt, String text, int align, int colSpan) {
        PdfPCell cellDatos = new PdfPCell(new Phrase(text, fnt));
        cellDatos.setBorder(PdfPCell.NO_BORDER);
        cellDatos.setHorizontalAlignment(align);
        cellDatos.setColspan(colSpan);

        if (bckColor != null) {
            cellDatos.setBackgroundColor(bckColor);
        }

        table.addCell(cellDatos);
    }

    private void createEncTblDet(PdfPTable table, Font fnt, String text) {
        PdfPCell cellDatos = new PdfPCell(new Phrase(text, fnt));
        cellDatos.setBorder(PdfPCell.NO_BORDER);
        cellDatos.setCellEvent(new CustomBorderPDF(null, null, null, new SolidLine()));
        cellDatos.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellDatos.setColspan(1);
        table.addCell(cellDatos);
    }
}
