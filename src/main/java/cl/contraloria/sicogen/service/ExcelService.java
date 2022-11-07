package cl.contraloria.sicogen.service;

import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.utils.AddImageExcel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {

    private InformesService informesService;

    public ExcelService(InformesService informesService) {
        this.informesService = informesService;
    }

    public Map<String, InputStreamResource> generaExcelReporteValidacion(URL logoSicogen,
                                                                         Integer idArchivo,
                                                                         URL tipError1,
                                                                         URL tipError2,
                                                                         URL tipError3) throws IOException {

        InformacionGeneralRV datosGeneralInforme = informesService.obtieneInfoGeneralRV(idArchivo);
        InformeContableDTO datosDetalleInforme = informesService.obtieneDatosInformeContable(idArchivo);
        ReporteValidacionPaginacion detalleInforme = informesService.obtieneReporteValidacionPaginacion(idArchivo,
                1, 0, 0, "1");
        List<ReporteValidacionData> dataInforme = detalleInforme.getData();

        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet resumen = book.createSheet();
        XSSFSheet detalle = book.createSheet();

        book.setSheetName(0, "Resumen");
        book.setSheetName(1, "Detalle");

        resumen.setColumnWidth(1, 8000);
        resumen.setColumnWidth(2, 30000);

        /* Styling */

        /* Backgrounds */
        XSSFColor bckTitulos =  new XSSFColor(new java.awt.Color(63,135,193));
        XSSFColor bckTabla =  new XSSFColor(new java.awt.Color(215,215,217));
        XSSFColor bckBlanco =  new XSSFColor(new java.awt.Color(255,255,255));
        XSSFColor bckError =  new XSSFColor(new java.awt.Color(255, 199, 206));
        XSSFColor bckWarning =  new XSSFColor(new java.awt.Color(255, 254, 120));
        XSSFColor bckInterno =  new XSSFColor(new java.awt.Color(204, 195, 255));

        /* Fonts */

        XSSFFont fntTituloReporte = book.createFont();
        fntTituloReporte.setFontHeightInPoints((short) 20);
        fntTituloReporte.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntTituloReporte.setBold(Boolean.TRUE);
        fntTituloReporte.setFontName("Arial");

        XSSFFont fntTitulos = book.createFont();
        fntTitulos.setFontHeightInPoints((short) 11);
        fntTitulos.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
        fntTitulos.setBold(Boolean.TRUE);
        fntTitulos.setFontName("Arial");

        XSSFFont fntDatosEncabezados = book.createFont();
        fntDatosEncabezados.setFontHeightInPoints((short) 11);
        fntDatosEncabezados.setColor(new XSSFColor(new java.awt.Color(102, 102, 102)));
        fntDatosEncabezados.setFontName("Arial");

        XSSFFont fntEncTblDet = book.createFont();
        fntEncTblDet.setFontHeightInPoints((short) 11);
        fntEncTblDet.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntEncTblDet.setBold(Boolean.TRUE);
        fntEncTblDet.setFontName("Arial");

        XSSFFont fntTblDet = book.createFont();
        fntTblDet.setFontHeightInPoints((short) 11);
        fntTblDet.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntTblDet.setFontName("Arial");

        XSSFFont fntTitulosEncabezados = book.createFont();
        fntTitulosEncabezados.setFontHeightInPoints((short) 11);
        fntTitulosEncabezados.setColor(new XSSFColor(new java.awt.Color(102, 102, 102)));
        fntTitulosEncabezados.setBold(Boolean.TRUE);
        fntTitulosEncabezados.setFontName("Arial");

        /* Cell Styles */

        XSSFCellStyle csTituloReporte = book.createCellStyle();
        csTituloReporte.setFont(fntTituloReporte);
        csTituloReporte.setFillForegroundColor(bckTabla);
        csTituloReporte.setFillBackgroundColor(bckTabla);
        csTituloReporte.setAlignment(HorizontalAlignment.CENTER);
        csTituloReporte.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csTitulos = book.createCellStyle();
        csTitulos.setFont(fntTitulos);
        csTitulos.setFillForegroundColor(bckTitulos);
        csTitulos.setFillBackgroundColor(bckTitulos);
        csTitulos.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        csTitulos.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle csEncabezados0 = book.createCellStyle();
        csEncabezados0.setFillForegroundColor(bckBlanco);
        csEncabezados0.setFillBackgroundColor(bckBlanco);
        csEncabezados0.setFont(fntTitulosEncabezados);
        csEncabezados0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncabezados1 = book.createCellStyle();
        csEncabezados1.setFont(fntTitulosEncabezados);
        csEncabezados1.setFillForegroundColor(bckTabla);
        csEncabezados1.setFillBackgroundColor(bckTabla);
        csEncabezados1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosEnc0 = book.createCellStyle();
        csDatosEnc0.setFont(fntDatosEncabezados);
        csDatosEnc0.setFillForegroundColor(bckBlanco);
        csDatosEnc0.setFillBackgroundColor(bckBlanco);
        csDatosEnc0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosEnc1 = book.createCellStyle();
        csDatosEnc1.setFont(fntDatosEncabezados);
        csDatosEnc1.setFillForegroundColor(bckTabla);
        csDatosEnc1.setFillBackgroundColor(bckTabla);
        csDatosEnc1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncTblDet = book.createCellStyle();
        csEncTblDet.setFont(fntEncTblDet);
        csEncTblDet.setFillForegroundColor(bckBlanco);
        csEncTblDet.setFillBackgroundColor(bckBlanco);
        csEncTblDet.setAlignment(HorizontalAlignment.CENTER);
        csEncTblDet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        csEncTblDet.setBorderBottom(BorderStyle.MEDIUM);

        XSSFCellStyle csDatosTblDet0 = book.createCellStyle();
        csDatosTblDet0.setFont(fntTblDet);
        csDatosTblDet0.setFillForegroundColor(bckBlanco);
        csDatosTblDet0.setFillBackgroundColor(bckBlanco);
        csDatosTblDet0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDet1 = book.createCellStyle();
        csDatosTblDet1.setFont(fntTblDet);
        csDatosTblDet1.setFillForegroundColor(bckTabla);
        csDatosTblDet1.setFillBackgroundColor(bckTabla);
        csDatosTblDet1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentrado0 = book.createCellStyle();
        csDatosTblDetCentrado0.setFont(fntTblDet);
        csDatosTblDetCentrado0.setFillForegroundColor(bckBlanco);
        csDatosTblDetCentrado0.setFillBackgroundColor(bckBlanco);
        csDatosTblDetCentrado0.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentrado0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentrado1 = book.createCellStyle();
        csDatosTblDetCentrado1.setFont(fntTblDet);
        csDatosTblDetCentrado1.setFillForegroundColor(bckTabla);
        csDatosTblDetCentrado1.setFillBackgroundColor(bckTabla);
        csDatosTblDetCentrado1.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentrado1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerecha0 = book.createCellStyle();
        csDatosTblDetDerecha0.setFont(fntTblDet);
        csDatosTblDetDerecha0.setFillForegroundColor(bckBlanco);
        csDatosTblDetDerecha0.setFillBackgroundColor(bckBlanco);
        csDatosTblDetDerecha0.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerecha0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerecha1 = book.createCellStyle();
        csDatosTblDetDerecha1.setFont(fntTblDet);
        csDatosTblDetDerecha1.setFillForegroundColor(bckTabla);
        csDatosTblDetDerecha1.setFillBackgroundColor(bckTabla);
        csDatosTblDetDerecha1.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerecha1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetError = book.createCellStyle();
        csDatosTblDetError.setFont(fntTblDet);
        csDatosTblDetError.setFillForegroundColor(bckError);
        csDatosTblDetError.setFillBackgroundColor(bckError);
        csDatosTblDetError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentradoError = book.createCellStyle();
        csDatosTblDetCentradoError.setFont(fntTblDet);
        csDatosTblDetCentradoError.setFillForegroundColor(bckError);
        csDatosTblDetCentradoError.setFillBackgroundColor(bckError);
        csDatosTblDetCentradoError.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentradoError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerechaError = book.createCellStyle();
        csDatosTblDetDerechaError.setFont(fntTblDet);
        csDatosTblDetDerechaError.setFillForegroundColor(bckError);
        csDatosTblDetDerechaError.setFillBackgroundColor(bckError);
        csDatosTblDetDerechaError.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerechaError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetWarning = book.createCellStyle();
        csDatosTblDetWarning.setFont(fntTblDet);
        csDatosTblDetWarning.setFillForegroundColor(bckWarning);
        csDatosTblDetWarning.setFillBackgroundColor(bckWarning);
        csDatosTblDetWarning.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentradoWarning = book.createCellStyle();
        csDatosTblDetCentradoWarning.setFont(fntTblDet);
        csDatosTblDetCentradoWarning.setFillForegroundColor(bckWarning);
        csDatosTblDetCentradoWarning.setFillBackgroundColor(bckWarning);
        csDatosTblDetCentradoWarning.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentradoWarning.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerechaWarning = book.createCellStyle();
        csDatosTblDetDerechaWarning.setFont(fntTblDet);
        csDatosTblDetDerechaWarning.setFillForegroundColor(bckWarning);
        csDatosTblDetDerechaWarning.setFillBackgroundColor(bckWarning);
        csDatosTblDetDerechaWarning.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerechaWarning.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetInterno = book.createCellStyle();
        csDatosTblDetInterno.setFont(fntTblDet);
        csDatosTblDetInterno.setFillForegroundColor(bckInterno);
        csDatosTblDetInterno.setFillBackgroundColor(bckInterno);
        csDatosTblDetInterno.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentradoInterno = book.createCellStyle();
        csDatosTblDetCentradoInterno.setFont(fntTblDet);
        csDatosTblDetCentradoInterno.setFillForegroundColor(bckInterno);
        csDatosTblDetCentradoInterno.setFillBackgroundColor(bckInterno);
        csDatosTblDetCentradoInterno.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentradoInterno.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerechaInterno = book.createCellStyle();
        csDatosTblDetDerechaInterno.setFont(fntTblDet);
        csDatosTblDetDerechaInterno.setFillForegroundColor(bckInterno);
        csDatosTblDetDerechaInterno.setFillBackgroundColor(bckInterno);
        csDatosTblDetDerechaInterno.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerechaInterno.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        /* Imagen de cabecera */
        resumen.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
        new AddImageExcel().addImageToSheet("B2", resumen, resumen.createDrawingPatriarch(), logoSicogen,
                100, 25, AddImageExcel.OVERLAY_ROW_AND_COLUMN);

        /* Titulos */
        int rowNumTit1 = 7;
        int rowNumTit2 = 9;
        int rowNumTit3 = 17;
        int rowNumTit4 = 22;

        /* Titulo General */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit1, rowNumTit1, 1, 2));
        XSSFRow rowTit1 = resumen.createRow(rowNumTit1);
        XSSFCell cellTit1 = rowTit1.createCell(1);
        cellTit1.setCellStyle(csTituloReporte);
        cellTit1.setCellValue(new XSSFRichTextString("REPORTE DE VALIDACIÓN"));

        /* Titulo 1 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit2, rowNumTit2, 1, 2));
        XSSFRow rowTit2 = resumen.createRow(rowNumTit2);
        XSSFCell cellTit2 = rowTit2.createCell(1);
        cellTit2.setCellStyle(csTitulos);
        cellTit2.setCellValue(new XSSFRichTextString("Información General"));

        /* Titulo 2 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit3, rowNumTit3, 1, 2));
        XSSFRow rowTit3 = resumen.createRow(rowNumTit3);
        XSSFCell cellTit3 = rowTit3.createCell(1);
        cellTit3.setCellStyle(csTitulos);
        cellTit3.setCellValue(new XSSFRichTextString("Detalle de Archivo Cargado"));

        /* Titulo 3 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit4, rowNumTit4, 1, 2));
        XSSFRow rowTit4 = resumen.createRow(rowNumTit4);
        XSSFCell cellTit4 = rowTit4.createCell(1);
        cellTit4.setCellStyle(csTitulos);
        cellTit4.setCellValue(new XSSFRichTextString("Errores Genéricos"));

        /* Encabezados Titulo 2 */

        XSSFRow rowEnc1Tit2 = resumen.createRow(10);
        XSSFCell cellEnc1Tit2 = rowEnc1Tit2.createCell(1);
        cellEnc1Tit2.setCellStyle(csEncabezados1);
        cellEnc1Tit2.setCellValue(new XSSFRichTextString("ESTADO DE VALIDACIÓN"));

        XSSFRow rowEnc2Tit2 = resumen.createRow(11);
        XSSFCell cellEnc2Tit2 = rowEnc2Tit2.createCell(1);
        cellEnc2Tit2.setCellStyle(csEncabezados0);
        cellEnc2Tit2.setCellValue(new XSSFRichTextString("TIPO DE INFORME"));

        XSSFRow rowEnc3Tit2 = resumen.createRow(12);
        XSSFCell cellEnc3Tit2 = rowEnc3Tit2.createCell(1);
        cellEnc3Tit2.setCellStyle(csEncabezados1);
        cellEnc3Tit2.setCellValue(new XSSFRichTextString("INFORME"));

        XSSFRow rowEnc4Tit2 = resumen.createRow(13);
        XSSFCell cellEnc4Tit2 = rowEnc4Tit2.createCell(1);
        cellEnc4Tit2.setCellStyle(csEncabezados0);
        cellEnc4Tit2.setCellValue(new XSSFRichTextString("PERÍODO"));

        XSSFRow rowEnc5Tit2 = resumen.createRow(14);
        XSSFCell cellEnc5Tit2 = rowEnc5Tit2.createCell(1);
        cellEnc5Tit2.setCellStyle(csEncabezados1);
        cellEnc5Tit2.setCellValue(new XSSFRichTextString("EJERCICIO"));

        XSSFRow rowEnc6Tit2 = resumen.createRow(15);
        XSSFCell cellEnc6Tit2 = rowEnc6Tit2.createCell(1);
        cellEnc6Tit2.setCellStyle(csEncabezados0);
        cellEnc6Tit2.setCellValue(new XSSFRichTextString("USUARIO"));

        XSSFRow rowEnc7Tit2 = resumen.createRow(16);
        XSSFCell cellEnc7Tit2 = rowEnc7Tit2.createCell(1);
        cellEnc7Tit2.setCellStyle(csEncabezados1);
        cellEnc7Tit2.setCellValue(new XSSFRichTextString("ENTIDAD"));

        /* Encabezados Titulo 3 */

        XSSFRow rowEnc1Tit3 = resumen.createRow(18);
        XSSFCell cellEnc1Tit3 = rowEnc1Tit3.createCell(1);
        cellEnc1Tit3.setCellStyle(csEncabezados1);
        cellEnc1Tit3.setCellValue(new XSSFRichTextString("INFORME"));

        XSSFRow rowEnc2Tit3 = resumen.createRow(19);
        XSSFCell cellEnc2Tit3 = rowEnc2Tit3.createCell(1);
        cellEnc2Tit3.setCellStyle(csEncabezados0);
        cellEnc2Tit3.setCellValue(new XSSFRichTextString("PARTIDA"));

        XSSFRow rowEnc3Tit3 = resumen.createRow(20);
        XSSFCell cellEnc3Tit3 = rowEnc3Tit3.createCell(1);
        cellEnc3Tit3.setCellStyle(csEncabezados1);
        cellEnc3Tit3.setCellValue(new XSSFRichTextString("CAPITULO"));

        XSSFRow rowEnc4Tit3 = resumen.createRow(21);
        XSSFCell cellEnc4Tit3 = rowEnc4Tit3.createCell(1);
        cellEnc4Tit3.setCellStyle(csEncabezados0);
        cellEnc4Tit3.setCellValue(new XSSFRichTextString("RUT"));

        /* Encabezado Detalle reporte */

        XSSFRow rowEncTblDet = detalle.createRow(0);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Programa", 0, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Área Transaccional", 1, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Moneda", 2, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Movim.", 3, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Cuenta Contable", 4, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Código BIP", 5, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Denominación Proyecto", 6, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Debe CLP", 7, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Haber CLP", 8, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Debe USB", 9, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Haber USD", 10, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Denominación Cuenta", 11, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Folio Contable", 12, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Tipo Transacción", 13, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Cuenta Presupuestaria", 14, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Estado R002", 15, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Mensaje R002", 16, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Estado R003", 17, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Mensaje R003", 18, csEncTblDet);


        /* Volcamiento de datos */

        /* Informacion General */
        setValorEncabezado(rowEnc1Tit2, csDatosEnc1, datosGeneralInforme.getEstado());
        setValorEncabezado(rowEnc2Tit2, csDatosEnc0, datosGeneralInforme.getTipoInforme());
        setValorEncabezado(rowEnc3Tit2, csDatosEnc1, datosGeneralInforme.getInforme());
        setValorEncabezado(rowEnc4Tit2, csDatosEnc0, datosGeneralInforme.getPeriodo());
        setValorEncabezado(rowEnc5Tit2, csDatosEnc1, datosGeneralInforme.getEjercicio());
        setValorEncabezado(rowEnc6Tit2, csDatosEnc0, datosGeneralInforme.getUsuario());
        setValorEncabezado(rowEnc7Tit2, csDatosEnc1, datosGeneralInforme.getEntidad());

        /* Detalle del Archivo Cargado */
        setValorEncabezado(rowEnc1Tit3, csDatosEnc1, datosDetalleInforme.getUuid());
        setValorEncabezado(rowEnc2Tit3, csDatosEnc0, datosDetalleInforme.getPartida());
        setValorEncabezado(rowEnc3Tit3, csDatosEnc1, datosDetalleInforme.getCapitulo());
        setValorEncabezado(rowEnc4Tit3, csDatosEnc0, datosDetalleInforme.getRut());

        /* Errores Genericos */
        if (!datosDetalleInforme.getErroresGenericos().isEmpty()) {
            for (int i = 0 ; i < datosDetalleInforme.getErroresGenericos().size(); i++) {
                rowNumTit4 = rowNumTit4 + 1;
                resumen.addMergedRegion(new CellRangeAddress(rowNumTit4, rowNumTit4, 1, 2));
                XSSFRow rowErrGen = resumen.createRow(rowNumTit4);
                XSSFCell cellErrGen = rowErrGen.createCell(1);

                ErrorGenerico error = datosDetalleInforme.getErroresGenericos().get(i);
                cellErrGen.setCellValue("   " + error.getMensaje());

                if (error.getTipoError() == 1) {
                    new AddImageExcel().addImageToSheet("B" + (rowNumTit4 + 1), resumen, resumen.createDrawingPatriarch(), tipError1,
                            2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                } else if (error.getTipoError() == 2) {
                    new AddImageExcel().addImageToSheet("B" + (rowNumTit4 + 1), resumen, resumen.createDrawingPatriarch(), tipError2,
                            2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                } else if (error.getTipoError() == 3) {
                    new AddImageExcel().addImageToSheet("B" + (rowNumTit4 + 1), resumen, resumen.createDrawingPatriarch(), tipError3,
                            2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                }

                if (i%2 == 0) {
                    cellErrGen.setCellStyle(csDatosEnc1);
                } else {
                    cellErrGen.setCellStyle(csDatosEnc0);
                }
            }
        } else {
            resumen.addMergedRegion(new CellRangeAddress(23, 23, 1, 2));
            XSSFRow rowErrGen = resumen.createRow(23);
            XSSFCell cellErrGen = rowErrGen.createCell(1);
            cellErrGen.setCellValue("El informe contable no presenta errores genéricos");
            cellErrGen.setCellStyle(csDatosEnc0);
        }

        /* Datos tabla detalle */
        if (datosDetalleInforme.getErrorEsquema().equals(0)) {
            int nextIndex;

            for (int i = 0; i < dataInforme.size(); i++) {
                nextIndex = i + 1;

                ReporteValidacionData data = dataInforme.get(i);
                XSSFRow rowInfTblDet = detalle.createRow(nextIndex);

                if (data.getEstadpRegla2().equals(1) || data.getEstadpRegla3().equals(1)) {

                    String tipoerror = "";

                    if (data.getTipoErrorRegla2().equals("1") || data.getTipoErrorRegla3().equals("1")) {
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getPrograma(), 0, csDatosTblDetError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getAreaTransaccional(), 1, csDatosTblDetError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getMoneda(), 2, csDatosTblDetCentradoError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getMovimiento(), 3, csDatosTblDetCentradoError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaContable(), 4, csDatosTblDetCentradoError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getCodigoBIP(), 5, csDatosTblDetCentradoError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionProyecto(), 6, csDatosTblDetCentradoError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeCLP(), 7, csDatosTblDetDerechaError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberCLP(), 8, csDatosTblDetDerechaError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeUSD(), 9, csDatosTblDetDerechaError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberUSD(), 10, csDatosTblDetDerechaError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionCuenta(), 11, csDatosTblDetError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getFolioContable(), 12, csDatosTblDetError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getTipoTransaccion(), 13, csDatosTblDetError);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaPresupuestaria(), 14, csDatosTblDetCentradoError);
                        tipoerror = "1";
                    } else if (data.getTipoErrorRegla2().equals("2") || data.getTipoErrorRegla3().equals("2")) {
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getPrograma(), 0, csDatosTblDetWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getAreaTransaccional(), 1, csDatosTblDetWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getMoneda(), 2, csDatosTblDetCentradoWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getMovimiento(), 3, csDatosTblDetCentradoWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaContable(), 4, csDatosTblDetCentradoWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getCodigoBIP(), 5, csDatosTblDetCentradoWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionProyecto(), 6, csDatosTblDetCentradoWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeCLP(), 7, csDatosTblDetDerechaWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberCLP(), 8, csDatosTblDetDerechaWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeUSD(), 9, csDatosTblDetDerechaWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberUSD(), 10, csDatosTblDetDerechaWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionCuenta(), 11, csDatosTblDetWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getFolioContable(), 12, csDatosTblDetWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getTipoTransaccion(), 13, csDatosTblDetWarning);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaPresupuestaria(), 14, csDatosTblDetCentradoWarning);
                        tipoerror = "2";
                    } else {
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getPrograma(), 0, csDatosTblDetInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getAreaTransaccional(), 1, csDatosTblDetInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getMoneda(), 2, csDatosTblDetCentradoInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getMovimiento(), 3, csDatosTblDetCentradoInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaContable(), 4, csDatosTblDetCentradoInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getCodigoBIP(), 5, csDatosTblDetCentradoInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionProyecto(), 6, csDatosTblDetCentradoInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeCLP(), 7, csDatosTblDetDerechaInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberCLP(), 8, csDatosTblDetDerechaInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeUSD(), 9, csDatosTblDetDerechaInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberUSD(), 10, csDatosTblDetDerechaInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionCuenta(), 11, csDatosTblDetInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getFolioContable(), 12, csDatosTblDetInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getTipoTransaccion(), 13, csDatosTblDetInterno);
                        creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaPresupuestaria(), 14, csDatosTblDetCentradoInterno);
                    }

                    if (data.getEstadpRegla2().equals(1)) {
                        if (tipoerror.equals("1")) {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "NOK", 15, csDatosTblDetCentradoError);
                            creaCeldaDataTablaDetalle(rowInfTblDet, data.getMensajeRegla2(), 16, csDatosTblDetError);
                        } else if (tipoerror.equals("2")) {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "NOK", 15, csDatosTblDetCentradoWarning);
                            creaCeldaDataTablaDetalle(rowInfTblDet, data.getMensajeRegla2(), 16, csDatosTblDetWarning);
                        } else {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "NOK", 15, csDatosTblDetCentradoInterno);
                            creaCeldaDataTablaDetalle(rowInfTblDet, data.getMensajeRegla2(), 16, csDatosTblDetInterno);
                        }
                    } else {
                        if (tipoerror.equals("1")) {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "OK", 15, csDatosTblDetCentradoError);
                            creaCeldaDataTablaDetalle(rowInfTblDet, "", 16, csDatosTblDetError);
                        } else if (tipoerror.equals("2")) {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "OK", 15, csDatosTblDetCentradoWarning);
                            creaCeldaDataTablaDetalle(rowInfTblDet, "", 16, csDatosTblDetWarning);
                        } else {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "OK", 15, csDatosTblDetCentradoInterno);
                            creaCeldaDataTablaDetalle(rowInfTblDet, "", 16, csDatosTblDetInterno);
                        }
                    }

                    if (data.getEstadpRegla3().equals(1)) {
                        if (tipoerror.equals("1")) {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "NOK", 17, csDatosTblDetCentradoError);
                            creaCeldaDataTablaDetalle(rowInfTblDet, data.getMensajeRegla3(), 18, csDatosTblDetError);
                        } else if (tipoerror.equals("2")) {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "NOK", 17, csDatosTblDetCentradoWarning);
                            creaCeldaDataTablaDetalle(rowInfTblDet, data.getMensajeRegla3(), 18, csDatosTblDetWarning);
                        } else {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "NOK", 17, csDatosTblDetCentradoInterno);
                            creaCeldaDataTablaDetalle(rowInfTblDet, data.getMensajeRegla3(), 18, csDatosTblDetInterno);
                        }
                    } else {
                        if (tipoerror.equals("1")) {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "OK", 17, csDatosTblDetCentradoError);
                            creaCeldaDataTablaDetalle(rowInfTblDet, "", 18, csDatosTblDetError);
                        } else if (tipoerror.equals("2")) {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "OK", 17, csDatosTblDetCentradoWarning);
                            creaCeldaDataTablaDetalle(rowInfTblDet, "", 18, csDatosTblDetWarning);
                        } else {
                            creaCeldaDataTablaDetalle(rowInfTblDet, "OK", 17, csDatosTblDetCentradoInterno);
                            creaCeldaDataTablaDetalle(rowInfTblDet, "", 18, csDatosTblDetInterno);
                        }
                    }
                } else if (i%2 == 0) {
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getPrograma(), 0, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getAreaTransaccional(), 1, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getMoneda(), 2, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getMovimiento(), 3, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaContable(), 4, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCodigoBIP(), 5, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionProyecto(), 6, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeCLP(), 7, csDatosTblDetDerecha0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberCLP(), 8, csDatosTblDetDerecha0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeUSD(), 9, csDatosTblDetDerecha0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberUSD(), 10, csDatosTblDetDerecha0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionCuenta(), 11, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getFolioContable(), 12, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getTipoTransaccion(), 13, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaPresupuestaria(), 14, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, "OK", 15, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, "", 16, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, "OK", 17, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, "", 18, csDatosTblDet0);
                } else {
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getPrograma(), 0, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getAreaTransaccional(), 1, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getMoneda(), 2, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getMovimiento(), 3, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaContable(), 4, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCodigoBIP(), 5, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionProyecto(), 6, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeCLP(), 7, csDatosTblDetDerecha1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberCLP(), 8, csDatosTblDetDerecha1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeUSD(), 9, csDatosTblDetDerecha1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberUSD(), 10, csDatosTblDetDerecha1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionCuenta(), 11, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getFolioContable(), 12, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getTipoTransaccion(), 13, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaPresupuestaria(), 14, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, "OK", 15, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, "", 16, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, "OK", 17, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, "", 18, csDatosTblDet1);
                }
            }
        }

        /* Ordena Hoja Detalle */
        for (int i = 0; i < 19; i++) {
            detalle.autoSizeColumn(i);
        }

        detalle.setAutoFilter(new CellRangeAddress(0, detalleInforme.getRecordsTotal(), 0, 18));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        book.write(baos);
        baos.close();
        byte[] bArray = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(bArray);

        Map<String, InputStreamResource> result = new HashMap<String, InputStreamResource>();
        result.put(generaNombreArchivo(datosDetalleInforme.getUuid()), new InputStreamResource(is));

        return result;
    }

    private void creaCeldaEncTablaDetalle(XSSFRow rowEncTblDet, String nombre, Integer indice, XSSFCellStyle style) {
        XSSFCell cell = rowEncTblDet.createCell(indice);
        cell.setCellStyle(style);
        cell.setCellValue(new XSSFRichTextString(nombre));
    }

    private void setValorEncabezado(XSSFRow row, XSSFCellStyle style, String valor) {
        XSSFCell cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue(valor);
    }

    private void setValorEncabezado(XSSFRow row, XSSFCellStyle style, String valor, Integer columnIndex) {
        XSSFCell cell = row.createCell(columnIndex);
        cell.setCellStyle(style);
        cell.setCellValue(valor);
    }

    private void creaCeldaDataTablaDetalle(XSSFRow rowInfTblDet, String valor, Integer indice, XSSFCellStyle style) {
        XSSFCell cell = rowInfTblDet.createCell(indice);
        cell.setCellStyle(style);
        cell.setCellValue(valor);
    }

    private String generaNombreArchivo(String uuid) {

        String extensionArchivo = ".xlsx";

        if (uuid.toLowerCase().contains(".xml")) {
            return uuid.substring(0, uuid.length() - 4).concat(extensionArchivo);
        } else {
            return uuid.concat(extensionArchivo);
        }
    }

    public Map<String, InputStreamResource> generaExcelReporteValidacionTDR(URL logoSicogen,
                                                                            Integer idArchivo,
                                                                            String idSistradoc,
                                                                            URL tipError1,
                                                                            URL tipError2,
                                                                            URL tipError3) throws Exception {

        InformacionGeneralRV datosGeneralInforme = informesService.obtieneInfoGeneralRV(idArchivo);
        InformeSistradocBO datosSistradoc = informesService.getInformeTDR(idSistradoc);
        InformeTDRRV datosXML = informesService.getDatosXMLInformeTDR(idArchivo);
        InformeArchivoDet datosArchivo = informesService.getArchivo(idArchivo);


        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet resumen = book.createSheet();
        XSSFSheet detalle = book.createSheet();

        book.setSheetName(0, "Resumen");
        book.setSheetName(1, "Detalle");

        resumen.setColumnWidth(1, 8000);
        resumen.setColumnWidth(2, 10000);
        resumen.setColumnWidth(3, 10000);
        resumen.setColumnWidth(4, 10000);

        /* Styling */

        /* Backgrounds */
        XSSFColor bckTitulos =  new XSSFColor(new java.awt.Color(63,135,193));
        XSSFColor bckTabla =  new XSSFColor(new java.awt.Color(215,215,217));
        XSSFColor bckBlanco =  new XSSFColor(new java.awt.Color(255,255,255));
        XSSFColor bckError =  new XSSFColor(new java.awt.Color(255, 199, 206));

        /* Fonts */

        XSSFFont fntTituloReporte = book.createFont();
        fntTituloReporte.setFontHeightInPoints((short) 20);
        fntTituloReporte.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntTituloReporte.setBold(Boolean.TRUE);
        fntTituloReporte.setFontName("Arial");

        XSSFFont fntTitulos = book.createFont();
        fntTitulos.setFontHeightInPoints((short) 11);
        fntTitulos.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
        fntTitulos.setBold(Boolean.TRUE);
        fntTitulos.setFontName("Arial");

        XSSFFont fntDatosEncabezados = book.createFont();
        fntDatosEncabezados.setFontHeightInPoints((short) 11);
        fntDatosEncabezados.setColor(new XSSFColor(new java.awt.Color(102, 102, 102)));
        fntDatosEncabezados.setFontName("Arial");

        XSSFFont fntTitulosEncabezados = book.createFont();
        fntTitulosEncabezados.setFontHeightInPoints((short) 11);
        fntTitulosEncabezados.setColor(new XSSFColor(new java.awt.Color(102, 102, 102)));
        fntTitulosEncabezados.setBold(Boolean.TRUE);
        fntTitulosEncabezados.setFontName("Arial");

        XSSFFont fntEncTblDet = book.createFont();
        fntEncTblDet.setFontHeightInPoints((short) 11);
        fntEncTblDet.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntEncTblDet.setBold(Boolean.TRUE);
        fntEncTblDet.setFontName("Arial");

        XSSFFont fntTblDet = book.createFont();
        fntTblDet.setFontHeightInPoints((short) 11);
        fntTblDet.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntTblDet.setFontName("Arial");

        /* Cell Styles */

        XSSFCellStyle csTituloReporte = book.createCellStyle();
        csTituloReporte.setFont(fntTituloReporte);
        csTituloReporte.setFillForegroundColor(bckTabla);
        csTituloReporte.setFillBackgroundColor(bckTabla);
        csTituloReporte.setAlignment(HorizontalAlignment.CENTER);
        csTituloReporte.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csTitulos = book.createCellStyle();
        csTitulos.setFont(fntTitulos);
        csTitulos.setFillForegroundColor(bckTitulos);
        csTitulos.setFillBackgroundColor(bckTitulos);
        csTitulos.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        csTitulos.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle csTitulosErr = book.createCellStyle();
        csTitulosErr.setFont(fntTitulos);
        csTitulosErr.setFillForegroundColor(bckTitulos);
        csTitulosErr.setFillBackgroundColor(bckTitulos);
        csTitulosErr.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncabezados0 = book.createCellStyle();
        csEncabezados0.setFillForegroundColor(bckBlanco);
        csEncabezados0.setFillBackgroundColor(bckBlanco);
        csEncabezados0.setFont(fntTitulosEncabezados);
        csEncabezados0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncabezados1 = book.createCellStyle();
        csEncabezados1.setFont(fntTitulosEncabezados);
        csEncabezados1.setFillForegroundColor(bckTabla);
        csEncabezados1.setFillBackgroundColor(bckTabla);
        csEncabezados1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosEnc0 = book.createCellStyle();
        csDatosEnc0.setFont(fntDatosEncabezados);
        csDatosEnc0.setFillForegroundColor(bckBlanco);
        csDatosEnc0.setFillBackgroundColor(bckBlanco);
        csDatosEnc0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosEnc1 = book.createCellStyle();
        csDatosEnc1.setFont(fntDatosEncabezados);
        csDatosEnc1.setFillForegroundColor(bckTabla);
        csDatosEnc1.setFillBackgroundColor(bckTabla);
        csDatosEnc1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncTblDet = book.createCellStyle();
        csEncTblDet.setFont(fntEncTblDet);
        csEncTblDet.setFillForegroundColor(bckBlanco);
        csEncTblDet.setFillBackgroundColor(bckBlanco);
        csEncTblDet.setAlignment(HorizontalAlignment.CENTER);
        csEncTblDet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        csEncTblDet.setBorderBottom(BorderStyle.MEDIUM);

        XSSFCellStyle csDatosTblDet0 = book.createCellStyle();
        csDatosTblDet0.setFont(fntTblDet);
        csDatosTblDet0.setFillForegroundColor(bckBlanco);
        csDatosTblDet0.setFillBackgroundColor(bckBlanco);
        csDatosTblDet0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDet1 = book.createCellStyle();
        csDatosTblDet1.setFont(fntTblDet);
        csDatosTblDet1.setFillForegroundColor(bckTabla);
        csDatosTblDet1.setFillBackgroundColor(bckTabla);
        csDatosTblDet1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentrado0 = book.createCellStyle();
        csDatosTblDetCentrado0.setFont(fntTblDet);
        csDatosTblDetCentrado0.setFillForegroundColor(bckBlanco);
        csDatosTblDetCentrado0.setFillBackgroundColor(bckBlanco);
        csDatosTblDetCentrado0.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentrado0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentrado1 = book.createCellStyle();
        csDatosTblDetCentrado1.setFont(fntTblDet);
        csDatosTblDetCentrado1.setFillForegroundColor(bckTabla);
        csDatosTblDetCentrado1.setFillBackgroundColor(bckTabla);
        csDatosTblDetCentrado1.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentrado1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerecha0 = book.createCellStyle();
        csDatosTblDetDerecha0.setFont(fntTblDet);
        csDatosTblDetDerecha0.setFillForegroundColor(bckBlanco);
        csDatosTblDetDerecha0.setFillBackgroundColor(bckBlanco);
        csDatosTblDetDerecha0.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerecha0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerecha1 = book.createCellStyle();
        csDatosTblDetDerecha1.setFont(fntTblDet);
        csDatosTblDetDerecha1.setFillForegroundColor(bckTabla);
        csDatosTblDetDerecha1.setFillBackgroundColor(bckTabla);
        csDatosTblDetDerecha1.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerecha1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetError = book.createCellStyle();
        csDatosTblDetError.setFont(fntTblDet);
        csDatosTblDetError.setFillForegroundColor(bckError);
        csDatosTblDetError.setFillBackgroundColor(bckError);
        csDatosTblDetError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentradoError = book.createCellStyle();
        csDatosTblDetCentradoError.setFont(fntTblDet);
        csDatosTblDetCentradoError.setFillForegroundColor(bckError);
        csDatosTblDetCentradoError.setFillBackgroundColor(bckError);
        csDatosTblDetCentradoError.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentradoError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerechaError = book.createCellStyle();
        csDatosTblDetDerechaError.setFont(fntTblDet);
        csDatosTblDetDerechaError.setFillForegroundColor(bckError);
        csDatosTblDetDerechaError.setFillBackgroundColor(bckError);
        csDatosTblDetDerechaError.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerechaError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        /* Imagen de cabecera */
        resumen.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
        new AddImageExcel().addImageToSheet("B2", resumen, resumen.createDrawingPatriarch(), logoSicogen,
                100, 25, AddImageExcel.OVERLAY_ROW_AND_COLUMN);

        /* Titulos */
        int rowNumTit1 = 7;
        int rowNumTit2 = 9;
        int rowNumTit3 = 17;
        int rowNumTit4 = 26;
        int rowNumTit5 = 32;

        /* Titulo General */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit1, rowNumTit1, 1, 4));
        XSSFRow rowTit1 = resumen.createRow(rowNumTit1);
        XSSFCell cellTit1 = rowTit1.createCell(1);
        cellTit1.setCellStyle(csTituloReporte);
        cellTit1.setCellValue(new XSSFRichTextString("REPORTE DE VALIDACIÓN"));

        /* Titulo 1 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit2, rowNumTit2, 1, 4));
        XSSFRow rowTit2 = resumen.createRow(rowNumTit2);
        XSSFCell cellTit2 = rowTit2.createCell(1);
        cellTit2.setCellStyle(csTitulos);
        cellTit2.setCellValue(new XSSFRichTextString("Información General"));

        /* Titulo 2 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit3, rowNumTit3, 1, 4));
        XSSFRow rowTit3 = resumen.createRow(rowNumTit3);
        XSSFCell cellTit3 = rowTit3.createCell(1);
        cellTit3.setCellStyle(csTitulos);
        cellTit3.setCellValue(new XSSFRichTextString("Información SISTRADOC"));

        /* Titulo 3 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit4, rowNumTit4, 1, 4));
        XSSFRow rowTit4 = resumen.createRow(rowNumTit4);
        XSSFCell cellTit4 = rowTit4.createCell(1);
        cellTit4.setCellStyle(csTitulos);
        cellTit4.setCellValue(new XSSFRichTextString("Información General XML"));

        /* Titulo 4 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit5, rowNumTit5, 1, 4));
        XSSFRow rowTit5 = resumen.createRow(rowNumTit5);
        XSSFCell cellTit5 = rowTit5.createCell(1);
        cellTit5.setCellStyle(csTitulos);
        cellTit5.setCellValue(new XSSFRichTextString("Errores Genéricos"));

        /* Titulo Detalle */
        detalle.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        XSSFRow rowTitDetalle = detalle.createRow(0);
        XSSFCell cellTitDetalle = rowTitDetalle.createCell(0);
        cellTitDetalle.setCellStyle(csTitulos);
        cellTitDetalle.setCellValue(new XSSFRichTextString("Detalle del Archivo Cargado"));

        /* Encabezados Titulo 2 */

        resumen.addMergedRegion(new CellRangeAddress(10, 10, 2, 4));
        XSSFRow rowEnc1Tit2 = resumen.createRow(10);
        XSSFCell cellEnc1Tit2 = rowEnc1Tit2.createCell(1);
        cellEnc1Tit2.setCellStyle(csEncabezados1);
        cellEnc1Tit2.setCellValue(new XSSFRichTextString("ESTADO DE VALIDACIÓN"));

        resumen.addMergedRegion(new CellRangeAddress(11, 11, 2, 4));
        XSSFRow rowEnc2Tit2 = resumen.createRow(11);
        XSSFCell cellEnc2Tit2 = rowEnc2Tit2.createCell(1);
        cellEnc2Tit2.setCellStyle(csEncabezados0);
        cellEnc2Tit2.setCellValue(new XSSFRichTextString("TIPO DE INFORME"));

        resumen.addMergedRegion(new CellRangeAddress(12, 12, 2, 4));
        XSSFRow rowEnc3Tit2 = resumen.createRow(12);
        XSSFCell cellEnc3Tit2 = rowEnc3Tit2.createCell(1);
        cellEnc3Tit2.setCellStyle(csEncabezados1);
        cellEnc3Tit2.setCellValue(new XSSFRichTextString("INFORME"));

        resumen.addMergedRegion(new CellRangeAddress(13, 13, 2, 4));
        XSSFRow rowEnc4Tit2 = resumen.createRow(13);
        XSSFCell cellEnc4Tit2 = rowEnc4Tit2.createCell(1);
        cellEnc4Tit2.setCellStyle(csEncabezados0);
        cellEnc4Tit2.setCellValue(new XSSFRichTextString("PERÍODO"));

        resumen.addMergedRegion(new CellRangeAddress(14, 14, 2, 4));
        XSSFRow rowEnc5Tit2 = resumen.createRow(14);
        XSSFCell cellEnc5Tit2 = rowEnc5Tit2.createCell(1);
        cellEnc5Tit2.setCellStyle(csEncabezados1);
        cellEnc5Tit2.setCellValue(new XSSFRichTextString("EJERCICIO"));

        resumen.addMergedRegion(new CellRangeAddress(15, 15, 2, 4));
        XSSFRow rowEnc6Tit2 = resumen.createRow(15);
        XSSFCell cellEnc6Tit2 = rowEnc6Tit2.createCell(1);
        cellEnc6Tit2.setCellStyle(csEncabezados0);
        cellEnc6Tit2.setCellValue(new XSSFRichTextString("USUARIO"));

        resumen.addMergedRegion(new CellRangeAddress(16, 16, 2, 4));
        XSSFRow rowEnc7Tit2 = resumen.createRow(16);
        XSSFCell cellEnc7Tit2 = rowEnc7Tit2.createCell(1);
        cellEnc7Tit2.setCellStyle(csEncabezados1);
        cellEnc7Tit2.setCellValue(new XSSFRichTextString("ENTIDAD"));

        /* Encabezados Titulo 3 */

        resumen.addMergedRegion(new CellRangeAddress(18, 18, 2, 4));
        XSSFRow rowEnc1Tit3 = resumen.createRow(18);
        XSSFCell cellEnc1Tit3 = rowEnc1Tit3.createCell(1);
        cellEnc1Tit3.setCellStyle(csEncabezados1);
        cellEnc1Tit3.setCellValue(new XSSFRichTextString("ANALISTA SISTRADOC"));

        resumen.addMergedRegion(new CellRangeAddress(19, 19, 2, 4));
        XSSFRow rowEnc2Tit3 = resumen.createRow(19);
        XSSFCell cellEnc2Tit3 = rowEnc2Tit3.createCell(1);
        cellEnc2Tit3.setCellStyle(csEncabezados0);
        cellEnc2Tit3.setCellValue(new XSSFRichTextString("ESTADO SICOGEN"));

        resumen.addMergedRegion(new CellRangeAddress(20, 20, 2, 4));
        XSSFRow rowEnc3Tit3 = resumen.createRow(20);
        XSSFCell cellEnc3Tit3 = rowEnc3Tit3.createCell(1);
        cellEnc3Tit3.setCellStyle(csEncabezados1);
        cellEnc3Tit3.setCellValue(new XSSFRichTextString("INFORME"));

        resumen.addMergedRegion(new CellRangeAddress(21, 21, 2, 4));
        XSSFRow rowEnc4Tit3 = resumen.createRow(21);
        XSSFCell cellEnc4Tit3 = rowEnc4Tit3.createCell(1);
        cellEnc4Tit3.setCellStyle(csEncabezados0);
        cellEnc4Tit3.setCellValue(new XSSFRichTextString("ENTIDAD EMISORA"));

        resumen.addMergedRegion(new CellRangeAddress(22, 22, 2, 4));
        XSSFRow rowEnc5Tit3 = resumen.createRow(22);
        XSSFCell cellEnc5Tit3 = rowEnc5Tit3.createCell(1);
        cellEnc5Tit3.setCellStyle(csEncabezados1);
        cellEnc5Tit3.setCellValue(new XSSFRichTextString("TIPO DOCUMENTO"));

        resumen.addMergedRegion(new CellRangeAddress(23, 23, 2, 4));
        XSSFRow rowEnc6Tit3 = resumen.createRow(23);
        XSSFCell cellEnc6Tit3 = rowEnc6Tit3.createCell(1);
        cellEnc6Tit3.setCellStyle(csEncabezados0);
        cellEnc6Tit3.setCellValue(new XSSFRichTextString("NRO. DOCUMENTO"));

        resumen.addMergedRegion(new CellRangeAddress(24, 24, 2, 4));
        XSSFRow rowEnc7Tit3 = resumen.createRow(24);
        XSSFCell cellEnc7Tit3 = rowEnc7Tit3.createCell(1);
        cellEnc7Tit3.setCellStyle(csEncabezados1);
        cellEnc7Tit3.setCellValue(new XSSFRichTextString("FECHA DOCUMENTO"));

        resumen.addMergedRegion(new CellRangeAddress(25, 25, 2, 4));
        XSSFRow rowEnc8Tit3 = resumen.createRow(25);
        XSSFCell cellEnc8Tit3 = rowEnc8Tit3.createCell(1);
        cellEnc8Tit3.setCellStyle(csEncabezados0);
        cellEnc8Tit3.setCellValue(new XSSFRichTextString("FECHA RECEPCIÓN CGR"));

        /* Encabezados Titulo 4 */

        XSSFRow row1EncsTit4 = resumen.createRow(27);
        XSSFCell cellEnc1Tit4 = row1EncsTit4.createCell(1);
        cellEnc1Tit4.setCellStyle(csEncabezados1);
        cellEnc1Tit4.setCellValue(new XSSFRichTextString("Ejercicio"));
        XSSFCell cellEnc2Tit4 = row1EncsTit4.createCell(3);
        cellEnc2Tit4.setCellStyle(csEncabezados1);
        cellEnc2Tit4.setCellValue(new XSSFRichTextString("ID DECRETO"));

        XSSFRow row2EncsTit4 = resumen.createRow(28);
        XSSFCell cellEnc3Tit4 = row2EncsTit4.createCell(1);
        cellEnc3Tit4.setCellStyle(csEncabezados0);
        cellEnc3Tit4.setCellValue(new XSSFRichTextString("Mes"));
        XSSFCell cellEnc4Tit4 = row2EncsTit4.createCell(3);
        cellEnc4Tit4.setCellStyle(csEncabezados0);
        cellEnc4Tit4.setCellValue(new XSSFRichTextString("TIPO DE REGISTRO"));

        XSSFRow row3EncsTit4 = resumen.createRow(29);
        XSSFCell cellEnc5Tit4 = row3EncsTit4.createCell(1);
        cellEnc5Tit4.setCellStyle(csEncabezados1);
        cellEnc5Tit4.setCellValue(new XSSFRichTextString("Día"));
        XSSFCell cellEnc6Tit4 = row3EncsTit4.createCell(3);
        cellEnc6Tit4.setCellStyle(csEncabezados1);
        cellEnc6Tit4.setCellValue(new XSSFRichTextString("NÚMERO REGISTRO"));

        XSSFRow row4EncsTit4 = resumen.createRow(30);
        XSSFCell cellEnc7Tit4 = row4EncsTit4.createCell(1);
        cellEnc7Tit4.setCellStyle(csEncabezados0);
        cellEnc7Tit4.setCellValue(new XSSFRichTextString("SECTOR RESPONSABLE"));
        XSSFCell cellEnc8Tit4 = row4EncsTit4.createCell(3);
        cellEnc8Tit4.setCellStyle(csEncabezados0);
        cellEnc8Tit4.setCellValue(new XSSFRichTextString("FECHA REGISTRO"));

        XSSFRow row5EncsTit4 = resumen.createRow(31);
        XSSFCell cellEnc9Tit4 = row5EncsTit4.createCell(1);
        cellEnc9Tit4.setCellStyle(csEncabezados1);
        cellEnc9Tit4.setCellValue(new XSSFRichTextString("MONTO TOTAL"));
        XSSFCell cellEnc10Tit4 = row5EncsTit4.createCell(3);
        cellEnc10Tit4.setCellStyle(csEncabezados1);


        /* Volcamiento de datos */

        /* Informacion General */
        setValorEncabezado(rowEnc1Tit2, csDatosEnc1, datosGeneralInforme.getEstado());
        setValorEncabezado(rowEnc2Tit2, csDatosEnc0, datosGeneralInforme.getTipoInforme());
        setValorEncabezado(rowEnc3Tit2, csDatosEnc1, datosGeneralInforme.getInforme());
        setValorEncabezado(rowEnc4Tit2, csDatosEnc0, datosGeneralInforme.getPeriodo());
        setValorEncabezado(rowEnc5Tit2, csDatosEnc1, datosGeneralInforme.getEjercicio());
        setValorEncabezado(rowEnc6Tit2, csDatosEnc0, datosGeneralInforme.getUsuario());
        setValorEncabezado(rowEnc7Tit2, csDatosEnc1, datosGeneralInforme.getEntidad());

        /* Informacion SISTRADOC */
        setValorEncabezado(rowEnc1Tit3, csDatosEnc1, datosSistradoc.getAnalista());
        setValorEncabezado(rowEnc2Tit3, csDatosEnc0, datosSistradoc.getEstadoSicogen());
        setValorEncabezado(rowEnc3Tit3, csDatosEnc1, datosSistradoc.getInforme());
        setValorEncabezado(rowEnc4Tit3, csDatosEnc0, datosSistradoc.getEntidadEmisora());
        setValorEncabezado(rowEnc5Tit3, csDatosEnc1, datosSistradoc.getTipoDocumento());
        setValorEncabezado(rowEnc6Tit3, csDatosEnc0, String.valueOf(datosSistradoc.getNroDocumento()));
        setValorEncabezado(rowEnc7Tit3, csDatosEnc1, datosSistradoc.getFechaDocumento());
        setValorEncabezado(rowEnc8Tit3, csDatosEnc0, datosSistradoc.getFechaRecepcionCGR());

        /* Informacion General XML */
        setValorEncabezado(row1EncsTit4, csDatosEnc1, datosXML.getEjercicio(), 2);
        setValorEncabezado(row1EncsTit4, csDatosEnc1, datosXML.getIdDecreto(), 4);
        setValorEncabezado(row2EncsTit4, csDatosEnc0, datosXML.getMes(), 2);
        setValorEncabezado(row2EncsTit4, csDatosEnc0, datosXML.getTipoRegistro(), 4);
        setValorEncabezado(row3EncsTit4, csDatosEnc1, datosXML.getDia(), 2);
        setValorEncabezado(row3EncsTit4, csDatosEnc1, datosXML.getNumeroRegistro(), 4);
        setValorEncabezado(row4EncsTit4, csDatosEnc0, datosXML.getSectorResponsable(), 2);
        setValorEncabezado(row4EncsTit4, csDatosEnc0, datosXML.getFechaRegistro(), 4);
        setValorEncabezado(row5EncsTit4, csDatosEnc1, datosXML.getMontoTotal(), 2);
        setValorEncabezado(row5EncsTit4, csDatosEnc1, "", 4);

        /* Errores Genericos */
        if (datosXML.getErroresGenericos() != null && !datosXML.getErroresGenericos().isEmpty()) {
            for (int i = 0 ; i < datosXML.getErroresGenericos().size(); i++) {
                rowNumTit5 = rowNumTit5 + 1;
                resumen.addMergedRegion(new CellRangeAddress(rowNumTit5, rowNumTit5, 1, 4));
                XSSFRow rowErrGen = resumen.createRow(rowNumTit5);
                XSSFCell cellErrGen = rowErrGen.createCell(1);

                ErrorGenerico error = datosXML.getErroresGenericos().get(i);
                cellErrGen.setCellValue("   " + error.getMensaje());

                if (error.getTipoError() == 1) {
                    new AddImageExcel().addImageToSheet("B" + (rowNumTit5 + 1), resumen, resumen.createDrawingPatriarch(), tipError1,
                            2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                } else if (error.getTipoError() == 2) {
                    new AddImageExcel().addImageToSheet("B" + (rowNumTit5 + 1), resumen, resumen.createDrawingPatriarch(), tipError2,
                            2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                } else if (error.getTipoError() == 3) {
                    new AddImageExcel().addImageToSheet("B" + (rowNumTit5 + 1), resumen, resumen.createDrawingPatriarch(), tipError3,
                            2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                }

                if (i%2 == 0) {
                    cellErrGen.setCellStyle(csDatosEnc1);
                } else {
                    cellErrGen.setCellStyle(csDatosEnc0);
                }
            }
        } else {
            resumen.addMergedRegion(new CellRangeAddress(33, 33, 1, 4));
            XSSFRow rowErrGen = resumen.createRow(33);
            XSSFCell cellErrGen = rowErrGen.createCell(1);
            cellErrGen.setCellValue("El informe no presenta errores genéricos");
            cellErrGen.setCellStyle(csDatosEnc1);
        }

        int idxDetalle = 1;

        for (int i = 0; i < datosXML.getDetalle().size(); i++) {
            DetalleInformeTDRMP detalleInf = datosXML.getDetalle().get(i);

            detalle.addMergedRegion(new CellRangeAddress(idxDetalle, idxDetalle, 1, 6));
            XSSFRow rowEnc1Det = detalle.createRow(idxDetalle);
            XSSFCell cellEnc1Det = rowEnc1Det.createCell(0);
            cellEnc1Det.setCellStyle(csEncabezados1);
            cellEnc1Det.setCellValue(new XSSFRichTextString("PARTIDA"));

            detalle.addMergedRegion(new CellRangeAddress(idxDetalle + 1, idxDetalle + 1, 1, 6));
            XSSFRow rowEnc2Det = detalle.createRow(idxDetalle + 1);
            XSSFCell cellEnc2Det = rowEnc2Det.createCell(0);
            cellEnc2Det.setCellStyle(csEncabezados0);
            cellEnc2Det.setCellValue(new XSSFRichTextString("CAPITULO"));

            detalle.addMergedRegion(new CellRangeAddress(idxDetalle + 2, idxDetalle + 2, 1, 6));
            XSSFRow rowEnc3Det = detalle.createRow(idxDetalle + 2);
            XSSFCell cellEnc3Det = rowEnc3Det.createCell(0);
            cellEnc3Det.setCellStyle(csEncabezados1);
            cellEnc3Det.setCellValue(new XSSFRichTextString("PROGRAMA"));

            setValorEncabezado(rowEnc1Det, csDatosEnc1, detalleInf.getPartida(), 1);
            setValorEncabezado(rowEnc2Det, csDatosEnc0, detalleInf.getCapitulo(), 1);
            setValorEncabezado(rowEnc3Det, csDatosEnc1, detalleInf.getPrograma(), 1);

            XSSFRow rowEncTblDet = detalle.createRow(idxDetalle + 4);
            creaCeldaEncTablaDetalle(rowEncTblDet, "SUBTITULO", 0, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "ITEM", 1, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "ASIGNACION", 2, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "SUBASIGNACION", 3, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "DENOMINACION", 4, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "INCREMENTO", 5, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "REDUCCION", 6, csEncTblDet);

            List<CuentaTDRMP> cuentas = detalleInf.getCuentasTDRMP();
            idxDetalle = idxDetalle + 5;

            for (int j = 0; j < cuentas.size(); j++) {
                CuentaTDRMP cuenta = cuentas.get(j);
                XSSFRow rowInfTblDet = detalle.createRow(idxDetalle);

                if (j%2 == 0) {
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getSubtitulo(), 0, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getItem(), 1, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getAsignacion(), 2, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getSubasignacion(), 3, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getDenominacion(), 4, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getIncremento(), 5, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getReduccion(), 6, csDatosTblDet1);
                } else {
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getSubtitulo(), 0, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getItem(), 1, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getAsignacion(), 2, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getSubasignacion(), 3, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getDenominacion(), 4, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getIncremento(), 5, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getReduccion(), 6, csDatosTblDet0);
                }

                idxDetalle = idxDetalle + 1;

                if (cuenta.getError()) {
                    detalle.addMergedRegion(new CellRangeAddress(idxDetalle, idxDetalle, 0, 6));
                    XSSFRow rowEncErrDet = detalle.createRow(idxDetalle);
                    XSSFCell cellEncErrDet = rowEncErrDet.createCell(0);
                    cellEncErrDet.setCellStyle(csTitulosErr);
                    cellEncErrDet.setCellValue(new XSSFRichTextString("ERRORES DETECTADOS LÍNEA"));

                    List<ErrorDetalle> erroresDetalle = cuenta.getErroresDetalle();
                    idxDetalle = idxDetalle + 1;

                    for (int k = 0; k < erroresDetalle.size(); k++) {
                        ErrorDetalle errorDetalle = erroresDetalle.get(k);
                        detalle.addMergedRegion(new CellRangeAddress(idxDetalle, idxDetalle, 0, 6));
                        XSSFRow rowErrDet = detalle.createRow(idxDetalle);

                        if (errorDetalle.getTipoError() == 1) {
                            new AddImageExcel().addImageToSheet("A" + (idxDetalle + 1), detalle, detalle.createDrawingPatriarch(), tipError1,
                                    2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                        } else if (errorDetalle.getTipoError() == 2) {
                            new AddImageExcel().addImageToSheet("A" + (idxDetalle + 1), detalle, detalle.createDrawingPatriarch(), tipError2,
                                    2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                        } else if (errorDetalle.getTipoError() == 3) {
                            new AddImageExcel().addImageToSheet("A" + (idxDetalle + 1), detalle, detalle.createDrawingPatriarch(), tipError3,
                                    2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                        }

                        creaCeldaDataTablaDetalle(rowErrDet, "  ".concat(errorDetalle.getMensaje()), 0, csDatosTblDet1);
                        idxDetalle = idxDetalle + 1;
                    }
                }
            }

            idxDetalle = idxDetalle + 1;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        book.write(baos);
        baos.close();
        byte[] bArray = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(bArray);

        Map<String, InputStreamResource> result = new HashMap<String, InputStreamResource>();
        result.put(generaNombreArchivo(datosArchivo.getListaDetalle().get(0).getNombre()), new InputStreamResource(is));

        return result;
    }

    public Map<String, InputStreamResource> generaExcelReporteValidacionPI(URL logoSicogen,
                                                                            Integer idArchivo,
                                                                            URL tipError1,
                                                                            URL tipError2,
                                                                            URL tipError3) throws Exception {

        DatosInformePIDTO datosInformePI = informesService.getDatosInformePI(idArchivo);
        InformeArchivoDet datosArchivo = informesService.getArchivo(idArchivo);

        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet resumen = book.createSheet();
        XSSFSheet detalle = book.createSheet();

        book.setSheetName(0, "Resumen");
        book.setSheetName(1, "Detalle");

        resumen.setColumnWidth(1, 8000);
        resumen.setColumnWidth(2, 10000);
        resumen.setColumnWidth(3, 10000);
        resumen.setColumnWidth(4, 10000);

        /* Styling */

        /* Backgrounds */
        XSSFColor bckTitulos =  new XSSFColor(new java.awt.Color(63,135,193));
        XSSFColor bckTabla =  new XSSFColor(new java.awt.Color(215,215,217));
        XSSFColor bckBlanco =  new XSSFColor(new java.awt.Color(255,255,255));
        XSSFColor bckError =  new XSSFColor(new java.awt.Color(255, 199, 206));

        /* Fonts */

        XSSFFont fntTituloReporte = book.createFont();
        fntTituloReporte.setFontHeightInPoints((short) 20);
        fntTituloReporte.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntTituloReporte.setBold(Boolean.TRUE);
        fntTituloReporte.setFontName("Arial");

        XSSFFont fntTitulos = book.createFont();
        fntTitulos.setFontHeightInPoints((short) 11);
        fntTitulos.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
        fntTitulos.setBold(Boolean.TRUE);
        fntTitulos.setFontName("Arial");

        XSSFFont fntDatosEncabezados = book.createFont();
        fntDatosEncabezados.setFontHeightInPoints((short) 11);
        fntDatosEncabezados.setColor(new XSSFColor(new java.awt.Color(102, 102, 102)));
        fntDatosEncabezados.setFontName("Arial");

        XSSFFont fntTitulosEncabezados = book.createFont();
        fntTitulosEncabezados.setFontHeightInPoints((short) 11);
        fntTitulosEncabezados.setColor(new XSSFColor(new java.awt.Color(102, 102, 102)));
        fntTitulosEncabezados.setBold(Boolean.TRUE);
        fntTitulosEncabezados.setFontName("Arial");

        XSSFFont fntEncTblDet = book.createFont();
        fntEncTblDet.setFontHeightInPoints((short) 11);
        fntEncTblDet.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntEncTblDet.setBold(Boolean.TRUE);
        fntEncTblDet.setFontName("Arial");

        XSSFFont fntTblDet = book.createFont();
        fntTblDet.setFontHeightInPoints((short) 11);
        fntTblDet.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntTblDet.setFontName("Arial");

        /* Cell Styles */

        XSSFCellStyle csTituloReporte = book.createCellStyle();
        csTituloReporte.setFont(fntTituloReporte);
        csTituloReporte.setFillForegroundColor(bckTabla);
        csTituloReporte.setFillBackgroundColor(bckTabla);
        csTituloReporte.setAlignment(HorizontalAlignment.CENTER);
        csTituloReporte.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csTitulos = book.createCellStyle();
        csTitulos.setFont(fntTitulos);
        csTitulos.setFillForegroundColor(bckTitulos);
        csTitulos.setFillBackgroundColor(bckTitulos);
        csTitulos.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        csTitulos.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle csTitulosErr = book.createCellStyle();
        csTitulosErr.setFont(fntTitulos);
        csTitulosErr.setFillForegroundColor(bckTitulos);
        csTitulosErr.setFillBackgroundColor(bckTitulos);
        csTitulosErr.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncabezados0 = book.createCellStyle();
        csEncabezados0.setFillForegroundColor(bckBlanco);
        csEncabezados0.setFillBackgroundColor(bckBlanco);
        csEncabezados0.setFont(fntTitulosEncabezados);
        csEncabezados0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncabezados1 = book.createCellStyle();
        csEncabezados1.setFont(fntTitulosEncabezados);
        csEncabezados1.setFillForegroundColor(bckTabla);
        csEncabezados1.setFillBackgroundColor(bckTabla);
        csEncabezados1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosEnc0 = book.createCellStyle();
        csDatosEnc0.setFont(fntDatosEncabezados);
        csDatosEnc0.setFillForegroundColor(bckBlanco);
        csDatosEnc0.setFillBackgroundColor(bckBlanco);
        csDatosEnc0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosEnc1 = book.createCellStyle();
        csDatosEnc1.setFont(fntDatosEncabezados);
        csDatosEnc1.setFillForegroundColor(bckTabla);
        csDatosEnc1.setFillBackgroundColor(bckTabla);
        csDatosEnc1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncTblDet = book.createCellStyle();
        csEncTblDet.setFont(fntEncTblDet);
        csEncTblDet.setFillForegroundColor(bckBlanco);
        csEncTblDet.setFillBackgroundColor(bckBlanco);
        csEncTblDet.setAlignment(HorizontalAlignment.CENTER);
        csEncTblDet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        csEncTblDet.setBorderBottom(BorderStyle.MEDIUM);

        XSSFCellStyle csDatosTblDet0 = book.createCellStyle();
        csDatosTblDet0.setFont(fntTblDet);
        csDatosTblDet0.setFillForegroundColor(bckBlanco);
        csDatosTblDet0.setFillBackgroundColor(bckBlanco);
        csDatosTblDet0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDet1 = book.createCellStyle();
        csDatosTblDet1.setFont(fntTblDet);
        csDatosTblDet1.setFillForegroundColor(bckTabla);
        csDatosTblDet1.setFillBackgroundColor(bckTabla);
        csDatosTblDet1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentrado0 = book.createCellStyle();
        csDatosTblDetCentrado0.setFont(fntTblDet);
        csDatosTblDetCentrado0.setFillForegroundColor(bckBlanco);
        csDatosTblDetCentrado0.setFillBackgroundColor(bckBlanco);
        csDatosTblDetCentrado0.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentrado0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentrado1 = book.createCellStyle();
        csDatosTblDetCentrado1.setFont(fntTblDet);
        csDatosTblDetCentrado1.setFillForegroundColor(bckTabla);
        csDatosTblDetCentrado1.setFillBackgroundColor(bckTabla);
        csDatosTblDetCentrado1.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentrado1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerecha0 = book.createCellStyle();
        csDatosTblDetDerecha0.setFont(fntTblDet);
        csDatosTblDetDerecha0.setFillForegroundColor(bckBlanco);
        csDatosTblDetDerecha0.setFillBackgroundColor(bckBlanco);
        csDatosTblDetDerecha0.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerecha0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerecha1 = book.createCellStyle();
        csDatosTblDetDerecha1.setFont(fntTblDet);
        csDatosTblDetDerecha1.setFillForegroundColor(bckTabla);
        csDatosTblDetDerecha1.setFillBackgroundColor(bckTabla);
        csDatosTblDetDerecha1.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerecha1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetError = book.createCellStyle();
        csDatosTblDetError.setFont(fntTblDet);
        csDatosTblDetError.setFillForegroundColor(bckError);
        csDatosTblDetError.setFillBackgroundColor(bckError);
        csDatosTblDetError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentradoError = book.createCellStyle();
        csDatosTblDetCentradoError.setFont(fntTblDet);
        csDatosTblDetCentradoError.setFillForegroundColor(bckError);
        csDatosTblDetCentradoError.setFillBackgroundColor(bckError);
        csDatosTblDetCentradoError.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentradoError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerechaError = book.createCellStyle();
        csDatosTblDetDerechaError.setFont(fntTblDet);
        csDatosTblDetDerechaError.setFillForegroundColor(bckError);
        csDatosTblDetDerechaError.setFillBackgroundColor(bckError);
        csDatosTblDetDerechaError.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerechaError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        /* Imagen de cabecera */
        resumen.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
        new AddImageExcel().addImageToSheet("B2", resumen, resumen.createDrawingPatriarch(), logoSicogen,
                100, 25, AddImageExcel.OVERLAY_ROW_AND_COLUMN);

        /* Titulos */
        int rowNumTit1 = 7;
        int rowNumTit2 = 9;
        int rowNumTit3 = 18;

        /* Titulo General */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit1, rowNumTit1, 1, 4));
        XSSFRow rowTit1 = resumen.createRow(rowNumTit1);
        XSSFCell cellTit1 = rowTit1.createCell(1);
        cellTit1.setCellStyle(csTituloReporte);
        cellTit1.setCellValue(new XSSFRichTextString("REPORTE DE VALIDACIÓN"));

        /* Titulo 2 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit2, rowNumTit2, 1, 4));
        XSSFRow rowTit2 = resumen.createRow(rowNumTit2);
        XSSFCell cellTit2 = rowTit2.createCell(1);
        cellTit2.setCellStyle(csTitulos);
        cellTit2.setCellValue(new XSSFRichTextString("Información General"));

        /* Titulo 3 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit3, rowNumTit3, 1, 4));
        XSSFRow rowTit3 = resumen.createRow(rowNumTit3);
        XSSFCell cellTit3 = rowTit3.createCell(1);
        cellTit3.setCellStyle(csTitulos);
        cellTit3.setCellValue(new XSSFRichTextString("Errores Genéricos"));

        /* Titulo Detalle */
        detalle.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        XSSFRow rowTitDetalle = detalle.createRow(0);
        XSSFCell cellTitDetalle = rowTitDetalle.createCell(0);
        cellTitDetalle.setCellStyle(csTitulos);
        cellTitDetalle.setCellValue(new XSSFRichTextString("Detalle del Archivo Cargado"));

        /* Encabezados Titulo 2 */

        resumen.addMergedRegion(new CellRangeAddress(10, 10, 2, 4));
        XSSFRow rowEnc1Tit2 = resumen.createRow(10);
        XSSFCell cellEnc1Tit2 = rowEnc1Tit2.createCell(1);
        cellEnc1Tit2.setCellStyle(csEncabezados1);
        cellEnc1Tit2.setCellValue(new XSSFRichTextString("ESTADO DE VALIDACIÓN"));

        resumen.addMergedRegion(new CellRangeAddress(11, 11, 2, 4));
        XSSFRow rowEnc2Tit2 = resumen.createRow(11);
        XSSFCell cellEnc2Tit2 = rowEnc2Tit2.createCell(1);
        cellEnc2Tit2.setCellStyle(csEncabezados0);
        cellEnc2Tit2.setCellValue(new XSSFRichTextString("TIPO DE INFORME"));

        resumen.addMergedRegion(new CellRangeAddress(12, 12, 2, 4));
        XSSFRow rowEnc3Tit2 = resumen.createRow(12);
        XSSFCell cellEnc3Tit2 = rowEnc3Tit2.createCell(1);
        cellEnc3Tit2.setCellStyle(csEncabezados1);
        cellEnc3Tit2.setCellValue(new XSSFRichTextString("INFORME"));

        resumen.addMergedRegion(new CellRangeAddress(13, 13, 2, 4));
        XSSFRow rowEnc4Tit2 = resumen.createRow(13);
        XSSFCell cellEnc4Tit2 = rowEnc4Tit2.createCell(1);
        cellEnc4Tit2.setCellStyle(csEncabezados0);
        cellEnc4Tit2.setCellValue(new XSSFRichTextString("PERÍODO"));

        resumen.addMergedRegion(new CellRangeAddress(14, 14, 2, 4));
        XSSFRow rowEnc5Tit2 = resumen.createRow(14);
        XSSFCell cellEnc5Tit2 = rowEnc5Tit2.createCell(1);
        cellEnc5Tit2.setCellStyle(csEncabezados1);
        cellEnc5Tit2.setCellValue(new XSSFRichTextString("EJERCICIO"));

        resumen.addMergedRegion(new CellRangeAddress(15, 15, 2, 4));
        XSSFRow rowEnc6Tit2 = resumen.createRow(15);
        XSSFCell cellEnc6Tit2 = rowEnc6Tit2.createCell(1);
        cellEnc6Tit2.setCellStyle(csEncabezados0);
        cellEnc6Tit2.setCellValue(new XSSFRichTextString("USUARIO"));

        resumen.addMergedRegion(new CellRangeAddress(16, 16, 2, 4));
        XSSFRow rowEnc7Tit2 = resumen.createRow(16);
        XSSFCell cellEnc7Tit2 = rowEnc7Tit2.createCell(1);
        cellEnc7Tit2.setCellStyle(csEncabezados1);
        cellEnc7Tit2.setCellValue(new XSSFRichTextString("ENTIDAD"));


        /* Volcamiento de datos */

        /* Informacion General */
        setValorEncabezado(rowEnc1Tit2, csDatosEnc1, datosInformePI.getEstadoValidacion());
        setValorEncabezado(rowEnc2Tit2, csDatosEnc0, datosInformePI.getTipoInforme());
        setValorEncabezado(rowEnc3Tit2, csDatosEnc1, datosInformePI.getNombreInforme());
        setValorEncabezado(rowEnc4Tit2, csDatosEnc0, datosInformePI.getPeriodo());
        setValorEncabezado(rowEnc5Tit2, csDatosEnc1, datosInformePI.getEjercicio());
        setValorEncabezado(rowEnc6Tit2, csDatosEnc0, datosInformePI.getUsuario());
        setValorEncabezado(rowEnc7Tit2, csDatosEnc1, datosInformePI.getEntdad());

        /* Errores Genericos */
        if (!datosInformePI.getErroresGenericos().isEmpty()) {

            List<ErrorGenerico> erroresGenericos = datosInformePI.getErroresGenericos();

            for (int i = 0 ; i < erroresGenericos.size(); i++) {
                rowNumTit3 = rowNumTit3 + 1;
                resumen.addMergedRegion(new CellRangeAddress(rowNumTit3, rowNumTit3, 1, 4));
                XSSFRow rowErrGen = resumen.createRow(rowNumTit3);
                XSSFCell cellErrGen = rowErrGen.createCell(1);

                ErrorGenerico error = erroresGenericos.get(i);
                cellErrGen.setCellValue("   " + error.getMensaje());

                if (error.getTipoError() == 1) {
                    new AddImageExcel().addImageToSheet("B" + (rowNumTit3 + 1), resumen, resumen.createDrawingPatriarch(), tipError1,
                            2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                } else if (error.getTipoError() == 2) {
                    new AddImageExcel().addImageToSheet("B" + (rowNumTit3 + 1), resumen, resumen.createDrawingPatriarch(), tipError2,
                            2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                } else if (error.getTipoError() == 3) {
                    new AddImageExcel().addImageToSheet("B" + (rowNumTit3 + 1), resumen, resumen.createDrawingPatriarch(), tipError3,
                            2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                }

                if (i%2 == 0) {
                    cellErrGen.setCellStyle(csDatosEnc1);
                } else {
                    cellErrGen.setCellStyle(csDatosEnc0);
                }
            }
        } else {
            resumen.addMergedRegion(new CellRangeAddress(19, 19, 1, 4));
            XSSFRow rowErrGen = resumen.createRow(19);
            XSSFCell cellErrGen = rowErrGen.createCell(1);
            cellErrGen.setCellValue("El informe no presenta errores genéricos");
            cellErrGen.setCellStyle(csDatosEnc1);
        }

        int idxDetalle = 1;
        List<DatosEntidadInformePIDTO> datosEntidades = datosInformePI.getListaEntidades();

        for (int i = 0; i < datosEntidades.size(); i++) {
            DatosEntidadInformePIDTO detalleInf = datosEntidades.get(i);

            detalle.addMergedRegion(new CellRangeAddress(idxDetalle, idxDetalle, 1, 5));
            XSSFRow rowEnc1Det = detalle.createRow(idxDetalle);
            XSSFCell cellEnc1Det = rowEnc1Det.createCell(0);
            cellEnc1Det.setCellStyle(csEncabezados1);
            cellEnc1Det.setCellValue(new XSSFRichTextString("PARTIDA"));

            detalle.addMergedRegion(new CellRangeAddress(idxDetalle + 1, idxDetalle + 1, 1, 5));
            XSSFRow rowEnc2Det = detalle.createRow(idxDetalle + 1);
            XSSFCell cellEnc2Det = rowEnc2Det.createCell(0);
            cellEnc2Det.setCellStyle(csEncabezados0);
            cellEnc2Det.setCellValue(new XSSFRichTextString("CAPITULO"));

            detalle.addMergedRegion(new CellRangeAddress(idxDetalle + 2, idxDetalle + 2, 1, 5));
            XSSFRow rowEnc3Det = detalle.createRow(idxDetalle + 2);
            XSSFCell cellEnc3Det = rowEnc3Det.createCell(0);
            cellEnc3Det.setCellStyle(csEncabezados1);
            cellEnc3Det.setCellValue(new XSSFRichTextString("PROGRAMA"));

            detalle.addMergedRegion(new CellRangeAddress(idxDetalle + 3, idxDetalle + 3, 1, 5));
            XSSFRow rowEnc4Det = detalle.createRow(idxDetalle + 3);
            XSSFCell cellEnc4Det = rowEnc4Det.createCell(0);
            cellEnc4Det.setCellStyle(csEncabezados0);
            cellEnc4Det.setCellValue(new XSSFRichTextString("TOTAL INGRESOS CLP"));

            detalle.addMergedRegion(new CellRangeAddress(idxDetalle + 4, idxDetalle + 4, 1, 5));
            XSSFRow rowEnc5Det = detalle.createRow(idxDetalle + 4);
            XSSFCell cellEnc5Det = rowEnc5Det.createCell(0);
            cellEnc5Det.setCellStyle(csEncabezados1);
            cellEnc5Det.setCellValue(new XSSFRichTextString("TOTAL GASTOS CLP"));

            setValorEncabezado(rowEnc1Det, csDatosEnc1, detalleInf.getPartida(), 1);
            setValorEncabezado(rowEnc2Det, csDatosEnc0, detalleInf.getCapitulo(), 1);
            setValorEncabezado(rowEnc3Det, csDatosEnc1, detalleInf.getPrograma(), 1);
            setValorEncabezado(rowEnc4Det, csDatosEnc0, detalleInf.getTotalIngresosCLP(), 1);
            setValorEncabezado(rowEnc5Det, csDatosEnc1, detalleInf.getTotalGastosCLP(), 1);

            XSSFRow rowEncTblDet = detalle.createRow(idxDetalle + 6);
            creaCeldaEncTablaDetalle(rowEncTblDet, "CUENTA", 0, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "TIPO CUENTA", 1, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "DENOMINACIÓN DE LA CUENTA", 2, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "GLOSAS", 3, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "MONTO CLP", 4, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "MONTO USD", 5, csEncTblDet);

            List<DetalleInformePIDTO> cuentas = detalleInf.getListaDetalleCuentas();
            idxDetalle = idxDetalle + 7;

            for (int j = 0; j < cuentas.size(); j++) {
                DetalleInformePIDTO cuenta = cuentas.get(j);
                XSSFRow rowInfTblDet = detalle.createRow(idxDetalle);

                if (j%2 == 0) {
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getCuenta(), 0, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getTipoCuenta(), 1, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getDenominacionCuenta(), 2, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getGlosas(), 3, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getMontoCLP(), 4, csDatosTblDetDerecha1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getMontoUSD(), 5, csDatosTblDetDerecha1);
                } else {
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getCuenta(), 0, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getTipoCuenta(), 1, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getDenominacionCuenta(), 2, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getGlosas(), 3, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getMontoCLP(), 4, csDatosTblDetDerecha0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getMontoUSD(), 5, csDatosTblDetDerecha0);
                }

                idxDetalle = idxDetalle + 1;

                if (cuenta.getEstadoRegla1().equals("1")) {
                    detalle.addMergedRegion(new CellRangeAddress(idxDetalle, idxDetalle, 0, 5));
                    XSSFRow rowEncErrDet = detalle.createRow(idxDetalle);
                    XSSFCell cellEncErrDet = rowEncErrDet.createCell(0);
                    cellEncErrDet.setCellStyle(csTitulosErr);
                    cellEncErrDet.setCellValue(new XSSFRichTextString("ERRORES DETECTADOS LÍNEA"));

                    detalle.addMergedRegion(new CellRangeAddress(idxDetalle + 1, idxDetalle + 1, 0, 5));
                    XSSFRow rowErrDet = detalle.createRow(idxDetalle);
                    String tipoErrorRegla = cuenta.getTipoErrorRegla();

                    if (tipoErrorRegla.equals("1")) {
                        new AddImageExcel().addImageToSheet("A" + (idxDetalle + 1), detalle, detalle.createDrawingPatriarch(), tipError1,
                                2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                    } else if (tipoErrorRegla.equals("2")) {
                        new AddImageExcel().addImageToSheet("A" + (idxDetalle + 1), detalle, detalle.createDrawingPatriarch(), tipError2,
                                2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                    } else if (tipoErrorRegla.equals("3")) {
                        new AddImageExcel().addImageToSheet("A" + (idxDetalle + 1), detalle, detalle.createDrawingPatriarch(), tipError3,
                                2, 5, AddImageExcel.OVERLAY_ROW_AND_COLUMN);
                    }

                    creaCeldaDataTablaDetalle(rowErrDet, "  ".concat(cuenta.getMensajeRegla1()), 0, csDatosTblDet1);
                    idxDetalle = idxDetalle + 2;
                }

            }

            idxDetalle = idxDetalle + 1;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        book.write(baos);
        baos.close();
        byte[] bArray = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(bArray);

        Map<String, InputStreamResource> result = new HashMap<String, InputStreamResource>();
        result.put(generaNombreArchivo(datosArchivo.getListaDetalle().get(0).getNombre()), new InputStreamResource(is));

        return result;
    }

    public Map<String, InputStreamResource> generaExcelVerIC(URL logoSicogen, Integer idArchivo) throws IOException {

        InformacionGeneralRV datosGeneralInforme = informesService.obtieneInfoGeneralRV(idArchivo);
        InformeContableDTO datosDetalleInforme = informesService.obtieneDatosInformeContable(idArchivo);
        ReporteValidacionPaginacion detalleInforme = informesService.obtieneReporteValidacionPaginacion(idArchivo,
                1, 0, 0, "1");
        List<ReporteValidacionData> dataInforme = detalleInforme.getData();

        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet resumen = book.createSheet();
        XSSFSheet detalle = book.createSheet();

        book.setSheetName(0, "Resumen");
        book.setSheetName(1, "Detalle");

        resumen.setColumnWidth(1, 8000);
        resumen.setColumnWidth(2, 30000);

        /* Styling */

        /* Backgrounds */
        XSSFColor bckTitulos =  new XSSFColor(new java.awt.Color(63,135,193));
        XSSFColor bckTabla =  new XSSFColor(new java.awt.Color(215,215,217));
        XSSFColor bckBlanco =  new XSSFColor(new java.awt.Color(255,255,255));
        XSSFColor bckError =  new XSSFColor(new java.awt.Color(255, 199, 206));

        /* Fonts */

        XSSFFont fntTituloReporte = book.createFont();
        fntTituloReporte.setFontHeightInPoints((short) 20);
        fntTituloReporte.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntTituloReporte.setBold(Boolean.TRUE);
        fntTituloReporte.setFontName("Arial");

        XSSFFont fntTitulos = book.createFont();
        fntTitulos.setFontHeightInPoints((short) 11);
        fntTitulos.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
        fntTitulos.setBold(Boolean.TRUE);
        fntTitulos.setFontName("Arial");

        XSSFFont fntDatosEncabezados = book.createFont();
        fntDatosEncabezados.setFontHeightInPoints((short) 11);
        fntDatosEncabezados.setColor(new XSSFColor(new java.awt.Color(102, 102, 102)));
        fntDatosEncabezados.setFontName("Arial");

        XSSFFont fntTitulosEncabezados = book.createFont();
        fntTitulosEncabezados.setFontHeightInPoints((short) 11);
        fntTitulosEncabezados.setColor(new XSSFColor(new java.awt.Color(102, 102, 102)));
        fntTitulosEncabezados.setBold(Boolean.TRUE);
        fntTitulosEncabezados.setFontName("Arial");

        XSSFFont fntEncTblDet = book.createFont();
        fntEncTblDet.setFontHeightInPoints((short) 11);
        fntEncTblDet.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntEncTblDet.setBold(Boolean.TRUE);
        fntEncTblDet.setFontName("Arial");

        XSSFFont fntTblDet = book.createFont();
        fntTblDet.setFontHeightInPoints((short) 11);
        fntTblDet.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntTblDet.setFontName("Arial");

        /* Cell Styles */

        XSSFCellStyle csTituloReporte = book.createCellStyle();
        csTituloReporte.setFont(fntTituloReporte);
        csTituloReporte.setFillForegroundColor(bckTabla);
        csTituloReporte.setFillBackgroundColor(bckTabla);
        csTituloReporte.setAlignment(HorizontalAlignment.CENTER);
        csTituloReporte.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csTitulos = book.createCellStyle();
        csTitulos.setFont(fntTitulos);
        csTitulos.setFillForegroundColor(bckTitulos);
        csTitulos.setFillBackgroundColor(bckTitulos);
        csTitulos.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        csTitulos.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle csEncabezados0 = book.createCellStyle();
        csEncabezados0.setFillForegroundColor(bckBlanco);
        csEncabezados0.setFillBackgroundColor(bckBlanco);
        csEncabezados0.setFont(fntTitulosEncabezados);
        csEncabezados0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncabezados1 = book.createCellStyle();
        csEncabezados1.setFont(fntTitulosEncabezados);
        csEncabezados1.setFillForegroundColor(bckTabla);
        csEncabezados1.setFillBackgroundColor(bckTabla);
        csEncabezados1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosEnc0 = book.createCellStyle();
        csDatosEnc0.setFont(fntDatosEncabezados);
        csDatosEnc0.setFillForegroundColor(bckBlanco);
        csDatosEnc0.setFillBackgroundColor(bckBlanco);
        csDatosEnc0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosEnc1 = book.createCellStyle();
        csDatosEnc1.setFont(fntDatosEncabezados);
        csDatosEnc1.setFillForegroundColor(bckTabla);
        csDatosEnc1.setFillBackgroundColor(bckTabla);
        csDatosEnc1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncTblDet = book.createCellStyle();
        csEncTblDet.setFont(fntEncTblDet);
        csEncTblDet.setFillForegroundColor(bckBlanco);
        csEncTblDet.setFillBackgroundColor(bckBlanco);
        csEncTblDet.setAlignment(HorizontalAlignment.CENTER);
        csEncTblDet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        csEncTblDet.setBorderBottom(BorderStyle.MEDIUM);

        XSSFCellStyle csDatosTblDet0 = book.createCellStyle();
        csDatosTblDet0.setFont(fntTblDet);
        csDatosTblDet0.setFillForegroundColor(bckBlanco);
        csDatosTblDet0.setFillBackgroundColor(bckBlanco);
        csDatosTblDet0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDet1 = book.createCellStyle();
        csDatosTblDet1.setFont(fntTblDet);
        csDatosTblDet1.setFillForegroundColor(bckTabla);
        csDatosTblDet1.setFillBackgroundColor(bckTabla);
        csDatosTblDet1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentrado0 = book.createCellStyle();
        csDatosTblDetCentrado0.setFont(fntTblDet);
        csDatosTblDetCentrado0.setFillForegroundColor(bckBlanco);
        csDatosTblDetCentrado0.setFillBackgroundColor(bckBlanco);
        csDatosTblDetCentrado0.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentrado0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentrado1 = book.createCellStyle();
        csDatosTblDetCentrado1.setFont(fntTblDet);
        csDatosTblDetCentrado1.setFillForegroundColor(bckTabla);
        csDatosTblDetCentrado1.setFillBackgroundColor(bckTabla);
        csDatosTblDetCentrado1.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentrado1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerecha0 = book.createCellStyle();
        csDatosTblDetDerecha0.setFont(fntTblDet);
        csDatosTblDetDerecha0.setFillForegroundColor(bckBlanco);
        csDatosTblDetDerecha0.setFillBackgroundColor(bckBlanco);
        csDatosTblDetDerecha0.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerecha0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerecha1 = book.createCellStyle();
        csDatosTblDetDerecha1.setFont(fntTblDet);
        csDatosTblDetDerecha1.setFillForegroundColor(bckTabla);
        csDatosTblDetDerecha1.setFillBackgroundColor(bckTabla);
        csDatosTblDetDerecha1.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerecha1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetError = book.createCellStyle();
        csDatosTblDetError.setFont(fntTblDet);
        csDatosTblDetError.setFillForegroundColor(bckError);
        csDatosTblDetError.setFillBackgroundColor(bckError);
        csDatosTblDetError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentradoError = book.createCellStyle();
        csDatosTblDetCentradoError.setFont(fntTblDet);
        csDatosTblDetCentradoError.setFillForegroundColor(bckError);
        csDatosTblDetCentradoError.setFillBackgroundColor(bckError);
        csDatosTblDetCentradoError.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentradoError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerechaError = book.createCellStyle();
        csDatosTblDetDerechaError.setFont(fntTblDet);
        csDatosTblDetDerechaError.setFillForegroundColor(bckError);
        csDatosTblDetDerechaError.setFillBackgroundColor(bckError);
        csDatosTblDetDerechaError.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerechaError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        /* Imagen de cabecera */
        resumen.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
        new AddImageExcel().addImageToSheet("B2", resumen, resumen.createDrawingPatriarch(), logoSicogen,
                100, 25, AddImageExcel.OVERLAY_ROW_AND_COLUMN);

        /* Titulos */
        int rowNumTit1 = 7;
        int rowNumTit2 = 9;
        int rowNumTit3 = 17;

        /* Titulo General */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit1, rowNumTit1, 1, 2));
        XSSFRow rowTit1 = resumen.createRow(rowNumTit1);
        XSSFCell cellTit1 = rowTit1.createCell(1);
        cellTit1.setCellStyle(csTituloReporte);
        cellTit1.setCellValue(new XSSFRichTextString("INFORME CONTABLE"));

        /* Titulo 1 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit2, rowNumTit2, 1, 2));
        XSSFRow rowTit2 = resumen.createRow(rowNumTit2);
        XSSFCell cellTit2 = rowTit2.createCell(1);
        cellTit2.setCellStyle(csTitulos);
        cellTit2.setCellValue(new XSSFRichTextString("Información General"));

        /* Titulo 2 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit3, rowNumTit3, 1, 2));
        XSSFRow rowTit3 = resumen.createRow(rowNumTit3);
        XSSFCell cellTit3 = rowTit3.createCell(1);
        cellTit3.setCellStyle(csTitulos);
        cellTit3.setCellValue(new XSSFRichTextString("Detalle de Archivo Cargado"));

        /* Encabezados Titulo 2 */

        XSSFRow rowEnc1Tit2 = resumen.createRow(10);
        XSSFCell cellEnc1Tit2 = rowEnc1Tit2.createCell(1);
        cellEnc1Tit2.setCellStyle(csEncabezados1);
        cellEnc1Tit2.setCellValue(new XSSFRichTextString("ESTADO DE VALIDACIÓN"));

        XSSFRow rowEnc2Tit2 = resumen.createRow(11);
        XSSFCell cellEnc2Tit2 = rowEnc2Tit2.createCell(1);
        cellEnc2Tit2.setCellStyle(csEncabezados0);
        cellEnc2Tit2.setCellValue(new XSSFRichTextString("TIPO DE INFORME"));

        XSSFRow rowEnc3Tit2 = resumen.createRow(12);
        XSSFCell cellEnc3Tit2 = rowEnc3Tit2.createCell(1);
        cellEnc3Tit2.setCellStyle(csEncabezados1);
        cellEnc3Tit2.setCellValue(new XSSFRichTextString("INFORME"));

        XSSFRow rowEnc4Tit2 = resumen.createRow(13);
        XSSFCell cellEnc4Tit2 = rowEnc4Tit2.createCell(1);
        cellEnc4Tit2.setCellStyle(csEncabezados0);
        cellEnc4Tit2.setCellValue(new XSSFRichTextString("PERÍODO"));

        XSSFRow rowEnc5Tit2 = resumen.createRow(14);
        XSSFCell cellEnc5Tit2 = rowEnc5Tit2.createCell(1);
        cellEnc5Tit2.setCellStyle(csEncabezados1);
        cellEnc5Tit2.setCellValue(new XSSFRichTextString("EJERCICIO"));

        XSSFRow rowEnc6Tit2 = resumen.createRow(15);
        XSSFCell cellEnc6Tit2 = rowEnc6Tit2.createCell(1);
        cellEnc6Tit2.setCellStyle(csEncabezados0);
        cellEnc6Tit2.setCellValue(new XSSFRichTextString("USUARIO"));

        XSSFRow rowEnc7Tit2 = resumen.createRow(16);
        XSSFCell cellEnc7Tit2 = rowEnc7Tit2.createCell(1);
        cellEnc7Tit2.setCellStyle(csEncabezados1);
        cellEnc7Tit2.setCellValue(new XSSFRichTextString("ENTIDAD"));

        /* Encabezados Titulo 3 */

        XSSFRow rowEnc1Tit3 = resumen.createRow(18);
        XSSFCell cellEnc1Tit3 = rowEnc1Tit3.createCell(1);
        cellEnc1Tit3.setCellStyle(csEncabezados1);
        cellEnc1Tit3.setCellValue(new XSSFRichTextString("INFORME"));

        XSSFRow rowEnc2Tit3 = resumen.createRow(19);
        XSSFCell cellEnc2Tit3 = rowEnc2Tit3.createCell(1);
        cellEnc2Tit3.setCellStyle(csEncabezados0);
        cellEnc2Tit3.setCellValue(new XSSFRichTextString("PARTIDA"));

        XSSFRow rowEnc3Tit3 = resumen.createRow(20);
        XSSFCell cellEnc3Tit3 = rowEnc3Tit3.createCell(1);
        cellEnc3Tit3.setCellStyle(csEncabezados1);
        cellEnc3Tit3.setCellValue(new XSSFRichTextString("CAPITULO"));

        XSSFRow rowEnc4Tit3 = resumen.createRow(21);
        XSSFCell cellEnc4Tit3 = rowEnc4Tit3.createCell(1);
        cellEnc4Tit3.setCellStyle(csEncabezados0);
        cellEnc4Tit3.setCellValue(new XSSFRichTextString("RUT"));

        /* Encabezado Detalle reporte */

        XSSFRow rowEncTblDet = detalle.createRow(0);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Programa", 0, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Área Transaccional", 1, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Moneda", 2, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Movim.", 3, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Cuenta Contable", 4, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Código BIP", 5, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Denominación Proyecto", 6, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Debe CLP", 7, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Haber CLP", 8, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Debe USB", 9, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Haber USD", 10, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Denominación Cuenta", 11, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Folio Contable", 12, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Tipo Transacción", 13, csEncTblDet);
        creaCeldaEncTablaDetalle(rowEncTblDet, "Cuenta Presupuestaria", 14, csEncTblDet);


        /* Volcamiento de datos */

        /* Informacion General */
        setValorEncabezado(rowEnc1Tit2, csDatosEnc1, datosGeneralInforme.getEstado());
        setValorEncabezado(rowEnc2Tit2, csDatosEnc0, datosGeneralInforme.getTipoInforme());
        setValorEncabezado(rowEnc3Tit2, csDatosEnc1, datosGeneralInforme.getInforme());
        setValorEncabezado(rowEnc4Tit2, csDatosEnc0, datosGeneralInforme.getPeriodo());
        setValorEncabezado(rowEnc5Tit2, csDatosEnc1, datosGeneralInforme.getEjercicio());
        setValorEncabezado(rowEnc6Tit2, csDatosEnc0, datosGeneralInforme.getUsuario());
        setValorEncabezado(rowEnc7Tit2, csDatosEnc1, datosGeneralInforme.getEntidad());

        /* Detalle del Archivo Cargado */
        setValorEncabezado(rowEnc1Tit3, csDatosEnc1, datosDetalleInforme.getUuid());
        setValorEncabezado(rowEnc2Tit3, csDatosEnc0, datosDetalleInforme.getPartida());
        setValorEncabezado(rowEnc3Tit3, csDatosEnc1, datosDetalleInforme.getCapitulo());
        setValorEncabezado(rowEnc4Tit3, csDatosEnc0, datosDetalleInforme.getRut());

        /* Datos tabla detalle */
        if (datosDetalleInforme.getErrorEsquema().equals(0)) {
            int nextIndex;

            for (int i = 0; i < dataInforme.size(); i++) {
                nextIndex = i + 1;

                ReporteValidacionData data = dataInforme.get(i);
                XSSFRow rowInfTblDet = detalle.createRow(nextIndex);

                if (i%2 != 0) {
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getPrograma(), 0, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getAreaTransaccional(), 1, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getMoneda(), 2, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getMovimiento(), 3, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaContable(), 4, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCodigoBIP(), 5, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionProyecto(), 6, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeCLP(), 7, csDatosTblDetDerecha0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberCLP(), 8, csDatosTblDetDerecha0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeUSD(), 9, csDatosTblDetDerecha0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberUSD(), 10, csDatosTblDetDerecha0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionCuenta(), 11, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getFolioContable(), 12, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getTipoTransaccion(), 13, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaPresupuestaria(), 14, csDatosTblDetCentrado0);
                } else {
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getPrograma(), 0, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getAreaTransaccional(), 1, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getMoneda(), 2, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getMovimiento(), 3, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaContable(), 4, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCodigoBIP(), 5, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionProyecto(), 6, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeCLP(), 7, csDatosTblDetDerecha1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberCLP(), 8, csDatosTblDetDerecha1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDebeUSD(), 9, csDatosTblDetDerecha1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getHaberUSD(), 10, csDatosTblDetDerecha1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getDenominacionCuenta(), 11, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getFolioContable(), 12, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getTipoTransaccion(), 13, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, data.getCuentaPresupuestaria(), 14, csDatosTblDetCentrado1);
                }
            }
        }

        /* Ordena Hoja Detalle */
        for (int i = 0; i < 19; i++) {
            detalle.autoSizeColumn(i);
        }

        detalle.setAutoFilter(new CellRangeAddress(0, detalleInforme.getRecordsTotal(), 0, 14));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        book.write(baos);
        baos.close();
        byte[] bArray = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(bArray);

        Map<String, InputStreamResource> result = new HashMap<String, InputStreamResource>();
        result.put(generaNombreArchivo(datosDetalleInforme.getUuid()), new InputStreamResource(is));

        return result;
    }

    public Map<String, InputStreamResource> generaExcelVerTDRMP(URL logoSicogen,
                                                                Integer idArchivo,
                                                                String idSistradoc) throws Exception {

        InformacionGeneralRV datosGeneralInforme = informesService.obtieneInfoGeneralRV(idArchivo);
        InformeSistradocBO datosSistradoc = informesService.getInformeTDR(idSistradoc);
        InformeTDRRV datosXML = informesService.getDatosXMLInformeTDR(idArchivo);
        InformeArchivoDet datosArchivo = informesService.getArchivo(idArchivo);


        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet resumen = book.createSheet();
        XSSFSheet detalle = book.createSheet();

        book.setSheetName(0, "Resumen");
        book.setSheetName(1, "Detalle");

        resumen.setColumnWidth(1, 8000);
        resumen.setColumnWidth(2, 10000);
        resumen.setColumnWidth(3, 10000);
        resumen.setColumnWidth(4, 10000);

        /* Styling */

        /* Backgrounds */
        XSSFColor bckTitulos =  new XSSFColor(new java.awt.Color(63,135,193));
        XSSFColor bckTabla =  new XSSFColor(new java.awt.Color(215,215,217));
        XSSFColor bckBlanco =  new XSSFColor(new java.awt.Color(255,255,255));
        XSSFColor bckError =  new XSSFColor(new java.awt.Color(255, 199, 206));

        /* Fonts */

        XSSFFont fntTituloReporte = book.createFont();
        fntTituloReporte.setFontHeightInPoints((short) 20);
        fntTituloReporte.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntTituloReporte.setBold(Boolean.TRUE);
        fntTituloReporte.setFontName("Arial");

        XSSFFont fntTitulos = book.createFont();
        fntTitulos.setFontHeightInPoints((short) 11);
        fntTitulos.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
        fntTitulos.setBold(Boolean.TRUE);
        fntTitulos.setFontName("Arial");

        XSSFFont fntDatosEncabezados = book.createFont();
        fntDatosEncabezados.setFontHeightInPoints((short) 11);
        fntDatosEncabezados.setColor(new XSSFColor(new java.awt.Color(102, 102, 102)));
        fntDatosEncabezados.setFontName("Arial");

        XSSFFont fntTitulosEncabezados = book.createFont();
        fntTitulosEncabezados.setFontHeightInPoints((short) 11);
        fntTitulosEncabezados.setColor(new XSSFColor(new java.awt.Color(102, 102, 102)));
        fntTitulosEncabezados.setBold(Boolean.TRUE);
        fntTitulosEncabezados.setFontName("Arial");

        XSSFFont fntEncTblDet = book.createFont();
        fntEncTblDet.setFontHeightInPoints((short) 11);
        fntEncTblDet.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntEncTblDet.setBold(Boolean.TRUE);
        fntEncTblDet.setFontName("Arial");

        XSSFFont fntTblDet = book.createFont();
        fntTblDet.setFontHeightInPoints((short) 11);
        fntTblDet.setColor(new XSSFColor(new java.awt.Color(69, 70, 72)));
        fntTblDet.setFontName("Arial");

        /* Cell Styles */

        XSSFCellStyle csTituloReporte = book.createCellStyle();
        csTituloReporte.setFont(fntTituloReporte);
        csTituloReporte.setFillForegroundColor(bckTabla);
        csTituloReporte.setFillBackgroundColor(bckTabla);
        csTituloReporte.setAlignment(HorizontalAlignment.CENTER);
        csTituloReporte.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csTitulos = book.createCellStyle();
        csTitulos.setFont(fntTitulos);
        csTitulos.setFillForegroundColor(bckTitulos);
        csTitulos.setFillBackgroundColor(bckTitulos);
        csTitulos.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        csTitulos.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle csTitulosErr = book.createCellStyle();
        csTitulosErr.setFont(fntTitulos);
        csTitulosErr.setFillForegroundColor(bckTitulos);
        csTitulosErr.setFillBackgroundColor(bckTitulos);
        csTitulosErr.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncabezados0 = book.createCellStyle();
        csEncabezados0.setFillForegroundColor(bckBlanco);
        csEncabezados0.setFillBackgroundColor(bckBlanco);
        csEncabezados0.setFont(fntTitulosEncabezados);
        csEncabezados0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncabezados1 = book.createCellStyle();
        csEncabezados1.setFont(fntTitulosEncabezados);
        csEncabezados1.setFillForegroundColor(bckTabla);
        csEncabezados1.setFillBackgroundColor(bckTabla);
        csEncabezados1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosEnc0 = book.createCellStyle();
        csDatosEnc0.setFont(fntDatosEncabezados);
        csDatosEnc0.setFillForegroundColor(bckBlanco);
        csDatosEnc0.setFillBackgroundColor(bckBlanco);
        csDatosEnc0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosEnc1 = book.createCellStyle();
        csDatosEnc1.setFont(fntDatosEncabezados);
        csDatosEnc1.setFillForegroundColor(bckTabla);
        csDatosEnc1.setFillBackgroundColor(bckTabla);
        csDatosEnc1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csEncTblDet = book.createCellStyle();
        csEncTblDet.setFont(fntEncTblDet);
        csEncTblDet.setFillForegroundColor(bckBlanco);
        csEncTblDet.setFillBackgroundColor(bckBlanco);
        csEncTblDet.setAlignment(HorizontalAlignment.CENTER);
        csEncTblDet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        csEncTblDet.setBorderBottom(BorderStyle.MEDIUM);

        XSSFCellStyle csDatosTblDet0 = book.createCellStyle();
        csDatosTblDet0.setFont(fntTblDet);
        csDatosTblDet0.setFillForegroundColor(bckBlanco);
        csDatosTblDet0.setFillBackgroundColor(bckBlanco);
        csDatosTblDet0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDet1 = book.createCellStyle();
        csDatosTblDet1.setFont(fntTblDet);
        csDatosTblDet1.setFillForegroundColor(bckTabla);
        csDatosTblDet1.setFillBackgroundColor(bckTabla);
        csDatosTblDet1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentrado0 = book.createCellStyle();
        csDatosTblDetCentrado0.setFont(fntTblDet);
        csDatosTblDetCentrado0.setFillForegroundColor(bckBlanco);
        csDatosTblDetCentrado0.setFillBackgroundColor(bckBlanco);
        csDatosTblDetCentrado0.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentrado0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentrado1 = book.createCellStyle();
        csDatosTblDetCentrado1.setFont(fntTblDet);
        csDatosTblDetCentrado1.setFillForegroundColor(bckTabla);
        csDatosTblDetCentrado1.setFillBackgroundColor(bckTabla);
        csDatosTblDetCentrado1.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentrado1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerecha0 = book.createCellStyle();
        csDatosTblDetDerecha0.setFont(fntTblDet);
        csDatosTblDetDerecha0.setFillForegroundColor(bckBlanco);
        csDatosTblDetDerecha0.setFillBackgroundColor(bckBlanco);
        csDatosTblDetDerecha0.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerecha0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerecha1 = book.createCellStyle();
        csDatosTblDetDerecha1.setFont(fntTblDet);
        csDatosTblDetDerecha1.setFillForegroundColor(bckTabla);
        csDatosTblDetDerecha1.setFillBackgroundColor(bckTabla);
        csDatosTblDetDerecha1.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerecha1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetError = book.createCellStyle();
        csDatosTblDetError.setFont(fntTblDet);
        csDatosTblDetError.setFillForegroundColor(bckError);
        csDatosTblDetError.setFillBackgroundColor(bckError);
        csDatosTblDetError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetCentradoError = book.createCellStyle();
        csDatosTblDetCentradoError.setFont(fntTblDet);
        csDatosTblDetCentradoError.setFillForegroundColor(bckError);
        csDatosTblDetCentradoError.setFillBackgroundColor(bckError);
        csDatosTblDetCentradoError.setAlignment(HorizontalAlignment.CENTER);
        csDatosTblDetCentradoError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle csDatosTblDetDerechaError = book.createCellStyle();
        csDatosTblDetDerechaError.setFont(fntTblDet);
        csDatosTblDetDerechaError.setFillForegroundColor(bckError);
        csDatosTblDetDerechaError.setFillBackgroundColor(bckError);
        csDatosTblDetDerechaError.setAlignment(HorizontalAlignment.RIGHT);
        csDatosTblDetDerechaError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        /* Imagen de cabecera */
        resumen.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
        new AddImageExcel().addImageToSheet("B2", resumen, resumen.createDrawingPatriarch(), logoSicogen,
                100, 25, AddImageExcel.OVERLAY_ROW_AND_COLUMN);

        /* Titulos */
        int rowNumTit1 = 7;
        int rowNumTit2 = 9;
        int rowNumTit3 = 17;
        int rowNumTit4 = 26;

        /* Titulo General */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit1, rowNumTit1, 1, 4));
        XSSFRow rowTit1 = resumen.createRow(rowNumTit1);
        XSSFCell cellTit1 = rowTit1.createCell(1);
        cellTit1.setCellStyle(csTituloReporte);
        cellTit1.setCellValue(new XSSFRichTextString("TDR Modificación Presupuestaria"));

        /* Titulo 1 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit2, rowNumTit2, 1, 4));
        XSSFRow rowTit2 = resumen.createRow(rowNumTit2);
        XSSFCell cellTit2 = rowTit2.createCell(1);
        cellTit2.setCellStyle(csTitulos);
        cellTit2.setCellValue(new XSSFRichTextString("Información General"));

        /* Titulo 2 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit3, rowNumTit3, 1, 4));
        XSSFRow rowTit3 = resumen.createRow(rowNumTit3);
        XSSFCell cellTit3 = rowTit3.createCell(1);
        cellTit3.setCellStyle(csTitulos);
        cellTit3.setCellValue(new XSSFRichTextString("Información SISTRADOC"));

        /* Titulo 3 */
        resumen.addMergedRegion(new CellRangeAddress(rowNumTit4, rowNumTit4, 1, 4));
        XSSFRow rowTit4 = resumen.createRow(rowNumTit4);
        XSSFCell cellTit4 = rowTit4.createCell(1);
        cellTit4.setCellStyle(csTitulos);
        cellTit4.setCellValue(new XSSFRichTextString("Información General XML"));

        /* Titulo Detalle */
        detalle.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        XSSFRow rowTitDetalle = detalle.createRow(0);
        XSSFCell cellTitDetalle = rowTitDetalle.createCell(0);
        cellTitDetalle.setCellStyle(csTitulos);
        cellTitDetalle.setCellValue(new XSSFRichTextString("Detalle del Archivo Cargado"));

        /* Encabezados Titulo 2 */

        resumen.addMergedRegion(new CellRangeAddress(10, 10, 2, 4));
        XSSFRow rowEnc1Tit2 = resumen.createRow(10);
        XSSFCell cellEnc1Tit2 = rowEnc1Tit2.createCell(1);
        cellEnc1Tit2.setCellStyle(csEncabezados1);
        cellEnc1Tit2.setCellValue(new XSSFRichTextString("ESTADO DE VALIDACIÓN"));

        resumen.addMergedRegion(new CellRangeAddress(11, 11, 2, 4));
        XSSFRow rowEnc2Tit2 = resumen.createRow(11);
        XSSFCell cellEnc2Tit2 = rowEnc2Tit2.createCell(1);
        cellEnc2Tit2.setCellStyle(csEncabezados0);
        cellEnc2Tit2.setCellValue(new XSSFRichTextString("TIPO DE INFORME"));

        resumen.addMergedRegion(new CellRangeAddress(12, 12, 2, 4));
        XSSFRow rowEnc3Tit2 = resumen.createRow(12);
        XSSFCell cellEnc3Tit2 = rowEnc3Tit2.createCell(1);
        cellEnc3Tit2.setCellStyle(csEncabezados1);
        cellEnc3Tit2.setCellValue(new XSSFRichTextString("INFORME"));

        resumen.addMergedRegion(new CellRangeAddress(13, 13, 2, 4));
        XSSFRow rowEnc4Tit2 = resumen.createRow(13);
        XSSFCell cellEnc4Tit2 = rowEnc4Tit2.createCell(1);
        cellEnc4Tit2.setCellStyle(csEncabezados0);
        cellEnc4Tit2.setCellValue(new XSSFRichTextString("PERÍODO"));

        resumen.addMergedRegion(new CellRangeAddress(14, 14, 2, 4));
        XSSFRow rowEnc5Tit2 = resumen.createRow(14);
        XSSFCell cellEnc5Tit2 = rowEnc5Tit2.createCell(1);
        cellEnc5Tit2.setCellStyle(csEncabezados1);
        cellEnc5Tit2.setCellValue(new XSSFRichTextString("EJERCICIO"));

        resumen.addMergedRegion(new CellRangeAddress(15, 15, 2, 4));
        XSSFRow rowEnc6Tit2 = resumen.createRow(15);
        XSSFCell cellEnc6Tit2 = rowEnc6Tit2.createCell(1);
        cellEnc6Tit2.setCellStyle(csEncabezados0);
        cellEnc6Tit2.setCellValue(new XSSFRichTextString("USUARIO"));

        resumen.addMergedRegion(new CellRangeAddress(16, 16, 2, 4));
        XSSFRow rowEnc7Tit2 = resumen.createRow(16);
        XSSFCell cellEnc7Tit2 = rowEnc7Tit2.createCell(1);
        cellEnc7Tit2.setCellStyle(csEncabezados1);
        cellEnc7Tit2.setCellValue(new XSSFRichTextString("ENTIDAD"));

        /* Encabezados Titulo 3 */

        resumen.addMergedRegion(new CellRangeAddress(18, 18, 2, 4));
        XSSFRow rowEnc1Tit3 = resumen.createRow(18);
        XSSFCell cellEnc1Tit3 = rowEnc1Tit3.createCell(1);
        cellEnc1Tit3.setCellStyle(csEncabezados1);
        cellEnc1Tit3.setCellValue(new XSSFRichTextString("ANALISTA SISTRADOC"));

        resumen.addMergedRegion(new CellRangeAddress(19, 19, 2, 4));
        XSSFRow rowEnc2Tit3 = resumen.createRow(19);
        XSSFCell cellEnc2Tit3 = rowEnc2Tit3.createCell(1);
        cellEnc2Tit3.setCellStyle(csEncabezados0);
        cellEnc2Tit3.setCellValue(new XSSFRichTextString("ESTADO SICOGEN"));

        resumen.addMergedRegion(new CellRangeAddress(20, 20, 2, 4));
        XSSFRow rowEnc3Tit3 = resumen.createRow(20);
        XSSFCell cellEnc3Tit3 = rowEnc3Tit3.createCell(1);
        cellEnc3Tit3.setCellStyle(csEncabezados1);
        cellEnc3Tit3.setCellValue(new XSSFRichTextString("INFORME"));

        resumen.addMergedRegion(new CellRangeAddress(21, 21, 2, 4));
        XSSFRow rowEnc4Tit3 = resumen.createRow(21);
        XSSFCell cellEnc4Tit3 = rowEnc4Tit3.createCell(1);
        cellEnc4Tit3.setCellStyle(csEncabezados0);
        cellEnc4Tit3.setCellValue(new XSSFRichTextString("ENTIDAD EMISORA"));

        resumen.addMergedRegion(new CellRangeAddress(22, 22, 2, 4));
        XSSFRow rowEnc5Tit3 = resumen.createRow(22);
        XSSFCell cellEnc5Tit3 = rowEnc5Tit3.createCell(1);
        cellEnc5Tit3.setCellStyle(csEncabezados1);
        cellEnc5Tit3.setCellValue(new XSSFRichTextString("TIPO DOCUMENTO"));

        resumen.addMergedRegion(new CellRangeAddress(23, 23, 2, 4));
        XSSFRow rowEnc6Tit3 = resumen.createRow(23);
        XSSFCell cellEnc6Tit3 = rowEnc6Tit3.createCell(1);
        cellEnc6Tit3.setCellStyle(csEncabezados0);
        cellEnc6Tit3.setCellValue(new XSSFRichTextString("NRO. DOCUMENTO"));

        resumen.addMergedRegion(new CellRangeAddress(24, 24, 2, 4));
        XSSFRow rowEnc7Tit3 = resumen.createRow(24);
        XSSFCell cellEnc7Tit3 = rowEnc7Tit3.createCell(1);
        cellEnc7Tit3.setCellStyle(csEncabezados1);
        cellEnc7Tit3.setCellValue(new XSSFRichTextString("FECHA DOCUMENTO"));

        resumen.addMergedRegion(new CellRangeAddress(25, 25, 2, 4));
        XSSFRow rowEnc8Tit3 = resumen.createRow(25);
        XSSFCell cellEnc8Tit3 = rowEnc8Tit3.createCell(1);
        cellEnc8Tit3.setCellStyle(csEncabezados0);
        cellEnc8Tit3.setCellValue(new XSSFRichTextString("FECHA RECEPCIÓN CGR"));

        /* Encabezados Titulo 4 */

        XSSFRow row1EncsTit4 = resumen.createRow(27);
        XSSFCell cellEnc1Tit4 = row1EncsTit4.createCell(1);
        cellEnc1Tit4.setCellStyle(csEncabezados1);
        cellEnc1Tit4.setCellValue(new XSSFRichTextString("Ejercicio"));
        XSSFCell cellEnc2Tit4 = row1EncsTit4.createCell(3);
        cellEnc2Tit4.setCellStyle(csEncabezados1);
        cellEnc2Tit4.setCellValue(new XSSFRichTextString("ID DECRETO"));

        XSSFRow row2EncsTit4 = resumen.createRow(28);
        XSSFCell cellEnc3Tit4 = row2EncsTit4.createCell(1);
        cellEnc3Tit4.setCellStyle(csEncabezados0);
        cellEnc3Tit4.setCellValue(new XSSFRichTextString("Mes"));
        XSSFCell cellEnc4Tit4 = row2EncsTit4.createCell(3);
        cellEnc4Tit4.setCellStyle(csEncabezados0);
        cellEnc4Tit4.setCellValue(new XSSFRichTextString("TIPO DE REGISTRO"));

        XSSFRow row3EncsTit4 = resumen.createRow(29);
        XSSFCell cellEnc5Tit4 = row3EncsTit4.createCell(1);
        cellEnc5Tit4.setCellStyle(csEncabezados1);
        cellEnc5Tit4.setCellValue(new XSSFRichTextString("Día"));
        XSSFCell cellEnc6Tit4 = row3EncsTit4.createCell(3);
        cellEnc6Tit4.setCellStyle(csEncabezados1);
        cellEnc6Tit4.setCellValue(new XSSFRichTextString("NÚMERO REGISTRO"));

        XSSFRow row4EncsTit4 = resumen.createRow(30);
        XSSFCell cellEnc7Tit4 = row4EncsTit4.createCell(1);
        cellEnc7Tit4.setCellStyle(csEncabezados0);
        cellEnc7Tit4.setCellValue(new XSSFRichTextString("SECTOR RESPONSABLE"));
        XSSFCell cellEnc8Tit4 = row4EncsTit4.createCell(3);
        cellEnc8Tit4.setCellStyle(csEncabezados0);
        cellEnc8Tit4.setCellValue(new XSSFRichTextString("FECHA REGISTRO"));

        XSSFRow row5EncsTit4 = resumen.createRow(31);
        XSSFCell cellEnc9Tit4 = row5EncsTit4.createCell(1);
        cellEnc9Tit4.setCellStyle(csEncabezados1);
        cellEnc9Tit4.setCellValue(new XSSFRichTextString("MONTO TOTAL"));
        XSSFCell cellEnc10Tit4 = row5EncsTit4.createCell(3);
        cellEnc10Tit4.setCellStyle(csEncabezados1);


        /* Volcamiento de datos */

        /* Informacion General */
        setValorEncabezado(rowEnc1Tit2, csDatosEnc1, datosGeneralInforme.getEstado());
        setValorEncabezado(rowEnc2Tit2, csDatosEnc0, datosGeneralInforme.getTipoInforme());
        setValorEncabezado(rowEnc3Tit2, csDatosEnc1, datosGeneralInforme.getInforme());
        setValorEncabezado(rowEnc4Tit2, csDatosEnc0, datosGeneralInforme.getPeriodo());
        setValorEncabezado(rowEnc5Tit2, csDatosEnc1, datosGeneralInforme.getEjercicio());
        setValorEncabezado(rowEnc6Tit2, csDatosEnc0, datosGeneralInforme.getUsuario());
        setValorEncabezado(rowEnc7Tit2, csDatosEnc1, datosGeneralInforme.getEntidad());

        /* Informacion SISTRADOC */
        setValorEncabezado(rowEnc1Tit3, csDatosEnc1, datosSistradoc.getAnalista());
        setValorEncabezado(rowEnc2Tit3, csDatosEnc0, datosSistradoc.getEstadoSicogen());
        setValorEncabezado(rowEnc3Tit3, csDatosEnc1, datosSistradoc.getInforme());
        setValorEncabezado(rowEnc4Tit3, csDatosEnc0, datosSistradoc.getEntidadEmisora());
        setValorEncabezado(rowEnc5Tit3, csDatosEnc1, datosSistradoc.getTipoDocumento());
        setValorEncabezado(rowEnc6Tit3, csDatosEnc0, String.valueOf(datosSistradoc.getNroDocumento()));
        setValorEncabezado(rowEnc7Tit3, csDatosEnc1, datosSistradoc.getFechaDocumento());
        setValorEncabezado(rowEnc8Tit3, csDatosEnc0, datosSistradoc.getFechaRecepcionCGR());

        /* Informacion General XML */
        setValorEncabezado(row1EncsTit4, csDatosEnc1, datosXML.getEjercicio(), 2);
        setValorEncabezado(row1EncsTit4, csDatosEnc1, datosXML.getIdDecreto(), 4);
        setValorEncabezado(row2EncsTit4, csDatosEnc0, datosXML.getMes(), 2);
        setValorEncabezado(row2EncsTit4, csDatosEnc0, datosXML.getTipoRegistro(), 4);
        setValorEncabezado(row3EncsTit4, csDatosEnc1, datosXML.getDia(), 2);
        setValorEncabezado(row3EncsTit4, csDatosEnc1, datosXML.getNumeroRegistro(), 4);
        setValorEncabezado(row4EncsTit4, csDatosEnc0, datosXML.getSectorResponsable(), 2);
        setValorEncabezado(row4EncsTit4, csDatosEnc0, datosXML.getFechaRegistro(), 4);
        setValorEncabezado(row5EncsTit4, csDatosEnc1, datosXML.getMontoTotal(), 2);
        setValorEncabezado(row5EncsTit4, csDatosEnc1, "", 4);

        int idxDetalle = 1;

        for (int i = 0; i < datosXML.getDetalle().size(); i++) {
            DetalleInformeTDRMP detalleInf = datosXML.getDetalle().get(i);

            detalle.addMergedRegion(new CellRangeAddress(idxDetalle, idxDetalle, 1, 6));
            XSSFRow rowEnc1Det = detalle.createRow(idxDetalle);
            XSSFCell cellEnc1Det = rowEnc1Det.createCell(0);
            cellEnc1Det.setCellStyle(csEncabezados1);
            cellEnc1Det.setCellValue(new XSSFRichTextString("PARTIDA"));

            detalle.addMergedRegion(new CellRangeAddress(idxDetalle + 1, idxDetalle + 1, 1, 6));
            XSSFRow rowEnc2Det = detalle.createRow(idxDetalle + 1);
            XSSFCell cellEnc2Det = rowEnc2Det.createCell(0);
            cellEnc2Det.setCellStyle(csEncabezados0);
            cellEnc2Det.setCellValue(new XSSFRichTextString("CAPITULO"));

            detalle.addMergedRegion(new CellRangeAddress(idxDetalle + 2, idxDetalle + 2, 1, 6));
            XSSFRow rowEnc3Det = detalle.createRow(idxDetalle + 2);
            XSSFCell cellEnc3Det = rowEnc3Det.createCell(0);
            cellEnc3Det.setCellStyle(csEncabezados1);
            cellEnc3Det.setCellValue(new XSSFRichTextString("PROGRAMA"));

            setValorEncabezado(rowEnc1Det, csDatosEnc1, detalleInf.getPartida(), 1);
            setValorEncabezado(rowEnc2Det, csDatosEnc0, detalleInf.getCapitulo(), 1);
            setValorEncabezado(rowEnc3Det, csDatosEnc1, detalleInf.getPrograma(), 1);

            XSSFRow rowEncTblDet = detalle.createRow(idxDetalle + 4);
            creaCeldaEncTablaDetalle(rowEncTblDet, "SUBTITULO", 0, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "ITEM", 1, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "ASIGNACION", 2, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "SUBASIGNACION", 3, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "DENOMINACION", 4, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "INCREMENTO", 5, csEncTblDet);
            creaCeldaEncTablaDetalle(rowEncTblDet, "REDUCCION", 6, csEncTblDet);

            List<CuentaTDRMP> cuentas = detalleInf.getCuentasTDRMP();
            idxDetalle = idxDetalle + 5;

            for (int j = 0; j < cuentas.size(); j++) {
                CuentaTDRMP cuenta = cuentas.get(j);
                XSSFRow rowInfTblDet = detalle.createRow(idxDetalle);

                if (j%2 == 0) {
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getSubtitulo(), 0, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getItem(), 1, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getAsignacion(), 2, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getSubasignacion(), 3, csDatosTblDetCentrado1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getDenominacion(), 4, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getIncremento(), 5, csDatosTblDet1);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getReduccion(), 6, csDatosTblDet1);
                } else {
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getSubtitulo(), 0, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getItem(), 1, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getAsignacion(), 2, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getSubasignacion(), 3, csDatosTblDetCentrado0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getDenominacion(), 4, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getIncremento(), 5, csDatosTblDet0);
                    creaCeldaDataTablaDetalle(rowInfTblDet, cuenta.getReduccion(), 6, csDatosTblDet0);
                }

                idxDetalle = idxDetalle + 1;
            }

            idxDetalle = idxDetalle + 1;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        book.write(baos);
        baos.close();
        byte[] bArray = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(bArray);

        Map<String, InputStreamResource> result = new HashMap<String, InputStreamResource>();
        result.put(generaNombreArchivo(datosArchivo.getListaDetalle().get(0).getNombre()), new InputStreamResource(is));

        return result;
    }
}
