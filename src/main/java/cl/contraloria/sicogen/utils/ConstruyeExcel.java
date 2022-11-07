package cl.contraloria.sicogen.utils;

import cl.contraloria.sicogen.model.Disponibilidades;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

public class ConstruyeExcel {



    public InputStream excel(Disponibilidades repDisp) throws Exception{

        XSSFWorkbook wb2 = new XSSFWorkbook();
        XSSFSheet x = wb2.createSheet();
        InputStream inputStream = null;

        x.setColumnWidth(1, 2000);
        x.setColumnWidth(2, 5000);
        x.setColumnWidth(3, 5000);
        x.setColumnWidth(4, 5000);
        x.setColumnWidth(5, 6000);
        x.setColumnWidth(6, 5000);
        x.setColumnWidth(7, 5000);
        x.setColumnWidth(8, 5000);
        x.setColumnWidth(9, 5000);
        x.setColumnWidth(10, 5000);
        x.setColumnWidth(11, 5000);
        x.setColumnWidth(12, 5000);
        x.setColumnWidth(13, 5000);


        XSSFColor bckTitulo =  new XSSFColor(new java.awt.Color(215,215,217));
        XSSFColor bckSubtit =  new XSSFColor(new java.awt.Color( 69, 70, 72));
        XSSFColor bckEncIzq =  new XSSFColor(new java.awt.Color(248,248,248));
        XSSFColor bckEncDer =  new XSSFColor(new java.awt.Color(215,215,217));
        XSSFColor bckTitDet =  new XSSFColor(new java.awt.Color(  0,102,184));
        XSSFColor bckSTitDt =  new XSSFColor(new java.awt.Color( 60,130,200));
        XSSFColor bckDetImp =  new XSSFColor(new java.awt.Color(188,207,237));
        XSSFColor bckDetPar =  new XSSFColor(new java.awt.Color(255,255,255));
        XSSFColor bckErrImp =  new XSSFColor(new java.awt.Color(147,150,249));
        XSSFColor bckErrPar =  new XSSFColor(new java.awt.Color(147,150,249));

        XSSFColor bckEncDerDisp =  new XSSFColor(new java.awt.Color(215,215,217));

        XSSFCellStyle csTitulo = wb2.createCellStyle();
        XSSFCellStyle csSubTit = wb2.createCellStyle();
        XSSFCellStyle csEncIzq = wb2.createCellStyle();
        XSSFCellStyle csEncDer = wb2.createCellStyle();
        XSSFCellStyle csTitDet = wb2.createCellStyle();
        XSSFCellStyle csSTitDt = wb2.createCellStyle();
        XSSFCellStyle csDetImp = wb2.createCellStyle();
        XSSFCellStyle csDetPar = wb2.createCellStyle();
        XSSFCellStyle csErrImp = wb2.createCellStyle();
        XSSFCellStyle csErrPar = wb2.createCellStyle();
        XSSFCellStyle csEncDerDisp = wb2.createCellStyle();
        XSSFCellStyle csEncDerDispCB = wb2.createCellStyle();
        XSSFCellStyle csEncDerDispCBBB = wb2.createCellStyle();
        XSSFCellStyle csEncTitulo	= wb2.createCellStyle();

        XSSFFont headerFontBOLDWEIGHT = wb2.createFont();
        headerFontBOLDWEIGHT.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        headerFontBOLDWEIGHT.setColor(IndexedColors.BLACK.getIndex());
        csEncDerDispCBBB.setFont(headerFontBOLDWEIGHT);
        XSSFFont headerFont = wb2.createFont();
        headerFont.setColor(IndexedColors.BLACK.getIndex());
        csEncDerDispCB.setFont(headerFont);

        XSSFFont headerFontTitulo=wb2.createFont();
        headerFontTitulo.setColor(IndexedColors.BLACK.getIndex());
        headerFontTitulo.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        csEncTitulo.setFont(headerFontTitulo);
        csEncTitulo.setFillBackgroundColor(bckTitulo);
        csEncTitulo.setFillForegroundColor(bckTitulo);
        csEncTitulo.setFillPattern(CellStyle.SOLID_FOREGROUND);
        csEncTitulo.setAlignment(HorizontalAlignment.CENTER);

        csSubTit.setFillForegroundColor(bckSubtit);
        csSubTit.setFillPattern(CellStyle.SOLID_FOREGROUND);
        csEncIzq.setFillForegroundColor(bckEncIzq);
        csEncIzq.setFillPattern(CellStyle.SOLID_FOREGROUND);
        csEncDer.setFillForegroundColor(bckEncDer);
        csEncDer.setFillPattern(CellStyle.SOLID_FOREGROUND);
        csTitDet.setFillForegroundColor(bckTitDet);
        csTitDet.setFillPattern(CellStyle.SOLID_FOREGROUND);
        csSTitDt.setFillForegroundColor(bckSTitDt);
        csSTitDt.setFillPattern(CellStyle.SOLID_FOREGROUND);
        csDetPar.setFillForegroundColor(bckDetImp);
        csDetPar.setFillPattern(CellStyle.SOLID_FOREGROUND);
        csDetImp.setFillForegroundColor(bckDetPar);
        csDetImp.setFillPattern(CellStyle.SOLID_FOREGROUND);
        csErrPar.setFillForegroundColor(bckErrPar);
        csErrPar.setFillPattern(CellStyle.SOLID_FOREGROUND);
        csErrImp.setFillForegroundColor(bckErrImp);
        csErrImp.setFillPattern(CellStyle.SOLID_FOREGROUND);

        csEncDerDisp.setFillForegroundColor(bckEncDerDisp);

        csEncDer.setFillPattern(CellStyle.SOLID_FOREGROUND);

        XSSFFont fntTitulo = wb2.createFont();

        fntTitulo.setFontHeightInPoints((short) 12);
        fntTitulo.setColor(new XSSFColor(new java.awt.Color(69,70,72)));
        fntTitulo.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        csTitulo.setFont(fntTitulo);
        csTitulo.setFillForegroundColor(bckTitulo);
        csTitulo.setFillBackgroundColor(bckTitulo);
        csTitulo.setFillPattern(CellStyle.SOLID_FOREGROUND);
        csTitulo.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        XSSFFont fntSubTit = wb2.createFont();
        fntSubTit.setFontHeightInPoints((short) 9);
        fntSubTit.setColor(new XSSFColor(new java.awt.Color(  0,  0,  0)));
        fntSubTit.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        csSubTit.setFont(fntSubTit);
        csTitDet.setFont(fntSubTit);
        csSTitDt.setFont(fntSubTit);

        XSSFFont fntEncInf = wb2.createFont();
        fntEncInf.setFontHeightInPoints((short) 9);
        fntEncInf.setColor(new XSSFColor(new java.awt.Color(255,255,255)));
        fntEncInf.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        csEncIzq.setFont(fntEncInf);
        csEncDer.setFont(fntEncInf);

        XSSFFont fntDetalle = wb2.createFont();
        fntDetalle.setFontHeightInPoints((short) 8);
        fntDetalle.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        csDetImp.setFont(fntDetalle);
        csDetPar.setFont(fntDetalle);

        csTitulo.setWrapText(true);

        XSSFCellStyle csSubTitulo = wb2.createCellStyle();
        XSSFFont fntSubTitulo = wb2.createFont();

        fntSubTitulo.setFontHeightInPoints((short) 8);
        fntSubTitulo.setColor(new XSSFColor(new java.awt.Color(255,255,255)));
        fntSubTitulo.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        csSubTitulo.setFont(fntSubTitulo);
        csSubTitulo.setFillBackgroundColor(bckSubtit);

        XSSFCellStyle csTexto = wb2.createCellStyle();
        XSSFFont fntTexto = wb2.createFont();

        fntTexto.setFontHeightInPoints((short) 8);
        fntTexto.setColor(new XSSFColor(new java.awt.Color(21,21,21)));
        fntTexto.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        csTexto.setFont(fntTexto);
        csTexto.setFillBackgroundColor(bckSubtit);

        wb2.setSheetName(0, "Reporte de Cuadratura");


        // setParSistema(new ParametrosSistema());

        XSSFRow rowLogo = x.createRow(1);
        //  String imageLogo=                    getParSistema().getArtefPDF() + "cuadros.png";
        //Image img = Image.getInstance(String.format(getParSistema().getArtefPDF()+"cuadros.png", "0120903"));
        //java.awt.Image image = new ImageIcon(this.getClass().getResource("/img/cuadros.png")).getImage();
        try{
            //x.addMergedRegion(new CellRangeAddress(1, 1, 1, 1));
            //new AddImageExcel().addImageToSheet("B2", x, x.createDrawingPatriarch(),
              //      new File(String.valueOf(image)).toURI().toURL(), 20, 5,
                //    AddImageExcel.OVERLAY_ROW_AND_COLUMN);

            XSSFCell celDet = rowLogo.createCell(2); //HSSFCell celEnc2 = rowDet.createCell(1);
            x.addMergedRegion(new CellRangeAddress(1, 1, 2,8));
            celDet.getCellStyle().setWrapText(true);
            celDet.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
            celDet.setCellStyle(csTitulo);

            celDet.setCellValue(new XSSFRichTextString("  Reporte de Cuadratura"));

        }catch(Exception ex){
            ex.printStackTrace();
        }

        int row=2;
        x.addMergedRegion(new CellRangeAddress(row, row, 1, 8)); //firstRow, LastRow, firstCol, lastCol
        XSSFRow rowTit = x.createRow(row);
        XSSFCell celTit = rowTit.createCell(1);
        celTit.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_LEFT);
        celTit.setCellValue(new XSSFRichTextString("\t	Cuadratura Disponibilidades"));
        //setNombreArchivo( "RV" +getsInforme()+" "+cabRep.getPeriodoName()+getsEjercicio() + ".xls");

        row+=2;
        String[][] mCab={
                {"Información General"			,null	,null							},
                {"Entidad"			, repDisp.getNombreEntidad(),null},
                {"Ejercicio"		, repDisp.getEjercicioNombre(),null},
                {"Periodo"			, repDisp.getPeriodoNombre()	,null	},
                {"Resultado"		, repDisp.getResultado(),null	},
        };
        //DATOS ENCANBEZADO
        for (int i = 0; i< 5; i++) {
            XSSFRow rowEnc = x.createRow(row);
            if(i==0){
                XSSFCell celEnc1 = rowEnc.createCell(1);
                celEnc1.setCellStyle(csSubTit);
                celEnc1.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
                x.addMergedRegion(new CellRangeAddress(row, row, 1, 5));
                celEnc1.setCellValue(new XSSFRichTextString(mCab[i][0]));

            }else{
                for(int b=0;b<3;b++){
                    int[] pos={1,3,4,5,6,7,8,9,10,11};

                    if(mCab[i][b]!=null){
                        XSSFCell celDetEnc = rowEnc.createCell(pos[b]);
                        switch (b) {
                            case 0:
                            case 3:
                                celDetEnc.setCellStyle(csEncIzq);
                                x.addMergedRegion(new CellRangeAddress(row, row, 1, 2));
                                break;
                            case 1:
                            case 4:
                                celDetEnc.setCellStyle(csEncDer);
                                x.addMergedRegion(new CellRangeAddress(row, row, 3, 5));
                                break;
                            default:
                                celDetEnc.setCellStyle(csEncDer);
                                break;
                        }

                        if(pos[b]==6) {
                            x.addMergedRegion(new CellRangeAddress(row, row, 6, 7));
                        }
                        celDetEnc.setCellValue(new XSSFRichTextString(mCab[i][b]));
                    }
                }
            }
            row++;
        }
        row++;

        XSSFRow rowEncCuentax = x.createRow(row);
        XSSFCell celEncCuentax = rowEncCuentax.createCell(1);

        x.addMergedRegion(new CellRangeAddress(row, row, 1, 8));
        celEncCuentax.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);
        celEncCuentax.setCellStyle(csEncTitulo);
        celEncCuentax.setCellValue(new XSSFRichTextString("Cuadro Resumen"));

        row++;


        XSSFRow rowEncDetDisp = x.createRow(row);
        XSSFCell celempty = rowEncDetDisp.createCell(2);
        celempty.setCellStyle(csEncDerDispCB);


        x.addMergedRegion(new CellRangeAddress(row, row, 2, 4));
        celempty.setCellStyle(csEncDerDispCBBB);
        celempty.setCellValue(new XSSFRichTextString("Descripción"));


        XSSFCell celEnc1Disp = rowEncDetDisp.createCell(5);
        celEnc1Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celEnc1Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);
        x.addMergedRegion(new CellRangeAddress(row, row, 5, 6));
        celEnc1Disp.setCellValue(new XSSFRichTextString(""));

        XSSFCell celEnc2Disp = rowEncDetDisp.createCell(7);
        celEnc2Disp.setCellStyle(csEncDerDispCBBB);
        x.addMergedRegion(new CellRangeAddress(row, row, 7, 8));
        celEnc2Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_LEFT);
        celEnc2Disp.setCellValue(new XSSFRichTextString("Monto (en pesos)"));
        row++;


        XSSFCellStyle CSStempFont=csEncDerDispCB;

        rowEncDetDisp = x.createRow(row);
        celempty = rowEncDetDisp.createCell(2);
        celempty.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celempty.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        celempty.setCellStyle(CSStempFont);
        x.addMergedRegion(new CellRangeAddress(row, row, 2, 4));
        celempty.setCellValue(new XSSFRichTextString("DEBITOS RECURSOS DISPONIBLES (111 debitos)"));

        celEnc1Disp = rowEncDetDisp.createCell(5);
        celEnc1Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celEnc1Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        x.addMergedRegion(new CellRangeAddress(row, row, 5, 6));
        celEnc1Disp.setCellValue(new XSSFRichTextString(""));

        celEnc2Disp = rowEncDetDisp.createCell(7);
        celEnc2Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        x.addMergedRegion(new CellRangeAddress(row, row, 7, 8));
        celEnc2Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_LEFT);
        celEnc2Disp.setCellStyle(CSStempFont);
        celEnc2Disp.setCellValue(new XSSFRichTextString(Convertidor.formatearNumero(repDisp.getRecDispDebe())));
        row++;

        rowEncDetDisp = x.createRow(row);
        celempty = rowEncDetDisp.createCell(2);
        celempty.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celempty.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        celempty.setCellStyle(CSStempFont);
        x.addMergedRegion(new CellRangeAddress(row, row, 2, 4));
        celempty.setCellValue(new XSSFRichTextString("CREDITOS RECURSOS DISPONIBLES (111 creditos)"));

        celEnc1Disp = rowEncDetDisp.createCell(5);
        celEnc1Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celEnc1Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        x.addMergedRegion(new CellRangeAddress(row, row, 5, 6));
        celEnc1Disp.setCellValue(new XSSFRichTextString(""));

        celEnc2Disp = rowEncDetDisp.createCell(7);
        celEnc2Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        x.addMergedRegion(new CellRangeAddress(row, row, 7, 8));
        celEnc2Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_LEFT);
        celEnc2Disp.setCellStyle(CSStempFont);
        celEnc2Disp.setCellValue(new XSSFRichTextString(Convertidor.formatearNumero(repDisp.getRecDispHaber())));
        row++;


        rowEncDetDisp = x.createRow(row);
        celempty = rowEncDetDisp.createCell(2);
        celempty.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celempty.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        celempty.setCellStyle(csEncDerDispCBBB);
        x.addMergedRegion(new CellRangeAddress(row, row, 2, 4));
        celempty.setCellValue(new XSSFRichTextString("VARIACION DE DISPONIBILIDADES"));

        celEnc1Disp = rowEncDetDisp.createCell(5);
        celEnc1Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celEnc1Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        x.addMergedRegion(new CellRangeAddress(row, row, 5, 6));
        celEnc1Disp.setCellValue(new XSSFRichTextString(""));

        celEnc2Disp = rowEncDetDisp.createCell(7);
        celEnc2Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        x.addMergedRegion(new CellRangeAddress(row, row, 7, 8));
        celEnc2Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_LEFT);
        celEnc2Disp.setCellStyle(csEncDerDispCBBB);
        celEnc2Disp.setCellValue(new XSSFRichTextString(Convertidor.formatearNumero(repDisp.getRecDispDebe() -repDisp.getRecDispHaber() )));
        row++;


        rowEncDetDisp = x.createRow(row);
        celempty = rowEncDetDisp.createCell(2);
        celempty.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celempty.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        celempty.setCellStyle(CSStempFont);
        x.addMergedRegion(new CellRangeAddress(row, row, 2, 4));
        celempty.setCellValue(new XSSFRichTextString("PERCIBIDO (115 creditos)"));

        celEnc1Disp = rowEncDetDisp.createCell(5);
        celEnc1Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celEnc1Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);


        x.addMergedRegion(new CellRangeAddress(row, row, 5, 6));
        celEnc1Disp.setCellValue(new XSSFRichTextString(""));

        celEnc2Disp = rowEncDetDisp.createCell(7);
        celEnc2Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        x.addMergedRegion(new CellRangeAddress(row, row, 7, 8));
        celEnc2Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_LEFT);
        celEnc2Disp.setCellStyle(CSStempFont);
        celEnc2Disp.setCellValue(new XSSFRichTextString(Convertidor.formatearNumero(repDisp.getCtaCobHaber() )));
        row++;

        rowEncDetDisp = x.createRow(row);
        celempty = rowEncDetDisp.createCell(2);
        celempty.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celempty.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        celempty.setCellStyle(CSStempFont);
        x.addMergedRegion(new CellRangeAddress(row, row, 2, 4));
        celempty.setCellValue(new XSSFRichTextString("PAGADO (215 debitos)"));

        celEnc1Disp = rowEncDetDisp.createCell(5);
        celEnc1Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celEnc1Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        x.addMergedRegion(new CellRangeAddress(row, row, 5, 6));
        celEnc1Disp.setCellValue(new XSSFRichTextString(""));

        celEnc2Disp = rowEncDetDisp.createCell(7);
        celEnc2Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        x.addMergedRegion(new CellRangeAddress(row, row, 7, 8));
        celEnc2Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_LEFT);
        celEnc2Disp.setCellStyle(CSStempFont);
        celEnc2Disp.setCellValue(new XSSFRichTextString(Convertidor.formatearNumero(repDisp.getCtaPagDebe())));
        row++;

        rowEncDetDisp = x.createRow(row);
        celempty = rowEncDetDisp.createCell(2);
        celempty.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celempty.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        celempty.setCellStyle(CSStempFont);
        x.addMergedRegion(new CellRangeAddress(row, row, 2, 4));
        celempty.setCellValue(new XSSFRichTextString("ACREEDORES (credito complementarias)"));

        celEnc1Disp = rowEncDetDisp.createCell(5);
        celEnc1Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celEnc1Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        x.addMergedRegion(new CellRangeAddress(row, row, 5, 6));
        celEnc1Disp.setCellValue(new XSSFRichTextString(""));

        celEnc2Disp = rowEncDetDisp.createCell(7);
        celEnc2Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        x.addMergedRegion(new CellRangeAddress(row, row, 7, 8));
        celEnc2Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_LEFT);
        celEnc2Disp.setCellStyle(CSStempFont);
        celEnc2Disp.setCellValue(new XSSFRichTextString(Convertidor.formatearNumero(repDisp.getCtaComHaber())));
        row++;

        rowEncDetDisp = x.createRow(row);
        celempty = rowEncDetDisp.createCell(2);
        celempty.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celempty.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        celempty.setCellStyle(CSStempFont);
        x.addMergedRegion(new CellRangeAddress(row, row, 2, 4));
        celempty.setCellValue(new XSSFRichTextString("DEUDORES (debito complementarias)"));

        celEnc1Disp = rowEncDetDisp.createCell(5);
        celEnc1Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celEnc1Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        x.addMergedRegion(new CellRangeAddress(row, row, 5, 6));
        celEnc1Disp.setCellValue(new XSSFRichTextString(""));

        celEnc2Disp = rowEncDetDisp.createCell(7);
        celEnc2Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        x.addMergedRegion(new CellRangeAddress(row, row, 7, 8));
        celEnc2Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_LEFT);
        celEnc2Disp.setCellStyle(CSStempFont);
        celEnc2Disp.setCellValue(new XSSFRichTextString(Convertidor.formatearNumero(repDisp.getCtaComDebe())));
        row++;

        rowEncDetDisp = x.createRow(row);
        celempty = rowEncDetDisp.createCell(2);
        celempty.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celempty.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        celempty.setCellStyle(csEncDerDispCBBB);
        x.addMergedRegion(new CellRangeAddress(row, row, 2, 4));
        celempty.setCellValue(new XSSFRichTextString("VARIACION DE DISPONIBILIDADES (COMPROBACION)"));

        celEnc1Disp = rowEncDetDisp.createCell(5);
        celEnc1Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        celEnc1Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);

        x.addMergedRegion(new CellRangeAddress(row, row, 5, 6));
        celEnc1Disp.setCellValue(new XSSFRichTextString(""));

        celEnc2Disp = rowEncDetDisp.createCell(7);
        celEnc2Disp.getCellStyle().setFillPattern(CellStyle.SOLID_FOREGROUND);
        x.addMergedRegion(new CellRangeAddress(row, row, 7, 8));
        celEnc2Disp.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_LEFT);
        celEnc2Disp.setCellStyle(csEncDerDispCBBB);
        celEnc2Disp.setCellValue(new XSSFRichTextString(Convertidor.formatearNumero((repDisp.getCtaCobHaber() -repDisp.getCtaPagDebe()) +
                (repDisp.getCtaComHaber() -repDisp.getCtaComDebe() ))));
        row++;


        /*****************************		FIN Cuadro Resumen						****************************************/
        /*******************************************************************************************************************/


        /*******************************************************************************************************************/
        /*****************************		RECURSOS DISPONIBLES				********************************************/

        row++;

        XSSFRow rowEncCuentaR = x.createRow(row);
        XSSFCell celEncCuentaR = rowEncCuentaR.createCell(1);

        x.addMergedRegion(new CellRangeAddress(row, row, 1, 8));
        celEncCuentaR.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);
        celEncCuentaR.setCellStyle(csEncTitulo);
        celEncCuentaR.setCellValue(new XSSFRichTextString("RECURSOS DISPONIBLES"));
        row++;
        for(int r=0;r<repDisp.getRecDisp().size();r++){
            if(r==0){


                XSSFRow rowEncDetLinea = x.createRow(row);
                XSSFCell celEnc1Linea = rowEncDetLinea.createCell(1);

                rowEncDetLinea = x.createRow(row);
                String[] titulos={"Línea","Cuenta","DENOMINACIÓN","DÉBITO","CRÉDITO"};

                int[] posDet={	1,2,3, 7, 8};
                for(int c=0;c<titulos.length;c++){
                    celEnc1Linea = rowEncDetLinea.createCell(posDet[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
                    celEnc1Linea.setCellStyle(csEncDerDispCBBB);
                    celEnc1Linea.getCellStyle().setWrapText(true);
                    celEnc1Linea.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
                    if(c==3){
                        x.addMergedRegion(new CellRangeAddress(row, row, 3, 6));
                    }
                    celEnc1Linea.setCellValue(new XSSFRichTextString(titulos[c]));
                }
                row++;
            }

        }

        for(int b=0;b<repDisp.getRecDisp().size();b++){

            String[]detalle={repDisp.getRecDisp().get(b).getLinea(),
                    repDisp.getRecDisp().get(b).getCodCta(),
                    repDisp.getRecDisp().get(b).getCuenta(),
                    String.valueOf(repDisp.getRecDisp().get(b).getDebe()),
                    String.valueOf(repDisp.getRecDisp().get(b).getHaber())};

            XSSFRow rowDet = x.createRow(row);
            int[] posDet={	1,2,3,7,8};
            for(int c=0;c<detalle.length;c++){
                XSSFCell celDet = rowDet.createCell(posDet[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
                celDet.getCellStyle().setWrapText(true);
                celDet.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
                if(c==3){
                    x.addMergedRegion(new CellRangeAddress(row, row, 3, 6));
                }
                celDet.setCellStyle(csEncDerDispCB);
                celDet.setCellValue(new XSSFRichTextString(detalle[c]));
            }
            row++;
        }

        XSSFRow rowEncDetLinea = x.createRow(row);
        XSSFCell celEnc1Linea = rowEncDetLinea.createCell(1);

        rowEncDetLinea = x.createRow(row);
        String[] titulos={"","","TOTALES DEBITOS Y CREDITOS",String.valueOf(repDisp.getRecDispDebe()),String.valueOf(repDisp.getRecDispHaber())};

        int[] posDet={	1,2,3, 7, 8};
        for(int c=0;c<titulos.length;c++){
            celEnc1Linea = rowEncDetLinea.createCell(posDet[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
            celEnc1Linea.setCellStyle(csEncDerDispCBBB);
            celEnc1Linea.getCellStyle().setWrapText(true);
            celEnc1Linea.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
            if(c==3){
                x.addMergedRegion(new CellRangeAddress(row, row, 3, 6));

            }
            celEnc1Linea.setCellValue(new XSSFRichTextString(titulos[c]));

        }
        row++;

        /*****************************		FIN RECURSOS DISPONIBLES			********************************************/
        /*******************************************************************************************************************/
        /*****************************		CUENTAS POR COBRAR					********************************************/
        row++;

        rowEncCuentaR = x.createRow(row);
        celEncCuentaR = rowEncCuentaR.createCell(1);

        x.addMergedRegion(new CellRangeAddress(row, row, 1, 8));
        celEncCuentaR.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);
        celEncCuentaR.setCellStyle(csEncTitulo);
        celEncCuentaR.setCellValue(new XSSFRichTextString("CUENTAS POR COBRAR"));
        row++;
        for(int r=0;r<repDisp.getCtaxCob().size();r++){
            if(r==0){


                rowEncDetLinea = x.createRow(row);
                celEnc1Linea = rowEncDetLinea.createCell(1);

                rowEncDetLinea = x.createRow(row);
                String[] titulosCB={"Línea","Cuenta","DENOMINACIÓN","CRÉDITO"};

                int[] posDetCB={1,2,3,8};
                for(int c=0;c<titulosCB.length;c++){
                    celEnc1Linea = rowEncDetLinea.createCell(posDetCB[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
                    celEnc1Linea.setCellStyle(csEncDerDispCBBB);
                    celEnc1Linea.getCellStyle().setWrapText(true);
                    celEnc1Linea.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
                    if(c==3){
                        x.addMergedRegion(new CellRangeAddress(row, row, 3, 7));
                    }
                    celEnc1Linea.setCellValue(new XSSFRichTextString(titulosCB[c]));
                }
                row++;
            }
            String[]detalle={repDisp.getCtaxCob().get(r).getLinea(),
                    repDisp.getCtaxCob().get(r).getCodCta(),
                    repDisp.getCtaxCob().get(r).getCuenta(),
                    String.valueOf(repDisp.getCtaxCob().get(r).getHaber())};

            XSSFRow rowDet = x.createRow(row);
            int[] posDetCB={	1,2,3,8};
            for(int c=0;c<detalle.length;c++){
                XSSFCell celDet = rowDet.createCell(posDetCB[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
                celDet.getCellStyle().setWrapText(true);
                celDet.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
                if(c==3){
                    x.addMergedRegion(new CellRangeAddress(row, row, 3, 7));
                }
                celDet.setCellStyle(csEncDerDispCB);
                celDet.setCellValue(new XSSFRichTextString(detalle[c]));
            }
            row++;

        }


        rowEncDetLinea = x.createRow(row);
        celEnc1Linea = rowEncDetLinea.createCell(1);

        rowEncDetLinea = x.createRow(row);
        String[] titulosCBT={"","","TOTALES PERCIBIDO",String.valueOf(repDisp.getCtaCobHaber())};

        int[] posDetCBT={	1,2,3, 8};
        for(int c=0;c<titulosCBT.length;c++){
            celEnc1Linea = rowEncDetLinea.createCell(posDetCBT[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
            celEnc1Linea.setCellStyle(csEncDerDispCBBB);
            celEnc1Linea.getCellStyle().setWrapText(true);
            celEnc1Linea.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
            if(c==3){
                x.addMergedRegion(new CellRangeAddress(row, row, 3, 7));

            }
            celEnc1Linea.setCellValue(new XSSFRichTextString(titulosCBT[c]));

        }
        row++;
        /*****************************		FIN CUENTAS POR COBRAR				********************************************/
        /*******************************************************************************************************************/
        /*****************************		CUENTAS POR PAGAR					********************************************/
        row++;

        rowEncCuentaR = x.createRow(row);
        celEncCuentaR = rowEncCuentaR.createCell(1);

        x.addMergedRegion(new CellRangeAddress(row, row, 1, 8));
        celEncCuentaR.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);
        celEncCuentaR.setCellStyle(csEncTitulo);
        celEncCuentaR.setCellValue(new XSSFRichTextString("CUENTAS POR PAGAR"));
        row++;
        for(int r=0;r<repDisp.getCtaxPag().size();r++){
            if(r==0){


                rowEncDetLinea = x.createRow(row);
                celEnc1Linea = rowEncDetLinea.createCell(1);

                rowEncDetLinea = x.createRow(row);
                String[] titulosCB={"Línea","Cuenta","DENOMINACIÓN","DÉBITO"};

                int[] posDetCB={1,2,3,8};
                for(int c=0;c<titulosCB.length;c++){
                    celEnc1Linea = rowEncDetLinea.createCell(posDetCB[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
                    celEnc1Linea.setCellStyle(csEncDerDispCBBB);
                    celEnc1Linea.getCellStyle().setWrapText(true);
                    celEnc1Linea.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
                    if(c==3){
                        x.addMergedRegion(new CellRangeAddress(row, row, 3, 7));
                    }
                    celEnc1Linea.setCellValue(new XSSFRichTextString(titulosCB[c]));
                }
                row++;
            }
            String[]detalle={repDisp.getCtaxPag().get(r).getLinea(),
                    repDisp.getCtaxPag().get(r).getCodCta(),
                    repDisp.getCtaxPag().get(r).getCuenta(),
                    String.valueOf(repDisp.getCtaxPag().get(r).getDebe())};

            XSSFRow rowDet = x.createRow(row);
            int[] posDetCB={	1,2,3,8};
            for(int c=0;c<detalle.length;c++){
                XSSFCell celDet = rowDet.createCell(posDetCB[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
                celDet.getCellStyle().setWrapText(true);
                celDet.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
                if(c==3){
                    x.addMergedRegion(new CellRangeAddress(row, row, 3, 7));
                }
                celDet.setCellStyle(csEncDerDispCB);
                celDet.setCellValue(new XSSFRichTextString(detalle[c]));
            }
            row++;

        }


        rowEncDetLinea = x.createRow(row);
        celEnc1Linea = rowEncDetLinea.createCell(1);

        rowEncDetLinea = x.createRow(row);
        String[] titulosCPT={"","","TOTALES PAGADO",String.valueOf(repDisp.getCtaPagDebe())};

        int[] posDetCPT={	1,2,3, 8};
        for(int c=0;c<titulosCPT.length;c++){
            celEnc1Linea = rowEncDetLinea.createCell(posDetCPT[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
            celEnc1Linea.setCellStyle(csEncDerDispCBBB);
            celEnc1Linea.getCellStyle().setWrapText(true);
            celEnc1Linea.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
            if(c==3){
                x.addMergedRegion(new CellRangeAddress(row, row, 3, 7));

            }
            celEnc1Linea.setCellValue(new XSSFRichTextString(titulosCPT[c]));

        }
        row++;
        /*****************************		FIN CUENTAS POR PAGAR				********************************************/
        /*******************************************************************************************************************/
        /*****************************		CUENTAS COMPLEMENTARIAS				********************************************/
        row++;

        rowEncCuentaR = x.createRow(row);
        celEncCuentaR = rowEncCuentaR.createCell(1);

        x.addMergedRegion(new CellRangeAddress(row, row, 1, 8));
        celEncCuentaR.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);
        celEncCuentaR.setCellStyle(csEncTitulo);
        celEncCuentaR.setCellValue(new XSSFRichTextString("CUENTAS COMPLEMENTARIAS"));
        row++;
        for(int r=0;r<repDisp.getRecDisp().size();r++){
            if(r==0){


                rowEncDetLinea = x.createRow(row);
                celEnc1Linea = rowEncDetLinea.createCell(1);

                rowEncDetLinea = x.createRow(row);
                String[] titulosComp={"Línea","Cuenta","DENOMINACIÓN","DÉBITO","CRÉDITO"};

                int[] posDetComp={	1,2,3, 7, 8};
                for(int c=0;c<titulosComp.length;c++){
                    celEnc1Linea = rowEncDetLinea.createCell(posDetComp[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
                    celEnc1Linea.setCellStyle(csEncDerDispCBBB);
                    celEnc1Linea.getCellStyle().setWrapText(true);
                    celEnc1Linea.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
                    if(c==3){
                        x.addMergedRegion(new CellRangeAddress(row, row, 3, 6));
                    }
                    celEnc1Linea.setCellValue(new XSSFRichTextString(titulosComp[c]));
                }
                row++;
            }

        }

        for(int b=0;b<repDisp.getCtaComp().size();b++){

            String[]detalle={repDisp.getCtaComp().get(b).getLinea(),
                    repDisp.getCtaComp().get(b).getCodCta(),
                    repDisp.getCtaComp().get(b).getCuenta(),
                    String.valueOf(repDisp.getCtaComp().get(b).getDebe()),
                    String.valueOf(repDisp.getCtaComp().get(b).getHaber())};

            XSSFRow rowDet = x.createRow(row);
            int[] posDetComp={	1,2,3,7,8};
            for(int c=0;c<detalle.length;c++){
                XSSFCell celDet = rowDet.createCell(posDetComp[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
                celDet.getCellStyle().setWrapText(true);
                celDet.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
                if(c==3){
                    x.addMergedRegion(new CellRangeAddress(row, row, 3, 6));
                }
                celDet.setCellStyle(csEncDerDispCB);
                celDet.setCellValue(new XSSFRichTextString(detalle[c]));
            }
            row++;
        }

        rowEncDetLinea = x.createRow(row);
        celEnc1Linea = rowEncDetLinea.createCell(1);

        rowEncDetLinea = x.createRow(row);
        String[] titulosComp={"","","TOTALES DEUDORES Y ACREEDORES",String.valueOf(repDisp.getCtaComDebe()),String.valueOf(repDisp.getCtaComHaber())};

        int[] posDetComp={	1,2,3, 7, 8};
        for(int c=0;c<titulosComp.length;c++){
            celEnc1Linea = rowEncDetLinea.createCell(posDetComp[c]); //HSSFCell celEnc2 = rowDet.createCell(1);
            celEnc1Linea.setCellStyle(csEncDerDispCBBB);
            celEnc1Linea.getCellStyle().setWrapText(true);
            celEnc1Linea.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
            if(c==3){
                x.addMergedRegion(new CellRangeAddress(row, row, 3, 6));

            }
            celEnc1Linea.setCellValue(new XSSFRichTextString(titulosComp[c]));

        }
        row++;
        try{
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            wb2.write(boas);
            inputStream =new ByteArrayInputStream(boas.toByteArray());

        }catch(Exception e){
            e.fillInStackTrace();

        }

        return inputStream;
    }
}
